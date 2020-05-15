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
import com.liferay.osb.asah.backend.model.Goal;
import com.liferay.osb.asah.backend.model.GoalMetric;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentMetrics;
import com.liferay.osb.asah.common.model.VariantMetrics;

import io.improbable.keanu.Keanu;
import io.improbable.keanu.algorithms.NetworkSamples;
import io.improbable.keanu.algorithms.PosteriorSamplingAlgorithm;
import io.improbable.keanu.algorithms.mcmc.NetworkSamplesGenerator;
import io.improbable.keanu.network.BayesianNetwork;
import io.improbable.keanu.network.KeanuProbabilisticModel;
import io.improbable.keanu.network.KeanuProbabilisticModelWithGradient;
import io.improbable.keanu.tensor.bool.BooleanTensor;
import io.improbable.keanu.tensor.bool.JVMBooleanTensor;
import io.improbable.keanu.tensor.dbl.DoubleTensor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.util.FastMath;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseExperimentMetricCalculator<T>
	implements ExperimentMetricCalculator {

	@Override
	public Long estimateDaysDuration(
		List<DXPVariantSettings> dxpVariantsSettings, Experiment experiment) {

		Goal goal = experiment.getGoal();

		if (goal.getGoalMetric() == GoalMetric.CLICK_RATE) {
			return null;
		}

		Stream<DXPVariantSettings> stream = dxpVariantsSettings.stream();

		List<Variant<T>> variants = stream.map(
			dxpVariantSettings -> mapVariant(dxpVariantSettings, experiment)
		).collect(
			Collectors.toList()
		);

		for (Variant<T> variant : variants) {
			if ((variant.getFailures() < 5) || (variant.getSuccesses() < 5)) {
				return null;
			}
		}

		setVariantsEstimatedSampleSize(
			experiment.getConfidenceLevel(), variants);

		return _estimateDaysLeft(experiment, variants);
	}

	protected ExperimentMetrics createEmptyExperimentMetrics(
		Experiment experiment, List<Variant<T>> variants) {

		ExperimentMetrics experimentMetrics = new ExperimentMetrics();

		for (Variant<T> variant : variants) {
			experimentMetrics.addVariantMetrics(
				_createEmptyVariantMetrics(variant));
		}

		experimentMetrics.setConfidenceLevel(0);
		experimentMetrics.setElapsedDays(_getExperimentElapsedDays(experiment));
		experimentMetrics.setEstimatedDaysLeft(null);
		experimentMetrics.setProcessedDate(LocalDateTime.now(ZoneOffset.UTC));

		return experimentMetrics;
	}

	protected ExperimentMetrics createExperimentMetrics(
		Experiment experiment, Map<String, DoubleTensor> variantDoubleTensorMap,
		List<Variant<T>> variants) {

		ExperimentMetrics experimentMetrics = new ExperimentMetrics();

		Variant<T> controlVariant = findControlVariant(variants);
		Goal goal = experiment.getGoal();

		for (Variant<T> variant : variants) {
			experimentMetrics.addVariantMetrics(
				_createVariantMetrics(
					experiment.getConfidenceLevel(), controlVariant,
					goal.getGoalMetric(), variant, variantDoubleTensorMap));
		}

		setVariantsEstimatedSampleSize(
			experiment.getConfidenceLevel(), variants);

		experimentMetrics.setCompletion(_getExperimentCompletion(variants));
		experimentMetrics.setConfidenceLevel(experiment.getConfidenceLevel());
		experimentMetrics.setElapsedDays(_getExperimentElapsedDays(experiment));
		experimentMetrics.setEstimatedDaysLeft(
			_estimateDaysLeft(experiment, variants));
		experimentMetrics.setProcessedDate(LocalDateTime.now(ZoneOffset.UTC));

		return experimentMetrics;
	}

	protected NetworkSamples createPosteriorNetworkSamples(
		BayesianNetwork bayesianNetwork, int dropCount, int sampleSize,
		SamplerType samplerType) {

		KeanuProbabilisticModel keanuProbabilisticModel =
			new KeanuProbabilisticModelWithGradient(bayesianNetwork);

		PosteriorSamplingAlgorithm posteriorSamplingAlgorithm;

		if (samplerType == SamplerType.METROPOLIS_HASTINGS) {
			posteriorSamplingAlgorithm =
				Keanu.Sampling.MetropolisHastings.withDefaultConfig();
		}
		else if (samplerType == SamplerType.NO_U_TURN_SAMPLER) {
			posteriorSamplingAlgorithm = Keanu.Sampling.NUTS.builder(
			).build();
		}
		else {
			throw new IllegalStateException(
				"Unrecognized sampler type: " + samplerType);
		}

		NetworkSamplesGenerator networkSamplesGenerator =
			posteriorSamplingAlgorithm.generatePosteriorSamples(
				keanuProbabilisticModel, bayesianNetwork.getLatentVertices());

		networkSamplesGenerator.dropCount(dropCount);

		return networkSamplesGenerator.generate(sampleSize);
	}

	protected Variant<T> findControlVariant(List<Variant<T>> variants) {
		Stream<Variant<T>> stream = variants.stream();

		Optional<Variant<T>> controlVariantOptional = stream.filter(
			Variant::isControl
		).findFirst();

		return controlVariantOptional.orElseThrow(IllegalStateException::new);
	}

	protected List<Variant<T>> getVariants(Experiment experiment) {
		List<DXPVariant> dxpVariants = experiment.getDXPVariants();

		if (dxpVariants == null) {
			return Collections.emptyList();
		}

		Stream<DXPVariant> stream = dxpVariants.stream();

		return stream.map(
			dxpVariant -> mapVariant(dxpVariant, experiment)
		).collect(
			Collectors.toList()
		);
	}

	protected abstract Variant<T> mapVariant(
		DXPVariant dxpVariant, Experiment experiment);

	protected abstract Variant<T> mapVariant(
		DXPVariantSettings dxpVariantSettings, Experiment experiment);

	protected abstract void setVariantsEstimatedSampleSize(
		double confidenceLevel, List<Variant<T>> variants);

	protected double toSafeDouble(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			return 0;
		}

		return value;
	}

	private double[] _calculateConfidenceIntervalArray(
		double confidenceLevel, DoubleTensor doubleTensor,
		GoalMetric goalMetric) {

		double lowerBound;
		double upperBound;

		Percentile percentile = new Percentile();

		double lowerQuantile = 100 - confidenceLevel;
		double upperQuantile = confidenceLevel;

		if (goalMetric == GoalMetric.MAX_SCROLL_DEPTH) {
			double[] data = doubleTensor.asFlatDoubleArray();

			lowerBound = percentile.evaluate(data, lowerQuantile);
			lowerBound = FastMath.max(0, lowerBound);

			upperBound = percentile.evaluate(data, upperQuantile);
			upperBound = FastMath.min(100, upperBound);
		}
		else if (goalMetric == GoalMetric.TIME_ON_PAGE) {
			DoubleTensor normalizedDoubleTensor = _log10exp(doubleTensor);

			double[] data = normalizedDoubleTensor.asFlatDoubleArray();

			lowerBound = percentile.evaluate(data, lowerQuantile);
			upperBound = percentile.evaluate(data, upperQuantile);
		}
		else {
			double[] data = doubleTensor.asFlatDoubleArray();

			lowerBound = percentile.evaluate(data, lowerQuantile);
			upperBound = percentile.evaluate(data, upperQuantile);
		}

		return new double[] {lowerBound, upperBound};
	}

	private double _calculateImprovement(
		double controlMedian, GoalMetric goalMetric, double variantMedian) {

		if (controlMedian == 0) {
			return 0;
		}

		double improvement =
			(variantMedian - controlMedian) / controlMedian * 100;

		if (goalMetric.isInverseMetric()) {
			return -improvement;
		}

		return improvement;
	}

	private long _calculateMaxExperimentDaysLeft(List<Variant<T>> variants) {
		Stream<Variant<T>> stream = variants.stream();

		return stream.map(
			variant ->
				(variant.getEstimatedSampleSize() - variant.getTrials()) /
					variant.getEstimatedTrafficRate()
		).mapToLong(
			value -> (long)Math.max(FastMath.ceil(value), 0)
		).max(
		).orElse(
			0L
		);
	}

	private double _calculateMedian(
		DoubleTensor doubleTensor, GoalMetric goalMetric) {

		Percentile percentile = new Percentile();

		if (goalMetric == GoalMetric.TIME_ON_PAGE) {
			DoubleTensor normalizedDoubleTensor = _log10exp(doubleTensor);

			return percentile.evaluate(
				normalizedDoubleTensor.asFlatDoubleArray(),
				_PERCENTILE_FOR_MEDIAN);
		}

		return percentile.evaluate(
			doubleTensor.asFlatDoubleArray(), _PERCENTILE_FOR_MEDIAN);
	}

	private double _calculateProbabilityToWin(
		GoalMetric goalMetric, Variant<T> variant,
		DoubleTensor variantDoubleTensor,
		Map<String, DoubleTensor> variantDoubleTensorMap) {

		long[] shape = variantDoubleTensor.getShape();

		int tensorLength = (int)shape[0];

		boolean[] trueArray = new boolean[tensorLength];

		Arrays.fill(trueArray, true);

		BooleanTensor booleanTensor = JVMBooleanTensor.create(trueArray, shape);

		for (Map.Entry<String, DoubleTensor> entry :
				variantDoubleTensorMap.entrySet()) {

			if (Objects.equals(variant.getDXPVariantId(), entry.getKey())) {
				continue;
			}

			DoubleTensor otherVariantDoubleTensor = variantDoubleTensorMap.get(
				entry.getKey());

			if (goalMetric.isInverseMetric()) {
				booleanTensor.andInPlace(
					variantDoubleTensor.lessThan(otherVariantDoubleTensor));
			}
			else {
				booleanTensor.andInPlace(
					variantDoubleTensor.greaterThan(otherVariantDoubleTensor));
			}
		}

		DoubleTensor doubleTensor = booleanTensor.toDoubleMask();

		return doubleTensor.sum() / tensorLength * 100;
	}

	private VariantMetrics _createEmptyVariantMetrics(Variant<T> variant) {
		VariantMetrics variantMetrics = new VariantMetrics(
			variant.isControl(), variant.getDXPVariantId());

		variantMetrics.setConfidenceIntervalArray(new double[] {0, 0});
		variantMetrics.setImprovement(0);
		variantMetrics.setMedian(0);
		variantMetrics.setProbabilityToWin(0);

		return variantMetrics;
	}

	private VariantMetrics _createVariantMetrics(
		double confidenceLevel, Variant<T> controlVariant,
		GoalMetric goalMetric, Variant<T> variant,
		Map<String, DoubleTensor> variantDoubleTensorMap) {

		VariantMetrics variantMetrics = new VariantMetrics(
			variant.isControl(), variant.getDXPVariantId());

		DoubleTensor variantDoubleTensor = variantDoubleTensorMap.get(
			variant.getDXPVariantId());

		variantMetrics.setConfidenceIntervalArray(
			_calculateConfidenceIntervalArray(
				confidenceLevel, variantDoubleTensor, goalMetric));
		variantMetrics.setMedian(
			_calculateMedian(variantDoubleTensor, goalMetric));
		variantMetrics.setProbabilityToWin(
			_calculateProbabilityToWin(
				goalMetric, variant, variantDoubleTensor,
				variantDoubleTensorMap));

		if (!variant.isControl()) {
			double controlMedian = _calculateMedian(
				variantDoubleTensorMap.get(controlVariant.getDXPVariantId()),
				goalMetric);

			variantMetrics.setImprovement(
				_calculateImprovement(
					controlMedian, goalMetric, variantMetrics.getMedian()));
		}

		return variantMetrics;
	}

	private Long _estimateDaysLeft(
		Experiment experiment, List<Variant<T>> variants) {

		long daysLeftMax = _calculateMaxExperimentDaysLeft(variants);
		long daysLeftMin =
			ExperimentConstants.MINIMUM_EXPERIMENT_DURATION_IN_DAYS -
				_getExperimentElapsedDays(experiment);

		return Math.max(daysLeftMax, daysLeftMin);
	}

	private Double _getExperimentCompletion(List<Variant<T>> variants) {
		Stream<Variant<T>> stream = variants.stream();

		return stream.filter(
			variant ->
				(variant.getEstimatedSampleSize() > 0) &&
				(variant.getTrials() > 0)
		).mapToDouble(
			variant ->
				(double)variant.getTrials() / variant.getEstimatedSampleSize() *
					100
		).map(
			ratio -> Math.min(ratio, 100)
		).average(
		).orElse(
			0D
		);
	}

	private long _getExperimentElapsedDays(Experiment experiment) {
		Date experimentStartedDate = experiment.getStartedDate();

		if (experimentStartedDate == null) {
			return 0;
		}

		return ChronoUnit.DAYS.between(
			experimentStartedDate.toInstant(), Instant.now());
	}

	private DoubleTensor _log10exp(DoubleTensor doubleTensor) {
		return doubleTensor.duplicate(
		).div(
			FastMath.log10(FastMath.E)
		).exp();
	}

	private static final double _PERCENTILE_FOR_MEDIAN = 50.0;

}