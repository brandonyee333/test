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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingExamServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingExamServiceSoap
 * @generated
 */
public class TrainingExamSoap implements Serializable {
	public static TrainingExamSoap toSoapModel(TrainingExam model) {
		TrainingExamSoap soapModel = new TrainingExamSoap();

		soapModel.setTrainingExamId(model.getTrainingExamId());
		soapModel.setTrainingCertificateTemplateId(model.getTrainingCertificateTemplateId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static TrainingExamSoap[] toSoapModels(TrainingExam[] models) {
		TrainingExamSoap[] soapModels = new TrainingExamSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamSoap[][] toSoapModels(TrainingExam[][] models) {
		TrainingExamSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingExamSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingExamSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamSoap[] toSoapModels(List<TrainingExam> models) {
		List<TrainingExamSoap> soapModels = new ArrayList<TrainingExamSoap>(models.size());

		for (TrainingExam model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingExamSoap[soapModels.size()]);
	}

	public TrainingExamSoap() {
	}

	public long getPrimaryKey() {
		return _trainingExamId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingExamId(pk);
	}

	public long getTrainingExamId() {
		return _trainingExamId;
	}

	public void setTrainingExamId(long trainingExamId) {
		_trainingExamId = trainingExamId;
	}

	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplateId;
	}

	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplateId = trainingCertificateTemplateId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _trainingExamId;
	private long _trainingCertificateTemplateId;
	private String _name;
}