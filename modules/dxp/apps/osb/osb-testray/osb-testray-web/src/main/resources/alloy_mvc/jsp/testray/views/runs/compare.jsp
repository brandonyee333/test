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
	<liferay-util:param name="title" value="compare-cases" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<h2 class="testray-data-header">
		<liferay-ui:message key="compare-details" />
	</h2>

	<aui:row>
		<aui:col span="<%= 8 %>">
			<div class="compare-run-data-section compare-run-data-section-run-a">
				<dl class="data-list dl-horizontal">
					<dt>
						<liferay-ui:message key="run-a" />
					</dt>
					<dd>
						<portlet:renderURL var="viewTestrayRunATestrayCaseResultsURL">
							<portlet:param name="controller" value="case_results" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayBuildId" value="${testrayRunCompositeA.testrayBuildId}" />
							<portlet:param name="testrayRunId" value="${testrayRunCompositeA.testrayRunId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayRunCompositeA.testrayProjectId)}'
							/>
						</portlet:renderURL>

						<a href="${viewTestrayRunATestrayCaseResultsURL}" target="_blank">${testrayRunCompositeA.testrayRunId}</a>
					</dd>
					<dt>
						<liferay-ui:message key="project-name" />
					</dt>
					<dd>
						<portlet:renderURL var="viewTestrayRunATestrayRoutinesURL">
							<portlet:param name="controller" value="routines" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayProjectId" value="${testrayRunCompositeA.testrayProjectId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayRunCompositeA.testrayProjectId)}'
							/>
						</portlet:renderURL>

						<a href="${viewTestrayRunATestrayRoutinesURL}" target="_blank">
							<c:out value="${testrayRunCompositeA.testrayProjectName}" />
						</a>
					</dd>
					<dt>
						<liferay-ui:message key="build" />
					</dt>
					<dd>
						<portlet:renderURL var="viewTestrayRunATestrayBuildURL">
							<portlet:param name="controller" value="case_results" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayBuildId" value="${testrayRunCompositeA.testrayBuildId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayRunCompositeA.testrayProjectId)}'
							/>
						</portlet:renderURL>

						<a href="${viewTestrayRunATestrayBuildURL}" target="_blank">
							<c:out value="${testrayRunCompositeA.testrayBuildName}" />
						</a>
					</dd>
					<dt>
						<liferay-ui:message key="environment" />
					</dt>
					<dd>
						<c:out value="${testrayRunCompositeA.title}" />
					</dd>
				</dl>
			</div>

			<div class="separator"></div>

			<div class="compare-run-data-section compare-run-data-section-run-b">
				<dl class="data-list dl-horizontal">
					<dt>
						<liferay-ui:message key="run-b" />
					</dt>
					<dd>
						<portlet:renderURL var="viewTestrayRunBTestrayCaseResultsURL">
							<portlet:param name="controller" value="case_results" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayBuildId" value="${testrayRunCompositeB.testrayBuildId}" />
							<portlet:param name="testrayRunId" value="${testrayRunCompositeB.testrayRunId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayRunCompositeB.testrayProjectId)}'
							/>
						</portlet:renderURL>

						<a href="${viewTestrayRunBTestrayCaseResultsURL}" target="_blank">${testrayRunCompositeB.testrayRunId}</a>
					</dd>
					<dt>
						<liferay-ui:message key="project-name" />
					</dt>
					<dd>
						<portlet:renderURL var="viewTestrayRunBTestrayRoutinesURL">
							<portlet:param name="controller" value="routines" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayProjectId" value="${testrayRunCompositeB.testrayProjectId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayRunCompositeB.testrayProjectId)}'
							/>
						</portlet:renderURL>

						<a href="${viewTestrayRunBTestrayRoutinesURL}" target="_blank">
							<c:out value="${testrayRunCompositeB.testrayProjectName}" />
						</a>
					</dd>
					<dt>
						<liferay-ui:message key="build" />
					</dt>
					<dd>
						<portlet:renderURL var="viewTestrayRunBTestrayBuildURL">
							<portlet:param name="controller" value="case_results" />
							<portlet:param name="action" value="index" />
							<portlet:param name="testrayBuildId" value="${testrayRunCompositeB.testrayBuildId}" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "index", testrayRunCompositeB.testrayProjectId)}'
							/>
						</portlet:renderURL>

						<a href="${viewTestrayRunBTestrayBuildURL}" target="_blank">
							<c:out value="${testrayRunCompositeB.testrayBuildName}" />
						</a>
					</dd>
					<dt>
						<liferay-ui:message key="environment" />
					</dt>
					<dd>
						<c:out value="${testrayRunCompositeB.title}" />
					</dd>
				</dl>
			</div>
		</aui:col>

		<aui:col span="<%= 4 %>">
			<c:set value='${AlloyLanguageUtil.get("number-of-case-results")}' var="summaryTableName" />

			<c:set value="${testrayRunComparison.testrayRunSummaryMap}" var="summaryMap" />

			<%@ include file="/alloy_mvc/jsp/testray/views/runs/summary_table.jspf" %>
		</aui:col>
	</aui:row>
</div>

<div class="testray-card">
	<portlet:renderURL var="compareTestrayRunsDetailsURL">
		<portlet:param name="controller" value="runs" />
		<portlet:param name="action" value="compare" />
		<portlet:param name="redirect" value="${redirect}" />
		<portlet:param name="testrayRunIdA" value="${testrayRunCompositeA.testrayRunId}" />
		<portlet:param name="testrayRunIdB" value="${testrayRunCompositeB.testrayRunId}" />
	</portlet:renderURL>

	<liferay-ui:tabs
		names="teams,components,details"
		param="view"
		url="${compareTestrayRunsDetailsURL}"
	/>

	<c:choose>
		<c:when test='${param.view == "components"}'>
			<%@ include file="/alloy_mvc/jsp/testray/views/runs/components_tab.jspf" %>
		</c:when>
		<c:when test='${param.view == "details"}'>
			<%@ include file="/alloy_mvc/jsp/testray/views/runs/details_tab.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/alloy_mvc/jsp/testray/views/runs/teams_tab.jspf" %>
		</c:otherwise>
	</c:choose>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>