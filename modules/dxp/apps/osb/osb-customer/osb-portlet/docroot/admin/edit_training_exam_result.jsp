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

long trainingExamResultId = ParamUtil.getLong(request, "trainingExamResultId");

TrainingExamResult trainingExamResult = TrainingExamResultLocalServiceUtil.getTrainingExamResult(trainingExamResultId);

TrainingCustomer trainingCustomer = trainingExamResult.getTrainingCustomer();

TrainingCertificate trainingCertificate = TrainingCertificateLocalServiceUtil.getTrainingCertificateByTrainingCustomerId(trainingCustomer.getTrainingCustomerId());

UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.getUserProfileHistory(trainingCertificate.getUserProfileHistoryId());

PortletURL breadcrumbURL = renderResponse.createRenderURL();

breadcrumbURL.setParameter("mvcPath", "/admin/view.jsp");
breadcrumbURL.setParameter("tabs1", "training");
breadcrumbURL.setParameter("tabs2", "certification-exams");
breadcrumbURL.setWindowState(WindowState.MAXIMIZED);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "training"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "certification-exams"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "candidates"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, userProfileHistory.getFullName(), backURL);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit-candidate"), currentURL);
%>

<portlet:actionURL name="updateTrainingExamResult" var="updateTrainingExamResultURL">
	<portlet:param name="mvcPath" value="/admin/edit_training_exam_result.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingExamResultURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="trainingExamResultId" type="hidden" value="<%= trainingExamResultId %>" />
	<aui:input name="emailAddress" type="hidden" value="<%= userProfileHistory.getEmailAddress() %>" />
	<aui:input name="legalEntityName" type="hidden" value="<%= userProfileHistory.getLegalEntityName() %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="candidate"
	/>

	<liferay-ui:error exception="<%= UserProfileFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= UserProfileHistoryFirstNameException.class %>" message="please-enter-a-valid-first-name" />
	<liferay-ui:error exception="<%= UserProfileHistoryLastNameException.class %>" message="please-enter-a-valid-last-name" />
	<liferay-ui:error exception="<%= UserProfileLastNameException.class %>" message="please-enter-a-valid-last-name" />

	<aui:model-context model="<%= User.class %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="first-name" />
		</td>
		<td>
			<aui:input label="" name="firstName" value="<%= userProfileHistory.getFirstName() %>">
				<aui:validator name="required" />
			</aui:input>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="last-name" />
		</td>
		<td>
			<aui:input label="" name="lastName" value="<%= userProfileHistory.getLastName() %>">
				<aui:validator name="required" />
			</aui:input>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="email-address" />
		</td>
		<td>
			<%= userProfileHistory.getEmailAddress() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="legal-entity-name" />
		</td>
		<td>
			<%= HtmlUtil.escape(userProfileHistory.getLegalEntityName()) %>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />firstName);
	</script>
</c:if>