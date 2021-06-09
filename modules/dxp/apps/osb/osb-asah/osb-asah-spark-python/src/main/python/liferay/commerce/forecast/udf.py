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

from liferay.commerce.configuration import CommerceConfiguration
from liferay.commerce.forecast.constants import CommerceMLForecastPeriod, \
	CommerceMLForecastScope, \
	CommerceMLForecastTarget
from liferay.commerce.forecast.sarima import CommerceSARIMAX

from pyspark.sql.types import DoubleType, \
	LongType, \
	StringType, \
	StructField, \
	StructType

import logging
import os

class ForecastUDFHelper(object):

	def __init__(
		self, configuration: CommerceConfiguration,
		period: CommerceMLForecastPeriod, scope: CommerceMLForecastScope,
		target: CommerceMLForecastTarget
	):
		self._configuration = configuration
		self._log = logging.getLogger(self.__class__.__name__)
		self._period = period
		self._scope = scope
		self._target = target

	def _get_parallel_count(self):
		# number of threads to use in parallel regions

		num_threads = self._configuration.get('OMP_NUM_THREADS', None)

		# number of thread used by OpenBLAS

		num_threads = self._configuration.get(
			'OPENBLAS_NUM_THREADS', num_threads
		)

		num_threads = self._configuration.get('MKL_NUM_THREADS', num_threads)

		cpu_count = os.cpu_count()

		if num_threads is None:
			num_threads = cpu_count

		if cpu_count is None:
			self._log.warning(
				"Unable to determine CPU count. Setting parallel count to 1"
			)

			return 1

		parallelism = cpu_count // num_threads

		self._log.info(
			"CPU count: {}. Threads: {}. Parallelism: {}".format(
				cpu_count, num_threads, parallelism
			)
		)

		return parallelism

	@staticmethod
	def get_schema():
		schema = StructType(
			[
				StructField('assetCategoryId', LongType()),
				StructField('commerceAccountId', LongType()),
				StructField('sku', StringType()),
				StructField('scope', StringType()),
				StructField('period', StringType()),
				StructField('target', StringType()),
				StructField('forecast', DoubleType()),
				StructField('timestamp', LongType()),
				StructField('forecastLowerBound', DoubleType()),
				StructField('forecastUpperBound', DoubleType()),
				StructField('modelAttributes', StringType()),
				StructField('error', StringType())
			]
		)

		return schema

	def get_instance(self):
		return CommerceSARIMAX(
			period=self._period,
			scope=self._scope,
			target=self._target,
			p_params=self._configuration.get_list('forecast.model.order.p'),
			d_params=self._configuration.get_list('forecast.model.order.d'),
			q_params=self._configuration.get_list('forecast.model.order.q'),
			seasonal_p_params=self._configuration.get_list(
				'forecast.model.seasonal.p'
			),
			seasonal_q_params=self._configuration.get_list(
				'forecast.model.seasonal.q'
			),
			seasonal_d_params=self._configuration.get_list(
				'forecast.model.seasonal.d'
			),
			t_params=self._configuration.get_list('forecast.model.trend'),
			m_params=self._configuration.get_list('forecast.model.period'),
			max_order=self._configuration.get('forecast.model.max.order'),
			ahead=self._configuration.get('forecast.ahead'),
			dynamic=self._configuration.get(
				'forecast.model.prediction.dynamic.flag'
			),
			test_sample_count=self._configuration.get(
				'forecast.test.sample.count'
			),
			parallel_count=self._configuration.get(
				'forecast.force.parallelism', self._get_parallel_count()
			),
			model_selection_criterion=self._configuration.get(
				'forecast.model.selection.criterion'
			)
		)