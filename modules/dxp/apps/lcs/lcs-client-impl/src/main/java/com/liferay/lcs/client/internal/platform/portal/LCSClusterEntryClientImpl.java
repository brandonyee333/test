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

package com.liferay.lcs.client.internal.platform.portal;

import com.liferay.lcs.client.platform.portal.LCSClusterEntry;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component
public class LCSClusterEntryClientImpl implements LCSClusterEntryClient {

	@Override
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return _jsonWebServiceClient.doGetToObject(
			LCSClusterEntry.class,
			_URL_LCS_CLUSTER_ENTRY + "/" + lcsClusterEntryId);
	}

	private static final String _URL_LCS_CLUSTER_ENTRY =
		"/o/osb-lcs-rest/LCSClusterEntry";

	@Reference
	private LCSPortalClient _jsonWebServiceClient;

}