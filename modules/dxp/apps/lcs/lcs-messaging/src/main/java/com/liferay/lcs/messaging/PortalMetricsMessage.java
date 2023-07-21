/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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