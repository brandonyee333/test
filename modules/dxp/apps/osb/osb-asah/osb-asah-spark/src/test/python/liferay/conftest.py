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

from pyspark import SparkConf

import os
import pytest
import sys

os.environ['PYSPARK_DRIVER_PYTHON'] = sys.executable
os.environ['PYSPARK_PYTHON'] = sys.executable

sys.path.insert(
	0,
	os.path.dirname(os.path.abspath(__file__)) + '/../../../main/python'
)

os.environ['PYTHONPATH'] = ":".join(sys.path)

@pytest.fixture(scope='session')
def spark_application():
	from liferay.common.configuration import Configuration
	from liferay.common.spark import BaseSparkApplication

	class BaseSparkApplicationTest(BaseSparkApplication):

		def __init__(self):
			self.args = ''
			self.configuration = Configuration()
			self.spark_session = self._build_spark_session()

		def _create_spark_conf(self):
			spark_conf = SparkConf()

			spark_conf.setAppName('test')
			spark_conf.setMaster('local')

			spark_conf.set('spark.sql.session.timeZone', 'UTC')

			return spark_conf

	return BaseSparkApplicationTest()

@pytest.fixture
def spark_session(spark_application):
	return spark_application.spark_session