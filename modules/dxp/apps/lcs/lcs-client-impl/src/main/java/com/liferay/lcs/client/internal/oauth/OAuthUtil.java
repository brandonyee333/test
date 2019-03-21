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