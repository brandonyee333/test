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
long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetReceipt.getProductClassPK());

DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

int compatibility = ParamUtil.getInteger(request, "compatibility");
%>

<div class="marketplace-apps view-app-versions">
	<liferay-portlet:renderURL var="viewAppEntryURL">
		<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entry.jsp" />
		<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
	</liferay-portlet:renderURL>

	<liferay-util:include page="/marketplace_apps/app_entry_header.jsp" servletContext="<%= application %>">
		<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		<liferay-util:param name="backURL" value="<%= viewAppEntryURL %>" />
	</liferay-util:include>

	<h2>
		<liferay-ui:message key="past-versions" />
	</h2>

	<liferay-portlet:renderURL varImpl="compatibilityURL">
		<portlet:param name="mvcPath" value="/marketplace_apps/view_app_versions.jsp" />
		<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
	</liferay-portlet:renderURL>

	<aui:select changesContext="<%= true %>" label="filter-by" name="build-numbers" onChange="location.href = this.value;">

		<%
		compatibilityURL.setParameter("compatibility", String.valueOf(PortalReleaseConstants.PORTAL_ALL_BUILDS));
		%>

		<aui:option label="liferay-version" value="<%= compatibilityURL.toString() %>" />

		<%
		List<PortalRelease> portalReleases = PortalReleaseLocalServiceUtil.getPortalReleases(true);

		for (PortalRelease portalRelease : portalReleases) {
			if (portalRelease.isHidden() && !OrganizationLocalServiceUtil.hasUserOrganization(themeDisplay.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {
				continue;
			}

			compatibilityURL.setParameter("compatibility", String.valueOf(portalRelease.getBuildNumber()));
		%>

			<aui:option label="<%= portalRelease.getVersionName() %>" selected="<%= compatibility == portalRelease.getBuildNumber() %>" value="<%= compatibilityURL.toString() %>" />

		<%
		}
		%>

	</aui:select>

	<%
	List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	for (AppVersion appVersion : appVersions) {
	%>

		<liferay-util:include page="/marketplace_apps/app_version.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
			<liferay-util:param name="appVersionId" value="<%= String.valueOf(appVersion.getAppVersionId()) %>" />
		</liferay-util:include>

	<%
	}
	%>

</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />openLicenseKeysDialog',
		function(url) {
			Liferay.Util.openWindow(
				{
					cache: false,
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER
					},
					id: '<portlet:namespace />viewLicenseKeys',
					title: '<liferay-ui:message key="download-with-license" unicode="<%= true %>" />',
					uri: url
				}
			);
		},
		['aui-dialog', 'liferay-util-window']
	);
</aui:script>