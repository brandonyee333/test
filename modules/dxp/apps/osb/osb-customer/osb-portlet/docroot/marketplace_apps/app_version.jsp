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

long appVersionId = ParamUtil.getLong(request, "appVersionId");

AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(appVersionId);

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appVersion.getAppEntryId());
%>

<div class="app-version">
	<h4>
		<liferay-ui:message arguments="<%= appVersion.getVersion() %>" key="version-x" />
	</h4>

	<div class="data">
		<span>
			<strong>
				<liferay-ui:message key="release-date" />
			</strong>

			<%= shortDateFormatDate.format(appVersion.getReleaseDate()) %>
		</span>

		<span>
			<strong>
				<liferay-ui:message key="security" />
			</strong>

			<liferay-ui:message key='<%= appVersion.isPaclEnabled() ? "enabled" : "disabled" %>' />
		</span>
	</div>

	<div class="packages">

		<%
		List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		%>

		<div class="packages">
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
		</div>
	</div>
</div>