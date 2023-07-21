<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

double fromFixPackVersion = ParamUtil.getDouble(request, "fromFixPackVersion");
String product = ParamUtil.getString(request, "product");
double productVersion = ParamUtil.getDouble(request, "productVersion");
double toFixPackVersion = ParamUtil.getDouble(request, "toFixPackVersion");

String changelogDescription = StringPool.BLANK;

JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), changelogJournalArticleId);

if (journalArticle != null) {
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);

	changelogDescription = journalArticleDisplay.getContent();
}
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/jira_issues" var="jiraIssueSearchURL ">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="productVersion" value="<%= String.valueOf(productVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<div class="container-fluid row" id="<portlet:namespace />changelog"></div>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.Changelog,
		{
			description: '<%= HtmlUtil.escapeJS(changelogDescription) %>',
			endpoint: '<%= jiraIssueSearchURL %>',
			filters: <%= releaseToolDisplayContext.getJIRAComponentFiltersJSONArray() %>,
			jsonObject: <%= jiraIssueSearcher.search(renderRequest, renderResponse) %>
		},
		document.getElementById('<portlet:namespace />changelog')
	);
</aui:script>