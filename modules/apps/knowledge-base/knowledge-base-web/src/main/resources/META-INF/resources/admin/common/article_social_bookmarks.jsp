<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/common/init.jsp" %>

<%
KBArticle kbArticle = (KBArticle)request.getAttribute(KBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);

PortletURL viewKBArticleURL = kbArticleURLHelper.createViewURL(kbArticle);
%>

<liferay-ui:social-bookmarks
	contentId="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>"
	displayStyle="<%= socialBookmarksDisplayStyle %>"
	target="_blank"
	title="<%= kbArticle.getTitle() %>"
	types="<%= socialBookmarksTypes %>"
	url="<%= PortalUtil.getCanonicalURL(viewKBArticleURL.toString(), themeDisplay, layout) %>"
/>