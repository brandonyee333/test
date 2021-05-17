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

class CustomAssetDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

	def _calculate_read_time(self, data_frame):
		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId'
		).orderBy(
			F.asc('event_date')
		).rowsBetween(
			Window.unboundedPreceding, Window.currentRow - 1
		)

		data_frame = data_frame.withColumn(
			'previous_asset_viewed_event_date',
			F.max(
				F.when(
					F.col('eventId') == 'assetViewed',
					F.col('event_date')
				)
			).over(window)
		)

		data_frame = data_frame.filter(F.col('eventId') != 'assetViewed')

		data_frame = data_frame.withColumn(
			'previous_asset_viewed_to_current_event_delta',
			F.col('event_date').cast('long') -
			F.col('previous_asset_viewed_event_date').cast('long')
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId', 'previous_asset_viewed_event_date'
		).orderBy(
			F.desc('previous_asset_viewed_to_current_event_delta')
		)

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		)

		data_frame = data_frame.filter('row_number = 1')

		data_frame = data_frame.withColumn(
			'last_asset_event_date',
			F.max(
				F.col('event_date')
			).over(
				Window.partitionBy(
					'projectId', 'channelId', 'userId', 'assetId', 'category',
					'variantId'
				)
			)
		)

		return data_frame.groupby(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId', 'last_asset_event_date'
		).sum(
			'previous_asset_viewed_to_current_event_delta'
		).withColumn(
			'normalized_event_date',
			F.date_trunc('hour', 'last_asset_event_date')
		).withColumnRenamed(
			'sum(previous_asset_viewed_to_current_event_delta)',
			'read_time'
		).drop(
			'last_asset_event_date'
		)

	def _calculate_submission_time(self, data_frame):
		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId'
		).orderBy(
			F.asc('event_date')
		).rowsBetween(
			Window.unboundedPreceding, Window.currentRow - 1
		)

		data_frame = data_frame.withColumn(
			'previous_asset_viewed_event_date',
			F.max(
				F.when(
					F.col('eventId') == 'assetViewed',
					F.col('event_date')
				)
			).over(window)
		)

		data_frame = data_frame.filter(F.col('eventId') == 'assetSubmitted')

		data_frame = data_frame.withColumn(
			'previous_asset_viewed_to_submit_event_delta',
			F.col('event_date').cast('long') -
			F.col('previous_asset_viewed_event_date').cast('long')
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId', 'previous_asset_viewed_event_date'
		).orderBy(
			F.desc('previous_asset_viewed_to_submit_event_delta')
		)

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		)

		data_frame = data_frame.filter('row_number = 1')

		return data_frame.groupby(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId', 'normalized_event_date'
		).sum(
			'previous_asset_viewed_to_submit_event_delta'
		).withColumnRenamed(
			'sum(previous_asset_viewed_to_submit_event_delta)',
			'submission_time'
		)

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"""
				(applicationId = 'Custom') AND
				(eventProperties.assetId != '')
			"""
		)

	def _get_asset_id_column(self):
		return F.col('eventProperties.assetId')

	def _process(self, data_frame):
		data_frame = data_frame.withColumn(
			'category',
			F.when(
				F.col('eventProperties.category') != '',
				F.col('eventProperties.category')
			).otherwise(
				F.lit('default')
			)
		)

		data_frame_with_read_time = self._calculate_read_time(
			data_frame
		)

		data_frame_with_submission_time = self._calculate_submission_time(
			data_frame
		)

		data_frame = data_frame.withColumn(
			'clicks',
			F.when(
				F.col('eventId') == 'assetClicked', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'downloads',
			F.when(
				F.col('eventId') == 'assetDownloaded', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'formEnabled',
			F.when(
				F.col('eventProperties.formEnabled') != '',
				F.col('eventProperties.formEnabled').cast('boolean')
			).otherwise(
				F.lit(False)
			)
		).withColumn(
			'submissions',
			F.when(
				F.col('eventId') == 'assetSubmitted', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'views',
			F.when(
				F.col('eventId') == 'assetViewed', F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'category',
			'variantId', 'normalized_event_date',
		).agg(
			F.sum('clicks').alias('clicks'),
			F.sum('downloads').alias('downloads'),
			F.last('formEnabled').alias('formEnabled'),
			F.sum('submissions').alias('submissions'),
			F.sum('views').alias('views')
		)

		data_frame = data_frame.withColumn(
			'views',
			F.greatest(F.col('views'), F.col('submissions'))
		).withColumn(
			'abandonments',
			F.when(
				F.col('formEnabled') == True, F.col('views') - F.col('submissions')
			).otherwise(
				F.lit(0)
			)
		).drop(
			'formEnabled'
		)

		return data_frame.join(
			data_frame_with_read_time,
			how='left',
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'category',
				'variantId', 'normalized_event_date'
			]
		).join(
			data_frame_with_submission_time,
			how='left',
			on=[
				'projectId', 'channelId', 'userId', 'assetId', 'category',
				'variantId', 'normalized_event_date'
			]
		).fillna(0)