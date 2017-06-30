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
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter(mvcPathParam, "/marketplace/view.jsp");
%>

<div class="marketplace view">
	<c:if test="<%= themeDisplay.isSignedIn() && !RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
		<liferay-util:include page="/marketplace/user_verification.jsp" servletContext="<%= application %>" />
	</c:if>

	<div class="banner container">
		<div class="banner-carousel">
			<liferay-util:include page="/marketplace/asset_entries_display.jsp" servletContext="<%= application %>">
				<liferay-util:param name="assetListType" value="<%= String.valueOf(AssetListConstants.TYPE_BANNER) %>" />
				<liferay-util:param name="count" value="10" />
				<liferay-util:param name="displayStyle" value="3" />
			</liferay-util:include>
		</div>
	</div>

	<liferay-util:include page="/marketplace/search_form.jsp" servletContext="<%= application %>" />

	<div class="featured-apps">
		<liferay-portlet:renderURL var="featuredURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/search.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="assetListType" value="<%= String.valueOf(AssetListConstants.TYPE_FEATURED) %>" />
		</liferay-portlet:renderURL>

		<liferay-util:include page="/marketplace/app_entries_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetListType" value="<%= String.valueOf(AssetListConstants.TYPE_FEATURED) %>" />
			<liferay-util:param name="count" value="10" />
			<liferay-util:param name="headingLabel" value="featured-apps" />
			<liferay-util:param name="headingURL" value="<%= featuredURL %>" />
		</liferay-util:include>
	</div>

	<div class="new-and-interesting">
		<liferay-portlet:renderURL var="newAndInterestingURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/search.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="assetListType" value="<%= String.valueOf(AssetListConstants.TYPE_NEW_AND_INTERESTING) %>" />
		</liferay-portlet:renderURL>

		<liferay-util:include page="/marketplace/app_entries_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetListType" value="<%= String.valueOf(AssetListConstants.TYPE_NEW_AND_INTERESTING) %>" />
			<liferay-util:param name="count" value="10" />
			<liferay-util:param name="headingLabel" value="new-and-interesting" />
			<liferay-util:param name="headingURL" value="<%= newAndInterestingURL %>" />
		</liferay-util:include>
	</div>

	<div class="most-viewed-in-the-past-month">
		<liferay-util:include page="/marketplace/app_entries_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="mostViewed" value="<%= String.valueOf(true) %>" />
			<liferay-util:param name="count" value="5" />
			<liferay-util:param name="headingLabel" value="most-viewed-in-the-past-month" />
		</liferay-util:include>
	</div>

	<div class="asset-category">

		<%
		AssetCategory themeAssetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(PortletPropsValues.MARKETPLACE_THEMES_ASSET_CATEGORY_ID);

		portletURL.setParameter(mvcPathParam, "/marketplace/view_category.jsp");
		portletURL.setParameter("assetCategoryId", String.valueOf(themeAssetCategory.getCategoryId()));
		%>

		<liferay-util:include page="/marketplace/app_entries_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(themeAssetCategory.getCategoryId()) %>" />
			<liferay-util:param name="count" value="2" />
			<liferay-util:param name="displayStyle" value="tile" />
			<liferay-util:param name="headingLabel" value="<%= themeAssetCategory.getTitle(themeDisplay.getLanguageId(), true) %>" />
			<liferay-util:param name="headingURL" value="<%= portletURL.toString() %>" />
			<liferay-util:param name="orderByCol" value="modifiedDate" />
		</liferay-util:include>
	</div>

	<%
	for (AssetCategory assetCategory : MarketplaceUtil.getLeafAssetCategories()) {
		if (assetCategory.getCategoryId() == PortletPropsValues.MARKETPLACE_THEMES_ASSET_CATEGORY_ID) {
			continue;
		}
	%>

		<div class="asset-category">

			<%
			portletURL.setParameter(mvcPathParam, "/marketplace/view_category.jsp");
			portletURL.setParameter("assetCategoryId", String.valueOf(assetCategory.getCategoryId()));
			%>

			<liferay-util:include page="/marketplace/app_entries_display.jsp" servletContext="<%= application %>">
				<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(assetCategory.getCategoryId()) %>" />
				<liferay-util:param name="count" value="5" />
				<liferay-util:param name="headingLabel" value="<%= assetCategory.getTitle(themeDisplay.getLanguageId(), true) %>" />
				<liferay-util:param name="headingURL" value="<%= portletURL.toString() %>" />
				<liferay-util:param name="orderByCol" value="modifiedDate" />
			</liferay-util:include>
		</div>

	<%
	}
	%>

	<div class="weekly-stats">
		<liferay-util:include page="/marketplace/app_entries_weekly_stats.jsp" servletContext="<%= application %>" />
	</div>

	<c:if test="<%= !MarketplaceUtil.isMarketplaceServer(request) %>">
		<liferay-util:include page="/marketplace/change_country.jsp" servletContext="<%= application %>" />
	</c:if>
</div>

<aui:script use="aui-carousel,aui-io">
	new A.Carousel(
		{
			activeIndex: 0,
			contentBox: '.marketplace .banner-carousel',
			height: 180,
			intervalTime: 5,
			width: 960
		}
	).render();
</aui:script>