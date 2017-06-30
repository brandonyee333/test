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

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePluginConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.util.PluginPackageUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Joan Kim
 */
public class AppPackagePluginImpl extends AppPackagePluginBaseImpl {

	public AppPackagePluginImpl() {
	}

	public AppPackage getAppPackage() throws PortalException, SystemException {
		return AppPackageLocalServiceUtil.getAppPackage(getAppPackageId());
	}

	public AssetAttachment getAssetAttachment() throws SystemException {
		return AssetAttachmentLocalServiceUtil.fetchAssetAttachment(
			getAssetAttachmentId());
	}

	public String getIdentifyingKey() {
		if (Validator.isNotNull(getBundleVersion())) {
			return getBundleVersion();
		}
		else {
			return getRelengHash();
		}
	}

	public String getIdentifyingName() {
		if (Validator.isNotNull(getBundleSymbolicName())) {
			return getBundleSymbolicName();
		}
		else {
			return getContextName();
		}
	}

	public UnicodeProperties getPACLProperties()
		throws PortalException, SystemException {

		UnicodeProperties paclProperties = new UnicodeProperties(true);

		if (!isPaclEnabled()) {
			return paclProperties;
		}

		File file = null;

		try {
			AssetAttachment assetAttachment = getAssetAttachment();

			file = FileUtil.createTempFile(assetAttachment.getFileAsStream());

			Properties properties =
				PluginPackageUtil.readPluginPackageProperties(file);

			for (Map.Entry<Object, Object> entry : properties.entrySet()) {
				String key = (String)entry.getKey();
				String value = (String)entry.getValue();

				if (key.startsWith("security-manager-")) {
					paclProperties.setProperty(key, value);
				}
			}
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}
		finally {
			FileUtil.delete(file);
		}

		return paclProperties;
	}

	public String getType() {
		return AppPackagePluginConstants.getType(getFileName());
	}

	public boolean hasConflictingContextName() throws SystemException {
		int count =
			AppPackagePluginLocalServiceUtil.getOtherAppPackagePluginsCount(
				getAppEntryId(), getContextName());

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean isType(String type) {
		return type.equals(getType());
	}

	public boolean isTypeBundle() {
		return isType(AppPackagePluginConstants.TYPE_BUNDLE);
	}

}