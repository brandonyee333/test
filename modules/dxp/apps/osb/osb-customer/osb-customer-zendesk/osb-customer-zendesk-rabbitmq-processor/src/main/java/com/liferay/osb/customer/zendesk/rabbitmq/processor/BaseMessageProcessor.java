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

package com.liferay.osb.customer.zendesk.rabbitmq.processor;

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class BaseMessageProcessor implements MessageProcessor {

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				message.trim());

			doProcess(jsonObject);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);

			String detailMessage = e.getMessage();

			int statusCode = Integer.valueOf(
				detailMessage.substring(
					detailMessage.indexOf("Server returned status") + 23));

			if ((statusCode == 429) || _isStatus5XX(statusCode)) {
				try {
					Thread.sleep(90000);
				}
				catch (InterruptedException ie) {
					_log.error(ie, ie);
				}

				process(routingKey, message, properties);
			}
		}
	}

	protected abstract void doProcess(JSONObject jsonObject) throws Exception;

	protected void handleResponseErrors(JSONObject responseJSONObject)
		throws PortalException {

		JSONObject errorJSONObject = responseJSONObject.getJSONObject("error");

		if (errorJSONObject != null) {
			_log.error(errorJSONObject.toString());

			_log.error(responseJSONObject.toString());
		}
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		this.jsonFactory = jsonFactory;
	}

	protected JSONFactory jsonFactory;

	private boolean _isStatus5XX(int statusCode) {
		if ((statusCode == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) ||
			(statusCode == HttpServletResponse.SC_SERVICE_UNAVAILABLE)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageProcessor.class);

}