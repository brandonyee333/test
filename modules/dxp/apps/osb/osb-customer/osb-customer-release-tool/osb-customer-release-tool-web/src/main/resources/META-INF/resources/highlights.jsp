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

<strong>Refinement Filters:</strong> <%= releaseToolDisplayContext.getHightlightsFiltersJSONArray() %>

<br />

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/fix_packs" var="fixPacksResultsURL">
	<portlet:param name="fromFixPackVersion" value="2.0" />
	<portlet:param name="fromProductVersion" value="7.0" />
	<portlet:param name="product" value="dxp" />
	<portlet:param name="toFixPackVersion" value="1.0" />
	<portlet:param name="toProductVersion" value="7.1" />
</liferay-portlet:resourceURL>

<strong>Fix Pack Refinement Endpoint:</strong> <%= fixPacksResultsURL %>

<h1>
	<liferay-ui:message key="highlights" />
</h1>

<%
JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), highlightsJournalArticleId);
%>

<c:if test="<%= journalArticle != null %>">

	<%
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
	%>

	<%= journalArticleDisplay.getContent() %>
</c:if>

<%
JSONObject fixPackJSONObject = fixPackSearcher.search(renderRequest, renderResponse);
%>

<strong>Results:</strong> <%= HtmlUtil.escape(fixPackJSONObject.toString()) %>

<h2 class="highlights">
	<liferay-ui:message key="highlights" />
</h2>

<h5 class="secondary-text-color section-subtitle">
	<liferay-ui:message key="the-following-information-summarizes-the-important-changes-known-issues-and-security-details-in-each-release" />
</h5>

<div class="showing-results">
	<liferay-ui:message arguments='<%= fixPackJSONObject.get("total") %>' key="showing-x-results" />
</div>

<div class="" id="<portlet:namespace />sortableTable"></div>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.SortableTable,
		{
			fixPacksResultsURL: '<%= fixPacksResultsURL %>'
		},
		document.getElementById('<portlet:namespace />sortableTable')
	);
</aui:script>