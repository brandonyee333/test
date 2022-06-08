from airflow.providers.google.cloud.operators.bigquery import BigQueryInsertJobOperator

import os

class BigQueryInsertJobFromTemplateOperator(BigQueryInsertJobOperator):

	def __init__(self, task_id: str):
		super(BigQueryInsertJobFromTemplateOperator, self).__init__(
			configuration={
				"query": {
					"query": self._read_query_template("sql/" + task_id + "_statement.sql"),
					"useLegacySql": False,
				}
			},
			task_id=task_id
		)

	def _read_query_template(self, template_path: str):
		my_dir = os.path.dirname(os.path.abspath(__file__))

		full_template_path = os.path.join(my_dir, 'resources', template_path)

		with open(full_template_path, 'r') as template_file:
			return template_file.read()