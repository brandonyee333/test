/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Adolfo Pérez
 */
public class SocialNetworkingServiceConfigurationValues {

	public static final String WALL_LAYOUT_FRIENDLY_URL = GetterUtil.getString(
		SocialNetworkingServiceConfigurationUtil.get(
			"wall.layout.friendly.url"));

}