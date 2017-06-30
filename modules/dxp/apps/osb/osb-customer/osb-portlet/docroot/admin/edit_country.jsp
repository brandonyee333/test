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

long countryId = ParamUtil.getLong(request, "countryId");

Country country = null;

try {
	country = OSBCountryLocalServiceUtil.getCountry(countryId);
}
catch (NoSuchCountryException nsce) {
}

boolean active = BeanParamUtil.getBoolean(country, request, "active", true);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_country.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("countryId", String.valueOf(countryId));

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<portlet:actionURL name="updateCountry" var="updateCountryURL">
	<portlet:param name="mvcPath" value="/admin/edit_country.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateCountryURL %>" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />countryId" type="hidden" value="<%= countryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="country"
	/>

	<liferay-ui:error exception="<%= CountryA2Exception.class %>" message="please-enter-a-valid-country-a2-code" />
	<liferay-ui:error exception="<%= CountryA3Exception.class %>" message="please-enter-a-valid-country-a3-code" />
	<liferay-ui:error exception="<%= CountryIddException.class %>" message="please-enter-a-valid-country-idd" />
	<liferay-ui:error exception="<%= CountryNameException.class %>" message="please-enter-a-valid-country-name" />
	<liferay-ui:error exception="<%= CountryNumberException.class %>" message="please-enter-a-valid-country-number" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= country %>" field="name" model="<%= Country.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="a2" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= country %>" field="a2" model="<%= Country.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="a3" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= country %>" field="a3" model="<%= Country.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="number" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= country %>" field="number" model="<%= Country.class %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="idd" />
		</td>
		<td>
			<liferay-ui:input-field bean="<%= country %>" field="idd" model="<%= Country.class %>" />
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

	<c:if test="<%= country != null %>">
		<liferay-portlet:renderURL varImpl="addRegionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/edit_region.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="countryId" value="<%= String.valueOf(country.getCountryId()) %>" />
		</liferay-portlet:renderURL>

		<input onClick="location.href = '<%= addRegionURL %>';" type="button" value="<liferay-ui:message key="add-region" />" />
	</c:if>

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<br />

<liferay-ui:tabs
	names="regions"
	url="<%= portletURL.toString() %>"
/>

<c:if test="<%= country != null %>">
	<liferay-ui:search-container
		headerNames="country,region-code,name,status"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results
			results="<%= OSBRegionLocalServiceUtil.getRegions(country.getCountryId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
			total="<%= OSBRegionLocalServiceUtil.getRegionsCount(country.getCountryId()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Region"
			escapedModel="<%= true %>"
			keyProperty="regionId"
			modelVar="region"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_region.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="regionId" value="<%= String.valueOf(region.getRegionId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= region.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region-code"
				value="<%= region.getRegionCode() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
				value='<%= region.isActive() ? LanguageUtil.get(pageContext, "active") : LanguageUtil.get(pageContext, "inactive") %>'
			/>

			<liferay-ui:search-container-column-jsp
				path="/admin/region_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<br />

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</c:if>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>