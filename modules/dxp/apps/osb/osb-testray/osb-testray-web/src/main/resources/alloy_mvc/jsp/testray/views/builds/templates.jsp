<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${testrayRoutine.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<testray:table-toolbar
	title="templates"
>
	<%@ include file="/alloy_mvc/jsp/testray/views/builds/templates_filter.jspf" %>

	<%@ include file="/alloy_mvc/jsp/testray/views/builds/create_dropdown.jspf" %>
</testray:table-toolbar>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-templates"
	id="templatesSearchContainer"
	iteratorURL="${alloySearchResult.portletURL}"
	orderByCol="${orderByCol}"
	orderByType="${orderByType}"
	total="${alloySearchResult.size}"
>
	<liferay-ui:search-container-results
		results="${templateTestrayBuildComposites}"
	/>

	<liferay-ui:search-container-row
		className="java.lang.Object"
		escapedModel="${true}"
		keyProperty="testrayBuildId"
		modelVar="templateTestrayBuildComposite"
	>
		<liferay-ui:search-container-column-text
			name="status"
			translate="${true}"
			value="${templateTestrayBuildComposite.status == TestrayBuildConstants.STATUS_DEFAULT ? 'activated' : 'deactivated'}"
		/>

		<liferay-ui:search-container-column-text
			name="template-name"
			orderable="${true}"
			orderableProperty="name_sortable"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="template-tests"
			property="estimatedTestrayCaseResultsCount"
		/>

		<liferay-ui:search-container-column-text
			name="latest-build"
			value="${templateTestrayBuildComposite.latestTestrayBuild.name}"
		/>

		<liferay-ui:search-container-column-text
			name="last-used-date"
		>
			<testray:date
				value="${templateTestrayBuildComposite.latestTestrayBuild.createDate}"
			/>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}templatesSearchContainer',
			menu: [
				{
					action: 'updateStatus',
					controller: 'builds',
					iconCssClass: 'icon-off',
					label: '${templateTestrayBuildComposite.status == TestrayBuildConstants.STATUS_DEACTIVATED ? "activate" : "deactivate"}',
					method: 'POST',
					parameters: {
						status: '${templateTestrayBuildComposite.status == TestrayBuildConstants.STATUS_DEACTIVATED ? TestrayBuildConstants.STATUS_DEFAULT : TestrayBuildConstants.STATUS_DEACTIVATED}'
					},
					visible: ${testrayPermission:containsModelAction(themeDisplay, templateTestrayBuildComposite.testrayBuild, "updateStatus")}
				},
				{
					action: 'delete',
					controller: 'builds',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, templateTestrayBuildComposite.testrayBuild, "delete") && (templateTestrayBuildComposite.status == TestrayBuildConstants.STATUS_DEACTIVATED)}
				}
			],
			redirect: '${alloySearchResult.portletURL}'
		}
	).render();
</aui:script>