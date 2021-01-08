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

package com.liferay.osb.asah.backend.dog.keanu;

import com.liferay.osb.asah.backend.dog.experiment.CustomScrollDepthDistribution;
import com.liferay.osb.asah.backend.dog.experiment.CustomScrollDepthDistributionVertex;

import io.improbable.keanu.KeanuRandom;
import io.improbable.keanu.tensor.dbl.DoubleTensor;
import io.improbable.keanu.vertices.LogProbGraph;
import io.improbable.keanu.vertices.Vertex;
import io.improbable.keanu.vertices.dbl.DoublePlaceholderVertex;
import io.improbable.keanu.vertices.dbl.DoubleVertex;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.ConstantDoubleVertex;

import org.apache.commons.math3.util.FastMath;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Edward Kwok-Yu Wong
 */
public class CustomScrollDepthDistributionTest {

	@Before
	public void setUp() {
		_random = new KeanuRandom(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAllQuantilesZero() {
		CustomScrollDepthDistribution.of(
			DoubleTensor.create(0), DoubleTensor.create(0),
			DoubleTensor.create(0), DoubleTensor.create(0),
			DoubleTensor.create(0));
	}

	@Test
	public void testSamplerBasicFunctionality() {
		CustomScrollDepthDistribution customScrollDepthDistribution =
			CustomScrollDepthDistribution.of(
				DoubleTensor.create(100), DoubleTensor.create(100),
				DoubleTensor.create(100), DoubleTensor.create(100),
				DoubleTensor.create(100));

		double previousP = 0;
		double previousResult = 0;

		for (double currentP = 0; currentP <= 1.01; currentP += .05) {
			DoubleTensor pDoubleTensor = DoubleTensor.create(currentP);

			DoubleTensor resultDoubleTensor =
				customScrollDepthDistribution.sample(
					pDoubleTensor, new long[0]);

			double currentResult = resultDoubleTensor.getValue(0);

			String message = String.format(
				"Newer Value does not exceed previous value.%nPrevious " +
					"f(%.2f) = %.4f%nCurrent f(%.2f) = %.4f",
				previousP, previousResult, currentP, currentResult);

			Assert.assertTrue(message, currentResult >= previousResult);

			previousP = currentP;
			previousResult = currentResult;
		}
	}

	@Test
	public void testSamplerWithZeroQuantileHeight() {
		_genericSamplerTestingWithQuantileRange(1, 0, 0, 0, 0, 0, 25);
		_genericSamplerTestingWithQuantileRange(0, 1, 0, 0, 0, 0, 50);
		_genericSamplerTestingWithQuantileRange(0, 0, 1, 0, 0, 25, 75);
		_genericSamplerTestingWithQuantileRange(0, 0, 0, 1, 0, 50, 100);
		_genericSamplerTestingWithQuantileRange(0, 0, 0, 0, 1, 75, 100);
	}

	@Test
	public void testScrollDepthDistributionLogProb() {
		CustomScrollDepthDistribution customScrollDepthDistribution =
			CustomScrollDepthDistribution.of(
				DoubleTensor.create(100), DoubleTensor.create(25),
				DoubleTensor.create(10), DoubleTensor.create(13),
				DoubleTensor.create(25));

		for (int value = -10; value <= 110; value += 5) {
			DoubleTensor outputTensor = customScrollDepthDistribution.logProb(
				DoubleTensor.create(value));

			double outputDouble = outputTensor.getValue(0);

			if (value < 0) {
				Assert.assertEquals(outputDouble, Double.NEGATIVE_INFINITY, 1);
			}
			else if (value <= 100) {
				Assert.assertTrue(
					"Log probability is negative", outputDouble < 0);
			}
			else {
				Assert.assertEquals(outputDouble, Double.NEGATIVE_INFINITY, 1);
			}
		}
	}

	@Test
	public void testScrollDepthVertexLogProbOutput() {
		DoublePlaceholderVertex q0 = new DoublePlaceholderVertex(
			new ConstantDoubleVertex(100));
		DoublePlaceholderVertex q25 = new DoublePlaceholderVertex(
			new ConstantDoubleVertex(25));
		DoublePlaceholderVertex q50 = new DoublePlaceholderVertex(
			new ConstantDoubleVertex(10));
		DoublePlaceholderVertex q75 = new DoublePlaceholderVertex(
			new ConstantDoubleVertex(13));
		DoublePlaceholderVertex q100 = new DoublePlaceholderVertex(
			new ConstantDoubleVertex(25));

		CustomScrollDepthDistributionVertex
			customScrollDepthDistributionVertex =
				new CustomScrollDepthDistributionVertex(
					q0, q25, q50, q75, q100);

		double sumOfAreas = 0;
		double sliceWidth = .1;

		for (double value = -5; value <= 105; value += sliceWidth) {
			LogProbGraph logProbGraph =
				customScrollDepthDistributionVertex.logProbGraph();

			_feedValue(logProbGraph, q0, q0.getValue());
			_feedValue(logProbGraph, q25, q25.getValue());
			_feedValue(logProbGraph, q50, q50.getValue());
			_feedValue(logProbGraph, q75, q75.getValue());
			_feedValue(logProbGraph, q100, q100.getValue());
			_feedValue(
				logProbGraph, customScrollDepthDistributionVertex,
				DoubleTensor.scalar(value));

			DoubleVertex logProbGraphOutput = logProbGraph.getLogProbOutput();

			DoubleTensor outputTensor = logProbGraphOutput.getValue();

			if ((value > 0) && (value <= 100)) {
				sumOfAreas += FastMath.exp(outputTensor.sum()) * sliceWidth;
			}
		}

		Assert.assertEquals(1.0, sumOfAreas, .01);
	}

	private static <T> void _feedValue(
		LogProbGraph logProbGraph, Vertex<T> input, T value) {

		Vertex<T> placeholderVertex = logProbGraph.getPlaceholder(input);

		placeholderVertex.setValue(value);
	}

	private void _genericSamplerTestingWithQuantileRange(
		double q0, double q25, double q50, double q75, double q100,
		double expectedLowerBound, double expectedUpperBound) {

		CustomScrollDepthDistribution customScrollDepthDistribution =
			CustomScrollDepthDistribution.of(
				DoubleTensor.create(q0), DoubleTensor.create(q25),
				DoubleTensor.create(q50), DoubleTensor.create(q75),
				DoubleTensor.create(q100));

		_verifyTensorValuesBounds(
			customScrollDepthDistribution.sample(new long[] {100000}, _random),
			expectedLowerBound, expectedUpperBound);
	}

	private void _verifyTensorValuesBounds(
		DoubleTensor inputDoubleTensor, double lowerBound, double upperBound) {

		DoubleTensor lowerBoundDoubleTensor = DoubleTensor.create(
			lowerBound, inputDoubleTensor.getShape());
		DoubleTensor upperBoundDoubleTensor = DoubleTensor.create(
			upperBound, inputDoubleTensor.getShape());

		DoubleTensor outOfBoundsMaskDoubleTensor =
			inputDoubleTensor.lessThanMask(
				lowerBoundDoubleTensor
			).times(
				inputDoubleTensor.greaterThanMask(upperBoundDoubleTensor)
			);

		if (outOfBoundsMaskDoubleTensor.sum() > 0) {
			Assert.fail(
				"The resulting tensor generated values outside the expected " +
					"range");
		}
	}

	private KeanuRandom _random;

}