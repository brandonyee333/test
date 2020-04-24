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

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class LoopUserNotificationRecordSoap implements Serializable {
	public static LoopUserNotificationRecordSoap toSoapModel(
		LoopUserNotificationRecord model) {
		LoopUserNotificationRecordSoap soapModel = new LoopUserNotificationRecordSoap();

		soapModel.setLoopUserNotificationRecordId(model.getLoopUserNotificationRecordId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateTime(model.getCreateTime());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setDeliveryType(model.getDeliveryType());

		return soapModel;
	}

	public static LoopUserNotificationRecordSoap[] toSoapModels(
		LoopUserNotificationRecord[] models) {
		LoopUserNotificationRecordSoap[] soapModels = new LoopUserNotificationRecordSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopUserNotificationRecordSoap[][] toSoapModels(
		LoopUserNotificationRecord[][] models) {
		LoopUserNotificationRecordSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopUserNotificationRecordSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopUserNotificationRecordSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopUserNotificationRecordSoap[] toSoapModels(
		List<LoopUserNotificationRecord> models) {
		List<LoopUserNotificationRecordSoap> soapModels = new ArrayList<LoopUserNotificationRecordSoap>(models.size());

		for (LoopUserNotificationRecord model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopUserNotificationRecordSoap[soapModels.size()]);
	}

	public LoopUserNotificationRecordSoap() {
	}

	public long getPrimaryKey() {
		return _loopUserNotificationRecordId;
	}

	public void setPrimaryKey(long pk) {
		setLoopUserNotificationRecordId(pk);
	}

	public long getLoopUserNotificationRecordId() {
		return _loopUserNotificationRecordId;
	}

	public void setLoopUserNotificationRecordId(
		long loopUserNotificationRecordId) {
		_loopUserNotificationRecordId = loopUserNotificationRecordId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getCreateTime() {
		return _createTime;
	}

	public void setCreateTime(long createTime) {
		_createTime = createTime;
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

	public int getDeliveryType() {
		return _deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		_deliveryType = deliveryType;
	}

	private long _loopUserNotificationRecordId;
	private long _userId;
	private long _createTime;
	private long _classNameId;
	private long _classPK;
	private int _deliveryType;
}