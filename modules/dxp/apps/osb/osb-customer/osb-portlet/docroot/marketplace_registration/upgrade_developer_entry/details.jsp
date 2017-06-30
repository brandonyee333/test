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
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long developerEntryId = ParamUtil.getLong(request, "developerEntryId");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);
%>

<div class="upgrade-developer-entry-details">
	<div class="upgrade-step-header details">
		<span class="circle">1</span>

		<span><liferay-ui:message key="upload-the-required-tax-form-and-confirm-contact-information-to-receive-payments" /></span>
	</div>

	<c:choose>
		<c:when test="<%= developerEntry.isCompany() %>">
			<p>
				<liferay-ui:message key="corporate-developers-need-to-include-the-following-form" />

				<ul>
					<li>

						<%
						String[] arguments = new String[] {"<a href=\"http:///www.irs.gov/pub/irs-pdf/fw9.pdf\" target=\"_blank\">", "</a>"};
						%>

						<liferay-ui:message arguments="<%= arguments %>" key="for-those-in-the-us-no-additional-forms-are-necessary" />
					</li>
					<li>

						<%
						arguments = new String[] {"<a href=\"http:///www.irs.gov/pub/irs-pdf/fw8bene.pdf\" target=\"_blank\">", "</a>"};
						%>

						<liferay-ui:message arguments="<%= arguments %>" key="for-those-outside-the-us-please-use-this-w-8-ben-e-form" />

						<liferay-ui:icon-help message='<%= LanguageUtil.format(pageContext, "for-more-information-regarding-w-8ben-e-please-visit", "https://www.irs.gov/uac/about-form-w-8ben-e") %>' />
					</li>
				</ul>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<liferay-ui:message key="individual-developers-need-to-include-the-following-form" />

				<ul>
					<li>

						<%
						String[] arguments = new String[] {"<a href=\"http:///www.irs.gov/pub/irs-pdf/fw9.pdf\" target=\"_blank\">", "</a>"};
						%>

						<liferay-ui:message arguments="<%= arguments %>" key="for-those-in-the-us-please-submit-the-w-9-form" />

						<liferay-ui:icon-help message='<%= LanguageUtil.format(pageContext, "for-more-information-regarding-w-9s-please-visit", "http://www.irs.gov/uac/Form-W-9,-Request-for-Taxpayer-Identification-Number-and-Certification" ) %>'/>
					</li>
					<li>

						<%
						arguments = new String[] {"<a href=\"http:///www.irs.gov/pub/irs-pdf/fw8ben.pdf\" target=\"_blank\">", "</a>"};
						%>

						<liferay-ui:message arguments="<%= arguments %>" key="for-those-outside-the-us-please-use-this-w-8-ben-form" />

						<liferay-ui:icon-help message='<%= LanguageUtil.format(pageContext, "for-more-information-regarding-w-8bens-please-visit", "http://www.irs.gov/uac/Form-W-8BEN,-Certificate-of-Foreign-Status-of-Beneficial-Owner-for-United-States-Tax-Withholding") %>' />
					</li>
				</ul>
			</p>
		</c:otherwise>
	</c:choose>

	<portlet:actionURL name="updateDeveloperEntryDetails" var="updateDeveloperEntryDetailsURL" />

	<aui:form action="<%= updateDeveloperEntryDetailsURL %>" enctype="multipart/form-data" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_registration/upgrade_developer_entry.jsp" />

		<portlet:renderURL var="updateDeveloperEntryEmailURL">
			<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
			<portlet:param name="backURL" value="<%= backURL %>" />
			<portlet:param name="upgradeStep" value="email" />
			<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntryId) %>" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="<%= updateDeveloperEntryEmailURL %>" />

		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="upgradeStep" type="hidden" value="details" />
		<aui:input name="developerEntryId" type="hidden" value="<%= developerEntryId %>" />

		<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
		<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
		<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
		<liferay-ui:error exception="<%= DeveloperEntryNameException.class %>" message="please-enter-your-first-and-last-name" />
		<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentException.class %>" message="please-upload-a-tax-document" />
		<liferay-ui:error exception="<%= DeveloperEntryTaxDocumentFileNameException.class %>" message="please-select-a-tax-document-type" />
		<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-valid-country" />

		<liferay-ui:error exception="<%= NoSuchECCountryException.class %>">

			<%
			String taglibDeveloperFAQURL = "<a href=\"" + themeDisplay.getPortalURL() + "/web/developer/marketplace/faq\" target=\"_blank\">";
			%>

			<liferay-ui:message arguments='<%= new String[] {taglibDeveloperFAQURL, "</a>"} %>' key="marketplace-does-not-support-your-country-for-paid-apps" />
		</liferay-ui:error>

		<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-valid-region" />
		<liferay-ui:error exception="<%= PhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />
		<liferay-ui:error exception="<%= RestrictedCountryException.class %>" message="under-us-export-laws-the-exportation-sale-or-supply-directly-or-indirectly-of-app-or-services-to-the-selected-country-is-strictly-prohibited-without-prior-authorization-by-the-us-government" />

		<aui:model-context bean="<%= developerEntry %>" model="<%= DeveloperEntry.class %>" />

		<aui:input label="upload-tax-form" name="taxDocumentFile" type="file" />

		<aui:select name="taxDocumentType">
			<aui:option value="" />

			<%
			for (String paidAppDeveloperTaxDocumentName : DeveloperEntryConstants.PAID_APP_DEVELOPER_TAX_DOCUMENT_NAMES) {
			%>

				<aui:option label="<%= paidAppDeveloperTaxDocumentName %>" value="<%= paidAppDeveloperTaxDocumentName %>" />

			<%
			}
			%>

			<aui:option label="other" value="other" />
		</aui:select>

		<p>
			<liferay-ui:message key="please-confirm-or-fill-in-the-information-below" />
		</p>

		<aui:layout>
			<aui:column columnWidth="<%= 33 %>" first="<%= true %>">
				<aui:input name="firstName" />

				<aui:input name="middleName" />

				<aui:input name="lastName" />

				<aui:input label="company" name="legalEntityName" />

				<aui:input label="phone" name="phoneNumber" />
			</aui:column>

			<aui:column columnWidth="<%= 33 %>">
				<aui:input label="address1" name="street1" type="text" />

				<aui:input label="address2" name="street2" type="text" />

				<aui:input label="address3" name="street3" type="text" />
			</aui:column>

			<aui:column columnWidth="<%= 33 %>" last="<%= true %>">
				<aui:select label="country" name="countryId" />

				<aui:select label="region" name="regionId" />

				<aui:input name="city" type="text" />

				<aui:input label="postal-code" name="zip" type="text" />
			</aui:column>
		</aui:layout>

		<aui:layout cssClass="rt-align">
			<aui:button-row>
				<aui:button onClick="<%= backURL %>" value="cancel" />

				<portlet:renderURL var="upgradeDeveloperEntryPayURL">
					<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
					<portlet:param name="backURL" value="<%= backURL %>" />
					<portlet:param name="upgradeStep" value="overview" />
					<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntryId) %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= upgradeDeveloperEntryPayURL %>" value="back" />

				<aui:button type="submit" value="next" />
			</aui:button-row>
		</aui:layout>
	</aui:form>
</div>

<aui:script use="aui-dialog,aui-io,liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />countryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'name',
				selectId: 'countryId',
				selectVal: '<%= developerEntry.getCountryId() %>'
			},
			{
				select: '<portlet:namespace />regionId',
				selectData: Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '<%= developerEntry.getRegionId() %>'
			}
		]
	);
</aui:script>