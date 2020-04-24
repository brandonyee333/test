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

<liferay-util:include page="/alloy_mvc/jsp/testray/views/case_results/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tab" value="result" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="updateTestrayCaseResultStatusURL">
	<portlet:param name="controller" value="case_results" />
	<portlet:param name="action" value="updateStatus" />
</portlet:actionURL>

<aui:button-row cssClass="button-row-top">
	<c:set value='${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "delete")}' var="administrationPermitted" />

	<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "updateUser")}'>
		<c:set value="${((testrayCaseResultComposite.assignedUserId > 0) && (testrayCaseResultComposite.assignedUserId != themeDisplay.userId) && !administrationPermitted) || (testrayCaseResultComposite.status == TestrayCaseResultConstants.STATUS_IN_PROGRESS)}" var="assigningDisabled" />

		<portlet:renderURL var="viewUsersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="controller" value="users" />
			<portlet:param name="action" value="select" />
			<portlet:param name="submitIdName" value="id" />
			<portlet:param name="submitIdValue" value="${testrayCaseResultComposite.testrayCaseResultId}" />
		</portlet:renderURL>

		<c:set value='${AlloyLanguageUtil.getUnicode("users")}' var="viewUsersURLTitle" />

		<c:set value="javascript:Liferay.Testray.openWindow('${viewUsersURL}', '${viewUsersURLTitle}', 1000)" var="viewUsersURL" />

		<aui:button disabled="${assigningDisabled}" onClick="${viewUsersURL}" primary="${true}" value="assign" />

		<portlet:actionURL var="assignTestrayCaseResultURL">
			<portlet:param name="controller" value="case_results" />
			<portlet:param name="action" value="updateUser" />
			<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
			<portlet:param name="redirect" value="${portletURL}" />
			<portlet:param name="assignedUserId" value="${(testrayCaseResultComposite.assignedUserId != themeDisplay.userId) ? themeDisplay.userId : 0}" />
		</portlet:actionURL>

		<aui:button disabled="${assigningDisabled}" href="${assignTestrayCaseResultURL}" value='${(testrayCaseResultComposite.assignedUserId != themeDisplay.userId) ? "assign-to-me" : "unassign-myself"}' />
	</c:if>

	<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "updateStatus")}'>
		<c:set value="${(testrayCaseResultComposite.assignedUserId <= 0) || (testrayCaseResultComposite.assignedUserId != themeDisplay.userId)}" var="workflowDisabled" />

		<portlet:actionURL var="startTestrayCaseResultURL">
			<portlet:param name="controller" value="case_results" />
			<portlet:param name="action" value="updateStatus" />
			<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
			<portlet:param name="redirect" value="${portletURL}" />
			<portlet:param name="status" value="${TestrayCaseResultConstants.STATUS_IN_PROGRESS}" />
		</portlet:actionURL>

		<aui:button disabled="${workflowDisabled || (testrayCaseResultComposite.status != TestrayCaseResultConstants.STATUS_UNTESTED)}" href="${startTestrayCaseResultURL}" value="start-test" />

		<portlet:renderURL var="editTestrayCaseResultURL">
			<portlet:param name="controller" value="case_results" />
			<portlet:param name="action" value="edit" />
			<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
			<portlet:param name="redirect" value="${portletURL}" />
		</portlet:renderURL>

		<aui:button disabled="${workflowDisabled || (testrayCaseResultComposite.status != TestrayCaseResultConstants.STATUS_IN_PROGRESS)}" href="${editTestrayCaseResultURL}" type="submit" value="complete-test" />

		<portlet:actionURL var="reopenTestrayCaseResultURL">
			<portlet:param name="controller" value="case_results" />
			<portlet:param name="action" value="updateStatus" />
			<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
			<portlet:param name="redirect" value="${portletURL}" />
			<portlet:param name="status" value="${TestrayCaseResultConstants.STATUS_IN_PROGRESS}" />
		</portlet:actionURL>

		<aui:button disabled="${workflowDisabled || ((testrayCaseResultComposite.status != TestrayCaseResultConstants.STATUS_BLOCKED) && (testrayCaseResultComposite.status != TestrayCaseResultConstants.STATUS_FAILED) && (testrayCaseResultComposite.status != TestrayCaseResultConstants.STATUS_PASSED) && (testrayCaseResultComposite.status != TestrayCaseResultConstants.STATUS_TEST_FIX))}" href="${reopenTestrayCaseResultURL}" value="reopen-test" />
	</c:if>

	<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayCaseResultComposite.testrayCaseResult, "edit")}'>
		<aui:button disabled="${assigningDisabled}" href="${editTestrayCaseResultURL}" value="edit" />
	</c:if>

	<c:if test="${administrationPermitted && (testrayCaseResultComposite.status == TestrayCaseResultConstants.STATUS_IN_PROGRESS)}">
		<portlet:actionURL var="resetTestrayCaseResultURL">
			<portlet:param name="controller" value="case_results" />
			<portlet:param name="action" value="updateUser" />
			<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseResultId}" />
			<portlet:param name="redirect" value="${portletURL}" />
		</portlet:actionURL>

		<aui:button href="${resetTestrayCaseResultURL}" value="reset-test" />
	</c:if>
