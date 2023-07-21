/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.messaging;

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationRecordUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Time;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopUserNotificationMessageListener extends BaseMessageListener {

	public static LoopUserNotificationMessageListener getInstance() {
		return _instance;
	}

	protected static void deleteLoopUserNotificationEvents(long time)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			LoopUserNotificationEvent.class.getName());

		DynamicQuery dynamicQuery = alloyServiceInvoker.buildDynamicQuery();

		Property property = PropertyFactoryUtil.forName("createTime");

		dynamicQuery.add(property.lt(time));

		List<LoopUserNotificationEvent> loopUserNotificationEvents =
			alloyServiceInvoker.executeDynamicQuery(dynamicQuery);

		LoopUserNotificationEventUtil.deleteLoopUserNotificationEvents(
			loopUserNotificationEvents);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long time =
			System.currentTimeMillis() -
				(LoopWebConfigurationValues.LOOP_USER_NOTIFICATION_CLEAN_TIME *
					Time.MINUTE);

		deleteLoopUserNotificationEvents(time);

		LoopUserNotificationRecordUtil.deleteLoopUserNotificationRecords(time);
	}

	private static final LoopUserNotificationMessageListener _instance =
		new LoopUserNotificationMessageListener();

}