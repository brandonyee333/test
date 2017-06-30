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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingExamResultServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingExamResultServiceSoap
 * @generated
 */
public class TrainingExamResultSoap implements Serializable {
	public static TrainingExamResultSoap toSoapModel(TrainingExamResult model) {
		TrainingExamResultSoap soapModel = new TrainingExamResultSoap();

		soapModel.setTrainingExamResultId(model.getTrainingExamResultId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setTrainingExamId(model.getTrainingExamId());
		soapModel.setRecordType(model.getRecordType());
		soapModel.setRegistrationNumber(model.getRegistrationNumber());
		soapModel.setFormKey(model.getFormKey());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setTestScore(model.getTestScore());
		soapModel.setCorrectCount(model.getCorrectCount());
		soapModel.setIncorrectCount(model.getIncorrectCount());
		soapModel.setSkippedCount(model.getSkippedCount());
		soapModel.setGrade(model.getGrade());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TrainingExamResultSoap[] toSoapModels(
		TrainingExamResult[] models) {
		TrainingExamResultSoap[] soapModels = new TrainingExamResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamResultSoap[][] toSoapModels(
		TrainingExamResult[][] models) {
		TrainingExamResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingExamResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingExamResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamResultSoap[] toSoapModels(
		List<TrainingExamResult> models) {
		List<TrainingExamResultSoap> soapModels = new ArrayList<TrainingExamResultSoap>(models.size());

		for (TrainingExamResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingExamResultSoap[soapModels.size()]);
	}

	public TrainingExamResultSoap() {
	}

	public long getPrimaryKey() {
		return _trainingExamResultId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingExamResultId(pk);
	}

	public long getTrainingExamResultId() {
		return _trainingExamResultId;
	}

	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultId = trainingExamResultId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getTrainingExamId() {
		return _trainingExamId;
	}

	public void setTrainingExamId(long trainingExamId) {
		_trainingExamId = trainingExamId;
	}

	public int getRecordType() {
		return _recordType;
	}

	public void setRecordType(int recordType) {
		_recordType = recordType;
	}

	public String getRegistrationNumber() {
		return _registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		_registrationNumber = registrationNumber;
	}

	public String getFormKey() {
		return _formKey;
	}

	public void setFormKey(String formKey) {
		_formKey = formKey;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public String getTestScore() {
		return _testScore;
	}

	public void setTestScore(String testScore) {
		_testScore = testScore;
	}

	public int getCorrectCount() {
		return _correctCount;
	}

	public void setCorrectCount(int correctCount) {
		_correctCount = correctCount;
	}

	public int getIncorrectCount() {
		return _incorrectCount;
	}

	public void setIncorrectCount(int incorrectCount) {
		_incorrectCount = incorrectCount;
	}

	public int getSkippedCount() {
		return _skippedCount;
	}

	public void setSkippedCount(int skippedCount) {
		_skippedCount = skippedCount;
	}

	public int getGrade() {
		return _grade;
	}

	public void setGrade(int grade) {
		_grade = grade;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _trainingExamResultId;
	private Date _createDate;
	private long _trainingExamId;
	private int _recordType;
	private String _registrationNumber;
	private String _formKey;
	private Date _startDate;
	private String _testScore;
	private int _correctCount;
	private int _incorrectCount;
	private int _skippedCount;
	private int _grade;
	private int _status;
}