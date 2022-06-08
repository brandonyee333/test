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

from airflow.providers.google.cloud.hooks.bigquery import BigQueryHook

from liferay.bigquery import BigQueryInsertJobFromTemplateOperator

def create_dag(ac_project_id, dag_id, dag_description):
	with airflow.DAG(
			dag_id=dag_id,
			description=dag_description,
			default_args={
				'ac_project_id': ac_project_id,
				'owner': 'Liferay',
				'start_date': datetime.datetime.now() - datetime.timedelta(
					minutes=1)
			},
			max_active_runs=1,
			schedule_interval='@hourly'
	) as dag:
		[
			BigQueryInsertJobFromTemplateOperator('account_entry_merge'),
			BigQueryInsertJobFromTemplateOperator('account_group_merge'),
			BigQueryInsertJobFromTemplateOperator('expando_column_merge'),
			BigQueryInsertJobFromTemplateOperator('expando_value_delete'),
			BigQueryInsertJobFromTemplateOperator('expando_value_merge'),
			BigQueryInsertJobFromTemplateOperator('group_merge'),
			BigQueryInsertJobFromTemplateOperator('organization_expando_column_update'),
			BigQueryInsertJobFromTemplateOperator('organization_merge'),
			BigQueryInsertJobFromTemplateOperator('role_merge'),
			BigQueryInsertJobFromTemplateOperator('team_merge'),
			BigQueryInsertJobFromTemplateOperator('user_expando_column_update'),
			BigQueryInsertJobFromTemplateOperator('user_group_merge'),
			BigQueryInsertJobFromTemplateOperator('user_merge')
		]

		return dag

bigquery_hook = BigQueryHook(gcp_conn_id='google_cloud_default')

client = bigquery_hook.get_client()

for dataset in client.list_datasets():
	dag_id = 'merge_dxp_entity_{}'.format(dataset.dataset_id)

	globals()[dag_id] = create_dag(
		dataset.dataset_id, dag_id,
		'DXP Entity Merge DAG For {}'.format(dataset.dataset_id)
	)