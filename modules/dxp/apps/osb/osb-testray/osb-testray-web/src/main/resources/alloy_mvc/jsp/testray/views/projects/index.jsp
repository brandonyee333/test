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
	<liferay-util:param name="title" value="project-directory" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="projects"
	>
		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayProjectClass, "create")}'>
			<portlet:renderURL var="createTestrayProjectURL">
				<portlet:param name="controller" value="projects" />
				<portlet:param name="action" value="create" />
			</portlet:renderURL>

			<aui:button href="${createTestrayProjectURL}" icon="icon-plus" primary="${true}" value="new-project" />
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-projects"
		id="projectsSearchContainer"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${testrayProjectsCount}"
	>
		<liferay-ui:search-container-results
			results="${testrayProjects}"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.testray.model.TestrayProject"
			escapedModel="${true}"
			keyProperty="testrayProjectId"
			modelVar="testrayProject"
		>
			<portlet:renderURL var="viewTestrayBuildsURL">
				<portlet:param name="controller" value="routines" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayProject.testrayProjectId)}'
				/>
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-column-main"
				href="${viewTestrayBuildsURL}"
				name="project"
				orderable="${true}"
				orderableProperty="name"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				href="${viewTestrayBuildsURL}"
				property="description"
			/>

			<liferay-ui:search-container-column-text
				cssClass="hide user-filter-preferences"
				value="${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, 'routines', 'index', testrayProject.testrayProjectId)}"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}projectsSearchContainer',
			menu: [
				{
					action: 'index',
					controller: 'routines',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '?${htmlNamespace}testrayProjectId={id}'
				},
				{
					action: 'edit',
					controller: 'projects',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayProject, "edit")}
				},
				{
					action: 'index',
					controller: 'components',
					iconCssClass: 'icon-cogs',
					label: '<liferay-ui:message key="manage-components" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="components" />'
					},
					urlTemplate: '?${htmlNamespace}testrayProjectId={id}',
					visible: ${testrayPermission:containsClassAction(themeDisplay, testrayComponentClass, "index")}
				},
				{
					action: 'index',
					controller: 'teams',
					iconCssClass: 'icon-group',
					label: '<liferay-ui:message key="manage-teams" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="teams" />'
					},
					urlTemplate: '?${htmlNamespace}testrayProjectId={id}',
					visible: ${testrayPermission:containsClassAction(themeDisplay, testrayTeamClass, "index")}
				},
				{
					action: 'index',
					controller: 'product_versions',
					iconCssClass: 'icon-cog',
					label: '<liferay-ui:message key="manage-product-versions" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="product-versions" />'
					},
					urlTemplate: '?${htmlNamespace}testrayProjectId={id}',
					visible: ${testrayPermission:containsClassAction(themeDisplay, testrayProductVersionClass, "index")}
				},
				{
					action: 'delete',
					controller: 'projects',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayProject, "delete")}
				},
				{
					action: 'selectExport',
					controller: 'cases',
					iconCssClass: 'icon-print',
					label: '<liferay-ui:message key="export-cases" />',
					urlTemplate: '/selectExport?${htmlNamespace}testrayProjectId={id}&redirect=${portletURL}'
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>