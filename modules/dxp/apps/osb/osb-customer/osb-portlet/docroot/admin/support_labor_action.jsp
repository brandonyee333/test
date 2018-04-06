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

SupportLabor supportLabor = (SupportLabor)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_support_labor.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="supportLaborId" value="<%= String.valueOf(supportLabor.getSupportLaborId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_support_labor_workers.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="supportLaborId" value="<%= String.valueOf(supportLabor.getSupportLaborId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="assign-workers"
		url="<%= assignWorkersURL %>"
	/>

	<portlet:actionURL name="deleteSupportLabor" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="tabs1" value="support" />
		<portlet:param name="tabs2" value="labor-hours" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="supportLaborId" value="<%= String.valueOf(supportLabor.getSupportLaborId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>