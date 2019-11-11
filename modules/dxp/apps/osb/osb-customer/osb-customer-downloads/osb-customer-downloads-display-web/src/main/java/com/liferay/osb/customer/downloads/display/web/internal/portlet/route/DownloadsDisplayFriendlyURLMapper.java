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