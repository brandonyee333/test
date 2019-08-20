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

package com.liferay.lcs.client.internal.messaging.advisor;

import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.advisor.MonitoringAdvisor;
import com.liferay.lcs.client.internal.advisor.MonitoringAdvisorFactory;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.license.messaging.LicenseManagerMessageType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = MessageBusAdvisor.class)
public class MessageBusAdvisor implements LCSEventListener {

	public MessageBusAdvisor() {
	}

	public MessageBusAdvisor(
		LCSEventManager lcsEventManager, MessageBus messageBus) {

		_lcsEventManager = lcsEventManager;
		_messageBus = messageBus;

		_subscribeToLCSEvents();
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (_log.isTraceEnabled()) {
			_log.trace("Notified on LCS event " + lcsEvent);
		}

		if (lcsEvent == LCSEvent.LCS_GATEWAY_AVAILABLE) {
			processLCSPortletState(LCSPortletState.NO_SUBSCRIPTION);
		}
		else if (lcsEvent == LCSEvent.LCS_GATEWAY_UNAVAILABLE) {
			processLCSPortletState(LCSPortletState.NO_CONNECTION);
			_unregisterAll();
		}
		else if (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS) {
			processLCSPortletState(LCSPortletState.NO_CONNECTION);
		}
	}

	public void processLCSPortletState(LCSPortletState lcsPortletState) {
		Message message = LicenseManagerMessageType.LCS_AVAILABLE.createMessage(
			lcsPortletState);

		_messageBus.sendMessage(message.getDestinationName(), message);

		if (_log.isTraceEnabled()) {
			StringBundler sb = new StringBundler(3);

			sb.append("Service availability message published for LCS ");
			sb.append("portlet state ");
			sb.append(lcsPortletState.name());

			_log.trace(sb.toString());
		}
	}

	public void registerMessageListener(Map<String, String> listenerContext) {
		ListenerDescriptor listenerDescriptor = new ListenerDescriptor(
			listenerContext.get("destinationName"),
			listenerContext.get("messageListenerName"));

		if (_listenerDescriptors.contains(listenerDescriptor)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting listener registration. The listener " +
						listenerDescriptor + " is already registered.");
			}

			return;
		}

		listenerDescriptor.listenerInstance = _getMessageListener(
			listenerDescriptor.listenerName);

		_messageBus.registerMessageListener(
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

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_subscribeToLCSEvents();

		processLCSPortletState(LCSPortletState.NOT_REGISTERED);
	}

	@Deactivate
	protected void deactivate() {
		_lcsEventManager.unsubscribe(this);
		_unregisterAll();

		if (_log.isTraceEnabled()) {
			_log.trace("Destroyed " + this);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private MessageListener _getMessageListener(String taskName) {
		try {
			ServiceReference<?>[] serviceReferences =
				_bundleContext.getServiceReferences(
					MessageListener.class.getName(),
					"(lcs.client.message.listener.name=" + taskName + ")");

			if (serviceReferences.length > 0) {
				return (MessageListener)_bundleContext.getService(
					serviceReferences[0]);
			}
		}
		catch (InvalidSyntaxException ise) {
			throw new IllegalArgumentException(ise);
		}

		return null;
	}

	private void _subscribeToLCSEvents() {
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS, this);
		_lcsEventManager.subscribe(LCSEvent.LCS_GATEWAY_UNAVAILABLE, this);
		_lcsEventManager.subscribe(LCSEvent.LCS_GATEWAY_AVAILABLE, this);
	}

	private void _unregisterAll() {
		if (_log.isDebugEnabled()) {
			_log.debug("Unregistering all message listeners");
		}

		for (ListenerDescriptor listenerDescriptor : _listenerDescriptors) {
			_messageBus.unregisterMessageListener(
				listenerDescriptor.destinationName,
				listenerDescriptor.listenerInstance);

			if (_log.isTraceEnabled()) {
				_log.trace(
					"Unregistered message listener " +
						listenerDescriptor.listenerName);
			}
		}

		_listenerDescriptors.clear();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageBusAdvisor.class);

	private BundleContext _bundleContext;

	@Reference
	private LCSEventManager _lcsEventManager;

	private final List<ListenerDescriptor> _listenerDescriptors =
		new ArrayList<>();

	@Reference
	private MessageBus _messageBus;

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