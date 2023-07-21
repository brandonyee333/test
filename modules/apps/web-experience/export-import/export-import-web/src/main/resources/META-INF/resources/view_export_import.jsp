<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long backgroundTaskId = ParamUtil.getLong(request, "backgroundTaskId");
%>

<div class="container-fluid-1280" id="<portlet:namespace />exportImportProcessContainer">
	<liferay-util:include page="/export_import_process.jsp" servletContext="<%= application %>" />
</div>

<aui:script use="liferay-export-import">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="exportImport" var="exportImportProcessURL">
		<portlet:param name="<%= Constants.CMD %>" value="export_import" />
		<portlet:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTaskId) %>" />
	</liferay-portlet:resourceURL>

	new Liferay.ExportImport(
		{
			incompleteProcessMessageNode: '#<portlet:namespace />incompleteProcessMessage',
			locale: '<%= locale.toLanguageTag() %>',
			namespace: '<portlet:namespace />',
			processesNode: '#exportImportProcessContainer',
			processesResourceURL: '<%= HtmlUtil.escapeJS(exportImportProcessURL) %>',
			timeZoneOffset: <%= timeZoneOffset %>
		}
	);
</aui:script>