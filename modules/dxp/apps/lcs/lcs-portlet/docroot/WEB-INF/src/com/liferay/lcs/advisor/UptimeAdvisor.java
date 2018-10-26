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

import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
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
public class UptimeAdvisor implements LCSEventListener {

	public UptimeAdvisor(LCSKeyAdvisor lcsKeyAdvisor) {
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	public List<Map<String, Long>> getUptimeEntries() {
		if (!_initalized) {
			throw new UnsupportedOperationException("Bean is not initialized");
		}

		List<Map<String, Long>> uptimeEntries = new ArrayList<>();

		for (Uptime uptime : _uptimes) {
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

		_uptimes = new ArrayList<>();

		_checkCurrentUptime();

		_addPersistedUptimes(_uptimes);

		_addCurrentUptime(_uptimes);

		_initalized = true;

		if (_log.isDebugEnabled()) {
			_log.debug("Initialized");
		}
	}

	@Override
	public synchronized void onLCSEvent(LCSEvent lcsEvent) {
		if (!_initalized) {
			if (_log.isDebugEnabled()) {
				_log.debug("Object instance is not initialized");
			}

			return;
		}

		if (lcsEvent == LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED) {
			_startNewUptimeSession();
		}

		if (lcsEvent == LCSEvent.HANDSHAKE_SUCCESS) {
			_resetUptimes();
		}
	}

	public void resetCurrentUptimeEndTime(List<Map<String, Long>> uptimes) {
		for (Map<String, Long> uptime : uptimes) {
			if (_currentUptime.startTime == uptime.get("startTime")) {
				uptime.remove("endTime");

				return;
			}
		}
	}

	public synchronized void updateCurrentUptime() {
		if (!_initalized) {
			throw new UnsupportedOperationException("Bean is not initialized");
		}

		_currentUptime.endTime = System.currentTimeMillis();

		_storeUptimes();
	}

	private void _addCurrentUptime(List<Uptime> uptimes) {
		for (Uptime uptime : uptimes) {
			if (_currentUptime.startTime == uptime.startTime) {
				return;
			}
		}

		uptimes.add(_currentUptime);
	}

	private void _addPersistedUptimes(List<Uptime> uptimesList)
		throws IOException {

		String key = _lcsKeyAdvisor.getKey();

		if (key == null) {
			return;
		}

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

	private void _checkCurrentUptime() {
		if (_currentUptime != null) {
			return;
		}

		Uptime uptime = new Uptime();

		uptime.endTime =
			_runtimeMXBean.getStartTime() + _runtimeMXBean.getUptime();

		uptime.startTime = _runtimeMXBean.getStartTime();

		_currentUptime = uptime;

		if (_log.isDebugEnabled()) {
			_log.debug("Updated with current uptime");
		}
	}

	private void _resetUptimes() {
		if (!_initalized) {
			throw new UnsupportedOperationException("Bean is not initialized");
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Reset uptimes");
		}

		Iterator<Uptime> iterator = _uptimes.iterator();

		while (iterator.hasNext()) {
			Uptime uptime = iterator.next();

			if (uptime.startTime != _currentUptime.startTime) {
				iterator.remove();
			}
		}

		_storeUptimes();

		if (_log.isDebugEnabled()) {
			_log.debug("Uptimes reset and ready for updates");
		}
	}

	private void _startNewUptimeSession() {
		updateCurrentUptime();

		long previousEndTime = _currentUptime.endTime;

		_currentUptime = new Uptime();

		_currentUptime.startTime = previousEndTime;
		_currentUptime.endTime = System.currentTimeMillis();

		_uptimes.add(_currentUptime);

		_storeUptimes();

		if (_log.isInfoEnabled()) {
			_log.info("New uptime session started " + _currentUptime);
		}
	}

	private void _storeUptimes() {
		String key = _lcsKeyAdvisor.getKey();

		if (key == null) {
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		ArrayNode arrayNode = objectMapper.createArrayNode();

		for (Uptime uptime : _uptimes) {
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

	private static final Log _log = LogFactoryUtil.getLog(UptimeAdvisor.class);

	private static final RuntimeMXBean _runtimeMXBean =
		ManagementFactory.getRuntimeMXBean();

	private Uptime _currentUptime;
	private boolean _initalized;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private List<Uptime> _uptimes;

	private class Uptime {

		@Override
		public String toString() {
			return "{endTime=" + endTime + ", startTime=" + startTime + "}";
		}

		protected long endTime;
		protected long startTime;

	}

}