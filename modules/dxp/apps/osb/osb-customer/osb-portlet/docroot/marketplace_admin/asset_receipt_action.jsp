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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AssetReceipt assetReceipt = (AssetReceipt)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= OSBAssetReceiptPermission.contains(themeDisplay.getPermissionChecker(), assetReceipt, OSBActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editAssetReceiptURL">
			<portlet:param name="mvcPath" value="/marketplace_admin/edit_asset_receipt.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon image="edit" url="<%= editAssetReceiptURL %>" />
	</c:if>

	<c:if test="<%= OSBAssetReceiptPermission.contains(themeDisplay.getPermissionChecker(), assetReceipt, OSBActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteAssetReceipt" var="deleteAssetReceiptURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteAssetReceiptURL %>" />
	</c:if>
</liferay-ui:icon-menu>