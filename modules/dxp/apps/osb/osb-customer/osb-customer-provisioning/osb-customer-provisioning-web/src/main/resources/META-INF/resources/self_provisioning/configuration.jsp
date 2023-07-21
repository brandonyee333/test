<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long[] digitalEnterpriseProductMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "digitalEnterprise_productMinorVersions"), 0L);
long[] portalProductMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "portal_productMinorVersions"), 0L);
%>

<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, CustomerProvisioningPortletKeys.SELF_PROVISIONING, OSBActionKeys.CONFIGURATION) %>">
	<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= redirect %>" />
	</liferay-portlet:renderURL>

	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<div class="container-fluid-1280">
			<aui:row>
				<aui:col width="<%= 50 %>">
					<h2>
						<%= ProductEntryConstants.ROOT_NAME_DXP %>
					</h2>

					<aui:select label="product-versions" multiple="<%= true %>" name="digitalEnterprise_productMinorVersions">

						<%
						for (ListType digitalEnterpriseMinorVersionType : ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_DIGITAL_ENTERPRISE_MINOR_VERSIONS)) {
						%>

							<aui:option label="<%= digitalEnterpriseMinorVersionType.getName() %>" selected="<%= ArrayUtil.contains(digitalEnterpriseProductMinorVersions, digitalEnterpriseMinorVersionType.getListTypeId()) %>" value="<%= digitalEnterpriseMinorVersionType.getListTypeId() %>" />

						<%
						}
						%>

					</aui:select>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<h2>
						<%= ProductEntryConstants.ROOT_NAME_PORTAL %>
					</h2>

					<aui:select label="product-versions" multiple="<%= true %>" name="portal_productMinorVersions">

						<%
						for (ListType portalMinorVersionType : ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS)) {
						%>

							<aui:option label="<%= portalMinorVersionType.getName() %>" selected="<%= ArrayUtil.contains(portalProductMinorVersions, portalMinorVersionType.getListTypeId()) %>" value="<%= portalMinorVersionType.getListTypeId() %>" />

						<%
						}
						%>

					</aui:select>
				</aui:col>
			</aui:row>

			<aui:button type="submit" value="save" />

			<aui:button onClick='<%= "location.href = '" + HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) + "';" %>' value="cancel" />
		</div>
	</aui:form>
</c:if>