<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/export/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
long liveGroupId = ParamUtil.getLong(request, "liveGroupId");
boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
String displayStyle = ParamUtil.getString(request, "displayStyle");

List<ExportImportConfiguration> exportImportConfigurations = ExportImportConfigurationLocalServiceUtil.getExportImportConfigurations(liveGroupId, ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT);
%>

<liferay-frontend:add-menu>

	<%
	for (ExportImportConfiguration exportImportConfiguration : exportImportConfigurations) {
		Map<String, Serializable> settingsMap = exportImportConfiguration.getSettingsMap();
	%>

		<portlet:renderURL copyCurrentRenderParameters="<%= false %>" var="addNewExportProcessURL">
			<portlet:param name="mvcPath" value="/export/new_export/export_layouts.jsp" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EXPORT %>" />
			<portlet:param name="exportImportConfigurationId" value="<%= String.valueOf(exportImportConfiguration.getExportImportConfigurationId()) %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
			<portlet:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
			<portlet:param name="privateLayout" value='<%= MapUtil.getString(settingsMap, "privateLayout") %>' />
			<portlet:param name="displayStyle" value="<%= displayStyle %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item
			title="<%= exportImportConfiguration.getName() %>"
			url="<%= addNewExportProcessURL %>"
		/>

	<%
	}
	%>

	<liferay-portlet:renderURL copyCurrentRenderParameters="<%= false %>" var="addNewCustomExportProcessURL">
		<portlet:param name="mvcPath" value="/export/new_export/export_layouts.jsp" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EXPORT %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		<portlet:param name="liveGroupId" value="<%= String.valueOf(liveGroupId) %>" />
		<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
		<portlet:param name="displayStyle" value="<%= displayStyle %>" />
	</liferay-portlet:renderURL>

	<liferay-frontend:add-menu-item
		title='<%= LanguageUtil.get(request, "custom-export") %>'
		url="<%= addNewCustomExportProcessURL %>"
	/>
</liferay-frontend:add-menu>