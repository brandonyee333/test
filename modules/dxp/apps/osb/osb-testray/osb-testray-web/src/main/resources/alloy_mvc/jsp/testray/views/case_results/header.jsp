<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<testray:header
	breadcrumbComposites="${testrayBreadcrumbComposites}"
	title="${testrayCaseResultComposite.testrayCaseName}"
>
	<testray:dropdown
		cssClass="manage-page-dropdown"
		icon="ellipsis-vertical"
		label="manage-case-result"
	>
		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "delete")}'>
			<portlet:renderURL var="viewTestrayCaseResultsURL">
				<portlet:param name="controller" value="case_results" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayRunId" value="${testrayCaseResultComposite.testrayRunId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<portlet:actionURL var="deleteTestrayCaseResultURL">
				<portlet:param name="controller" value="case_results" />
				<portlet:param name="action" value="delete" />
				<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
				<portlet:param name="redirect" value="${viewTestrayCaseResultsURL}" />
			</portlet:actionURL>

			<c:set value="javascript:Liferay.Testray.confirmDelete('${deleteTestrayCaseResultURL}');" var="deleteTestrayCaseResultURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="trash"
				label="delete-case-result"
				onClick="${deleteTestrayCaseResultURL}"
			/>
		</c:if>
	</testray:dropdown>

	<portlet:renderURL var="viewTestrayCaseResultURL">
		<portlet:param name="controller" value="case_results" />
		<portlet:param name="action" value="view" />
		<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayCaseResultHistoryURL">
		<portlet:param name="controller" value="case_results" />
		<portlet:param name="action" value="history" />
		<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
		<portlet:param name="hideFilterPin" value="${true}" />
	</portlet:renderURL>

	<c:set value="${viewTestrayCaseResultURL},${viewTestrayCaseResultHistoryURL}" var="tabUrls" />

	<liferay-ui:tabs
		cssClass="testray-nav-tabs"
		names="result,history"
		urls="${fn:split(tabUrls, StringPool.COMMA)}"
		value="${param.tab}"
	/>
</testray:header>