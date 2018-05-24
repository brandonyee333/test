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

package com.liferay.fabrica.core.internal.admin;

import com.liferay.fabrica.core.admin.ExtendedRemoteServiceAdmin;
import com.liferay.fabrica.core.distribution.DistributionProvider;
import com.liferay.fabrica.core.distribution.client.Client;
import com.liferay.fabrica.core.distribution.server.Server;
import com.liferay.fabrica.core.internal.exports.ExportReferenceImpl;
import com.liferay.fabrica.core.internal.exports.ExportRegistrationImpl;
import com.liferay.fabrica.core.internal.imports.ImportReferenceImpl;
import com.liferay.fabrica.core.internal.imports.ImportRegistrationImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ExportReference;
import org.osgi.service.remoteserviceadmin.ExportRegistration;
import org.osgi.service.remoteserviceadmin.ImportReference;
import org.osgi.service.remoteserviceadmin.ImportRegistration;
import org.osgi.service.remoteserviceadmin.RemoteConstants;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdmin;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminEvent;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

/**
 * @author Miguel Pastor
 */
@Component(
	immediate = true,
	service = {ExtendedRemoteServiceAdmin.class, RemoteServiceAdmin.class}
)
public class RemoteServiceAdminImpl implements ExtendedRemoteServiceAdmin {

	@Override
	public Collection<ExportRegistration> exportService(
		ServiceReference<?> serviceReference, Map<String, ?> properties) {

		Collection<ExportRegistration> exportRegistrations = new ArrayList<>();

		Bundle bundle = serviceReference.getBundle();

		BundleContext bundleContext = bundle.getBundleContext();

		String classNames = (String)properties.get(
			RemoteConstants.SERVICE_EXPORTED_INTERFACES);

		Class<?>[] classes = _getClasses(
			bundle, Arrays.asList(classNames.split(",")));

		Collection<Server> servers = _distributionProvider.registerServer(
			bundleContext.getService(serviceReference), classes, properties);

		for (Server server : servers) {
			ExportReference exportReference = _buildExportReference(
				serviceReference, server);

			_exportReferences.put(exportReference, server);

			exportRegistrations.add(
				new ExportRegistrationImpl(exportReference, this));

			_notifyRemoteServiceAdminListeners(
				new RemoteServiceAdminEvent(
					RemoteServiceAdminEvent.EXPORT_REGISTRATION, bundle,
					exportReference, null));
		}

		return exportRegistrations;
	}

	@Override
	public Collection<ExportReference> getExportedServices() {
		return _exportReferences.keySet();
	}

	@Override
	public Collection<ImportReference> getImportedEndpoints() {
		return _importReferences.keySet();
	}

	@Override
	public ImportRegistration importService(
		EndpointDescription endpointDescription) {

		List<String> classNames = endpointDescription.getInterfaces();

		ServiceRegistration<?> serviceRegistration =
			_bundleContext.registerService(
				classNames.toArray(new String[classNames.size()]),
				new ClientProxyServiceFactory(
					_distributionProvider, classNames, null),
				null);

		ServiceReference<?> serviceReference =
			serviceRegistration.getReference();

		ImportReference importReference = new ImportReferenceImpl(
			endpointDescription, serviceReference);

		_importReferences.put(importReference, serviceRegistration);

		_notifyRemoteServiceAdminListeners(
			new RemoteServiceAdminEvent(
				RemoteServiceAdminEvent.IMPORT_REGISTRATION,
				serviceReference.getBundle(), importReference, null));

		return new ImportRegistrationImpl(importReference, this);
	}

	@Override
	public void removeExportedService(ExportReference exportReference) {
		Server server = _exportReferences.remove(exportReference);

		if (server != null) {
			try {
				server.stop();
			}
			catch (InterruptedException ie) {
				throw new RuntimeException(ie);
			}
		}

		ServiceReference<?> serviceReference =
			exportReference.getExportedService();

		Bundle bundle = serviceReference.getBundle();

		BundleContext bundleContext = bundle.getBundleContext();

		bundleContext.ungetService(serviceReference);

		_notifyRemoteServiceAdminListeners(
			new RemoteServiceAdminEvent(
				RemoteServiceAdminEvent.EXPORT_UNREGISTRATION, bundle,
				exportReference, null));
	}

