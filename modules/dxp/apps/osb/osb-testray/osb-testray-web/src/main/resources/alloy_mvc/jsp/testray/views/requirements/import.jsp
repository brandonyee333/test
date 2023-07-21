<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="import-jira-issues" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="importTestrayRequirementIssuesURL">
	<portlet:param name="controller" value="requirements" />
	<portlet:param name="action" value="importIssues" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<testray:table-toolbar
		title="import-jira-issues"
	/>

	<aui:form action="${importTestrayRequirementIssuesURL}" method="post">
		<aui:input name="testrayProjectId" type="hidden" value="${param.testrayProjectId}" />

		<aui:input autoFocus="${true}" helpMessage="automatically-sync-the-data-for-these-comma-delimited-issue-keys-from-jira" name="issueKeys" type="textarea" />

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>