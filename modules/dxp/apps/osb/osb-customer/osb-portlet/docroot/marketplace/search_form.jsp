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
int buildNumber = ParamUtil.getInteger(request, "buildNumber");
String keywords = ParamUtil.getString(request, "keywords");
String licenseType = ParamUtil.getString(request, "licenseType");
%>

<div class="marketplace search-form">
	<liferay-portlet:renderURL varImpl="searchURL">
		<portlet:param name="<%= mvcPathParam %>" value="/marketplace/search.jsp" />
	</liferay-portlet:renderURL>

	<aui:form action="<%= searchURL.toString() %>" method="get" name="searchFm">
		<liferay-portlet:renderURLParams varImpl="searchURL" />

		<aui:input cssClass="keywords" label="" name="keywords" placeholder="search" type="text" value="<%= HtmlUtil.escapeAttribute(keywords) %>" />

		<aui:select cssClass="category" label="category" name="assetCategoryId" showEmptyOption="true">

			<%
			AssetCategory eePluginsAssetCategory = AssetCategoryServiceUtil.getCategory(OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID);
			%>

			<aui:option label="<%= eePluginsAssetCategory.getTitle(themeDisplay.getLanguageId(), true) %>" selected="<%= eePluginsAssetCategory.getCategoryId() == assetCategoryId %>" value="<%= String.valueOf(eePluginsAssetCategory.getCategoryId()) %>" />

			<%
			AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(OSBConstants.GROUP_GUEST_ID, "Marketplace");

			List<AssetCategory> assetCategories = AssetCategoryServiceUtil.getVocabularyRootCategories(assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetCategoryTreeComparator(true));

			for (AssetCategory assetCategory : assetCategories) {
				if (assetCategory.getCategoryId() == OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID) {
					continue;
				}
			%>

				<aui:option label="<%= assetCategory.getTitle(themeDisplay.getLanguageId(), true) %>" selected="<%= assetCategory.getCategoryId() == assetCategoryId %>" value="<%= String.valueOf(assetCategory.getCategoryId()) %>" />

				<%
				List<AssetCategory> subassetCategories = AssetCategoryServiceUtil.getChildCategories(assetCategory.getCategoryId());

				for (AssetCategory subassetCategory : subassetCategories) {
				%>

					<aui:option label='<%= "&nbsp;&nbsp;&nbsp;" + subassetCategory.getTitle(themeDisplay.getLanguageId(), true) %>' selected="<%= assetCategory.getCategoryId() == assetCategoryId %>" value="<%= subassetCategory.getCategoryId() %>" />

			<%
				}
			}
			%>

		</aui:select>

		<aui:select cssClass="liferay-version" label="liferay-version" name="buildNumber" showEmptyOption="true">

			<%
			List<PortalRelease> portalReleases = PortalReleaseLocalServiceUtil.getPortalReleases(true);

			for (PortalRelease portalRelease : portalReleases) {
				if (portalRelease.isHidden() && !OrganizationLocalServiceUtil.hasUserOrganization(themeDisplay.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {
					continue;
				}
			%>

				<aui:option label="<%= portalRelease.getVersionName() %>" selected="<%= portalRelease.getBuildNumber() == buildNumber %>" value="<%= portalRelease.getBuildNumber() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select cssClass="license-type" label="price" name="licenseType" showEmptyOption="true">
			<aui:option label="license-type-free" selected='<%= licenseType.equals("free") %>' value="free" />
			<aui:option label="license-type-paid" selected='<%= licenseType.equals("paid") %>' value="paid" />
		</aui:select>

		<aui:button cssClass="search-button" type="submit" value="search" />
	</aui:form>
</div>