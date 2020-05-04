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

import com.google.common.base.Preconditions;

import io.improbable.keanu.KeanuRandom;
import io.improbable.keanu.distributions.ContinuousDistribution;
import io.improbable.keanu.distributions.hyperparam.Diffs;
import io.improbable.keanu.tensor.dbl.DoubleTensor;
import io.improbable.keanu.vertices.dbl.DoubleVertex;
import io.improbable.keanu.vertices.dbl.nonprobabilistic.ConstantDoubleVertex;

/**
 * @author Edward Kwok-Yu Wong
 */
public class CustomScrollDepthDistribution implements ContinuousDistribution {

	public static DoubleVertex logProbOutput(
		DoubleVertex q0DoubleVertex, DoubleVertex q25DoubleVertex,
		DoubleVertex q50DoubleVertex, DoubleVertex q75DoubleVertex,
		DoubleVertex q100DoubleVertex, DoubleVertex xDoubleVertex) {

		DoubleVertex areaBetween0And25DoubleVertex = q0DoubleVertex.plus(
			q25DoubleVertex
		).times(
			12.5
		);

		DoubleVertex areaBetween25And50DoubleVertex = q25DoubleVertex.plus(
			q50DoubleVertex
		).times(
			12.5
		);

		DoubleVertex areaBetween50And75DoubleVertex = q50DoubleVertex.plus(
			q75DoubleVertex
		).times(
			12.5
		);

		DoubleVertex areaBetween75And100DoubleVertex = q75DoubleVertex.plus(
			q100DoubleVertex
		).times(
			12.5
		);

		DoubleVertex threshold0DoubleVertex = new ConstantDoubleVertex(0);
		DoubleVertex threshold25DoubleVertex = new ConstantDoubleVertex(25);

		DoubleVertex between0And25MaskDoubleVertex =
			xDoubleVertex.toGreaterThanOrEqualToMask(
				threshold0DoubleVertex
			).times(
				xDoubleVertex.toLessThanMask(threshold25DoubleVertex)
			);

		DoubleVertex threshold50DoubleVertex = new ConstantDoubleVertex(50);

		DoubleVertex between25And50MaskDoubleVertex =
			xDoubleVertex.toGreaterThanOrEqualToMask(
				threshold25DoubleVertex
			).times(
				xDoubleVertex.toLessThanMask(threshold50DoubleVertex)
			);

		DoubleVertex threshold75DoubleVertex = new ConstantDoubleVertex(75);

		DoubleVertex between50And75MaskDoubleVertex =
			xDoubleVertex.toGreaterThanOrEqualToMask(
				threshold50DoubleVertex
			).times(
				xDoubleVertex.toLessThanMask(threshold75DoubleVertex)
			);

		DoubleVertex threshold100DoubleVertex = new ConstantDoubleVertex(100);

		DoubleVertex between75And100MaskDoubleVertex =
			xDoubleVertex.toGreaterThanOrEqualToMask(
				threshold75DoubleVertex
			).times(
				xDoubleVertex.toLessThanOrEqualToMask(threshold100DoubleVertex)
			);

		DoubleVertex normalizationDoubleVertex =
			areaBetween0And25DoubleVertex.plus(
				areaBetween25And50DoubleVertex
			).plus(
				areaBetween50And75DoubleVertex
			).plus(
				areaBetween75And100DoubleVertex
			);

		DoubleVertex between0And25DoubleVertex = xDoubleVertex.times(
			q25DoubleVertex.minus(
				q0DoubleVertex
			).div(
				25
			)
		).plus(
			q0DoubleVertex
		).div(
			normalizationDoubleVertex
		);

		DoubleVertex between25And50ValueDoubleVertex = xDoubleVertex.minus(
			25
		).times(
			q50DoubleVertex.minus(
				q25DoubleVertex
			).div(
				25
			)
		).plus(
			q25DoubleVertex
		).div(
			normalizationDoubleVertex
		);

		DoubleVertex between50And75ValueDoubleVertex = xDoubleVertex.minus(
			50
		).times(
			q75DoubleVertex.minus(
				q50DoubleVertex
			).div(
				25
			)
		).plus(
			q50DoubleVertex
		).times(
			between50And75MaskDoubleVertex
		).div(
			normalizationDoubleVertex
		);

		DoubleVertex between75And100DoubleVertex = xDoubleVertex.minus(
			75
		).times(
			q100DoubleVertex.minus(
				q75DoubleVertex
			).div(
				25
			)
		).plus(
			q75DoubleVertex
		).times(
			between75And100MaskDoubleVertex
		).div(
			normalizationDoubleVertex
		);

		return between0And25DoubleVertex.times(
			between0And25MaskDoubleVertex
		).plus(
			between25And50ValueDoubleVertex.times(
				between25And50MaskDoubleVertex)
		).plus(
			between50And75ValueDoubleVertex.times(
				between50And75MaskDoubleVertex)
		).plus(
			between75And100DoubleVertex.times(between75And100MaskDoubleVertex)
		).log();
	}

