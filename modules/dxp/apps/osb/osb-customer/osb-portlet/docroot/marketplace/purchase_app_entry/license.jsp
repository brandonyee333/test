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
long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntry ecDocumentEntry = ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

List<ECDocumentItem> ecDocumentItems = ecDocumentEntry.getECDocumentItems();

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

AssetReceipt assetReceipt = AssetReceiptServiceUtil.fetchAssetReceipt(ecDocumentEntryExtraSettings.getOwnerClassName(), ecDocumentEntryExtraSettings.getOwnerClassPK(), AppEntry.class.getName(), appEntryId);

int assetReceiptLicenseCount = 0;

if (assetReceipt != null) {
	assetReceiptLicenseCount += AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicensesCount(assetReceipt.getAssetReceiptId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
	assetReceiptLicenseCount += AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicensesCount(assetReceipt.getAssetReceiptId(), AssetLicenseConstants.USAGE_TYPE_STANDARD);
}

String purchaseType = ecDocumentEntryExtraSettings.getPurchaseType();

if (Validator.isNull(purchaseType)) {
	purchaseType = ParamUtil.getString(renderRequest, "purchaseType");
}
%>

<div class="purchase-app-entry-license">
	<c:choose>
		<c:when test='<%= (assetReceiptLicenseCount <= 0) || purchaseType.equals("new") %>'>
			<%@ include file="/marketplace/purchase_app_entry/license_new.jspf" %>
		</c:when>
		<c:when test='<%= (assetReceiptLicenseCount > 0) && purchaseType.equals("renew") %>'>
			<%@ include file="/marketplace/purchase_app_entry/license_renew.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/marketplace/purchase_app_entry/license_purchase_type.jspf" %>
		</c:otherwise>
	</c:choose>
</div>