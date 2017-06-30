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

long ddlRecordId = ParamUtil.getLong(request, "ddlRecordId");

DDLRecord ddlRecord = DDLRecordLocalServiceUtil.getRecord(ddlRecordId);

String version = ParamUtil.getString(request, "version", ddlRecord.getVersion());

DDLRecordVersion ddlRecordVersion = ddlRecord.getRecordVersion(version);
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="surveys"
/>

<aui:model-context bean="<%= ddlRecordVersion %>" model="<%= DDLRecordVersion.class %>" />

<aui:workflow-status model="<%= DDLRecord.class %>" status="<%= ddlRecordVersion.getStatus() %>" version="<%= ddlRecordVersion.getVersion() %>" />

<aui:fieldset>

	<%
	long ddmStructureId = ParamUtil.getLong(request, "ddmStructureId");

	DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(ddmStructureId);

	Fields fields = null;

	if (ddlRecordVersion != null) {
		fields = StorageEngineUtil.getFields(ddlRecordVersion.getDDMStorageId());
	}
	%>

	<%= DDMXSDUtil.getHTML(pageContext, ddmStructure.getXsd(), fields, StringPool.BLANK, true, locale) %>

	<aui:button-row>
		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:fieldset>