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
String redirect = ParamUtil.getString(request, "redirect", currentURL);
%>

<liferay-portlet:actionURL name="updateStoreCountry" var="updateStoreCountryURL" />

<aui:form action="<%= updateStoreCountryURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset label="select-your-country">
		<span class="aui-field-help">
			<liferay-ui:message key="select-the-country-where-the-app-will-be-downloaded" />
		</span>

		<aui:select label="" name="storeCountryId">

			<%
			List<Country> countries = CountryServiceUtil.getCountries(true);

			for (Country country : countries) {
				if (ArrayUtil.contains(PortletPropsValues.MARKETPLACE_RESTRICTED_COUNTRIES, country.getA2())) {
					continue;
				}
			%>

				<aui:option label="<%= country.getName() %>" selected="<%= country.getCountryId() == storeCountry.getCountryId() %>" value="<%= country.getCountryId() %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>

	<aui:button label="save" type="submit" />
</aui:form>