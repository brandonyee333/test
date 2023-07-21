/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesResponseMessage;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SendPortalPropertiesTask extends BaseTask {

	public SendPortalPropertiesTask(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSGatewayClient lcsGatewayClient,
		SendPortalPropertiesCommandMessage sendPortalPropertiesCommandMessage) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsGatewayClient = lcsGatewayClient;
		_sendPortalPropertiesCommandMessage =
			sendPortalPropertiesCommandMessage;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void doRun() throws CompressionException, LCSGatewayException {
		if (_log.isTraceEnabled()) {
			_log.trace("Executing send portal properties command");
		}

		Properties portalProperties = getSecurityInsensitivePropertiesKeys();

		String installedHashCode = _getHashCode(portalProperties);

		if (installedHashCode.equals(
				_sendPortalPropertiesCommandMessage.getHashCode())) {

			return;
		}

		Map<String, String> portalPropertiesMap = new TreeMap(portalProperties);

		SendPortalPropertiesResponseMessage
			sendPortalPropertiesResponseMessage =
				_getSendPortalPropertiesResponseMessage(
					_sendPortalPropertiesCommandMessage.getKey(),
					installedHashCode, portalPropertiesMap);

		_lcsGatewayClient.sendMessage(sendPortalPropertiesResponseMessage);
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.COMMAND;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	protected Properties getSecurityInsensitivePropertiesKeys() {
		String portalPropertiesBlacklist =
			_lcsClusterEntryTokenAdvisor.getPortalPropertiesBlacklist();

		Properties portalProperties = new SortedProperties(
			PropsUtil.getProperties());

		Set<Object> portalPropertiesKeys = portalProperties.keySet();

		Iterator<Object> iterator = portalPropertiesKeys.iterator();

		while (iterator.hasNext()) {
			String portalPropertiesKey = (String)iterator.next();

			if (portalPropertiesKey.endsWith(".password")) {
				if (isSecurityInsensitive(portalPropertiesKey)) {
					continue;
				}

				iterator.remove();

				continue;
			}

			for (String portalPropertiesSecuritySensitiveKey :
					LCSConstants.PORTAL_PROPERTIES_SECURITY_SENSITIVE) {

				if (portalPropertiesKey.startsWith(
						portalPropertiesSecuritySensitiveKey)) {

					iterator.remove();

					break;
				}
			}

			if (StringUtil.contains(
					portalPropertiesBlacklist, portalPropertiesKey)) {

				iterator.remove();
			}
		}

		return portalProperties;
	}

	protected boolean isSecurityInsensitive(String portalPropertiesKey) {
		for (String portalPropertiesSecurityInsensitiveKey :
				LCSConstants.PORTAL_PROPERTIES_SECURITY_INSENSITIVE) {

			if (portalPropertiesKey.startsWith(
					portalPropertiesSecurityInsensitiveKey)) {

				return true;
			}
		}

		return false;
	}

	private String _getHashCode(Properties properties) {
		StringBundler sb = new StringBundler(properties.size());

		for (Object key : properties.keySet()) {
			sb.append(
				DigesterUtil.digestHex(
					Digester.MD5, (String)properties.get(key)));
		}

		return DigesterUtil.digestHex(Digester.MD5, sb.toString());
	}

	private SendPortalPropertiesResponseMessage
		_getSendPortalPropertiesResponseMessage(
			String key, String hashCode, Map<String, String> portalProperties) {

		SendPortalPropertiesResponseMessage
			sendPortalPropertiesResponseMessage =
				new SendPortalPropertiesResponseMessage();

		sendPortalPropertiesResponseMessage.setCreateTime(
			System.currentTimeMillis());
		sendPortalPropertiesResponseMessage.setHashCode(hashCode);
		sendPortalPropertiesResponseMessage.setKey(key);
		sendPortalPropertiesResponseMessage.setPortalProperties(
			portalProperties);

		return sendPortalPropertiesResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendPortalPropertiesTask.class);

	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final LCSGatewayClient _lcsGatewayClient;
	private final SendPortalPropertiesCommandMessage
		_sendPortalPropertiesCommandMessage;

}