	public static CustomScrollDepthDistribution of(
		DoubleTensor q0DoubleTensor, DoubleTensor q25DoubleTensor,
		DoubleTensor q50DoubleTensor, DoubleTensor q75DoubleTensor,
		DoubleTensor q100DoubleTensor) {

		return new CustomScrollDepthDistribution(
			q0DoubleTensor, q25DoubleTensor, q50DoubleTensor, q75DoubleTensor,
			q100DoubleTensor);
	}

	@Override
	public Diffs dLogProb(DoubleTensor doubleTensor) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DoubleTensor logProb(DoubleTensor doubleTensor) {
		DoubleTensor minValueDoubleTensor = DoubleTensor.create(0);
		DoubleTensor maxValueDoubleTensor = DoubleTensor.create(100);

		return prob(
			doubleTensor
		).logInPlace(
		).replaceNaN(
			Double.NEGATIVE_INFINITY
		).setWithMaskInPlace(
			doubleTensor.lessThanMask(minValueDoubleTensor),
			Double.NEGATIVE_INFINITY
		).setWithMaskInPlace(
			doubleTensor.greaterThanMask(maxValueDoubleTensor),
			Double.NEGATIVE_INFINITY
		);
	}

	public DoubleTensor prob(DoubleTensor doubleTensor) {
		DoubleTensor threshold0DoubleTensor = DoubleTensor.create(0);
		DoubleTensor threshold25DoubleTensor = DoubleTensor.create(25);
		DoubleTensor threshold50DoubleTensor = DoubleTensor.create(50);
		DoubleTensor threshold75DoubleTensor = DoubleTensor.create(75);
		DoubleTensor threshold100DoubleTensor = DoubleTensor.create(100);

		DoubleTensor between25And50MaskDoubleTensor =
			doubleTensor.greaterThanOrEqualToMask(
				threshold25DoubleTensor
			).times(
				doubleTensor.lessThanMask(threshold50DoubleTensor)
			);

		DoubleTensor between75And100MaskDoubleTensor =
			doubleTensor.greaterThanOrEqualToMask(
				threshold75DoubleTensor
			).times(
				doubleTensor.lessThanOrEqualToMask(threshold100DoubleTensor)
			);

		DoubleTensor normalizationDoubleTensor =
			_calculateNormalizationDoubleTensor();

		DoubleTensor between0And25DoubleTensor = doubleTensor.times(
			_q25DoubleTensor.minus(
				_q0DoubleTensor
			).div(
				25
			)
		).plus(
			_q0DoubleTensor
		).div(
			normalizationDoubleTensor
		);

		DoubleTensor between25And50DoubleTensor = doubleTensor.minus(
			25
		).times(
			_q50DoubleTensor.minus(
				_q25DoubleTensor
			).div(
				25
			)
		).plus(
			_q25DoubleTensor
		).div(
			normalizationDoubleTensor
		);

		DoubleTensor between50And75MaskDoubleTensor =
			doubleTensor.greaterThanOrEqualToMask(
				threshold50DoubleTensor
			).times(
				doubleTensor.lessThanMask(threshold75DoubleTensor)
			);

		DoubleTensor between50And75DoubleTensor = doubleTensor.minus(
			50
		).times(
			_q75DoubleTensor.minus(
				_q50DoubleTensor
			).div(
				25
			)
		).plus(
			_q50DoubleTensor
		).times(
			between50And75MaskDoubleTensor
		).div(
			normalizationDoubleTensor
		);

		DoubleTensor between75And100DoubleTensor = doubleTensor.minus(
			75
		).times(
			_q100DoubleTensor.minus(
				_q75DoubleTensor
			).div(
				25
			)
		).plus(
			_q75DoubleTensor
		).times(
			between75And100MaskDoubleTensor
		).div(
			normalizationDoubleTensor
		);

		return between0And25DoubleTensor.times(
			doubleTensor.greaterThanOrEqualToMask(
				threshold0DoubleTensor
			).times(
				doubleTensor.lessThanMask(threshold25DoubleTensor)
			)
		).plus(
			between25And50DoubleTensor.times(between25And50MaskDoubleTensor)
		).plus(
			between50And75DoubleTensor.times(between50And75MaskDoubleTensor)
		).plus(
			between75And100DoubleTensor.times(between75And100MaskDoubleTensor)
		);
	}

