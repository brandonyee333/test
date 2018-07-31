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

String productEntryRootName = PrefsParamUtil.getString(portletPreferences, request, "productEntryRootName");
int[] productMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "productMinorVersions"), 0);
%>

<c:if test="<%= PortletPermissionUtil.contains(permissionChecker, plid, OSBPortletKeys.OSB_SELF_PROVISIONING, OSBActionKeys.CONFIGURATION) %>">
	<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= redirect %>" />
	</liferay-portlet:renderURL>

	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<aui:select label="product" name="productEntryRootName" onChange='<%= renderResponse.getNamespace() + "selectProductEntryRootName(this.value);" %>'>
			<aui:option value=""></aui:option>
			<aui:option label="<%= ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE %>" selected="<%= productEntryRootName.equals(ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE) %>" value="<%= ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE %>" />
			<aui:option label="<%= ProductEntryConstants.ROOT_NAME_PORTAL %>" selected="<%= productEntryRootName.equals(ProductEntryConstants.ROOT_NAME_PORTAL) %>" value="<%= ProductEntryConstants.ROOT_NAME_PORTAL %>" />
		</aui:select>

		<aui:select label="product-versions" multiple="<%= true %>" name="productMinorVersions">
		</aui:select>

		<br />

		<aui:button type="submit" value="save" />

		<aui:button onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" value="cancel" />
	</aui:form>
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectProductEntryRootName',
		function(productEntryRootName) {
			var A = AUI();

			var productMinorVersionsSelect = A.one('#<portlet:namespace />productMinorVersions');

			productMinorVersionsSelect.empty();

			var productMinorVersionsOptions = [];

			if (productEntryRootName == '<%= ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE %>') {

				<%
				for (ListType digitalEnterpriseMinorVersionType : ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_DIGITAL_ENTERPRISE_MINOR_VERSIONS)) {
				%>

					productMinorVersionsOptions.push('<option value="<%= digitalEnterpriseMinorVersionType.getListTypeId() %>"><%= LanguageUtil.get(request, digitalEnterpriseMinorVersionType.getName()) %></option>');

				<%
				}
				%>

			}
			else {

				<%
				for (ListType portalMinorVersionType : ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS)) {
				%>

					productMinorVersionsOptions.push('<option value="<%= portalMinorVersionType.getListTypeId() %>"><%= LanguageUtil.get(request, portalMinorVersionType.getName()) %></option>');

				<%
				}
				%>

			}

			productMinorVersionsSelect.append(productMinorVersionsOptions.join(''));

			var productMinorVersions = [<%= StringUtil.merge(productMinorVersions) %>];

			productMinorVersionsSelect.all('option').each(
				function(item, index, collection) {
					if (productMinorVersions.indexOf(parseInt(item.val())) < 0) {
						item.attr('selected', false);
					}
					else {
						item.attr('selected', true);
					}
				}
			);
		},
		['aui-base']
	);

	<c:if test="<%= Validator.isNotNull(productEntryRootName) %>">
		<portlet:namespace />selectProductEntryRootName('<%= HtmlUtil.escapeJS(productEntryRootName) %>');
	</c:if>
</aui:script>