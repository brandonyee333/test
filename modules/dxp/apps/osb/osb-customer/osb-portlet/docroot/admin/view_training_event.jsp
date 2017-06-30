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
String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingEventId = ParamUtil.getLong(request, "trainingEventId");

TrainingEvent trainingEvent = null;

try {
	trainingEvent = TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);
}
catch (NoSuchTrainingEventException nstee) {
}
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="class"
/>

<c:if test="<%= trainingEvent != null %>">
	<%@ include file="/admin/training_event_details.jspf" %>
</c:if>

<liferay-ui:tabs
	names="students"
/>

<liferay-ui:search-container
	emptyResultsMessage="no-students-were-found"
	headerNames="first-name,last-name,email-address,company,certificate-key"
>

	<%
	List<TrainingCustomer> trainingCustomers = Collections.emptyList();

	if (trainingEvent != null) {
		trainingCustomers = trainingEvent.getTrainingCustomers();
	}
	%>

	<liferay-ui:search-container-results
		results="<%= trainingCustomers %>"
		total="<%= trainingCustomers.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.TrainingCustomer"
		escapedModel="<%= true %>"
		keyProperty="trainingCustomerId"
		modelVar="trainingCustomer"
	>

		<%
		UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(trainingCustomer.getUserProfileHistoryId());
		%>

		<liferay-ui:search-container-column-text
			name="first-name"
			value="<%= HtmlUtil.escape(userProfileHistory.getFirstName()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="last-name"
			value="<%= HtmlUtil.escape(userProfileHistory.getLastName()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="email-address"
			value="<%= HtmlUtil.escape(userProfileHistory.getEmailAddress()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="company"
			value="<%= HtmlUtil.escape(userProfileHistory.getLegalEntityName()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="attended"
			translate="<%= true %>"
			value='<%= trainingCustomer.getStatus() > 0 ? "yes" : "no" %>'
		/>

		<liferay-ui:search-container-column-text
			name="certificate-key"
			value="<%= !TrainingCertificateLocalServiceUtil.hasTrainingCertificateCertifiedDate(trainingCustomer.getTrainingCustomerId()) ? StringPool.BLANK : trainingCustomer.getTrainingCertificateKey() %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>