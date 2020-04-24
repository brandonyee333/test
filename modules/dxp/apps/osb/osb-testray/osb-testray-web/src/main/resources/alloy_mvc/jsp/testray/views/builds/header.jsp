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
	title="${testrayBuildComposite.name}"
>
	<testray:dropdown
		cssClass="manage-page-dropdown"
		icon="ellipsis-vertical"
		label="manage-build"
	>
		<c:if test='${(param.controller == "case_results")}'>
			<portlet:renderURL var="exportTestrayCaseResultsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="controller" value="case_results" />
				<portlet:param name="action" value="index" />
				<portlet:param name="format" value="csv" />
				<portlet:param name="end" value="-1" />
				<portlet:param name="start" value="-1" />
				<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
			</portlet:renderURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="download-alt"
				label="export-as-csv"
				url="${exportTestrayCaseResultsURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "edit")}'>
			<portlet:renderURL var="editTestrayBuildURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="edit" />
				<portlet:param name="id" value="${testrayBuildComposite.testrayBuildId}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:renderURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="edit"
				label="edit-build"
				url="${editTestrayBuildURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "updatePromoted")}'>
			<portlet:actionURL var="promoteTestrayBuildURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="updatePromoted" />
				<portlet:param name="id" value="${testrayBuildComposite.testrayBuildId}" />
				<portlet:param name="redirect" value="${portletURL}" />
				<portlet:param name="promoted" value="${!testrayBuildComposite.promoted}" />
			</portlet:actionURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="star"
				label="promote"
				url="${promoteTestrayBuildURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "updateArchived")}'>
			<portlet:actionURL var="archiveTestrayBuildURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="updateArchived" />
				<portlet:param name="id" value="${testrayBuildComposite.testrayBuildId}" />
				<portlet:param name="redirect" value="${portletURL}" />
				<portlet:param name="archived" value="${param.archived ? false : true}" />
			</portlet:actionURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="archive"
				label="${param.archived ? 'restore' : 'archive'}"
				url="${archiveTestrayBuildURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "delete")}'>
			<portlet:renderURL var="viewTestrayBuildsURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayRoutineId" value="${testrayBuildComposite.testrayRoutineId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "builds", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<portlet:actionURL var="deleteTestrayBuildURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="delete" />
				<portlet:param name="id" value="${testrayBuildComposite.testrayBuildId}" />
				<portlet:param name="redirect" value="${viewTestrayBuildsURL}" />
			</portlet:actionURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("deleting-this-build-will-delete-all-tasks-runs-and-results-associated-with-this-build.-this-action-cannot-be-undone")}' var="deleteTestrayBuildMessage" />

			<c:set value="javascript:Liferay.Testray.confirmDelete('${deleteTestrayBuildURL}', '${deleteTestrayBuildMessage}')" var="deleteTestrayBuildURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="trash"
				label="delete-build"
				onClick="${deleteTestrayBuildURL}"
			/>
		</c:if>
	</testray:dropdown>

	<portlet:renderURL var="viewTestrayCaseResultsURL">
		<portlet:param name="controller" value="case_results" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
		<portlet:param name="testrayCaseTypeId" value="${param.testrayCaseTypeId}" />
		<portlet:param name="testrayRunId" value="${param.testrayRunId}" />
		<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayBuildComposite.testrayProjectId)}'
		/>
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayCaseTypesMetricsURL">
		<portlet:param name="controller" value="case_types" />
		<portlet:param name="action" value="metrics" />
		<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
		<portlet:param name="testrayRunId" value="${param.testrayRunId}" />
		<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_types", "metrics", testrayBuildComposite.testrayProjectId)}'
		/>
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayComponentsMetricsURL">
		<portlet:param name="controller" value="components" />
		<portlet:param name="action" value="metrics" />
		<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
		<portlet:param name="testrayCaseTypeId" value="${param.testrayCaseTypeId}" />
		<portlet:param name="testrayRunId" value="${param.testrayRunId}" />
		<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "components", "metrics", testrayBuildComposite.testrayProjectId)}'
		/>
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayRunsURL">
		<portlet:param name="controller" value="runs" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
		<portlet:param name="testrayCaseTypeId" value="${param.testrayCaseTypeId}" />
		<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "runs", "index", testrayBuildComposite.testrayProjectId)}'
		/>
	</portlet:renderURL>

	<portlet:renderURL var="viewTestrayTeamsMetricsURL">
		<portlet:param name="controller" value="teams" />
		<portlet:param name="action" value="metrics" />
		<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
		<portlet:param name="testrayCaseTypeId" value="${param.testrayCaseTypeId}" />
		<portlet:param name="testrayRunId" value="${param.testrayRunId}" />

		<testray:filter-preference
			value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "teams", "metrics", testrayBuildComposite.testrayProjectId)}'
		/>
	</portlet:renderURL>

	<c:set value="${viewTestrayCaseResultsURL},${viewTestrayRunsURL},${viewTestrayTeamsMetricsURL},${viewTestrayComponentsMetricsURL},${viewTestrayCaseTypesMetricsURL}" var="tabUrls" />

	<liferay-ui:tabs
		cssClass="testray-nav-tabs"
		names="results,runs,teams,components,case-types"
		urls="${fn:split(tabUrls, StringPool.COMMA)}"
		value="${param.tab}"
	/>
