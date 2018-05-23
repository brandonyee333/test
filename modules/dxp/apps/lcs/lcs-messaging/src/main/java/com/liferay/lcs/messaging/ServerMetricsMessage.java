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