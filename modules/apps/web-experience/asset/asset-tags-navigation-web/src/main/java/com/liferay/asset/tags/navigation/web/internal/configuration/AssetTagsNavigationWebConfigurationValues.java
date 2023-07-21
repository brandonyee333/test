/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.tags.navigation.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Eudaldo Alonso
 */
public class AssetTagsNavigationWebConfigurationValues {

	public static final String DISPLAY_TEMPLATES_CONFIG = GetterUtil.getString(
		AssetTagsNavigationWebConfigurationUtil.get(
			"display.templates.config"));

}