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
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLikeLocalServiceUtil;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.AssetEntrySetComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationUtil;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class AssetEntrySetUtil {

	public static AssetEntrySet addAssetEntrySet(
			HttpServletRequest request, ThemeDisplay themeDisplay,
			long parentAssetEntrySetId, long classNameId, long classPK,
			long creatorClassNameId, long creatorClassPK, String payload,
			boolean privateAssetEntrySet, long stickyTime, String title,
			int type, int status)
		throws Exception {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		processMarkdownMessage(request, themeDisplay, payloadJSONObject, type);

		LoopMarkdownUtil.appendSharedTo(payloadJSONObject);

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.addAssetEntrySet(
				themeDisplay.getUserId(), parentAssetEntrySetId, classNameId,
				classPK, creatorClassNameId, creatorClassPK, payloadJSONObject,
				privateAssetEntrySet, stickyTime, title, type, status);

		setImagePermissions(
			themeDisplay,
			JSONFactoryUtil.createJSONObject(assetEntrySet.getPayload()));

		return assetEntrySet;
	}

	public static JSONObject addFileAttachment(long userId, File file)
		throws Exception {

		return AssetEntrySetLocalServiceUtil.addFileAttachment(userId, file);
	}

	public static AssetEntrySet deleteAssetEntrySet(long assetEntrySetId)
		throws Exception {

		return AssetEntrySetLocalServiceUtil.deleteAssetEntrySet(
			assetEntrySetId);
	}

	public static void deleteAssetEntrySets(long classNameId, long classPK)
		throws Exception {

		List<AssetEntrySet> assetEntrySets =
			AssetEntrySetLocalServiceUtil.getAssetEntrySets(
				classNameId, classPK);

		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			deleteAssetEntrySet(assetEntrySet.getAssetEntrySetId());
		}
	}

	public static List<AssetEntrySet> getAllChildAssetEntrySets(
		AssetEntrySet assetEntrySet) {

		List<AssetEntrySet> assetEntrySets = new ArrayList<>();

		List<AssetEntrySet> childAssetEntrySets =
			AssetEntrySetLocalServiceUtil.getChildAssetEntrySets(
				assetEntrySet.getAssetEntrySetId());

		for (AssetEntrySet childAssetEntrySet : childAssetEntrySets) {
			assetEntrySets.add(childAssetEntrySet);
			assetEntrySets.addAll(
				getAllChildAssetEntrySets(childAssetEntrySet));
		}

		return assetEntrySets;
	}

	public static JSONObject getAssetEntrySetJSONObject(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			long untruncatedAssetEntrySetId, int childAssetEntrySetsLimit,
			int likedParticipantsLimit)
		throws Exception {

		if (childAssetEntrySetsLimit > 0) {
			setChildAssetEntrySets(assetEntrySet, childAssetEntrySetsLimit);
		}

		preprocessPayload(
			themeDisplay, assetEntrySet, untruncatedAssetEntrySetId,
			likedParticipantsLimit);

		AssetEntrySetComposite assetEntrySetComposite =
			new AssetEntrySetComposite(
				themeDisplay, assetEntrySet, untruncatedAssetEntrySetId);

		return assetEntrySetComposite.getJSONObject();
	}

	public static List<AssetEntrySet> getNewChildAssetEntrySets(
			long createTime, long parentAssetEntrySetId, boolean ascending,
			int start, int end)
		throws Exception {

		OrderByComparator orderByComparator =
			OrderByComparatorFactoryUtil.create(
				"AssetEntrySet", "createTime", ascending);

		List<AssetEntrySet> assetEntrySets =
			AssetEntrySetLocalServiceUtil.getNewChildAssetEntrySets(
				createTime, parentAssetEntrySetId, start, end,
				orderByComparator);

		assetEntrySets = ListUtil.copy(assetEntrySets);

		if (!ascending) {
			Collections.reverse(assetEntrySets);
		}

		return assetEntrySets;
	}

	public static List<AssetEntrySet> getOldChildAssetEntrySets(
			long createTime, long parentAssetEntrySetId, int start, int end)
		throws Exception {

		OrderByComparator orderByComparator =
			OrderByComparatorFactoryUtil.create(
				"AssetEntrySet", "createTime", false);

		List<AssetEntrySet> assetEntrySets =
			AssetEntrySetLocalServiceUtil.getOldChildAssetEntrySets(
				createTime, parentAssetEntrySetId, start, end,
				orderByComparator);

		assetEntrySets = ListUtil.copy(assetEntrySets);

		Collections.reverse(assetEntrySets);

		return assetEntrySets;
	}

	public static JSONArray getSharedToJSONArray(
			ThemeDisplay themeDisplay, JSONObject payloadJSONObject)
		throws Exception {

		JSONArray returnedSharedToJSONArray = JSONFactoryUtil.createJSONArray();

		JSONArray payloadSharedToJSONArray = payloadJSONObject.getJSONArray(
			LoopAssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		if (payloadSharedToJSONArray == null) {
			return returnedSharedToJSONArray;
		}

		for (int i = 0; i < payloadSharedToJSONArray.length(); i++) {
			JSONObject participantJSONObject =
				payloadSharedToJSONArray.getJSONObject(i);

			long entityClassNameId = participantJSONObject.getLong(
				"entityClassNameId");
			long entityClassPK = participantJSONObject.getLong("entityClassPK");
			String name = participantJSONObject.getString("name");

			returnedSharedToJSONArray.put(
				LoopUtil.getCompositeJSONObject(
					themeDisplay, entityClassNameId, entityClassPK, name));
		}

		return returnedSharedToJSONArray;
	}

	public static void sendActivityLogEmail(
		User user, String action, JSONObject assetEntrySetJSONObjectComposite) {

		sendActivityLogEmail(
			user, action, assetEntrySetJSONObjectComposite, null);
	}

	public static void sendActivityLogEmail(
		User user, String action, JSONObject assetEntrySetJSONObjectComposite,
		JSONObject oldAssetEntrySetJSONObjectComposite) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("action", action);
		jsonObject.put("data", assetEntrySetJSONObjectComposite);
		jsonObject.put("modifiedBy", user.getEmailAddress());
		jsonObject.put("modifiedTime", new Date().getTime());

		if ((oldAssetEntrySetJSONObjectComposite != null) &&
			(oldAssetEntrySetJSONObjectComposite.length() > 0)) {

			jsonObject.put("oldData", oldAssetEntrySetJSONObjectComposite);

			if (Objects.equals(
					assetEntrySetJSONObjectComposite.getString("payload"),
					oldAssetEntrySetJSONObjectComposite.getString("payload")) &&
				Objects.equals(
					assetEntrySetJSONObjectComposite.getString("title"),
					oldAssetEntrySetJSONObjectComposite.getString("title"))) {

				return;
			}
		}

		LoopEmailNotificationUtil.sendActivityLogEmail(
			"Loop Page", null, jsonObject);
	}

	public static void setChildAssetEntrySets(
			AssetEntrySet assetEntrySet, int childAssetEntrySetsLimit)
		throws Exception {

		List<AssetEntrySet> childAssetEntrySets = getNewChildAssetEntrySets(
			0, assetEntrySet.getAssetEntrySetId(), false, 0,
			childAssetEntrySetsLimit);

		assetEntrySet.setChildAssetEntrySets(childAssetEntrySets);
	}

	public static AssetEntrySet updateAssetEntrySet(
			HttpServletRequest request, ThemeDisplay themeDisplay,
			long assetEntrySetId, String payload, boolean privateAssetEntrySet,
			long stickyTime, String title, int type, int status)
		throws Exception {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		processMarkdownMessage(request, themeDisplay, payloadJSONObject, type);

		LoopMarkdownUtil.appendSharedTo(payloadJSONObject);

		AssetEntrySet assetEntrySet =
			AssetEntrySetLocalServiceUtil.updateAssetEntrySet(
				assetEntrySetId, payloadJSONObject, privateAssetEntrySet,
				stickyTime, title, type, status);

		setImagePermissions(
			themeDisplay,
			JSONFactoryUtil.createJSONObject(assetEntrySet.getPayload()));

		return assetEntrySet;
	}

	protected static void preprocessPayload(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			long untruncatedAssetEntrySetId, int likedParticipantsLimit)
		throws Exception {

		LoopPerson loopPerson =
			LoopPersonLocalServiceUtil.fetchLoopPersonByPersonUserId(
				themeDisplay.getUserId());

		if (loopPerson == null) {
			return;
		}

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		HttpServletRequest request = themeDisplay.getRequest();

		int apiVersion = request.getIntHeader("api-version");

		if (apiVersion < 9) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			AssetEntrySetComposite assetEntrySetComposite =
				new AssetEntrySetComposite(themeDisplay, assetEntrySet);

			jsonObject.put(
				"deleteAssetEntrySet",
				assetEntrySetComposite.getPermissionDelete());
			jsonObject.put(
				"editAssetEntrySet",
				assetEntrySetComposite.getPermissionEdit());
			jsonObject.put(
				"updateAssetEntrySet",
				assetEntrySetComposite.getPermissionUpdate());

			payloadJSONObject.put("permissions", jsonObject);

			if (apiVersion < 7) {
				payloadJSONObject.put("title", assetEntrySet.getTitle());
			}
		}

		setBookmarked(
			loopPerson.getLoopPersonId(), assetEntrySet, payloadJSONObject);
		setGeolocation(payloadJSONObject);
		setLikedParticipants(
			themeDisplay, loopPerson.getLoopPersonId(), assetEntrySet,
			payloadJSONObject, likedParticipantsLimit);
		setMessage(
			assetEntrySet, payloadJSONObject, untruncatedAssetEntrySetId,
			themeDisplay.getCompanyId());
		setSharedToParticipants(themeDisplay, assetEntrySet, payloadJSONObject);

		assetEntrySet.setPayload(
			JSONFactoryUtil.looseSerialize(payloadJSONObject));
	}

	protected static void processMarkdownMessage(
			HttpServletRequest request, ThemeDisplay themeDisplay,
			JSONObject payloadJSONObject, int type)
		throws Exception {

		String message = payloadJSONObject.getString("message");

		payloadJSONObject.put("rawMessage", message);

		boolean anchorLinkEnabled = false;

		if (type == LoopAssetEntrySetConstants.TYPE_PAGE) {
			anchorLinkEnabled = true;
		}

		message = LoopMarkdownUtil.markdownToHtml(
			themeDisplay.getCompanyId(), message, true, false,
			anchorLinkEnabled);

		payloadJSONObject.put("message", message);

		String typeLabel = LoopAssetEntrySetConstants.LABEL_POST;

		if (LoopAssetEntrySetConstants.TYPE_PAGE == type) {
			typeLabel = LoopAssetEntrySetConstants.LABEL_PAGE;
		}

		Filter filter = new Filter(typeLabel);

		LoopHTMLTruncator htmlTruncator = new LoopHTMLTruncator(
			LoopUtil.getPortalURL(request, themeDisplay.getCompanyId()),
			message,
			GetterUtil.getInteger(
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.
						LOOP_TRUNCATED_FEED_LINE_CHAR_COUNT,
					filter)),
			GetterUtil.getInteger(
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.
						LOOP_TRUNCATED_FEED_LINE_CHAR_COUNT_TABLE_ELEMENT,
					filter)),
			GetterUtil.getInteger(
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.
						LOOP_TRUNCATED_FEED_LINE_COUNT_GRACE,
					filter)),
			GetterUtil.getInteger(
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.
						LOOP_TRUNCATED_FEED_LINE_COUNT_IMAGE,
					filter)),
			GetterUtil.getInteger(
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.
						LOOP_TRUNCATED_FEED_LINE_COUNT_IMAGE_TABLE_ELEMENT,
					filter)),
			GetterUtil.getInteger(
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.LOOP_TRUNCATED_FEED_LINE_COUNT,
					filter)));

		String truncatedHtml = htmlTruncator.truncateHtml();

		payloadJSONObject.put("truncatedMessage", truncatedHtml);

		payloadJSONObject.put("truncated", Validator.isNotNull(truncatedHtml));
	}

	protected static void setBookmarked(
			long loopPersonId, AssetEntrySet assetEntrySet,
			JSONObject payloadJSONObject)
		throws Exception {

		boolean bookmarked = false;

		LoopStreamEntry loopStreamEntry =
			LoopStreamEntryUtil.fetchLoopStreamEntry(
				loopPersonId, LoopStreamConstants.LOOP_STREAM_ID_BOOKMARKS,
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getAssetEntrySetId());

		if (loopStreamEntry != null) {
			bookmarked = true;
		}

		payloadJSONObject.put("bookmarked", bookmarked);
	}

	protected static void setGeolocation(JSONObject payloadJSONObject) {
		JSONObject geolocationJSONObject = payloadJSONObject.getJSONObject(
			"geolocation");

		if (geolocationJSONObject != null) {
			geolocationJSONObject.remove("latitude");
			geolocationJSONObject.remove("longitude");
		}

		payloadJSONObject.put("geolocation", geolocationJSONObject);
	}

	protected static void setImagePermissions(
			ThemeDisplay themeDisplay, JSONObject payloadJSONObject)
		throws Exception {

		JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
			"imageData");

		if (imageDataJSONArray == null) {
			return;
		}

		for (int i = 0; i < imageDataJSONArray.length(); i++) {
			JSONObject imageDataJSONObject = imageDataJSONArray.getJSONObject(
				i);

			JSONObject fileEntryIdJSONObject =
				imageDataJSONObject.getJSONObject("fileEntryIds");

			if (fileEntryIdJSONObject == null) {
				return;
			}

			Iterator<String> iterator = fileEntryIdJSONObject.keys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				long fileEntryId = fileEntryIdJSONObject.getLong(key);

				LoopImageUtil.addResourcePermission(
					themeDisplay.getCompanyId(), fileEntryId,
					LoopRoleConstants.LOOP_PERSON);
			}
		}
	}

	protected static void setLikedParticipants(
			ThemeDisplay themeDisplay, long loopPersonId,
			AssetEntrySet assetEntrySet, JSONObject payloadJSONObject,
			int likedParticipantsLimit)
		throws Exception {

		if (assetEntrySet.getAssetEntrySetLikesCount() == 0) {
			return;
		}

		JSONObject likedParticipantsJSONObject =
			JSONFactoryUtil.createJSONObject();

		AssetEntrySetLike assetEntrySetLike =
			AssetEntrySetLikeLocalServiceUtil.fetchAssetEntrySetLike(
				assetEntrySet.getAssetEntrySetId(),
				PortalUtil.getClassNameId(LoopPerson.class), loopPersonId);

		boolean liked = false;

		if (assetEntrySetLike != null) {
			liked = true;
		}

		likedParticipantsJSONObject.put("liked", liked);

		if (assetEntrySet.getLevel() == 0) {
			JSONArray participantsJSONArray = JSONFactoryUtil.createJSONArray();

			if (liked && (likedParticipantsLimit > 0)) {
				likedParticipantsLimit = likedParticipantsLimit - 1;
			}

			List<AssetEntrySetLike> assetEntrySetLikes =
				AssetEntrySetLikeLocalServiceUtil.getAssetEntrySetLikes(
					assetEntrySet.getAssetEntrySetId(),
					PortalUtil.getClassNameId(LoopPerson.class), loopPersonId,
					0, likedParticipantsLimit);

			for (AssetEntrySetLike curAssetEntrySetLike : assetEntrySetLikes) {
				participantsJSONArray.put(
					LoopUtil.getCompositeJSONObject(
						themeDisplay, curAssetEntrySetLike.getClassNameId(),
						curAssetEntrySetLike.getClassPK(), StringPool.BLANK));
			}

			likedParticipantsJSONObject.put(
				"participants", participantsJSONArray);
		}

		payloadJSONObject.put(
			LoopAssetEntrySetConstants.PAYLOAD_KEY_LIKED_PARTICIPANTS,
			likedParticipantsJSONObject);
	}

	protected static void setMessage(
			AssetEntrySet assetEntrySet, JSONObject payloadJSONObject,
			long untruncatedAssetEntrySetId, long companyId)
		throws Exception {

		String message = payloadJSONObject.getString("message");

		boolean showTruncated = true;

		if (assetEntrySet.getAssetEntrySetId() == untruncatedAssetEntrySetId) {
			showTruncated = false;
		}

		boolean truncated = payloadJSONObject.getBoolean("truncated");

		if (showTruncated && truncated) {
			message = payloadJSONObject.getString("truncatedMessage");
		}
		else {
			payloadJSONObject.put("truncated", false);
		}

		payloadJSONObject.remove("truncatedMessage");

		payloadJSONObject.put(
			"message",
			LoopMarkdownUtil.interpretTokens(
				companyId, message,
				payloadJSONObject.getJSONArray(
					LoopAssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO),
				true));
	}

	protected static void setSharedToParticipants(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			JSONObject payloadJSONObject)
		throws Exception {

		JSONObject creatorJSONObject = LoopUtil.getCompositeJSONObject(
			themeDisplay, assetEntrySet.getCreatorClassNameId(),
			assetEntrySet.getCreatorClassPK(), assetEntrySet.getCreatorName());

		payloadJSONObject.put(
			LoopAssetEntrySetConstants.PAYLOAD_KEY_CREATOR, creatorJSONObject);

		JSONArray sharedToJSONArray = getSharedToJSONArray(
			themeDisplay, payloadJSONObject);

		payloadJSONObject.put(
			LoopAssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
			sharedToJSONArray);
	}

}