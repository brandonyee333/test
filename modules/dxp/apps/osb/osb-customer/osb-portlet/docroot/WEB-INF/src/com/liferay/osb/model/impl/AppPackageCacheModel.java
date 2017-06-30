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

import com.liferay.osb.model.AppPackage;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppPackage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppPackage
 * @generated
 */
public class AppPackageCacheModel implements CacheModel<AppPackage>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{appPackageId=");
		sb.append(appPackageId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", appVersionId=");
		sb.append(appVersionId);
		sb.append(", compatibility=");
		sb.append(compatibility);
		sb.append(", compatibilityPlus=");
		sb.append(compatibilityPlus);
		sb.append(", prepackaged=");
		sb.append(prepackaged);
		sb.append(", downloadCount=");
		sb.append(downloadCount);
		sb.append("}");

		return sb.toString();
	}

	public AppPackage toEntityModel() {
		AppPackageImpl appPackageImpl = new AppPackageImpl();

		appPackageImpl.setAppPackageId(appPackageId);

		if (createDate == Long.MIN_VALUE) {
			appPackageImpl.setCreateDate(null);
		}
		else {
			appPackageImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			appPackageImpl.setModifiedDate(null);
		}
		else {
			appPackageImpl.setModifiedDate(new Date(modifiedDate));
		}

		appPackageImpl.setAppEntryId(appEntryId);
		appPackageImpl.setAppVersionId(appVersionId);
		appPackageImpl.setCompatibility(compatibility);
		appPackageImpl.setCompatibilityPlus(compatibilityPlus);
		appPackageImpl.setPrepackaged(prepackaged);
		appPackageImpl.setDownloadCount(downloadCount);

		appPackageImpl.resetOriginalValues();

		return appPackageImpl;
	}

	public long appPackageId;
	public long createDate;
	public long modifiedDate;
	public long appEntryId;
	public long appVersionId;
	public int compatibility;
	public boolean compatibilityPlus;
	public boolean prepackaged;
	public int downloadCount;
}