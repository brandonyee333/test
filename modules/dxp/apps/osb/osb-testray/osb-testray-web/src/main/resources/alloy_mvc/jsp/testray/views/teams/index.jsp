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

<c:set value='${AlloyLanguageUtil.get("teams")}' var="teamsLabel" />

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${teamsLabel} - ${testrayProject.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayTeamClass, "create")}'>
	<aui:button-row>
		<portlet:renderURL var="createTestrayTeamURL">
			<portlet:param name="controller" value="teams" />
			<portlet:param name="action" value="create" />
			<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />
		</portlet:renderURL>

		<aui:button href="${createTestrayTeamURL}" primary="${true}" value="add-team" />
	</aui:button-row>
</c:if>

<portlet:renderURL var="viewTestrayTeamsURL">
	<portlet:param name="controller" value="teams" />
	<portlet:param name="action" value="index" />
	<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />
</portlet:renderURL>

<aui:form action="${viewTestrayTeamsURL}" method="get" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}searchTestrayTeams();">
	<aui:fieldset>
		<aui:input autoFocus="${true}" inlineField="${true}" label="" name="name" size="30" title="search-teams" type="text" />

		<aui:button type="submit" value="search" />
	</aui:fieldset>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-teams"
		id="teamsSearchContainer"
		iteratorURL="${alloySearchResult.portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${alloySearchResult.size}"
	>
		<liferay-ui:search-container-results
			results="${alloySearchResult.baseModels}"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.testray.model.TestrayTeam"
			escapedModel="${true}"
			keyProperty="testrayTeamId"
			modelVar="testrayTeam"
		>
			<liferay-ui:search-container-column-text
				orderable="${true}"
				orderableProperty="name_sortable"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}searchTestrayTeams() {
		var name = document.${htmlNamespace}fm.${htmlNamespace}name.value;

		window.location.href = '${viewTestrayTeamsURL}&${htmlNamespace}name=' + escape(name);
	}
</aui:script>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}teamsSearchContainer',
			menu: [
				{
					action: 'edit',
					controller: 'teams',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayTeam, "edit")}
				},
				{
					action: 'delete',
					controller: 'teams',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayTeam, "delete")}
				}
			],
			redirect: '${alloySearchResult.portletURL}'
		}
	).render();
</aui:script>