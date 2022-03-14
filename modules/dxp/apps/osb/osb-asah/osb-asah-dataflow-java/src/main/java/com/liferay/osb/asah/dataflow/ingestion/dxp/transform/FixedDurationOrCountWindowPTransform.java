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

package com.liferay.osb.asah.dataflow.ingestion.dxp.transform;

import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.windowing.AfterPane;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Repeatedly;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollection;

import org.joda.time.Duration;

/**
 * @author Riccardo Ferrari
 */
public class FixedDurationOrCountWindowPTransform<T>
	extends PTransform<PCollection<T>, PCollection<T>> {

	public FixedDurationOrCountWindowPTransform(
		int elementCount, Duration intervalDuration) {

		_elementCount = elementCount;
		_intervalDuration = intervalDuration;
	}

	@Override
	public PCollection<T> expand(PCollection<T> input) {
		return input.apply(
			Window.<T>into(
				FixedWindows.of(_intervalDuration)
			).triggering(
				Repeatedly.forever(AfterPane.elementCountAtLeast(_elementCount))
			).discardingFiredPanes(
			).withAllowedLateness(
				Duration.ZERO
			));
	}

	private final int _elementCount;
	private final Duration _intervalDuration;

}