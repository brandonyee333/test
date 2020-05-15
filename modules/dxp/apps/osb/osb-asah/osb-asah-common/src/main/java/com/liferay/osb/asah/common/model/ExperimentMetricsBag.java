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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class ExperimentMetricsBag {

	@JsonProperty("metrics")
	public List<ExperimentMetrics> getExperimentMetricsList() {
		return _experimentMetricsList;
	}

	public void setExperimentMetricsList(
		List<ExperimentMetrics> experimentMetricsList) {

		_experimentMetricsList = experimentMetricsList;
	}

	private List<ExperimentMetrics> _experimentMetricsList = new ArrayList<>();

}