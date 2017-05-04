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
	String clientID = ParamUtil.getString(request, "client_id", "list");
	String redirectURI = ParamUtil.getString(request, "redirect_uri", "name");
	String scope = ParamUtil.getString(request, "scope", "asc");
%>

<portlet:renderURL var="authorizeURL">
	<portlet:param name="mvcPath" value="/authorize.jsp" />
	<portlet:param name="clientID" value="<%= clientID %>" />
	<portlet:param name="redirectURI" value="<%= redirectURI %>" />
	<portlet:param name="scope" value="<%= scope %>" />
</portlet:renderURL>

<p><a href="<%= authorizeURL %>">Authorize User</a></p>