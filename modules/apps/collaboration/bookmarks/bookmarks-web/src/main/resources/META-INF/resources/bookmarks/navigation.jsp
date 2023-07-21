<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/bookmarks/init.jsp" %>

<%
long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="bookmarks" selected="<%= true %>" />
	</aui:nav>

	<c:if test="<%= bookmarksGroupServiceOverriddenConfiguration.showFoldersSearch() %>">
		<aui:nav-bar-search>
			<liferay-portlet:renderURL varImpl="searchURL">
				<portlet:param name="mvcRenderCommandName" value="/bookmarks/view" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
			</liferay-portlet:renderURL>

			<aui:form action="<%= searchURL %>" name="searchFm">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>