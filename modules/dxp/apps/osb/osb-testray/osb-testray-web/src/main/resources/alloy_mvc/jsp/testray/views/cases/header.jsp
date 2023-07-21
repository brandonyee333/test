<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<testray:header
	breadcrumbComposites="${testrayBreadcrumbComposites}"
	title="${testrayCase.name}"
>
	<testray:dropdown
		cssClass="manage-page-dropdown"
		icon="ellipsis-vertical"
		label="manage-case"
	>
		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayCase, "edit")}'>
			<portlet:renderURL var="editTestrayCaseURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="edit" />
				<portlet:param name="id" value="${testrayCase.testrayCaseId}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:renderURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="edit"
				label="edit-case"
				url="${editTestrayCaseURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayCase, "delete")}'>
			<portlet:renderURL var="viewTestrayCasesURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<portlet:actionURL var="deleteTestrayCaseURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="delete" />
				<portlet:param name="id" value="${testrayCase.testrayCaseId}" />
				<portlet:param name="redirect" value="${viewTestrayCasesURL}" />
			</portlet:actionURL>

			<c:set value="javascript:Liferay.Testray.confirmDelete('${deleteTestrayCaseURL}')" var="deleteTestrayCaseURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="trash"
				label="delete-case"
				onClick="${deleteTestrayCaseURL}"
			/>
		</c:if>
	</testray:dropdown>

	<portlet:renderURL var="viewTestrayCaseDetailsURL">
		<portlet:param name="controller" value="cases" />
		<portlet:param name="action" value="view" />
		<portlet:param name="id" value="${param.id}" />
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayRequirementsURL">
		<portlet:param name="controller" value="cases" />
		<portlet:param name="action" value="requirements" />
		<portlet:param name="id" value="${param.id}" />
	</portlet:renderURL>

	<c:set value="${viewTestrayCaseDetailsURL},${viewTestrayRequirementsURL}" var="tabUrls" />

	<liferay-ui:tabs
		cssClass="testray-nav-tabs"
		names="case-details,requirements"
		urls="${fn:split(tabUrls, StringPool.COMMA)}"
		value="${param.tabs1}"
	/>
</testray:header>