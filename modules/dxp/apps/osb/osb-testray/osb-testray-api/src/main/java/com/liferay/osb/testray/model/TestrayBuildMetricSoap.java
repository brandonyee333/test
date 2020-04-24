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

package com.liferay.osb.testray.model;

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
public class TestrayBuildMetricSoap implements Serializable {
	public static TestrayBuildMetricSoap toSoapModel(TestrayBuildMetric model) {
		TestrayBuildMetricSoap soapModel = new TestrayBuildMetricSoap();

		soapModel.setTestrayBuildMetricId(model.getTestrayBuildMetricId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayBuildId(model.getTestrayBuildId());
		soapModel.setTestrayCaseTypeId(model.getTestrayCaseTypeId());
		soapModel.setStatus(model.getStatus());
		soapModel.setCount(model.getCount());

		return soapModel;
	}

	public static TestrayBuildMetricSoap[] toSoapModels(
		TestrayBuildMetric[] models) {
		TestrayBuildMetricSoap[] soapModels = new TestrayBuildMetricSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayBuildMetricSoap[][] toSoapModels(
		TestrayBuildMetric[][] models) {
		TestrayBuildMetricSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestrayBuildMetricSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayBuildMetricSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayBuildMetricSoap[] toSoapModels(
		List<TestrayBuildMetric> models) {
		List<TestrayBuildMetricSoap> soapModels = new ArrayList<TestrayBuildMetricSoap>(models.size());

		for (TestrayBuildMetric model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayBuildMetricSoap[soapModels.size()]);
	}

	public TestrayBuildMetricSoap() {
	}

	public long getPrimaryKey() {
		return _testrayBuildMetricId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayBuildMetricId(pk);
	}

	public long getTestrayBuildMetricId() {
		return _testrayBuildMetricId;
	}

	public void setTestrayBuildMetricId(long testrayBuildMetricId) {
		_testrayBuildMetricId = testrayBuildMetricId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getTestrayBuildId() {
		return _testrayBuildId;
	}

	public void setTestrayBuildId(long testrayBuildId) {
		_testrayBuildId = testrayBuildId;
	}

	public long getTestrayCaseTypeId() {
		return _testrayCaseTypeId;
	}

	public void setTestrayCaseTypeId(long testrayCaseTypeId) {
		_testrayCaseTypeId = testrayCaseTypeId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getCount() {
		return _count;
	}

	public void setCount(long count) {
		_count = count;
	}

	private long _testrayBuildMetricId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayBuildId;
	private long _testrayCaseTypeId;
	private int _status;
	private long _count;
}