<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "users");

Team team = (Team)request.getAttribute("edit_team_assignments.jsp-team");

boolean searchEnabled = ParamUtil.getBoolean(request, "searchEnabled");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/edit_team_assignments.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("teamId", String.valueOf(team.getTeamId()));
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL usersURL = PortletURLUtil.clone(portletURL, renderResponse);

		usersURL.setParameter("tabs1", "users");
		%>

		<aui:nav-item href="<%= usersURL.toString() %>" label="users" selected='<%= tabs1.equals("users") %>' />

		<%
		PortletURL userGroupsURL = PortletURLUtil.clone(portletURL, renderResponse);

		userGroupsURL.setParameter("tabs1", "user-groups");
		%>

		<aui:nav-item href="<%= userGroupsURL.toString() %>" label="user-groups" selected='<%= tabs1.equals("user-groups") %>' />
	</aui:nav>

	<c:if test="<%= searchEnabled %>">
		<aui:nav-bar-search>
			<aui:form action="<%= portletURL %>" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="portletURL" />

				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>