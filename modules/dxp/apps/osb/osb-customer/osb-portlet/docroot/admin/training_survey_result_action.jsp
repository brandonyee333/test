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

DDLRecord ddlRecord = (DDLRecord)row.getObject();

DDLRecordVersion ddlRecordVersion = ddlRecord.getRecordVersion();

DDMStorageLink ddmStorageLink = DDMStorageLinkLocalServiceUtil.getClassStorageLink(ddlRecordVersion.getDDMStorageId());

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="viewTrainingSurveyResultURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/training_survey_result.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="ddlRecordId" value="<%= String.valueOf(ddlRecord.getRecordId()) %>" />
		<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStorageLink.getStructureId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="view"
		message="view"
		url="<%= viewTrainingSurveyResultURL %>"
	/>

	<portlet:renderURL var="viewTrainingSurveyResultHistoryURL">
		<portlet:param name="mvcPath" value="/admin/training_survey_result_history.jsp" />
		<portlet:param name="backURL" value="<%= currentURL %>" />
		<portlet:param name="ddlRecordId" value="<%= String.valueOf(ddlRecord.getRecordId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="time"
		message="view-history"
		url="<%= viewTrainingSurveyResultHistoryURL %>"
	/>
</liferay-ui:icon-menu>