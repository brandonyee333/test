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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportWorkerComponentServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.SupportWorkerComponentServiceSoap
 * @generated
 */
public class SupportWorkerComponentSoap implements Serializable {
	public static SupportWorkerComponentSoap toSoapModel(
		SupportWorkerComponent model) {
		SupportWorkerComponentSoap soapModel = new SupportWorkerComponentSoap();

		soapModel.setSupportWorkerComponentId(model.getSupportWorkerComponentId());
		soapModel.setSupportWorkerId(model.getSupportWorkerId());
		soapModel.setComponent(model.getComponent());

		return soapModel;
	}

	public static SupportWorkerComponentSoap[] toSoapModels(
		SupportWorkerComponent[] models) {
		SupportWorkerComponentSoap[] soapModels = new SupportWorkerComponentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerComponentSoap[][] toSoapModels(
		SupportWorkerComponent[][] models) {
		SupportWorkerComponentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportWorkerComponentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportWorkerComponentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerComponentSoap[] toSoapModels(
		List<SupportWorkerComponent> models) {
		List<SupportWorkerComponentSoap> soapModels = new ArrayList<SupportWorkerComponentSoap>(models.size());

		for (SupportWorkerComponent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportWorkerComponentSoap[soapModels.size()]);
	}

	public SupportWorkerComponentSoap() {
	}

	public long getPrimaryKey() {
		return _supportWorkerComponentId;
	}

	public void setPrimaryKey(long pk) {
		setSupportWorkerComponentId(pk);
	}

	public long getSupportWorkerComponentId() {
		return _supportWorkerComponentId;
	}

	public void setSupportWorkerComponentId(long supportWorkerComponentId) {
		_supportWorkerComponentId = supportWorkerComponentId;
	}

	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;
	}

	public int getComponent() {
		return _component;
	}

	public void setComponent(int component) {
		_component = component;
	}

	private long _supportWorkerComponentId;
	private long _supportWorkerId;
	private int _component;
}