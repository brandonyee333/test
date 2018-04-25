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

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "portal");

boolean liferayIncOrg = OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID);

if (!liferayIncOrg) {
	tabs1 = "portal";
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/view.jsp");
portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);
%>

<h1 class="section-heading">
	<liferay-ui:message key="license-management" />
</h1>

<c:choose>
	<c:when test="<%= liferayIncOrg %>">
		<%@ include file="/license/view_portal_worker.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/license/view_portal_customer.jspf" %>
	</c:otherwise>
</c:choose>