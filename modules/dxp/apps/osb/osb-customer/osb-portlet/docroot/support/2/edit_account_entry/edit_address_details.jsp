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

<%@ include file="/support/2/init.jsp" %>

<%
//TODO no direct replacement for setIncludeServiceJs
//themeDisplay.setIncludeServiceJs(true);

AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry_dialog.jsp-accountEntry");

Address address = accountEntry.getAddress();

long addressId = BeanPropertiesUtil.getLong(address, "addressId");
long countryId = BeanParamUtil.getLong(address, request, "countryId");
long regionId = BeanParamUtil.getLong(address, request, "regionId");
%>

<div class="hide tab-content-tab" id="<portlet:namespace />addressDetails">
	<aui:input name="addressId" type="hidden" value="<%= addressId %>" />

	<div class="field-group">
		<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-address-line") %>">
			<span class="inline long-field">
				<aui:input bean="<%= address %>" data-field-required-status="<%= false %>" field="street1" label="address-line" model="<%= Address.class %>" name="street1" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<aui:select bean="<%= address %>" field="countryId" label="country" name="countryId" />
	</div>

	<div class="field-group">
		<aui:select bean="<%= address %>" field="regionId" label="state-province" name="regionId" />
	</div>

	<div class="field-group">
		<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-city") %>">
			<span class="inline long-field">
				<aui:input bean="<%= address %>" data-field-required-status="<%= false %>" field="city" label="city" model="<%= Address.class %>" name="city" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<div class="field-align" field-required-message="<%= LanguageUtil.get(request, "please-enter-a-valid-postal-code") %>">
			<span class="inline long-field">
				<aui:input bean="<%= address %>" data-field-required-status="<%= false %>" field="zip" label="postal-code" model="<%= Address.class %>" name="zip" type="text" />
			</span>
		</div>
	</div>
</div>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />countryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'name',
				selectId: 'countryId',
				selectVal: '<%= countryId %>'
			},
			{
				select: '<portlet:namespace />regionId',
				selectData: Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '<%= regionId %>'
			}
		]
	);
</aui:script>