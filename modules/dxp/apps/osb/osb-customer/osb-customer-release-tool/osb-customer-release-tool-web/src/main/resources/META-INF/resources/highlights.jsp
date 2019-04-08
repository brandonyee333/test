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

JSONObject fixPackJSONObject = fixPackSearcher.search(renderRequest, renderResponse);

String highlightsDescription = StringPool.BLANK;

JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), highlightsJournalArticleId);

if (journalArticle != null) {
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);

	highlightsDescription = journalArticleDisplay.getContent();
}
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/fix_packs" var="fixPackResultsURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="productVersion" value="<%= String.valueOf(productVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<div class="container-fluid row" id="<portlet:namespace />highlights"></div>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.Highlights,
		{
			description: '<%= HtmlUtil.escapeJS(highlightsDescription) %>',
			filters: <%= releaseToolDisplayContext.getHightlightsFiltersJSONArray() %>,
			fixPackJSONObject: <%= fixPackJSONObject %>,
			fixPackResultsURL: '<%= fixPackResultsURL %>'
		},
		document.getElementById('<portlet:namespace />highlights')
	);
</aui:script>