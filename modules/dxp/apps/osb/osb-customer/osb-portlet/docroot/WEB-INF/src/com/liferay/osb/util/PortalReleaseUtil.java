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

package com.liferay.osb.util;

import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.plugin.Version;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class PortalReleaseUtil {

	public static int getBaseBuildNumber(int buildNumber) {
		int baseNumber = buildNumber;

		int bugFixNumber = buildNumber % 100;

		baseNumber = baseNumber - bugFixNumber;

		if (bugFixNumber >= 10) {
			baseNumber = baseNumber + 10;
		}

		return baseNumber;
	}

	public static int getBuildNumber(String version) {
		Version versionObj = Version.getInstance(version);

		int buildNumber = GetterUtil.getInteger(versionObj.getMajor()) * 1000;
		buildNumber += GetterUtil.getInteger(versionObj.getMinor()) * 100;
		buildNumber += GetterUtil.getInteger(versionObj.getBugFix());

		return buildNumber;
	}

	public static String[] getSupportedVersions(int buildNumber)
		throws PortalException, SystemException {

		List<String> versions = new ArrayList<String>();

		int pos = -1;

		List<PortalRelease> portalReleases =
			PortalReleaseLocalServiceUtil.getPortalReleases(true);

		for (PortalRelease portalRelease : portalReleases) {
			if (portalRelease.getBuildNumber() == buildNumber) {
				pos = portalReleases.indexOf(portalRelease);

				break;
			}
		}

		if (pos > -1) {
			versions.add(getVersion(buildNumber));
		}

		for (int i = pos; i >= 0; i--) {
			int supportedBuildNumber = portalReleases.get(i).getBuildNumber();

			if (isMatchingMinorEdition(buildNumber, supportedBuildNumber)) {
				versions.add(getVersion(supportedBuildNumber, true));
			}
		}

		return versions.toArray(new String[0]);
	}

	public static String getVersion(int buildNumber) {
		return getVersion(buildNumber, false);
	}

	public static String getVersion(int buildNumber, boolean plus) {
		StringBundler sb = new StringBundler(5);

		int major = buildNumber / 1000;

		sb.append(major);
		sb.append(StringPool.PERIOD);

		int minor = (buildNumber % 1000) / 100;

		sb.append(minor);
		sb.append(StringPool.PERIOD);

		int bugFix = buildNumber % 100;

		sb.append(bugFix);

		if (plus) {
			sb.append(StringPool.PLUS);
		}

		return sb.toString();
	}

	public static String getVersionName(AppPackage appPackage) {
		return getVersionName(
			appPackage.getCompatibility(), appPackage.getCompatibilityPlus());
	}

	public static String getVersionName(int buildNumber, boolean plus) {
		try {
			PortalRelease portalRelease =
				PortalReleaseLocalServiceUtil.getPortalRelease(buildNumber);

			String versionName = portalRelease.getVersionName();

			if (plus) {
				versionName = versionName.concat(StringPool.PLUS);
			}

			return versionName;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No portal release exists with build number " +
						buildNumber);
			}

			return StringPool.BLANK;
		}
	}

	public static boolean isMarketplaceSupportedVersion(
			int buildNumber, boolean paclEnabled)
		throws PortalException, SystemException {

		PortalRelease portalRelease =
			PortalReleaseLocalServiceUtil.getPortalRelease(buildNumber);

		if (paclEnabled && !portalRelease.isPaclSupport()) {
			return false;
		}

		return portalRelease.isMarketplaceSupport();
	}

	protected static boolean isMatchingMinorEdition(
		int buildNumber1, int buildNumber2) {

		if (buildNumber1 == buildNumber2) {
			return true;
		}

		// Minor version

		if ((buildNumber1 / 100) != (buildNumber2 / 100)) {
			return false;
		}

		// Community edition

		int bugFix1 = buildNumber1 % 100;
		int bugFix2 = buildNumber2 % 100;

		if ((bugFix1 < 10) && (bugFix2 < 10)) {
			return true;
		}

		// Enterprise edition

		if ((bugFix1 >= 10) && (bugFix2 >= 10)) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(PortalReleaseUtil.class);

}