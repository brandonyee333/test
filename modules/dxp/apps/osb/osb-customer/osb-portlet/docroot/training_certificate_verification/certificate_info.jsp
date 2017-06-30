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
TrainingCertificate trainingCertificate = (TrainingCertificate)SessionMessages.get(portletSession, "certificateKeyVerified");

TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.getTrainingCustomer(trainingCertificate.getTrainingCustomerId());

String message = "training-verified";

int trainingCertificateType = PrefsParamUtil.getInteger(portletPreferences, request, "trainingCertificateType");

if (trainingCertificateType == TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM) {
	message = "certification-verified";
}
%>

<liferay-ui:success key="certificateKeyVerified" message="<%= message %>" />

<c:choose>
	<c:when test="<%= TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM == trainingCertificateType %>">

		<%
		TrainingExamResult trainingExamResult = TrainingExamResultLocalServiceUtil.getTrainingExamResult(trainingCustomer.getClassPK());
		%>

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />:
			</td>
			<td>

				<%
				UserProfileHistory trainingCustomerUserProfileHistory = UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(trainingCustomer.getUserProfileHistoryId());
				%>

				<%= HtmlUtil.escape(trainingCustomerUserProfileHistory.getFullName()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="certification-date" />
			</td>
			<td>
				<%= longDateFormatDate.format(trainingExamResult.getStartDate()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="certification-type" />:
			</td>
			<td>

				<%
				TrainingCertificateTemplate trainingCertificateTemplate = trainingExamResult.getTrainingCertificateTemplate();
				%>

				<%= trainingCertificateTemplate.getName() %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="certificate-number" />:
			</td>
			<td>
				<%= trainingExamResult.getTrainingCertificateKey() %>
			</td>
		</tr>
		</table>
	</c:when>
	<c:otherwise>

		<%
		TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.getTrainingEvent(trainingCustomer.getClassPK());
		%>

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />:
			</td>
			<td>

				<%
				UserProfileHistory trainingCustomerUserProfileHistory = UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(trainingCustomer.getUserProfileHistoryId());
				%>

				<%= HtmlUtil.escape(trainingCustomerUserProfileHistory.getFullName()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="course" />:
			</td>
			<td>

				<%
				TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();
				%>

				<%= trainingCourse.getName() %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="version" />:
			</td>
			<td>

				<%
				ListType portalMinorVersionType = ListTypeServiceUtil.getListType(trainingEvent.getPortalMinorVersion());
				%>

				<%= portalMinorVersionType.getName() %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="date" />:
			</td>
			<td>

				<%
				User trainingCustomerUser = trainingCustomer.getUser();

				TimeZone trainingEventTimeZone = TimeZoneUtil.getTimeZone(trainingEvent.getTimeZoneId());

				DateFormat trainingCustomerDateFormat = DateFormatFactoryUtil.getDate(trainingCustomerUser.getLocale(), trainingEventTimeZone);
				%>

				<%= trainingCustomerDateFormat.format(trainingCertificate.getCertifiedDate()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="location" />:
			</td>
			<td>
				<%= trainingEvent.getAddressDisplayHTML() %>
			</td>
		</tr>

		<%
		String trainingWorkerDisplayHTML = trainingEvent.getTrainingWorkerDisplayHTML();
		%>

		<c:if test="<%= Validator.isNotNull(trainingWorkerDisplayHTML) %>">
			<tr>
				<td>
					<liferay-ui:message key="certified-liferay-trainer" />:
				</td>
				<td>
					<%= HtmlUtil.escape(trainingWorkerDisplayHTML) %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="certificate-number" />:
			</td>
			<td>
				<%= trainingCustomer.getTrainingCertificateKey() %>
			</td>
		</tr>
		</table>
	</c:otherwise>
</c:choose>