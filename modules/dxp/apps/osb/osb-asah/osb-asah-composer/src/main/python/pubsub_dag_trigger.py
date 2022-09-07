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

import airflow
import datetime
import os

from liferay.pubsub import PubSubDagTriggerOperator

with airflow.DAG(
	dag_id='pubsub_dag_trigger',
	description='Pubsub DAG Trigger',
	default_args={
		'lcp_project_id': os.environ['LCP_PROJECT_ID'],
		'owner': 'Liferay',
		'project_id': os.environ['GOOGLE_PROJECT_ID'],
		'retries': 0,
		'start_date': datetime.datetime.now() - datetime.timedelta(minutes=1)
	},
	max_active_runs=1,
	schedule_interval='*/1 * * * *'
) as dag:
	pubsub_dag_trigger = PubSubDagTriggerOperator(
		task_id='pubsub_dag_trigger'
	)

	pubsub_dag_trigger