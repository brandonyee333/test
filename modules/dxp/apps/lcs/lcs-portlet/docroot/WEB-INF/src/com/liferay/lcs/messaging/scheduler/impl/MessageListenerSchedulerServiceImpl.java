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
import com.liferay.lcs.platform.LCSEvent;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.platform.gateway.LCSGatewayStateListener;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class MessageListenerSchedulerServiceImpl
	implements LCSGatewayStateListener {

	public MessageListenerSchedulerServiceImpl(
		LCSGatewayClient lcsGatewayClient) {

		lcsGatewayClient.registerLCSGatewayStateListener(this);
	}

	public void destroy() {
		_unregisterAll();

		if (_log.isTraceEnabled()) {
			_log.trace("Destroyed " + this);
		}
	}

	@Override
	public void onLCSGatewayStateChanged(LCSEvent lcsEvent) {
		if (_log.isTraceEnabled()) {
			_log.trace("Notified on LCS event " + lcsEvent);
		}

		if (lcsEvent == LCSEvent.UNAVAILABLE) {
			_unregisterAll();
		}
	}

	public void scheduleMessageListener(Map<String, String> listenerContext) {
		ListenerDescriptor listenerDescriptor = new ListenerDescriptor(
			listenerContext.get("destinationName"),
			listenerContext.get("messageListenerName"));

		if (_listenerDescriptors.contains(listenerDescriptor)) {
			if (_log.isInfoEnabled()) {
				_log.info("Already registered " + listenerDescriptor);
			}

			return;
		}

		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		listenerDescriptor.listenerInstance =
			(MessageListener)beanLocator.locate(
				listenerDescriptor.listenerName);

		MessageBusUtil.registerMessageListener(
			listenerDescriptor.destinationName,
			listenerDescriptor.listenerInstance);

		_listenerDescriptors.add(listenerDescriptor);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Registered message listener " +
					listenerDescriptor.listenerName);
		}

		MessageListener messageListener = listenerDescriptor.listenerInstance;

		MonitoringAdvisor monitoringAdvisor =
			MonitoringAdvisorFactory.getInstance(messageListener.getClass());

		if (monitoringAdvisor != null) {
			monitoringAdvisor.activateMonitoring();
		}
	}

	private void _unregisterAll() {
		if (_log.isTraceEnabled()) {
			_log.trace("Unregister all message listeners");
		}

		for (ListenerDescriptor listenerDescriptor : _listenerDescriptors) {
			MessageBusUtil.unregisterMessageListener(
				listenerDescriptor.destinationName,
				listenerDescriptor.listenerInstance);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unregistered message listener " +
						listenerDescriptor.listenerName);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageListenerSchedulerServiceImpl.class);

	private final List<ListenerDescriptor> _listenerDescriptors =
		new ArrayList<>();

	private class ListenerDescriptor {

		public ListenerDescriptor(String destinationName, String listenerName) {
			this.destinationName = destinationName;
			this.listenerName = listenerName;
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof ListenerDescriptor) {
				ListenerDescriptor otherListenerDescriptor =
					(ListenerDescriptor)object;

				if (destinationName.equals(
						otherListenerDescriptor.destinationName) &&
					listenerName.equals(otherListenerDescriptor.listenerName)) {

					return true;
				}
			}

			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(destinationName, listenerName);
		}

		@Override
		public String toString() {
			StringBundler sb = new StringBundler(7);

			sb.append("ListenerDescriptor{destinationName='");
			sb.append(destinationName);
			sb.append("', listenerInstance=");
			sb.append(listenerInstance);
			sb.append(", listenerName='");
			sb.append(listenerName);
			sb.append("'}");

			return sb.toString();
		}

		protected String destinationName;
		protected MessageListener listenerInstance;
		protected String listenerName;

	}

}