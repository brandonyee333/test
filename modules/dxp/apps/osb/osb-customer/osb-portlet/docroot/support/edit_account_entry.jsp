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

<%@ include file="/support/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/view.jsp");
	portletURL.setParameter("tabs1", "projects");
	portletURL.setWindowState(LiferayWindowState.NORMAL);

	backURL = portletURL.toString();
}

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_account_entry.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<div class="cleared section">
	<div class="fr">
		<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
	</div>
</div>

<c:if test="<%= liferayIncOrg %>">
	<liferay-ui:tabs
		names="project-information,project-details,global-services"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>
</c:if>

<c:choose>
	<c:when test='<%= liferayIncOrg && tabs1.equals("global-services") %>'>
		<liferay-util:include page="/support/edit_account_entry_global_services.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= liferayIncOrg && tabs1.equals("project-details") %>'>
		<liferay-util:include page="/support/edit_account_entry_project_details.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/support/edit_account_entry_account_information.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>