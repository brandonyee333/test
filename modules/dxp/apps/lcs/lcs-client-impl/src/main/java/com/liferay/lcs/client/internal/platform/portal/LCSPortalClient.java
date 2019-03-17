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

import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

import java.util.List;

/**
 * @author Igor Beslic
 */
public interface LCSPortalClient {

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public boolean isAuthorized(
			String oauthAccessSecret, String oauthAccessToken)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

	public void resetHttpClient();

	public void testOAuthRequest()
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException;

}