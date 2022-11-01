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
from liferay.interest_score.job import IndividualInterestScoreSparkJob, \
	IndividualInterestScoreSparkJob, \
	KeywordsExtractionSparkJob, \
	ReadAnalyticsEventsSparkJob, \
	SegmentInterestScoreSparkJob

from pyspark import SparkConf

import argparse
import sys

class InterestScoreApplication(BaseSparkApplication):

	def __init__(self):
		super(InterestScoreApplication, self).__init__()

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.interest_score.InterestScoreApplication '
			'-configuration <Configuration Path> '
		  	'-job-parameters <Job Parameters> '
			'-lcp-project-id <LCP Project ID>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('-configuration', required=True)
		argument_parser.add_argument(
			'-job-parameters', default='[]', required=False)
		argument_parser.add_argument('-lcp-project-id', required=True)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName('Interest Score')

		spark_conf.set('materializationDataset', self.args.lcp_project_id)
		spark_conf.set(
			'spark.jars.packages',
			self.configuration.get('spark.jars.packages'))
		spark_conf.set(
			'temporaryGcsBucket',
			self.configuration.get('google.storage.path.temporaryGcsBucket'))
		spark_conf.set('viewsEnabled', 'true')

		return spark_conf

	def _create_spark_job_pipeline(self):
		jobs = list()

		jobs.append(ReadAnalyticsEventsSparkJob(self))

		jobs.append(KeywordsExtractionSparkJob(self))

		jobs.append(IndividualInterestScoreSparkJob(self))

		return SparkJobPipeline(jobs)

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		spark_job_pipeline.run()