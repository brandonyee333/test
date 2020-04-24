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

package com.liferay.osb.loop.web.internal.composite;

import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopTopicConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageURL;
import com.liferay.osb.loop.web.internal.permission.LoopPermission;
import com.liferay.osb.loop.web.internal.util.LoopFeaturedContentUtil;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopSQLUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationSubscriptionUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class LoopTopicComposite
	extends BaseLoopComposite implements Comparable<LoopTopicComposite> {

	public LoopTopicComposite(Long loopTopicId, ThemeDisplay themeDisplay)
		throws Exception {

		this(LoopTopicLocalServiceUtil.getLoopTopic(loopTopicId), themeDisplay);
	}

	public LoopTopicComposite(LoopTopic loopTopic, ThemeDisplay themeDisplay) {
		this(themeDisplay);

		_loopTopic = loopTopic;
	}

	public LoopTopicComposite(ThemeDisplay themeDisplay) {
		super(themeDisplay);
	}

	@Override
	public int compareTo(LoopTopicComposite loopTopicComposite) {
		String name1 = StringUtil.lowerCase(getName());
		String name2 = StringUtil.lowerCase(loopTopicComposite.getName());

		return name1.compareTo(name2);
	}

	@Override
	public JSONObject getBaseJSONObject(JSONObject jsonObject)
		throws Exception {

		jsonObject = super.getBaseJSONObject(jsonObject);

		jsonObject.put("childLoopTopicsCount", getChildLoopTopicsCount());

		if (getAPIVersion() < 4) {
			jsonObject.put("coverImageProfileURL", getCoverImageProfileURL());
			jsonObject.put(
				"coverImageThumbnailURL", getCoverImageThumbnailURL());
		}
		else {
			jsonObject.put("coverImageData", getCoverImageDataJSONObject());
		}

		jsonObject.put("description", getDescription());
		jsonObject.put("displayURL", getDisplayURL());
		jsonObject.put("expert", isExpert());
		jsonObject.put(
			"featured",
			LoopFeaturedContentUtil.isFeatured(
				themeDisplay, "featuredLoopTopicIds", getLoopTopicId()));
		jsonObject.put("followersCount", getFollowersCount());
		jsonObject.put("following", isFollowing());
		jsonObject.put("followingType", getFollowingType());
		jsonObject.put(
			"loopTopicAssignmentsCount", getLoopTopicAssignmentsCount());
		jsonObject.put("mergeTime", getMergeTime());
		jsonObject.put("modifiedTime", getModifiedTime());
		jsonObject.put("name", getName());
		jsonObject.put("notifying", isNotifying());
		jsonObject.put("notifyingEmail", isNotifyingEmail());
		jsonObject.put("parentTopicId", getParentLoopTopicId());

		if (getAPIVersion() < 4) {
			jsonObject.put("profileImageURL", getProfileImageURL());
		}
		else {
			jsonObject.put("profileImageData", getProfileImageDataJSONObject());
		}

		jsonObject.put("type", getType());

		return jsonObject;
	}

	public int getChildLoopTopicsCount() throws Exception {
		List<Long> childLoopTopicIds = LoopTopicUtil.getAllChildLoopTopicIds(
			getLoopTopicId());

		return childLoopTopicIds.size();
	}

	public JSONObject getCoverImageDataJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String imageType : LoopWebConfigurationValues.IMAGE_COVER_TYPES) {
			LoopImageURL loopImageURL = new LoopImageURL(
				_loopTopic, "coverImagePayload", imageType);

			jsonObject.put("imageURL_" + imageType, loopImageURL.getImageURL());
		}

		return jsonObject;
	}

	public String getCoverImageProfileURL() {
		LoopImageURL loopImageURL = new LoopImageURL(
			_loopTopic, "coverImagePayload", "web");

		return loopImageURL.getImageURL();
	}

	public String getCoverImageThumbnailURL() {
		LoopImageURL loopImageURL = new LoopImageURL(
			_loopTopic, "coverImagePayload", "thumbnail");

		return loopImageURL.getImageURL();
	}

	public String getDescription() {
		return _loopTopic.getDescription();
	}

	@SuppressWarnings("unused")
	public String getDescriptionMarkdownHTML() throws Exception {
		return LoopMarkdownUtil.markdownToHtml(
			themeDisplay.getCompanyId(), getDescription(), false, false);
	}

	public String getDisplayURL() throws Exception {
		return LoopUtil.getDisplayURL(
			themeDisplay.getCompanyId(),
			PortalUtil.getClassNameId(LoopTopic.class),
			_loopTopic.getLoopTopicId());
	}

	@Override
	public long getEntityClassNameId() {
		return PortalUtil.getClassNameId(LoopTopic.class);
	}

	@Override
	public long getEntityClassPK() {
		return getLoopTopicId();
	}

	public int getFollowersCount() throws Exception {
		return LoopStreamEntryUtil.getFollowersCount(
			getEntityClassNameId(), getLoopTopicId());
	}

	public int getFollowingType() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopStreamEntryUtil.getFollowingType(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopTopicId());
	}

	public int getLoopTopicAssignmentsCount() throws Exception {
		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopTopicAssignmentModelImpl.TABLE_NAME, "loopTopicId",
				getLoopTopicId(), "status", LoopTopicConstants.STATUS_VERIFIED);

		return LoopPersonUtil.getLoopPersonCount(
			LoopTopicAssignmentModelImpl.TABLE_NAME, "loopPersonId",
			whereConditions);
	}

	public long getLoopTopicId() {
		return _loopTopic.getLoopTopicId();
	}

	public long getMergeTime() {
		return _loopTopic.getMergeTime();
	}

	public long getModifiedTime() {
		Date modifiedDate = _loopTopic.getModifiedDate();

		return modifiedDate.getTime();
	}

	public String getName() {
		return _loopTopic.getName();
	}

	public long getParentLoopTopicId() {
		return _loopTopic.getParentLoopTopicId();
	}

	public boolean getPermissionCreate() {
		return LoopPermission.contains(themeDisplay, "topics", "create");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionEdit() {
		return LoopPermission.contains(themeDisplay, _loopTopic, "edit");
	}

	public boolean getPermissionSetParent() {
		return LoopPermission.contains(themeDisplay, _loopTopic, "setParent");
	}

	public JSONObject getProfileImageDataJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String imageType :
				LoopWebConfigurationValues.IMAGE_PROFILE_TYPES) {

			LoopImageURL loopImageURL = new LoopImageURL(
				_loopTopic, "profileImagePayload", imageType);

			jsonObject.put("imageURL_" + imageType, loopImageURL.getImageURL());
		}

		return jsonObject;
	}

	public String getProfileImageURL() {
		LoopImageURL loopImageURL = new LoopImageURL(
			_loopTopic, "profileImagePayload", "web");

		return loopImageURL.getImageURL();
	}

	public int getType() {
		return LoopConstants.TYPE_LOOP_TOPIC;
	}

	public boolean isExpert() throws Exception {
		return LoopTopicAssignmentUtil.isExpert(
			themeDisplay.getUserId(), getLoopTopicId());
	}

	public boolean isFollowing() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopStreamEntryUtil.isFollowing(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopTopicId());
	}

	public boolean isNotifying() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopUserNotificationSubscriptionUtil.isNotifying(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopTopicId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
	}

	public boolean isNotifyingEmail() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopUserNotificationSubscriptionUtil.isNotifying(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopTopicId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
	}

	private LoopTopic _loopTopic;

}