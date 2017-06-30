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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppFlagServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppFlagServiceSoap
 * @generated
 */
public class AppFlagSoap implements Serializable {
	public static AppFlagSoap toSoapModel(AppFlag model) {
		AppFlagSoap soapModel = new AppFlagSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAppFlagId(model.getAppFlagId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static AppFlagSoap[] toSoapModels(AppFlag[] models) {
		AppFlagSoap[] soapModels = new AppFlagSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppFlagSoap[][] toSoapModels(AppFlag[][] models) {
		AppFlagSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppFlagSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppFlagSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppFlagSoap[] toSoapModels(List<AppFlag> models) {
		List<AppFlagSoap> soapModels = new ArrayList<AppFlagSoap>(models.size());

		for (AppFlag model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppFlagSoap[soapModels.size()]);
	}

	public AppFlagSoap() {
	}

	public long getPrimaryKey() {
		return _appFlagId;
	}

	public void setPrimaryKey(long pk) {
		setAppFlagId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAppFlagId() {
		return _appFlagId;
	}

	public void setAppFlagId(long appFlagId) {
		_appFlagId = appFlagId;
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

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private String _uuid;
	private long _appFlagId;
	private Date _createDate;
	private long _appEntryId;
	private long _appVersionId;
	private int _type;
}