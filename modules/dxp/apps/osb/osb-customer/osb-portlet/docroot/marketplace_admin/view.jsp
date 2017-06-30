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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<c:choose>
	<c:when test="<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID) %>">
		<liferay-ui:tabs
			names="dashboard,apps,contracts,customers,developers,lists,metrics,portal-releases,sales"
			param="tabs1"
			url="<%= portletURL.toString() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("apps") %>'>
				<%@ include file="/marketplace_admin/app_entries.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("contracts") %>'>
				<%@ include file="/marketplace_admin/contract_entries.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("customers") %>'>
				<%@ include file="/marketplace_admin/asset_receipts.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("developers") %>'>
				<%@ include file="/marketplace_admin/developer_entries.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("metrics") %>'>
				<%@ include file="/marketplace_admin/asset_stats.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("lists") %>'>
				<%@ include file="/marketplace_admin/asset_lists.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("portal-releases") %>'>
				<%@ include file="/marketplace_admin/portal_releases.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("sales") %>'>
				<%@ include file="/marketplace_admin/ec_document_entries.jspf" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/marketplace_admin/dashboard.jspf" %>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_MARKETPLACE_TESTER_ID) %>">
		<%@ include file="/marketplace_admin/app_entries.jspf" %>
	</c:when>
</c:choose>