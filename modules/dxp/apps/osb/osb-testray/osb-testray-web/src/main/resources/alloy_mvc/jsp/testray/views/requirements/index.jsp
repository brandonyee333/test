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
	<liferay-util:param name="tab" value="requirements" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="requirements"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/requirements/index_filter.jspf" %>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRequirementClass, "importIssues")}'>
			<portlet:renderURL var="importTestrayRequirementsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="import" />
				<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("import-jira-issues")}' var="importTestrayRequirementsURLTitle" />

			<c:set value="javascript:Liferay.Testray.openWindow('${importTestrayRequirementsURL}', '${importTestrayRequirementsURLTitle}', 1000)" var="importTestrayRequirementsURL" />

			<aui:button icon="icon-share-alt" onClick="${importTestrayRequirementsURL}" value="import-jira-issues" />
		</c:if>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRequirementClass, "importRequirements")}'>
			<aui:button icon="icon-upload" onClick="${htmlNamespace}uploadCSVRequirements();" value="upload-csv" />

			<portlet:actionURL var="importTestrayRequirementsURL">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="importRequirements" />
				<portlet:param name="redirect" value="${portletURL}" />
			</portlet:actionURL>

			<aui:form action="${importTestrayRequirementsURL}" enctype="multipart/form-data" method="post" name="uploadCSVRequirementsFm">
				<aui:input name="testrayProjectId" type="hidden" value="${testrayProject.testrayProjectId}" />

				<aui:input autocomplete="off" cssClass="hide" label="" multiple="${true}" name="requirements" onChange="this.form.submit()" title="upload-csv" type="file" value="" />
			</aui:form>
		</c:if>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayRequirementClass, "create")}'>
			<portlet:renderURL var="createTestrayRequirementURL">
				<portlet:param name="controller" value="requirements" />
				<portlet:param name="action" value="create" />
				<portlet:param name="testrayProjectId" value="${param.testrayProjectId}" />
			</portlet:renderURL>

			<aui:button href="${createTestrayRequirementURL}" icon="icon-plus" primary="${true}" value="add-requirement" />
		</c:if>
	</testray:table-toolbar>

	<%@ include file="/alloy_mvc/jsp/testray/views/requirements/index_table.jspf" %>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}uploadCSVRequirements() {
		var uploadFileInput = document.getElementById('requirements');

		if (uploadFileInput) {
			uploadFileInput.click();
		}
	}
</aui:script>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}requirementsSearchContainer',
			menu: [
				{
					action: 'view',
					controller: 'requirements',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '/{id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "requirements", "view", testrayRequirementComposite.testrayProjectId)}
				},
				{
					action: 'edit',
					controller: 'requirements',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "edit")}
				},
				{
					action: 'sync',
					controller: 'requirements',
					iconCssClass: 'icon-refresh',
					label: '<liferay-ui:message key="resync-with-jira" />',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "sync")}
				},
				{
					action: 'select',
					controller: 'cases',
					iconCssClass: 'icon-list',
					label: '<liferay-ui:message key="link-cases" />',
					menuAction: 'modal',
					modalConfig: {
						title: '<liferay-ui:message key="select-cases" />'
					},
					parameters: {
						testrayProjectId: '${param.testrayProjectId}'
					},
					urlTemplate: '/select?${htmlNamespace}testrayRequirementId={id}',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "updateCases")}
				},
				{
					action: 'delete',
					controller: 'requirements',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayRequirementComposite.testrayRequirement, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>