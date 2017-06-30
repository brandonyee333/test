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
long corpProjectId = ParamUtil.getLong(request, "corpProjectId");

List<User> users = CorpMembersUtil.getCorpProjectUsers(corpProjectId, StringPool.BLANK, 0, 0, 16, new UserFirstNameComparator(true));
int usersCount = CorpMembersUtil.getCorpProjectUsersCount(corpProjectId, StringPool.BLANK, 0);

String seeMembersURL = ParamUtil.getString(request, "seeMembersURL");
%>

<div class="users">

	<%
	for (User user2 : users) {
	%>

		<a class="user" data-userFullname="<%= HtmlUtil.escapeAttribute(user2.getFullName()) %>" href="<%= HtmlUtil.escapeAttribute(seeMembersURL) %>#user-<%= user2.getUserId() %>"><img class="avatar" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>

	<%
	}
	%>

</div>

<div class="all-users">
	<c:choose>
		<c:when test="<%= OSBCorpProjectPermission.contains(permissionChecker, corpProjectId, OSBActionKeys.ASSIGN_CORP_ENTRY_ROLES) %>">
			<a href="<%= HtmlUtil.escapeAttribute(seeMembersURL) %>"><liferay-ui:message arguments="<%= String.valueOf(usersCount) %>" key="manage-x-members" /></a>
		</c:when>
		<c:otherwise>
			<a href="<%= HtmlUtil.escapeAttribute(seeMembersURL) %>"><liferay-ui:message arguments="<%= String.valueOf(usersCount) %>" key="see-x-members" /></a>
		</c:otherwise>
	</c:choose>
</div>