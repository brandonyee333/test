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

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

String product = ParamUtil.getString(request, "product");
double productVersion = ParamUtil.getDouble(request, "productVersion");
double fromFixPackVersion = ParamUtil.getDouble(request, "fromFixPackVersion");
double toFixPackVersion = ParamUtil.getDouble(request, "toFixPackVersion");
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/jira_issues" var="refinedJiraIssuesURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="productVersion" value="<%= String.valueOf(productVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<strong>Changelog Refinement Endpoint:</strong> <%= refinedJiraIssuesURL %>

<br />

<strong>JIRA Component:</strong> <%= releaseToolDisplayContext.getJIRAComponentFiltersJSONArray() %>

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
JSONObject jiraIssueJSONObject = jiraIssueSearcher.search(renderRequest, renderResponse);
%>

<div id="<portlet:namespace />changelog"></div>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.Changelog,
		{
			jiraIssueJSONObject: <%= jiraIssueJSONObject %>
		},
		document.getElementById('<portlet:namespace />changelog')
	);
</aui:script>