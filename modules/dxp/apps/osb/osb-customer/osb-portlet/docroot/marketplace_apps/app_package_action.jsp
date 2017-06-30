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

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AppPackage appPackage = (AppPackage)row.getObject();

AppEntry appEntry = (AppEntry)row.getParameter("appEntry");
AssetReceipt assetReceipt = (AssetReceipt)row.getParameter("assetReceipt");

List<AssetAttachment> sourceCodeAssetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppPackage.class.getName(), appPackage.getAppPackageId(), AssetAttachmentConstants.TYPE_PACKAGE_SRC, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
%>

<div class="app-package-action">
	<c:if test="<%= !sourceCodeAssetAttachments.isEmpty() && (!appEntry.isSOEEPlugin() || MarketplaceSOEEUtil.hasSourceCodePermission(assetReceipt.getOwnerClassName(), assetReceipt.getOwnerClassPK())) %>">

		<%
		AssetAttachment sourceCodeAssetAttachment = sourceCodeAssetAttachments.get(0);
		%>

		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAppPackageSrc" var="serveAppPackageSrcURL">
			<portlet:param name="assetAttachmentId" value="<%= String.valueOf(sourceCodeAssetAttachment.getAssetAttachmentId()) %>" />
		</liferay-portlet:resourceURL>

		<aui:a cssClass="btn" href="<%= serveAppPackageSrcURL %>" label="source" />
	</c:if>

	<%
	AppVersion appVersion = appEntry.getApprovedAppVersion();
	%>

	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveApp" var="serveAppPackageURL">
		<portlet:param name="appPackageId" value="<%= String.valueOf(appPackage.getAppPackageId()) %>" />
		<portlet:param name="version" value="<%= String.valueOf(appVersion.getVersion()) %>" />
		<portlet:param name="portalBuildNumber" value="<%= String.valueOf(appPackage.getCompatibility()) %>" />
	</liferay-portlet:resourceURL>

	<aui:a cssClass="btn" href="<%= serveAppPackageURL %>" label="app" />

	<c:if test="<%= !appEntry.isFree() || appEntry.isSOEEPlugin() %>">
		<liferay-portlet:renderURL var="viewLicenseKeysURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/marketplace_apps/view_license_keys.jsp" />
			<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
			<portlet:param name="appPackageId" value="<%= String.valueOf(appPackage.getAppPackageId()) %>" />
		</liferay-portlet:renderURL>

		<%
		String taglibOnClick = renderResponse.getNamespace() + "openLicenseKeysDialog('" + viewLicenseKeysURL + "');";
		%>

		<aui:a cssClass="btn" href="javascript:;" label="app-plus-license" onClick="<%= taglibOnClick %>" />
	</c:if>
</div>