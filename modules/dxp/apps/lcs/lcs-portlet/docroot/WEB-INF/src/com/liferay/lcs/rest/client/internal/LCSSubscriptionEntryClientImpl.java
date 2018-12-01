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

import com.liferay.lcs.rest.client.LCSSubscriptionEntry;
import com.liferay.lcs.rest.client.LCSSubscriptionEntryClient;
import com.liferay.lcs.rest.client.exception.LCSClientInternalException;
import com.liferay.lcs.rest.client.exception.LCSClientRemoteAuthorizationException;
import com.liferay.lcs.rest.client.exception.LCSClientRemoteException;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.util.StringBundler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Igor Beslic
 */
public class LCSSubscriptionEntryClientImpl
	implements LCSSubscriptionEntryClient {

	@Override
	public LCSSubscriptionEntry fetchLCSSubscriptionEntry(String key)
		throws LCSClientInternalException,
			   LCSClientRemoteAuthorizationException,
			   LCSClientRemoteException {

		try {
			return _jsonWebServiceClient.doGetToObject(
				LCSSubscriptionEntry.class,
				_URL_LCS_SUBSCRIPTION_ENTRY + "/find/" + key);
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw new LCSClientRemoteException(
				"Unable to execute remote request", jsonwsie);
		}
		catch (JSONWebServiceSerializeException jsonwsse) {
			throw new LCSClientInternalException(
				"Error communicating with LCS. A message in an unexpected " +
					"format caused a serialization error.",
				jsonwsse);
		}
		catch (JSONWebServiceTransportException jsonwste) {
			if (jsonwste instanceof
					JSONWebServiceTransportException.AuthenticationFailure) {

				StringBundler sb = new StringBundler(4);

				sb.append("Unable to communicate with LCS. The user");
				sb.append("credentials in the environment token were ");
				sb.append("rejected. Please regenerate, download, and ");
				sb.append("install a new token.");

				throw new LCSClientRemoteAuthorizationException(
					sb.toString(), jsonwste);
			}

			throw new LCSClientRemoteException(
				"Unable to communicate with LCS", jsonwste);
		}
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	private static final String _URL_LCS_SUBSCRIPTION_ENTRY =
		"/o/osb-lcs-rest/LCSSubscriptionEntry";

	private JSONWebServiceClient _jsonWebServiceClient;

}