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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportWorkerAccountTierServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.SupportWorkerAccountTierServiceSoap
 * @generated
 */
public class SupportWorkerAccountTierSoap implements Serializable {
	public static SupportWorkerAccountTierSoap toSoapModel(
		SupportWorkerAccountTier model) {
		SupportWorkerAccountTierSoap soapModel = new SupportWorkerAccountTierSoap();

		soapModel.setSupportWorkerAccountTierId(model.getSupportWorkerAccountTierId());
		soapModel.setSupportWorkerId(model.getSupportWorkerId());
		soapModel.setAccountTier(model.getAccountTier());

		return soapModel;
	}

	public static SupportWorkerAccountTierSoap[] toSoapModels(
		SupportWorkerAccountTier[] models) {
		SupportWorkerAccountTierSoap[] soapModels = new SupportWorkerAccountTierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerAccountTierSoap[][] toSoapModels(
		SupportWorkerAccountTier[][] models) {
		SupportWorkerAccountTierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportWorkerAccountTierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportWorkerAccountTierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerAccountTierSoap[] toSoapModels(
		List<SupportWorkerAccountTier> models) {
		List<SupportWorkerAccountTierSoap> soapModels = new ArrayList<SupportWorkerAccountTierSoap>(models.size());

		for (SupportWorkerAccountTier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportWorkerAccountTierSoap[soapModels.size()]);
	}

	public SupportWorkerAccountTierSoap() {
	}

	public long getPrimaryKey() {
		return _supportWorkerAccountTierId;
	}

	public void setPrimaryKey(long pk) {
		setSupportWorkerAccountTierId(pk);
	}

	public long getSupportWorkerAccountTierId() {
		return _supportWorkerAccountTierId;
	}

	public void setSupportWorkerAccountTierId(long supportWorkerAccountTierId) {
		_supportWorkerAccountTierId = supportWorkerAccountTierId;
	}

	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;
	}

	public int getAccountTier() {
		return _accountTier;
	}

	public void setAccountTier(int accountTier) {
		_accountTier = accountTier;
	}

	private long _supportWorkerAccountTierId;
	private long _supportWorkerId;
	private int _accountTier;
}