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
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingLocationId = ParamUtil.getLong(request, "trainingLocationId");

TrainingLocation trainingLocation = null;

try {
	trainingLocation = TrainingLocationLocalServiceUtil.getTrainingLocation(trainingLocationId);
}
catch (NoSuchTrainingLocationException nstle) {
}

Address address = null;

if (trainingLocation != null) {
	address = trainingLocation.getAddress();
}

long regionId = BeanParamUtil.getLong(address, request, "regionId");
long countryId = BeanParamUtil.getLong(address, request, "countryId");
%>

<portlet:actionURL name="updateTrainingLocation" var="updateTrainingLocationURL">
	<portlet:param name="mvcPath" value="/admin/edit_training_location.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingLocationURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="trainingLocationId" type="hidden" value="<%= trainingLocationId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="location"
	/>

	<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
	<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
	<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-zip" />
	<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
	<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />
	<liferay-ui:error exception="<%= TrainingLocationNameException.class %>" message="please-enter-a-valid-name" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<aui:input bean="<%= trainingLocation %>" label="" model="<%= TrainingLocation.class %>" name="name" />
		</td>
	</tr>
	</table>

	<br />

	<table class="lfr-table">
	<tr>
		<td valign="top">
			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="street1" />
				</td>
				<td>
					<aui:input cssClass="address" label="" name="street1" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet1()) %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="street2" />
				</td>
				<td>
					<aui:input cssClass="address" label="" name="street2" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet2()) %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="street3" />
				</td>
				<td>
					<aui:input cssClass="address" label="" name="street3" type="text" value="<%= (address == null ? StringPool.BLANK : address.getStreet3()) %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="city" />
				</td>
				<td>
					<aui:input cssClass="address" label="" name="city" type="text" value="<%= (address == null ? StringPool.BLANK : address.getCity()) %>" />
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="zip" />
				</td>
				<td>
					<aui:input cssClass="address" label="" name="zip" type="text" value="<%= (address == null ? StringPool.BLANK : address.getZip()) %>" />
				</td>
			</tr>
			</table>
		</td>
		<td valign="top">
			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="country" />
				</td>
				<td>
					<select id="<portlet:namespace />countryId" name="<portlet:namespace />countryId"></select>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="region" />
				</td>
				<td>
					<select id="<portlet:namespace />regionId" name="<portlet:namespace />regionId"></select>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<aui:script use="liferay-dynamic-select">
	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</c:if>

	new Liferay.DynamicSelect(
		[
			{
				select: "<portlet:namespace />countryId",
				selectData: Liferay.Address.getCountries,
				selectDesc: 'name',
				selectId: 'countryId',
				selectVal: "<%= countryId %>"
			},
			{
				select: "<portlet:namespace />regionId",
				selectData:Liferay.Address.getRegions,
				selectDesc: "name",
				selectId: "regionId",
				selectVal: "<%= regionId %>"
			}
		]
	);
</aui:script>