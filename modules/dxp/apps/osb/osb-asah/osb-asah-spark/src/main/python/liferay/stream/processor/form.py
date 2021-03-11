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

class FormDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

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
					F.col('eventId') == 'formViewed',
					F.col('event_date')
				)
			).over(window)
		)

		data_frame = data_frame.filter(F.col('eventId') == 'formSubmitted')

		data_frame = data_frame.withColumn(
			'previous_form_viewed_to_submit_event_delta',
			F.col('event_date').cast('long') -
			F.col('previous_form_viewed_event_date').cast('long')
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

class FormFieldDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

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
		data_frame = data_frame.withColumn(
			'field_name',
			F.coalesce(
				F.col('eventProperties.fieldName'), F.lit('unknown')
			)
		).withColumn(
			'interactions',
			F.when(
				F.col('eventId') == 'fieldFocused',
				F.lit(1)
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'interaction_duration',
			F.when(
				F.col('eventId') == 'fieldBlurred',
				F.col('eventProperties.focusDuration').cast('long')
			).otherwise(
				F.lit(0)
			)
		).withColumn(
			'page_index',
			F.coalesce(
				F.col('eventProperties.page'), F.lit(0)
			).cast(
				'integer'
			)
		)

		window = Window.partitionBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId'
		).orderBy(
			F.asc('event_date')
		).rowsBetween(
			Window.currentRow + 1, Window.unboundedFollowing
		)

		data_frame = data_frame.withColumn(
			'abandonments',
			F.min(
				F.when(
					F.col('eventId') == 'formSubmitted',
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

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		).withColumn(
			'abandonments',
			F.when(
				F.col('row_number') == 1,
				F.col('abandonments')
			).otherwise(
				F.lit(0)
			)
		)

		data_frame = data_frame.filter(
			F.col('eventId').isin(['fieldBlurred', 'fieldFocused'])
		)

		return data_frame.groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'page_index', 'field_name'
		).agg(
			F.sum('abandonments').alias('abandonments'),
			F.sum('interaction_duration').alias('interaction_duration'),
			F.sum('interactions').alias('interactions')
		)

class FormPageDataFrameProcessor(AnalyticsEventsDataFrameProcessor):

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
		data_frame = data_frame.withColumn(
			'page_index',
			F.coalesce(
				F.col('eventProperties.page'), F.lit(0)
			).cast(
				'integer'
			)
		).withColumn(
			'views', F.lit(1)
		).withColumn(
			'submissions',
			F.when(
				F.col('eventId') == 'formSubmitted',
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

		data_frame = data_frame.withColumn(
			'row_number',
			F.row_number().over(window)
		)

		data_frame = data_frame.filter(
			"row_number = 1 OR eventId = 'formSubmitted'"
		)

		data_frame = data_frame.groupBy(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'page_index'
		).agg(
			F.sum('views').alias('views'),
			F.sum('submissions').alias('submissions')
		)

		return data_frame.withColumn(
			'abandonments',
			F.col('views') - F.col('submissions')
		).drop(
			'submissions'
		)