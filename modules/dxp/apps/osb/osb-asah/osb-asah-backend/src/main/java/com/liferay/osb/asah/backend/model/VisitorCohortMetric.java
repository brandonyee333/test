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

package com.liferay.osb.asah.backend.model;

import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
public class VisitorCohortMetric {

	public VisitorCohortMetric(
		Set<String> individualIds, String key, MetricType metricType,
		Double value) {

		_individualIds = individualIds;
		_key = key;
		_metricType = metricType;
		_value = value;
	}

	public Set<String> getIndividualIds() {
		return _individualIds;
	}

	public String getKey() {
		return _key;
	}

	public MetricType getMetricType() {
		return _metricType;
	}

	public Double getValue() {
		return _value;
	}

	public void setIndividualIds(Set<String> individualIds) {
		_individualIds = individualIds;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setMetricType(MetricType metricType) {
		_metricType = metricType;
	}

	public void setValue(Double value) {
		_value = value;
	}

	private Set<String> _individualIds;
	private String _key;
	private MetricType _metricType;
	private Double _value;

}