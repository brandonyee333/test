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

package com.liferay.osb.customer.release.notes.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ReleaseNotesSoap implements Serializable {

	public static ReleaseNotesSoap toSoapModel(ReleaseNotes model) {
		ReleaseNotesSoap soapModel = new ReleaseNotesSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setReleaseNotesId(model.getReleaseNotesId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setJiraIssueKeys(model.getJiraIssueKeys());

		return soapModel;
	}

	public static ReleaseNotesSoap[] toSoapModels(ReleaseNotes[] models) {
		ReleaseNotesSoap[] soapModels = new ReleaseNotesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ReleaseNotesSoap[][] toSoapModels(ReleaseNotes[][] models) {
		ReleaseNotesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ReleaseNotesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ReleaseNotesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ReleaseNotesSoap[] toSoapModels(List<ReleaseNotes> models) {
		List<ReleaseNotesSoap> soapModels = new ArrayList<ReleaseNotesSoap>(
			models.size());

		for (ReleaseNotes model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReleaseNotesSoap[soapModels.size()]);
	}

	public ReleaseNotesSoap() {
	}

	public long getPrimaryKey() {
		return _releaseNotesId;
	}

	public void setPrimaryKey(long pk) {
		setReleaseNotesId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getReleaseNotesId() {
		return _releaseNotesId;
	}

	public void setReleaseNotesId(long releaseNotesId) {
		_releaseNotesId = releaseNotesId;
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

	public String getJiraIssueKeys() {
		return _jiraIssueKeys;
	}

	public void setJiraIssueKeys(String jiraIssueKeys) {
		_jiraIssueKeys = jiraIssueKeys;
	}

	private String _uuid;
	private long _releaseNotesId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _jiraIssueKeys;

}