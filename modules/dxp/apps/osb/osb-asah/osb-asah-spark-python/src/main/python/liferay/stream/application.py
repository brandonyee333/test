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
from liferay.stream.job import CuratorSparkJob
from liferay.stream.udf import AcquisitionChannelFunction

from pyspark import SparkConf

import argparse
import sys

class OSBAsahStreamCuratorApplication(BaseSparkApplication):

	def __init__(self):
		super(OSBAsahStreamCuratorApplication, self).__init__()

		AcquisitionChannelFunction(
			self.spark_session,
			self.configuration.get('acquisition.channel.paid-host-names'),
			self.configuration.get('acquisition.channel.search-host-names'),
			self.configuration.get('acquisition.channel.social-host-names'),
		)

	def _create_argument_parser(self):
		argument_parser = argparse.ArgumentParser(
			usage='{} liferay.stream.OSBAsahStreamCuratorApplication '
			'-configuration <Configuration Path> '
			'-region <Region> '.format(sys.argv[0])
		)

		argument_parser.add_argument('application')
		argument_parser.add_argument('-configuration', required=True)
		argument_parser.add_argument('-region', required=True)

		return argument_parser

	def _create_spark_conf(self):
		spark_conf = SparkConf()

		spark_conf.setAppName('OSB Asah Stream Curator')

		spark_conf.set('spark.sql.session.timeZone', 'UTC')

		return spark_conf

	def start(self):
		spark_job_pipeline = SparkJobPipeline([CuratorSparkJob(self)])

		spark_job_pipeline.run()