<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<liferay-ui:search-container
	searchContainer="<%= accountEntryViewDisplayContext.getProductPurchaseViewsSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.customer.account.entry.details.web.internal.display.context.ProductPurchaseViewDisplay"
		modelVar="productPurchaseViewDisplay"
	>
		<liferay-ui:search-container-column-text
			name="product"
			value="<%= productPurchaseViewDisplay.getProductName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="start-date"
			value="<%= productPurchaseViewDisplay.getStartDate() %>"
		/>

		<liferay-ui:search-container-column-text
			name="support-end-date"
			value="<%= productPurchaseViewDisplay.getOriginalEndDate() %>"
		/>

		<liferay-ui:search-container-column-text
			name="instance-size"
			value="<%= productPurchaseViewDisplay.getSizing() %>"
		/>

		<liferay-ui:search-container-column-text
			name="quantity"
			value="<%= productPurchaseViewDisplay.getQuantity() %>"
		/>

		<liferay-ui:search-container-column-text
			name="provisioned"
			value="<%= productPurchaseViewDisplay.getCurrentProvisionedCount() %>"
		/>

		<c:if test="<%= accountEntryViewDisplayContext.isAdminOrSubscriptionServices() || accountEntryViewDisplayContext.isPartnerManagedSupportWorker() %>">
			<liferay-ui:search-container-column-text
				name="state"
			>
				<span class="label label-sm <%= productPurchaseViewDisplay.getStateStyle() %>"><%= productPurchaseViewDisplay.getState() %></span>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>