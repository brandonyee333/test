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

/**
 * @author Edward Kwok-Yu Wong
 */
public class ExperimentDataPoint<T> {

	public ExperimentDataPoint(long trials, T value) {
		_trials = trials;
		_value = value;
	}

	public long getTrials() {
		return _trials;
	}

	public T getValue() {
		return _value;
	}

	public void setTrials(long trials) {
		_trials = trials;
	}

	public void setValue(T value) {
		_value = value;
	}

	private long _trials;
	private T _value;

}