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
public class TestrayComponentSoap implements Serializable {

	public static TestrayComponentSoap toSoapModel(TestrayComponent model) {
		TestrayComponentSoap soapModel = new TestrayComponentSoap();

		soapModel.setTestrayComponentId(model.getTestrayComponentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayProjectId(model.getTestrayProjectId());
		soapModel.setTestrayTeamId(model.getTestrayTeamId());
		soapModel.setName(model.getName());
		soapModel.setOriginationKey(model.getOriginationKey());

		return soapModel;
	}

	public static TestrayComponentSoap[] toSoapModels(
		TestrayComponent[] models) {

		TestrayComponentSoap[] soapModels =
			new TestrayComponentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayComponentSoap[][] toSoapModels(
		TestrayComponent[][] models) {

		TestrayComponentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayComponentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayComponentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayComponentSoap[] toSoapModels(
		List<TestrayComponent> models) {

		List<TestrayComponentSoap> soapModels =
			new ArrayList<TestrayComponentSoap>(models.size());

		for (TestrayComponent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayComponentSoap[soapModels.size()]);
	}

	public TestrayComponentSoap() {
	}

	public long getPrimaryKey() {
		return _testrayComponentId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayComponentId(pk);
	}

	public long getTestrayComponentId() {
		return _testrayComponentId;
	}

	public void setTestrayComponentId(long testrayComponentId) {
		_testrayComponentId = testrayComponentId;
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

	public long getTestrayTeamId() {
		return _testrayTeamId;
	}

	public void setTestrayTeamId(long testrayTeamId) {
		_testrayTeamId = testrayTeamId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getOriginationKey() {
		return _originationKey;
	}

	public void setOriginationKey(long originationKey) {
		_originationKey = originationKey;
	}

	private long _testrayComponentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayProjectId;
	private long _testrayTeamId;
	private String _name;
	private long _originationKey;

}