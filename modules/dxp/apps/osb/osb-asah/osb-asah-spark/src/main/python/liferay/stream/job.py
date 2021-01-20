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

from liferay.common.spark import BaseSparkJob
from liferay.stream.processor import JournalDataFrameProcessor

from pyspark.sql import types as T

class CuratorSparkJob(BaseSparkJob):
	def _process_batch(self, analytics_events_data_frame, batch_id):
		analytics_events_data_frame.persist()

		for processor in [JournalDataFrameProcessor(batch_id, self)]:
			processor.process(analytics_events_data_frame)

		analytics_events_data_frame.unpersist()

	def run(self):
		data_stream_reader = self.spark_session.readStream

		data_stream_reader.schema(
			T.StructType([
				T.StructField("applicationId", T.StringType(), False),
				T.StructField("channelId", T.StringType(), False),
				T.StructField("eventDate", T.StringType(), False),
				T.StructField("eventId", T.StringType(), False),
				T.StructField(
					"eventProperties",
					T.MapType(T.StringType(), T.StringType(), True), True
				),
				T.StructField("projectId", T.StringType(), False),
				T.StructField("userId", T.StringType(), False),
				T.StructField("variantId", T.StringType(), True)
			])
		).json(
			'{}/**/analytics_events.json'.format(
				self.spark_application_configuration.get(
					'google.storage.path.analytics-events'
				)
			)
		)

		analytics_events_data_frame = data_stream_reader.load()

		data_stream_writer = analytics_events_data_frame.writeStream

		data_stream_writer = data_stream_writer.trigger(
			processingTime='10 seconds'
		).foreachBatch(
			self._process_batch
		).option(
			'checkpointLocation',
			self.spark_application_configuration.get(
				'google.storage.path.stream-curator-checkpoint'
			)
		).outputMode(
			'append'
		)

		streaming_query = data_stream_writer.start()

		streaming_query.awaitTermination()