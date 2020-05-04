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

import com.liferay.osb.asah.backend.constants.ExperimentConstants;
import com.liferay.osb.asah.backend.constants.SamplerType;
import com.liferay.osb.asah.backend.model.DXPVariant;
import com.liferay.osb.asah.backend.model.Experiment;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentMetrics;

import io.improbable.keanu.algorithms.NetworkSamples;
import io.improbable.keanu.network.BayesianNetwork;
import io.improbable.keanu.tensor.dbl.DoubleTensor;
import io.improbable.keanu.vertices.dbl.DoubleVertexSamples;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.ConstantDoubleVertex;
import io.improbable.keanu.vertices.dbl.probabilistic.BetaVertex;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Edward Kwok-Yu Wong
 * @author André Miranda
 * @author Marcellus Tavares
 */
@Component
public class DichotomousDataExperimentMetricCalculator
	extends BaseExperimentMetricCalculator<Double> {

	@Override
	public ExperimentMetrics calculateMetrics(Experiment experiment) {
		List<Variant<Double>> variants = getVariants(experiment);

		for (Variant<Double> variant : variants) {
			if ((variant.getFailures() < 5) || (variant.getSuccesses() < 5)) {
				return createEmptyExperimentMetrics(experiment, variants);
			}
		}

		Pair<double[], double[]> alphasAndBetasPair =
			_calculateAlphasAndBetasPair(variants);

		long[] shape = {variants.size(), 1};

		ConstantDoubleVertex alphaConstantDoubleVertex =
			new ConstantDoubleVertex(alphasAndBetasPair.getFirst(), shape);

		ConstantDoubleVertex betaConstantDoubleVertex =
			new ConstantDoubleVertex(alphasAndBetasPair.getSecond(), shape);

		BetaVertex betaVertex = new BetaVertex(
			shape, alphaConstantDoubleVertex, betaConstantDoubleVertex);

		NetworkSamples posteriorNetworkSamples = createPosteriorNetworkSamples(
			new BayesianNetwork(betaVertex.getConnectedGraph()),
			ExperimentConstants.DISCARDED_SAMPLES,
			ExperimentConstants.MONTE_CARLO_SAMPLE_SIZE,
			SamplerType.NO_U_TURN_SAMPLER);

		DoubleVertexSamples doubleTensorSamples =
			posteriorNetworkSamples.getDoubleTensorSamples(betaVertex);

		DoubleTensor doubleTensor = doubleTensorSamples.asTensor();

		doubleTensor.timesInPlace(100D);

		Map<String, DoubleTensor> variantDoubleTensorMap = new HashMap<>();

		for (int i = 0; i < variants.size(); i++) {
			Variant variant = variants.get(i);

			variantDoubleTensorMap.put(
				variant.getDXPVariantId(), doubleTensor.slice(1, i));
		}

		return createExperimentMetrics(
			experiment, variantDoubleTensorMap, variants);
	}

	@Override
	protected Variant<Double> mapVariant(
		DXPVariant dxpVariant, Experiment experiment) {

		Variant<Double> variant = new Variant<>(
			dxpVariant.isControl(), dxpVariant.getDXPVariantId(),
			dxpVariant.getTrafficSplit());

		TimeRange timeRange = _getTimeRange(
			experiment.getStartedDateLocalDateTime());

		ExperimentDataPoint<Double> experimentDataPoint =
			_experimentDataDog.fetchDichotomousDataPoint(
				experiment.getDataSourceId(), experiment.getDXPExperienceId(),
				experiment.getId(),
				ExperimentUtil.getPageMetricType(experiment), null, timeRange,
				dxpVariant.getDXPVariantId());

		variant.addExperimentDataPoint(experimentDataPoint);

		setVariantProperties(timeRange.getDeltaDays(), variant);

		return variant;
	}

	@Override
	protected Variant<Double> mapVariant(
		DXPVariantSettings dxpVariantSettings, Experiment experiment) {

		Variant<Double> variant = new Variant<>(
			dxpVariantSettings.isControl(),
			dxpVariantSettings.getDXPVariantId(),
			dxpVariantSettings.getTrafficSplit());

		TimeRange timeRange = _getTimeRange(null);

		ExperimentDataPoint<Double> experimentDataPoint =
			_experimentDataDog.fetchDichotomousDataPoint(
				experiment.getDataSourceId(), experiment.getDXPExperienceId(),
				null, ExperimentUtil.getPageMetricType(experiment),
				experiment.getPageURL(), timeRange, null);

		double rate = dxpVariantSettings.getTrafficSplit() / 100;

		variant.addExperimentDataPoint(
			new ExperimentDataPoint<>(
				(long)(experimentDataPoint.getTrials() * rate),
				experimentDataPoint.getValue() * rate));

		setVariantProperties(timeRange.getDeltaDays(), variant);

		return variant;
	}

	protected void setVariantProperties(
		long deltaDays, Variant<Double> variant) {

		_setVariantEstimatedTrafficRate(deltaDays, variant);
		_setVariantSuccessAndTrials(variant);
		_setVariantSuccessRate(variant);
	}

	@Override
	protected void setVariantsEstimatedSampleSize(
		double confidenceLevel, List<Variant<Double>> variants) {

		Variant<Double> controlVariant = findControlVariant(variants);

		double alpha = ExperimentUtil.calculateAlpha(
			confidenceLevel, variants.size());

		NormalDistribution normalDistribution = new NormalDistribution();

		double statisticAlpha = FastMath.abs(
			normalDistribution.inverseCumulativeProbability(alpha));

		double statisticBeta = normalDistribution.inverseCumulativeProbability(
			ExperimentConstants.DEFAULT_POWER_LEVEL);

		int largestControlSampleSize = 0;

		for (Variant variant : variants) {
			if (variant.isControl()) {
				continue;
			}

			double trafficSplitRatio =
				variant.getTrafficSplit() / controlVariant.getTrafficSplit();

			int sampleSize = _calculateSampleSize(
				controlVariant.getSuccessRate(), statisticAlpha, statisticBeta,
				trafficSplitRatio);

			if (sampleSize > largestControlSampleSize) {
				largestControlSampleSize = sampleSize;
			}
		}

		for (Variant variant : variants) {
			double trafficSplitRatio =
				variant.getTrafficSplit() / controlVariant.getTrafficSplit();

			double estimatedSampleSize = FastMath.ceil(
				largestControlSampleSize * trafficSplitRatio);

			variant.setEstimatedSampleSize((long)estimatedSampleSize);
		}
	}

	private Pair<double[], double[]> _calculateAlphasAndBetasPair(
		List<Variant<Double>> variants) {

		long totalSuccesses = 0;
		long totalTrials = 0;

		for (Variant<Double> variant : variants) {
			totalSuccesses += variant.getSuccesses();
			totalTrials += variant.getTrials();
		}

		double[] alphas = new double[variants.size()];
		double[] betas = new double[variants.size()];

		double totalSuccessRate = 0;

		if (totalTrials > 0) {
			totalSuccessRate = (double)totalSuccesses / totalTrials;
		}

		double globalWeight = 0;

		if (variants.size() > 2) {
			globalWeight = _GROUP_WEIGHT;
		}

		int i = 0;

		for (Variant variant : variants) {
			alphas[i] =
				(((globalWeight * totalSuccessRate) +
					((1 - globalWeight) * variant.getSuccessRate())) *
						variant.getTrials()) + _ALPHA_PRIOR;

			betas[i] =
				(((globalWeight * (1 - totalSuccessRate)) +
					((1 - globalWeight) * (1 - variant.getSuccessRate()))) *
						variant.getTrials()) + _BETA_PRIOR;

			i++;
		}

		return new Pair<>(alphas, betas);
	}

	private int _calculateSampleSize(
		double controlSuccessRate, double statisticAlpha, double statisticBeta,
		double trafficSplitRatio) {

		double epsilon =
			controlSuccessRate *
				ExperimentConstants.
					DEFAULT_MINIMUM_DETECTABLE_EFFECT_PERCENTAGE;

		double target = controlSuccessRate + epsilon;

		return (int)FastMath.ceil(
			FastMath.pow(statisticAlpha + statisticBeta, 2) /
				FastMath.pow(epsilon, 2) *
					((target * (1 - target) / trafficSplitRatio) +
						(controlSuccessRate * (1 - controlSuccessRate))));
	}

	private TimeRange _getTimeRange(LocalDateTime startedDateLocalDateTime) {
		LocalDate localDate = LocalDate.now(ZoneOffset.UTC);

		LocalDate startLocalDate = null;

		if (startedDateLocalDateTime == null) {
			startLocalDate = localDate.minusDays(
				ExperimentConstants.MINIMUM_TRAFFIC_SAMPLE_SIZE);
		}
		else {
			startLocalDate = startedDateLocalDateTime.toLocalDate();
		}

		return TimeRange.of(startLocalDate);
	}

	private void _setVariantEstimatedTrafficRate(
		long deltaDays, Variant<Double> variant) {

		List<ExperimentDataPoint<Double>> experimentDataPoints =
			variant.getExperimentDataPoints();

		Stream<ExperimentDataPoint<Double>> stream =
			experimentDataPoints.stream();

		stream.map(
			ExperimentDataPoint::getTrials
		).reduce(
			Long::sum
		).map(
			variantTraffic -> (double)variantTraffic / deltaDays
		).ifPresent(
			variant::setEstimatedTrafficRate
		);
	}

	private void _setVariantSuccessAndTrials(Variant<Double> variant) {
		long successes = 0;
		long trials = 0;

		List<ExperimentDataPoint<Double>> experimentDataPoints =
			variant.getExperimentDataPoints();

		for (ExperimentDataPoint<Double> experimentDataPoint :
				experimentDataPoints) {

			successes += experimentDataPoint.getValue();
			trials += experimentDataPoint.getTrials();
		}

		variant.setSuccesses(successes);
		variant.setTrials(trials);
	}

	private void _setVariantSuccessRate(Variant<Double> variant) {
		if (variant.getTrials() == 0) {
			variant.setSuccessRate(0);

			return;
		}

		double successRate =
			(double)variant.getSuccesses() / variant.getTrials();

		if (successRate > 1) {
			successRate = 1;
		}

		variant.setSuccessRate(successRate);
	}

	private static final double _ALPHA_PRIOR = 1.0;

	private static final double _BETA_PRIOR = 1.0;

	private static final double _GROUP_WEIGHT = .25;

	@Autowired
	private ExperimentDataDog _experimentDataDog;

}