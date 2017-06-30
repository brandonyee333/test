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
String redirect = ParamUtil.getString(request, "redirect");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TicketLink ticketLink = (TicketLink)row.getObject();
%>

<c:if test="<%= OSBTicketLinkPermission.contains(permissionChecker, ticketLink, OSBActionKeys.DELETE) %>">
	<portlet:actionURL name="deleteTicketLink" var="deleteURL">
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="ticketLinkId" value="<%= String.valueOf(ticketLink.getTicketLinkId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete label="<%= true %>" url="<%= deleteURL %>" />
</c:if>