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

package com.liferay.osb.remote.web;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.remote.BaseWebService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class WebRESTWebServiceImpl
	extends BaseWebService implements WebRESTWebService {

	@Override
	public void deleteCorpEntriesUser(
			String dossieraAccountKey, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doDelete(
			_URL_API_REST_CORP_ENTRIES + dossieraAccountKey + "/user",
			parameters);
	}

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
	public String doDelete(String url) throws RemoteServiceException {
		try {
			return super.doDelete(url);
		}
		catch (RemoteServiceException rse) {
			sendEmail(StackTraceUtil.getStackTrace(rse));

			throw rse;
		}
	}

	@Override
	public String doDelete(String url, Map<String, String> parameters)
		throws RemoteServiceException {

		try {
			return super.doDelete(url, parameters);
		}
		catch (RemoteServiceException rse) {
			sendEmail(StackTraceUtil.getStackTrace(rse));

			throw rse;
		}
	}

	@Override
	public String doPut(String url, Map<String, String> parameters)
		throws RemoteServiceException {

		try {
			return super.doPut(url, parameters);
		}
		catch (RemoteServiceException rse) {
			sendEmail(StackTraceUtil.getStackTrace(rse));

			throw rse;
		}
	}

	@Override
	public JSONObject getUsers(String uuid) throws RemoteServiceException {
		return doGetToJSONObject(_URL_API_REST_USERS + uuid, new HashMap<>());
	}

	@Override
	public JSONObject getUsersEmailAddress(String emailAddress)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("emailAddress", emailAddress);

		try {
			return doGetToJSONObject(
				_URL_API_REST_USERS + "email_address", parameters);
		}
		catch (RemoteServiceException rse) {
			if (rse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw rse;
		}
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
	public void putCorpEntriesUser(String dossieraAccountKey, String userUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("userUUID", userUUID);

		doPut(
			_URL_API_REST_CORP_ENTRIES + dossieraAccountKey + "/user",
			parameters);
	}

	@Override
	public void putCorpEntriesUserRole(
			String dossieraAccountKey, String userUUID, String roleUUID)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("roleUUID", roleUUID);
		parameters.put("userUUID", userUUID);

		doPut(
			_URL_API_REST_CORP_ENTRIES + dossieraAccountKey + "/user_role",
			parameters);
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
	public void putUsersExpandoValue(
			String userUUID, String expandoTableName, String expandoColumnName,
			String data)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("data", data);
		parameters.put("expandoColumnName", expandoColumnName);
		parameters.put("expandoTableName", expandoTableName);

		doPut(_URL_API_REST_USERS + userUUID + "/expando_Value", parameters);
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

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			sendEmail(StackTraceUtil.getStackTrace(e));

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
			sendEmail(StackTraceUtil.getStackTrace(e));

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
			sendEmail(StackTraceUtil.getStackTrace(e));

			throw new RemoteServiceException(e);
		}
	}

	protected void sendEmail(String mailBody) {
		try {
			InternetAddress from = new InternetAddress("noreply@liferay.com");
			InternetAddress to = new InternetAddress(
				PortletPropsValues.
					REMOTE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS);

			String mailSubject = "Auto Generated Web API Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, mailBody, true);

			MailServiceUtil.sendEmail(mailMessage);
		}
		catch (AddressException ae) {
			_log.error(ae, ae);
		}
	}

	private static final String _URL_API_REST = "/osb-entity-web";

	private static final String _URL_API_REST_CORP_ENTRIES =
		_URL_API_REST + "/corp_entries/";

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