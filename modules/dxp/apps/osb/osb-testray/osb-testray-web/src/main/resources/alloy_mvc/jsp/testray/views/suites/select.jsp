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
	<liferay-util:param name="title" value="select-suites" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:renderURL var="selectTestraySuitesURL">
	<portlet:param name="controller" value="suites" />
	<portlet:param name="action" value="select" />
	<portlet:param name="testrayBuildId" value="${testrayBuildId}" />
	<portlet:param name="testrayRunId" value="${param.testrayRunId}" />
</portlet:renderURL>

<div class="${popup ? "testray-modal-toolbar" : StringPool.BLANK}">
	<aui:form action="${selectTestraySuitesURL}" method="get" name="fm1" onSubmit="event.preventDefault(); ${htmlNamespace}searchTestraySuites();">
		<aui:fieldset>
			<aui:input autoFocus="${true}" inlineField="${true}" label="" name="keywords" size="30" title="search-suites" type="text" />

			<aui:button type="submit" value="search" />
		</aui:fieldset>
	</aui:form>
</div>

<c:choose>
	<c:when test="${testrayBuildId >= 0}">
		<portlet:actionURL var="updateTestrayBuildTestrayCasesURL">
			<portlet:param name="controller" value="builds" />
			<portlet:param name="action" value="updateCases" />
		</portlet:actionURL>

		<c:set value="${updateTestrayBuildTestrayCasesURL}" var="formAction" />
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="addTestrayCaseResultsURL">
			<portlet:param name="controller" value="case_results" />
			<portlet:param name="action" value="addTestrayCaseResults" />
		</portlet:actionURL>

		<c:set value="${addTestrayCaseResultsURL}" var="formAction" />
	</c:otherwise>
</c:choose>

<aui:form action="${formAction}" method="post" name="fm2" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="${selectTestraySuitesURL}" />

	<aui:input name="addTestraySuiteIds" type="hidden" value="" />
	<aui:input name="testrayBuildId" type="hidden" value="${testrayBuildId}" />
	<aui:input name="testrayRunId" type="hidden" value="${param.testrayRunId}" />
	<aui:input name="testrayRunIds" type="hidden" value="${param.testrayRunIds}" />

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="select-suites" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>

	<div class="${popup ? "spacing-footer spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-suites"
			iteratorURL="${alloySearchResult.portletURL}"
			orderByCol="${orderByCol}"
			orderByType="${orderByType}"
			rowChecker="${rowChecker}"
			total="${alloySearchResult.size}"
		>
			<liferay-ui:search-container-results
				results="${alloySearchResult.baseModels}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.testray.model.TestraySuite"
				escapedModel="${true}"
				keyProperty="testraySuiteId"
				modelVar="testraySuite"
			>
				<portlet:renderURL var="selectTestrayCasesURL">
					<portlet:param name="controller" value="cases" />
					<portlet:param name="action" value="select" />
					<portlet:param name="scopeBySuite" value="${true}" />
					<portlet:param name="testrayBuildId" value="${testrayBuildId}" />
					<portlet:param name="testrayRunId" value="${param.testrayRunId}" />
					<portlet:param name="testraySuiteId" value="${testraySuite.testraySuiteId}" />

					<testray:filter-preference
						value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "select", testrayProjectId)}'
					/>
				</portlet:renderURL>

				<portlet:actionURL var="refreshTestraySuiteURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="controller" value="suites" />
					<portlet:param name="action" value="refreshCases" />
					<portlet:param name="redirect" value="${selectTestrayCasesURL}" />
					<portlet:param name="id" value="${testraySuite.testraySuiteId}" />
				</portlet:actionURL>

				<liferay-ui:search-container-column-text
					href="${refreshTestraySuiteURL}"
					orderable="${true}"
					orderableProperty="name_sortable"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					href="${refreshTestraySuiteURL}"
					property="description"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}searchTestraySuites() {
		var keywords = document.${htmlNamespace}fm1.${htmlNamespace}keywords.value;

		window.location.href = '${selectTestraySuitesURL}&${htmlNamespace}keywords=' + escape(keywords);
	}

	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var testraySuiteIds = Liferay.Util.listCheckedExcept(document.${htmlNamespace}fm2, '${htmlNamespace}allRowIds');

			if (${empty param.redirect}) {
				document.${htmlNamespace}fm2.${htmlNamespace}addTestraySuiteIds.value = testraySuiteIds;

				submitForm(document.${htmlNamespace}fm2);

				Liferay.Testray.closeWindow();
			}
			else {
				window.location.href = '${param.redirect}&${htmlNamespace}testraySuiteIds=' + testraySuiteIds;
			}
		},
		['liferay-util-list-fields', 'testray-base']
	);
</aui:script>