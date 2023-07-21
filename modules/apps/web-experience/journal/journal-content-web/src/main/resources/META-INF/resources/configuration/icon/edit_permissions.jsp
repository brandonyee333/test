<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalContentDisplayContext.getArticle();
%>

<c:if test="<%= JournalArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="<%= JournalArticle.class.getName() %>"
		modelResourceDescription="<%= HtmlUtil.escape(article.getTitle(locale)) %>"
		resourcePrimKey="<%= String.valueOf(article.getResourcePrimKey()) %>"
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