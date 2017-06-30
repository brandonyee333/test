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
Group group = themeDisplay.getScopeGroup();
%>

<div class="corp-members view">
	<c:choose>
		<c:when test="<%= group.isOrganization() %>">
			<div class="company-users">
				<%@ include file="/corp_members/corp_entry_users.jspf" %>
			</div>
		</c:when>
		<c:when test="<%= group.isUser() %>">

			<%
			long corpProjectId = ParamUtil.getLong(request, "corpProjectId");
			%>

			<c:choose>
				<c:when test="<%= group.isUser() && (corpProjectId > 0) %>">
					<div class="project-users">
						<%@ include file="/corp_members/corp_project_users.jspf" %>
					</div>
				</c:when>
				<c:when test="<%= group.isUser() %>">
					<div class="projects">
						<%@ include file="/corp_members/corp_projects.jspf" %>
					</div>
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>
</div>