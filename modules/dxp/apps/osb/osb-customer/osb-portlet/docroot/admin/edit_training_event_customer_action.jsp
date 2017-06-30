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
String redirect = ParamUtil.getString(request, "redirect");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TrainingCustomer trainingCustomer = (TrainingCustomer)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("edit.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<portlet:actionURL name="deleteTrainingCustomer" var="deleteTrainingCustomerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
	</portlet:actionURL>

	<%
	Object[] arguments = new Object[] {trainingCustomer.getFirstName() + StringPool.SPACE + trainingCustomer.getLastName(), trainingCustomer.getEmailAddress()};

	String taglibConfirmationLabel = LanguageUtil.format(locale, "are-you-sure-you-want-to-remove-x-this-will-remove-any-awarded-certificates-for-this-class", arguments);
	%>

	<liferay-ui:icon-delete
		confirmation="<%= taglibConfirmationLabel %>"
		message="remove-user"
		url="<%= deleteTrainingCustomerURL %>"
	/>

	<c:if test="<%= TrainingCertificateLocalServiceUtil.hasTrainingCertificate(trainingCustomer.getTrainingCustomerId()) %>">
		<portlet:renderURL var="editCertificateURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_training_certificate.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editCertificateURL %>"
		/>

		<portlet:resourceURL id="generateCertificate" var="generateCertificateURL">
			<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
		</portlet:resourceURL>

		<liferay-ui:icon
			image="download"
			message="generate-certificate"
			url="<%= generateCertificateURL %>"
		/>

		<c:if test="<%= trainingCustomer.getStatus() == TrainingCustomerConstants.STATUS_PENDING_PARTICIPANT_FORM_COMPLETION %>">
			<portlet:actionURL name="sendTrainingSurvey" var="sendTrainingSurvey" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="download"
				message="resend-participation-form"
				url="<%= sendTrainingSurvey %>"
			/>
		</c:if>

		<c:if test="<%= trainingCustomer.getStatus() == TrainingCustomerConstants.STATUS_CERTIFIED %>">
			<portlet:actionURL name="sendTrainingCertificate" var="sendTrainingCertificate" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_training_event.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="trainingCustomerId" value="<%= String.valueOf(trainingCustomer.getTrainingCustomerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="download"
				message="resend-certificate"
				url="<%= sendTrainingCertificate %>"
			/>
		</c:if>
	</c:if>
</liferay-ui:icon-menu>