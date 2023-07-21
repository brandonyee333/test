<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WorkflowDefinition workflowDefinition = (WorkflowDefinition)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/edit_workflow_definition.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
		<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message='<%= LanguageUtil.format(request, "add-new-x", "file") %>'
		url="<%= editURL %>"
	/>

	<c:if test='<%= DeployManagerUtil.isDeployed("kaleo-designer-portlet") %>'>

		<%
		String taglibOnClick = "javascript:Liferay.Util.getOpener()." + renderResponse.getNamespace() + "openKaleoDesigner('" + HtmlUtil.escapeJS(workflowDefinition.getName()) + "', '" + workflowDefinition.getVersion() + "', '', Liferay.Util.getWindowName());";
		%>

		<liferay-ui:icon
			message="edit"
			url="<%= taglibOnClick %>"
		/>
	</c:if>

	<c:if test="<%= !workflowDefinition.isActive() %>">
		<liferay-portlet:actionURL name="restoreWorkflowDefinition" var="restoreWorkflowDefinitionURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
			<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon
			message="activate"
			url="<%= restoreWorkflowDefinitionURL %>"
		/>
	</c:if>

	<liferay-portlet:actionURL name='<%= workflowDefinition.isActive() ? "deactivateWorkflowDefinition" : "deleteWorkflowDefinition" %>' var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
		<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
	</liferay-portlet:actionURL>

	<c:choose>
		<c:when test="<%= workflowDefinition.isActive() %>">
			<liferay-ui:icon-deactivate
				url="<%= deleteURL %>"
			/>
		</c:when>
		<c:otherwise>
			<liferay-ui:icon-delete
				url="<%= deleteURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>