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

package com.liferay.osb.customer.metrics.sync.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SyncStateSoap implements Serializable {
	public static SyncStateSoap toSoapModel(SyncState model) {
		SyncStateSoap soapModel = new SyncStateSoap();

		soapModel.setSyncStateId(model.getSyncStateId());
		soapModel.setModelName(model.getModelName());
		soapModel.setLastRunTime(model.getLastRunTime());

		return soapModel;
	}

	public static SyncStateSoap[] toSoapModels(SyncState[] models) {
		SyncStateSoap[] soapModels = new SyncStateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncStateSoap[][] toSoapModels(SyncState[][] models) {
		SyncStateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncStateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncStateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncStateSoap[] toSoapModels(List<SyncState> models) {
		List<SyncStateSoap> soapModels = new ArrayList<SyncStateSoap>(models.size());

		for (SyncState model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncStateSoap[soapModels.size()]);
	}

	public SyncStateSoap() {
	}

	public long getPrimaryKey() {
		return _syncStateId;
	}

	public void setPrimaryKey(long pk) {
		setSyncStateId(pk);
	}

	public long getSyncStateId() {
		return _syncStateId;
	}

	public void setSyncStateId(long syncStateId) {
		_syncStateId = syncStateId;
	}

	public String getModelName() {
		return _modelName;
	}

	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	public long getLastRunTime() {
		return _lastRunTime;
	}

	public void setLastRunTime(long lastRunTime) {
		_lastRunTime = lastRunTime;
	}

	private long _syncStateId;
	private String _modelName;
	private long _lastRunTime;
}