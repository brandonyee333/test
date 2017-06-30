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

PortalRelease portalRelease = PortalReleaseLocalServiceUtil.fetchBuildNumberPortalRelease(buildNumber);

if (portalRelease == null) {
	buildNumber = 0;
}

String licenseType = ParamUtil.getString(request, "licenseType");
String displayStyle = ParamUtil.getString(request, "displayStyle");

String title = StringPool.BLANK;

if (assetListType > 0) {
	if (assetCategory != null) {
		title = LanguageUtil.format(pageContext, AssetListConstants.getTypeLabel(assetListType), assetCategory.getTitle(themeDisplay.getLanguageId()));
	}
	else {
		title = LanguageUtil.format(pageContext, AssetListConstants.getTypeLabel(assetListType), StringPool.BLANK);
	}
}
else if (Validator.isNull(keywords)) {
	if (assetCategory != null) {
		title = assetCategory.getTitle(themeDisplay.getLanguageId());
	}
	else {
		title = LanguageUtil.get(pageContext, "home");
	}
}
else {
	title = LanguageUtil.format(pageContext, "search-results-for-x", keywords);
}
%>

<div class="marketplace search">
	<div class="search-header">
		<h1>
			<%= HtmlUtil.escape(title) %>
		</h1>

		<div class="search-filter-labels">
			<div class="category">
				<h3>
					<liferay-ui:message key="category" />
				</h3>

				<div>
					<liferay-ui:message key='<%= (assetCategory != null) ? assetCategory.getTitle(themeDisplay.getLanguageId()) : "none" %>' />
				</div>
			</div>

			<div class="liferay-version">
				<h3>
					<liferay-ui:message key="liferay-version" />
				</h3>

				<div>
					<c:choose>
						<c:when test="<%= buildNumber > 0 %>">
							<%= portalRelease.getVersionName() %>
						</c:when >
						<c:otherwise>
							<liferay-ui:message key="all" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="license-type">
				<h3>
					<liferay-ui:message key="price" />
				</h3>

				<div>
					<c:choose>
						<c:when test='<%= licenseType.equals("free") %>'>
							<liferay-ui:message key="license-type-free" />
						</c:when>
						<c:when test='<%= licenseType.equals("paid") %>'>
							<liferay-ui:message key="license-type-paid" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="all" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>

	<liferay-util:include page="/marketplace/search_form.jsp" servletContext="<%= application %>">
		<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(assetCategoryId) %>" />
		<liferay-util:param name="assetListType" value="<%= String.valueOf(assetListType) %>" />
		<liferay-util:param name="buildNumber" value="<%= String.valueOf(buildNumber) %>" />
		<liferay-util:param name="keywords" value="<%= keywords %>" />
		<liferay-util:param name="licenseType" value="<%= licenseType %>" />
	</liferay-util:include>

	<liferay-util:include page="/marketplace/app_entry_search_results.jsp" servletContext="<%= application %>">
		<liferay-util:param name="keywords" value="<%= keywords %>" />
		<liferay-util:param name="assetCategoryId" value="<%= String.valueOf(assetCategoryId) %>" />
		<liferay-util:param name="assetListType" value="<%= String.valueOf(assetListType) %>" />
		<liferay-util:param name="buildNumber" value="<%= String.valueOf(buildNumber) %>" />
		<liferay-util:param name="licenseType" value="<%= licenseType %>" />
		<liferay-util:param name="displayStyle" value="<%= displayStyle %>" />
	</liferay-util:include>

	<c:if test="<%= !MarketplaceUtil.isMarketplaceServer(request) %>">
		<liferay-util:include page="/marketplace/change_country.jsp" servletContext="<%= application %>" />
	</c:if>
</div>