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
int assetListType = ParamUtil.getInteger(request, "assetListType");
int count = ParamUtil.getInteger(request, "count");
int displayStyle = ParamUtil.getInteger(request, "displayStyle");
String orderByCol = ParamUtil.getString(request, "orderByCol");

List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

for (int i = 0; assetEntries.size() < count; i++) {
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

		if (className.equals(AppEntry.class.getName())) {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetCategoryAssetEntry.getClassPK());

			if (!appEntry.hasAvailableCountry(storeCountryId)) {
				continue;
			}
		}

		assetEntries.add(assetCategoryAssetEntry);

		if (assetEntries.size() >= count) {
			break;
		}
	}
}

for (AssetEntry assetEntry : assetEntries) {
%>

	<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
		<liferay-util:param name="className" value="<%= PortalUtil.getClassName(assetEntry.getClassNameId()) %>" />
		<liferay-util:param name="classPK" value="<%= String.valueOf(assetEntry.getClassPK()) %>" />
		<liferay-util:param name="displayStyle" value="<%= String.valueOf(displayStyle) %>" />
		<liferay-util:param name="title" value="<%= assetEntry.getTitle() %>" />
	</liferay-util:include>

<%
}
%>