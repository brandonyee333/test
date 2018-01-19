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

package com.liferay.pulpo.connector.de.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ConnectorTransactionSoap implements Serializable {
	public static ConnectorTransactionSoap toSoapModel(
		ConnectorTransaction model) {
		ConnectorTransactionSoap soapModel = new ConnectorTransactionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setConnectorTransactionId(model.getConnectorTransactionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setOperation(model.getOperation());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static ConnectorTransactionSoap[] toSoapModels(
		ConnectorTransaction[] models) {
		ConnectorTransactionSoap[] soapModels = new ConnectorTransactionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ConnectorTransactionSoap[][] toSoapModels(
		ConnectorTransaction[][] models) {
		ConnectorTransactionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ConnectorTransactionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ConnectorTransactionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ConnectorTransactionSoap[] toSoapModels(
		List<ConnectorTransaction> models) {
		List<ConnectorTransactionSoap> soapModels = new ArrayList<ConnectorTransactionSoap>(models.size());

		for (ConnectorTransaction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ConnectorTransactionSoap[soapModels.size()]);
	}

	public ConnectorTransactionSoap() {
	}

	public long getPrimaryKey() {
		return _connectorTransactionId;
	}

	public void setPrimaryKey(long pk) {
		setConnectorTransactionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getConnectorTransactionId() {
		return _connectorTransactionId;
	}

	public void setConnectorTransactionId(long connectorTransactionId) {
		_connectorTransactionId = connectorTransactionId;
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

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	private String _uuid;
	private long _connectorTransactionId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _operation;
	private String _status;
}