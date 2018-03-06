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

package com.liferay.osb.remote.lcs;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.remote.BaseRemoteService;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpMessage;

/**
 * @author Amos Fong
 */
public class RemoteLCSSubscriptionEntryServiceImpl
	extends BaseRemoteService implements RemoteLCSSubscriptionEntryService {

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
		RemoteLCSSubscriptionEntryServiceImpl.class);

}