	public DoubleTensor sample(DoubleTensor pDoubleTensor, long[] shape) {
		DoubleTensor normalizationDoubleTensor =
			_calculateNormalizationDoubleTensor();

		pDoubleTensor = pDoubleTensor.times(normalizationDoubleTensor);

		DoubleTensor areaLessThan0DoubleTensor = DoubleTensor.create(0, shape);

		DoubleTensor areaBetween0And25DoubleTensor = _q0DoubleTensor.plus(
			_q25DoubleTensor
		).times(
			12.5
		);

		DoubleTensor areaBetween0And50DoubleTensor = _q25DoubleTensor.plus(
			_q50DoubleTensor
		).times(
			12.5
		).plus(
			areaBetween0And25DoubleTensor
		);

		DoubleTensor areaBetween0And75DoubleTensor = _q50DoubleTensor.plus(
			_q75DoubleTensor
		).times(
			12.5
		).plus(
			areaBetween0And50DoubleTensor
		);

		DoubleTensor areaBetween0And100DoubleTensor = _q75DoubleTensor.plus(
			_q100DoubleTensor
		).times(
			12.5
		).plus(
			areaBetween0And75DoubleTensor
		);

		DoubleTensor lessThan25Value = _calculateArea(
			areaLessThan0DoubleTensor, areaBetween0And25DoubleTensor,
			pDoubleTensor, _q0DoubleTensor, _q25DoubleTensor,
			DoubleTensor.create(0, shape), shape);

		DoubleTensor between25And50Value = _calculateArea(
			areaBetween0And25DoubleTensor, areaBetween0And50DoubleTensor,
			pDoubleTensor, _q25DoubleTensor, _q50DoubleTensor,
			DoubleTensor.create(25, shape), shape);

		DoubleTensor between50And75Value = _calculateArea(
			areaBetween0And50DoubleTensor, areaBetween0And75DoubleTensor,
			pDoubleTensor, _q50DoubleTensor, _q75DoubleTensor,
			DoubleTensor.create(50, shape), shape);

		DoubleTensor greaterThan75Value = _calculateArea(
			areaBetween0And75DoubleTensor, areaBetween0And100DoubleTensor,
			pDoubleTensor, _q75DoubleTensor, _q100DoubleTensor,
			DoubleTensor.create(75, shape), shape);

		return lessThan25Value.plus(
			between25And50Value
		).plus(
			between50And75Value
		).plus(
			greaterThan75Value
		);
	}

	@Override
	public DoubleTensor sample(long[] shape, KeanuRandom keanuRandom) {
		return sample(keanuRandom.nextDouble(shape), shape);
	}

	private static DoubleTensor _replacePositiveInfinity(
		DoubleTensor input, double newValue) {

		DoubleTensor largeValueTensor = DoubleTensor.create(Double.MAX_VALUE);

		DoubleTensor comparisonMask = input.greaterThanOrEqualToMask(
			largeValueTensor);

		return input.setWithMask(comparisonMask, newValue);
	}

	private CustomScrollDepthDistribution(
		DoubleTensor q0DoubleTensor, DoubleTensor q25DoubleTensor,
		DoubleTensor q50DoubleTensor, DoubleTensor q75DoubleTensor,
		DoubleTensor q100DoubleTensor) {

		Preconditions.checkArgument(
			q0DoubleTensor.greaterThanOrEqual(
				0.
			).allTrue(),
			"Quantile 0 values must be greater than zero");

		Preconditions.checkArgument(
			q25DoubleTensor.greaterThanOrEqual(
				0.
			).allTrue(),
			"Quantile 25 values must be greater than zero");

		Preconditions.checkArgument(
			q50DoubleTensor.greaterThanOrEqual(
				0.
			).allTrue(),
			"Quantile 50 values must be greater or equal than zero");

		Preconditions.checkArgument(
			q75DoubleTensor.greaterThanOrEqual(
				0.
			).allTrue(),
			"Quantile 75 values must be greater or equal than zero");

		Preconditions.checkArgument(
			q100DoubleTensor.greaterThanOrEqual(
				0.
			).allTrue(),
			"Quantile 100 values must be greater or equal than zero");

		Preconditions.checkArgument(
			q100DoubleTensor.greaterThanOrEqual(
				0.
			).allTrue(),
			"Quantile 100 values must be greater or equal than zero");

		Preconditions.checkArgument(
			q0DoubleTensor.plus(
				q25DoubleTensor
			).plus(
				q50DoubleTensor
			).plus(
				q75DoubleTensor
			).plus(
				q100DoubleTensor
			).greaterThan(
				0.
			).allTrue(),
			"At least one quantile must be greater than zero");

		_q0DoubleTensor = q0DoubleTensor;
		_q25DoubleTensor = q25DoubleTensor;
		_q50DoubleTensor = q50DoubleTensor;
		_q75DoubleTensor = q75DoubleTensor;
		_q100DoubleTensor = q100DoubleTensor;
	}

