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

long ddlRecordSetId = ParamUtil.getLong(request, "ddlRecordSetId");

DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.getRecordSet(ddlRecordSetId);

TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(ddlRecordSetId);

Locale trainingEventLocale = LocaleUtil.fromLanguageId(trainingEvent.getLanguageId(), false);
%>

<a class="btn header-back style-a" href="<%= HtmlUtil.escapeAttribute(backURL) %>"><%= LanguageUtil.get(trainingEventLocale, "back") %></a>

<div class="form-header">
	<h3 class="header-title">
		<%= ddlRecordSet.getName(trainingEventLocale) %>
	</h3>

	<h4 class="header-date">
		<%= longDateFormatDate.format(trainingEvent.getStartDate()) + " - " + longDateFormatDate.format(trainingEvent.getEndDate()) %>
	</h4>
</div>

<portlet:actionURL name="updateTrainingSurveyResult" var="updateTrainingSurveyResultURL">
	<portlet:param name="mvcPath" value="/training_survey/form.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingSurveyResultURL %>" cssClass="lfr-dynamic-form" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="ddlRecordSetId" type="hidden" value="<%= ddlRecordSetId %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<liferay-ui:error exception="<%= FileSizeException.class %>" message='<%= LanguageUtil.format(trainingEventLocale, "please-enter-a-file-with-a-valid-file-size-no-larger-than-x", PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) / 1024) %>' />
	<liferay-ui:error exception="<%= StorageFieldRequiredException.class %>" message='<%= LanguageUtil.get(trainingEventLocale, "please-fill-out-all-required-fields") %>' />

	<aui:fieldset cssClass="survey-body">

		<%
		DDMStructure ddmStructure = ddlRecordSet.getDDMStructure(0);

		Fields fields = null;

		DDLRecordVersion ddlRecordVersion = null;

		List<DDLRecord> ddlRecords = DDLRecordLocalServiceUtil.getRecords(ddlRecordSetId, themeDisplay.getUserId());

		if (!ddlRecords.isEmpty()) {
			DDLRecord ddlRecord = ddlRecords.get(0);

			ddlRecordVersion = DDLRecordLocalServiceUtil.getLatestRecordVersion(ddlRecord.getRecordId());
		}

		if (ddlRecordVersion != null) {
			fields = StorageEngineUtil.getFields(ddlRecordVersion.getDDMStorageId());
		}
		%>

		<%= HtmlUtil.unescape(DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd(), fields, locale)) %>

		<aui:button-row>
			<aui:button href="<%= backURL %>" type="cancel" />

			<aui:button type="submit" value="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>