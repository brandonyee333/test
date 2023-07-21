<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/designer/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KaleoDraftDefinition kaleoDraftDefinition = (KaleoDraftDefinition)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= KaleoDraftDefinitionPermission.contains(permissionChecker, kaleoDraftDefinition, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/designer/edit_kaleo_draft_definition.jsp" />
			<portlet:param name="name" value="<%= kaleoDraftDefinition.getName() %>" />
			<portlet:param name="version" value="<%= String.valueOf(kaleoDraftDefinition.getVersion()) %>" />
			<portlet:param name="draftVersion" value="<%= String.valueOf(kaleoDraftDefinition.getDraftVersion()) %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= KaleoDraftDefinitionPermission.contains(permissionChecker, kaleoDraftDefinition, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= KaleoDraftDefinition.class.getName() %>"
			modelResourceDescription="<%= kaleoDraftDefinition.getName() %>"
			resourcePrimKey="<%= String.valueOf(kaleoDraftDefinition.getKaleoDraftDefinitionId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= (kaleoDraftDefinition.getVersion() == 0) && KaleoDraftDefinitionPermission.contains(permissionChecker, kaleoDraftDefinition, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteKaleoDraftDefinition" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="name" value="<%= kaleoDraftDefinition.getName() %>" />
			<portlet:param name="version" value="<%= String.valueOf(kaleoDraftDefinition.getVersion()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>