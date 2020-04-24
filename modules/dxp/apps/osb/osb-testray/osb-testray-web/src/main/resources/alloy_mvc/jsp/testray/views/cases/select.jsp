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
	<liferay-util:param name="title" value="select-cases" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="${popup ? "testray-modal-toolbar" : StringPool.BLANK}">
	<testray:table-toolbar
		title="cases"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/cases/index_filter.jspf" %>
	</testray:table-toolbar>
</div>

<c:choose>
	<c:when test="${not empty param.testrayBuildId && param.testrayBuildId >= 0}">
		<portlet:actionURL var="updateTestrayBuildTestrayCasesURL">
			<portlet:param name="controller" value="builds" />
			<portlet:param name="action" value="updateCases" />
			<portlet:param name="id" value="${param.testrayBuildId}" />
		</portlet:actionURL>

		<c:set value="${updateTestrayBuildTestrayCasesURL}" var="formAction" />
	</c:when>
	<c:when test="${not empty param.testrayRequirementId && param.testrayRequirementId > 0}">
		<portlet:actionURL var="updateTestrayRequirementTestrayCasesURL">
			<portlet:param name="controller" value="requirements" />
			<portlet:param name="action" value="updateCases" />
			<portlet:param name="id" value="${param.testrayRequirementId}" />
		</portlet:actionURL>

		<c:set value="${updateTestrayRequirementTestrayCasesURL}" var="formAction" />
	</c:when>
	<c:when test="${not empty param.testraySuiteId && param.testrayRunId > 0}">
		<portlet:actionURL var="updateTestraySuiteTestrayCasesURL">
			<portlet:param name="controller" value="suites" />
			<portlet:param name="action" value="updateCases" />
			<portlet:param name="id" value="${param.testraySuiteId}" />
		</portlet:actionURL>

		<c:set value="${updateTestraySuiteTestrayCasesURL}" var="formAction" />
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
	<aui:input name="redirect" type="hidden" value="" />

	<aui:input name="addTestrayCaseIds" type="hidden" value="" />
	<aui:input name="scopeByProject" type="hidden" value="${scopeByProject}" />
	<aui:input name="scopeBySuite" type="hidden" value="${scopeBySuite}" />
	<aui:input name="testrayRunId" type="hidden" value="${param.testrayRunId}" />
	<aui:input name="testrayRunIds" type="hidden" value="${param.testrayRunIds}" />

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="select-cases" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>

	<div class="${popup ? "spacing-footer spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-cases"
			iteratorURL="${portletURL}"
			orderByCol="${orderByCol}"
			orderByType="${orderByType}"
			rowChecker="${rowChecker}"
			total="${alloySearchResult.size}"
		>
			<liferay-ui:search-container-results
				results="${alloySearchResult.baseModels}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.testray.model.TestrayCase"
				escapedModel="${true}"
				keyProperty="testrayCaseId"
				modelVar="testrayCase"
			>
				<liferay-ui:search-container-column-text
					orderable="${true}"
					orderableProperty="testrayCasePriority_sortable"
					property="priority"
				/>

				<c:set value="<%= TestrayComponentLocalServiceUtil.fetchTestrayComponent(testrayCase.getTestrayComponentId()) %>" var="testrayComponent" />

				<liferay-ui:search-container-column-text
					name="component"
					orderable="${true}"
					orderableProperty="testrayComponentName_sortable"
					value="${testrayComponent.name}"
				/>

				<liferay-ui:search-container-column-text
					orderable="${true}"
					orderableProperty="name_sortable"
					property="name"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}getTestrayCases(testrayCaseIds) {
		var testrayCases = [];

		var testrayCasesJSONArray = ${testrayCasesJSONArray};

		for (var i = 0; i < testrayCasesJSONArray.length; i++) {
			var testrayCase = testrayCasesJSONArray[i];

			if (testrayCaseIds.indexOf(testrayCase.testrayCaseId.toString()) >= 0) {
				testrayCases.push(testrayCase);
			}
		}

		return testrayCases;
	}

	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var A = AUI();

			var checkedRowIdsString = Liferay.Util.listCheckedExcept(document.${htmlNamespace}fm2, '${htmlNamespace}allRowIds');

			var opener = Liferay.Util.getOpener();

			if (opener && opener.${htmlNamespace}buildSearchContainer) {
				var testrayCases = ${htmlNamespace}getTestrayCases(checkedRowIdsString.split(','));

				opener.${htmlNamespace}buildSearchContainer(testrayCases);

				var alertMessage = A.Lang.sub(
					'<liferay-ui:message key="x-cases-were-added-successfully" />',
					[
						testrayCases.length
					]
				);

				opener.Liferay.Testray.addAlert(
					{
						containerId: '#${htmlNamespace}testrayAlertContainer',
						message: alertMessage,
						type: 'success'
					}
				);
			}
			else {
				document.${htmlNamespace}fm2.${htmlNamespace}addTestrayCaseIds.value = checkedRowIdsString;

				submitForm(document.${htmlNamespace}fm2);
			}

			Liferay.Testray.closeWindow();
		},
		['liferay-util-list-fields', 'testray-base']
	);
</aui:script>