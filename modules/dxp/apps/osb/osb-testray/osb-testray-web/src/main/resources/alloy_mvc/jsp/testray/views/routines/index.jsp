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

<liferay-util:include page="/alloy_mvc/jsp/testray/views/projects/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tab" value="routines" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="routines"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/routines/index_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayRoutineConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayRoutineConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayRoutinesIndexColumns"
		/>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRoutineClass, "create")}'>
			<portlet:renderURL var="createTestrayRoutineURL">
				<portlet:param name="controller" value="routines" />
				<portlet:param name="action" value="create" />
				<portlet:param name="testrayProjectId" value="${param.testrayProjectId}" />
			</portlet:renderURL>

			<aui:button href="${createTestrayRoutineURL}" icon="icon-plus" primary="${true}" value="add-routine" />
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-routines"
		id="routinesSearchContainer"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${testrayRoutinesCount}"
	>
		<liferay-ui:search-container-results
			results="${testrayRoutineComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayRoutineId"
			modelVar="testrayRoutineComposite"
		>
			<portlet:renderURL var="viewTestrayBuildsURL">
				<portlet:param name="controller" value="builds" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayRoutineId" value="${testrayRoutineComposite.testrayRoutineId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "builds", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-column-main"
				href="${viewTestrayBuildsURL}"
				name="routine"
				orderable="${true}"
				orderableProperty="name"
				value="${fn:escapeXml(testrayRoutineComposite.name)}"
			/>

			<c:if test="${testrayRoutinesIndexColumns.contains(TestrayRoutineConstants.COLUMN_LABEL_EXECUTION_DATE)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayBuildsURL}"
					name="execution-date"
				>
					<testray:date
						relative="${true}"
						value="${testrayRoutineComposite.latestExecutionDate}"
					/>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:forEach items="${TestrayCaseResultConstants.STATUS_LABELS}" var="statusLabel">
				<c:if test="${testrayRoutinesIndexColumns.contains(statusLabel)}">
					<liferay-ui:search-container-column-text
						href="${viewTestrayBuildsURL}"
						name="${statusLabel}"
						value="${testrayRoutineComposite.testrayCaseResultReporter.getTestrayCaseResultsCount(statusLabel)}"
					/>
				</c:if>
			</c:forEach>

			<c:if test="${testrayRoutinesIndexColumns.contains(TestrayRoutineConstants.COLUMN_LABEL_TOTAL)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayBuildsURL}"
					name="${TestrayRoutineConstants.COLUMN_LABEL_TOTAL}"
					value="${testrayRoutineComposite.testrayCaseResultReporter.testrayCaseResultsCount}"
				/>
			</c:if>

			<c:if test="${testrayRoutinesIndexColumns.contains(TestrayRoutineConstants.COLUMN_LABEL_METRICS)}">
				<liferay-ui:search-container-column-text
					cssClass="metrics"
					name="metrics"
				>
					<c:set value="${testrayRoutineComposite}" var="composite" />
					<c:set value="${testrayRoutineComposite.testrayRoutineId}" var="id" />

					<%@ include file="/alloy_mvc/jsp/testray/views/progress_bars.jspf" %>
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}routinesSearchContainer',
			menu: [
				{
					action: 'index',
					controller: 'builds',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '?${htmlNamespace}testrayRoutineId={id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "builds", "index", testrayRoutineComposite.testrayProjectId)}
				},
				{
					action: 'edit',
					controller: 'routines',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRoutineComposite.testrayRoutine, "edit")}
				},
				{
					action: 'select',
					controller: 'factor_categories',
					iconCssClass: 'icon-desktop',
					label: '<liferay-ui:message key="select-default-environment-factors" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="select-categories" />'
					},
					urlTemplate: '/select?${htmlNamespace}testrayRoutineId={id}',
					visible: ${testrayPermission:containsClassAction(themeDisplay, testrayProductVersionClass, "index")}
				},
				{
					action: 'delete',
					controller: 'routines',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'modal',
					modalConfig: {
						height: 380,
						title: '<liferay-ui:message key="delete-routine" />',
						width: 600
					},
					urlTemplate: '/deleteConfirm?${htmlNamespace}id={id}',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRoutineComposite.testrayRoutine, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>