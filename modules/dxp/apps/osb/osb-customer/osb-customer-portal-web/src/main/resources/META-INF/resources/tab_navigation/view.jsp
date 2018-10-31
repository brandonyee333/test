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