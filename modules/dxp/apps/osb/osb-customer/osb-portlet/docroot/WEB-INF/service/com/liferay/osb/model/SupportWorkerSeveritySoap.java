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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportWorkerSeverityServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.SupportWorkerSeverityServiceSoap
 * @generated
 */
public class SupportWorkerSeveritySoap implements Serializable {
	public static SupportWorkerSeveritySoap toSoapModel(
		SupportWorkerSeverity model) {
		SupportWorkerSeveritySoap soapModel = new SupportWorkerSeveritySoap();

		soapModel.setSupportWorkerSeverityId(model.getSupportWorkerSeverityId());
		soapModel.setSupportWorkerId(model.getSupportWorkerId());
		soapModel.setSeverity(model.getSeverity());

		return soapModel;
	}

	public static SupportWorkerSeveritySoap[] toSoapModels(
		SupportWorkerSeverity[] models) {
		SupportWorkerSeveritySoap[] soapModels = new SupportWorkerSeveritySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerSeveritySoap[][] toSoapModels(
		SupportWorkerSeverity[][] models) {
		SupportWorkerSeveritySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportWorkerSeveritySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportWorkerSeveritySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerSeveritySoap[] toSoapModels(
		List<SupportWorkerSeverity> models) {
		List<SupportWorkerSeveritySoap> soapModels = new ArrayList<SupportWorkerSeveritySoap>(models.size());

		for (SupportWorkerSeverity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportWorkerSeveritySoap[soapModels.size()]);
	}

	public SupportWorkerSeveritySoap() {
	}

	public long getPrimaryKey() {
		return _supportWorkerSeverityId;
	}

	public void setPrimaryKey(long pk) {
		setSupportWorkerSeverityId(pk);
	}

	public long getSupportWorkerSeverityId() {
		return _supportWorkerSeverityId;
	}

	public void setSupportWorkerSeverityId(long supportWorkerSeverityId) {
		_supportWorkerSeverityId = supportWorkerSeverityId;
	}

	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;
	}

	public int getSeverity() {
		return _severity;
	}

	public void setSeverity(int severity) {
		_severity = severity;
	}

	private long _supportWorkerSeverityId;
	private long _supportWorkerId;
	private int _severity;
}