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

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Kwok-Yu Wong
 */
public class Variant<T> {

	public Variant(boolean control, String dxpVariantId, Double trafficSplit) {
		_control = control;
		_dxpVariantId = dxpVariantId;
		_trafficSplit = trafficSplit;
	}

	public void addExperimentDataPoint(
		ExperimentDataPoint<T> experimentDataPoint) {

		_experimentDataPoints.add(experimentDataPoint);
	}

	public String getDXPVariantId() {
		return _dxpVariantId;
	}

	public long getEstimatedSampleSize() {
		return _estimatedSampleSize;
	}

	public double getEstimatedTrafficRate() {
		return _estimatedTrafficRate;
	}

	public List<ExperimentDataPoint<T>> getExperimentDataPoints() {
		return _experimentDataPoints;
	}

	@JsonIgnore
	public long getFailures() {
		return _trials - _successes;
	}

	public long getSuccesses() {
		return _successes;
	}

	public double getSuccessRate() {
		return _successRate;
	}

	public double getTrafficSplit() {
		return _trafficSplit;
	}

	public long getTrials() {
		return _trials;
	}

	public boolean isControl() {
		return _control;
	}

	public void setEstimatedSampleSize(long estimatedSampleSize) {
		_estimatedSampleSize = estimatedSampleSize;
	}

	public void setEstimatedTrafficRate(double estimatedTrafficRate) {
		_estimatedTrafficRate = estimatedTrafficRate;
	}

	public void setExperimentDataPoints(
		List<ExperimentDataPoint<T>> experimentDataPoints) {

		_experimentDataPoints = experimentDataPoints;
	}

	public void setSuccesses(long successes) {
		_successes = successes;
	}

	public void setSuccessRate(double successRate) {
		_successRate = successRate;
	}

	public void setTrials(long trials) {
		_trials = trials;
	}

	private final boolean _control;
	private final String _dxpVariantId;
	private long _estimatedSampleSize;
	private double _estimatedTrafficRate;
	private List<ExperimentDataPoint<T>> _experimentDataPoints =
		new ArrayList<>();
	private long _successes;
	private double _successRate;
	private final double _trafficSplit;
	private long _trials;

}