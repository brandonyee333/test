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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportResponseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.SupportResponseServiceSoap
 * @generated
 */
@ProviderType
public class SupportResponseSoap implements Serializable {
	public static SupportResponseSoap toSoapModel(SupportResponse model) {
		SupportResponseSoap soapModel = new SupportResponseSoap();

		soapModel.setSupportResponseId(model.getSupportResponseId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setSupportLevel(model.getSupportLevel());
		soapModel.setSeverity1Response(model.getSeverity1Response());
		soapModel.setSeverity1Resolution(model.getSeverity1Resolution());
		soapModel.setSeverity2Response(model.getSeverity2Response());
		soapModel.setSeverity2Resolution(model.getSeverity2Resolution());
		soapModel.setSeverity3Response(model.getSeverity3Response());
		soapModel.setSeverity3Resolution(model.getSeverity3Resolution());

		return soapModel;
	}

	public static SupportResponseSoap[] toSoapModels(SupportResponse[] models) {
		SupportResponseSoap[] soapModels = new SupportResponseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportResponseSoap[][] toSoapModels(
		SupportResponse[][] models) {
		SupportResponseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportResponseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportResponseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportResponseSoap[] toSoapModels(
		List<SupportResponse> models) {
		List<SupportResponseSoap> soapModels = new ArrayList<SupportResponseSoap>(models.size());

		for (SupportResponse model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportResponseSoap[soapModels.size()]);
	}

	public SupportResponseSoap() {
	}

	public long getPrimaryKey() {
		return _supportResponseId;
	}

	public void setPrimaryKey(long pk) {
		setSupportResponseId(pk);
	}

	public long getSupportResponseId() {
		return _supportResponseId;
	}

	public void setSupportResponseId(long supportResponseId) {
		_supportResponseId = supportResponseId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getSupportLevel() {
		return _supportLevel;
	}

	public void setSupportLevel(int supportLevel) {
		_supportLevel = supportLevel;
	}

	public int getSeverity1Response() {
		return _severity1Response;
	}

	public void setSeverity1Response(int severity1Response) {
		_severity1Response = severity1Response;
	}

	public int getSeverity1Resolution() {
		return _severity1Resolution;
	}

	public void setSeverity1Resolution(int severity1Resolution) {
		_severity1Resolution = severity1Resolution;
	}

	public int getSeverity2Response() {
		return _severity2Response;
	}

	public void setSeverity2Response(int severity2Response) {
		_severity2Response = severity2Response;
	}

	public int getSeverity2Resolution() {
		return _severity2Resolution;
	}

	public void setSeverity2Resolution(int severity2Resolution) {
		_severity2Resolution = severity2Resolution;
	}

	public int getSeverity3Response() {
		return _severity3Response;
	}

	public void setSeverity3Response(int severity3Response) {
		_severity3Response = severity3Response;
	}

	public int getSeverity3Resolution() {
		return _severity3Resolution;
	}

	public void setSeverity3Resolution(int severity3Resolution) {
		_severity3Resolution = severity3Resolution;
	}

	private long _supportResponseId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private int _supportLevel;
	private int _severity1Response;
	private int _severity1Resolution;
	private int _severity2Response;
	private int _severity2Resolution;
	private int _severity3Response;
	private int _severity3Resolution;
}