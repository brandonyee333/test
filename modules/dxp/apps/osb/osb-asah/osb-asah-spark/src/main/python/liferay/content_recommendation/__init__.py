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

from ..common.elasticsearch import ElasticsearchBridge
from ..common.spark import BaseSparkApplication, SparkJobPipeline

from .job import *

from pyspark import SparkConf

import argparse
import datetime
import sys

class ContentRecommendationApplication(BaseSparkApplication):
	def __init__(self):
		super(ContentRecommendationApplication, self).__init__()

		self.elasticsearch_bridge = ElasticsearchBridge(self)

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
		    usage='{} liferay.hello_world.ContentRecommendationApplication '
		    '--configuration <Configuration Path> '
		    '--elasticsearch-hostname <Elasticsearch Hostname> '
		    '--job-execution-id <Job Execution ID> '
		    '--lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('--configuration', required=True)
		argument_parser.add_argument('--elasticsearch-hostname', required=True)
		argument_parser.add_argument('--job-execution-id', required=True)
		argument_parser.add_argument('--lcp-project-id', required=True)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName('Content Recommendation')
		spark_conf.set('es.nodes', self.args.elasticsearch_hostname)
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

		return spark_conf

	def _create_spark_job_pipeline(self):
		jobs = []

		if self.job_execution.get('step') == 'DATA_PREPARATION':
			jobs.append(ReadAnalyticsEventsSparkJob(self))

			jobs.append(GenerateUserItemInteractionsSparkJob(self))

			jobs.append(GenerateItemsSparkJob(self))

			jobs.append(WriteItemsSparkJob(self))
			jobs.append(WriteUserItemInteractionsSparkJob(self))

			jobs.append(UpdateJobExecutionStepSparkJob(self))
		else:
			jobs.append(ReadRecommendedItemsSparkJob(self))

			jobs.append(WriteRecommendedItemsSparkJob(self))

			jobs.append(CompleteJobExecutionStepSparkJob(self))

		return SparkJobPipeline(jobs)

	def _get_job_execution(self):
		elasticsearch_bridge = self.elasticsearch_bridge

		return elasticsearch_bridge.get_document(
		    'job-executions', self.args.job_execution_id, 'osbasahfaroinfo'
		)

	def _update_job_execution_status(self, status):
		elasticsearch_bridge = self.elasticsearch_bridge
		job_execution = self.job_execution

		now = datetime.datetime.utcnow()

		elasticsearch_bridge.update_document(
		    'job-executions', {
		        'lastUpdatedDate': now,
		        'status': status
		    }, job_execution.get('id'), 'osbasahfaroinfo'
		)

	def start(self):
		self.job_execution = self._get_job_execution()

		spark_job_pipeline = self._create_spark_job_pipeline()

		try:
			spark_job_pipeline.run()
		except Exception as e:
			self._update_job_execution_status('FAILED')

			raise e
