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