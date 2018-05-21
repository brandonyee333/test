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