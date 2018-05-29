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
public class WatsonListTypeRelAuditSoap implements Serializable {
	public static WatsonListTypeRelAuditSoap toSoapModel(
		WatsonListTypeRelAudit model) {
		WatsonListTypeRelAuditSoap soapModel = new WatsonListTypeRelAuditSoap();

		soapModel.setWatsonListTypeRelAuditId(model.getWatsonListTypeRelAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setWatsonListTypeId(model.getWatsonListTypeId());
		soapModel.setWatsonListTypeRelId(model.getWatsonListTypeRelId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setPrimary(model.isPrimary());
		soapModel.setValue(model.getValue());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonListTypeRelAuditSoap[] toSoapModels(
		WatsonListTypeRelAudit[] models) {
		WatsonListTypeRelAuditSoap[] soapModels = new WatsonListTypeRelAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonListTypeRelAuditSoap[][] toSoapModels(
		WatsonListTypeRelAudit[][] models) {
		WatsonListTypeRelAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonListTypeRelAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonListTypeRelAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonListTypeRelAuditSoap[] toSoapModels(
		List<WatsonListTypeRelAudit> models) {
		List<WatsonListTypeRelAuditSoap> soapModels = new ArrayList<WatsonListTypeRelAuditSoap>(models.size());

		for (WatsonListTypeRelAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonListTypeRelAuditSoap[soapModels.size()]);
	}

	public WatsonListTypeRelAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonListTypeRelAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonListTypeRelAuditId(pk);
	}

	public long getWatsonListTypeRelAuditId() {
		return _watsonListTypeRelAuditId;
	}

	public void setWatsonListTypeRelAuditId(long watsonListTypeRelAuditId) {
		_watsonListTypeRelAuditId = watsonListTypeRelAuditId;
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

	public long getWatsonListTypeId() {
		return _watsonListTypeId;
	}

	public void setWatsonListTypeId(long watsonListTypeId) {
		_watsonListTypeId = watsonListTypeId;
	}

	public long getWatsonListTypeRelId() {
		return _watsonListTypeRelId;
	}

	public void setWatsonListTypeRelId(long watsonListTypeRelId) {
		_watsonListTypeRelId = watsonListTypeRelId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public boolean getPrimary() {
		return _primary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public void setPrimary(boolean primary) {
		_primary = primary;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
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

	private long _watsonListTypeRelAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _watsonListTypeId;
	private long _watsonListTypeRelId;
	private long _classNameId;
	private long _classPK;
	private boolean _primary;
	private String _value;
	private String _type;
	private int _status;
}