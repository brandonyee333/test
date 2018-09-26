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

package com.liferay.lcs.messaging.scheduler.impl;

import com.liferay.lcs.advisor.MonitoringAdvisor;
import com.liferay.lcs.advisor.MonitoringAdvisorFactory;
import com.liferay.lcs.messaging.scheduler.MessageListenerSchedulerService;
import com.liferay.lcs.platform.LCSEvent;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.platform.gateway.LCSGatewayStateListener;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class MessageListenerSchedulerServiceImpl
	implements LCSGatewayStateListener, MessageListenerSchedulerService {

	public MessageListenerSchedulerServiceImpl(
		LCSGatewayClient lcsGatewayClient) {

		lcsGatewayClient.registerLCSGatewayStateListener(this);
	}

	@Override
	public void onLCSGatewayStateChanged(LCSEvent lcsEvent) {
		if (lcsEvent == LCSEvent.UNAVAILABLE) {
			unscheduleAllMessageListeners();
		}
	}

	@Override
	public void scheduleMessageListener(Map<String, String> schedulerContext) {
		String destinationName = schedulerContext.get("destinationName");
		String messageListenerName = schedulerContext.get(
			"messageListenerName");

		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		MessageListener messageListener = (MessageListener)beanLocator.locate(
			messageListenerName);

		MessageBusUtil.registerMessageListener(
			destinationName, messageListener);

		_messageListenerNamesDestinationNames.put(
			messageListenerName, destinationName);

		if (_log.isDebugEnabled()) {
			_log.debug("Scheduled message listener " + messageListenerName);
		}

		MonitoringAdvisor monitoringAdvisor =
			MonitoringAdvisorFactory.getInstance(messageListener.getClass());

		if (monitoringAdvisor != null) {
			monitoringAdvisor.activateMonitoring();
		}
	}

	@Override
	public void unscheduleAllMessageListeners() {
		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		if (beanLocator == null) {
			return;
		}

		for (Map.Entry<String, String> entry :
				_messageListenerNamesDestinationNames.entrySet()) {

			String messageListenerName = entry.getKey();

			MessageBusUtil.unregisterMessageListener(
				entry.getValue(),
				(MessageListener)beanLocator.locate(messageListenerName));

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unscheduled message listener " + messageListenerName);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageListenerSchedulerServiceImpl.class);

	private final Map<String, String> _messageListenerNamesDestinationNames =
		new HashMap<>();

}