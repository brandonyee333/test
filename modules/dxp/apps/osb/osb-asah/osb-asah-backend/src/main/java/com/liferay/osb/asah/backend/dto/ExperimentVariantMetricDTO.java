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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.entity.ExperimentVariantMetric;

import java.math.BigDecimal;

import java.util.Arrays;

/**
 * @author Marcos Martins
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExperimentVariantMetricDTO {

	public ExperimentVariantMetricDTO(
		ExperimentVariantMetric experimentVariantMetric) {

		_confidenceIntervals = experimentVariantMetric.getConfidenceIntervals();
		_control = experimentVariantMetric.getControl();
		_dxpVariantId = experimentVariantMetric.getDXPVariantId();
		_improvement = experimentVariantMetric.getImprovement();
		_median = experimentVariantMetric.getMedian();
		_probabilityToWin = experimentVariantMetric.getProbabilityToWin();
	}

	@JsonProperty("confidenceInterval")
	public BigDecimal[] getConfidenceIntervals() {
		return Arrays.copyOf(_confidenceIntervals, _confidenceIntervals.length);
	}

	public boolean getControl() {
		return _control;
	}

	@JsonProperty("dxpVariantId")
	public String getDXPVariantId() {
		return _dxpVariantId;
	}

	public Double getImprovement() {
		return _improvement;
	}

	public Double getMedian() {
		return _median;
	}

	public Double getProbabilityToWin() {
		return _probabilityToWin;
	}

	private final BigDecimal[] _confidenceIntervals;
	private final boolean _control;
	private final String _dxpVariantId;
	private final Double _improvement;
	private final Double _median;
	private final Double _probabilityToWin;

}