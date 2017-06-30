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

import com.liferay.osb.model.AppPackagePlugin;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppPackagePlugin in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppPackagePlugin
 * @generated
 */
public class AppPackagePluginCacheModel implements CacheModel<AppPackagePlugin>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{appPackagePluginId=");
		sb.append(appPackagePluginId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", appVersionId=");
		sb.append(appVersionId);
		sb.append(", appPackageId=");
		sb.append(appPackageId);
		sb.append(", assetAttachmentId=");
		sb.append(assetAttachmentId);
		sb.append(", fileName=");
		sb.append(fileName);
		sb.append(", bundleSymbolicName=");
		sb.append(bundleSymbolicName);
		sb.append(", bundleVersion=");
		sb.append(bundleVersion);
		sb.append(", contextName=");
		sb.append(contextName);
		sb.append(", paclEnabled=");
		sb.append(paclEnabled);
		sb.append(", relengHash=");
		sb.append(relengHash);
		sb.append(", portalRestartRequired=");
		sb.append(portalRestartRequired);
		sb.append("}");

		return sb.toString();
	}

	public AppPackagePlugin toEntityModel() {
		AppPackagePluginImpl appPackagePluginImpl = new AppPackagePluginImpl();

		appPackagePluginImpl.setAppPackagePluginId(appPackagePluginId);

		if (createDate == Long.MIN_VALUE) {
			appPackagePluginImpl.setCreateDate(null);
		}
		else {
			appPackagePluginImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			appPackagePluginImpl.setModifiedDate(null);
		}
		else {
			appPackagePluginImpl.setModifiedDate(new Date(modifiedDate));
		}

		appPackagePluginImpl.setAppEntryId(appEntryId);
		appPackagePluginImpl.setAppVersionId(appVersionId);
		appPackagePluginImpl.setAppPackageId(appPackageId);
		appPackagePluginImpl.setAssetAttachmentId(assetAttachmentId);

		if (fileName == null) {
			appPackagePluginImpl.setFileName(StringPool.BLANK);
		}
		else {
			appPackagePluginImpl.setFileName(fileName);
		}

		if (bundleSymbolicName == null) {
			appPackagePluginImpl.setBundleSymbolicName(StringPool.BLANK);
		}
		else {
			appPackagePluginImpl.setBundleSymbolicName(bundleSymbolicName);
		}

		if (bundleVersion == null) {
			appPackagePluginImpl.setBundleVersion(StringPool.BLANK);
		}
		else {
			appPackagePluginImpl.setBundleVersion(bundleVersion);
		}

		if (contextName == null) {
			appPackagePluginImpl.setContextName(StringPool.BLANK);
		}
		else {
			appPackagePluginImpl.setContextName(contextName);
		}

		appPackagePluginImpl.setPaclEnabled(paclEnabled);

		if (relengHash == null) {
			appPackagePluginImpl.setRelengHash(StringPool.BLANK);
		}
		else {
			appPackagePluginImpl.setRelengHash(relengHash);
		}

		appPackagePluginImpl.setPortalRestartRequired(portalRestartRequired);

		appPackagePluginImpl.resetOriginalValues();

		return appPackagePluginImpl;
	}

	public long appPackagePluginId;
	public long createDate;
	public long modifiedDate;
	public long appEntryId;
	public long appVersionId;
	public long appPackageId;
	public long assetAttachmentId;
	public String fileName;
	public String bundleSymbolicName;
	public String bundleVersion;
	public String contextName;
	public boolean paclEnabled;
	public String relengHash;
	public boolean portalRestartRequired;
}