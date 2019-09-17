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

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.alert.LCSAlert;
import com.liferay.lcs.client.alert.advisor.LCSAlertAdvisor;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.internal.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.advisor.UptimeAdvisor;
import com.liferay.lcs.client.internal.command.queue.CommandQueue;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.exception.LCSHandshakeException;
import com.liferay.lcs.client.internal.util.LCSPatcherUtil;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.client.platform.portal.LCSRESTError;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.HandshakeResponseMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ResponseMessage;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.monitoring.PortalMonitoringControl;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.servlet.LiferayFilter;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor,
		ThreadFactory threadFactory, UptimeAdvisor uptimeAdvisor) {

		_companyLocalService = companyLocalService;
		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsEventManager = lcsEventManager;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_threadFactory = threadFactory;
		_uptimeAdvisor = uptimeAdvisor;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Activate
	public void activate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Override
	public void run() {
		if (_log.isTraceEnabled()) {
			_log.trace(
				String.format(
					_EXECUTION_TRACE_MESSAGE_PATTERN, this, _getKey()));
		}

		try {
			if (_log.isInfoEnabled()) {
				_log.info("Connecting to LCS");
			}

			_lcsGatewayClient.deleteMessages(_getKey());

			HandshakeMessage handshakeMessage = _createHandshakeMessage();

			if (_log.isTraceEnabled()) {
				_log.trace("Sending handshake message: " + handshakeMessage);
			}

			_lcsGatewayClient.sendMessage(false, handshakeMessage);

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

	protected boolean processResponse(List<Message> receivedMessages) {
		boolean receivedHandshakeResponse = false;

		for (Message receivedMessage : receivedMessages) {
			if (!(receivedMessage instanceof ResponseMessage)) {
				if (_log.isTraceEnabled()) {
					_log.trace("Unable to process message: " + receivedMessage);
				}

				_commandQueue.add(receivedMessage);

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

			if (Validator.isNotNull(handshakeResponseMessage.getNewKey())) {
				_lcsKeyAdvisor.updateKey(handshakeResponseMessage.getNewKey());
			}

			_checkLCSClientBuildNumber(
				handshakeResponseMessage.getLatestLCSPortletBuildNumber());
		}

		return receivedHandshakeResponse;
	}

	private void _checkLCSClientBuildNumber(int latestLCSClientBuildNumber) {
		if (latestLCSClientBuildNumber >
				LCSClientConstants.LCS_CLIENT_BUILD_NUMBER) {

			_lcsAlertAdvisor.add(
				LCSAlert.WARNING_LCS_PORTLET_NEW_VERSION_AVAILABLE);
		}
		else {
			_lcsAlertAdvisor.remove(
				LCSAlert.WARNING_LCS_PORTLET_NEW_VERSION_AVAILABLE);
		}
	}

	private HandshakeMessage _createHandshakeMessage() {
		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.setClusterExecutorEnabled(
			_clusterExecutor.isEnabled());
		handshakeMessage.setCompanyIdsWebIds(_getCompanyIdsWebIds());

		String key = _getKey();

		handshakeMessage.setHashCode(key.hashCode());

		handshakeMessage.setHeartbeatInterval(
			LCSClientConstants.HEARTBEAT_INTERVAL);

		handshakeMessage.setKey(key);

		handshakeMessage.setLCSClusterEntryTokenId(
			_lcsClusterEntryTokenAdvisor.getLcsClusterEntryTokenId());

		handshakeMessage.setLCSClusterNodeName(
			LicenseManagerUtil.getHostName() + StringPool.DASH +
				System.currentTimeMillis());

		handshakeMessage.setLCSPortletBuildNumber(
			LCSClientConstants.LCS_CLIENT_BUILD_NUMBER);
		handshakeMessage.setLCSPortletVersion(
			LCSClientConstants.LCS_CLIENT_VERSION);

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

	private Map<Integer, String> _getCompanyIdsWebIds() {
		Map<Integer, String> companyIdsWebIds = new HashMap<>();

		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			companyIdsWebIds.put(
				(int)company.getCompanyId(), company.getWebId());
		}

		return companyIdsWebIds;
	}

	private String _getKey() {
		String key = _lcsKeyAdvisor.getKey();

		if (key != null) {
			return key;
		}

		return _lcsKeyAdvisor.getTemporaryKey();
	}

	private List<Map<String, Long>> _getPortalUptimeEntries() {
		List<Map<String, Long>> uptimeEntries =
			_uptimeAdvisor.getUptimeEntries();

		_uptimeAdvisor.resetCurrentUptimeEndTime(uptimeEntries);

		return uptimeEntries;
	}

	private int _getProcessorCoresTotal() {
		return _installationEnvironmentAdvisor.getProcessorCoresTotal();
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

	private void _waitForHandshakeResponse() throws LCSGatewayException {
		boolean handshakeResponseReceived = false;
		List<Message> receivedMessages = null;

		for (int i = 0; i < 12; i++) {
			receivedMessages = _lcsGatewayClient.getMessages(false, _getKey());

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
			}

			if (processResponse(receivedMessages)) {
				handshakeResponseReceived = true;

				break;
			}
		}

		if (!handshakeResponseReceived) {
			throw new LCSHandshakeException(
				"Handshake response was not received within 1 minute");
		}
	}

	private static final String _EXECUTION_TRACE_MESSAGE_PATTERN =
		"Executing %s with registration key %s";

	private static final Log _log = LogFactoryUtil.getLog(HandshakeTask.class);

	@Reference
	private ClusterExecutor _clusterExecutor;

	@Reference
	private CommandQueue _commandQueue;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private InstallationEnvironmentAdvisor _installationEnvironmentAdvisor;

	@Reference
	private LCSAlertAdvisor _lcsAlertAdvisor;

	@Reference
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private ThreadFactory _threadFactory;

	@Reference
	private UptimeAdvisor _uptimeAdvisor;

}