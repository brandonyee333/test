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

import json

from liferay.common.spark import BaseSparkJob

from pyspark.sql import functions as F, \
	types as T

class DeltaTableIngestionSparkJob(BaseSparkJob):

	def run(self):
		data_stream_reader = self.spark_session.readStream

		data_frame = data_stream_reader.format(
			'pubsublite'
		).option(
			'pubsublite.subscription',
			self.spark_application_configuration.get(
				'google.pubsub.subscription'
			)
		).load()

		message_schema_json = json.loads(
			self.spark_application_configuration.get(
				'delta.table.schema'
			)
		)

		data_frame = data_frame.withColumn(
			'data_str', F.col('data').cast('String')
		).withColumn(
			'message',
			F.from_json(
				F.col('data_str'), T.StructType.fromJson(message_schema_json)
			)
		).select(
			F.col('message.*')
		)

		data_stream_writer = data_frame.writeStream

		streaming_query = data_stream_writer.trigger(
			processingTime=self.spark_application_configuration.get(
				'delta.table.ingestion.trigger'
			)
		).format(
			'delta'
		).option(
			'checkpointLocation',
			'{}-{}/'.format(
				self.spark_application_configuration.get(
					'delta.table.ingestion.checkpoint'
				),
				self.spark_application.args.region
			)
		).outputMode(
			'append'
		).start(
			'{}-{}/'.format(
				self.spark_application_configuration.get(
					'delta.table.path'
				),
				self.spark_application.args.region
			)
		)

		streaming_query.awaitTermination()