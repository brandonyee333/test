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

from pyspark.sql.functions import udf
from pyspark.sql.types import StringType

from urllib.parse import parse_qs, \
	unquote, \
	urlparse

class AcquisitionChannelFunction(object):

	def __init__(
		self, spark_session, paid_host_names=[], search_host_names=[],
		social_host_names=[]
	):

		self._paid_host_names = paid_host_names
		self._paid_mediums = ['cpc', 'paidsearch', 'ppc']
		self._search_host_names = search_host_names
		self._social_host_names = social_host_names
		self._social_mediums = [
			'sm', 'social', 'social media', 'social network', 'social-media',
			'social-network'
		]

		udf_function = udf(self.acquisition_channel, StringType())

		spark_session.udf.register('acquisition_channel', udf_function)

	def _array_contains(self, array, value):
		if value is None or value == '':
			return False

		if value.endswith('/'):
			value = value[:-1]

		for item in array:
			if value.endswith(item):
				return True

		return False

	def _get_first_param_value(self, query_parameters_dict, param_key):
		param_values = query_parameters_dict.get(param_key)

		if (param_values is None) or (len(param_values) == 0):
			return None
		else:
			return unquote(param_values[0])

	def acquisition_channel(self, url, referrer_url):
		url_parse_result = urlparse(url)

		url_hostname = url_parse_result.hostname

		url_query_parameters_dict = parse_qs(
			url_parse_result.query, keep_blank_values=True
		)

		gclid = self._get_first_param_value(
			url_query_parameters_dict, 'gclid'
		)

		medium = self._get_first_param_value(
			url_query_parameters_dict, 'utm_medium'
		)

		referrer_parse_result = urlparse(referrer_url)

		referrer_hostname = referrer_parse_result.hostname

		if (medium == 'organic') or \
			self._array_contains(self._search_host_names, referrer_hostname):

			return 'organic'

		if (medium in ['cpc', 'paidsearch', 'ppc']) or \
			(gclid is not None) or \
			self._array_contains(self._paid_host_names, referrer_hostname):

			return 'paid'

		if (medium in self._social_mediums) or \
			self._array_contains(self._social_host_names, referrer_hostname):

			return 'social'

		if referrer_hostname is None:
			return 'direct'

		if (medium == 'referral') or (url_hostname != referrer_hostname):
			return 'referral'

		return None