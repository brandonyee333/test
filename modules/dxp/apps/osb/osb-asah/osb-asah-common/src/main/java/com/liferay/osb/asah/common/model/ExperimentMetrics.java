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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class ExperimentMetrics {

	public void addVariantMetrics(VariantMetrics variantMetrics) {
		_variantMetricsList.add(variantMetrics);
	}

	public double getCompletion() {
		return _completion;
	}

	public double getConfidenceLevel() {
		return _confidenceLevel;
	}

	public long getElapsedDays() {
		return _elapsedDays;
	}

	public Long getEstimatedDaysLeft() {
		return _estimatedDaysLeft;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	public LocalDateTime getProcessedDate() {
		return _processedDate;
	}

	@JsonProperty("variantMetrics")
	public List<VariantMetrics> getVariantMetricsList() {
		return _variantMetricsList;
	}

	public void setCompletion(double completion) {
		_completion = completion;
	}

	public void setConfidenceLevel(double confidenceLevel) {
		_confidenceLevel = confidenceLevel;
	}

	public void setElapsedDays(long elapsedDays) {
		_elapsedDays = elapsedDays;
	}

	public void setEstimatedDaysLeft(Long estimatedDaysLeft) {
		_estimatedDaysLeft = estimatedDaysLeft;
	}

	public void setProcessedDate(LocalDateTime processedDate) {
		_processedDate = processedDate;
	}

	private double _completion;
	private double _confidenceLevel;
	private long _elapsedDays;
	private Long _estimatedDaysLeft;
	private LocalDateTime _processedDate;
	private final List<VariantMetrics> _variantMetricsList = new ArrayList<>();

}