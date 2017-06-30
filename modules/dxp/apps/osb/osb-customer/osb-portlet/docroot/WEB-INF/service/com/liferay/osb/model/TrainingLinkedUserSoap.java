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
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class TrainingLinkedUserSoap implements Serializable {
	public static TrainingLinkedUserSoap toSoapModel(TrainingLinkedUser model) {
		TrainingLinkedUserSoap soapModel = new TrainingLinkedUserSoap();

		soapModel.setTrainingLinkedUserId(model.getTrainingLinkedUserId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPrimaryUserId(model.getPrimaryUserId());

		return soapModel;
	}

	public static TrainingLinkedUserSoap[] toSoapModels(
		TrainingLinkedUser[] models) {
		TrainingLinkedUserSoap[] soapModels = new TrainingLinkedUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingLinkedUserSoap[][] toSoapModels(
		TrainingLinkedUser[][] models) {
		TrainingLinkedUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingLinkedUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingLinkedUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingLinkedUserSoap[] toSoapModels(
		List<TrainingLinkedUser> models) {
		List<TrainingLinkedUserSoap> soapModels = new ArrayList<TrainingLinkedUserSoap>(models.size());

		for (TrainingLinkedUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingLinkedUserSoap[soapModels.size()]);
	}

	public TrainingLinkedUserSoap() {
	}

	public long getPrimaryKey() {
		return _trainingLinkedUserId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingLinkedUserId(pk);
	}

	public long getTrainingLinkedUserId() {
		return _trainingLinkedUserId;
	}

	public void setTrainingLinkedUserId(long trainingLinkedUserId) {
		_trainingLinkedUserId = trainingLinkedUserId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getPrimaryUserId() {
		return _primaryUserId;
	}

	public void setPrimaryUserId(long primaryUserId) {
		_primaryUserId = primaryUserId;
	}

	private long _trainingLinkedUserId;
	private long _userId;
	private long _primaryUserId;
}