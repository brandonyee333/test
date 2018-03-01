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

package com.liferay.osb.remote.dossiera;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.remote.BaseWebService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class DossieraRESTWebServiceImpl
	extends BaseWebService implements DossieraRESTWebService {

	@Override
	public JSONArray getOpportunitiesJSONArray(String salesforceProjectKey)
		throws RemoteServiceException {

		return doGetToJSONArray(
			_URL_API_REST_PURCHASED_PRODUCT + "/opportunities/" +
				salesforceProjectKey);
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"Dossiera-API-Token",
			PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN);

		super.addHeaders(httpMessage, headers);
	}

	protected JSONArray doGetToJSONArray(String url)
		throws RemoteServiceException {

		try {
			String response = doGet(url);

			return JSONFactoryUtil.createJSONArray(response);
		}
		catch (Exception e) {
			throw new RemoteServiceException(e);
		}
	}

	private static final String _URL_API_REST = "/osb-dossiera-portlet/rest";

	private static final String _URL_API_REST_PURCHASED_PRODUCT =
		_URL_API_REST + "/purchased-product";

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraRESTWebServiceImpl.class);

}