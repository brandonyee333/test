/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
		soapModel.setModel(model.getModel());
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

	public String getModel() {
		return _model;
	}

	public void setModel(String model) {
		_model = model;
	}

	public long getLastRunTime() {
		return _lastRunTime;
	}

	public void setLastRunTime(long lastRunTime) {
		_lastRunTime = lastRunTime;
	}

	private long _syncStateId;
	private String _model;
	private long _lastRunTime;
}