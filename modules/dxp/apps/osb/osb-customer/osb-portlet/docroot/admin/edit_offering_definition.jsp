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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long offeringDefinitionId = ParamUtil.getLong(request, "offeringDefinitionId");

OfferingDefinition offeringDefinition = OfferingDefinitionLocalServiceUtil.fetchOfferingDefinition(offeringDefinitionId);

long productEntryId = BeanParamUtil.getLong(offeringDefinition, request, "productEntryId");
long supportResponseId = BeanParamUtil.getLong(offeringDefinition, request, "supportResponseId");
String productDescription = BeanParamUtil.getString(offeringDefinition, request, "productDescription");
boolean licenses = BeanParamUtil.getBoolean(offeringDefinition, request, "licenses");
boolean unlimitedLicenses = BeanParamUtil.getBoolean(offeringDefinition, request, "unlimitedLicenses");
boolean supportTickets = BeanParamUtil.getBoolean(offeringDefinition, request, "supportTickets");
%>

<script type="text/javascript">
	function <portlet:namespace />disableInput(field, checked) {
		eval("document.<portlet:namespace />fm.<portlet:namespace />" + field + ".disabled = " + checked + ";");
	}
</script>

<portlet:actionURL name="updateOfferingDefinition" var="updateOfferingDefinitionURL">
	<portlet:param name="mvcPath" value="/admin/edit_offering_definition.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateOfferingDefinitionURL %>" method="post">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />offeringDefinitionId" type="hidden" value="<%= offeringDefinitionId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="offering"
	/>

	<liferay-ui:error exception="<%= DuplicateOfferingDefinitionException.class %>" message="the-offering-already-exists" />
	<liferay-ui:error exception="<%= RequiredOfferingDefinitionException.class %>" message="you-cannot-modify-this-offering" />

	<table class="lfr-table">
		<c:if test="<%= offeringDefinition != null %>">
			<tr>
				<td>
					<liferay-ui:message key="created-by" />
				</td>
				<td>
					<%= HtmlUtil.escape(PortalUtil.getUserName(offeringDefinition.getUserId(), offeringDefinition.getUserName())) %>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="last-modified" />
				</td>
				<td>
					<%= longDateFormatDateTime.format(offeringDefinition.getModifiedDate()) %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="product" />
			</td>
			<td>
				<aui:select label="" name="productEntryId">

					<%
					List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ProductEntry productEntry : productEntries) {
					%>

						<option <%= (productEntryId == productEntry.getProductEntryId()) ? "selected" : "" %> value="<%= productEntry.getProductEntryId() %>"><%= HtmlUtil.escape(productEntry.getName()) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="product-description" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= offeringDefinition %>"
					field="productDescription"
					model="<%= OfferingDefinition.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="licenses" />
			</td>
			<td>

				<%
				String taglibLicensesOnClick = renderResponse.getNamespace() + "disableInput('unlimitedLicenses', !this.checked);";
				%>

				<liferay-ui:input-checkbox
					defaultValue="<%= licenses %>"
					onClick="<%= taglibLicensesOnClick %>"
					param="licenses"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="unlimited-licenses" />
			</td>
			<td>
				<liferay-ui:input-checkbox
					defaultValue="<%= unlimitedLicenses %>"
					disabled="<%= !licenses %>"
					param="unlimitedLicenses"
				/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="sla" />
			</td>
			<td>
				<aui:select label="" name="supportResponseId">

					<%
					List<SupportResponse> supportResponses = SupportResponseLocalServiceUtil.getSupportResponses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportResponse supportResponse : supportResponses) {
					%>

						<option <%= (supportResponseId == supportResponse.getSupportResponseId()) ? "selected" : "" %> value="<%= supportResponse.getSupportResponseId() %>"><%= HtmlUtil.escape(supportResponse.getName()) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="support-tickets" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= offeringDefinition %>"
					field="supportTickets"
					model="<%= OfferingDefinition.class %>"
				/>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
</aui:form>