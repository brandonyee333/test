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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.PartnerWorkerServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.PartnerWorkerServiceSoap
 * @generated
 */
@ProviderType
public class PartnerWorkerSoap implements Serializable {
	public static PartnerWorkerSoap toSoapModel(PartnerWorker model) {
		PartnerWorkerSoap soapModel = new PartnerWorkerSoap();

		soapModel.setPartnerWorkerId(model.getPartnerWorkerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPartnerEntryId(model.getPartnerEntryId());
		soapModel.setRole(model.getRole());

		return soapModel;
	}

	public static PartnerWorkerSoap[] toSoapModels(PartnerWorker[] models) {
		PartnerWorkerSoap[] soapModels = new PartnerWorkerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PartnerWorkerSoap[][] toSoapModels(PartnerWorker[][] models) {
		PartnerWorkerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PartnerWorkerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PartnerWorkerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PartnerWorkerSoap[] toSoapModels(List<PartnerWorker> models) {
		List<PartnerWorkerSoap> soapModels = new ArrayList<PartnerWorkerSoap>(models.size());

		for (PartnerWorker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PartnerWorkerSoap[soapModels.size()]);
	}

	public PartnerWorkerSoap() {
	}

	public long getPrimaryKey() {
		return _partnerWorkerId;
	}

	public void setPrimaryKey(long pk) {
		setPartnerWorkerId(pk);
	}

	public long getPartnerWorkerId() {
		return _partnerWorkerId;
	}

	public void setPartnerWorkerId(long partnerWorkerId) {
		_partnerWorkerId = partnerWorkerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	private long _partnerWorkerId;
	private long _userId;
	private long _partnerEntryId;
	private int _role;
}