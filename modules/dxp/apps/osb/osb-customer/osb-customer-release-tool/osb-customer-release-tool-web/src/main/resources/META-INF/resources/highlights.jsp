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

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/fix_packs" var="refinedFixPacksURL">
	<portlet:param name="fromFixPackVersion" value="2.0" />
	<portlet:param name="fromProductVersion" value="7.0" />
	<portlet:param name="orderByCol" value="releaseDate" />
	<portlet:param name="orderByType" value="desc" />
	<portlet:param name="product" value="dxp" />
	<portlet:param name="toFixPackVersion" value="1.0" />
	<portlet:param name="toProductVersion" value="7.1" />
</liferay-portlet:resourceURL>

<strong>Fix Pack Refinement Endpoint:</strong> <%= refinedFixPacksURL %>

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
JSONObject jsonObject = fixPackSearcher.search(renderRequest, renderResponse);
%>

<strong>Results:</strong> <%= HtmlUtil.escape(jsonObject.toString()) %>