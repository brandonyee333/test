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

import com.liferay.lcs.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.advisor.InstallationEnvironmentAdvisorFactory;
import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.advisor.UptimeAdvisor;
import com.liferay.lcs.exception.LCSHandshakeException;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ResponseMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.rest.commons.LCSRESTError;
import com.liferay.lcs.runnable.LCSPortletBuildNumberCheckRunnable;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.LCSPatcherUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.lcs.util.comparator.MessagePriorityComparator;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.monitoring.PortalMonitoringControl;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.servlet.LiferayFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

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
		long lcsClusterEntryTokenId, LCSAlertAdvisor lcsAlertAdvisor,
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor,
		TaskSchedulerService taskSchedulerService, ThreadFactory threadFactory,
		UptimeAdvisor uptimeAdvisor) {

		_handshakeReplyReads = GetterUtil.getInteger(
			PortletPropsValues.COMMUNICATION_HANDSHAKE_REPLY_READS, 5);

		_handshakeWaitTime = GetterUtil.getLong(
			PortletPropsValues.COMMUNICATION_HANDSHAKE_WAIT_TIME, 60000L);

		_lcsClusterEntryTokenId = lcsClusterEntryTokenId;
		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_threadFactory = threadFactory;
		_uptimeAdvisor = uptimeAdvisor;

		if (_lcsKeyAdvisor.getKey() != null) {
			_key = _lcsKeyAdvisor.getKey();
		}
		else {
			_key = _createTemporaryKey();
		}

		_lcsEventListeners = new ArrayList<>();

		_lcsEventListeners.add(lcsClusterEntryTokenAdvisor);
		_lcsEventListeners.add(lcsGatewayClient);
		_lcsEventListeners.add(taskSchedulerService);
		_lcsEventListeners.add(uptimeAdvisor);

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void run() {
		try {
			if (_log.isInfoEnabled()) {
				_log.info("Initiate handshake");
			}

			if (!_temporaryKey) {
				_lcsGatewayClient.deleteMessages(_key);
			}

			HandshakeMessage handshakeMessage = _createHandshakeMessage();

			_lcsGatewayClient.sendMessage(handshakeMessage);

			_waitForHandshakeResponse();

			_notifyLCSEventListeners(LCSEvent.HANDSHAKE_SUCCESS);

			if (_log.isInfoEnabled()) {
				_log.info("Established connection");
			}
		}
		catch (Exception e) {
			LCSRESTError lcsRESTError = LCSRESTError.UNDEFINED;

			if (Validator.isNotNull(e.getMessage())) {
				lcsRESTError = LCSRESTError.getRESTError(e.getMessage());
			}

			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}
			else {
				if (_log.isWarnEnabled()) {
					if (lcsRESTError == LCSRESTError.UNDEFINED) {
						_log.warn(e.getMessage());
					}
					else {
						_log.warn(lcsRESTError.getErrorDescription());
					}
				}
			}

			if (e instanceof LCSHandshakeException) {
				_lcsClusterEntryTokenAdvisor.checkLCSClusterEntryTokenError(
					(LCSHandshakeException)e);
			}

			if ((lcsRESTError.getErrorCode() < 200) ||
				(lcsRESTError.getErrorCode() > 299)) {

				_notifyLCSEventListeners(LCSEvent.HANDSHAKE_FAILED);

				return;
			}

			_notifyLCSEventListeners(
				LCSEvent.HANDSHAKE_FAILED_LCS_CLUSTER_ENTRY_TOKEN_CORRUPTED);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
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

			if ((handshakeResponseMessage.getErrorCode() != 0) ||
				Validator.isNotNull(
					handshakeResponseMessage.getErrorMessage())) {

				throw new LCSHandshakeException(
					handshakeResponseMessage.getErrorMessage(),
					handshakeResponseMessage.getErrorCode());
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

			if (_temporaryKey ||
				Validator.isNotNull(handshakeResponseMessage.getNewKey())) {

				_lcsKeyAdvisor.updateKey(handshakeResponseMessage.getNewKey());

				_temporaryKey = false;
			}

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

		handshakeMessage.setHeartbeatInterval(
			GetterUtil.getLong(
				PortletPropsValues.COMMUNICATION_HEARTBEAT_INTERVAL, 60000L));

		handshakeMessage.setKey(_key);
		handshakeMessage.setLCSClusterEntryTokenId(_lcsClusterEntryTokenId);

		handshakeMessage.setLCSClusterNodeName(
			LicenseManagerUtil.getHostName() + StringPool.DASH +
				System.currentTimeMillis());

		handshakeMessage.setLCSPortletBuildNumber(
			LCSUtil.getLCSPortletBuildNumber());
		handshakeMessage.setLCSPortletVersion(
			PortletPropsValues.LCS_CLIENT_VERSION);
		handshakeMessage.setMonitoringEnabled(_isMonitoringEnabled());

		if (LCSPatcherUtil.isConfigured()) {
			handshakeMessage.setPatchingToolEnabled(true);
		}

		handshakeMessage.setPatchingToolVersion(
			LCSPatcherUtil.getPatchingToolVersion());
		handshakeMessage.setPortalBuildNumber(ReleaseInfo.getBuildNumber());
		handshakeMessage.setPortalEdition(LCSUtil.getPortalEdition());
		handshakeMessage.setProcessorCoresTotal(_getProcessorCoresTotal());
		handshakeMessage.setUptimes(_getPortalUptimeEntries());

		return handshakeMessage;
	}

	private String _createTemporaryKey() {
		if (_temporaryKey) {
			throw new UnsupportedOperationException(
				"Temporary key is already created");
		}

		UUID uuid = UUID.randomUUID();

		try {
			return _LCS_KEY_TEMPORARY_PREFIX + uuid.toString();
		}
		finally {
			_temporaryKey = true;
		}
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

	private List<Map<String, Long>> _getPortalUptimeEntries() {
		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		_uptimeAdvisor.resetCurrentUptimeEndTime(uptimeEntries);

		return uptimeEntries;
	}

	private int _getProcessorCoresTotal() {
		InstallationEnvironmentAdvisor installationEnvironmentAdvisor =
			InstallationEnvironmentAdvisorFactory.getInstance();

		return installationEnvironmentAdvisor.getProcessorCoresTotal();
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

	private void _notifyLCSEventListeners(LCSEvent lcsEvent) {
		for (LCSEventListener lcsEventListener : _lcsEventListeners) {
			try {
				lcsEventListener.onLCSEvent(lcsEvent);
			}
			catch (Throwable t) {
				_log.error("Unable to notify listener", t);
			}
		}
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

			receivedMessages = _lcsGatewayClient.getMessages(_key);

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
				_log.debug("Processing received messages");
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

	private static final String _LCS_KEY_TEMPORARY_PREFIX = "TEMP-KEY-";

	private static final Log _log = LogFactoryUtil.getLog(HandshakeTask.class);

	private final int _handshakeReplyReads;
	private final long _handshakeWaitTime;
	private final String _key;
	private final LCSAlertAdvisor _lcsAlertAdvisor;
	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final long _lcsClusterEntryTokenId;
	private final List<LCSEventListener> _lcsEventListeners;
	private final LCSGatewayClient _lcsGatewayClient;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private boolean _temporaryKey;
	private final ThreadFactory _threadFactory;
	private final UptimeAdvisor _uptimeAdvisor;

}