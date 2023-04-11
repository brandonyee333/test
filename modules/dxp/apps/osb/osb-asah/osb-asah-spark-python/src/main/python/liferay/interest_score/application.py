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
from liferay.interest_score.job import IdentityInterestScoreBigQueryDataFrameWriterSparkJob, \
	IdentityInterestScorePageBigQueryDataFrameWriterSparkJob, \
	IdentityInterestScorePageSparkJob, \
	IdentityInterestScorePrepareAnalyticsEventsWithKeywordsSparkJob, \
	IdentityInterestScoreSQLCommandSparkJob, \
	IndividualInterestScoreSparkJob, \
	KeywordsExtractionSparkJob, \
	ReadAnalyticsEventsSparkJob, \
	SessionInterestScoreBigQuerydataFrameWriterSparkJob, \
	SessionInterestScoreSQLCommandSparkJob

from pyspark import SparkConf

import argparse
import sys

class InterestScoreApplication(BaseSparkApplication):

	def __init__(self):
		super(InterestScoreApplication, self).__init__()

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.interest_score.InterestScoreApplication '
			'--ac-project-id <AC Project ID> '
			'--configuration <Configuration Path> '
			'--job-parameters <Job Parameters>'.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('--ac-project-id', required=True)
		argument_parser.add_argument('--configuration', required=True)
		argument_parser.add_argument(
			'--job-parameters', default='[]', required=False)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName('Interest Score')

		spark_conf.set('materializationDataset', self.args.ac_project_id)
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

		jobs.append(IdentityInterestScorePrepareAnalyticsEventsWithKeywordsSparkJob(self))

		jobs.append(IdentityInterestScorePageSparkJob(self))

		jobs.append(IdentityInterestScorePageBigQueryDataFrameWriterSparkJob(self))

		jobs.append(IndividualInterestScoreSparkJob(self))

		jobs.append(IdentityInterestScoreSQLCommandSparkJob(self))

		jobs.append(IdentityInterestScoreBigQueryDataFrameWriterSparkJob(self))

		jobs.append(SessionInterestScoreSQLCommandSparkJob(self))

		jobs.append(SessionInterestScoreBigQuerydataFrameWriterSparkJob(self))

		return SparkJobPipeline(jobs)

	def start(self):
		spark_job_pipeline = self._create_spark_job_pipeline()

		spark_job_pipeline.run()