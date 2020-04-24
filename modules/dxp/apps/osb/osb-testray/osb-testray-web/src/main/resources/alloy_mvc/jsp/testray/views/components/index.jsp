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
	<liferay-util:param name="title" value="components" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayComponentClass, "create")}'>
	<aui:button-row>
		<portlet:renderURL var="createTestrayComponentURL">
			<portlet:param name="controller" value="components" />
			<portlet:param name="action" value="create" />
			<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />
		</portlet:renderURL>

		<aui:button href="${createTestrayComponentURL}" primary="${true}" value="add-component" />
	</aui:button-row>
</c:if>

<portlet:renderURL var="viewTestrayComponentsURL">
	<portlet:param name="controller" value="components" />
	<portlet:param name="action" value="index" />
	<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />
</portlet:renderURL>

<aui:form action="${viewTestrayComponentsURL}" method="get" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}searchTestrayComponents();">
	<aui:fieldset>
		<aui:input inlineField="${true}" label="" name="name" size="30" title="search-components" type="text" />

		<aui:button type="submit" value="search" />
	</aui:fieldset>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-components"
		id="componentsSearchContainer"
		iteratorURL="${portletURL}"
		orderByCol="${orderByCol}"
		orderByType="${orderByType}"
		total="${alloySearchResult.size}"
	>
		<liferay-ui:search-container-results
			results="${testrayComponentComposites}"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			escapedModel="${true}"
			keyProperty="testrayComponentId"
			modelVar="testrayComponentComposite"
		>
			<liferay-ui:search-container-column-text
				name="team"
				orderable="${true}"
				orderableProperty="testrayTeamName_sortable"
				property="testrayTeamName"
			/>

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
	function ${htmlNamespace}searchTestrayComponents() {
		var name = document.${htmlNamespace}fm.${htmlNamespace}name.value;

		window.location.href = '${viewTestrayComponentsURL}&${htmlNamespace}name=' + escape(name);
	}
</aui:script>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}componentsSearchContainer',
			menu: [
				{
					action: 'edit',
					controller: 'components',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayComponent, "edit")}
				},
				{
					action: 'delete',
					controller: 'components',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayComponent, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>