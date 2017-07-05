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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.SupportWorkerServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.SupportWorkerServiceSoap
 * @generated
 */
@ProviderType
public class SupportWorkerSoap implements Serializable {
	public static SupportWorkerSoap toSoapModel(SupportWorker model) {
		SupportWorkerSoap soapModel = new SupportWorkerSoap();

		soapModel.setSupportWorkerId(model.getSupportWorkerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSupportTeamId(model.getSupportTeamId());
		soapModel.setSupportLaborId(model.getSupportLaborId());
		soapModel.setAutoAssign(model.getAutoAssign());
		soapModel.setAssignedWork(model.getAssignedWork());
		soapModel.setMaxWork(model.getMaxWork());
		soapModel.setEscalationLevel(model.getEscalationLevel());
		soapModel.setRole(model.getRole());
		soapModel.setEscalationLevel2Role(model.getEscalationLevel2Role());
		soapModel.setNotifications(model.getNotifications());
		soapModel.setClockedIn(model.getClockedIn());

		return soapModel;
	}

	public static SupportWorkerSoap[] toSoapModels(SupportWorker[] models) {
		SupportWorkerSoap[] soapModels = new SupportWorkerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerSoap[][] toSoapModels(SupportWorker[][] models) {
		SupportWorkerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SupportWorkerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SupportWorkerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SupportWorkerSoap[] toSoapModels(List<SupportWorker> models) {
		List<SupportWorkerSoap> soapModels = new ArrayList<SupportWorkerSoap>(models.size());

		for (SupportWorker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SupportWorkerSoap[soapModels.size()]);
	}

	public SupportWorkerSoap() {
	}

	public long getPrimaryKey() {
		return _supportWorkerId;
	}

	public void setPrimaryKey(long pk) {
		setSupportWorkerId(pk);
	}

	public long getSupportWorkerId() {
		return _supportWorkerId;
	}

	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerId = supportWorkerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getSupportTeamId() {
		return _supportTeamId;
	}

	public void setSupportTeamId(long supportTeamId) {
		_supportTeamId = supportTeamId;
	}

	public long getSupportLaborId() {
		return _supportLaborId;
	}

	public void setSupportLaborId(long supportLaborId) {
		_supportLaborId = supportLaborId;
	}

	public boolean getAutoAssign() {
		return _autoAssign;
	}

	public boolean isAutoAssign() {
		return _autoAssign;
	}

	public void setAutoAssign(boolean autoAssign) {
		_autoAssign = autoAssign;
	}

	public double getAssignedWork() {
		return _assignedWork;
	}

	public void setAssignedWork(double assignedWork) {
		_assignedWork = assignedWork;
	}

	public double getMaxWork() {
		return _maxWork;
	}

	public void setMaxWork(double maxWork) {
		_maxWork = maxWork;
	}

	public int getEscalationLevel() {
		return _escalationLevel;
	}

	public void setEscalationLevel(int escalationLevel) {
		_escalationLevel = escalationLevel;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	public int getEscalationLevel2Role() {
		return _escalationLevel2Role;
	}

	public void setEscalationLevel2Role(int escalationLevel2Role) {
		_escalationLevel2Role = escalationLevel2Role;
	}

	public int getNotifications() {
		return _notifications;
	}

	public void setNotifications(int notifications) {
		_notifications = notifications;
	}

	public boolean getClockedIn() {
		return _clockedIn;
	}

	public boolean isClockedIn() {
		return _clockedIn;
	}

	public void setClockedIn(boolean clockedIn) {
		_clockedIn = clockedIn;
	}

	private long _supportWorkerId;
	private long _userId;
	private long _supportTeamId;
	private long _supportLaborId;
	private boolean _autoAssign;
	private double _assignedWork;
	private double _maxWork;
	private int _escalationLevel;
	private int _role;
	private int _escalationLevel2Role;
	private int _notifications;
	private boolean _clockedIn;
}