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

import logging
import pandas as pd
from abc import abstractmethod

from liferay.commerce.forecast.constants import ModelSelectionCriterion

from multiprocessing import cpu_count

class BaseCommerceForecast(object):

	COLUMNS = [
		'assetCategoryId', 'commerceAccountId', 'sku', 'scope', 'period',
		'target', 'forecast', 'timestamp', 'forecastLowerBound',
		'forecastUpperBound', 'modelAttributes', 'error'
	]

	def __init__(
			self,
			period,
			scope,
			target,
			cross_validation_steps=None,
			horizon_steps=None,
			model_selection_criterion=None,
			parallel_count=None,
			target_column='actual',
			time_column='timestamp',
			**kwargs):
		self._log = logging.getLogger(self.__class__.__name__)

		self._period = period

		self._scope = scope

		self._target = target

		self._cross_validation_steps = cross_validation_steps

		self._horizon_steps = horizon_steps

		self._model_selection_criterion = \
			ModelSelectionCriterion.from_description(
				model_selection_criterion
			)

		self._parallel_count = \
			parallel_count if parallel_count else cpu_count() // 2

		self._target_column = target_column

		self._time_column = time_column

	def _return_empty_pandas_data_frame(self, target_pks):
		return_data = [None for _ in range(len(self.COLUMNS))]

		return_data_frame = pd.DataFrame(
			data=[return_data],
			columns=self.COLUMNS
		)

		return_data_frame[self._scope.columns] = target_pks

		return return_data_frame

	@abstractmethod
	def fit_predict(self, data_frame):
		raise NotImplementedError()