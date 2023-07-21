/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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