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

<%@ include file="/profile_navigation/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();
%>

<c:choose>
	<c:when test="<%= group.isOrganization() %>">
		<%@ include file="/profile_navigation/view_organization.jspf" %>
	</c:when>
	<c:when test="<%= group.isUser() %>">
		<%@ include file="/profile_navigation/view_user.jspf" %>
	</c:when>
</c:choose>