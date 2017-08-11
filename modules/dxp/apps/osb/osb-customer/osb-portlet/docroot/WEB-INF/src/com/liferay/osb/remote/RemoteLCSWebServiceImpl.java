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

package com.liferay.osb.remote;

import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientHandler;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.portlet.PortletProps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class RemoteLCSWebServiceImpl
	extends BaseJSONWebServiceClientHandler implements RemoteLCSWebService {

	public void deleteLCSMessage(
		long corpProjectId, long corpProjectMessageId) {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("corpProjectId", String.valueOf(corpProjectId));
		parameters.put("sourceMessageId", String.valueOf(corpProjectMessageId));

		doPost(_URL_API_JSONWS_LCS_GATEWAY + "/delete-lcs-message", parameters);
	}

	@Override
	public JSONWebServiceClient getJSONWebServiceClient() {
		return _jsonWebServiceClient;
	}

	public void sendLCSMessage(
		long corpProjectId, long corpProjectMessageId, String content,
		int severityLevel, String title, int type) {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("content", content);
		parameters.put("corpProjectId", String.valueOf(corpProjectId));
		parameters.put("severityLevel", String.valueOf(severityLevel));
		parameters.put("sourceMessageId", String.valueOf(corpProjectMessageId));
		parameters.put("title", title);
		parameters.put("type", String.valueOf(type));

		doPost(_URL_API_JSONWS_LCS_GATEWAY + "/send-lcs-message", parameters);
	}

	public void sendLCSSubscriptionEntries(
		long corpProjectId, String lcsSubscriptionEntriesJSON) {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("corpProjectId", String.valueOf(corpProjectId));
		parameters.put("lcsSubscriptionEntries", lcsSubscriptionEntriesJSON);

		doPost(
			_URL_API_JSONWS_LCS_GATEWAY + "/send-lcs-subscription-entries",
			parameters);
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	protected void doPost(String url, Map<String, String> parameters) {
		Map<String, String> headers = new HashMap<>();

		headers.put(
			"OSB_LCS_API_Token",
			PortletPropsValues.REMOTE_JSON_SERVICE_API_LCS_TOKEN);

		try {
			if (_log.isDebugEnabled()) {
				StringBundler sb = new StringBundler(6);

				sb.append(
					PortletProps.get(
						PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_PROTOCOL));
				sb.append("://");
				sb.append(
					PortletProps.get(
						PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_HOST));
				sb.append(StringPool.COLON);
				sb.append(
					PortletProps.get(
						PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_PORT));
				sb.append(url);

				_log.debug("Sending request to: " + sb.toString());

				_log.debug("Parameters: " + MapUtil.toString(parameters));
			}

			doPost(url, parameters, headers);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static final String _URL_API_JSONWS =
		"/osb-lcs-gateway-web/api/jsonws";

	private static final String _URL_API_JSONWS_LCS_GATEWAY =
		_URL_API_JSONWS + "/lcsgateway";

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteLCSWebServiceImpl.class);

	private JSONWebServiceClient _jsonWebServiceClient;

}