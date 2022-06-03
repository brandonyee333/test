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

from airflow.providers.google.cloud.hooks.bigquery import BigQueryHook
from airflow.providers.google.cloud.operators.bigquery import BigQueryInsertJobOperator

def create_dag(ac_project_id, dag_id, dag_description):
	with airflow.DAG(
			dag_id=dag_id,
			description=dag_description,
			default_args={
				'ac_project_id': ac_project_id,
				'lcp_project_id': 'asahdev',
				'owner': 'Liferay',
				'project_id': 'liferaycloud-ac-uat',
				'start_date': datetime.datetime.now() - datetime.timedelta(
					minutes=1)
			},
			max_active_runs=1,
			schedule_interval='@hourly'
	) as dag:
		[
			create_bigquery_insert_job('account_entry_merge'),
			create_bigquery_insert_job('account_group_merge'),
			create_bigquery_insert_job('expando_column_merge'),
			create_bigquery_insert_job('expando_value_delete'),
			create_bigquery_insert_job('expando_value_merge'),
			create_bigquery_insert_job('group_merge'),
			create_bigquery_insert_job('organization_expando_column_update'),
			create_bigquery_insert_job('organization_merge'),
			create_bigquery_insert_job('role_merge'),
			create_bigquery_insert_job('team_merge'),
			create_bigquery_insert_job('user_expando_column_update'),
			create_bigquery_insert_job('user_group_merge'),
			create_bigquery_insert_job('user_merge')
		]

		return dag

def create_bigquery_insert_job(task_id: str):
	return BigQueryInsertJobOperator(
		configuration={
			"query": {
				"query": read_query_template("sql/" + task_id + "_statement.sql"),
				"useLegacySql": False,
			}
		},
		task_id=task_id
	)

def read_query_template(template_path: str):
	my_dir = os.path.dirname(os.path.abspath(__file__))

	full_template_path = os.path.join(my_dir, 'resources', template_path)

	with open(full_template_path, 'r') as template_file:
		return template_file.read()


bigquery_hook = BigQueryHook(gcp_conn_id='google_cloud_default')

client = bigquery_hook.get_client()

for dataset in client.list_datasets():
	dag_id = 'merge_dxp_entity_{}'.format(dataset.dataset_id)

	globals()[dag_id] = create_dag(
		dataset.dataset_id, dag_id,
		'DXP Entity Merge DAG For {}'.format(dataset.dataset_id)
	)