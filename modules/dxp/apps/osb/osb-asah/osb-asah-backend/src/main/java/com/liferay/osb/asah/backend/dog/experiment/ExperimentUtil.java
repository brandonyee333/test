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

import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.backend.model.Goal;
import com.liferay.osb.asah.backend.model.GoalMetric;
import com.liferay.osb.asah.backend.model.PageMetricType;

import org.apache.commons.math3.util.FastMath;

/**
 * @author Marcellus Tavares
 */
public class ExperimentUtil {

	public static double calculateAlpha(
		double confidenceLevel, int variantsSize) {

		confidenceLevel /= 100;

		if (variantsSize == 2) {
			return (1 - confidenceLevel) / 2.0;
		}

		// Apply Šidák correction if there are 3 or more variants.
		// https://en.wikipedia.org/wiki/%C5%A0id%C3%A1k_correction

		return 1.0 -
			FastMath.pow(
				1.0 - ((1.0 - confidenceLevel) / 2.0), 1.0 / variantsSize);
	}

	public static PageMetricType getPageMetricType(Experiment experiment) {
		Goal goal = experiment.getGoal();

		GoalMetric goalMetric = goal.getGoalMetric();

		return goalMetric.getPageMetricType();
	}

}