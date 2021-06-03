/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.backend.dog.experiment;

import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.ExperimentMetric;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.Goal;
import com.liferay.osb.asah.common.model.GoalMetric;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ExperimentMetricDog {

	public ExperimentMetric calculateExperimentMetric(Experiment experiment) {
		ExperimentMetricCalculator experimentMetricCalculator =
			_getExperimentMetricCalculator(experiment);

		return experimentMetricCalculator.calculateExperimentMetric(experiment);
	}

	public Long estimateDaysDuration(
		List<DXPVariantSettings> dxpVariantsSettings, Experiment experiment) {

		ExperimentMetricCalculator experimentMetricCalculator =
			_getExperimentMetricCalculator(experiment);

		return experimentMetricCalculator.estimateDaysDuration(
			dxpVariantsSettings, experiment);
	}

	private ExperimentMetricCalculator _getExperimentMetricCalculator(
		Experiment experiment) {

		Goal goal = experiment.getGoal();

		if (goal == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Experiment is missing goal metric");
		}

		GoalMetric goalMetric = goal.getGoalMetric();

		if (!goalMetric.isContinuous()) {
			return _dichotomousDataExperimentMetricCalculator;
		}

		if (goalMetric == GoalMetric.MAX_SCROLL_DEPTH) {
			return _scrollDepthExperimentCalculator;
		}

		throw new IllegalStateException("Unexpected goal metric " + goalMetric);
	}

	@Autowired
	private DichotomousDataExperimentMetricCalculator
		_dichotomousDataExperimentMetricCalculator;

	@Autowired
	private ScrollDepthExperimentCalculator _scrollDepthExperimentCalculator;

}