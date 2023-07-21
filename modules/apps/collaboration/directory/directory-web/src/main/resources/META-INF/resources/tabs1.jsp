<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/directory/view");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<c:if test="<%= !portletName.equals(PortletKeys.FRIENDS_DIRECTORY) %>">
		<aui:nav cssClass="navbar-nav">

			<%
			portletURL.setParameter("tabs1", "users");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="users" selected='<%= tabs1.equals("users") %>' />

			<%
			portletURL.setParameter("tabs1", "organizations");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="organizations" selected='<%= tabs1.equals("organizations") %>' />

			<%
			portletURL.setParameter("tabs1", "user-groups");
			%>

			<aui:nav-item href="<%= portletURL.toString() %>" label="user-groups" selected='<%= tabs1.equals("user-groups") %>' />
		</aui:nav>
	</c:if>

	<aui:nav-bar-search>
		<c:choose>
			<c:when test='<%= tabs1.equals("organizations") %>'>
				<liferay-util:include page="/organization_search.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= tabs1.equals("user-groups") %>'>
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</c:when>
			<c:when test='<%= tabs1.equals("users") || portletName.equals(PortletKeys.FRIENDS_DIRECTORY) %>'>
				<liferay-util:include page="/user_search.jsp" servletContext="<%= application %>" />
			</c:when>
		</c:choose>
	</aui:nav-bar-search>
</aui:nav-bar>