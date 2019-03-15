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

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/jira_issues" var="refinedJiraIssuesURL">
	<portlet:param name="fromFixPackVersion" value="2.0" />
	<portlet:param name="product" value="dxp" />
	<portlet:param name="productVersion" value="7.0" />
	<portlet:param name="toFixPackVersion" value="5.0" />
</liferay-portlet:resourceURL>

<strong>Changelog Refinement Endpoint:</strong> <%= refinedJiraIssuesURL %>

<br />

<h2>
	<liferay-ui:message key="changelog" />
</h2>

<%
JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), changelogJournalArticleId);
%>

<c:if test="<%= journalArticle != null %>">

	<%
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
	%>

	<h5 class="secondary-text-color section-subtitle">
		<%= journalArticleDisplay.getContent() %>
	</h5>
</c:if>

<%
JSONObject jsonObject = jiraIssueSearcher.search(renderRequest, renderResponse);
%>

<strong>Results:</strong> <%= HtmlUtil.escape(jsonObject.toString()) %>