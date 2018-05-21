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
String tabs2 = ParamUtil.getString(request, "tabs2");

String tabs2Names = StringPool.BLANK;

if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
	tabs2Names = "orders,offerings,offering-bundles,service-level-agreements,products,licenses";
}
else {
	String[] tabs2NamesArray = new String[0];

	if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {
		tabs2NamesArray = ArrayUtil.append(tabs2NamesArray, "orders");
	}

	if (!ArrayUtil.contains(tabs2NamesArray, tabs2)) {
		tabs2 = tabs2NamesArray[0];
	}

	tabs2Names = StringUtil.merge(tabs2NamesArray);
}

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-ui:tabs
	names="<%= tabs2Names %>"
	param="tabs2"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("licenses") %>'>
		<%@ include file="/admin/licenses.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("offerings") %>'>
		<%@ include file="/admin/offerings.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("offering-bundles") %>'>
		<%@ include file="/admin/offering_bundles.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("products") %>'>
		<%@ include file="/admin/products.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("service-level-agreements") %>'>
		<%@ include file="/admin/support_responses.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/admin/orders.jspf" %>
	</c:otherwise>
</c:choose>