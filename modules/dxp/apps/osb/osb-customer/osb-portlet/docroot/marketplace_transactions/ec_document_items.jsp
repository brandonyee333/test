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
long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntry ecDocumentEntry = ECDocumentEntryLocalServiceUtil.getECDocumentEntry(ecDocumentEntryId);

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);
%>

<div class="ec-document-entry-invoice-items">
	<liferay-ui:search-container>
		<liferay-ui:search-container-results
			results="<%= ECDocumentItemLocalServiceUtil.getECDocumentItems(ecDocumentEntry.getEcDocumentEntryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS) %>"
			total="<%= ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(ecDocumentEntry.getEcDocumentEntryId()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.ecommerce.model.ECDocumentItem"
			keyProperty="ecDocumentItemId"
			modelVar="ecDocumentItem"
		>
			<liferay-ui:search-container-column-text
				name="quantity"
				property="quantity"
			/>

			<liferay-ui:search-container-column-text
				name="item"
			>

				<%
				String className = ecDocumentItem.getClassName();
				%>

				<c:choose>
					<c:when test="<%= className.equals(AssetLicense.class.getName()) %>">
						<c:choose>
							<c:when test="<%= ecDocumentItem.getClassPK() == ECommerceConstants.CLASS_PK_DEVELOPER %>">
								<liferay-ui:message key="extended-developer-licenses" /> (<liferay-ui:message key="prorated" />)
							</c:when>
							<c:when test="<%= ecDocumentItem.getClassPK() == ECommerceConstants.CLASS_PK_STANDARD %>">
								<liferay-ui:message key="extended-standard-licenses" /> (<liferay-ui:message key="prorated" />)
							</c:when>
							<c:otherwise>

								<%
								AssetLicense assetLicense = AssetLicenseLocalServiceUtil.getAssetLicense(ecDocumentItem.getClassPK());
								%>

								<div>
									<liferay-ui:message arguments="<%= assetLicense.getLicenseTypeAllotment() %>" key='<%= (assetLicense.getLicenseTypeAllotment() > 1) ? "x-instance-units" : "x-instance-unit" %>' /> <liferay-ui:message key="<%= assetLicense.getUsageTypeLabel() %>" />
								</div>

								<c:if test="<%= Validator.isNotNull(ecDocumentItem.getExtraSettings()) %>">
									<div class="description">

										<%
										ECDocumentItemExtraSettings ecDocumentItemExtraSettings = new ECDocumentItemExtraSettings(ecDocumentItem);
										%>

										<c:if test="<%= ecDocumentItemExtraSettings.getStartDate() != null %>">
											<div>
												<liferay-ui:message key="license-starts" />: <%= mediumDateFormatDate.format(ecDocumentItemExtraSettings.getStartDate()) %>
											</div>
										</c:if>

										<c:if test="<%= ecDocumentItemExtraSettings.getEndDate() != null %>">
											<div>
												<liferay-ui:message key="license-ends" />: <%= mediumDateFormatDate.format(ecDocumentItemExtraSettings.getEndDate()) %>
											</div>
										</c:if>
									</div>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="<%= className.equals(AssetReceiptSupport.class.getName()) %>">

						<%
						String purchaseType = ecDocumentEntryExtraSettings.getPurchaseType();
						%>

						<c:choose>
							<c:when test='<%= purchaseType.equals("new") %>'>
								<c:choose>
									<c:when test="<%= ecDocumentItem.getClassPK() == ECommerceConstants.CLASS_PK_DEVELOPER %>">
										<liferay-ui:message key="extend-subscription-services-for-developer-license" /> (<liferay-ui:message key="prorated" />)
									</c:when>
									<c:when test="<%= ecDocumentItem.getClassPK() == ECommerceConstants.CLASS_PK_STANDARD %>">
										<liferay-ui:message key="extend-subscription-services-for-standard-license" /> (<liferay-ui:message key="prorated" />)
									</c:when>
								</c:choose>
							</c:when>
							<c:when test='<%= purchaseType.equals("renew") %>'>
								<c:choose>
									<c:when test="<%= ecDocumentItem.getClassPK() == ECommerceConstants.CLASS_PK_DEVELOPER %>">
										<liferay-ui:message key="renew-subscription-services-for-developer-license" />
									</c:when>
									<c:when test="<%= ecDocumentItem.getClassPK() == ECommerceConstants.CLASS_PK_STANDARD %>">
										<liferay-ui:message key="renew-subscription-services-for-standard-license" />
									</c:when>
								</c:choose>
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="unit-price"
				value="<%= ecDocumentItem.getFormattedUnitPrice() %>"
			/>

			<liferay-ui:search-container-column-text
				name="line-price"
				value="<%= ecDocumentItem.getFormattedLinePrice() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator paginate="<%= false %>" />
	</liferay-ui:search-container>

	<table class="totals">
		<tbody>
			<tr>
				<td>
				</td>
				<td class="col-3">
					<liferay-ui:message key="subtotal" />
				</td>
				<td class="col-4">
					<%= ecDocumentEntry.getFormattedSubtotal() %>
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="col-3">
					<liferay-ui:message key="tax" />
				</td>
				<td class="col-4">
					<%= ecDocumentEntry.getFormattedTaxAmount() %>
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td class="col-3">
					<liferay-ui:message key="total" />
				</td>
				<td class="col-4">
					<%= ecDocumentEntry.getFormattedTotal() %>
				</td>
			</tr>
		</tbody>
	</table>
</div>