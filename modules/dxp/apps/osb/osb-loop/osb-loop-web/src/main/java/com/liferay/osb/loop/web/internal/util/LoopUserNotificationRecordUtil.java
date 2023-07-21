/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.osb.loop.service.LoopUserNotificationRecordLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;
import java.util.Set;

/**
 * @author Timothy Bell
 */
public class LoopUserNotificationRecordUtil {

	public static void addLoopUserNotificationRecords(
		Set<Long> userIds, long classNameId, long classPK, int deliveryType,
		int notificationType) {

		if (notificationType == LoopUserNotificationConstants.TYPE_LIKED) {
			return;
		}

		for (long userId : userIds) {
			LoopUserNotificationRecord loopUserNotificationRecord =
				LoopUserNotificationRecordLocalServiceUtil.
					createLoopUserNotificationRecord(
						CounterLocalServiceUtil.increment());

			loopUserNotificationRecord.setUserId(userId);
			loopUserNotificationRecord.setCreateTime(
				System.currentTimeMillis());
			loopUserNotificationRecord.setClassNameId(classNameId);
			loopUserNotificationRecord.setClassPK(classPK);
			loopUserNotificationRecord.setDeliveryType(deliveryType);

			LoopUserNotificationRecordLocalServiceUtil.
				addLoopUserNotificationRecord(loopUserNotificationRecord);
		}
	}

	public static void deleteLoopUserNotificationRecords(long time)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			LoopUserNotificationRecord.class.getName());

		DynamicQuery dynamicQuery = alloyServiceInvoker.buildDynamicQuery();

		Property property = PropertyFactoryUtil.forName("createTime");

		dynamicQuery.add(property.lt(time));

		List<LoopUserNotificationRecord> loopUserNotificationRecords =
			alloyServiceInvoker.executeDynamicQuery(dynamicQuery);

		for (LoopUserNotificationRecord loopUserNotificationRecord :
				loopUserNotificationRecords) {

			LoopUserNotificationRecordLocalServiceUtil.
				deleteLoopUserNotificationRecord(loopUserNotificationRecord);
		}
	}

	public static void deleteLoopUserNotificationRecords(
			long classNameId, long classPK)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			LoopUserNotificationRecord.class.getName());

		List<LoopUserNotificationRecord> loopUserNotificationRecords =
			alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"classNameId", classNameId, "classPK", classPK},
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (LoopUserNotificationRecord loopUserNotificationRecord :
				loopUserNotificationRecords) {

			LoopUserNotificationRecordLocalServiceUtil.
				deleteLoopUserNotificationRecord(loopUserNotificationRecord);
		}
	}

	public static List<LoopUserNotificationRecord>
			getLoopUserNotificationRecords(
				long classNameId, long classPK, int deliveryType)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			LoopUserNotificationRecord.class.getName());

		return alloyServiceInvoker.executeDynamicQuery(
			new Object[] {
				"classNameId", classNameId, "classPK", classPK, "deliveryType",
				deliveryType
			},
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

}