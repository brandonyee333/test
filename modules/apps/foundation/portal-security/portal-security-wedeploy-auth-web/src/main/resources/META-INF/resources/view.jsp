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

<%@ include file="/META-INF/resources/init.jsp" %>

<%
String clientId = ParamUtil.getString(request, "client_id");
String redirectURI = ParamUtil.getString(request, "redirect_uri");
String scope = ParamUtil.getString(request, "scope");

long userId = user.getUserId();

boolean hasViewPermission = UserPermissionUtil.contains(
		permissionChecker, userId, ActionKeys.VIEW);
%>

<c:if test="<%= hasViewPermission %>">
	<portlet:actionURL name="/authorize/allow_user" var="allowUserURL">
		<portlet:param name="mvcActionCommand" value="/authorize/allow_user" />
		<portlet:param name="client_id" value="<%= clientId %>" />
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(userId) %>" />
		<portlet:param name="redirect_uri" value="<%= redirectURI %>" />
		<portlet:param name="scope" value="<%= scope %>" />
	</portlet:actionURL>

	<a href="<%= allowUserURL %>" >Allow</a>

	<portlet:renderURL var="denyUserURL">
		<portlet:param name="mvcActionCommandName" value="/authorize/deny_user" />
		<portlet:param name="redirect_uri" value="<%= redirectURI %>" />
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(userId) %>" />
	</portlet:renderURL>

	<a href="<%= denyUserURL %>" >Deny</a>
</c:if>