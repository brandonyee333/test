/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.remote.web;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.remote.BaseWebService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class WebRESTWebServiceImpl
	extends BaseWebService implements WebRESTWebService {

	@Override
	public void deleteCorpProjectMessages(String corpProjectMessageUUID)
		throws RemoteServiceException {

		doDelete(_URL_API_REST_CORP_PROJECT_MESSAGES + corpProjectMessageUUID);
	}

	@Override
	public void deleteCorpProjects(String corpProjectUUID)
		throws RemoteServiceException {

		doDelete(_URL_API_REST_CORP_PROJECTS + corpProjectUUID);
	}

	@Override
	public void deleteOrganizationsUser(
			String organizationUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doDelete(
			_URL_API_REST_ORGANIZATIONS + organizationUUID + "/user",
			parameters);
	}

	@Override
	public void deleteRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doDelete(_URL_API_REST_ROLES + roleUUID + "/user", parameters);
	}

	@Override
	public JSONObject getUsersEmailAddress(String emailAddress)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("emailAddress", emailAddress);

		return doGetToJSONObject(
			_URL_API_REST_USERS + "email_address", parameters);
	}

	@Override
	public JSONObject postCorpProjectMessages(
			String userUUID, String corpProjectUUID, int type,
			int severityLevel, String title, String content, boolean displayCP,
			boolean displayLCS)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("content", content);
		parameters.put("corpProjectUUID", corpProjectUUID);
		parameters.put("displayCP", String.valueOf(displayCP));
		parameters.put("displayLCS", String.valueOf(displayLCS));
		parameters.put("displayLESA", String.valueOf(Boolean.FALSE));
		parameters.put("severityLevel", String.valueOf(severityLevel));
		parameters.put("title", title);
		parameters.put("type", String.valueOf(type));
		parameters.put("userUUID", userUUID);

		return doPostToJSONObject(
			_URL_API_REST_CORP_PROJECT_MESSAGES, parameters);
	}

	@Override
	public JSONObject postCorpProjects(
			String creatorUserUUID, String ownerUserUUID,
			String dossieraProjectKey, String salesforceProjectKey, String name)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("creatorUserUUID", creatorUserUUID);
		parameters.put("dossieraProjectKey", dossieraProjectKey);
		parameters.put("name", name);
		parameters.put("ownerUserUUID", ownerUserUUID);
		parameters.put("salesforceProjectKey", salesforceProjectKey);

		return doPostToJSONObject(_URL_API_REST_CORP_PROJECTS, parameters);
	}

	@Override
	public JSONObject putCorpProjects(String corpProjectUUID, String name)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("name", name);

		return doPutToJSONObject(
			_URL_API_REST_CORP_PROJECTS + corpProjectUUID, parameters);
	}

	@Override
	public void putCorpProjectsUser(String corpProjectUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doPut(
			_URL_API_REST_CORP_PROJECTS + corpProjectUUID + "/user",
			parameters);
	}

	@Override
	public void putCorpProjectsUserRole(
			String corpProjectUUID, String userUUID, String roleUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("roleUUID", roleUUID);
		parameters.put("userUUID", userUUID);

		doPut(
			_URL_API_REST_CORP_PROJECTS + corpProjectUUID + "/user_role",
			parameters);
	}

	@Override
	public void putOrganizationsUser(String organizationUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doPut(
			_URL_API_REST_ORGANIZATIONS + organizationUUID + "/user",
			parameters);
	}

	@Override
	public void putRolesUser(String roleUUID, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doPut(_URL_API_REST_ROLES + roleUUID + "/user", parameters);
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"Authorization",
			"token " + PortletPropsValues.REMOTE_REST_SERVICE_API_WEB_TOKEN);

		super.addHeaders(httpMessage, headers);
	}

	protected JSONObject doGetToJSONObject(
			String url, Map<String, String> parameters)
		throws RemoteServiceException {

		try {
			String response = doGet(url, parameters);

			if (response == null) {
				return null;
			}

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new RemoteServiceException(e);
		}
	}

	protected JSONObject doPostToJSONObject(
			String url, Map<String, String> parameters)
		throws RemoteServiceException {

		try {
			String response = doPost(url, parameters);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new RemoteServiceException(e);
		}
	}

	protected JSONObject doPutToJSONObject(
			String url, Map<String, String> parameters)
		throws RemoteServiceException {

		try {
			String response = doPut(url, parameters);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new RemoteServiceException(e);
		}
	}

	private static final String _URL_API_REST = "/osb-entity-web";

	private static final String _URL_API_REST_CORP_PROJECT_MESSAGES =
		_URL_API_REST + "/corp_project_messages/";

	private static final String _URL_API_REST_CORP_PROJECTS =
		_URL_API_REST + "/corp_projects/";

	private static final String _URL_API_REST_ORGANIZATIONS =
		_URL_API_REST + "/organizations/";

	private static final String _URL_API_REST_ROLES = _URL_API_REST + "/roles/";

	private static final String _URL_API_REST_USERS = _URL_API_REST + "/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		WebRESTWebServiceImpl.class);

}