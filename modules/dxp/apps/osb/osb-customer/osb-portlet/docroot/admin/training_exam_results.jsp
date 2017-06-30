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

User trainingExamResultUser = trainingExamResult.getUser();

ExpandoBridge expandoBridge = trainingExamResultUser.getExpandoBridge();

PortletURL breadcrumbURL = renderResponse.createRenderURL();

breadcrumbURL.setParameter("mvcPath", "/admin/view.jsp");
breadcrumbURL.setParameter("tabs1", "training");
breadcrumbURL.setParameter("tabs2", "certification-exams");
breadcrumbURL.setWindowState(WindowState.MAXIMIZED);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "training"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "certification-exams"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "candidates"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, trainingExamResultUser.getFullName(), currentURL);
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="candidate"
/>

<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="first-name" />
		</td>
		<td>
			<%= HtmlUtil.escape(trainingExamResultUser.getFirstName()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="last-name" />
		</td>
		<td>
			<%= HtmlUtil.escape(trainingExamResultUser.getLastName()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="email-address" />
		</td>
		<td>
			<%= HtmlUtil.escape(trainingExamResultUser.getEmailAddress()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="legal-entity-name" />
		</td>
		<td>
			<%= HtmlUtil.escape((String)expandoBridge.getAttribute("osbCompany")) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="candidate-id" />
		</td>
		<td>
			<%= trainingExamResult.getCandidateKey() %>
		</td>
	</tr>
</table>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/training_exam_results.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("trainingExamResultId", String.valueOf(trainingExamResultId));

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-ui:tabs
	names="candidate-exams"
/>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<liferay-ui:search-container
		searchContainer="<%= new TrainingExamResultSearch(renderRequest, portletURL) %>"
	>

		<%
		TrainingExamResultDisplayTerms displayTerms = (TrainingExamResultDisplayTerms)searchContainer.getDisplayTerms();
		TrainingExamResultSearchTerms searchTerms = (TrainingExamResultSearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/admin/training_exam_results_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/admin/training_exam_results_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.TrainingExamResult"
			escapedModel="<%= true %>"
			keyProperty="trainingExamResultId"
			modelVar="curTrainingExamResult"
		>
			<portlet:renderURL var="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/training_exam_result.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="trainingExamResultId" value="<%= String.valueOf(curTrainingExamResult.getTrainingExamResultId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="registration-number"
				property="registrationNumber"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="test-date"
				value="<%= longDateFormatDate.format(curTrainingExamResult.getStartDate()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="test-start-time"
				value="<%= longDateFormatTime.format(curTrainingExamResult.getStartDate()) %>"
			/>

			<%
			TrainingExam trainingExam = curTrainingExamResult.getTrainingExam();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="test-id"
				value="<%= trainingExam.getTestKey() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="form-id"
				property="formKey"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="correct-items"
				property="correctCount"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="incorrect-items"
				property="incorrectCount"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="skipped-items"
				property="skippedCount"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="result"
				property="result"
				translate="<%= true %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="certificate-key"
				property="trainingCertificateKey"
				translate="<%= true %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/training_exam_result_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator paginate="<%= false %>" />
	</liferay-ui:search-container>
</aui:form>