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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.ContractAuditServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.ContractAuditServiceSoap
 * @generated
 */
@ProviderType
public class ContractAuditSoap implements Serializable {
	public static ContractAuditSoap toSoapModel(ContractAudit model) {
		ContractAuditSoap soapModel = new ContractAuditSoap();

		soapModel.setContractAuditId(model.getContractAuditId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setUserEmailAddress(model.getUserEmailAddress());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setContractEntryId(model.getContractEntryId());
		soapModel.setSignatoryClassNameId(model.getSignatoryClassNameId());
		soapModel.setSignatoryClassPK(model.getSignatoryClassPK());
		soapModel.setProductClassNameId(model.getProductClassNameId());
		soapModel.setProductClassPK(model.getProductClassPK());
		soapModel.setType(model.getType());
		soapModel.setLanguageId(model.getLanguageId());
		soapModel.setVersion(model.getVersion());

		return soapModel;
	}

	public static ContractAuditSoap[] toSoapModels(ContractAudit[] models) {
		ContractAuditSoap[] soapModels = new ContractAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContractAuditSoap[][] toSoapModels(ContractAudit[][] models) {
		ContractAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContractAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContractAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContractAuditSoap[] toSoapModels(List<ContractAudit> models) {
		List<ContractAuditSoap> soapModels = new ArrayList<ContractAuditSoap>(models.size());

		for (ContractAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContractAuditSoap[soapModels.size()]);
	}

	public ContractAuditSoap() {
	}

	public long getPrimaryKey() {
		return _contractAuditId;
	}

	public void setPrimaryKey(long pk) {
		setContractAuditId(pk);
	}

	public long getContractAuditId() {
		return _contractAuditId;
	}

	public void setContractAuditId(long contractAuditId) {
		_contractAuditId = contractAuditId;
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

	public String getUserEmailAddress() {
		return _userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		_userEmailAddress = userEmailAddress;
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

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;
	}

	public long getSignatoryClassNameId() {
		return _signatoryClassNameId;
	}

	public void setSignatoryClassNameId(long signatoryClassNameId) {
		_signatoryClassNameId = signatoryClassNameId;
	}

	public long getSignatoryClassPK() {
		return _signatoryClassPK;
	}

	public void setSignatoryClassPK(long signatoryClassPK) {
		_signatoryClassPK = signatoryClassPK;
	}

	public long getProductClassNameId() {
		return _productClassNameId;
	}

	public void setProductClassNameId(long productClassNameId) {
		_productClassNameId = productClassNameId;
	}

	public long getProductClassPK() {
		return _productClassPK;
	}

	public void setProductClassPK(long productClassPK) {
		_productClassPK = productClassPK;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	private long _contractAuditId;
	private long _userId;
	private String _userName;
	private String _userEmailAddress;
	private Date _createDate;
	private Date _modifiedDate;
	private long _contractEntryId;
	private long _signatoryClassNameId;
	private long _signatoryClassPK;
	private long _productClassNameId;
	private long _productClassPK;
	private String _type;
	private String _languageId;
	private int _version;
}