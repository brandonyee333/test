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
String backURL = ParamUtil.getString(request, "backURL");

long ddlRecordId = ParamUtil.getLong(request, "ddlRecordId");

DDLRecord ddlRecord = DDLRecordLocalServiceUtil.getRecord(ddlRecordId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/training_survey_result_history.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("ddlRecordId", String.valueOf(ddlRecord.getRecordId()));

request.setAttribute("view.jsp-portletURL", portletURL);

SearchContainer searchContainer = new SearchContainer(renderRequest, portletURL, new ArrayList(), null);

List headerNames = searchContainer.getHeaderNames();

headerNames.add("id");
headerNames.add("version");
headerNames.add("status");
headerNames.add("author");
headerNames.add(StringPool.BLANK);

int total = DDLRecordLocalServiceUtil.getRecordVersionsCount(ddlRecord.getRecordId());

searchContainer.setTotal(total);

List<DDLRecordVersion> results = DDLRecordLocalServiceUtil.getRecordVersions(ddlRecord.getRecordId(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	DDLRecordVersion ddlRecordVersion = results.get(i);

	ddlRecordVersion = ddlRecordVersion.toEscapedModel();

	ResultRow row = new ResultRow(ddlRecordVersion, ddlRecordVersion.getRecordVersionId(), i);

	DDMStorageLink ddmStorageLink = DDMStorageLinkLocalServiceUtil.getClassStorageLink(ddlRecordVersion.getDDMStorageId());

	PortletURL rowURL = renderResponse.createRenderURL();

	rowURL.setParameter("mvcPath", "/admin/training_survey_result.jsp");
	rowURL.setParameter("redirect", currentURL);
	rowURL.setParameter("ddlRecordId", String.valueOf(ddlRecordVersion.getRecordId()));
	rowURL.setParameter("ddmStructureId", String.valueOf(ddmStorageLink.getStructureId()));
	rowURL.setParameter("version", String.valueOf(ddlRecordVersion.getVersion()));

	// Columns

	row.addText(String.valueOf(ddlRecordVersion.getRecordVersionId()), rowURL);
	row.addText(ddlRecordVersion.getVersion(), rowURL);
	row.addText(WorkflowConstants.toLabel(ddlRecordVersion.getStatus()), rowURL);
	row.addText(PortalUtil.getUserName(ddlRecordVersion), rowURL);

	// Action

	row.addJSP("/admin/training_survey_result_version_action.jsp", application, request, response);

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="surveys"
/>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />