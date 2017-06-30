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
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.AppPackageException;
import com.liferay.osb.AppPackagePluginBundleException;
import com.liferay.osb.AppPackagePluginBundleSymbolicNameException;
import com.liferay.osb.AppPackagePluginDeploymentContextException;
import com.liferay.osb.AppPackagePluginFileException;
import com.liferay.osb.AppPackagePluginFileNameException;
import com.liferay.osb.AppPackagePluginFileNameLengthException;
import com.liferay.osb.AppPackagePluginNameException;
import com.liferay.osb.AppPackagePluginPACLException;
import com.liferay.osb.AppPackagePluginPluginPackageException;
import com.liferay.osb.AppPackagePluginXMLException;
import com.liferay.osb.DuplicateAppPackagePluginException;
import com.liferay.osb.DuplicateAppPackagePluginOwnerException;
import com.liferay.osb.RequiredLiferayDeploymentContextException;
import com.liferay.osb.RequiredResourcesImporterException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppPackagePluginConstants;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.osb.service.base.AppPackagePluginLocalServiceBaseImpl;
import com.liferay.osb.util.BundleUtil;
import com.liferay.osb.util.ContextUtil;
import com.liferay.osb.util.PluginPackageUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
public class AppPackagePluginLocalServiceImpl
	extends AppPackagePluginLocalServiceBaseImpl {

	public AppPackagePlugin addAppPackagePlugin(
			long userId, long appVersionId, String fileName, File file,
			int compatibility, boolean compatibilityPlus)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		AppPackage appPackage = appPackageLocalService.fetchAppPackage(
			appVersionId, compatibility);

		if (appPackage == null) {
			appPackage = appPackageLocalService.addAppPackage(
				appVersion.getAppEntryId(), appVersionId, compatibility,
				compatibilityPlus);
		}
		else if (!compatibilityPlus && appPackage.isCompatibilityPlus()) {
			appPackage = appPackageLocalService.updateAppPackage(
				appPackage.getAppPackageId(), false);
		}

		validate(appVersion, appPackage, fileName, file, compatibility);

		String bundleSymbolicName = StringPool.BLANK;
		String bundleVersion = StringPool.BLANK;
		String contextName = StringPool.BLANK;
		boolean portalRestartRequired = false;

		if (fileName.endsWith(".jar")) {
			Manifest manifest = BundleUtil.getManifest(file);

			Attributes attributes = manifest.getMainAttributes();

			bundleSymbolicName = GetterUtil.getString(
				attributes.getValue("Bundle-SymbolicName"));
			bundleVersion = GetterUtil.getString(
				attributes.getValue("Bundle-Version"));

			String contextPath = GetterUtil.getString(
				attributes.getValue("Web-ContextPath"));

			contextName = ContextUtil.getContextName(contextPath, true);

			portalRestartRequired = GetterUtil.getBoolean(
				attributes.getValue("Liferay-Releng-Restart-Required"));
		}
		else {
			contextName = ContextUtil.getContextName(fileName, false);
		}

		Date now = new Date();

		validate(appPackage, contextName);

		long appPackagePluginId = counterLocalService.increment();

		AppPackagePlugin appPackagePlugin = appPackagePluginPersistence.create(
			appPackagePluginId);

		appPackagePlugin.setCreateDate(now);
		appPackagePlugin.setModifiedDate(now);
		appPackagePlugin.setAppEntryId(appPackage.getAppEntryId());
		appPackagePlugin.setAppVersionId(appPackage.getAppVersionId());
		appPackagePlugin.setAppPackageId(appPackage.getAppPackageId());

		AssetAttachment assetAttachment =
			assetAttachmentLocalService.addAssetAttachment(
				userId, AppPackagePlugin.class.getName(), appPackagePluginId,
				fileName, AssetAttachmentConstants.TYPE_PACKAGE_PLUGIN, 0,
				file);

		appPackagePlugin.setAssetAttachmentId(
			assetAttachment.getAssetAttachmentId());
		appPackagePlugin.setFileName(assetAttachment.getFileName());
		appPackagePlugin.setBundleSymbolicName(bundleSymbolicName);
		appPackagePlugin.setBundleVersion(bundleVersion);
		appPackagePlugin.setContextName(contextName);

		try {
			Properties properties =
				PluginPackageUtil.readPluginPackageProperties(file);

			if (properties != null) {
				boolean paclEnabled = GetterUtil.getBoolean(
					properties.getProperty("security-manager-enabled"));

				appPackagePlugin.setPaclEnabled(paclEnabled);
			}
		}
		catch (Exception e) {
			appPackagePlugin.setPaclEnabled(false);
		}

		setRelengAttributes(appPackagePlugin, file);

		appPackagePlugin.setPortalRestartRequired(portalRestartRequired);

		appPackagePluginPersistence.update(appPackagePlugin, false);

		return appPackagePlugin;
	}

	public void copyAppPackagePlugins(
			long sourceAppPackageId, long targetAppPackageId)
		throws PortalException, SystemException {

		AppPackage sourceAppPackage = appPackagePersistence.findByPrimaryKey(
			sourceAppPackageId);
		AppPackage targetAppPackage = appPackagePersistence.findByPrimaryKey(
			targetAppPackageId);
		Date now = new Date();

		List<AppPackagePlugin> appPackagePlugins =
			appPackagePluginPersistence.findByAppPackageId(
				sourceAppPackage.getAppPackageId());

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			long newAppPackagePluginId = counterLocalService.increment();

			AppPackagePlugin newAppPackagePlugin =
				appPackagePluginPersistence.create(newAppPackagePluginId);

			newAppPackagePlugin.setCreateDate(now);
			newAppPackagePlugin.setModifiedDate(now);
			newAppPackagePlugin.setAppEntryId(targetAppPackage.getAppEntryId());
			newAppPackagePlugin.setAppVersionId(
				targetAppPackage.getAppVersionId());
			newAppPackagePlugin.setAppPackageId(
				targetAppPackage.getAppPackageId());

			AssetAttachment assetAttachment =
				assetAttachmentLocalService.copyAssetAttachment(
					appPackagePlugin.getAssetAttachmentId(),
					AppPackagePlugin.class.getName(), newAppPackagePluginId);

			newAppPackagePlugin.setAssetAttachmentId(
				assetAttachment.getAssetAttachmentId());

			newAppPackagePlugin.setFileName(assetAttachment.getFileName());
			newAppPackagePlugin.setBundleSymbolicName(
				appPackagePlugin.getBundleSymbolicName());
			newAppPackagePlugin.setBundleVersion(
				appPackagePlugin.getBundleVersion());
			newAppPackagePlugin.setContextName(
				appPackagePlugin.getContextName());
			newAppPackagePlugin.setPaclEnabled(
				appPackagePlugin.getPaclEnabled());
			newAppPackagePlugin.setPortalRestartRequired(
				appPackagePlugin.getPortalRestartRequired());

			appPackagePluginPersistence.update(newAppPackagePlugin, false);
		}
	}

	public AppPackagePlugin deleteAppPackagePlugin(
			AppPackagePlugin appPackagePlugin)
		throws PortalException, SystemException {

		appPackagePluginPersistence.remove(appPackagePlugin);

		assetAttachmentLocalService.deleteAssetAttachments(
			AppPackagePlugin.class.getName(),
			appPackagePlugin.getAppPackagePluginId());

		return appPackagePlugin;
	}

	@Override
	public AppPackagePlugin deleteAppPackagePlugin(long appPackagePluginId)
		throws PortalException, SystemException {

		AppPackagePlugin appPackagePlugin =
			appPackagePluginPersistence.findByPrimaryKey(appPackagePluginId);

		return deleteAppPackagePlugin(appPackagePlugin);
	}

	public void deleteAppPackagePlugins(long appPackageId)
		throws PortalException, SystemException {

		List<AppPackagePlugin> appPackagePlugins =
			appPackagePluginPersistence.findByAppPackageId(appPackageId);

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			deleteAppPackagePlugin(appPackagePlugin);
		}
	}

	public void deleteInvalidAppPackagePlugins(long appVersionId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		if (!appVersion.isPaclEnabled()) {
			return;
		}

		List<AppPackage> appPackages = appPackagePersistence.findByAppVersionId(
			appVersionId);

		for (AppPackage appPackage : appPackages) {
			List<AppPackagePlugin> appPackagePlugins =
				appPackagePluginPersistence.findByAppPackageId(
					appPackage.getAppPackageId());

			for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
				if (!appPackagePlugin.isType(
						AppPackagePluginConstants.TYPE_BUNDLE) &&
					!appPackagePlugin.isPaclEnabled()) {

					deleteAppPackagePlugin(appPackagePlugin);
				}
			}
		}
	}

	public List<AppPackagePlugin> getAppPackagePlugins(long appPackageId)
		throws SystemException {

		return appPackagePluginPersistence.findByAppPackageId(appPackageId);
	}

	public List<AppPackagePlugin> getAppPackagePlugins(
			long appPackageId, boolean paclEnabled)
		throws SystemException {

		return appPackagePluginPersistence.findByAPI_PE(
			appPackageId, paclEnabled);
	}

	public int getAppPackagePluginsCount(long appPackageId)
		throws SystemException {

		return appPackagePluginPersistence.countByAppPackageId(appPackageId);
	}

	public int getAppPackagePluginsCount(long appPackageId, boolean paclEnabled)
		throws SystemException {

		return appPackagePluginPersistence.countByAPI_PE(
			appPackageId, paclEnabled);
	}

	public List<AppPackagePlugin> getOtherAppPackagePlugins(
			long appEntryId, String contextName)
		throws SystemException {

		return appPackagePluginPersistence.findByNotAEI_CN(
			appEntryId, contextName);
	}

	public int getOtherAppPackagePluginsCount(
			long appEntryId, String contextName)
		throws SystemException {

		return appPackagePluginPersistence.countByNotAEI_CN(
			appEntryId, contextName);
	}

	public void validateAppPackagePlugin(
			AppVersion appVersion, AppPackage appPackage,
			AppPackagePlugin appPackagePlugin)
		throws PortalException, SystemException {

		String contextName = appPackagePlugin.getContextName();

		if (contextName.equals(
				AppPackagePluginConstants.CONTEXT_NAME_RESOURCES_IMPORTER)) {

			return;
		}

		File file = null;
		ZipFile zipFile = null;

		try {
			AssetAttachment assetAttachment =
				appPackagePlugin.getAssetAttachment();

			file = FileUtil.createTempFile(assetAttachment.getFileAsStream());

			String fileName = assetAttachment.getFileName();

			if (fileName.endsWith(".jar")) {
				validateJAR(
					appPackage.getAppPackageId(),
					appPackagePlugin.getAppPackagePluginId(),
					appVersion.getDeveloperEntry(),
					appPackage.getCompatibility(), file);

				return;
			}
			else if (fileName.endsWith(".war")) {
				validateWAR(
					appVersion.getAppEntryId(), appPackage, fileName, file);
			}
			else if (fileName.equals("index.xml")) {
				validateIndexXML(appVersion.getAppEntry(), appPackage);

				return;
			}
			else {
				throw new AppPackagePluginFileNameException();
			}

			PluginPackage pluginPackage = PluginPackageUtil.readPluginPackage(
				fileName, file);

			List<String> requiredDeploymentContexts =
				pluginPackage.getRequiredDeploymentContexts();

			if (appVersion.isDeveloperEntryLiferayInc()) {
				for (String requiredDeploymentContext :
						requiredDeploymentContexts) {

					int count = appPackagePluginPersistence.countByAPI_CN(
						appPackage.getAppPackageId(),
						requiredDeploymentContext);

					if (count <= 0) {
						throw new RequiredLiferayDeploymentContextException();
					}
				}
			}

			if (!appVersion.isAddResourcesImporter()) {
				return;
			}

			if (!appPackagePlugin.isType(
					AppPackagePluginConstants.TYPE_THEME)) {

				return;
			}

			AppPackage resourcesImporterAppPackage = null;

			PortalRelease portalRelease =
				portalReleaseLocalService.getPortalRelease(
					appPackage.getCompatibility());

			if (portalRelease.isEE()) {
				resourcesImporterAppPackage =
					appPackageLocalService.fetchCompatibleAppPackage(
						PortletPropsValues.
							MARKETPLACE_RESOURCES_IMPORTER_EE_APP_ENTRY_ID,
						appPackage.getCompatibility(),
						WorkflowConstants.STATUS_APPROVED);
			}
			else {
				resourcesImporterAppPackage =
					appPackageLocalService.fetchCompatibleAppPackage(
						PortletPropsValues.
							MARKETPLACE_RESOURCES_IMPORTER_CE_APP_ENTRY_ID,
						appPackage.getCompatibility(),
						WorkflowConstants.STATUS_APPROVED);
			}

			if (resourcesImporterAppPackage != null) {
				if (!requiredDeploymentContexts.contains(
						AppPackagePluginConstants.
						CONTEXT_NAME_RESOURCES_IMPORTER)) {

					throw new RequiredResourcesImporterException();
				}

				zipFile = new ZipFile(file);

				ZipEntry zipEntry = zipFile.getEntry(
					"WEB-INF/classes/resources-importer/");

				if (zipEntry == null) {
					throw new RequiredResourcesImporterException();
				}
			}
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}
		finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}

			FileUtil.delete(file);
		}
	}

	protected boolean isDuplicateContextName(
			long developerEntryId, String contextName)
		throws PortalException, SystemException {

		List<AppPackagePlugin> appPackagePlugins =
			appPackagePluginPersistence.findByContextName(contextName);

		for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
			AppEntry appEntry = appEntryPersistence.findByPrimaryKey(
				appPackagePlugin.getAppEntryId());

			if (appEntry.getDeveloperEntryId() != developerEntryId) {
				return true;
			}
		}

		return false;
	}

	protected void setRelengAttributes(
		AppPackagePlugin appPackagePlugin, File file) {

		InputStream inputStream = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry zipEntry = null;

			if (appPackagePlugin.isType(
					AppPackagePluginConstants.TYPE_BUNDLE)) {

				zipEntry = zipFile.getEntry(
					"META-INF/liferay-releng.changelog.md5");
			}
			else {
				zipEntry = zipFile.getEntry(
					"WEB-INF/liferay-releng.changelog.md5");
			}

			if (zipEntry == null) {
				return;
			}

			inputStream = zipFile.getInputStream(zipEntry);

			String relengHash = StringUtil.read(inputStream);

			appPackagePlugin.setRelengHash(relengHash);
		}
		catch (Exception e) {
		}
		finally {
			StreamUtil.cleanUp(inputStream);

			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}
		}
	}

	protected void validate(AppPackage appPackage, String contextName)
		throws PortalException, SystemException {

		int count = appPackagePluginPersistence.countByAPI_CN(
			appPackage.getAppPackageId(), contextName);

		if (Validator.isNotNull(contextName) && (count > 0)) {
			throw new DuplicateAppPackagePluginException();
		}

		AppVersion appVersion = appPackage.getAppVersion();

		if (!appVersion.isNewRelease()) {
			AppVersion approvedAppVersion =
				appVersionLocalService.fetchAppVersion(
					appVersion.getAppEntryId(),
					AppVersionConstants.STATUSES_APPROVED, null);

			AppPackage approvedAppPackage = appPackagePersistence.fetchByAVI_C(
				approvedAppVersion.getAppVersionId(),
				appPackage.getCompatibility());

			if (approvedAppPackage != null) {
				throw new AppPackageException();
			}
		}
	}

	protected void validate(
			AppVersion appVersion, AppPackage appPackage, String fileName,
			File file, int compatibility)
		throws PortalException, SystemException {

		if (fileName.length() > 255) {
			throw new AppPackagePluginFileNameLengthException();
		}

		List<AppPackagePlugin> appPackagePlugins =
			appPackagePluginPersistence.findByAPI_FN(
				appPackage.getAppPackageId(), fileName);

		if (appPackagePlugins.size() > 0) {
			throw new AppPackagePluginFileException();
		}

		if (fileName.endsWith(".jar")) {
			validateJAR(
				appPackage.getAppPackageId(), 0, appVersion.getDeveloperEntry(),
				compatibility, file);
		}
		else if (fileName.endsWith(".war")) {
			validateWAR(appVersion.getAppEntryId(), appPackage, fileName, file);
		}
		else if (fileName.equals("index.xml")) {
			validateIndexXML(appVersion.getAppEntry(), appPackage);
		}
		else {
			throw new AppPackagePluginFileNameException();
		}
	}

	protected void validateIndexXML(AppEntry appEntry, AppPackage appPackage)
		throws PortalException, SystemException {

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new AppPackagePluginFileNameException();
		}

		PortalRelease portalRelease =
			portalReleasePersistence.fetchByBuildNumber(
				appPackage.getCompatibility());

		if (!portalRelease.hasOsgiSupport()) {
			throw new AppPackagePluginFileNameException();
		}
	}

	protected void validateJAR(
			long appPackageId, long appPackagePluginId,
			DeveloperEntry developerEntry, int compatibility, File file)
		throws PortalException, SystemException {

		Manifest manifest = BundleUtil.getManifest(file);

		Attributes attributes = manifest.getMainAttributes();

		String bundleSymbolicName = GetterUtil.getString(
			attributes.getValue("Bundle-SymbolicName"));

		String bundleVersion = GetterUtil.getString(
			attributes.getValue("Bundle-Version"));

		List<AppPackagePlugin> appPackagePlugins =
			appPackagePluginPersistence.findByAPI_BSN_BV(
				appPackageId, bundleSymbolicName, bundleVersion);

		if (appPackagePlugins.size() > 0) {
			if (appPackagePlugins.size() == 1) {
				AppPackagePlugin appPackagePlugin = appPackagePlugins.get(0);

				if (appPackagePlugin.getAppPackagePluginId() !=
						appPackagePluginId) {

					throw new AppPackagePluginBundleSymbolicNameException(
						"Bundle-SymbolicName not unique");
				}
			}
			else {
				throw new AppPackagePluginBundleSymbolicNameException(
					"Bundle-SymbolicName not unique");
			}
		}

		PortalRelease portalRelease =
			PortalReleaseLocalServiceUtil.getPortalRelease(compatibility);

		if (!portalRelease.isOsgiSupport()) {
			throw new AppPackagePluginBundleException();
		}

		String contextPath = BundleUtil.getContextPath(file);

		if (Validator.isNull(contextPath)) {
			return;
		}

		if (isDuplicateContextName(
				developerEntry.getDeveloperEntryId(),
				ContextUtil.getContextName(contextPath, true))) {

			throw new DuplicateAppPackagePluginOwnerException();
		}
	}

	protected void validateWAR(
			long appEntryId, AppPackage appPackage, String fileName, File file)
		throws PortalException, SystemException {

		PluginPackage pluginPackage = PluginPackageUtil.readPluginPackage(
			fileName, file);

		validateWAR(appEntryId, fileName, file, pluginPackage);
	}

	protected void validateWAR(
			long appEntryId, String fileName, File file,
			PluginPackage pluginPackage)
		throws PortalException, SystemException {

		if (fileName.contains(StringPool.COMMA)) {
			throw new AppPackagePluginFileNameException();
		}

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			Matcher versionPatternMatcher = _warFileNamePattern.matcher(
				fileName);

			if (!versionPatternMatcher.matches()) {
				throw new AppPackagePluginNameException();
			}
		}

		if (Validator.isNull(AppPackagePluginConstants.getType(fileName))) {
			throw new AppPackagePluginNameException();
		}

		if (pluginPackage == null) {
			throw new AppPackagePluginPluginPackageException();
		}

		String artifactId = pluginPackage.getArtifactId();
		String recommendedDeploymentContext =
			pluginPackage.getRecommendedDeploymentContext();

		if (!artifactId.equals(recommendedDeploymentContext)) {
			throw new AppPackagePluginDeploymentContextException();
		}

		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry liferayPluginPackageZipEntry = zipFile.getEntry(
				"WEB-INF/liferay-plugin-package.xml");

			if (liferayPluginPackageZipEntry != null) {
				throw new AppPackagePluginXMLException();
			}

			/* if (appEntry.isDeveloperEntryLiferayInc()) {
				ZipEntry liferayRelengZipEntry = zipFile.getEntry(
					"WEB-INF/liferay-releng.properties");

				if (liferayRelengZipEntry == null) {
					throw new AppPackagePluginRelengException();
				}
			} */
		}
		catch (ZipException ze) {
			throw new PortalException(ze);
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}
		finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}
		}

		String contextName = ContextUtil.getContextName(
			fileName, fileName.endsWith(".jar"));

		if (ArrayUtil.contains(
				PortletPropsValues.MARKETPLACE_GLOBAL_CONTEXT_NAMES,
				contextName)) {

			return;
		}

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		Properties properties = PluginPackageUtil.readPluginPackageProperties(
			file);

		boolean paclEnabled = GetterUtil.getBoolean(
			properties.getProperty("security-manager-enabled"));

		if (appVersion.isPaclEnabled() && !paclEnabled &&
			!ArrayUtil.contains(
				PortletPropsValues.MARKETPLACE_PACL_EXEMPT_DEVELOPER_ENTRY_IDS,
				appEntry.getDeveloperEntryId())) {

			throw new AppPackagePluginPACLException();
		}

		if (!appEntry.isDeveloperEntryLiferayInc() &&
			contextName.equals("marketplace-portlet")) {

			throw new AppPackagePluginNameException();
		}

		if (isDuplicateContextName(
				appEntry.getDeveloperEntryId(), contextName)) {

			throw new DuplicateAppPackagePluginOwnerException();
		}
	}

	private static final Pattern _warFileNamePattern = Pattern.compile(
		"(.*)(-)([0-9]+)(\\.)([0-9]+)(\\.)([0-9]+)(\\.)([0-9]+)(\\.war)");

}