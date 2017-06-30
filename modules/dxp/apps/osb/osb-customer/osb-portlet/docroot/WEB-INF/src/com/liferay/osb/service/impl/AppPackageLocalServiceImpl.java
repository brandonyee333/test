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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.AppPackageMarketplaceSupportException;
import com.liferay.osb.AppPackagePluginFileException;
import com.liferay.osb.AppPackagePluginVersionException;
import com.liferay.osb.DuplicateAppPackageException;
import com.liferay.osb.DuplicateAppPackageSrcException;
import com.liferay.osb.RestrictedLiferayDeploymentContextException;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.service.base.AppPackageLocalServiceBaseImpl;
import com.liferay.osb.util.BundleUtil;
import com.liferay.osb.util.PluginPackageUtil;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * @author Ryan Park
 * @author Amos Fong
 * @author Joan Kim
 * @author Haote Chou
 */
public class AppPackageLocalServiceImpl extends AppPackageLocalServiceBaseImpl {

	public AppPackage addAppPackage(
			long appEntryId, long appVersionId, int compatibility,
			boolean compatibilityPlus)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(appVersionId, compatibility);

		long appPackageId = counterLocalService.increment();

		AppPackage appPackage = appPackagePersistence.create(appPackageId);

		appPackage.setCreateDate(now);
		appPackage.setModifiedDate(now);
		appPackage.setAppEntryId(appEntryId);
		appPackage.setAppVersionId(appVersionId);
		appPackage.setCompatibility(compatibility);
		appPackage.setCompatibilityPlus(compatibilityPlus);
		appPackage.setPrepackaged(false);

		appPackagePersistence.update(appPackage, false);

