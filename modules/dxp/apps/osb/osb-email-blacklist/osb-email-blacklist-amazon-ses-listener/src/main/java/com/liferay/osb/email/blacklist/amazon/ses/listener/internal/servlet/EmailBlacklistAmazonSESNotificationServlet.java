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

package com.liferay.osb.email.blacklist.amazon.ses.listener.internal.servlet;

import com.liferay.osb.email.blacklist.amazon.ses.listener.internal.configuration.EmailBlacklistAmazonSESListenerConfiguration;
import com.liferay.osb.email.blacklist.service.BlacklistEntryLocalService;
import com.liferay.osb.email.blacklist.service.BounceEntryLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Haote Chou
 * @author Jamie Sammons
 */
@Component(
	configurationPid = "com.liferay.osb.email.blacklist.amazon.ses.listener.internal.configuration.EmailBlacklistAmazonSESListenerConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {
		"osgi.http.whiteboard.context.path=/",
		"osgi.http.whiteboard.servlet.pattern=/notification/*"
	},
	service = Servlet.class
)
public class EmailBlacklistAmazonSESNotificationServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			if (!_validate(request)) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);

				return;
			}

			String messageType = request.getHeader("x-amz-sns-message-type");

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Received a message from SNS with type " + messageType);
			}

			if (messageType.equals("Notification")) {
				addEmailBounceEntries(request);
			}
			else if (messageType.equals("SubscriptionConfirmation")) {
				confirmSubscription(request);
			}
			else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		catch (Exception e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);

			_log.error(e, e);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_emailBlacklistAmazonSESListenerConfiguration =
			ConfigurableUtil.createConfigurable(
				EmailBlacklistAmazonSESListenerConfiguration.class, properties);
	}

	protected void addEmailBounceEntries(HttpServletRequest request)
		throws Exception {

		String body = StringUtil.read(request.getInputStream());

		JSONObject snsJSONObject = JSONFactoryUtil.createJSONObject(body);

		String message = snsJSONObject.getString("Message");

		JSONObject messageJSONObject = JSONFactoryUtil.createJSONObject(
			message);

		String notificationType = messageJSONObject.getString(
			"notificationType");

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received a notficiation from SNS with type " +
					notificationType);
		}

		if (!notificationType.equals("Bounce")) {
			return;
		}

		JSONObject bounceJSONObject = messageJSONObject.getJSONObject("bounce");

		JSONArray bouncedRecipientsJSONArray = bounceJSONObject.getJSONArray(
			"bouncedRecipients");

		Date bounceDate = _simpleDateFormat.parse(
			bounceJSONObject.getString("timestamp"));

		String bounceType = bounceJSONObject.getString("bounceType");
		String bounceSubtype = bounceJSONObject.getString("bounceSubType");

		for (int i = 0; i < bouncedRecipientsJSONArray.length(); i++) {
			JSONObject bouncedRecipientJSONObject =
				bouncedRecipientsJSONArray.getJSONObject(i);

			String emailAddress = bouncedRecipientJSONObject.getString(
				"emailAddress");

			if (bounceType.equals("Permanent")) {
				_blacklistEntryLocalService.addBlacklistEntry(emailAddress);
			}

			if (bounceType.equals("Transient") &&
				bounceSubtype.equals("General")) {

				return;
			}

			_bounceEntryLocalService.addBounceEntry(
				emailAddress, bounceDate, bounceType, bounceSubtype);
		}
	}

	protected void confirmSubscription(HttpServletRequest request)
		throws Exception {

		String body = StringUtil.read(request.getInputStream());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(body);

		URL url = new URL(jsonObject.getString("SubscribeURL"));

		HttpURLConnection httpURLConnection =
			(HttpURLConnection)url.openConnection();

		httpURLConnection.setRequestMethod("GET");

		httpURLConnection.connect();

		if (_log.isDebugEnabled()) {
			_log.debug("Confirming subscription to " + url);
		}
	}

	private boolean _validate(HttpServletRequest request) {
		String userAgent = GetterUtil.getString(
			request.getHeader("User-Agent"));
		String resourceName = GetterUtil.getString(
			request.getHeader("x-amz-sns-topic-arn"));

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Validating a message from SNS with user agent " + userAgent +
					" and resource name " + resourceName);
		}

		if (!_emailBlacklistAmazonSESListenerConfiguration.
				sesNotificationValidationEnabled()) {

			return true;
		}

		if (!request.isSecure()) {
			return false;
		}

		if (!userAgent.equals("Amazon Simple Notification Service Agent")) {
			return false;
		}

		if (!resourceName.equals(
				_emailBlacklistAmazonSESListenerConfiguration.
					sesBounceNotificationResourceName())) {

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EmailBlacklistAmazonSESNotificationServlet.class);

	private static final long serialVersionUID = 1L;

	@Reference
	private BlacklistEntryLocalService _blacklistEntryLocalService;

	@Reference
	private BounceEntryLocalService _bounceEntryLocalService;

	private volatile EmailBlacklistAmazonSESListenerConfiguration
		_emailBlacklistAmazonSESListenerConfiguration;
	private final SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(
		"yyyy-MM-dd'T'HH:mm:ss.SSSX");

}