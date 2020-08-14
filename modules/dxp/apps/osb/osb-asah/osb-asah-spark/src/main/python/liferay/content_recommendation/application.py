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

from liferay.common.elasticsearch import ElasticsearchBridge
from liferay.common.spark import BaseSparkApplication, SparkJobPipeline
from liferay.common.util import new_utc_date_string
from liferay.content_recommendation.job import GenerateItemsSparkJob, \
GenerateUserItemInteractionsSparkJob, PublishJobRunSparkJob, \
ReadAnalyticsEventsSparkJob, ReadRecommendedItemsSparkJob, \
UpdateJobRunStepSparkJob, WriteItemsSparkJob, WriteRecommendedItemsSparkJob, \
WriteUserItemInteractionsSparkJob

from pyspark import SparkConf

import argparse
import sys

class ContentRecommendationApplication(BaseSparkApplication):
	def __init__(self):
		super(ContentRecommendationApplication, self).__init__()

		self.elasticsearch_bridge = ElasticsearchBridge(self)

		self.job_run = self._get_job_run()

		self.job = self._get_job()

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
		    usage='{} liferay.hello_world.ContentRecommendationApplication '
		    '--configuration <Configuration Path> '
		    '--job-run-id <Job Run ID> '
		    '--lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('--configuration', required=True)
		argument_parser.add_argument('--job-run-id', required=True)
		argument_parser.add_argument('--lcp-project-id', required=True)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName('Content Recommendation')
		spark_conf.set(
		    'es.net.http.auth.user',
		    self.configuration.get('es.net.http.auth.user')
		)
		spark_conf.set(
		    'es.net.http.auth.pass',
		    self.configuration.get('es.net.http.auth.pass')
		)
		spark_conf.set('es.nodes', self.configuration.get('es.nodes'))
		spark_conf.set('es.nodes.wan.only', 'true')
		spark_conf.set('es.port', self.configuration.get('es.port'))
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

		if self.job_run.get('step') == 'DATA_PREPARATION':
			jobs.append(ReadAnalyticsEventsSparkJob(self))

			jobs.append(GenerateUserItemInteractionsSparkJob(self))

			jobs.append(GenerateItemsSparkJob(self))

			jobs.append(WriteItemsSparkJob(self))
			jobs.append(WriteUserItemInteractionsSparkJob(self))

			jobs.append(UpdateJobRunStepSparkJob(self))
		else:
			jobs.append(ReadRecommendedItemsSparkJob(self))

			jobs.append(WriteRecommendedItemsSparkJob(self))

			jobs.append(PublishJobRunSparkJob(self))

		return SparkJobPipeline(jobs)

	def _get_job(self):
		job = self.job_run.get('job')

		return self.elasticsearch_bridge.get_document(
		    'jobs', job.get('id'), 'osbasahfaroinfo'
		)

	def _get_job_run(self):
		return self.elasticsearch_bridge.get_document(
		    'job-runs', self.args.job_run_id, 'osbasahfaroinfo'
		)

	def _update_job_run_status(self, status):
		self.elasticsearch_bridge.update_document(
		    'job-runs', {
		        'lastUpdatedDate': new_utc_date_string(),
		        'status': status
		    }, self.job_run.get('id'), 'osbasahfaroinfo'
		)

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		try:
			spark_job_pipeline.run()
		except Exception as e:
			self._update_job_run_status('FAILED')

			raise e