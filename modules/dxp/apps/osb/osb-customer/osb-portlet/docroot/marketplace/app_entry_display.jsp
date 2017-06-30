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
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);
AppVersion appVersion = appEntry.getApprovedAppVersion();

String displayStyle = ParamUtil.getString(request, "displayStyle", "icon");

PortletURL portletURL = null;

if (MarketplaceUtil.isMarketplaceServer(request)) {
	portletURL = renderResponse.createRenderURL();
}
else {
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

	portletURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, marketplacePlid, PortletRequest.RENDER_PHASE);
}

long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId");

if ((assetCategoryId == PortletPropsValues.MARKETPLACE_THEMES_ASSET_CATEGORY_ID) && (displayStyle.equals("tile") || displayStyle.equals("featured"))) {
	displayStyle += "-theme";
}

portletURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
%>

<div class="app-entry-display">
	<div class='app-entry<%= Validator.isNotNull(displayStyle) ? " app-entry-" + HtmlUtil.escape(displayStyle) : StringPool.BLANK %>'>
		<div class="images">
			<c:if test='<%= displayStyle.equals("tile-theme") || displayStyle.equals("featured-theme") %>'>
				<div class="screen-capture">

					<%
					List<AssetAttachment> assetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetAttachmentConstants.TYPE_MEDIA, 0, 1, null);
					%>

					<c:if test="<%= !assetAttachments.isEmpty() %>">

						<%
						AssetAttachment assetAttachment = assetAttachments.get(0);
						%>

						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveMedia" var="approvedMediaURL">
							<portlet:param name="assetAttachmentId" value="<%= String.valueOf(assetAttachment.getAssetAttachmentId()) %>" />
						</liferay-portlet:resourceURL>

						<img alt="<%= assetAttachment.getFileName() %>" src="<%= approvedMediaURL %>" />
					</c:if>
				</div>
			</c:if>

			<div class="icon">
				<a href="<%= portletURL.toString() %>">

					<%
					LiferayPortletURL appEntryIconURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					appEntryIconURL.setCopyCurrentRenderParameters(false);
					appEntryIconURL.setParameter("assetAttachmentId", String.valueOf(appEntry.getIconImageId()), false);
					appEntryIconURL.setResourceID("serveIcon");
					%>

					<img src="<%= appEntryIconURL.toString() %>" />
				</a>
			</div>
		</div>

		<ul class="info">
			<li class="title">
				<a href="<%= portletURL.toString() %>" title="<%= HtmlUtil.escapeAttribute(appEntry.getTitle()) %>">
					<%= HtmlUtil.escape(MarketplaceUtil.shortenString(appEntry.getTitle(), 50)) %>
				</a>
			</li>
			<li class="owner">
				<%= HtmlUtil.escape(MarketplaceUtil.shortenString(appEntry.getDeveloperEntryName(), 50)) %>
			</li>
			<li class="category">

				<%
				AssetEntry assetEntry = appEntry.getAssetEntry();

				AssetCategory assetCategory = MarketplaceUtil.getSubcategory(assetEntry);
				%>

				<%= assetCategory.getName() %>
			</li>

			<%
			String purchaseURL = StringPool.BLANK;

			if (MarketplaceUtil.isMarketplaceServer(request) && (!appEntry.isFree() || appEntry.isSOEEPlugin())) {
				purchaseURL = MarketplaceUtil.getExternalPurchaseURL(company.getVirtualHostname(), appEntryId);
			}
			else if (appEntry.hasCustomOrderWorkflow()) {
				purchaseURL = appEntry.getOrderURL();
			}
			else {
				portletURL.setParameter(mvcPathParam, "/marketplace/purchase_app_entry.jsp");
				portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

				purchaseURL = portletURL.toString();

				if (!themeDisplay.isSignedIn()) {
					purchaseURL = HttpUtil.addParameter(themeDisplay.getURLSignIn(), "redirect", purchaseURL);
				}
			}
			%>

			<c:choose>
				<c:when test="<%= appEntry.isPortalRequired() %>" />
				<c:when test="<%= appEntry.hasCustomOrderWorkflow() || appEntry.isSOEEPlugin() %>">
					<li>
						<a class="btn price" href="<%= purchaseURL %>" target="<%=MarketplaceUtil.isMarketplaceServer(request) ? "_blank" : StringPool.BLANK %>">
							<liferay-ui:message key="order-2" />
						</a>
					</li>
				</c:when>
				<c:when test="<%= appEntry.isLiferayEEPlugin() && !OSBAppEntryPermission.contains(permissionChecker, appEntry, OSBActionKeys.PURCHASE_APP) %>">
					<li>
						<span class="btn price">
							<liferay-ui:message key="ee-only" />
						</span>
					</li>
				</c:when>
				<c:when test="<%= appEntry.isFree() %>">
					<li>
						<a class="btn price" href="<%= purchaseURL %>">
							<liferay-ui:message key="free" />
						</a>
					</li>
				</c:when>
				<c:otherwise>

					<%
					AssetLicense assetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION, 1, WorkflowConstants.STATUS_APPROVED);

					if (assetLicense == null) {
						assetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION, 1, WorkflowConstants.STATUS_APPROVED);
					}

					AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.fetchAppPricingItem(appVersion.getAppVersionId(), storeCountryId, assetLicense.getAssetLicenseId());
					%>

					<c:if test="<%= appPricingItem != null %>">
						<li>
							<a class="btn price" href="<%= purchaseURL %>" target="<%= MarketplaceUtil.isMarketplaceServer(request) ? "_blank" : StringPool.BLANK %>">
								<%= appPricingItem.getFormattedPrice(locale) %>
							</a>
						</li>
					</c:if>
				</c:otherwise>
			</c:choose>
		</ul>

		<c:if test='<%= displayStyle.equals("featured") || displayStyle.equals("list") || displayStyle.equals("tile") %>'>
			<div class="description">

				<%
				int descriptionLength = 140;

				if (displayStyle.equals("featured")) {
					descriptionLength = 400;
				}
				else if (displayStyle.equals("list")) {
					descriptionLength = 650;
				}
				%>

				<%= MarketplaceMarkupUtil.getSummary(appVersion.getDescription(themeDisplay.getLanguageId()), descriptionLength) %>
			</div>
		</c:if>
	</div>
</div>