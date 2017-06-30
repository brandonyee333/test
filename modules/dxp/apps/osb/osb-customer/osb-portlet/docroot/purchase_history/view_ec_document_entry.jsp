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

ECDocumentEntry ecDocumentEntry = ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(ecDocumentEntryExtraSettings.getAppEntryId());
%>

<div class="purchase-history view-ec-document-entry">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="purchase-history"
	/>

	<c:if test="<%= appEntry != null %>">
		<div class="app-details">

			<%
			long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
			%>

			<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="appEntryURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
				<portlet:param name="mvcPath" value="/marketplace/view_app_entry.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
				<liferay-util:param name="assetEntryURL" value="<%= appEntryURL.toString() %>" />
				<liferay-util:param name="className" value="<%= AppEntry.class.getName() %>" />
				<liferay-util:param name="classPK" value="<%= String.valueOf(ecDocumentEntryExtraSettings.getAppEntryId()) %>" />
				<liferay-util:param name="displayStyle" value="5" />
				<liferay-util:param name="title" value="<%= appEntry.getTitle() %>" />
			</liferay-util:include>
		</div>
	</c:if>

	<div class="document-details">
		<div class="detail">
			<span class="title">
				<liferay-ui:message key="date" />:
			</span>

			<%= shortDateFormatDate.format(ecDocumentEntry.getModifiedDate()) %>
		</div>

		<div class="detail">
			<span class="title">
				<liferay-ui:message key="transaction-id" />:
			</span>

			<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>
		</div>

		<div class="detail">
			<span class="title">
				<liferay-ui:message key="project" />:
			</span>

			<%
			String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();
			%>

			<c:if test="<%= ownerClassName.equals(CorpProject.class.getName()) %>">

				<%
				CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(ecDocumentEntryExtraSettings.getOwnerClassPK());
				%>

				<%= HtmlUtil.escape(corpProject.getName()) %>
			</c:if>
		</div>

		<div class="detail">
			<span class="title">
				<liferay-ui:message key="paid-via" />:
			</span>

			<%
			String paymentProcessor = ecDocumentEntry.getPaymentProcessor();
			%>

			<c:choose>
				<c:when test="<%= paymentProcessor.equals(MarketplaceEcommerceUtil.PAY_PAL_PAYMENT_PROCESSOR_CLASS_NAME) %>">
					<liferay-ui:message key="paypal" />
				</c:when>
				<c:when test="<%= paymentProcessor.equals(MarketplaceEcommerceUtil.MANUAL_TAX_PROCESSOR_CLASS_NAME) %>">
					<liferay-ui:message key="invoice" />
				</c:when>
			</c:choose>
		</div>
	</div>

	<liferay-util:include page="/marketplace_transactions/ec_document_items.jsp" servletContext="<%= application %>">
		<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
	</liferay-util:include>

	<aui:button href="<%= redirect %>" value="back" />
</div>