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

package com.liferay.osb.servlet.util;

import com.liferay.osb.ext.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AssetAttachmentRankComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class MarketplaceReleaseServletCommitUtil {

	public static AppEntry updateAppEntry(
			long appEntryId, String version, String changeLog, int buildNumber,
			File[] pluginFiles, File sourceCodeFile)
		throws Exception {

		return _instance._updateAppEntry(
			appEntryId, version, changeLog, buildNumber, pluginFiles,
			sourceCodeFile);
	}

	private AppEntry _updateAppEntry(
			long appEntryId, String version, String changeLog, int buildNumber,
			File[] pluginFiles, File sourceCodeFile)
		throws Exception {

		if (_updateAppEntryWrapperMethod == null) {
			_updateAppEntryWrapperMethod = getClass().getDeclaredMethod(
				"_updateAppEntryWrapper",
				new Class<?>[] {
					long.class, String.class, String.class, int.class,
					File[].class, File.class
				});
		}

		return (AppEntry)ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, getClass(), _updateAppEntryWrapperMethod,
			new Object[] {
				appEntryId, version, changeLog, buildNumber, pluginFiles,
				sourceCodeFile
			},
			new String[] {"transactionAdvice"});
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	private AppEntry _updateAppEntryWrapper(
			long appEntryId, String version, String changeLog, int buildNumber,
			File[] pluginFiles, File sourceCodeFile)
		throws Exception {

		// App entry

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new PrincipalException();
		}

		AppVersion appVersion = appEntry.getActionableAppVersion();

		Map<Locale, String> eulaContentMap = null;

		if (appVersion.getContractEntryId() > 0) {
			ContractEntry contractEntry =
				ContractEntryLocalServiceUtil.getContractEntry(
					appVersion.getContractEntryId());

			eulaContentMap = contractEntry.getContentMap();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAssetCategoryIds(appEntry.getAssetCategoryIds());
		serviceContext.setAssetTagNames(appEntry.getAssetTagNames());

		appEntry = AppEntryLocalServiceUtil.updateAppEntry(
			appVersion.getUserId(), appEntryId, appEntry.getTitle(),
			appEntry.getDescriptionMap(), appEntry.getWebsite(),
			appEntry.getDemoWebsite(), appEntry.getDocumentationWebsite(),
			appEntry.getReferenceWebsite(), appEntry.getSourceCodeWebsite(),
			appEntry.getSupportWebsite(), appEntry.isLabs(),
			appEntry.getProductType(), null,
			AppVersionConstants.VERSION_ORDER_LAST, null, null,
			appEntry.isPaclEnabled(),
			AppVersionConstants.RELEASE_TYPE_NEW_VERSION, eulaContentMap,
			appEntry.getLicenseType(), appEntry.getOrderURL(),
			appEntry.isHidden(), appEntry.isPortalRequired(),
			WorkflowConstants.STATUS_DRAFT, serviceContext);

		// Asset attachment

		if (appVersion.isApproved()) {
			AppVersion newAppVersion = appEntry.getActionableAppVersion();

			List<AssetAttachment> assetAttachments =
				AssetAttachmentLocalServiceUtil.getAssetAttachments(
					AppVersion.class.getName(), appVersion.getAppVersionId(),
					AssetAttachmentConstants.TYPE_MEDIA, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, new AssetAttachmentRankComparator());

			for (AssetAttachment assetAttachment : assetAttachments) {
				AssetAttachmentLocalServiceUtil.copyAssetAttachment(
					assetAttachment.getAssetAttachmentId(),
					AppVersion.class.getName(),
					newAppVersion.getAppVersionId());
			}
		}

		// App package plugins

		appVersion = appEntry.getActionableAppVersion();

		for (File pluginFile : pluginFiles) {
			AppPackagePluginLocalServiceUtil.addAppPackagePlugin(
				appVersion.getUserId(), appVersion.getAppVersionId(),
				pluginFile.getName(), pluginFile, buildNumber, true);
		}

		// App pacakge source

		if ((sourceCodeFile != null) && sourceCodeFile.exists()) {
			AppPackage appPackage = AppPackageLocalServiceUtil.fetchAppPackage(
				appVersion.getAppVersionId(), buildNumber);

			AssetAttachmentLocalServiceUtil.addAssetAttachment(
				appVersion.getUserId(), AppPackage.class.getName(),
				appPackage.getAppPackageId(), sourceCodeFile.getName(),
				AssetAttachmentConstants.TYPE_PACKAGE_SRC, 0, sourceCodeFile);
		}

		// App version

		Map<Locale, String> changeLogMap = new HashMap<Locale, String>(1);

		changeLogMap.put(LocaleUtil.getDefault(), changeLog);

		appEntry = AppEntryLocalServiceUtil.updateAppEntry(
			appEntryId, version, changeLogMap, serviceContext);

		// App entry status

		AppEntryLocalServiceUtil.updateStatus(
			appVersion.getUserId(), appEntry.getAppEntryId(),
			WorkflowConstants.STATUS_PENDING, "Submitted by Release",
			serviceContext);

		return appEntry;
	}

	private static MarketplaceReleaseServletCommitUtil _instance =
		new MarketplaceReleaseServletCommitUtil();

	private Method _updateAppEntryWrapperMethod;

}