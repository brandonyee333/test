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

import com.liferay.osb.model.AppVersion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AppVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AppVersion
 * @generated
 */
public class AppVersionCacheModel implements CacheModel<AppVersion>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(77);

		sb.append("{appVersionId=");
		sb.append(appVersionId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", appEntryId=");
		sb.append(appEntryId);
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
		sb.append(", versionId=");
		sb.append(versionId);
		sb.append(", versionOrder=");
		sb.append(versionOrder);
		sb.append(", changeLog=");
		sb.append(changeLog);
		sb.append(", iconImageId=");
		sb.append(iconImageId);
		sb.append(", size=");
		sb.append(size);
		sb.append(", downloadCount=");
		sb.append(downloadCount);
		sb.append(", paclEnabled=");
		sb.append(paclEnabled);
		sb.append(", releaseDate=");
		sb.append(releaseDate);
		sb.append(", releaseType=");
		sb.append(releaseType);
		sb.append(", contractEntryId=");
		sb.append(contractEntryId);
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
		sb.append(", statusMessage=");
		sb.append(statusMessage);
		sb.append("}");

		return sb.toString();
	}

	public AppVersion toEntityModel() {
		AppVersionImpl appVersionImpl = new AppVersionImpl();

		appVersionImpl.setAppVersionId(appVersionId);
		appVersionImpl.setUserId(userId);

		if (userName == null) {
			appVersionImpl.setUserName(StringPool.BLANK);
		}
		else {
			appVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			appVersionImpl.setCreateDate(null);
		}
		else {
			appVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			appVersionImpl.setModifiedDate(null);
		}
		else {
			appVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		appVersionImpl.setAppEntryId(appEntryId);
		appVersionImpl.setDeveloperEntryId(developerEntryId);

		if (developerName == null) {
			appVersionImpl.setDeveloperName(StringPool.BLANK);
		}
		else {
			appVersionImpl.setDeveloperName(developerName);
		}

		if (title == null) {
			appVersionImpl.setTitle(StringPool.BLANK);
		}
		else {
			appVersionImpl.setTitle(title);
		}

		if (description == null) {
			appVersionImpl.setDescription(StringPool.BLANK);
		}
		else {
			appVersionImpl.setDescription(description);
		}

		if (website == null) {
			appVersionImpl.setWebsite(StringPool.BLANK);
		}
		else {
			appVersionImpl.setWebsite(website);
		}

		if (demoWebsite == null) {
			appVersionImpl.setDemoWebsite(StringPool.BLANK);
		}
		else {
			appVersionImpl.setDemoWebsite(demoWebsite);
		}

		if (documentationWebsite == null) {
			appVersionImpl.setDocumentationWebsite(StringPool.BLANK);
		}
		else {
			appVersionImpl.setDocumentationWebsite(documentationWebsite);
		}

		if (referenceWebsite == null) {
			appVersionImpl.setReferenceWebsite(StringPool.BLANK);
		}
		else {
			appVersionImpl.setReferenceWebsite(referenceWebsite);
		}

		if (sourceCodeWebsite == null) {
			appVersionImpl.setSourceCodeWebsite(StringPool.BLANK);
		}
		else {
			appVersionImpl.setSourceCodeWebsite(sourceCodeWebsite);
		}

		if (supportWebsite == null) {
			appVersionImpl.setSupportWebsite(StringPool.BLANK);
		}
		else {
			appVersionImpl.setSupportWebsite(supportWebsite);
		}

		appVersionImpl.setLabs(labs);
		appVersionImpl.setProductType(productType);

		if (version == null) {
			appVersionImpl.setVersion(StringPool.BLANK);
		}
		else {
			appVersionImpl.setVersion(version);
		}

		appVersionImpl.setVersionId(versionId);
		appVersionImpl.setVersionOrder(versionOrder);

		if (changeLog == null) {
			appVersionImpl.setChangeLog(StringPool.BLANK);
		}
		else {
			appVersionImpl.setChangeLog(changeLog);
		}

		appVersionImpl.setIconImageId(iconImageId);
		appVersionImpl.setSize(size);
		appVersionImpl.setDownloadCount(downloadCount);
		appVersionImpl.setPaclEnabled(paclEnabled);

		if (releaseDate == Long.MIN_VALUE) {
			appVersionImpl.setReleaseDate(null);
		}
		else {
			appVersionImpl.setReleaseDate(new Date(releaseDate));
		}

		appVersionImpl.setReleaseType(releaseType);
		appVersionImpl.setContractEntryId(contractEntryId);
		appVersionImpl.setSupported(supported);

		if (orderURL == null) {
			appVersionImpl.setOrderURL(StringPool.BLANK);
		}
		else {
			appVersionImpl.setOrderURL(orderURL);
		}

		appVersionImpl.setHidden(hidden);
		appVersionImpl.setPortalRequired(portalRequired);
		appVersionImpl.setStatus(status);
		appVersionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			appVersionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			appVersionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			appVersionImpl.setStatusDate(null);
		}
		else {
			appVersionImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			appVersionImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			appVersionImpl.setStatusMessage(statusMessage);
		}

		appVersionImpl.resetOriginalValues();

		return appVersionImpl;
	}

	public long appVersionId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long appEntryId;
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
	public int versionId;
	public int versionOrder;
	public String changeLog;
	public long iconImageId;
	public long size;
	public int downloadCount;
	public boolean paclEnabled;
	public long releaseDate;
	public int releaseType;
	public long contractEntryId;
	public boolean supported;
	public String orderURL;
	public boolean hidden;
	public boolean portalRequired;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
}