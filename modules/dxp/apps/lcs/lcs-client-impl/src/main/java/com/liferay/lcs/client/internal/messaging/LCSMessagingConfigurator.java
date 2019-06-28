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

package com.liferay.lcs.client.internal.messaging;

import com.liferay.lcs.client.internal.constants.LCSDestinationNames;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSMessagingConfigurator.class)
public class LCSMessagingConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceRegistrations.put(
			LCSDestinationNames.LCS_REQUEST,
			_registerDestination(
				bundleContext, LCSDestinationNames.LCS_REQUEST));

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		for (ServiceRegistration<Destination> serviceRegistration :
				_serviceRegistrations.values()) {

			serviceRegistration.unregister();
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Deactivated " + this);
		}
	}

	private ServiceRegistration<Destination> _registerDestination(
		BundleContext bundleContext, String destinationName) {

		DestinationConfiguration destinationConfiguration =
			DestinationConfiguration.createParallelDestinationConfiguration(
				destinationName);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> dictionary = new HashMapDictionary<>();

		dictionary.put("destination.name", destination.getName());

		if (_log.isTraceEnabled()) {
			Bundle bundle = bundleContext.getBundle();

			_log.trace(
				String.format(
					_DESTINATION_REGISTERED_LOG_PATTERN,
					bundle.getSymbolicName(), destinationName));
		}

		return bundleContext.registerService(
			Destination.class, destination, dictionary);
	}

	private static final String _DESTINATION_REGISTERED_LOG_PATTERN =
		"Bundle context %s registered destination %s";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSMessagingConfigurator.class);

	@Reference
	private DestinationFactory _destinationFactory;

	private final Map<String, ServiceRegistration<Destination>>
		_serviceRegistrations = new HashMap<>();

}