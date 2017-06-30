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

UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.getUserProfileHistory(trainingCustomer.getUserProfileHistoryId());

PortletURL breadcrumbURL = renderResponse.createRenderURL();

breadcrumbURL.setParameter("mvcPath", "/admin/view.jsp");
breadcrumbURL.setParameter("tabs1", "training");
breadcrumbURL.setParameter("tabs2", "certification-exams");
breadcrumbURL.setWindowState(WindowState.MAXIMIZED);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "training"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "certification-exams"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "candidates"), breadcrumbURL.toString());
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "exam-result"), currentURL);
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="candidate-exam"
/>

<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="first-name" />
		</td>
		<td>
			<%= HtmlUtil.escape(userProfileHistory.getFirstName()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="last-name" />
		</td>
		<td>
			<%= HtmlUtil.escape(userProfileHistory.getLastName()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="email-address" />
		</td>
		<td>
			<%= HtmlUtil.escape(userProfileHistory.getEmailAddress()) %>
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
		<td>
			<liferay-ui:message key="candidate-id" />
		</td>
		<td>
			<%= trainingExamResult.getCandidateKey() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="registration-number" />
		</td>
		<td>
			<%= trainingExamResult.getRegistrationNumber() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="import-date" />
		</td>
		<td>
			<%= longDateFormatDate.format(trainingExamResult.getCreateDate()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="test-date" />
		</td>
		<td>
			<%= longDateFormatDate.format(trainingExamResult.getStartDate()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="test-start-time" />
		</td>
		<td>
			<%= longDateFormatTime.format(trainingExamResult.getStartDate()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="correct-items" />
		</td>
		<td>
			<%= trainingExamResult.getCorrectCount() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="incorrect-items" />
		</td>
		<td>
			<%= trainingExamResult.getIncorrectCount() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="skipped-items" />
		</td>
		<td>
			<%= trainingExamResult.getSkippedCount() %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="result" />
		</td>
		<td>
			<%= LanguageUtil.get(pageContext, trainingExamResult.getResult()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="certificate-key" />
		</td>
		<td>
			<%= trainingExamResult.getTrainingCertificateKey() %>
		</td>
	</tr>
</table>

<%
LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

params.put("noSectionKey", PortletPropsValues.TRAINING_EXAM_IGNORE_SECTION);

List<TrainingExamResultSection> trainingExamResultSections = TrainingExamResultSectionLocalServiceUtil.search(trainingExamResultId, params);
%>

<c:if test="<%= permissionChecker.isOmniadmin() %>">
	<aui:button-row>
		<liferay-portlet:actionURL name="deleteTrainingExamResult" var="deleteTrainingExamResultURL">
			<portlet:param name="redirect" value="<%= backURL %>" />
			<portlet:param name="trainingExamResultId" value="<%= String.valueOf(trainingExamResultId) %>" />
		</liferay-portlet:actionURL>

		<aui:button onClick="<%= deleteTrainingExamResultURL %>" value="delete" />
	</aui:button-row>
</c:if>

<br />

<liferay-ui:tabs
	names="section-results"
/>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-training-exam-sections"
	headerNames="section-id,title,score-indicator,computed-score,correct-items,incorrect-items,skipped-items,result"
>
	<liferay-ui:search-container-results
		results="<%= trainingExamResultSections %>"
		total="<%= trainingExamResultSections.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.TrainingExamResultSection"
		escapedModel="<%= true %>"
		keyProperty="trainingExamResultSectionId"
		modelVar="trainingExamResultSection"
	>
		<liferay-ui:search-container-column-text
			name="section-id"
			property="sectionKey"
		/>

		<liferay-ui:search-container-column-text
			name="title"
			property="title"
		/>

		<liferay-ui:search-container-column-text
			name="score-indicator"
			value='<%= LanguageUtil.get(pageContext, (trainingExamResultSection.getScoreIndicator() ? "yes" : "no")) %>'
		/>

		<liferay-ui:search-container-column-text
			name="computed-score"
			property="score"
		/>

		<liferay-ui:search-container-column-text
			name="correct-items"
			property="correctCount"
		/>

		<liferay-ui:search-container-column-text
			name="incorrect-items"
			property="incorrectCount"
		/>

		<liferay-ui:search-container-column-text
			name="skipped-items"
			property="skippedCount"
		/>

		<liferay-ui:search-container-column-text
			name="result"
			property="result"
			translate="<%= true %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<%
List<TrainingExamResultItem> trainingExamResultItems = TrainingExamResultItemLocalServiceUtil.search(trainingExamResultId, params);
%>

<br />

<liferay-ui:tabs
	names="item-results"
/>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-training-exam-items"
	headerNames="item-name,status,section-number,correct-response,candidate-response,distractor,type,time-in-seconds,item-score"
>
	<liferay-ui:search-container-results
		results="<%= trainingExamResultItems %>"
		total="<%= trainingExamResultItems.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.TrainingExamResultItem"
		escapedModel="<%= true %>"
		keyProperty="trainingExamResultItemId"
		modelVar="trainingExamResultItem"
	>
		<liferay-ui:search-container-column-text
			name="item-name"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="status"
			property="statusLabel"
			translate="<%= true %>"
		/>

		<liferay-ui:search-container-column-text
			name="section-id"
			property="sectionKey"
		/>

		<liferay-ui:search-container-column-text
			name="correct-response"
			property="key"
		/>

		<liferay-ui:search-container-column-text
			name="candidate-response"
			property="response"
		/>

		<liferay-ui:search-container-column-text
			name="distractor"
			property="distractorCount"
		/>

		<liferay-ui:search-container-column-text
			name="type"
			property="type"
		/>

		<liferay-ui:search-container-column-text
			name="time-in-seconds"
			property="time"
		/>

		<liferay-ui:search-container-column-text
			name="item-score"
			property="scoreResult"
			translate="<%= true %>"
		/>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>