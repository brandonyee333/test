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

User user2 = (User)row.getObject();

CorpProject corpProject = (CorpProject)row.getParameter("corpProject");
%>

<liferay-ui:icon-menu>
	<portlet:actionURL name="unsetCorpProjectUsers" var="unsetCorpProjectUsersURL">
		<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
		<portlet:param name="userIds" value="<%= String.valueOf(user2.getUserId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="remove"
		url="<%= unsetCorpProjectUsersURL %>"
	/>
</liferay-ui:icon-menu>