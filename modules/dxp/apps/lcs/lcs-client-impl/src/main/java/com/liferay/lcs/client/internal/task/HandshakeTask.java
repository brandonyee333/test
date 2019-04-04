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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.alert.advisor.LCSAlertAdvisor;
import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.client.internal.advisor.InstallationEnvironmentAdvisorFactory;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.advisor.UptimeAdvisor;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.exception.LCSHandshakeException;
import com.liferay.lcs.client.internal.runnable.LCSPortletBuildNumberCheckRunnable;
import com.liferay.lcs.client.internal.util.LCSPatcherUtil;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.internal.util.comparator.MessagePriorityComparator;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.client.platform.portal.LCSRESTError;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ResponseMessage;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.monitoring.PortalMonitoringControl;
import com.liferay.portal.kernel.service.CompanyLocalService;
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
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Marko Cikos
 */
@Component(
	immediate = true,
	name = "com.liferay.lcs.client.internal.task.HandshakeTask",
	service = Task.class
)
public class HandshakeTask implements Task {

	public HandshakeTask() {
	}

	public HandshakeTask(
		CompanyLocalService companyLocalService,
		LCSAlertAdvisor lcsAlertAdvisor, LCSEventManager lcsEventManager,
		long lcsClusterEntryTokenId, LCSGatewayClient lcsGatewayClient,
		LCSKeyAdvisor lcsKeyAdvisor, ThreadFactory threadFactory,
		UptimeAdvisor uptimeAdvisor) {

		_companyLocalService = companyLocalService;
		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsEventManager = lcsEventManager;
		_lcsClusterEntryTokenId = lcsClusterEntryTokenId;
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

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Activate
	public void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}

		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_heartbeatInterval = lcsConfiguration.communicationHeartbeatInterval();
		_lcsClientVersion = lcsConfiguration.lcsClientVersion();
	}

	@Override
	public void run() {
		try {
			if (_log.isInfoEnabled()) {
				_log.info("Connecting to LCS");
			}

			if (!_temporaryKey) {
				_lcsGatewayClient.deleteMessages(_key);
			}

			HandshakeMessage handshakeMessage = _createHandshakeMessage();

			if (_log.isTraceEnabled()) {
				_log.trace("Sending handshake message: " + handshakeMessage);
			}

			_lcsGatewayClient.sendMessage(handshakeMessage);

			_waitForHandshakeResponse();

			_lcsEventManager.publish(LCSEvent.HANDSHAKE_SUCCESS);

			if (_log.isInfoEnabled()) {
				_log.info("Established connection");
			}
		}
		catch (Throwable t) {
			String exceptionMessage = t.getMessage();

			LCSEvent lcsEvent = LCSEvent.HANDSHAKE_FAILED;

			LCSRESTError lcsRESTError = LCSRESTError.UNDEFINED;

			if (t instanceof LCSHandshakeException) {
				if (Validator.isNotNull(exceptionMessage)) {
					lcsRESTError = LCSRESTError.getRESTError(t.getMessage());

					if (lcsRESTError != LCSRESTError.UNDEFINED) {
						exceptionMessage = lcsRESTError.getErrorDescription();
					}
				}

				if (lcsRESTError.getErrorCode() == 200) {
					lcsEvent = LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID;
				}
				else if (lcsRESTError.getErrorCode() == 201) {
					lcsEvent =
						LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH;
				}
				else if (lcsRESTError.getErrorCode() == 202) {
					lcsEvent =
						LCSEvent.
							LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS;
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug(exceptionMessage, t);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(exceptionMessage);
			}

			_lcsEventManager.publish(lcsEvent);
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
			GetterUtil.getLong(_heartbeatInterval, 60000L));

		handshakeMessage.setKey(_key);
		handshakeMessage.setLCSClusterEntryTokenId(_lcsClusterEntryTokenId);

		handshakeMessage.setLCSClusterNodeName(
			LicenseManagerUtil.getHostName() + StringPool.DASH +
				System.currentTimeMillis());

		handshakeMessage.setLCSPortletBuildNumber(
			LCSUtil.getLCSPortletBuildNumber());
		handshakeMessage.setLCSPortletVersion(_lcsClientVersion);
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

		List<Company> companies = _companyLocalService.getCompanies();

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

	private void _submitLCSPortletBuildNumberCheck(
		int latestLCSPortletBuildNumber) {

		Runnable runnable = new LCSPortletBuildNumberCheckRunnable(
			latestLCSPortletBuildNumber, _lcsAlertAdvisor);

		Thread thread = _threadFactory.newThread(runnable);

		thread.start();
	}

	private void _waitForHandshakeResponse() throws LCSGatewayException {
		boolean handshakeResponseReceived = false;
		List<Message> delayedMessages = new ArrayList<>();
		List<Message> receivedMessages = null;

		for (int i = 0; i < 12; i++) {
			receivedMessages = _lcsGatewayClient.getMessages(_key);

			if (receivedMessages.isEmpty()) {
				try {
					TimeUnit.SECONDS.sleep(5);
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
				handshakeResponseReceived = true;

				break;
			}
		}

		if (!handshakeResponseReceived) {
			throw new LCSHandshakeException(
				"Handshake response was not received within 1 minute");
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

	@Reference
	private CompanyLocalService _companyLocalService;

	private int _heartbeatInterval;
	private String _key;

	@Reference
	private LCSAlertAdvisor _lcsAlertAdvisor;

	private String _lcsClientVersion;
	private long _lcsClusterEntryTokenId;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	private boolean _temporaryKey;

	@Reference
	private ThreadFactory _threadFactory;

	@Reference
	private UptimeAdvisor _uptimeAdvisor;

}