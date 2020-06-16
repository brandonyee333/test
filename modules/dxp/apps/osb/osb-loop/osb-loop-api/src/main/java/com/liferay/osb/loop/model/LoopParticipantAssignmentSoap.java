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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
public class LoopParticipantAssignmentSoap implements Serializable {
	public static LoopParticipantAssignmentSoap toSoapModel(
		LoopParticipantAssignment model) {
		LoopParticipantAssignmentSoap soapModel = new LoopParticipantAssignmentSoap();

		soapModel.setLoopParticipantAssignmentId(model.getLoopParticipantAssignmentId());
		soapModel.setLoopDivisionId(model.getLoopDivisionId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LoopParticipantAssignmentSoap[] toSoapModels(
		LoopParticipantAssignment[] models) {
		LoopParticipantAssignmentSoap[] soapModels = new LoopParticipantAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopParticipantAssignmentSoap[][] toSoapModels(
		LoopParticipantAssignment[][] models) {
		LoopParticipantAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopParticipantAssignmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopParticipantAssignmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopParticipantAssignmentSoap[] toSoapModels(
		List<LoopParticipantAssignment> models) {
		List<LoopParticipantAssignmentSoap> soapModels = new ArrayList<LoopParticipantAssignmentSoap>(models.size());

		for (LoopParticipantAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopParticipantAssignmentSoap[soapModels.size()]);
	}

	public LoopParticipantAssignmentSoap() {
	}

	public long getPrimaryKey() {
		return _loopParticipantAssignmentId;
	}

	public void setPrimaryKey(long pk) {
		setLoopParticipantAssignmentId(pk);
	}

	public long getLoopParticipantAssignmentId() {
		return _loopParticipantAssignmentId;
	}

	public void setLoopParticipantAssignmentId(long loopParticipantAssignmentId) {
		_loopParticipantAssignmentId = loopParticipantAssignmentId;
	}

	public long getLoopDivisionId() {
		return _loopDivisionId;
	}

	public void setLoopDivisionId(long loopDivisionId) {
		_loopDivisionId = loopDivisionId;
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
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

	private long _loopParticipantAssignmentId;
	private long _loopDivisionId;
	private long _loopPersonId;
	private String _description;
	private int _type;
}