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

double fromFixPackVersion = ParamUtil.getDouble(request, "fromFixPackVersion");
String product = ParamUtil.getString(request, "product");
double productVersion = ParamUtil.getDouble(request, "productVersion");
double toFixPackVersion = ParamUtil.getDouble(request, "toFixPackVersion");
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/artifact_versions" var="refinedArtifactVersionsURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="productVersion" value="<%= String.valueOf(productVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<%
JSONObject jsonObject = artifactVersionSearcher.search(renderRequest, renderResponse);
%>

<div class="container-fluid row" id="<portlet:namespace />moduleChanges"></div>

<strong>Refinement Endpoint:</strong> <%= refinedArtifactVersionsURL %>

<strong>Owners Filter:</strong> <%= releaseToolDisplayContext.getArtifactVersionFiltersJSONArray() %>

<strong>Results:</strong> <%= HtmlUtil.escape(jsonObject.toString()) %>

<aui:script>
	var moduleChangesDescription = '';

	<%
	JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), moduleChangesJournalArticleId);
	%>

	<c:if test="<%= journalArticle != null %>">

		<%
		JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
		%>

		moduleChangesDescription = '<%= journalArticleDisplay.getContent() %>';
	</c:if>

	ReleaseTool.render(
		ReleaseTool.ModuleChanges,
		{
			description: moduleChangesDescription,
			filters: <%= releaseToolDisplayContext.getJIRAComponentFiltersJSONArray() %>,
			jiraIssueEndpoint: '<%= refinedJiraIssuesURL %>',
			jiraIssueJSONObject: <%= jiraIssueJSONObject %>
		},
		document.getElementById('<portlet:namespace />moduleChanges')
	);
</aui:script>