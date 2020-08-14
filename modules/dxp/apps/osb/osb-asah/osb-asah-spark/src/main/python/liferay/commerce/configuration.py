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

from liferay.common.configuration import Configuration
from liferay.common.util import new_utc_date_string

class CommerceConfiguration(Configuration):
	def __init__(self, configuration_path, job_manager):
		super(CommerceConfiguration, self).__init__(configuration_path)

		self.job_manager = job_manager

	def get(self, key, default_value=None):
		value = self._get_environment_variable(key)

		if value is not None:
			return self._cast_value(value)

		value = self.job_manager.get_job_parameter(key)

		if value is not None:
			return self._cast_value(value)

		if self._dict is not None:
			value = self._dict.get(key)

		if value is not None:
			return value

		return default_value

	def get_list(self, key=None, default=None):
		value = self.get(key, default_value=default)

		if isinstance(value, list):
			return value

		if isinstance(value, str):
			return [self._cast_value(v.strip()) for v in value.split(',')]

		if value is None:
			return None

		return [value]

	def _cast_value(self, value):
		if value.lower() in ['true', 'false']:
			return value.lower() == 'true'

		if value.replace('.', '', 1).isdigit():
			return float(value) if '.' in value else int(value)

		return value

class JobManager(object):
	def __init__(self, elasticsearch_bridge, job_run_id):
		self.elasticsearch_bridge = elasticsearch_bridge

		self.job_run_id = job_run_id

		self.job_id = self._get_job_id()

	def get_job_parameter(self, parameter_name, default_value=None):
		parameters = self.get_job_parameters()

		for parameter in parameters:
			if parameter['name'] == parameter_name:
				return parameter['value']

		return default_value

	def get_job_parameters(self):
		document = self.elasticsearch_bridge.get_document(
		    'jobs', self.job_id, 'osbasahfaroinfo'
		)

		return document['parameters']

	def get_job_run_context_attribute(self, attribute_name, default_value=None):
		document = self.elasticsearch_bridge.get_document(
		    'job-runs', self.job_run_id, 'osbasahfaroinfo'
		)

		context = document['context']

		return context.get(attribute_name, default_value=default_value)

	def update_job_run_context_attribute(self, attribute_name, attribute_value):
		self.elasticsearch_bridge.update_document(
		    'job-runs', {
		        'context': {
		            attribute_name: attribute_value
		        },
		        'lastUpdatedDate': new_utc_date_string(),
		    }, self.job_run_id, 'osbasahfaroinfo'
		)

	def update_job_run_status(self, status):
		self.elasticsearch_bridge.update_document(
		    'job-runs', {
		        'status': status,
		        'lastUpdatedDate': new_utc_date_string(),
		    }, self.job_run_id, 'osbasahfaroinfo'
		)

	def _get_job_id(self):
		document = self.elasticsearch_bridge.get_document(
		    'job-runs', self.job_run_id, 'osbasahfaroinfo'
		)

		return document['job']['id']