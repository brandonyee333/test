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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppVersionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppVersionServiceSoap
 * @generated
 */
public class AppVersionSoap implements Serializable {
	public static AppVersionSoap toSoapModel(AppVersion model) {
		AppVersionSoap soapModel = new AppVersionSoap();

		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAppEntryId(model.getAppEntryId());
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
		soapModel.setVersionId(model.getVersionId());
		soapModel.setVersionOrder(model.getVersionOrder());
		soapModel.setChangeLog(model.getChangeLog());
		soapModel.setIconImageId(model.getIconImageId());
		soapModel.setSize(model.getSize());
		soapModel.setDownloadCount(model.getDownloadCount());
		soapModel.setPaclEnabled(model.getPaclEnabled());
		soapModel.setReleaseDate(model.getReleaseDate());
		soapModel.setReleaseType(model.getReleaseType());
		soapModel.setContractEntryId(model.getContractEntryId());
		soapModel.setSupported(model.getSupported());
		soapModel.setOrderURL(model.getOrderURL());
		soapModel.setHidden(model.getHidden());
		soapModel.setPortalRequired(model.getPortalRequired());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStatusMessage(model.getStatusMessage());

		return soapModel;
	}

	public static AppVersionSoap[] toSoapModels(AppVersion[] models) {
		AppVersionSoap[] soapModels = new AppVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppVersionSoap[][] toSoapModels(AppVersion[][] models) {
		AppVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppVersionSoap[] toSoapModels(List<AppVersion> models) {
		List<AppVersionSoap> soapModels = new ArrayList<AppVersionSoap>(models.size());

		for (AppVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppVersionSoap[soapModels.size()]);
	}

	public AppVersionSoap() {
	}

	public long getPrimaryKey() {
		return _appVersionId;
	}

	public void setPrimaryKey(long pk) {
		setAppVersionId(pk);
	}

	public long getAppVersionId() {
		return _appVersionId;
	}

	public void setAppVersionId(long appVersionId) {
		_appVersionId = appVersionId;
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

	public long getAppEntryId() {
		return _appEntryId;
	}

	public void setAppEntryId(long appEntryId) {
		_appEntryId = appEntryId;
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

	public int getVersionId() {
		return _versionId;
	}

	public void setVersionId(int versionId) {
		_versionId = versionId;
	}

	public int getVersionOrder() {
		return _versionOrder;
	}

	public void setVersionOrder(int versionOrder) {
		_versionOrder = versionOrder;
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

	public boolean getPaclEnabled() {
		return _paclEnabled;
	}

	public boolean isPaclEnabled() {
		return _paclEnabled;
	}

	public void setPaclEnabled(boolean paclEnabled) {
		_paclEnabled = paclEnabled;
	}

	public Date getReleaseDate() {
		return _releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		_releaseDate = releaseDate;
	}

	public int getReleaseType() {
		return _releaseType;
	}

	public void setReleaseType(int releaseType) {
		_releaseType = releaseType;
	}

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;
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

	public String getStatusMessage() {
		return _statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	private long _appVersionId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
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
	private int _versionId;
	private int _versionOrder;
	private String _changeLog;
	private long _iconImageId;
	private long _size;
	private int _downloadCount;
	private boolean _paclEnabled;
	private Date _releaseDate;
	private int _releaseType;
	private long _contractEntryId;
	private boolean _supported;
	private String _orderURL;
	private boolean _hidden;
	private boolean _portalRequired;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
}