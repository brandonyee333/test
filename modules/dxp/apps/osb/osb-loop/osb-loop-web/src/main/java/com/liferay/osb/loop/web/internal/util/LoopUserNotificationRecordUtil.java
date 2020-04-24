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