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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AuditEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.AuditEntryServiceSoap
 * @generated
 */
@ProviderType
public class AuditEntrySoap implements Serializable {
	public static AuditEntrySoap toSoapModel(AuditEntry model) {
		AuditEntrySoap soapModel = new AuditEntrySoap();

		soapModel.setAuditEntryId(model.getAuditEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setPreviousAuditEntryId(model.getPreviousAuditEntryId());
		soapModel.setAuditSetId(model.getAuditSetId());
		soapModel.setFieldClassNameId(model.getFieldClassNameId());
		soapModel.setFieldClassPK(model.getFieldClassPK());
		soapModel.setAction(model.getAction());
		soapModel.setField(model.getField());
		soapModel.setVisibility(model.getVisibility());
		soapModel.setOldLabel(model.getOldLabel());
		soapModel.setOldValue(model.getOldValue());
		soapModel.setNewLabel(model.getNewLabel());
		soapModel.setNewValue(model.getNewValue());
		soapModel.setDescription(model.getDescription());
		soapModel.setI18n(model.getI18n());

		return soapModel;
	}

	public static AuditEntrySoap[] toSoapModels(AuditEntry[] models) {
		AuditEntrySoap[] soapModels = new AuditEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditEntrySoap[][] toSoapModels(AuditEntry[][] models) {
		AuditEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditEntrySoap[] toSoapModels(List<AuditEntry> models) {
		List<AuditEntrySoap> soapModels = new ArrayList<AuditEntrySoap>(models.size());

		for (AuditEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditEntrySoap[soapModels.size()]);
	}

	public AuditEntrySoap() {
	}

	public long getPrimaryKey() {
		return _auditEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAuditEntryId(pk);
	}

	public long getAuditEntryId() {
		return _auditEntryId;
	}

	public void setAuditEntryId(long auditEntryId) {
		_auditEntryId = auditEntryId;
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

	public long getPreviousAuditEntryId() {
		return _previousAuditEntryId;
	}

	public void setPreviousAuditEntryId(long previousAuditEntryId) {
		_previousAuditEntryId = previousAuditEntryId;
	}

	public long getAuditSetId() {
		return _auditSetId;
	}

	public void setAuditSetId(long auditSetId) {
		_auditSetId = auditSetId;
	}

	public long getFieldClassNameId() {
		return _fieldClassNameId;
	}

	public void setFieldClassNameId(long fieldClassNameId) {
		_fieldClassNameId = fieldClassNameId;
	}

	public long getFieldClassPK() {
		return _fieldClassPK;
	}

	public void setFieldClassPK(long fieldClassPK) {
		_fieldClassPK = fieldClassPK;
	}

	public int getAction() {
		return _action;
	}

	public void setAction(int action) {
		_action = action;
	}

	public int getField() {
		return _field;
	}

	public void setField(int field) {
		_field = field;
	}

	public int getVisibility() {
		return _visibility;
	}

	public void setVisibility(int visibility) {
		_visibility = visibility;
	}

	public String getOldLabel() {
		return _oldLabel;
	}

	public void setOldLabel(String oldLabel) {
		_oldLabel = oldLabel;
	}

	public String getOldValue() {
		return _oldValue;
	}

	public void setOldValue(String oldValue) {
		_oldValue = oldValue;
	}

	public String getNewLabel() {
		return _newLabel;
	}

	public void setNewLabel(String newLabel) {
		_newLabel = newLabel;
	}

	public String getNewValue() {
		return _newValue;
	}

	public void setNewValue(String newValue) {
		_newValue = newValue;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public boolean getI18n() {
		return _i18n;
	}

	public boolean isI18n() {
		return _i18n;
	}

	public void setI18n(boolean i18n) {
		_i18n = i18n;
	}

	private long _auditEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private long _previousAuditEntryId;
	private long _auditSetId;
	private long _fieldClassNameId;
	private long _fieldClassPK;
	private int _action;
	private int _field;
	private int _visibility;
	private String _oldLabel;
	private String _oldValue;
	private String _newLabel;
	private String _newValue;
	private String _description;
	private boolean _i18n;
}