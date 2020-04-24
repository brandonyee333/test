<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/journal" prefix="liferay-journal" %><%@
taglib uri="http://liferay.com/tld/loop" prefix="loop" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet" %><%@
page import="com.liferay.osb.loop.constants.LoopConstants" %><%@
page import="com.liferay.osb.loop.model.LoopDivision" %><%@
page import="com.liferay.osb.loop.model.LoopJobTitle" %><%@
page import="com.liferay.osb.loop.model.LoopPerson" %><%@
page import="com.liferay.osb.loop.model.LoopTopic" %><%@
page import="com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues" %><%@
page import="com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants" %><%@
page import="com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants" %><%@
page import="com.liferay.osb.loop.web.internal.constants.LoopPersonConstants" %><%@
page import="com.liferay.osb.loop.web.internal.constants.LoopStreamConstants" %><%@
page import="com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants" %><%@
page import="com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants" %><%@
page import="com.liferay.osb.loop.web.internal.util.LoopUtil" %><%@
page import="com.liferay.petra.string.StringBundler" %><%@
page import="com.liferay.portal.kernel.bean.ConstantsBeanFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.model.Portlet" %><%@
page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.Http" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

<%@ page import="java.util.Arrays" %><%@
page import="java.util.List" %>

<liferay-theme:defineObjects />

<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopAssetEntrySetConstants.class) %>" var="LoopAssetEntrySetConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopConstants.class) %>" var="LoopConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopDivisionConstants.class) %>" var="LoopDivisionConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopPersonConstants.class) %>" var="LoopPersonConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopStreamConstants.class) %>" var="LoopStreamConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopStreamEntryConstants.class) %>" var="LoopStreamEntryConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopUserNotificationConstants.class) %>" var="LoopUserNotificationConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(LoopWebConfigurationValues.class) %>" var="LoopWebConfigurationValues" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(PropsKeys.class) %>" var="PropsKeys" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(WorkflowConstants.class) %>" var="WorkflowConstants" />

<c:set value="<%= new PropsUtil() %>" var="PropsUtil" />
<c:set value="<%= Http.HTTP_PORT %>" var="httpPort" />
<c:set value="<%= Http.HTTPS_PORT %>" var="httpsPort" />

<%
Portlet loopPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());

long timestamp = loopPortlet.getTimestamp();
%>

