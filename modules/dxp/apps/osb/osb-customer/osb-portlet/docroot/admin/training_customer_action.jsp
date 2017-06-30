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

User trainingCustomerUser = (User)row.getObject();

TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(trainingCustomerUser.getUserId(), null);

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= (trainingCustomer != null) && trainingCustomer.isTrainingUser() %>">
		<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_training_customer.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<portlet:renderURL var="viewTrainingEventsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="tabs1" value="training" />
		<portlet:param name="tabs2" value="classes" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="trainingUserId" value="<%= String.valueOf(trainingCustomerUser.getUserId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="../shopping/cart"
		message="view-classes"
		url="<%= viewTrainingEventsURL %>"
	/>

	<c:choose>
		<c:when test="<%= trainingCustomer != null %>">
			<portlet:renderURL var="editTrainingLinkedUserURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_training_linked_user.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="edit"
				message="link-profile"
				url="<%= editTrainingLinkedUserURL %>"
			/>
		</c:when>
		<c:otherwise>
			<portlet:renderURL var="editTrainingLinkedUserURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_training_linked_user.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="userId" value="<%= String.valueOf(trainingCustomerUser.getUserId()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				image="edit"
				message="link-profile"
				url="<%= editTrainingLinkedUserURL %>"
			/>
		</c:otherwise>
	</c:choose>

	<c:if test="<%= (trainingCustomer != null) && trainingCustomer.isTrainingUser() %>">
		<portlet:actionURL name="deleteTrainingCustomers" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="tabs1" value="training" />
			<portlet:param name="tabs2" value="students" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingUserId" value="<%= String.valueOf(trainingCustomerUser.getUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>