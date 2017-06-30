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

PartnerEntry partnerEntry = (PartnerEntry)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/support/edit_partner_entry.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		url="<%= editURL %>"
	/>

	<portlet:renderURL var="addTicketEntryURL">
		<portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" />
		<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="add"
		message="add-ticket"
		url="<%= addTicketEntryURL %>"
	/>

	<portlet:renderURL var="viewTicketEntriesURL">
		<portlet:param name="mvcPath" value="/support/view.jsp" />
		<portlet:param name="tabs1" value="tickets" />
		<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="pages"
		message="view-tickets"
		url="<%= viewTicketEntriesURL %>"
	/>
</liferay-ui:icon-menu>