/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.equinox.log.bridge.internal;

import org.eclipse.equinox.log.ExtendedLogReaderService;
import org.eclipse.osgi.internal.hookregistry.ActivatorHookFactory;
import org.eclipse.osgi.internal.hookregistry.HookConfigurator;
import org.eclipse.osgi.internal.hookregistry.HookRegistry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Raymond Augé
 * @author Kamesh Sampath
 */
public class PortalHookConfigurator
	implements ActivatorHookFactory, BundleActivator, HookConfigurator {

	public PortalHookConfigurator() {
		_bundleStartStopLogger = new BundleStartStopLogger();
		_portalSynchronousLogListener = new PortalSynchronousLogListener();
	}

	@Override
	public void addHooks(HookRegistry hookRegistry) {
		hookRegistry.addActivatorHookFactory(this);
	}

	@Override
	public BundleActivator createActivator() {
		return this;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ServiceReference<ExtendedLogReaderService> serviceReference =
			bundleContext.getServiceReference(ExtendedLogReaderService.class);

		if (serviceReference != null) {
			ExtendedLogReaderService extendedLogReaderService =
				bundleContext.getService(serviceReference);

			extendedLogReaderService.addLogListener(
				_portalSynchronousLogListener);
		}

		bundleContext.addBundleListener(_bundleStartStopLogger);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		ServiceReference<ExtendedLogReaderService> serviceReference =
			bundleContext.getServiceReference(ExtendedLogReaderService.class);

		ExtendedLogReaderService extendedLogReaderService =
			bundleContext.getService(serviceReference);

		extendedLogReaderService.removeLogListener(
			_portalSynchronousLogListener);

		bundleContext.removeBundleListener(_bundleStartStopLogger);
	}

	private final BundleStartStopLogger _bundleStartStopLogger;
	private final PortalSynchronousLogListener _portalSynchronousLogListener;

}