/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.web.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Juergen Kappler
 */
public class SiteNavigationMenuConfigurationValues {

	public static final String DISPLAY_TEMPLATES_CONFIG = GetterUtil.getString(
		SiteNavigationMenuWebConfigurationUtil.get("display.templates.config"));

}