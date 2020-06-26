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

import string
from datetime import datetime

from liferay.common.configuration import Configuration
from liferay.common.elasticsearch import ElasticsearchBridge

class JobManager(object):

	_OSB_ASAH_JOBS_COLLECTION = 'jobs'

	_OSB_ASAH_JOB_RUNS_COLLECTION = 'job-runs'

	_OSB_ASAH_FARO_INFO_NAMESPACE = 'osbasahfaroinfo'

	def __init__(
	    self, elasticsearch_bridge: ElasticsearchBridge, job_run_id: string
	):
		self.elasticsearch_bridge = elasticsearch_bridge

		self.job_run_id = job_run_id

		self.job_id = self._get_job_id()

	def get_parameter(self, parameter_name, default_value=None):
		parameters = self.get_parameters()

		for parameter in parameters:
			if parameter['name'] == parameter_name:
				return parameter['value']

		return default_value

	def get_parameters(self):
		elasticsearch_bridge = self.elasticsearch_bridge

		document = elasticsearch_bridge.get_document(
		    self._OSB_ASAH_JOBS_COLLECTION, self.job_id,
		    self._OSB_ASAH_FARO_INFO_NAMESPACE
		)

		return document['parameters']

	def get_context_attribute(self, name: string, default_value: string = None):
		elasticsearch_bridge = self.elasticsearch_bridge

		document = elasticsearch_bridge.get_document(
		    self._OSB_ASAH_JOB_RUNS_COLLECTION, self.job_run_id,
		    self._OSB_ASAH_FARO_INFO_NAMESPACE
		)

		context = document['context']

		return context.get(name, default_value=default_value)

	def update_context_attribute(self, attribute_name, value):
		elasticsearch_bridge = self.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    self._OSB_ASAH_JOB_RUNS_COLLECTION, {
		        'context': {
		            attribute_name: value
		        },
		        'lastUpdatedDate': datetime.utcnow(),
		    }, self.job_run_id, self._OSB_ASAH_FARO_INFO_NAMESPACE
		)

	def update_status(self, status):
		elasticsearch_bridge = self.elasticsearch_bridge

		elasticsearch_bridge.update_document(
		    self._OSB_ASAH_JOB_RUNS_COLLECTION, {
		        'status': status,
		        'lastUpdatedDate': datetime.utcnow(),
		    }, self.job_run_id, self._OSB_ASAH_FARO_INFO_NAMESPACE
		)

	def _get_job_id(self):
		elasticsearch_bridge = self.elasticsearch_bridge

		document = elasticsearch_bridge.get_document(
		    self._OSB_ASAH_JOB_RUNS_COLLECTION, self.job_run_id,
		    self._OSB_ASAH_FARO_INFO_NAMESPACE
		)

		return document['job']['id']

class CommerceConfiguration(Configuration):

	_BOOLEAN_VALUES = ['true', 'false']

	def __init__(self, configuration_path: string, job_manager: JobManager):
		super(CommerceConfiguration, self).__init__(configuration_path)

		self.job_manager = job_manager

	def get(self, key, default_value=None):
		value = self._get_environment_variable(key)

		if value is not None:
			return self._cast_value(value)

		value = self.job_manager.get_parameter(key)

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
			return [self._cast_value(x.strip()) for x in value.split(',')]

		if value is None:
			return None

		return [value]

	def _cast_value(self, value):
		if value.lower() in self._BOOLEAN_VALUES:
			return value.lower() == 'true'

		if value.replace('.', '', 1).isdigit():
			return float(value) if '.' in value else int(value)

		return value
