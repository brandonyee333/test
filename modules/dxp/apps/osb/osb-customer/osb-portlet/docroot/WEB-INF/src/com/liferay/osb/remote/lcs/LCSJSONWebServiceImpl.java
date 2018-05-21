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

package com.liferay.osb.remote.lcs;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.remote.BaseWebService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class LCSJSONWebServiceImpl
	extends BaseWebService implements LCSJSONWebService {

	@Override
	public void sendLCSSubscriptionEntries(
			long corpProjectId, String lcsSubscriptionEntriesJSON)
		throws RemoteServiceException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("corpProjectId", String.valueOf(corpProjectId));
		parameters.put("lcsSubscriptionEntries", lcsSubscriptionEntriesJSON);

		doPost(
			_URL_API_JSONWS_LCS_GATEWAY + "/send-lcs-subscription-entries",
			parameters);
	}

	@Override
	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		headers.put(
			"OSB_LCS_API_Token",
			PortletPropsValues.REMOTE_JSON_SERVICE_API_LCS_TOKEN);

		super.addHeaders(httpMessage, headers);
	}

	private static final String _URL_API_JSONWS =
		"/osb-lcs-gateway-web/api/jsonws";

	private static final String _URL_API_JSONWS_LCS_GATEWAY =
		_URL_API_JSONWS + "/lcsgateway";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSJSONWebServiceImpl.class);

}