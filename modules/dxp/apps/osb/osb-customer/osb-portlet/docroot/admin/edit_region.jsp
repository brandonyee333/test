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

long regionId = ParamUtil.getLong(request, "regionId");

Region region = null;

try {
	region = OSBRegionLocalServiceUtil.getRegion(regionId);
}
catch (NoSuchRegionException nsce) {
}

long countryId = BeanParamUtil.getLong(region, request, "countryId");
boolean active = BeanParamUtil.getBoolean(region, request, "active", true);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_region.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("countryId", String.valueOf(countryId));
portletURL.setParameter("regionId", String.valueOf(regionId));
%>

<portlet:actionURL name="updateRegion" var="updateRegionURL">
	<portlet:param name="mvcPath" value="/admin/edit_region.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateRegionURL %>" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />countryId" type="hidden" value="<%= countryId %>" />
	<input name="<portlet:namespace />regionId" type="hidden" value="<%= regionId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="region"
	/>

	<liferay-ui:error exception="<%= RegionCodeException.class %>" message="please-enter-a-valid-region-code" />
	<liferay-ui:error exception="<%= RegionNameException.class %>" message="please-enter-a-valid-region-name" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="country" />
		</td>
		<td>

			<%
			Country country = OSBCountryLocalServiceUtil.getCountry(countryId);
			%>

			<%= HtmlUtil.escape(country.getName()) %>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= region %>" field="name" model="<%= Region.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="region-code" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= region %>" field="regionCode" model="<%= Region.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="status" />
		</td>
		<td>
			<select name="<portlet:namespace/>active">
				<option <%= active ? "selected" : "" %> value="<%= true %>"><liferay-ui:message key="active" /></option>
				<option <%= !active ? "selected" : "" %> value="<%= false %>"><liferay-ui:message key="inactive" /></option>
			</select>
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>