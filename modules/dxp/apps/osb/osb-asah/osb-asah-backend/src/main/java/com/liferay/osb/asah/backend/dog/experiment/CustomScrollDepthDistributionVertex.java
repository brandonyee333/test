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

import io.improbable.keanu.KeanuRandom;
import io.improbable.keanu.tensor.TensorShapeValidation;
import io.improbable.keanu.tensor.dbl.DoubleTensor;
import io.improbable.keanu.vertices.LoadShape;
import io.improbable.keanu.vertices.LoadVertexParam;
import io.improbable.keanu.vertices.LogProbGraph;
import io.improbable.keanu.vertices.LogProbGraphSupplier;
import io.improbable.keanu.vertices.SamplableWithManyScalars;
import io.improbable.keanu.vertices.SaveVertexParam;
import io.improbable.keanu.vertices.Vertex;
import io.improbable.keanu.vertices.dbl.Differentiable;
import io.improbable.keanu.vertices.dbl.DoublePlaceholderVertex;
import io.improbable.keanu.vertices.dbl.DoubleVertex;
import io.improbable.keanu.vertices.dbl.probabilistic.ProbabilisticDouble;

import java.util.Map;
import java.util.Set;

/**
 * @author Edward Kwok-Yu Wong
 */
public class CustomScrollDepthDistributionVertex
	extends DoubleVertex
	implements Differentiable, LogProbGraphSupplier, ProbabilisticDouble,
			   SamplableWithManyScalars<DoubleTensor> {

	public CustomScrollDepthDistributionVertex(
		DoubleVertex q0DoubleVertex, DoubleVertex q25DoubleVertex,
		DoubleVertex q50DoubleVertex, DoubleVertex q75DoubleVertex,
		DoubleVertex q100DoubleVertex) {

		this(
			TensorShapeValidation.checkHasOneNonLengthOneShapeOrAllLengthOne(
				q0DoubleVertex.getShape(), q25DoubleVertex.getShape(),
				q50DoubleVertex.getShape(), q75DoubleVertex.getShape(),
				q100DoubleVertex.getShape()),
			q0DoubleVertex, q25DoubleVertex, q50DoubleVertex, q75DoubleVertex,
			q100DoubleVertex);
	}

	public CustomScrollDepthDistributionVertex(
		@LoadShape long[] initialShape,
		@LoadVertexParam(_Q0_NAME) DoubleVertex q0DoubleVertex,
		@LoadVertexParam(_Q25_NAME) DoubleVertex q25DoubleVertex,
		@LoadVertexParam(_Q50_NAME) DoubleVertex q50DoubleVertex,
		@LoadVertexParam(_Q75_NAME) DoubleVertex q75DoubleVertex,
		@LoadVertexParam(_Q100_NAME) DoubleVertex q100DoubleVertex) {

		super(initialShape);

		TensorShapeValidation.checkTensorsMatchNonLengthOneShapeOrAreLengthOne(
			initialShape, q0DoubleVertex.getShape(), q25DoubleVertex.getShape(),
			q50DoubleVertex.getShape(), q75DoubleVertex.getShape(),
			q100DoubleVertex.getShape());

		_q0DoubleVertex = q0DoubleVertex;
		_q25DoubleVertex = q25DoubleVertex;
		_q50DoubleVertex = q50DoubleVertex;
		_q75DoubleVertex = q75DoubleVertex;
		_q100DoubleVertex = q100DoubleVertex;

		setParents(
			q0DoubleVertex, q25DoubleVertex, q50DoubleVertex, q75DoubleVertex,
			q100DoubleVertex);
	}

	@Override
	public Map<Vertex, DoubleTensor> dLogProb(
		DoubleTensor value, Set<? extends Vertex> withRespectTo) {

		throw new UnsupportedOperationException();
	}

	@SaveVertexParam(_Q0_NAME)
	public DoubleVertex getQ0DoubleVertex() {
		return _q0DoubleVertex;
	}

	@SaveVertexParam(_Q25_NAME)
	public DoubleVertex getQ25DoubleVertex() {
		return _q25DoubleVertex;
	}

	@SaveVertexParam(_Q50_NAME)
	public DoubleVertex getQ50DoubleVertex() {
		return _q50DoubleVertex;
	}

	@SaveVertexParam(_Q75_NAME)
	public DoubleVertex getQ75DoubleVertex() {
		return _q75DoubleVertex;
	}

	@SaveVertexParam(_Q100_NAME)
	public DoubleVertex getQ100DoubleVertex() {
		return _q100DoubleVertex;
	}

	@Override
	public double logProb(DoubleTensor value) {
		CustomScrollDepthDistribution customScrollDepthDistribution =
			CustomScrollDepthDistribution.of(
				_q0DoubleVertex.getValue(), _q25DoubleVertex.getValue(),
				_q50DoubleVertex.getValue(), _q75DoubleVertex.getValue(),
				_q100DoubleVertex.getValue());

		return customScrollDepthDistribution.logProb(
			value
		).sum();
	}

	@Override
	public LogProbGraph logProbGraph() {
		DoublePlaceholderVertex xDoublePlaceHolderVertex =
			new DoublePlaceholderVertex(getShape());

		DoublePlaceholderVertex q0DoublePlaceholderVertex =
			new DoublePlaceholderVertex(_q0DoubleVertex.getShape());
		DoublePlaceholderVertex q25DoublePlaceholderVertex =
			new DoublePlaceholderVertex(_q25DoubleVertex.getShape());
		DoublePlaceholderVertex q50DoublePlaceholderVertex =
			new DoublePlaceholderVertex(_q50DoubleVertex.getShape());
		DoublePlaceholderVertex q75DoublePlaceholderVertex =
			new DoublePlaceholderVertex(_q75DoubleVertex.getShape());
		DoublePlaceholderVertex q100DoublePlaceholderVertex =
			new DoublePlaceholderVertex(_q100DoubleVertex.getShape());

		return LogProbGraph.builder(
		).input(
			this, xDoublePlaceHolderVertex
		).input(
			_q0DoubleVertex, q0DoublePlaceholderVertex
		).input(
			_q25DoubleVertex, q25DoublePlaceholderVertex
		).input(
			_q50DoubleVertex, q50DoublePlaceholderVertex
		).input(
			_q75DoubleVertex, q75DoublePlaceholderVertex
		).input(
			_q100DoubleVertex, q100DoublePlaceholderVertex
		).logProbOutput(
			CustomScrollDepthDistribution.logProbOutput(
				q0DoublePlaceholderVertex, q25DoublePlaceholderVertex,
				q50DoublePlaceholderVertex, q75DoublePlaceholderVertex,
				q100DoublePlaceholderVertex, xDoublePlaceHolderVertex)
		).build();
	}

	@Override
	public DoubleTensor sampleWithShape(long[] shape, KeanuRandom keanuRandom) {
		CustomScrollDepthDistribution customScrollDepthDistribution =
			CustomScrollDepthDistribution.of(
				_q0DoubleVertex.getValue(), _q25DoubleVertex.getValue(),
				_q50DoubleVertex.getValue(), _q75DoubleVertex.getValue(),
				_q100DoubleVertex.getValue());

		return customScrollDepthDistribution.sample(shape, keanuRandom);
	}

	private static final String _Q0_NAME = "_q0";

	private static final String _Q25_NAME = "_q25";

	private static final String _Q50_NAME = "_q50";

	private static final String _Q75_NAME = "_q75";

	private static final String _Q100_NAME = "_q100";

	private DoubleVertex _q0DoubleVertex;
	private DoubleVertex _q25DoubleVertex;
	private DoubleVertex _q50DoubleVertex;
	private DoubleVertex _q75DoubleVertex;
	private DoubleVertex _q100DoubleVertex;

}