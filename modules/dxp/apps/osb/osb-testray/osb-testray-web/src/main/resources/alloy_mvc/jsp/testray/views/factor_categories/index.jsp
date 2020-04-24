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
	<liferay-util:param name="title" value="categories" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayFactorCategoryClass, "create")}'>
	<aui:button-row>
		<portlet:renderURL var="createTestrayFactorCategoryURL">
			<portlet:param name="controller" value="factor_categories" />
			<portlet:param name="action" value="create" />
		</portlet:renderURL>

		<aui:button href="${createTestrayFactorCategoryURL}" primary="${true}" value="add-category" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-categories"
	id="factorCategoriesSearchContainer"
	iteratorURL="${portletURL}"
	orderByCol="${orderByCol}"
	orderByType="${orderByType}"
	total="${testrayFactorCategoriesCount}"
>
	<liferay-ui:search-container-results
		results="${testrayFactorCategories}"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.testray.model.TestrayFactorCategory"
		escapedModel="${true}"
		keyProperty="testrayFactorCategoryId"
		modelVar="testrayFactorCategory"
	>
		<liferay-ui:search-container-column-text
			orderable="${true}"
			property="name"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}factorCategoriesSearchContainer',
			menu: [
				{
					action: 'edit',
					controller: 'factor_categories',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayFactorCategory, "edit")}
				},
				{
					action: 'delete',
					controller: 'factor_categories',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayFactorCategory, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>