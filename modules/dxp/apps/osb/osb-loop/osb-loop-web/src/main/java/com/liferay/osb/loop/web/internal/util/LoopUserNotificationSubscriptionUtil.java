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

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopUserNotificationSubscription;
import com.liferay.osb.loop.service.LoopUserNotificationSubscriptionLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PortletKeys;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopUserNotificationSubscriptionUtil {

	public static void deleteLoopUserNotificationSubscription(
		long loopPersonId, long classNameId, long classPK, int deliveryType) {

		LoopUserNotificationSubscription loopUserNotificationSubscription =
			LoopUserNotificationSubscriptionLocalServiceUtil.
				fetchLoopUserNotificationSubscription(
					loopPersonId, classNameId, classPK, deliveryType);

		if (loopUserNotificationSubscription != null) {
			LoopUserNotificationSubscriptionLocalServiceUtil.
				deleteLoopUserNotificationSubscription(
					loopUserNotificationSubscription);
		}
	}

	public static void deleteLoopUserNotificationSubscriptions(
			long classNameId, long classPK)
		throws Exception {

		List<LoopUserNotificationSubscription>
			loopUserNotificationSubscriptions =
				_alloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"classNameId", classNameId, "classPK", classPK
					});

		for (LoopUserNotificationSubscription loopUserNotificationSubscription :
				loopUserNotificationSubscriptions) {

			LoopUserNotificationSubscriptionLocalServiceUtil.
				deleteLoopUserNotificationSubscription(
					loopUserNotificationSubscription);
		}
	}

	public static List<LoopUserNotificationSubscription>
			getLoopUserNotificationSubscriptions(
				long classNameId, long classPK, int deliveryType)
		throws Exception {

		return _alloyServiceInvoker.executeDynamicQuery(
			new Object[] {
				"classNameId", classNameId, "classPK", classPK, "deliveryType",
				deliveryType
			});
	}

	public static boolean isNotifying(
		long loopPersonId, long classNameId, long classPK, int deliveryType) {

		LoopUserNotificationSubscription loopUserNotificationSubscription =
			LoopUserNotificationSubscriptionLocalServiceUtil.
				fetchLoopUserNotificationSubscription(
					loopPersonId, classNameId, classPK, deliveryType);

		if (loopUserNotificationSubscription == null) {
			return false;
		}

		return true;
	}

	public static boolean isNotifying(
			long companyId, long userId, String key, int deliveryType)
		throws Exception {

		String value = LoopPortletPreferencesUtil.getPreference(
			companyId, userId, PortletKeys.PREFS_OWNER_TYPE_USER, key);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(value);

		return jsonObject.getBoolean(
			LoopUserNotificationConstants.getLabel(deliveryType));
	}

	public static void updateLoopUserNotificationSubscription(
			AlloyController alloyController, long loopPersonId,
			long classNameId, long classPK, int deliveryType)
		throws Exception {

		LoopUserNotificationSubscription loopUserNotificationSubscription =
			LoopUserNotificationSubscriptionLocalServiceUtil.
				fetchLoopUserNotificationSubscription(
					loopPersonId, classNameId, classPK, deliveryType);

		if (loopUserNotificationSubscription != null) {
			return;
		}

		loopUserNotificationSubscription =
			LoopUserNotificationSubscriptionLocalServiceUtil.
				createLoopUserNotificationSubscription(0);

		alloyController.updateModelIgnoreRequest(
			loopUserNotificationSubscription, "loopPersonId", loopPersonId,
			"classNameId", classNameId, "classPK", classPK, "deliveryType",
			deliveryType);
	}

	public static void updateLoopUserNotificationSubscriptions(
			AlloyController alloyController, LoopPerson loopPerson,
			long classNameId, long classPK, String key)
		throws Exception {

		for (int deliveryType : LoopUserNotificationConstants.DELIVERY_TYPES) {
			if (isNotifying(
					loopPerson.getCompanyId(), loopPerson.getPersonUserId(),
					key, deliveryType)) {

				updateLoopUserNotificationSubscription(
					alloyController, loopPerson.getLoopPersonId(), classNameId,
					classPK, deliveryType);
			}
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(
			LoopUserNotificationSubscription.class.getName());

}