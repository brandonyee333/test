<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/message_boards/init.jsp" %>

<%
long categoryId = GetterUtil.getLong(request.getAttribute("view.jsp-categoryId"));
%>

<liferay-frontend:add-menu>
	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_MESSAGE) %>">
		<portlet:renderURL var="addMessageURL">
			<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_message" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="mbCategoryId" value="<%= String.valueOf(categoryId) %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item
			title='<%= LanguageUtil.get(request, "thread") %>'
			url="<%= addMessageURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY) %>">
		<portlet:renderURL var="addCategoryURL">
			<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_category" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu-item
			title='<%= LanguageUtil.get(request, (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ? "category[message-board]" : "subcategory[message-board]") %>'
			url="<%= addCategoryURL.toString() %>"
		/>
	</c:if>
</liferay-frontend:add-menu>