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

package com.liferay.lcs.messaging.osgi.internal;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = DestinationResolver.class)
public class DestinationResolverImpl implements DestinationResolver {

	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Deactivate
	public void deactivate() {
		for (ServiceRegistration<Destination> serviceRegistration :
				_destinationNamesServiceRegistrations.values()) {

			Destination destination = _bundleContext.getService(
				serviceRegistration.getReference());

			serviceRegistration.unregister();

			destination.destroy();
		}

		_destinationNamesServiceRegistrations.clear();
	}

	@Override
	public void resolveLocalDestination(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);

		if (destination != null) {
			return;
		}

		_registerDestination(destinationName, false);
	}

	@Override
	public void resolveRemoteDestination(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);

		if (destination != null) {
			return;
		}

		_registerDestination(destinationName, true);
	}

	private void _registerDestination(String destinationName, boolean remote) {
		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				destinationName);

		destinationConfiguration.setMaximumQueueSize(5);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"destination.name", destinationConfiguration.getDestinationName());

		if (remote) {
			properties.put(RemoteDestinationType.OSB_LCS, true);
		}

		destinationConfiguration.setRejectedExecutionHandler(
			new LCSCallerRunsPolicy());

		Destination destination = DestinationFactoryUtil.createDestination(
			destinationConfiguration);

		ServiceRegistration<Destination> serviceRegistration =
			_bundleContext.registerService(
				Destination.class, destination, properties);

		_destinationNamesServiceRegistrations.put(
			destination.getName(), serviceRegistration);
	}

	private BundleContext _bundleContext;
	private final Map<String, ServiceRegistration<Destination>>
		_destinationNamesServiceRegistrations = new HashMap<>();

	@Reference
	private MessageBus _messageBus;

}