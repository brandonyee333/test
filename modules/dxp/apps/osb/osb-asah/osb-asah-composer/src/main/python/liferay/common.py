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

from abc import abstractmethod

from airflow.operators.python import PythonOperator
from airflow.utils.context import Context

import json
import yaml

class BaseOperator(PythonOperator):

	def __init__(self, **kwargs):
		super().__init__(
			python_callable=self.do_execute,
			op_args=None,
			op_kwargs=None,
			templates_dict=None,
			templates_exts=None,
			provide_context=True,
			**kwargs
		)

	@abstractmethod
	def do_execute(self, *args, **kwargs):
		raise NotImplementedError()

	def pre_execute(self, context):
		conf = context.get('conf')

		dags_folder = conf.get('core', 'dags_folder')

		with open(
			dags_folder + '/resources/configuration.yaml', 'r'
		) as yaml_file:
			configuration = yaml.safe_load(yaml_file)

			dag_configuration = configuration.get(self.dag.dag_id)

			render_configuration = self._flatten_dict(dag_configuration)

			render_configuration = self.render_template(
				content=json.dumps(render_configuration),
				context=Context(context)
			)

			self.log.info(render_configuration)

			if dag_configuration:
				context[self.dag.dag_id] = json.loads(render_configuration)

	def _flatten_dict(self, dictionary, flat_dict=None, parent_key=None):
		if flat_dict is None:
			flat_dict = {}
		for key, value in dictionary.items():
			new_key = key

			if parent_key is not None:
				new_key = parent_key + '.' + new_key

			if isinstance(value, dict):
				self._flatten_dict(value, flat_dict, new_key)
			else:
				flat_dict[new_key] = value

		return flat_dict