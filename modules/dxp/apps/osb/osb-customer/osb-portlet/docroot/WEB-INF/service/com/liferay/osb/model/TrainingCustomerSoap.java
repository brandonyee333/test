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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingCustomerServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingCustomerServiceSoap
 * @generated
 */
public class TrainingCustomerSoap implements Serializable {
	public static TrainingCustomerSoap toSoapModel(TrainingCustomer model) {
		TrainingCustomerSoap soapModel = new TrainingCustomerSoap();

		soapModel.setTrainingCustomerId(model.getTrainingCustomerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setUserProfileHistoryId(model.getUserProfileHistoryId());
		soapModel.setComments(model.getComments());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TrainingCustomerSoap[] toSoapModels(TrainingCustomer[] models) {
		TrainingCustomerSoap[] soapModels = new TrainingCustomerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingCustomerSoap[][] toSoapModels(
		TrainingCustomer[][] models) {
		TrainingCustomerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingCustomerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingCustomerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingCustomerSoap[] toSoapModels(
		List<TrainingCustomer> models) {
		List<TrainingCustomerSoap> soapModels = new ArrayList<TrainingCustomerSoap>(models.size());

		for (TrainingCustomer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingCustomerSoap[soapModels.size()]);
	}

	public TrainingCustomerSoap() {
	}

	public long getPrimaryKey() {
		return _trainingCustomerId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingCustomerId(pk);
	}

	public long getTrainingCustomerId() {
		return _trainingCustomerId;
	}

	public void setTrainingCustomerId(long trainingCustomerId) {
		_trainingCustomerId = trainingCustomerId;
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

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _trainingCustomerId;
	private long _userId;
	private long _classNameId;
	private long _classPK;
	private long _userProfileHistoryId;
	private String _comments;
	private int _status;
}