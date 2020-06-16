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

package com.liferay.osb.loop.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopAuditEntrySoap implements Serializable {

	public static LoopAuditEntrySoap toSoapModel(LoopAuditEntry model) {
		LoopAuditEntrySoap soapModel = new LoopAuditEntrySoap();

		soapModel.setLoopAuditEntryId(model.getLoopAuditEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static LoopAuditEntrySoap[] toSoapModels(LoopAuditEntry[] models) {
		LoopAuditEntrySoap[] soapModels = new LoopAuditEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopAuditEntrySoap[][] toSoapModels(
		LoopAuditEntry[][] models) {

		LoopAuditEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LoopAuditEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopAuditEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopAuditEntrySoap[] toSoapModels(
		List<LoopAuditEntry> models) {

		List<LoopAuditEntrySoap> soapModels = new ArrayList<LoopAuditEntrySoap>(
			models.size());

		for (LoopAuditEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopAuditEntrySoap[soapModels.size()]);
	}

	public LoopAuditEntrySoap() {
	}

	public long getPrimaryKey() {
		return _loopAuditEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLoopAuditEntryId(pk);
	}

	public long getLoopAuditEntryId() {
		return _loopAuditEntryId;
	}

	public void setLoopAuditEntryId(long loopAuditEntryId) {
		_loopAuditEntryId = loopAuditEntryId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _loopAuditEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _name;

}