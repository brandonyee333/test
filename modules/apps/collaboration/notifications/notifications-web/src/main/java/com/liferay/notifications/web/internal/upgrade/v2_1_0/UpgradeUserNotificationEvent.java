/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notifications.web.internal.upgrade.v2_1_0;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Roberto Díaz
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	public UpgradeUserNotificationEvent(
		UserNotificationEventLocalService userNotificationEventLocalService) {

		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("Notifications_UserNotificationEvent")) {
			updateUserNotificationEventActionRequired();
		}

		updateUserNotificationEvents();
	}

	protected void updateUserNotificationEventActionRequired()
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(5);

			sb.append("update UserNotificationEvent set actionRequired = ");
			sb.append("[$TRUE$] where userNotificationEventId in (select ");
			sb.append("userNotificationEventId from ");
			sb.append("Notifications_UserNotificationEvent where ");
			sb.append("actionRequired = [$TRUE$])");

			runSQL(sb.toString());

			runSQL(
				"update UserNotificationEvent set actionRequired = [$FALSE$] " +
					"where actionRequired IS NULL");
		}
	}

	protected void updateUserNotificationEvents() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select userNotificationEventId, payload from " +
					"UserNotificationEvent");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				String payload = rs.getString("payload");

				UserNotificationEvent userNotificationEvent =
					_userNotificationEventLocalService.getUserNotificationEvent(
						userNotificationEventId);

				userNotificationEvent.setDelivered(true);

				int deliveryType = userNotificationEvent.getDeliveryType();

				if (deliveryType == 0) {
					userNotificationEvent.setDeliveryType(
						UserNotificationDeliveryConstants.TYPE_WEBSITE);
				}

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					payload);

				boolean actionRequired = jsonObject.getBoolean(
					"actionRequired");

				jsonObject.remove("actionRequired");

				userNotificationEvent.setPayload(jsonObject.toString());

				if (!userNotificationEvent.isActionRequired()) {
					userNotificationEvent.setActionRequired(actionRequired);
				}

				_userNotificationEventLocalService.updateUserNotificationEvent(
					userNotificationEvent);
			}
		}
	}

	private final UserNotificationEventLocalService
		_userNotificationEventLocalService;

}