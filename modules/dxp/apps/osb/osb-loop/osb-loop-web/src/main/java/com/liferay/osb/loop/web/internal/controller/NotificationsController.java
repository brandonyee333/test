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

package com.liferay.osb.loop.web.internal.controller;

import com.liferay.alloy.mvc.AlloyException;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.alloy.mvc.json.web.service.JSONWebServiceMethod;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.LoopUserNotificationEvent;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopTopicConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.messaging.LoopNotificationControllerMessageListener;
import com.liferay.osb.loop.web.internal.messaging.LoopUserNotificationMessageListener;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.osb.loop.web.internal.util.LoopHttpServletRequestUtil;
import com.liferay.osb.loop.web.internal.util.LoopParticipantAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopPortletPreferencesUtil;
import com.liferay.osb.loop.web.internal.util.LoopPushNotificationUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationSubscriptionUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Timothy Bell
 */
public class NotificationsController extends LoopAlloyControllerImpl {

	public NotificationsController() {
		setAlloyServiceInvokerClass(LoopUserNotificationEvent.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE)
	public void clearCount() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<LoopUserNotificationEvent> loopUserNotificationEvents =
			alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"recipientUserId", user.getUserId(), "received", false
				});

		for (LoopUserNotificationEvent loopUserNotificationEvent :
				loopUserNotificationEvents) {

			updateModel(loopUserNotificationEvent, "received", true);
		}

		LoopPersonUtil.updateGroupedUserNotificationEventsCount(
			user.getUserId(), 0);

		LoopPushNotificationUtil.sendPushNotification(
			new HashSet<>(Collections.singletonList(user.getUserId())), 0);

		respondWith("The user's notification count was successfully cleared.");
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = "loopUserNotificationEventGroupKey",
		parameterTypes = Long.class
	)
	public void markAsRead() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long groupKey = ParamUtil.getLong(
			request, "loopUserNotificationEventGroupKey");

		LoopUserNotificationEventUtil.setOpened(user.getUserId(), groupKey);

		respondWith("The notification was successfully marked as read.");
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.ACTION_PHASE,
		parameterNames = {"key", "type", "value"},
		parameterTypes = {String.class, String.class, Boolean.class}
	)
	public void saveSetting() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateSaveSetting();

		String key = ParamUtil.getString(request, "key");

		int deliveryType = ParamUtil.getInteger(request, "type");

		String deliveryTypeLabel = LoopUserNotificationConstants.getLabel(
			deliveryType);

		boolean value = ParamUtil.getBoolean(request, "value");

		boolean currentValue = false;

		String portletPreferenceValue =
			LoopPortletPreferencesUtil.getPreference(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				PortletKeys.PREFS_OWNER_TYPE_USER, key);

		if (ArrayUtil.contains(
				LoopUserNotificationConstants.SETTING_KEYS_TYPE_BOOLEAN, key)) {

			currentValue = GetterUtil.getBoolean(portletPreferenceValue);

			portletPreferenceValue = String.valueOf(value);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				portletPreferenceValue);

			currentValue = jsonObject.getBoolean(deliveryTypeLabel);

			jsonObject.put(deliveryTypeLabel, value);

			if (deliveryTypeLabel.equals(
					LoopUserNotificationConstants.LABEL_EMAIL) &&
				value) {

				jsonObject.put(
					LoopUserNotificationConstants.LABEL_IN_APP, true);
			}
			else if (deliveryTypeLabel.equals(
						LoopUserNotificationConstants.LABEL_IN_APP) &&
					 !value) {

				jsonObject.put(
					LoopUserNotificationConstants.LABEL_EMAIL, false);
			}

			portletPreferenceValue = jsonObject.toString();
		}

		if (value == currentValue) {
			respondWith(
				LoopPortletPreferencesUtil.getPreferencesJSONObject(
					themeDisplay, PortletKeys.PREFS_OWNER_TYPE_USER));

			return;
		}

		LoopPortletPreferencesUtil.setPreferences(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			PortletKeys.PREFS_OWNER_TYPE_USER, key, portletPreferenceValue);

		LoopPerson loopPerson =
			LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(
				themeDisplay.getUserId());

		if (key.equals(LoopUserNotificationConstants.SETTING_KEY_FOLLOWING)) {
			List<LoopStreamEntry> loopStreamEntries =
				LoopStreamEntryUtil.getLoopStreamEntries(
					loopPerson.getLoopPersonId(),
					LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING, true);

			for (LoopStreamEntry loopStreamEntry : loopStreamEntries) {
				_updateLoopUserNotificationSubscriptions(
					deliveryTypeLabel, value, loopPerson.getLoopPersonId(),
					loopStreamEntry.getClassNameId(),
					loopStreamEntry.getClassPK(), false);
			}
		}
		else if (key.equals(
					LoopUserNotificationConstants.SETTING_KEY_GROUP_MEMBER)) {

			List<Long> loopDivisionIds =
				LoopParticipantAssignmentUtil.
					getLoopParticipantAssignmentLoopDivisionIds(
						themeDisplay.getCompanyId(),
						loopPerson.getLoopPersonId(), 0, true,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (long loopDivisionId : loopDivisionIds) {
				_updateLoopUserNotificationSubscriptions(
					deliveryTypeLabel, value, loopPerson.getLoopPersonId(),
					PortalUtil.getClassNameId(LoopDivision.class),
					loopDivisionId, true);
			}
		}
		else if (key.equals(
					LoopUserNotificationConstants.
						SETTING_KEY_POSTS_I_COMMENTED_ON)) {

			AlloyServiceInvoker assetEntrySetAlloyServiceInvoker =
				new AlloyServiceInvoker(AssetEntrySet.class.getName());

			DynamicQuery assetEntrySetDynamicQuery =
				assetEntrySetAlloyServiceInvoker.buildDynamicQuery(
					new Object[] {
						"creatorClassNameId",
						PortalUtil.getClassNameId(LoopPerson.class),
						"creatorClassPK", loopPerson.getLoopPersonId(), "level",
						1
					});

			Projection parentAssetEntrySetIdProjection =
				ProjectionFactoryUtil.property("parentAssetEntrySetId");

			assetEntrySetDynamicQuery.setProjection(
				ProjectionFactoryUtil.distinct(
					parentAssetEntrySetIdProjection));

			List<Long> parentAssetEntrySetIds =
				assetEntrySetAlloyServiceInvoker.executeDynamicQuery(
					assetEntrySetDynamicQuery, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (long parentAssetEntrySetId : parentAssetEntrySetIds) {
				_updateLoopUserNotificationSubscriptions(
					deliveryTypeLabel, value, loopPerson.getLoopPersonId(),
					PortalUtil.getClassNameId(AssetEntrySet.class),
					parentAssetEntrySetId, true);
			}
		}
		else if (key.equals(
					LoopUserNotificationConstants.
						SETTING_KEY_COMMENTS_ON_MY_POSTS)) {

			AlloyServiceInvoker assetEntrySetAlloyServiceInvoker =
				new AlloyServiceInvoker(AssetEntrySet.class.getName());

			List<AssetEntrySet> assetEntrySets =
				assetEntrySetAlloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"creatorClassNameId",
						PortalUtil.getClassNameId(LoopPerson.class),
						"creatorClassPK", loopPerson.getLoopPersonId(), "level",
						0
					});

			for (AssetEntrySet assetEntrySet : assetEntrySets) {
				_updateLoopUserNotificationSubscriptions(
					deliveryTypeLabel, value, loopPerson.getLoopPersonId(),
					PortalUtil.getClassNameId(AssetEntrySet.class),
					assetEntrySet.getAssetEntrySetId(), true);
			}
		}
		else if (key.equals(
					LoopUserNotificationConstants.
						SETTING_KEY_REPLIES_TO_MY_COMMENTS)) {

			AlloyServiceInvoker assetEntrySetAlloyServiceInvoker =
				new AlloyServiceInvoker(AssetEntrySet.class.getName());

			List<AssetEntrySet> assetEntrySets =
				assetEntrySetAlloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"creatorClassNameId",
						PortalUtil.getClassNameId(LoopPerson.class),
						"creatorClassPK", loopPerson.getLoopPersonId(), "level",
						1
					});

			for (AssetEntrySet assetEntrySet : assetEntrySets) {
				_updateLoopUserNotificationSubscriptions(
					deliveryTypeLabel, value, loopPerson.getLoopPersonId(),
					PortalUtil.getClassNameId(AssetEntrySet.class),
					assetEntrySet.getAssetEntrySetId(), true);
			}
		}
		else if (key.equals(
					LoopUserNotificationConstants.SETTING_KEY_TOPIC_EXPERT)) {

			List<LoopTopicComposite> loopTopicComposites =
				LoopTopicAssignmentUtil.
					getLoopTopicAssignmentLoopTopicComposites(
						themeDisplay, loopPerson.getLoopPersonId(),
						LoopTopicConstants.STATUS_VERIFIED);

			for (LoopTopicComposite loopTopicComposite : loopTopicComposites) {
				_updateLoopUserNotificationSubscriptions(
					deliveryTypeLabel, value, loopPerson.getLoopPersonId(),
					PortalUtil.getClassNameId(LoopTopic.class),
					loopTopicComposite.getLoopTopicId(), true);
			}
		}

		respondWith(
			LoopPortletPreferencesUtil.getPreferencesJSONObject(
				themeDisplay, PortletKeys.PREFS_OWNER_TYPE_USER));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"timestamp", "end", "start"},
		parameterTypes = {Long.class, Integer.class, Integer.class}
	)
	public void viewNewNotifications() throws Exception {
		_getLoopUserNotificationEvents(true);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		methodName = "viewNewNotifications",
		parameterNames = {"timestamp", "end", "mobile", "start"},
		parameterTypes = {
			Long.class, Integer.class, Boolean.class, Integer.class
		}
	)
	public void viewNewNotifications2() throws Exception {
		viewNewNotifications();
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"timestamp", "end", "start"},
		parameterTypes = {Long.class, Integer.class, Integer.class}
	)
	public void viewOldNotifications() throws Exception {
		_getLoopUserNotificationEvents(false);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		methodName = "viewOldNotifications",
		parameterNames = {"timestamp", "end", "mobile", "start"},
		parameterTypes = {
			Long.class, Integer.class, Boolean.class, Integer.class
		}
	)
	public void viewOldNotifications2() throws Exception {
		viewOldNotifications();
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE)
	public void viewSettings() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		respondWith(
			LoopPortletPreferencesUtil.getPreferencesJSONObject(
				themeDisplay, PortletKeys.PREFS_OWNER_TYPE_USER));
	}

	@Override
	protected MessageListener buildControllerMessageListener() {
		return LoopNotificationControllerMessageListener.getInstance(this);
	}

	@Override
	protected MessageListener buildSchedulerMessageListener() {
		return LoopUserNotificationMessageListener.getInstance();
	}

	@Override
	protected String getControllerDestinationName() {
		return LoopUserNotificationConstants.CONTROLLER_DESTINATION_NAME;
	}

	@Override
	protected Trigger getSchedulerTrigger() {
		return TriggerFactoryUtil.createTrigger(
			getSchedulerJobName(), getMessageListenerGroupName(),
			LoopWebConfigurationValues.
				LOOP_CRON_TRIGGER_NOTIFICATIONS_CONTROLLER);
	}

	private void _getLoopUserNotificationEvents(
			boolean newUserNotificationEvents)
		throws Exception {

		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		long timestamp = ParamUtil.getLong(request, "timestamp");
		int start = ParamUtil.getInteger(request, "start");
		int end = ParamUtil.getInteger(request, "end");

		List<LoopUserNotificationEvent> loopUserNotificationEvents =
			LoopUserNotificationEventUtil.getLoopUserNotificationEvents(
				timestamp, user.getUserId(), newUserNotificationEvents, start,
				end);

		boolean mobile = LoopHttpServletRequestUtil.isMobileRequest(
			request.getHeader(HttpHeaders.USER_AGENT));

		for (LoopUserNotificationEvent loopUserNotificationEvent :
				loopUserNotificationEvents) {

			JSONObject groupedLoopUserNotificationEventJSONObject =
				LoopUserNotificationEventUtil.
					getGroupedLoopUserNotificationEventJSONObject(
						themeDisplay, loopUserNotificationEvent, mobile,
						getAPIVersion());

			if (groupedLoopUserNotificationEventJSONObject.length() == 0) {
				continue;
			}

			JSONObject displayJSONObject =
				groupedLoopUserNotificationEventJSONObject.getJSONObject(
					"displayJSONObject");

			groupedLoopUserNotificationEventJSONObject.put(
				"displayCompositeJSONObject",
				LoopUtil.getCompositeJSONObject(
					themeDisplay,
					displayJSONObject.getLong("entityClassNameId"),
					displayJSONObject.getLong("entityClassPK"),
					StringPool.BLANK));

			groupedLoopUserNotificationEventJSONObject.remove(
				"displayJSONObject");

			jsonArray.put(groupedLoopUserNotificationEventJSONObject);
		}

		LoopPerson loopPerson = LoopPersonUtil.getLoopPerson(user.getUserId());

		if (getAPIVersion() < 3) {
			jsonObject.put(
				"userNotificationEventsCount",
				loopPerson.getGroupedUserNotificationEventsCount());
			jsonObject.put("userNotificationEventsJSONArray", jsonArray);
		}
		else {
			jsonObject.put(
				"groupedUserNotificationEventsCount",
				loopPerson.getGroupedUserNotificationEventsCount());
			jsonObject.put("groupedUserNotificationEventsJSONArray", jsonArray);
		}

		respondWith(jsonObject);
	}

	private void _updateLoopUserNotificationSubscriptions(
			String deliveryTypeLabel, boolean enabled, long loopPersonId,
			long classNameId, long classPK, boolean checkFollowing)
		throws Exception {

		if (checkFollowing) {
			LoopStreamEntry loopStreamEntry =
				LoopStreamEntryUtil.fetchLoopStreamEntry(
					loopPersonId, LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					classNameId, classPK);

			if (classNameId == PortalUtil.getClassNameId(AssetEntrySet.class)) {
				if ((loopStreamEntry != null) &&
					!loopStreamEntry.isFollowing()) {

					return;
				}
			}
			else {
				if ((loopStreamEntry == null) ||
					!loopStreamEntry.isFollowing()) {

					return;
				}
			}
		}

		if (deliveryTypeLabel.equals(
				LoopUserNotificationConstants.LABEL_IN_APP) &&
			!enabled) {

			LoopUserNotificationSubscriptionUtil.
				deleteLoopUserNotificationSubscription(
					loopPersonId, classNameId, classPK,
					LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
			LoopUserNotificationSubscriptionUtil.
				deleteLoopUserNotificationSubscription(
					loopPersonId, classNameId, classPK,
					LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
		}
		else if (deliveryTypeLabel.equals(
					LoopUserNotificationConstants.LABEL_EMAIL) &&
				 enabled) {

			LoopUserNotificationSubscriptionUtil.
				updateLoopUserNotificationSubscription(
					this, loopPersonId, classNameId, classPK,
					LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
			LoopUserNotificationSubscriptionUtil.
				updateLoopUserNotificationSubscription(
					this, loopPersonId, classNameId, classPK,
					LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
		}
		else if ((deliveryTypeLabel.equals(
					LoopUserNotificationConstants.LABEL_IN_APP) &&
				  enabled) ||
				 (deliveryTypeLabel.equals(
					 LoopUserNotificationConstants.LABEL_EMAIL) &&
				  !enabled)) {

			LoopUserNotificationSubscriptionUtil.
				deleteLoopUserNotificationSubscription(
					loopPersonId, classNameId, classPK,
					LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
			LoopUserNotificationSubscriptionUtil.
				updateLoopUserNotificationSubscription(
					this, loopPersonId, classNameId, classPK,
					LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
		}
		else if (deliveryTypeLabel.equals(
					LoopUserNotificationConstants.LABEL_PUSH)) {

			if (enabled) {
				LoopUserNotificationSubscriptionUtil.
					updateLoopUserNotificationSubscription(
						this, loopPersonId, classNameId, classPK,
						LoopUserNotificationConstants.DELIVERY_TYPE_PUSH);
			}
			else {
				LoopUserNotificationSubscriptionUtil.
					deleteLoopUserNotificationSubscription(
						loopPersonId, classNameId, classPK,
						LoopUserNotificationConstants.DELIVERY_TYPE_PUSH);
			}
		}
	}

	private void _validateSaveSetting() throws Exception {
		String key = ParamUtil.getString(request, "key");
		int deliveryType = ParamUtil.getInteger(request, "type");
		boolean value = ParamUtil.getBoolean(request, "value");

		if (!ArrayUtil.contains(
				LoopUserNotificationConstants.SETTING_KEYS, key) ||
			(ArrayUtil.contains(
				LoopUserNotificationConstants.SETTING_KEYS_TYPE_JSON_OBJECT,
				key) &&
			 !ArrayUtil.contains(
				 LoopUserNotificationConstants.DELIVERY_TYPES, deliveryType))) {

			throw new AlloyException(
				"you-do-not-have-permission-to-access-the-requested-resource");
		}

		if (key.equals(LoopUserNotificationConstants.SETTING_KEY_MENTIONED) &&
			(deliveryType ==
				LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP) &&
			!value) {

			throw new AlloyException(
				"you-cannot-turn-off-the-setting-for-this-type");
		}

		if ((key.equals(
				LoopUserNotificationConstants.
					SETTING_KEY_LIKED_COMMENTS_AND_REPLIES) ||
			 key.equals(
				 LoopUserNotificationConstants.SETTING_KEY_LIKED_POST)) &&
			(deliveryType ==
				LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL)) {

			throw new AlloyException(
				"the-setting-for-this-type-is-unsupported");
		}
	}

}