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

package com.liferay.lcs.client.internal.util;

import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.license.messaging.LicenseManagerMessageType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalServiceUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;

import java.lang.reflect.Field;

import java.util.StringTokenizer;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSUtil {

	public static int getLCSPortletBuildNumber() {
		int lcsPortletBuildNumber = 0;

		try {
			Release release = ReleaseLocalServiceUtil.fetchRelease(
				"lcs-portlet");

			lcsPortletBuildNumber = release.getBuildNumber();
		}
		catch (Exception e) {
			_log.error(
				"Unable to resolve LCS client's portlet build number", e);
		}

		return lcsPortletBuildNumber;
	}

	public static String getPortalEdition() {
		try {
			Field field = ReleaseInfo.class.getDeclaredField(
				"_VERSION_DISPLAY_NAME");

			field.setAccessible(true);

			StringTokenizer stringTokenizer = new StringTokenizer(
				(String)field.get(null));

			stringTokenizer.nextToken();

			return stringTokenizer.nextToken();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void processLCSPortletState(LCSPortletState lcsPortletState) {
		Message message = LicenseManagerMessageType.LCS_AVAILABLE.createMessage(
			lcsPortletState);

		MessageBusUtil.sendMessage(message.getDestinationName(), message);

		if (_log.isTraceEnabled()) {
			StringBundler sb = new StringBundler(3);

			sb.append("Service availability message published for LCS ");
			sb.append("portlet state ");
			sb.append(lcsPortletState.name());

			_log.trace(sb.toString());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(LCSUtil.class);

}