	private DoubleTensor _calculateArea(
		DoubleTensor areaLowerDoubleTensor, DoubleTensor areaUpperDoubleTensor,
		DoubleTensor pDoubleTensor, DoubleTensor qHeightLowerDoubleTensor,
		DoubleTensor qHeightUpperDoubleTensor,
		DoubleTensor qLengthLowerDoubleTensor, long[] shape) {

		DoubleTensor aDoubleTensor = qHeightUpperDoubleTensor.minus(
			qHeightLowerDoubleTensor
		).div(
			50
		);

		DoubleTensor bDoubleTensor = qHeightLowerDoubleTensor.duplicate();

		DoubleTensor cDoubleTensor = pDoubleTensor.minus(
			areaLowerDoubleTensor
		).unaryMinus();

		DoubleTensor higherQuadraticRootDoubleTensor =
			_generateHigherQuadraticRootDoubleTensor(
				aDoubleTensor, bDoubleTensor, cDoubleTensor);

		DoubleTensor zeroDoubleTensor = DoubleTensor.zeros(shape);

		DoubleTensor secondDegreePolynomialMaskDoubleTensor =
			higherQuadraticRootDoubleTensor.greaterThanMask(zeroDoubleTensor);

		DoubleTensor firstDegreePolynomialDoubleTensor =
			_replacePositiveInfinity(
				pDoubleTensor.div(
					qHeightLowerDoubleTensor
				).minus(
					qLengthLowerDoubleTensor
				),
				0);

		DoubleTensor firstDegreePolynomialMaskDoubleTensor =
			higherQuadraticRootDoubleTensor.lessThanOrEqualToMask(
				zeroDoubleTensor);

		DoubleTensor combinedDoubleTensor =
			firstDegreePolynomialDoubleTensor.times(
				firstDegreePolynomialMaskDoubleTensor
			).plus(
				higherQuadraticRootDoubleTensor.times(
					secondDegreePolynomialMaskDoubleTensor)
			);

		DoubleTensor withinPercentileMaskDoubleTensor =
			pDoubleTensor.greaterThanOrEqualToMask(
				areaLowerDoubleTensor
			).times(
				pDoubleTensor.lessThanMask(areaUpperDoubleTensor)
			);

		DoubleTensor maxLengthDoubleTensor = DoubleTensor.create(25, shape);

		DoubleTensor greaterThanPercentileMask =
			pDoubleTensor.greaterThanOrEqualToMask(areaUpperDoubleTensor);

		return combinedDoubleTensor.times(
			withinPercentileMaskDoubleTensor
		).plus(
			maxLengthDoubleTensor.times(greaterThanPercentileMask)
		).replaceNaN(
			0D
		);
	}

	private DoubleTensor _calculateNormalizationDoubleTensor() {
		DoubleTensor areaBetween0And25DoubleTensor = _q0DoubleTensor.plus(
			_q25DoubleTensor
		).times(
			12.5
		);

		DoubleTensor areaBetween25And50DoubleTensor = _q25DoubleTensor.plus(
			_q50DoubleTensor
		).times(
			12.5
		);

		DoubleTensor areaBetween50And75DoubleTensor = _q50DoubleTensor.plus(
			_q75DoubleTensor
		).times(
			12.5
		);

		DoubleTensor areaBetween75And100DoubleTensor = _q75DoubleTensor.plus(
			_q100DoubleTensor
		).times(
			12.5
		);

		return areaBetween0And25DoubleTensor.plus(
			areaBetween25And50DoubleTensor
		).plus(
			areaBetween50And75DoubleTensor
		).plus(
			areaBetween75And100DoubleTensor
		);
	}

	/**
	 * Higher positive root of the quadratic formula
	 * https://en.wikipedia.org/wiki/Quadratic_formula
	 */
	private DoubleTensor _generateHigherQuadraticRootDoubleTensor(
		DoubleTensor aDoubleTensor, DoubleTensor bDoubleTensor,
		DoubleTensor cDoubleTensor) {

		return bDoubleTensor.unaryMinus(
		).plus(
			bDoubleTensor.pow(
				2
			).minus(
				aDoubleTensor.times(
					cDoubleTensor
				).times(
					4
				)
			).sqrt()
		).div(
			aDoubleTensor.times(2)
		).replaceNaN(
			0D
		);
	}

	private final DoubleTensor _q0DoubleTensor;
	private final DoubleTensor _q25DoubleTensor;
	private final DoubleTensor _q50DoubleTensor;
	private final DoubleTensor _q75DoubleTensor;
	private final DoubleTensor _q100DoubleTensor;

}