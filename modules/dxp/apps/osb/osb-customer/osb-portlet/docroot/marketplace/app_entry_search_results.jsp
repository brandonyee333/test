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

<%@ include file="/marketplace/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId");

AssetCategory assetCategory = null;

if (assetCategoryId > 0) {
	assetCategory = AssetCategoryServiceUtil.getCategory(assetCategoryId);
}

int assetListType = ParamUtil.getInteger(request, "assetListType");
int buildNumber = ParamUtil.getInteger(request, "buildNumber");
String licenseType = ParamUtil.getString(request, "licenseType");

String defaultDisplayStyle = SessionClicks.get(request, "osbDisplayStyle", "tile");

String displayStyle = ParamUtil.getString(request, "displayStyle", defaultDisplayStyle);

if (!displayStyle.equals(defaultDisplayStyle)) {
	SessionClicks.put(request, "osbDisplayStyle", displayStyle);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter(mvcPathParam, "/marketplace/search.jsp");
portletURL.setParameter("assetCategoryId", String.valueOf(assetCategoryId));
portletURL.setParameter("buildNumber", String.valueOf(buildNumber));
portletURL.setParameter("licenseType", licenseType);
portletURL.setParameter("displayStyle", displayStyle);

if (assetListType > 0) {
	portletURL.setParameter("assetListType", String.valueOf(assetListType));
}
else if (Validator.isNotNull(keywords)) {
	portletURL.setParameter("keywords", keywords);
}
%>

<div class="marketplace app-entry-search-results">

	<%
	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, OSBConstants.MARKETPLACE_SEARCH_DEFAULT_DELTA, portletURL, null, null);

	SearchContext searchContext = SearchContextFactory.getInstance(request);

	LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	params.put("appEntryStatus", WorkflowConstants.STATUS_APPROVED);
	params.put("availableCountryIds", storeCountryId);

	if (assetListType > 0) {
		AssetList assetList = AssetListLocalServiceUtil.fetchAssetList(assetCategoryId, assetListType);

		params.put("assetListIds", assetList.getAssetListId());
	}
	else if (assetCategoryId > 0) {
		params.put(Field.ASSET_CATEGORY_IDS, assetCategoryId);
	}

	if (buildNumber > 0) {
		params.put("compatibility", buildNumber);
	}

	if (licenseType.equals("free")) {
		params.put("free", true);
	}
	else if (licenseType.equals("paid")) {
		params.put("free", false);
	}

	if (keywords.startsWith(MarketplaceUtil.ASSET_COMPANY_KEYWORD)) {
		String developerEntryId = keywords.substring(MarketplaceUtil.ASSET_COMPANY_KEYWORD.length());

		params.put("developerEntryId", developerEntryId);

		searchContext.setKeywords(null);
	}
	else if (keywords.startsWith(MarketplaceUtil.ASSET_DEVELOPER_KEYWORD)) {
		String developerEntryId = keywords.substring(MarketplaceUtil.ASSET_DEVELOPER_KEYWORD.length());

		params.put("developerEntryId", developerEntryId);

		searchContext.setKeywords(null);
	}
	else if (keywords.startsWith(MarketplaceUtil.ASSET_TAGS_KEYWORD)) {
		keywords = keywords.substring(MarketplaceUtil.ASSET_TAGS_KEYWORD.length());

		String[] assetTagNames = StringUtil.split(keywords);

		params.put(Field.ASSET_TAG_NAMES, assetTagNames);

		searchContext.setKeywords(null);
	}

	searchContext.setAttribute("params", params);

	searchContext.setEnd(searchContainer.getEnd());
	searchContext.setSorts(SortFactoryUtil.getDefaultSorts());
	searchContext.setStart(searchContainer.getStart());

	Indexer indexer = IndexerRegistryUtil.getIndexer(AppEntry.class);

	Hits results = indexer.search(searchContext);

	searchContainer.setTotal(results.getLength());

	PortletURL displayControlURL = PortletURLUtil.clone(portletURL, renderResponse);

	displayControlURL.setParameter("displayStyle", "tile");
	%>

	<div class="display-controls">
		<a class="display-control-tile<%= displayStyle.equals("tile") ? " selected" : StringPool.BLANK %>" href="<%= displayControlURL.toString() %>" title="tile"></a>

		<%
		displayControlURL.setParameter("displayStyle", "list");
		%>

		<a class="display-control-list<%= displayStyle.equals("list") ? " selected" : StringPool.BLANK %>" href="<%= displayControlURL.toString() %>" title="list"></a>
	</div>

	<%
	for (int i = 0; i < results.getDocs().length; i++) {
		Document document = results.doc(i);
	%>

		<liferay-util:include page="/marketplace/app_entry_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="appEntryId" value="<%= document.get(Field.ENTRY_CLASS_PK) %>" />
			<liferay-util:param name="displayStyle" value="<%= displayStyle %>" />
		</liferay-util:include>

	<%
	}
	%>

	<c:if test="<%= searchContainer.getTotal() <= 0 %>">
		<c:choose>
			<c:when test="<%= Validator.isNotNull(keywords) %>">
				<%= LanguageUtil.format(pageContext, "no-entries-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(keywords) + "</strong>") %>
			</c:when>
			<c:otherwise>
				<%= LanguageUtil.get(pageContext, "no-results-were-found") %>
			</c:otherwise>
		</c:choose>
	</c:if>

	<div class="taglib-search-iterator-page-iterator-bottom">
		<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
	</div>
</div>