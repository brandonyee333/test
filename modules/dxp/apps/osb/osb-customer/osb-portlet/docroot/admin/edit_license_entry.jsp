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

long licenseEntryId = ParamUtil.getLong(request, "licenseEntryId");

LicenseEntry licenseEntry = null;

try {
	licenseEntry = LicenseEntryLocalServiceUtil.getLicenseEntry(licenseEntryId);
}
catch (NoSuchLicenseEntryException nslee) {
}

long productEntryId = BeanParamUtil.getLong(licenseEntry, request, "productEntryId");
String type = BeanParamUtil.getString(licenseEntry, request, "type");
int portalVersionMin = BeanParamUtil.getInteger(licenseEntry, request, "portalVersionMin");
int portalVersionMax = BeanParamUtil.getInteger(licenseEntry, request, "portalVersionMax");
%>

<portlet:actionURL name="updateLicenseEntry" var="updateLicenseEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_license_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateLicenseEntryURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="licenseEntryId" type="hidden" value="<%= licenseEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="license"
	/>

	<liferay-ui:error exception="<%= LicenseEntryNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= LicenseEntryPortalVersionException.class %>" message="please-enter-a-valid-portal-version-range" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= licenseEntry %>"
					field="name"
					model="<%= LicenseEntry.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="product" />
			</td>
			<td>
				<aui:select label="" name="productEntryId">
					<aui:option value="" />

					<%
					List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ProductEntry productEntry : productEntries) {
					%>

						<aui:option label="<%= productEntry.getName() %>" selected="<%= productEntry.getProductEntryId() == productEntryId %>" value="<%= productEntry.getProductEntryId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="type" />
			</td>
			<td>
				<aui:select label="" name="type">

					<%
					for (String curType : LicenseEntryConstants.TYPES) {
					%>

						<aui:option label="<%= curType %>" selected="<%= type.equals(curType) %>" value="<%= curType %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="portal-version-range-minimum" />
			</td>
			<td>

				<%
				List<ListType> portalVersionTypes = ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS);
				%>

				<aui:select label="" name="portalVersionMin">

					<%
					String previousNamePrefix = StringPool.BLANK;

					for (ListType portalVersionType : portalVersionTypes) {
						if (portalVersionType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) {
							continue;
						}

						String name = portalVersionType.getName();

						String namePrefix = name.substring(0, 3);
					%>

						<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
							<aui:option disabled="<% true %>" label="--------" />
						</c:if>

						<aui:option label="<%= portalVersionType.getName() %>" selected="<%= portalVersionType.getListTypeId() == portalVersionMin %>" value="<%= portalVersionType.getListTypeId() %>" />

					<%
						previousNamePrefix = namePrefix;
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="portal-version-range-maximum" />
			</td>
			<td>
				<aui:select label="" name="portalVersionMax">

					<%
					ListType lastVersionType = portalVersionTypes.get(portalVersionTypes.size() - 2);

					for (ListType portalVersionType : portalVersionTypes) {
					%>

						<aui:option label="<%= (portalVersionType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) ? (lastVersionType.getName() + StringPool.PLUS) : portalVersionType.getName() %>" selected="<%= portalVersionType.getListTypeId() == portalVersionMax %>" value="<%= portalVersionType.getListTypeId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>