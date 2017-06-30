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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AuditActionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AuditActionServiceSoap
 * @generated
 */
public class AuditActionSoap implements Serializable {
	public static AuditActionSoap toSoapModel(AuditAction model) {
		AuditActionSoap soapModel = new AuditActionSoap();

		soapModel.setAuditActionId(model.getAuditActionId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setMappingClassPK(model.getMappingClassPK());
		soapModel.setAction(model.getAction());

		return soapModel;
	}

	public static AuditActionSoap[] toSoapModels(AuditAction[] models) {
		AuditActionSoap[] soapModels = new AuditActionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditActionSoap[][] toSoapModels(AuditAction[][] models) {
		AuditActionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditActionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditActionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditActionSoap[] toSoapModels(List<AuditAction> models) {
		List<AuditActionSoap> soapModels = new ArrayList<AuditActionSoap>(models.size());

		for (AuditAction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditActionSoap[soapModels.size()]);
	}

	public AuditActionSoap() {
	}

	public long getPrimaryKey() {
		return _auditActionId;
	}

	public void setPrimaryKey(long pk) {
		setAuditActionId(pk);
	}

	public long getAuditActionId() {
		return _auditActionId;
	}

	public void setAuditActionId(long auditActionId) {
		_auditActionId = auditActionId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public long getMappingClassPK() {
		return _mappingClassPK;
	}

	public void setMappingClassPK(long mappingClassPK) {
		_mappingClassPK = mappingClassPK;
	}

	public int getAction() {
		return _action;
	}

	public void setAction(int action) {
		_action = action;
	}

	private long _auditActionId;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _mappingClassPK;
	private int _action;
}