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

TrainingEvent trainingEvent = (TrainingEvent)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="viewURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/view_training_event.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEvent.getTrainingEventId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="view"
		url="<%= viewURL %>"
	/>

	<c:if test="<%= trainingEvent.getDDLRecordSetId() > 0 %>">
		<portlet:renderURL var="viewTrainingSurveyURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/training_survey_results.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="ddlRecordSetId" value="<%= String.valueOf(trainingEvent.getDDLRecordSetId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="view"
			message="view-survey"
			url="<%= viewTrainingSurveyURL %>"
		/>
	</c:if>

	<c:if test="<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) || RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_TRAINING_ADMIN_ID) %>">
		<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEvent.getTrainingEventId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>

		<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_training_event_workers.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEvent.getTrainingEventId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="assign"
			message="assign-teachers"
			url="<%= assignWorkersURL %>"
		/>

		<portlet:renderURL var="assignCustomersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_training_event_customers.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEvent.getTrainingEventId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="assign"
			message="assign-students"
			url="<%= assignCustomersURL %>"
		/>

		<portlet:actionURL name="deleteTrainingEvent" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="tabs1" value="training" />
			<portlet:param name="tabs2" value="classes" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEvent.getTrainingEventId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>