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

package com.liferay.osb.customer.github.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.github.service.http.CollaboratorServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CollaboratorSoap implements Serializable {

	public static CollaboratorSoap toSoapModel(Collaborator model) {
		CollaboratorSoap soapModel = new CollaboratorSoap();

		soapModel.setCollaboratorId(model.getCollaboratorId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setFullName(model.getFullName());
		soapModel.setGitHubUserName(model.getGitHubUserName());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static CollaboratorSoap[] toSoapModels(Collaborator[] models) {
		CollaboratorSoap[] soapModels = new CollaboratorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CollaboratorSoap[][] toSoapModels(Collaborator[][] models) {
		CollaboratorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CollaboratorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CollaboratorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CollaboratorSoap[] toSoapModels(List<Collaborator> models) {
		List<CollaboratorSoap> soapModels = new ArrayList<CollaboratorSoap>(
			models.size());

		for (Collaborator model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CollaboratorSoap[soapModels.size()]);
	}

	public CollaboratorSoap() {
	}

	public long getPrimaryKey() {
		return _collaboratorId;
	}

	public void setPrimaryKey(long pk) {
		setCollaboratorId(pk);
	}

	public long getCollaboratorId() {
		return _collaboratorId;
	}

	public void setCollaboratorId(long collaboratorId) {
		_collaboratorId = collaboratorId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public String getFullName() {
		return _fullName;
	}

	public void setFullName(String fullName) {
		_fullName = fullName;
	}

	public String getGitHubUserName() {
		return _gitHubUserName;
	}

	public void setGitHubUserName(String gitHubUserName) {
		_gitHubUserName = gitHubUserName;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _collaboratorId;
	private long _userId;
	private Date _createDate;
	private long _accountEntryId;
	private String _emailAddress;
	private String _fullName;
	private String _gitHubUserName;
	private int _status;

}