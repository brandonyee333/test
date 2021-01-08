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

from ..common.configuration import Configuration

from abc import ABCMeta, abstractmethod

from pyspark import SparkConf
from pyspark.sql import SparkSession

class BaseSparkApplication(object):
	__metaclass__ = ABCMeta

	def __init__(self):
		argument_parser = self._create_argument_parser()

		self.args = argument_parser.parse_args()

		self.configuration = Configuration(self.args.configuration)
		self.spark_session = self._build_spark_session()

	def _build_spark_session(self):
		spark_session_builder = SparkSession.Builder()

		spark_session_builder.config(conf=self._create_spark_conf())

		return spark_session_builder.getOrCreate()

	@abstractmethod
	def _create_argument_parser(self):
		return

	def _create_spark_conf(self):
		return SparkConf()

	@abstractmethod
	def start(self):
		pass

class BaseSparkJob:
	def __init__(self, spark_application):
		self.spark_application = spark_application

	def spark_context(self):
		spark_session = self.spark_session()

		return spark_session.sparkContext

	def spark_session(self):
		return self.spark_application.spark_session

	@abstractmethod
	def run(self):
		pass

class SparkJobPipeline:
	def __init__(self, jobs):
		self.jobs = jobs

	def run(self):
		for job in self.jobs:
			job.run()