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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingExamResultItemServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingExamResultItemServiceSoap
 * @generated
 */
public class TrainingExamResultItemSoap implements Serializable {
	public static TrainingExamResultItemSoap toSoapModel(
		TrainingExamResultItem model) {
		TrainingExamResultItemSoap soapModel = new TrainingExamResultItemSoap();

		soapModel.setTrainingExamResultItemId(model.getTrainingExamResultItemId());
		soapModel.setTrainingExamResultId(model.getTrainingExamResultId());
		soapModel.setTrainingExamResultSectionId(model.getTrainingExamResultSectionId());
		soapModel.setName(model.getName());
		soapModel.setStatus(model.getStatus());
		soapModel.setKey(model.getKey());
		soapModel.setDistractorCount(model.getDistractorCount());
		soapModel.setType(model.getType());
		soapModel.setResponse(model.getResponse());
		soapModel.setScore(model.getScore());
		soapModel.setTime(model.getTime());
		soapModel.setLearningResources(model.getLearningResources());

		return soapModel;
	}

	public static TrainingExamResultItemSoap[] toSoapModels(
		TrainingExamResultItem[] models) {
		TrainingExamResultItemSoap[] soapModels = new TrainingExamResultItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamResultItemSoap[][] toSoapModels(
		TrainingExamResultItem[][] models) {
		TrainingExamResultItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingExamResultItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingExamResultItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamResultItemSoap[] toSoapModels(
		List<TrainingExamResultItem> models) {
		List<TrainingExamResultItemSoap> soapModels = new ArrayList<TrainingExamResultItemSoap>(models.size());

		for (TrainingExamResultItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingExamResultItemSoap[soapModels.size()]);
	}

	public TrainingExamResultItemSoap() {
	}

	public long getPrimaryKey() {
		return _trainingExamResultItemId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingExamResultItemId(pk);
	}

	public long getTrainingExamResultItemId() {
		return _trainingExamResultItemId;
	}

	public void setTrainingExamResultItemId(long trainingExamResultItemId) {
		_trainingExamResultItemId = trainingExamResultItemId;
	}

	public long getTrainingExamResultId() {
		return _trainingExamResultId;
	}

	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultId = trainingExamResultId;
	}

	public long getTrainingExamResultSectionId() {
		return _trainingExamResultSectionId;
	}

	public void setTrainingExamResultSectionId(long trainingExamResultSectionId) {
		_trainingExamResultSectionId = trainingExamResultSectionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public int getDistractorCount() {
		return _distractorCount;
	}

	public void setDistractorCount(int distractorCount) {
		_distractorCount = distractorCount;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getResponse() {
		return _response;
	}

	public void setResponse(String response) {
		_response = response;
	}

	public String getScore() {
		return _score;
	}

	public void setScore(String score) {
		_score = score;
	}

	public int getTime() {
		return _time;
	}

	public void setTime(int time) {
		_time = time;
	}

	public String getLearningResources() {
		return _learningResources;
	}

	public void setLearningResources(String learningResources) {
		_learningResources = learningResources;
	}

	private long _trainingExamResultItemId;
	private long _trainingExamResultId;
	private long _trainingExamResultSectionId;
	private String _name;
	private String _status;
	private String _key;
	private int _distractorCount;
	private String _type;
	private String _response;
	private String _score;
	private int _time;
	private String _learningResources;
}