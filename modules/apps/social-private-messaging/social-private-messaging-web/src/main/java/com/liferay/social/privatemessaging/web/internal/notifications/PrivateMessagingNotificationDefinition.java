/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.web.internal.notifications;

import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;
import com.liferay.social.privatemessaging.constants.PrivateMessagingPortletKeys;
import com.liferay.social.privatemessaging.model.PrivateMessagingConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eric Yan
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + PrivateMessagingPortletKeys.PRIVATE_MESSAGING,
	service = UserNotificationDefinition.class
)
public class PrivateMessagingNotificationDefinition
	extends UserNotificationDefinition {

	public PrivateMessagingNotificationDefinition() {
		super(
			PrivateMessagingPortletKeys.PRIVATE_MESSAGING, 0,
			PrivateMessagingConstants.NEW_MESSAGE,
			"receive-a-notification-when-someone-sends-you-a-message");

		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"email", UserNotificationDeliveryConstants.TYPE_EMAIL, true,
				true));
		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"website", UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				true));
	}

}