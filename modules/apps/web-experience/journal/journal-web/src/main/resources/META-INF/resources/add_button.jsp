<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= JournalFolderPermission.contains(permissionChecker, scopeGroupId, journalDisplayContext.getFolderId(), ActionKeys.ADD_FOLDER) || JournalFolderPermission.contains(permissionChecker, scopeGroupId, journalDisplayContext.getFolderId(), ActionKeys.ADD_ARTICLE) %>">
	<liferay-frontend:add-menu>
		<c:if test="<%= JournalFolderPermission.contains(permissionChecker, scopeGroupId, journalDisplayContext.getFolderId(), ActionKeys.ADD_FOLDER) %>">
			<portlet:renderURL var="addFolderURL">
				<portlet:param name="mvcPath" value="/edit_folder.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
				<portlet:param name="parentFolderId" value="<%= String.valueOf(journalDisplayContext.getFolderId()) %>" />
			</portlet:renderURL>

			<liferay-frontend:add-menu-item
				title='<%= LanguageUtil.get(request, (journalDisplayContext.getFolder() != null) ? "subfolder" : "folder") %>'
				url="<%= addFolderURL.toString() %>"
			/>
		</c:if>

		<c:if test="<%= JournalFolderPermission.contains(permissionChecker, scopeGroupId, journalDisplayContext.getFolderId(), ActionKeys.ADD_ARTICLE) %>">

			<%
			List<DDMStructure> ddmStructures = journalDisplayContext.getDDMStructures();

			for (DDMStructure ddmStructure : ddmStructures) {
			%>

				<portlet:renderURL var="addArticleURL">
					<portlet:param name="mvcPath" value="/edit_article.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(journalDisplayContext.getFolderId()) %>" />
					<portlet:param name="ddmStructureKey" value="<%= ddmStructure.getStructureKey() %>" />
				</portlet:renderURL>

				<liferay-frontend:add-menu-item
					title="<%= ddmStructure.getUnambiguousName(ddmStructures, themeDisplay.getScopeGroupId(), locale) %>"
					url="<%= addArticleURL.toString() %>"
				/>

			<%
			}
			%>

		</c:if>
	</liferay-frontend:add-menu>
</c:if>