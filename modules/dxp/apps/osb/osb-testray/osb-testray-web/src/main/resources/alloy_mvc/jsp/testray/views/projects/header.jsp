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

<testray:header
	breadcrumbComposites="${testrayBreadcrumbComposites}"
	title="${testrayProject.name}"
>
	<testray:dropdown
		cssClass="manage-page-dropdown"
		icon="ellipsis-vertical"
		label="manage-project"
	>
		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayProject, "edit")}'>
			<portlet:renderURL var="editTestrayProjectURL">
				<portlet:param name="controller" value="projects" />
				<portlet:param name="action" value="edit" />
				<portlet:param name="id" value="${testrayProjectId}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:renderURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="edit"
				label="edit-project"
				url="${editTestrayProjectURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayComponentClass, "index")}'>
			<portlet:renderURL var="viewTestrayComponentsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="components" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("manage-components")}' var="viewTestrayComponentsURLTitle" />

			<c:set value="Liferay.Testray.openWindow('${viewTestrayComponentsURL}', '${viewTestrayComponentsURLTitle}', 1000);" var="viewTestrayComponentsURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="cogs"
				label="manage-components"
				onClick="${viewTestrayComponentsURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayTeamClass, "index")}'>
			<portlet:renderURL var="viewTestrayTeamsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="teams" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("manage-teams")}' var="viewTestrayTeamsURLTitle" />

			<c:set value="Liferay.Testray.openWindow('${viewTestrayTeamsURL}', '${viewTestrayTeamsURLTitle}', 1000);" var="viewTestrayTeamsURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="group"
				label="manage-teams"
				onClick="${viewTestrayTeamsURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayProductVersionClass, "index")}'>
			<portlet:renderURL var="viewTestrayProductVersionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="product_versions" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("manage-product-versions")}' var="viewTestrayProductVersionsURLTitle" />

			<c:set value="Liferay.Testray.openWindow('${viewTestrayProductVersionsURL}', '${viewTestrayProductVersionsURLTitle}', 1000);" var="viewTestrayProductVersionsURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="cog"
				label="manage-product-versions"
				onClick="${viewTestrayProductVersionsURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayProject, "delete")}'>
			<portlet:renderURL var="viewTestrayProjectsURL">
				<portlet:param name="controller" value="projects" />
				<portlet:param name="action" value="index" />
			</portlet:renderURL>

			<portlet:actionURL var="deleteTestrayProjectURL">
				<portlet:param name="controller" value="projects" />
				<portlet:param name="action" value="delete" />
				<portlet:param name="id" value="${testrayProjectId}" />
				<portlet:param name="redirect" value="${viewTestrayProjectsURL}" />
			</portlet:actionURL>

			<c:set value="Liferay.Testray.confirmDelete('${deleteTestrayProjectURL}')" var="deleteTestrayProjectURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="trash"
				label="delete-project"
				onClick="${deleteTestrayProjectURL}"
			/>
		</c:if>

		<portlet:renderURL var="selectExportTestrayCasesURL">
			<portlet:param name="controller" value="cases" />
			<portlet:param name="action" value="selectExport" />
			<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
			<portlet:param name="redirect" value="${portletURL}" />
		</portlet:renderURL>

		<testray:dropdown-item
			cssClass="testray-menu-item"
			icon="print"
			label="export-cases"
			url="${selectExportTestrayCasesURL}"
		/>
	</testray:dropdown>

	<portlet:renderURL var="viewTestrayProjectURL">
		<portlet:param name="controller" value="projects" />
		<portlet:param name="action" value="view" />
		<portlet:param name="id" value="${testrayProjectId}" />
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayRoutinesURL">
		<portlet:param name="controller" value="routines" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayProjectId)}'
		/>
	</portlet:renderURL>

	<portlet:renderURL var="viewTestraySuitesURL">
		<portlet:param name="controller" value="suites" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayCasesURL">
		<portlet:param name="controller" value="cases" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "index", testrayProjectId)}'
		/>
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayRequirementsURL">
		<portlet:param name="controller" value="requirements" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
	</portlet:renderURL>

	<c:set value="${viewTestrayProjectURL},${viewTestrayRoutinesURL},${viewTestraySuitesURL},${viewTestrayCasesURL},${viewTestrayRequirementsURL}" var="tabUrls" />

	<liferay-ui:tabs
		cssClass="testray-nav-tabs"
		names="overview,routines,suites,cases,requirements"
		urls="${fn:split(tabUrls, StringPool.COMMA)}"
		value="${param.tab}"
	/>
</testray:header>