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
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentMetrics;

import io.improbable.keanu.algorithms.NetworkSamples;
import io.improbable.keanu.network.BayesianNetwork;
import io.improbable.keanu.tensor.dbl.DoubleTensor;
import io.improbable.keanu.vertices.dbl.DoubleVertexSamples;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.ConstantDoubleVertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Edward Kwok-Yu Wong
 */
@Component
public class ScrollDepthExperimentCalculator
	extends BaseExperimentMetricCalculator<Double[]> {

	@Override
	public ExperimentMetrics calculateMetrics(Experiment experiment) {
		List<Variant<Double[]>> variants = getVariants(experiment);

		for (Variant<Double[]> variant : variants) {
			List<ExperimentDataPoint<Double[]>> dataPoints =
				variant.getExperimentDataPoints();

			if (dataPoints.isEmpty()) {
				return createEmptyExperimentMetrics(experiment, variants);
			}
		}

		List<double[]> quantileValues = _calculateQuantiles(variants);

		long[] shape = {variants.size(), 1};

		CustomScrollDepthDistributionVertex scrollDepthVertex =
			new CustomScrollDepthDistributionVertex(
				new ConstantDoubleVertex(quantileValues.get(0), shape),
				new ConstantDoubleVertex(quantileValues.get(1), shape),
				new ConstantDoubleVertex(quantileValues.get(2), shape),
				new ConstantDoubleVertex(quantileValues.get(3), shape),
				new ConstantDoubleVertex(quantileValues.get(4), shape));

		NetworkSamples posteriorNetworkSamples = createPosteriorNetworkSamples(
			new BayesianNetwork(scrollDepthVertex.getConnectedGraph()),
			ExperimentConstants.DISCARDED_SAMPLES,
			ExperimentConstants.MONTE_CARLO_SAMPLE_SIZE,
			SamplerType.METROPOLIS_HASTINGS);

		DoubleVertexSamples doubleTensorSamples =
			posteriorNetworkSamples.getDoubleTensorSamples(scrollDepthVertex);

		DoubleTensor doubleTensor = doubleTensorSamples.asTensor();

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
	protected Variant<Double[]> mapVariant(
		DXPVariant dxpVariant, Experiment experiment) {

		Variant<Double[]> variant = new Variant<>(
			dxpVariant.isControl(), dxpVariant.getDXPVariantId(),
			dxpVariant.getTrafficSplit());

		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			_experimentDataDog.fetchContinuousDataPoints(
				ExperimentUtil.getPageMetricType(experiment),
				experiment.getStartedDateLocalDateTime(),
				dxpVariant.getDXPVariantId());

		variant.setExperimentDataPoints(experimentDataPoints);

		setVariantEstimatedTrafficRate(variant);

		return variant;
	}

	@Override
	protected Variant<Double[]> mapVariant(
		DXPVariantSettings dxpVariantSettings, Experiment experiment) {

		Variant<Double[]> variant = new Variant<>(
			dxpVariantSettings.isControl(),
			dxpVariantSettings.getDXPVariantId(),
			dxpVariantSettings.getTrafficSplit());

		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			_experimentDataDog.fetchContinuousDataPoints(
				experiment.getDXPExperienceId(),
				ExperimentUtil.getPageMetricType(experiment));

		Stream<ExperimentDataPoint<Double[]>> stream =
			experimentDataPoints.stream();

		stream.map(
			experimentDataPoint -> _mapExperimentDataPoint(
				experimentDataPoint, dxpVariantSettings.getTrafficSplit())
		).forEach(
			variant::addExperimentDataPoint
		);

		setVariantEstimatedTrafficRate(variant);

		return variant;
	}

	protected void setVariantEstimatedTrafficRate(Variant<Double[]> variant) {
		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			variant.getExperimentDataPoints();

		Stream<ExperimentDataPoint<Double[]>> stream =
			experimentDataPoints.stream();

		stream.map(
			ExperimentDataPoint::getValue
		).map(
			values -> values.length
		).reduce(
			Integer::sum
		).map(
			variantTraffic -> variantTraffic / experimentDataPoints.size()
		).ifPresent(
			variant::setEstimatedTrafficRate
		);
	}

	@Override
	protected void setVariantsEstimatedSampleSize(
		double confidenceLevel, List<Variant<Double[]>> variants) {

		Variant<Double[]> controlVariant = findControlVariant(variants);

		double alpha = ExperimentUtil.calculateAlpha(
			confidenceLevel, variants.size());

		NormalDistribution normalDistribution = new NormalDistribution();

		double statisticAlpha = FastMath.abs(
			normalDistribution.inverseCumulativeProbability(alpha));

		double statisticBeta = normalDistribution.inverseCumulativeProbability(
			ExperimentConstants.DEFAULT_POWER_LEVEL);

		double[] controlRawDataListForAllDays =
			_getVariantExperimentDataPointValuesFlatten(controlVariant);

		double controlMean = StatUtils.mean(controlRawDataListForAllDays);

		int largestControlSampleSize = 0;

		for (Variant<Double[]> variant : variants) {
			if (variant.isControl()) {
				continue;
			}

			double trafficSplitRatio =
				variant.getTrafficSplit() / controlVariant.getTrafficSplit();

			ArrayList<double[]> controlAndVariantData = new ArrayList<>();

			controlAndVariantData.add(controlRawDataListForAllDays);

			double[] variantRawDataFlat =
				_getVariantExperimentDataPointValuesFlatten(variant);

			controlAndVariantData.add(variantRawDataFlat);

			double pooledStdDev = _calculatePooledStdDev(controlAndVariantData);

			Pair<Integer, Integer> sampleSizePair = _calculateSampleSizePair(
				controlMean, pooledStdDev, statisticAlpha, statisticBeta,
				trafficSplitRatio);

			if (sampleSizePair.getFirst() > largestControlSampleSize) {
				largestControlSampleSize = sampleSizePair.getFirst();
			}
		}

		for (Variant variant : variants) {
			if (variant.isControl()) {
				variant.setEstimatedSampleSize(largestControlSampleSize);

				continue;
			}

			double trafficSplitRatio =
				variant.getTrafficSplit() / controlVariant.getTrafficSplit();

			double estimatedSampleSize = FastMath.ceil(
				largestControlSampleSize * trafficSplitRatio);

			variant.setEstimatedSampleSize((long)estimatedSampleSize);
		}
	}

	private double _calculatePooledStdDev(List<double[]> rawData) {
		ArrayList<Double> individualSampleStandardDeviation = new ArrayList<>();

		ArrayList<Integer> individualSampleSampleSize = new ArrayList<>();

		for (double[] variantStdDevs : rawData) {
			StandardDeviation standardDeviation = new StandardDeviation(false);

			individualSampleStandardDeviation.add(
				standardDeviation.evaluate(variantStdDevs));

			individualSampleSampleSize.add(variantStdDevs.length);
		}

		double numerator = 0;
		double denominator = 0;

		for (int i = 0; i < individualSampleStandardDeviation.size(); i++) {
			int numOfSamples = individualSampleSampleSize.get(i);

			double standardDeviation = individualSampleStandardDeviation.get(i);

			numerator +=
				(numOfSamples - 1) * standardDeviation * standardDeviation;

			denominator += numOfSamples;
		}

		return toSafeDouble(
			FastMath.sqrt(
				numerator / (denominator - individualSampleSampleSize.size())));
	}

	private List<double[]> _calculateQuantiles(
		List<Variant<Double[]>> variants) {

		double[] q0s = new double[variants.size()];

		Arrays.fill(q0s, _Q0_PRIOR);

		double[] q25s = new double[variants.size()];

		Arrays.fill(q25s, _Q25_PRIOR);

		double[] q50s = new double[variants.size()];

		Arrays.fill(q50s, _Q50_PRIOR);

		double[] q75s = new double[variants.size()];

		Arrays.fill(q75s, _Q75_PRIOR);

		double[] q100s = new double[variants.size()];

		Arrays.fill(q100s, _Q100_PRIOR);

		// Need to get counts of each percentile for each variant

		for (int i = 0; i < variants.size(); i++) {
			Variant<Double[]> variant = variants.get(i);

			List<ExperimentDataPoint<Double[]>> variantResults =
				variant.getExperimentDataPoints();

			for (ExperimentDataPoint<Double[]> dataPointList : variantResults) {
				for (Double d : dataPointList.getValue()) {
					if (d == 0) {
						q0s[i]++;
					}
					else if (d == 25) {
						q25s[i]++;
					}
					else if (d == 50) {
						q50s[i]++;
					}
					else if (d == 75) {
						q75s[i]++;
					}
					else if (d == 100) {
						q100s[i]++;
					}
				}
			}
		}

		return Arrays.asList(q0s, q25s, q50s, q75s, q100s);
	}

	private Pair<Integer, Integer> _calculateSampleSizePair(
		double controlMean, double pooledStdDev, double statisticAlpha,
		double statisticBeta, double trafficSplitRatio) {

		double epsilon =
			ExperimentConstants.DEFAULT_MINIMUM_DETECTABLE_EFFECT_PERCENTAGE *
				controlMean;

		double sampleSizeControl = FastMath.ceil(
			(FastMath.pow(statisticAlpha + statisticBeta, 2) *
				FastMath.pow(pooledStdDev, 2) *
					(1.0 + (1.0 / trafficSplitRatio))) /
						FastMath.pow(epsilon, 2));

		double sampleSizeVariant = FastMath.ceil(
			trafficSplitRatio * sampleSizeControl);

		return new Pair<>((int)sampleSizeControl, (int)sampleSizeVariant);
	}

	private double[] _getVariantExperimentDataPointValuesFlatten(
		Variant<Double[]> variant) {

		return _getVariantExperimentDataPointValuesFlatten(
			variant, Function.identity());
	}

	private double[] _getVariantExperimentDataPointValuesFlatten(
		Variant<Double[]> variant,
		Function<Double, Double> valueMapperFunction) {

		List<ExperimentDataPoint<Double[]>> experimentDataPoints =
			variant.getExperimentDataPoints();

		Stream<ExperimentDataPoint<Double[]>> stream =
			experimentDataPoints.stream();

		return stream.map(
			ExperimentDataPoint::getValue
		).flatMap(
			Arrays::stream
		).map(
			valueMapperFunction
		).mapToDouble(
			Double::doubleValue
		).toArray();
	}

	private ExperimentDataPoint<Double[]> _mapExperimentDataPoint(
		ExperimentDataPoint<Double[]> experimentDataPoint,
		double variantTrafficSplit) {

		Stream<Double> stream = Arrays.stream(experimentDataPoint.getValue());

		Double[] newExperimentDataPointValue = stream.map(
			value -> value / variantTrafficSplit
		).toArray(
			Double[]::new
		);

		return new ExperimentDataPoint<>(
			(long)(experimentDataPoint.getTrials() / variantTrafficSplit),
			newExperimentDataPointValue);
	}

	private static final double _Q0_PRIOR = 10;

	private static final double _Q25_PRIOR = 1;

	private static final double _Q50_PRIOR = 1;

	private static final double _Q75_PRIOR = 1;

	private static final double _Q100_PRIOR = 2;

	@Autowired
	private ExperimentDataDog _experimentDataDog;

}