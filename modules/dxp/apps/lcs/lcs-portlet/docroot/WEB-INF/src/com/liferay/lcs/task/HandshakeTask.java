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

package com.liferay.lcs.task;

import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.UptimeMonitoringAdvisor;
import com.liferay.lcs.exception.LCSHandshakeException;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ResponseMessage;
import com.liferay.lcs.runnable.LCSPortletBuildNumberCheckRunnable;
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.LCSPatcherUtil;
import com.liferay.lcs.util.LCSPortletPreferencesUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.lcs.util.comparator.MessagePriorityComparator;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.monitoring.PortalMonitoringControl;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.servlet.LiferayFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.portlet.PortletPreferences;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Marko Cikos
 */
public class HandshakeTask implements Task {

	public HandshakeTask(
		String key, LCSAlertAdvisor lcsAlertAdvisor,
		LCSConnectionManager lcsConnectionManager, ThreadFactory threadFactory,
		UptimeMonitoringAdvisor uptimeMonitoringAdvisor) {

		_handshakeReplyReads = GetterUtil.getInteger(
			PortletPropsValues.COMMUNICATION_HANDSHAKE_REPLY_READS, 5);

		_handshakeWaitTime = GetterUtil.getLong(
			PortletPropsValues.COMMUNICATION_HANDSHAKE_WAIT_TIME, 60000L);

		_heartbeatInterval = GetterUtil.getLong(
			PortletPropsValues.COMMUNICATION_HEARTBEAT_INTERVAL, 60000L);

		_key = key;
		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsConnectionManager = lcsConnectionManager;
		_threadFactory = threadFactory;
		_uptimeMonitoringAdvisor = uptimeMonitoringAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			if (e instanceof LCSHandshakeException) {
				throw (LCSHandshakeException)e;
			}

			throw new LCSHandshakeException(e);
		}
	}

	protected void doRun() throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Initiate handshake");
		}

		_lcsConnectionManager.deleteMessages(_key);

		HandshakeMessage handshakeMessage = _createHandshakeMessage();

		_lcsConnectionManager.sendMessage(handshakeMessage);

		_waitForHandshakeResponse();

		_uptimeMonitoringAdvisor.resetUptimes();

		_lcsConnectionManager.onHandshakeSuccess();

		if (_log.isInfoEnabled()) {
			_log.info("Established connection");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	protected boolean isNewLCSPortletBuildNumber(
		int latestLCSPortletBuildNumber) {

		if (latestLCSPortletBuildNumber > LCSUtil.getLCSPortletBuildNumber()) {
			return true;
		}

		return false;
	}

	protected boolean processResponse(
		List<Message> receivedMessages, List<Message> delayedMessages) {

		boolean receivedHandshakeResponse = false;

		for (Message receivedMessage : receivedMessages) {
			if (!(receivedMessage instanceof ResponseMessage)) {
				if (_log.isTraceEnabled()) {
					_log.trace(
						"Adding to delayed messages: " + receivedMessage);
				}

				delayedMessages.add(receivedMessage);

				continue;
			}

			ResponseMessage responseMessage = (ResponseMessage)receivedMessage;

			if (!(responseMessage instanceof HandshakeResponseMessage)) {
				continue;
			}

			HandshakeResponseMessage handshakeResponseMessage =
				(HandshakeResponseMessage)responseMessage;

			if (Validator.isNotNull(
					handshakeResponseMessage.getErrorMessage()) ||
				(handshakeResponseMessage.getErrorStatus() != 0)) {

				throw new LCSHandshakeException(
					handshakeResponseMessage.getErrorMessage(),
					handshakeResponseMessage.getErrorStatus());
			}

			if (handshakeResponseMessage.isHandshakeExpiredError()) {
				_lcsAlertAdvisor.add(LCSAlert.WARNING_HANDSHAKE_EXPIRED);

				throw new LCSHandshakeException(
					"Handshake expired. Check that the server is " +
						"synchronized with an NTP server.");
			}

			if (_log.isTraceEnabled()) {
				_log.trace("Received handshake response");
			}

			receivedHandshakeResponse = true;

			_submitLCSPortletBuildNumberCheck(
				handshakeResponseMessage.getLatestLCSPortletBuildNumber());
		}

		return receivedHandshakeResponse;
	}

	private HandshakeMessage _createHandshakeMessage() {
		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.setClusterExecutorEnabled(
			ClusterExecutorUtil.isEnabled());
		handshakeMessage.setCompanyIdsWebIds(_getCompanyIdsWebIds());
		handshakeMessage.setHashCode(_key.hashCode());
		handshakeMessage.setHeartbeatInterval(_heartbeatInterval);
		handshakeMessage.setKey(_key);
		handshakeMessage.setMetricsLCSServiceEnabled(
			_getMetricsLCSServiceEnabled());
		handshakeMessage.setMonitoringEnabled(_isMonitoringEnabled());
		handshakeMessage.setLCSPortletBuildNumber(
			LCSUtil.getLCSPortletBuildNumber());
		handshakeMessage.setPatchesLCSServiceEnabled(
			_getPatchesLCSServiceEnabled());

		if (LCSPatcherUtil.isConfigured()) {
			handshakeMessage.setPatchingToolEnabled(true);
		}

		handshakeMessage.setPatchingToolVersion(
			LCSPatcherUtil.getPatchingToolVersion());
		handshakeMessage.setPortalBuildNumber(ReleaseInfo.getBuildNumber());
		handshakeMessage.setPortalEdition(LCSUtil.getPortalEdition());
		handshakeMessage.setPortalPropertiesLCSServiceEnabled(
			_getPortalPropertiesLCSServiceEnabled());
		handshakeMessage.setUptimes(_getPortalUptimeEntries());

		return handshakeMessage;
	}

	private Map<Integer, String> _getCompanyIdsWebIds() {
		Map<Integer, String> companyIdsWebIds = new HashMap<>();

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			companyIdsWebIds.put(
				(int)company.getCompanyId(), company.getWebId());
		}

		return companyIdsWebIds;
	}

	private boolean _getMetricsLCSServiceEnabled() {
		PortletPreferences jxPortletPreferences =
			LCSPortletPreferencesUtil.fetchReadOnlyJxPortletPreferences();

		return GetterUtil.getBoolean(
			jxPortletPreferences.getValue(
				LCSConstants.METRICS_LCS_SERVICE_ENABLED,
				Boolean.FALSE.toString()));
	}

	private boolean _getPatchesLCSServiceEnabled() {
		PortletPreferences jxPortletPreferences =
			LCSPortletPreferencesUtil.fetchReadOnlyJxPortletPreferences();

		return GetterUtil.getBoolean(
			jxPortletPreferences.getValue(
				LCSConstants.PATCHES_LCS_SERVICE_ENABLED,
				Boolean.FALSE.toString()));
	}

	private boolean _getPortalPropertiesLCSServiceEnabled() {
		PortletPreferences jxPortletPreferences =
			LCSPortletPreferencesUtil.fetchReadOnlyJxPortletPreferences();

		return GetterUtil.getBoolean(
			jxPortletPreferences.getValue(
				LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED,
				Boolean.FALSE.toString()));
	}

	private List<Map<String, Long>> _getPortalUptimeEntries() {
		try {
			List<Map<String, Long>> uptimeEntries =
				_uptimeMonitoringAdvisor.getUptimes();

			_uptimeMonitoringAdvisor.resetCurrentUptimeEndTime(uptimeEntries);

			return uptimeEntries;
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to collect portal uptime entries required for " +
					"handshake message");

			throw new LCSHandshakeException(
				"Portal uptime entries are required", pe);
		}
	}

	private boolean _isMonitoringEnabled() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceReference<PortalMonitoringControl> serviceReference =
			bundleContext.getServiceReference(PortalMonitoringControl.class);

		if (serviceReference == null) {
			return false;
		}

		LiferayFilter liferayFilter = (LiferayFilter)bundleContext.getService(
			serviceReference);

		if (liferayFilter.isFilterEnabled()) {
			return true;
		}

		return false;
	}

	private void _submitLCSPortletBuildNumberCheck(
		int latestLCSPortletBuildNumber) {

		Runnable runnable = new LCSPortletBuildNumberCheckRunnable(
			latestLCSPortletBuildNumber, _lcsAlertAdvisor);

		Thread thread = _threadFactory.newThread(runnable);

		thread.start();
	}

	private void _waitForHandshakeResponse() throws JSONWebServiceException {
		int attempt = 0;
		List<Message> delayedMessages = new ArrayList<>();
		List<Message> receivedMessages = null;

		while (true) {
			if (attempt++ > _handshakeReplyReads) {
				throw new LCSHandshakeException(
					"Unable to establish a connection after " +
						_handshakeReplyReads + " handshakes");
			}

			receivedMessages = _lcsConnectionManager.getMessages(_key);

			if (receivedMessages.isEmpty()) {
				try {
					TimeUnit.MILLISECONDS.sleep(
						_handshakeWaitTime / _handshakeReplyReads);
				}
				catch (InterruptedException ie) {
				}

				continue;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Sending messages for processing");
			}

			if (_log.isTraceEnabled()) {
				_log.trace("Received messages: " + receivedMessages);
				_log.trace("Delayed messages: " + delayedMessages);
			}

			if (processResponse(receivedMessages, delayedMessages)) {
				break;
			}
		}

		Collections.sort(delayedMessages, new MessagePriorityComparator());

		for (Message delayedMessage : delayedMessages) {
			if (delayedMessage instanceof CommandMessage) {
				if (_log.isTraceEnabled()) {
					_log.trace(
						"Sending command to message bus: " + delayedMessage);
				}

				MessageBusUtil.sendMessage(
					"liferay/lcs_commands", delayedMessage);
			}
			else {
				_log.error(
					"There are no handlers for message: " + delayedMessage);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(HandshakeTask.class);

	private final int _handshakeReplyReads;
	private final long _handshakeWaitTime;
	private final long _heartbeatInterval;
	private final String _key;
	private final LCSAlertAdvisor _lcsAlertAdvisor;
	private final LCSConnectionManager _lcsConnectionManager;
	private final ThreadFactory _threadFactory;
	private final UptimeMonitoringAdvisor _uptimeMonitoringAdvisor;

}