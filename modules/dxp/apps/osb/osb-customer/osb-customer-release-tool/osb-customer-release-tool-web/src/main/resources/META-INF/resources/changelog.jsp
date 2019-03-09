<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<h1>
	<liferay-ui:message key="changelog" />
</h1>

<%
JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), changelogJournalArticleId);
%>

<c:if test="<%= journalArticle != null %>">

	<%
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
	%>

	<%= journalArticleDisplay.getContent() %>
</c:if>

<%
JSONObject jsonObject = jiraIssueSearcher.search(renderRequest, renderResponse);
%>

<strong>Results:</strong> <%= HtmlUtil.escape(jsonObject.toString()) %>