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
			default_args={
				'ac_project_id': ac_project_id,
				'owner': 'Liferay'
			},
			description=dag_description,
			max_active_runs=1,
			schedule_interval='0 1 * * *',
			start_date=datetime.datetime.now() - datetime.timedelta(days=2)
	) as dag:
		[
			BigQueryInsertJobFromTemplateOperator(task_id='blog_daily_merge'),
			BigQueryInsertJobFromTemplateOperator(task_id='customasset_daily_merge'),
			BigQueryInsertJobFromTemplateOperator(task_id='document_library_daily_merge'),
			BigQueryInsertJobFromTemplateOperator(task_id='form_daily_merge'),
			BigQueryInsertJobFromTemplateOperator(task_id='identity_activity_summary_merge'),
			BigQueryInsertJobFromTemplateOperator(task_id='journal_daily_merge'),
			BigQueryInsertJobFromTemplateOperator(task_id='page_daily_merge')
		]

		return dag

bigquery_hook = BigQueryHook(gcp_conn_id='google_cloud_default')

client = bigquery_hook.get_client()

for dataset in client.list_datasets():
	dag_id = 'merge_daily_metrics_{}'.format(dataset.dataset_id)

	globals()[dag_id] = create_dag(
		dataset.dataset_id, dag_id,
		'Daily Merge DAG For {}'.format(dataset.dataset_id)
	)