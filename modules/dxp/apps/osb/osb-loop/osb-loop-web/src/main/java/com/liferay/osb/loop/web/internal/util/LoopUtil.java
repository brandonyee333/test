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
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.model.LoopAuditEntry;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.service.LoopAuditEntryLocalServiceUtil;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.composite.LoopJobTitleComposite;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopJobTitleConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonConstants;
import com.liferay.osb.loop.web.internal.controller.LoopAlloyControllerImpl;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Sherry Yang
 */
public class LoopUtil {

	public static void addUserGroupRole(
			long loopPersonId, LoopDivision loopDivision, String roleName)
		throws Exception {

		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		Role role = RoleLocalServiceUtil.getRole(
			loopDivision.getCompanyId(), roleName);

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			new long[] {loopPerson.getPersonUserId()},
			LoopDivisionUtil.getGroupId(loopDivision), role.getRoleId());
	}

	public static void addUserRole(long userId, long companyId, String roleName)
		throws Exception {

		if (Validator.isNull(roleName)) {
			return;
		}

		Role role = RoleLocalServiceUtil.getRole(companyId, roleName);

		RoleLocalServiceUtil.addUserRole(userId, role);
	}

	public static void deleteUserGroupRole(
			long userId, long groupId, long companyId, String roleName)
		throws Exception {

		if (Validator.isNull(roleName)) {
			return;
		}

		Role role = RoleLocalServiceUtil.getRole(companyId, roleName);

		UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(
			userId, groupId, new long[] {role.getRoleId()});
	}

	public static void deleteUserGroupRole(
			long loopPersonId, LoopDivision loopDivision, String roleName)
		throws Exception {

		LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
			loopPersonId);

		deleteUserGroupRole(
			loopPerson.getPersonUserId(),
			LoopDivisionUtil.getGroupId(loopDivision),
			loopDivision.getCompanyId(), roleName);
	}

	public static void deleteUserGroupRoles(long groupId, long roleId) {
		List<UserGroupRole> userGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(
				groupId, roleId);

		for (UserGroupRole userGroupRole : userGroupRoles) {
			UserGroupRoleLocalServiceUtil.deleteUserGroupRole(userGroupRole);
		}
	}

	public static String escapeName(String name) {
		return StringUtil.replace(name, _UNESCAPED_CHARS, _ESCAPED_CHARS);
	}

	public static void executeController(
			PageContext pageContext, HttpServletRequest request)
		throws Exception {

		String baseControllerClassName =
			LoopAlloyControllerImpl.class.getName();

		String packageName = baseControllerClassName.substring(
			0, baseControllerClassName.lastIndexOf(StringPool.PERIOD));

		String controllerName = (String)request.getAttribute("controllerName");

		String[] controllerNames = StringUtil.split(
			controllerName, StringPool.UNDERLINE);

		for (int i = 0; i < controllerNames.length; i++) {
			controllerNames[i] = StringUtil.upperCaseFirstLetter(
				controllerNames[i]);
		}

		StringBundler sb = new StringBundler(4);

		sb.append(packageName);
		sb.append(StringPool.PERIOD);
		sb.append(StringUtil.merge(controllerNames, StringPool.BLANK));
		sb.append("Controller");

		executeController(pageContext, sb.toString());
	}

	public static void executeController(
			PageContext pageContext, String controllerClassName)
		throws Exception {

		Class<?> clazz = Class.forName(controllerClassName);

		AlloyController alloyController = (AlloyController)clazz.newInstance();

		alloyController.setPageContext(pageContext);

		alloyController.afterPropertiesSet();

		alloyController.execute();
	}

	public static Map<String, Serializable> getAttributes(Object... attributes)
		throws Exception {

		Map<String, Serializable> attributesMap = new HashMap<>();

		if ((attributes.length == 0) || ((attributes.length % 2) != 0)) {
			throw new Exception("Arguments length is not an even number");
		}

		for (int i = 0; i < attributes.length; i += 2) {
			String name = String.valueOf(attributes[i]);

			Serializable value = (Serializable)attributes[i + 1];

			attributesMap.put(name, value);
		}

		return attributesMap;
	}

	public static JSONObject getCompositeJSONObject(
			ThemeDisplay themeDisplay, long classNameId, long classPK,
			String name)
		throws Exception {

		HttpServletRequest request = themeDisplay.getRequest();

		int apiVersion = request.getIntHeader("api-version");

		if (!hasLoopBaseModel(themeDisplay, classNameId, classPK)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (apiVersion < 5) {
				jsonObject.put("classNameId", classNameId);
				jsonObject.put("classPK", classPK);
			}

			jsonObject.put("entityClassNameId", classNameId);
			jsonObject.put("entityClassPK", classPK);
			jsonObject.put("name", name);
			jsonObject.put("type", LoopConstants.TYPE_REMOVED);

			return jsonObject;
		}
		else if (classNameId == PortalUtil.getClassNameId(
					LoopAuditEntry.class)) {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			LoopAuditEntry loopAuditEntry =
				LoopAuditEntryLocalServiceUtil.getLoopAuditEntry(classPK);

			if (apiVersion < 5) {
				jsonObject.put("classNameId", loopAuditEntry.getClassNameId());
				jsonObject.put("classPK", loopAuditEntry.getClassPK());
			}

			jsonObject.put(
				"entityClassNameId", loopAuditEntry.getClassNameId());
			jsonObject.put("entityClassPK", loopAuditEntry.getClassPK());

			Date modifiedDate = loopAuditEntry.getModifiedDate();

			jsonObject.put("modifiedTime", modifiedDate.getTime());

			jsonObject.put("name", loopAuditEntry.getName());
			jsonObject.put("type", LoopConstants.TYPE_REMOVED);

			return jsonObject;
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopDivision.class)) {
			LoopDivisionComposite loopDivisionComposite =
				new LoopDivisionComposite(classPK, themeDisplay);

			return loopDivisionComposite.getJSONObject();
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopJobTitle.class)) {
			LoopJobTitleComposite loopJobTitleComposite =
				new LoopJobTitleComposite(classPK, themeDisplay);

			return loopJobTitleComposite.getJSONObject();
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
				classPK, themeDisplay);

			return loopPersonComposite.getJSONObject();
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopTopic.class)) {
			LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
				classPK, themeDisplay);

			return loopTopicComposite.getJSONObject();
		}

		return JSONFactoryUtil.createJSONObject();
	}

	public static long getContactPhoneListTypeId(String name) {
		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			ListTypeConstants.CONTACT_PHONE);

		for (ListType listType : listTypes) {
			if (Objects.equals(listType.getName(), name)) {
				return listType.getListTypeId();
			}
		}

		return -1;
	}

	public static String getController(BaseModel<?> baseModel) {
		if (baseModel instanceof LoopDivision) {
			return "divisions";
		}
		else if (baseModel instanceof LoopPerson) {
			return "people";
		}
		else if (baseModel instanceof LoopTopic) {
			return "topics";
		}
		else {
			return "home";
		}
	}

	public static String getDisplayURL(
			long companyId, long classNameId, long classPK)
		throws Exception {

		long assetEntrySetId = 0;
		boolean assetEntrySetPage = false;
		String url = StringPool.BLANK;
		String token = StringPool.BLANK;
		String name = StringPool.BLANK;

		if (classNameId == PortalUtil.getClassNameId(AssetEntrySet.class)) {
			assetEntrySetId = classPK;

			AssetEntrySet assetEntrySet =
				AssetEntrySetLocalServiceUtil.getAssetEntrySet(assetEntrySetId);

			if (assetEntrySet.getType() ==
					LoopAssetEntrySetConstants.TYPE_PAGE) {

				classNameId = assetEntrySet.getClassNameId();
				classPK = assetEntrySet.getClassPK();

				assetEntrySetPage = true;
			}
			else {
				url = LoopConstants.URL_LOOP_ASSET_ENTRY_SET_POST;

				name = String.valueOf(classPK);
			}
		}

		if (classNameId == PortalUtil.getClassNameId(LoopDivision.class)) {
			LoopDivision loopDivision =
				LoopDivisionLocalServiceUtil.getLoopDivision(classPK);

			if (loopDivision.getType() ==
					LoopConstants.TYPE_LOOP_DIVISION_DEPARTMENT) {

				url = LoopConstants.URL_LOOP_DIVISION_DEPARTMENT;
			}
			else if (loopDivision.getType() ==
						LoopConstants.TYPE_LOOP_DIVISION_LOCATION) {

				url = LoopConstants.URL_LOOP_DIVISION_LOCATION;
			}
			else if (loopDivision.getType() ==
						LoopConstants.TYPE_LOOP_DIVISION_TEAM) {

				url = LoopConstants.URL_LOOP_DIVISION_TEAM;
			}
			else {
				url = LoopConstants.URL_LOOP_DIVISION_ROOT;
			}

			token = LoopConstants.URL_TOKEN_LOOP_DIVISION_NAME;

			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					loopDivision.getOrganizationId());

			name = organization.getName();
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopJobTitle.class)) {
			url = LoopConstants.URL_LOOP_JOB_TITLES;
			token = LoopConstants.URL_TOKEN_LOOP_JOB_TITLES_NAME;

			LoopJobTitle loopJobTitle =
				LoopJobTitleLocalServiceUtil.getLoopJobTitle(classPK);

			name = loopJobTitle.getName();
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			url = LoopConstants.URL_LOOP_PEOPLE;
			token = LoopConstants.URL_TOKEN_LOOP_PEOPLE_NAME;

			LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
				classPK);

			User user = UserLocalServiceUtil.getUser(
				loopPerson.getPersonUserId());

			name = user.getScreenName();
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopTopic.class)) {
			url = LoopConstants.URL_LOOP_TOPIC;
			token = LoopConstants.URL_TOKEN_LOOP_TOPIC_NAME;

			LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				classPK);

			name = loopTopic.getName();
		}

		if (assetEntrySetPage) {
			return getParticipantURL(
				companyId, url, token, URLCodec.encodeURL(escapeName(name)),
				LoopConstants.URL_LOOP_ASSET_ENTRY_SET_PAGE,
				String.valueOf(assetEntrySetId));
		}

		return getParticipantURL(
			companyId, url, token, URLCodec.encodeURL(escapeName(name)));
	}

	public static String getParticipantURL(long companyId, String... params)
		throws Exception {

		StringBundler sb = new StringBundler(2 + params.length);

		sb.append(_LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);
		sb.append(getLoopLayoutFriendlyURL(companyId));

		for (String param : params) {
			sb.append(param);
		}

		return sb.toString();
	}

	public static String getPortalURL(
			HttpServletRequest request, long companyId)
		throws Exception {

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		int serverPort = Http.HTTP_PORT;
		boolean secure = false;

		if (PortalUtil.isSecure(request)) {
			serverPort = Http.HTTPS_PORT;
			secure = true;
		}

		return PortalUtil.getPortalURL(
			company.getVirtualHostname(), serverPort, secure);
	}

	public static boolean hasLoopBaseModel(
			ThemeDisplay themeDisplay, long classNameId, long classPK)
		throws Exception {

		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(
			PortalUtil.getClassName(classNameId));

		String primaryKey = StringPool.BLANK;

		if (classNameId == PortalUtil.getClassNameId(LoopAuditEntry.class)) {
			primaryKey = "loopAuditEntryId";
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopDivision.class)) {
			primaryKey = "loopDivisionId";
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopJobTitle.class)) {
			primaryKey = "loopJobTitleId";
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			primaryKey = "loopPersonId";
		}
		else if (classNameId == PortalUtil.getClassNameId(LoopTopic.class)) {
			primaryKey = "loopTopicId";
		}

		long loopBaseModelsCount = alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {
				"companyId", themeDisplay.getCompanyId(), primaryKey, classPK
			});

		if (loopBaseModelsCount > 0) {
			return true;
		}

		return false;
	}

	public static boolean isSharedWithUser(
			AssetEntrySet assetEntrySet, long userId)
		throws Exception {

		if (!assetEntrySet.isPrivateAssetEntrySet()) {
			return true;
		}

		List<AssetSharingEntry> assetSharingEntries =
			AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(
				PortalUtil.getClassNameId(AssetEntrySet.class),
				LoopAssetEntrySetUtil.getRootAssetEntrySetId(assetEntrySet));

		LoopPerson loopPerson =
			LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(userId);

		for (AssetSharingEntry assetSharingEntry : assetSharingEntries) {
			if (AssetEntrySetParticipantInfoUtil.isMember(
					PortalUtil.getClassNameId(LoopPerson.class),
					loopPerson.getLoopPersonId(),
					assetSharingEntry.getSharedToClassNameId(),
					assetSharingEntry.getSharedToClassPK())) {

				return true;
			}
		}

		return false;
	}

	public static void pollIndexState(
			long companyId, String className, long entryClassPK, int length)
		throws Exception {

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.ENTRY_CLASS_PK, entryClassPK);

		pollIndexState(companyId, className, attributes, length);
	}

	public static void pollIndexState(
			long companyId, String className,
			Map<String, Serializable> attributes, int length)
		throws Exception {

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(className);

		SearchContext searchContext = new SearchContext();

		searchContext.setAttributes(attributes);
		searchContext.setCompanyId(companyId);

		for (int i = 0; i < LoopWebConfigurationValues.POLL_INDEX_MAX_COUNT;
			 i++) {

			Hits hits = indexer.search(searchContext);

			if (hits.getLength() == length) {
				return;
			}

			Thread.sleep(LoopWebConfigurationValues.POLL_INDEX_WAIT_INTERVAL);
		}
	}

	public static void publishRabbitMQ(
			String routingKey, String id, JSONObject compositeJSONObject,
			JSONObject oldCompositeJSONObject)
		throws Exception {

		if (!LoopWebConfigurationValues.RABBIT_MQ_PUBLISH_ENABLED) {
			return;
		}

		if ((routingKey == null) || Validator.isNull(routingKey)) {
			return;
		}

		if ((compositeJSONObject == null) ||
			(compositeJSONObject.length() <= 0)) {

			return;
		}

		JSONObject rabbitMQPayloadJSONObject =
			JSONFactoryUtil.createJSONObject();

		rabbitMQPayloadJSONObject.put("data", compositeJSONObject);

		if (Validator.isNotNull(id)) {
			rabbitMQPayloadJSONObject.put("id", id);
		}

		if ((oldCompositeJSONObject != null) &&
			(oldCompositeJSONObject.length() > 0)) {

			rabbitMQPayloadJSONObject.put("oldData", oldCompositeJSONObject);
		}

		LoopAMQPUtil.publish(routingKey, rabbitMQPayloadJSONObject.toString());
	}

	public static boolean sendSyncMessage(
			JSONObject jsonObject, String entityName)
		throws Exception {

		String controllerDestinationName = StringPool.BLANK;

		if (entityName.equals("branch") || entityName.equals("location")) {
			controllerDestinationName =
				LoopDivisionConstants.CONTROLLER_DESTINATION_NAME;
		}
		else if (entityName.equals("job_title")) {
			controllerDestinationName =
				LoopJobTitleConstants.CONTROLLER_DESTINATION_NAME;
		}
		else if (entityName.equals("worker")) {
			controllerDestinationName =
				LoopPersonConstants.CONTROLLER_DESTINATION_NAME;
		}
		else {
			throw new Exception(
				"The entity name cannot be found for " + entityName);
		}

		if (!MessageBusUtil.hasMessageListener(controllerDestinationName)) {
			return false;
		}

		MessageBusUtil.sendMessage(controllerDestinationName, jsonObject);

		return true;
	}

	public static void setUserGroupRoles(
			long[] userIds, long groupId, long companyId, String roleName)
		throws Exception {

		if (Validator.isNull(roleName)) {
			return;
		}

		Role role = RoleLocalServiceUtil.getRole(companyId, roleName);

		deleteUserGroupRoles(groupId, role.getRoleId());

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			userIds, groupId, role.getRoleId());
	}

	public static void setUserGroupRoles(
			long[] loopPersonIds, LoopDivision loopDivision, String roleName)
		throws Exception {

		long[] personUserIds = new long[loopPersonIds.length];

		for (int i = 0; i < loopPersonIds.length; i++) {
			LoopPerson loopPerson = LoopPersonLocalServiceUtil.getLoopPerson(
				loopPersonIds[i]);

			personUserIds[i] = loopPerson.getPersonUserId();
		}

		setUserGroupRoles(
			personUserIds, LoopDivisionUtil.getGroupId(loopDivision),
			loopDivision.getCompanyId(), roleName);
	}

	public static String unescapeName(String name) {
		return StringUtil.replace(name, _ESCAPED_CHARS, _UNESCAPED_CHARS);
	}

	protected static String getLoopLayoutFriendlyURL(long companyId)
		throws Exception {

		Layout loopLayout = LayoutLocalServiceUtil.getLayout(
			getLoopPlid(companyId));

		Group group = loopLayout.getGroup();

		return group.getFriendlyURL() + loopLayout.getFriendlyURL();
	}

	protected static long getLoopPlid(long companyId) throws Exception {
		long loopPlid = LayoutConstants.DEFAULT_PLID;

		List<Group> groups = GroupLocalServiceUtil.getGroups(
			companyId, GroupConstants.ANY_PARENT_GROUP_ID, true);

		for (Group group : groups) {
			loopPlid = PortalUtil.getPlidFromPortletId(
				group.getGroupId(), LoopPortletKeys.LOOP);

			if (loopPlid != LayoutConstants.DEFAULT_PLID) {
				break;
			}
		}

		if (loopPlid == LayoutConstants.DEFAULT_PLID) {
			throw new NoSuchLayoutException();
		}

		return loopPlid;
	}

	private static final String[] _ESCAPED_CHARS = {"<BACKSLASH>", "<SLASH>"};

	private static final String _LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING =
		PropsUtil.get(PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);

	private static final String[] _UNESCAPED_CHARS = {
		StringPool.BACK_SLASH, StringPool.SLASH
	};

}