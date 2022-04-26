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

import os
import pytest
import sys

assert "airflow" not in sys.modules, "No airflow module can be imported before these lines"
tests_directory = os.path.dirname(os.path.realpath(__file__))

os.environ["AIRFLOW__CORE__DAGS_FOLDER"] = os.path.join(tests_directory, "..")
os.environ["AIRFLOW__CORE__UNIT_TEST_MODE"] = "True"

@pytest.fixture()
def reset_db():
	"""
    Resets Airflow db.
    """

	from airflow.utils import db

	db.resetdb()
	yield