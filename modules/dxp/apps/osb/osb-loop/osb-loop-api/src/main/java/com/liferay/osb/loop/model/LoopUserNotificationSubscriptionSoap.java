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
public class LoopUserNotificationSubscriptionSoap implements Serializable {
	public static LoopUserNotificationSubscriptionSoap toSoapModel(
		LoopUserNotificationSubscription model) {
		LoopUserNotificationSubscriptionSoap soapModel = new LoopUserNotificationSubscriptionSoap();

		soapModel.setLoopUserNotificationSubscriptionId(model.getLoopUserNotificationSubscriptionId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setDeliveryType(model.getDeliveryType());

		return soapModel;
	}

	public static LoopUserNotificationSubscriptionSoap[] toSoapModels(
		LoopUserNotificationSubscription[] models) {
		LoopUserNotificationSubscriptionSoap[] soapModels = new LoopUserNotificationSubscriptionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopUserNotificationSubscriptionSoap[][] toSoapModels(
		LoopUserNotificationSubscription[][] models) {
		LoopUserNotificationSubscriptionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopUserNotificationSubscriptionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopUserNotificationSubscriptionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopUserNotificationSubscriptionSoap[] toSoapModels(
		List<LoopUserNotificationSubscription> models) {
		List<LoopUserNotificationSubscriptionSoap> soapModels = new ArrayList<LoopUserNotificationSubscriptionSoap>(models.size());

		for (LoopUserNotificationSubscription model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopUserNotificationSubscriptionSoap[soapModels.size()]);
	}

	public LoopUserNotificationSubscriptionSoap() {
	}

	public long getPrimaryKey() {
		return _loopUserNotificationSubscriptionId;
	}

	public void setPrimaryKey(long pk) {
		setLoopUserNotificationSubscriptionId(pk);
	}

	public long getLoopUserNotificationSubscriptionId() {
		return _loopUserNotificationSubscriptionId;
	}

	public void setLoopUserNotificationSubscriptionId(
		long loopUserNotificationSubscriptionId) {
		_loopUserNotificationSubscriptionId = loopUserNotificationSubscriptionId;
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
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

	private long _loopUserNotificationSubscriptionId;
	private long _loopPersonId;
	private long _classNameId;
	private long _classPK;
	private int _deliveryType;
}