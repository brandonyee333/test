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

from elasticsearch import Elasticsearch
from pyspark.sql import SparkSession

class ElasticsearchBridge:
	def __init__(self, spark_application):
		self.elasticsearch = Elasticsearch()
		self.spark_application = spark_application

	def _get_index_path(self, collection_name, namespace):
		args = self.spark_application.args

		index_path = "{}_{}_{}".format(
		    args.lcp_project_id, namespace, collection_name
		)

		return index_path.lower()

	def read(self, collection_name, namespace):
		spark_session = self.spark_application.spark_session

		data_frame_reader = spark_session.read

		return data_frame_reader.load(
		    format='es', path=self._get_index_path(collection_name, namespace)
		)

	def update_document(self, collection_name, document, id, namespace):
		self.elasticsearch.update(
		    body={'doc': document},
		    doc_type=collection_name,
		    id=id,
		    index=self._get_index_path(collection_name, namespace)
		)

	def write(self, collection_name, data_frame, mode, namespace):
		data_frame_writer = data_frame.write

		data_frame_writer.format('es').mode(mode).save(
		    '{}/{}'.format(
		        self._get_index_path(collection_name, namespace),
		        collection_name
		    )
		)