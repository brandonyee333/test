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

<c:if test="<%= layout != null %>">
	<nav class="tab-nav">
		<ul>

			<%
			if (!layout.isRootLayout()) {
				List<Layout> siblingLayouts = LayoutLocalServiceUtil.getLayouts(layout.getGroupId(), layout.isPrivateLayout(), layout.getParentLayoutId());

				for (Layout siblingLayout : siblingLayouts) {
					if (!LayoutPermissionUtil.contains(themeDisplay.getPermissionChecker(), siblingLayout, ActionKeys.VIEW)) {
						continue;
					}
			%>

					<li class="<%= (siblingLayout.getLayoutId() == layout.getLayoutId()) ? "selected" : "" %>">
						<a href="<%= PortalUtil.getLayoutURL(siblingLayout, themeDisplay) %>" <%= PortalUtil.getLayoutTarget(siblingLayout) %>><%= siblingLayout.getName(themeDisplay.getLocale()) %></a>
					</li>

			<%
				}
			}
			%>

		</ul>
	</nav>
</c:if>