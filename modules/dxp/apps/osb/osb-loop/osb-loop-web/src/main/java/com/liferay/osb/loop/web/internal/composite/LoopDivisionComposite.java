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

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.impl.LoopParticipantAssignmentModelImpl;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopParticipantAssignmentConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.image.LoopDivisionImageURL;
import com.liferay.osb.loop.web.internal.image.LoopImageURL;
import com.liferay.osb.loop.web.internal.permission.LoopPermission;
import com.liferay.osb.loop.web.internal.util.LoopCompositeUtil;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.osb.loop.web.internal.util.LoopParticipantAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopSQLUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationSubscriptionUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class LoopDivisionComposite
	extends BaseLoopComposite implements Comparable<LoopDivisionComposite> {

	public LoopDivisionComposite(Long loopDivisionId, ThemeDisplay themeDisplay)
		throws Exception {

		this(
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId),
			themeDisplay);
	}

	public LoopDivisionComposite(
			LoopDivision loopDivision, ThemeDisplay themeDisplay)
		throws Exception {

		this(themeDisplay);

		_loopDivision = loopDivision;
		_organization = OrganizationLocalServiceUtil.getOrganization(
			loopDivision.getOrganizationId());
	}

	public LoopDivisionComposite(ThemeDisplay themeDisplay) {
		super(themeDisplay);
	}

	@Override
	public int compareTo(LoopDivisionComposite loopDivisionComposite) {
		String name1 = StringUtil.lowerCase(getName());
		String name2 = StringUtil.lowerCase(loopDivisionComposite.getName());

		return name1.compareTo(name2);
	}

	@Override
	public JSONObject getBaseJSONObject(JSONObject jsonObject)
		throws Exception {

		jsonObject = super.getBaseJSONObject(jsonObject);

		jsonObject.put("childLoopDivisionsCount", getChildLoopDivisionsCount());

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
		jsonObject.put("followersCount", getFollowersCount());
		jsonObject.put("following", isFollowing());
		jsonObject.put("followingType", getFollowingType());
		jsonObject.put("gitHubRepositories", getGitHubRepositories());
		jsonObject.put(
			"loopParticipantAssignmentsCount",
			getLoopParticipantAssignmentsCount());
		jsonObject.put("modifiedTime", getModifiedTime());
		jsonObject.put("name", getName());
		jsonObject.put("notifying", isNotifying());
		jsonObject.put("notifyingEmail", isNotifyingEmail());
		jsonObject.put("preferredName", getPreferredName());

		if (getAPIVersion() < 4) {
			jsonObject.put("profileImageURL", getProfileImageURL());
		}
		else {
			jsonObject.put("profileImageData", getProfileImageDataJSONObject());
		}

		jsonObject.put("subtype", getSubtype());
		jsonObject.put("type", getType());

		return jsonObject;
	}

	public int getChildLoopDivisionsCount() throws Exception {
		AlloyServiceInvoker loopDivisionAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopDivision.class.getName());

		long count = loopDivisionAlloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {
				"companyId", themeDisplay.getCompanyId(),
				"parentLoopDivisionId", getLoopDivisionId()
			});

		return Math.toIntExact(count);
	}

	public JSONObject getCoverImageDataJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String imageType : LoopWebConfigurationValues.IMAGE_COVER_TYPES) {
			LoopImageURL loopImageURL = new LoopDivisionImageURL(
				_loopDivision, "coverImagePayload", imageType);

			jsonObject.put("imageURL_" + imageType, loopImageURL.getImageURL());
		}

		return jsonObject;
	}

	public String getCoverImageProfileURL() {
		LoopImageURL loopImageURL = new LoopDivisionImageURL(
			_loopDivision, "coverImagePayload", "web");

		return loopImageURL.getImageURL();
	}

	public String getCoverImageThumbnailURL() {
		LoopImageURL loopImageURL = new LoopDivisionImageURL(
			_loopDivision, "coverImagePayload", "thumbnail");

		return loopImageURL.getImageURL();
	}

	public String getDescription() {
		return _organization.getComments();
	}

	@SuppressWarnings("unused")
	public String getDescriptionMarkdownHTML() throws Exception {
		return LoopMarkdownUtil.markdownToHtml(
			themeDisplay.getCompanyId(), getDescription(), false, false);
	}

	@SuppressWarnings("unused")
	public JSONArray getDirectMemberLoopPersonCompositesJSONArray()
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopParticipantAssignmentModelImpl.TABLE_NAME, "loopDivisionId",
				_loopDivision.getLoopDivisionId(), "type_",
				LoopParticipantAssignmentConstants.TYPE_MEMBER);

		List<LoopPersonComposite> loopPersonComposites =
			LoopPersonUtil.getLoopPersonComposites(
				themeDisplay, LoopParticipantAssignmentModelImpl.TABLE_NAME,
				"loopPersonId", whereConditions, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		return LoopCompositeUtil.getCompositesJSONArray(
			loopPersonComposites,
			new String[] {"entityClassPK", "name", "type"}, false);
	}

	public String getDisplayURL() throws Exception {
		return LoopUtil.getDisplayURL(
			themeDisplay.getCompanyId(),
			PortalUtil.getClassNameId(LoopDivision.class),
			_loopDivision.getLoopDivisionId());
	}

	@Override
	public long getEntityClassNameId() {
		return PortalUtil.getClassNameId(LoopDivision.class);
	}

	@Override
	public long getEntityClassPK() {
		return getLoopDivisionId();
	}

	public int getFollowersCount() throws Exception {
		return LoopStreamEntryUtil.getFollowersCount(
			getEntityClassNameId(), getLoopDivisionId());
	}

	public int getFollowingType() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopStreamEntryUtil.getFollowingType(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopDivisionId());
	}

	public String getGitHubRepositories() throws Exception {
		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
			_loopDivision.getExtraData());

		return extraDataJSONObject.getString("gitHubRepositories");
	}

	@SuppressWarnings("unused")
	public JSONArray getInheritedMemberLoopPersonCompositesJSONArray()
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopParticipantAssignmentModelImpl.TABLE_NAME, "loopDivisionId",
				_loopDivision.getLoopDivisionId(), "type_",
				LoopParticipantAssignmentConstants.TYPE_INHERITED);

		List<LoopPersonComposite> loopPersonComposites =
			LoopPersonUtil.getLoopPersonComposites(
				themeDisplay, LoopParticipantAssignmentModelImpl.TABLE_NAME,
				"loopPersonId", whereConditions, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		return LoopCompositeUtil.getCompositesJSONArray(
			loopPersonComposites,
			new String[] {"entityClassPK", "name", "type"}, false);
	}

	public JSONObject getJSONObject() throws Exception {
		return getJSONObject(new String[0]);
	}

	public List<LoopPersonComposite> getLeadAssistantLoopPersonComposites()
		throws Exception {

		List<LoopPersonComposite> leadAssistantLoopPersonComposites =
			new ArrayList<>();

		List<LoopPersonComposite> leadLoopPersonComposites =
			getLeadLoopPersonComposites();

		Role role = RoleLocalServiceUtil.getRole(
			themeDisplay.getCompanyId(), LoopRoleConstants.LOOP_DIVISION_LEAD);

		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
				getOrganizationGroupId(), role.getRoleId());

		for (UserGroupRole userGroupRole : userGroupRoles) {
			User user = userGroupRole.getUser();

			if (user.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				continue;
			}

			LoopPerson loopPerson =
				LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(
					userGroupRole.getUserId());

			LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
				loopPerson.getLoopPersonId(), themeDisplay);

			if (!leadLoopPersonComposites.contains(loopPersonComposite)) {
				leadAssistantLoopPersonComposites.add(loopPersonComposite);
			}
		}

		return leadAssistantLoopPersonComposites;
	}

	@SuppressWarnings("unused")
	public JSONArray getLeadAssistantLoopPersonCompositesJSONArray()
		throws Exception {

		return LoopCompositeUtil.getCompositesJSONArray(
			getLeadAssistantLoopPersonComposites(),
			new String[] {"entityClassPK", "name", "type", "uuid"}, false);
	}

	public List<LoopPersonComposite> getLeadLoopPersonComposites()
		throws Exception {

		if (_leadLoopPersonComposites == null) {
			_leadLoopPersonComposites =
				LoopParticipantAssignmentUtil.
					getLoopParticipantAssignmentLoopPersonComposites(
						themeDisplay, getLoopDivisionId(), _loopDivision,
						LoopParticipantAssignmentConstants.TYPE_LEAD,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return _leadLoopPersonComposites;
	}

	@SuppressWarnings("unused")
	public JSONArray getLeadLoopPersonCompositesJSONArray() throws Exception {
		return LoopCompositeUtil.getCompositesJSONArray(
			getLeadLoopPersonComposites(),
			new String[] {"entityClassPK", "name", "type", "uuid"}, false);
	}

	public long getLoopDivisionId() {
		return _loopDivision.getLoopDivisionId();
	}

	public int getLoopParticipantAssignmentsCount() throws Exception {
		return LoopParticipantAssignmentUtil.getLoopParticipantAssignmentsCount(
			_loopDivision, 0);
	}

	public List<LoopPersonComposite> getMemberLoopPersonComposites()
		throws Exception {

		if (_memberLoopPersonComposites == null) {
			_memberLoopPersonComposites =
				LoopParticipantAssignmentUtil.
					getLoopParticipantAssignmentLoopPersonComposites(
						themeDisplay, getLoopDivisionId(), _loopDivision,
						LoopParticipantAssignmentConstants.TYPE_MEMBER,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return _memberLoopPersonComposites;
	}

	public long getModifiedTime() {
		Date modifiedDate = _loopDivision.getModifiedDate();

		return modifiedDate.getTime();
	}

	public String getName() {
		return _organization.getName();
	}

	public long getOrganizationGroupId() {
		return _organization.getGroupId();
	}

	@SuppressWarnings("unused")
	public int getPagesCount() {
		return AssetEntrySetLocalServiceUtil.getAssetEntrySetsCount(
			PortalUtil.getClassNameId(LoopDivision.class), getLoopDivisionId(),
			LoopAssetEntrySetConstants.TYPE_PAGE);
	}

	@SuppressWarnings("unused")
	public boolean getPermissionAddPages() {
		return LoopPermission.contains(themeDisplay, _loopDivision, "addPages");
	}

	public boolean getPermissionCreate() {
		return LoopPermission.contains(themeDisplay, "divisions", "create");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionCreatePages() {
		return LoopPermission.contains(
			themeDisplay, _loopDivision, "createPages");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionEdit() {
		return LoopPermission.contains(themeDisplay, _loopDivision, "edit");
	}

	public boolean getPermissionSetChildLoopDivisions() {
		return LoopPermission.contains(
			themeDisplay, _loopDivision, "setChildLoopDivisions");
	}

	public boolean getPermissionSetGitHubRepositories() {
		return LoopPermission.contains(
			themeDisplay, "divisions", "setGithubRepositories");
	}

	@SuppressWarnings("unused")
	public boolean getPermissionSetType() {
		return LoopPermission.contains(themeDisplay, _loopDivision, "setType");
	}

	public String getPreferredName() throws Exception {
		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
			_loopDivision.getExtraData());

		return extraDataJSONObject.getString("preferredName");
	}

	public JSONObject getProfileImageDataJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (String imageType :
				LoopWebConfigurationValues.IMAGE_PROFILE_TYPES) {

			LoopImageURL loopImageURL = new LoopDivisionImageURL(
				_loopDivision, "profileImagePayload", imageType);

			jsonObject.put("imageURL_" + imageType, loopImageURL.getImageURL());
		}

		return jsonObject;
	}

	public String getProfileImageURL() {
		LoopImageURL loopImageURL = new LoopDivisionImageURL(
			_loopDivision, "profileImagePayload", "web");

		return loopImageURL.getImageURL();
	}

	public int getSubtype() {
		return _loopDivision.getSubtype();
	}

	public int getType() {
		return _loopDivision.getType();
	}

	@SuppressWarnings("unused")
	public String getTypeLabel() {
		return LoopDivisionConstants.getTypeLabel(_loopDivision.getType());
	}

	public boolean isFollowing() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopStreamEntryUtil.isFollowing(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopDivisionId());
	}

	public boolean isNotifying() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopUserNotificationSubscriptionUtil.isNotifying(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopDivisionId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP);
	}

	public boolean isNotifyingEmail() throws Exception {
		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		return LoopUserNotificationSubscriptionUtil.isNotifying(
			curLoopPerson.getLoopPersonId(), getEntityClassNameId(),
			getLoopDivisionId(),
			LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL);
	}

	private List<LoopPersonComposite> _leadLoopPersonComposites;
	private LoopDivision _loopDivision;
	private List<LoopPersonComposite> _memberLoopPersonComposites;
	private Organization _organization;

}