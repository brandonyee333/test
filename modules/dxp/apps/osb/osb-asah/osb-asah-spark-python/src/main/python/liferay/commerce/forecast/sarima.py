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

from liferay.commerce.forecast.common import BaseCommerceForecast
from liferay.commerce.forecast.constants import CommerceMLForecastPeriod

from math import sqrt

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

	def reindex(self, data_frame, target_column):
		new_index_values = self.get_index(data_frame.index.values)

		new_index = pd.Index(new_index_values)

		data_frame = data_frame.reindex(new_index)

		data_frame[target_column] = data_frame[target_column].fillna(0)

		data_frame.sort_index(inplace=True)

		return data_frame

class SARIMAXCommerceForecast(BaseCommerceForecast):

	def __init__(
		self,
		period,
		scope,
		target,
		cross_validation_steps=None,
		dynamic=True,
		horizon_steps=None,
		max_order=None,
		model_selection_criterion=None,
		order_d_params=None,
		order_p_params=None,
		order_q_params=None,
		parallel_count=None,
		seasonal_d_params=None,
		seasonal_m_params=None,
		seasonal_p_params=None,
		seasonal_q_params=None,
		target_column='actual',
		time_column='timestamp',
		trend_params=None
	):
		super(SARIMAXCommerceForecast, self).__init__(
			period=period,
			scope=scope,
			target=target,
			cross_validation_steps=cross_validation_steps,
			horizon_steps=horizon_steps,
			model_selection_criterion=model_selection_criterion,
			parallel_count=parallel_count,
			target_column=target_column,
			time_column=time_column
		)

		self._dynamic = dynamic
		self._indexer = DateTimeIndexGenerator(period)
		self._max_order = max_order
		self._model = None
		self._model_attributes = None
		self._order_d_params = order_d_params
		self._order_p_params = order_p_params
		self._order_q_params = order_q_params
		self._seasonal_d_params = seasonal_d_params
		self._seasonal_m_params = seasonal_m_params if seasonal_m_params else [
			1, period.period_value
		]
		self._seasonal_p_params = seasonal_p_params
		self._seasonal_q_params = seasonal_q_params
		self._trend_params = trend_params

		if self._log.isEnabledFor(logging.INFO):
			self._log.info(
				"Preparing SARIMAX model for: {}-{}-{}".format(
					self._scope.label, self._period.label, self._target.label
				)
			)

			self._log.info(
				"'p' parameter range: {}".format(self._order_p_params)
			)

			self._log.info(
				"'d' parameter range: {}".format(self._order_d_params)
			)

			self._log.info(
				"'q' parameter range: {}".format(self._order_q_params)
			)

			self._log.info(
				"'P' parameter range: {}".format(self._seasonal_p_params)
			)

			self._log.info(
				"'D' parameter range: {}".format(self._seasonal_d_params)
			)

			self._log.info(
				"'Q' parameter range: {}".format(self._seasonal_q_params)
			)

			self._log.info("Trend: {}".format(self._trend_params))

			self._log.info(
				"Seasonality period parameter range: {}".format(
					self._seasonal_m_params
				)
			)

			self._log.info("Max order: {}".format(self._max_order))

			self._log.info(
				"In task parallelism: {}".format(self._parallel_count)
			)

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

	def _generate_parameter_configs(self, series, series_length):
		# Evaluate 'd' parameters

		if self._order_d_params is None:
			try:
				diff = ndiffs(series, test='adf')
			except:
				diff = 0

			self._order_d_params = [diff]

			self._log.info(
				"Using 'd': {}".format(str(self._order_d_params))
			)

		# Evaluate 'D' parameters

		if self._seasonal_d_params is None:
			try:
				seasonal_diffs = nsdiffs(
					series, m=self._period.period_value
				)
			except:
				seasonal_diffs = 0

			self._seasonal_d_params = [seasonal_diffs]

			self._log.info(
				"Using 'D': {}".format(str(self._seasonal_d_params))
			)

		order_set = itertools.product(
			self._order_p_params,
			self._order_d_params,
			self._order_q_params
		)

		seasonal_set = itertools.product(
			self._seasonal_p_params,
			self._seasonal_d_params,
			self._seasonal_q_params,
			self._seasonal_m_params
		)

		configuration_products = itertools.product(
			order_set,
			seasonal_set,
			self._trend_params
		)

		configuration_products = filter(
			lambda cfg: self._validate_configuration(cfg, series_length),
			configuration_products
		)

		configuration_list = {
			i: cfg
			for i, cfg in enumerate(configuration_products)
		}

		return configuration_list

	def _score_model(self, series, n_test, configuration):
		rmse = None

		aic = None

		id_, parameters = configuration

		if self._log.isEnabledFor(logging.INFO):
			if (id_ % 10) == 0:
				self._log.info("Testing {}: {}".format(id_, parameters))

		try:
			with warnings.catch_warnings():
				warnings.filterwarnings("ignore")

				rmse, aic = self._walk_forward_validation(
					series,
					n_test,
					parameters
				)
		except Exception as e:
			self._log.warning(
				"Configuration {} error: {}".format(parameters, e)
			)

		if rmse is not None:
			self._log.debug(
				"Model [{}] RMSE: {}, AIC: {}".format(
					str(parameters), rmse, aic
				)
			)

		return id_, rmse, aic

	def _score_models(self, time_series, configurations):
		if self._parallel_count > 1:
			# execute configs in parallel

			executor = Parallel(
				n_jobs=self._parallel_count,
				backend='multiprocessing'
			)

			tasks = (
				delayed(self._score_model)(
					time_series[self._target_column],
					self._cross_validation_steps,
					configuration
				) for configuration in configurations.items()
			)

			scores = executor(tasks)
		else:
			scores = [
				self._score_model(
					time_series[self._target_column],
					self._cross_validation_steps,
					configuration
				) for configuration in configurations.items()
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

	def _walk_forward_validation(self, series, n_test, configuration):
		aic = None

		predictions = list()

		train, test = series[:-n_test], series[-n_test:]

		series = [x for x in train]

		# step over each time-step in the test set

		for i in range(len(test)):
			# fit model and make forecast for history

			y_hat, aic = self._fit_predict(series, configuration)

			# store forecast in list of predictions

			predictions.append(y_hat)

			# add actual observation to history for the next loop

			series.append(test.iloc[i])

		# estimate prediction rmse

		rmse = sqrt(mean_squared_error(test, predictions))

		return rmse, aic

	def fit(self, time_series):
		if not time_series.index.is_monotonic_increasing:
			raise ValueError("Time series should be monotonically increasing")

		time_series_values = time_series[self._target_column].values

		target_pks = time_series[self._scope.columns].iloc[0].tolist()

		time_series_length = len(time_series_values)

		# Check stationary

		try:
			adf_test = ADFTest()

			_, adf_should_diff = adf_test.should_diff(time_series_values)

			adf_stationary = not adf_should_diff

			kpss_test = KPSSTest()

			_, kpss_should_diff = kpss_test.should_diff(time_series_values)

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
			time_series_values, time_series_length
		)

		self._log.info("Testing {} configurations".format(len(configurations)))

		scores = self._score_models(time_series, configurations)

		# Filter completed scores

		scores = list(filter(lambda s: s[1] is not None, scores))

		if len(scores) == 0:
			self._log.warning("No model was trained, check data")

			return self._return_empty_pandas_data_frame(target_pks)

		# sort configs by model selection criterion

		scores.sort(key=lambda s: s[self._model_selection_criterion.value])

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
			"Best model: {}, configuration: {}, {}, {}."
			"AIC: {}, RMSE: {}".format(
				best_model_id, order, seasonal_order, trend, aic, rmse
			)
		)

		self._model = self._fit_model(
			time_series[self._target_column], order, seasonal_order, trend
		)

		return self

	def fit_predict(self, time_series):
		# Reindex series and fill missing values

		original_series_length = len(time_series)

		time_series.set_index(time_series[self._time_column], inplace=True)

		time_series = self._indexer.reindex(time_series, self._target_column)

		target_pks = time_series[self._scope.columns].iloc[0].tolist()

		if self._log.isEnabledFor(logging.INFO):
			series_coverage = original_series_length / len(time_series)

			self._log.info(
				"TargetPKs: {}, data length: {}  Coverage: {}".format(
					target_pks, original_series_length, series_coverage
				)
			)

		self.fit(time_series)

		return self.predict(time_series)

	def predict(self, time_series):
		target_pks = time_series[self._scope.columns].iloc[0].tolist()

		if self._model is None:
			self._log.warning("No model was trained, check data")

			return self._return_empty_pandas_data_frame(target_pks)

		start = self._model.nobs

		end = self._model.nobs + self._horizon_steps - 1

		prediction_date_start = time_series.index.values[-1]

		prediction_date_count = self._horizon_steps

		if self._log.isEnabledFor(logging.INFO):
			self._log.info("Target PKs {} ".format(target_pks))
			self._log.info("Start Date {} ".format(prediction_date_start))
			self._log.info(
				"TS {} ".format(time_series[self._target_column].values[-2:])
			)
			self._log.info("IDX {} ".format(time_series.index.values[-2:]))

		if type(self._dynamic) is int:
			start -= self._dynamic

			prediction_date_start = time_series.index.values[-self._dynamic]

			prediction_date_count += self._dynamic

		self._log.info(
			"Using start {} end {}, dynamic {}".format(
				start, end, self._dynamic
			)
		)

		forecast = self._model.get_prediction(
			start=start,
			end=end,
			dynamic=self._dynamic
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
				forecast_lower_bound[idx - start] = time_series[
					self._target_column].iloc[idx]

				forecast_predicted_mean.values[idx - start] = time_series[
					self._target_column].iloc[idx]

				forecast_upper_bound[idx - start] = time_series[
					self._target_column].iloc[idx]

		result = pd.DataFrame([], columns=self.COLUMNS)

		for idx in range(prediction_date_count):
			row = [None, None, None]
			row += [self._scope.label, self._period.label, self._target.label]
			row += [forecast_predicted_mean.values[idx]]
			row += [forecast_date_index[idx]]
			row += [forecast_lower_bound[idx]]
			row += [forecast_upper_bound[idx]]
			row += [json.dumps(self._model_attributes)]
			row += [None]

			pd_row = pd.DataFrame([row], columns=self.COLUMNS)

			pd_row[self._scope.columns] = target_pks

			result = pd.concat([result, pd_row])

		return result