	@Override
	public void removeImportedService(ImportReference importReference) {
		ServiceReference<?> serviceReference =
			importReference.getImportedService();

		Bundle bundle = serviceReference.getBundle();

		BundleContext bundleContext = bundle.getBundleContext();

		Client client = (Client)(bundleContext.getService(serviceReference));

		try {
			client.shutdown();
		}
		catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
		finally {
			bundleContext.ungetService(serviceReference);
		}

		ServiceRegistration<?> serviceRegistration = _importReferences.remove(
			importReference);

		serviceRegistration.unregister();

		_notifyRemoteServiceAdminListeners(
			new RemoteServiceAdminEvent(
				RemoteServiceAdminEvent.IMPORT_UNREGISTRATION, bundle,
				importReference, null));
	}

	private static Class<?>[] _getClasses(
		Bundle bundle, List<String> classNames) {

		List<Class<?>> classes = new ArrayList<>();

		for (String className : classNames) {
			try {
				classes.add(bundle.loadClass(className));
			}
			catch (ClassNotFoundException cnfe) {
			}
		}

		return classes.toArray(new Class<?>[classes.size()]);
	}

	@Activate
	private void _activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private ExportReference _buildExportReference(
		ServiceReference<?> serviceReference, Server server) {

		Map<String, Object> properties = new HashMap<>();

		properties.putAll(server.getInfo());

		for (String key : serviceReference.getPropertyKeys()) {
			properties.put(key, serviceReference.getProperty(key));
		}

		properties.put(RemoteConstants.ENDPOINT_ID, UUID.randomUUID());

		Bundle bundle = serviceReference.getBundle();

		BundleContext bundleContext = bundle.getBundleContext();

		String frameworkUUID = bundleContext.getProperty(
			"org.osgi.framework.uuid");

		properties.put(RemoteConstants.ENDPOINT_FRAMEWORK_UUID, frameworkUUID);

		properties.put(RemoteConstants.ENDPOINT_SERVICE_ID, _getServiceId());

		EndpointDescription endpointDescription = new EndpointDescription(
			properties);

		return new ExportReferenceImpl(endpointDescription, serviceReference);
	}

	private long _getServiceId() {
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

		return threadLocalRandom.nextLong();
	}

	private void _notifyRemoteServiceAdminListeners(
		RemoteServiceAdminEvent remoteServiceAdminEvent) {

		for (RemoteServiceAdminListener remoteRemoteServiceAdminListener :
				_remoteRemoteServiceAdminListeners) {

			remoteRemoteServiceAdminListener.remoteAdminEvent(
				remoteServiceAdminEvent);
		}
	}

	private BundleContext _bundleContext;

	@Reference
	private DistributionProvider _distributionProvider;

	private final Map<ExportReference, Server> _exportReferences =
		new ConcurrentHashMap<>();
	private final Map<ImportReference, ServiceRegistration<?>>
		_importReferences = new ConcurrentHashMap<>();

	@Reference(policyOption = ReferencePolicyOption.GREEDY)
	private volatile List<RemoteServiceAdminListener>
		_remoteRemoteServiceAdminListeners = new CopyOnWriteArrayList<>();

	private static class ClientProxyServiceFactory implements ServiceFactory {

		public ClientProxyServiceFactory(
			DistributionProvider disDistributionProvider,
			List<String> classNames, Map<String, ?> properties) {

			_disDistributionProvider = disDistributionProvider;
			_classNames = classNames;
			_properties = properties;
		}

		@Override
		public Object getService(
			Bundle bundle, ServiceRegistration serviceRegistration) {

			Class<?>[] classes = _getClasses(bundle, _classNames);

			return _disDistributionProvider.registerClient(
				classes, _properties);
		}

		@Override
		public void ungetService(
			Bundle bundle, ServiceRegistration serviceRegistration,
			Object service) {
		}

		private final List<String> _classNames;
		private final DistributionProvider _disDistributionProvider;
		private final Map<String, ?> _properties;

	}

}