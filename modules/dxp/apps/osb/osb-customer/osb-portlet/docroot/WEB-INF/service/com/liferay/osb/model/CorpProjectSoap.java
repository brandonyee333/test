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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CorpProjectServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.CorpProjectServiceSoap
 * @generated
 */
@ProviderType
public class CorpProjectSoap implements Serializable {
	public static CorpProjectSoap toSoapModel(CorpProject model) {
		CorpProjectSoap soapModel = new CorpProjectSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setCorpProjectId(model.getCorpProjectId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDossieraProjectKey(model.getDossieraProjectKey());
		soapModel.setSalesforceProjectKey(model.getSalesforceProjectKey());
		soapModel.setName(model.getName());
		soapModel.setOrganizationId(model.getOrganizationId());

		return soapModel;
	}

	public static CorpProjectSoap[] toSoapModels(CorpProject[] models) {
		CorpProjectSoap[] soapModels = new CorpProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CorpProjectSoap[][] toSoapModels(CorpProject[][] models) {
		CorpProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CorpProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CorpProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CorpProjectSoap[] toSoapModels(List<CorpProject> models) {
		List<CorpProjectSoap> soapModels = new ArrayList<CorpProjectSoap>(models.size());

		for (CorpProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CorpProjectSoap[soapModels.size()]);
	}

	public CorpProjectSoap() {
	}

	public long getPrimaryKey() {
		return _corpProjectId;
	}

	public void setPrimaryKey(long pk) {
		setCorpProjectId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
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

	public String getDossieraProjectKey() {
		return _dossieraProjectKey;
	}

	public void setDossieraProjectKey(String dossieraProjectKey) {
		_dossieraProjectKey = dossieraProjectKey;
	}

	public String getSalesforceProjectKey() {
		return _salesforceProjectKey;
	}

	public void setSalesforceProjectKey(String salesforceProjectKey) {
		_salesforceProjectKey = salesforceProjectKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	private String _uuid;
	private long _corpProjectId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _dossieraProjectKey;
	private String _salesforceProjectKey;
	private String _name;
	private long _organizationId;
}