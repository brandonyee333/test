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

CorpProjectMessage corpProjectMessage = (CorpProjectMessage)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:actionURL name="deleteCorpProjectMessage" var="deleteCorpProjectMessageURL">
		<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
		<portlet:param name="tabs1" value="messages" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProjectMessage.getCorpProjectId()) %>" />
		<portlet:param name="corpProjectMessageId" value="<%= String.valueOf(corpProjectMessage.getCorpProjectMessageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		image="delete"
		url="<%= deleteCorpProjectMessageURL %>"
	/>

	<portlet:renderURL var="editCorpProjectMessageURL">
		<portlet:param name="mvcPath" value="/corp_project_admin/edit_corp_project_message.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="corpProjectMessageId" value="<%= String.valueOf(corpProjectMessage.getCorpProjectMessageId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="edit"
		url="<%= editCorpProjectMessageURL %>"
	/>
</liferay-ui:icon-menu>