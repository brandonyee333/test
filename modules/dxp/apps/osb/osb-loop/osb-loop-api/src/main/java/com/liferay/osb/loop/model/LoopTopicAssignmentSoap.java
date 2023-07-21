/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.model;

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
public class LoopTopicAssignmentSoap implements Serializable {

	public static LoopTopicAssignmentSoap toSoapModel(
		LoopTopicAssignment model) {

		LoopTopicAssignmentSoap soapModel = new LoopTopicAssignmentSoap();

		soapModel.setLoopTopicAssignmentId(model.getLoopTopicAssignmentId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setLoopTopicId(model.getLoopTopicId());
		soapModel.setStatusByDate(model.getStatusByDate());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static LoopTopicAssignmentSoap[] toSoapModels(
		LoopTopicAssignment[] models) {

		LoopTopicAssignmentSoap[] soapModels =
			new LoopTopicAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopTopicAssignmentSoap[][] toSoapModels(
		LoopTopicAssignment[][] models) {

		LoopTopicAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LoopTopicAssignmentSoap[models.length][models[0].length];
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

		List<LoopTopicAssignmentSoap> soapModels =
			new ArrayList<LoopTopicAssignmentSoap>(models.size());

		for (LoopTopicAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LoopTopicAssignmentSoap[soapModels.size()]);
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

	public Date getStatusByDate() {
		return _statusByDate;
	}

	public void setStatusByDate(Date statusByDate) {
		_statusByDate = statusByDate;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _loopTopicAssignmentId;
	private long _loopPersonId;
	private long _loopTopicId;
	private Date _statusByDate;
	private long _statusByUserId;
	private String _statusByUserName;
	private int _status;

}