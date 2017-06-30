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
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class AppAuditSoap implements Serializable {
	public static AppAuditSoap toSoapModel(AppAudit model) {
		AppAuditSoap soapModel = new AppAuditSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAppAuditId(model.getAppAuditId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static AppAuditSoap[] toSoapModels(AppAudit[] models) {
		AppAuditSoap[] soapModels = new AppAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppAuditSoap[][] toSoapModels(AppAudit[][] models) {
		AppAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppAuditSoap[] toSoapModels(List<AppAudit> models) {
		List<AppAuditSoap> soapModels = new ArrayList<AppAuditSoap>(models.size());

		for (AppAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppAuditSoap[soapModels.size()]);
	}

	public AppAuditSoap() {
	}

	public long getPrimaryKey() {
		return _appAuditId;
	}

	public void setPrimaryKey(long pk) {
		setAppAuditId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAppAuditId() {
		return _appAuditId;
	}

	public void setAppAuditId(long appAuditId) {
		_appAuditId = appAuditId;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _appAuditId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _appEntryId;
	private long _appVersionId;
	private int _status;
}