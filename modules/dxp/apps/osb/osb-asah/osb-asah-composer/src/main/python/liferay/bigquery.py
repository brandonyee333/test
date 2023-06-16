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

from airflow.configuration import conf
from airflow.models import DagRun
from airflow.operators.python import ShortCircuitOperator
from airflow.providers.common.sql.sensors.sql import SqlSensor
from airflow.providers.google.cloud.hooks.bigquery import BigQueryHook
from airflow.providers.google.cloud.operators.bigquery import \
	BigQueryInsertJobOperator

import os

class BigQueryInsertJobFromTemplateOperator(BigQueryInsertJobOperator):

	def __init__(self, task_id: str, **kwargs):
		super(BigQueryInsertJobFromTemplateOperator, self).__init__(
			configuration={
				"query": {
					"query": self._read_query_template(
						"sql/" + task_id + "_statement.sql"
					),
					"useLegacySql": False,
				}
			},
			task_id=task_id,
			**kwargs
		)

	def _read_query_template(self, template_path: str):
		full_template_path = os.path.join(
			conf.get('core', 'dags_folder'),
			'resources',
			template_path
		)

		with open(full_template_path, 'r') as template_file:
			return template_file.read()

class BigQueryShortCircuitOperator(ShortCircuitOperator):

	def __init__(self, sql: str, **kwargs):
		super().__init__(
			python_callable=self._validate,
			op_args=None,
			op_kwargs=None,
			templates_dict=None,
			templates_exts=None,
			provide_context=True,
			**kwargs)
		self._sql = sql

	def _validate(self, dag_run: DagRun):
		dag = dag_run.dag

		bigquery_sql_sensor = BigQuerySqlSensor(
			task_id='bigquery_sql_sensor',
			ac_project_id=dag.default_args['ac_project_id'],
			project_id=dag.default_args['project_id'],
			sql=self._sql,
			retries=1
		)

		return bigquery_sql_sensor.poke(None)

class BigQuerySqlSensor(SqlSensor):

	def __init__(self, project_id: str, ac_project_id: str, sql: str, **kwargs):
		super(BigQuerySqlSensor, self).__init__(
			conn_id='bigquery_default',
			fail_on_empty=False,
			sql=sql,
			**kwargs
		)

		self._location = self._get_location(ac_project_id, project_id)

	def _get_hook(self):
		return BigQueryHook(
			gcp_conn_id=self.conn_id,
			location=self._location,
			use_legacy_sql=False
		)

	def _get_location(self, ac_project_id: str, project_id: str):
		big_query_hook = BigQueryHook(
			gcp_conn_id='bigquery_default',
			use_legacy_sql=False
		)

		dataset = big_query_hook.get_dataset(
			project_id=project_id,
			dataset_id=ac_project_id
		)

		return dataset.location