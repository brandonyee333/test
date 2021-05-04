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
from liferay.event_ingestion.job import EventIngestionSparkJob

from pyspark import SparkConf

import argparse
import sys


class EventIngestionApplication(BaseSparkApplication):

	def __init__(self):
		super(EventIngestionApplication, self).__init__()
		self.job_id = self.args.job_id
		self.job_run_id = self.args.job_run_id

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.event_ingestion.EventIngestionApplication '
			'--configuration <Configuration Path> '
			'--job-id <Job ID> '
			'--job-parameters <Job Parameters> '
			'--job-run-data-period <Job Run Data Period> '
			'--job-run-id <Job Run ID> '
			'--job-run-step <Job Run Step> '
			'--lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('-configuration', required=True)
		argument_parser.add_argument('-job-id', required=True)
		argument_parser.add_argument('-job-parameters', required=True)
		argument_parser.add_argument('-job-run-data-period', required=True)
		argument_parser.add_argument('-job-run-id', required=True)
		argument_parser.add_argument('-job-run-step', required=True)
		argument_parser.add_argument('-lcp-project-id', required=True)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName('Event Ingestion')
		spark_conf.set(
			'fs.s3a.access.key', self.configuration.get('aws.access.key.id')
		)
		spark_conf.set(
			'fs.s3a.secret.key',
			self.configuration.get('aws.secret.access.key')
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

	def _create_spark_job_pipeline(self):
		jobs = []
		jobs.append(EventIngestionSparkJob(self))

		return SparkJobPipeline(jobs)

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()
		
		spark_job_pipeline.run()