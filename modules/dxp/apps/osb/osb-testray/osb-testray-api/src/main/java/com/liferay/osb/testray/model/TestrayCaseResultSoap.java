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
public class TestrayCaseResultSoap implements Serializable {

	public static TestrayCaseResultSoap toSoapModel(TestrayCaseResult model) {
		TestrayCaseResultSoap soapModel = new TestrayCaseResultSoap();

		soapModel.setTestrayCaseResultId(model.getTestrayCaseResultId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommentMBMessageId(model.getCommentMBMessageId());
		soapModel.setTestrayBuildId(model.getTestrayBuildId());
		soapModel.setTestrayCaseId(model.getTestrayCaseId());
		soapModel.setTestrayComponentId(model.getTestrayComponentId());
		soapModel.setTestrayRunId(model.getTestrayRunId());
		soapModel.setAssignedUserId(model.getAssignedUserId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setClosedDate(model.getClosedDate());
		soapModel.setAttachments(model.getAttachments());
		soapModel.setErrors(model.getErrors());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TestrayCaseResultSoap[] toSoapModels(
		TestrayCaseResult[] models) {

		TestrayCaseResultSoap[] soapModels =
			new TestrayCaseResultSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayCaseResultSoap[][] toSoapModels(
		TestrayCaseResult[][] models) {

		TestrayCaseResultSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayCaseResultSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayCaseResultSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayCaseResultSoap[] toSoapModels(
		List<TestrayCaseResult> models) {

		List<TestrayCaseResultSoap> soapModels =
			new ArrayList<TestrayCaseResultSoap>(models.size());

		for (TestrayCaseResult model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayCaseResultSoap[soapModels.size()]);
	}

	public TestrayCaseResultSoap() {
	}

	public long getPrimaryKey() {
		return _testrayCaseResultId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayCaseResultId(pk);
	}

	public long getTestrayCaseResultId() {
		return _testrayCaseResultId;
	}

	public void setTestrayCaseResultId(long testrayCaseResultId) {
		_testrayCaseResultId = testrayCaseResultId;
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

	public long getTestrayBuildId() {
		return _testrayBuildId;
	}

	public void setTestrayBuildId(long testrayBuildId) {
		_testrayBuildId = testrayBuildId;
	}

	public long getTestrayCaseId() {
		return _testrayCaseId;
	}

	public void setTestrayCaseId(long testrayCaseId) {
		_testrayCaseId = testrayCaseId;
	}

	public long getTestrayComponentId() {
		return _testrayComponentId;
	}

	public void setTestrayComponentId(long testrayComponentId) {
		_testrayComponentId = testrayComponentId;
	}

	public long getTestrayRunId() {
		return _testrayRunId;
	}

	public void setTestrayRunId(long testrayRunId) {
		_testrayRunId = testrayRunId;
	}

	public long getAssignedUserId() {
		return _assignedUserId;
	}

	public void setAssignedUserId(long assignedUserId) {
		_assignedUserId = assignedUserId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getClosedDate() {
		return _closedDate;
	}

	public void setClosedDate(Date closedDate) {
		_closedDate = closedDate;
	}

	public String getAttachments() {
		return _attachments;
	}

	public void setAttachments(String attachments) {
		_attachments = attachments;
	}

	public String getErrors() {
		return _errors;
	}

	public void setErrors(String errors) {
		_errors = errors;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _testrayCaseResultId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commentMBMessageId;
	private long _testrayBuildId;
	private long _testrayCaseId;
	private long _testrayComponentId;
	private long _testrayRunId;
	private long _assignedUserId;
	private Date _startDate;
	private Date _closedDate;
	private String _attachments;
	private String _errors;
	private int _status;

}