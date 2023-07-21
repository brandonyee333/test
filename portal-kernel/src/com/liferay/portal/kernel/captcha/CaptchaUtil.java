/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.captcha;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class CaptchaUtil {

	public static void check(HttpServletRequest request)
		throws CaptchaException {

		getCaptcha().check(request);
	}

	public static void check(PortletRequest portletRequest)
		throws CaptchaException {

		getCaptcha().check(portletRequest);
	}

	public static Captcha getCaptcha() {
		PortalRuntimePermission.checkGetBeanProperty(CaptchaUtil.class);

		if (_serviceTrackerMap == null) {
			return null;
		}

		String captchaClassName = PrefsPropsUtil.getString(
			PropsKeys.CAPTCHA_ENGINE_IMPL, _CAPTCHA_ENGINE_IMPL);

		return _serviceTrackerMap.getService(captchaClassName);
	}

	public static String getTaglibPath() {
		return getCaptcha().getTaglibPath();
	}

	public static boolean isEnabled(HttpServletRequest request) {
		return getCaptcha().isEnabled(request);
	}

	public static boolean isEnabled(PortletRequest portletRequest) {
		return getCaptcha().isEnabled(portletRequest);
	}

	public static void serveImage(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		getCaptcha().serveImage(request, response);
	}

	public static void serveImage(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		getCaptcha().serveImage(resourceRequest, resourceResponse);
	}

	public void setCaptcha(Captcha captcha) throws Exception {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences();

		Class<?> clazz = captcha.getClass();

		portletPreferences.setValue(
			PropsKeys.CAPTCHA_ENGINE_IMPL, clazz.getName());

		portletPreferences.store();
	}

	private static final String _CAPTCHA_ENGINE_IMPL = PropsUtil.get(
		PropsKeys.CAPTCHA_ENGINE_IMPL);

	private static final ServiceTrackerMap<String, Captcha> _serviceTrackerMap =
		ServiceTrackerCollections.openSingleValueMap(
			Captcha.class, "captcha.engine.impl");

}