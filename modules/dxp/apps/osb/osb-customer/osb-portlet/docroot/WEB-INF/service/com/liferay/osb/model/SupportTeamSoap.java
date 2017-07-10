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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportTeamServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.SupportTeamServiceSoap
 * @generated
 */
@ProviderType
public class SupportTeamSoap implements Serializable {
	public static SupportTeamSoap toSoapModel(SupportTeam model) {
		SupportTeamSoap soapModel = new SupportTeamSoap();

		soapModel.setSupportTeamId(model.getSupportTeamId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentSupportTeamId(model.getParentSupportTeamId());
		soapModel.setSupportLaborId(model.getSupportLaborId());
		soapModel.setLocationSupportRegionId(model.getLocationSupportRegionId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());
		soapModel.setAssignedWork(model.getAssignedWork());
		soapModel.setMaxWork(model.getMaxWork());

		return soapModel;
	}

	public static SupportTeamSoap[] toSoapModels(SupportTeam[] models) {
		SupportTeamSoap[] soapModels = new SupportTeamSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportTeamSoap[][] toSoapModels(SupportTeam[][] models) {
		SupportTeamSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportTeamSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportTeamSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportTeamSoap[] toSoapModels(List<SupportTeam> models) {
		List<SupportTeamSoap> soapModels = new ArrayList<SupportTeamSoap>(models.size());

		for (SupportTeam model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportTeamSoap[soapModels.size()]);
	}

	public SupportTeamSoap() {
	}

	public long getPrimaryKey() {
		return _supportTeamId;
	}

	public void setPrimaryKey(long pk) {
		setSupportTeamId(pk);
	}

	public long getSupportTeamId() {
		return _supportTeamId;
	}

	public void setSupportTeamId(long supportTeamId) {
		_supportTeamId = supportTeamId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getParentSupportTeamId() {
		return _parentSupportTeamId;
	}

	public void setParentSupportTeamId(long parentSupportTeamId) {
		_parentSupportTeamId = parentSupportTeamId;
	}

	public long getSupportLaborId() {
		return _supportLaborId;
	}

	public void setSupportLaborId(long supportLaborId) {
		_supportLaborId = supportLaborId;
	}

	public long getLocationSupportRegionId() {
		return _locationSupportRegionId;
	}

	public void setLocationSupportRegionId(long locationSupportRegionId) {
		_locationSupportRegionId = locationSupportRegionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public double getAssignedWork() {
		return _assignedWork;
	}

	public void setAssignedWork(double assignedWork) {
		_assignedWork = assignedWork;
	}

	public double getMaxWork() {
		return _maxWork;
	}

	public void setMaxWork(double maxWork) {
		_maxWork = maxWork;
	}

	private long _supportTeamId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentSupportTeamId;
	private long _supportLaborId;
	private long _locationSupportRegionId;
	private String _name;
	private String _description;
	private int _type;
	private double _assignedWork;
	private double _maxWork;
}