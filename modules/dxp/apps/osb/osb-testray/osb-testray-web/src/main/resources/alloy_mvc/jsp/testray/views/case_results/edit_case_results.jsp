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
	<liferay-util:param name="title" value="bulk-operation" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="updateTestrayCaseResultsBulkURL">
	<portlet:param name="controller" value="bulk" />
	<portlet:param name="action" value="updateCaseResults" />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${updateTestrayCaseResultsBulkURL}" method="post" name="fm" onSubmit="${htmlNamespace}setRedirectParams();">
		<aui:input name="redirect" type="hidden" value="${redirect}" />

		<aui:row>
			<aui:col span="<%= 4 %>">
				<aui:fieldset cssClass="bulk-input-option">
					<aui:input cssClass="toggle-edit-section" data-toggle-section="statusSection" label="status" name="editStatus" type="checkbox" />

					<aui:fieldset cssClass="bulk-input-section hide" id="statusSection">
						<aui:select disabled="${true}" label="" name="status" showEmptyOption="${false}">
							<aui:option label="${TestrayCaseResultConstants.LABEL_PASSED}" value="${TestrayCaseResultConstants.STATUS_PASSED}" />
							<aui:option label="${TestrayCaseResultConstants.LABEL_FAILED}" value="${TestrayCaseResultConstants.STATUS_FAILED}" />
							<aui:option label="${TestrayCaseResultConstants.LABEL_BLOCKED}" value="${TestrayCaseResultConstants.STATUS_BLOCKED}" />
							<aui:option label="${TestrayCaseResultConstants.LABEL_TEST_FIX}" value="${TestrayCaseResultConstants.STATUS_TEST_FIX}" />
						</aui:select>
					</aui:fieldset>
				</aui:fieldset>

				<aui:fieldset cssClass="bulk-input-option">
					<aui:input cssClass="toggle-edit-section" data-toggle-section="assignedUserSection" label="assignee" name="editAssignee" type="checkbox" value="${testrayCaseResultBulkOperation.assignedUserId > 0}" />

					<aui:fieldset cssClass='bulk-input-section ${(testrayCaseResultBulkOperation.assignedUserId > 0) ? StringPool.BLANK : "hide"}' id="assignedUserSection">
						<div id="${htmlNamespace}assigneeContent">
							<aui:input inlineField="${true}" label="" name="assigneeUserName" readonly="true" type="text" value="${testrayCaseResultBulkOperation.assignedUserFullName}" />

							<portlet:renderURL var="selectAssigneeUserURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
								<portlet:param name="controller" value="users" />
								<portlet:param name="action" value="select" />
								<portlet:param name="submitAction" value="setAssigned" />
								<portlet:param name="submitController" value="bulk" />
							</portlet:renderURL>

							<c:set value='${AlloyLanguageUtil.getUnicode("select-assignee")}' var="selectAssigneeUserURLTitle" />

							<c:set value="javascript:Liferay.Testray.openWindow('${selectAssigneeUserURL}', '${selectAssigneeUserURLTitle}', 1000)" var="selectAssigneeUserURL" />

							<aui:button onClick="${selectAssigneeUserURL}" value="select-assignee" />
						</div>
					</aui:fieldset>
				</aui:fieldset>
			</aui:col>

			<aui:col span="<%= 4 %>">
				<aui:fieldset cssClass="bulk-input-option">
					<aui:input cssClass="toggle-edit-section" data-toggle-section="issuesSection" label="issues" name="editIssues" type="checkbox" />

					<aui:fieldset cssClass="bulk-input-section hide" id="issuesSection">
						<div class="replace-append-toggle-box">
							<aui:input checked="<%= true %>" disabled="<%= true %>" label="${TestrayCaseResultConstants.BULK_EDIT_LABEL_APPEND}" name="issuesType" type="radio" value="${TestrayCaseResultConstants.BULK_EDIT_TYPE_APPEND}" />
							<aui:input checked="<%= false %>" disabled="<%= true %>" label="${TestrayCaseResultConstants.BULK_EDIT_LABEL_REPLACE}" name="issuesType" type="radio" value="${TestrayCaseResultConstants.BULK_EDIT_TYPE_REPLACE}" />
							<aui:input checked="<%= false %>" disabled="<%= true %>" label="${TestrayCaseResultConstants.BULK_EDIT_LABEL_REMOVE}" name="issuesType" type="radio" value="${TestrayCaseResultConstants.BULK_EDIT_TYPE_REMOVE}" />
						</div>

						<aui:input cssClass="replace-append-textarea" disabled="${true}" label="" name="issues" type="textarea" />

						<div class="alert alert-warn hide" id="${htmlNamespace}issueWarning">
							<span id="${htmlNamespace}issueWarningMessage">
								<liferay-ui:message arguments="${testrayCaseResultBulkOperation.selectedTestrayCaseResultsCount}" key="you-are-about-to-erase-issues-for-all-x-cases" />
							</span>
						</div>
					</aui:fieldset>
				</aui:fieldset>

				<aui:fieldset cssClass="bulk-input-option">
					<aui:input cssClass="toggle-edit-section" data-toggle-section="commentSection" label="comment" name="editComment" type="checkbox" />

					<aui:fieldset cssClass="bulk-input-section hide" id="commentSection">
						<div class="replace-append-toggle-box">
							<aui:input checked="<%= true %>" disabled="<%= true %>" label="${TestrayCaseResultConstants.BULK_EDIT_LABEL_APPEND}" name="commentType" type="radio" value="${TestrayCaseResultConstants.BULK_EDIT_TYPE_APPEND}" />
							<aui:input checked="<%= false %>" disabled="<%= true %>" label="${TestrayCaseResultConstants.BULK_EDIT_LABEL_REPLACE}" name="commentType" type="radio" value="${TestrayCaseResultConstants.BULK_EDIT_TYPE_REPLACE}" />
						</div>

						<aui:input cssClass="replace-append-textarea" disabled="${true}" label="" name="comment" type="textarea" />

						<div class="alert alert-warn hide" id="${htmlNamespace}commentWarning">
							<span id="${htmlNamespace}commentWarningMessage">
								<liferay-ui:message arguments="${testrayCaseResultBulkOperation.selectedTestrayCaseResultsCount}" key="you-are-about-to-erase-comments-for-all-x-cases" />
							</span>
						</div>
					</aui:fieldset>
				</aui:fieldset>
			</aui:col>
		</aui:row>

		<aui:button-row>
			<portlet:renderURL var="selectTestrayCaseResultsBulkURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="bulk" />
				<portlet:param name="action" value="selectCaseResults" />
				<portlet:param name="testrayRunId" value="${testrayRunId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("select-case-results")}' var="selectTestrayCaseResultsBulkURLTitle" />

			<c:set value="Liferay.Testray.openWindow('${selectTestrayCaseResultsBulkURL}', '${selectTestrayCaseResultsBulkURLTitle}', 2000)" var="selectTestrayCaseResultsBulkURL" />

			<aui:button onClick="${selectTestrayCaseResultsBulkURL}" value="select-more-cases" />

			<aui:button type="submit" value="confirm" />

			<portlet:actionURL var="cancelBulkOperationURL">
				<portlet:param name="controller" value="bulk" />
				<portlet:param name="action" value="cancelBulkOperation" />
				<portlet:param name="redirect" value="${redirect}" />
			</portlet:actionURL>

			<aui:button href="${cancelBulkOperationURL}" value="cancel" />
		</aui:button-row>
	</aui:form>

	<div id="${htmlNamespace}selectedTestrayCaseResultsSection">
		<div id="${htmlNamespace}selectedTestrayCaseResultsContent">
			<c:if test="${testrayCaseResultBulkOperation.selectedTestrayCaseResultsCount > 0}">
				<div class="alert alert-info">
					<liferay-ui:message arguments="${testrayCaseResultBulkOperation.selectedTestrayCaseResultsCount}" key="bulk-operation-will-be-applied-to-x-cases-that-are-now-assigned-to-you" />
				</div>
			</c:if>

			<portlet:renderURL var="editTestrayCaseResultsBulkURL">
				<portlet:param name="controller" value="bulk" />
				<portlet:param name="action" value="editCaseResults" />
				<portlet:param name="redirect" value="${redirect}" />
				<portlet:param name="testrayRunId" value="${testrayRunId}" />
			</portlet:renderURL>

			<liferay-ui:tabs
				names="cases"
				url0="${editTestrayCaseResultsBulkURL}"
			/>

			<liferay-ui:search-container
				emptyResultsMessage="there-are-no-case-results"
				id="selectedTestrayCaseResults"
				iteratorURL="${portletURL}"
				orderByCol="${orderByCol}"
				orderByType="${orderByType}"
				rowChecker="${rowChecker}"
				total="${testrayCaseResultBulkOperation.selectedTestrayCaseResultsCount}"
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
						href="${viewTestrayCaseResultURL}"
						orderable="${true}"
						property="priority"
						target="_blank"
					/>

					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultURL}"
						name="component"
						orderable="${true}"
						orderableProperty="testrayComponentName"
						property="testrayComponentName"
						target="_blank"
					/>

					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultURL}"
						name="case"
						orderable="${true}"
						property="testrayCaseName"
						target="_blank"
					/>

					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultURL}"
						name="assignee"
						orderable="${true}"
						orderableProperty="assignedUserFullName_sortable"
						property="assignedUserFullName"
						target="_blank"
					/>

					<liferay-ui:search-container-column-text
						name="status"
						orderable="${true}"
						orderableProperty="statusLabel"
					>
						<aui:a cssClass="status ${testrayCaseResultComposite.statusLabel}" href="${viewTestrayCaseResultURL}" label="${testrayCaseResultComposite.statusLabel}" />
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultURL}"
						name="warnings"
						property="testrayCaseResultWarningsCount"
						target="_blank"
					/>

					<liferay-ui:search-container-column-text
						name="issues"
					>
						<c:set value="${testrayCaseResultComposite.issues}" var="issues" />
						<c:set value="${false}" var="showViewAllLink" />

						<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="${viewTestrayCaseResultURL}"
						name="comment"
						orderable="${true}"
						target="_blank"
						value="${fn:escapeXml(testrayCaseResultComposite.comment)}"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-column-errors"
						name="errors"
						orderable="${true}"
					>
						<pre>${fn:escapeXml(testrayCaseResultComposite.errors)}</pre>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						align="right"
					>
						<liferay-ui:icon-menu
							icon=""
							message=""
						>
							<c:if test='${testrayPermission:containsControllerAction(themeDisplay, "bulk", "unsetCaseResult")}'>
								<portlet:actionURL var="unsetTestrayCaseResultBulkURL">
									<portlet:param name="controller" value="bulk" />
									<portlet:param name="action" value="unsetCaseResult" />
									<portlet:param name="redirect" value="${portletURL}" />
									<portlet:param name="testrayCaseResultId" value="${testrayCaseResultComposite.testrayCaseResultId}" />
								</portlet:actionURL>

								<liferay-ui:icon
									image="delete"
									message="remove"
									url="${unsetTestrayCaseResultBulkURL}"
								/>
							</c:if>
						</liferay-ui:icon-menu>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="aui-node">
	var form = A.one('#${htmlNamespace}fm');

	if (form) {
		form.all('.bulk-input-option input').on(
			'click',
			function(event) {
				${htmlNamespace}setBulkInputOptionAlert(event.target);
			}
		);

		form.all('input.toggle-edit-section').on(
			'change',
			function(event) {
				var checkbox = event.target;

				var section = A.one('#${htmlNamespace}' + checkbox.getData('toggle-section'));

				if (section) {
					var checked = checkbox.get('checked');

					var inputs = section.all(':input');

					Liferay.Util.toggleDisabled(inputs, !checked);

					section.toggle(checked);

					if (checked) {
						var textarea = section.one('textarea');

						if (textarea) {
							Liferay.Util.focusFormField(textarea);
						}
					}
				}
			}
		);

		form.all('.replace-append-textarea').on(
			'keyup',
			function(event) {
				${htmlNamespace}setBulkInputOptionAlert(event.target);
			}
		);
	}

	var emptyResultsMessage = A.one('#${htmlNamespace}selectedTestrayCaseResultsEmptyResultsMessage');

	if (emptyResultsMessage && !emptyResultsMessage.hasClass('hide')) {
		${selectTestrayCaseResultsBulkURL};
	}
