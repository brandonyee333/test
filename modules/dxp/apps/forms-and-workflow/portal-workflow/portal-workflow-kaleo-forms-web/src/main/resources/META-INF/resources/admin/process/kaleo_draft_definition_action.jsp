<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

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
	<liferay-portlet:renderURL portletName="<%= KaleoDesignerPortletKeys.KALEO_DESIGNER %>" var="editURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/designer/edit_kaleo_draft_definition.jsp" />
		<portlet:param name="closeRedirect" value='<%= (String)row.getParameter("backURL") %>' />
		<portlet:param name="name" value="<%= kaleoDraftDefinition.getName() %>" />
		<portlet:param name="version" value="<%= String.valueOf(kaleoDraftDefinition.getVersion()) %>" />
		<portlet:param name="draftVersion" value="<%= String.valueOf(kaleoDraftDefinition.getDraftVersion()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		onClick='<%= "javascript:" + renderResponse.getNamespace() + "editWorkflow('" + editURL + "');" %>'
		url="javascript:;"
	/>

	<portlet:actionURL name="deleteKaleoDraftDefinition" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="name" value="<%= kaleoDraftDefinition.getName() %>" />
		<portlet:param name="version" value="<%= String.valueOf(kaleoDraftDefinition.getVersion()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>