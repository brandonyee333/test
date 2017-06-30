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

<%@ include file="/marketplace_navigation/init.jsp" %>

<%
PortletURL portletURL = null;

if (MarketplaceUtil.isMarketplaceServer(request)) {
	portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("remoteMVCPath", "/marketplace/view.jsp");
}
else {
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

	portletURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, marketplacePlid, PortletRequest.RENDER_PHASE);

	portletURL.setParameter("mvcPath", "/marketplace/view.jsp");
}
%>

<div class="marketplace-navigation view">
	<div class="banner-content">
		<a class="logo-marketplace" href="<%= portletURL %>" title="<liferay-ui:message key="liferay-marketplace" />"></a>

		<div class="class-toggle menu-navigation responsive-only" data-target-node=".navigation-container"></div>

		<div class="navigation-container">
			<%@ include file="/marketplace_navigation/view_navigation.jspf" %>

			<c:if test="<%= !MarketplaceUtil.isMarketplaceServer(request) %>">
				<%@ include file="/marketplace_navigation/view_menu_navigation.jspf" %>
			</c:if>
		</div>
	</div>
</div>