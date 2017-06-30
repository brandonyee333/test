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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppEntryRelServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppEntryRelServiceSoap
 * @generated
 */
public class AppEntryRelSoap implements Serializable {
	public static AppEntryRelSoap toSoapModel(AppEntryRel model) {
		AppEntryRelSoap soapModel = new AppEntryRelSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAppEntryRelId(model.getAppEntryRelId());
		soapModel.setAppEntryId1(model.getAppEntryId1());
		soapModel.setAppEntryId2(model.getAppEntryId2());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static AppEntryRelSoap[] toSoapModels(AppEntryRel[] models) {
		AppEntryRelSoap[] soapModels = new AppEntryRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppEntryRelSoap[][] toSoapModels(AppEntryRel[][] models) {
		AppEntryRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppEntryRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppEntryRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppEntryRelSoap[] toSoapModels(List<AppEntryRel> models) {
		List<AppEntryRelSoap> soapModels = new ArrayList<AppEntryRelSoap>(models.size());

		for (AppEntryRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppEntryRelSoap[soapModels.size()]);
	}

	public AppEntryRelSoap() {
	}

	public long getPrimaryKey() {
		return _appEntryRelId;
	}

	public void setPrimaryKey(long pk) {
		setAppEntryRelId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAppEntryRelId() {
		return _appEntryRelId;
	}

	public void setAppEntryRelId(long appEntryRelId) {
		_appEntryRelId = appEntryRelId;
	}

	public long getAppEntryId1() {
		return _appEntryId1;
	}

	public void setAppEntryId1(long appEntryId1) {
		_appEntryId1 = appEntryId1;
	}

	public long getAppEntryId2() {
		return _appEntryId2;
	}

	public void setAppEntryId2(long appEntryId2) {
		_appEntryId2 = appEntryId2;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private String _uuid;
	private long _appEntryRelId;
	private long _appEntryId1;
	private long _appEntryId2;
	private int _type;
}