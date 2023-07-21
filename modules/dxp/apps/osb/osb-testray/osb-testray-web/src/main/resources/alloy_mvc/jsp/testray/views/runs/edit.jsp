<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${testrayRun.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayRun}" model="<%= TestrayRun.class %>" />

<portlet:actionURL var="updateTestrayRunURL">
	<portlet:param name="controller" value="runs" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateTestrayRunURL}" method="post">
		<portlet:renderURL var="viewTestrayRunsURL">
			<portlet:param name="controller" value="runs" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayBuildId" value="${testrayRun.testrayBuildId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "runs", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : viewTestrayRunsURL}" />

		<aui:input name="id" type="hidden" value="${testrayRun.testrayRunId}" />

		<aui:input name="testrayBuildId" type="hidden" value="${testrayRun.testrayBuildId}" />

		<aui:input autoFocus="${true}" name="name" />

		<aui:input name="description" />

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayRunsURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>