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

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);
%>

<div class="purchase-app-entry-end-user">
	<portlet:actionURL name="purchaseAppEntryEndUser" var="purchaseAppEntryEndUserURL" />

	<aui:form action="<%= purchaseAppEntryEndUserURL %>" method="post" name="fm">
		<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace/purchase_app_entry.jsp" />

		<portlet:renderURL var="purchaseAppEntryLicenseURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
			<portlet:param name="purchaseStep" value="license" />
			<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="<%= purchaseAppEntryLicenseURL %>" />
		<aui:input name="purchaseStep" type="hidden" value="end-user" />
		<aui:input name="ecDocumentEntryId" type="hidden" value="<%= ecDocumentEntryId %>" />
		<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

		<liferay-ui:error exception="<%= RequiredEndUserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
		<liferay-ui:error exception="<%= RequiredEndUserFirstNameException.class %>" message="please-enter-a-valid-first-name" />
		<liferay-ui:error exception="<%= RequiredEndUserLastNameException.class %>" message="please-enter-a-valid-last-name" />
		<liferay-ui:error exception="<%= RequiredEndUserPhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />

		<div class="aui-field-help">
			<liferay-ui:message key="enter-your-customers-technical-contact-details-here" />
		</div>

		<div class="end-user-contact-information">
			<h3>
				<liferay-ui:message key="end-user-contact-information" />
			</h3>

			<aui:input cssClass="required" helpMessage="if-your-customer-is-already-registered-on-liferay-com-enter-the-email-address-they-are-using-for-their-account" name="emailAddress" type="text" value="<%= ecDocumentEntryExtraSettings.getEndUserEmailAddress() %>" />

			<aui:input cssClass="required" name="firstName" type="text" value="<%= ecDocumentEntryExtraSettings.getEndUserFirstName() %>" />

			<aui:input cssClass="required" name="lastName" type="text" value="<%= ecDocumentEntryExtraSettings.getEndUserLastName() %>" />

			<aui:input cssClass="required" name="phoneNumber" type="text" value="<%= ecDocumentEntryExtraSettings.getEndUserPhone() %>" />

			<aui:input label="send-end-user-a-copy-of-the-receipt" name="emailReceipt" type="checkbox" value="<%= ecDocumentEntryExtraSettings.isEndUserEmailReceipt() %>" />
		</div>

		<aui:button-row>
			<portlet:renderURL var="viewAppEntryURL">
				<portlet:param name="<%= mvcPathParam %>" value="/marketplace/view_app_entry.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</portlet:renderURL>

			<portlet:actionURL name="cancelPurchaseAppEntry" var="cancelPurchaseAppEntryURL">
				<portlet:param name="redirect" value="<%= viewAppEntryURL %>" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
			</portlet:actionURL>

			<aui:button href="<%= cancelPurchaseAppEntryURL %>" value="cancel" />

			<portlet:renderURL var="purchaseAppEntryProjectURL">
				<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
				<portlet:param name="purchaseStep" value="project" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</portlet:renderURL>

			<aui:button href="<%= purchaseAppEntryProjectURL %>" value="back" />

			<aui:button type="submit" value="next" />
		</aui:button-row>
	</aui:form>
</div>