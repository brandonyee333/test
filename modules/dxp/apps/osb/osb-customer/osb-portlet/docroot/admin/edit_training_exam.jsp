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

long trainingExamId = ParamUtil.getLong(request, "trainingExamId");

TrainingExam trainingExam = null;

try {
	trainingExam = TrainingExamLocalServiceUtil.getTrainingExam(trainingExamId);
}
catch (NoSuchTrainingExamException nstee) {
}

long trainingCertificateTemplateId = BeanParamUtil.getLong(trainingExam, request, "trainingCertificateTemplateId");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "training"), backURL);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "certification-exams"), backURL);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "exams"), backURL);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit-exams"), currentURL);
%>

<portlet:actionURL name="updateTrainingExam" var="updateTrainingExamURL">
	<portlet:param name="mvcPath" value="/admin/edit_training_course.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingExamURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="trainingExamId" type="hidden" value="<%= trainingExamId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="exams"
	/>

	<aui:model-context bean="<%= trainingExam %>" model="<%= TrainingExam.class %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<%= HtmlUtil.escape(trainingExam.getName()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="test-id" />
		</td>
		<td>
			<%= trainingExam.getTestKey() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="certificate-template" />
		</td>
		<td>
			<aui:select label="" name="trainingCertificateTemplateId">
				<aui:option value="" />

				<%
				List<TrainingCertificateTemplate> trainingCertificateTemplates = TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplates(TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM);

				for (TrainingCertificateTemplate trainingCertificateTemplate : trainingCertificateTemplates) {
				%>

					<aui:option label="<%= trainingCertificateTemplate.getName() %>" selected="<%= trainingCertificateTemplateId == trainingCertificateTemplate.getTrainingCertificateTemplateId() %>" value="<%= trainingCertificateTemplate.getTrainingCertificateTemplateId() %>" />

				<%
				}
				%>

			</aui:select>
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>