</aui:button-row>

<aui:row>
	<aui:col md="9">
		<div class="testray-card testray-data-section">
			<h2 class="testray-data-header">
				<liferay-ui:message key="test-details" />
			</h2>

			<dl class="data-list dl-horizontal testray-data-content">
				<dt>
					<liferay-ui:message key="status" />
				</dt>
				<dd>
					<span class="status ${testrayCaseResultComposite.statusLabel}">
						<liferay-ui:message key="${testrayCaseResultComposite.statusLabel}" />
					</span>
				</dd>
				<dt>
					<liferay-ui:message key="errors" />
				</dt>
				<dd>
					<pre>${fn:escapeXml(testrayCaseResultComposite.errors)}</pre>
				</dd>
				<dt>
					<liferay-ui:message arguments="${testrayCaseResultWarningsCount}" key="warnings-x" />
				</dt>
				<dd>
					<c:choose>
						<c:when test="${not empty testrayCaseResultComposite.testrayCaseResultWarnings}">
							<c:set value="${fn:split(testrayCaseResultWarningsCount, StringPool.COMMA)}" var="arguments" />

							<c:set value='${AlloyLanguageUtil.format("show-x-warnings", arguments)}' var="toggleShowWarningsMessage" />

							<c:set value='${AlloyLanguageUtil.format("hide-x-warnings", arguments)}' var="toggleHideWarningsMessage" />

							<liferay-ui:toggle-area
								align="none"
								defaultShowContent="${false}"
								hideMessage="${toggleHideWarningsMessage}"
								id="testrayCaseResultWarningsToggleArea${testrayCaseResultComposite.testrayCaseResultId}"
								showMessage="${toggleShowWarningsMessage}"
							>
								<c:forEach items="${testrayCaseResultComposite.testrayCaseResultWarnings}" var="testrayCaseResultWarning">
									<pre class="warning">${fn:escapeXml(testrayCaseResultWarning.content)}</pre>
								</c:forEach>
							</liferay-ui:toggle-area>
						</c:when>
						<c:otherwise>
							${StringPool.DASH}
						</c:otherwise>
					</c:choose>
				</dd>
				<dt>
					<liferay-ui:message key="attachments" />

					<c:if test='${testrayPermission:containsControllerAction(themeDisplay, "attachments", "create")}'>
						<portlet:renderURL var="createAttachmentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="controller" value="attachments" />
							<portlet:param name="action" value="create" />
							<portlet:param name="testrayCaseResultId" value="${testrayCaseResultComposite.testrayCaseResultId}" />
						</portlet:renderURL>

						<c:set var="createAttachmentURLTitle">
							<liferay-ui:message key="new-attachment" unicode="${true}" />
						</c:set>

						<c:set value="javascript:Liferay.Testray.openWindow('${createAttachmentURL}', '${createAttachmentURLTitle}', 800, 600)" var="createAttachmentURL" />

						<liferay-ui:icon
							cssClass="add-attachment"
							iconCssClass="icon-plus-sign-2"
							message="add-attachment"
							method="get"
							onClick="${createAttachmentURL}"
							url="javascript:;"
						/>
					</c:if>
				</dt>
				<dd>
					<ul class="list-unstyled">
						<c:forEach items="${testrayCaseResultComposite.attachmentsMap}" var="attachmentEntry">
							<li>
								<aui:a href="${attachmentEntry.value}" label="${fn:escapeXml(attachmentEntry.key)}" target="_blank" />
							</li>
						</c:forEach>
					</ul>
				</dd>
				<dt>
					<liferay-ui:message key="git-hash" />
				</dt>
				<dd>
					${testrayCaseResultComposite.testrayBuildGitHash}
				</dd>

				<c:if test="${PortletPropsValues.TESTRAY_GITHUB_COMPARE_URLS_ENABLED}">
					<dt>
						<liferay-ui:message key="github-compare-urls" />
					</dt>
					<dd>
						<ul class="list-unstyled">
							<c:forEach items="${testrayCaseResultComposite.testrayBuildGithubCompareURLs}" var="testrayBuildGithubCompareURL">
								<li>
									<a href="${testrayBuildGithubCompareURL}" target="_blank">
										${testrayBuildGithubCompareURL}
									</a>
								</li>
							</c:forEach>
						</ul>
					</dd>
				</c:if>
			</dl>
		</div>

		<div class="testray-card testray-data-section">
			<h2 class="testray-data-header">
				<liferay-ui:message key="case-details" />
			</h2>

			<dl class="data-list dl-horizontal testray-data-content">
				<dt>
					<liferay-ui:message key="priority" />
				</dt>
				<dd>
					${testrayCaseResultComposite.priority}
				</dd>
				<dt>
					<liferay-ui:message key="main-component" />
				</dt>
				<dd>
					${testrayCaseResultComposite.testrayComponentName}
				</dd>
				<dt>
					<liferay-ui:message key="subcomponents" />
				</dt>
				<dd>
					${testrayCaseResultComposite.testrayComponentNames}
				</dd>
				<dt>
					<liferay-ui:message key="type" />
				</dt>
				<dd>
					${testrayCaseResultComposite.testrayCaseTypeName}
				</dd>
				<dt>
					<liferay-ui:message key="estimated-duration" />
				</dt>
				<dd>
					${testrayCaseResultComposite.estimatedDuration}
				</dd>
				<dt>
					<liferay-ui:message key="description" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayCaseResultComposite.testrayCaseDescriptionType}"
						value="${testrayCaseResultComposite.testrayCaseDescription}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="steps" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayCaseResultComposite.stepsType}"
						value="${testrayCaseResultComposite.steps}"
					/>
				</dd>
			</dl>

			<div class="testray-data-content-link">
				<portlet:renderURL var="viewTestrayCaseURL">
					<portlet:param name="controller" value="cases" />
					<portlet:param name="action" value="view" />
					<portlet:param name="id" value="${testrayCaseResultComposite.testrayCaseId}" />
				</portlet:renderURL>

				<aui:a href="${viewTestrayCaseURL}" label="view-case" />
			</div>
		</div>
	</aui:col>

	<aui:col md="3">
		<div class="testray-card">
			<div class="testray-data-section">
				<h3 class="testray-data-header">
					<liferay-ui:message key="dates" />
				</h3>

				<dl class="data-list testray-data-content">
					<dt>
						<liferay-ui:message key="updated" />
					</dt>
					<dd>
						<testray:date
							relative="${true}"
							value="${testrayCaseResultComposite.modifiedDate}"
						/>
					</dd>
					<dt>
						<liferay-ui:message key="execution-date" />
					</dt>
					<dd>
						<testray:date
							relative="${true}"
							value="${testrayCaseResultComposite.closedDate}"
						/>
					</dd>
				</dl>
			</div>

			<div class="testray-data-divider"></div>

			<div class="testray-data-section">
				<h3 class="testray-data-header">
					<liferay-ui:message key="assignee" />
				</h3>

				<div class="testray-data-content" id="${htmlNamespace}assignedUserSection">
					<div id="${htmlNamespace}assigneeContent">
						<div class="avatar-container" id="${htmlNamespace}avatarContainer${testrayCaseResultComposite.testrayCaseResultId}">${testrayCaseResultComposite.assignedUserFullName}</div>

						<aui:script use="testray-avatar">
							var testrayCaseResultJSON = ${testrayCaseResultComposite.getJSONObject()};

							new Liferay.Testray.Avatar(
								{
									container: '#${htmlNamespace}avatarContainer${testrayCaseResultComposite.testrayCaseResultId}',
									data: [testrayCaseResultJSON.assignedUser],
									emptyLinkConfig: {
										iconCssClass: 'user',
										label: '<liferay-ui:message key="assign-to-me" />',
										url: '${assignTestrayCaseResultURL}'
									},
									showLabel: true
								}
							).render();
						</aui:script>
					</div>
				</div>
			</div>

			<div class="testray-data-divider"></div>

			<div class="testray-data-section">
				<h3 class="testray-data-header">
					<liferay-ui:message key="issues" />
				</h3>

				<div class="testray-data-content">
					<c:set value="${testrayCaseResultComposite.issues}" var="issues" />
					<c:set value="${true}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
				</div>
			</div>

			<div class="testray-data-divider"></div>

			<div class="testray-data-section">
				<h3 class="testray-data-header">
					<liferay-ui:message key="comment" />
				</h3>

				<div class="testray-data-content">
					<c:choose>
						<c:when test="${not empty testrayCaseResultComposite.comment}">
							<div class="comment">
								${fn:escapeXml(testrayCaseResultComposite.comment)}
							</div>

							<div class="comment-metadata">
								${fn:escapeXml(testrayCaseResultComposite.commentAuthorFullName)} &#183;

								<testray:date
									relative="${true}"
									value="${testrayCaseResultComposite.commentDate}"
								/>
							</div>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="none" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</aui:col>
</aui:row>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

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
</aui:script>