/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.notifications;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.portal.kernel.notifications.BaseModelUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;

import org.osgi.service.component.annotations.Component;

/**
 * @author Iván Zaera
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL,
	service = UserNotificationHandler.class
)
public class JournalUserNotificationHandler
	extends BaseModelUserNotificationHandler {

	public JournalUserNotificationHandler() {
		setPortletId(JournalPortletKeys.JOURNAL);
	}

}