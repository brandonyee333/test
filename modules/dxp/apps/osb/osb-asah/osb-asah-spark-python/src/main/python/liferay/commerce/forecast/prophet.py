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

from prophet import Prophet
from prophet.diagnostics import performance_metrics, \
	prophet_copy

import itertools
import json
import logging
import pandas as pd
import warnings

class ProphetCommerceForecast(BaseCommerceForecast):

	def __init__(
			self,
			period,
			scope,
			target,
			changepoint_prior_scale=None,
			changepoint_range=None,
			cross_validation_steps=None,
			daily_seasonality=None,
			growth=None,
			holidays_prior_scale=None,
			horizon_steps=None,
			interval_width=None,
			mcmc_samples=None,
			model_selection_criterion=None,
			n_changepoints=None,
			parallel_count=None,
			seasonality_mode=None,
			seasonality_prior_scale=None,
			target_column='actual',
			time_column='timestamp',
			weekly_seasonality=None,
			yearly_seasonality=None
	):
		super(ProphetCommerceForecast, self).__init__(
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

		self._changepoint_prior_scale = changepoint_prior_scale
		self._changepoint_range = changepoint_range
		self._daily_seasonality = daily_seasonality
		self._growth = growth
		self._holidays_prior_scale = holidays_prior_scale
		self._interval_width = interval_width
		self._mcmc_samples = mcmc_samples
		self._model = None
		self._model_attributes = None
		self._n_changepoints = n_changepoints
		self._seasonality_prior_scale = seasonality_prior_scale
		self._seasonality_mode = seasonality_mode
		self._weekly_seasonality = weekly_seasonality
		self._yearly_seasonality = yearly_seasonality

	def _cross_validation(self, model, cutoffs=None):

		data_frame = model.history.copy()

		data_frame = data_frame.reset_index(drop=True)

		predict_columns = ['ds', 'yhat']

		if model.uncertainty_samples:
			predict_columns.extend(['yhat_lower', 'yhat_upper'])

		# Identify largest seasonality period

		period_max = 0.

		for seasonality in model.seasonalities.values():
			period_max = max(period_max, seasonality['period'])

		seasonality_dt = pd.Timedelta(str(period_max) + ' days')

		# add validation of the cutoff to make sure that the min cutoff is
		# strictly greater than the min date in the history

		if min(cutoffs) <= data_frame['ds'].min():
			raise ValueError(
				"Minimum cutoff value is not strictly greater than min date"
				"in history"
			)

		# max value of cutoffs is <= (end date minus horizon)

		end_date_minus_horizon = data_frame['ds'].iloc[-1]

		if max(cutoffs) > end_date_minus_horizon:
			raise ValueError(
				"Maximum cutoff value is greater than end date minus horizon,"
				"no value for cross-validation remaining"
			)

		initial = cutoffs[0] - data_frame['ds'].min()

		# Check if the initial window
		# (that is, the amount of time between the start of the history and
		# the first cutoff) is less than the maximum seasonality period

		if initial < seasonality_dt:
			msg = 'Seasonality has period of {} days '.format(period_max)
			msg += 'which is larger than initial window. '
			msg += 'Consider increasing initial.'
			self._log.warning(msg)

		predicts = [
			self._single_cutoff_forecast(
				data_frame,
				model,
				cutoff,
				predict_columns
			)
			for cutoff in cutoffs
		]

		# Combine all predicted pd.DataFrame into one pd.DataFrame

		return pd.concat(predicts, axis=0).reset_index(drop=True)

	def _fit_model(self, time_series, configuration):
		model = Prophet(
			changepoint_range=self._changepoint_range,
			daily_seasonality=self._daily_seasonality,
			growth=self._growth,
			interval_width=self._interval_width,
			mcmc_samples=self._mcmc_samples,
			n_changepoints=self._n_changepoints,
			weekly_seasonality=self._weekly_seasonality,
			yearly_seasonality=self._yearly_seasonality,
			**configuration
		)

		return model.fit(time_series)

	def _get_horizon(self, time_series, cutoff):
		index_value = time_series[time_series['ds'] == cutoff].index.values[0]

		predict_ds = time_series.iloc[index_value + 1]['ds']

		return pd.to_timedelta(predict_ds - cutoff)

	def _generate_parameter_configs(self):
		configurations = itertools.product(
			self._changepoint_prior_scale,
			self._seasonality_prior_scale,
			self._holidays_prior_scale,
			self._seasonality_mode
		)

		parameter_labels = [
			'changepoint_prior_scale',
			'seasonality_prior_scale',
			'holidays_prior_scale',
			'seasonality_mode'
		]

		configurations = [
			dict(
				zip(parameter_labels, configuration)
			) for configuration in configurations
		]

		configuration_list = {
			i: configuration
			for i, configuration in enumerate(configurations)
		}

		return configuration_list

	def _single_cutoff_forecast(self, data_frame, model, cutoff, predict_columns):
		horizon = self._get_horizon(data_frame, cutoff)

		# Generate new object with copying fitting options

		model_cutoff = prophet_copy(model, cutoff)

		# Train model

		history_cutoff = data_frame[data_frame['ds'] <= cutoff]

		if history_cutoff.shape[0] < 2:
			raise Exception(
				'Less than two datapoints before cutoff. '
				'Increase initial window.'
			)

		model_cutoff.fit(history_cutoff, **model.fit_kwargs)

		# Calculate yhat

		index_predicted =  \
			(data_frame['ds'] > cutoff) & \
			(data_frame['ds'] <= cutoff + horizon)

		# Get the columns for the future data_frame

		columns = ['ds']

		if model_cutoff.growth == 'logistic':
			columns.append('cap')

			if model_cutoff.logistic_floor:
				columns.append('floor')

		columns.extend(model_cutoff.extra_regressors.keys())

		columns.extend([
			props[
				'condition_name'
			] for props in model_cutoff.seasonalities.values()
			if props['condition_name'] is not None
		])

		yhat = model_cutoff.predict(data_frame[index_predicted][columns])

		# Merge yhat(predicts), y(df, original data) and cutoff

		return pd.concat(
			[
				yhat[predict_columns],
				data_frame[index_predicted][['y']].reset_index(drop=True),
				pd.DataFrame({'cutoff': [cutoff] * len(yhat)})
			],
			axis=1
		)

	def _score_models(self, time_series, configurations):
		if self._parallel_count > 1:
			# execute configs in parallel

			executor = Parallel(
				n_jobs=self._parallel_count,
				backend='multiprocessing'
			)

			tasks = (
				delayed(self._score_model)(
					time_series,
					self._cross_validation_steps,
					configuration
				) for configuration in configurations.items()
			)

			scores = executor(tasks)
		else:
			scores = [
				self._score_model(
					time_series,
					self._cross_validation_steps,
					configuration
				) for configuration in configurations.items()
			]

		return scores

	def _score_model(self, data_frame, n_test, configuration):
		rmse = None

		id_, parameters = configuration

		if self._log.isEnabledFor(logging.INFO):
			if (id_ % 10) == 0:
				self._log.info("Testing {}: {}".format(id_, parameters))

		try:
			with warnings.catch_warnings():
				warnings.filterwarnings("ignore")

				cutoffs = list(
					data_frame['ds'][-(n_test + 1):-1].values
				)

				model = self._fit_model(
					data_frame, parameters
				)

				cross_validation_data_frame = self._cross_validation(
					model=model,
					cutoffs=cutoffs
				)

				performance_data_frame = performance_metrics(
					df=cross_validation_data_frame,
					rolling_window=1
				)

				rmse = performance_data_frame['rmse'].values[0]

		except Exception as e:
			self._log.warning(
				"Configuration {} error: {}".format(parameters, e)
			)

		if rmse is not None:
			self._log.debug(
				"Model [{}] RMSE: {}".format(str(parameters), rmse)
			)

		return id_, rmse

	def _to_prophet_format(self, time_series):
		return time_series.rename(
					columns={
						self._time_column: 'ds',
						self._target_column: 'y'
					}
				).sort_values(
					'ds'
				).reset_index(
					drop=True
				)

	def fit(self, time_series):
		target_pks = time_series[self._scope.columns].iloc[0].tolist()

		configurations = self._generate_parameter_configs()

		prophet_data_frame = self._to_prophet_format(time_series)

		scores = self._score_models(
			prophet_data_frame,
			configurations
		)

		# Filter completed scores

		scores = list(filter(lambda s: s[1] is not None, scores))

		if len(scores) == 0:
			self._log.warning("No model was trained, check data")

			return self._return_empty_pandas_data_frame(target_pks)

		# sort configs by model selection criterion

		scores.sort(key=lambda s: s[1])

		# Model attributes

		best_model_id, rmse = scores[0]

		model_attributes = dict()

		model_attributes['parameters'] = configurations[best_model_id]

		model_attributes['metrics'] = {'rmse': rmse}

		self._model_attributes = model_attributes

		self._log.info(
			"Best model: {}, configuration: '{}'. "
			"RMSE: {}".format(
				best_model_id, model_attributes, rmse
			)
		)

		self._model = self._fit_model(
			prophet_data_frame, model_attributes['parameters']
		)

		return self

	def fit_predict(self, time_series):
		self.fit(time_series)

		return self.predict(time_series)

	def predict(self, time_series):
		target_pks = time_series[self._scope.columns].iloc[0].tolist()

		if self._model is None:
			self._log.warning("No model was trained, check data")

			return self._return_empty_pandas_data_frame(target_pks)

		freq = 'MS'

		if self._period.label == 'week':
			freq = 'W'

		future = self._model.make_future_dataframe(
			periods=self._horizon_steps, freq=freq
		)

		predictions = self._model.predict(future.tail(self._horizon_steps))

		predictions = predictions[
			['ds', 'yhat', 'yhat_lower', 'yhat_upper']
		].rename(
			columns={
				'ds': self._time_column,
				'yhat': 'forecast',
				'yhat_lower': 'forecastLowerBound',
				'yhat_upper': 'forecastUpperBound'
			}
		)

		predictions[self._time_column] = \
			predictions[self._time_column].astype(int) // 1e6

		result = pd.DataFrame(predictions, columns=self.COLUMNS)

		result = result.assign(
			scope=self._scope.label,
			target=self._target.label,
			period=self._period.label,
			modelAttributes=json.dumps(self._model_attributes)
		)

		result = result.assign(
			**{k: v for k, v in zip(self._scope.columns, target_pks)}
		)

		return result