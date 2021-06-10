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

package com.liferay.osb.customer.admin.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProjectSolutionSoap implements Serializable {

	public static ProjectSolutionSoap toSoapModel(ProjectSolution model) {
		ProjectSolutionSoap soapModel = new ProjectSolutionSoap();

		soapModel.setSalesforceProjectKey(model.getSalesforceProjectKey());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static ProjectSolutionSoap[] toSoapModels(ProjectSolution[] models) {
		ProjectSolutionSoap[] soapModels =
			new ProjectSolutionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectSolutionSoap[][] toSoapModels(
		ProjectSolution[][] models) {

		ProjectSolutionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ProjectSolutionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectSolutionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectSolutionSoap[] toSoapModels(
		List<ProjectSolution> models) {

		List<ProjectSolutionSoap> soapModels =
			new ArrayList<ProjectSolutionSoap>(models.size());

		for (ProjectSolution model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectSolutionSoap[soapModels.size()]);
	}

	public ProjectSolutionSoap() {
	}

	public String getPrimaryKey() {
		return _salesforceProjectKey;
	}

	public void setPrimaryKey(String pk) {
		setSalesforceProjectKey(pk);
	}

	public String getSalesforceProjectKey() {
		return _salesforceProjectKey;
	}

	public void setSalesforceProjectKey(String salesforceProjectKey) {
		_salesforceProjectKey = salesforceProjectKey;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _salesforceProjectKey;
	private String _value;

}