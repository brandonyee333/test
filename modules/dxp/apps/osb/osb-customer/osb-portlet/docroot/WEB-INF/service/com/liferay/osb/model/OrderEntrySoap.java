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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.OrderEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.OrderEntryServiceSoap
 * @generated
 */
@ProviderType
public class OrderEntrySoap implements Serializable {
	public static OrderEntrySoap toSoapModel(OrderEntry model) {
		OrderEntrySoap soapModel = new OrderEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setOrderEntryId(model.getOrderEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedUserId(model.getModifiedUserId());
		soapModel.setModifiedUserName(model.getModifiedUserName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setPurchaseOrderKey(model.getPurchaseOrderKey());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setProrated(model.getProrated());
		soapModel.setActualStartDate(model.getActualStartDate());
		soapModel.setRenewCount(model.getRenewCount());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setStatusMessage(model.getStatusMessage());

		return soapModel;
	}

	public static OrderEntrySoap[] toSoapModels(OrderEntry[] models) {
		OrderEntrySoap[] soapModels = new OrderEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrderEntrySoap[][] toSoapModels(OrderEntry[][] models) {
		OrderEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OrderEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrderEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrderEntrySoap[] toSoapModels(List<OrderEntry> models) {
		List<OrderEntrySoap> soapModels = new ArrayList<OrderEntrySoap>(models.size());

		for (OrderEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrderEntrySoap[soapModels.size()]);
	}

	public OrderEntrySoap() {
	}

	public long getPrimaryKey() {
		return _orderEntryId;
	}

	public void setPrimaryKey(long pk) {
		setOrderEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getOrderEntryId() {
		return _orderEntryId;
	}

	public void setOrderEntryId(long orderEntryId) {
		_orderEntryId = orderEntryId;
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

	public long getModifiedUserId() {
		return _modifiedUserId;
	}

	public void setModifiedUserId(long modifiedUserId) {
		_modifiedUserId = modifiedUserId;
	}

	public String getModifiedUserName() {
		return _modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		_modifiedUserName = modifiedUserName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public String getPurchaseOrderKey() {
		return _purchaseOrderKey;
	}

	public void setPurchaseOrderKey(String purchaseOrderKey) {
		_purchaseOrderKey = purchaseOrderKey;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public boolean getProrated() {
		return _prorated;
	}

	public boolean isProrated() {
		return _prorated;
	}

	public void setProrated(boolean prorated) {
		_prorated = prorated;
	}

	public Date getActualStartDate() {
		return _actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		_actualStartDate = actualStartDate;
	}

	public int getRenewCount() {
		return _renewCount;
	}

	public void setRenewCount(int renewCount) {
		_renewCount = renewCount;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getStatusMessage() {
		return _statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		_statusMessage = statusMessage;
	}

	private String _uuid;
	private long _orderEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _modifiedUserId;
	private String _modifiedUserName;
	private Date _modifiedDate;
	private long _accountEntryId;
	private String _purchaseOrderKey;
	private Date _startDate;
	private boolean _prorated;
	private Date _actualStartDate;
	private int _renewCount;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _statusMessage;
}