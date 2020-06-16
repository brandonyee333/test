/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.model;

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
public class TestrayProductVersionSoap implements Serializable {

	public static TestrayProductVersionSoap toSoapModel(
		TestrayProductVersion model) {

		TestrayProductVersionSoap soapModel = new TestrayProductVersionSoap();

		soapModel.setTestrayProductVersionId(
			model.getTestrayProductVersionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayProjectId(model.getTestrayProjectId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static TestrayProductVersionSoap[] toSoapModels(
		TestrayProductVersion[] models) {

		TestrayProductVersionSoap[] soapModels =
			new TestrayProductVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayProductVersionSoap[][] toSoapModels(
		TestrayProductVersion[][] models) {

		TestrayProductVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayProductVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayProductVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayProductVersionSoap[] toSoapModels(
		List<TestrayProductVersion> models) {

		List<TestrayProductVersionSoap> soapModels =
			new ArrayList<TestrayProductVersionSoap>(models.size());

		for (TestrayProductVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new TestrayProductVersionSoap[soapModels.size()]);
	}

	public TestrayProductVersionSoap() {
	}

	public long getPrimaryKey() {
		return _testrayProductVersionId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayProductVersionId(pk);
	}

	public long getTestrayProductVersionId() {
		return _testrayProductVersionId;
	}

	public void setTestrayProductVersionId(long testrayProductVersionId) {
		_testrayProductVersionId = testrayProductVersionId;
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

	public long getTestrayProjectId() {
		return _testrayProjectId;
	}

	public void setTestrayProjectId(long testrayProjectId) {
		_testrayProjectId = testrayProjectId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _testrayProductVersionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayProjectId;
	private String _name;

}