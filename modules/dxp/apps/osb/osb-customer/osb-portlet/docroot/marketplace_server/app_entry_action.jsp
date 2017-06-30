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

<%@ include file="/marketplace_server/init.jsp" %>

<%
String tabs1 = (String)request.getAttribute("view_purchased.jsp-tabs1");

int compatibility = GetterUtil.getInteger(request.getAttribute("view_purchased.jsp-compatibility"));

PortalRelease portalRelease = PortalReleaseLocalServiceUtil.getPortalRelease(compatibility);

String ownerClassName = (String)request.getAttribute("view_purchased.jsp-ownerClassName");
long ownerClassPK = GetterUtil.getLong(request.getAttribute("view_purchased.jsp-ownerClassPK"));

boolean supportsHotDeploy = GetterUtil.getBoolean(request.getAttribute("view_purchased.jsp-supportsHotDeploy"));

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AppEntry appEntry = null;
AssetReceipt assetReceipt = null;

if (row.getObject() instanceof AppEntry) {
	appEntry = (AppEntry)row.getObject();
}
else {
	assetReceipt = (AssetReceipt)row.getObject();
}

if (appEntry == null) {
	appEntry = AppEntryLocalServiceUtil.getAppEntry(assetReceipt.getProductClassPK());
}
else if (assetReceipt == null) {
	assetReceipt = AssetReceiptLocalServiceUtil.fetchAssetReceipt(ownerClassName, ownerClassPK, AppEntry.class.getName(), appEntry.getAppEntryId());
}

AppPackage appPackage = null;

if (tabs1.equals("developer")) {
	appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_DRAFT);

	if (appPackage == null) {
		appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_DENIED);
	}

	if (appPackage == null) {
		appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_PENDING);
	}

	if (appPackage == null) {
		appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_PENDING_QA);
	}

	if (appPackage == null) {
		appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED);
	}
}
else if (appEntry.isHidden()) {
	appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED_HIDDEN);
}
else {
	appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED);
}

AppVersion appVersion = null;

if (appPackage != null) {
	appVersion = appPackage.getAppVersion();
}

if ((appPackage != null) && appPackage.isPrepackaged() && (assetReceipt == null)) {
	List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(themeDisplay.getUserId());

	for (CorpProject corpProject : corpProjects) {
		assetReceipt = AssetReceiptLocalServiceUtil.fetchAssetReceipt(CorpProject.class.getName(), corpProject.getCorpProjectId(), AppEntry.class.getName(), appEntry.getAppEntryId());

		if (assetReceipt != null) {
			ownerClassName = CorpProject.class.getName();
			ownerClassPK = corpProject.getCorpProjectId();
		}
	}
}

AssetAttachment sourceCodeAssetAttachment = null;

if (appPackage != null) {
	List<AssetAttachment> sourceCodeAssetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppPackage.class.getName(), appPackage.getAppPackageId(), AssetAttachmentConstants.TYPE_PACKAGE_SRC, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

	if (!sourceCodeAssetAttachments.isEmpty()) {
		sourceCodeAssetAttachment = sourceCodeAssetAttachments.get(0);
	}

	if (appEntry.isSOEEPlugin() && !MarketplaceSOEEUtil.hasSourceCodePermission(ownerClassName, ownerClassPK)) {
		sourceCodeAssetAttachment = null;
	}
}
%>

