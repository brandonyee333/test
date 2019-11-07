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

package com.liferay.fabrica.core.internal.topology;

import com.liferay.fabrica.core.discovery.Announcement;
import com.liferay.fabrica.core.discovery.Announcer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ExportReference;
import org.osgi.service.remoteserviceadmin.ExportRegistration;
import org.osgi.service.remoteserviceadmin.RemoteConstants;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdmin;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminEvent;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

/**
 * @author Miguel Pastor
 */
@Component(immediate = true)
public class TopologyManager implements RemoteServiceAdminListener {

	@Override
	public void remoteAdminEvent(
		RemoteServiceAdminEvent remoteServiceAdminEvent) {

		ExportReference exportReference =
			remoteServiceAdminEvent.getExportReference();

		if (remoteServiceAdminEvent.getType() ==
				RemoteServiceAdminEvent.EXPORT_REGISTRATION) {

			EndpointDescription endpointDescription =
				exportReference.getExportedEndpoint();

			CompletableFuture<Announcement> completableFuture =
				_announcer.announce(endpointDescription.getProperties());

			completableFuture.thenApply(
				announcement -> _announcements.put(
					exportReference, announcement));
		}
		else if (remoteServiceAdminEvent.getType() ==
					RemoteServiceAdminEvent.EXPORT_UNREGISTRATION) {

			Announcement announcement = _announcements.get(exportReference);

			if (announcement != null) {
				announcement.unannounce();
			}
		}
		else {
			throw new IllegalArgumentException(
				"Unknown event type" + remoteServiceAdminEvent.getType());
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(" + RemoteConstants.SERVICE_EXPORTED_INTERFACES + "=*)",
		unbind = "_removeService"
	)
	private void _exportService(ServiceReference<?> serviceReference) {
		Collection<ExportRegistration> exportRegistrations =
			_remoteServiceAdmin.exportService(serviceReference, null);

		_exportedServices.putIfAbsent(serviceReference, exportRegistrations);
	}

	private void _removeService(ServiceReference<?> serviceReference) {
		Collection<ExportRegistration> exportRegistrations =
			_exportedServices.remove(serviceReference);

		for (ExportRegistration exportRegistration : exportRegistrations) {
			exportRegistration.close();
		}
	}

	private final Map<ExportReference, Announcement> _announcements =
		new ConcurrentHashMap<>();

	@Reference
	private Announcer _announcer;

	private final
		ConcurrentMap<ServiceReference<?>, Collection<ExportRegistration>>
			_exportedServices = new ConcurrentHashMap<>();

	@Reference
	private RemoteServiceAdmin _remoteServiceAdmin;

}