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
	<liferay-util:param name="title" value="select-runs" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-modal-toolbar">
	<testray:table-toolbar
		title="select-runs"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/runs/index_filter.jspf" %>
	</testray:table-toolbar>
</div>

<c:choose>
	<c:when test="${param.type == 'cases'}">
		<portlet:renderURL var="selectTestrayCasesURL">
			<portlet:param name="controller" value="cases" />
			<portlet:param name="action" value="select" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "select", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<c:set value="${selectTestrayCasesURL}" var="formAction" />
	</c:when>
	<c:when test="${param.type == 'suites'}">
		<portlet:renderURL var="selectTestraySuiteURL">
			<portlet:param name="controller" value="suites" />
			<portlet:param name="action" value="select" />
		</portlet:renderURL>

		<c:set value="${selectTestraySuiteURL}" var="formAction" />
	</c:when>
</c:choose>

<portlet:actionURL var="importResultsURL">
	<portlet:param name="controller" value="case_results" />
	<portlet:param name="action" value="importResults" />
	<portlet:param name="redirect" value="${portletURL}" />
</portlet:actionURL>

<aui:form action="${importResultsURL}" enctype="multipart/form-data" method="post" name="uploadCucumberFm">
	<aui:input name="testrayRunIds" type="hidden" value="" />
	<aui:input name="type" type="hidden" value="${TestrayAutomationConstants.EXTERNAL_REFERENCE_TYPE_CUCUMBER}" />

	<aui:input autocomplete="off" cssClass="hide" label="" multiple="${true}" name="results" onChange="this.form.submit()" title="upload-cucumber-results" type="file" value="" />
</aui:form>

<aui:form action="${formAction}" method="post" name="fm2" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="${redirect}" />

	<aui:input name="testrayRunIds" type="hidden" value="" />
	<aui:input name="testrayProjectId" type="hidden" value="${testrayProjectId}" />

	<aui:button-row cssClass="testray-modal-footer">
		<aui:button type="submit" value="next" />

		<aui:button href="${redirect}" value="cancel" />
	</aui:button-row>

	<div class="spacing-footer spacing-toolbar testray-modal-content">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-runs"
			id="runsSearchContainer"
			iteratorURL="${portletURL}"
			orderByCol="${orderByCol}"
			orderByType="${orderByType}"
			rowChecker="${rowChecker}"
			total="${testrayRunsCount}"
		>
			<liferay-ui:search-container-results
				results="${testrayRunComposites}"
			/>

			<liferay-ui:search-container-row
				className="java.lang.Object"
				escapedModel="${true}"
				keyProperty="testrayRunId"
				modelVar="testrayRunComposite"
			>
				<portlet:renderURL var="viewTestrayCaseResultsURL">
					<portlet:param name="controller" value="case_results" />
					<portlet:param name="action" value="index" />
					<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
					<portlet:param name="testrayCaseTypeId" value="${param.testrayCaseTypeId}" />
					<portlet:param name="testrayRunId" value="${testrayRunComposite.testrayRunId}" />
					<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

					<testray:filter-preference
						value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayProjectId)}'
					/>
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="table-column-main"
					href="${viewTestrayCaseResultsURL}"
					name="run"
				>
					<fmt:formatNumber
						minIntegerDigits="2"
						value="${testrayRunComposite.number}"
					/>
				</liferay-ui:search-container-column-text>

				<c:forEach items="${testrayRunComposite.allTestrayFactors}" var="testrayFactor">
					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultsURL}"
						name="${testrayFactor.testrayFactorCategoryName}"
						value="${testrayFactor.testrayFactorOptionName}"
					/>
				</c:forEach>

				<c:if test="${testrayRunComposite.testrayFactorsCount == 0}">
					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultsURL}"
						property="name"
					/>
				</c:if>

				<c:forEach items="${TestrayCaseResultConstants.STATUS_LABELS}" var="statusLabel">
					<c:if test="${testrayRunsIndexColumns.contains(statusLabel)}">
						<liferay-ui:search-container-column-text
							href="${viewTestrayCaseResultsURL}"
							name="${statusLabel}"
							value="${testrayRunComposite.testrayCaseResultReporter.getTestrayCaseResultsCount(statusLabel)}"
						/>
					</c:if>
				</c:forEach>

				<c:if test="${testrayRunsIndexColumns.contains(TestrayRunConstants.COLUMN_LABEL_TOTAL)}">
					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultsURL}"
						name="${TestrayRunConstants.COLUMN_LABEL_TOTAL}"
						value="${testrayRunComposite.testrayCaseResultReporter.testrayCaseResultsCount}"
					/>
				</c:if>

				<c:if test="${testrayRunsIndexColumns.contains(TestrayRunConstants.COLUMN_LABEL_METRICS)}">
					<liferay-ui:search-container-column-text
						cssClass="metrics"
						name="metrics"
					>
						<c:set value="${testrayRunComposite}" var="composite" />
						<c:set value="${testrayRunComposite.testrayRunId}" var="id" />

						<%@ include file="/alloy_mvc/jsp/testray/views/progress_bars.jspf" %>
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var A = AUI();

			var checkedRowIdsString = Liferay.Util.listCheckedExcept(document.${htmlNamespace}fm2, '${htmlNamespace}allRowIds');

			if ('${param.type}' === 'upload') {
				document.${htmlNamespace}uploadCucumberFm.${htmlNamespace}testrayRunIds.value = checkedRowIdsString;

				var uploadFileInput = document.getElementById('results');

				if (uploadFileInput) {
					uploadFileInput.click();
				}
			}
			else {
				document.${htmlNamespace}fm2.${htmlNamespace}testrayRunIds.value = checkedRowIdsString;

				submitForm(document.${htmlNamespace}fm2);
			}

		},
		['liferay-util-list-fields', 'testray-base']
	);
</aui:script>