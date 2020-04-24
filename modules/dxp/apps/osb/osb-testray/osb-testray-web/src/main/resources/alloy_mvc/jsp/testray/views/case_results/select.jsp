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
	<liferay-util:param name="title" value="select-case-results" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:renderURL var="selectTestrayCaseResultsURL">
	<portlet:param name="controller" value="bulk" />
	<portlet:param name="action" value="selectCaseResults" />
</portlet:renderURL>

<aui:form action="${selectTestrayCaseResultsURL}" method="get" name="filterForm" onSubmit="event.preventDefault(); ${htmlNamespace}submitFilter();">
	<aui:input name="priorities" type="hidden" value="${param.priorities}" />
	<aui:input name="p_p_state" type="hidden" useNamespace="" value="<%= LiferayWindowState.POP_UP.toString() %>" />
	<aui:input name="search" type="hidden" value="${true}" />
	<aui:input name="testrayRunId" type="hidden" value="${param.testrayRunId}" />

	<aui:fieldset>
		<aui:row>
			<aui:col md="3">
				<aui:fieldset id="priority">
					<aui:field-wrapper cssClass="filter-priority-wrapper" label="priority" name="priority">
						<c:forEach items="${TestrayCaseConstants.PRIORITIES}" var="priority">
							<div class="filter-priority-checkbox-wrapper">
								<aui:input checked="${testrayCasePriorities.contains(priority)}" cssClass="filter-priority" ignoreRequestValue="${true}" label="${priority}" name="priority${priority}" type="checkbox" />
							</div>
						</c:forEach>
					</aui:field-wrapper>
				</aui:fieldset>
			</aui:col>

			<aui:col md="3">
				<aui:field-wrapper>
					<aui:select label="team" name="testrayTeamId">
						<aui:option label="" value="${-1}" />

						<c:forEach items="${testrayTeams}" var="testrayTeam">
							<aui:option label="${testrayTeam.name}" value="${testrayTeam.testrayTeamId}" />
						</c:forEach>
					</aui:select>
				</aui:field-wrapper>
			</aui:col>

			<aui:col md="3">
				<aui:field-wrapper>
					<aui:select label="component" name="testrayComponentId">
						<aui:option label="" value="${-1}" />

						<c:forEach items="${testrayComponents}" var="testrayComponent">
							<aui:option label="${testrayComponent.name}" value="${testrayComponent.testrayComponentId}" />
						</c:forEach>
					</aui:select>
				</aui:field-wrapper>
			</aui:col>

			<aui:col md="3">
				<aui:field-wrapper>
					<aui:input label="case-name" name="name" />
				</aui:field-wrapper>
			</aui:col>
		</aui:row>

		<aui:row>
			<aui:col md="3">
				<aui:field-wrapper>
					<aui:select cssClass="choices-select" label="status" multiple="${true}" name="statuses">
						<c:forEach items="${TestrayCaseResultConstants.STATUSES_ALL}" var="status">
							<aui:option label="${TestrayCaseResultConstants.getStatusLabel(status)}" selected="${selectedStatuses.contains(status)}" useModelValue="${false}" value="${status}" />
						</c:forEach>
					</aui:select>
				</aui:field-wrapper>

				<%@ include file="/alloy_mvc/jsp/testray/views/choices.jspf" %>
			</aui:col>

			<aui:col md="3">
				<aui:field-wrapper>
					<aui:input disabled="${param.issuesBlankOnly}" label="issues" name="issues" type="textarea" />

					<aui:input label="no-issues" name="issuesBlankOnly" onChange="Liferay.Util.toggleDisabled('#${htmlNamespace}issues', this.checked);" type="checkbox" value="${param.issuesBlankOnly}" />
				</aui:field-wrapper>
			</aui:col>

			<aui:col md="3">
				<aui:field-wrapper>
					<aui:input disabled="${param.errorsBlankOnly}" label="errors" name="errors" type="textarea" />

					<aui:input label="no-errors" name="errorsBlankOnly" onChange="Liferay.Util.toggleDisabled('#${htmlNamespace}errors', this.checked);" type="checkbox" value="${param.errorsBlankOnly}" />
				</aui:field-wrapper>
			</aui:col>

			<aui:col md="3">
				<aui:field-wrapper>
					<aui:input disabled="${param.commentsBlankOnly}" label="comments" name="comments" type="textarea" />

					<aui:input label="no-comments" name="commentsBlankOnly" onChange="Liferay.Util.toggleDisabled('#${htmlNamespace}comments', this.checked);" type="checkbox" value="${param.commentsBlankOnly}" />
				</aui:field-wrapper>
			</aui:col>
		</aui:row>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="search" />
		<aui:button onClick="Liferay.Testray.clearForm('${htmlNamespace}filterForm');" value="clear" />
	</aui:button-row>
