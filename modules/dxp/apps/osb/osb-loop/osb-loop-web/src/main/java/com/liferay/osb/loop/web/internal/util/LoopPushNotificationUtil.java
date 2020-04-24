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

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageURL;
import com.liferay.osb.loop.web.internal.image.LoopImageURLFactory;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.push.notifications.constants.PushNotificationsConstants;

import java.util.Set;

/**
 * @author Timothy Bell
 */
public class LoopPushNotificationUtil {

	public static void sendPushNotification(Set<Long> toUserIds, int badge) {
		LoopUserNotificationEventUtil.filterInactiveUsers(toUserIds);

		if (toUserIds.isEmpty()) {
			return;
		}

		JSONObject pushNotificationJSONObject =
			JSONFactoryUtil.createJSONObject();

		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_BADGE, badge);
		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_TO_USER_IDS,
			getToUserIds(toUserIds));

		MessageBusUtil.sendMessage(
			"liferay/push_notification", pushNotificationJSONObject);
	}

	public static void sendPushNotification(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			int notificationType, long fromClassNameId, long fromClassPK,
			Set<Long> toUserIds, Set<Long> sentToUserIds)
		throws Exception {

		LoopUserNotificationEventUtil.filterUsers(
			themeDisplay.getUserId(), toUserIds, sentToUserIds);

		if (toUserIds.isEmpty()) {
			return;
		}

		LoopUserNotificationRecordUtil.addLoopUserNotificationRecords(
			toUserIds, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_PUSH, notificationType);

		JSONObject pushNotificationJSONObject =
			JSONFactoryUtil.createJSONObject();

		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_BODY,
			getBody(
				themeDisplay, assetEntrySet, fromClassNameId, fromClassPK,
				notificationType));

		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_FROM,
			getFrom(fromClassNameId, fromClassPK));

		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_TO_USER_IDS,
			getToUserIds(toUserIds));

		pushNotificationJSONObject.put(
			"action", LoopUserNotificationEventUtil.getAction(assetEntrySet));
		pushNotificationJSONObject.put(
			"id", assetEntrySet.getAssetEntrySetId());

		if (LoopUserNotificationConstants.isGroupedPush(notificationType)) {
			pushNotificationJSONObject.put(
				"groupId", getGroupId(assetEntrySet, notificationType));
			pushNotificationJSONObject.put(
				"groupTitle",
				getGroupTitle(assetEntrySet.getLevel(), notificationType));
		}

		pushNotificationJSONObject.put(
			"privateAssetEntrySet", assetEntrySet.isPrivateAssetEntrySet());

		MessageBusUtil.sendMessage(
			"liferay/push_notification", pushNotificationJSONObject);
	}

	public static void sendSilentPushNotification(
		Set<Long> toUserIds, String key, String value) {

		LoopUserNotificationEventUtil.filterInactiveUsers(toUserIds);

		if (toUserIds.isEmpty()) {
			return;
		}

		JSONObject pushNotificationJSONObject =
			JSONFactoryUtil.createJSONObject();

		pushNotificationJSONObject.put(key, value);
		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_SILENT, true);
		pushNotificationJSONObject.put(
			PushNotificationsConstants.KEY_TO_USER_IDS,
			getToUserIds(toUserIds));

		MessageBusUtil.sendMessage(
			"liferay/push_notification", pushNotificationJSONObject);
	}

	protected static String getBody(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			long fromClassNameId, long fromClassPK, int notificationType)
		throws Exception {

		String message = StringPool.BLANK;

		if ((notificationType ==
				LoopUserNotificationConstants.TYPE_ANNOUNCEMENT) ||
			(notificationType ==
				LoopUserNotificationConstants.
					TYPE_FOLLOWING_ENTITY_PAGE_CREATED)) {

			message = StringUtil.shorten(assetEntrySet.getTitle(), 100);
		}
		else {
			message = LoopUserNotificationEventUtil.getMessage(
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getAssetEntrySetId(), 100, true);
		}

		return themeDisplay.translate(
			LoopUserNotificationEventUtil.getNotificationMessagePattern(
				assetEntrySet.getLevel(), notificationType,
				LoopUserNotificationConstants.DELIVERY_TYPE_PUSH),
			AssetEntrySetParticipantInfoUtil.getParticipantName(
				fromClassNameId, fromClassPK),
			message);
	}

	protected static JSONObject getFrom(long fromClassNameId, long fromClassPK)
		throws Exception {

		JSONObject fromJSONObject = JSONFactoryUtil.createJSONObject();

		fromJSONObject.put(
			"name",
			AssetEntrySetParticipantInfoUtil.getParticipantName(
				fromClassNameId, fromClassPK));

		LoopImageURL loopImageURL = LoopImageURLFactory.createLoopImageURL(
			fromClassNameId, fromClassPK, "profileImagePayload", "web");

		fromJSONObject.put("portraitURL", loopImageURL.getImageURL());

		return fromJSONObject;
	}

	protected static long getGroupId(
		AssetEntrySet assetEntrySet, int notificationType) {

		if (notificationType == LoopUserNotificationConstants.TYPE_LIKED) {
			return assetEntrySet.getAssetEntrySetId();
		}

		return assetEntrySet.getParentAssetEntrySetId();
	}

	protected static String getGroupTitle(int level, int notificationType) {
		if ((level == 0) &&
			(notificationType == LoopUserNotificationConstants.TYPE_LIKED)) {

			return "New likes on your post";
		}
		else if (level == 1) {
			if (notificationType ==
					LoopUserNotificationConstants.TYPE_COMMENTED_ON_MY_POST) {

				return "New comments on your post";
			}
			else if (notificationType ==
						LoopUserNotificationConstants.
							TYPE_COMMENTED_ON_SUBSCRIBED_POST) {

				return "New comments on a post you are following";
			}
			else if (notificationType ==
						LoopUserNotificationConstants.TYPE_LIKED) {

				return "New likes on your comment";
			}
		}
		else if (level == 2) {
			if (notificationType ==
					LoopUserNotificationConstants.TYPE_COMMENTED_ON_MY_POST) {

				return "New replies on your comment";
			}
			else if (notificationType ==
						LoopUserNotificationConstants.TYPE_LIKED) {

				return "New likes on your reply";
			}
		}

		return StringPool.BLANK;
	}

	protected static JSONArray getToUserIds(Set<Long> toUserIds) {
		JSONArray toUserIdsJSONArray = JSONFactoryUtil.createJSONArray();

		for (long toUserId : toUserIds) {
			toUserIdsJSONArray.put(toUserId);
		}

		return toUserIdsJSONArray;
	}

}