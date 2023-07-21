<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "users");

String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

long teamId = ParamUtil.getLong(request, "teamId");

Team team = TeamLocalServiceUtil.fetchTeam(teamId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/edit_team_assignments.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("teamId", String.valueOf(team.getTeamId()));

request.setAttribute("edit_team_assignments.jsp-tabs1", tabs1);

request.setAttribute("edit_team_assignments.jsp-team", team);

request.setAttribute("edit_team_assignments.jsp-portletURL", portletURL);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(team.getName());
%>

<c:choose>
	<c:when test='<%= tabs1.equals("users") %>'>
		<liferay-util:include page="/edit_team_assignments_users.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("user-groups") %>'>
		<liferay-util:include page="/edit_team_assignments_user_groups.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>

<%
Group group = GroupLocalServiceUtil.getGroup(team.getGroupId());

if (group != null) {
	group = StagingUtil.getLiveGroup(group.getGroupId());
}

Organization organization = null;

if (group.isOrganization()) {
	organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());
}

if (group.isOrganization()) {
	UsersAdminUtil.addPortletBreadcrumbEntries(organization, request, renderResponse);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, group.getDescriptiveName(locale), null);
}

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "manage-teams"), redirect);
PortalUtil.addPortletBreadcrumbEntry(request, team.getName(), null);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "assign-members"), currentURL);
%>