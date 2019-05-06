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

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/artifact_versions" var="upgradeArtifactVersionSearchURL">
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="fromProductVersion" value="<%= String.valueOf(fromProductVersion) %>" />
	<portlet:param name="toProductVersion" value="<%= String.valueOf(toProductVersion) %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:resourceURL>

<div class="container-fluid row" id="<portlet:namespace />upgradeModuleChanges"></div>