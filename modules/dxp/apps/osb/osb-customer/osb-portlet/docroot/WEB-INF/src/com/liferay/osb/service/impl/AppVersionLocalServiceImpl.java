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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.PortalReleaseConstants;
import com.liferay.osb.service.base.AppVersionLocalServiceBaseImpl;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AppVersionVersionOrderComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Amos Fong
 * @author Ryan Park
 */
public class AppVersionLocalServiceImpl extends AppVersionLocalServiceBaseImpl {

	@Override
	public AppVersion deleteAppVersion(AppVersion appVersion)
		throws PortalException, SystemException {

		// App version

		appVersionPersistence.remove(appVersion);

		// App flag

		appFlagPersistence.removeByAppVersionId(appVersion.getAppVersionId());

		// App packages

		List<AppPackage> appPackages =
			appPackagePersistence.findByAppVersionId(
				appVersion.getAppVersionId());

		for (AppPackage appPackage : appPackages) {
			appPackageLocalService.deleteAppPackage(appPackage);
		}

		// App pricing

		appPricingLocalService.deleteAppPricingByAppVersionId(
			appVersion.getAppVersionId());

		// Asset attachments

		assetAttachmentLocalService.deleteAssetAttachments(
			AppVersion.class.getName(), appVersion.getAppVersionId());

		// Asset license

		assetLicenseLocalService.deleteAssetLicenses(
			AppVersion.class.getName(), appVersion.getAppVersionId());

		return appVersion;
	}

	@Override
	public AppVersion deleteAppVersion(long appVersionId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		return deleteAppVersion(appVersion);
	}

	public AppVersion fetchAppVersion(
			long appEntryId, int status, OrderByComparator obc)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return appVersionPersistence.fetchByAppEntryId_First(
				appEntryId, obc);
		}

		return appVersionPersistence.fetchByAEI_S_First(
			appEntryId, status, obc);
	}

	public AppVersion fetchAppVersion(
			long appEntryId, int[] statuses, OrderByComparator obc)
		throws SystemException {

		List<AppVersion> appVersions = appVersionFinder.findByAEI_S(
			appEntryId, statuses, 0, 1, obc);

		if (appVersions.isEmpty()) {
			return null;
		}

		return appVersions.get(0);
	}

	public AppVersion fetchAppVersion(long appEntryId, String version)
		throws PortalException, SystemException {

		return appVersionPersistence.fetchByAEI_V(appEntryId, version);
	}

	public List<AppVersion> getAppVersions(
			long appEntryId, int compatibility, int status, int start, int end)
		throws SystemException {

		if (compatibility == PortalReleaseConstants.PORTAL_ALL_BUILDS) {
			return getAppVersions(
				appEntryId, status, start, end,
				new AppVersionVersionOrderComparator());
		}

		return appVersionFinder.findByAEI_C_S(
			appEntryId, compatibility, status, start, end);
	}

	public List<AppVersion> getAppVersions(
			long appEntryId, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return appVersionPersistence.findByAppEntryId(
				appEntryId, start, end, obc);
		}

		return appVersionPersistence.findByAEI_S(
			appEntryId, status, start, end, obc);
	}

	public List<AppVersion> getAppVersions(
			long appEntryId, int[] statuses, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return appVersionFinder.findByAEI_S(
			appEntryId, statuses, start, end, obc);
	}

	public int getAppVersionsByReleaseTypeCount(
			long appEntryId, int releaseType)
		throws SystemException {

		return appVersionPersistence.countByAEI_RT(appEntryId, releaseType);
	}

	public int getAppVersionsCount(long appEntryId, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return appVersionPersistence.countByAppEntryId(appEntryId);
		}

		return appVersionPersistence.countByAEI_S(appEntryId, status);
	}

	public int getAppVersionsCount(long appEntryId, int[] statuses)
		throws SystemException {

		return appVersionFinder.countByAEI_S(appEntryId, statuses);
	}

}