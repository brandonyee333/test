/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.osgi.framework;

import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.InputStream;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Igor Beslic
 */
public class MockBundleContext<S> implements BundleContext {

	@Override
	public void addBundleListener(BundleListener bundleListener) {
	}

	@Override
	public void addFrameworkListener(FrameworkListener frameworkListener) {
	}

	@Override
	public void addServiceListener(ServiceListener serviceListener) {
	}

	@Override
	public void addServiceListener(
			ServiceListener serviceListener, String filterString)
		throws InvalidSyntaxException {
	}

	@Override
	public Filter createFilter(String filterString)
		throws InvalidSyntaxException {

		return null;
	}

	@Override
	public ServiceReference<?>[] getAllServiceReferences(
			String clazz, String filterString)
		throws InvalidSyntaxException {

		return new ServiceReference[0];
	}

	@Override
	public Bundle getBundle() {
		return null;
	}

	@Override
	public Bundle getBundle(long id) {
		return null;
	}

	@Override
	public Bundle getBundle(String location) {
		return null;
	}

	@Override
	public Bundle[] getBundles() {
		return new Bundle[0];
	}

	@Override
	public File getDataFile(String filename) {
		return null;
	}

	@Override
	public String getProperty(String key) {
		return null;
	}

	@Override
	public <S> S getService(ServiceReference<S> reference) {
		return (S)_serviceReferenceServices.get(reference);
	}

	@Override
	public <S> ServiceObjects<S> getServiceObjects(
		ServiceReference<S> reference) {

		return null;
	}

	@Override
	public <S> ServiceReference<S> getServiceReference(Class<S> clazz) {
		return null;
	}

	@Override
	public ServiceReference<?> getServiceReference(String clazz) {
		return null;
	}

	@Override
	public <S> Collection<ServiceReference<S>> getServiceReferences(
			Class<S> clazz, String filterString)
		throws InvalidSyntaxException {

		return null;
	}

	@Override
	public ServiceReference<?>[] getServiceReferences(
			String clazz, String filterString)
		throws InvalidSyntaxException {

		List<ServiceReference<?>> filteredServiceReferences =
			new CopyOnWriteArrayList<>();

		_serviceReferenceServices.forEach(
			(serviceReference, service) -> {
				if (Validator.isNotNull(filterString)) {
					Matcher matcher = _propertyPattern.matcher(filterString);

					if (!matcher.matches()) {
						return;
					}

					String propertyValue = matcher.group(2);
					String propertyKey = matcher.group(1);

					if (!propertyValue.equals(
							serviceReference.getProperty(propertyKey))) {

						return;
					}
				}

				if (serviceReference.compareTo(clazz) == 0) {
					filteredServiceReferences.add(serviceReference);
				}
			});

		ServiceReference<?>[] serviceReferences =
			new ServiceReference[filteredServiceReferences.size()];

		for (int i = 0; i < serviceReferences.length; i++) {
			serviceReferences[i] = filteredServiceReferences.get(i);
		}

		return serviceReferences;
	}

	@Override
	public Bundle installBundle(String location) throws BundleException {
		return null;
	}

	@Override
	public Bundle installBundle(String location, InputStream inputStream)
		throws BundleException {

		return null;
	}

	@Override
	public <S> ServiceRegistration<S> registerService(
		Class<S> clazz, S service, Dictionary<String, ?> properties) {

		return null;
	}

	@Override
	public <S> ServiceRegistration<S> registerService(
		Class<S> clazz, ServiceFactory<S> serviceFactory,
		Dictionary<String, ?> properties) {

		return null;
	}

	@Override
	public ServiceRegistration<?> registerService(
		String clazz, Object service, Dictionary<String, ?> properties) {

		_serviceReferenceServices.put(
			new MockServiceReference(
				(String)properties.get("component.name"), clazz),
			service);

		return null;
	}

	@Override
	public ServiceRegistration<?> registerService(
		String[] clazzes, Object service, Dictionary<String, ?> properties) {

		return null;
	}

	@Override
	public void removeBundleListener(BundleListener bundleListener) {
	}

	@Override
	public void removeFrameworkListener(FrameworkListener frameworkListener) {
	}

	@Override
	public void removeServiceListener(ServiceListener serviceListener) {
	}

	@Override
	public boolean ungetService(ServiceReference<?> serviceReference) {
		return false;
	}

	private static final Pattern _propertyPattern = Pattern.compile(
		"^\\(([^=]+)=([^=]+)\\)$");

	private final Map<ServiceReference<S>, Object> _serviceReferenceServices =
		new HashMap<>();

}