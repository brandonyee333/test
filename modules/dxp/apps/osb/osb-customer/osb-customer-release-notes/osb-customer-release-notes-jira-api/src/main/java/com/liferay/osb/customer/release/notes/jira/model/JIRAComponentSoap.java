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
public class JIRAComponentSoap implements Serializable {

	public static JIRAComponentSoap toSoapModel(JIRAComponent model) {
		JIRAComponentSoap soapModel = new JIRAComponentSoap();

		soapModel.setJiraComponentId(model.getJiraComponentId());
		soapModel.setJiraProjectId(model.getJiraProjectId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static JIRAComponentSoap[] toSoapModels(JIRAComponent[] models) {
		JIRAComponentSoap[] soapModels = new JIRAComponentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAComponentSoap[][] toSoapModels(JIRAComponent[][] models) {
		JIRAComponentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAComponentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAComponentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAComponentSoap[] toSoapModels(List<JIRAComponent> models) {
		List<JIRAComponentSoap> soapModels = new ArrayList<JIRAComponentSoap>(
			models.size());

		for (JIRAComponent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAComponentSoap[soapModels.size()]);
	}

	public JIRAComponentSoap() {
	}

	public long getPrimaryKey() {
		return _jiraComponentId;
	}

	public void setPrimaryKey(long pk) {
		setJiraComponentId(pk);
	}

	public long getJiraComponentId() {
		return _jiraComponentId;
	}

	public void setJiraComponentId(long jiraComponentId) {
		_jiraComponentId = jiraComponentId;
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

	private long _jiraComponentId;
	private long _jiraProjectId;
	private String _name;

}