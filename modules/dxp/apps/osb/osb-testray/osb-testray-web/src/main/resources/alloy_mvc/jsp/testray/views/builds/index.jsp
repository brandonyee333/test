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

<%@ include file="/alloy_mvc/jsp/testray/views/routines/header.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="build-history"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/builds/index_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayBuildConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayBuildConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayBuildsIndexColumns"
		/>

		<%@ include file="/alloy_mvc/jsp/testray/views/builds/create_dropdown.jspf" %>
	</testray:table-toolbar>

	<c:if test="${!param.archived && (not empty testrayBuildComposites)}">
		<div class="graph-container graph-container-block graph-container-hide-x" id="${htmlNamespace}buildHistoryGraph">
			<div class="placeholder"></div>
		</div>

		<aui:script use="testray-graph">
			new Liferay.Testray.Graph(
				{
					containerId: '${htmlNamespace}buildHistoryGraph',
					controller: 'case_results',
					controllerMethod: 'buildMetrics.json',
					params: {
						cur: ${cur},
						delta: ${delta},
						priorities: ${testrayCasePriorities},
						testrayCaseTypeId: ${testrayCaseTypeIds},
						testrayRoutineId: ${testrayRoutine.testrayRoutineId},
						testrayTeamId: ${testrayTeamId}
					},
					reverseData: true,
					xAxisLabel: '<liferay-ui:message key="builds-ordered-by-date" />',
					yAxisLabel: '<liferay-ui:message key="tests" />'
				}
			).render();
		</aui:script>
	</c:if>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-builds"
		id="buildsSearchContainer"
		iteratorURL="${alloySearchResult.portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${alloySearchResult.size}"
	>
		<liferay-ui:search-container-results
			results="${testrayBuildComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayBuildId"
			modelVar="testrayBuildComposite"
		>
			<c:if test="${!param.archived}">
				<portlet:renderURL var="viewTestrayCaseResultsURL">
					<portlet:param name="controller" value="case_results" />
					<portlet:param name="action" value="index" />
					<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />

					<testray:filter-preference
						value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayProjectId)}'
					/>
				</portlet:renderURL>
			</c:if>

			<c:if test="${testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_PROMOTED)}">
				<liferay-ui:search-container-column-text
					cssClass='${testrayBuildComposite.promoted ? "promoted" : StringPool.BLANK}'
					orderable="${true}"
					orderableProperty="promoted_sortable"
				>
					<c:if test="${testrayBuildComposite.promoted}">
						<liferay-ui:icon
							cssClass="promoted status-icon"
							iconCssClass="icon-star"
							message="promoted"
						/>
					</c:if>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayTaskClass, "index") && !param.archived && testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_STATUS)}'>
				<liferay-ui:search-container-column-text
					cssClass="archived-status archived-status-${param.archived} task-status task-status-${testrayBuildComposite.testrayTaskStatusLabel}"
					name="status"
				>
					<c:if test="${not empty testrayBuildComposite.testrayTaskStatusLabel}">
						<c:set value="<%= StringUtil.merge(TestraySubtaskConstants.STATUSES_DEFAULT) %>" var="defaultSubtaskStatuses" />

						<portlet:renderURL var="viewTestraySubtasksURL">
							<portlet:param name="controller" value="subtasks" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayTaskStatuses" value='${(testrayBuildComposite.testrayTaskStatus == TestrayTaskConstants.STATUS_IN_ANALYSIS) ? defaultSubtaskStatuses : ""}' />
							<portlet:param name="testrayTaskId" value="${testrayBuildComposite.testrayTaskId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "subtasks", "index", testrayProjectId)}'
							/>
						</portlet:renderURL>

						<aui:a cssClass="status-icon ${testrayBuildComposite.testrayTaskStatusLabel}" href="${viewTestraySubtasksURL}">
							<liferay-ui:icon
								iconCssClass="icon-circle"
								message="${testrayBuildComposite.testrayTaskStatusLabel}"
							/>
						</aui:a>
					</c:if>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_CREATE_DATE)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="create-date"
					orderable="${true}"
					orderableProperty="createDate_sortable"
				>
					<testray:date
						value="${testrayBuildComposite.createDate}"
					/>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_GIT_HASH)}">
				<liferay-ui:search-container-column-text
					name="git-hash"
				>
					${testrayBuildComposite.gitHashShortened}

					<a class="copy-icon-inline" data-toggle="tooltip" onClick="${htmlNamespace}copyGitHash('${testrayBuildComposite.gitHash}', title='<liferay-ui:message key="copied" />');" title='<liferay-ui:message key="click-to-copy" />'></a>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_PRODUCT_VERSION)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="product-version"
					orderable="${true}"
					orderableProperty="testrayProductVersionName_sortable"
					property="testrayProductVersionName"
				/>
			</c:if>

			<liferay-ui:search-container-column-text
				cssClass="table-column-main"
				href="${viewTestrayCaseResultsURL}"
				name="build"
				orderable="${true}"
				orderableProperty="name_sortable"
				value="${fn:escapeXml(testrayBuildComposite.name)}"
			/>

			<c:if test="${!param.archived}">
				<c:forEach items="${TestrayCaseResultConstants.STATUS_LABELS}" var="statusLabel">
					<c:if test="${testrayBuildsIndexColumns.contains(statusLabel)}">
						<liferay-ui:search-container-column-text
							href="${viewTestrayCaseResultsURL}"
							name="${statusLabel}"
							value="${testrayBuildComposite.testrayCaseResultReporter.getTestrayCaseResultsCount(statusLabel)}"
						/>
					</c:if>
				</c:forEach>

				<c:if test="${testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_TOTAL)}">
					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultsURL}"
						name="${TestrayBuildConstants.COLUMN_LABEL_TOTAL}"
						value="${testrayBuildComposite.testrayCaseResultReporter.testrayCaseResultsCount}"
					/>
				</c:if>

				<c:if test="${testrayBuildsIndexColumns.contains(TestrayBuildConstants.COLUMN_LABEL_METRICS)}">
					<liferay-ui:search-container-column-text
						cssClass="metrics"
						name="metrics"
					>
						<c:set value="${testrayBuildComposite}" var="composite" />
						<c:set value="${testrayBuildComposite.testrayBuildId}" var="id" />

						<%@ include file="/alloy_mvc/jsp/testray/views/progress_bars.jspf" %>
					</liferay-ui:search-container-column-text>
				</c:if>
			</c:if>

			<c:if test="${param.archived}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="archive-date"
					orderable="${true}"
					orderableProperty="archiveDate_sortable"
				>
					<c:choose>
						<c:when test="${testrayBuildComposite.archiveDate == null}">
							<liferay-ui:message key="archived-before-april-8-2017" />
						</c:when>
						<c:otherwise>
							<testray:date
								value="${testrayBuildComposite.archiveDate}"
							/>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
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

	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}buildsSearchContainer',
			menu: [
				{
					action: 'index',
					controller: 'case_results',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '?${htmlNamespace}testrayBuildId={id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayBuildComposite.testrayProjectId)},
					visible: ${!param.archived}
				},
				{
					action: 'updatePromoted',
					controller: 'builds',
					iconCssClass: 'icon-star',
					label: '<liferay-ui:message key="promote" />',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "updatePromoted")}
				},
				{
					action: 'create',
					controller: 'tasks',
					iconCssClass: 'icon-tasks',
					label: '<liferay-ui:message key="analyze" />',
					name: 'taskCreate',
					parameters: {
						redirect: '${portletURL}',
						taskStatusLabel: '${TestrayTaskConstants.LABEL_IN_ANALYSIS}'
					},
					urlTemplate: '/create?${htmlNamespace}testrayBuildId={id}',
					visible: ${!param.archived && testrayPermission:containsClassAction(themeDisplay, testrayTaskClass, "create")}
				},
				{
					action: 'edit',
					controller: 'builds',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "edit")}
				},
				{
					action: 'updateArchived',
					controller: 'builds',
					iconCssClass: 'icon-archive',
					label: '<liferay-ui:message key="${param.archived ? 'restore' : 'archive'}" />',
					method: 'POST',
					parameters: {
						archived: '${param.archived ? false : true}'
					},
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "updateArchived")}
				},
				{
					action: 'delete',
					controller: 'builds',
					deleteConfig: {
						message: '<liferay-ui:message key="deleting-this-build-will-delete-all-tasks-runs-and-results-associated-with-this-build.-this-action-cannot-be-undone" />'
					},
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayBuildComposite.testrayBuild, "delete")}
				}
			],
			redirect: '${alloySearchResult.portletURL}'
		}
	).render();
</aui:script>