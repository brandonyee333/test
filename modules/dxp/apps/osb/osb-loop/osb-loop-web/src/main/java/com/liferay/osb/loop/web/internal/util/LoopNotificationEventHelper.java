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

import com.liferay.alloy.mvc.AlloyNotificationEventHelper;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.osb.loop.model.LoopUserNotificationRecord;
import com.liferay.osb.loop.model.LoopUserNotificationSubscription;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopUserNotificationEventLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopNotificationEventHelper extends AlloyNotificationEventHelper {

	public LoopNotificationEventHelper() {
		_controllerNotificationActionPaths.put(
			"feed", ListUtil.fromArray(_FEED_ACTION_PATHS));
		_controllerNotificationActionPaths.put(
			"pages", ListUtil.fromArray(_PAGES_ACTION_PATHS));
	}

	@Override
	public void addUserNotificationEvents(
		HttpServletRequest request, String controllerPath, String actionPath,
		JSONObject payloadJSONObject) {

		List<String> notificationActionPaths =
			_controllerNotificationActionPaths.get(controllerPath);

		if (notificationActionPaths == null) {
			return;
		}

		Matcher actionPathMatcher = _actionPathPattern.matcher(actionPath);

		if (actionPathMatcher.find()) {
			actionPath = actionPathMatcher.group(1);
		}

		if (!notificationActionPaths.contains(actionPath)) {
			return;
		}

		_themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		MessageBusUtil.sendMessage(
			DestinationNames.ASYNC_SERVICE,
			new ProcessLoopActionsProcessCallable(
				this, controllerPath, actionPath, payloadJSONObject));
	}

	protected void addLoopUserNotificationEvent(
			long userId, int notificationType, long classNameId, long classPK,
			long groupClassNameId, long groupClassPK)
		throws Exception {

		long groupKey = CounterLocalServiceUtil.increment();

		LoopUserNotificationEvent loopUserNotificationEvent =
			LoopUserNotificationEventLocalServiceUtil.
				createLoopUserNotificationEvent(
					CounterLocalServiceUtil.increment());

		List<Long> groupKeys = new ArrayList<>();

		if (LoopUserNotificationConstants.isGroupedComments(notificationType) ||
			(notificationType == LoopUserNotificationConstants.TYPE_LIKED)) {

			groupKeys = LoopUserNotificationEventUtil.getGroupKeys(
				userId, groupClassNameId, groupClassPK, notificationType);
		}

		if (!groupKeys.isEmpty()) {
			groupKey = groupKeys.get(0);
		}

		loopUserNotificationEvent.setCreateTime(System.currentTimeMillis());
		loopUserNotificationEvent.setRecipientUserId(userId);
		loopUserNotificationEvent.setClassNameId(classNameId);
		loopUserNotificationEvent.setClassPK(classPK);
		loopUserNotificationEvent.setGroupClassNameId(groupClassNameId);
		loopUserNotificationEvent.setGroupClassPK(groupClassPK);
		loopUserNotificationEvent.setGroupKey(groupKey);
		loopUserNotificationEvent.setType(notificationType);
		loopUserNotificationEvent.setReceived(false);
		loopUserNotificationEvent.setOpened(false);

		LoopUserNotificationEventLocalServiceUtil.addLoopUserNotificationEvent(
			loopUserNotificationEvent);
	}

	@Override
	protected void addUserNotificationEvent(
			String portletKey, long userId, int notificationType,
			int deliveryType, Object... attributes)
		throws Exception {

		if ((attributes.length == 0) || ((attributes.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Attributes length is not an even number");
		}

		if (userId == _themeDisplay.getUserId()) {
			return;
		}

		long classNameId = 0;
		long classPK = 0;
		long groupClassNameId = 0;
		long groupClassPK = 0;

		for (int i = 0; i < attributes.length; i += 2) {
			String key = String.valueOf(attributes[i]);
			String value = String.valueOf(attributes[i + 1]);

			if (key.equals("classNameId")) {
				classNameId = GetterUtil.getLong(value);
			}
			else if (key.equals("classPK")) {
				classPK = GetterUtil.getLong(value);
			}
			else if (key.equals("groupClassNameId")) {
				groupClassNameId = GetterUtil.getLong(value);
			}
			else if (key.equals("groupClassPK")) {
				groupClassPK = GetterUtil.getLong(value);
			}
		}

		addLoopUserNotificationEvent(
			userId, notificationType, classNameId, classPK, groupClassNameId,
			groupClassPK);

		LoopPersonUtil.updateGroupedUserNotificationEventsCount(userId);
	}

	protected Set<Long> getAnnouncementUserIds(
			JSONArray sharedToJSONArray, AssetEntrySet assetEntrySet,
			int deliveryType)
		throws Exception {

		Set<Long> userIds = new HashSet<>();

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			long entityClassNameId = sharedToJSONObject.getLong(
				"entityClassNameId");
			long entityClassPK = sharedToJSONObject.getLong("entityClassPK");

			if (entityClassNameId == PortalUtil.getClassNameId(
					LoopPerson.class)) {

				userIds.add(getPersonUserId(entityClassPK));
			}

			userIds.addAll(
				getSubscribedUserIds(
					assetEntrySet, entityClassNameId, entityClassPK,
					deliveryType, true));
		}

		return userIds;
	}

	protected Set<Long> getDirectSharedToUserIds(
			List<Long> sharedToLoopPersonIds, int deliveryType)
		throws Exception {

		if (ListUtil.isEmpty(sharedToLoopPersonIds)) {
			return Collections.emptySet();
		}

		Set<Long> userIds = new HashSet<>();

		for (long loopPersonId : sharedToLoopPersonIds) {
			long userId = getPersonUserId(loopPersonId);

			if (LoopUserNotificationSubscriptionUtil.isNotifying(
					_themeDisplay.getCompanyId(), userId,
					LoopUserNotificationConstants.SETTING_KEY_MENTIONED,
					deliveryType)) {

				userIds.add(userId);
			}
		}

		return userIds;
	}

	protected Set<Long> getGroupMemberUserIds(List<Long> loopDivisionIds)
		throws Exception {

		if (ListUtil.isEmpty(loopDivisionIds)) {
			return Collections.emptySet();
		}

		Set<Long> userIds = new HashSet<>();

		for (long loopDivisionId : loopDivisionIds) {
			LoopDivisionComposite loopDivisionComposite =
				new LoopDivisionComposite(loopDivisionId, _themeDisplay);

			for (LoopPersonComposite loopPersonComposite :
					loopDivisionComposite.getLeadLoopPersonComposites()) {

				userIds.add(loopPersonComposite.getPersonUserId());
			}

			for (LoopPersonComposite loopPersonComposite :
					loopDivisionComposite.getMemberLoopPersonComposites()) {

				userIds.add(loopPersonComposite.getPersonUserId());
			}
		}

		return userIds;
	}

	protected long getPersonUserId(long loopPersonId) throws Exception {
		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		return loopPerson.getPersonUserId();
	}

	protected Set<Long> getSharedToFollowersUserIds(
			long classNameId, List<Long> classPKs, AssetEntrySet assetEntrySet,
			int deliveryType)
		throws Exception {

		if (ListUtil.isEmpty(classPKs)) {
			return Collections.emptySet();
		}

		Set<Long> userIds = new HashSet<>();

		for (long classPK : classPKs) {
			if ((classNameId == PortalUtil.getClassNameId(LoopPerson.class)) &&
				(classPK == assetEntrySet.getCreatorClassPK())) {

				continue;
			}

			userIds.addAll(
				getSubscribedUserIds(
					assetEntrySet, classNameId, classPK, deliveryType, true));
		}

		return userIds;
	}

	protected Map<Long, List<Long>> getSharedToMap(
		JSONArray sharedToJSONArray) {

		Map<Long, List<Long>> sharedToMap = new HashMap<>();

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			long entityClassNameId = sharedToJSONObject.getLong(
				"entityClassNameId");

			List<Long> entityClassPKs = sharedToMap.get(entityClassNameId);

			if (entityClassPKs == null) {
				entityClassPKs = new ArrayList<>();
			}

			entityClassPKs.add(sharedToJSONObject.getLong("entityClassPK"));

			sharedToMap.put(entityClassNameId, entityClassPKs);
		}

		return sharedToMap;
	}

	protected Set<Long> getSubscribedUserIds(
			AssetEntrySet assetEntrySet, long classNameId, long classPK,
			int deliveryType, boolean checkFollowingType)
		throws Exception {

		Set<Long> subscribedUserIds = new HashSet<>();

		List<LoopUserNotificationSubscription>
			loopUserNotificationSubscriptions =
				LoopUserNotificationSubscriptionUtil.
					getLoopUserNotificationSubscriptions(
						classNameId, classPK, deliveryType);

		for (LoopUserNotificationSubscription loopUserNotificationSubscription :
				loopUserNotificationSubscriptions) {

			if (((assetEntrySet.getLevel() > 0) || checkFollowingType) &&
				(classNameId == PortalUtil.getClassNameId(LoopPerson.class))) {

				LoopStreamEntry loopStreamEntry =
					LoopStreamEntryUtil.fetchLoopStreamEntry(
						loopUserNotificationSubscription.getLoopPersonId(),
						LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
						classNameId, classPK);

				if ((loopStreamEntry != null) &&
					(loopStreamEntry.getFollowingType() ==
						LoopStreamEntryConstants.TYPE_FOLLOWING_LIMITED)) {

					continue;
				}
			}

			long userId = getPersonUserId(
				loopUserNotificationSubscription.getLoopPersonId());

			if (LoopUtil.isSharedWithUser(assetEntrySet, userId)) {
				subscribedUserIds.add(userId);
			}
		}

		return subscribedUserIds;
	}

	@SuppressWarnings("unused")
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	protected void processLoopFeedActions(
			String actionPath, JSONObject payloadJSONObject)
		throws Exception {

		if (payloadJSONObject == null) {
			return;
		}

		long assetEntrySetId = payloadJSONObject.getLong("assetEntrySetId");

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.getAssetEntrySet(assetEntrySetId);

		if (actionPath.equals("like")) {
			sendNotificationsLiked(assetEntrySet, payloadJSONObject);

			return;
		}

		JSONArray sharedToJSONArray = payloadJSONObject.getJSONArray(
			"sharedToJSONArray");

		Map<Long, List<Long>> sharedToMap = getSharedToMap(sharedToJSONArray);

		Map<Integer, Set<Long>> sentUserIdsMap = new HashMap<>();

		for (int deliveryType : LoopUserNotificationConstants.DELIVERY_TYPES) {
			sentUserIdsMap.put(deliveryType, new HashSet<Long>());
		}

		if (assetEntrySet.getType() ==
				LoopAssetEntrySetConstants.TYPE_ANNOUNCEMENT) {

			sendNotificationsAnnouncementsGroupMembers(
				sharedToMap.get(PortalUtil.getClassNameId(LoopDivision.class)),
				assetEntrySet,
				sentUserIdsMap.get(
					LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL));
		}

		for (int deliveryType : LoopUserNotificationConstants.DELIVERY_TYPES) {
			Set<Long> sentUserIds = sentUserIdsMap.get(deliveryType);

			for (LoopUserNotificationRecord loopUserNotificationRecord :
					LoopUserNotificationRecordUtil.
						getLoopUserNotificationRecords(
							PortalUtil.getClassNameId(AssetEntrySet.class),
							assetEntrySetId, deliveryType)) {

				sentUserIds.add(loopUserNotificationRecord.getUserId());
			}

			sentUserIdsMap.put(deliveryType, sentUserIds);
		}

		if (assetEntrySet.getType() ==
				LoopAssetEntrySetConstants.TYPE_ANNOUNCEMENT) {

			sendNotificationsAnnouncements(
				sharedToJSONArray, assetEntrySet, sentUserIdsMap);

			return;
		}

		sendNotificationsDirectMention(
			sharedToMap.get(PortalUtil.getClassNameId(LoopPerson.class)),
			assetEntrySet, sentUserIdsMap);

		boolean update = Objects.equals(actionPath, "update");

		if (!update) {
			sendNotificationsParent(assetEntrySet, sentUserIdsMap);
		}

		sendNotificationsSharedToFollowers(
			PortalUtil.getClassNameId(LoopDivision.class),
			sharedToMap.get(PortalUtil.getClassNameId(LoopDivision.class)),
			assetEntrySet, sentUserIdsMap);
		sendNotificationsSharedToFollowers(
			PortalUtil.getClassNameId(LoopPerson.class),
			sharedToMap.get(PortalUtil.getClassNameId(LoopPerson.class)),
			assetEntrySet, sentUserIdsMap);
		sendNotificationsSharedToFollowers(
			PortalUtil.getClassNameId(LoopTopic.class),
			sharedToMap.get(PortalUtil.getClassNameId(LoopTopic.class)),
			assetEntrySet, sentUserIdsMap);

		if (!update) {
			sendNotificationsPostFollowers(assetEntrySet, sentUserIdsMap);

			sendNotificationsCreatorFollowers(assetEntrySet, sentUserIdsMap);
		}

		if (AssetEntrySetLocalServiceUtil.fetchAssetEntrySet(assetEntrySetId) ==
				null) {

			throw new Exception(
				"Rolling back because AssetEntrySet no longer exists");
		}
	}

	@SuppressWarnings("unused")
	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	protected void processLoopPagesActions(
			String actionPath, JSONObject payloadJSONObject)
		throws Exception {

		long assetEntrySetId = payloadJSONObject.getLong("assetEntrySetId");

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.getAssetEntrySet(assetEntrySetId);

		Set<Long> inAppUserIds = getSubscribedUserIds(
			assetEntrySet, assetEntrySet.getClassNameId(),
			assetEntrySet.getClassPK(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP, false);

		sendUserNotificationEvents(
			inAppUserIds, null,
			LoopUserNotificationConstants.TYPE_FOLLOWING_ENTITY_PAGE_CREATED,
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId());

		Set<Long> emailUserIds = getSubscribedUserIds(
			assetEntrySet, assetEntrySet.getClassNameId(),
			assetEntrySet.getClassPK(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL, false);

		sendEmailNotifications(
			emailUserIds, null, assetEntrySet,
			LoopUserNotificationConstants.TYPE_FOLLOWING_ENTITY_PAGE_CREATED);

		Set<Long> pushUserIds = getSubscribedUserIds(
			assetEntrySet, assetEntrySet.getClassNameId(),
			assetEntrySet.getClassPK(),
			LoopUserNotificationConstants.DELIVERY_TYPE_PUSH, false);

		LoopPushNotificationUtil.sendPushNotification(
			_themeDisplay, assetEntrySet,
			LoopUserNotificationConstants.TYPE_FOLLOWING_ENTITY_PAGE_CREATED,
			assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK(), pushUserIds, null);
	}

	protected void sendEmailNotifications(
		Set<Long> userIds, Set<Long> sentUserIds, AssetEntrySet assetEntrySet,
		int notificationType) {

		LoopUserNotificationEventUtil.filterUsers(
			_themeDisplay.getUserId(), userIds, sentUserIds);

		if (userIds.isEmpty()) {
			return;
		}

		LoopUserNotificationRecordUtil.addLoopUserNotificationRecords(
			userIds, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL,
			notificationType);

		Message message = new Message();

		Map<String, Object> values = new HashMap<>();

		values.put("assetEntrySet", assetEntrySet);
		values.put("notificationType", notificationType);
		values.put("themeDisplay", _themeDisplay);
		values.put("userIds", StringUtil.merge(userIds.toArray()));

		message.setValues(values);

		MessageBusUtil.sendMessage(
			LoopUserNotificationConstants.CONTROLLER_DESTINATION_NAME, message);
	}

	protected void sendNotificationsAnnouncements(
			JSONArray sharedToJSONArray, AssetEntrySet assetEntrySet,
			Map<Integer, Set<Long>> sentUserIds)
		throws Exception {

		Set<Long> emailUserIds = getAnnouncementUserIds(
			sharedToJSONArray, assetEntrySet,
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);

		sendEmailNotifications(
			emailUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
			assetEntrySet, LoopUserNotificationConstants.TYPE_ANNOUNCEMENT);

		Set<Long> pushUserIds = getAnnouncementUserIds(
			sharedToJSONArray, assetEntrySet,
			LoopUserNotificationConstants.DELIVERY_TYPE_PUSH);

		LoopPushNotificationUtil.sendPushNotification(
			_themeDisplay, assetEntrySet,
			LoopUserNotificationConstants.TYPE_ANNOUNCEMENT,
			assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK(), pushUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_PUSH));

		Set<Long> inAppUserIds = getAnnouncementUserIds(
			sharedToJSONArray, assetEntrySet,
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);

		sendUserNotificationEvents(
			inAppUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP),
			LoopUserNotificationConstants.TYPE_ANNOUNCEMENT,
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId());
	}

	protected void sendNotificationsAnnouncementsGroupMembers(
			List<Long> loopDivisionIds, AssetEntrySet assetEntrySet,
			Set<Long> sentUserIds)
		throws Exception {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		if (!payloadJSONObject.getBoolean("sendEmailNotifications")) {
			return;
		}

		Set<Long> userIds = getGroupMemberUserIds(loopDivisionIds);

		sendEmailNotifications(
			userIds, sentUserIds, assetEntrySet,
			LoopUserNotificationConstants.TYPE_ANNOUNCEMENT);
	}

	protected void sendNotificationsCreatorFollowers(
			AssetEntrySet assetEntrySet, Map<Integer, Set<Long>> sentUserIds)
		throws Exception {

		Set<Long> emailUserIds = getSubscribedUserIds(
			assetEntrySet, assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL, false);

		int notificationType =
			LoopUserNotificationConstants.TYPE_FOLLOWING_CREATOR_POSTS;

		if (assetEntrySet.getLevel() > 0) {
			notificationType =
				LoopUserNotificationConstants.
					TYPE_FOLLOWING_CREATOR_COMMENTS_AND_REPLIES;
		}

		sendEmailNotifications(
			emailUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
			assetEntrySet, notificationType);

		Set<Long> pushUserIds = getSubscribedUserIds(
			assetEntrySet, assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK(),
			LoopUserNotificationConstants.DELIVERY_TYPE_PUSH, false);

		LoopPushNotificationUtil.sendPushNotification(
			_themeDisplay, assetEntrySet, notificationType,
			PortalUtil.getClassNameId(LoopPerson.class),
			assetEntrySet.getCreatorClassPK(), pushUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_PUSH));

		Set<Long> inAppUserIds = getSubscribedUserIds(
			assetEntrySet, assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP, false);

		long groupClassPK = assetEntrySet.getAssetEntrySetId();

		if (assetEntrySet.getLevel() > 0) {
			groupClassPK = assetEntrySet.getParentAssetEntrySetId();
		}

		sendUserNotificationEvents(
			inAppUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP),
			notificationType, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			PortalUtil.getClassNameId(AssetEntrySet.class), groupClassPK);
	}

	protected void sendNotificationsDirectMention(
			List<Long> loopPersonIds, AssetEntrySet assetEntrySet,
			Map<Integer, Set<Long>> sentUserIds)
		throws Exception {

		Set<Long> emailUserIds = getDirectSharedToUserIds(
			loopPersonIds, LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);

		sendEmailNotifications(
			emailUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
			assetEntrySet,
			LoopUserNotificationConstants.TYPE_MENTIONED_DIRECTLY);

		Set<Long> pushUserIds = getDirectSharedToUserIds(
			loopPersonIds, LoopUserNotificationConstants.DELIVERY_TYPE_PUSH);

		LoopPushNotificationUtil.sendPushNotification(
			_themeDisplay, assetEntrySet,
			LoopUserNotificationConstants.TYPE_MENTIONED_DIRECTLY,
			PortalUtil.getClassNameId(LoopPerson.class),
			assetEntrySet.getCreatorClassPK(), pushUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_PUSH));

		Set<Long> inAppUserIds = getDirectSharedToUserIds(
			loopPersonIds, LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);

		sendUserNotificationEvents(
			inAppUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP),
			LoopUserNotificationConstants.TYPE_MENTIONED_DIRECTLY,
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId());
	}

	protected void sendNotificationsLiked(
			AssetEntrySet assetEntrySet, JSONObject payloadJSONObject)
		throws Exception {

		LoopPerson creatorLoopPerson =
			LoopPersonLocalServiceUtil.fetchLoopPerson(
				assetEntrySet.getCreatorClassPK());

		String key = LoopUserNotificationConstants.SETTING_KEY_LIKED_POST;

		if (assetEntrySet.getLevel() > 0) {
			key =
				LoopUserNotificationConstants.
					SETTING_KEY_LIKED_COMMENTS_AND_REPLIES;
		}

		long loopPersonId = payloadJSONObject.getLong("loopPersonId");

		if (LoopUserNotificationSubscriptionUtil.isNotifying(
				creatorLoopPerson.getCompanyId(),
				creatorLoopPerson.getPersonUserId(), key,
				LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP)) {

			sendUserNotificationEvents(
				new HashSet<>(
					Collections.singletonList(
						creatorLoopPerson.getPersonUserId())),
				null, LoopUserNotificationConstants.TYPE_LIKED,
				PortalUtil.getClassNameId(LoopPerson.class), loopPersonId,
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getAssetEntrySetId());
		}

		if (LoopUserNotificationSubscriptionUtil.isNotifying(
				creatorLoopPerson.getCompanyId(),
				creatorLoopPerson.getPersonUserId(), key,
				LoopUserNotificationConstants.DELIVERY_TYPE_PUSH)) {

			LoopPushNotificationUtil.sendPushNotification(
				_themeDisplay, assetEntrySet,
				LoopUserNotificationConstants.TYPE_LIKED,
				PortalUtil.getClassNameId(LoopPerson.class), loopPersonId,
				new HashSet<>(
					Collections.singletonList(
						creatorLoopPerson.getPersonUserId())),
				null);
		}
	}

	protected void sendNotificationsParent(
			AssetEntrySet assetEntrySet, Map<Integer, Set<Long>> sentUserIds)
		throws Exception {

		if (assetEntrySet.getLevel() == 0) {
			return;
		}

		AssetEntrySet parentAssetEntrySet =
			AssetEntrySetLocalServiceUtil.getAssetEntrySet(
				assetEntrySet.getParentAssetEntrySetId());

		if (parentAssetEntrySet.getCreatorClassNameId() !=
				PortalUtil.getClassNameId(LoopPerson.class)) {

			return;
		}

		Set<Long> userIds = new HashSet<>();

		long userId = getPersonUserId(parentAssetEntrySet.getCreatorClassPK());

		userIds.add(userId);

		if (LoopUserNotificationSubscriptionUtil.isNotifying(
				parentAssetEntrySet.getCreatorClassPK(),
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getParentAssetEntrySetId(),
				LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL)) {

			sendEmailNotifications(
				userIds,
				sentUserIds.get(
					LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
				assetEntrySet,
				LoopUserNotificationConstants.TYPE_COMMENTED_ON_MY_POST);
		}

		if (LoopUserNotificationSubscriptionUtil.isNotifying(
				parentAssetEntrySet.getCreatorClassPK(),
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getParentAssetEntrySetId(),
				LoopUserNotificationConstants.DELIVERY_TYPE_PUSH)) {

			LoopPushNotificationUtil.sendPushNotification(
				_themeDisplay, assetEntrySet,
				LoopUserNotificationConstants.TYPE_COMMENTED_ON_MY_POST,
				PortalUtil.getClassNameId(LoopPerson.class),
				assetEntrySet.getCreatorClassPK(), userIds,
				sentUserIds.get(
					LoopUserNotificationConstants.DELIVERY_TYPE_PUSH));
		}

		if (LoopUserNotificationSubscriptionUtil.isNotifying(
				parentAssetEntrySet.getCreatorClassPK(),
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getParentAssetEntrySetId(),
				LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP)) {

			sendUserNotificationEvents(
				userIds,
				sentUserIds.get(
					LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP),
				LoopUserNotificationConstants.TYPE_COMMENTED_ON_MY_POST,
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getAssetEntrySetId(),
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getParentAssetEntrySetId());
		}
	}

	protected void sendNotificationsPostFollowers(
			AssetEntrySet assetEntrySet, Map<Integer, Set<Long>> sentUserIds)
		throws Exception {

		if (assetEntrySet.getLevel() != 1) {
			return;
		}

		Set<Long> emailUserIds = getSubscribedUserIds(
			assetEntrySet, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getParentAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL, false);

		sendEmailNotifications(
			emailUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
			assetEntrySet,
			LoopUserNotificationConstants.TYPE_COMMENTED_ON_SUBSCRIBED_POST);

		Set<Long> pushUserIds = getSubscribedUserIds(
			assetEntrySet, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getParentAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_PUSH, false);

		LoopPushNotificationUtil.sendPushNotification(
			_themeDisplay, assetEntrySet,
			LoopUserNotificationConstants.TYPE_COMMENTED_ON_SUBSCRIBED_POST,
			PortalUtil.getClassNameId(LoopPerson.class),
			assetEntrySet.getCreatorClassPK(), pushUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_PUSH));

		Set<Long> inAppUserIds = getSubscribedUserIds(
			assetEntrySet, PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getParentAssetEntrySetId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP, false);

		sendUserNotificationEvents(
			inAppUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP),
			LoopUserNotificationConstants.TYPE_COMMENTED_ON_SUBSCRIBED_POST,
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getParentAssetEntrySetId());
	}

	protected void sendNotificationsSharedToFollowers(
			long classNameId, List<Long> classPKs, AssetEntrySet assetEntrySet,
			Map<Integer, Set<Long>> sentUserIds)
		throws Exception {

		Set<Long> emailUserIds = getSharedToFollowersUserIds(
			classNameId, classPKs, assetEntrySet,
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);

		int notificationType =
			LoopUserNotificationConstants.TYPE_FOLLOWING_DIVISION_MENTIONED;

		if (classNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			notificationType =
				LoopUserNotificationConstants.TYPE_FOLLOWING_PERSON_MENTIONED;
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopTopic.class)) {
			notificationType =
				LoopUserNotificationConstants.TYPE_FOLLOWING_TOPIC_MENTIONED;
		}

		sendEmailNotifications(
			emailUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
			assetEntrySet, notificationType);

		Set<Long> pushUserIds = getSharedToFollowersUserIds(
			classNameId, classPKs, assetEntrySet,
			LoopUserNotificationConstants.DELIVERY_TYPE_PUSH);

		LoopPushNotificationUtil.sendPushNotification(
			_themeDisplay, assetEntrySet, notificationType,
			PortalUtil.getClassNameId(LoopPerson.class),
			assetEntrySet.getCreatorClassPK(), pushUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_PUSH));

		Set<Long> inAppUserIds = getSharedToFollowersUserIds(
			classNameId, classPKs, assetEntrySet,
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);

		sendUserNotificationEvents(
			inAppUserIds,
			sentUserIds.get(LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP),
			LoopUserNotificationConstants.TYPE_FOLLOWING_ENTITY_MENTIONED,
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId());
	}

	protected void sendUserNotificationEvents(
			Set<Long> userIds, Set<Long> sentUserIds, int notificationType,
			long classNameId, long classPK, long groupClassNameId,
			long groupClassPK)
		throws Exception {

		LoopUserNotificationEventUtil.filterUsers(
			_themeDisplay.getUserId(), userIds, sentUserIds);

		if (userIds.isEmpty()) {
			return;
		}

		LoopUserNotificationRecordUtil.addLoopUserNotificationRecords(
			userIds, PortalUtil.getClassNameId(AssetEntrySet.class), classPK,
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP,
			notificationType);

		for (long userId : userIds) {
			addUserNotificationEvent(
				LoopPortletKeys.LOOP, userId, notificationType,
				UserNotificationDeliveryConstants.TYPE_WEBSITE, "classNameId",
				classNameId, "classPK", classPK, "groupClassNameId",
				groupClassNameId, "groupClassPK", groupClassPK);

			LoopPerson loopPerson =
				LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(userId);

			LoopPushNotificationUtil.sendPushNotification(
				new HashSet<>(Collections.singletonList(userId)),
				loopPerson.getGroupedUserNotificationEventsCount());

			LoopPushNotificationUtil.sendSilentPushNotification(
				new HashSet<>(Collections.singletonList(userId)),
				"notifications", "sync");
		}
	}

	private static final String[] _FEED_ACTION_PATHS = {
		"addMyPost", "addPost", "create", "like", "update"
	};

	private static final String[] _PAGES_ACTION_PATHS = {"add"};

	private static final String _REGEX_ACTION_PATH = "([a-zA-Z]+)";

	private static final Pattern _actionPathPattern = Pattern.compile(
		_REGEX_ACTION_PATH);

	private Map<String, List<String>> _controllerNotificationActionPaths =
		new HashMap<>();
	private ThemeDisplay _themeDisplay;

	private class ProcessLoopActionsProcessCallable
		implements ProcessCallable<Serializable> {

		public ProcessLoopActionsProcessCallable(
			LoopNotificationEventHelper loopNotificationEventHelper,
			String controllerPath, String actionPath,
			JSONObject payloadJSONObject) {

			_loopNotificationEventHelper = loopNotificationEventHelper;
			_controllerPath = controllerPath;
			_actionPath = actionPath;
			_payloadJSONObject = payloadJSONObject;
		}

		@Override
		public Serializable call() {
			try {
				Class<?> clazz = _loopNotificationEventHelper.getClass();

				StringBundler sb = new StringBundler(3);

				sb.append("processLoop");
				sb.append(StringUtil.upperCaseFirstLetter(_controllerPath));
				sb.append("Actions");

				Method processLoopActionsMethod = clazz.getDeclaredMethod(
					sb.toString(), String.class, JSONObject.class);

				ServiceBeanMethodInvocationFactoryUtil.proceed(
					_loopNotificationEventHelper, clazz,
					processLoopActionsMethod,
					new Object[] {_actionPath, _payloadJSONObject},
					new String[] {"transactionAdvice"});
			}
			catch (Exception e) {
			}

			return null;
		}

		private static final long serialVersionUID = 1L;

		private final String _actionPath;
		private final String _controllerPath;
		private final LoopNotificationEventHelper _loopNotificationEventHelper;
		private final JSONObject _payloadJSONObject;

	}

}