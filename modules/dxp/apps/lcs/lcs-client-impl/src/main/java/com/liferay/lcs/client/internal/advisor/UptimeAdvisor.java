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

package com.liferay.lcs.client.internal.advisor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.util.LCSPortletPreferences;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(service = UptimeAdvisor.class)
public class UptimeAdvisor implements LCSEventListener {

	public UptimeAdvisor() {
	}

	public UptimeAdvisor(
		LCSEventManager lcsEventManager, LCSKeyAdvisor lcsKeyAdvisor,
		LCSPortletPreferences lcsPortletPreferences) {

		_lcsEventManager = lcsEventManager;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_lcsPortletPreferences = lcsPortletPreferences;

		_subscribeEvents();
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

	@Override
	public synchronized void onLCSEvent(LCSEvent lcsEvent) {
		if (!_initalized) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting event processing. Object instance is not " +
						"initialized.");
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

	@Activate
	protected void activate() throws IOException {
		if (_initalized) {
			return;
		}

		_uptimes = new ArrayList<>(_getPersistedUptimes());

		_checkCurrentUptime();

		_addCurrentUptime(_uptimes);

		_subscribeEvents();

		_initalized = true;

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		_currentUptime = null;

		_uptimes.clear();

		_uptimes = null;

		if (_lcsEventManager != null) {
			_lcsEventManager.unsubscribe(this);
		}

		_initalized = false;

		if (_log.isTraceEnabled()) {
			_log.trace("Deactivated " + this);
		}
	}

	private void _addCurrentUptime(List<Uptime> uptimes) {
		for (Uptime uptime : uptimes) {
			if (_currentUptime.startTime == uptime.startTime) {
				return;
			}
		}

		uptimes.add(_currentUptime);
	}

	private void _checkCurrentUptime() {
		if (_currentUptime != null) {
			return;
		}

		Uptime currentUptime = new Uptime();

		currentUptime.endTime = System.currentTimeMillis();

		long startTime = 0;

		for (Uptime uptime : _uptimes) {
			startTime = Math.max(startTime, uptime.endTime);
		}

		startTime = Math.max(startTime, _runtimeMXBean.getStartTime());

		currentUptime.startTime = startTime;

		_currentUptime = currentUptime;

		if (_log.isDebugEnabled()) {
			_log.debug("Updated current uptime");
		}
	}

	private List<Uptime> _getPersistedUptimes() throws IOException {
		String key = _lcsKeyAdvisor.getKey();

		if (key == null) {
			return Collections.emptyList();
		}

		PortletPreferences portletPreferences =
			_lcsPortletPreferences.fetchReadOnlyJxPortletPreferences();

		String json = portletPreferences.getValue("uptimes-" + key, null);

		if (json == null) {
			return Collections.emptyList();
		}

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(json);

		if (!jsonNode.isArray()) {
			return Collections.emptyList();
		}

		List<Uptime> uptimes = new ArrayList<>();

		Iterator<JsonNode> iterator = jsonNode.iterator();

		while (iterator.hasNext()) {
			JsonNode uptimeJSONNode = iterator.next();

			Uptime uptime = new Uptime();

			JsonNode endTimeJSONNode = uptimeJSONNode.get("endTime");

			uptime.endTime = endTimeJSONNode.asLong();

			JsonNode startTimeJSONNode = uptimeJSONNode.get("startTime");

			uptime.startTime = startTimeJSONNode.asLong();

			uptimes.add(uptime);
		}

		return uptimes;
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

		if (_log.isDebugEnabled()) {
			_log.debug("New uptime session started " + _currentUptime);
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
			_lcsPortletPreferences.store(
				"uptimes-" + key, arrayNode.toString());
		}
		catch (Exception e) {
			_log.error("Unable to store portal uptimes for key " + key, e);
		}
	}

	private void _subscribeEvents() {
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED, this);
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_SUCCESS, this);
	}

	private static final Log _log = LogFactoryUtil.getLog(UptimeAdvisor.class);

	private static final RuntimeMXBean _runtimeMXBean =
		ManagementFactory.getRuntimeMXBean();

	private Uptime _currentUptime;
	private boolean _initalized;

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private LCSPortletPreferences _lcsPortletPreferences;

	private List<Uptime> _uptimes;

	private class Uptime {

		@Override
		public String toString() {
			return StringBundler.concat(
				"{endTime=", endTime, ", startTime=", startTime, "}");
		}

		protected long endTime;
		protected long startTime;

	}

}