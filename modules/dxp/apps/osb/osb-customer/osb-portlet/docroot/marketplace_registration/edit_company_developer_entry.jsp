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

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(developerEntryId);

long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(corpEntryId);

boolean upgradeRegistration = false;

if ((corpEntry != null) && corpEntry.isApproved()) {
	upgradeRegistration = true;
}
else {
	corpEntry = CorpEntryLocalServiceUtil.createCorpEntry(0);
}

String companyName = ParamUtil.getString(request, "companyName");

long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
%>

<div class="marketplace-registration-wrapper">
	<c:if test="<%= corpEntryId > 0 %>">
		<div class="portlet-msg-info">
			<liferay-ui:message arguments="<%= corpEntry.getName() %>" key="x-is-already-a-liferay-service-partner-company" />
		</div>
	</c:if>

	<liferay-ui:header
		backURL="<%= backURL %>"
		title='<%= (developerEntry == null) ? "register-your-company" : "edit-your-company" %>'
	/>

	<p>
		<liferay-ui:message key="thank-you-for-your-interest-in-representing-your-company-on-the-liferay-marketplace" />
	</p>

	<h3>
		<liferay-ui:message key="step-2" />
	</h3>

	<p>
		<liferay-ui:message key="fill-out-your-companys-information" />
	</p>

	<portlet:actionURL name="updateCompanyDeveloperEntry" var="updateCompanyDeveloperEntryURL" />

	<aui:form action="<%= updateCompanyDeveloperEntryURL %>" cssClass="corp-add-form" enctype="multipart/form-data" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_registration/edit_company_developer_entry.jsp" />
		<aui:input name="developerEntryId" type="hidden" value="<%= developerEntryId %>" />
		<aui:input name="corpEntryId" type="hidden" value="<%= corpEntryId %>" />

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
		<liferay-ui:error exception="<%= NoSuchContractEntryException.class %>" message="please-accept-the-liferay-terms-of-service-and-liferay-marketplace-developer-agreement" />
		<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-enter-a-valid-country" />
		<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-valid-region" />
		<liferay-ui:error exception="<%= RestrictedCountryException.class %>" message="under-us-export-laws-the-exportation-sale-or-supply-directly-or-indirectly-of-app-or-services-to-the-selected-country-is-strictly-prohibited-without-prior-authorization-by-the-us-government" />

		<aui:fieldset>
			<aui:layout>
				<aui:column columnWidth="<%= 33 %>" first="<%= true %>">
					<aui:fieldset label="public-info">
						<c:if test="<%= (developerEntry != null) || upgradeRegistration %>">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveMedia" var="logoURL">
								<portlet:param name="assetAttachmentId" value='<%= String.valueOf(BeanParamUtil.getLong(developerEntry, request, "profileLogoId", corpEntry.getLogoId())) %>' />
							</liferay-portlet:resourceURL>

							<img src="<%= logoURL %>" />
						</c:if>

						<div class="cleared"></div>

						<liferay-ui:message key="logos-must-at-most-be-a-220-by-220-pixel-png-file" />

						<aui:input accept="image/*" cssClass="required" label="upload-a-logo" name="profileLogo" type="file" />

						<aui:input cssClass="required" label="full-company-legal-name" name="legalEntityName" value='<%= (Validator.isNull(companyName)) ? BeanParamUtil.getString(developerEntry, request, "name", corpEntry.getName()) : companyName %>' />

						<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
							<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "company-description") %>' />
							<liferay-util:param name="name" value="profileDescription" />
							<liferay-util:param name="required" value="<%= String.valueOf(true) %>" />
							<liferay-util:param name="value" value='<%= BeanParamUtil.getString(developerEntry, request, "profileDescription", corpEntry.getDescription()) %>' />
						</liferay-util:include>

						<aui:input label="profile-email" name="profileEmailAddress" value='<%= BeanParamUtil.getString(developerEntry, request, "profileEmailAddress", corpEntry.getProfileEmailAddress()) %>' />

						<aui:input label="homepage-url" name="profileWebsite" value='<%= BeanParamUtil.getString(developerEntry, request, "profileWebsite", corpEntry.getWebsite()) %>' />
					</aui:fieldset>
				</aui:column>

				<aui:column columnWidth="<%= 33 %>">
					<aui:fieldset label="private-info">
						<aui:input cssClass="required" label="headquarters-address" name="street1" type="text" value='<%= BeanParamUtil.getString(developerEntry, request, "street1", corpEntry.getStreet1()) %>' />

						<aui:input label="" name="street2" type="text" value='<%= BeanParamUtil.getString(developerEntry, request, "street2", corpEntry.getStreet2()) %>' />

						<aui:input label="" name="street3" type="text" value='<%= BeanParamUtil.getString(developerEntry, request, "street3", corpEntry.getStreet3()) %>' />

						<aui:select cssClass="required" label="country" name="countryId" />

						<aui:select cssClass="region" label="region" name="regionId" />

						<aui:input cssClass="required" name="city" type="text" value='<%= BeanParamUtil.getString(developerEntry, request, "city", corpEntry.getCity()) %>' />

						<aui:input cssClass="required" label="postal-code" name="zip" type="text" value='<%= BeanParamUtil.getString(developerEntry, request, "zip", corpEntry.getZip()) %>' />

						<aui:input cssClass="required" label="phone" name="phoneNumber" value='<%= BeanParamUtil.getString(developerEntry, request, "phoneNumber", corpEntry.getPhoneNumber()) %>' />

						<aui:input label="fax" name="faxNumber" value='<%= BeanParamUtil.getString(developerEntry, request, "faxNumber", corpEntry.getFaxNumber()) %>' />
					</aui:fieldset>
				</aui:column>

				<aui:column columnWidth="<%= 33 %>" last="<%= true %>">
					<aui:fieldset label="validation">
						<aui:input cssClass="required" label="company-email" name="emailAddress" value='<%= BeanParamUtil.getString(developerEntry, request, "emailAddress", corpEntry.getContactEmailAddress()) %>' />

						<p class="fs-11 light-gray">
							<liferay-ui:message key="a-verification-email-will-be-sent-to-the-email-address-above" />
						</p>

						<aui:input cssClass="required" label="legal-tax-document" name="taxDocument" type="file" />

						<aui:select name="taxDocumentType">
							<aui:option value="" />

							<%
							for (String developerTaxDocumentName : DeveloperEntryConstants.DEVELOPER_TAX_DOCUMENT_NAMES) {
							%>

								<aui:option label="<%= developerTaxDocumentName %>" value="<%= developerTaxDocumentName %>" />

							<%
							}
							%>

							<aui:option label="other" value="other" />
						</aui:select>

						<p>
							<liferay-ui:message key="please-attach-a-scanned-copy-of-one-of-the-following" />

							<ul>

								<%
								for (String developerTaxDocumentName : DeveloperEntryConstants.DEVELOPER_TAX_DOCUMENT_NAMES) {
								%>

									<li>
										<liferay-ui:message key="<%= developerTaxDocumentName %>" />
									</li>

								<%
								}
								%>

							</ul>
						</p>

						<c:if test="<%= developerEntry == null %>">

							<%
							ContractEntry tosContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TERMS_OF_SERVICE);

							PortletURL tosURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, marketplacePlid, PortletRequest.RENDER_PHASE);

							tosURL.setParameter("mvcPath", "/marketplace/view_contract_entry.jsp");
							tosURL.setParameter("contractEntryId", String.valueOf(tosContractEntry.getContractEntryId()));
							tosURL.setWindowState(LiferayWindowState.EXCLUSIVE);

							ContractEntry developerAgreementContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_DEVELOPER_AGREEMENT);

							PortletURL developerAgreementURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, marketplacePlid, PortletRequest.RENDER_PHASE);

							developerAgreementURL.setParameter("mvcPath", "/marketplace/view_contract_entry.jsp");
							developerAgreementURL.setParameter("contractEntryId", String.valueOf(developerAgreementContractEntry.getContractEntryId()));
							developerAgreementURL.setWindowState(LiferayWindowState.EXCLUSIVE);

							String taglibContractEntriesLabel = LanguageUtil.format(pageContext, "i-have-read-and-agree-to-the-liferay-terms-of-service-and-the-liferay-marketplace-developer-agreement", new Object[] {tosURL.toString(), developerAgreementURL.toString()});
							%>

							<p class="agreements">
								<aui:input cssClass="fs-11 required" label="<%= taglibContractEntriesLabel %>" name="acceptContractEntries" type="checkbox" />
							</p>
						</c:if>
					</aui:fieldset>
				</aui:column>
			</aui:layout>

			<aui:layout cssClass="rt-align">
				<aui:button-row>
					<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
						<portlet:param name="mvcPath" value="/marketplace/view.jsp" />
					</liferay-portlet:renderURL>

					<aui:button onClick="<%= marketplaceURL %>" value="cancel" />

					<aui:button type="submit" value="submit-request" />
				</aui:button-row>
			</aui:layout>
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
					selectVal: '<%= BeanParamUtil.getLong(developerEntry, request, "countryId", corpEntry.getCountryId()) %>'
				},
				{
					select: '<portlet:namespace />regionId',
					selectData: function(callback, selectKey) {
						Liferay.Address.getRegions(
							function(response) {
								callback(response);

								var label = A.one('label[for="<portlet:namespace />regionId"]');

								var container = label.ancestor('.aui-field-select');

								if (response.length > 1) {
									container.addClass('required');
								}
								else {
									container.removeClass('required');
								}
							},
							selectKey
						);
					},
					selectDesc: 'name',
					selectId: 'regionId',
					selectVal: '<%= BeanParamUtil.getLong(developerEntry, request, "regionId", corpEntry.getRegionId()) %>'
				}
			]
		);

		<c:if test="<%= !upgradeRegistration %>">
			A.one('#<portlet:namespace />logo').delegate(
				'change',
				function(event) {
					var container = event.currentTarget.ancestor();

					var setLogoImage = function(logoImageSrc) {
						var imageNode = container.one('img');

						if (!logoImageSrc && imageNode) {
							imageNode.remove();

							return;
						}

						if (!imageNode) {
							container.prepend('<img />');
						}

						container.one('img').set('src', logoImageSrc);
					};

					var inputNodeFile = event.target.get('files');

					if (inputNodeFile.getDOM().length == 0) {
						setLogoImage();

						return;
					}

					var fileReader = new FileReader();

					fileReader.readAsDataURL(inputNodeFile.getDOMNodes()[0]);

					fileReader.onload = function(event) {
						setLogoImage(event.target.result);
					}
				},
				'.aui-field-input'
			);
		</c:if>

		A.all('.osb-portlet-marketplace-registration .corp-add-form .agreements a').on(
			'click',
			function(event) {
				event.preventDefault();

				var node = event.currentTarget;

				var dialogWidth = 435;

				var dialogX = (A.getBody().width() - dialogWidth) / 2;

				var dialogY = 80;

				var dialogHeight = A.getBody().height() - (dialogY * 2);

				var dialog = new A.Dialog(
					{
						destroyOnClose: true,
						draggable: false,
						height: dialogHeight,
						modal: true,
						resizable: false,
						title: node.text(),
						width: dialogWidth,
						xy: [dialogX, dialogY]
					}
				).plug(
					A.Plugin.IO,
					{
						uri: node.attr('href')
					}
				).render();
			}
		);
	</aui:script>
</div>