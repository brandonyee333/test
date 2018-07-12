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

package com.liferay.lcs.metrics;

import com.liferay.lcs.monitoring.statistics.AverageStatistics;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.monitoring.DataSample;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;

import java.io.UnsupportedEncodingException;

import java.lang.reflect.Method;

import java.net.URLDecoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class PortalMetricsAggregator {

	public synchronized boolean isEmpty() {
		return _performanceMetricsMap.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public synchronized List<Map<String, Object>>[] pop() {
		List<Map<String, Object>> layoutPerformanceMetricsList =
			new ArrayList<>();
		List<Map<String, Object>> portletPerformanceMetricsList =
			new ArrayList<>();

		Set<String> keys = _performanceMetricsMap.keySet();

		for (String key : keys) {
			Map<String, Object> performanceMetrics = new HashMap<>();

			MapUtil.copy(_performanceMetricsMap.get(key), performanceMetrics);

			AverageStatistics averageStatistics = _averageStatisticsMap.get(
				key);

			performanceMetrics.put(
				"duration", averageStatistics.getAverageTime());
			performanceMetrics.put("frequency", averageStatistics.getCount());

			if (key.startsWith(_METRICS_TYPE_LAYOUT)) {
				layoutPerformanceMetricsList.add(performanceMetrics);
			}
			else if (key.startsWith(_METRICS_TYPE_PORTLET)) {
				portletPerformanceMetricsList.add(performanceMetrics);
			}
		}

		_averageStatisticsMap.clear();
		_performanceMetricsMap.clear();

		List<Map<String, Object>>[] performanceMetrics = new List[2];

		performanceMetrics[0] = layoutPerformanceMetricsList;
		performanceMetrics[1] = portletPerformanceMetricsList;

		return performanceMetrics;
	}

	public synchronized void push(DataSample dataSample) {
		updatePerformanceMetricsMap(
			getPerformanceMetrics(dataSample), getMetricsType(dataSample),
			StringPool.BLANK);
	}

	public synchronized void push(List<DataSample> dataSamples) {
		List<Map<String, Object>> performanceMetricsList = new ArrayList<>();

		for (DataSample dataSample : dataSamples) {
			Map<String, Object> performanceMetrics = getPerformanceMetrics(
				dataSample);

			performanceMetrics.put("metricsType", getMetricsType(dataSample));

			performanceMetricsList.add(performanceMetrics);
		}

		List<String> layoutNames = new ArrayList<>();

		boolean requestTypeRender = true;

		for (Map<String, Object> performanceMetrics : performanceMetricsList) {
			String metricsType = (String)performanceMetrics.get("metricsType");

			if (metricsType.equals(_METRICS_TYPE_LAYOUT)) {
				layoutNames.add((String)performanceMetrics.get("name"));
			}
			else if (metricsType.equals(_METRICS_TYPE_PORTLET)) {
				String requestType = (String)performanceMetrics.get(
					"requestType");

				if (!requestType.equals("RENDER")) {
					requestTypeRender = false;
				}
			}
		}

		for (Map<String, Object> performanceMetrics : performanceMetricsList) {
			String metricsType = (String)performanceMetrics.get("metricsType");

			if (metricsType.equals(_METRICS_TYPE_LAYOUT) && requestTypeRender) {
				updatePerformanceMetricsMap(
					performanceMetrics, metricsType, StringPool.BLANK);
			}
			else if (metricsType.equals(_METRICS_TYPE_PORTLET)) {
				updatePerformanceMetricsMap(
					performanceMetrics, metricsType, StringPool.BLANK);

				for (String layoutName : layoutNames) {
					updatePerformanceMetricsMap(
						performanceMetrics, metricsType, layoutName);
				}
			}
		}
	}

	protected String getMetricsType(DataSample dataSample) {
		String namespace = dataSample.getNamespace();

		if (namespace.contains("Portal")) {
			return _METRICS_TYPE_LAYOUT;
		}
		else if (namespace.contains("Portlet")) {
			return _METRICS_TYPE_PORTLET;
		}
		else {
			return _METRICS_TYPE_SERVICE;
		}
	}

	protected Map<String, Object> getPerformanceMetrics(DataSample dataSample) {
		Map<String, Object> performanceMetrics = new HashMap<>();

		Class<?> clazz = dataSample.getClass();

		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			String methodName = method.getName();

			if (!methodName.startsWith("get") ||
				methodName.equals("getClass")) {

				continue;
			}

			String name = methodName.substring(3);

			name = TextFormatter.format(name, TextFormatter.I);

			Object value = null;

			try {
				value = method.invoke(dataSample);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}

				continue;
			}

			if ((value != null) && !(value instanceof Number) &&
				!(value instanceof String)) {

				value = String.valueOf(value);
			}

			if (value != null) {
				performanceMetrics.put(name, value);
			}
		}

		return performanceMetrics;
	}

	protected void updatePerformanceMetricsMap(
		Map<String, Object> performanceMetrics, String metricsType,
		String layoutName) {

		Map<String, Object> map = new HashMap<>();

		MapUtil.copy(performanceMetrics, map);

		performanceMetrics = map;

		performanceMetrics.put("metricsType", metricsType);

		AverageStatistics averageStatistics = null;
		String averageStatisticsName = null;
		String key = null;

		if (metricsType.equals(_METRICS_TYPE_LAYOUT)) {
			String name = (String)performanceMetrics.get("name");

			try {
				name = URLDecoder.decode(name, "UTF-8");
			}
			catch (UnsupportedEncodingException uee) {
				if (_log.isWarnEnabled()) {
					_log.warn(uee.getMessage(), uee);
				}
			}

			if (name.startsWith("/c") || name.endsWith(".jsp") ||
				name.contains("/control_panel")) {

				return;
			}

			if (name.contains(";jsessionid")) {
				int index = name.indexOf(";jsessionid");

				name = name.substring(0, index);
			}

			if (name.endsWith("/")) {
				name = name.substring(0, name.length() - 1);
			}

			performanceMetrics.put("name", name);

			averageStatisticsName = name;
			key = metricsType.concat(name);
		}
		else if (metricsType.equals(_METRICS_TYPE_PORTLET)) {
			performanceMetrics.put("layoutName", layoutName);

			String portletId = (String)performanceMetrics.get("portletId");

			if (portletId.contains("_INSTANCE")) {
				portletId = portletId.substring(
					0, portletId.indexOf("_INSTANCE"));

				performanceMetrics.put("portletId", portletId);
			}

			averageStatisticsName = portletId;
			key = metricsType.concat(portletId).concat(layoutName);
		}

		_performanceMetricsMap.put(key, performanceMetrics);

		if (_averageStatisticsMap.containsKey(key)) {
			averageStatistics = _averageStatisticsMap.get(key);
		}
		else {
			averageStatistics = new AverageStatistics(averageStatisticsName);

			_averageStatisticsMap.put(key, averageStatistics);
		}

		long duration = (Long)performanceMetrics.get("duration");

		averageStatistics.addDuration(duration);
	}

	private static final String _METRICS_TYPE_LAYOUT = "LAYOUT";

	private static final String _METRICS_TYPE_PORTLET = "PORTLET";

	private static final String _METRICS_TYPE_SERVICE = "SERVICE";

	private static final Log _log = LogFactoryUtil.getLog(
		PortalMetricsAggregator.class);

	private final Map<String, AverageStatistics> _averageStatisticsMap =
		new HashMap<>();
	private final Map<String, Map<String, Object>> _performanceMetricsMap =
		new HashMap<>();

}