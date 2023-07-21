/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.bundle.blacklist.internal;

import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.lpkg.deployer.util.BundleStartLevelUtil;
import com.liferay.portal.osgi.web.wab.generator.WabGenerator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Shuyang Zhou
 */
public class WebBundleInstaller extends ServiceTracker<WabGenerator, Void> {

	public WebBundleInstaller(
			BundleContext bundleContext, String location, int startLevel)
		throws InvalidSyntaxException {

		super(bundleContext, WabGenerator.class, null);

		_location = location;
		_startLevel = startLevel;
	}

	@Override
	public Void addingService(ServiceReference<WabGenerator> serviceReference) {

		// Service must be explicitly gotten from the bundle context to ensure
		// DS component's lazy activation is completed

		WabGenerator wabGenerator = context.getService(serviceReference);

		if (wabGenerator == null) {
			throw new IllegalStateException("Missing WAB generator");
		}

		try {
			Bundle bundle = context.installBundle(_location);

			BundleStartLevelUtil.setStartLevelAndStart(
				bundle, _startLevel, context);
		}
		catch (BundleException be) {
			ReflectionUtil.throwException(be);
		}
		finally {
			context.ungetService(serviceReference);
		}

		close();

		return null;
	}

	private final String _location;
	private final int _startLevel;

}