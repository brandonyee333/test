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

from liferay.dataproc import DataprocClusterGetOrCreateOperator, \
	DataprocShortCircuitOperator, \
	DataprocSubmitCommercePySparkJobOperator

import airflow
import datetime

with airflow.DAG(
	dag_id='commerce_dataproc_trigger',
	description='Commerce Dataproc Trigger',
	default_args={
		'lcp_project_id': 'riccardo',
		'owner': 'Liferay',
		'project_id': 'liferaycloud-ac-uat',
		'region': 'us-west1',
		'retries': 0,
		'start_date': datetime.datetime.now() - datetime.timedelta(minutes=1)
	},
	render_template_as_native_obj=True
) as dag:
	short_circuit_operator = DataprocShortCircuitOperator(
		task_id='dataproc_short_circuit'
	)

	cluster_get_or_create = DataprocClusterGetOrCreateOperator(
		task_id='dataproc_cluster_get_or_create'
	)

	submit_commerce_pyspark_job = DataprocSubmitCommercePySparkJobOperator(
		task_id='dataproc_submit_commerce_pyspark_job',
		cluster_name="{{ ti.xcom_pull(task_ids='dataproc_cluster_get_or_create')['cluster_name'] }}"
	)

	short_circuit_operator >> cluster_get_or_create >> submit_commerce_pyspark_job