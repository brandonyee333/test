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
public class TestrayFactorOptionSoap implements Serializable {

	public static TestrayFactorOptionSoap toSoapModel(
		TestrayFactorOption model) {

		TestrayFactorOptionSoap soapModel = new TestrayFactorOptionSoap();

		soapModel.setTestrayFactorOptionId(model.getTestrayFactorOptionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTestrayFactorCategoryId(
			model.getTestrayFactorCategoryId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static TestrayFactorOptionSoap[] toSoapModels(
		TestrayFactorOption[] models) {

		TestrayFactorOptionSoap[] soapModels =
			new TestrayFactorOptionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayFactorOptionSoap[][] toSoapModels(
		TestrayFactorOption[][] models) {

		TestrayFactorOptionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayFactorOptionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayFactorOptionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayFactorOptionSoap[] toSoapModels(
		List<TestrayFactorOption> models) {

		List<TestrayFactorOptionSoap> soapModels =
			new ArrayList<TestrayFactorOptionSoap>(models.size());

		for (TestrayFactorOption model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new TestrayFactorOptionSoap[soapModels.size()]);
	}

	public TestrayFactorOptionSoap() {
	}

	public long getPrimaryKey() {
		return _testrayFactorOptionId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayFactorOptionId(pk);
	}

	public long getTestrayFactorOptionId() {
		return _testrayFactorOptionId;
	}

	public void setTestrayFactorOptionId(long testrayFactorOptionId) {
		_testrayFactorOptionId = testrayFactorOptionId;
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

	public long getTestrayFactorCategoryId() {
		return _testrayFactorCategoryId;
	}

	public void setTestrayFactorCategoryId(long testrayFactorCategoryId) {
		_testrayFactorCategoryId = testrayFactorCategoryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _testrayFactorOptionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _testrayFactorCategoryId;
	private String _name;

}