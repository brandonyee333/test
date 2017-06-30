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

if (!ecDocumentEntry.isTaxProcessed()) {
	ecDocumentEntry = ECDocumentEntryLocalServiceUtil.updateECDocumentEntryTax(ecDocumentEntry.getEcDocumentEntryId());
}

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);
%>

<div class="purchase-app-entry-summary">
	<h3>
		<liferay-ui:message key="order-summary" />
	</h3>

	<div class="aui-field-help">
		<c:choose>
			<c:when test="<%= ecDocumentEntryExtraSettings.isResale() %>">
				<liferay-ui:message key="you-are-making-this-purchase-on-behalf-of-an-end-user" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="please-review-the-details-of-your-order-before-confirming-your-purchase" />
			</c:otherwise>
		</c:choose>
	</div>

	<div class="card-container">
		<div class="card project">
			<div class="card-heading">
				<liferay-ui:message key="project" />

				<span>
					<portlet:renderURL var="purchaseAppEntryProjectURL">
						<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
						<portlet:param name="purchaseStep" value="project" />
						<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
					</portlet:renderURL>

					<a href="<%= purchaseAppEntryProjectURL %>"><liferay-ui:message key="edit" /></a>
				</span>
			</div>

			<div class="card-body">

				<%
				String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();

				CorpProject corpProject = null;

				if (ownerClassName.equals(CorpProject.class.getName())) {
					corpProject = CorpProjectLocalServiceUtil.getCorpProject(ecDocumentEntryExtraSettings.getOwnerClassPK());
				}
				%>

				<div class="name">
					<c:choose>
						<c:when test="<%= corpProject == null %>">
							<liferay-ui:message key="for-personal-use-only" />
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(corpProject.getName()) %>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="owner">
					<c:choose>
						<c:when test="<%= corpProject == null %>">
							<%= HtmlUtil.escape(user.getFullName()) %>
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(PortalUtil.getUserName(corpProject)) %>
						</c:otherwise>
					</c:choose>
				</div>

				<liferay-util:include page="/marketplace/purchase_app_entry/project_licenses.jsp" servletContext="<%= application %>">
					<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
					<liferay-util:param name="ownerClassName" value="<%= ownerClassName %>" />
					<liferay-util:param name="ownerClassPK" value="<%= String.valueOf(ecDocumentEntryExtraSettings.getOwnerClassPK()) %>" />
				</liferay-util:include>
			</div>
		</div>

		<div class="card destination-address">
			<div class="card-heading">
				<liferay-ui:message key="destination-address" />

				<span>
					<portlet:renderURL var="purchaseAppEntryDestinationURL">
						<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
						<portlet:param name="purchaseStep" value="destination" />
						<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
					</portlet:renderURL>

					<a href="<%= purchaseAppEntryDestinationURL %>"><liferay-ui:message key="edit" /></a>
				</span>
			</div>

			<div class="card-body">

				<%
				String addressCompanyName = StringPool.BLANK;
				String vatNumber = StringPool.BLANK;
				String street1 = StringPool.BLANK;
				String street2 = StringPool.BLANK;
				String street3 = StringPool.BLANK;
				String city = StringPool.BLANK;
				String regionName = StringPool.BLANK;
				String zip = StringPool.BLANK;
				String countryName = StringPool.BLANK;

				if (ecDocumentEntryExtraSettings.isResale()) {
					addressCompanyName = ecDocumentEntryExtraSettings.getEndUserCompanyName();
					vatNumber = ecDocumentEntryExtraSettings.getEndUserVatNumber();
					street1 = ecDocumentEntry.getShippingAddressStreet1();
					street2 = ecDocumentEntry.getShippingAddressStreet2();
					street3 = ecDocumentEntry.getShippingAddressStreet3();
					city = ecDocumentEntry.getShippingAddressCity();

					Region region = RegionServiceUtil.getRegion(ecDocumentEntry.getShippingAddressRegionId());

					regionName = region.getName();

					zip = ecDocumentEntry.getShippingAddressZip();

					Country country = CountryServiceUtil.getCountry(ecDocumentEntry.getShippingAddressCountryId());

					countryName = country.getName();
				}
				else {
					Address address = AddressLocalServiceUtil.getAddress(ecDocumentEntryExtraSettings.getAddressId());

					Country country = address.getCountry();
					Region region = address.getRegion();

					ExpandoBridge addressExpandoBridge = address.getExpandoBridge();

					addressCompanyName = GetterUtil.getString(addressExpandoBridge.getAttribute("osbCompanyName", false));
					vatNumber = GetterUtil.getString(addressExpandoBridge.getAttribute("vatNumber", false));

					address = address.toEscapedModel();

					street1 = address.getStreet1();
					street2 = address.getStreet2();
					street3 = address.getStreet3();
					city = address.getCity();
					regionName = region.getName();
					zip = address.getZip();
					countryName = country.getName();
				}
				%>

				<div>
					<c:if test="<%= Validator.isNotNull(addressCompanyName) %>">
						<div class="name">
							<%= HtmlUtil.escape(addressCompanyName) %>
						</div>
					</c:if>

					<c:if test="<%= Validator.isNotNull(vatNumber) %>">
						<div class="vat-number">
							<liferay-ui:message arguments="<%= HtmlUtil.escape(vatNumber) %>" key="vat-x" />
						</div>
					</c:if>

					<div class="address-content">
						<div>
							<%= street1 %>
						</div>

						<div>
							<%= street2 %>
						</div>

						<div>
							<%= street3 %>
						</div>

						<div>
							<%= city %>
						</div>

						<div>
							<%= regionName %>
						</div>

						<div>
							<%= zip %>
						</div>

						<div>
							<%= countryName %>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="cleared"></div>
	</div>

	<%@ include file="/marketplace/purchase_app_entry/cart.jspf" %>

	<div class="payment-options">
		<portlet:actionURL name="purchaseAppEntrySummary" var="purchaseAppEntrySummaryURL" />

		<aui:form action="<%= purchaseAppEntrySummaryURL %>" method="post" name="fm2">
			<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace/purchase_app_entry.jsp" />
			<aui:input name="purchaseStep" type="hidden" value="summary" />
			<aui:input name="ecDocumentEntryId" type="hidden" value="<%= ecDocumentEntryId %>" />
			<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

			<liferay-ui:error exception="<%= AssetReceiptEmailAddressException.class %>" message="please-enter-a-valid-email-address" />

			<%
			String paymentMethod = ParamUtil.getString(request, "paymentMethod", "paypal");
			%>

			<aui:input checked='<%= paymentMethod.equals("paypal") %>' cssClass="payment-method-radio payment-method-paypal" label="pay-now" name="paymentMethod" type="radio" value="paypal" />

			<img class="payment-method-paypal-icon" src="<%= PortalUtil.getPathContext(request) %>/images/payment_method_paypal.png" />

			<aui:input checked='<%= paymentMethod.equals("invoice-me") %>' cssClass="payment-method-radio payment-method-invoice" label="invoice-me" name="paymentMethod" type="radio" value="request-invoice" />

			<aui:input cssClass="disabled" disabled='<%= !paymentMethod.equals("request-invoice") %>' name="emailAddress" type="text" />

			<%@ include file="/marketplace/purchase_app_entry/contract_entries.jspf" %>

			<aui:button type="submit" value="submit" />
		</aui:form>
	</div>

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

		<portlet:renderURL var="purchaseAppEntryDestinationURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
			<portlet:param name="purchaseStep" value="destination" />
			<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</portlet:renderURL>

		<aui:button href="<%= purchaseAppEntryDestinationURL %>" value="back" />
	</aui:button-row>
