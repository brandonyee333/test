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

package com.liferay.osb.customer.legacy.web.service.internal;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.customer.legacy.web.service.LegacyWebService;
import com.liferay.osb.customer.legacy.web.service.internal.configuration.LegacyConfiguration;
import com.liferay.osb.customer.legacy.web.service.util.LegacyConverter;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
	configurationPid = "com.liferay.osb.customer.legacy.web.service.internal.configuration.LegacyConfiguration",
	immediate = true, service = LegacyWebService.class
)
public class LegacyWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements LegacyWebService {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	public User getUser(String uuid) throws PortalException {
		JSONObject jsonObject = doGetToJSONObject(
			_URL_API_REST_USERS + uuid, new HashMap<>());

		return _legacyConverter.toUser(jsonObject);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_legacyConfiguration = ConfigurableUtil.createConfigurable(
			LegacyConfiguration.class, properties);
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"Authorization", "token " + _legacyConfiguration.apiToken());

		super.addHeaders(httpMessage, headers);
	}

	protected JSONObject doGetToJSONObject(
			String url, Map<String, String> parameters)
		throws PortalException {

		try {
			String response = doGet(url, parameters, new HashMap<>());

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			sendEmail(e, parameters);

			throw new PortalException(e);
		}
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(_legacyConfiguration.host());
		setHostPort(_legacyConfiguration.port());
		setProtocol(_legacyConfiguration.protocol());

		return super.execute(httpRequestBase);
	}

	protected void sendEmail(Exception e, Map<String, String> parameters) {
		StringBundler sb = new StringBundler(5);

		if (parameters != null) {
			sb.append("<strong>Parameters: </strong><br />");
			sb.append(MapUtil.toString(parameters));
			sb.append("<br /><br />");
		}

		sb.append("<strong>Stack Trace:</strong><br />");

		String stackTrace = StackTraceUtil.getStackTrace(e);

		sb.append(StringUtil.replace(stackTrace, CharPool.NEW_LINE, "<br />"));

		try {
			InternetAddress from = new InternetAddress("noreply@liferay.com");
			InternetAddress to = new InternetAddress(
				_legacyConfiguration.errorEmailAddress());

			String mailSubject = "Auto Generated Web API Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, sb.toString(), true);

			_mailService.sendEmail(mailMessage);
		}
		catch (AddressException addressException) {
			_log.error(addressException, addressException);
		}
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private static final String _URL_API_REST = "/osb-entity-web";

	private static final String _URL_API_REST_USERS = _URL_API_REST + "/users/";

	private static final Log _log = LogFactoryUtil.getLog(
		LegacyWebServiceImpl.class);

	private volatile LegacyConfiguration _legacyConfiguration;

	@Reference
	private LegacyConverter _legacyConverter;

	@Reference
	private MailService _mailService;

}