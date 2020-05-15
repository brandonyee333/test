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
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.model.VariantMetrics;

import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.math3.util.FastMath;

import org.junit.Assert;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseExperimentMetricCalculatorTestCase {

	protected void assertExperimentMetrics(
		ExperimentMetrics actualExperimentMetrics,
		double expectedConfidenceLevel, long expectedElapsedDays,
		long expectedEstimatedDaysLeft) {

		Assert.assertEquals(
			expectedConfidenceLevel,
			actualExperimentMetrics.getConfidenceLevel(), 1);
		Assert.assertEquals(
			expectedElapsedDays, actualExperimentMetrics.getElapsedDays());
		Assert.assertEquals(
			expectedEstimatedDaysLeft,
			(long)actualExperimentMetrics.getEstimatedDaysLeft());
	}

	protected void assertVariantMetrics(
		VariantMetrics variantMetrics, double confidenceIntervalLeft,
		double confidenceIntervalRight, String dxpVariantId, double improvement,
		double median, double probabilityToWin) {

		Assert.assertEquals(dxpVariantId, variantMetrics.getDXPVariantId());

		percentageBasedAssertEquals(
			confidenceIntervalLeft,
			variantMetrics.getConfidenceIntervalArray()[0],
			getMarginOfErrorPercentageForInterval());

		percentageBasedAssertEquals(
			confidenceIntervalRight,
			variantMetrics.getConfidenceIntervalArray()[1],
			getMarginOfErrorPercentageForInterval());

		Assert.assertEquals(
			improvement, variantMetrics.getImprovement(),
			getMarginOfErrorForImprovement());

		percentageBasedAssertEquals(
			median, variantMetrics.getMedian(),
			getMarginOfErrorPercentageForMedian());

		Assert.assertEquals(
			probabilityToWin, variantMetrics.getProbabilityToWin(),
			getMarginOfErrorForProbabilityToWin());
	}

	protected <T> Variant<T> createVariant(
		boolean control, String dxpVariantId, double trafficSplit,
		List<ExperimentDataPoint<T>> experimentDataPoints) {

		Variant<T> variant = new Variant<>(control, dxpVariantId, trafficSplit);

		for (ExperimentDataPoint<T> experimentDataPoint :
				experimentDataPoints) {

			variant.addExperimentDataPoint(experimentDataPoint);
		}

		return variant;
	}

	protected Variant<Double[]> createVariant(
			boolean control, String dxpVariantId, double trafficSplit,
			String... paths)
		throws IOException {

		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			new ArrayList<>();

		for (String path : paths) {
			Double[] values = readValuesFromFile(path);

			experimentDataPoints.add(
				new ExperimentDataPoint<>(values.length, values));
		}

		return createVariant(
			control, dxpVariantId, trafficSplit, experimentDataPoints);
	}

	protected ExperimentMetricCalculator
		getDichotomousDataExperimentMetricCalculator(
			long deltaDays, List<Variant<Double>> variants) {

		return new DichotomousDataExperimentMetricCalculator() {

			protected List<Variant<Double>> getVariants(Experiment experiment) {
				for (Variant<Double> variant : variants) {
					setVariantProperties(deltaDays, variant);
				}

				return variants;
			}

		};
	}

	protected double getMarginOfErrorForImprovement() {
		return 5;
	}

	protected double getMarginOfErrorForProbabilityToWin() {
		return 5;
	}

	protected double getMarginOfErrorPercentageForInterval() {
		return 5;
	}

	protected double getMarginOfErrorPercentageForMedian() {
		return 5;
	}

	protected ExperimentMetricCalculator
		getScrollDepthExperimentMetricCalculator(
			List<Variant<Double[]>> variants) {

		return new ScrollDepthExperimentCalculator() {

			protected List<Variant<Double[]>> getVariants(
				Experiment experiment) {

				variants.forEach(this::setVariantEstimatedTrafficRate);

				return variants;
			}

		};
	}

	protected void percentageBasedAssertEquals(
		double expected, double actual, double deltaPercentage) {

		double delta = FastMath.abs(expected * deltaPercentage / 100.0);

		double minimumDelta = .1;

		if (delta < minimumDelta) {
			delta = minimumDelta;
		}

		Assert.assertEquals(expected, actual, delta);
	}

	protected Double[] readValuesFromFile(String path) throws IOException {
		Resource resource = new ClassPathResource(path, getClass());

		try (InputStream inputStream = resource.getInputStream()) {
			LineIterator lineIterator = IOUtils.lineIterator(
				inputStream, StandardCharsets.UTF_8);

			List<Double> values = new LinkedList<>();

			while (lineIterator.hasNext()) {
				values.add(Double.valueOf(lineIterator.next()));
			}

			return values.toArray(new Double[0]);
		}
	}

}