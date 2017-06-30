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

AssetCategory assetCategory = AssetCategoryServiceUtil.getCategory(assetCategoryId);

MarketplaceUtil.addAssetCategoryBreadcrumbEntries(assetCategoryId, request, renderResponse);
%>

<div class="marketplace view-category">
	<div class="image-banner">

		<%
		String assetCategoryName = TextFormatter.format(assetCategory.getName(), TextFormatter.C);

		assetCategoryName = StringUtil.replace(assetCategoryName, StringPool.SLASH, StringPool.UNDERLINE);
		assetCategoryName = StringUtil.toLowerCase(assetCategoryName);

		String categoryBannerURL = PortalUtil.getPathContext(request) + "/marketplace/images/category_banner_" + assetCategoryName + ".png";
		%>

		<img src="<%= categoryBannerURL %>" />
	</div>

	<div class="featured-apps">
		<liferay-util:include page="/marketplace/app_entries_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(assetCategoryId) %>" />
			<liferay-util:param name="assetListType" value="<%= String.valueOf(AssetListConstants.TYPE_FEATURED) %>" />
			<liferay-util:param name="count" value="2" />
			<liferay-util:param name="displayStyle" value="featured" />
			<liferay-util:param name="headingLabel" value='<%= LanguageUtil.format(pageContext, "featured-x", assetCategory.getTitle(themeDisplay.getLanguageId())) %>' />
		</liferay-util:include>
	</div>

	<liferay-util:include page="/marketplace/search_form.jsp" servletContext="<%= application %>">
		<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(assetCategoryId) %>" />
	</liferay-util:include>

	<liferay-util:include page="/marketplace/app_entry_search_results.jsp" servletContext="<%= application %>">
		<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(assetCategoryId) %>" />
	</liferay-util:include>

	<c:if test="<%= !MarketplaceUtil.isMarketplaceServer(request) %>">
		<liferay-util:include page="/marketplace/change_country.jsp" servletContext="<%= application %>" />
	</c:if>
</div>