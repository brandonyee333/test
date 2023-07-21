<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "categories");
%>

<c:choose>
	<c:when test='<%= tabs1.equals("cart") %>'>
		<liferay-util:include page="/cart.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("categories") %>'>
		<liferay-util:include page="/categories.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("coupons") %>'>
		<liferay-util:include page="/coupons.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("orders") && !user.isDefaultUser() %>'>
		<liferay-util:include page="/orders.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>