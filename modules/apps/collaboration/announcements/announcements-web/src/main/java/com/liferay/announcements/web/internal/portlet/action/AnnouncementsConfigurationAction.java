/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.announcements.web.internal.portlet.action;

import com.liferay.announcements.constants.AnnouncementsPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + AnnouncementsPortletKeys.ANNOUNCEMENTS,
	service = ConfigurationAction.class
)
public class AnnouncementsConfigurationAction
	extends DefaultConfigurationAction {
}