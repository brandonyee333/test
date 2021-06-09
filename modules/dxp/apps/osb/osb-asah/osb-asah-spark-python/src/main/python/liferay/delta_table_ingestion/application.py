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


from liferay.common.spark import BaseSparkApplication, \
	SparkJobPipeline
from liferay.delta_table_ingestion.job import DeltaTableIngestionSparkJob

from pyspark import SparkConf

import argparse
import sys

class DeltaTableIngestionApplication(BaseSparkApplication):

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.delta_ingestion.DeltaIngestionApplication '
				  '-configuration <Configuration Path> '
				  '-region <Region> '.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('-configuration', required=True)
		argument_parser.add_argument('-region', required=True)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName(
			self.configuration.get('delta.table.ingestion.name')
		)
		spark_conf.set(
			'spark.jars.packages',
			self.configuration.get('spark.jars.packages')
		)
		spark_conf.set(
			'spark.sql.extensions',
			'io.delta.sql.DeltaSparkSessionExtension'
		)
		spark_conf.set(
			'spark.sql.catalog.spark_catalog',
			'org.apache.spark.sql.delta.catalog.DeltaCatalog'
		)
		
		return spark_conf

	def start(self):
		spark_job_pipeline = SparkJobPipeline([
			DeltaTableIngestionSparkJob(self)
		])
		
		spark_job_pipeline.run()