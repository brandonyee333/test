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
	<liferay-util:param name="tab" value="results" />
</liferay-util:include>

<div class="testray-card">
	<testray:table-toolbar
		title="tests"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/case_results/index_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayCaseResultConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayCaseResultConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayCaseResultsIndexColumns"
		/>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayRunComposite.testrayRun, "edit")}'>
			<div class="dropdown">
				<aui:a cssClass="btn btn-default btn-primary dropdown-toggle" data-toggle="dropdown" href="javascript:;">
					<aui:icon image="plus" />

					<liferay-ui:message key="add" />

					<aui:icon image="caret-down" />
				</aui:a>

				<ul class="dropdown-menu dropdown-menu-right">
					<li>
						<portlet:renderURL var="selectTestrayRunTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="runs" />
							<portlet:param name="action" value="select" />
							<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
							<portlet:param name="redirect" value="${portletURL}" />
							<portlet:param name="type" value="cases" />

							<testray:filter-preference
								value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "select", testrayProjectId)}'
							/>
						</portlet:renderURL>

						<c:set value='${AlloyLanguageUtil.getUnicode("select-runs")}' var="selectTestrayRunTestrayCasesURLTitle" />

						<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayRunTestrayCasesURL}', '${selectTestrayRunTestrayCasesURLTitle}', 1000)" var="selectTestrayRunTestrayCasesURL" />

						<aui:a href="${selectTestrayRunTestrayCasesURL}" label="select-cases" />
					</li>
					<li>
						<portlet:renderURL var="selectTestrayRunTestraySuitesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="runs" />
							<portlet:param name="action" value="select" />
							<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
							<portlet:param name="redirect" value="${portletURL}" />
							<portlet:param name="type" value="suites" />
						</portlet:renderURL>

						<c:set value='${AlloyLanguageUtil.getUnicode("select-runs")}' var="selectTestrayRunTestraySuitesURLTitle" />

						<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayRunTestraySuitesURL}', '${selectTestrayRunTestraySuitesURLTitle}', 1000)" var="selectTestrayRunTestraySuitesURL" />

						<aui:a href="${selectTestrayRunTestraySuitesURL}" label="select-suites" />
					</li>
					<li>
						<portlet:renderURL var="selectTestrayRunUploadURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="runs" />
							<portlet:param name="action" value="select" />
							<portlet:param name="testrayBuildId" value="${testrayBuildComposite.testrayBuildId}" />
							<portlet:param name="redirect" value="${portletURL}" />
							<portlet:param name="type" value="upload" />
						</portlet:renderURL>

						<c:set value='${AlloyLanguageUtil.getUnicode("select-runs")}' var="selectTestrayRunUploadURLTitle" />

						<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayRunUploadURL}', '${selectTestrayRunUploadURLTitle}', 1000)" var="selectTestrayRunUploadURL" />

						<aui:a href="${selectTestrayRunUploadURL}" label="upload-cucumber-results" />
					</li>
				</ul>
			</div>
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		delta="${delta}"
		emptyResultsMessage="there-are-no-linked-cases"
		id="caseResultsSearchContainer"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${testrayCaseResultCompositesTotal}"
	>
		<liferay-ui:search-container-results
			results="${testrayCaseResultComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayCaseResultId"
			modelVar="testrayCaseResultComposite"
		>
			<portlet:renderURL var="viewTestrayCaseResultURL">
				<portlet:param name="controller" value="case_results" />
				<portlet:param name="action" value="view" />
				<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:renderURL>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_CASE_TYPE)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="case-type"
					orderable="${true}"
					orderableProperty="testrayCaseTypeName_sortable"
					value="${testrayCaseResultComposite.testrayCaseTypeName}"
				/>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_PRIORITY)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="priority"
					orderable="${true}"
					orderableProperty="testrayCasePriority_sortable"
					value="${testrayCaseResultComposite.priority}"
				/>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_TEAM)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="team"
					orderable="${true}"
					orderableProperty="teamName_sortable"
					value="${testrayCaseResultComposite.testrayTeamName}"
				/>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_COMPONENT)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="component"
					orderable="${true}"
					orderableProperty="testrayComponentName_sortable"
					property="testrayComponentName"
				/>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_CASE)}">
				<liferay-ui:search-container-column-text
					cssClass="table-column-case-name table-column-main valign-top"
					href="${viewTestrayCaseResultURL}"
					name="case"
					orderable="${true}"
					orderableProperty="testrayCaseName_sortable"
					property="testrayCaseNameTruncated"
				/>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_RUN)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="run"
					orderable="${true}"
					orderableProperty="testrayRunNumber_sortable"
				>
					<fmt:formatNumber
						minIntegerDigits="2"
						value="${testrayCaseResultComposite.testrayRunNumber}"
					/>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_ASSIGNEE)}">
				<liferay-ui:search-container-column-text
					cssClass="assignee assignee-${testrayCaseResultComposite.assignedUserId} valign-top"
					name="assignee"
					orderable="${true}"
					orderableProperty="assignedUserFullName_sortable"
				>
					<div class="avatar-container" id="${htmlNamespace}avatarContainer${testrayCaseResultComposite.testrayCaseResultId}">${testrayCaseResultComposite.assignedUserFullName}</div>

					<portlet:actionURL var="assignToMeTestrayCaseResultURL">
						<portlet:param name="controller" value="case_results" />
						<portlet:param name="action" value="updateUser" />
						<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
						<portlet:param name="redirect" value="${portletURL}" />
						<portlet:param name="assignedUserId" value="${themeDisplay.userId}" />
					</portlet:actionURL>

					<aui:script use="testray-avatar">
						var testrayCaseResultJSON = ${testrayCaseResultComposite.getJSONObject()};

						new Liferay.Testray.Avatar(
							{
								container: '#${htmlNamespace}avatarContainer${testrayCaseResultComposite.testrayCaseResultId}',
								data: [testrayCaseResultJSON.assignedUser],
								emptyLinkConfig: {
									iconCssClass: 'user',
									label: '<liferay-ui:message key="assign-to-me" />',
									url: '${assignToMeTestrayCaseResultURL}'
								},
								showLabel: true
							}
						).render();
					</aui:script>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_STATUS)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="status"
					orderable="${true}"
					orderableProperty="status_sortable"
				>
					<aui:a cssClass="status ${testrayCaseResultComposite.statusLabel}" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.statusLabel}" />
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_WARNINGS)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					href="${viewTestrayCaseResultURL}"
					name="warnings"
					property="testrayCaseResultWarningsCount"
				/>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_ISSUES)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="issues"
				>
					<c:set value="${testrayCaseResultComposite.issues}" var="issues" />
					<c:set value="${false}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_ERRORS)}">
				<liferay-ui:search-container-column-text
					cssClass="table-column-errors"
					name="errors"
					orderable="${true}"
					orderableProperty="errors_sortable"
				>
					<pre>${fn:escapeXml(testrayCaseResultComposite.errorsTruncated)}</pre>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCaseResultsIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_COMMENTS)}">
				<liferay-ui:search-container-column-text
					cssClass="table-column-comments"
					name="comments"
					orderable="${true}"
					orderableProperty="commentMBMessageBody_sortable"
				>
					<pre>${fn:escapeXml(testrayCaseResultComposite.comment)}</pre>
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
			containerId: '${htmlNamespace}caseResultsSearchContainer',
			menu: [
				{
					action: 'view',
					controller: 'case_results',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '/{id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "case_results", "view", testrayCaseResultComposite.testrayProjectId)}
				},
				{
					action: 'select',
					controller: 'users',
					iconCssClass: 'icon-user',
					label: '<liferay-ui:message key="assign" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="users" />'
					},
					urlTemplate: '/select?submitIdName=id&submitIdValue={id}',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "updateUser")}
				},
				{
					action: 'updateUser',
					controller: 'case_results',
					iconCssClass: 'icon-user',
					label: '<liferay-ui:message key="assign-to-me" />',
					method: 'POST',
					parameters: {
						assignedUserId: '${themeDisplay.userId}'
					},
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "updateUser")}
				},
				{
					action: 'delete',
					controller: 'case_results',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>