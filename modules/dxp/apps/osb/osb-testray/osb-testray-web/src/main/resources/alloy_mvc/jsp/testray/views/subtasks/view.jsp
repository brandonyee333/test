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
	<liferay-util:param name="title" value="${testraySubtaskComposite.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:choose>
	<c:when test="${testraySubtaskComposite.mergedToTestraySubtaskId > 0}">
		<div class="alert alert-warning">
			<c:set value="${testraySubtaskComposite.mergedToTestraySubtaskComposite.name}" var="mergedToTestraySubtaskCompositeName" />

			<liferay-ui:message arguments="${mergedToTestraySubtaskCompositeName}" key="this-subtask-has-been-merged-with-x" />

			<portlet:renderURL var="viewTestraySubtaskURL">
				<portlet:param name="controller" value="subtasks" />
				<portlet:param name="action" value="view" />
				<portlet:param name="id" value="${testraySubtaskComposite.mergedToTestraySubtaskId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "subtasks", "view", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<aui:a cssClass="alert-link" href="${viewTestraySubtaskURL}">
				<liferay-ui:message arguments="${mergedToTestraySubtaskCompositeName}" key="view-x" />
			</aui:a>
		</div>
	</c:when>
	<c:otherwise>
		<aui:button-row cssClass="button-row-top">
			<c:if test='${testrayPermission:containsModelAction(themeDisplay, testraySubtaskComposite.testraySubtask, "updateUser")}'>
				<portlet:renderURL var="selectUserURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="controller" value="users" />
					<portlet:param name="action" value="select" />
					<portlet:param name="submitAction" value="updateUser" />
					<portlet:param name="submitController" value="subtasks" />
					<portlet:param name="submitIdName" value="id" />
					<portlet:param name="submitIdValue" value="${testraySubtaskComposite.testraySubtaskId}" />
				</portlet:renderURL>

				<c:set value='${AlloyLanguageUtil.getUnicode("users")}' var="selectUserURLTitle" />

				<c:set value="javascript:Liferay.Testray.openWindow('${selectUserURL}', '${selectUserURLTitle}', 1000)" var="selectUserURL" />

				<aui:button onClick="${selectUserURL}" value='${(testraySubtaskComposite.status == TestraySubtaskConstants.STATUS_IN_ANALYSIS) ? "assign" : ((testraySubtaskComposite.status == TestraySubtaskConstants.STATUS_COMPLETE) ? "assign-and-reanalyze" : "assign-and-begin-analysis")}' />
			</c:if>

			<c:if test='${testrayPermission:containsModelAction(themeDisplay, testraySubtaskComposite.testraySubtask, "editStatus") && (testraySubtaskComposite.status == TestraySubtaskConstants.STATUS_IN_ANALYSIS)}'>
				<portlet:renderURL var="editStatusURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="controller" value="subtasks" />
					<portlet:param name="action" value="editStatus" />
					<portlet:param name="id" value="${testraySubtaskComposite.testraySubtaskId}" />
				</portlet:renderURL>

				<c:set value='${AlloyLanguageUtil.getUnicode("edit-status")}' var="editStatusURLTitle" />

				<c:set value="javascript:Liferay.Testray.openWindow('${editStatusURL}', '${editStatusURLTitle}', 1000)" var="editStatusURL" />

				<aui:button onClick="${editStatusURL}" primary="${true}" value="complete" />
			</c:if>

			<c:if test='${testrayPermission:containsModelAction(themeDisplay, testraySubtaskComposite.testraySubtask, "updateUser") && (testraySubtaskComposite.status == TestraySubtaskConstants.STATUS_IN_ANALYSIS)}'>
				<portlet:actionURL var="unassignURL">
					<portlet:param name="controller" value="subtasks" />
					<portlet:param name="action" value="updateUser" />
					<portlet:param name="redirect" value="${portletURL}" />
					<portlet:param name="id" value="${testraySubtaskComposite.testraySubtaskId}" />
					<portlet:param name="assignedUserId" value="0" />
				</portlet:actionURL>

				<aui:button onClick="${unassignURL}" value="return-to-open" />
			</c:if>
		</aui:button-row>
	</c:otherwise>
</c:choose>

