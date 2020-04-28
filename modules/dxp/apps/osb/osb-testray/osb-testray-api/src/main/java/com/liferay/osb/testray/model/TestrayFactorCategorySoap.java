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
public class TestrayFactorCategorySoap implements Serializable {

	public static TestrayFactorCategorySoap toSoapModel(
		TestrayFactorCategory model) {

		TestrayFactorCategorySoap soapModel = new TestrayFactorCategorySoap();

		soapModel.setTestrayFactorCategoryId(
			model.getTestrayFactorCategoryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static TestrayFactorCategorySoap[] toSoapModels(
		TestrayFactorCategory[] models) {

		TestrayFactorCategorySoap[] soapModels =
			new TestrayFactorCategorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayFactorCategorySoap[][] toSoapModels(
		TestrayFactorCategory[][] models) {

		TestrayFactorCategorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayFactorCategorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayFactorCategorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayFactorCategorySoap[] toSoapModels(
		List<TestrayFactorCategory> models) {

		List<TestrayFactorCategorySoap> soapModels =
			new ArrayList<TestrayFactorCategorySoap>(models.size());

		for (TestrayFactorCategory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new TestrayFactorCategorySoap[soapModels.size()]);
	}

	public TestrayFactorCategorySoap() {
	}

	public long getPrimaryKey() {
		return _testrayFactorCategoryId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayFactorCategoryId(pk);
	}

	public long getTestrayFactorCategoryId() {
		return _testrayFactorCategoryId;
	}

	public void setTestrayFactorCategoryId(long testrayFactorCategoryId) {
		_testrayFactorCategoryId = testrayFactorCategoryId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _testrayFactorCategoryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;

}