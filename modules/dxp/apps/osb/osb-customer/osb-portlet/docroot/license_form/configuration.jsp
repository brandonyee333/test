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

String orderUuid = PrefsParamUtil.getString(portletPreferences, request, "orderUuid");
int productVersion = PrefsParamUtil.getInteger(portletPreferences, request, "productVersion");
%>

<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, OSBPortletKeys.OSB_LICENSE_FORM, OSBActionKeys.CONFIGURATION) %>">
	<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= redirect %>" />
	</liferay-portlet:renderURL>

	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

	<aui:form action="<%= configurationURL %>">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<aui:input cssClass="lfr-input-text-container" label="order-uuid" name="orderUuid" value="<%= orderUuid %>" />

		<aui:select label="product-version" name="productVersion">
			<aui:option value=""></aui:option>

			<%
			List<ListType> productVersionTypes = ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS);

			String previousNamePrefix = StringPool.BLANK;

			for (ListType productVersionType : productVersionTypes) {
				if ((productVersionType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) || (productVersionType.getListTypeId() < ProductEntryConstants.PORTAL_VERSION_6_1_10)) {
					continue;
				}

				String name = productVersionType.getName();

				String namePrefix = name.substring(0, 3);
			%>

				<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
					<option disabled>--------</option>
				</c:if>

				<option <%= (productVersionType.getListTypeId() == productVersion) ? "selected" : "" %> value="<%= productVersionType.getListTypeId() %>"><%= LanguageUtil.get(request, productVersionType.getName()) %></option>

			<%
				previousNamePrefix = namePrefix;
			}
			%>

		</aui:select>

		<br />

		<aui:button type="submit" value="save" />

		<aui:button href="<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>" value="cancel" />
	</aui:form>
</c:if>