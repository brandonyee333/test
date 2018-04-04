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