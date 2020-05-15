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
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.model.VariantMetrics;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author André Miranda
 */
public class ClickThroughRateExperimentMetricCalculatorTest
	extends BaseExperimentMetricCalculatorTestCase {

	@Test
	public void test2Variants() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.CLICK_RATE, ""));

		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		LocalDateTime startedLocalDateTime = nowLocalDateTime.minusDays(2);

		experiment.setStartedDate(
			Date.from(startedLocalDateTime.toInstant(ZoneOffset.UTC)));

		ExperimentMetricCalculator experimentMetricCalculator =
			getDichotomousDataExperimentMetricCalculator(
				2,
				Arrays.asList(
					createVariant(
						true, "Control", 50,
						Arrays.asList(
							new ExperimentDataPoint<>(1025, 235D),
							new ExperimentDataPoint<>(895, 132D))),
					createVariant(
						false, "Variant", 50,
						Arrays.asList(
							new ExperimentDataPoint<>(1002, 289D),
							new ExperimentDataPoint<>(1523, 165D)))));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 2, 12);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 17.70, 20.63, "Control", 0, 19.14, 83);
		assertVariantMetrics(
			variantMetricsList.get(1), 16.76, 19.28, "Variant", -5, 18.00, 17);
	}

	@Test
	public void test3Variants() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.CLICK_RATE, ""));

		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		LocalDateTime startedLocalDateTime = nowLocalDateTime.minusDays(2);

		experiment.setStartedDate(
			Date.from(startedLocalDateTime.toInstant(ZoneOffset.UTC)));

		ExperimentMetricCalculator experimentMetricCalculator =
			getDichotomousDataExperimentMetricCalculator(
				2,
				Arrays.asList(
					createVariant(
						true, "Control", 50,
						Arrays.asList(
							new ExperimentDataPoint<>(1025, 235D),
							new ExperimentDataPoint<>(895, 132D))),
					createVariant(
						false, "Variant A", 25,
						Arrays.asList(
							new ExperimentDataPoint<>(1002, 289D),
							new ExperimentDataPoint<>(1523, 165D))),
					createVariant(
						false, "Variant B", 25,
						Arrays.asList(
							new ExperimentDataPoint<>(1065, 245D),
							new ExperimentDataPoint<>(1645, 135D)))));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 2, 13);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 17.13, 20.04, "Control", 0, 18.55, 77);
		assertVariantMetrics(
			variantMetricsList.get(1), 16.47, 18.97, "Variant A", -3, 17.70,
			23);
		assertVariantMetrics(
			variantMetricsList.get(2), 13.63, 15.87, "Variant B", -20, 14.73,
			0);
	}

	@Ignore
	@Test
	public void testNoData() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(.95);
		experiment.setGoal(new Goal(GoalMetric.CLICK_RATE, ""));

		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		LocalDateTime startedLocalDateTime = nowLocalDateTime.minusDays(1);

		experiment.setStartedDate(
			Date.from(startedLocalDateTime.toInstant(ZoneOffset.UTC)));

		ExperimentMetricCalculator experimentMetricCalculator =
			getDichotomousDataExperimentMetricCalculator(
				2,
				Arrays.asList(
					createVariant(
						true, "Control", 50,
						Collections.singletonList(
							new ExperimentDataPoint<>(0, 0D))),
					createVariant(
						false, "Variant", 50,
						Collections.singletonList(
							new ExperimentDataPoint<>(0, 0D)))));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 1, 13);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 0, 0, "Control", 0, 0, 50);
		assertVariantMetrics(
			variantMetricsList.get(1), 0, 0, "Variant", 0, 0, 50);
	}

}