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

import argparse
import logging
import os
import sys

from abc import ABCMeta, \
	abstractmethod

from liferay.commerce.configuration import CommerceConfiguration
from liferay.common.spark import BaseSparkApplication, \
	BaseSparkJob

class BaseCommerceSparkApplication(BaseSparkApplication, metaclass=ABCMeta):

	def __init__(self):
		super(BaseCommerceSparkApplication, self).__init__()

		self.configuration = CommerceConfiguration(self.args.configuration)

		self.log = self._initialize_logging()

		spark_context = self.spark_session.sparkContext

		spark_conf = spark_context.getConf()

		for key, value in spark_conf.getAll():
			if key.startswith('spark.yarn.appMasterEnv.'):
				env_key = key[len('spark.yarn.appMasterEnv.'):]

				os.environ[env_key] = value

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.commerce.recommend.<ApplicationName> '
			'--configuration <Configuration Path> '
			'--lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('--configuration', required=True)
		argument_parser.add_argument('--lcp-project-id', required=True)

		return argument_parser

	@abstractmethod
	def _create_spark_job_pipeline(self):
		pass

	def _initialize_logging(self):
		logging.basicConfig(
			format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
			level=logging.INFO
		)

		return logging.getLogger(self.__class__.__name__)

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		try:
			spark_job_pipeline.run()
		except Exception as e:
			self.log.error(e)

			raise e

class BaseJSONDataFrameReaderSparkJob(BaseSparkJob):

	def __init__(
		self,
		spark_application,
		file_pattern,
		table_name,
		cache=True,
		latest=True
	):
		super(BaseJSONDataFrameReaderSparkJob, self).__init__(spark_application)

		self.cache = cache
		self.file_pattern = file_pattern
		self.latest = latest
		self.table_name = table_name

	def _get_bucket_path(self):
		configuration = self.spark_application_configuration

		bucket_path = '{}/{}/{}/{}/'.format(
			configuration.get('google.storage.bucket'),
			self.spark_application.args.lcp_project_id,
			configuration.get('dataSourceId'), self.file_pattern
		)

		if self.latest:
			jvm = self.spark_session._jvm
			spark_context = self.spark_session.sparkContext

			file_system = jvm.org.apache.hadoop.fs.FileSystem

			file_system_instance = file_system.get(
				jvm.java.net.URI(bucket_path),
				spark_context._jsc.hadoopConfiguration()
			)

			file_status_list = file_system_instance.globStatus(
				jvm.org.apache.hadoop.fs.Path(
					'{}*/_SUCCESS'.format(bucket_path)
				)
			)

			file_status_list_sorted = sorted(
				file_status_list, key=lambda f: f.getModificationTime()
			)

			bucket_path = str(file_status_list_sorted[-1].getPath())

			bucket_path = bucket_path[:-len('_SUCCESS')]

		self.spark_application.log.info(
			'Loading data from: {}'.format(bucket_path)
		)

		return bucket_path

	@abstractmethod
	def _post_process(self, data_frame):
		raise NotImplementedError()

	def run(self):
		data_frame_reader = self.spark_session.read

		data_frame = data_frame_reader.json(self._get_bucket_path())

		data_frame = self._post_process(data_frame)

		data_frame.createOrReplaceTempView(self.table_name)

		if self.cache:
			self.spark_session.catalog.cacheTable(self.table_name)

class BaseJSONDataFrameWriterSparkJob(BaseSparkJob):

	def __init__(self, spark_application, target_folder, table_name):
		super(BaseJSONDataFrameWriterSparkJob, self).__init__(spark_application)

		self.table_name = table_name
		self.target_folder = target_folder

	def _get_bucket_path(self):
		configuration = self.spark_application_configuration

		return '{}/{}/{}/{}/'.format(
			configuration.get('google.storage.bucket'),
			self.spark_application.args.lcp_project_id,
			configuration.get('dataSourceId'), self.target_folder
		)

	def _pre_process(self, data_frame):
		return data_frame

	def run(self):
		data_frame = self.spark_session.table(self.table_name)

		data_frame = self._pre_process(data_frame)

		data_frame_writer = data_frame.write

		data_frame_writer.format("json").mode("overwrite").save(
			self._get_bucket_path()
		)