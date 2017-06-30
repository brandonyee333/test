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

package com.liferay.osb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppPackageServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppPackageServiceSoap
 * @generated
 */
public class AppPackageSoap implements Serializable {
	public static AppPackageSoap toSoapModel(AppPackage model) {
		AppPackageSoap soapModel = new AppPackageSoap();

		soapModel.setAppPackageId(model.getAppPackageId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setCompatibility(model.getCompatibility());
		soapModel.setCompatibilityPlus(model.getCompatibilityPlus());
		soapModel.setPrepackaged(model.getPrepackaged());
		soapModel.setDownloadCount(model.getDownloadCount());

		return soapModel;
	}

	public static AppPackageSoap[] toSoapModels(AppPackage[] models) {
		AppPackageSoap[] soapModels = new AppPackageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppPackageSoap[][] toSoapModels(AppPackage[][] models) {
		AppPackageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppPackageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppPackageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppPackageSoap[] toSoapModels(List<AppPackage> models) {
		List<AppPackageSoap> soapModels = new ArrayList<AppPackageSoap>(models.size());

		for (AppPackage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppPackageSoap[soapModels.size()]);
	}

	public AppPackageSoap() {
	}

	public long getPrimaryKey() {
		return _appPackageId;
	}

	public void setPrimaryKey(long pk) {
		setAppPackageId(pk);
	}

	public long getAppPackageId() {
		return _appPackageId;
	}

	public void setAppPackageId(long appPackageId) {
		_appPackageId = appPackageId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getAppEntryId() {
		return _appEntryId;
	}

	public void setAppEntryId(long appEntryId) {
		_appEntryId = appEntryId;
	}

	public long getAppVersionId() {
		return _appVersionId;
	}

	public void setAppVersionId(long appVersionId) {
		_appVersionId = appVersionId;
	}

	public int getCompatibility() {
		return _compatibility;
	}

	public void setCompatibility(int compatibility) {
		_compatibility = compatibility;
	}

	public boolean getCompatibilityPlus() {
		return _compatibilityPlus;
	}

	public boolean isCompatibilityPlus() {
		return _compatibilityPlus;
	}

	public void setCompatibilityPlus(boolean compatibilityPlus) {
		_compatibilityPlus = compatibilityPlus;
	}

	public boolean getPrepackaged() {
		return _prepackaged;
	}

	public boolean isPrepackaged() {
		return _prepackaged;
	}

	public void setPrepackaged(boolean prepackaged) {
		_prepackaged = prepackaged;
	}

	public int getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		_downloadCount = downloadCount;
	}

	private long _appPackageId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
	private long _appVersionId;
	private int _compatibility;
	private boolean _compatibilityPlus;
	private boolean _prepackaged;
	private int _downloadCount;
}