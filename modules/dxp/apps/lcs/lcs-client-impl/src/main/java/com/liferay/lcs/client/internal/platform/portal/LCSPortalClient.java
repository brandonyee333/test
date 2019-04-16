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

import com.liferay.lcs.client.platform.exception.LCSException;

import java.util.List;

/**
 * @author Igor Beslic
 */
public interface LCSPortalClient {

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws LCSException;

	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws LCSException;

	public boolean isAuthorized(
			String oauthAccessSecret, String oauthAccessToken)
		throws LCSException;

	public void resetHttpClient();

	public void testOAuthRequest() throws LCSException;

}