</div>

<aui:script use="aui-base,aui-tooltip">
	var tooltipPayPal = new A.Tooltip(
		{
			bodyContent: '<liferay-ui:message key="your-credit-debit-card-payment-will-be-processed-via-the-paypal-network" unicode="<%= true %>" />',
			trigger: '.payment-method-paypal'
		}
	).render();

	var tooltipInvoice = new A.Tooltip(
		{

			<%
			String accountsEmailLink = "<a href=\"mailto:accounts@liferay.com\">accounts@liferay.com</a>";
			%>

			bodyContent: '<liferay-ui:message arguments="<%= accountsEmailLink %>" key="your-purchased-app-will-be-available-for-download-once-payment-is-received" unicode="<%= true %>" />',
			trigger: ".payment-method-invoice"
		}
	).render();

	A.all('.purchase-app-entry-summary .payment-method-radio input[type=radio]').on(
		'change',
		function(event) {
			var targetRadio = event.currentTarget;

			var emailAddress = A.one('#<portlet:namespace />emailAddress');

			if (targetRadio.val() == 'request-invoice') {
				emailAddress.attr('disabled', false);
				emailAddress.removeClass('disabled');
			}
			else {
				emailAddress.attr('disabled', true);
				emailAddress.addClass('disabled');
			}
		}
	);
</aui:script>