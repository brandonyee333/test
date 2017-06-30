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

long currencyEntryId = ParamUtil.getLong(request, "currencyEntryId");

CurrencyEntry currencyEntry = null;

try {
	currencyEntry = CurrencyEntryLocalServiceUtil.getCurrencyEntry(currencyEntryId);
}
catch (NoSuchCurrencyEntryException nscee) {
}

long countryId = BeanParamUtil.getLong(currencyEntry, request, "countryId");
%>

<portlet:actionURL name="updateCurrencyEntry" var="updateCurrencyEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_currency_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateCurrencyEntryURL %>" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />currencyEntryId" type="hidden" value="<%= currencyEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="currency"
	/>

	<liferay-ui:error exception="<%= CurrencyEntryCodeException.class %>" message="please-enter-a-valid-code" />
	<liferay-ui:error exception="<%= CurrencyEntryCountryException.class %>" message="please-enter-a-valid-country" />
	<liferay-ui:error exception="<%= DuplicateCurrencyEntryException.class %>" message="please-enter-a-unique-code" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="country" />
		</td>
		<td>

			<%
			List<Country> countries = CountryServiceUtil.getCountries();
			%>

			<select name="<portlet:namespace />countryId">
				<option></option>

				<%
				for (Country country : countries) {
				%>

					<option <%= (country.getCountryId() == countryId) ? "selected" : "" %> value="<%= country.getCountryId() %>"><%= country.getName() %></option>

				<%
				}
				%>

			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="code" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= currencyEntry %>" field="currencyCode" model="<%= CurrencyEntry.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="marketplace-enabled" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= currencyEntry %>" field="marketplaceEnabled" model="<%= CurrencyEntry.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="marketplace-minimum-price" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= currencyEntry %>" field="marketplaceMinPrice" model="<%= CurrencyEntry.class %>" />
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />code);
	</script>
</c:if>