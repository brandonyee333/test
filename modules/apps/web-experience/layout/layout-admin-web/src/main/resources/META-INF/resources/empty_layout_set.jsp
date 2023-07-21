<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
renderResponse.setTitle(layoutsAdminDisplayContext.getRootNodeName());
%>

<div class="container-fluid-1280 text-center">
	<liferay-ui:empty-result-message
		message='<%= layoutsAdminDisplayContext.isPrivateLayout() ? "there-are-no-private-pages" : "there-are-no-public-pages" %>'
	>
		<p class="text-center text-muted">
			<liferay-ui:message key='<%= layoutsAdminDisplayContext.isPrivateLayout() ? "private-pages-help" : "public-pages-help" %>' />
		</p>
	</liferay-ui:empty-result-message>

	<c:if test="<%= layoutsAdminDisplayContext.isShowAddRootLayoutButton() %>">

		<%
		PortletURL addLayoutURL = layoutsAdminDisplayContext.getAddLayoutURL(LayoutConstants.DEFAULT_PLID, layoutsAdminDisplayContext.isPrivateLayout());
		%>

		<liferay-frontend:add-menu>
			<liferay-frontend:add-menu-item
				title='<%= LanguageUtil.get(request, layoutsAdminDisplayContext.isPrivateLayout() ? "add-private-page" : "add-public-page") %>'
				url="<%= addLayoutURL.toString() %>"
			/>
		</liferay-frontend:add-menu>
	</c:if>
</div>