		return appPackage;
	}

	public void addAppPackageSrc(
			long userId, long appPackageId, String fileName, File file)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackagePersistence.fetchByPrimaryKey(
			appPackageId);

		int count = assetAttachmentLocalService.getAssetAttachmentsCount(
			AppPackage.class.getName(), appPackageId,
			AssetAttachmentConstants.TYPE_PACKAGE_SRC);

		if (count > 0) {
			throw new DuplicateAppPackageSrcException();
		}

		assetAttachmentLocalService.addAssetAttachment(
			userId, AppPackage.class.getName(), appPackage.getAppPackageId(),
			fileName, AssetAttachmentConstants.TYPE_PACKAGE_SRC, 0, file);
	}

	public void copyAppPackages(
			long sourceAppVersionId, long targetAppVersionId)
		throws PortalException, SystemException {

		if (sourceAppVersionId == targetAppVersionId) {
			return;
		}

		appVersionPersistence.findByPrimaryKey(sourceAppVersionId);

		AppVersion targetAppVersion = appVersionPersistence.findByPrimaryKey(
			targetAppVersionId);

		long classNameId = PortalUtil.getClassNameId(
			AppPackage.class.getName());

		List<AppPackage> appPackages = appPackagePersistence.findByAppVersionId(
			sourceAppVersionId);

		for (AppPackage appPackage : appPackages) {
			AppPackage targetAppPackage =
				appPackageLocalService.fetchAppPackage(
					targetAppVersionId, appPackage.getCompatibility());

			if (targetAppPackage != null) {
				continue;
			}

			targetAppPackage =
				appPackageLocalService.addAppPackage(
					targetAppVersion.getAppEntryId(), targetAppVersionId,
					appPackage.getCompatibility(),
					appPackage.getCompatibilityPlus());

			appPackagePluginLocalService.copyAppPackagePlugins(
				appPackage.getAppPackageId(),
				targetAppPackage.getAppPackageId());

			List<AssetAttachment> assetAttachments =
				assetAttachmentPersistence.findByC_C_T(
					classNameId, appPackage.getAppPackageId(),
					AssetAttachmentConstants.TYPE_PACKAGE_SRC);

			for (AssetAttachment assetAttachment : assetAttachments) {
				assetAttachmentLocalService.copyAssetAttachment(
					assetAttachment.getAssetAttachmentId(), classNameId,
					targetAppPackage.getAppPackageId());
			}
		}
	}

	@Override
	public AppPackage deleteAppPackage(AppPackage appPackage)
		throws PortalException, SystemException {

		// App package

		appPackagePersistence.remove(appPackage);

		// App package plugins

		appPackagePluginLocalService.deleteAppPackagePlugins(
			appPackage.getAppPackageId());

		// Asset attachments

		assetAttachmentLocalService.deleteAssetAttachments(
			AppPackage.class.getName(), appPackage.getAppPackageId());

		return appPackage;
	}

	@Override
	public AppPackage deleteAppPackage(long appPackageId)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackagePersistence.findByPrimaryKey(
			appPackageId);

		return deleteAppPackage(appPackage);
	}

	public AppPackage fetchAppPackage(long appVersionId, int compatibility)
		throws SystemException {

		return appPackagePersistence.fetchByAVI_C(appVersionId, compatibility);
	}

	public AppPackage fetchCompatibleAppPackage(
			long appEntryId, int compatibility, int status)
		throws SystemException {

		List<AppPackage> appPackages =
			appPackageFinder.findByLatestCompatibility(
				appEntryId, compatibility, status, 0, 1);

		if (appPackages.isEmpty()) {
			return null;
		}

		return appPackages.get(0);
	}

	public List<AppPackage> getAppPackages(
			long appVersionId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return appPackagePersistence.findByAppVersionId(
			appVersionId, start, end, obc);
	}

	public int getAppPackagesCount(long appVersionId) throws SystemException {
		return appPackagePersistence.countByAppVersionId(appVersionId);
	}

	public List<AppPackage> getPrepackagedAppPackages(
			int portalBuildNumber, String identifyingName,
			String identifyingVersion)
		throws SystemException {

		if (portalBuildNumber >= 7000) {
			return appPackageFinder.findByPBN_P_BSN_BV(
				portalBuildNumber, true, identifyingName, identifyingVersion,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		else {
			return appPackageFinder.findByPBN_P_CN_RH(
				portalBuildNumber, true, identifyingName, identifyingVersion,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
	}

	public boolean hasContextNames(long appPackageId, String[] contextNames)
		throws SystemException {

		for (String contextName : contextNames) {
			contextName = StringUtil.trim(contextName);

			int count = appPackagePluginPersistence.countByAPI_CN(
				appPackageId, contextName);

			if (count <= 0) {
				return false;
			}
		}

		return true;
	}

	public boolean isAppPackagePortalRestartRequired(long appPackageId)
		throws SystemException {

		int count = appPackagePluginPersistence.countByAPI_PRR(
			appPackageId, true);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public AppPackage updateAppPackage(
			long appPackageId, boolean compatibilityPlus)
		throws PortalException, SystemException {

		// App package

		AppPackage appPackage = appPackagePersistence.findByPrimaryKey(
			appPackageId);

		appPackage.setModifiedDate(new Date());
		appPackage.setCompatibilityPlus(compatibilityPlus);

		appPackagePersistence.update(appPackage, false);

		// App package plugin

		List<AppPackagePlugin> appPackagePlugins =
			appPackage.getAppPackagePlugins();

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			try {
				appPackagePluginLocalService.validateAppPackagePlugin(
					appPackage.getAppVersion(), appPackage, appPackagePlugin);
			}
			catch (AppPackagePluginVersionException appve) {
				appPackagePluginPersistence.remove(appPackagePlugin);

				continue;
			}
		}

		return appPackage;
	}

	public AppPackage updateAppPackagePrepackaged(long appPackageId)
		throws PortalException, SystemException {

		AppPackage appPackage = appPackagePersistence.findByPrimaryKey(
			appPackageId);

		appPackage.setPrepackaged(false);

		List<AppPackagePlugin> appPackagePlugins =
			appPackagePluginPersistence.findByAppPackageId(appPackageId);

		if (appPackagePlugins.isEmpty()) {
			return appPackagePersistence.findByPrimaryKey(appPackageId);
		}

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			if (appPackagePlugin.isTypeBundle()) {
				processAppPackagePluginPrepackagedJAR(
					appPackage, appPackagePlugin);
			}
			else {
				processAppPackagePluginPrepackagedWAR(
					appPackage, appPackagePlugin);
			}
		}

		appPackagePersistence.update(appPackage, false);

		return appPackage;
	}

	protected void processAppPackagePluginPrepackagedJAR(
			AppPackage appPackage, AppPackagePlugin appPackagePlugin)
		throws PortalException, SystemException {

		File file = null;
		Manifest manifest = null;

		try {
			AssetAttachment assetAttachment =
				appPackagePlugin.getAssetAttachment();

			file = FileUtil.createTempFile(assetAttachment.getFileAsStream());

			manifest = BundleUtil.getManifest(file);
		}
		catch (IOException ioe) {
			new AppPackagePluginFileException();
		}
		finally {
			if ((file != null) && file.exists()) {
				file.delete();
			}
		}

		Attributes attributes = manifest.getMainAttributes();

		String bundleSymbolicName = GetterUtil.getString(
			attributes.getValue("Bundle-SymbolicName"));

		if (!bundleSymbolicName.startsWith("com.liferay")) {
			return;
		}

		boolean bundle = GetterUtil.getBoolean(
			attributes.getValue("Liferay-Releng-Bundle"));

		if (!bundle && appPackage.isPrepackaged()) {
			throw new RestrictedLiferayDeploymentContextException();
		}

		appPackage.setPrepackaged(bundle);
	}

	protected void processAppPackagePluginPrepackagedWAR(
			AppPackage appPackage, AppPackagePlugin appPackagePlugin)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment = appPackagePlugin.getAssetAttachment();

		File file = null;
		Properties properties = null;

		try {
			file = FileUtil.createTempFile(assetAttachment.getFileAsStream());

			properties = PluginPackageUtil.readRelengProperties(
				assetAttachment.getFileName(), file);
		}
		catch (IOException ioe) {
			return;
		}
		finally {
			if ((file != null) && file.exists()) {
				file.delete();
			}
		}

		if (properties == null) {
			return;
		}

		boolean bundle = GetterUtil.getBoolean(
			properties.getProperty("bundle"));

		if (bundle) {
			appPackage.setPrepackaged(true);
		}
	}

	protected void validate(long appVersionId, int compatibility)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		if ((appVersion.getReleaseType() !=
				AppVersionConstants.RELEASE_TYPE_PRICING) &&
			!PortalReleaseUtil.isMarketplaceSupportedVersion(
				compatibility, appVersion.isPaclEnabled())) {

			throw new AppPackageMarketplaceSupportException();
		}

		AppPackage appPackage = appPackagePersistence.fetchByAVI_C(
			appVersionId, compatibility);

		if (appPackage != null) {
			throw new DuplicateAppPackageException();
		}
	}

}