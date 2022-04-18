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

from liferay.stream.processor.common import AnalyticsEventsDataFrameProcessor

from pyspark.sql import Window, \
	functions as F

class PageDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _create_page_time_on_page_data_frame(self, analytics_events_data_frame):
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
		).withColumn(
			'primaryKey',
			F.sha2(
				F.concat_ws(
					'#', F.col('projectId'), F.col('channelId'),
					F.col('userId'), F.col('url'), F.col('variantId'),
					F.col('normalized_event_date'),
				),
				256
			)
		).fillna(
			'', subset=['variantId']
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
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'sessionId', 'url', 'variantId',
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
					F.col('eventId') == 'pageViewed',
					F.col('event_date')
				)
			).over(window)
		).withColumn(
			'delta',
			F.col('event_date').cast('long') -
			F.col('previous_page_viewed_event_date').cast('long')
		)

		data_frame = data_frame.filter(
		 	F.col('previous_page_viewed_event_date').isNotNull()
		).filter(
			"eventId != 'pageViewed'"
		)

		data_frame = data_frame.withColumn(
			'row',
			F.row_number().over(
				Window.partitionBy(
					'projectId', 'channelId', 'userId', 'sessionId', 'url',
					'variantId', 'previous_page_viewed_event_date'
				).orderBy(
					F.desc('event_date')
				)
			)
		).filter(
			'row = 1'
		)

		data_frame = data_frame.groupby(
			'projectId', 'channelId', 'userId', 'sessionId', 'url', 'variantId',
			'normalized_event_date', 'primaryKey'
		).agg(
			F.sum('delta').alias('time_on_page')
		)

		data_frame.createOrReplaceTempView(
			'page_time_on_page'
		)

		return data_frame

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

	def _pre_filter(self, analytics_events_data_frame):
		self._create_page_time_on_page_data_frame(analytics_events_data_frame)
		self._create_session_data_frame(analytics_events_data_frame)

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
				F.col('eventId') == 'ctaClicked',
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
				F.col('eventId') == 'pageRead',
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
		).withColumnRenamed(
			'assetId', 'url'
		)

		session_data_frame.unpersist()

		page_time_on_page_data_frame = self._spark_job.spark_session.table(
			'page_time_on_page'
		)

		data_frame = data_frame.join(
			page_time_on_page_data_frame,
			how='left',
			on=[
				'projectId', 'channelId', 'userId', 'sessionId', 'url',
				'variantId', 'normalized_event_date', 'primaryKey'
			]
		).fillna(
			0, subset=['time_on_page']
		)

		page_time_on_page_data_frame.unpersist()

		return data_frame