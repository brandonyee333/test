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

DDLRecordVersion ddlRecordVersion = (DDLRecordVersion)row.getObject();

DDMStorageLink ddmStorageLink = DDMStorageLinkLocalServiceUtil.getClassStorageLink(ddlRecordVersion.getDDMStorageId());

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<liferay-portlet:renderURL var="viewTrainingSurveyResultVersionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/training_survey_result.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="ddlRecordId" value="<%= String.valueOf(ddlRecordVersion.getRecordId()) %>" />
		<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStorageLink.getStructureId()) %>" />
		<portlet:param name="version" value="<%= String.valueOf(ddlRecordVersion.getVersion()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon
		image="view"
		message="view"
		url="<%= viewTrainingSurveyResultVersionURL %>"
	/>
</liferay-ui:icon-menu>