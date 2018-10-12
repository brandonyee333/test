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

package com.liferay.lcs.advisor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.liferay.lcs.util.LCSPortletPreferencesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class UptimeMonitoringAdvisor {

	public List<Map<String, Long>> getUptimeEntries() throws IOException {
		if (!_initalized) {
			throw new UnsupportedOperationException("Bean is not initialized");
		}

		List<Map<String, Long>> uptimeEntries = new ArrayList<>();

		for (Uptime uptime : _getUptimes()) {
			Map<String, Long> uptimeEntry = new HashMap<>();

			uptimeEntry.put("endTime", uptime.endTime);
			uptimeEntry.put("startTime", uptime.startTime);

			uptimeEntries.add(uptimeEntry);
		}

		return uptimeEntries;
	}

	public void init() throws IOException {
		if (_initalized) {
			return;
		}

		List<Uptime> uptimes = _getUptimes();

		_checkUptime(uptimes);

		_initalized = true;

		if (_log.isDebugEnabled()) {
			_log.debug("Initialized");
		}
	}

	public void resetCurrentUptimeEndTime(List<Map<String, Long>> uptimes) {
		long startTime = _runtimeMXBean.getStartTime();

		for (Map<String, Long> uptime : uptimes) {
			if (startTime == uptime.get("startTime")) {
				uptime.remove("endTime");

				return;
			}
		}
	}

	public synchronized void resetUptimes() throws IOException {
		if (!_initalized) {
			throw new UnsupportedOperationException("Bean is not initialized");
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Reset uptimes");
		}

		List<Uptime> uptimes = _getUptimes();

		Iterator<Uptime> iterator = uptimes.iterator();

		while (iterator.hasNext()) {
			Uptime next = iterator.next();

			if (next.startTime != _runtimeMXBean.getStartTime()) {
				iterator.remove();
			}
		}

		_storeUptimesJSONArray(uptimes);

		_readyForUpdates = true;

		if (_log.isDebugEnabled()) {
			_log.debug("Uptimes reset and ready for updates");
		}
	}

	public void setLCSKeyAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	public synchronized void updateCurrentUptime() throws IOException {
		if (!_initalized) {
			throw new UnsupportedOperationException("Bean is not initialized");
		}

		if (!_readyForUpdates) {
			return;
		}

		List<Uptime> uptimes = _getUptimes();

		for (Uptime uptime : uptimes) {
			if (uptime.startTime == _runtimeMXBean.getStartTime()) {
				uptime.endTime =
					_runtimeMXBean.getStartTime() + _runtimeMXBean.getUptime();

				_storeUptimesJSONArray(uptimes);

				if (_log.isTraceEnabled()) {
					_log.trace("Uptimes updated to: " + uptimes);
				}

				return;
			}
		}
	}

	private void _addPersistedUptimes(String key, List<Uptime> uptimesList)
		throws IOException {

		PortletPreferences portletPreferences =
			LCSPortletPreferencesUtil.fetchReadOnlyJxPortletPreferences();

		String json = portletPreferences.getValue("uptimes-" + key, null);

		if (json == null) {
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(json);

		if (jsonNode.isArray()) {
			Iterator<JsonNode> iterator = jsonNode.iterator();

			while (iterator.hasNext()) {
				JsonNode uptimeJSONNode = iterator.next();

				Uptime uptime = new Uptime();

				JsonNode endTimeJSONNode = uptimeJSONNode.get("endTime");

				uptime.endTime = endTimeJSONNode.asLong();

				JsonNode startTimeJSONNode = uptimeJSONNode.get("startTime");

				uptime.startTime = startTimeJSONNode.asLong();

				uptimesList.add(uptime);
			}
		}
	}

	private void _addTemporaryUptimes(List<Uptime> uptimes) {
		List<Uptime> mergeableTemporaryUptimes = new ArrayList<>();

		for (Uptime temporaryUptime : _temporaryUptimes) {
			boolean mergeable = true;

			for (Uptime uptime : uptimes) {
				if (temporaryUptime.startTime == uptime.startTime) {
					mergeable = false;

					break;
				}
			}

			if (mergeable) {
				mergeableTemporaryUptimes.add(temporaryUptime);
			}
		}

		uptimes.addAll(mergeableTemporaryUptimes);
	}

	private void _checkUptime(List<Uptime> uptimes) {
		long startTime = _runtimeMXBean.getStartTime();

		for (Uptime uptime : uptimes) {
			if (startTime == uptime.startTime) {
				return;
			}
		}

		Uptime uptime = new Uptime();

		uptime.endTime = startTime + _runtimeMXBean.getUptime();
		uptime.startTime = startTime;

		_temporaryUptimes.add(uptime);

		if (_log.isDebugEnabled()) {
			_log.debug("Temporary uptime created");
		}
	}

	private List<Uptime> _getUptimes() throws IOException {
		List<Uptime> uptimes = new ArrayList<>();

		String key = _lcsKeyAdvisor.getKey();

		if (key == null) {
			return uptimes;
		}

		_addPersistedUptimes(key, uptimes);

		_addTemporaryUptimes(uptimes);

		return uptimes;
	}

	private void _storeUptimesJSONArray(List<Uptime> uptimes) {
		String key = _lcsKeyAdvisor.getKey();

		if (key == null) {
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		ArrayNode arrayNode = objectMapper.createArrayNode();

		for (Uptime uptime : uptimes) {
			ObjectNode objectNode = objectMapper.createObjectNode();

			objectNode.put("endTime", uptime.endTime);
			objectNode.put("startTime", uptime.startTime);

			arrayNode.add(objectNode);
		}

		try {
			LCSPortletPreferencesUtil.store(
				"uptimes-" + key, arrayNode.toString());
		}
		catch (Exception e) {
			_log.error("Unable to store portal uptimes for key " + key, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UptimeMonitoringAdvisor.class);

	private static final RuntimeMXBean _runtimeMXBean =
		ManagementFactory.getRuntimeMXBean();

	private boolean _initalized;
	private LCSKeyAdvisor _lcsKeyAdvisor;
	private boolean _readyForUpdates;
	private final List<Uptime> _temporaryUptimes = new ArrayList();

	private class Uptime {

		@Override
		public String toString() {
			return
				"Uptime{startTime=" + startTime + ", endTime=" + endTime + "}";
		}

		protected long endTime;
		protected long startTime;

	}

}