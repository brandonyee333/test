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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingCourseServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingCourseServiceSoap
 * @generated
 */
public class TrainingCourseSoap implements Serializable {
	public static TrainingCourseSoap toSoapModel(TrainingCourse model) {
		TrainingCourseSoap soapModel = new TrainingCourseSoap();

		soapModel.setTrainingCourseId(model.getTrainingCourseId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setCreditAmount(model.getCreditAmount());
		soapModel.setCourseURL(model.getCourseURL());
		soapModel.setArchived(model.getArchived());

		return soapModel;
	}

	public static TrainingCourseSoap[] toSoapModels(TrainingCourse[] models) {
		TrainingCourseSoap[] soapModels = new TrainingCourseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingCourseSoap[][] toSoapModels(TrainingCourse[][] models) {
		TrainingCourseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingCourseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingCourseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingCourseSoap[] toSoapModels(List<TrainingCourse> models) {
		List<TrainingCourseSoap> soapModels = new ArrayList<TrainingCourseSoap>(models.size());

		for (TrainingCourse model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingCourseSoap[soapModels.size()]);
	}

	public TrainingCourseSoap() {
	}

	public long getPrimaryKey() {
		return _trainingCourseId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingCourseId(pk);
	}

	public long getTrainingCourseId() {
		return _trainingCourseId;
	}

	public void setTrainingCourseId(long trainingCourseId) {
		_trainingCourseId = trainingCourseId;
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

	public int getCreditAmount() {
		return _creditAmount;
	}

	public void setCreditAmount(int creditAmount) {
		_creditAmount = creditAmount;
	}

	public String getCourseURL() {
		return _courseURL;
	}

	public void setCourseURL(String courseURL) {
		_courseURL = courseURL;
	}

	public boolean getArchived() {
		return _archived;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	private long _trainingCourseId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private int _creditAmount;
	private String _courseURL;
	private boolean _archived;
}