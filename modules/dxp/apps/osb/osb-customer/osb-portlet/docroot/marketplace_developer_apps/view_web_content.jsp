<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/marketplace_developer_apps/init.jsp" %>

<%
String articleId = ParamUtil.getString(request, "articleId");

JournalArticle journalArticle = null;

try {
	journalArticle = JournalArticleServiceUtil.getLatestArticle(OSBConstants.GROUP_GUEST_ID, articleId, WorkflowConstants.STATUS_APPROVED);
}
catch (Exception e) {
}
%>

<c:choose>
	<c:when test="<%= journalArticle != null %>">

		<%
		String languageId = LanguageUtil.getLanguageId(request);

		JournalArticleDisplay journalArticleDisplay = JournalContentUtil.getDisplay(journalArticle.getGroupId(), journalArticle.getArticleId(), journalArticle.getTemplateId(), null, languageId, themeDisplay);

		request.setAttribute(WebKeys.JOURNAL_ARTICLE, journalArticle);
		request.setAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY, journalArticleDisplay);
		%>

		<liferay-util:include page="/html/portlet/journal_content/view.jsp" />
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error">
			<liferay-ui:message key="an-unexpected-error-has-occurred" />
		</div>
	</c:otherwise>
</c:choose>