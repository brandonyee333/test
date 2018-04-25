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
String tabs2 = ParamUtil.getString(request, "tabs2");

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:tabs
	names="teams,support-regions,labor-hours,support-worker-statuses"
	param="tabs2"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("labor-hours") %>'>
		<%@ include file="/admin/support_labors.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("support-regions") %>'>
		<%@ include file="/admin/support_regions.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("support-worker-statuses") %>'>
		<%@ include file="/admin/support_worker_statuses.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/admin/support_teams.jspf" %>
	</c:otherwise>
</c:choose>