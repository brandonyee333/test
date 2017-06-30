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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.service.base.AppEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.service.permission.OSBAssetAttachmentPermission;
import com.liferay.osb.service.permission.OSBMarketplacePermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.util.Locale;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Haote Chou
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AppEntryServiceImpl extends AppEntryServiceBaseImpl {

	public AppEntry addAppEntry(
			long developerEntryId, String title,
			Map<Locale, String> descriptionMap, String website,
			String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, boolean labs, int productType,
			String version, Map<Locale, String> changeLogMap, File iconFile,
			boolean paclEnabled, Map<Locale, String> eulaContentMap,
			int licenseType, String orderURL, boolean hidden,
			boolean portalRequired, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBMarketplacePermission.check(
			getPermissionChecker(), developerEntryId, OSBActionKeys.ADD_APP);

		checkAssetCategories(serviceContext);

		checkProductType(productType);

		return appEntryLocalService.addAppEntry(
			getUserId(), developerEntryId, title, descriptionMap, website,
			demoWebsite, documentationWebsite, referenceWebsite,
			sourceCodeWebsite, supportWebsite, labs, productType, version,
			changeLogMap, iconFile, paclEnabled, eulaContentMap, licenseType,
			orderURL, hidden, portalRequired, serviceContext);
	}

	public AssetAttachment addAppEntryMedia(
			long appEntryId, String fileName, File file)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryPersistence.fetchByPrimaryKey(appEntryId);

		AppVersion appVersion = null;

		if (appEntry != null) {
			OSBAppEntryPermission.check(
				getPermissionChecker(), appEntry, OSBActionKeys.UPDATE);

			appVersion = appEntry.getUnreleasedAppVersion();
		}

		if (appVersion != null) {
			return assetAttachmentLocalService.addAssetAttachment(
				getUserId(), AppVersion.class.getName(),
				appVersion.getAppVersionId(), fileName,
				AssetAttachmentConstants.TYPE_MEDIA, 0, file);
		}
		else {
			return assetAttachmentLocalService.addAssetAttachment(
				getUserId(), AssetAttachmentConstants.CLASS_NAME_ID_DEFAULT,
				AssetAttachmentConstants.CLASS_PK_DEFAULT, fileName,
				AssetAttachmentConstants.TYPE_MEDIA, 0, file);
		}
	}

	public File buildLiferayPackage(AppPackage appPackage, boolean licensed)
		throws PortalException, SystemException {

		AppEntry appEntry = appPackage.getAppEntry();

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntry, OSBActionKeys.MANAGE_APP);

		return appEntryLocalService.buildLiferayPackage(appPackage, licensed);
	}

	public AppEntry deleteAppEntry(long appEntryId)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryLocalService.getAppEntry(appEntryId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntry, OSBActionKeys.DELETE);

		return appEntryLocalService.deleteAppEntry(appEntry);
	}

	public void deleteAppEntryMedia(long appEntryId, long assetAttachmentId)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment =
			assetAttachmentLocalService.fetchAssetAttachment(assetAttachmentId);

		if ((appEntryId > 0) ||
			(assetAttachment.getClassPK() >
				AssetAttachmentConstants.CLASS_PK_DEFAULT)) {

			OSBAppEntryPermission.check(
				getPermissionChecker(), appEntryId, OSBActionKeys.UPDATE);
		}
		else {
			OSBAssetAttachmentPermission.check(
				getPermissionChecker(), assetAttachment, OSBActionKeys.DELETE);
		}

		assetAttachmentLocalService.deleteAssetAttachment(assetAttachment);
	}

	public AppEntry getAppEntry(long appEntryId)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryLocalService.getAppEntry(appEntryId);

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntry, OSBActionKeys.VIEW);

		return appEntry;
	}

	public AppEntry updateAppEntry(
			long appEntryId, long licenseLifetime, boolean supported,
			long[] standardQuantities, boolean standardUnlimited,
			long[] developerQuantities, boolean developerUnlimited,
			boolean trial)
		throws PortalException, SystemException {

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryId, OSBActionKeys.UPDATE);

		return appEntryLocalService.updateAppEntry(
			getUserId(), appEntryId, licenseLifetime, supported,
			standardQuantities, standardUnlimited, developerQuantities,
			developerUnlimited, trial);
	}

	public AppEntry updateAppEntry(
			long appEntryId, String version, Map<Locale, String> changeLogMap,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryId,
			OSBActionKeys.UPDATE_APP_VERSION);

		return appEntryLocalService.updateAppEntry(
			appEntryId, version, changeLogMap, serviceContext);
	}

	public AppEntry updateAppEntry(
			long appEntryId, String title, Map<Locale, String> descriptionMap,
			String website, String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, boolean labs, int productType,
			String version, int versionOrder, Map<Locale, String> changeLogMap,
			File iconFile, boolean paclEnabled, int releaseType,
			Map<Locale, String> eulaContentMap, int licenseType,
			String orderURL, boolean hidden, boolean portalRequired, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		checkUpdateAppEntryPermission(appEntryId, status);

		checkAssetCategories(serviceContext);

		checkProductType(productType);

		return appEntryLocalService.updateAppEntry(
			getUserId(), appEntryId, title, descriptionMap, website,
			demoWebsite, documentationWebsite, referenceWebsite,
			sourceCodeWebsite, supportWebsite, labs, productType, version,
			versionOrder, changeLogMap, iconFile, paclEnabled, releaseType,
			eulaContentMap, licenseType, orderURL, hidden, portalRequired,
			status, serviceContext);
	}

	public AppEntry updateStatus(
			long appEntryId, int status, String statusMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		checkUpdateAppEntryPermission(appEntryId, status);

		return appEntryLocalService.updateStatus(
			getUserId(), appEntryId, status, statusMessage, serviceContext);
	}

	protected void checkAssetCategories(ServiceContext serviceContext)
		throws PortalException, SystemException {

		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();

		if (ArrayUtil.contains(
				assetCategoryIds, OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID)) {

			if (!organizationLocalService.hasUserOrganization(
					getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				throw new PrincipalException();
			}
		}
	}

	protected void checkProductType(int productType)
		throws PortalException, SystemException {

		if ((productType > 0) &&
			!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			throw new PrincipalException();
		}
	}

	protected void checkUpdateAppEntryPermission(long appEntryId, int status)
		throws PortalException, SystemException {

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			OSBAppEntryPermission.check(
				getPermissionChecker(), appEntryId, OSBActionKeys.EXPIRE_APP);

			return;
		}

		if (status == WorkflowConstants.STATUS_DRAFT) {
			OSBAppEntryPermission.check(
				getPermissionChecker(), appEntryId, OSBActionKeys.UPDATE);

			return;
		}

		AppEntry appEntry = appEntryLocalService.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getActionableAppVersion();

		if (status == WorkflowConstants.STATUS_PENDING) {
			if (ArrayUtil.contains(
					AppVersionConstants.STATUSES_USER_EDITABLE,
					appVersion.getStatus()) &&
				roleLocalService.hasUserRole(
					getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID)) {

				OSBAppEntryPermission.check(
					getPermissionChecker(), appEntryId,
					OSBActionKeys.MANAGE_APP);

				return;
			}
		}

		if (appVersion.getReleaseType() ==
				AppVersionConstants.RELEASE_TYPE_PRICING) {

			OSBAppEntryPermission.check(
				getPermissionChecker(), appEntryId, OSBActionKeys.MANAGE_APP);

			return;
		}

		OSBAppEntryPermission.check(
			getPermissionChecker(), appEntryId, OSBActionKeys.ADMIN_APP);
	}

}