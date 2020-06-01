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

package com.liferay.osb.customer.release.notes.jira.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAProjectVersionSoap implements Serializable {

	public static JIRAProjectVersionSoap toSoapModel(JIRAProjectVersion model) {
		JIRAProjectVersionSoap soapModel = new JIRAProjectVersionSoap();

		soapModel.setJiraProjectVersionId(model.getJiraProjectVersionId());
		soapModel.setJiraProjectId(model.getJiraProjectId());
		soapModel.setName(model.getName());
		soapModel.setReleased(model.getReleased());
		soapModel.setArchived(model.getArchived());

		return soapModel;
	}

	public static JIRAProjectVersionSoap[] toSoapModels(
		JIRAProjectVersion[] models) {

		JIRAProjectVersionSoap[] soapModels =
			new JIRAProjectVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAProjectVersionSoap[][] toSoapModels(
		JIRAProjectVersion[][] models) {

		JIRAProjectVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new JIRAProjectVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAProjectVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAProjectVersionSoap[] toSoapModels(
		List<JIRAProjectVersion> models) {

		List<JIRAProjectVersionSoap> soapModels =
			new ArrayList<JIRAProjectVersionSoap>(models.size());

		for (JIRAProjectVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new JIRAProjectVersionSoap[soapModels.size()]);
	}

	public JIRAProjectVersionSoap() {
	}

	public long getPrimaryKey() {
		return _jiraProjectVersionId;
	}

	public void setPrimaryKey(long pk) {
		setJiraProjectVersionId(pk);
	}

	public long getJiraProjectVersionId() {
		return _jiraProjectVersionId;
	}

	public void setJiraProjectVersionId(long jiraProjectVersionId) {
		_jiraProjectVersionId = jiraProjectVersionId;
	}

	public long getJiraProjectId() {
		return _jiraProjectId;
	}

	public void setJiraProjectId(long jiraProjectId) {
		_jiraProjectId = jiraProjectId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getReleased() {
		return _released;
	}

	public void setReleased(String released) {
		_released = released;
	}

	public String getArchived() {
		return _archived;
	}

	public void setArchived(String archived) {
		_archived = archived;
	}

	private long _jiraProjectVersionId;
	private long _jiraProjectId;
	private String _name;
	private String _released;
	private String _archived;

}