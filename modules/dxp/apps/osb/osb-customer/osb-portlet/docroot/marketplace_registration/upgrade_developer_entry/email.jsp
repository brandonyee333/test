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
String backURL = ParamUtil.getString(request, "backURL", redirect);

long developerEntryId = ParamUtil.getLong(request, "developerEntryId");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);
%>

<div class="upgrade-developer-entry-email">
	<div class="upgrade-step-header email">
		<span class="circle">2</span>

		<span><liferay-ui:message key="confirm-or-create-a-paypal-account-to-receive-payments" /></span>
	</div>

	<div class="aui-field-help">
		<liferay-ui:message key="enter-your-paypal-information" />
	</div>

	<portlet:actionURL name="updateDeveloperEntryEmail" var="updateDeveloperEntryEmailURL" />

	<aui:form action="<%= updateDeveloperEntryEmailURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_registration/upgrade_developer_entry.jsp" />

		<portlet:renderURL var="updateDeveloperEntryPayURL">
			<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
			<portlet:param name="backURL" value="<%= backURL %>" />
			<portlet:param name="upgradeStep" value="pay" />
			<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntryId) %>" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="<%= updateDeveloperEntryPayURL %>" />

		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="upgradeStep" type="hidden" value="email" />
		<aui:input name="developerEntryId" type="hidden" value="<%= developerEntryId %>" />

		<liferay-ui:error exception="<%= DeveloperEntryNameException.class %>" message="please-enter-your-first-and-last-name" />
		<liferay-ui:error exception="<%= DeveloperEntryPaymentAccountException.class %>" message="a-paypal-account-could-not-be-found-with-the-name-and-email-address-you-provided" />
		<liferay-ui:error exception="<%= DeveloperEntryPaymentEmailAddressException.class %>" message="please-enter-the-email-address-associated-with-your-paypal-account" />

		<aui:model-context bean="<%= developerEntry %>" model="<%= DeveloperEntry.class %>" />

		<aui:input label="first-name" name="paymentFirstName" type="text" />

		<aui:input label="last-name" name="paymentLastName" type="text" />

		<aui:input label="email-address" name="paymentEmailAddress" type="text" />

		<aui:layout cssClass="button-row">
			<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
				<liferay-ui:message key="dont-have-a-paypal-account" /><br />

				<a href="http:///www.paypal.com" target="_blank"><liferay-ui:message key="create-one" /></a>
			</aui:column>

			<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
				<aui:button-row cssClass="rt-align">
					<aui:button onClick="<%= backURL %>" value="cancel" />

					<portlet:renderURL var="upgradeDeveloperEntryDetailsURL">
						<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
						<portlet:param name="backURL" value="<%= backURL %>" />
						<portlet:param name="upgradeStep" value="details" />
						<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntryId) %>" />
					</portlet:renderURL>

					<aui:button onClick="<%= upgradeDeveloperEntryDetailsURL %>" value="back" />

					<aui:button type="submit" value="next" />
				</aui:button-row>
			</aui:column>
		</aui:layout>
	</aui:form>
</div>