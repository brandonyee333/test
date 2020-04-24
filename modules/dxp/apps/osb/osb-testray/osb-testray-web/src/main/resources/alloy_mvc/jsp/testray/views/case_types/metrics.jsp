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
	<liferay-util:param name="tab" value="case-types" />
</liferay-util:include>

<div class="testray-card">
	<testray:table-toolbar
		title="case-types"
	>
		<testray:filter>
			<aui:input name="tab" type="hidden" value="${param.tab}" />
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
				items="${testrayTeams}"
				label="team"
				modelClassName="com.liferay.osb.testray.model.TestrayTeam"
				name="testrayTeamId"
				selectedLabel="team-x"
				type="select-model"
			/>
		</testray:filter>

		<testray:configure-columns
			columnLabels="${TestrayCaseTypeConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayCaseTypeConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayCaseTypesMetricsColumns"
		/>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-case-types"
		id="metricsSearchContainer"
		iteratorURL="${portletURL}"
		total="${testrayCaseTypeComposites.size()}"
	>
		<liferay-ui:search-container-results
			results="${testrayCaseTypeComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayCaseTypeId"
			modelVar="testrayCaseTypeComposite"
		>
			<portlet:renderURL var="viewTestrayCaseResultsURL">
				<portlet:param name="controller" value="case_results" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
				<portlet:param name="testrayCaseTypeId" value="${testrayCaseTypeComposite.testrayCaseTypeId}" />
				<portlet:param name="testrayRunId" value="${testrayRunComposite.testrayRunId}" />
				<portlet:param name="testrayTeamId" value="${param.testrayTeamId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-column-main"
				href="${viewTestrayCaseResultsURL}"
				name="test-type"
				property="name"
			/>

			<jsp:useBean
				class="java.util.HashMap"
				id="attributes"
			/>

			<c:set property="testrayCaseTypeId" target="${attributes}" value="${testrayCaseTypeComposite.testrayCaseTypeId}" />

			<c:set value="${testrayCaseTypeComposite.testrayCaseResultReporter.getSubsetTestrayCaseResultReporter(attributes)}" var="testrayCaseTypeTestrayCaseResultReporter" />

			<c:if test="${testrayCaseTypesMetricsColumns.contains(TestrayBuildConstants.COLUMN_LABEL_TOTAL)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseResultsURL}"
					name="${TestrayBuildConstants.COLUMN_LABEL_TOTAL}"
					value="${testrayCaseTypeTestrayCaseResultReporter.testrayCaseResultsCount}"
				/>
			</c:if>

			<c:forEach items="${TestrayCaseResultConstants.STATUS_LABELS}" var="statusLabel">
				<c:if test="${testrayCaseTypesMetricsColumns.contains(statusLabel)}">
					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultsURL}"
						name="${statusLabel}"
						value="${testrayCaseTypeTestrayCaseResultReporter.getTestrayCaseResultsCount(statusLabel)}"
					/>
				</c:if>
			</c:forEach>

			<liferay-ui:search-container-column-text
				cssClass="metrics"
				name="metrics"
			>
				<div class="metrics-bar" id="${htmlNamespace}metricsBar${testrayCaseTypeComposite.testrayCaseTypeId}"></div>

				<aui:script use="testray-metrics-bar">
					var data = {};

					<c:forEach items="${testrayCaseTypeTestrayCaseResultReporter.simplifiedTestrayCaseResultStatusCounts}" var="statusCountEntry">
						data['${statusCountEntry.key}'] = {
							label: '<liferay-ui:message key="${statusCountEntry.key}" />',
							value: ${statusCountEntry.value}
						};
					</c:forEach>

					new Liferay.Testray.MetricsBar(
						{
							container: '#${htmlNamespace}metricsBar${testrayCaseTypeComposite.testrayCaseTypeId}',
							data: data,
							total: ${testrayCaseTypeTestrayCaseResultReporter.testrayCaseResultsCount}
						}
					).render();
				</aui:script>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			paginate="${false}"
		/>
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>