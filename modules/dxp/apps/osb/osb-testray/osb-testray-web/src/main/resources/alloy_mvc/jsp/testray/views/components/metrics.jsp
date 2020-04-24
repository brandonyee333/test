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

<liferay-util:include page="/alloy_mvc/jsp/testray/views/builds/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tab" value="components" />
</liferay-util:include>

<div class="testray-card">
	<testray:table-toolbar
		title="components"
	>
		<testray:filter>
			<aui:input name="tabs1" type="hidden" value="${tabs1}" />
			<aui:input name="testrayBuildId" type="hidden" value="${testrayBuildComposite.testrayBuildId}" />

			<testray:filter-element
				items="${TestrayCaseConstants.PRIORITIES}"
				label="priority"
				name="priorities"
				selectedLabel="priority-x"
				selectedValue="${testrayCasePriorities}"
				type="checkboxes"
			/>

			<testray:filter-element
				items="${testrayCaseTypes}"
				label="case-type"
				modelClassName="com.liferay.osb.testray.model.TestrayCaseType"
				name="testrayCaseTypeId"
				selectedLabel="case-type-x"
				type="select-model"
			/>

			<testray:filter-element
				items="${testrayTeams}"
				label="team"
				modelClassName="com.liferay.osb.testray.model.TestrayTeam"
				name="testrayTeamId"
				selectedLabel="team-x"
				type="select-model"
			/>

			<testray:filter-element
				items="${testrayRuns}"
				label="run"
				modelClassName="com.liferay.osb.testray.model.TestrayRun"
				name="testrayRunId"
				optionLabelProperty="number"
				selectedLabel="run-x"
				showEmptyOption="${true}"
				type="select-model"
			/>
		</testray:filter>

		<testray:configure-columns
			columnLabels="${TestrayComponentConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayComponentConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayComponentsMetricsColumns"
		/>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-components"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${testrayComponentsCount}"
	>
		<liferay-ui:search-container-results
			results="${testrayComponentComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayComponentId"
			modelVar="testrayComponentComposite"
		>
			<portlet:renderURL var="viewTestrayCaseResultsURL">
				<portlet:param name="controller" value="case_results" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
				<portlet:param name="testrayCaseTypeId" value="${param.testrayCaseTypeId}" />
				<portlet:param name="testrayComponentId" value="${testrayComponentComposite.testrayComponentId}" />
				<portlet:param name="testrayRunId" value="${testrayRunComposite.testrayRunId}" />
				<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="${viewTestrayCaseResultsURL}"
				name="component"
				orderable="${true}"
				orderableProperty="name"
				property="name"
			/>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayCaseResultConstants.LABEL_UNTESTED)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="untested"
					orderable="${true}"
					orderableProperty="testrayCaseResultsUntestedCount"
					property="testrayCaseResultsUntestedCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayCaseResultConstants.LABEL_IN_PROGRESS)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="in-progress"
					orderable="${true}"
					orderableProperty="testrayCaseResultsInProgressCount"
					property="testrayCaseResultsInProgressCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayCaseResultConstants.LABEL_PASSED)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="passed"
					orderable="${true}"
					orderableProperty="testrayCaseResultsPassedCount"
					property="testrayCaseResultsPassedCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayCaseResultConstants.LABEL_FAILED)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="failed"
					orderable="${true}"
					orderableProperty="testrayCaseResultsFailedCount"
					property="testrayCaseResultsFailedCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayCaseResultConstants.LABEL_BLOCKED)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="blocked"
					orderable="${true}"
					orderableProperty="testrayCaseResultsBlockedCount"
					property="testrayCaseResultsBlockedCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayCaseResultConstants.LABEL_TEST_FIX)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="test-fix"
					orderable="${true}"
					orderableProperty="testrayCaseResultsTestFixCount"
					property="testrayCaseResultsTestFixCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayBuildConstants.COLUMN_LABEL_TOTAL)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="total"
					orderable="${true}"
					orderableProperty="testrayCaseResultsCount"
					property="testrayCaseResultsCount"
				/>
			</c:if>

			<c:if test="${testrayComponentsMetricsColumns.contains(TestrayBuildConstants.COLUMN_LABEL_METRICS)}">
				<liferay-ui:search-container-column-text
					cssClass="metrics"
					name="metrics"
					orderable="${true}"
					orderableProperty="failedRatio"
				>
					<c:set value="${testrayComponentComposite}" var="composite" />
					<c:set value="${testrayComponentComposite.testrayComponentId}" var="id" />

					<%@ include file="/alloy_mvc/jsp/testray/views/progress_bars.jspf" %>
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>