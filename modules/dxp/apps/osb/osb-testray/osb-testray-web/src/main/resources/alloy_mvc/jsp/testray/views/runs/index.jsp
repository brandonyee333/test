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
	<liferay-util:param name="tab" value="runs" />
</liferay-util:include>

<div class="testray-card">
	<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRunClass, "create")}'>
		<portlet:renderURL var="selectNewTestrayRunTestrayFactorOptionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="controller" value="factor_options" />
			<portlet:param name="action" value="select" />
			<portlet:param name="redirect" value="" />
			<portlet:param name="className" value="<%= TestrayRun.class.getName() %>" />
			<portlet:param name="classPK" value="0" />
			<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
			<portlet:param name="testrayRoutineId" value="${testrayBuildComposite.testrayRoutineId}" />
		</portlet:renderURL>

		<c:set value='${AlloyLanguageUtil.getUnicode("select-options")}' var="selectNewTestrayRunTestrayFactorOptionsURLTitle" />

		<c:set value="javascript:Liferay.Testray.openWindow('${selectNewTestrayRunTestrayFactorOptionsURL}', '${selectNewTestrayRunTestrayFactorOptionsURLTitle}', 1000)" var="selectNewTestrayRunTestrayFactorOptionsURL" />
	</c:if>

	<testray:table-toolbar
		title="runs"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/runs/index_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayRunConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayRunConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayRunsIndexColumns"
		/>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRunClass, "create")}'>
			<aui:button icon="icon-plus" onClick="${selectNewTestrayRunTestrayFactorOptionsURL}" primary="${true}" value="add-run" />
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-runs"
		id="runsSearchContainer"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
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

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}runsSearchContainer',
			menu: [
				{
					action: 'index',
					controller: 'case_results',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '?${htmlNamespace}testrayRunId={id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayRunComposite.testrayProjectId)}
				},
				{
					action: 'select',
					controller: 'factor_options',
					iconCssClass: 'icon-desktop',
					label: '<liferay-ui:message key="select-environment-factors" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="select-environment-factors" />'
					},
					parameters: {
						className: '${testrayRunClass.name}'
					},
					urlTemplate: '/select?${htmlNamespace}classPK={id}',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRunComposite.testrayRun, "edit")}
				},
				{
					action: 'delete',
					controller: 'runs',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRunComposite.testrayRun, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>