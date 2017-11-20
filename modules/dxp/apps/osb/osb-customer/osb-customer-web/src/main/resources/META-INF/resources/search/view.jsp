<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/search/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = liferayPortletResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/search/view.jsp");

ArticleSearch articleSearch = new ArticleSearch(renderRequest, portletURL);

ArticleSearchTerms searchTerms = (ArticleSearchTerms)articleSearch.getSearchTerms();

Hits hits = null;

if (searchTerms.hasSearchTerms()) {
	SearchContext searchContext = SearchContextFactory.getInstance(request);

	hits = ArticleSearchUtil.search(searchContext, new String[] {JournalArticle.class.getName(), KBArticle.class.getName()}, searchTerms.getAssetCategoryIds(), searchTerms.getLanguageIds(), searchTerms.getPermissionTypes(), searchStructureIds, themeDisplay.getLanguageId(), articleSearch.getStart(), articleSearch.getEnd());

	articleSearch.setTotal(hits.getLength());
}
%>

<div class="search-container">
	<aui:row>
		<aui:col cssClass="search-header">
			<h1 class="header">
				<liferay-ui:message key="search" />
			</h1>

			<aui:form action="<%= portletURL.toString() %>" cssClass="search-form" onSubmit="submitForm(this); return false;">
				<aui:input cssClass="lfr-search-keywords" id="keywords" label="" name="keywords" placeholder="search-liferays-documentation" value="<%= HtmlUtil.escapeAttribute(keywords) %>" />

				<%
				String iconCss = "toggle-off-click";
				String onclick = renderResponse.getNamespace() + "updateSearchResults(false, false);";

				if (Validator.isNotNull(keywords)) {
					iconCss = iconCss.concat(" toggle-off-click-active");
					onclick = renderResponse.getNamespace() + "updateSearchResults(true, false);";
				}
				%>

				<div class="<%= iconCss %>" onclick="<%= onclick %>"></div>

				<c:if test="<%= articleSearch.getTotal() > 0 %>">
					<div class="showing-results">

						<%
						int start = (articleSearch.getCur() - 1) * articleSearch.getDelta();
						int end = articleSearch.getCur() * articleSearch.getDelta();

						if (end > articleSearch.getTotal()) {
							end = articleSearch.getTotal();
						}
						%>

						<%= LanguageUtil.format(request, "showing-x-x-of-x-results", new Object[] {numberFormat.format(start + 1), numberFormat.format(end), numberFormat.format(articleSearch.getTotal())}) %>
					</div>
				</c:if>
			</aui:form>
		</aui:col>
	</aui:row>

	<aui:row>
		<aui:col cssClass="navigation-pane" width="<%= 25 %>">
			<%@ include file="/search/search_navigation.jspf" %>
		</aui:col>

		<aui:col cssClass="context-pane" last="<%= true %>" width="<%= 75 %>">
			<div class="article-search-results" id="<portlet:namespace />entriesContainer">
				<%@ include file="/search/article_search_results_index.jspf" %>
			</div>
		</aui:col>
	</aui:row>
</div>