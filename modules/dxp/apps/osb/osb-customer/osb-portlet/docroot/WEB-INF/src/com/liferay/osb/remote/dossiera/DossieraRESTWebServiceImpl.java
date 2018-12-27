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
import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashMap;
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
	public JSONObject postProject(
			String dossieraAccountKey, String recordTypeId,
			String primaryContactEmailAddress, String primaryContactFirstName,
			String primaryContactLastName,
			String primaryContactMailingCountryCode, String currencyIsoCode)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("currencyIsoCode", currencyIsoCode);
		parameters.put("dossieraAccountKey", dossieraAccountKey);
		parameters.put(
			"primaryContactEmailAddress", primaryContactEmailAddress);
		parameters.put("primaryContactFirstName", primaryContactFirstName);
		parameters.put("primaryContactLastName", primaryContactLastName);
		parameters.put(
			"primaryContactMailingCountryCode",
			primaryContactMailingCountryCode);
		parameters.put("recordTypeId", recordTypeId);

		return doPostToJSONObject(_URL_API_REST_PROJECT, parameters);
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

	protected JSONObject doPostToJSONObject(
			String url, Map<String, String> parameters)
		throws RemoteServiceException {

		try {
			String response = doPost(url, parameters);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new RemoteServiceException(e);
		}
	}

	private static final String _URL_API_REST = "/osb-dossiera-portlet/rest";

	private static final String _URL_API_REST_PROJECT =
		_URL_API_REST + "/project";

	private static final String _URL_API_REST_PURCHASED_PRODUCT =
		_URL_API_REST + "/purchased-product";

}