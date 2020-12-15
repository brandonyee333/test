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

from pyspark.sql import SparkSession

import os
import pytest
import sys

os.environ['PYSPARK_DRIVER_PYTHON'] = sys.executable
os.environ['PYSPARK_PYTHON'] = sys.executable

sys.path.insert(
    0,
    os.path.dirname(os.path.abspath(__file__)) + '/../../../main/python'
)

@pytest.fixture(scope='session')
def spark():
	spark_session_builder = SparkSession.builder()

	spark_session_builder.appName("test")
	spark_session_builder.master("local")

	return spark_session_builder.getOrCreate()