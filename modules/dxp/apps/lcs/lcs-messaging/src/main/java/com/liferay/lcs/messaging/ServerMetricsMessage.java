/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class ServerMetricsMessage extends MetricsMessage {

	public Map<String, Map<String, Object>> getCurrentThreadsMetrics() {
		return _currentThreadsMetrics;
	}

	public Map<String, Map<String, Object>> getJDBCConnectionPoolsMetrics() {
		return _jdbcConnectionPoolsMetrics;
	}

	public void setCurrentThreadsMetrics(
		Map<String, Map<String, Object>> currentThreadsMetrics) {

		_currentThreadsMetrics = currentThreadsMetrics;
	}

	@JsonProperty("jdbcConnectionPoolsMetrics")
	public void setJDBCConnectionPoolsMetrics(
		Map<String, Map<String, Object>> jdbcConnectionPoolsMetrics) {

		_jdbcConnectionPoolsMetrics = jdbcConnectionPoolsMetrics;
	}

	private Map<String, Map<String, Object>> _currentThreadsMetrics =
		new HashMap<String, Map<String, Object>>();
	private Map<String, Map<String, Object>> _jdbcConnectionPoolsMetrics =
		new HashMap<String, Map<String, Object>>();

}