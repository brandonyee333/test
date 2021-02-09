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
		data_frame = self._filter(
			analytics_events_data_frame
		).withColumn(
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
		)

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

		data_frame.withColumn(
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
		)

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

		data_frame.withColumn(
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
		)

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
			'projectId', 'channelId', 'assetId', 'eventDate', 'userId',
			'variantId'
		).sum(
			'views'
		).withColumnRenamed(
			'sum(views)', 'views'
		)