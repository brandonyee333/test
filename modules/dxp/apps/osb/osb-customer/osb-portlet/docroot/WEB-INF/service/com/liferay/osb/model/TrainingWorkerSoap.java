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
public class TrainingWorkerSoap implements Serializable {
	public static TrainingWorkerSoap toSoapModel(TrainingWorker model) {
		TrainingWorkerSoap soapModel = new TrainingWorkerSoap();

		soapModel.setTrainingWorkerId(model.getTrainingWorkerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setUserProfileHistoryId(model.getUserProfileHistoryId());

		return soapModel;
	}

	public static TrainingWorkerSoap[] toSoapModels(TrainingWorker[] models) {
		TrainingWorkerSoap[] soapModels = new TrainingWorkerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingWorkerSoap[][] toSoapModels(TrainingWorker[][] models) {
		TrainingWorkerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingWorkerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingWorkerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingWorkerSoap[] toSoapModels(List<TrainingWorker> models) {
		List<TrainingWorkerSoap> soapModels = new ArrayList<TrainingWorkerSoap>(models.size());

		for (TrainingWorker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingWorkerSoap[soapModels.size()]);
	}

	public TrainingWorkerSoap() {
	}

	public long getPrimaryKey() {
		return _trainingWorkerId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingWorkerId(pk);
	}

	public long getTrainingWorkerId() {
		return _trainingWorkerId;
	}

	public void setTrainingWorkerId(long trainingWorkerId) {
		_trainingWorkerId = trainingWorkerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public long getUserProfileHistoryId() {
		return _userProfileHistoryId;
	}

	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_userProfileHistoryId = userProfileHistoryId;
	}

	private long _trainingWorkerId;
	private long _userId;
	private long _classNameId;
	private long _classPK;
	private long _userProfileHistoryId;
}