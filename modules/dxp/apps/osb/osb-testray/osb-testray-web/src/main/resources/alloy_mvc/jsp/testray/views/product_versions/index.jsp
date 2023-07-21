<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="product-versions" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test='${testrayPermission:containsClassAction(themeDisplay, testrayProductVersionClass, "create")}'>
	<aui:button-row>
		<portlet:renderURL var="createTestrayProductVersionURL">
			<portlet:param name="controller" value="product_versions" />
			<portlet:param name="action" value="create" />
			<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />
		</portlet:renderURL>

		<aui:button href="${createTestrayProductVersionURL}" primary="${true}" value="add-product-version" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-product-versions"
	id="productVersionsSearchContainer"
	iteratorURL="${portletURL}"
	total="${testrayProductVersionsCount}"
>
	<liferay-ui:search-container-results
		results="${testrayProductVersions}"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.testray.model.TestrayProductVersion"
		escapedModel="${true}"
		keyProperty="testrayProductVersionId"
		modelVar="testrayProductVersion"
	>
		<liferay-ui:search-container-column-text
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
			containerId: '${htmlNamespace}productVersionsSearchContainer',
			menu: [
				{
					action: 'edit',
					controller: 'product_versions',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayProductVersion, "edit")}
				},
				{
					action: 'delete',
					controller: 'product_versions',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayProductVersion, "delete")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>