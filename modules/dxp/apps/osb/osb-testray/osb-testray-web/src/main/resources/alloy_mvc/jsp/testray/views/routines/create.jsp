<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="new-routine" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayRoutine}" model="<%= TestrayRoutine.class %>" />

<portlet:actionURL var="addTestrayRoutineURL">
	<portlet:param name="controller" value="routines" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${addTestrayRoutineURL}" method="post">
		<portlet:renderURL var="viewTestrayRoutinesURL">
			<portlet:param name="controller" value="routines" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${viewTestrayRoutinesURL}" />

		<aui:input name="testrayProjectId" type="hidden" />

		<aui:input autoFocus="${true}" name="name" required="${true}" />

		<aui:input checked="${testrayRoutine.autoanalyze}" helpMessage="allow-testray-to-automatically-populate-assignee-comments-and-issues-when-an-error-matches-the-previous-result" name="autoanalyze" type="checkbox" />

		<aui:button-row>
			<aui:button type="submit" value="add" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayRoutinesURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>