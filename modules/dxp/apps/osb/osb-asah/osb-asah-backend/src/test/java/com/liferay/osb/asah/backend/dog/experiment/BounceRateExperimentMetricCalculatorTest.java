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
public class BounceRateExperimentMetricCalculatorTest
	extends BaseExperimentMetricCalculatorTestCase {

	@Test
	public void test2Variants() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.BOUNCE_RATE, ""));

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
							new ExperimentDataPoint<>(3025, 2235D),
							new ExperimentDataPoint<>(2895, 2132D))),
					createVariant(
						false, "Variant", 50,
						Arrays.asList(
							new ExperimentDataPoint<>(3200, 1789D),
							new ExperimentDataPoint<>(3523, 3002D)))));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 2, 12);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 71, 72, "Control", 0, 72, 0);
		assertVariantMetrics(
			variantMetricsList.get(1), 69, 69, "Variant", 3, 69, 99);
	}

	@Test
	public void test3Variants() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.BOUNCE_RATE, ""));

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
							new ExperimentDataPoint<>(3025, 2235D),
							new ExperimentDataPoint<>(2895, 2132D))),
					createVariant(
						false, "Variant A", 25,
						Arrays.asList(
							new ExperimentDataPoint<>(3200, 1789D),
							new ExperimentDataPoint<>(3523, 3002D))),
					createVariant(
						false, "Variant B", 25,
						Arrays.asList(
							new ExperimentDataPoint<>(2065, 1245D),
							new ExperimentDataPoint<>(1645, 1135D)))));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 2, 12);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 71, 72, "Control", 0, 71, 0);
		assertVariantMetrics(
			variantMetricsList.get(1), 69, 69, "Variant A", 2, 69, 1);
		assertVariantMetrics(
			variantMetricsList.get(2), 63, 64, "Variant B", 11, 63, 99);
	}

	@Ignore
	@Test
	public void testNoData() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.BOUNCE_RATE, ""));

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