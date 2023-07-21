<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String product = ParamUtil.getString(request, "product");
double fromProductVersion = ParamUtil.getDouble(request, "fromProductVersion");
double toProductVersion = ParamUtil.getDouble(request, "toProductVersion");
double fromFixPackVersion = ParamUtil.getDouble(request, "fromFixPackVersion");
double toFixPackVersion = ParamUtil.getDouble(request, "toFixPackVersion");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("product", product);
portletURL.setParameter("fromProductVersion", String.valueOf(fromProductVersion));
portletURL.setParameter("toProductVersion", String.valueOf(toProductVersion));
portletURL.setParameter("fromFixPackVersion", String.valueOf(fromFixPackVersion));
portletURL.setParameter("toFixPackVersion", String.valueOf(toFixPackVersion));

String moduleChangesDescription = StringPool.BLANK;

JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), moduleChangesJournalArticleId);

if (journalArticle != null) {
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);

	moduleChangesDescription = journalArticleDisplay.getContent();
}
%>

<liferay-portlet:renderURL var="compareVersionURL">
	<portlet:param name="orderByType" value="desc" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="fromProductVersion" value="<%= String.valueOf(fromProductVersion) %>" />
	<portlet:param name="toProductVersion" value="<%= String.valueOf(toProductVersion) %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/artifact_versions" var="upgradeArtifactVersionSearchURL">
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="fromProductVersion" value="<%= String.valueOf(fromProductVersion) %>" />
	<portlet:param name="toProductVersion" value="<%= String.valueOf(toProductVersion) %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<div class="container-fluid container-fluid-max-xl upgrade-module-changes-container" id="<portlet:namespace />upgradeModuleChanges"></div>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.ModuleChanges,
		{
			compareVersionActionURL: '<%= compareVersionURL %>',
			compareVersionFiltersJSON: <%= releaseToolDisplayContext.getFixPackFiltersJSONArray() %>,
			description: '<%= HtmlUtil.escapeJS(moduleChangesDescription) %>',
			endpoint: '<%= upgradeArtifactVersionSearchURL %>',
			filters: <%= releaseToolDisplayContext.getArtifactVersionFiltersJSONArray() %>,
			fromFixPackVersion: '<%= String.valueOf(fromFixPackVersion) %>',
			fromProductVersion: '<%= String.valueOf(fromProductVersion) %>',
			jsonObject: <%= artifactVersionSearcher.search(renderRequest, renderResponse) %>,
			toFixPackVersion: '<%= String.valueOf(toFixPackVersion) %>',
			toProductVersion: '<%= String.valueOf(toProductVersion) %>'
		},
		document.getElementById('<portlet:namespace />upgradeModuleChanges')
	);
</aui:script>