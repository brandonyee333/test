<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:success key='<%= portletDisplay.getPortletName() + "layoutAdded" %>' message='<%= LanguageUtil.get(resourceBundle, "the-page-was-created-succesfully") %>' targetNode="#controlMenuAlertsContainer" />

<liferay-site-navigation:navigation
	ddmTemplateGroupId="<%= siteNavigationMenuDisplayContext.getDisplayStyleGroupId() %>"
	ddmTemplateKey="<%= siteNavigationMenuDisplayContext.getDDMTemplateKey() %>"
	displayDepth="<%= siteNavigationMenuDisplayContext.getDisplayDepth() %>"
	includedLayouts="<%= siteNavigationMenuDisplayContext.getIncludedLayouts() %>"
	preview="<%= siteNavigationMenuDisplayContext.isPreview() %>"
	rootLayoutLevel="<%= siteNavigationMenuDisplayContext.getRootLayoutLevel() %>"
	rootLayoutType="<%= siteNavigationMenuDisplayContext.getRootLayoutType() %>"
	rootLayoutUuid="<%= siteNavigationMenuDisplayContext.getRootLayoutUuid() %>"
/>