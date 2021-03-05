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

from pyspark.sql import functions as F

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
		).agg(
			F.sum('views').alias('views')
		)