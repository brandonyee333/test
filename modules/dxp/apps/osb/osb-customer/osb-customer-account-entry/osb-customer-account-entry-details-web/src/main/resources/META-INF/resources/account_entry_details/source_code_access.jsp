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

<liferay-ui:error exception="<%= CollaboratorEmailAddressException.class %>" message="please-provide-an-email-address" />
<liferay-ui:error exception="<%= CollaboratorFullNameException.class %>" message="please-provide-a-full-name" />
<liferay-ui:error exception="<%= CollaboratorGitHubUserNameException.class %>" message="please-provide-a-github-username" />
<liferay-ui:error exception="<%= DuplicateCollaboratorException.class %>" message="please-provide-a-unique-github-username" />

<liferay-ui:success key="pendingInvitationLimit" message="oh-no-you-can't-be-granted-access-yet" />
<liferay-ui:success key="pendingProjectStatus" message="this-request-is-pending-project-status" />

<div class="card source-code-access" id="<portlet:namespace />sourceCodeAccess"></div>

<aui:script>
	AccountDetails.render(
		AccountDetails.SourceCodeAccess,
		{
			addCollaboratorURL: '<%= accountEntryViewDisplayContext.getCollaboratorAddURL() %>',
			collaborators: <%= accountEntryViewDisplayContext.getCollaboratorsJSONArray() %>
		},
		document.getElementById('<portlet:namespace />sourceCodeAccess')
	);
</aui:script>