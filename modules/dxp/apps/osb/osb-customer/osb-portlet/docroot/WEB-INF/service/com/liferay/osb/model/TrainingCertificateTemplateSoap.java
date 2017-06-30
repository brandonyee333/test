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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingCertificateTemplateServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingCertificateTemplateServiceSoap
 * @generated
 */
public class TrainingCertificateTemplateSoap implements Serializable {
	public static TrainingCertificateTemplateSoap toSoapModel(
		TrainingCertificateTemplate model) {
		TrainingCertificateTemplateSoap soapModel = new TrainingCertificateTemplateSoap();

		soapModel.setTrainingCertificateTemplateId(model.getTrainingCertificateTemplateId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static TrainingCertificateTemplateSoap[] toSoapModels(
		TrainingCertificateTemplate[] models) {
		TrainingCertificateTemplateSoap[] soapModels = new TrainingCertificateTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingCertificateTemplateSoap[][] toSoapModels(
		TrainingCertificateTemplate[][] models) {
		TrainingCertificateTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingCertificateTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingCertificateTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingCertificateTemplateSoap[] toSoapModels(
		List<TrainingCertificateTemplate> models) {
		List<TrainingCertificateTemplateSoap> soapModels = new ArrayList<TrainingCertificateTemplateSoap>(models.size());

		for (TrainingCertificateTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingCertificateTemplateSoap[soapModels.size()]);
	}

	public TrainingCertificateTemplateSoap() {
	}

	public long getPrimaryKey() {
		return _trainingCertificateTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingCertificateTemplateId(pk);
	}

	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplateId;
	}

	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplateId = trainingCertificateTemplateId;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _trainingCertificateTemplateId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private int _type;
}