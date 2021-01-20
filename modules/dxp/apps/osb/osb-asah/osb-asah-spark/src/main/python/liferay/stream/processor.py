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

from pyspark.sql import functions as F

class AnalyticsEventsDataFrameProcessor(object):
	def __init__(self, batch_id, spark_job):
		self._batch_id = batch_id
		self._spark_job = spark_job

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame

	def _process(self, filtered_analytics_events_data_frame):
		return filtered_analytics_events_data_frame

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

	def process(self, analytics_events_data_frame):
		return self._write(
			self._process(self._filter(analytics_events_data_frame))
		)

class JournalDataFrameProcessor(AnalyticsEventsDataFrameProcessor):
	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame.filter(
			"applicationId = 'WebContent'"
		).filter(
			"eventId = 'webContentViewed'"
		).filter(
			"eventProperties.articleId IS NOT NULL"
		).filter(
			"eventProperties.articleId != ''"
		)

	def _process(self, filtered_analytics_events_data_frame):
		return filtered_analytics_events_data_frame.withColumn(
			'assetId', F.col('eventProperties.articleId')
		).withColumn(
			'views', F.lit(1)
		).groupBy(
			'projectId', 'channelId', 'assetId', 'eventDate', 'userId',
			'variantId'
		).sum(
			'views'
		).withColumnRenamed(
			'sum(views)', 'views'
		).withColumn(
			'primaryKey',
			F.sha2(
				F.concat_ws(
					"#", F.col('projectId'),  F.col('assetId'),
					F.col('channelId'), F.col('eventDate'), F.col('userId'),
					F.col('variantId')
				),
				256
			)
		)