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
	urlencode, \
	urlparse, \
	urlunparse

class NormalizeURLFunction(object):

	def __init__(self, spark_session, ignore_parameters=[]):
		udf_function = udf(
			lambda url: self.normalize_url(url, ignore_parameters),
			StringType()
		)

		spark_session.udf.register('normalize_url', udf_function)

	@staticmethod
	def normalize_url(url, ignore_parameters=[]):
		url_parts = list(urlparse(url))

		query = url_parts[4]

		query_parameters_dict = parse_qs(query, keep_blank_values=True)

		for ignore_parameter in ignore_parameters:
			query_parameters_dict.pop(ignore_parameter, None)

		url_parts[4] = urlencode(query_parameters_dict, doseq=True)

		return urlunparse(url_parts)