<div class="testray-card">
	<h2 class="testray-data-header">
		<liferay-ui:message key="subtask-details" />
	</h2>

	<aui:row>
		<aui:col lg="4" md="5" sm="12">
			<dl class="data-list dl-horizontal">
				<dt>
					<liferay-ui:message key="status" />
				</dt>
				<dd>
					<span class="status ${testraySubtaskComposite.statusLabel}">
						<liferay-ui:message key="${testraySubtaskComposite.statusLabel}" />
					</span>
				</dd>
				<dt>
					<liferay-ui:message key="assignee" />
				</dt>
				<dd>
					<div class="avatar-container" id="${htmlNamespace}avatarContainer${testraySubtaskComposite.testraySubtaskId}">${testraySubtaskComposite.assignedUserName}</div>

					<portlet:actionURL var="assignToMeTestraySubtaskURL">
						<portlet:param name="controller" value="subtasks" />
						<portlet:param name="action" value="updateUser" />
						<portlet:param name="redirect" value="${portletURL}" />
						<portlet:param name="id" value="${testraySubtaskComposite.testraySubtaskId}" />
						<portlet:param name="assignedUserId" value="${themeDisplay.userId}" />
					</portlet:actionURL>

					<aui:script use="testray-avatar">
						var testraySubtaskJSON = ${testraySubtaskComposite.getJSONObject()};

						new Liferay.Testray.Avatar(
							{
								container: '#${htmlNamespace}avatarContainer${testraySubtaskComposite.testraySubtaskId}',
								data: [testraySubtaskJSON.assignedUser],
								emptyLinkConfig: {
									iconCssClass: 'user',
									label: '<liferay-ui:message key="assign-to-me" />',
									url: '${assignToMeTestraySubtaskURL}'
								},
								showLabel: true
							}
						).render();
					</aui:script>
				</dd>
				<dt>
					<liferay-ui:message key="updated" />
				</dt>
				<dd>
					<testray:date
						relative="${true}"
						value="${testraySubtaskComposite.modifiedDate}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="issues" />
				</dt>
				<dd>
					<c:set value="${testraySubtaskComposite.issues}" var="issues" />
					<c:set value="${true}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
				</dd>
				<dt>
					<liferay-ui:message key="comment" />
				</dt>
				<dd>
					<c:choose>
						<c:when test="${not empty testraySubtaskComposite.comment}">
							${fn:escapeXml(testraySubtaskComposite.comment)}

							<div class="comment-metadata">
								${fn:escapeXml(testraySubtaskComposite.commentAuthorFullName)} &#183;

								<testray:date
									relative="${true}"
									value="${testraySubtaskComposite.commentDate}"
								/>
							</div>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="none" />
						</c:otherwise>
					</c:choose>
				</dd>
			</dl>
		</aui:col>

		<aui:col lg="8" md="7" sm="12">
			<dl class="data-list dl-horizontal">
				<dt>
					<liferay-ui:message key="score" />
				</dt>
				<dd>
					<span class="score-text">
						${testraySubtaskComposite.score}
					</span>
				</dd>
				<dt>
					<liferay-ui:message key="error" />
				</dt>
				<dd>
					<pre>${fn:escapeXml(testraySubtaskComposite.errors)}</pre>
				</dd>

				<c:if test="${not empty testraySubtaskComposite.mergeeTestraySubtaskComposites}">
					<dt>
						<liferay-ui:message key="merged-with" />
					</dt>
					<dd>
						<c:forEach items="${testraySubtaskComposite.mergeeTestraySubtaskComposites}" var="mergeeTestraySubtaskComposite" varStatus="mergeeStatus">
							<c:out value="${mergeeTestraySubtaskComposite.name}" />${(!mergeeStatus.last) ? StringPool.COMMA_AND_SPACE : StringPool.BLANK}
						</c:forEach>
					</dd>
				</c:if>

				<c:if test="${not empty testraySubtaskComposite.parentTestraySubtaskComposite}">
					<dt>
						<liferay-ui:message key="split-from" />
					</dt>
					<dd>
						${testraySubtaskComposite.parentTestraySubtaskComposite.name}
					</dd>
				</c:if>

				<c:if test="${not empty testraySubtaskComposite.childrenTestraySubtaskComposites}">
					<dt>
						<liferay-ui:message key="split-to" />
					</dt>
					<dd>
						<c:forEach items="${testraySubtaskComposite.childrenTestraySubtaskComposites}" var="childTestraySubtaskComposite" varStatus="childrenStatus">
							<c:out value="${childTestraySubtaskComposite.name}" />${(!childrenStatus.last) ? StringPool.COMMA_AND_SPACE : StringPool.BLANK}
						</c:forEach>
					</dd>
				</c:if>
			</dl>
		</aui:col>
	</aui:row>
</div>

