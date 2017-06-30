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
long ddlRecordSetId = ParamUtil.getLong(request, "ddlRecordSetId");

DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.getRecordSet(ddlRecordSetId);

List<DDLRecord> ddlRecords = DDLRecordLocalServiceUtil.getRecords(ddlRecordSetId, themeDisplay.getUserId());

TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(ddlRecordSetId);

TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

Locale trainingEventLocale = LocaleUtil.fromLanguageId(trainingEvent.getLanguageId(), false);

TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.getTrainingCustomer(themeDisplay.getUserId(), PortalUtil.getClassNameId(TrainingEvent.class), trainingEvent.getTrainingEventId());

UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.getUserProfileHistory(trainingCustomer.getUserProfileHistoryId());
%>

<c:choose>
	<c:when test="<%= TrainingCertificateLocalServiceUtil.hasTrainingCertificateCertifiedDate(trainingCustomer.getTrainingCustomerId()) %>">
		<h3 class="header-title">
			<span class="portlet-icon-gear"></span>

			<%= LanguageUtil.get(trainingEventLocale, "liferay-training-certificate") %>
		</h3>

		<div class="survey-message">
			<p>
				<%= LanguageUtil.get(trainingEventLocale, "thank-you-for-submitting-your-information") %>
			</p>

			<p>
				<%= LanguageUtil.format(trainingEventLocale, "your-certificate-number-is-x,-which-you-can-use-to-verify-your-attendance", new String[] {trainingCustomer.getTrainingCertificateKey(), "/services/training/verify"}) %>
			</p>

			<p>
				<%= LanguageUtil.get(trainingEventLocale, "would-you-like-to-provide-additional-feedback-on-the-training") %>
			</p>
		</div>

		<input class="btn survey-message" onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="mvcPath" value="/training_survey/form.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="ddlRecordSetId" value="<%= String.valueOf(ddlRecordSetId) %>" /></portlet:renderURL>';" type="button" value='<%= LanguageUtil.get(trainingEventLocale, ddlRecords.isEmpty() ? "start-survey" : "retake") %>' />

		<h4 class="header-certify">
			<span class="portlet-icon-check"></span>

			<%= LanguageUtil.get(trainingEventLocale, "get-certified") %>
		</h4>

		<div class="survey-message">
			<p>
				<%= LanguageUtil.format(trainingEventLocale, "now-that-you-have-completed-a-liferay-training-course", "/services/certification") %>
			</p>

			<p>
				<%= LanguageUtil.format(trainingEventLocale, "you-can-find-out-more-about-the-topics-covered-in-the-certification-exam-in-the-exam-blueprint", "/services/certification/professional-developer/6.2") %>
			</p>

			<div class="header-learn">
				<%= LanguageUtil.get(trainingEventLocale, "want-to-learn-more") %>
			</div>

			<p>
				<%= LanguageUtil.format(trainingEventLocale, "expand-your-knowledge-with-more-information-on-our-other-courses", "/services/training") %>
			</p>
		</div>
	</c:when>
	<c:otherwise>
		<aui:model-context bean="<%= userProfileHistory %>" model="<%= UserProfileHistory.class %>" />

		<portlet:actionURL name="updateTrainingCertificate" var="updateTrainingCertificateURL">
			<portlet:param name="mvcPath" value="/training_survey/view.jsp" />
		</portlet:actionURL>

		<aui:form action="<%= updateTrainingCertificateURL %>" method="post" name="fm">
			<aui:input name="ddlRecordSetId" type="hidden" value="<%= ddlRecordSetId %>" />
			<aui:input name="trainingCustomerId" type="hidden" value="<%= trainingCustomer.getTrainingCustomerId() %>" />

			<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />
			<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />
			<liferay-ui:error exception="<%= TrainingCertificateSurveyStatusException.class %>" message="please-select-a-valid-feedback-response" />
			<liferay-ui:error exception="<%= UserProfileFirstNameException.class %>" message="please-enter-a-valid-first-name" />
			<liferay-ui:error exception="<%= UserProfileHistoryFirstNameException.class %>" message="please-enter-a-valid-first-name" />
			<liferay-ui:error exception="<%= UserProfileHistoryLastNameException.class %>" message="please-enter-a-valid-last-name" />
			<liferay-ui:error exception="<%= UserProfileLastNameException.class %>" message="please-enter-a-valid-last-name" />

			<h3 class="header-title">
				<%= LanguageUtil.get(trainingEventLocale, "participant-form") %>
			</h3>

			<p>
				<%= LanguageUtil.format(trainingEventLocale, "welcome,-x", userProfileHistory.getFirstName()) %>
			</p>

			<p>
				<%= LanguageUtil.format(trainingEventLocale, "thanks-for-attending-x-on-x.-take-a-moment-to-confirm-that-the-information-below-is-exactly-what-you-would-like-displayed-on-your-training-certificate,-which-will-be-emailed-to-you-upon-completion", new String[] {trainingCourse.getName(), longDateFormatDate.format(trainingEvent.getStartDate())}) %>
			</p>

			<p>
				<i><%= LanguageUtil.get(trainingEventLocale, "please-note-that-you-will-not-be-able-to-make-changes-to-your-certificate-once-you-have-submitted-the-form") %></i>
			</p>

			<aui:input cssClass="participant-name" name="firstName">
				<aui:validator name="required" />
			</aui:input>

			<aui:input cssClass="participant-name" name="lastName">
				<aui:validator name="required" />
			</aui:input>

			<aui:field-wrapper cssClass="provide-feedback" name="i-would-like-to-provide-my-feedback-on-the-training">
				<aui:input label="yes" name="surveyStatus" type="radio" value="<%= TrainingCertificateConstants.SURVEY_STATUS_OPT_IN %>" />
				<aui:input label="no" name="surveyStatus" type="radio" value="<%= TrainingCertificateConstants.SURVEY_STATUS_OPT_OUT %>"  />
			</aui:field-wrapper>

			<aui:button type="submit" value="submit" />
		</aui:form>
	</c:otherwise>
</c:choose>