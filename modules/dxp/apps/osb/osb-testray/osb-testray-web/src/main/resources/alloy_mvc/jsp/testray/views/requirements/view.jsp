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

<testray:header
	breadcrumbComposites="${testrayBreadcrumbComposites}"
	title="${testrayRequirementComposite.key}"
>
	<testray:dropdown
		cssClass="manage-page-dropdown"
		icon="ellipsis-vertical"
		label="manage-requirement"
	>
		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "edit")}'>
			<portlet:renderURL var="editTestrayRequirementURL">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="edit" />
				<portlet:param name="id" value="${testrayRequirementComposite.testrayRequirementId}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:renderURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="edit"
				label="edit-requirement"
				url="${editTestrayRequirementURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "sync")}'>
			<portlet:actionURL var="syncTestrayRequirementURL">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="sync" />
				<portlet:param name="id" value="${testrayRequirementComposite.testrayRequirementId}" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:actionURL>

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="refresh"
				label="resync-with-jira"
				url="${syncTestrayRequirementURL}"
			/>
		</c:if>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "delete")}'>
			<portlet:renderURL var="viewTestrayRequirementsURL">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="index" />
				<portlet:param name="testrayProjectId" value="${testrayRequirementComposite.testrayProjectId}" />
			</portlet:renderURL>

			<portlet:actionURL var="deleteTestrayRequirementURL">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="delete" />
				<portlet:param name="id" value="${testrayRequirementComposite.testrayRequirementId}" />
				<portlet:param name="redirect" value="${viewTestrayRequirementsURL}" />
			</portlet:actionURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("are-sure-you-want-to-delete-this-requirement-this-action-cannot-be-undone")}' var="deleteTestrayRequirementMessage" />

			<c:set value="javascript:Liferay.Testray.confirmDelete('${deleteTestrayRequirementURL}', '${deleteTestrayRequirementMessage}')" var="deleteTestrayRequirementURL" />

			<testray:dropdown-item
				cssClass="testray-menu-item"
				icon="trash"
				label="delete-requirement"
				onClick="${deleteTestrayRequirementURL}"
			/>
		</c:if>
	</testray:dropdown>
</testray:header>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card testray-card-metadata-panel">
	<liferay-ui:panel-container
		cssClass="metadata-panel"
	>
		<liferay-ui:panel
			collapsible="${false}"
			title="details"
		>
			<dl class="data-list dl-horizontal">
				<dt>
					<liferay-ui:message key="link" />
				</dt>
				<dd>
					<aui:a href="${testrayRequirementComposite.linkURL}" label="${fn:escapeXml(testrayRequirementComposite.linkTitle)}" target="_blank" />
				</dd>
				<dt>
					<liferay-ui:message key="team" />
				</dt>
				<dd>
					<c:out value="${testrayRequirementComposite.testrayTeamName}" />
				</dd>
				<dt>
					<liferay-ui:message key="component" />
				</dt>
				<dd>
					<c:out value="${testrayRequirementComposite.testrayComponentName}" />
				</dd>
				<dt>
					<liferay-ui:message key="jira-components" />
				</dt>
				<dd>
					<c:out value="${testrayRequirementComposite.components}" />
				</dd>
				<dt>
					<liferay-ui:message key="summary" />
				</dt>
				<dd>
					<c:out value="${testrayRequirementComposite.summary}" />
				</dd>
				<dt>
					<liferay-ui:message key="description" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayRequirementComposite.descriptionType}"
						value="${testrayRequirementComposite.description}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="goals" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayRequirementComposite.goalsType}"
						value="${testrayRequirementComposite.goals}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="variations" />
				</dt>
				<dd>
					<testray:rich-output
						type="${testrayRequirementComposite.variationsType}"
						value="${testrayRequirementComposite.variations}"
					/>
				</dd>
			</dl>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</div>

<div class="testray-card">
	<testray:table-toolbar
		title="cases"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/requirements/view_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayCaseConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayCaseConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayRequirementTestrayCasesColumns"
		/>

		<c:if test='${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "updateCases")}'>
			<portlet:renderURL var="selectTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="select" />
				<portlet:param name="testrayProjectId" value="${testrayRequirementComposite.testrayProjectId}" />
				<portlet:param name="testrayRequirementId" value="${testrayRequirementComposite.testrayRequirementId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("select-cases")}' var="selectTestrayCasesURLTitle" />

			<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayCasesURL}', '${selectTestrayCasesURLTitle}', 1000)" var="selectTestrayCasesURL" />

			<aui:button icon="icon-list" onClick="${selectTestrayCasesURL}" value="link-cases" />
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-cases"
		id="requirementCasesSearchContainer"
		iteratorURL="${portletURL}"
		total="${testrayCaseCompositesTotal}"
	>
		<liferay-ui:search-container-results
			results="${testrayCaseComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayCaseId"
			modelVar="testrayCaseComposite"
		>
			<portlet:renderURL var="viewTestrayCaseURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="view" />
				<portlet:param name="id" value="${testrayCaseComposite.testrayCaseId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "view", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseConstants.COLUMN_LABEL_PRIORITY)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					orderable="${true}"
					orderableProperty="testrayCasePriority_sortable"
					property="priority"
				/>
			</c:if>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseConstants.COLUMN_LABEL_CASE_TYPE)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="case-type"
					orderable="${true}"
					orderableProperty="testrayCaseTypeName_sortable"
					property="testrayCaseTypeName"
				/>
			</c:if>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseConstants.COLUMN_LABEL_NAME)}">
				<liferay-ui:search-container-column-text
					cssClass="table-column-main"
					href="${viewTestrayCaseURL}"
					name="case-name"
					orderable="${true}"
					orderableProperty="name_sortable"
					property="name"
				/>
			</c:if>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseConstants.COLUMN_LABEL_TEAM)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="team"
					orderable="${true}"
					orderableProperty="testrayTeamName_sortable"
					property="testrayTeamName"
				/>
			</c:if>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseConstants.COLUMN_LABEL_COMPONENT)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="component"
					orderable="${true}"
					orderableProperty="testrayComponentName_sortable"
					property="testrayComponentName"
				/>
			</c:if>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseConstants.COLUMN_LABEL_DESCRIPTION)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					orderable="${true}"
					orderableProperty="description_sortable"
				>
					<testray:rich-output
						type="${testrayCaseComposite.descriptionType}"
						value="${testrayCaseComposite.description}"
					/>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayRequirementTestrayCasesColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_ISSUES)}">
				<liferay-ui:search-container-column-text
					cssClass="valign-top"
					name="issues"
					orderable="${true}"
					orderableProperty="issues_sortable"
				>
					<c:set value="${testrayCaseComposite.issues}" var="issues" />
					<c:set value="${false}" var="showViewAllLink" />

					<%@ include file="/alloy_mvc/jsp/testray/views/issues.jspf" %>
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
			containerId: '${htmlNamespace}requirementCasesSearchContainer',
			menu: [
				{
					action: 'removeCase',
					controller: 'requirements',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="remove" />',
					menuAction: 'delete',
					method: 'POST',
					urlTemplate: '/${testrayRequirementComposite.testrayRequirementId}/{action}/?${htmlNamespace}testrayCaseId={id}',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "removeCase")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>