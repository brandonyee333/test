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

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class LoopTopicAssignmentSoap implements Serializable {
	public static LoopTopicAssignmentSoap toSoapModel(LoopTopicAssignment model) {
		LoopTopicAssignmentSoap soapModel = new LoopTopicAssignmentSoap();

		soapModel.setLoopTopicAssignmentId(model.getLoopTopicAssignmentId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setLoopTopicId(model.getLoopTopicId());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusByDate(model.getStatusByDate());

		return soapModel;
	}

	public static LoopTopicAssignmentSoap[] toSoapModels(
		LoopTopicAssignment[] models) {
		LoopTopicAssignmentSoap[] soapModels = new LoopTopicAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopTopicAssignmentSoap[][] toSoapModels(
		LoopTopicAssignment[][] models) {
		LoopTopicAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopTopicAssignmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopTopicAssignmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopTopicAssignmentSoap[] toSoapModels(
		List<LoopTopicAssignment> models) {
		List<LoopTopicAssignmentSoap> soapModels = new ArrayList<LoopTopicAssignmentSoap>(models.size());

		for (LoopTopicAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopTopicAssignmentSoap[soapModels.size()]);
	}

	public LoopTopicAssignmentSoap() {
	}

	public long getPrimaryKey() {
		return _loopTopicAssignmentId;
	}

	public void setPrimaryKey(long pk) {
		setLoopTopicAssignmentId(pk);
	}

	public long getLoopTopicAssignmentId() {
		return _loopTopicAssignmentId;
	}

	public void setLoopTopicAssignmentId(long loopTopicAssignmentId) {
		_loopTopicAssignmentId = loopTopicAssignmentId;
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
	}

	public long getLoopTopicId() {
		return _loopTopicId;
	}

	public void setLoopTopicId(long loopTopicId) {
		_loopTopicId = loopTopicId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusByDate() {
		return _statusByDate;
	}

	public void setStatusByDate(Date statusByDate) {
		_statusByDate = statusByDate;
	}

	private long _loopTopicAssignmentId;
	private long _loopPersonId;
	private long _loopTopicId;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusByDate;
}