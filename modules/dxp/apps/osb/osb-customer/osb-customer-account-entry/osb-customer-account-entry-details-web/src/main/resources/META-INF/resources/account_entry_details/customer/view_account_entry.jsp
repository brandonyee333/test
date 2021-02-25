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

<%@ include file="/account_entry_details/init.jsp" %>

<%
Account koroneikiAccount = accountEntryViewDisplayContext.getAccount();

String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view_account_entry");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("koroneikiAccountKey", String.valueOf(koroneikiAccount.getKey()));

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "project-details"), portletURL.toString(), null, false);

String tabNames = "overview,team-members,liferay-contacts,offerings,attachments";

if (GitHubConfigurationValues.GITHUB_FEATURE_ENABLED) {
	tabNames += ",source-code-access";
}
%>

<h1>
	<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
</h1>

<liferay-ui:tabs
	names="<%= tabNames %>"
	url="<%= portletURL.toString() %>"
/>

<liferay-ui:error exception="<%= AccountEnvironmentEnvASException.class %>" message="please-select-a-valid-application-server" />
<liferay-ui:error exception="<%= AccountEnvironmentEnvBrowserException.class %>" message="please-select-a-valid-browser" />
<liferay-ui:error exception="<%= AccountEnvironmentEnvCSException.class %>" message="please-select-a-valid-cloud-service" />
<liferay-ui:error exception="<%= AccountEnvironmentEnvDBException.class %>" message="please-select-a-valid-database" />
<liferay-ui:error exception="<%= AccountEnvironmentEnvLFRException.class %>" message="please-select-a-valid-liferay-version" />
<liferay-ui:error exception="<%= AccountEnvironmentEnvOSException.class %>" message="please-select-a-valid-operating-system" />
<liferay-ui:error exception="<%= AccountEnvironmentEnvSearchException.class %>" message="please-select-a-valid-search" />

<liferay-ui:error exception="<%= AccountEnvironmentAttachmentSizeException.class %>">

	<%
	AccountEnvironmentAttachmentSizeException aease = (AccountEnvironmentAttachmentSizeException)errorException;
	%>

	<c:choose>
		<c:when test="<%= aease.getType() == AccountEnvironmentAttachmentSizeException.EMPTY_FILE %>">
			<liferay-ui:message arguments="<%= aease.getFileName() %>" key="x-contains-no-data-and-cannot-be-uploaded" />
		</c:when>
		<c:when test="<%= aease.getType() == AccountEnvironmentAttachmentSizeException.EXCEEDS_LIMIT %>">
			<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
		</c:when>
	</c:choose>
</liferay-ui:error>

<liferay-ui:error exception="<%= AccountEnvironmentNameException.class %>" message="please-provide-a-unique-environment-name-for-the-product" />
<liferay-ui:error exception="<%= DuplicateAccountEnvironmentException.class %>" message="please-provide-a-unique-environment-name" />

<c:choose>
	<c:when test='<%= tabs1.equals("attachments") %>'>
		<liferay-util:include page="/account_entry_details/attachments.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("liferay-contacts") %>'>
		<liferay-util:include page="/account_entry_details/liferay_contacts.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("offerings") %>'>
		<liferay-util:include page="/account_entry_details/offerings.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("source-code-access") && GitHubConfigurationValues.GITHUB_FEATURE_ENABLED %>'>
		<liferay-util:include page="/account_entry_details/source_code_access.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("team-members") %>'>
		<liferay-util:include page="/account_entry_details/team_members.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/account_entry_details/customer/overview.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>