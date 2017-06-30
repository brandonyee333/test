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

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

AssetReceipt assetReceipt = AssetReceiptServiceUtil.fetchAssetReceipt(ecDocumentEntryExtraSettings.getOwnerClassName(), ecDocumentEntryExtraSettings.getOwnerClassPK(), AppEntry.class.getName(), appEntryId);
%>

<h3>
	<c:choose>
		<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION %>">
			<liferay-ui:message key="coterminous-instance-terms" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="coterminous-subscription-services" />
		</c:otherwise>
	</c:choose>
</h3>

<portlet:actionURL name="purchaseAppEntryCoterm" var="purchaseAppEntryCotermURL" />

<aui:form action="<%= purchaseAppEntryCotermURL %>" method="post" name="fm">
	<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace/purchase_app_entry.jsp" />
	<aui:input name="purchaseStep" type="hidden" value="license" />
	<aui:input name="ecDocumentEntryId" type="hidden" value="<%= ecDocumentEntryId %>" />
	<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

	<c:choose>
		<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION %>">
			<%@ include file="/marketplace/purchase_app_entry/coterm_license.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/marketplace/purchase_app_entry/coterm_support.jspf" %>
		</c:otherwise>
	</c:choose>

	<aui:button-row>
		<portlet:renderURL var="viewAppEntryURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/view_app_entry.jsp" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</portlet:renderURL>

		<portlet:actionURL name="cancelPurchaseAppEntry" var="cancelPurchaseAppEntryURL">
			<portlet:param name="redirect" value="<%= viewAppEntryURL %>" />
			<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
		</portlet:actionURL>

		<aui:button href="<%= cancelPurchaseAppEntryURL %>" value="cancel" />

		<portlet:renderURL var="purchaseAppEntryLicenseURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
			<portlet:param name="purchaseStep" value="license" />
			<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</portlet:renderURL>

		<aui:button href="<%= purchaseAppEntryLicenseURL %>" value="back" />

		<aui:button type="submit" value="next" />
	</aui:button-row>
</aui:form>