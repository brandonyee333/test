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
public class TestraySubtaskSoap implements Serializable {

	public static TestraySubtaskSoap toSoapModel(TestraySubtask model) {
		TestraySubtaskSoap soapModel = new TestraySubtaskSoap();

		soapModel.setTestraySubtaskId(model.getTestraySubtaskId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommentMBMessageId(model.getCommentMBMessageId());
		soapModel.setMergedToTestraySubtaskId(
			model.getMergedToTestraySubtaskId());
		soapModel.setSplitFromTestraySubtaskId(
			model.getSplitFromTestraySubtaskId());
		soapModel.setTestrayTaskId(model.getTestrayTaskId());
		soapModel.setName(model.getName());
		soapModel.setScore(model.getScore());
		soapModel.setStatusUpdateDate(model.getStatusUpdateDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TestraySubtaskSoap[] toSoapModels(TestraySubtask[] models) {
		TestraySubtaskSoap[] soapModels = new TestraySubtaskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestraySubtaskSoap[][] toSoapModels(
		TestraySubtask[][] models) {

		TestraySubtaskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestraySubtaskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestraySubtaskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestraySubtaskSoap[] toSoapModels(
		List<TestraySubtask> models) {

		List<TestraySubtaskSoap> soapModels = new ArrayList<TestraySubtaskSoap>(
			models.size());

		for (TestraySubtask model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestraySubtaskSoap[soapModels.size()]);
	}

	public TestraySubtaskSoap() {
	}

	public long getPrimaryKey() {
		return _testraySubtaskId;
	}

	public void setPrimaryKey(long pk) {
		setTestraySubtaskId(pk);
	}

	public long getTestraySubtaskId() {
		return _testraySubtaskId;
	}

	public void setTestraySubtaskId(long testraySubtaskId) {
		_testraySubtaskId = testraySubtaskId;
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

	public long getCommentMBMessageId() {
		return _commentMBMessageId;
	}

	public void setCommentMBMessageId(long commentMBMessageId) {
		_commentMBMessageId = commentMBMessageId;
	}

	public long getMergedToTestraySubtaskId() {
		return _mergedToTestraySubtaskId;
	}

	public void setMergedToTestraySubtaskId(long mergedToTestraySubtaskId) {
		_mergedToTestraySubtaskId = mergedToTestraySubtaskId;
	}

	public long getSplitFromTestraySubtaskId() {
		return _splitFromTestraySubtaskId;
	}

	public void setSplitFromTestraySubtaskId(long splitFromTestraySubtaskId) {
		_splitFromTestraySubtaskId = splitFromTestraySubtaskId;
	}

	public long getTestrayTaskId() {
		return _testrayTaskId;
	}

	public void setTestrayTaskId(long testrayTaskId) {
		_testrayTaskId = testrayTaskId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getScore() {
		return _score;
	}

	public void setScore(int score) {
		_score = score;
	}

	public Date getStatusUpdateDate() {
		return _statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		_statusUpdateDate = statusUpdateDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _testraySubtaskId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commentMBMessageId;
	private long _mergedToTestraySubtaskId;
	private long _splitFromTestraySubtaskId;
	private long _testrayTaskId;
	private String _name;
	private int _score;
	private Date _statusUpdateDate;
	private int _status;

}