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

package com.liferay.lcs.command;

import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesResponseMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.lcs.task.advisor.TaskAdvisor;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.kernel.util.StringBundler;
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
public class SendPortalPropertiesCommand
	implements Command<SendPortalPropertiesCommandMessage> {

	public SendPortalPropertiesCommand(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSGatewayService lcsGatewayService, TaskAdvisor taskAdvisor) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsGatewayService = lcsGatewayService;
		_taskAdvisor = taskAdvisor;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void execute(
		SendPortalPropertiesCommandMessage sendPortalPropertiesCommandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Executing send portal properties command");
		}

		_taskAdvisor.registerActivity(this);

		Properties portalProperties = getSecurityInsensitivePropertiesKeys();

		String installedHashCode = _getHashCode(portalProperties);

		if (installedHashCode.equals(
				sendPortalPropertiesCommandMessage.getHashCode())) {

			return;
		}

		Map<String, String> portalPropertiesMap = new TreeMap(portalProperties);

		SendPortalPropertiesResponseMessage
			sendPortalPropertiesResponseMessage =
				_getSendPortalPropertiesResponseMessage(
					sendPortalPropertiesCommandMessage, installedHashCode,
					portalPropertiesMap);

		try {
			_lcsGatewayService.sendMessage(sendPortalPropertiesResponseMessage);
		}
		catch (Exception e) {
			_log.error("Unable to send portal properties", e);
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
			SendPortalPropertiesCommandMessage
				sendPortalPropertiesCommandMessage,
			String hashCode, Map<String, String> portalProperties) {

		SendPortalPropertiesResponseMessage
			sendPortalPropertiesResponseMessage =
				new SendPortalPropertiesResponseMessage();

		sendPortalPropertiesResponseMessage.setCreateTime(
			System.currentTimeMillis());
		sendPortalPropertiesResponseMessage.setHashCode(hashCode);
		sendPortalPropertiesResponseMessage.setKey(
			sendPortalPropertiesCommandMessage.getKey());
		sendPortalPropertiesResponseMessage.setPortalProperties(
			portalProperties);

		return sendPortalPropertiesResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendPortalPropertiesCommand.class);

	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final LCSGatewayService _lcsGatewayService;
	private final TaskAdvisor _taskAdvisor;

}