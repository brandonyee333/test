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