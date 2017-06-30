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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketWorkerServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TicketWorkerServiceSoap
 * @generated
 */
public class TicketWorkerSoap implements Serializable {
	public static TicketWorkerSoap toSoapModel(TicketWorker model) {
		TicketWorkerSoap soapModel = new TicketWorkerSoap();

		soapModel.setTicketWorkerId(model.getTicketWorkerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setSourceClassNameId(model.getSourceClassNameId());
		soapModel.setSourceClassPK(model.getSourceClassPK());
		soapModel.setRole(model.getRole());
		soapModel.setPrimary(model.getPrimary());

		return soapModel;
	}

	public static TicketWorkerSoap[] toSoapModels(TicketWorker[] models) {
		TicketWorkerSoap[] soapModels = new TicketWorkerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketWorkerSoap[][] toSoapModels(TicketWorker[][] models) {
		TicketWorkerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketWorkerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketWorkerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketWorkerSoap[] toSoapModels(List<TicketWorker> models) {
		List<TicketWorkerSoap> soapModels = new ArrayList<TicketWorkerSoap>(models.size());

		for (TicketWorker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketWorkerSoap[soapModels.size()]);
	}

	public TicketWorkerSoap() {
	}

	public long getPrimaryKey() {
		return _ticketWorkerId;
	}

	public void setPrimaryKey(long pk) {
		setTicketWorkerId(pk);
	}

	public long getTicketWorkerId() {
		return _ticketWorkerId;
	}

	public void setTicketWorkerId(long ticketWorkerId) {
		_ticketWorkerId = ticketWorkerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;
	}

	public long getSourceClassNameId() {
		return _sourceClassNameId;
	}

	public void setSourceClassNameId(long sourceClassNameId) {
		_sourceClassNameId = sourceClassNameId;
	}

	public long getSourceClassPK() {
		return _sourceClassPK;
	}

	public void setSourceClassPK(long sourceClassPK) {
		_sourceClassPK = sourceClassPK;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	public boolean getPrimary() {
		return _primary;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public void setPrimary(boolean primary) {
		_primary = primary;
	}

	private long _ticketWorkerId;
	private long _userId;
	private long _ticketEntryId;
	private long _sourceClassNameId;
	private long _sourceClassPK;
	private int _role;
	private boolean _primary;
}