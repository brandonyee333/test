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

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/training_survey_results.jsp");
portletURL.setParameter("redirect", currentURL);
portletURL.setParameter("ddlRecordSetId", String.valueOf(ddlRecordSet.getRecordSetId()));

request.setAttribute("view.jsp-portletURL", portletURL);

DDMStructure ddmStructure = ddlRecordSet.getDDMStructure(0);

String languageId = LanguageUtil.getLanguageId(request);

Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap(languageId);

List<String> headerNames = new ArrayList<String>();

headerNames.add("prior-survey-form");
headerNames.add("first-name");
headerNames.add("last-name");
headerNames.add("email");
headerNames.add("company");

for (Map<String, String> fields : fieldsMap.values()) {
	String label = fields.get(FieldConstants.LABEL);

	headerNames.add(label);
}

headerNames.add("modified-date");
headerNames.add(StringPool.BLANK);

SearchContainer searchContainer = new SearchContainer(renderRequest, portletURL, headerNames, "no-survey-records-were-found");

int status = WorkflowConstants.STATUS_ANY;

int total = DDLRecordLocalServiceUtil.getRecordsCount(ddlRecordSet.getRecordSetId(), status);

searchContainer.setTotal(total);

List<DDLRecord> results = DDLRecordLocalServiceUtil.getRecords(ddlRecordSet.getRecordSetId(), status, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	DDLRecord ddlRecord = results.get(i);

	DDLRecordVersion ddlRecordVersion = ddlRecord.getRecordVersion();

	Fields fieldsModel = StorageEngineUtil.getFields(ddlRecordVersion.getDDMStorageId());

	ResultRow row = new ResultRow(ddlRecord, ddlRecord.getRecordId(), i);

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter("mvcPath", "/admin/training_survey_result.jsp");
	rowURL.setParameter("redirect", currentURL);
	rowURL.setParameter("ddlRecordId", String.valueOf(ddlRecord.getRecordId()));

	DDMStorageLink ddmStorageLink = DDMStorageLinkLocalServiceUtil.getClassStorageLink(ddlRecordVersion.getDDMStorageId());

	rowURL.setParameter("ddmStructureId", String.valueOf(ddmStorageLink.getStructureId()));

	// Columns

	if (ddmStorageLink.getStructureId() != ddlRecordSet.getDDMStructureId()) {
		row.addText("center", "middle", "<img class=\"icon\" src=\"" + themeDisplay.getPathThemeImages() + "/api/exception.png\" />");
	}
	else {
		row.addText(StringPool.BLANK);
	}

	TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.getTrainingCustomer(ddlRecord.getUserId(), PortalUtil.getClassNameId(TrainingEvent.class), trainingEvent.getTrainingEventId());

	UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.getUserProfileHistory(trainingCustomer.getUserProfileHistoryId());

	row.addText(HtmlUtil.escape(userProfileHistory.getFirstName()), rowURL);
	row.addText(HtmlUtil.escape(userProfileHistory.getLastName()), rowURL);
	row.addText(HtmlUtil.escape(userProfileHistory.getEmailAddress()), rowURL);
	row.addText(HtmlUtil.escape(userProfileHistory.getLegalEntityName()), rowURL);

	for (Map<String, String> fields : fieldsMap.values()) {
		String dataType = fields.get(FieldConstants.DATA_TYPE);
		String name = fields.get(FieldConstants.NAME);

		String value = null;

		if (fieldsModel.contains(name)) {
			com.liferay.portlet.dynamicdatamapping.storage.Field field = fieldsModel.get(name);

			value = HtmlUtil.escape(field.getRenderedValue(themeDisplay.getLocale()));
		}
		else {
			value = StringPool.BLANK;
		}

		row.addText(value, rowURL);
	}

	row.addText(longDateFormatDateTime.format(ddlRecord.getModifiedDate()), rowURL);

	// Action

	row.addJSP("/admin/training_survey_result_action.jsp", application, request, response);

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="surveys"
/>

<c:if test="<%= total > 0 %>">
	<portlet:resourceURL id="exportSurveyResults" var="exportSurveyResultsURL">
		<portlet:param name="mvcPath" value="/admin/training_survey_results.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="ddlRecordSetId" value="<%= String.valueOf(ddlRecordSetId) %>" />
	</portlet:resourceURL>

	<div>
		<aui:button onClick="<%= exportSurveyResultsURL.toString() %>" value="export-survey-results" />
	</div>

	<br />

	<div>
		<%= LanguageUtil.format(pageContext, "x-participant-forms-completed", trainingEvent.getCertificateCountDisplayHTML()) %>
	</div>
</c:if>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />