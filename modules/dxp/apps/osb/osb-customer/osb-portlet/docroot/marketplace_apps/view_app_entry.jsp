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

<%@ include file="/marketplace_apps/init.jsp" %>

<%
long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetReceipt.getProductClassPK());

AppVersion appVersion = appEntry.getApprovedAppVersion();
%>

<div class="marketplace-apps view-app-entry">
	<liferay-portlet:renderURL var="viewURL">
		<portlet:param name="mvcPath" value="/marketplace_apps/view.jsp" />
		<portlet:param name="ownerClassName" value="<%= assetReceipt.getOwnerClassName() %>" />
		<portlet:param name="ownerClassPK" value="<%= String.valueOf(assetReceipt.getOwnerClassPK()) %>" />
	</liferay-portlet:renderURL>

	<liferay-util:include page="/marketplace_apps/app_entry_header.jsp" servletContext="<%= application %>">
		<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		<liferay-util:param name="backURL" value="<%= viewURL %>" />
	</liferay-util:include>

	<div class="deprecated-warning">
		<c:if test="<%= AppFlagLocalServiceUtil.hasAppFlag(appVersion.getAppVersionId(), AppFlagConstants.TYPE_DEPRECATED) %>">
			<strong><liferay-ui:message key="final-version" />:</strong>

			<liferay-ui:message key="no-new-versions-will-be-available-for-this-app" />
		</c:if>
	</div>

	<div class="version-information">
		<table>
			<thead>
				<tr>
					<th class="col-1 first">
						<liferay-ui:message key="latest-app-version" />
					</th>
					<th class="col-2">
						<liferay-ui:message key="release-date" />
					</th>
					<th class="col-3 last">
						<liferay-ui:message key="security" />
					</th>
				</tr>
			</thead>
			<tbody>
				<tr class="last">
					<td class="col-1 first">
						<%= appVersion.getVersion() %>
					</td>
					<td class="col-2">
						<%= shortDateFormatDate.format(appVersion.getReleaseDate()) %>
					</td>
					<td class="col-3 last">
						<liferay-ui:message key='<%= appVersion.isPaclEnabled() ? "enabled" : "disabled" %>' />
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="downloads">
		<h2>
			<liferay-ui:message key="downloads" />
		</h2>

		<p>
			<liferay-ui:message arguments="<%= HtmlUtil.escape(appVersion.getTitle()) %>" key="below-are-the-available-downloads-for-the-latest-version-of-x" />
		</p>

		<%
		List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		%>

		<div class="version wrapper">
			<liferay-ui:search-container
				delta="<%= appPackages.size() %>"
			>
				<liferay-ui:search-container-results
					results="<%= appPackages %>"
					total="<%= appPackages.size() %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.AppPackage"
					escapedModel="<%= true %>"
					keyProperty="appPackageId"
					modelVar="appPackage"
				>
					<liferay-ui:search-container-column-text
						name="liferay-version"
						value="<%= PortalReleaseUtil.getVersionName(appPackage) %>"
					/>

					<%
					row.setParameter("appEntry", appEntry);
					row.setParameter("assetReceipt", assetReceipt);
					%>

					<liferay-ui:search-container-column-jsp
						align="right"
						name="downloads"
						path="/marketplace_apps/app_package_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="<%= false %>" />
			</liferay-ui:search-container>

			<%
			int count = AppVersionLocalServiceUtil.getAppVersionsCount(appEntry.getAppEntryId(), WorkflowConstants.STATUS_APPROVED);
			%>

			<c:if test="<%= count > 1 %>">
				<liferay-portlet:renderURL var="pastVersionsURL">
					<portlet:param name="mvcPath" value="/marketplace_apps/view_app_versions.jsp" />
					<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
				</liferay-portlet:renderURL>

				<aui:a cssClass="btn" href="<%= pastVersionsURL %>" label="past-versions" />
			</c:if>

			<c:if test="<%= !appEntry.isFree() || appEntry.isSOEEPlugin() %>">
				<liferay-portlet:renderURL var="manageLicensesURL">
					<portlet:param name="mvcPath" value="/marketplace_apps/view_license_keys.jsp" />
					<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
				</liferay-portlet:renderURL>

				<aui:a cssClass="btn" href="<%= manageLicensesURL %>" label="manage-licenses" />
			</c:if>
		</div>
	</div>
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