/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopParticipantAssignmentSoap implements Serializable {

	public static LoopParticipantAssignmentSoap toSoapModel(
		LoopParticipantAssignment model) {

		LoopParticipantAssignmentSoap soapModel =
			new LoopParticipantAssignmentSoap();

		soapModel.setLoopParticipantAssignmentId(
			model.getLoopParticipantAssignmentId());
		soapModel.setLoopDivisionId(model.getLoopDivisionId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setDescription(model.getDescription());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LoopParticipantAssignmentSoap[] toSoapModels(
		LoopParticipantAssignment[] models) {

		LoopParticipantAssignmentSoap[] soapModels =
			new LoopParticipantAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopParticipantAssignmentSoap[][] toSoapModels(
		LoopParticipantAssignment[][] models) {

		LoopParticipantAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LoopParticipantAssignmentSoap
					[models.length][models[0].length];
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

		List<LoopParticipantAssignmentSoap> soapModels =
			new ArrayList<LoopParticipantAssignmentSoap>(models.size());

		for (LoopParticipantAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LoopParticipantAssignmentSoap[soapModels.size()]);
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

	public void setLoopParticipantAssignmentId(
		long loopParticipantAssignmentId) {

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