</testray:header>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test='${(not empty testrayBuildComposite.testrayTaskStatusLabel) && (testrayPermission:containsClassAction(themeDisplay, testrayTaskClass, "index"))}'>
	<div class="testray-banner testray-banner-${testrayBuildComposite.testrayTaskStatusLabel}">
		<portlet:renderURL var="viewTestraySubtasksURL">
			<portlet:param name="controller" value="subtasks" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayTaskId" value="${testrayBuildComposite.testrayTaskId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "subtasks", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<div class="testray-banner-icon">
			<aui:a cssClass="status ${testrayBuildComposite.testrayTaskStatusLabel}" href="${viewTestraySubtasksURL}">
				<liferay-ui:message key="${testrayBuildComposite.testrayTaskStatusLabel}" />
			</aui:a>
		</div>

		<div class="testray-banner-content">
			<c:choose>
				<c:when test="${testrayBuildComposite.testrayTaskStatus == TestrayTaskConstants.STATUS_ABANDONED}">
					<liferay-ui:message key="this-builds-task-has-been-abandoned" />
				</c:when>
				<c:when test="${testrayBuildComposite.testrayTaskStatus == TestrayTaskConstants.STATUS_COMPLETE}">
					<liferay-ui:message key="this-build-has-been-analyzed" />
				</c:when>
				<c:when test="${testrayBuildComposite.testrayTaskStatus == TestrayTaskConstants.STATUS_IN_ANALYSIS}">
					<liferay-ui:message key="this-build-is-currently-in-analysis" />
				</c:when>
			</c:choose>
		</div>

		<aui:a cssClass="testray-banner-action" href="${viewTestraySubtasksURL}">
			<liferay-ui:message key="view-task" />
		</aui:a>
	</div>
</c:if>

<c:set value='${testrayPermission:containsClassAction(themeDisplay, testrayTaskClass, "create")}' var="userMayCreateTasks" />

<c:if test="${(testrayBuildComposite.testrayTaskStatus == 0) && userMayCreateTasks}">
	<aui:button-row cssClass="button-row-top runs">
		<div class="task-btn-container">
			<div class="btn-group">
				<portlet:renderURL var="createTestrayTaskURL">
					<portlet:param name="controller" value="tasks" />
					<portlet:param name="action" value="create" />
					<portlet:param name="redirect" value="${portletURL}" />
					<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
				</portlet:renderURL>

				<aui:button disabled="${testrayBuildComposite.archived}" onClick="${createTestrayTaskURL}" primary="${true}" value="analyze" />
			</div>
		</div>
	</aui:button-row>
</c:if>

<div class="testray-card testray-card-metadata-panel">
	<liferay-ui:panel-container
		cssClass="metadata-panel"
		extended="${false}"
		persistState="${true}"
	>
		<liferay-ui:panel
			collapsible="${true}"
			defaultState="closed"
			extended="${false}"
			id="additionInformationPanel"
			persistState="${true}"
			title="details"
		>
			<dl class="data-list dl-horizontal">
				<dt>
					<liferay-ui:message key="product-version" />
				</dt>
				<dd>
					${testrayProductVersion.name}
				</dd>
				<dt>
					<liferay-ui:message key="description" />
				</dt>
				<dd>
					${testrayBuildComposite.description}
				</dd>
				<dt>
					<liferay-ui:message key="git-hash" />
				</dt>
				<dd>
					${testrayBuildComposite.gitHashShortened}

					<a class="copy-icon-inline" data-toggle="tooltip" onClick="${htmlNamespace}copyGitHash('${testrayBuildComposite.gitHash}', title='<liferay-ui:message key="copied" />');" title='<liferay-ui:message key="click-to-copy" />'></a>
				</dd>
				<dt>
					<liferay-ui:message key="create-date" />
				</dt>
				<dd>
					<testray:date
						value="${testrayBuildComposite.createDate}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="created-by" />
				</dt>
				<dd>
					${testrayBuildComposite.userName}
				</dd>
				<dt>
					<liferay-ui:message key="all-issues-found" />
				</dt>
				<dd>
					<c:set value="${testrayCaseResultReporter.issues}" var="issues" />
					<c:set value="${true}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
				</dd>
			</dl>

			<ul class="legend-container metadata-block-counts">
				<li class="legend-item">
					<div class="legend-item-numbers">
						<span class="primary">
							<c:set value="${testrayCaseResultReporter.getTestrayCaseEstimatedDuration(false)}" var="minutesCount" />

							<%@ include file="/alloy_mvc/jsp/testray/views/format_minutes.jspf" %>
						</span>
					</div>

					<div class="legend-item-label">
						<liferay-ui:message key="total-estimated-time" />
					</div>
				</li>
				<li class="legend-item">
					<div class="legend-item-numbers">
						<span class="primary">
							<c:set value="${testrayCaseResultReporter.getTestrayCaseEstimatedDuration(true)}" var="minutesCount" />

							<%@ include file="/alloy_mvc/jsp/testray/views/format_minutes.jspf" %>
						</span>
					</div>

					<div class="legend-item-label">
						<liferay-ui:message key="remaining-estimated-time" />
					</div>
				</li>
				<li class="legend-item">
					<div class="legend-item-numbers">
						<span class="primary">
							${testrayCaseResultReporter.issuesCount}
						</span>
					</div>

					<div class="legend-item-label">
						<liferay-ui:message key="total-issues" />
					</div>
				</li>
			</ul>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</div>

<div class="testray-card">
	<aui:row>
		<aui:col md="4">
			<h2 class="testray-data-header">
				<liferay-ui:message key="total-test-cases" />
			</h2>

			<aui:row>
				<aui:col sm="6">
					<div class="graph-container testray-metrics-donut-chart" id="${htmlNamespace}testrayTotalMetricsGraph">
						<div class="placeholder"></div>
					</div>
				</aui:col>

				<aui:col sm="6">
					<div class="testray-metrics-donut-chart-legend" id="${htmlNamespace}testrayTotalMetricsGraphLegend"></div>
				</aui:col>
			</aui:row>

			<aui:script use="testray-graph">
				new Liferay.Testray.Graph(
					{
						containerId: '${htmlNamespace}testrayTotalMetricsGraph',
						controller: 'case_results',
						controllerMethod: 'metrics.json',
						dataFormat: 'totalMetrics',
						graphHeight: 180,
						legend: false,
						legendContainerId: '${htmlNamespace}testrayTotalMetricsGraphLegend',
						params: {
							testrayBuildId: ${testrayBuildComposite.testrayBuildId}
						},
						type: 'donut'
					}
				).render();
			</aui:script>
		</aui:col>

		<aui:col md="8">
			<liferay-ui:tabs
				cssClass="testray-graph-tabs"
				names="runs,teams,components"
				param="graphTab"
				refresh="${false}"
				type="dropdown"
			>
				<liferay-ui:section>
					<div class="graph-container graph-container-sm" id="${htmlNamespace}testrayRunsGraph">
						<div class="placeholder"></div>
					</div>

					<aui:script use="testray-graph">
						new Liferay.Testray.Graph(
							{
								containerId: '${htmlNamespace}testrayRunsGraph',
								controller: 'case_results',
								controllerMethod: 'runMetrics.json',
								dataFormat: 'runs',
								graphHeight: 250,
								params: {
									testrayBuildId: ${testrayBuildComposite.testrayBuildId}
								},
								type: 'bar',
								xAxisKey: 'number',
								xAxisLabel: '<liferay-ui:message key="runs" />',
								yAxisLabel: '<liferay-ui:message key="tests" />'
							}
						).render();
					</aui:script>
				</liferay-ui:section>

				<liferay-ui:section>
					<div class="graph-container graph-container-hide-x graph-container-sm" id="${htmlNamespace}testrayTeamsGraph">
						<div class="placeholder"></div>
					</div>

					<aui:script use="testray-graph">
						new Liferay.Testray.Graph(
							{
								containerId: '${htmlNamespace}testrayTeamsGraph',
								controller: 'case_results',
								controllerMethod: 'teamMetrics.json',
								graphHeight: 250,
								params: {
									testrayBuildId: ${testrayBuildComposite.testrayBuildId}
								},
								type: 'bar',
								xAxisLabel: '<liferay-ui:message key="teams" />',
								yAxisLabel: '<liferay-ui:message key="tests" />'
							}
						).render();
					</aui:script>
				</liferay-ui:section>

				<liferay-ui:section>
					<div class="graph-container graph-container-hide-x graph-container-sm" id="${htmlNamespace}testrayComponentsGraph">
						<div class="placeholder"></div>
					</div>

					<aui:script use="testray-graph">
						new Liferay.Testray.Graph(
							{
								containerId: '${htmlNamespace}testrayComponentsGraph',
								controller: 'case_results',
								controllerMethod: 'componentMetrics.json',
								graphHeight: 250,
								params: {
									testrayBuildId: ${testrayBuildComposite.testrayBuildId}
								},
								type: 'bar',
								xAxisLabel: '<liferay-ui:message key="components" />',
								yAxisLabel: '<liferay-ui:message key="tests" />'
							}
						).render();
					</aui:script>
				</liferay-ui:section>
			</liferay-ui:tabs>
		</aui:col>
	</aui:row>
</div>

<c:if test='${(not empty testrayRunComposite) && testrayPermission:containsControllerAction(themeDisplay, "bulk", "edit_case_results")}'>
	<aui:button-row cssClass="button-row-top">
		<portlet:renderURL var="editTestrayCaseResultsBulkURL">
			<portlet:param name="controller" value="bulk" />
			<portlet:param name="action" value="editCaseResults" />
			<portlet:param name="redirect" value="${portletURL}" />
			<portlet:param name="testrayRunId" value="${testrayRunComposite.testrayRunId}" />
		</portlet:renderURL>

		<aui:button href="${editTestrayCaseResultsBulkURL}" primary="${true}" value="bulk-operation" />
	</aui:button-row>
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}copyGitHash',
		function(gitHash) {
			var copyText = document.createElement('input');

			copyText.style = 'position: absolute; left: -1000px; top: -1000px';

			copyText.value = gitHash;

			document.body.appendChild(copyText);

			copyText.select();

			document.execCommand('copy');

			document.body.removeChild(copyText);
		}
	);
</aui:script>