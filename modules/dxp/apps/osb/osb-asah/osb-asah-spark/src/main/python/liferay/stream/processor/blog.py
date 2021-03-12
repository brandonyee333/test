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
					F.col('eventId') == 'blogViewed',
					F.col('event_date')
				)
			).over(window)
		)

		data_frame = data_frame.filter(F.col('eventId') != 'blogViewed')

		data_frame = data_frame.withColumn(
			'previous_blog_viewed_to_current_event_delta',
			F.col('event_date').cast('long') -
			F.col('previous_blog_viewed_event_date').cast('long')
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
				F.col('event_date')
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
				class_name='com.liferay.blogs.model.BlogsEntry'
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

		data_frame = data_frame.filter(
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
			how='left',
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'variantId',
				'normalized_event_date', 'primaryKey'
			]
		).fillna(0)