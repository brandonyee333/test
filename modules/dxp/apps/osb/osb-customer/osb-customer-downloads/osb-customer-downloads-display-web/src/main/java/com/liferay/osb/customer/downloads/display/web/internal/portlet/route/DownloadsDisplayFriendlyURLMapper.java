/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.downloads.display.web.internal.portlet.route;

import com.liferay.osb.customer.downloads.display.constants.DownloadsDisplayPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/downloads-display-friendly-url-routes.xml",
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY
	},
	service = FriendlyURLMapper.class
)
public class DownloadsDisplayFriendlyURLMapper
	extends DefaultFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "download";

}