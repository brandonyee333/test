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

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${testrayRoutine.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayRoutine}" model="<%= TestrayRoutine.class %>" />

<portlet:actionURL var="updateTestrayRoutineURL">
	<portlet:param name="controller" value="routines" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateTestrayRoutineURL}" method="post">
		<portlet:renderURL var="viewTestrayRoutinesURL">
			<portlet:param name="controller" value="routines" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayProjectId" value="${testrayRoutine.testrayProjectId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : viewTestrayRoutinesURL}" />
		<aui:input name="id" type="hidden" value="${testrayRoutine.testrayRoutineId}" />
		<aui:input name="testrayProjectId" type="hidden" />

		<aui:input autoFocus="${true}" name="name" />

		<aui:input checked="${testrayRoutine.autoanalyze}" helpMessage="allow-testray-to-automatically-populate-assignee-comments-and-issues-when-an-error-matches-the-previous-result" name="autoanalyze" type="checkbox" />

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayRoutinesURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>