</aui:form>

<c:if test="${param.search}">
	<div class="separator"></div>

	<div class="hide" id="${htmlNamespace}caseResultSearchAlertContainer">
		<div class="alert alert-error">
			<liferay-ui:message key="your-request-failed-to-complete" />
		</div>
	</div>

	<portlet:actionURL var="saveTestrayCaseResultSelectionBulkURL">
		<portlet:param name="controller" value="bulk" />
		<portlet:param name="action" value="setCaseResults" />
	</portlet:actionURL>

	<aui:form action="${saveTestrayCaseResultSelectionBulkURL}" method="post" name="selectCaseResultsFm" onSubmit="event.preventDefault(); ${htmlNamespace}submitCaseResults();">
		<aui:input name="redirect" type="hidden" value="${portletURL}" />

		<aui:input name="addTestrayCaseResultIds" type="hidden" value="" />

		<aui:button-row>
			<aui:button type="submit" value="assign-to-me-and-edit" />

			<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
		</aui:button-row>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-case-results"
			iteratorURL="${portletURL}"
			orderByCol="${orderByCol}"
			orderByType="${orderByType}"
			rowChecker="${rowChecker}"
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

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="priority"
					orderable="${true}"
				>
					<aui:a cssClass="clean-link" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.priority}" localizeLabel="${false}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="component"
					orderable="${true}"
					orderableProperty="testrayComponentName"
				>
					<aui:a cssClass="clean-link" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.testrayComponentName}" localizeLabel="${false}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="table-column-case-name valign-top"
					name="case"
					orderable="${true}"
					orderableProperty="testrayCaseName"
				>
					<aui:a cssClass="clean-link" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.testrayCaseName}" localizeLabel="${false}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="assignee"
					orderable="${true}"
					orderableProperty="assignedUserFullName_sortable"
				>
					<aui:a cssClass="clean-link" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.assignedUserFullName}" localizeLabel="${false}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="status"
					orderable="${true}"
					orderableProperty="statusLabel"
				>
					<aui:a cssClass="clean-link status ${testrayCaseResultComposite.statusLabel}" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.statusLabel}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="warnings"
				>
					<aui:a cssClass="clean-link" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.testrayCaseResultWarningsCount}" localizeLabel="${false}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="issues"
				>
					<c:set value="${testrayCaseResultComposite.issues}" var="issues" />
					<c:set value="${false}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="comment"
					orderable="${true}"
				>
					<aui:a cssClass="clean-link" href="${viewTestrayCaseResultURL}" label="${fn:escapeXml(testrayCaseResultComposite.comment)}" localizeLabel="${false}" target="blank" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="table-column-errors valign-top"
					name="errors"
					orderable="${true}"
				>
					<pre>${fn:escapeXml(testrayCaseResultComposite.errors)}</pre>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				paginate="${false}"
			/>
		</liferay-ui:search-container>
	</aui:form>
</c:if>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submitCaseResults',
		function() {
			document.${htmlNamespace}selectCaseResultsFm.${htmlNamespace}addTestrayCaseResultIds.value = Liferay.Util.listCheckedExcept(document.${htmlNamespace}selectCaseResultsFm, '${htmlNamespace}allRowIds');

			var A = AUI();

			var selectCaseResultsForm = A.one('#${htmlNamespace}selectCaseResultsFm');

			if (selectCaseResultsForm) {
				A.io.request(
					selectCaseResultsForm.attr('action'),
					{
						form: {
							id: selectCaseResultsForm.getDOM()
						},
						method: 'POST',
						on: {
							failure: function(event, id, obj) {
								var alertContainer = A.one('#${htmlNamespace}caseResultSearchAlertContainer');

								if (alertContainer) {
									alertContainer.show();
								}
							},
							success: function(event, id, obj) {
								var opener = Liferay.Util.getOpener();

								if (opener && opener.${htmlNamespace}refreshSelectedTestrayCaseResults) {
									opener.${htmlNamespace}refreshSelectedTestrayCaseResults();
								}

								Liferay.Testray.closeWindow();
							}
						}
					}
				);
			}
		},
		['aui-io-request', 'liferay-util-list-fields', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}submitFilter',
		function() {
			Liferay.Testray.combineCheckboxValues('${htmlNamespace}priority', 'priority', 'filter-priority', 'priorities');

			submitForm(document.${htmlNamespace}filterForm);
		},
		['testray-base']
	);

	AUI().ready(
		function() {
			var A = AUI();

			var cleanLinks = A.all('.clean-link');

			cleanLinks.each(
				function(cleanLink) {
					var href = cleanLink.attr('href');

					var index = href.indexOf('?');

					if (index >= 0) {
						cleanLink.set('href', href.substring(0, index));
					}
				}
			);
		}
	);
</aui:script>