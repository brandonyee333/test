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

<%@ include file="/corp_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CorpEntry corpEntry = (CorpEntry)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= OSBCorpEntryPermission.contains(themeDisplay.getPermissionChecker(), corpEntry, OSBActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editCorpEntryURL">
			<portlet:param name="mvcPath" value="/corp_admin/edit_corp_entry.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="corpEntryId" value="<%= String.valueOf(corpEntry.getCorpEntryId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editCorpEntryURL %>" />
	</c:if>

	<c:if test="<%= OSBCorpEntryPermission.contains(themeDisplay.getPermissionChecker(), corpEntry, OSBActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteCorpEntry" var="deleteCorpEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="corpEntryId" value="<%= String.valueOf(corpEntry.getCorpEntryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteCorpEntryURL %>" />
	</c:if>
</liferay-ui:icon-menu>