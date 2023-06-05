/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.accessibility.menu.web.internal.util;

import com.liferay.accessibility.menu.web.internal.constants.AccessibilitySettingConstants;
import com.liferay.accessibility.menu.web.internal.display.context.AccessibilitySetting;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.SessionClicks;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Evan Thibodeau
 */
public class AccessibilitySettingsUtil {

	public static List<AccessibilitySetting> getAccessibilitySettings(
		HttpServletRequest httpServletRequest) {

		return ListUtil.fromArray(
			AccessibilitySetting.create(
				LanguageUtil.get(
					httpServletRequest, "show-underline-effect-in-links"),
				accessibilitySetting -> {
					accessibilitySetting.setClassName(
						"c-prefers-link-underline");
					accessibilitySetting.setDefaultValue(
						Boolean.toString(true));
					accessibilitySetting.setKey(
						AccessibilitySettingConstants.
							ACCESSIBILITY_SETTING_SHOW_UNDERLINE);
					accessibilitySetting.setSessionClicksValue(
						GetterUtil.getString(
							SessionClicks.get(
								httpServletRequest,
								AccessibilitySettingConstants.
									ACCESSIBILITY_SETTING_SHOW_UNDERLINE,
								"undefined")));
				}),
			AccessibilitySetting.create(
				LanguageUtil.get(httpServletRequest, "prefers-reduced-motion"),
				accessibilitySetting -> {
					accessibilitySetting.setClassName(
						"c-prefers-reduced-motion");
					accessibilitySetting.setDefaultValue(
						Boolean.toString(false));
					accessibilitySetting.setKey(
						AccessibilitySettingConstants.
							ACCESSIBILITY_SETTING_PREFERS_REDUCED_MOTION);
					accessibilitySetting.setSessionClicksValue(
						GetterUtil.getString(
							SessionClicks.get(
								httpServletRequest,
								AccessibilitySettingConstants.
									ACCESSIBILITY_SETTING_PREFERS_REDUCED_MOTION,
								"undefined")));
				}));
	}

}