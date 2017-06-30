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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppPackagePluginServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppPackagePluginServiceSoap
 * @generated
 */
public class AppPackagePluginSoap implements Serializable {
	public static AppPackagePluginSoap toSoapModel(AppPackagePlugin model) {
		AppPackagePluginSoap soapModel = new AppPackagePluginSoap();

		soapModel.setAppPackagePluginId(model.getAppPackagePluginId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setAppPackageId(model.getAppPackageId());
		soapModel.setAssetAttachmentId(model.getAssetAttachmentId());
		soapModel.setFileName(model.getFileName());
		soapModel.setBundleSymbolicName(model.getBundleSymbolicName());
		soapModel.setBundleVersion(model.getBundleVersion());
		soapModel.setContextName(model.getContextName());
		soapModel.setPaclEnabled(model.getPaclEnabled());
		soapModel.setRelengHash(model.getRelengHash());
		soapModel.setPortalRestartRequired(model.getPortalRestartRequired());

		return soapModel;
	}

	public static AppPackagePluginSoap[] toSoapModels(AppPackagePlugin[] models) {
		AppPackagePluginSoap[] soapModels = new AppPackagePluginSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppPackagePluginSoap[][] toSoapModels(
		AppPackagePlugin[][] models) {
		AppPackagePluginSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppPackagePluginSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppPackagePluginSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppPackagePluginSoap[] toSoapModels(
		List<AppPackagePlugin> models) {
		List<AppPackagePluginSoap> soapModels = new ArrayList<AppPackagePluginSoap>(models.size());

		for (AppPackagePlugin model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppPackagePluginSoap[soapModels.size()]);
	}

	public AppPackagePluginSoap() {
	}

	public long getPrimaryKey() {
		return _appPackagePluginId;
	}

	public void setPrimaryKey(long pk) {
		setAppPackagePluginId(pk);
	}

	public long getAppPackagePluginId() {
		return _appPackagePluginId;
	}

	public void setAppPackagePluginId(long appPackagePluginId) {
		_appPackagePluginId = appPackagePluginId;
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

	public long getAppPackageId() {
		return _appPackageId;
	}

	public void setAppPackageId(long appPackageId) {
		_appPackageId = appPackageId;
	}

	public long getAssetAttachmentId() {
		return _assetAttachmentId;
	}

	public void setAssetAttachmentId(long assetAttachmentId) {
		_assetAttachmentId = assetAttachmentId;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public String getBundleSymbolicName() {
		return _bundleSymbolicName;
	}

	public void setBundleSymbolicName(String bundleSymbolicName) {
		_bundleSymbolicName = bundleSymbolicName;
	}

	public String getBundleVersion() {
		return _bundleVersion;
	}

	public void setBundleVersion(String bundleVersion) {
		_bundleVersion = bundleVersion;
	}

	public String getContextName() {
		return _contextName;
	}

	public void setContextName(String contextName) {
		_contextName = contextName;
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

	public String getRelengHash() {
		return _relengHash;
	}

	public void setRelengHash(String relengHash) {
		_relengHash = relengHash;
	}

	public boolean getPortalRestartRequired() {
		return _portalRestartRequired;
	}

	public boolean isPortalRestartRequired() {
		return _portalRestartRequired;
	}

	public void setPortalRestartRequired(boolean portalRestartRequired) {
		_portalRestartRequired = portalRestartRequired;
	}

	private long _appPackagePluginId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
	private long _appVersionId;
	private long _appPackageId;
	private long _assetAttachmentId;
	private String _fileName;
	private String _bundleSymbolicName;
	private String _bundleVersion;
	private String _contextName;
	private boolean _paclEnabled;
	private String _relengHash;
	private boolean _portalRestartRequired;
}