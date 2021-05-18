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

package com.liferay.osb.asah.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

import org.apache.commons.lang3.Range;

/**
 * @author Marcellus Tavares
 */
public class VariantMetrics {

	public VariantMetrics() {
	}

	public VariantMetrics(boolean control, String dxpVariantId) {
		_control = control;
		_dxpVariantId = dxpVariantId;
	}

	@JsonProperty("confidenceInterval")
	public double[] getConfidenceIntervalArray() {
		return Arrays.copyOf(
			_confidenceIntervalArray, _confidenceIntervalArray.length);
	}

	@JsonIgnore
	public Range<Double> getConfidenceIntervalRange() {
		if ((_confidenceIntervalArray == null) ||
			(_confidenceIntervalArray.length < 2)) {

			return Range.is(0D);
		}

		return Range.between(
			_confidenceIntervalArray[0], _confidenceIntervalArray[1]);
	}

	@JsonProperty("dxpVariantId")
	public String getDXPVariantId() {
		return _dxpVariantId;
	}

	public double getImprovement() {
		return _improvement;
	}

	public double getMedian() {
		return _median;
	}

	public double getProbabilityToWin() {
		return _probabilityToWin;
	}

	public boolean isControl() {
		return _control;
	}

	public void setConfidenceIntervalArray(double[] confidenceIntervalArray) {
		_confidenceIntervalArray = Arrays.copyOf(
			confidenceIntervalArray, confidenceIntervalArray.length);
	}

	public void setControl(boolean control) {
		_control = control;
	}

	public void setDXPVariantId(String dxpVariantId) {
		_dxpVariantId = dxpVariantId;
	}

	public void setImprovement(double improvement) {
		_improvement = improvement;
	}

	public void setMedian(double median) {
		_median = median;
	}

	public void setProbabilityToWin(double probabilityToWin) {
		_probabilityToWin = probabilityToWin;
	}

	private double[] _confidenceIntervalArray = {0.0, 0.0};
	private boolean _control;
	private String _dxpVariantId;
	private double _improvement;
	private double _median;
	private double _probabilityToWin;

}