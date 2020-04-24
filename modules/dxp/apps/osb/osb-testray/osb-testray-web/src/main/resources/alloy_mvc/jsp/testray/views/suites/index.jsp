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
	<liferay-util:param name="tab" value="suites" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:renderURL var="viewTestraySuitesURL">
	<portlet:param name="controller" value="suites" />
	<portlet:param name="action" value="index" />
</portlet:renderURL>

<div class="testray-card">
	<aui:form action="${viewTestraySuitesURL}" method="get" name="fm">
		<testray:table-toolbar
			title="suites"
		>
			<aui:fieldset cssClass="search">
				<aui:input name="testrayProjectId" type="hidden" value="${testrayProjectId}" />

				<aui:input inlineField="${true}" label="" name="name" placeholder="search" size="30" title="search-suites" type="text" />

				<aui:button type="submit" value="search" />
			</aui:fieldset>

			<c:if test='${testrayPermission:containsClassAction(themeDisplay, testraySuiteClass, "create") && (empty renderRequest.getAttribute("testrayTeam"))}'>
				<portlet:renderURL var="createTestraySuiteURL">
					<portlet:param name="controller" value="suites" />
					<portlet:param name="action" value="create" />
					<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
				</portlet:renderURL>

				<aui:button href="${createTestraySuiteURL}" icon="icon-plus" primary="${true}" value="add-suite" />
			</c:if>
		</testray:table-toolbar>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-suites"
			id="suitesSearchContainer"
			iteratorURL="${alloySearchResult.portletURL}"
			orderByCol="${orderByCol}"
			orderByType="${orderByType}"
			total="${alloySearchResult.size}"
		>
			<liferay-ui:search-container-results
				results="${alloySearchResult.baseModels}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.testray.model.TestraySuite"
				escapedModel="${true}"
				keyProperty="testraySuiteId"
				modelVar="testraySuite"
			>
				<portlet:renderURL var="viewTestraySuiteURL">
					<portlet:param name="controller" value="suites" />
					<portlet:param name="action" value="view" />
					<portlet:param name="id" value="${testraySuite.testraySuiteId}" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass="table-column-main"
					href="${viewTestraySuiteURL}"
					name="suite"
					orderable="${true}"
					orderableProperty="name_sortable"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					href="${viewTestraySuiteURL}"
					property="description"
				/>

				<liferay-ui:search-container-column-text
					href="${viewTestraySuiteURL}"
					name="type"
				>
					<liferay-ui:message key='${(empty testraySuite.caseParameters) ? "static" : "smart"}' />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}suitesSearchContainer',
			menu: [
				{
					action: 'view',
					controller: 'suites',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					urlTemplate: '/{id}'
				},
				{
					action: 'edit',
					controller: 'suites',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testraySuite, "edit")}
				},
				{
					action: 'delete',
					controller: 'suites',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testraySuite, "delete")}
				}
			],
			redirect: '${alloySearchResult.portletURL}'
		}
	).render();
</aui:script>