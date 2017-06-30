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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.ContractEntryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.ContractEntryServiceSoap
 * @generated
 */
public class ContractEntrySoap implements Serializable {
	public static ContractEntrySoap toSoapModel(ContractEntry model) {
		ContractEntrySoap soapModel = new ContractEntrySoap();

		soapModel.setContractEntryId(model.getContractEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setType(model.getType());
		soapModel.setVersion(model.getVersion());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static ContractEntrySoap[] toSoapModels(ContractEntry[] models) {
		ContractEntrySoap[] soapModels = new ContractEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ContractEntrySoap[][] toSoapModels(ContractEntry[][] models) {
		ContractEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ContractEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ContractEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ContractEntrySoap[] toSoapModels(List<ContractEntry> models) {
		List<ContractEntrySoap> soapModels = new ArrayList<ContractEntrySoap>(models.size());

		for (ContractEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ContractEntrySoap[soapModels.size()]);
	}

	public ContractEntrySoap() {
	}

	public long getPrimaryKey() {
		return _contractEntryId;
	}

	public void setPrimaryKey(long pk) {
		setContractEntryId(pk);
	}

	public long getContractEntryId() {
		return _contractEntryId;
	}

	public void setContractEntryId(long contractEntryId) {
		_contractEntryId = contractEntryId;
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

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _contractEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private int _version;
	private String _content;
}