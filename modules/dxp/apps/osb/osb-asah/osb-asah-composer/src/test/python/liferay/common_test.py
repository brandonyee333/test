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

import datetime
import os
import pendulum
import sys
import time

sys.path.insert(
	0,
	os.path.dirname(os.path.abspath(__file__)) + '/../../../main/python'
)

from airflow import DAG
from airflow.utils.state import DagRunState, \
	State
from airflow.utils.types import DagRunType

from liferay.common import BaseOperator

DATA_INTERVAL_START = pendulum.datetime(year=2022, month=4, day=23)
DATA_INTERVAL_END = DATA_INTERVAL_START + datetime.timedelta(days=1)

TEST_TASK_ID = "test_base_operator_task"


def test_base_operator(reset_db):
	class TestBaseOperator(BaseOperator):

		def __init__(self, **kwargs):
			super().__init__(**kwargs)

		def do_execute(self, dag: DAG, **kwargs):
			dag_configuration = kwargs[dag.dag_id]

			nested_property = dag_configuration['nested.property']

			assert(nested_property == 'nested_property')

	with DAG(
			dag_id="test_operator_dag",
			schedule_interval="@daily",
			start_date=DATA_INTERVAL_START,
	) as dag:
		TestBaseOperator(task_id=TEST_TASK_ID)

	dagrun = dag.create_dagrun(
		state=DagRunState.RUNNING,
		execution_date=DATA_INTERVAL_START,
		data_interval=(DATA_INTERVAL_START, DATA_INTERVAL_END),
		start_date=DATA_INTERVAL_END,
		run_id=str(int(time.time())),
		run_type=DagRunType.MANUAL,
	)

	ti = dagrun.get_task_instance(task_id=TEST_TASK_ID)
	ti.task = dag.get_task(task_id=TEST_TASK_ID)
	ti.run(ignore_ti_state=True)

	assert ti.state == State.SUCCESS