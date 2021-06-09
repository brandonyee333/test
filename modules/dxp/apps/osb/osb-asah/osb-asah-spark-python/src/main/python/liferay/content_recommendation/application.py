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

from liferay.common.pubsub import PubSubBridge
from liferay.common.spark import BaseSparkApplication, \
	SparkJobPipeline
from liferay.content_recommendation.job import GenerateItemsSparkJob, \
	GenerateUserItemInteractionsSparkJob, \
	PublishJobRunSparkJob, \
	ReadAnalyticsEventsSparkJob, \
	ReadRecommendedItemsSparkJob, \
	UpdateJobRunStepSparkJob, \
	WriteItemsSparkJob, \
	WriteRecommendedItemsSparkJob, \
	WriteUserItemInteractionsSparkJob

from pyspark import SparkConf

import argparse
import sys

class ContentRecommendationApplication(BaseSparkApplication):

	def __init__(self):
		super(ContentRecommendationApplication, self).__init__()

		self.pub_sub_bridge = PubSubBridge(self)

		self.job_id = self.args.job_id
		self.job_run_id = self.args.job_run_id

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.hello_world.ContentRecommendationApplication '
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

		spark_conf.setAppName('Content Recommendation')
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

		job_run_step = self.args.job_run_step

		if job_run_step == 'DATA_PREPARATION':
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

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		spark_job_pipeline.run()