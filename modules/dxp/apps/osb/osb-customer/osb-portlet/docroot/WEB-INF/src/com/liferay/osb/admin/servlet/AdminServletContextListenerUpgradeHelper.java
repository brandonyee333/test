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