<aui:script position="inline">
	Liferay.namespace('Loop').staticResourceURLParams = '<%= PortalUtil.getStaticResourceURL(request, "", timestamp) %>';

	var <portlet:namespace />stripPorts = function(url) {
		var httpsString = ':${httpsPort}/';
		var httpString = ':${httpPort}/';

		if (url.indexOf(httpString) > -1) {
			url = url.replace(httpString, '/');
		}

		if (url.indexOf(httpsString) > -1) {
			url = url.replace(httpsString, '/');
		}

		return url;
	};

	window.LoopConstants = {
		autocompleteMaxResults: ${LoopWebConfigurationValues.LOOP_AUTOCOMPLETE_MAX_RESULTS},
		childAssetEntrySetsLimit: ${LoopConstants.ASSET_ENTRY_SET_CHILD_ASSET_ENTRY_SETS_LIMIT},
		classNameIds: {
			divisions: <%= PortalUtil.getClassNameId(LoopDivision.class) %>,
			jobTitles: <%= PortalUtil.getClassNameId(LoopJobTitle.class) %>,
			people: <%= PortalUtil.getClassNameId(LoopPerson.class) %>,
			posts: <%= PortalUtil.getClassNameId(AssetEntrySet.class) %>,
			topics: <%= PortalUtil.getClassNameId(LoopTopic.class) %>
		},
		currentPerson: {
			coverImageProfileURL: '${currentLoopPersonComposite.coverImageProfileURL}',
			displayURL: '${currentLoopPersonComposite.displayURL}',
			entityClassNameId: ${currentLoopPersonComposite.entityClassNameId},
			entityClassPK: ${currentLoopPersonComposite.entityClassPK},
			name: '${loop:escapeJS(currentLoopPersonComposite.name)}',
			permissionAddAnnouncement: ${currentLoopPersonComposite.permissionAddAnnouncement},
			permissionAdmin: ${currentLoopPersonComposite.permissionAdmin},
			permissionCurator: ${currentLoopPersonComposite.getPermissionUpdateFeaturedContent()},
			profileImageURL: '${currentLoopPersonComposite.profileImageURL}',
			type: ${currentLoopPersonComposite.type}
		},
		departmentSubtypes: {
			functional: ${LoopDivisionConstants.SUBTYPE_DEPARTMENT_FUNCTIONAL},
			none: ${LoopDivisionConstants.SUBTYPE_NONE},
			regional: ${LoopDivisionConstants.SUBTYPE_DEPARTMENT_REGIONAL}
		},
		employmentTypes: [
			{
				label: '<liferay-ui:message key="${LoopPersonConstants.EMPLOYMENT_LABEL_CONTRACTOR}" />',
				value: '${LoopPersonConstants.EMPLOYMENT_TYPE_CONTRACTOR}'
			},
			{
				label: '<liferay-ui:message key="${LoopPersonConstants.EMPLOYMENT_LABEL_FULL_TIME}" />',
				value: '${LoopPersonConstants.EMPLOYMENT_TYPE_FULL_TIME}'
			},
			{
				label: '<liferay-ui:message key="${LoopPersonConstants.EMPLOYMENT_LABEL_INTERN}" />',
				value: '${LoopPersonConstants.EMPLOYMENT_TYPE_INTERN}'
			},
			{
				label: '<liferay-ui:message key="${LoopPersonConstants.EMPLOYMENT_LABEL_PART_TIME}" />',
				value: '${LoopPersonConstants.EMPLOYMENT_TYPE_PART_TIME}'
			},
			{
				label: '<liferay-ui:message key="${LoopPersonConstants.EMPLOYMENT_LABEL_TEMP_TO_HIRE}" />',
				value: '${LoopPersonConstants.EMPLOYMENT_TYPE_TEMP_TO_HIRE}'
			}
		],
		feedbackName: '${LoopWebConfigurationValues.LOOP_TOPIC_FEEDBACK_NAME}',
		feedItemsPerPage: Number('${LoopWebConfigurationValues.LOOP_FEED_PAGE_DEFAULT_DELTA}'),
		followingType: {
			full: ${LoopStreamEntryConstants.TYPE_FOLLOWING_FULL},
			limited: ${LoopStreamEntryConstants.TYPE_FOLLOWING_LIMITED},
			unfollowing: ${LoopStreamEntryConstants.TYPE_UNFOLLOWING}
		},
		likedParticipantsLimit: ${LoopConstants.ASSET_ENTRY_SET_LIKED_PARTICIPANTS_LIMIT},
		locale: '${locale}',
		locationSubtypes: {
			office: ${LoopDivisionConstants.SUBTYPE_LOCATION_OFFICE},
			region: ${LoopDivisionConstants.SUBTYPE_LOCATION_REGION},
			remote: ${LoopDivisionConstants.SUBTYPE_LOCATION_REMOTE}
		},
		loopStreamAliasIds: {
			announcements: ${LoopStreamConstants.LOOP_STREAM_ALIAS_ID_ANNOUNCEMENTS},
			announcementsSticky: ${LoopStreamConstants.LOOP_STREAM_ALIAS_ID_ANNOUNCEMENTS_STICKY},
			bookmarks: ${LoopStreamConstants.LOOP_STREAM_ALIAS_ID_BOOKMARKS},
			following: ${LoopStreamConstants.LOOP_STREAM_ALIAS_ID_FOLLOWING},
			privatePosts: ${LoopStreamConstants.LOOP_STREAM_ALIAS_ID_PRIVATE}
		},
		markdownToken: '${LoopConstants.ENCODE_TOKEN}',
		namespace: '${renderResponse.namespace}',
		notificationTypes: {
			email: ${LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL},
			inApp: ${LoopUserNotificationConstants.DELIVERY_TYPE_IN_APP},
			none: ${LoopUserNotificationConstants.DELIVERY_TYPE_NONE}
		},
		payloadTypes: {
			image: '${LoopConstants.PAYLOAD_TYPE_IMAGE}',
			link: '${LoopConstants.PAYLOAD_TYPE_LINK}',
			text: '${LoopConstants.PAYLOAD_TYPE_TEXT}'
		},
		phoneTypes: {
			business: <%= LoopUtil.getContactPhoneListTypeId("business") %>,
			businessFax: <%= LoopUtil.getContactPhoneListTypeId("business-fax") %>,
			mobile: <%= LoopUtil.getContactPhoneListTypeId("mobile-phone") %>,
			other: <%= LoopUtil.getContactPhoneListTypeId("other") %>,
			pager: <%= LoopUtil.getContactPhoneListTypeId("pager") %>,
			personal: <%= LoopUtil.getContactPhoneListTypeId("personal") %>,
			personalFax: <%= LoopUtil.getContactPhoneListTypeId("personal-fax") %>,
			tty: <%= LoopUtil.getContactPhoneListTypeId("tty") %>
		},
		portletId: '${portletDisplay.id}',
		postTypes: {
			announcement: ${LoopAssetEntrySetConstants.TYPE_ANNOUNCEMENT},
			post: ${LoopAssetEntrySetConstants.TYPE_POST}
		},
		queryTypes: {
			'@': ${LoopConstants.QUERY_TYPE_AT},
			'#': ${LoopConstants.QUERY_TYPE_HASHTAG}
		},
		socialUrls: {
			facebook: '${LoopWebConfigurationValues.EXTERNAL_FACEBOOK_URL}',
			github: '${LoopWebConfigurationValues.EXTERNAL_GITHUB_URL}',
			linkedin: '${LoopWebConfigurationValues.EXTERNAL_LINKEDIN_URL}',
			twitter: '${LoopWebConfigurationValues.EXTERNAL_TWITTER_URL}'
		},
		status: {
			any: ${WorkflowConstants.STATUS_ANY},
			approved: ${WorkflowConstants.STATUS_APPROVED},
			inactive: ${WorkflowConstants.STATUS_INACTIVE}
		},
		types: {
			department: ${LoopConstants.TYPE_LOOP_DIVISION_DEPARTMENT},
			jobTitle: ${LoopConstants.TYPE_LOOP_JOB_TITLE},
			location: ${LoopConstants.TYPE_LOOP_DIVISION_LOCATION},
			person: ${LoopConstants.TYPE_LOOP_PERSON},
			removed: ${LoopConstants.TYPE_REMOVED},
			root: ${LoopConstants.TYPE_LOOP_DIVISION_ROOT},
			team: ${LoopConstants.TYPE_LOOP_DIVISION_TEAM},
			topic: ${LoopConstants.TYPE_LOOP_TOPIC}
		},
		uiKitEnabled: ${LoopWebConfigurationValues.LOOP_UI_KIT_PAGE_ENABLED},
		uploadSettings: {
			maxFileCount: ${LoopWebConfigurationValues.LOOP_FILE_UPLOADER_COUNT_MAX},
			maxFileSize: ${PropsUtil.get(PropsKeys.DL_FILE_MAX_SIZE)}
		},
		urls: {
			adminPage: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="home" /><portlet:param name="action" value="admin" /></portlet:renderURL>'),
			company: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="company" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			departments: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="departments" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			feed: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="feed" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			groups: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="divisions" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			help: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="home" /><portlet:param name="action" value="help" /></portlet:renderURL>'),
			home: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="home" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			jobTitles: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="job_titles" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			konami: '${LoopWebConfigurationValues.LOOP_KONAMI_REDIRECT_URL}',
			locations: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="locations" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			modalView: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="home" /><portlet:param name="action" value="modal" /></portlet:renderURL>'),
			people: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="people" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			search: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="home" /><portlet:param name="action" value="search" /></portlet:renderURL>'),
			teams: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="teams" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			topics: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="topics" /><portlet:param name="action" value="index" /></portlet:renderURL>'),
			uiKit: <portlet:namespace />stripPorts('<portlet:renderURL><portlet:param name="controller" value="home" /><portlet:param name="action" value="uiKit" /></portlet:renderURL>')
		}
	};
</aui:script>

<c:import url="/images/icons/dist/loop_icons.svg" />

<c:import url="/dist/manifest.json" var="manifestJSON" />

<%
String json = (String)pageContext.getAttribute("manifestJSON");

JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

List<String> bundleFileNames = Arrays.asList("manifest.nocsf.js", "vendors.nocsf.js", "bundle.nocsf.js", "ui-kit.nocsf.js");

for (String bundleFileName : bundleFileNames) {
	if (bundleFileName.equals("ui-kit.nocsf.js") && !LoopWebConfigurationValues.LOOP_UI_KIT_PAGE_ENABLED) {
		continue;
	}

	StringBundler sb = new StringBundler(7);

	sb.append(request.getContextPath());
	sb.append("/dist/");
	sb.append(bundleFileName);
	sb.append("?js_fast_load=0&hash=");
	sb.append(jsonObject.getString(bundleFileName));
	sb.append("&languageId=");
	sb.append(themeDisplay.getLanguageId());
%>

	<script src="<%= sb.toString() %>" type="text/javascript"></script>

<%
}
%>