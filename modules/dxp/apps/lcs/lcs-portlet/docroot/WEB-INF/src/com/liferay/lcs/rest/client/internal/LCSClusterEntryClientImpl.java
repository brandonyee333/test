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

package com.liferay.lcs.rest.client.internal;

import com.liferay.lcs.rest.client.LCSClusterEntry;
import com.liferay.lcs.rest.client.LCSClusterEntryClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterEntryClientImpl implements LCSClusterEntryClient {

	@Override
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return _jsonWebServiceClient.doGetToObject(
			LCSClusterEntry.class, _URL_LCS_CLUSTER_ENTRY, "lcsClusterEntryId",
			String.valueOf(lcsClusterEntryId));
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	private static final String _URL_LCS_CLUSTER_ENTRY =
		"/osb-lcs-portlet/lcs/jsonws/v1_4/LCSClusterEntry";

	private JSONWebServiceClient _jsonWebServiceClient;

}