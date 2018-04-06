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
String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_offering_definition.jsp");
portletURL.setParameter("callback", callback);
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<liferay-ui:tabs
		names="offerings"
	/>

	<liferay-ui:search-container
		searchContainer="<%= new OfferingDefinitionSearch(renderRequest, portletURL) %>"
	>

		<%
		OfferingDefinitionDisplayTerms displayTerms = (OfferingDefinitionDisplayTerms)searchContainer.getDisplayTerms();
		OfferingDefinitionSearchTerms searchTerms = (OfferingDefinitionSearchTerms)searchContainer.getSearchTerms();

		pageContext.setAttribute("total", OfferingDefinitionLocalServiceUtil.getOfferingDefinitionsCount(searchTerms.getProductEntryIds(), searchTerms.getSupportResponseIds()));
		%>

		<div>
			<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="product" />
					</td>
					<td>
						<liferay-ui:message key="service-level-agreement" />
					</td>
				</tr>
				<tr>
					<td>
						<aui:select label="" name="<%= displayTerms.PRODUCT_ENTRY_IDS %>">
							<option value=""></option>

							<%
							List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

							for (ProductEntry productEntry : productEntries) {
							%>

								<option <%= ArrayUtil.contains(displayTerms.getProductEntryIds(), productEntry.getProductEntryId()) ? "selected" : "" %> value="<%= productEntry.getProductEntryId() %>"><%= HtmlUtil.escape(productEntry.getName()) %></option>

							<%
							}
							%>

						</aui:select>
					</td>
					<td>
						<aui:select label="" name="<%= displayTerms.SUPPORT_RESPONSE_IDS %>">
							<option value=""></option>

							<%
							List<SupportResponse> supportResponses = SupportResponseLocalServiceUtil.getSupportResponses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

							for (SupportResponse supportResponse : supportResponses) {
							%>

								<option <%= ArrayUtil.contains(displayTerms.getSupportResponseIds(), supportResponse.getSupportResponseId()) ? "selected" : "" %> value="<%= supportResponse.getSupportResponseId() %>"><%= HtmlUtil.escape(supportResponse.getName()) %></option>

							<%
							}
							%>

						</aui:select>
					</td>
				</tr>
			</table>

			<aui:button type="submit" value="search" />
		</div>

		<br />

		<liferay-ui:search-container-results
			results="<%= OfferingDefinitionLocalServiceUtil.getOfferingDefinitions(searchTerms.getProductEntryIds(), searchTerms.getSupportResponseIds(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.OfferingDefinition"
			escapedModel="<%= true %>"
			keyProperty="offeringDefinitionId"
			modelVar="offeringDefinition"
		>

			<%
			ProductEntry productEntry = offeringDefinition.getProductEntry();
			SupportResponse supportResponse = offeringDefinition.getSupportResponse();

			StringBuilder sb = new StringBuilder();

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append(callback);
			sb.append("('");
			sb.append(offeringDefinition.getOfferingDefinitionId());
			sb.append("', ['");
			sb.append(productEntry.getProductEntryId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(productEntry.getName()));
			sb.append("', '");
			sb.append(supportResponse.getSupportResponseId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(supportResponse.getName()));
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(offeringDefinition.getProductDescription()));
			sb.append("', '");
			sb.append(offeringDefinition.isLicenses());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(offeringDefinition.getLicensesLabel()));
			sb.append("', '");
			sb.append(offeringDefinition.isUnlimitedLicenses());
			sb.append("', '");
			sb.append(offeringDefinition.isSupportTickets());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(offeringDefinition.getSupportTicketsLabel()));
			sb.append("', '");
			sb.append(offeringDefinition.getQuantity());
			sb.append("']); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="product"
			>
				<%= HtmlUtil.escape(productEntry.getName()) %>

				<c:if test="<%= Validator.isNotNull(offeringDefinition.getProductDescription()) %>">
					- <%= HtmlUtil.escape(offeringDefinition.getProductDescription()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="sla"
				property="supportResponse.name"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="licenses"
				property="licensesLabel"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="tickets"
				property="supportTicketsLabel"
				translate="<%= true %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>