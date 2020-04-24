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
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonRelConstants;
import com.liferay.osb.loop.web.internal.constants.LoopTopicConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageURL;
import com.liferay.osb.loop.web.internal.image.LoopPersonImageURL;
import com.liferay.osb.loop.web.internal.permission.LoopPermission;
import com.liferay.osb.loop.web.internal.util.LoopCompositeUtil;
import com.liferay.osb.loop.web.internal.util.LoopDivisionUtil;
import com.liferay.osb.loop.web.internal.util.LoopJobTitleUtil;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.osb.loop.web.internal.util.LoopParticipantAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopSQLUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationSubscriptionUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class LoopPersonComposite
	extends BaseLoopComposite implements Comparable<LoopPersonComposite> {

	public LoopPersonComposite(Long loopPersonId, ThemeDisplay themeDisplay)
		throws Exception {

		this(
			LoopPersonLocalServiceUtil.getLoopPerson(loopPersonId),
			themeDisplay);
	}

	public LoopPersonComposite(LoopPerson loopPerson, ThemeDisplay themeDisplay)
		throws Exception {

		this(themeDisplay);

		User user = UserLocalServiceUtil.getUser(loopPerson.getPersonUserId());

		_contact = user.getContact();

		_loopJobTitle = LoopJobTitleLocalServiceUtil.fetchLoopJobTitle(
			loopPerson.getLoopJobTitleId());
		_loopPerson = loopPerson;
		_user = user;
	}

	public LoopPersonComposite(ThemeDisplay themeDisplay) {
		super(themeDisplay);
	}

	@Override
	public int compareTo(LoopPersonComposite loopPersonComposite) {
		String lastName1 = StringUtil.lowerCase(getLastName());
		String lastName2 = StringUtil.lowerCase(
			loopPersonComposite.getLastName());

		int value = lastName1.compareTo(lastName2);

		if (value == 0) {
			String firstName1 = StringUtil.lowerCase(getFirstName());
			String firstName2 = StringUtil.lowerCase(
				loopPersonComposite.getFirstName());

			value = firstName1.compareTo(firstName2);
		}

		if (value == 0) {
			String middleName1 = StringUtil.lowerCase(getMiddleName());
			String middleName2 = StringUtil.lowerCase(
				loopPersonComposite.getMiddleName());

			value = middleName1.compareTo(middleName2);
		}

		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopPersonComposite)) {
			return false;
		}

		LoopPersonComposite loopPersonComposite = (LoopPersonComposite)obj;

		if (getLoopPersonId() == loopPersonComposite.getLoopPersonId()) {
			return true;
		}

		return false;
	}

	public String getAge() throws Exception {
		if (getShowAge()) {
			return String.valueOf(
				CalendarUtil.getAge(
					_contact.getBirthday(),
					CalendarFactoryUtil.getCalendar(
						themeDisplay.getTimeZone(), themeDisplay.getLocale())));
		}

		return StringPool.BLANK;
	}

	@Override
	public JSONObject getBaseJSONObject(JSONObject jsonObject)
		throws Exception {

		jsonObject = super.getBaseJSONObject(jsonObject);

		jsonObject.put("age", getAge());
		jsonObject.put("birthday", getBirthdayDisplay());

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
		jsonObject.put("emailAddress", getEmailAddress());

		if (getAPIVersion() == 1) {
			jsonObject.put(
				"employmentType", getEmploymentTypeLabel(themeDisplay));
		}
		else {
			jsonObject.put("employmentType", getEmploymentType());
		}

		jsonObject.put(
			"employmentTypeLabel", getEmploymentTypeLabel(themeDisplay));
		jsonObject.put("facebookSn", getFacebookSn());
		jsonObject.put("firstName", getFirstName());
		jsonObject.put("followersCount", getFollowersCount());
		jsonObject.put("following", isFollowing());
		jsonObject.put("followingCount", getFollowingCount());
		jsonObject.put("followingType", getFollowingType());
		jsonObject.put("gitHubSn", getGitHubSn());
		jsonObject.put("hireDate", getStartDateDisplay());
		jsonObject.put("inactive", isInactive());
		jsonObject.put("jobTitle", getLoopJobTitleName());
		jsonObject.put("languages", getLanguages());
		jsonObject.put("lastName", getLastName());
		jsonObject.put("linkedInSn", getLinkedInSn());
		jsonObject.put("locationName", getLocationName());
		jsonObject.put("locationSubtype", getLocationSubtype());
		jsonObject.put("loopJobTitleId", getLoopJobTitleId());
		jsonObject.put(
			"loopParticipantAssignmentsCount",
			getLoopParticipantAssignmentsCount());
		jsonObject.put(
			"loopTopicAssignmentsCount", getLoopTopicAssignmentsCount());
		jsonObject.put("male", isMale());
		jsonObject.put("modifiedTime", getModifiedTime());
		jsonObject.put("name", getName());
		jsonObject.put("notifying", isNotifying());
		jsonObject.put("notifyingEmail", isNotifyingEmail());
		jsonObject.put("personUserId", getPersonUserId());
		jsonObject.put("preferredName", getPreferredName());

		if (getAPIVersion() < 7) {
			jsonObject.put("primaryPhoneNumber", getPrimaryPhoneNumber());
		}
		else {
			jsonObject.put(
				"primaryPhoneNumber", getPrimaryPhoneNumberJSONObject());
		}

		if (getAPIVersion() < 4) {
			jsonObject.put("profileImageURL", getProfileImageURL());
		}
		else {
			jsonObject.put("profileImageData", getProfileImageDataJSONObject());
		}

		jsonObject.put("showAge", getShowAge());
		jsonObject.put("showBirthday", getShowBirthday());
		jsonObject.put("skypeSn", getSkypeSn());
		jsonObject.put("twitterSn", getTwitterSn());
		jsonObject.put("type", getType());
		jsonObject.put("uuid", getUuid());

		return jsonObject;
	}

	@SuppressWarnings("unused")
	public String getBirthday() {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"MMMMM dd, yyyy", themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		return dateFormat.format(_contact.getBirthday());
	}

	public String getBirthdayDisplay() throws Exception {
		if (!getShowBirthday()) {
			return StringPool.BLANK;
		}

		String dateFormatString = "MMMMM dd";

		if (getShowAge()) {
			dateFormatString = dateFormatString.concat(", yyyy");
		}

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			dateFormatString, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		return dateFormat.format(_contact.getBirthday());
	}

	public JSONObject getCoverImageDataJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String imageType : LoopWebConfigurationValues.IMAGE_COVER_TYPES) {
			jsonObject.put(
				"imageURL_" + imageType, getCoverImageURL(imageType));
		}

		return jsonObject;
	}

	public String getCoverImageProfileURL() {
		return getCoverImageURL("web");
	}

	public String getCoverImageThumbnailURL() {
		return getCoverImageURL("thumbnail");
	}

	@SuppressWarnings("unused")
	public JSONArray getDepartmentLoopDivisionCompositesJSONArray()
		throws Exception {

		return LoopCompositeUtil.getCompositesJSONArray(
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionComposites(
					themeDisplay, _loopPerson.getLoopPersonId(),
					LoopDivisionConstants.TYPE_DEPARTMENT, false,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS));
	}

	public String getDescription() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getString("description");
	}

	@SuppressWarnings("unused")
	public String getDescriptionMarkdownHTML() throws Exception {
		return LoopMarkdownUtil.markdownToHtml(
			themeDisplay.getCompanyId(), getDescription(), false, false);
	}

	public String getDisplayURL() throws Exception {
		return LoopUtil.getDisplayURL(
			themeDisplay.getCompanyId(),
			PortalUtil.getClassNameId(LoopPerson.class),
			_loopPerson.getLoopPersonId());
	}

	public String getEmailAddress() {
		return _user.getEmailAddress();
	}

	@SuppressWarnings("unused")
	public JSONArray getEmailAddressesJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<EmailAddress> emailAddresses = _user.getEmailAddresses();

		for (EmailAddress emailAddress : emailAddresses) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("address", emailAddress.getAddress());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public int getEmploymentType() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getInt("employmentType");
	}

	public String getEmploymentTypeLabel(ThemeDisplay themeDisplay)
		throws Exception {

		return themeDisplay.translate(
			LoopPersonConstants.getEmploymentTypeLabel(getEmploymentType()));
	}

	@Override
	public long getEntityClassNameId() {
		return PortalUtil.getClassNameId(LoopPerson.class);
	}

	@Override
	public long getEntityClassPK() {
		return getLoopPersonId();
	}

	public JSONObject getExtraDataJSONObject() throws Exception {
		return JSONFactoryUtil.createJSONObject(_loopPerson.getExtraData());
	}

	public String getFacebookSn() {
		return _contact.getFacebookSn();
	}

	public String getFirstName() {
		return _user.getFirstName();
	}

	public int getFollowersCount() throws Exception {
		return LoopStreamEntryUtil.getFollowersCount(
			getEntityClassNameId(), getLoopPersonId());
	}

	public int getFollowingCount() throws Exception {
		int followingLoopDivisionCount =
			LoopDivisionUtil.getFollowingLoopDivisionCount(getLoopPersonId());

		return followingLoopDivisionCount +
			LoopPersonUtil.getFollowingLoopPersonCount(getLoopPersonId()) +
				LoopTopicUtil.getFollowingLoopTopicCount(getLoopPersonId());
	}

	public int getFollowingType() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopStreamEntryUtil.getFollowingType(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopPersonId());
	}

	public String getGitHubSn() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getString("gitHubSn");
	}

	public String getJobResponsibilities() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getString("jobResponsibilities");
	}

	@SuppressWarnings("unused")
	public String getJobResponsibilitiesMarkdownHTML() throws Exception {
		return LoopMarkdownUtil.markdownToHtml(
			themeDisplay.getCompanyId(), getJobResponsibilities(), false,
			false);
	}

	public String getLanguages() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getString("languages");
	}

	public String getLastName() {
		return _user.getLastName();
	}

	public String getLinkedInSn() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getString("linkedInSn");
	}

	@SuppressWarnings("unused")
	public JSONArray getLocationLoopDivisionCompositesJSONArray()
		throws Exception {

		return LoopCompositeUtil.getCompositesJSONArray(
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionComposites(
					themeDisplay, _loopPerson.getLoopPersonId(),
					LoopDivisionConstants.TYPE_LOCATION, false,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS));
	}

	public String getLocationName() throws Exception {
		List<LoopDivisionComposite> loopDivisionComposites =
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionComposites(
					themeDisplay, getLoopPersonId(),
					LoopDivisionConstants.TYPE_LOCATION, false, 0, 1);

		if (loopDivisionComposites.isEmpty()) {
			return StringPool.BLANK;
		}

		LoopDivisionComposite loopDivisionComposite =
			loopDivisionComposites.get(0);

		return loopDivisionComposite.getName();
	}

	public int getLocationSubtype() throws Exception {
		List<LoopDivisionComposite> loopDivisionComposites =
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionComposites(
					themeDisplay, getLoopPersonId(),
					LoopDivisionConstants.TYPE_LOCATION, false, 0, 1);

		if (loopDivisionComposites.isEmpty()) {
			return 0;
		}

		LoopDivisionComposite loopDivisionComposite =
			loopDivisionComposites.get(0);

		return loopDivisionComposite.getSubtype();
	}

	@SuppressWarnings("unused")
	public JSONArray getLoopJobTitleCompositesJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LoopJobTitleComposite> loopJobTitleComposites =
			LoopJobTitleUtil.getLoopJobTitleComposites(themeDisplay);

		for (LoopJobTitleComposite loopJobTitleComposite :
				loopJobTitleComposites) {

			jsonArray.put(loopJobTitleComposite.getJSONObject());
		}

		return jsonArray;
	}

	public long getLoopJobTitleId() {
		return _loopPerson.getLoopJobTitleId();
	}

	public String getLoopJobTitleName() {
		if (_loopJobTitle != null) {
			return _loopJobTitle.getName();
		}

		return StringPool.BLANK;
	}

	public int getLoopParticipantAssignmentsCount() throws Exception {
		return LoopParticipantAssignmentUtil.
			getLoopParticipantAssignmentLoopDivisionCount(
				_loopPerson.getCompanyId(), _loopPerson.getLoopPersonId(), 0,
				true);
	}

	@SuppressWarnings("unused")
	public JSONArray getLoopPersonDisabledFieldsJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<String> loopPersonDisabledFields =
			LoopPersonUtil.getLoopPersonDisabledFields();

		for (String loopPersonDisabledField : loopPersonDisabledFields) {
			jsonArray.put(loopPersonDisabledField);
		}

		return jsonArray;
	}

	public long getLoopPersonId() {
		return _loopPerson.getLoopPersonId();
	}

	public int getLoopTopicAssignmentsCount() throws Exception {
		return LoopTopicAssignmentUtil.getLoopTopicAssignmentsCount(
			getLoopPersonId());
	}

	@SuppressWarnings("unused")
	public JSONArray getLoopTopicCompositesJSONArray() throws Exception {
		return LoopCompositeUtil.getCompositesJSONArray(
			LoopTopicAssignmentUtil.getLoopTopicAssignmentLoopTopicComposites(
				themeDisplay, _loopPerson.getLoopPersonId(),
				LoopTopicConstants.STATUS_VERIFIED));
	}

	@SuppressWarnings("unused")
	public JSONArray getManagerLoopPersonCompositesJSONArray()
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopPersonRelModelImpl.TABLE_NAME, "childLoopPersonId",
				_loopPerson.getLoopPersonId(), "type_",
				LoopPersonRelConstants.TYPE_MANAGER);

		List<LoopPersonComposite> loopPersonComposites =
			LoopPersonUtil.getLoopPersonComposites(
				themeDisplay, LoopPersonRelModelImpl.TABLE_NAME,
				"parentLoopPersonId", whereConditions, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		return LoopCompositeUtil.getCompositesJSONArray(loopPersonComposites);
	}

	public String getMiddleName() {
		return _user.getMiddleName();
	}

	public long getModifiedTime() {
		Date modifiedDate = _loopPerson.getModifiedDate();

		return modifiedDate.getTime();
	}

	public String getName() {
		return _user.getFullName();
	}

	@SuppressWarnings("unused")
	public JSONArray getOtherEmailAddressesJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (EmailAddress emailAddress : _user.getEmailAddresses()) {
			jsonArray.put(emailAddress.getAddress());
		}

		return jsonArray;
	}

	@SuppressWarnings("unused")
	public JSONArray getOtherPhonesJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Phone> phones = _user.getPhones();

		if (phones.size() > 1) {
			for (int i = 1; i < phones.size(); i++) {
				Phone phone = phones.get(i);

				if (getAPIVersion() < 7) {
					jsonArray.put(phone.getNumber());
				}
				else {
					jsonArray.put(getPhoneJSONObject(phone));
				}
			}
		}

		return jsonArray;
	}

	public boolean getPermissionAddAnnouncement() throws Exception {
		if (LoopParticipantAssignmentUtil.isLoopDivisionLead(
				getLoopPersonId())) {

			return true;
		}

		return LoopPermission.contains(themeDisplay, "feed", "addAnnouncement");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionAdmin() {
		return LoopPermission.contains(themeDisplay, "home", "admin");
	}

	public boolean getPermissionCreate() {
		return LoopPermission.contains(themeDisplay, "people", "create");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionDelete() {
		return LoopPermission.contains(themeDisplay, _loopPerson, "delete");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionEdit() {
		return LoopPermission.contains(themeDisplay, _loopPerson, "edit");
	}

	public boolean getPermissionSetRole() {
		return LoopPermission.contains(themeDisplay, "people", "setRole");
	}

	public boolean getPermissionUpdateFeaturedContent() {
		return LoopPermission.contains(themeDisplay, "updateFeaturedContent");
	}

	public long getPersonUserId() {
		return _user.getUserId();
	}

	@SuppressWarnings("unused")
	public JSONArray getPhonesJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Phone> phones = _user.getPhones();

		for (Phone phone : phones) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("number", phone.getNumber());
			jsonObject.put("typeId", phone.getTypeId());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@SuppressWarnings("unused")
	public JSONArray getPhoneTypesJSONArray() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<ListType> phoneListTypes = ListTypeServiceUtil.getListTypes(
			Contact.class.getName() + ListTypeConstants.PHONE);

		for (ListType phoneListType : phoneListTypes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("listTypeId", phoneListType.getListTypeId());
			jsonObject.put(
				"name", themeDisplay.translate(phoneListType.getName()));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public String getPreferredName() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getString("preferredName");
	}

	@SuppressWarnings("unused")
	public JSONArray getPrimaryManagerLoopPersonCompositesJSONArray()
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopPersonRelModelImpl.TABLE_NAME, "childLoopPersonId",
				_loopPerson.getLoopPersonId(), "type_",
				LoopPersonRelConstants.TYPE_PRIMARY_MANAGER);

		List<LoopPersonComposite> loopPersonComposites =
			LoopPersonUtil.getLoopPersonComposites(
				themeDisplay, LoopPersonRelModelImpl.TABLE_NAME,
				"parentLoopPersonId", whereConditions, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		return LoopCompositeUtil.getCompositesJSONArray(loopPersonComposites);
	}

	public String getPrimaryPhoneNumber() {
		String primaryPhoneNumber = StringPool.BLANK;

		try {
			List<Phone> phones = _user.getPhones();

			if (!phones.isEmpty()) {
				Phone phone = phones.get(0);

				primaryPhoneNumber = phone.getNumber();
			}
		}
		catch (Exception e) {
		}

		return primaryPhoneNumber;
	}

	public JSONObject getPrimaryPhoneNumberJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			List<Phone> phones = _user.getPhones();

			if (!phones.isEmpty()) {
				jsonObject = getPhoneJSONObject(phones.get(0));
			}
		}
		catch (Exception e) {
		}

		return jsonObject;
	}

	public JSONObject getProfileImageDataJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String imageType :
				LoopWebConfigurationValues.IMAGE_PROFILE_TYPES) {

			LoopImageURL loopImageURL = new LoopPersonImageURL(
				_loopPerson, "profileImagePayload", imageType);

			jsonObject.put("imageURL_" + imageType, loopImageURL.getImageURL());
		}

		return jsonObject;
	}

	public String getProfileImageURL() {
		LoopImageURL loopImageURL = new LoopPersonImageURL(
			_loopPerson, "profileImagePayload", "web");

		return loopImageURL.getImageURL();
	}

	public boolean getShowAge() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getBoolean("showAge");
	}

	public boolean getShowBirthday() throws Exception {
		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return extraDataJSONObject.getBoolean("showBirthday");
	}

	public String getSkypeSn() {
		return _contact.getSkypeSn();
	}

	public Calendar getStartDate() throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		calendar.setTimeInMillis(extraDataJSONObject.getLong("startTime"));

		return calendar;
	}

	public String getStartDateDisplay() throws Exception {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"MMMMM dd, yyyy", themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		JSONObject extraDataJSONObject = getExtraDataJSONObject();

		return dateFormat.format(extraDataJSONObject.getLong("startTime"));
	}

	@SuppressWarnings("unused")
	public JSONArray getTeamLoopDivisionCompositesJSONArray() throws Exception {
		return LoopCompositeUtil.getCompositesJSONArray(
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionComposites(
					themeDisplay, _loopPerson.getLoopPersonId(),
					LoopDivisionConstants.TYPE_TEAM, false, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS));
	}

	public String getTwitterSn() {
		return _contact.getTwitterSn();
	}

	public int getType() {
		return LoopConstants.TYPE_LOOP_PERSON;
	}

	public String getUuid() {
		return _user.getUuid();
	}

	public boolean isFollowing() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopStreamEntryUtil.isFollowing(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopPersonId());
	}

	public boolean isInactive() {
		if (_user.getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}

		return false;
	}

	public boolean isMale() {
		return _contact.isMale();
	}

	public boolean isNotifying() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopUserNotificationSubscriptionUtil.isNotifying(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopPersonId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
	}

	public boolean isNotifyingEmail() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopUserNotificationSubscriptionUtil.isNotifying(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopPersonId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
	}

	protected String getCoverImageURL(String type) {
		LoopImageURL loopImageURL = new LoopPersonImageURL(
			_loopPerson, "coverImagePayload", type);

		return loopImageURL.getImageURL();
	}

	protected JSONObject getPhoneJSONObject(Phone phone) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("phoneNumber", phone.getNumber());
		jsonObject.put("typeId", phone.getTypeId());

		return jsonObject;
	}

	private Contact _contact;
	private LoopJobTitle _loopJobTitle;
	private LoopPerson _loopPerson;
	private User _user;

}