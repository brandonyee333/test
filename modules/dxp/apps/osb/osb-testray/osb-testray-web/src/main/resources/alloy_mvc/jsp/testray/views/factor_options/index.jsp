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
	<liferay-util:param name="title" value="options" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayFactorOptionClass, "create")}'>
	<aui:button-row>
		<portlet:renderURL var="createTestrayFactorOptionURL">
			<portlet:param name="controller" value="factor_options" />
			<portlet:param name="action" value="create" />
		</portlet:renderURL>

		<aui:button href="${createTestrayFactorOptionURL}" primary="${true}" value="add-option" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-options"
	id="factorOptionsSearchContainer"
	iteratorURL="${alloySearchResult.portletURL}"
	orderByCol="${orderByCol}"
	orderByType="${orderByType}"
	total="${alloySearchResult.size}"
>
	<liferay-ui:search-container-results
		results="${testrayFactorOptionComposites}"
	/>

	<liferay-ui:search-container-row
		className="java.lang.Object"
		escapedModel="${true}"
		keyProperty="testrayFactorOptionId"
		modelVar="testrayFactorOptionComposite"
	>
		<liferay-ui:search-container-column-text
			orderable="${true}"
			orderableProperty="name_sortable"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="category"
			orderable="${true}"
			orderableProperty="testrayFactorCategoryName_sortable"
			value="${testrayFactorOptionComposite.testrayFactorCategoryName}"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}factorOptionsSearchContainer',
			menu: [
				{
					action: 'edit',
					controller: 'factor_options',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayFactorOptionComposite.testrayFactorOption, "edit")}
				},
				{
					action: 'delete',
					controller: 'factor_options',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayFactorOptionComposite.testrayFactorOption, "delete")}
				}
			],
			redirect: '${alloySearchResult.portletURL}'
		}
	).render();
</aui:script>