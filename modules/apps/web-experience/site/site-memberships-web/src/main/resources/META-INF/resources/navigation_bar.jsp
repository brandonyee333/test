<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL usersURL = siteMembershipsDisplayContext.getPortletURL();

		usersURL.setParameter("tabs1", "users");

		String tabs1 = siteMembershipsDisplayContext.getTabs1();
		%>

		<aui:nav-item href="<%= usersURL.toString() %>" label="users" selected='<%= tabs1.equals("users") %>' />

		<%
		PortletURL organizationsURL = siteMembershipsDisplayContext.getPortletURL();

		organizationsURL.setParameter("tabs1", "organizations");
		%>

		<aui:nav-item href="<%= organizationsURL.toString() %>" label="organizations" selected='<%= tabs1.equals("organizations") %>' />

		<%
		PortletURL userGroupsURL = siteMembershipsDisplayContext.getPortletURL();

		userGroupsURL.setParameter("tabs1", "user-groups");
		%>

		<aui:nav-item href="<%= userGroupsURL.toString() %>" label="user-groups" selected='<%= tabs1.equals("user-groups") %>' />
	</aui:nav>

	<c:if test='<%= ParamUtil.getBoolean(request, "searchEnabled") %>'>
		<aui:nav-bar-search>
			<aui:form action="<%= siteMembershipsDisplayContext.getPortletURL() %>" name="searchFm">
				<liferay-ui:input-search
					autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
					markupView="lexicon"
				/>
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>