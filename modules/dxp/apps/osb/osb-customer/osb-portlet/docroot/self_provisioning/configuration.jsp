<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long[] digitalEnterpriseProductMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "digitalEnterprise_productMinorVersions"), 0L);
long[] portalProductMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "portal_productMinorVersions"), 0L);
%>

<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, OSBPortletKeys.OSB_SELF_PROVISIONING, OSBActionKeys.CONFIGURATION) %>">
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
						<%= ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE %>
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

			<aui:button onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" value="cancel" />
		</div>
	</aui:form>
</c:if>