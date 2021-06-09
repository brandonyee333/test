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

import logging
import time

class AnalyticsEventsDataFrameProcessor(object):

	__metaclass__ = ABCMeta

	def __init__(self, batch_id, processor_name, spark_job):
		self._batch_id = batch_id
		self._processor_name = processor_name
		self._spark_job = spark_job

		self.log = self._initialize_logging()

	def _initialize_logging(self):
		logging.basicConfig(
			format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
			level=logging.INFO
		)

		return logging.getLogger(self.__class__.__name__)

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
			'row_number = 1'
		)

		score_col_value = F.col('eventProperties.score').cast('float')

		return data_frame.withColumn(
			'ratings', F.when(score_col_value < 0, F.lit(0)).otherwise(F.lit(1))
		).withColumn(
			'ratingsScore', F.when(score_col_value < 0, F.lit(0)).otherwise(score_col_value)
		).select(
			'projectId', 'channelId', 'userId', 'assetId', 'variantId',
			'normalized_event_date', 'primaryKey', 'ratings', 'ratingsScore'
		)

	def _filter(self, analytics_events_data_frame):
		return analytics_events_data_frame

	@abstractmethod
	def _get_asset_id_column(self):
		return

	def _pre_filter(self, analytics_events_data_frame):
		pass

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
					'#', F.col('projectId'), F.col('channelId'),
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
		self.log.info(
			'Start writing {}, batch {}'.format(
				self._processor_name, self._batch_id
			)
		)

		start_time = time.time()

		data_frame_writer = output_data_frame.write

		data_frame_writer.json(
			'{}-{}/{}/{}'.format(
				self._spark_job.spark_application_configuration.get(
					'google.storage.path.stream-curator-output'
				),
				self._spark_job.spark_application.args.region,
				self._processor_name, self._batch_id
			)
		)

		self.log.info(
			'Wrote {}, batch {} in {} seconds'.format(
				self._processor_name, self._batch_id,
				(time.time() - start_time)
			)
		)

	def process(self, analytics_events_data_frame, write=True):
		self.log.info('Start processing {}'.format(self._processor_name))

		start_time = time.time()

		self._pre_filter(analytics_events_data_frame)

		data_frame = self._filter(analytics_events_data_frame)

		data_frame = self._pre_process(data_frame)

		data_frame = self._process(data_frame)

		if write:
			self._write(data_frame)

		self.log.info(
			'Processed {} in {} seconds'.format(
				self._processor_name, (time.time() - start_time)
			)
		)

		return data_frame