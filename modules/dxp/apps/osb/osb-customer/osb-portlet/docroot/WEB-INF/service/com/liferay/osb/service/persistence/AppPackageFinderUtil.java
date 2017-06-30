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

package com.liferay.osb.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPackageFinderUtil {
	public static java.util.List<com.liferay.osb.model.AppPackage> findByLatestCompatibility(
		long appEntryId, int compatibility, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByLatestCompatibility(appEntryId, compatibility,
			status, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AppPackage> findByPBN_P_BSN_BV(
		int portalBuildNumber, boolean prepackaged,
		java.lang.String bundleSymbolicName, java.lang.String bundleVersion,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByPBN_P_BSN_BV(portalBuildNumber, prepackaged,
			bundleSymbolicName, bundleVersion, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AppPackage> findByPBN_P_CN_RH(
		int portalBuildNumber, boolean prepackaged,
		java.lang.String contextName, java.lang.String relengHash, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByPBN_P_CN_RH(portalBuildNumber, prepackaged,
			contextName, relengHash, start, end);
	}

	public static AppPackageFinder getFinder() {
		if (_finder == null) {
			_finder = (AppPackageFinder)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppPackageFinder.class.getName());

			ReferenceRegistry.registerReference(AppPackageFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AppPackageFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AppPackageFinderUtil.class,
			"_finder");
	}

	private static AppPackageFinder _finder;
}