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

import com.liferay.jsonwebserviceclient.BaseJSONWebServiceClientHandler;
import com.liferay.jsonwebserviceclient.JSONWebServiceClient;
import com.liferay.jsonwebserviceclient.JSONWebServiceTransportException;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class RemoteDossieraWebServiceImpl
	extends BaseJSONWebServiceClientHandler
	implements RemoteDossieraWebService {

	@Override
	public JSONWebServiceClient getJSONWebServiceClient() {
		return _jsonWebServiceClient;
	}

	public JSONArray getOpportunitiesJSONArray(String salesforceProjectKey)
		throws SystemException {

		return doGetJSONArray(
			_URL_API_REST_PURCHASED_PRODUCT + "/opportunities/" +
				salesforceProjectKey);
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	protected JSONArray doGetJSONArray(String url) throws SystemException {
		Map<String, String> headers = new HashMap<String, String>();

		headers.put(
			"Dossiera-API-Token",
			PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN);

		try {
			if (_log.isDebugEnabled()) {
				StringBundler sb = new StringBundler(6);

				sb.append(
					PortletPropsValues.
						REMOTE_REST_SERVICE_API_DOSSIERA_PROTOCOL);
				sb.append("://");
				sb.append(
					PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_HOST);
				sb.append(StringPool.COLON);
				sb.append(
					PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_PORT);
				sb.append(url);

				_log.debug("Sending GET request to: " + sb.toString());
			}

			String response = doGet(url, Collections.EMPTY_MAP, headers);

			return JSONFactoryUtil.createJSONArray(response);
		}
		catch (JSONWebServiceTransportException.CommunicationFailure cf) {
			if (cf.getStatus() == 404) {
				return null;
			}

			throw new SystemException(cf);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static final String _URL_API_REST = "/osb-dossiera-portlet/rest";

	private static final String _URL_API_REST_PURCHASED_PRODUCT =
		_URL_API_REST + "/purchased-product";

	private static Log _log = LogFactoryUtil.getLog(
		RemoteDossieraWebServiceImpl.class);

	private JSONWebServiceClient _jsonWebServiceClient;

}