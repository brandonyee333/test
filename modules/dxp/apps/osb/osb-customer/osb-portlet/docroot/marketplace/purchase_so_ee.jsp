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
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

ContractEntry eulaContractEntry = appVersion.getContractEntry();
ContractEntry tosContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TERMS_OF_SERVICE);

String ownerClassName = ParamUtil.getString(request, "ownerClassName");
int ownerClassNameRadio = ParamUtil.getInteger(request, "ownerClassNameRadio");

String country = ParamUtil.getString(request, "country");
boolean trial = ParamUtil.getBoolean(request, "trial");
%>

<portlet:actionURL name="purchaseAppEntryProject" var="purchaseAppEntryProjectURL" />

<aui:form action="<%= purchaseAppEntryProjectURL %>" method="post" name="fm">
	<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace/purchase_app_entry.jsp" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

	<liferay-ui:error exception="<%= AssetReceiptOwnerClassNameException.class %>" message="please-select-who-will-purchase-this-app" />
	<liferay-ui:error exception="<%= NoSuchContractEntryException.class %>" message="please-accept-the-end-user-license-agreement-and-terms-of-service" />
	<liferay-ui:error exception="<%= TrialLicenseException.class %>" message="trial-licenses-are-unavailable-for-this-account-because-you-may-have-previously-request-one-or-you-may-have-already-purchased-a-valid-license-for-this-app" />

	<liferay-ui:error exception="<%= SalesforceAddressException.class %>">

		<%
		SalesforceAddressException sae = (SalesforceAddressException)errorException;
		%>

		<c:if test="<%= sae.getType() == SalesforceAddressException.ADDRESS_COUNTRY %>">
			<liferay-ui:message key="please-enter-a-valid-country" />
		</c:if>

		<c:if test="<%= sae.getType() == SalesforceAddressException.ADDRESS_CITY %>">
			<liferay-ui:message key="please-enter-a-valid-city" />
		</c:if>

		<c:if test="<%= sae.getType() == SalesforceAddressException.ADDRESS_STREET %>">
			<liferay-ui:message key="please-enter-a-valid-address" />
		</c:if>

		<c:if test="<%= sae.getType() == SalesforceAddressException.ADDRESS_ZIP %>">
			<liferay-ui:message key="please-enter-a-valid-postal-code" />
		</c:if>

		<c:if test="<%= sae.getType() == SalesforceAddressException.EMAIL_ADDRESS %>">
			<liferay-ui:message key="please-enter-an-email-address" />
		</c:if>

		<c:if test="<%= sae.getType() == SalesforceAddressException.LICENSE_TYPE %>">
			<liferay-ui:message key="please-select-a-license" />
		</c:if>

		<c:if test="<%= sae.getType() == SalesforceAddressException.USER_NAME %>">
			<liferay-ui:message key="please-enter-your-first-and-last-name" />
		</c:if>
	</liferay-ui:error>

	<div class="aui-helper-hidden portlet-msg-error"></div>

	<c:choose>
		<c:when test="<%= trial && !OSBAppEntryPermission.contains(permissionChecker, appEntry, OSBActionKeys.PURCHASE_APP) %>">
			<div class="cleared">
				<p>
					<liferay-ui:message arguments='<%= "mailto:sales@liferay.com" %>' key="thank-you-for-your-interest-in-social-office-enterprise-edition" />
				</p>

				<p>
					<liferay-ui:message key="to-receive-a-free-30-day-trial,-please-let-the-sales-representative-know" />
				<p>
			</div>
		</c:when>
		<c:when test="<%= trial %>">
			<aui:fieldset cssClass="purchase-type" label="purchase-type">
				<aui:input name="ownerClassName" type="hidden" value="<%= ownerClassName %>" />
				<aui:input name="ownerClassPK" type="hidden" value="<%= themeDisplay.getUserId() %>" />
				<aui:input name="trial" type="hidden" value="<%= trial %>" />
				<aui:input name="countryId" type="hidden" value="<%= storeCountryId %>" />

				<aui:input label="purchase-this-app-for-my-personal-account" name="ownerClassNameRadio" type="radio" value="0" />
				<aui:input label="purchase-this-app-for-my-company" name="ownerClassNameRadio" type="radio" value="1" />

				<aui:input cssClass="legal-name" disabled="<%= ownerClassNameRadio != 1 %>" helpMessage="please-be-sure-to-use-your-companys-full-legal-entity-name" name="legalEntityName" type="text" />

				<%
				PortletURL eulaURL = renderResponse.createRenderURL();

				eulaURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
				eulaURL.setParameter("contractEntryId", String.valueOf(eulaContractEntry.getContractEntryId()));
				eulaURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				PortletURL tosURL = renderResponse.createRenderURL();

				tosURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
				tosURL.setParameter("contractEntryId", String.valueOf(tosContractEntry.getContractEntryId()));
				tosURL.setWindowState(LiferayWindowState.EXCLUSIVE);

				Object[] arguments = new Object[] {"<a href=\"" + eulaURL.toString() + "\" target=\"_blank\">", "</a>", "<a href=\"" + tosURL.toString() + "\" target=\"_blank\">", "</a>"};

				String taglibContractEntriesLabel = LanguageUtil.format(pageContext, "i-have-read-and-agree-to-the-end-user-license-agreement-and-the-terms-of-service", arguments);
				%>

				<aui:input cssClass="accept-contract" label="<%= taglibContractEntriesLabel %>" name="acceptContract" type="checkbox" />
			</aui:fieldset>

			<div class="cleared"></div>

			<div class="fr ha-r">
				<a class="btn" href="javascript:submitForm(document.<portlet:namespace />fm);"><liferay-ui:message key="trial" /></a>
			</div>
		</c:when>
		<c:when test="<%= Validator.isNull(country) %>">
			<aui:fieldset cssClass="purchase-type" label="purchase-type">
				<aui:input name="ownerClassName" type="hidden" value="<%= ownerClassName %>" />

				<aui:input label="purchase-this-app-for-my-personal-account" name="ownerClassNameRadio" type="radio" value="0" />
				<aui:input label="purchase-this-app-for-my-company" name="ownerClassNameRadio" type="radio" value="1" />

				<aui:input cssClass="legal-name" disabled="<%= ownerClassNameRadio != 1 %>" helpMessage="please-be-sure-to-use-your-companys-full-legal-entity-name" name="legalEntityName" type="text" />

				<aui:select cssClass="required" label="country-of-delivery" name="country">
					<%@ include file="/marketplace/purchase_so_ee_country_options.jspf" %>
				</aui:select>

				<div class="fr ha-r">
					<a class="btn" onClick="<portlet:namespace />selectPurchaseType();"><liferay-ui:message key="next" /></a>
				</div>
			</aui:fieldset>

			<aui:script>
				Liferay.provide(
					window,
					'<portlet:namespace />selectPurchaseType',
					function() {
						var A = AUI();

						var legalEntityName = A.one('#<portlet:namespace />legalEntityName');
						var ownerClassName = A.one('#<portlet:namespace />ownerClassName');
						var ownerClassPK = A.one('#<portlet:namespace />ownerClassPK');
						var country = A.one('#<portlet:namespace />country');

						if (ownerClassName.val().length == 0) {
							var msg = '<liferay-ui:message key="please-select-who-will-purchase-this-app" />';

							A.one('#<portlet:namespace />fm .portlet-msg-error').setContent(msg).show();
						}
						else if ((legalEntityName != null) && (legalEntityName.val().length == 0) && (A.one('input[name=<portlet:namespace />ownerClassNameRadio]:checked').val() == 1)) {
							var msg = '<liferay-ui:message key="please-enter-a-valid-legal-entity-name" />';

							A.one('#<portlet:namespace />fm .portlet-msg-error').setContent(msg).show();
						}
						else if (country.val().length == 0) {
							var msg = '<liferay-ui:message key="please-select-a-valid-region-of-destination" />';

							A.one('#<portlet:namespace />fm .portlet-msg-error').setContent(msg).show();
						}
						else {
							var purchaseUrl = '<portlet:renderURL><portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>';

							purchaseUrl += '&<portlet:namespace />ownerClassName=' + ownerClassName.val() + '&<portlet:namespace />country=' + country.val();

							if (legalEntityName != null) {
								purchaseUrl += '&<portlet:namespace />legalEntityName=' + legalEntityName.val();
							}

							if (ownerClassPK != null) {
								purchaseUrl += '&<portlet:namespace />ownerClassPK=' + ownerClassPK.val();
							}

							location.href = purchaseUrl;
						}
					},
					['aui-base']
				);
			</aui:script>

			<aui:script use="aui-base">

				<%
				String[] userCountries = (String[])userExpandoBridge.getAttribute("osbCountry");
				String userCountry = LanguageUtil.get(LocaleUtil.US, (String)ArrayUtil.getValue(userCountries, 0));
				%>

				var countrySelect = A.one('#<portlet:namespace />country');
				var countryOption = countrySelect.one('option[value=<%= userCountry %>]');

				if (countryOption) {
					countryOption.attr('selected', true);
				}
			</aui:script>
		</c:when>
		<c:otherwise>

			<%
			String legalEntityName = ParamUtil.getString(request, "legalEntityName");
			long ownerClassPK = ParamUtil.getLong(request, "ownerClassPK");
			%>

			<aui:input name="legalEntityName" type="hidden" value="<%= legalEntityName %>" />
			<aui:input name="ownerClassName" type="hidden" value="<%= ownerClassName %>" />
			<aui:input name="ownerClassPK" type="hidden" value="<%= ownerClassPK %>" />
			<aui:input name="country" type="hidden" value="<%= country %>" />

			<aui:fieldset cssClass="so-ee-license-fields">
				<h3>
					<liferay-ui:message key="step-1" />
				</h3>

				<p>
					<liferay-ui:message key="please-select-a-license-below" />
				</p>

				<p>
					<liferay-ui:message key="please-note-that-you-must-have-liferay-portal-6.1-ee-ga2-or-later" />
				</p>

				<table class="license-types">
					<thead>
						<tr class="results-header">
							<th class="col-1 first">
								<liferay-ui:message key="license-1-year-term" />
							</th>
							<th class="col-2 last">
								<liferay-ui:message key="price" />
							</th>
						</tr>
					</thead>
					<tbody>

						<%
						Locale orderLocale = MarketplaceSOEEUtil.getOrderLocale(country);
						Map<String, Double> priceMap = MarketplaceSOEEUtil.getPriceMap(country);

						Set<String> licenseTypes = priceMap.keySet();

						for (String licenseType : licenseTypes) {
						%>

							<tr class="results-row">
								<td class="col-1 first">
									<aui:input label="<%= licenseType %>" name="licenseType" type="radio" value="<%= licenseType %>" />
								</td>
								<td class="col-2 last">
									<%= CurrencyUtil.format(orderLocale, priceMap.get(licenseType)) %>
								</td>
							</tr>

						<%
						}
						%>

					</tbody>
				</table>

				<h3>
					<liferay-ui:message key="step-2" />
				</h3>

				<p>
					<liferay-ui:message key="please-provide-the-address-of-where-the-software-will-be-delivered" />
				</p>

				<aui:layout>
					<div class="name">
						<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
							<aui:input cssClass="required" label="first-name" name="firstName" type="text" value="<%= user.getFirstName() %>" />
						</aui:column>

						<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
							<aui:input cssClass="required" label="last-name" name="lastName" type="text" value="<%= user.getLastName() %>" />
						</aui:column>
					</div>

					<div class="email">
						<aui:column columnWidth="<%= 100 %>" first="<%= true %>">
							<aui:input cssClass="required" label="email-address" name="emailAddress" type="text" value="<%= user.getEmailAddress() %>" />
						</aui:column>
					</div>

					<div class="address">
						<aui:column columnWidth="<%= 100 %>" first="<%= true %>">
							<aui:input cssClass="required" label="address" name="street1" type="text" />

							<aui:input label="" name="street2" type="text" />

							<aui:input label="" name="street3" type="text" />
						</aui:column>
					</div>

					<div class="city-postal">
						<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
							<aui:input cssClass="required" name="city" type="text" />
						</aui:column>

						<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
							<aui:input cssClass="required" label="postal-code" name="zip" type="text" />
						</aui:column>
					</div>

					<div class="country-state">
						<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
							<span class="aui-field aui-field-text">
								<span class="aui-field-content">
									<span class="aui-field-label">
										<liferay-ui:message key="country" />
									</span>

									<span class="aui-field-element">
										<%= HtmlUtil.escape(country) %>
									</span>
								</span>
							</span>
						</aui:column>

						<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
							<aui:select label="State/Province" name="region">
								<%@ include file="/marketplace/purchase_so_ee_region_options.jspf" %>
							</aui:select>
						</aui:column>
					</div>
				</aui:layout>

				<div>

					<%
					PortletURL eulaURL = renderResponse.createRenderURL();

					eulaURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
					eulaURL.setParameter("contractEntryId", String.valueOf(eulaContractEntry.getContractEntryId()));
					eulaURL.setWindowState(LiferayWindowState.EXCLUSIVE);

					PortletURL tosURL = renderResponse.createRenderURL();

					tosURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
					tosURL.setParameter("contractEntryId", String.valueOf(tosContractEntry.getContractEntryId()));
					tosURL.setWindowState(LiferayWindowState.EXCLUSIVE);

					Object[] arguments = new Object[] {"<a href=\"" + eulaURL.toString() + "\" target=\"_blank\">", "</a>", "<a href=\"" + tosURL.toString() + "\" target=\"_blank\">", "</a>"};

					String taglibContractEntriesLabel = LanguageUtil.format(pageContext, "i-have-read-and-agree-to-the-end-user-license-agreement-and-the-terms-of-service", arguments);
					%>

					<aui:input cssClass="accept-contract" label="<%= taglibContractEntriesLabel %>" name="acceptContract" type="checkbox" />
				</div>

				<div class="portlet-msg-info">
					<liferay-ui:message key="upon-clicking-the-order-button-below,-a-sales-representative-will-contact-you-to-fulfill-your-order" />
				</div>

				<div class="fr ha-r">
					<a class="btn" href="javascript:if (confirm('<%= HtmlUtil.escapeAttribute(UnicodeLanguageUtil.format(pageContext, "are-you-sure-you-want-to-order-x", appEntry.getTitle())) %>')) { submitForm(document.<portlet:namespace />fm); }"><liferay-ui:message key="order-2" /></a>
				</div>
			</aui:fieldset>
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script use="aui-dialog,aui-io,liferay-dynamic-select">
	var ownerClassName = A.one('#<portlet:namespace />ownerClassName');

	var legalEntityName = A.one('#<portlet:namespace />legalEntityName');

	var purchaseType = A.one('.marketplace .purchase-type');

	if (purchaseType != null) {
		purchaseType.delegate(
			'change',
			function(event) {
				var targetRadio = event.currentTarget;

				var nameContainer = A.one('.legal-name');

				ownerClassName.val('<%= User.class.getName() %>');

				if (targetRadio.val() == 0) {
					legalEntityName.attr('value', '');
					legalEntityName.attr('disabled', true);

					nameContainer.addClass('aui-field-disabled');
				}
				else if (targetRadio.val() == 1) {
					legalEntityName.removeAttribute('disabled');

					nameContainer.removeClass('aui-field-disabled');
				}
			},
			'input[type=radio]'
		);
	}

	A.all('.accept-contract a').on(
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