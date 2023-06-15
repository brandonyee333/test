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

from airflow.models import Variable
from airflow.providers.google.cloud.operators.bigquery import \
	BigQueryCheckOperator

from liferay.dataproc import DataprocClusterGetOrCreateOperator, \
	DataprocSubmitCommercePySparkJobOperator

import airflow
import os
import pendulum
import requests

def create_dag(ac_project_id, ac_project_time_zone_id, dag_id, dag_description, data_source_ids, resource_name, schedule_interval: str):
	with airflow.DAG(
		dag_id=dag_id,
		default_args={
			'ac_project_id': ac_project_id,
			'ac_project_time_zone_id': ac_project_time_zone_id,
			'dag_configuration_key': 'commerce_recommenders_dag_trigger',
			'data_source_ids': data_source_ids,
			'owner': 'Liferay',
			'project_id': os.environ['GOOGLE_PROJECT_ID'],
			'region': os.environ['GOOGLE_REGION'],
			'resource_name': resource_name
		},
		description=dag_description,
		max_active_runs=1,
		schedule_interval=schedule_interval,
		start_date=pendulum.now(ac_project_time_zone_id) - pendulum.duration(days=2)
	) as dag:
		cluster_get_or_create = DataprocClusterGetOrCreateOperator(
			task_id='dataproc_cluster_get_or_create'
		)

		for data_source_id in data_source_ids:
			dataproc_submit_commerce_pyspark_job = \
				DataprocSubmitCommercePySparkJobOperator(
					task_id='dataproc_submit_commerce_pyspark_job_{}'.format(data_source_id),
					cluster_name="{{ ti.xcom_pull(task_ids='dataproc_cluster_get_or_create')['cluster_name'] }}",
					data_source_id=str(data_source_id),
					resource_name=resource_name
				)

			cluster_get_or_create >> dataproc_submit_commerce_pyspark_job

		return dag

response = requests.get(
	Variable.get('osb.asah.backend.url'),
	headers={
		'OSB-Asah-Faro-Backend-Security-Signature': Variable.get('osb.asah.faro.backend.security.signature'),
		'OSB-Asah-Project-ID': 'osbasah',
		'User-Agent': 'LiferayAnalyticsCloud'
	}
)

for project in response.json():
	if project.get('commerceChannelsSelected'):
		dag_id = 'commerce_product_content_recommenders_{}'.format(project.get('id'))

		globals()[dag_id] = create_dag(
			project.get('id'), project.get('timeZoneId'), dag_id,
			'Commerce Content based Recommenders DAG For {}'.format(project.get('id')),
			project.get('dataSourceIds'),
			'com.liferay.headless.commerce.machine.learning.dto.v1_0.Product',
			'0 1 * * 7'
		)

		dag_id = 'commerce_user_interactions_recommenders_{}'.format(project.get('id'))

		globals()[dag_id] = create_dag(
			project.get('id'), project.get('timeZoneId'), dag_id,
			'Commerce User Interaction based Recommenders DAG For {}'.format(project.get('id')),
			project.get('dataSourceIds'),
			'com.liferay.headless.commerce.machine.learning.dto.v1_0.Order',
			'0 1 * * *'
		)