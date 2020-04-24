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