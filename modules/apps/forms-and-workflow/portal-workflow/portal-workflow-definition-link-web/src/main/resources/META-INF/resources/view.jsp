<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-util:include page="/search_bar.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/management_bar.jsp" servletContext="<%= application %>" />

<div class="container-fluid-1280 workflow-definition-link-container" id="<portlet:namespace />Container">
	<liferay-ui:search-container
		id="searchContainer"
		searchContainer="<%= workflowDefinitionLinkDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.workflow.definition.link.web.internal.search.WorkflowDefinitionLinkSearchEntry"
			modelVar="workflowDefinitionLinkSearchEntry"
		>
			<liferay-ui:search-container-row-parameter
				name="workflowDefinitionLinkSearchEntry"
				value="<%= workflowDefinitionLinkSearchEntry %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="resource"
				value="<%= workflowDefinitionLinkSearchEntry.getResource() %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="workflow"
				value="<%= workflowDefinitionLinkSearchEntry.getWorkflowDefinitionLabel() %>"
			/>

			<liferay-ui:search-container-column-jsp
				path="/workflow_definition_link_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>