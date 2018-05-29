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
public class WatsonResourceAuditSoap implements Serializable {
	public static WatsonResourceAuditSoap toSoapModel(WatsonResourceAudit model) {
		WatsonResourceAuditSoap soapModel = new WatsonResourceAuditSoap();

		soapModel.setWatsonResourceAuditId(model.getWatsonResourceAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOriginalWatsonResourceId(model.getOriginalWatsonResourceId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setWatsonResourceId(model.getWatsonResourceId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonResourceAuditSoap[] toSoapModels(
		WatsonResourceAudit[] models) {
		WatsonResourceAuditSoap[] soapModels = new WatsonResourceAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonResourceAuditSoap[][] toSoapModels(
		WatsonResourceAudit[][] models) {
		WatsonResourceAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonResourceAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonResourceAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonResourceAuditSoap[] toSoapModels(
		List<WatsonResourceAudit> models) {
		List<WatsonResourceAuditSoap> soapModels = new ArrayList<WatsonResourceAuditSoap>(models.size());

		for (WatsonResourceAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonResourceAuditSoap[soapModels.size()]);
	}

	public WatsonResourceAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonResourceAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonResourceAuditId(pk);
	}

	public long getWatsonResourceAuditId() {
		return _watsonResourceAuditId;
	}

	public void setWatsonResourceAuditId(long watsonResourceAuditId) {
		_watsonResourceAuditId = watsonResourceAuditId;
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

	public long getOriginalWatsonResourceId() {
		return _originalWatsonResourceId;
	}

	public void setOriginalWatsonResourceId(long originalWatsonResourceId) {
		_originalWatsonResourceId = originalWatsonResourceId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
	}

	public long getWatsonResourceId() {
		return _watsonResourceId;
	}

	public void setWatsonResourceId(long watsonResourceId) {
		_watsonResourceId = watsonResourceId;
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

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonResourceAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _originalWatsonResourceId;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private long _watsonResourceId;
	private String _name;
	private String _description;
	private String _imagePayload;
	private int _status;
}