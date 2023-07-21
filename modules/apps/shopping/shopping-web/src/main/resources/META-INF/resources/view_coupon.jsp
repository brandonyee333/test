<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ShoppingCoupon coupon = (ShoppingCoupon)request.getAttribute(WebKeys.SHOPPING_COUPON);

coupon = coupon.toEscapedModel();
%>

<strong><%= coupon.getCouponId() %></strong>

<br /><br />

<%= coupon.getName() %>

<br /><br />

<%= coupon.getDescription() %>