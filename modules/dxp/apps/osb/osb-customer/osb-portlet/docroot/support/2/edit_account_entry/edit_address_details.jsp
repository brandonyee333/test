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
themeDisplay.setIncludeServiceJs(true);

AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry_dialog.jsp-accountEntry");

Address address = accountEntry.getAddress();

long addressId = BeanPropertiesUtil.getLong(address, "addressId");
long countryId = BeanParamUtil.getLong(address, request, "countryId");
long regionId = BeanParamUtil.getLong(address, request, "regionId");
%>

<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />addressDetails">
	<aui:input name="addressId" type="hidden" value="<%= addressId %>" />

	<div class="field-group">
		<label id="<portlet:namespace />street1Label"><liferay-ui:message key="address-line" /></label>

		<div class="field-align" field-required-message='<%= LanguageUtil.get(pageContext,"please-enter-a-valid-address-line") %>'>
			<span class="long-field inline">
				<aui:input bean="<%= address %>" data-field-required-status="<%= false %>" field="street1" label="" model="<%= Address.class %>" name="street1" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />countryIdLabel"><liferay-ui:message key="country" /></label>

		<aui:select bean="<%= address %>" field="countryId" label="" name="countryId" />
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />regionIdLabel"><liferay-ui:message key="state-province" /></label>

		<aui:select bean="<%= address %>" field="regionId" label="" name="regionId" />
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />cityLabel"><liferay-ui:message key="city" /></label>

		<div class="field-align" field-required-message='<%= LanguageUtil.get(pageContext,"please-enter-a-valid-city") %>'>
			<span class="long-field inline">
				<aui:input bean="<%= address %>" data-field-required-status="<%= false %>" field="city" label="" model="<%= Address.class %>" name="city" type="text" />
			</span>
		</div>
	</div>

	<div class="field-group">
		<label id="<portlet:namespace />zipLabel"><liferay-ui:message key="postal-code" /></label>

		<div class="field-align" field-required-message='<%= LanguageUtil.get(pageContext,"please-enter-a-valid-postal-code") %>'>
			<span class="long-field inline">
				<aui:input bean="<%= address %>" data-field-required-status="<%= false %>" field="zip" label="" model="<%= Address.class %>" name="zip" type="text" />
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