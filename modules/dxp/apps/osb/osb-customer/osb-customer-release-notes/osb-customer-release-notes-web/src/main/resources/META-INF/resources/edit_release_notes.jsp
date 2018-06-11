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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long releaseNotesId = ParamUtil.getLong(request, "releaseNotesId");

ReleaseNotes releaseNotes = ReleaseNotesLocalServiceUtil.fetchReleaseNotes(releaseNotesId);

String name = BeanParamUtil.getString(releaseNotes, request, "name");
String jiraIssueKeys = BeanParamUtil.getString(releaseNotes, request, "jiraIssueKeys");

String[] restrictedJIRAIssueKeys = null;

if (releaseNotes != null) {
	restrictedJIRAIssueKeys = ReleaseNotesUtil.filterJIRAIssueKeys(releaseNotes.getJiraIssueKeysArray(), false);
}
%>

<portlet:actionURL name="updateReleaseNotes" var="updateReleaseNotes">
	<portlet:param name="mvcPath" value="/edit_release_notes.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateReleaseNotes %>" method="post" name="fm">
	<aui:input name="releaseNotesId" type="hidden" value="<%= releaseNotesId %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="based-on-issue"
	/>

	<liferay-ui:error exception="<%= DuplicateJIRAIssueKeysException.class %>" message="release-notes-with-the-entered-jira-issue-keys-already-exists" />
	<liferay-ui:error exception="<%= RequiredJIRAIssueKeysException.class %>" message="please-fill-out-the-jira-issue-keys-field" />
	<liferay-ui:error exception="<%= RequiredNameException.class %>" message="please-fill-out-the-name-field" />

	<aui:input cssClass="lfr-input-text-container" name="name" type="text" value="<%= name %>" />

	<div class="fl">
		<div class="txt-b txt-up">
			<liferay-ui:message key="jira-issue-keys" />
		</div>
	</div>

	<textarea id="<portlet:namespace />jiraIssueKeys" maxlength="<%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />jiraIssueKeys" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 300px; width: 100%;" wrap="soft"><%= HtmlUtil.escape(jiraIssueKeys) %></textarea>

	<c:if test="<%= ArrayUtil.isNotEmpty(restrictedJIRAIssueKeys) %>">
		<div>
			<div class="txt-b txt-up">
				<liferay-ui:message key="restricted-jira-issue-keys" />
			</div>
		</div>

		<div class="portlet-msg-error">
			<%= HtmlUtil.escape(StringUtil.merge(restrictedJIRAIssueKeys)) %>
		</div>
	</c:if>

	<input type="submit" value="<liferay-ui:message key="update-release-notes" />" />

	<c:if test="<%= releaseNotes != null %>">
		<portlet:resourceURL var="viewReleaseNotesURL">
			<portlet:param name="uuid" value="<%= releaseNotes.getUuid() %>" />
		</portlet:resourceURL>

		<input onClick="location.href = '<%= viewReleaseNotesURL %>';" type="button" value="<liferay-ui:message key="view-release-notes" />" />
	</c:if>
</aui:form>