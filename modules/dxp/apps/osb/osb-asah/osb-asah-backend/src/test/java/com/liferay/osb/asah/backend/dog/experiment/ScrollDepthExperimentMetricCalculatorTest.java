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

import java.io.IOException;

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
public class ScrollDepthExperimentMetricCalculatorTest
	extends BaseExperimentMetricCalculatorTestCase {

	@Test
	public void test2Variants() throws IOException {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.MAX_SCROLL_DEPTH, ""));

		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		LocalDateTime startedLocalDateTime = nowLocalDateTime.minusDays(2);

		experiment.setStartedDate(
			Date.from(startedLocalDateTime.toInstant(ZoneOffset.UTC)));

		ExperimentMetricCalculator experimentMetricCalculator =
			getScrollDepthExperimentMetricCalculator(
				Arrays.asList(
					createVariant(
						true, "Control", 50, "scroll_depth_control_day1.txt",
						"scroll_depth_control_day2.txt"),
					createVariant(
						false, "Variant", 50, "scroll_depth_variant_a_day1.txt",
						"scroll_depth_variant_a_day2.txt")));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 2, 12);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 2.11, 92.04, "Control", 0, 45.70, 50.08);
		assertVariantMetrics(
			variantMetricsList.get(1), 1.79, 92.77, "Variant", -0.34, 45.56,
			49.92);
	}

	@Test
	public void test3Variants() throws IOException {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.MAX_SCROLL_DEPTH, ""));

		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		LocalDateTime startedLocalDateTime = nowLocalDateTime.minusDays(2);

		experiment.setStartedDate(
			Date.from(startedLocalDateTime.toInstant(ZoneOffset.UTC)));

		ExperimentMetricCalculator experimentMetricCalculator =
			getScrollDepthExperimentMetricCalculator(
				Arrays.asList(
					createVariant(
						true, "Control", 50, "scroll_depth_control_day1.txt",
						"scroll_depth_control_day2.txt"),
					createVariant(
						false, "Variant A", 25,
						"scroll_depth_variant_a_day1.txt",
						"scroll_depth_variant_a_day2.txt"),
					createVariant(
						false, "Variant B", 25,
						"scroll_depth_variant_b_day1.txt",
						"scroll_depth_variant_b_day2.txt")));

		ExperimentMetrics experimentMetrics =
			experimentMetricCalculator.calculateMetrics(experiment);

		assertExperimentMetrics(experimentMetrics, 95, 2, 12);

		List<VariantMetrics> variantMetricsList =
			experimentMetrics.getVariantMetricsList();

		variantMetricsList.sort(
			Comparator.comparing(VariantMetrics::getDXPVariantId));

		assertVariantMetrics(
			variantMetricsList.get(0), 2.09, 92.04, "Control", 0, 45.70, 32.76);
		assertVariantMetrics(
			variantMetricsList.get(1), 1.81, 92.78, "Variant A", -0.26, 45.58,
			34.13);
		assertVariantMetrics(
			variantMetricsList.get(2), 1.92, 93.22, "Variant B", -7.79, 42.14,
			33.11);
	}

	@Ignore
	@Test
	public void testNoData() {
		Experiment experiment = new Experiment();

		experiment.setConfidenceLevel(95D);
		experiment.setGoal(new Goal(GoalMetric.MAX_SCROLL_DEPTH, ""));

		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		LocalDateTime startedLocalDateTime = nowLocalDateTime.minusDays(1);

		experiment.setStartedDate(
			Date.from(startedLocalDateTime.toInstant(ZoneOffset.UTC)));

		ExperimentMetricCalculator experimentMetricCalculator =
			getScrollDepthExperimentMetricCalculator(
				Arrays.asList(
					createVariant(
						true, "Control", 50,
						Collections.singletonList(
							new ExperimentDataPoint<>(0, new Double[0]))),
					createVariant(
						false, "Variant", 50,
						Collections.singletonList(
							new ExperimentDataPoint<>(0, new Double[0])))));

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