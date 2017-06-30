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

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntry ecDocumentEntry = ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

List<Address> addresses = AddressLocalServiceUtil.getAddresses(themeDisplay.getCompanyId(), Contact.class.getName(), contact.getContactId());

addresses = ListUtil.copy(addresses);

Iterator<Address> itr = addresses.iterator();

while (itr.hasNext()) {
	Address address = itr.next();

	if (address.getCountryId() != storeCountryId) {
		itr.remove();

		continue;
	}

	ExpandoBridge addressExpandoBridge = address.getExpandoBridge();

	String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();

	String addressCompanyName = GetterUtil.getString(addressExpandoBridge.getAttribute("osbCompanyName", false));

	if (ownerClassName.equals(CorpProject.class.getName()) && Validator.isNull(addressCompanyName)) {
		itr.remove();
	}
}

boolean editAddress = ParamUtil.getBoolean(request, "editAddress", addresses.isEmpty());
%>

<div class="purchase-app-entry-destination">
	<h3>
		<liferay-ui:message key="destination-address" />
	</h3>

	<div class="aui-field-help">
		<liferay-ui:message key="the-destination-address-represents-the-location-where-the-app-will-be-electronically-downloaded" />
	</div>

	<portlet:actionURL name="purchaseAppEntryDestination" var="purchaseAppEntryDestinationURL" />

	<aui:form action="<%= purchaseAppEntryDestinationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= editAddress ? Constants.UPDATE : StringPool.BLANK %>" />
		<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace/purchase_app_entry.jsp" />

		<portlet:renderURL var="purchaseAppEntrySummaryURL">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
			<portlet:param name="purchaseStep" value="summary" />
			<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="<%= purchaseAppEntrySummaryURL %>" />

		<aui:input name="purchaseStep" type="hidden" value="destination" />
		<aui:input name="ecDocumentEntryId" type="hidden" value="<%= ecDocumentEntryId %>" />
		<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />
		<aui:input name="ownerClassName" type="hidden" value="<%= ecDocumentEntryExtraSettings.getOwnerClassName() %>" />

		<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
		<liferay-ui:error exception="<%= AddressCompanyNameException.class %>" message="please-select-an-address-with-a-valid-company-name" />
		<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
		<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
		<liferay-ui:error exception="<%= NoSuchAddressException.class %>" message="please-select-a-destination" />

		<c:choose>
			<c:when test="<%= editAddress %>">

				<%
				long addressId = ParamUtil.getLong(request, "addressId");

				Address address = AddressLocalServiceUtil.fetchAddress(addressId);

				Country country = CountryServiceUtil.getCountry(storeCountryId);
				%>

				<aui:model-context bean="<%= address %>" model="<%= Address.class %>" />

				<aui:input name="addressId" type="hidden" />

				<div class="edit-address-fields">
					<div class="edit-address-field-column">

						<%
						String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();
						%>

						<c:if test="<%= ownerClassName.equals(CorpProject.class.getName()) %>">

							<%
							String addressCompanyName = StringPool.BLANK;
							String addressVATNumber = StringPool.BLANK;

							if (address != null) {
								ExpandoBridge addressExpandoBridge = address.getExpandoBridge();

								addressCompanyName = GetterUtil.getString(addressExpandoBridge.getAttribute("osbCompanyName", false));
								addressVATNumber = GetterUtil.getString(addressExpandoBridge.getAttribute("vatNumber", false));
							}
							%>

							<aui:input name="companyName" required="<%= true %>" type="text" value="<%= addressCompanyName %>" />

							<c:if test="<%= ArrayUtil.contains(PortletPropsValues.VAT_EU_COUNTRIES, country.getA2()) %>">
								<aui:input label="vat-number" name="vatNumber" type="text" value="<%= addressVATNumber %>" />
							</c:if>
						</c:if>

						<aui:input name="street1" type="text" />

						<aui:input name="street2" type="text" />

						<aui:input name="street3" type="text" />
					</div>

					<div class="edit-address-field-column">
						<aui:input name="countryId" type="hidden" value="<%= storeCountryId %>" />

						<span class="aui-field">
							<label class="aui-field-label">
								<liferay-ui:message key="country" />
							</label>

							<span class="aui-field-element"><%= country.getName() %></span>
						</span>

						<%
						List<Region> regions = RegionServiceUtil.getRegions(storeCountryId);
						%>

						<c:if test="<%= !regions.isEmpty() %>">
							<aui:select label="region" name="regionId" showEmptyOption="true">

								<%
								for (Region region : regions) {
								%>

									<aui:option label="<%= region.getName() %>" value="<%= region.getRegionId() %>" />

								<%
								}
								%>

							</aui:select>
						</c:if>

						<aui:input name="city" type="text" />

						<aui:input name="zip" type="text" />
					</div>
				</div>
			</c:when>
			<c:otherwise>

				<%
				long addressId = ecDocumentEntryExtraSettings.getAddressId();
				%>

				<aui:input name="addressId" type="hidden" value="<%= addressId %>" />

				<div class="card-container destination-addresses">

					<%
					for (Address address : addresses) {
						address = address.toEscapedModel();

						ExpandoBridge addressExpandoBridge = address.getExpandoBridge();

						String addressCompanyName = GetterUtil.getString(addressExpandoBridge.getAttribute("osbCompanyName", false));

						Country country = address.getCountry();
						Region region = address.getRegion();
					%>

						<div class="card address" data-addressId="<%= address.getAddressId() %>">
							<div class="card-body">
								<c:if test="<%= Validator.isNotNull(addressCompanyName) %>">
									<div class="name">
										<%= HtmlUtil.escape(addressCompanyName) %>
									</div>
								</c:if>

								<div>
									<%= address.getStreet1() %>
								</div>

								<div>
									<%= address.getStreet2() %>
								</div>

								<div>
									<%= address.getStreet3() %>
								</div>

								<div>
									<%= address.getCity() %>
								</div>

								<div>
									<%= region.getName() %>
								</div>

								<div>
									<%= address.getZip() %>
								</div>

								<div>
									<%= country.getName() %>
								</div>

								<portlet:renderURL var="editAddressURL">
									<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
									<portlet:param name="purchaseStep" value="destination" />
									<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
									<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
									<portlet:param name="addressId" value="<%= String.valueOf(address.getAddressId()) %>" />
									<portlet:param name="editAddress" value="<%= Boolean.TRUE.toString() %>" />
								</portlet:renderURL>

								<a class="edit" href="<%= editAddressURL %>"><liferay-ui:message key="edit" /></a>
							</div>

							<div class="card-action">
								<a class="btn" href="javascript:;"><liferay-ui:message key="select" /></a>
							</div>
						</div>

					<%
					}
					%>

					<portlet:renderURL var="newAddressURL">
						<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
						<portlet:param name="purchaseStep" value="destination" />
						<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
						<portlet:param name="editAddress" value="<%= Boolean.TRUE.toString() %>" />
					</portlet:renderURL>

					<div class="card new-address" data-href="<%= newAddressURL %>">
						<div class="card-body">
							<div class="name">
								<liferay-ui:message key="add-a-new-address" />
							</div>
						</div>
					</div>

					<div class="cleared"></div>
				</div>

				<aui:script use="aui-base">
					var cardContainer = A.one('.marketplace .card-container');

					var card = cardContainer.one('.card[data-addressId=<%= addressId %>]');

					if (card) {
						card.addClass('selected');
					}

					cardContainer.delegate(
						'click',
						function(event) {
							var target = event.target;

							if (!target.hasClass('btn') && !target.hasClass('card-body') && !target.ancestor('.card-body')) {
								return;
							}

							var card = event.currentTarget;

							if (card.hasAttribute('data-href')) {
								window.location = card.getAttribute('data-href');
							}

							cardContainer.all('.card').removeClass('selected');

							card.addClass('selected');

							document.<portlet:namespace />fm.<portlet:namespace />addressId.value = card.getAttribute('data-addressId');
						},
						'.card'
					);
				</aui:script>
			</c:otherwise>
		</c:choose>

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

			<portlet:renderURL var="purchaseAppEntryLicenseURL">
				<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
				<portlet:param name="purchaseStep" value="license" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
			</portlet:renderURL>

			<aui:button href="<%= purchaseAppEntryLicenseURL %>" value="back" />

			<c:choose>
				<c:when test="<%= editAddress %>">
					<portlet:renderURL var="chooseAddressURL">
						<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
						<portlet:param name="purchaseStep" value="destination" />
						<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
						<portlet:param name="editAddress" value="<%= Boolean.FALSE.toString() %>" />
					</portlet:renderURL>

					<aui:button href="<%= chooseAddressURL %>" value="choose-address" />

					<aui:button type="submit" value="save-and-continue" />
				</c:when>
				<c:otherwise>
					<aui:button type="submit" value="next" />
				</c:otherwise>
			</c:choose>
		</aui:button-row>
	</aui:form>
</div>