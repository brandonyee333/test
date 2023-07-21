<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="new-component" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayComponent}" model="<%= TestrayComponent.class %>" />

<portlet:actionURL var="addTestrayComponentURL">
	<portlet:param name="controller" value="components" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addTestrayComponentURL}" method="post">
	<portlet:renderURL var="viewTestrayComponentsURL">
		<portlet:param name="controller" value="components" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayComponent.testrayProjectId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayComponentsURL}" />

	<aui:input name="testrayProjectId" type="hidden" />

	<aui:input autoFocus="${true}" name="name" />

	<aui:select label="team" name="testrayTeamId" showEmptyOption="${true}">
		<c:forEach items="${testrayTeams}" var="testrayTeam">
			<aui:option label="${testrayTeam.name}" selected="${testrayComponent.testrayTeamId == testrayTeam.testrayTeamId}" value="${testrayTeam.testrayTeamId}" />
		</c:forEach>
	</aui:select>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="${viewTestrayComponentsURL}" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>