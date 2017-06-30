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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntry ecDocumentEntry = ECDocumentEntryLocalServiceUtil.getECDocumentEntry(ecDocumentEntryId);

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.fetchAssetReceipt(ecDocumentEntryExtraSettings.getOwnerClassName(), ecDocumentEntryExtraSettings.getOwnerClassPK(), AppEntry.class.getName(), appEntryId);

String paymentMethod = ParamUtil.getString(request, "paymentMethod");

MarketplaceUtil.addPortletBreadcrumbEntries(AppEntry.class.getName(), appEntryId, request, renderResponse);
%>

<div class="marketplace view-app-entry-receipt">
	<liferay-util:include page="/marketplace/breadcrumb.jsp" servletContext="<%= application %>">
		<liferay-util:param name="backURL" value="<%= backURL %>" />
	</liferay-util:include>

	<h2 class="receipt-title">
		<liferay-ui:message key="receipt" />
	</h2>

	<div class="unit asset">
		<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="className" value="<%= AppEntry.class.getName() %>" />
			<liferay-util:param name="classPK" value="<%= String.valueOf(appEntryId) %>" />
			<liferay-util:param name="displayStyle" value="5" />
			<liferay-util:param name="title" value="<%= appEntry.getTitle() %>" />
		</liferay-util:include>
	</div>

	<div class="cleared"></div>

	<div class="container">
		<h3>
			<liferay-ui:message key="thank-you" />
		</h3>

		<p>
			<liferay-ui:message arguments="<%= ecDocumentEntryId %>" key="your-transaction-id-number-is-x" />
		</p>

		<c:choose>
			<c:when test='<%= paymentMethod.equals("request-invoice") %>'>
				<p>
					<liferay-ui:message key="a-confirmation-email-for-this-order-was-sent-to-your-inbox" />
				</p>

				<p>
					<liferay-ui:message key="you-should-be-receiving-an-invoice-from-our-accounts-team-shortly" />

					<liferay-ui:message key="once-payment-is-received,-your-purchased-app-will-be-available-under-apps-in-my-account-or-you-can-go-to-the-marketplace-through-your-portal-to-manage-your-purchases-from-there" />
				</p>
			</c:when>
			<c:otherwise>
				<p>
					<liferay-ui:message key="a-confirmation-email-for-this-order-was-sent-to-your-inbox" />
				</p>

				<p>
					<liferay-ui:message key="click-on-see-purchased-to-view-and-manage-your-purchases-online-or-you-may-go-to-the-marketplace-through-your-liferay-portal-instance-and-manage-your-purchases-from-there" />
				</p>
			</c:otherwise>
		</c:choose>

		<div class="button-container">
			<liferay-portlet:renderURL var="viewURL">
				<portlet:param name="<%= mvcPathParam %>" value="/marketplace/view.jsp" />
				<portlet:param name="assetCategoryId" value="0" />
			</liferay-portlet:renderURL>

			<a class="btn" href="<%= viewURL %>"><liferay-ui:message key="keep-shopping" /></a>

			<c:choose>
				<c:when test="<%= !MarketplaceUtil.isMarketplaceServer(request) %>">

					<%
					long marketplaceAppsPlid = PortalUtil.getPlidFromPortletId(user.getGroupId(), OSBPortletKeys.OSB_MARKETPLACE_APPS);
					%>

					<c:choose>
						<c:when test="<%= assetReceipt != null %>">
							<liferay-portlet:renderURL plid="<%= marketplaceAppsPlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE_APPS %>" var="marketplaceAppsURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
								<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entry.jsp" />
								<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
							</liferay-portlet:renderURL>

							<a class="btn" href="<%= marketplaceAppsURL %>"><liferay-ui:message key="see-purchased" /></a>
						</c:when>
						<c:otherwise>
							<liferay-portlet:renderURL plid="<%= marketplaceAppsPlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE_APPS %>" var="marketplaceAppsURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>" />

							<a class="btn" href="<%= marketplaceAppsURL %>"><liferay-ui:message key="see-purchased" /></a>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<a class="btn" href="javascript:;" onClick="Liferay.MarketplaceUtil.openMarketplacePurchased();"><liferay-ui:message key="see-purchased" /></a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<%
	AssetRecommendationSet assetRecommendationSet = AssetRecommendationSetLocalServiceUtil.fetchAssetRecommendationSet(AppEntry.class.getName(), appEntryId);
	%>

	<c:if test="<%= assetRecommendationSet != null %>">

		<%
		List<AssetRecommendationEntry> assetRecommendationEntries = AssetRecommendationEntryServiceUtil.getAssetRecommendationEntries(assetRecommendationSet.getAssetRecommendationSetId(), AssetRecommendationEntryConstants.TYPE_PURCHASED_ALSO_PURCHASED, 0, 3);
		%>

		<c:if test="<%= !assetRecommendationEntries.isEmpty() %>">
			<div class="recommendations">
				<h2 class="section-title">
					<liferay-ui:message key="customers-who-bought-this-also-bought" />
				</h2>

				<div class="asset container">

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