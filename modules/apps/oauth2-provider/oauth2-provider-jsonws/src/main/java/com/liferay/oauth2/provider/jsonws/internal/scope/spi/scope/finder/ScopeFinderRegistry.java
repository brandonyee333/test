/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.oauth2.provider.jsonws.internal.scope.spi.scope.finder;

import com.liferay.oauth2.provider.jsonws.internal.service.access.policy.scope.SAPEntryScopeDescriptorFinderRegistrator;
import com.liferay.oauth2.provider.scope.spi.scope.finder.ScopeFinder;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Jiaxu Wei
 */
@Component(service = ScopeFinderRegistry.class)
public class ScopeFinderRegistry {

	public Set<String> getJaxRsApplicationNames() {
		return _jaxRsApplicationNames;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTracker = ServiceTrackerFactory.open(
			bundleContext,
			"(&(osgi.jaxrs.name=*)(sap.scope.finder=true)(objectClass=" +
				ScopeFinder.class.getName() + "))",
			new ServiceTrackerCustomizer<ScopeFinder, ScopeFinder>() {

				@Override
				public ScopeFinder addingService(
					ServiceReference<ScopeFinder> serviceReference) {

					_jaxRsApplicationNames.add(
						GetterUtil.getString(
							serviceReference.getProperty("osgi.jaxrs.name")));

					_sapEntryScopeDescriptorFinderRegistrator.
						pushJaxRsApplicationNames(_jaxRsApplicationNames);

					return bundleContext.getService(serviceReference);
				}

				@Override
				public void modifiedService(
					ServiceReference<ScopeFinder> serviceReference,
					ScopeFinder scopeFinder) {
				}

				@Override
				public void removedService(
					ServiceReference<ScopeFinder> serviceReference,
					ScopeFinder scopeFinder) {

					bundleContext.ungetService(serviceReference);

					_jaxRsApplicationNames.remove(
						GetterUtil.getString(
							serviceReference.getProperty("osgi.jaxrs.name")));

					_sapEntryScopeDescriptorFinderRegistrator.
						pushJaxRsApplicationNames(_jaxRsApplicationNames);
				}

			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTracker.close();
	}

	private final Set<String> _jaxRsApplicationNames =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

	@Reference
	private SAPEntryScopeDescriptorFinderRegistrator
		_sapEntryScopeDescriptorFinderRegistrator;

	private ServiceTracker<ScopeFinder, ScopeFinder> _serviceTracker;

}