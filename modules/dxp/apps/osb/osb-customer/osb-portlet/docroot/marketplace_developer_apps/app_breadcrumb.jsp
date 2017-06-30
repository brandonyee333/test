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
String mvcPath = ParamUtil.getString(request, "mvcPath");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(appEntryId);

AppVersion appVersion = null;

if (appEntry != null) {
	appVersion = appEntry.getActionableAppVersion();
}

int releaseType = ParamUtil.getInteger(request, "releaseType");

if ((appVersion != null) && !appVersion.isApproved()) {
	releaseType = appVersion.getReleaseType();
}
%>

<ul class="app-breadcrumbs">

	<%
	String appDetailsCssClass = _getAppDetailsCssClass(mvcPath, appVersion, releaseType);
	%>

	<c:if test="<%= Validator.isNotNull(appDetailsCssClass) %>">
		<li>
			<liferay-portlet:renderURL var="editAppDetailsURL">
				<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</liferay-portlet:renderURL>

			<a class="<%= appDetailsCssClass %>"<%= appDetailsCssClass.endsWith(" active") ? " href=\"" + editAppDetailsURL + "\"" : StringPool.BLANK %>>
				<div class="sprite app-details"></div>

				<liferay-ui:message key="app-details" />
			</a>
		</li>
		<li class="arrow-container">
			<div class="sprite arrow"></div>
		</li>
	</c:if>

	<%
	String uploadCssClass = _getUploadCssClass(mvcPath, appVersion, releaseType);
	%>

	<c:if test="<%= Validator.isNotNull(uploadCssClass) %>">
		<li>
			<liferay-portlet:renderURL var="editAppVersionURL">
				<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_version.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</liferay-portlet:renderURL>

			<a class="<%= uploadCssClass %>"<%= uploadCssClass.endsWith(" active") ? " href=\"" + editAppVersionURL + "\"" : StringPool.BLANK %>>
				<div class="sprite upload"></div>

				<liferay-ui:message key="upload" />
			</a>
		</li>
		<li class="arrow-container">
			<div class="sprite arrow"></div>
		</li>
	</c:if>

	<c:if test="<%= (appEntry == null) || (!appVersion.hasCustomOrderWorkflow() && !appVersion.isFree()) %>">
		<li>

			<%
			String licenseCssClass = _getLicenseCssClass(mvcPath, appVersion);
			%>

			<liferay-portlet:renderURL var="editAppLicenseURL">
				<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_license.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</liferay-portlet:renderURL>

			<a class="<%= licenseCssClass %>"<%= licenseCssClass.endsWith(" active") ? " href=\"" + editAppLicenseURL + "\"" : StringPool.BLANK %>>
				<div class="sprite license"></div>

				<liferay-ui:message key="license" />
			</a>
		</li>
		<li class="arrow-container">
			<div class="sprite arrow"></div>
		</li>
		<li>

			<%
			String pricingCssClass = _getPricingCssClass(mvcPath, appVersion);
			%>

			<liferay-portlet:renderURL var="editAppPricingURL">
				<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_pricing.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</liferay-portlet:renderURL>

			<a class="<%= pricingCssClass %>"<%= pricingCssClass.endsWith(" active") ? " href=\"" + editAppPricingURL + "\"" : StringPool.BLANK %>>
				<div class="sprite pricing"></div>

				<liferay-ui:message key="pricing" />
			</a>
		</li>
	</c:if>
</ul>

<%!
private String _getAppDetailsCssClass(String mvcPath, AppVersion appVersion, int releaseType) throws Exception {
	if ((appVersion != null) && (releaseType == AppVersionConstants.RELEASE_TYPE_PRICING)) {
		return StringPool.BLANK;
	}

	String cssClass = "app-breadcrumb app-details";

	if (mvcPath.equals("/marketplace_developer_apps/edit_app_entry.jsp")) {
		return cssClass.concat(" current");
	}
	else {
		return cssClass.concat(" active");
	}
}

private String _getLicenseCssClass(String mvcPath, AppVersion appVersion) throws Exception {
	String cssClass = "app-breadcrumb license";

	if (appVersion == null) {
		return cssClass.concat(" inactive");
	}
	else if (mvcPath.equals("/marketplace_developer_apps/edit_app_license.jsp")) {
		return cssClass.concat(" current");
	}

	int appPackagesCount = AppPackageLocalServiceUtil.getAppPackagesCount(appVersion.getAppVersionId());

	if ((appPackagesCount == 0) || appVersion.isApproved()) {
		return cssClass.concat(" inactive");
	}
	else {
		return cssClass.concat(" active");
	}
}

private String _getPricingCssClass(String mvcPath, AppVersion appVersion) throws Exception {
	String cssClass = "app-breadcrumb pricing";

	if (appVersion == null) {
		return cssClass.concat(" inactive");
	}
	else if (mvcPath.equals("/marketplace_developer_apps/edit_app_pricing.jsp")) {
		return cssClass.concat(" current");
	}

	int assetLicenseCount = AssetLicenseLocalServiceUtil.getAssetLicensesCount(AppVersion.class.getName(), appVersion.getAppVersionId(), WorkflowConstants.STATUS_ANY);

	if (appVersion.isApproved() || (assetLicenseCount == 0)) {
		return cssClass.concat(" inactive");
	}
	else {
		return cssClass.concat(" active");
	}
}

private String _getUploadCssClass(String mvcPath, AppVersion appVersion, int releaseType) throws Exception {
	if ((appVersion != null) && (releaseType == AppVersionConstants.RELEASE_TYPE_PRICING)) {
		return StringPool.BLANK;
	}

	String cssClass = "app-breadcrumb upload";

	if (mvcPath.equals("/marketplace_developer_apps/edit_app_version.jsp")) {
		return cssClass.concat(" current");
	}
	else if ((appVersion == null) || appVersion.isApproved()) {
		return cssClass.concat(" inactive");
	}
	else {
		return cssClass.concat(" active");
	}
}
%>