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

import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryTokenClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component
public class LCSClusterEntryTokenClientImpl
	implements LCSClusterEntryTokenClient {

	@Override
	public LCSClusterEntryToken fetchLCSClusterEntryToken(
			long lcsClusterEntryTokenId)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		try {
			LCSClusterEntryToken lcsClusterEntryToken =
				_lcsPortalClient.doGetToObject(
					LCSClusterEntryToken.class,
					_URL_LCS_CLUSTER_ENTRY_TOKEN + "/" +
						lcsClusterEntryTokenId);

			return lcsClusterEntryToken;
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw jsonwsie;
		}
	}

	private static final String _URL_LCS_CLUSTER_ENTRY_TOKEN =
		"/o/osb-lcs-rest/LCSClusterEntryToken";

	@Reference
	private LCSPortalClient _lcsPortalClient;

}