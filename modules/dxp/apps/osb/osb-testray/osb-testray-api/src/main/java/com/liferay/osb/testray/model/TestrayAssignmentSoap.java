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
public class TestrayAssignmentSoap implements Serializable {

	public static TestrayAssignmentSoap toSoapModel(TestrayAssignment model) {
		TestrayAssignmentSoap soapModel = new TestrayAssignmentSoap();

		soapModel.setTestrayAssignmentId(model.getTestrayAssignmentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssignedUserId(model.getAssignedUserId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static TestrayAssignmentSoap[] toSoapModels(
		TestrayAssignment[] models) {

		TestrayAssignmentSoap[] soapModels =
			new TestrayAssignmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayAssignmentSoap[][] toSoapModels(
		TestrayAssignment[][] models) {

		TestrayAssignmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayAssignmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayAssignmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayAssignmentSoap[] toSoapModels(
		List<TestrayAssignment> models) {

		List<TestrayAssignmentSoap> soapModels =
			new ArrayList<TestrayAssignmentSoap>(models.size());

		for (TestrayAssignment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayAssignmentSoap[soapModels.size()]);
	}

	public TestrayAssignmentSoap() {
	}

	public long getPrimaryKey() {
		return _testrayAssignmentId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayAssignmentId(pk);
	}

	public long getTestrayAssignmentId() {
		return _testrayAssignmentId;
	}

	public void setTestrayAssignmentId(long testrayAssignmentId) {
		_testrayAssignmentId = testrayAssignmentId;
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

	public long getAssignedUserId() {
		return _assignedUserId;
	}

	public void setAssignedUserId(long assignedUserId) {
		_assignedUserId = assignedUserId;
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

	private long _testrayAssignmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assignedUserId;
	private long _classNameId;
	private long _classPK;

}