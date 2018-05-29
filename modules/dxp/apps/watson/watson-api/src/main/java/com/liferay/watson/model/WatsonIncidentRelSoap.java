/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonIncidentRelSoap implements Serializable {
	public static WatsonIncidentRelSoap toSoapModel(WatsonIncidentRel model) {
		WatsonIncidentRelSoap soapModel = new WatsonIncidentRelSoap();

		soapModel.setWatsonIncidentRelId(model.getWatsonIncidentRelId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonIncidentId1(model.getWatsonIncidentId1());
		soapModel.setWatsonIncidentId2(model.getWatsonIncidentId2());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonIncidentRelSoap[] toSoapModels(
		WatsonIncidentRel[] models) {
		WatsonIncidentRelSoap[] soapModels = new WatsonIncidentRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentRelSoap[][] toSoapModels(
		WatsonIncidentRel[][] models) {
		WatsonIncidentRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonIncidentRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonIncidentRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonIncidentRelSoap[] toSoapModels(
		List<WatsonIncidentRel> models) {
		List<WatsonIncidentRelSoap> soapModels = new ArrayList<WatsonIncidentRelSoap>(models.size());

		for (WatsonIncidentRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonIncidentRelSoap[soapModels.size()]);
	}

	public WatsonIncidentRelSoap() {
	}

	public long getPrimaryKey() {
		return _watsonIncidentRelId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonIncidentRelId(pk);
	}

	public long getWatsonIncidentRelId() {
		return _watsonIncidentRelId;
	}

	public void setWatsonIncidentRelId(long watsonIncidentRelId) {
		_watsonIncidentRelId = watsonIncidentRelId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getWatsonIncidentId1() {
		return _watsonIncidentId1;
	}

	public void setWatsonIncidentId1(long watsonIncidentId1) {
		_watsonIncidentId1 = watsonIncidentId1;
	}

	public long getWatsonIncidentId2() {
		return _watsonIncidentId2;
	}

	public void setWatsonIncidentId2(long watsonIncidentId2) {
		_watsonIncidentId2 = watsonIncidentId2;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonIncidentRelId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonIncidentId1;
	private long _watsonIncidentId2;
	private String _type;
	private int _status;
}