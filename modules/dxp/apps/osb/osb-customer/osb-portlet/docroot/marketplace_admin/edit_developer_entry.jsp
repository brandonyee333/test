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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long developerEntryId = ParamUtil.getLong(request, "developerEntryId");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);
%>

<div class="edit-developer-entry">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="edit-developer-entry"
	/>

	<h3>
		<%= HtmlUtil.escape(developerEntry.getName()) %>
	</h3>

	<c:if test="<%= developerEntry.isPending() && developerEntry.isCompany() %>">
		<portlet:actionURL name="updateCompanyDeveloperEntry" var="updateCompanyDeveloperEntryURL" />

		<aui:form action="<%= updateCompanyDeveloperEntryURL %>" enctype="multipart/form-data" method="post" name="fm1">
			<aui:input name="mvcPath" type="hidden" value="/marketplace_admin/edit_developer_entry.jsp" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="developerEntryId" type="hidden" value="<%= developerEntryId %>" />

			<aui:model-context bean="<%= developerEntry %>" model="<%= DeveloperEntry.class %>" />

			<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
			<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
			<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
			<liferay-ui:error exception="<%= DeveloperEntryEmailAddressException.class %>" message="please-enter-a-valid-company-email-address" />
			<liferay-ui:error exception="<%= DeveloperEntryFaxNumberException.class %>" message="please-enter-a-valid-fax-number" />
			<liferay-ui:error exception="<%= DeveloperEntryNameException.class %>" message="please-enter-a-valid-company-name" />
			<liferay-ui:error exception="<%= DeveloperEntryPhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />
			<liferay-ui:error exception="<%= DeveloperEntryProfileDescriptionException.class %>" message="please-enter-a-description-of-your-company" />
			<liferay-ui:error exception="<%= DeveloperEntryProfileEmailAddressException.class %>" message="please-enter-a-valid-profile-email-address" />

			<liferay-ui:error exception="<%= DeveloperEntryProfileLogoException.class %>">

				<%
				DeveloperEntryProfileLogoException deple = (DeveloperEntryProfileLogoException)errorException;
				%>

				<c:if test="<%= deple.getType() == DeveloperEntryProfileLogoException.TYPE_LOGO_EXTENSION %>">
					<liferay-ui:message key="logos-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(PortletPropsValues.DEVELOPER_ENTRY_LOGO_EXTENSIONS, StringPool.COMMA_AND_SPACE) %>.
				</c:if>

				<c:if test="<%= deple.getType() == DeveloperEntryProfileLogoException.TYPE_LOGO_INVALID %>">
					<liferay-ui:message key="please-upload-a-logo" />
				</c:if>
			</liferay-ui:error>

			<liferay-ui:error exception="<%= DeveloperEntryProfileWebsiteException.class %>" message="please-enter-a-valid-homepage-url" />
			<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentException.class %>" message="please-upload-a-tax-document" />
			<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentFileNameException.class %>" message="please-select-a-tax-document-type" />
			<liferay-ui:error exception="<%= DuplicateDeveloperEntryException.class %>" message="your-company-is-already-registered" />
			<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
			<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-enter-a-valid-country" />
			<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-valid-region" />
			<liferay-ui:error exception="<%= RestrictedCountryException.class %>" message="under-us-export-laws-the-exportation-sale-or-supply-directly-or-indirectly-of-app-or-services-to-the-selected-country-is-strictly-prohibited-without-prior-authorization-by-the-us-government" />
			<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />

			<aui:fieldset label="developer-registration">
				<span class="field">
					<span class="field-content">
						<label class="field-label">
							<liferay-ui:message key="logo" />
						</label>

						<span class="field-element">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveMedia" var="logoURL">
								<portlet:param name="assetAttachmentId" value="<%= String.valueOf(developerEntry.getProfileLogoId()) %>" />
							</liferay-portlet:resourceURL>

							<img src="<%= logoURL %>" />
						</span>
					</span>
				</span>

				<liferay-ui:message key="logos-must-at-most-be-a-220-by-220-pixel-png-file" />

				<aui:input accept="image/*" label="upload-a-logo" name="profileLogo" type="file" />

				<aui:input name="legalEntityName" />

				<aui:input label="description" name="profileDescription" />

				<aui:input label="profile-email" name="profileEmailAddress" />

				<aui:input label="contact-email" name="emailAddress" />

				<aui:input label="homepage-url" name="profileWebsite" />

				<%
				Address address = developerEntry.getAddress();
				%>

				<aui:input label="address1" name="street1" type="text" value="<%= address.getStreet1() %>" />

				<aui:input label="address2" name="street2" type="text" value="<%= address.getStreet2() %>" />

				<aui:input label="address3" name="street3" type="text" value="<%= address.getStreet3() %>" />

				<aui:select label="country" name="countryId" />

				<aui:select label="region" name="regionId" />

				<aui:input label="city" name="city" type="text" value="<%= address.getCity() %>" />

				<aui:input label="zip" name="zip" type="text" value="<%= address.getZip() %>" />

				<aui:input label="phone-number" name="phoneNumber" />

				<aui:input label="fax" name="faxNumber" />

				<aui:fieldset label="tax-documents">
					<aui:input label="upload-a-new-tax-document" name="taxDocument" type="file" />

					<aui:select name="taxDocumentType">

						<%
						for (String developerTaxDocumentName : DeveloperEntryConstants.DEVELOPER_TAX_DOCUMENT_NAMES) {
						%>

							<aui:option label="<%= developerTaxDocumentName %>" value="<%= developerTaxDocumentName %>" />

						<%
						}
						%>

						<aui:option label="other" value="other" />
					</aui:select>
				</aui:fieldset>

				<aui:button-row>
					<aui:button type="submit" value="save" />

					<aui:button href="<%= redirect %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<aui:script use="aui-dialog,aui-io,liferay-dynamic-select">
			new Liferay.DynamicSelect(
				[
					{
						select: '<portlet:namespace />countryId',
						selectData: Liferay.Address.getCountries,
						selectDesc: 'name',
						selectId: 'countryId',
						selectVal: '<%= BeanParamUtil.getLong(developerEntry, request, "countryId") %>'
					},
					{
						select: '<portlet:namespace />regionId',
						selectData: Liferay.Address.getRegions,
						selectDesc: 'name',
						selectId: 'regionId',
						selectVal: '<%= BeanParamUtil.getLong(developerEntry, request, "regionId") %>'
					}
				]
			);
		</aui:script>
	</c:if>

	<portlet:actionURL name="updateDeveloperEntry" var="updateDeveloperEntryURL" />

	<aui:form action="<%= updateDeveloperEntryURL %>" enctype="multipart/form-data" method="post" name="fm2">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_admin/edit_developer_entry.jsp" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="developerEntryId" type="hidden" value="<%= developerEntryId %>" />

		<aui:model-context bean="<%= developerEntry %>" model="<%= DeveloperEntry.class %>" />

		<liferay-ui:error exception="<%= DeveloperEntryDomainNameException.class %>" message="please-enter-a-valid-domain-name" />
		<liferay-ui:error exception="<%= DeveloperEntryDomainStatusException.class %>" message="the-domain-name-entered-is-already-registered-to-another-company" />
		<liferay-ui:error exception="<%= DeveloperEntryFatcaWithholdingPercentageException.class %>" message="please-enter-a-valid-fatca-withholding-percentage" />
		<liferay-ui:error exception="<%= DeveloperEntryNameException.class %>" message="unable-to-activate-developer-subscription" />
		<liferay-ui:error exception="<%= DeveloperEntryPaymentAccountException.class %>" message="a-paypal-account-could-not-be-found-with-the-name-and-email-address-you-provided" />
		<liferay-ui:error exception="<%= DeveloperEntryPaymentEmailAddressException.class %>" message="please-enter-the-email-address-associated-with-your-paypal-account" />
		<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentException.class %>" message="please-upload-a-tax-document" />
		<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentFileNameException.class %>" message="please-select-a-tax-document-type" />

		<aui:fieldset label="domain-name">
			<aui:input helpMessage="domain-name-should-not-start-with-http-or-https" name="domainName" />

			<aui:select name="domainStatus">

				<%
				for (int status : _DOMAIN_STATUSES) {
				%>

					<aui:option label="<%= WorkflowConstants.toLabel(status) %>" value="<%= status %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<aui:fieldset label="paypal">
			<aui:input label="paypal-first-name" name="paymentFirstName" type="text" />

			<aui:input label="paypal-last-name" name="paymentLastName" type="text" />

			<aui:input label="paypal-email" name="paymentEmailAddress" />
		</aui:fieldset>

		<aui:fieldset label="subscription">
			<aui:select name="subscriptionStatus">

				<%
				for (int status : _SUBSCRIPTION_STATUSES) {
				%>

					<aui:option label="<%= WorkflowConstants.toLabel(status) %>" value="<%= status %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input name="subscriptionExpirationDate" />
		</aui:fieldset>

		<c:if test="<%= developerEntry.isCompany() || (developerEntry.getSubscriptionExpirationDate() != null) %>">
			<aui:fieldset label="tax-documents">
				<aui:input label="upload-a-new-tax-document" name="taxDocument" type="file" />

				<aui:select name="taxDocumentType">

					<%
					for (String developerTaxDocumentName : DeveloperEntryConstants.DEVELOPER_TAX_DOCUMENT_NAMES) {
					%>

						<aui:option label="<%= developerTaxDocumentName %>" value="<%= developerTaxDocumentName %>" />

					<%
					}
					%>

					<aui:option label="other" value="other" />
				</aui:select>
			</aui:fieldset>
		</c:if>

		<aui:fieldset label="tax-settings">

			<%
			boolean useDefault = developerEntry.getFatcaWithholdingPercentage() == DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT;
			%>

			<aui:input checked="<%= useDefault %>" label="use-default-fatca-withhholding-percentage" name="useDefaultFatca" type="checkbox" />

			<aui:input disabled="<%= useDefault %>" name="fatcaWithholdingPercentage" />
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" value="save" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />useDefaultFatcaCheckbox').on(
		'change',
		function(event) {
			var checkbox = event.currentTarget;

			var fatcaWithholdingPercentage = A.one('#<portlet:namespace />fatcaWithholdingPercentage');

			if (checkbox.attr('checked')) {
				fatcaWithholdingPercentage.attr('disabled', 'disabled');
			}
			else {
				fatcaWithholdingPercentage.removeAttribute('disabled');
			}
		}
	);
</aui:script>

<%!
private static final int[] _DOMAIN_STATUSES = {WorkflowConstants.STATUS_PENDING_VALIDATION, WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_EXPIRED};

private static final int[] _SUBSCRIPTION_STATUSES = {WorkflowConstants.STATUS_DRAFT, WorkflowConstants.STATUS_APPROVED};
%>