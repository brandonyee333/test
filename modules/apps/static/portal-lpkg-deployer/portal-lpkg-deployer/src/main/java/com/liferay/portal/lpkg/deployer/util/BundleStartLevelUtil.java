/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lpkg.deployer.util;

import java.util.Dictionary;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.startlevel.BundleStartLevel;
import org.osgi.framework.startlevel.FrameworkStartLevel;

/**
 * @author Matthew Tambara
 */
public class BundleStartLevelUtil {

	public static void setStartLevelAndStart(
			Bundle bundle, int startLevel, BundleContext bundleContext)
		throws BundleException {

		Bundle systemBundle = bundleContext.getBundle(0);

		FrameworkStartLevel frameworkStartLevel = systemBundle.adapt(
			FrameworkStartLevel.class);

		BundleStartLevel bundleStartLevel = bundle.adapt(
			BundleStartLevel.class);

		if (frameworkStartLevel.getStartLevel() >= startLevel) {
			_startBundle(bundle);

			bundleStartLevel.setStartLevel(startLevel);
		}
		else {
			bundleStartLevel.setStartLevel(startLevel);

			_startBundle(bundle);
		}
	}

	private static void _startBundle(Bundle bundle) throws BundleException {
		Dictionary<String, String> headers = bundle.getHeaders();

		String fragmentHost = headers.get(Constants.FRAGMENT_HOST);

		if (fragmentHost == null) {
			bundle.start();
		}
	}

}