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
	searchContainer="<%= accountEntryViewDisplayContext.getProductPurchasesSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase"
		modelVar="productPurchase"
	>

		<%
		Product product = productPurchase.getProduct();
		%>

		<liferay-ui:search-container-column-text
			name="product"
			value="<%= product.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="start-date"
			value="<%= (productPurchase.getStartDate() != null) ? longDateFormatDate.format(productPurchase.getStartDate()) : StringPool.DASH %>"
		/>

		<liferay-ui:search-container-column-text
			name="support-end-date"
			value="<%= (productPurchase.getEndDate() != null) ? longDateFormatDate.format(productPurchase.getEndDate()) : StringPool.DASH %>"
		/>

		<liferay-ui:search-container-column-text
			name="instance-size"
		>

			<%
			Map<String, String> properties = productPurchase.getProperties();
			%>

			<c:if test="<%= (properties != null) && properties.containsKey(ProductPurchaseConstants.PROPERTY_SIZING) %>">
				<%= properties.get(ProductPurchaseConstants.PROPERTY_SIZING) %>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="quantity"
			value="<%= String.valueOf(productPurchase.getQuantity()) %>"
		/>

		<c:if test="<%= accountEntryViewDisplayContext.isLiferayContractorOrg() || accountEntryViewDisplayContext.isLiferayIncOrg() || accountEntryViewDisplayContext.isPartnerManagedSupportWorker() %>">
			<liferay-ui:search-container-column-text
				name="status"
			>
				<span class="label label-sm label-<%= productPurchase.getStatus() %>"><%= productPurchase.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>