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

TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(ddlRecordSetId);

TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

Locale trainingEventLocale = LocaleUtil.fromLanguageId(trainingEvent.getLanguageId(), false);

TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.getTrainingCustomer(themeDisplay.getUserId(), PortalUtil.getClassNameId(TrainingEvent.class), trainingEvent.getTrainingEventId());
%>

<div class="heading-msg portlet-msg-success">
	<span class="success-icon"></span>

	<liferay-ui:message key="your-responses-were-recorded" />
</div>

<h3 class="header-title">
	<span class="portlet-icon-gear"></span>

	<%= LanguageUtil.get(trainingEventLocale, "liferay-training-certificate") %>
</h3>

<div class="survey-message">
	<p>
		<%= LanguageUtil.format(trainingEventLocale, "thanks-for-confirming-your-information.-we-will-email-your-certificate-for-x-to-you-shortly", trainingCourse.getName()) %>
	</p>

	<p>
		<%= LanguageUtil.format(trainingEventLocale, "your-certificate-number-is-x,-which-you-can-use-to-verify-your-attendance", new String[] {trainingCustomer.getTrainingCertificateKey(), "/services/training/verify"}) %>
	</p>
</div>

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