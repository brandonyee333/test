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
String redirect = ParamUtil.getString(request, "redirect");

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntry ecDocumentEntry = _getECDocumentEntry(ecDocumentEntryId, themeDisplay.getScopeGroupId());

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(ecDocumentEntryExtraSettings.getAppEntryId());
%>

<div class="marketplace-transactions view-ec-document-entry">
	<liferay-ui:header
		title="transactions"
	/>

	<div class="app-details">
		<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
			<liferay-util:param name="assetEntryURL" value="#" />
			<liferay-util:param name="className" value="<%= AppEntry.class.getName() %>" />
			<liferay-util:param name="classPK" value="<%= String.valueOf(ecDocumentEntryExtraSettings.getAppEntryId()) %>" />
			<liferay-util:param name="displayStyle" value="5" />
			<liferay-util:param name="title" value="<%= appEntry.getTitle() %>" />
		</liferay-util:include>
	</div>

	<div class="document-details">
		<span>
			<strong><liferay-ui:message key="date" />:</strong> <%= shortDateFormatDate.format(ecDocumentEntry.getModifiedDate()) %>
		</span>

		<span>
			<strong><liferay-ui:message key="transaction-id" />:</strong> <%= ecDocumentEntry.getEcDocumentEntryId() %>
		</span>

		<span>
			<strong><liferay-ui:message key="purchased-by" />:</strong> <%= HtmlUtil.escape(PortalUtil.getUserName(ecDocumentEntry.getBillingUserId(), ecDocumentEntry.getBillingUserName())) %>
		</span>

		<%
		User billingUser = UserLocalServiceUtil.getUser(ecDocumentEntry.getBillingUserId());
		%>

		<span>
			<strong><liferay-ui:message key="email-address" />:</strong> <%= HtmlUtil.escape(billingUser.getEmailAddress()) %>
		</span>
	</div>

	<liferay-util:include page="/marketplace_transactions/ec_document_items.jsp" servletContext="<%= application %>">
		<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
	</liferay-util:include>
</div>

<a class="btn" href="<%= HtmlUtil.escapeAttribute(redirect) %>"><liferay-ui:message key="back" /></a>

<%!
private ECDocumentEntry _getECDocumentEntry(long ecDocumentEntryId, long groupId) throws Exception {
	ECDocumentEntry ecDocumentEntry = ECDocumentEntryLocalServiceUtil.getECDocumentEntry(ecDocumentEntryId);

	DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(groupId);

	if ((ecDocumentEntry.getVendorClassNameId() != PortalUtil.getClassNameId(DeveloperEntry.class.getName())) || (ecDocumentEntry.getVendorClassPK() != developerEntry.getDeveloperEntryId())) {
		throw new PrincipalException();
	}

	return ecDocumentEntry;
}
%>