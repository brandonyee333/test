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
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

String ownerClassName = ParamUtil.getString(request, "ownerClassName");
long ownerClassPK = ParamUtil.getLong(request, "ownerClassPK");

AssetReceipt assetReceipt = AssetReceiptServiceUtil.fetchAssetReceipt(ownerClassName, ownerClassPK, AppEntry.class.getName(), appEntryId);

long assetReceiptId = 0;

if (assetReceipt != null) {
	assetReceiptId = assetReceipt.getAssetReceiptId();
}
%>

<c:choose>
	<c:when test="<%= appEntry.isFree() && (assetReceipt != null) %>">
		<div class="licenses">
			<liferay-ui:message key="purchased" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		int count = AssetLicenseLocalServiceUtil.getAssetLicensesCount(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED);
		%>

		<c:if test="<%= count > 0 %>">
			<div class="licenses standard">
				<div class="type">
					<liferay-ui:message key="standard" />
				</div>

				<div class="allotment">
					<liferay-ui:message key="instances" />: <%= AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenseTypeAllotment(assetReceiptId, AssetLicenseConstants.USAGE_TYPE_STANDARD) %>
				</div>

				<c:if test="<%= assetReceipt != null %>">

					<%
					Date assetReceiptLicensesEndDate = assetReceipt.getAssetReceiptLicensesEndDate(AssetLicenseConstants.USAGE_TYPE_STANDARD);
					%>

					<c:if test="<%= (assetReceiptLicensesEndDate != null) && (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION) %>">
						<div class="license">
							<liferay-ui:message key="license-exp" />: <span><%= mediumDateFormatDate.format(assetReceiptLicensesEndDate) %></span>
						</div>
					</c:if>

					<%
					Date assetReceiptSupportsEndDate = assetReceipt.getAssetReceiptSupportsEndDate(AssetLicenseConstants.USAGE_TYPE_STANDARD);
					%>

					<c:if test="<%= assetReceiptSupportsEndDate != null %>">
						<div class="support">
							<liferay-ui:message key="support-exp" />: <span><%= mediumDateFormatDate.format(assetReceiptSupportsEndDate) %></span>
						</div>
					</c:if>
				</c:if>
			</div>
		</c:if>

		<%
		count = AssetLicenseLocalServiceUtil.getAssetLicensesCount(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED);
		%>

		<c:if test="<%= count > 0 %>">
			<div class="licenses developer">
				<div class="type">
					<liferay-ui:message key="developer" />
				</div>

				<div class="allotment">
					<liferay-ui:message key="instances" />: <%= AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenseTypeAllotment(assetReceiptId, AssetLicenseConstants.USAGE_TYPE_DEVELOPER) %>
				</div>

				<c:if test="<%= assetReceipt != null %>">

					<%
					Date assetReceiptLicensesEndDate = assetReceipt.getAssetReceiptLicensesEndDate(AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
					%>

					<c:if test="<%= (assetReceiptLicensesEndDate != null) && (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION) %>">
						<div class="license">
							<liferay-ui:message key="license-exp" />: <span><%= mediumDateFormatDate.format(assetReceiptLicensesEndDate) %></span>
						</div>
					</c:if>

					<%
					Date assetReceiptSupportsEndDate = assetReceipt.getAssetReceiptSupportsEndDate(AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
					%>

					<c:if test="<%= assetReceiptSupportsEndDate != null %>">
						<div class="support">
							<liferay-ui:message key="support-exp" />: <span><%= mediumDateFormatDate.format(assetReceiptSupportsEndDate) %></span>
						</div>
					</c:if>
				</c:if>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>