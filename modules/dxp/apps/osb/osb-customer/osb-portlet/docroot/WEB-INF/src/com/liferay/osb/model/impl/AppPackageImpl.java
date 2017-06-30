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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.plugin.Version;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPackageImpl extends AppPackageBaseImpl {

	public AppPackageImpl() {
	}

	public AppEntry getAppEntry() throws PortalException, SystemException {
		return AppEntryLocalServiceUtil.getAppEntry(getAppEntryId());
	}

	public List<AppPackagePlugin> getAppPackagePlugins()
		throws SystemException {

		return AppPackagePluginLocalServiceUtil.getAppPackagePlugins(
			getAppPackageId());
	}

	public AppVersion getAppVersion() throws PortalException, SystemException {
		return AppVersionLocalServiceUtil.getAppVersion(getAppVersionId());
	}

	public Version getVersion() {
		String version = PortalReleaseUtil.getVersion(
			getCompatibility(), isCompatibilityPlus());

		return Version.getInstance(version);
	}

	public boolean isPortalRestartRequired() throws SystemException {
		return AppPackageLocalServiceUtil.isAppPackagePortalRestartRequired(
			getAppPackageId());
	}

}