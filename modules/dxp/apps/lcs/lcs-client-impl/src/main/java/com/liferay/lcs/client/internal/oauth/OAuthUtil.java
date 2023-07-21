/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.oauth;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletRequest;

/**
 * @author Igor Beslic
 */
public class OAuthUtil {

	public static boolean hasOAuthException(Exception e) {
		String message = e.getMessage();

		for (OAuthExceptionType oAuthExceptionType :
				OAuthExceptionType.values()) {

			String oAuthExceptionPattern = StringUtil.toLowerCase(
				oAuthExceptionType.name());

			if (message.contains(oAuthExceptionPattern)) {
				return true;
			}
		}

		return false;
	}

	public static boolean hasOAuthTokenRejectedException(Throwable throwable) {
		if (throwable.getMessage() == null) {
			return false;
		}

		String message = throwable.getMessage();
		String tokenRejectedPattern = StringUtil.toLowerCase(
			OAuthExceptionType.TOKEN_REJECTED.name());

		if (message.contains(tokenRejectedPattern)) {
			return true;
		}

		return false;
	}

	public static void processOAuthException(
		PortletRequest portletRequest, Exception e) {

		processOAuthException(portletRequest, e.getMessage());
	}

	public static void processOAuthException(
		PortletRequest portletRequest, String message) {

		for (OAuthExceptionType oAuthExceptionType :
				OAuthExceptionType.values()) {

			String oAuthExceptionPattern = StringUtil.toLowerCase(
				oAuthExceptionType.name());

			if (message.contains(oAuthExceptionPattern)) {
				SessionErrors.add(portletRequest, oAuthExceptionType.getKey());
			}
		}
	}

}