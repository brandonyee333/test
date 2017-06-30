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
String backURL = ParamUtil.getString(request, "backURL");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

if (themeDisplay.isSignedIn()) {
	OSBAppEntryPermission.check(themeDisplay.getPermissionChecker(), appEntryId, OSBActionKeys.REVIEW_APP);
}

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

long appVersionId = ParamUtil.getLong(request, "appVersionId");

AppVersion appVersion = null;

if (appVersionId > 0) {
	appVersion = AppVersionServiceUtil.getAppVersion(appVersionId);
}
else {
	appVersion = appEntry.getApprovedAppVersion();

	appVersionId = appVersion.getAppVersionId();
}

DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter(mvcPathParam, "/marketplace/edit_app_entry_review.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

PortalUtil.addPageTitle(appVersion.getTitle(), request);

MarketplaceUtil.addPortletBreadcrumbEntries(AppEntry.class.getName(), appEntryId, request, renderResponse);
%>

<div class="marketplace edit-app-entry-review">
	<liferay-util:include page="/marketplace/breadcrumb.jsp" servletContext="<%= application %>">
		<liferay-util:param name="backURL" value="<%= backURL %>" />
	</liferay-util:include>

	<div class="app-heading">
		<div class="icon">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
				<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appVersion.getIconImageId()) %>" />
			</liferay-portlet:resourceURL>

			<img src="<%= iconURL %>" />
		</div>

		<div class="title">
			<h1>
				<%= HtmlUtil.escape(appVersion.getTitle()) %>
			</h1>

			<div class="owner">
				<%= HtmlUtil.escape(developerEntry.getName()) %>
			</div>
		</div>
	</div>

	<div class="app-footer">
		<c:if test="<%= !themeDisplay.isSignedIn() %>">

			<%
			String loginURL = HttpUtil.addParameter(themeDisplay.getURLSignIn(), "redirect", portletURL.toString());
			%>

			<div class="portlet-msg-info">
				<a href="<%= loginURL %>">
					<liferay-ui:message key="please-sign-in-to-rate-and-review" />
				</a>
			</div>
		</c:if>

		<%
		PortletURL assetEntryURL = renderResponse.createRenderURL();

		assetEntryURL.setParameter(mvcPathParam, "/marketplace/edit_app_entry_review.jsp");
		assetEntryURL.setParameter("backURL", backURL);
		assetEntryURL.setParameter("appEntryId", String.valueOf(appEntryId));
		assetEntryURL.setParameter("appVersionId", String.valueOf(appVersionId));

		request.setAttribute(OSBWebKeys.ASSET_ENTRY_PORTLET_URL, assetEntryURL);
		%>

		<liferay-util:include page="/marketplace/app_entry_reviews.jsp" servletContext="<%= application %>">
			<liferay-util:param name="mvcPath" value="/marketplace/edit_app_entry_review.jsp" />
			<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
		</liferay-util:include>
	</div>

	<%
	AssetRecommendationSet assetRecommendationSet = AssetRecommendationSetLocalServiceUtil.fetchAssetRecommendationSet(AppEntry.class.getName(), appEntryId);
	%>

	<c:if test="<%= assetRecommendationSet != null %>">

		<%
		List<AssetRecommendationEntry> assetRecommendationEntries = AssetRecommendationEntryServiceUtil.getAssetRecommendationEntries(assetRecommendationSet.getAssetRecommendationSetId(), AssetRecommendationEntryConstants.TYPE_VIEWED_ALSO_PURCHASED, 0, 4);
		%>

		<c:if test="<%= !assetRecommendationEntries.isEmpty() %>">
			<div class="callout-b-head">
				<div class="callout-content">
					<h2 class="title">
						<liferay-ui:message key="customers-who-viewed-this-also-bought" />
					</h2>
				</div>
			</div>

			<div class="callout-e pseudo-portlet-content">
				<div class="asset">

					<%
					for (AssetRecommendationEntry assetRecommendationEntry : assetRecommendationEntries) {
						AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(assetRecommendationEntry.getClassName(), assetRecommendationEntry.getClassPK());
					%>

						<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
							<liferay-util:param name="className" value="<%= PortalUtil.getClassName(assetEntry.getClassNameId()) %>" />
							<liferay-util:param name="classPK" value="<%= String.valueOf(assetEntry.getClassPK()) %>" />
							<liferay-util:param name="displayStyle" value="1" />
							<liferay-util:param name="title" value="<%= assetEntry.getTitle() %>" />
						</liferay-util:include>

					<%
					}
					%>

				</div>
			</div>
		</c:if>
	</c:if>
</div>