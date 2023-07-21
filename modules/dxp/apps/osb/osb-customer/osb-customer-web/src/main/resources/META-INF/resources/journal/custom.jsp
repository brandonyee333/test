<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String articleId = ParamUtil.getString(request, "articleId");

JournalArticle journalArticle = null;

if (Validator.isNotNull(articleId)) {
	journalArticle = JournalArticleLocalServiceUtil.getArticle(themeDisplay.getScopeGroupId(), articleId);
}
%>

<c:if test="<%= journalArticle != null %>">
	<aui:model-context bean="<%= journalArticle %>" model="<%= JournalArticle.class %>" />

	<aui:input label="url-title" name="urlTitle" />
</c:if>