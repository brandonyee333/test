#
# Copyright (c) 2000-present Liferay, Inc. All rights reserved.
#
# The contents of this file are subject to the terms of the Liferay Enterprise
# Subscription License ("License"). You may not use this file except in
# compliance with the License. You can obtain a copy of the License by
# contacting Liferay, Inc. See the License for the specific language governing
# permissions and limitations under the License, including but not limited to
# distribution rights of the Software.
#

from abc import ABCMeta, \
	abstractmethod

from pyspark.sql import Window, \
	functions as F

class AnalyticsEventsDataFrameProcessor(object):

	__metaclass__ = ABCMeta

	def __init__(self, batch_id, spark_job):
		self._batch_id = batch_id
		self._spark_job = spark_job

	def _calculate_rating_score(self, data_frame):
		data_frame = data_frame.filter(
			"applicationId = 'Ratings' AND eventId = 'VOTE'"
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'assetId', 'variantId', 'eventId'
		).orderBy(
			F.desc('eventDate')
		)

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		).filter(
			"row_number = 1"
		)

		return data_frame.withColumn(
			'ratings', F.lit(1)
		).withColumn(
			'ratingsScore', F.col('eventProperties.score').cast('float')
		).select(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'ratings', 'ratingsScore'
		)

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame

	@abstractmethod
	def _get_asset_id_column(self):
		return

	def _pre_process(self, data_frame):
		return data_frame.withColumn(
			'assetId', self._get_asset_id_column()
		).withColumn(
			'event_date',
			F.to_timestamp(F.col('eventDate'))
		).withColumn(
			'normalized_event_date',
			F.date_trunc('hour', 'event_date')
		).withColumn(
			'primaryKey',
			F.sha2(
				F.concat_ws(
					"#", F.col('projectId'), F.col('channelId'),
					F.col('userId'), F.col('assetId'), F.col('variantId'),
					F.col('normalized_event_date'),
				),
				256
			)
		).fillna(
			'', subset=['variantId']
		)

	def _process(self, data_frame):
		return data_frame

	def _write(self, output_data_frame):
		data_frame_writer = output_data_frame.write

		data_frame_writer.json(
			'{}/journal/{}'.format(
				self._spark_job.spark_application_configuration.get(
					'google.storage.path.stream-curator-output'
				),
				self._batch_id
			)
		)

	def process(self, analytics_events_data_frame, write=True):
		data_frame = self._filter(analytics_events_data_frame)

		data_frame = self._pre_process(data_frame)

		data_frame = self._process(data_frame)

		if write:
			self._write(data_frame)

		return data_frame

class BlogDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _calculate_read_time(self, data_frame):
		data_frame = data_frame.filter("applicationId = 'Blog'")

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId'
		).orderBy(
			F.asc('eventDate')
		).rowsBetween(
			Window.unboundedPreceding, Window.currentRow - 1
		)

		data_frame = data_frame.withColumn(
			'previous_blog_viewed_event_date',
			F.max(
				F.when(
					F.col("eventId") == 'blogViewed',
					F.col("event_date")
				)
			).over(window)
		)

		data_frame = data_frame.filter(F.col('eventId') != 'blogViewed')

		data_frame = data_frame.withColumn(
			'previous_blog_viewed_to_current_event_delta',
			F.col("event_date").cast('long') -
			F.col("previous_blog_viewed_event_date").cast('long')
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'previous_blog_viewed_event_date'
		).orderBy(
			F.desc('previous_blog_viewed_to_current_event_delta')
		)

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		)

		data_frame = data_frame.filter('row_number = 1')

		data_frame = data_frame.withColumn(
			'last_blog_event_date',
			F.max(
				F.col("event_date")
			).over(
				Window.partitionBy(
					'projectId', 'channelId', 'userId', 'assetId', 'variantId'
				)
			)
		)

		return data_frame.groupby(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'last_blog_event_date'
		).sum(
			'previous_blog_viewed_to_current_event_delta'
		).withColumnRenamed(
			'last_blog_event_date', 'event_date'
		).withColumnRenamed(
			'sum(previous_blog_viewed_to_current_event_delta)',
			'read_time'
		)

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(
					(
						(applicationId = 'Comment') AND 
						(eventId = 'posted') AND 
						(eventProperties.className = '{class_name}') AND
						(eventProperties.classPK != '')
					) OR
					(
						(applicationId = 'Blog') AND 
						(
							(eventId = 'blogClicked') OR
							(eventId = 'blogDepthReached') OR
							(eventId = 'blogViewed')
						) AND 
						( 
							(eventProperties.entryId != '')
						)
					) OR 
					(
						(applicationId = 'Ratings') AND 
						(eventId = 'VOTE') AND
						(eventProperties.className = '{class_name}') AND
						(eventProperties.classPK != '') AND
						(
							(eventProperties.ratingType IS NULL) OR
							(eventProperties.ratingType = 'stars')
						)
					)
				)	
			""".format(
				class_name = 'com.liferay.blogs.model.BlogsEntry'
			)
		)

	def _get_asset_id_column(self):
		return F.when(
			F.col('eventId').isin('posted', 'VOTE'),
			F.col('eventProperties.classPK')
		).otherwise(
			F.col('eventProperties.entryId')
		)

	def _process(self, data_frame):
		data_frame_with_rating_score = self._calculate_rating_score(
			data_frame
		)

		data_frame.filter(
			"applicationId != 'Ratings'"
		).withColumn(
			'clicks',
			F.when(
				F.col('eventId') == 'blogClicked', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'comments',
			F.when(
				F.col('eventId') == 'posted', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'views',
			F.when(
				F.col('eventId') == 'blogViewed', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey'
		).agg(
			F.sum('clicks').alias('clicks'),
			F.sum('comments').alias('comments'),
			F.sum('views').alias('views')
		)

		return data_frame.join(
			data_frame_with_rating_score,
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'variantId',
				'normalized_event_date', 'primaryKey'
			],
			how='left'
		).fillna(0)

class DocumentLibraryDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(
					(
						(applicationId = 'Comment') AND 
						(eventId = 'posted') AND 
						(eventProperties.className = '{class_name}') AND
						(eventProperties.classPK != '')
					) OR
					(
						(applicationId = 'Document') AND 
						(
							(eventId = 'documentDownloaded') OR
							(eventId = 'documentPreviewed')
						) AND 
						( 
							(eventProperties.fileEntryId != '')
						)
					) OR 
					(
						(applicationId = 'Ratings') AND 
						(eventId = 'VOTE') AND
						(eventProperties.className = '{class_name}') AND
						(eventProperties.classPK != '') AND
						(
							(eventProperties.ratingType IS NULL) OR
							(eventProperties.ratingType = 'stars')
						)
					)
				)	
			""".format(
				class_name =
					'com.liferay.document.library.kernel.model.DLFileEntry'
			)
		)

	def _get_asset_id_column(self):
		return F.when(
			F.col('eventId').isin('posted', 'VOTE'),
			F.col('eventProperties.classPK')
		).otherwise(
			F.col('eventProperties.fileEntryId')
		)

	def _process(self, data_frame):
		data_frame_with_rating_score = self._calculate_rating_score(data_frame)

		data_frame = data_frame.filter(
			"applicationId != 'Ratings'"
		).withColumn(
			'comments',
			F.when(
				F.col('eventId') == 'posted', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'downloads',
			F.when(
				F.col('eventId') == 'documentDownloaded', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'previews',
			F.when(
				F.col('eventId') == 'documentPreviewed', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey'
		).agg(
			F.sum('comments').alias('comments'),
			F.sum('downloads').alias('downloads'),
			F.sum('previews').alias('previews')
		)

		return data_frame.join(
			data_frame_with_rating_score,
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'variantId',
				'normalized_event_date', 'primaryKey'
			],
			how='left'
		).fillna(0)

class FormDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _calculate_field_metrics(self, data_frame):
		form_field_data_frame = data_frame.withColumn(
			'field_name',
			F.coalesce(
				F.col('eventProperties.fieldName'), F.lit('unknown')
			)
		).withColumn(
			'interactions',
			F.when(
				F.col("eventId") == 'fieldFocused',
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'interaction_duration',
			F.when(
				F.col("eventId") == 'fieldBlurred',
				F.col('eventProperties.focusDuration').cast('long')
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'page_index',
			F.coalesce(
				F.col('eventProperties.page'), F.lit(0)
			).cast(
				"integer"
			)
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId'
		).orderBy(
			F.asc('event_date')
		).rowsBetween(
			Window.currentRow + 1, Window.unboundedFollowing
		)

		form_field_data_frame = form_field_data_frame.withColumn(
			'abandonments',
			F.min(
				F.when(
					F.col("eventId") == 'formSubmitted',
					F.lit(0)
				).otherwise(
					F.lit(1)
				)
			).over(window)
		).fillna(1)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'page_index',
		).orderBy(
			F.desc('event_date')
		)

		form_field_data_frame = form_field_data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		).withColumn(
			'abandonments',
			F.when(
				F.col("row_number") == 1,
				F.col('abandonments')
			).otherwise(
				F.lit(0)
			)
		)

		form_field_data_frame = form_field_data_frame.filter(
			F.col('eventId').isin(['fieldBlurred', 'fieldFocused'])
		)

		return form_field_data_frame.groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'page_index', 'field_name'
		).agg(
			F.sum('abandonments').alias('abandonments'),
			F.sum('interaction_duration').alias('interaction_duration'),
			F.sum('interactions').alias('interactions')
		)

	def _calculate_page_metrics(self, data_frame):
		form_page_data_frame = data_frame.withColumn(
			'page_index',
			F.coalesce(
				F.col('eventProperties.page'), F.lit(0)
			).cast(
				"integer"
			)
		).withColumn(
			'views', F.lit(1)
		).withColumn(
			'submissions',
			F.when(
				F.col("eventId") == 'formSubmitted',
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'page_index'
		).orderBy(
			F.desc('event_date')
		)

		form_page_data_frame = form_page_data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		)

		form_page_data_frame = form_page_data_frame.filter(
			"row_number = 1 OR eventId = 'formSubmitted'"
		)

		form_page_data_frame = form_page_data_frame.groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'page_index'
		).agg(
			F.sum('views').alias('views'),
			F.sum('submissions').alias('submissions')
		)

		return form_page_data_frame.withColumn(
			'abandonments',
			F.col('views') - F.col('submissions')
		).drop(
			'submissions'
		)

	def _calculate_submission_time(self, data_frame):
		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId'
		).orderBy(
			F.asc('event_date')
		).rowsBetween(
			Window.unboundedPreceding, Window.currentRow - 1
		)

		data_frame = data_frame.withColumn(
			'previous_form_viewed_event_date',
			F.max(
				F.when(
					F.col("eventId") == 'formViewed',
					F.col("event_date")
				)
			).over(window)
		)

		data_frame = data_frame.filter(F.col('eventId') == 'formSubmitted')

		data_frame = data_frame.withColumn(
			'previous_form_viewed_to_submit_event_delta',
			F.col("event_date").cast('long') -
			F.col("previous_form_viewed_event_date").cast('long')
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'previous_form_viewed_event_date'
		).orderBy(
			F.desc('previous_form_viewed_to_submit_event_delta')
		)

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		)

		data_frame = data_frame.filter('row_number = 1')

		return data_frame.groupby(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey'
		).sum(
			'previous_form_viewed_to_submit_event_delta'
		).withColumnRenamed(
			'sum(previous_form_viewed_to_submit_event_delta)',
			'submission_time'
		)

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(applicationId = 'Form') AND
				(
					(eventId = 'fieldBlurred') OR
					(eventId = 'fieldFocused') OR
					(eventId = 'formSubmitted') OR
					(eventId = 'formViewed') OR
					(eventId = 'pageViewed')
				) AND
				(eventProperties.formId != '')
			"""
		)

	def _get_asset_id_column(self):
		return F.col('eventProperties.formId')

	def _process(self, data_frame):
		data_frame_with_submission_time = self._calculate_submission_time(
			data_frame
		)

		data_frame = data_frame.withColumn(
			'submissions',
			F.when(
				F.col('eventId') == 'formSubmitted', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'views',
			F.when(
				F.col('eventId') == 'formViewed', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey'
		).agg(
			F.sum('submissions').alias('submissions'),
			F.sum('views').alias('views')
		)

		data_frame = data_frame.withColumn(
			'views',
			F.greatest(F.col('views'), F.col('submissions'))
		)

		data_frame = data_frame.withColumn(
			'abandonments',
			F.col('views') - F.col('submissions')
		)

		return data_frame.join(
			data_frame_with_submission_time,
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'variantId',
				'normalized_event_date', 'primaryKey'
			],
			how='left'
		).fillna(0)

class JournalDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(applicationId = 'WebContent') AND
				(eventId = 'webContentViewed') AND
				(eventProperties.articleId != '')
			"""
		)

	def _get_asset_id_column(self):
		return F.col('eventProperties.articleId')

	def _process(self, data_frame):
		return data_frame.withColumn(
			'views', F.lit(1)
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date'
		).sum(
			F.sum('views').alias('comments')
		)

class PageDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _calculate_time_on_page(self, analytics_events_data_frame):
		data_frame = analytics_events_data_frame.withColumn(
			'event_date',
			F.to_timestamp(F.col('eventDate'))
		).withColumn(
			'normalized_event_date',
			F.date_trunc('hour', 'event_date')
		).withColumn(
			'url',
			F.when(
				F.col('context.canonicalUrl') != '',
				F.col('context.canonicalUrl')
			).otherwise(
				F.col('context.url')
			)
		)

		data_frame = data_frame.filter(
			"url != ''"
		).filter(
			F.col('eventId').isin(
				[
					'blogViewed', 'documentPreviewed', 'formViewed',
					'pageLoaded', 'pageUnloaded',
					'webContentViewed'
				]
			) == False
		).fillna(
			'', subset=['variantId']
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'url', 'variantId',
			'normalized_event_date'
		).orderBy(
			F.asc('eventDate')
		).rowsBetween(
			Window.unboundedPreceding, Window.currentRow - 1
		)

		data_frame = data_frame.withColumn(
			'previous_page_viewed_event_date',
			F.max(
				F.when(
					F.col("eventId") == 'pageViewed',
					F.col("event_date")
				)
			).over(window)

		).withColumn(
			'delta',
			F.col('event_date').cast('long') -
			F.col('previous_page_viewed_event_date').cast('long')
		)

		data_frame = data_frame.filter(
		 	F.col("previous_page_viewed_event_date").isNotNull()
		).filter(
			"eventId != 'pageViewed'"
		)

		data_frame = data_frame.withColumn(
			'row',
			F.row_number().over(
				Window.partitionBy(
					'projectId', 'channelId', 'userId', 'url', 'variantId',
					'previous_page_viewed_event_date'
				).orderBy(
					F.desc('event_date')
				)
			)
		).filter(
			"row = 1"
		)

		data_frame = data_frame.groupby(
			'projectId', 'channelId', 'userId', 'url', 'variantId',
			'normalized_event_date'
		).agg(
			F.sum('delta').alias('time_on_page')
		)

		return data_frame.withColumn(
			'primaryKey',
			F.sha2(
				F.concat_ws(
					"#", F.col('projectId'), F.col('channelId'),
					F.col('userId'), F.col('url'), F.col('variantId'),
					F.col('normalized_event_date'),
				),
				256
			)
		)

	def _create_session_data_frame(self, analytics_events_data_frame):
		session_data_frame = analytics_events_data_frame.withColumn(
			'event_date',
			F.to_timestamp(F.col('eventDate'))
		).withColumn(
			'normalized_event_date',
			F.date_trunc('hour', 'event_date')
		).withColumn(
			'url',
			F.when(
				F.col('context.canonicalUrl') != '',
				F.col('context.canonicalUrl')
			).otherwise(
				F.col('context.url')
			)
		).withColumn(
			'interactions',
			F.when(
				F.col(
					'eventId'
				).isin(
					[
						'blogViewed', 'documentPreviewed', 'formViewed',
						'pageLoaded', 'pageUnloaded', 'pageViewed',
						'webContentViewed'
					]
				), F.lit(0)
			).otherwise(
				F.lit(1)
			)
		).withColumn(
			'page_views',
			F.when(
				F.col('eventId') == 'pageViewed', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		)

		session_data_frame = session_data_frame.sort(
			'normalized_event_date'
		).groupby(
			'projectId', 'channelId', 'sessionId'
		).agg(
			F.sum(
				'interactions'
			).alias(
				'interactions'
			),
			F.sum(
				'page_views'
			).alias(
				'page_views'
			),
			F.last(
				F.col('normalized_event_date')
			).alias(
				'session_end_normalized_event_date'
			),
			F.first(
				F.col('normalized_event_date')
			).alias(
				'session_start_normalized_event_date'
			)
		).withColumn(
			'page_views', F.greatest(F.col('interactions'), F.col('page_views'))
		).withColumn(
			'bounced',
			F.when(
				(F.col('interactions') == 0) & (F.col('page_views') < 2),
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).drop(
			'interactions', 'page_views'
		)

		session_data_frame.createOrReplaceTempView(
			'sessions'
		)

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(applicationId = 'Page') AND
				(context.url != '')
			"""
		)

	def _get_asset_id_column(self):
		return F.when(
			F.col('context.canonicalUrl') != '',
			F.col('context.canonicalUrl')
		).otherwise(
			F.col('context.url')
		)

	def _pre_process(self, data_frame):
		self._create_session_data_frame(data_frame)

		return super(PageDataFrameProcessor, self)._pre_process(data_frame)

	def _process(self, data_frame):
		window = Window.partitionBy(
			'projectId', 'channelId', 'sessionId'
		).orderBy(
			F.asc('eventDate')
		)

		data_frame = data_frame.withColumn(
			'entry_url',
			F.first(
				F.col('assetId')
			).over(
				window.rowsBetween(Window.unboundedPreceding, Window.currentRow)
			)
		).withColumn(
			'entry_url_normalized_event_date',
			F.first(
				F.col('normalized_event_date')
			).over(
				window.rowsBetween(Window.unboundedPreceding, Window.currentRow)
			)
		).withColumn(
			'exit_url',
			F.last(
				F.col('assetId')
			).over(
				window.rowsBetween(Window.currentRow, Window.unboundedFollowing)
			)
		).withColumn(
			'exit_url_normalized_event_date',
			F.last(
				F.col('normalized_event_date')
			).over(
				window.rowsBetween(Window.currentRow, Window.unboundedFollowing)
			)
		)

		data_frame = data_frame.withColumn(
			'cta_clicks',
			F.when(
				F.col("eventId") == 'ctaClicked',
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'entrances',
			F.when(
				(
					(F.col('assetId') == F.col('entry_url')) &
					(
						F.col('normalized_event_date') ==
						F.col('entry_url_normalized_event_date')
					)
				), F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'exits',
			F.when(
				(
					(F.col('assetId') == F.col('exit_url')) &
					(
						F.col('normalized_event_date') ==
						F.col('exit_url_normalized_event_date')
					)
				), F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'views', F.lit(1)
		).withColumn(
			'reads',
			F.when(
				F.col("eventId") == 'pageRead',
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		)

		data_frame = data_frame.withColumn(
			'direct_access',
			F.when(
				(F.col('views') == 1) & (F.col('context.referrer') == ''),
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'indirect_access',
			F.when(
				(F.col('views') == 1) & (F.col('context.referrer') != ''),
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).groupBy(
			'projectId', 'channelId', 'userId', 'sessionId', 'assetId',
			'variantId', 'normalized_event_date', 'primaryKey'
		).agg(
			F.sum('cta_clicks').alias('cta_clicks'),
			F.sum('direct_access').alias('direct_access'),
			F.sum('entrances').alias('entrances'),
			F.sum('exits').alias('exits'),
			F.sum('indirect_access').alias('indirect_access'),
			F.sum('reads').alias('reads'),
			F.sum('views').alias('views')
		)

		session_data_frame = self._spark_job.spark_session.table(
			'sessions'
		).selectExpr(
			'projectId', 'channelId', 'sessionId',
			'session_end_normalized_event_date as normalized_event_date',
			'bounced as bounces'
		).filter(
			F.col('bounces') == 1
		)

		data_frame = data_frame.join(
			session_data_frame,
			how='left',
			on=[
		 		'projectId', 'channelId', 'sessionId', 'normalized_event_date'
		 	]
		).fillna(
			0, subset=['bounces']
		)

		return data_frame.withColumnRenamed(
			'assetId', 'url'
		)

class PageReferrerDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(applicationId = 'Page') AND
				(eventId = 'pageViewed') AND
				(context.url != '')
			"""
		)

	def _get_asset_id_column(self):
		return F.col('context.url')

	def _process(self, data_frame):
		data_frame = data_frame.withColumn(
			'access', F.lit(1)
		).withColumn(
			'referrer', F.col('context.referrer')
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'referrer'
		).agg(
			F.sum('access').alias('access')
		).fillna(
			'', subset=['referrer']
		)

		return data_frame.withColumnRenamed(
			'assetId', 'url'
		).withColumn(
			'acquisition_channel',
			F.expr('acquisition_channel(url, referrer)')
		)