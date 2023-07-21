<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WorkflowDefinitionLinkSearchEntry workflowDefinitionLinkSearchEntry = (WorkflowDefinitionLinkSearchEntry)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/edit_workflow_definition_link.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="className" value="<%= workflowDefinitionLinkSearchEntry.getClassName() %>" />
		<portlet:param name="resource" value="<%= workflowDefinitionLinkSearchEntry.getResource() %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>
</liferay-ui:icon-menu>