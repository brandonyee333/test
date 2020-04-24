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