<c:choose>
	<c:when test="<%= !supportsHotDeploy && (appPackage != null) %>">
		<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveApp" var="serveAppURL">
			<portlet:param name="appPackageId" value="<%= String.valueOf(appPackage.getAppPackageId()) %>" />
		</liferay-portlet:resourceURL>

		<a class="btn" href="<%= serveAppURL %>"><liferay-ui:message key="download" /></a>
	</c:when>
	<c:when test="<%= (appPackage != null) && appVersion.isPortalRequired() %>">
	</c:when>
	<c:when test="<%= (appPackage != null) && (appVersion != null) %>">
		<div class="app-entry-action" data-appEntryId="<%= appVersion.getAppEntryId() %>" data-appPackageId="<%= appPackage.getAppPackageId() %>" data-compatible="true" data-licensed="<%= String.valueOf(appEntry.getLicenseType() != AssetLicenseConstants.LICENSE_TYPE_FREE) %>" data-paclEnabled="<%= String.valueOf(appVersion.isPaclEnabled()) %>" data-portalRestartRequired="<%= String.valueOf(appPackage.isPortalRestartRequired()) %>" data-prepackaged="<%= appPackage.isPrepackaged() %>" data-purchased="<%= String.valueOf((assetReceipt != null) || tabs1.equals("developer")) %>" data-version="<%= HtmlUtil.escapeAttribute(appVersion.getVersion()) %>">
			<a class="btn loading" data-action="pleaseWait"><liferay-ui:message key="please-wait" /></a>

			<c:if test="<%= (assetReceipt != null) && appEntry.isSOEEPlugin() %>">
				<a class="btn always-show" href="<%= _getOwnerAppEntryLicensesURL(assetReceipt.getAssetReceiptId(), ownerClassName, ownerClassPK, themeDisplay) %>" target="_blank"><liferay-ui:message key="licenses" /></a>
			</c:if>

			<c:if test="<%= (assetReceipt != null) && (sourceCodeAssetAttachment != null) %>">
				<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAppPackageSrc" var="serveAppPackageSrcURL">
					<portlet:param name="assetAttachmentId" value="<%= String.valueOf(sourceCodeAssetAttachment.getAssetAttachmentId()) %>" />
				</liferay-portlet:resourceURL>

				<a class="btn aui-helper-hidden" href="<%= serveAppPackageSrcURL %>" target="_blank"><liferay-ui:message key="source-code" /></a>
			</c:if>

			<liferay-portlet:renderURL var="purchaseAppEntryURL">
				<portlet:param name="mvcPath" value="/marketplace_server/purchase_app_entry.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
				<portlet:param name="compatibility" value="<%= String.valueOf(compatibility) %>" />
			</liferay-portlet:renderURL>

			<c:choose>
				<c:when test="<%= (assetReceipt == null) && appPackage.isPrepackaged() %>">
					<a class="btn aui-helper-hidden update-prepackaged" data-action="updateApp"><liferay-ui:message key="update" /></a>
				</c:when>
				<c:otherwise>
					<a class="btn aui-helper-hidden" data-action="updateApp"><liferay-ui:message key="update" /></a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test='<%= (appEntry.isFree() && ((assetReceipt != null) || appEntry.isLiferayEEPlugin())) || tabs1.equals("developer") %>'>
					<a class="btn aui-helper-hidden" data-action="installApp"><liferay-ui:message key="install" /></a>
				</c:when>
				<c:when test="<%= !appEntry.isFree() && (assetReceipt != null) %>">
					<liferay-portlet:renderURL var="viewLicenseKeysURL">
						<portlet:param name="mvcPath" value="/marketplace_server/view_license_keys.jsp" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
						<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
					</liferay-portlet:renderURL>

					<a class="btn aui-helper-hidden" data-action="installApp" href="<%= viewLicenseKeysURL %>"><liferay-ui:message key="install" /></a>
				</c:when>
				<c:otherwise>
					<a class="btn aui-helper-hidden" data-action="installApp" href="<%= purchaseAppEntryURL %>"><liferay-ui:message key="install" /></a>
				</c:otherwise>
			</c:choose>

			<a class="btn aui-helper-hidden" data-action="uninstallApp"><liferay-ui:message key="uninstall" /></a>

			<a class="btn disabled up-to-date aui-helper-hidden"><liferay-ui:message key="up-to-date" /></a>
		</div>

		<c:if test="<%= !appVersion.isPaclEnabled() %>">
			<div class="security-disabled aui-helper-hidden">
				<liferay-ui:message key="this-app-no-longer-has-security-enabled" />
			</div>
		</c:if>
	</c:when>
	<c:otherwise>
		<div class="app-entry-action" data-appEntryId="<%= appEntry.getAppEntryId() %>" data-compatible="false">
			<a class="btn disabled"><liferay-ui:message key="not-compatible" /></a>

			<%
			String versionName = StringPool.BLANK;

			AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();

			if (approvedAppVersion != null) {
				List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(approvedAppVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AppPackageCompatibilityComparator(false));

				for (AppPackage curAppPackage : appPackages) {
					PortalRelease curAppPackagePortalRelease = PortalReleaseLocalServiceUtil.getPortalRelease(curAppPackage.getCompatibility());

					if (curAppPackagePortalRelease.isEE() && portalRelease.isEE()) {
						versionName = PortalReleaseUtil.getVersionName(curAppPackage);
					}
					else if (!curAppPackagePortalRelease.isEE() && !portalRelease.isEE()) {
						versionName = PortalReleaseUtil.getVersionName(curAppPackage);
					}

					if (Validator.isNotNull(versionName) && (curAppPackage.getCompatibility() > compatibility)) {
						break;
					}
				}
			}
			%>

			<c:if test="<%= Validator.isNotNull(versionName) %>">
				<div class="compatible-version">
					<liferay-ui:message arguments="<%= versionName %>" key="newer-version-available-for-x" />
				</div>
			</c:if>
		</div>
	</c:otherwise>
</c:choose>

<%!
private String _getOwnerAppEntryLicensesURL(long assetReceiptId, String ownerClassName, long ownerClassPK, ThemeDisplay themeDisplay) throws Exception {
	StringBundler sb = new StringBundler(5);

	sb.append("/web");

	User user = themeDisplay.getUser();

	Group group = user.getGroup();

	if (Validator.isNotNull(group.getFriendlyURL())) {
		sb.append(group.getFriendlyURL());
	}
	else {
		sb.append(group.getGroupId());
	}

	long plid = PortalUtil.getPlidFromPortletId(group.getGroupId(), OSBPortletKeys.OSB_MARKETPLACE_APPS);

	Layout layout = LayoutLocalServiceUtil.getLayout(plid);

	sb.append(layout.getFriendlyURL());
	sb.append("/-/marketplace_apps/");
	sb.append(assetReceiptId);

	Company company = themeDisplay.getCompany();

	String portalURL = PortalUtil.getPortalURL(company.getVirtualHostname(), PortalUtil.getPortalPort(true), true);

	return HttpUtil.addParameter(portalURL + "/c/portal/login", "redirect", sb.toString());
}
%>