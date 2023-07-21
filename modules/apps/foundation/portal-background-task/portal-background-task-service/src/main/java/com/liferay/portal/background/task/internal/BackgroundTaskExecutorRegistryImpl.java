/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.background.task.internal;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutorRegistry;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.Validator;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = BackgroundTaskExecutorRegistry.class)
public class BackgroundTaskExecutorRegistryImpl
	implements BackgroundTaskExecutorRegistry {

	@Override
	public synchronized BackgroundTaskExecutor getBackgroundTaskExecutor(
		String backgroundTaskExecutorClassName) {

		return _backgroundTaskExecutors.get(backgroundTaskExecutorClassName);
	}

	@Override
	public synchronized void registerBackgroundTaskExecutor(
		String backgroundTaskExecutorClassName,
		BackgroundTaskExecutor backgroundTaskExecutor) {

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			"background.task.executor.class.name",
			backgroundTaskExecutorClassName);

		ServiceRegistration<BackgroundTaskExecutor> serviceRegistration =
			_bundleContext.registerService(
				BackgroundTaskExecutor.class, backgroundTaskExecutor,
				properties);

		_backgroundTaskExecutorRegistrations.put(
			backgroundTaskExecutorClassName, serviceRegistration);
	}

	@Override
	public synchronized void unregisterBackgroundTaskExecutor(
		String backgroundTaskExecutorClassName) {

		if (!_backgroundTaskExecutorRegistrations.containsKey(
				backgroundTaskExecutorClassName)) {

			return;
		}

		ServiceRegistration<BackgroundTaskExecutor> serviceRegistration =
			_backgroundTaskExecutorRegistrations.get(
				backgroundTaskExecutorClassName);

		serviceRegistration.unregister();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected synchronized void addBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor,
		Map<String, Object> properties) {

		String backgroundTaskExecutorClassName = (String)properties.get(
			"background.task.executor.class.name");

		if (Validator.isNull(backgroundTaskExecutorClassName)) {
			throw new IllegalArgumentException(
				"Property \"background.task.executor.class.name\" is not set " +
					"for " + backgroundTaskExecutor);
		}

		_backgroundTaskExecutors.put(
			backgroundTaskExecutorClassName, backgroundTaskExecutor);
	}

	@Deactivate
	protected synchronized void deactivate() {
		_bundleContext = null;

		for (ServiceRegistration<BackgroundTaskExecutor> serviceRegistration :
				_backgroundTaskExecutorRegistrations.values()) {

			serviceRegistration.unregister();
		}

		_backgroundTaskExecutorRegistrations.clear();
	}

	protected synchronized void removeBackgroundTaskExecutor(
		BackgroundTaskExecutor backgroundTaskExecutor,
		Map<String, Object> properties) {

		String backgroundTaskExecutorClassName = (String)properties.get(
			"background.task.executor.class.name");

		if (Validator.isNull(backgroundTaskExecutorClassName)) {
			return;
		}

		_backgroundTaskExecutors.remove(backgroundTaskExecutorClassName);
	}

	private final Map<String, ServiceRegistration<BackgroundTaskExecutor>>
		_backgroundTaskExecutorRegistrations = new HashMap<>();
	private final Map<String, BackgroundTaskExecutor> _backgroundTaskExecutors =
		new HashMap<>();
	private BundleContext _bundleContext;

}