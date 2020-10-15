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

package com.liferay.osb.customer.identity.management.internal.provider;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.customer.identity.management.internal.configuration.WebUserIdentityConfiguration;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpMessage;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.nio.reactor.IOReactorException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "provider=web",
	service = UserIdentityProvider.class
)
public class WebUserIdentityProvider
	extends BaseJSONWebServiceClientImpl implements UserIdentityProvider {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	public User fetchUserByEmailAddress(String emailAddress) throws Exception {
		User user = _userLocalService.fetchUserByEmailAddress(
			_companyId, emailAddress);

		if (user == null) {
			user = _importUserByEmailAddress(emailAddress);
		}

		return user;
	}

	public User fetchUserByProviderId(String providerId) throws Exception {
		try {
			return getUserByProviderId(providerId);
		}
		catch (NoSuchUserException nsue) {
			return _importUserByUuid(providerId);
		}
	}

	public User getUserByEmailAddress(String emailAddress) throws Exception {
		User user = fetchUserByEmailAddress(emailAddress);

		if (user == null) {
			throw new NoSuchUserException();
		}

		return user;
	}

	public User getUserByProviderId(String providerId) throws Exception {
		return _userLocalService.getUserByUuidAndCompanyId(
			providerId, _companyId);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws Exception {
		_webUserIdentityConfiguration = ConfigurableUtil.createConfigurable(
			WebUserIdentityConfiguration.class, properties);

		_companyId = _portalInstancesLocalService.getDefaultCompanyId();

		User user = _userLocalService.getDefaultUser(_companyId);

		_defaultUserId = user.getUserId();
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"Authorization",
			"token " + _webUserIdentityConfiguration.apiToken());

		super.addHeaders(httpMessage, headers);
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(_webUserIdentityConfiguration.host());
		setHostPort(_webUserIdentityConfiguration.port());
		setProtocol(_webUserIdentityConfiguration.protocol());

		return super.execute(httpRequestBase);
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private JSONObject _getToJSONObject(
			String url, Map<String, String> parameters)
		throws Exception {

		try {
			String response = doGet(url, parameters, new HashMap<>());

			return _jsonFactory.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			_sendEmail(jsonwsie, parameters);

			throw jsonwsie;
		}
		catch (Exception e) {
			_sendEmail(e, parameters);

			throw e;
		}
	}

	private User _importUserByEmailAddress(String emailAddress)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("emailAddress", emailAddress);

		JSONObject jsonObject = _getToJSONObject(
			_URL_API_REST_USERS + "email_address", parameters);

		if (jsonObject == null) {
			return null;
		}

		Locale locale = LocaleUtil.fromLanguageId(
			jsonObject.getString("languageId"));

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(jsonObject.getString("uuid"));

		return _userLocalService.addUser(
			_defaultUserId, _companyId, true, StringPool.BLANK,
			StringPool.BLANK, false, jsonObject.getString("screenName"),
			jsonObject.getString("emailAddress"), 0, StringPool.BLANK, locale,
			jsonObject.getString("firstName"),
			jsonObject.getString("middleName"),
			jsonObject.getString("lastName"), 0, 0, false, 0, 1, 1970,
			StringPool.BLANK, new long[0], new long[0], new long[0],
			new long[0], false, serviceContext);
	}

	private User _importUserByUuid(String uuid) throws Exception {
		JSONObject jsonObject = _getToJSONObject(
			_URL_API_REST_USERS + uuid, new HashMap<>());

		if (jsonObject == null) {
			return null;
		}

		Locale locale = LocaleUtil.fromLanguageId(
			jsonObject.getString("languageId"));

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(jsonObject.getString("uuid"));

		return _userLocalService.addUser(
			_defaultUserId, _companyId, true, StringPool.BLANK,
			StringPool.BLANK, false, jsonObject.getString("screenName"),
			jsonObject.getString("emailAddress"), 0, StringPool.BLANK, locale,
			jsonObject.getString("firstName"),
			jsonObject.getString("middleName"),
			jsonObject.getString("lastName"), 0, 0, false, 0, 1, 1970,
			StringPool.BLANK, new long[0], new long[0], new long[0],
			new long[0], false, serviceContext);
	}

	private void _sendEmail(Exception e, Map<String, String> parameters) {
		if (Validator.isNull(
				_webUserIdentityConfiguration.errorEmailAddress())) {

			return;
		}

		StringBundler sb = new StringBundler(5);

		if (parameters != null) {
			sb.append("<strong>Parameters: </strong><br />");
			sb.append(MapUtil.toString(parameters));
			sb.append("<br /><br />");
		}

		sb.append("<strong>Stack Trace:</strong><br />");

		sb.append(
			StringUtil.replace(
				StackTraceUtil.getStackTrace(e), CharPool.NEW_LINE, "<br />"));

		try {
			InternetAddress from = new InternetAddress("noreply@liferay.com");
			InternetAddress to = new InternetAddress(
				_webUserIdentityConfiguration.errorEmailAddress());

			MailMessage mailMessage = new MailMessage(
				from, to, "Auto Generated Web API Error Message", sb.toString(),
				true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException ae) {
			_log.error(ae, ae);
		}
	}

	private static final String _URL_API_REST_USERS = "/osb-entity-web/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		WebUserIdentityProvider.class);

	private long _companyId;
	private long _defaultUserId;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MailService _mailService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private UserLocalService _userLocalService;

	private volatile WebUserIdentityConfiguration _webUserIdentityConfiguration;

}