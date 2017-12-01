<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SupportTeam supportTeam = (SupportTeam)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_support_team.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="supportTeamId" value="<%= String.valueOf(supportTeam.getSupportTeamId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_support_team_workers.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="supportTeamId" value="<%= String.valueOf(supportTeam.getSupportTeamId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="assign-workers"
		url="<%= assignWorkersURL %>"
	/>

	<portlet:actionURL name="deleteSupportTeam" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="tabs1" value="support" />
		<portlet:param name="tabs2" value="teams" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="supportTeamId" value="<%= String.valueOf(supportTeam.getSupportTeamId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>