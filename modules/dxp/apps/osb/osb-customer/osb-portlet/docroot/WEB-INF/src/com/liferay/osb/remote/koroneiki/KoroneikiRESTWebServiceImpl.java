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

package com.liferay.osb.remote.koroneiki;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.remote.BaseWebService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class KoroneikiRESTWebServiceImpl
	extends BaseWebService implements KoroneikiRESTWebService {

	@Override
	public void deleteAccounts(String accountKey)
		throws RemoteServiceException {

		doDelete(_URL_API_REST_ACCOUNTS + StringPool.SLASH + accountKey);
	}

	@Override
	public String doDelete(String url) throws RemoteServiceException {
		try {
			return super.doDelete(url);
		}
		catch (RemoteServiceException rse) {
			sendEmail(rse, null);

			throw rse;
		}
	}

	@Override
	public String doPut(String url) throws RemoteServiceException {
		try {
			return super.doPut(url);
		}
		catch (RemoteServiceException rse) {
			sendEmail(rse, null);

			throw rse;
		}
	}

	@Override
	public JSONObject getAccounts(
			String domain, String entityName, String entityId)
		throws RemoteServiceException {

		StringBundler sb = new StringBundler(7);

		sb.append(_URL_API_REST_ACCOUNTS);
		sb.append("/by-external-link/");
		sb.append(domain);
		sb.append(StringPool.SLASH);
		sb.append(entityName);
		sb.append(StringPool.SLASH);
		sb.append(entityId);

		return doGetToJSONObject(sb.toString());
	}

	@Override
	public JSONObject getContacts(String uuid) throws RemoteServiceException {
		try {
			return doGetToJSONObject(
				_URL_API_REST_CONTACTS + "/by-uuid/" + uuid);
		}
		catch (RemoteServiceException rse) {
			if (rse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			sendEmail(rse, null);

			throw rse;
		}
	}

	@Override
	public JSONObject postAccountExternalLinks(
			String accountKey, String domain, String entityName,
			String entityId)
		throws RemoteServiceException {

		StringBundler sb = new StringBundler(4);

		sb.append(_URL_API_REST_ACCOUNTS);
		sb.append(StringPool.SLASH);
		sb.append(accountKey);
		sb.append("/external-links");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("domain", domain);
		jsonObject.put("entityId", entityId);
		jsonObject.put("entityName", entityName);

		return doPostToJSONObject(sb.toString(), jsonObject);
	}

	@Override
	public JSONObject postAccounts(String parentAccountKey, String name)
		throws RemoteServiceException {

		StringBundler sb = new StringBundler(4);

		sb.append(_URL_API_REST_ACCOUNTS);

		if (Validator.isNotNull(parentAccountKey)) {
			sb.append(StringPool.SLASH);
			sb.append(parentAccountKey);
			sb.append("/child-accounts");
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("name", name);
		jsonObject.put("status", "approved");

		return doPostToJSONObject(sb.toString(), jsonObject);
	}

	@Override
	public JSONObject postContacts(
			String uuid, String emailAddress, String firstName,
			String middleName, String lastName, String languageId)
		throws RemoteServiceException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("emailAddress", emailAddress);
		jsonObject.put("firstName", firstName);
		jsonObject.put("languageId", languageId);
		jsonObject.put("lastName", lastName);
		jsonObject.put("middleName", middleName);
		jsonObject.put("uuid", uuid);

		return doPostToJSONObject(_URL_API_REST_CONTACTS, jsonObject);
	}

	@Override
	public void putAccountContactRoles(
			String accountKey, String contactUuid, String[] contactRoleKeys)
		throws RemoteServiceException {

		StringBundler sb = new StringBundler(7);

		sb.append(_URL_API_REST_ACCOUNTS);
		sb.append(StringPool.SLASH);
		sb.append(accountKey);
		sb.append("/contacts/by-uuid/");
		sb.append(contactUuid);
		sb.append("/roles?");
		sb.append(toUrlParameters("contactRoleKeys", contactRoleKeys));

		doPut(sb.toString());
	}

	@Override
	public void putAccountContacts(String accountKey, String[] contactUuids)
		throws RemoteServiceException {

		StringBundler sb = new StringBundler(5);

		sb.append(_URL_API_REST_ACCOUNTS);
		sb.append(StringPool.SLASH);
		sb.append(accountKey);
		sb.append("/contacts/by-uuid?");
		sb.append(toUrlParameters("contactUuids", contactUuids));

		doPut(sb.toString());
	}

	@Override
	public JSONObject putAccounts(String accountKey, String name)
		throws RemoteServiceException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("name", name);

		return doPutToJSONObject(
			_URL_API_REST_ACCOUNTS + StringPool.SLASH + accountKey, jsonObject);
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"API_Token",
			PortletPropsValues.REMOTE_REST_SERVICE_API_KORONEIKI_TOKEN);
		headers.put("Content-Type", "application/json");

		super.addHeaders(httpMessage, headers);
	}

	protected JSONObject doGetToJSONObject(String url)
		throws RemoteServiceException {

		try {
			String response = super.doGet(url);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			sendEmail(e, null);

			if (e instanceof RemoteServiceException) {
				throw (RemoteServiceException)e;
			}

			throw new RemoteServiceException(e);
		}
	}

	protected JSONObject doPostToJSONObject(String url, JSONObject jsonObject)
		throws RemoteServiceException {

		try {
			String response = super.doPostAsJSON(url, jsonObject.toString());

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			sendEmail(e, jsonObject);

			if (e instanceof RemoteServiceException) {
				throw (RemoteServiceException)e;
			}

			throw new RemoteServiceException(e);
		}
	}

	protected JSONObject doPutToJSONObject(String url, JSONObject jsonObject)
		throws RemoteServiceException {

		try {
			String response = super.doPutAsJSON(url, jsonObject.toString());

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			sendEmail(e, jsonObject);

			if (e instanceof RemoteServiceException) {
				throw (RemoteServiceException)e;
			}

			throw new RemoteServiceException(e);
		}
	}

	protected void sendEmail(Exception e, JSONObject jsonObject) {
		StringBundler sb = new StringBundler(5);

		if (jsonObject != null) {
			sb.append("<strong>Body: </strong><br />");
			sb.append(jsonObject.toString());
			sb.append("<br /><br />");
		}

		sb.append("<strong>Stack Trace:</strong><br />");

		String stackTrace = StackTraceUtil.getStackTrace(e);

		sb.append(StringUtil.replace(stackTrace, CharPool.NEW_LINE, "<br />"));

		try {
			InternetAddress from = new InternetAddress("noreply@liferay.com");
			InternetAddress to = new InternetAddress(
				PortletPropsValues.
					REMOTE_REST_SERVICE_API_KORONEIKI_ERROR_EMAIL_ADDRESS);

			String mailSubject = "Auto Generated Koroneiki API Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, sb.toString(), true);

			MailServiceUtil.sendEmail(mailMessage);
		}
		catch (AddressException ae) {
			_log.error(ae, ae);
		}
	}

	protected String toUrlParameters(String parameterName, String[] values) {
		StringBundler sb = new StringBundler((values.length * 4) - 1);

		for (int i = 0; i < values.length; i++) {
			sb.append(parameterName);
			sb.append(StringPool.EQUAL);
			sb.append(values[i]);

			if ((i + 1) < values.length) {
				sb.append(StringPool.AMPERSAND);
			}
		}

		return sb.toString();
	}

	private static final String _URL_API_REST = "/o/koroneiki-rest/v1.0";

	private static final String _URL_API_REST_ACCOUNTS =
		_URL_API_REST + "/accounts";

	private static final String _URL_API_REST_CONTACTS =
		_URL_API_REST + "/contacts";

	private static final Log _log = LogFactoryUtil.getLog(
		KoroneikiRESTWebServiceImpl.class);

}