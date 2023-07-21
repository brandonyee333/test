<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<portlet:renderURL var="viewDefinitionsURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
	<portlet:param name="tabs1" value="workflow-definitions" />
</portlet:renderURL>

<portlet:renderURL var="addWorkflowDefinitionURL">
	<portlet:param name="mvcPath" value="/edit_workflow_definition.jsp" />
	<portlet:param name="tabs1" value="workflow-definitions" />
	<portlet:param name="redirect" value="<%= viewDefinitionsURL %>" />
	<portlet:param name="backURL" value="<%= viewDefinitionsURL %>" />
</portlet:renderURL>

<%
List<AddMenuItem> addMenuItems = new ArrayList<>();

addMenuItems.add(new AddMenuItem(HtmlUtil.escape(LanguageUtil.get(request, "upload-definition")), addWorkflowDefinitionURL.toString()));
%>

<c:if test='<%= DeployManagerUtil.isDeployed("kaleo-designer-portlet") %>'>

	<%
	String taglibHREF = "javascript:Liferay.Util.getOpener()." + renderResponse.getNamespace() + "openKaleoDesigner('', '0', '', Liferay.Util.getWindowName());";

	addMenuItems.add(new AddMenuItem(LanguageUtil.format(request, "add-new-x", "definition"), taglibHREF));
	%>

</c:if>

<c:if test="<%= !addMenuItems.isEmpty() && workflowDefinitionDisplayContext.canPublishWorkflowDefinition() %>">
	<liferay-frontend:add-menu
		addMenuItems="<%= addMenuItems %>"
	/>
</c:if>

<c:if test='<%= DeployManagerUtil.isDeployed("kaleo-designer-portlet") %>'>
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />openKaleoDesigner',
			function(workflowDefinitionName, workflowDefinitionVersion, saveCallback, openerWindowName) {
				Liferay.Util.openKaleoDesignerPortlet(
					{
						availablePropertyModels: 'Liferay.KaleoDesigner.AVAILABLE_PROPERTY_MODELS.KALEO_FORMS_EDIT',
						name: workflowDefinitionName,
						openerWindowName: openerWindowName,
						portletResourceNamespace: '<%= renderResponse.getNamespace() %>',
						saveCallback: saveCallback,
						version: workflowDefinitionVersion,
						versionLabel: '<liferay-ui:message key="version" />'
					}
				);
			},
			['aui-base']
		);
	</aui:script>
</c:if>