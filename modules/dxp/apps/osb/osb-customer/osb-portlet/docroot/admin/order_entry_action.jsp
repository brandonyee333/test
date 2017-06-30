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

OrderEntry orderEntry = (OrderEntry)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

String redirect = ParamUtil.getString(request, "redirect", portletURL.toString());
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_order_entry.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="orderEntryId" value="<%= String.valueOf(orderEntry.getOrderEntryId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		url="<%= editURL %>"
	/>

	<portlet:actionURL name="deleteOrderEntry" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="tabs1" value="sales" />
		<portlet:param name="tabs2" value="orders" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="orderEntryId" value="<%= String.valueOf(orderEntry.getOrderEntryId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>