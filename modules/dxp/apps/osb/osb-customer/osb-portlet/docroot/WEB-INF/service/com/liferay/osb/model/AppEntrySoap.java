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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppEntryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppEntryServiceSoap
 * @generated
 */
public class AppEntrySoap implements Serializable {
	public static AppEntrySoap toSoapModel(AppEntry model) {
		AppEntrySoap soapModel = new AppEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDeveloperEntryId(model.getDeveloperEntryId());
		soapModel.setDeveloperName(model.getDeveloperName());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setWebsite(model.getWebsite());
		soapModel.setDemoWebsite(model.getDemoWebsite());
		soapModel.setDocumentationWebsite(model.getDocumentationWebsite());
		soapModel.setReferenceWebsite(model.getReferenceWebsite());
		soapModel.setSourceCodeWebsite(model.getSourceCodeWebsite());
		soapModel.setSupportWebsite(model.getSupportWebsite());
		soapModel.setLabs(model.getLabs());
		soapModel.setProductType(model.getProductType());
		soapModel.setVersion(model.getVersion());
		soapModel.setChangeLog(model.getChangeLog());
		soapModel.setIconImageId(model.getIconImageId());
		soapModel.setPaclEnabled(model.getPaclEnabled());
		soapModel.setSize(model.getSize());
		soapModel.setDownloadCount(model.getDownloadCount());
		soapModel.setLicenseType(model.getLicenseType());
		soapModel.setLicenseLifetime(model.getLicenseLifetime());
		soapModel.setSupported(model.getSupported());
		soapModel.setOrderURL(model.getOrderURL());
		soapModel.setHidden(model.getHidden());
		soapModel.setPortalRequired(model.getPortalRequired());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStatusVersionDate(model.getStatusVersionDate());

		return soapModel;
	}

	public static AppEntrySoap[] toSoapModels(AppEntry[] models) {
		AppEntrySoap[] soapModels = new AppEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppEntrySoap[][] toSoapModels(AppEntry[][] models) {
		AppEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppEntrySoap[] toSoapModels(List<AppEntry> models) {
		List<AppEntrySoap> soapModels = new ArrayList<AppEntrySoap>(models.size());

		for (AppEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppEntrySoap[soapModels.size()]);
	}

	public AppEntrySoap() {
	}

	public long getPrimaryKey() {
		return _appEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAppEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAppEntryId() {
		return _appEntryId;
	}

	public void setAppEntryId(long appEntryId) {
		_appEntryId = appEntryId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public long getDeveloperEntryId() {
		return _developerEntryId;
	}

	public void setDeveloperEntryId(long developerEntryId) {
		_developerEntryId = developerEntryId;
	}

	public String getDeveloperName() {
		return _developerName;
	}

	public void setDeveloperName(String developerName) {
		_developerName = developerName;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getWebsite() {
		return _website;
	}

	public void setWebsite(String website) {
		_website = website;
	}

	public String getDemoWebsite() {
		return _demoWebsite;
	}

	public void setDemoWebsite(String demoWebsite) {
		_demoWebsite = demoWebsite;
	}

	public String getDocumentationWebsite() {
		return _documentationWebsite;
	}

	public void setDocumentationWebsite(String documentationWebsite) {
		_documentationWebsite = documentationWebsite;
	}

	public String getReferenceWebsite() {
		return _referenceWebsite;
	}

	public void setReferenceWebsite(String referenceWebsite) {
		_referenceWebsite = referenceWebsite;
	}

	public String getSourceCodeWebsite() {
		return _sourceCodeWebsite;
	}

	public void setSourceCodeWebsite(String sourceCodeWebsite) {
		_sourceCodeWebsite = sourceCodeWebsite;
	}

	public String getSupportWebsite() {
		return _supportWebsite;
	}

	public void setSupportWebsite(String supportWebsite) {
		_supportWebsite = supportWebsite;
	}

	public boolean getLabs() {
		return _labs;
	}

	public boolean isLabs() {
		return _labs;
	}

	public void setLabs(boolean labs) {
		_labs = labs;
	}

	public int getProductType() {
		return _productType;
	}

	public void setProductType(int productType) {
		_productType = productType;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getChangeLog() {
		return _changeLog;
	}

	public void setChangeLog(String changeLog) {
		_changeLog = changeLog;
	}

	public long getIconImageId() {
		return _iconImageId;
	}

	public void setIconImageId(long iconImageId) {
		_iconImageId = iconImageId;
	}

	public boolean getPaclEnabled() {
		return _paclEnabled;
	}

	public boolean isPaclEnabled() {
		return _paclEnabled;
	}

	public void setPaclEnabled(boolean paclEnabled) {
		_paclEnabled = paclEnabled;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public int getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		_downloadCount = downloadCount;
	}

	public int getLicenseType() {
		return _licenseType;
	}

	public void setLicenseType(int licenseType) {
		_licenseType = licenseType;
	}

	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	public void setLicenseLifetime(long licenseLifetime) {
		_licenseLifetime = licenseLifetime;
	}

	public boolean getSupported() {
		return _supported;
	}

	public boolean isSupported() {
		return _supported;
	}

	public void setSupported(boolean supported) {
		_supported = supported;
	}

	public String getOrderURL() {
		return _orderURL;
	}

	public void setOrderURL(String orderURL) {
		_orderURL = orderURL;
	}

	public boolean getHidden() {
		return _hidden;
	}

	public boolean isHidden() {
		return _hidden;
	}

	public void setHidden(boolean hidden) {
		_hidden = hidden;
	}

	public boolean getPortalRequired() {
		return _portalRequired;
	}

	public boolean isPortalRequired() {
		return _portalRequired;
	}

	public void setPortalRequired(boolean portalRequired) {
		_portalRequired = portalRequired;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public Date getStatusVersionDate() {
		return _statusVersionDate;
	}

	public void setStatusVersionDate(Date statusVersionDate) {
		_statusVersionDate = statusVersionDate;
	}

	private String _uuid;
	private long _appEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _developerEntryId;
	private String _developerName;
	private String _title;
	private String _description;
	private String _website;
	private String _demoWebsite;
	private String _documentationWebsite;
	private String _referenceWebsite;
	private String _sourceCodeWebsite;
	private String _supportWebsite;
	private boolean _labs;
	private int _productType;
	private String _version;
	private String _changeLog;
	private long _iconImageId;
	private boolean _paclEnabled;
	private long _size;
	private int _downloadCount;
	private int _licenseType;
	private long _licenseLifetime;
	private boolean _supported;
	private String _orderURL;
	private boolean _hidden;
	private boolean _portalRequired;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private Date _statusVersionDate;
}