<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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