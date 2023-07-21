/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.hot.deploy.jmx.listener;

import com.liferay.hot.deploy.jmx.listener.statistics.PluginStatisticsManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.GetterUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(immediate = true, service = MessageListener.class)
public class HotDeployMessageListener extends BaseMessageListener {

	@Activate
	protected void activate() {
		_messageBus.registerMessageListener(DestinationNames.HOT_DEPLOY, this);
	}

	@Deactivate
	protected void deactivate() {
		_messageBus.unregisterMessageListener(
			DestinationNames.HOT_DEPLOY, this);
	}

	@Override
	protected void doReceive(Message message) {
		String command = GetterUtil.getString(message.getString("command"));
		String servletContextName = message.getString("servletContextName");

		if (command.equals("deploy")) {
			if (_pluginStatisticsManager.registerLegacyPlugin(
					servletContextName)) {

				if (_log.isInfoEnabled()) {
					_log.info("Deployed " + servletContextName);
				}
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(
					"Not deploying " + servletContextName +
						" because it is already deployed");
			}
		}
		else if (command.equals("undeploy")) {
			if (_pluginStatisticsManager.unregisterLegacyPlugin(
					servletContextName)) {

				if (_log.isInfoEnabled()) {
					_log.info("Undeployed " + servletContextName);
				}
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(
					"Not undeploying " + servletContextName +
						" because it was not deployed");
			}
		}
		else if (_log.isWarnEnabled()) {
			_log.warn("Unknown command " + command);
		}
	}

	@Reference(unbind = "-")
	protected void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	@Reference(unbind = "-")
	protected void setPluginStatisticsManager(
		PluginStatisticsManager pluginStatisticsManager) {

		_pluginStatisticsManager = pluginStatisticsManager;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HotDeployMessageListener.class);

	private MessageBus _messageBus;
	private PluginStatisticsManager _pluginStatisticsManager;

}