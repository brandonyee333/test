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
JSONObject jsonObject = (JSONObject)request.getAttribute("summary.jsp-jsonObject");

PortletURL portletURL = null;

long orderEntryId = jsonObject.getLong("orderEntryId");

if (orderEntryId > 0) {
	portletURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_ADMIN, layout.getPlid(), PortletRequest.RENDER_PHASE);

	portletURL.setParameter("mvcPath", "/admin/edit_order_entry.jsp");
	portletURL.setParameter("orderEntryId", String.valueOf(orderEntryId));
}
%>

<c:if test="<%= portletURL != null %>">
	<strong><liferay-ui:message key="order" />:</strong> <aui:a href="<%= portletURL.toString() %>" target="_blank" /> <%= orderEntryId %></a>

	<br />
</c:if>

<%
Date startDate = new Date(jsonObject.getLong("startDate"));
%>

<liferay-ui:message key="start-date" />: <%= longDateFormatDateTime.format(startDate) %>

<br /><br />

<table class="results-grid taglib-search-iterator">
	<tr class="results-header">
		<th>
			<liferay-ui:message key="product" />
		</th>
		<th>
			<liferay-ui:message key="licenses" />
		</th>
		<th>
			<liferay-ui:message key="license-lifetime" />
		</th>
		<th>
			<liferay-ui:message key="support-tickets" />
		</th>
		<th>
			<liferay-ui:message key="sla" />
		</th>
		<th>
			<liferay-ui:message key="support-lifetime" />
		</th>
		<th>
			<liferay-ui:message key="support-end-date" />
		</th>
		<th>
			<liferay-ui:message key="version" />
		</th>
		<th>
			<liferay-ui:message key="sizing" />
		</th>
		<th>
			<liferay-ui:message key="quantity" />
		</th>
	</tr>

	<%
	JSONArray jsonArray = jsonObject.getJSONArray("offeringEntries");

	for (int i = 0; i < jsonArray.length(); i++) {
		JSONObject offeringEntryJSONObject = jsonArray.getJSONObject(i);

		ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntry(offeringEntryJSONObject.getLong("productEntryId"));
		SupportResponse supportResponse = SupportResponseLocalServiceUtil.getSupportResponse(offeringEntryJSONObject.getLong("supportResponseId"));
	%>

		<tr class="results-row">
			<td>
				<%= HtmlUtil.escape(productEntry.getName()) %>

				<c:if test='<%= Validator.isNotNull(offeringEntryJSONObject.getString("productDescription")) %>'>
					- <%= offeringEntryJSONObject.getString("productDescription") %>
				</c:if>
			</td>
			<td>
				<%= LanguageUtil.get(request, offeringEntryJSONObject.getString("licenses")) %>
			</td>
			<td>

				<%
				long licenseLifetime = offeringEntryJSONObject.getLong("licenseLifetime");

				String licenseLifetimeLabel = OfferingDefinitionConstants.getLifetimeLabel(licenseLifetime);

				if (Validator.isNull(licenseLifetimeLabel)) {
					licenseLifetimeLabel = OfferingDefinitionConstants.getCustomLifetimeLabel(licenseLifetime);
				}
				%>

				<%= LanguageUtil.get(request, licenseLifetimeLabel) %>
			</td>
			<td>
				<%= LanguageUtil.get(request, offeringEntryJSONObject.getString("supportTickets")) %>
			</td>
			<td>
				<%= HtmlUtil.escape(supportResponse.getName()) %>
			</td>
			<td>

				<%
				long supportLifetime = offeringEntryJSONObject.getLong("supportLifetime");

				String supportLifetimeLabel = OfferingDefinitionConstants.getLifetimeLabel(supportLifetime);

				if (Validator.isNull(supportLifetimeLabel)) {
					supportLifetimeLabel = OfferingDefinitionConstants.getCustomLifetimeLabel(supportLifetime);
				}
				%>

				<%= LanguageUtil.get(request, supportLifetimeLabel) %>
			</td>
			<td>

				<%
				Date supportEndDate = new Date(offeringEntryJSONObject.getLong("supportEndDate"));
				%>

				<%= longDateFormatDateTime.format(supportEndDate) %>
			</td>
			<td>

				<%
				int version = offeringEntryJSONObject.getInt("version");
				%>

				<c:if test="<%= version > 0 %>">

					<%
					ListType listType = ListTypeServiceUtil.getListType(version);
					%>

					<%= LanguageUtil.get(request, listType.getName()) %>
				</c:if>
			</td>
			<td>
				<%= LanguageUtil.get(request, OfferingEntryConstants.getSizingLabel(offeringEntryJSONObject.getInt("sizing"))) %>
			</td>
			<td>
				<%= offeringEntryJSONObject.getString("quantity") %>
			</td>
		</tr>

	<%
	}
	%>

</table>