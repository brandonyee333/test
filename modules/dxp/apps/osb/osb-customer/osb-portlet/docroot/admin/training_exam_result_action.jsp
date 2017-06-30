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

TrainingExamResult trainingExamResult = (TrainingExamResult)row.getObject();

TrainingExam trainingExam = trainingExamResult.getTrainingExam();

long trainingCertificateTemplateId = trainingExam.getTrainingCertificateTemplateId();

TrainingCustomer trainingCustomer = trainingExamResult.getTrainingCustomer();

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="viewURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/training_exam_result.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="trainingExamResultId" value="<%= String.valueOf(trainingExamResult.getTrainingExamResultId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		image="view"
		url="<%= viewURL %>"
	/>

	<c:if test="<%= (trainingCertificateTemplateId > 0) && (trainingExamResult.getGrade() == TrainingExamResultConstants.GRADE_PASSED) %>">
		<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_training_exam_result.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingExamResultId" value="<%= String.valueOf(trainingExamResult.getTrainingExamResultId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>

		<portlet:resourceURL id="generateCertificate" var="generateCertificateURL">
			<portlet:param name="mvcPath" value="/admin/training_exam_result.jsp" />
			<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
		</portlet:resourceURL>

		<liferay-ui:icon
			image="download"
			message="generate-certificate"
			url="<%= generateCertificateURL %>"
		/>

		<portlet:actionURL name="sendTrainingCertificates" var="sendCertificatesURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/training_exam_result.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(TrainingExamResult.class)) %>" />
			<portlet:param name="classPK" value="<%= String.valueOf(trainingExamResult.getTrainingExamResultId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="share"
			message="email-certificate"
			url="<%= sendCertificatesURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>