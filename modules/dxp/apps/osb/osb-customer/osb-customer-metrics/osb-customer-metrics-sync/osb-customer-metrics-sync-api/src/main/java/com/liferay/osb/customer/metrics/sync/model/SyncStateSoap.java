/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
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
		List<SyncStateSoap> soapModels = new ArrayList<SyncStateSoap>(
			models.size());

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