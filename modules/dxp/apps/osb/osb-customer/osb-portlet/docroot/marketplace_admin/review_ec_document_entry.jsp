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
String backURL = ParamUtil.getString(request, "backURL", redirect);

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntry ecDocumentEntry = ECDocumentEntryLocalServiceUtil.fetchECDocumentEntry(ecDocumentEntryId);

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

DateFormat shortDateFormatDateTime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
%>

<div class="review-ec-document-entry">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="review-transaction"
	/>

	<h2>
		<liferay-ui:message key="document-number" />: <%= ecDocumentEntry.getEcDocumentEntryId() %>
	</h2>

	<aui:button-row>
		<div class="button-group">
			<c:if test="<%= !MarketplaceEcommerceUtil.PAY_PAL_PAYMENT_PROCESSOR_CLASS_NAME.equals(ecDocumentEntry.getPaymentProcessor()) %>">
				<c:choose>
					<c:when test="<%= ecDocumentEntry.isOrder() %>">
						<liferay-portlet:actionURL name="updateECDocumentEntryType" var="updateECDocumentEntryTypeURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="backURL" value="<%= backURL %>" />
							<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
							<portlet:param name="type" value="<%= String.valueOf(ECDocumentEntryConstants.TYPE_INVOICE) %>" />
						</liferay-portlet:actionURL>

						<aui:button onClick="<%= updateECDocumentEntryTypeURL %>" value="invoice-sent" />

						<%
						String taglibOnClick = renderResponse.getNamespace() + "voidECDocumentEntry();";
						%>

						<aui:button onClick="<%= taglibOnClick %>" value="invoice-declined" />
					</c:when>
					<c:when test="<%= (ecDocumentEntry.getType() == ECDocumentEntryConstants.TYPE_INVOICE) && !ecDocumentEntry.isPaid() %>">
						<liferay-portlet:actionURL name="updateECDocumentEntryStatus" var="updateECDocumentEntryStatusURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="backURL" value="<%= backURL %>" />
							<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
							<portlet:param name="status" value="<%= String.valueOf(ECDocumentEntryConstants.STATUS_PAID_PENDING_PAYOUT) %>" />
						</liferay-portlet:actionURL>

						<aui:button onClick="<%= updateECDocumentEntryStatusURL %>" value="invoice-paid" />

						<%
						String taglibOnClick = renderResponse.getNamespace() + "voidECDocumentEntry();";
						%>

						<aui:button onClick="<%= taglibOnClick %>" value="invoice-declined" />
					</c:when>
					<c:when test="<%= ecDocumentEntry.getStatus() == ECDocumentEntryConstants.STATUS_PAID_PENDING_PAYOUT %>">
						<liferay-portlet:actionURL name="updateECDocumentEntryStatus" var="updateECDocumentEntryStatusURL">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="backURL" value="<%= backURL %>" />
							<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
							<portlet:param name="status" value="<%= String.valueOf(ECDocumentEntryConstants.STATUS_PAID) %>" />
						</liferay-portlet:actionURL>

						<aui:button onClick="<%= updateECDocumentEntryStatusURL %>" value="developer-paid-out" />
					</c:when>
				</c:choose>
			</c:if>

			<c:if test="<%= ecDocumentEntry.isRefundable() %>">

				<%
				String taglibOnClick = renderResponse.getNamespace() + "refundECDocumentEntry();";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="refund-customer" />
			</c:if>
		</div>
	</aui:button-row>

	<table class="document-metadata">
		<thead>
			<tr>
				<th>
					<liferay-ui:message key="create-date" />
				</th>
				<th>
					<liferay-ui:message key="modified-date" />
				</th>
				<th>
					<liferay-ui:message key="paid-date" />
				</th>
				<th>
					<liferay-ui:message key="fulfillment-date" />
				</th>
				<th>
					<liferay-ui:message key="payment-method" />
				</th>
				<th>
					<liferay-ui:message key="status" />
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<%= shortDateFormatDateTime.format(ecDocumentEntry.getCreateDate()) %>
				</td>
				<td>
					<%= shortDateFormatDateTime.format(ecDocumentEntry.getModifiedDate()) %>
				</td>
				<td>
					<c:if test="<%= ecDocumentEntry.getPaidDate() != null %>">
						<%= shortDateFormatDateTime.format(ecDocumentEntry.getPaidDate()) %>
					</c:if>
				</td>
				<td>
					<c:if test="<%= ecDocumentEntry.getFulfillmentDate() != null %>">
						<%= shortDateFormatDateTime.format(ecDocumentEntry.getFulfillmentDate()) %>
					</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="<%= MarketplaceEcommerceUtil.PAY_PAL_PAYMENT_PROCESSOR_CLASS_NAME.equals(ecDocumentEntry.getPaymentProcessor()) %>">
							<liferay-ui:message key="paypal" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="invoice-me" />
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<liferay-ui:message key="<%= ecDocumentEntry.getStatusLabel() %>" />

					<c:if test="<%= (ecDocumentEntry.getStatus() == ECDocumentEntryConstants.STATUS_UNPAID) && (ecDocumentEntry.getType() == ECDocumentEntryConstants.TYPE_INVOICE) %>">
						(<liferay-ui:message key="invoice-sent" />)
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>

	<table class="document-users">
		<thead>
			<tr>
				<th>
					<liferay-ui:message key="customer" />
				</th>
				<th>
					<liferay-ui:message key="developer" />
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div class="owner">
						<strong><%= PortalUtil.getUserName(ecDocumentEntry.getBillingUserId(), ecDocumentEntry.getBillingUserName()) %></strong> (<%= ecDocumentEntry.getBillingUserId() %>)<br />

						<%
						User billingUser = UserLocalServiceUtil.getUser(ecDocumentEntry.getBillingUserId());
						%>

						<liferay-ui:message key="liferay-user-email" />: <%= HtmlUtil.escape(billingUser.getEmailAddress()) %><br />

						<liferay-ui:message key="billing-email" />: <%= HtmlUtil.escape(ecDocumentEntry.getBillingEmailAddress()) %><br />

						<c:choose>
							<c:when test="<%= ecDocumentEntryExtraSettings.getOwnerClassName().equals(CorpProject.class.getName()) %>">

								<%
								CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(ecDocumentEntryExtraSettings.getOwnerClassPK());
								%>

								<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_CORP_PROJECT_ADMIN %>" var="corpProjectURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
									<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
									<portlet:param name="backURL" value="<%= currentURL %>" />
									<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:message key="project" />: <a href="<%= corpProjectURL.toString() %>" target="_blank"><%= HtmlUtil.escape(corpProject.getName()) %></a>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="for-personal-use-only" />
							</c:otherwise>
						</c:choose>
					</div>

					<div class="address">
						<%= HtmlUtil.escape(ecDocumentEntry.getShippingAddressStreet1()) %><br />

						<c:if test="<%= Validator.isNotNull(ecDocumentEntry.getShippingAddressStreet2()) %>">
							<%= HtmlUtil.escape(ecDocumentEntry.getShippingAddressStreet2()) %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(ecDocumentEntry.getShippingAddressStreet3()) %>">
							<%= HtmlUtil.escape(ecDocumentEntry.getShippingAddressStreet3()) %><br />
						</c:if>

						<%= HtmlUtil.escape(ecDocumentEntry.getShippingAddressCity()) %><br />

						<c:if test="<%= ecDocumentEntry.getShippingAddressRegionId() > 0 %>">

							<%
							Region region = RegionServiceUtil.getRegion(ecDocumentEntry.getShippingAddressRegionId());
							%>

							<%= region.getName() %><br />
						</c:if>

						<c:if test="<%= Validator.isNotNull(ecDocumentEntry.getShippingAddressZip()) %>">
							<%= HtmlUtil.escape(ecDocumentEntry.getShippingAddressZip()) %><br />
						</c:if>

						<%
						Country country = CountryServiceUtil.getCountry(ecDocumentEntry.getShippingAddressCountryId());
						%>

						<%= country.getName() %>
					</div>

					<div class="vat-number">
						<liferay-ui:message key="vat-number" />: <%= ecDocumentEntry.getVatNumber() %>
					</div>
				</td>
				<td>
					<div class="developer">

						<%
						DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(ecDocumentEntryExtraSettings.getDeveloperEntryId());
						%>

						<strong><%= HtmlUtil.escape(developerEntry.getName()) %></strong> (<%= developerEntry.getDeveloperEntryId() %>)<br />

						<liferay-ui:message key="contact-email" />: <%= HtmlUtil.escape(developerEntry.getEmailAddress()) %> <br />
						<liferay-ui:message key="paypal-email" />: <%= HtmlUtil.escape(developerEntry.getPaymentEmailAddress()) %><br />
					</div>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="document-items-wrapper">
		<div class="document-items">
			<liferay-ui:search-container
				curParam="cur1"
				delta="<%= ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(ecDocumentEntryId) %>"
				emptyResultsMessage="there-are-no-results"
			>
				<liferay-ui:search-container-results
					results="<%= ECDocumentItemLocalServiceUtil.getECDocumentItems(ecDocumentEntryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
					total="<%= ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(ecDocumentEntryId) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.ecommerce.model.ECDocumentItem"
					escapedModel="<%= true %>"
					keyProperty="ecDocumentItemId"
					modelVar="ecDocumentItem"
				>
					<liferay-ui:search-container-column-text
						name="#"
						value="<%= String.valueOf(ecDocumentItem.getLineNumber()) %>"
					/>

					<liferay-ui:search-container-column-text
						name="item"
					>
						<div>
							<%= ecDocumentItem.getName() %>

							<%
							ECDocumentItemExtraSettings ecDocumentItemExtraSettings = new ECDocumentItemExtraSettings(ecDocumentItem);
							%>

							<c:if test="<%= (ecDocumentItem.getClassPK() < 0) && (ecDocumentItemExtraSettings.getUsageType() >= 0) %>">
								<liferay-ui:message key="<%= AssetLicenseConstants.getUsageTypeLabel(ecDocumentItemExtraSettings.getUsageType()) %>" />

								-

								<%
								long[] proratedAssetReceiptLicenseIds = ecDocumentItemExtraSettings.getProratedAssetReceiptLicenseIds();
								%>

								<c:if test="<%= proratedAssetReceiptLicenseIds.length > 0 %>">
									<liferay-ui:message arguments="<%= proratedAssetReceiptLicenseIds.length %>" key="x-instance-units" />,
								</c:if>

								<liferay-ui:message arguments="<%= ecDocumentItemExtraSettings.getProratedDays() %>" key="x-days" />
							</c:if>
						</div>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="qty"
						value="<%= String.valueOf(ecDocumentItem.getQuantity()) %>"
					/>

					<liferay-ui:search-container-column-text
						name="price"
						value="<%= ecDocumentItem.getFormattedUnitPrice() %>"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
			</liferay-ui:search-container>
		</div>

		<table class="totals">
			<tr>
				<td>
					<liferay-ui:message key="subtotal" />
				</td>
				<td class="amount">
					<%= ecDocumentEntry.getFormattedSubtotal() %>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="tax" />
				</td>
				<td class="amount">
					<%= ecDocumentEntry.getFormattedTaxAmount() %>
				</td>
			</tr>
			<tr class="total">
				<td>
					<liferay-ui:message key="total" />
				</td>
				<td class="amount">
					<%= ecDocumentEntry.getFormattedTotal() %>
				</td>
			</tr>
		</table>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />refundECDocumentEntry',
		function() {
			if (!confirm('<liferay-ui:message key="are-you-sure-you-want-to-refund-this-invoice" unicode="<%= true %>" />')) {
				return;
			}

			<liferay-portlet:actionURL name="refundECDocumentEntry" var="refundECDocumentEntryURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="backURL" value="<%= backURL %>" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
			</liferay-portlet:actionURL>

			submitForm(document.hrefFm, '<%= refundECDocumentEntryURL %>');
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />voidECDocumentEntry',
		function() {
			if (!confirm('<liferay-ui:message key="are-you-sure-the-user-declined-this-invoice" unicode="<%= true %>" />')) {
				return;
			}

			<liferay-portlet:actionURL name="updateECDocumentEntryStatus" var="updateECDocumentEntryStatusURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="backURL" value="<%= backURL %>" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				<portlet:param name="status" value="<%= String.valueOf(ECDocumentEntryConstants.STATUS_VOID) %>" />
			</liferay-portlet:actionURL>

			submitForm(document.hrefFm, '<%= updateECDocumentEntryStatusURL %>');
		}
	);
</aui:script>