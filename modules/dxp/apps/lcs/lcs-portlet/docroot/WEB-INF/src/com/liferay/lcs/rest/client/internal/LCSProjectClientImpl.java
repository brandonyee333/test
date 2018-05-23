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

package com.liferay.lcs.rest.client.internal;

import com.liferay.lcs.rest.client.LCSProject;
import com.liferay.lcs.rest.client.LCSProjectClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 */
public class LCSProjectClientImpl implements LCSProjectClient {

	@Override
	public List<LCSProject> getUserManageableLCSProjects()
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		List<LCSProject> remoteLCSProjects = null;

		remoteLCSProjects = _jsonWebServiceClient.doGetToList(
			LCSProject.class, _URL_LCS_PROJECT, "manage", "true");

		List<LCSProject> lcsProjects = new ArrayList<>();

		for (LCSProject lcsProject : remoteLCSProjects) {
			lcsProjects.add(lcsProject);
		}

		return lcsProjects;
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	private static final String _URL_LCS_PROJECT =
		"/osb-lcs-portlet/lcs/jsonws/v1_4/LCSProject";

	private JSONWebServiceClient _jsonWebServiceClient;

}