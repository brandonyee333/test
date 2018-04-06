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
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />licenseEntryId" type="hidden" value="<%= licenseEntryId %>" />

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
					<option value=""></option>

					<%
					List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ProductEntry productEntry : productEntries) {
					%>

						<option <%= (productEntry.getProductEntryId() == productEntryId) ? "selected" : "" %> value="<%= productEntry.getProductEntryId() %>"><%= HtmlUtil.escape(productEntry.getName()) %></option>

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

						<option <%= type.equals(curType) ? "selected" : "" %> value="<%= curType %>"><liferay-ui:message key="<%= curType %>" /></option>

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
							<option disabled>--------</option>
						</c:if>

						<option <%= (portalVersionType.getListTypeId() == portalVersionMin) ? "selected" : "" %> value="<%= portalVersionType.getListTypeId() %>"><%= LanguageUtil.get(request, portalVersionType.getName()) %></option>

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

						<option <%= (portalVersionType.getListTypeId() == portalVersionMax) ? "selected" : "" %> value="<%= portalVersionType.getListTypeId() %>"><%= (portalVersionType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) ? LanguageUtil.get(request, lastVersionType.getName()) + StringPool.PLUS : LanguageUtil.get(request, portalVersionType.getName()) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
	</table>

	<br />

	<aui:button type="submit" value="save" />

	<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>