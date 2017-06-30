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

Calendar calendar = CalendarFactoryUtil.getCalendar();

calendar.setTime(new Date());

boolean promotion = false;

if (OSBConstants.MARKETPLACE_SUBSCRIPTION_PROMOTION_YEAR >= calendar.get(Calendar.YEAR)) {
	promotion = true;
}
%>

<div class="upgrade-developer-entry-pay">
	<div class="upgrade-step-header pay">
		<c:if test="<%= developerEntry.getSubscriptionExpirationDate() == null %>">
			<span class="circle">3</span>
		</c:if>

		<span>
			<c:choose>
				<c:when test="<%= promotion %>">
					<del><liferay-ui:message key="pay-the-99-annual-fee" /></del>

					<em><liferay-ui:message key="launch-promotion,-fee-waved-for-a-limited-time" /></em>
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="pay-the-99-annual-fee" />
				</c:otherwise>
			</c:choose>
		</span>
	</div>

	<c:if test="<%= promotion %>">
		<p>
			<liferay-ui:message key="after-the-promotional-period-you-will-be-required-to-pay-the-99-annual-fee-to-submit-new-paid-apps-or-update-existing-paid-apps" />
		</p>
	</c:if>

	<portlet:actionURL name="updateDeveloperEntryPay" var="updateDeveloperEntryPayURL" />

	<aui:form action="<%= updateDeveloperEntryPayURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_registration/upgrade_developer_entry.jsp" />
		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="upgradeStep" type="hidden" value="pay" />
		<aui:input name="developerEntryId" type="hidden" value="<%= developerEntryId %>" />

		<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
		<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
		<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
		<liferay-ui:error exception="<%= DeveloperEntryNameException.class %>" message="please-enter-your-first-and-last-name" />
		<liferay-ui:error exception="<%= DeveloperEntryPaymentAccountException.class %>" message="a-paypal-account-could-not-be-found-with-the-name-and-email-address-you-provided" />
		<liferay-ui:error exception="<%= DeveloperEntryPaymentEmailAddressException.class %>" message="please-enter-the-email-address-associated-with-your-paypal-account" />
		<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentException.class %>" message="please-upload-a-tax-document" />
		<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-valid-country" />
		<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-valid-region" />
		<liferay-ui:error exception="<%= PhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />

		<div class="cart">
			<div class="cart-title">
				<img src="<%= PortalUtil.getPathContext(request) %>/images/cart.png" />

				<liferay-ui:message key="items-in-cart" />
			</div>

			<table>
				<thead>
					<tr>
						<th class="col-1">
							<liferay-ui:message key="quantity" />
						</th>
						<th class="col-2">
							<liferay-ui:message key="item" />
						</th>
						<th class="col-3">
							<liferay-ui:message key="unit-price" />
						</th>
						<th class="col-4">
							<liferay-ui:message key="line-price" />
						</th>
					</tr>
				</thead>

				<%
				ECProductEntry ecProductEntry = ECProductEntryLocalServiceUtil.getECProductEntry(ECommerceConstants.EC_PRODUCT_ENTRY_ID_MARKETPLACE_SUBSCRIPTION);
				%>

				<tbody>
					<tr>
						<td class="col-1">
							1
						</td>
						<td class="col-2">
							<liferay-ui:message key="paid-app-developer-account-subscription-1-year" />
						</td>

						<c:choose>
							<c:when test="<%= promotion %>">
								<td class="col-3">
									<liferay-ui:message key="free" />
								</td>
								<td class="col-4">
									<liferay-ui:message key="free" />
								</td>
							</c:when>
							<c:otherwise>
								<td class="col-3">
									<%= CurrencyUtil.format(locale, ecProductEntry.getPrice()) %>
								</td>
								<td class="col-4">
									<%= CurrencyUtil.format(locale, ecProductEntry.getPrice()) %>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr class="subtotal">
						<td class="col-1">
						</td>
						<td class="col-2">
						</td>
						<td class="col-3">
							<liferay-ui:message key="subtotal" />
						</td>
						<td class="col-4">
							<c:choose>
								<c:when test="<%= promotion %>">
									<liferay-ui:message key="free" />
								</c:when>
								<c:otherwise>
									<%= CurrencyUtil.format(locale, ecProductEntry.getPrice()) %>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr class="estimated-tax">
						<td class="col-1">
						</td>
						<td class="col-2">
						</td>
						<td class="col-3">
							<liferay-ui:message key="estimated-tax" />
						</td>
						<td class="col-4">
							<c:choose>
								<c:when test="<%= promotion %>">
									<liferay-ui:message key="free" />
								</c:when>
								<c:otherwise>
									<%= CurrencyUtil.format(locale, ecProductEntry.getPrice()) %>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr class="total">
						<td class="col-1">
						</td>
						<td class="col-2">
						</td>
						<td class="col-3">
							<liferay-ui:message key="total" />
						</td>
						<td class="col-4">
							<c:choose>
								<c:when test="<%= promotion %>">
									<liferay-ui:message key="free" />
								</c:when>
								<c:otherwise>
									<%= CurrencyUtil.format(locale, ecProductEntry.getPrice()) %>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<aui:layout cssClass="rt-align">
			<aui:button-row>
				<aui:button onClick="<%= backURL %>" value="cancel" />

				<portlet:renderURL var="upgradeDeveloperEntryEmailURL">
					<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
					<portlet:param name="backURL" value="<%= backURL %>" />
					<portlet:param name="upgradeStep" value="email" />
					<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntryId) %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= upgradeDeveloperEntryEmailURL %>" value="back" />

				<aui:button type="submit" value="next" />
			</aui:button-row>
		</aui:layout>
	</aui:form>
</div>