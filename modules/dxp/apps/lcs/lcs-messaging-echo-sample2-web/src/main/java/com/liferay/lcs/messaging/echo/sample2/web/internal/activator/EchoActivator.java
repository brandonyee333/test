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

package com.liferay.lcs.messaging.echo.sample2.web.internal.activator;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactoryUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Riccardo Ferrari
 */
public class EchoActivator implements BundleActivator {

	public void start(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		registerDestination("lcs_echo", false);

		registerDestination("osb_lcs_echo", true);
	}

	public void stop(BundleContext bundleContext) {
		deregisterDestinations();

		_bundleContext = null;
	}

	protected void deregisterDestinations() {
		for (ServiceRegistration<Destination> serviceRegistration :
				_serviceRegistrations.values()) {

			Destination destination = _bundleContext.getService(
				serviceRegistration.getReference());

			serviceRegistration.unregister();

			destination.destroy();
		}

		_serviceRegistrations.clear();
	}

	protected void registerDestination(
		String destinationName, boolean remoteDestination) {

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				destinationName);

		destinationConfiguration.setMaximumQueueSize(5);

		destinationConfiguration.setRejectedExecutionHandler(
			new EchoCallerRunsPolicy());

		Destination destination = DestinationFactoryUtil.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"destination.name", destinationConfiguration.getDestinationName());

		if (remoteDestination) {
			properties.put("destination.remote.osb.lcs", true);
		}

		ServiceRegistration<Destination> serviceRegistration =
			_bundleContext.registerService(
				Destination.class, destination, properties);

		_serviceRegistrations.put(destination.getName(), serviceRegistration);
	}

	private BundleContext _bundleContext;
	private final Map<String, ServiceRegistration<Destination>>
		_serviceRegistrations = new HashMap<>();

}