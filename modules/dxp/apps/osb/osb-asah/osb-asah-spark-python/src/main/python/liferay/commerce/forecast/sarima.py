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

from joblib import Parallel, \
	delayed

from liferay.commerce.forecast.constants import CommerceMLForecastPeriod, \
	ModelSelectionCriterion

from math import sqrt

from multiprocessing import cpu_count

from pmdarima.arima import ADFTest, \
	KPSSTest, \
	ndiffs, \
	nsdiffs

from sklearn.metrics import mean_squared_error

from statsmodels.tsa.statespace.sarimax import SARIMAX

import itertools
import json
import logging
import pandas as pd
import warnings

class CommerceSARIMAX(object):

	_COLUMNS = [
		'assetCategoryId', 'commerceAccountId', 'sku', 'scope', 'period',
		'target', 'forecast', 'timestamp', 'forecastLowerBound',
		'forecastUpperBound', 'modelAttributes', 'error'
	]

	def __init__(
		self,
		period,
		scope,
		target,
		target_column='actual',
		time_column='timestamp',
		p_params=None,
		d_params=None,
		q_params=None,
		seasonal_p_params=None,
		seasonal_d_params=None,
		seasonal_q_params=None,
		t_params=None,
		m_params=None,
		max_order=None,
		ahead=None,
		dynamic=True,
		test_sample_count=None,
		parallel_count=None,
		model_selection_criterion=None
	):
		self._log = logging.getLogger(self.__class__.__name__)

		self._period = period

		self._scope = scope

		self._target = target

		self._target_column = target_column

		self._time_column = time_column

		self._indexer = DateTimeIndexGenerator(period)

		self._p_params = p_params

		self._d_params = d_params

		self._q_params = q_params

		self._seasonal_p_params = seasonal_p_params

		self._seasonal_d_params = seasonal_d_params

		self._seasonal_q_params = seasonal_q_params

		self._t_params = t_params

		self._m_params = m_params if m_params is not None else [
			1, period.period_value
		]

		self._max_order = max_order

		self._ahead = ahead

		self._dynamic = dynamic

		self._test_sample_count = test_sample_count

		# Avoid clogging the CPU between multiprocess and blas/linpack. This
		# should be picked up from the OS environment and not from properties.

		self._parallel_count = parallel_count if parallel_count is not None else cpu_count(
		) // 2

		model_selection_criterion = model_selection_criterion

		self._model_selection_criterion = ModelSelectionCriterion.from_description(
			model_selection_criterion
		)

		if self._log.isEnabledFor(logging.INFO):
			self._log.info(
				"Preparing SARIMAX model for: {}-{}-{}".format(
					self._scope.label, self._period.label, self._target.label
				)
			)

			self._log.info("'p' parameter range: {}".format(self._p_params))
			self._log.info("'d' parameter range: {}".format(self._d_params))
			self._log.info("'q' parameter range: {}".format(self._q_params))

			self._log.info(
				"'P' parameter range: {}".format(self._seasonal_p_params)
			)
			self._log.info(
				"'D' parameter range: {}".format(self._seasonal_d_params)
			)
			self._log.info(
				"'Q' parameter range: {}".format(self._seasonal_q_params)
			)

			self._log.info("Trend: {}".format(self._t_params))
			self._log.info(
				"Seasonality period paraemter range: {}".format(self._m_params)
			)

			self._log.info("Max order: {}".format(self._max_order))

			self._log.info(
				"In task parallelism: {}".format(self._parallel_count)
			)

			self._model = None

			self._model_attributes = None

	def _fit_model(self, series, order, seasonal_order, trend):
		sarimax = SARIMAX(
			series,
			order=order,
			seasonal_order=seasonal_order,
			trend=trend,
			enforce_stationarity=False,
			enforce_invertibility=False,
			simple_differencing=False
		)

		return sarimax.fit(disp=False, maxiter=100)

	def _fit_predict(self, series, configuration):
		order, seasonal_order, trend = configuration

		sarimax_model = self._fit_model(series, order, seasonal_order, trend)

		prediction_index = len(series)

		y_hat = sarimax_model.predict(prediction_index, prediction_index)

		return y_hat, sarimax_model.aic

	def _generate_parameter_configs(self, series_values, series_length):
		# Evaluate 'd' parameters

		if self._d_params is None:
			try:
				diff = ndiffs(series_values, test='adf')
			except:
				diff = 0

			self._d_params = [diff]

			self._log.info("Using 'd': {}".format(str(self._d_params)))

		# Evaluate 'D' parameters

		if self._seasonal_d_params is None:
			try:
				seasonal_diffs = nsdiffs(
					series_values, m=self._period.period_value
				)
			except:
				seasonal_diffs = 0

			self._seasonal_d_params = [seasonal_diffs]

			self._log.info("Using 'D': {}".format(str(self._seasonal_d_params)))

		o_set = itertools.product(
			self._p_params, self._d_params, self._q_params
		)

		s_set = itertools.product(
			self._seasonal_p_params, self._seasonal_d_params,
			self._seasonal_q_params, self._m_params
		)

		configuration_products = itertools.product(o_set, s_set, self._t_params)

		configuration_products = filter(
			lambda cfg: self._validate_configuration(cfg, series_length),
			configuration_products
		)

		configuration_list = {
			i: cfg
			for i, cfg in enumerate(configuration_products)
		}

		return configuration_list

	def _return_empty_pandas_data_frame(self, target_pks):
		return_data = [None for _ in range(len(self._COLUMNS))]

		return_data_frame = pd.DataFrame(
			data=[return_data], columns=self._COLUMNS
		)

		return_data_frame[self._scope.columns] = target_pks

		return return_data_frame

	def _score_model(self, data, n_test, configuration):
		rmse = None

		aic = None

		id_, cfg = configuration

		if self._log.isEnabledFor(logging.INFO):
			if (id_ % 10) == 0:
				self._log.info("Testing {}: {}".format(id_, cfg))

		try:
			with warnings.catch_warnings():
				warnings.filterwarnings("ignore")

				rmse, aic = self._walk_forward_validation(data, n_test, cfg)
		except Exception as e:
			self._log.warning("Configuration {} error: {}".format(cfg, e))

		if rmse is not None:
			self._log.debug(
				"Model [{}] RMSE: {}, AIC: {}".format(str(cfg), rmse, aic)
			)

		return id_, rmse, aic

	def _score_models(self, timeseries, configurations):
		scores = None

		if self._parallel_count > 1:
			# execute configs in parallel

			executor = Parallel(
				n_jobs=self._parallel_count, backend='multiprocessing'
			)

			tasks = (
				delayed(self._score_model)(
					timeseries[self._target_column], self._test_sample_count,
					cfg
				) for cfg in configurations.items()
			)

			scores = executor(tasks)
		else:
			scores = [
				self._score_model(
					timeseries[self._target_column], self._test_sample_count,
					cfg
				) for cfg in configurations.items()
			]

		return scores

	def _validate_configuration(self, configuration, series_length):
		(p, d, q), (P, D, Q, s), trend = configuration

		model_order = p + q + P + Q

		if self._max_order is not None:
			if model_order > self._max_order:
				self._log.debug(
					"Model order sum: {}, is bigger than max_"
					"order: {}. Skipping".format(
						model_order, self._max_order
					)
				)

				return False

		# https://github.com/statsmodels/statsmodels/issues/4465#issuecomment-381004987

		param_sum = p + D * s + max(3 * q + 1, 3 * Q * s + 1, p, P * s) + 1

		if param_sum > series_length:
			self._log.debug(
				"Parameter sum {}, observation length: {}. "
				"Skipping".format(param_sum, series_length)
			)

			return False

		return True

	def _walk_forward_validation(self, data, n_test, cfg):
		aic = None

		predictions = list()

		train, test = data[:-n_test], data[-n_test:]

		history = [x for x in train]

		# step over each time-step in the test set

		for i in range(len(test)):
			# fit model and make forecast for history

			y_hat, aic = self._fit_predict(history, cfg)

			# store forecast in list of predictions

			predictions.append(y_hat)

			# add actual observation to history for the next loop

			history.append(test.iloc[i])

		# estimate prediction rmse

		rmse = sqrt(mean_squared_error(test, predictions))

		return rmse, aic

	def fit(self, timeseries):
		if not timeseries.index.is_monotonic_increasing:
			raise ValueError("Time series should be monotonically increasing")

		timeseries_values = timeseries[self._target_column].values

		target_pks = timeseries[self._scope.columns].iloc[0].tolist()

		timeseries_length = len(timeseries_values)

		# Check stationary

		try:
			adf_test = ADFTest()

			_, adf_should_diff = adf_test.should_diff(timeseries_values)

			adf_stationary = not adf_should_diff

			kpss_test = KPSSTest()

			_, kpss_should_diff = kpss_test.should_diff(timeseries_values)

			kpss_stationary = not kpss_should_diff

			if self._log.isEnabledFor(logging.INFO):
				self._log.info(
					"ADF test for stationarity. Is stationary: {}".
					format(adf_stationary)
				)

				self._log.info(
					"KPSS test for stationarity. Is stationary: {}".
					format(kpss_stationary)
				)

		# https://www.analyticsvidhya.com/blog/2018/09/non-stationary-time-series-python/

		# TODO: Series pre-processing if needed

		except:
			self._log.warning("Failed to test stationarity")

		configurations = self._generate_parameter_configs(
			timeseries_values, timeseries_length
		)

		self._log.info("Testing {} configurations".format(len(configurations)))

		scores = self._score_models(timeseries, configurations)

		# Filter completed scores

		scores = list(filter(lambda s: s[1] is not None, scores))

		if len(scores) == 0:
			self._log.warning("No model was trained, check data")

			return self._return_empty_pandas_data_frame(target_pks)

		# sort configs by model selection criterion

		scores.sort(key=lambda tup: tup[self._model_selection_criterion.value])

		# Forecast and confidence_intervals

		best_model_id, rmse, aic = scores[0]

		order, seasonal_order, trend = configurations.get(best_model_id)

		model_attributes = dict()

		model_attributes['aic'] = aic
		model_attributes['order'] = order
		model_attributes['rmse'] = rmse
		model_attributes['seasonal_order'] = seasonal_order
		model_attributes['trend'] = trend

		self._model_attributes = model_attributes

		self._log.info(
			"Best model: {}, configuration: {}, {}, {}. "
			"AIC: {}, RMSE: {}".format(
				best_model_id, order, seasonal_order, trend, aic, rmse
			)
		)

		self._model = self._fit_model(
			timeseries[self._target_column], order, seasonal_order, trend
		)

		return self

	def fit_predict(self, timeseries):
		# Reindex series and fill missing values

		timeseries.set_index(timeseries[self._time_column], inplace=True)

		timeseries = self._indexer.reindex(timeseries, self._target_column)

		target_pks = timeseries[self._scope.columns].iloc[0].tolist()

		timeseries_length = len(timeseries)

		if self._log.isEnabledFor(logging.INFO):
			original_series_length = len(timeseries)

			series_coverage = original_series_length / timeseries_length

			self._log.info(
				"TargetPKs: {}, data length: {}  Coverage: {}".format(
					target_pks, original_series_length, series_coverage
				)
			)

		self.fit(timeseries)

		return self.predict(timeseries)

	def predict(self, timeseries):
		target_pks = timeseries[self._scope.columns].iloc[0].tolist()

		if self._model is None:
			self._log.warning("No model was trained, check data")

			return self._return_empty_pandas_data_frame(target_pks)

		start = self._model.nobs

		end = self._model.nobs + self._ahead - 1

		prediction_date_start = timeseries.index.values[-1]

		prediction_date_count = self._ahead

		if self._log.isEnabledFor(logging.INFO):
			self._log.info("Target PKs {} ".format(target_pks))
			self._log.info("Start Date {} ".format(prediction_date_start))
			self._log.info(
				"TS {} ".format(timeseries[self._target_column].values[-2:])
			)
			self._log.info("IDX {} ".format(timeseries.index.values[-2:]))

		if type(self._dynamic) is int:
			start -= self._dynamic

			prediction_date_start = timeseries.index.values[-self._dynamic]

			prediction_date_count += self._dynamic

		self._log.info(
			"Using start {} end {}, dynamic {}".format(
				start, end, self._dynamic
			)
		)

		forecast = self._model.get_prediction(
			start=start, end=end, dynamic=self._dynamic
		)

		conf_int = forecast.conf_int()

		forecast_predicted_mean = forecast.predicted_mean

		self._log.info(
			"Prediction length {}".format(len(forecast_predicted_mean))
		)

		forecast_date_index = self._indexer.get_prediction_index(
			prediction_date_start, prediction_date_count
		)

		forecast_date_index = forecast_date_index.values.astype(int) // 1e6

		forecast_lower_bound = conf_int.iloc[:, 0].tolist()

		forecast_upper_bound = conf_int.iloc[:, 1].tolist()

		if type(self._dynamic) is int:
			for idx in range(start, self._model.nobs):
				# TODO: Fix with actual dynamic data
				forecast_lower_bound[idx - start] = timeseries[
					self._target_column].iloc[idx]

				forecast_predicted_mean.values[idx - start] = timeseries[
					self._target_column].iloc[idx]

				forecast_upper_bound[idx - start] = timeseries[
					self._target_column].iloc[idx]

		result = pd.DataFrame([], columns=self._COLUMNS)

		for idx in range(prediction_date_count):
			row = [None, None, None]
			row += [self._scope.label, self._period.label, self._target.label]
			row += [forecast_predicted_mean.values[idx]]
			row += [forecast_date_index[idx]]
			row += [forecast_lower_bound[idx]]
			row += [forecast_upper_bound[idx]]
			row += [json.dumps(self._model_attributes)]
			row += [None]

			pd_row = pd.DataFrame([row], columns=self._COLUMNS)

			pd_row[self._scope.columns] = target_pks

			result = pd.concat([result, pd_row])

		return result

class DateTimeIndexGenerator(object):

	def __init__(self, period):
		if period == CommerceMLForecastPeriod.MONTH:
			self._freq = '1MS'
		elif period == CommerceMLForecastPeriod.WEEK:
			self._freq = '7D'
		else:
			raise ValueError("Unsupported period")

	def get_index(self, values):
		min_v = min(values)

		max_v = max(values)

		return pd.date_range(start=min_v, end=max_v, freq=self._freq)

	def get_prediction_index(self, start, count):

		return pd.date_range(
			start=start, periods=count + 1, freq=self._freq, closed='right'
		)

	def reindex(self, dataframe, target_column):
		new_index_values = self.get_index(dataframe.index.values)

		new_index = pd.Index(new_index_values)

		dataframe = dataframe.reindex(new_index)

		dataframe[target_column] = dataframe[target_column].fillna(0)

		dataframe.sort_index(inplace=True)

		return dataframe