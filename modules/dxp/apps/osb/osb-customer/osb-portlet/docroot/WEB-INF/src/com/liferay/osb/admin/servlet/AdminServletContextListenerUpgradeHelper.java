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

package com.liferay.osb.admin.servlet;

import com.liferay.osb.util.UpgradeUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.NoSuchReleaseException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

/**
 * @author Amos Fong
 */
public class AdminServletContextListenerUpgradeHelper {

	public static void setup(String servletContextName) throws Exception {
		UpgradeUtil.setUp();

		if (!UpgradeUtil.isDeveloperUpgrade()) {
			return;
		}

		try {
			for (int releaseBuildNumber :
					UpgradeUtil.getDeveloperBuildNumbers(servletContextName)) {

				upgrade(releaseBuildNumber);
			}
		}
		catch (NoSuchReleaseException nsre) {
		}
	}

	protected static boolean hasRun(long timestamp) throws PortalException {
		if (timestamp <= 0) {
			return true;
		}

		if (UpgradeUtil.hasRun(timestamp)) {
			return true;
		}

		return false;
	}

	protected static void upgrade(Class<?> upgradeProcessClass)
		throws Exception {

		UpgradeProcess upgradeProcess =
			(UpgradeProcess)upgradeProcessClass.newInstance();

		int frequency = BeanPropertiesUtil.getIntegerSilent(
			upgradeProcess, "frequency");

		long timestamp = BeanPropertiesUtil.getLongSilent(
			upgradeProcess, "timestamp");

		if ((frequency != -1) &&
			((frequency == 1) || ((frequency == 0) && !hasRun(timestamp)))) {

			upgradeProcess.upgrade();
		}
	}

	protected static void upgrade(int buildNumber) throws Exception {
		List<Class<?>> classes = UpgradeUtil.getClasses(buildNumber);

		for (Class<?> clazz : classes) {
			upgrade(clazz);
		}
	}

}