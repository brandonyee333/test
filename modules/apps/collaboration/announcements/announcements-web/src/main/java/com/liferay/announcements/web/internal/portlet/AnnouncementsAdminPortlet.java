/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.announcements.web.internal.portlet;

import com.liferay.announcements.constants.AnnouncementsPortletKeys;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"javax.portlet.display-name=Announcements",
		"javax.portlet.name=" + AnnouncementsPortletKeys.ANNOUNCEMENTS_ADMIN,
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class AnnouncementsAdminPortlet extends MVCPortlet {

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.announcements.web)(release.schema.version=1.0.4))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

}