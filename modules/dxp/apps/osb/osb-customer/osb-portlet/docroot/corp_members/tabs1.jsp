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
String tabs1 = ParamUtil.getString(request, "tabs1", "manage-users");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/corp_members/view.jsp");
%>

<nav class="tab-nav">
	<ul>
		<li class="f<%= tabs1.equals("manage-users") ? " selected" : StringPool.BLANK %>">

			<%
			portletURL.setParameter("tabs1", "manage-users");
			%>

			<a href="<%= portletURL.toString() %>"><liferay-ui:message key="manage-users" /></a>
		</li>
		<li class="l<%= tabs1.equals("user-requests") ? " selected" : StringPool.BLANK %>">

			<%
			portletURL.setParameter("tabs1", "user-requests");
			%>

			<a href="<%= portletURL.toString() %>"><liferay-ui:message key="user-requests" /></a>
		</li>
	</ul>
</nav>