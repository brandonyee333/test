/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.model.ColorScheme;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Vilmos Papp
 */
public class ColorSchemeFactoryUtil {

	public static ColorScheme getColorScheme() {
		return getColorSchemeFactory().getColorScheme();
	}

	public static ColorScheme getColorScheme(String colorSchemeId) {
		return getColorSchemeFactory().getColorScheme(colorSchemeId);
	}

	public static ColorScheme getColorScheme(
		String colorSchemeId, String name, String cssClass) {

		return getColorSchemeFactory().getColorScheme(
			colorSchemeId, name, cssClass);
	}

	public static ColorSchemeFactory getColorSchemeFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ColorSchemeFactoryUtil.class);

		return _colorSchemeFactory;
	}

	public static ColorScheme getDefaultRegularColorScheme() {
		return getColorSchemeFactory().getDefaultRegularColorScheme();
	}

	public static String getDefaultRegularColorSchemeId() {
		return getColorSchemeFactory().getDefaultRegularColorSchemeId();
	}

	public void setColorSchemeFactory(ColorSchemeFactory colorSchemeFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_colorSchemeFactory = colorSchemeFactory;
	}

	private static ColorSchemeFactory _colorSchemeFactory;

}