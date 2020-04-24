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
public class TestrayFactorSoap implements Serializable {

	public static TestrayFactorSoap toSoapModel(TestrayFactor model) {
		TestrayFactorSoap soapModel = new TestrayFactorSoap();

		soapModel.setTestrayFactorId(model.getTestrayFactorId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setTestrayFactorCategoryId(
			model.getTestrayFactorCategoryId());
		soapModel.setTestrayFactorCategoryName(
			model.getTestrayFactorCategoryName());
		soapModel.setTestrayFactorOptionId(model.getTestrayFactorOptionId());
		soapModel.setTestrayFactorOptionName(
			model.getTestrayFactorOptionName());

		return soapModel;
	}

	public static TestrayFactorSoap[] toSoapModels(TestrayFactor[] models) {
		TestrayFactorSoap[] soapModels = new TestrayFactorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayFactorSoap[][] toSoapModels(TestrayFactor[][] models) {
		TestrayFactorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestrayFactorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayFactorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayFactorSoap[] toSoapModels(List<TestrayFactor> models) {
		List<TestrayFactorSoap> soapModels = new ArrayList<TestrayFactorSoap>(
			models.size());

		for (TestrayFactor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayFactorSoap[soapModels.size()]);
	}

	public TestrayFactorSoap() {
	}

	public long getPrimaryKey() {
		return _testrayFactorId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayFactorId(pk);
	}

	public long getTestrayFactorId() {
		return _testrayFactorId;
	}

	public void setTestrayFactorId(long testrayFactorId) {
		_testrayFactorId = testrayFactorId;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public long getTestrayFactorCategoryId() {
		return _testrayFactorCategoryId;
	}

	public void setTestrayFactorCategoryId(long testrayFactorCategoryId) {
		_testrayFactorCategoryId = testrayFactorCategoryId;
	}

	public String getTestrayFactorCategoryName() {
		return _testrayFactorCategoryName;
	}

	public void setTestrayFactorCategoryName(String testrayFactorCategoryName) {
		_testrayFactorCategoryName = testrayFactorCategoryName;
	}

	public long getTestrayFactorOptionId() {
		return _testrayFactorOptionId;
	}

	public void setTestrayFactorOptionId(long testrayFactorOptionId) {
		_testrayFactorOptionId = testrayFactorOptionId;
	}

	public String getTestrayFactorOptionName() {
		return _testrayFactorOptionName;
	}

	public void setTestrayFactorOptionName(String testrayFactorOptionName) {
		_testrayFactorOptionName = testrayFactorOptionName;
	}

	private long _testrayFactorId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private long _testrayFactorCategoryId;
	private String _testrayFactorCategoryName;
	private long _testrayFactorOptionId;
	private String _testrayFactorOptionName;

}