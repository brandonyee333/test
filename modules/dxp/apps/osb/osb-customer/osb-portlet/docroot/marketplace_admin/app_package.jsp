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
long appPackageId = ParamUtil.getLong(request, "appPackageId");

boolean displayLinks = ParamUtil.getBoolean(request, "displayLinks");

List<AppPackagePlugin> appPackagePlugins = AppPackagePluginLocalServiceUtil.getAppPackagePlugins(appPackageId);

for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
%>

	<div>
		<c:choose>
			<c:when test="<%= displayLinks %>">
				<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAppPackagePlugin" var="serveAppPackagePluginURL">
					<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appPackagePlugin.getAssetAttachmentId()) %>" />
				</liferay-portlet:resourceURL>

				<a href="<%= serveAppPackagePluginURL %>"><%= appPackagePlugin.getFileName() %></a>
			</c:when>
			<c:otherwise>
				<%= appPackagePlugin.getFileName() %>
			</c:otherwise>
		</c:choose>
	</div>

<%
}

List<AssetAttachment> sourceCodeAssetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppPackage.class.getName(), appPackageId, AssetAttachmentConstants.TYPE_PACKAGE_SRC, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

for (AssetAttachment sourceCodeAssetAttachment : sourceCodeAssetAttachments) {
%>

	<div>
		<c:choose>
			<c:when test="<%= displayLinks %>">
				<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAppPackageSrc" var="serveAppPackageSrcURL">
					<portlet:param name="assetAttachmentId" value="<%= String.valueOf(sourceCodeAssetAttachment.getAssetAttachmentId()) %>" />
				</liferay-portlet:resourceURL>

				<a href="<%= serveAppPackageSrcURL %>">
					<%= sourceCodeAssetAttachment.getFileName() %>
				</a>

				(<liferay-ui:message key="source-code" />)
			</c:when>
			<c:otherwise>
				<%= sourceCodeAssetAttachment.getFileName() %>
			</c:otherwise>
		</c:choose>
	</div>

<%
}
%>

<c:if test="<%= displayLinks %>">
	<div>
		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveUnlicensedApp" var="approvedAppFileURL">
			<portlet:param name="appPackageId" value="<%= String.valueOf(appPackageId) %>" />
		</liferay-portlet:resourceURL>

		<a href="<%= approvedAppFileURL %>">Unlicensed LPKG</a>
	</div>

	<div>
		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveLicensedApp" var="approvedLicensedAppFileURL">
			<portlet:param name="appPackageId" value="<%= String.valueOf(appPackageId) %>" />
		</liferay-portlet:resourceURL>

		<a href="<%= approvedLicensedAppFileURL %>">Licensed LPKG</a>
	</div>
</c:if>