</aui:script>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}refreshAssignedUserSection',
		function() {
			var A = AUI();

			var currentURL = Liferay.currentURL;

			var assignedUserSectionNode = A.one('#${htmlNamespace}assignedUserSection');

			if (assignedUserSectionNode) {
				if (!assignedUserSectionNode.hasPlugin('io')) {
					assignedUserSectionNode.load(
						currentURL + ' #${htmlNamespace}assigneeContent',
						{
							autoLoad: false,
							method: 'GET'
						}
					);
				}

				assignedUserSectionNode.io.start();
			}
		},
		['aui-base', 'aui-io-plugin-deprecated']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}refreshSelectedTestrayCaseResults',
		function() {
			var A = AUI();

			var currentURL = Liferay.currentURL;

			var selectedTestrayCaseResultsNode = A.one('#${htmlNamespace}selectedTestrayCaseResultsSection');

			if (selectedTestrayCaseResultsNode) {
				if (!selectedTestrayCaseResultsNode.hasPlugin('io')) {
					selectedTestrayCaseResultsNode.load(
						currentURL + ' #${htmlNamespace}selectedTestrayCaseResultsContent',
						{
							autoLoad: false,
							method: 'GET'
						}
					);
				}

				selectedTestrayCaseResultsNode.io.start();
			}

			var commentWarningNode = A.one('#${htmlNamespace}commentWarning');

			if (commentWarningNode) {
				if (!commentWarningNode.hasPlugin('io')) {
					commentWarningNode.load(
						currentURL + ' #${htmlNamespace}commentWarningMessage',
						{
							autoLoad: false,
							method: 'GET'
						}
					);
				}

				commentWarningNode.io.start();
			}

			var issueWarningNode = A.one('#${htmlNamespace}issueWarning');

			if (issueWarningNode) {
				if (!issueWarningNode.hasPlugin('io')) {
					issueWarningNode.load(
						currentURL + ' #${htmlNamespace}issueWarningMessage',
						{
							autoLoad: false,
							method: 'GET'
						}
					);
				}

				issueWarningNode.io.start();
			}
		},
		['aui-base', 'aui-io-plugin-deprecated']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}setBulkInputOptionAlert',
		function(target) {
			var A = AUI();

			A.all('input[type=radio]:checked').each(
				function(node) {
					node.ancestor('label').addClass('selected');
				}
			);

			A.all('input[type=radio]:not(:checked)').each(
				function(node) {
					node.ancestor('label').removeClass('selected');
				}
			);

			var bulkInputSection = target.ancestor('.bulk-input-section');

			if (bulkInputSection && bulkInputSection.one('.selected')) {
				var bulkEditTypeInput = bulkInputSection.one('input:checked');
				var replaceAppendTextarea = bulkInputSection.one('.replace-append-textarea');

				if (bulkEditTypeInput && replaceAppendTextarea) {
					var bulkEditType = Number(bulkEditTypeInput.val());

					var alert = bulkInputSection.one('.alert');

					if (alert) {
						var toggleVal = !replaceAppendTextarea.val() && bulkEditType === ${TestrayCaseResultConstants.BULK_EDIT_TYPE_REPLACE};

						alert.toggle(toggleVal);
					}
				}
			}
		},
		['aui-node']
	);
</aui:script>