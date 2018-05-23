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

package com.liferay.lcs.messaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class PortalMetricsMessage extends MetricsMessage {

	public List<Map<String, Object>> getLayoutPerformanceMetricsList() {
		return _layoutPerformanceMetricsList;
	}

	public List<Map<String, Object>> getPortletPerformanceMetricsList() {
		return _portletPerformanceMetricsList;
	}

	public void setLayoutPerformanceMetricsList(
		List<Map<String, Object>> layoutPerformanceMetricsList) {

		_layoutPerformanceMetricsList = layoutPerformanceMetricsList;
	}

	public void setPortletPerformanceMetricsList(
		List<Map<String, Object>> portletPerformanceMetricsList) {

		_portletPerformanceMetricsList = portletPerformanceMetricsList;
	}

	private List<Map<String, Object>> _layoutPerformanceMetricsList =
		new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> _portletPerformanceMetricsList =
		new ArrayList<Map<String, Object>>();

}