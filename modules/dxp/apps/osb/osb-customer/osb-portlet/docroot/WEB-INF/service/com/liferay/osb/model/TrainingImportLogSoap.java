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
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class TrainingImportLogSoap implements Serializable {
	public static TrainingImportLogSoap toSoapModel(TrainingImportLog model) {
		TrainingImportLogSoap soapModel = new TrainingImportLogSoap();

		soapModel.setTrainingImportLogId(model.getTrainingImportLogId());
		soapModel.setType(model.getType());
		soapModel.setImportDate(model.getImportDate());

		return soapModel;
	}

	public static TrainingImportLogSoap[] toSoapModels(
		TrainingImportLog[] models) {
		TrainingImportLogSoap[] soapModels = new TrainingImportLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingImportLogSoap[][] toSoapModels(
		TrainingImportLog[][] models) {
		TrainingImportLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingImportLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingImportLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingImportLogSoap[] toSoapModels(
		List<TrainingImportLog> models) {
		List<TrainingImportLogSoap> soapModels = new ArrayList<TrainingImportLogSoap>(models.size());

		for (TrainingImportLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingImportLogSoap[soapModels.size()]);
	}

	public TrainingImportLogSoap() {
	}

	public long getPrimaryKey() {
		return _trainingImportLogId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingImportLogId(pk);
	}

	public long getTrainingImportLogId() {
		return _trainingImportLogId;
	}

	public void setTrainingImportLogId(long trainingImportLogId) {
		_trainingImportLogId = trainingImportLogId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public Date getImportDate() {
		return _importDate;
	}

	public void setImportDate(Date importDate) {
		_importDate = importDate;
	}

	private long _trainingImportLogId;
	private int _type;
	private Date _importDate;
}