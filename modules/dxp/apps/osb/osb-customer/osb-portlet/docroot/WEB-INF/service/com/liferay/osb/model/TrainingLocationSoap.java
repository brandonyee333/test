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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingLocationServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingLocationServiceSoap
 * @generated
 */
public class TrainingLocationSoap implements Serializable {
	public static TrainingLocationSoap toSoapModel(TrainingLocation model) {
		TrainingLocationSoap soapModel = new TrainingLocationSoap();

		soapModel.setTrainingLocationId(model.getTrainingLocationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setAddressId(model.getAddressId());

		return soapModel;
	}

	public static TrainingLocationSoap[] toSoapModels(TrainingLocation[] models) {
		TrainingLocationSoap[] soapModels = new TrainingLocationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingLocationSoap[][] toSoapModels(
		TrainingLocation[][] models) {
		TrainingLocationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingLocationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingLocationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingLocationSoap[] toSoapModels(
		List<TrainingLocation> models) {
		List<TrainingLocationSoap> soapModels = new ArrayList<TrainingLocationSoap>(models.size());

		for (TrainingLocation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingLocationSoap[soapModels.size()]);
	}

	public TrainingLocationSoap() {
	}

	public long getPrimaryKey() {
		return _trainingLocationId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingLocationId(pk);
	}

	public long getTrainingLocationId() {
		return _trainingLocationId;
	}

	public void setTrainingLocationId(long trainingLocationId) {
		_trainingLocationId = trainingLocationId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;
	}

	private long _trainingLocationId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private long _addressId;
}