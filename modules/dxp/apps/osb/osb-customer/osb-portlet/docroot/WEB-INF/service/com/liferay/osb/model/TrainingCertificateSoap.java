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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingCertificateServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingCertificateServiceSoap
 * @generated
 */
public class TrainingCertificateSoap implements Serializable {
	public static TrainingCertificateSoap toSoapModel(TrainingCertificate model) {
		TrainingCertificateSoap soapModel = new TrainingCertificateSoap();

		soapModel.setTrainingCertificateId(model.getTrainingCertificateId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTrainingCustomerId(model.getTrainingCustomerId());
		soapModel.setUserProfileHistoryId(model.getUserProfileHistoryId());
		soapModel.setKey(model.getKey());
		soapModel.setCertifiedDate(model.getCertifiedDate());
		soapModel.setComments(model.getComments());
		soapModel.setSurveyStatus(model.getSurveyStatus());

		return soapModel;
	}

	public static TrainingCertificateSoap[] toSoapModels(
		TrainingCertificate[] models) {
		TrainingCertificateSoap[] soapModels = new TrainingCertificateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingCertificateSoap[][] toSoapModels(
		TrainingCertificate[][] models) {
		TrainingCertificateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingCertificateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingCertificateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingCertificateSoap[] toSoapModels(
		List<TrainingCertificate> models) {
		List<TrainingCertificateSoap> soapModels = new ArrayList<TrainingCertificateSoap>(models.size());

		for (TrainingCertificate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingCertificateSoap[soapModels.size()]);
	}

	public TrainingCertificateSoap() {
	}

	public long getPrimaryKey() {
		return _trainingCertificateId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingCertificateId(pk);
	}

	public long getTrainingCertificateId() {
		return _trainingCertificateId;
	}

	public void setTrainingCertificateId(long trainingCertificateId) {
		_trainingCertificateId = trainingCertificateId;
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

	public long getTrainingCustomerId() {
		return _trainingCustomerId;
	}

	public void setTrainingCustomerId(long trainingCustomerId) {
		_trainingCustomerId = trainingCustomerId;
	}

	public long getUserProfileHistoryId() {
		return _userProfileHistoryId;
	}

	public void setUserProfileHistoryId(long userProfileHistoryId) {
		_userProfileHistoryId = userProfileHistoryId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public Date getCertifiedDate() {
		return _certifiedDate;
	}

	public void setCertifiedDate(Date certifiedDate) {
		_certifiedDate = certifiedDate;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public int getSurveyStatus() {
		return _surveyStatus;
	}

	public void setSurveyStatus(int surveyStatus) {
		_surveyStatus = surveyStatus;
	}

	private long _trainingCertificateId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _trainingCustomerId;
	private long _userProfileHistoryId;
	private String _key;
	private Date _certifiedDate;
	private String _comments;
	private int _surveyStatus;
}