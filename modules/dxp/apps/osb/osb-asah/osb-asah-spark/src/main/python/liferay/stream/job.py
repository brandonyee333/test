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
from liferay.stream.processor.blog import BlogDataFrameProcessor
from liferay.stream.processor.document_library import DocumentLibraryDataFrameProcessor
from liferay.stream.processor.form import FormDataFrameProcessor, \
	FormFieldDataFrameProcessor
from liferay.stream.processor.journal import JournalDataFrameProcessor
from liferay.stream.processor.page import PageDataFrameProcessor, \
	PageReferrerDataFrameProcessor

from pyspark.sql import types as T

class CuratorSparkJob(BaseSparkJob):

	def _process_batch(self, analytics_events_data_frame, batch_id):
		analytics_events_data_frame.persist()

		processors = [
			BlogDataFrameProcessor(batch_id, 'blogs', self),
			DocumentLibraryDataFrameProcessor(
				batch_id, 'document-libraries', self
			),
			FormDataFrameProcessor(batch_id, 'forms', self),
			FormFieldDataFrameProcessor(batch_id, 'form-fields', self),
			JournalDataFrameProcessor(batch_id, 'journals', self),
			PageDataFrameProcessor(batch_id, 'pages', self),
			PageReferrerDataFrameProcessor(batch_id, 'page-referrers', self),
		]

		for processor in processors:
			processor.process(analytics_events_data_frame)

		analytics_events_data_frame.unpersist()

	def run(self):
		data_stream_reader = self.spark_session.readStream

		data_stream_reader.schema(
			T.StructType([
				T.StructField('applicationId', T.StringType(), False),
				T.StructField('channelId', T.StringType(), False),
				T.StructField(
					'context',
					T.MapType(T.StringType(), T.StringType(), True), True
				),
				T.StructField('eventDate', T.StringType(), False),
				T.StructField('eventId', T.StringType(), False),
				T.StructField(
					'eventProperties',
					T.MapType(T.StringType(), T.StringType(), True), True
				),
				T.StructField('projectId', T.StringType(), False),
				T.StructField('sessionId', T.StringType(), False),
				T.StructField('userId', T.StringType(), False),
				T.StructField('variantId', T.StringType(), True)
			])
		).parquet(
			'{}-{}/**/user_session_events.snappy.parquet'.format(
				self.spark_application_configuration.get(
					'google.storage.path.session-events'
				),
				self.spark_application.args.region
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
			'{}-{}/'.format(
				self.spark_application_configuration.get(
					'google.storage.path.stream-curator-checkpoint'
				),
				self.spark_application.args.region
			)
		).outputMode(
			'append'
		)

		streaming_query = data_stream_writer.start()

		streaming_query.awaitTermination()