/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.internal.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Calvin Keum
 */
public class TokenAuthValues {

	public static final String[] TOKEN_AUTHENTICATION_URLS_INCLUDES =
		StringUtil.split(
			TokenAuthUtil.get("token.authentication.urls.includes"));

	public static final long TOKEN_EXPIRATION_SECONDS = GetterUtil.getLong(
		TokenAuthUtil.get("token.expiration.seconds"));

	public static final boolean TOKEN_REVOKABLE = GetterUtil.getBoolean(
		TokenAuthUtil.get("token.revokable"));

	public static final String TOKEN_SECRET = TokenAuthUtil.get("token.secret");

}