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
long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId");

int count = ParamUtil.getInteger(request, "count");
String displayStyle = ParamUtil.getString(request, "displayStyle");
String headingLabel = ParamUtil.getString(request, "headingLabel");
String headingURL = ParamUtil.getString(request, "headingURL");
boolean mostViewed = ParamUtil.getBoolean(request, "mostViewed");

List<AppEntry> appEntries = new ArrayList<AppEntry>();

if (mostViewed) {
	appEntries = _getMostViewedAppEntries(request, storeCountryId, assetCategoryId, count);
}
else {
	int assetListType = ParamUtil.getInteger(request, "assetListType");
	String orderByCol = ParamUtil.getString(request, "orderByCol");

	appEntries = _getAppEntries(storeCountryId, assetCategoryId, assetListType, count, orderByCol);
}
%>

<div class="app-entries-display">
	<c:if test="<%= Validator.isNotNull(headingLabel) %>">
		<div class="app-entries-display-header">
			<c:if test="<%= Validator.isNotNull(headingURL) %>">
				<span class="see-all">
					<a href="<%= HtmlUtil.escapeAttribute(headingURL) %>"><liferay-ui:message key="see-all" /></a>
				</span>
			</c:if>

			<c:choose>
				<c:when test="<%= Validator.isNotNull(headingURL) %>">
					<a href="<%= HtmlUtil.escapeAttribute(headingURL) %>">
						<h2 class="header-title">
							<liferay-ui:message key="<%= HtmlUtil.escape(headingLabel) %>" />
						</h2>
					</a>
				</c:when>
				<c:otherwise>
					<h2 class="header-title">
						<liferay-ui:message key="<%= HtmlUtil.escape(headingLabel) %>" />
					</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>

	<div class="app-entries">

		<%
		for (AppEntry appEntry : appEntries) {
		%>

			<liferay-util:include page="/marketplace/app_entry_display.jsp" servletContext="<%= application %>">
				<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
				<liferay-util:param name="displayStyle" value="<%= displayStyle %>" />
			</liferay-util:include>

		<%
		}
		%>

	</div>
</div>

<%!
private List<AppEntry> _getAppEntries(long storeCountryId, long assetCategoryId, int assetListType, int count, String orderByCol) throws Exception {
	List<AppEntry> appEntries = new ArrayList<AppEntry>();

	for (int i = 0; appEntries.size() < count; i++) {
		int start = i * count;
		int end = start + (count - 1);

		List<AssetEntry> assetCategoryAssetEntries = null;

		if (assetListType > 0) {
			assetCategoryAssetEntries = MarketplaceUtil.getAssetListAssetEntries(assetCategoryId, assetListType, true, start, end);
		}
		else {
			assetCategoryAssetEntries = MarketplaceUtil.getAssetEntries(assetCategoryId, AppEntry.class.getName(), true, start, end, orderByCol);
		}

		if (assetCategoryAssetEntries.isEmpty()) {
			break;
		}

		for (AssetEntry assetCategoryAssetEntry : assetCategoryAssetEntries) {
			String className = assetCategoryAssetEntry.getClassName();

			AppEntry appEntry = null;

			if (className.equals(AppEntry.class.getName())) {
				appEntry = AppEntryLocalServiceUtil.getAppEntry(assetCategoryAssetEntry.getClassPK());

				if (!appEntry.hasAvailableCountry(storeCountryId)) {
					continue;
				}
			}

			if (appEntry != null) {
				appEntries.add(appEntry);
			}

			if (appEntries.size() >= count) {
				break;
			}
		}
	}

	return appEntries;
}

private List<AppEntry> _getMostViewedAppEntries(HttpServletRequest request, long storeCountryId, long assetCategoryId, int count) throws Exception {
	List<AppEntry> appEntries = new ArrayList<AppEntry>();

	SearchContext searchContext = SearchContextFactory.getInstance(request);

	LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	params.put("appEntryStatus", WorkflowConstants.STATUS_APPROVED);
	params.put("availableCountryIds", storeCountryId);

	if (assetCategoryId > 0) {
		params.put(Field.ASSET_CATEGORY_IDS, assetCategoryId);
	}

	searchContext.setAttribute("params", params);

	searchContext.setEnd(count);

	Sort sort = SortFactoryUtil.create("monthlyViewCount", Sort.INT_TYPE, true);

	searchContext.setSorts(new Sort[] {sort});

	searchContext.setStart(0);

	Indexer indexer = IndexerRegistryUtil.getIndexer(AppEntry.class);

	Hits results = indexer.search(searchContext);

	for (int i = 0; i < results.getDocs().length; i++) {
		Document document = results.doc(i);

		AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(Long.valueOf(document.get(Field.ENTRY_CLASS_PK)));

		if (appEntry != null) {
			appEntries.add(appEntry);
		}
	}

	return appEntries;
}
%>