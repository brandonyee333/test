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
from airflow.providers.google.cloud.operators.bigquery import BigQueryInsertJobOperator

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