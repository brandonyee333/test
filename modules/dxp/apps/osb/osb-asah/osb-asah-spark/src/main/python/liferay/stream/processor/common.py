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