<div class="testray-card">
	<c:set value="${testrayCaseResultsCount},${testrayCaseResultsTotalCount}" var="argumentsString" />
	<c:set value="${fn:split(argumentsString, StringPool.COMMA)}" var="arguments" />

	<testray:table-toolbar
		count='${AlloyLanguageUtil.format("showing-x-of-x-total-tests", arguments)}'
		title="tests"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/subtasks/view_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayCaseResultConstants.COLUMN_LABELS_SUBTASK}"
			columnLabelsDefault="${TestrayCaseResultConstants.COLUMN_LABELS_SUBTASK_DEFAULT}"
			sessionKey="testraySubtasksViewColumns"
		/>
	</testray:table-toolbar>

	<div class="inactive row-checker-toolbar-container" id="${htmlNamespace}caseResultsRowCheckerToolbar">
		<div class="row-checker-toolbar-content">
			<span class="selected-counter">
				<span class="item-count"></span>

				<liferay-ui:message key="selected" />
			</span>
			<span class="actions-container">
				<c:set value='${AlloyLanguageUtil.get("deselect-items")}' var="clearButtonTitle" />

				<aui:button cssClass="testray-tooltip-trigger" name="rowCheckerActionClear" title="${clearButtonTitle}" value="clear" />

				<c:set value='${AlloyLanguageUtil.get("move-selected-tests-to-a-new-subtask")}' var="splitButtonTitle" />

				<aui:button cssClass="testray-tooltip-trigger" name="rowCheckerActionSplit" primary="${true}" title="${splitButtonTitle}" value="split-tests" />
			</span>
		</div>
	</div>

	<c:set value='${testrayPermission:containsModelAction(themeDisplay, testraySubtaskComposite.testraySubtask, "split") ? rowChecker : StringPool.BLANK}' var="splitRowChecker" />

	<div class="table-row-checker table-row-checker-hide-check-all" id="${htmlNamespace}caseResultsRowCheckerSearchContainer">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-case-results"
			id="caseResultsSearchContainer"
			iteratorURL="${portletURL}"
			orderByCol="${orderByCol}"
			orderByType="${orderByType}"
			rowChecker="${splitRowChecker}"
			total="${testrayCaseResultsCount}"
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
				</portlet:renderURL>

				<c:if test="${testraySubtasksViewColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_RUN)}">
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

				<c:if test="${testraySubtasksViewColumns.contains(TestrayCaseConstants.COLUMN_LABEL_PRIORITY)}">
					<liferay-ui:search-container-column-text
						cssClass="valign-top"
						href="${viewTestrayCaseResultURL}"
						orderable="${true}"
						orderableProperty="testrayCasePriority_sortable"
						property="priority"
					/>
				</c:if>

				<c:if test="${testraySubtasksViewColumns.contains(TestrayCaseConstants.COLUMN_LABEL_TEAM)}">
					<liferay-ui:search-container-column-text
						cssClass="valign-top"
						href="${viewTestrayCaseResultURL}"
						name="team"
						orderable="${true}"
						orderableProperty="testrayTeamName_sortable"
						property="testrayTeamName"
					/>
				</c:if>

				<c:if test="${testraySubtasksViewColumns.contains(TestrayCaseConstants.COLUMN_LABEL_COMPONENT)}">
					<liferay-ui:search-container-column-text
						cssClass="valign-top"
						href="${viewTestrayCaseResultURL}"
						name="component"
						orderable="${true}"
						orderableProperty="testrayComponentName_sortable"
						property="testrayComponentName"
					/>
				</c:if>

				<liferay-ui:search-container-column-text
					cssClass="table-column-main valign-top"
					href="${viewTestrayCaseResultURL}"
					name="case"
					orderable="${true}"
					orderableProperty="testrayCaseName_sortable"
					property="testrayCaseName"
				/>

				<c:if test="${testraySubtasksViewColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_ISSUES)}">
					<liferay-ui:search-container-column-text
						cssClass="valign-top"
						name="issues"
					>
						<c:set value="${testrayCaseResultComposite.issues}" var="issues" />
						<c:set value="${false}" var="showViewAllLink" />

						<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
					</liferay-ui:search-container-column-text>
				</c:if>

				<c:if test="${testraySubtasksViewColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_STATUS)}">
					<liferay-ui:search-container-column-text
						cssClass="valign-top"
						name="status"
						orderable="${true}"
						orderableProperty="status_sortable"
					>
						<aui:a cssClass="status ${testrayCaseResultComposite.statusLabel}" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.statusLabel}" />
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<c:if test='${testrayPermission:containsModelAction(themeDisplay, testraySubtaskComposite.testraySubtask, "split")}'>
	<aui:script use="testray-row-checker-toolbar">
		new Liferay.Testray.RowCheckerToolbar(
			{
				actionSplit: {
					button: '#rowCheckerActionSplit',
					data: {
						assignedUserId: ${testraySubtaskComposite.assignedUserId},
						subtaskName: '${testraySubtaskComposite.name}',
						subtaskStatus: '${testraySubtaskComposite.statusLabel}',
						testrayCaseResultsTotalCount: <%= renderRequest.getAttribute("testrayCaseResultsTotalCount") %>
					},
					requestConfig: {
						controller: 'subtasks',
						controllerMethod: 'split.json',
						params: {
							name: '${testraySubtaskComposite.name}',
							testrayTaskId: ${testraySubtaskComposite.testrayTaskId}
						}
					}
				},
				alertContainerId: '#${htmlNamespace}testrayAlertContainer',
				container: '#${htmlNamespace}caseResultsRowCheckerToolbar',
				searchContainerId: 'caseResultsSearchContainer',
				searchContainerWrapper: '#${htmlNamespace}caseResultsRowCheckerSearchContainer',
				selectAllCheckbox: '#${htmlNamespace}caseResultsRowCheckerSearchContainer [name="allRowIds"]'
			}
		).render();
	</aui:script>
</c:if>