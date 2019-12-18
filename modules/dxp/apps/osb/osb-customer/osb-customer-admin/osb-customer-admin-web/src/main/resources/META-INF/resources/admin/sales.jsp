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

String tabs2Names = "products,licenses";

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
	<c:when test='<%= tabs2.equals("products") %>'>
		<%@ include file="/admin/products.jspf" %>
	</c:when>
</c:choose>