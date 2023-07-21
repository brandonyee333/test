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

String moduleChangesDescription = StringPool.BLANK;
String moduleChangesCTA = StringPool.BLANK;

JournalArticle moduleChangesJournalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), moduleChangesJournalArticleId);

JournalArticle moduleChangesCTAJournalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), moduleChangesCTAJournalArticleId);

if (moduleChangesJournalArticle != null) {
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(moduleChangesJournalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);

	moduleChangesDescription = journalArticleDisplay.getContent();
}

if (moduleChangesCTAJournalArticle != null) {
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(moduleChangesCTAJournalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);

	moduleChangesCTA = journalArticleDisplay.getContent();
}
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/artifact_versions" var="artifactVersionSearchURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="productVersion" value="<%= String.valueOf(productVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<div class="container-fluid row" id="<portlet:namespace />moduleChanges"></div>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.ModuleChanges,
		{
			cta: '<%= HtmlUtil.escapeJS(moduleChangesCTA) %>',
			description: '<%= HtmlUtil.escapeJS(moduleChangesDescription) %>',
			endpoint: '<%= artifactVersionSearchURL %>',
			filters: <%= releaseToolDisplayContext.getArtifactVersionFiltersJSONArray() %>,
			fromFixPackVersion: '<%= String.valueOf(fromFixPackVersion) %>',
			jsonObject: <%= artifactVersionSearcher.search(renderRequest, renderResponse) %>,
			toFixPackVersion: '<%= String.valueOf(toFixPackVersion) %>'
		},
		document.getElementById('<portlet:namespace />moduleChanges')
	);
</aui:script>