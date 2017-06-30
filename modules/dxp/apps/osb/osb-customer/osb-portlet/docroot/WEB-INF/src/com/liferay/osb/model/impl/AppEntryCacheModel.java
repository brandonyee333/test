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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppEntry
 * @generated
 */
public class AppEntryCacheModel implements CacheModel<AppEntry>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(71);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", developerEntryId=");
		sb.append(developerEntryId);
		sb.append(", developerName=");
		sb.append(developerName);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", website=");
		sb.append(website);
		sb.append(", demoWebsite=");
		sb.append(demoWebsite);
		sb.append(", documentationWebsite=");
		sb.append(documentationWebsite);
		sb.append(", referenceWebsite=");
		sb.append(referenceWebsite);
		sb.append(", sourceCodeWebsite=");
		sb.append(sourceCodeWebsite);
		sb.append(", supportWebsite=");
		sb.append(supportWebsite);
		sb.append(", labs=");
		sb.append(labs);
		sb.append(", productType=");
		sb.append(productType);
		sb.append(", version=");
		sb.append(version);
		sb.append(", changeLog=");
		sb.append(changeLog);
		sb.append(", iconImageId=");
		sb.append(iconImageId);
		sb.append(", paclEnabled=");
		sb.append(paclEnabled);
		sb.append(", size=");
		sb.append(size);
		sb.append(", downloadCount=");
		sb.append(downloadCount);
		sb.append(", licenseType=");
		sb.append(licenseType);
		sb.append(", licenseLifetime=");
		sb.append(licenseLifetime);
		sb.append(", supported=");
		sb.append(supported);
		sb.append(", orderURL=");
		sb.append(orderURL);
		sb.append(", hidden=");
		sb.append(hidden);
		sb.append(", portalRequired=");
		sb.append(portalRequired);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", statusVersionDate=");
		sb.append(statusVersionDate);
		sb.append("}");

		return sb.toString();
	}

	public AppEntry toEntityModel() {
		AppEntryImpl appEntryImpl = new AppEntryImpl();

		if (uuid == null) {
			appEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			appEntryImpl.setUuid(uuid);
		}

		appEntryImpl.setAppEntryId(appEntryId);
		appEntryImpl.setUserId(userId);

		if (userName == null) {
			appEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			appEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			appEntryImpl.setCreateDate(null);
		}
		else {
			appEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			appEntryImpl.setModifiedDate(null);
		}
		else {
			appEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		appEntryImpl.setDeveloperEntryId(developerEntryId);

		if (developerName == null) {
			appEntryImpl.setDeveloperName(StringPool.BLANK);
		}
		else {
			appEntryImpl.setDeveloperName(developerName);
		}

		if (title == null) {
			appEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			appEntryImpl.setTitle(title);
		}

		if (description == null) {
			appEntryImpl.setDescription(StringPool.BLANK);
		}
		else {
			appEntryImpl.setDescription(description);
		}

		if (website == null) {
			appEntryImpl.setWebsite(StringPool.BLANK);
		}
		else {
			appEntryImpl.setWebsite(website);
		}

		if (demoWebsite == null) {
			appEntryImpl.setDemoWebsite(StringPool.BLANK);
		}
		else {
			appEntryImpl.setDemoWebsite(demoWebsite);
		}

		if (documentationWebsite == null) {
			appEntryImpl.setDocumentationWebsite(StringPool.BLANK);
		}
		else {
			appEntryImpl.setDocumentationWebsite(documentationWebsite);
		}

		if (referenceWebsite == null) {
			appEntryImpl.setReferenceWebsite(StringPool.BLANK);
		}
		else {
			appEntryImpl.setReferenceWebsite(referenceWebsite);
		}

		if (sourceCodeWebsite == null) {
			appEntryImpl.setSourceCodeWebsite(StringPool.BLANK);
		}
		else {
			appEntryImpl.setSourceCodeWebsite(sourceCodeWebsite);
		}

		if (supportWebsite == null) {
			appEntryImpl.setSupportWebsite(StringPool.BLANK);
		}
		else {
			appEntryImpl.setSupportWebsite(supportWebsite);
		}

		appEntryImpl.setLabs(labs);
		appEntryImpl.setProductType(productType);

		if (version == null) {
			appEntryImpl.setVersion(StringPool.BLANK);
		}
		else {
			appEntryImpl.setVersion(version);
		}

		if (changeLog == null) {
			appEntryImpl.setChangeLog(StringPool.BLANK);
		}
		else {
			appEntryImpl.setChangeLog(changeLog);
		}

		appEntryImpl.setIconImageId(iconImageId);
		appEntryImpl.setPaclEnabled(paclEnabled);
		appEntryImpl.setSize(size);
		appEntryImpl.setDownloadCount(downloadCount);
		appEntryImpl.setLicenseType(licenseType);
		appEntryImpl.setLicenseLifetime(licenseLifetime);
		appEntryImpl.setSupported(supported);

		if (orderURL == null) {
			appEntryImpl.setOrderURL(StringPool.BLANK);
		}
		else {
			appEntryImpl.setOrderURL(orderURL);
		}

		appEntryImpl.setHidden(hidden);
		appEntryImpl.setPortalRequired(portalRequired);
		appEntryImpl.setStatus(status);
		appEntryImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			appEntryImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			appEntryImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			appEntryImpl.setStatusDate(null);
		}
		else {
			appEntryImpl.setStatusDate(new Date(statusDate));
		}

		if (statusVersionDate == Long.MIN_VALUE) {
			appEntryImpl.setStatusVersionDate(null);
		}
		else {
			appEntryImpl.setStatusVersionDate(new Date(statusVersionDate));
		}

		appEntryImpl.resetOriginalValues();

		return appEntryImpl;
	}

	public String uuid;
	public long appEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long developerEntryId;
	public String developerName;
	public String title;
	public String description;
	public String website;
	public String demoWebsite;
	public String documentationWebsite;
	public String referenceWebsite;
	public String sourceCodeWebsite;
	public String supportWebsite;
	public boolean labs;
	public int productType;
	public String version;
	public String changeLog;
	public long iconImageId;
	public boolean paclEnabled;
	public long size;
	public int downloadCount;
	public int licenseType;
	public long licenseLifetime;
	public boolean supported;
	public String orderURL;
	public boolean hidden;
	public boolean portalRequired;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public long statusVersionDate;
}