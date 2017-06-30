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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User curUser = (User)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="selectUserProfilesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/admin/select_user_profile.jsp" />
		<portlet:param name="userId" value="<%= String.valueOf(curUser.getUserId()) %>" />
	</portlet:renderURL>

	<%
	String taglibURL = "javascript:var userProfilesWindow = window.open('" + selectUserProfilesURL + "', 'userProfiles', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); userProfilesWindow.focus();";
	%>

	<liferay-ui:icon
		cssClass="modify-link"
		image="add"
		label="<%= true %>"
		message="select-profile"
		url="<%= taglibURL %>"
	/>
</liferay-ui:icon-menu>