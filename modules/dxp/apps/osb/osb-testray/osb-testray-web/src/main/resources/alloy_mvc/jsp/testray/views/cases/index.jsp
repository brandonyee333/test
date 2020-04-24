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
	<liferay-util:param name="tab" value="cases" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="cases"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/cases/index_filter.jspf" %>

		<testray:configure-columns
			columnLabels="${TestrayCaseConstants.COLUMN_LABELS}"
			columnLabelsDefault="${TestrayCaseConstants.COLUMN_LABELS_DEFAULT}"
			sessionKey="testrayCasesIndexColumns"
		/>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayCaseClass, "importCases")}'>
			<aui:button icon="icon-upload" onClick="${htmlNamespace}uploadCSVCases();" value="upload-csv" />

			<portlet:actionURL var="importTestrayCasesURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="importCases" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:actionURL>

			<aui:form action="${importTestrayCasesURL}" enctype="multipart/form-data" method="post" name="uploadCSVCasesFm">
				<aui:input name="testrayProjectId" type="hidden" value="${testrayProjectId}" />

				<aui:input autocomplete="off" cssClass="hide" label="" multiple="${true}" name="cases" onChange="this.form.submit()" title="upload-csv" type="file" value="" />
			</aui:form>
		</c:if>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayCaseClass, "create")}'>
			<portlet:renderURL var="createTestrayCaseURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="create" />
				<portlet:param name="testrayProjectId" value="${param.testrayProjectId}" />
			</portlet:renderURL>

			<aui:button icon="icon-plus" onClick="${createTestrayCaseURL}" primary="${true}" value="add-case" />
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-cases"
		id="casesSearchContainer"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${alloySearchResult.size}"
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

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_CREATE_DATE)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="create-date"
					orderable="${true}"
					orderableProperty="createDate_sortable"
				>
					<testray:date
						value="${testrayCaseComposite.createDate}"
					/>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_MODIFIED_DATE)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="modified-date"
					orderable="${true}"
					orderableProperty="modified_sortable"
				>
					<testray:date
						value="${testrayCaseComposite.modifiedDate}"
					/>
				</liferay-ui:search-container-column-text>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_PRIORITY)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					orderable="${true}"
					orderableProperty="testrayCasePriority_sortable"
					property="priority"
				/>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_CASE_TYPE)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="case-type"
					orderable="${true}"
					orderableProperty="testrayCaseTypeName_sortable"
					property="testrayCaseTypeName"
				/>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_NAME)}">
				<liferay-ui:search-container-column-text
					cssClass="table-column-main"
					href="${viewTestrayCaseURL}"
					name="case-name"
					orderable="${true}"
					orderableProperty="name_sortable"
					property="name"
				/>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_TEAM)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="team"
					orderable="${true}"
					orderableProperty="testrayTeamName_sortable"
					property="testrayTeamName"
				/>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_COMPONENT)}">
				<liferay-ui:search-container-column-text
					href="${viewTestrayCaseURL}"
					name="component"
					orderable="${true}"
					orderableProperty="testrayComponentName_sortable"
					property="testrayComponentName"
				/>
			</c:if>

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseConstants.COLUMN_LABEL_DESCRIPTION)}">
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

			<c:if test="${testrayCasesIndexColumns.contains(TestrayCaseResultConstants.COLUMN_LABEL_ISSUES)}">
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

<aui:script>
	function ${htmlNamespace}uploadCSVCases() {
		var uploadFileInput = document.getElementById('cases');

		if (uploadFileInput) {
			uploadFileInput.click();
		}
	}
</aui:script>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}casesSearchContainer',
			menu: [
				{
					action: 'view',
					controller: 'cases',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '/{id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "view", testrayCaseComposite.testrayProjectId)}
				},
				{
					action: 'edit',
					controller: 'cases',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCaseComposite.testrayCase, "edit")}
				},
				{
					action: 'select',
					controller: 'requirements',
					iconCssClass: 'icon-list',
					label: '<liferay-ui:message key="link-requirements" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="select-requirements" />'
					},
					parameters: {
						testrayProjectId: '${param.testrayProjectId}'
					},
					urlTemplate: '/select?testrayCaseId={id}',
					visible: ${testrayPermission:containsClassAction(themeDisplay, testrayRequirementClass, "select")}
				},
				{
					action: 'delete',
					controller: 'cases',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCaseComposite.testrayCase, "delete")}
				}
			],
			redirect: '${alloySearchResult.portletURL}'
		}
	).render();
</aui:script>