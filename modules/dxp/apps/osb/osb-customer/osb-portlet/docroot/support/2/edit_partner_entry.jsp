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

<%@ include file="/support/2/init.jsp" %>

<%
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");

PartnerEntry partnerEntry = PartnerEntryServiceUtil.getPartnerEntry(partnerEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/edit_partner_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));

request.setAttribute("edit_partner_entry.jsp-backURL", backURL);
request.setAttribute("edit_partner_entry.jsp-partnerEntry", partnerEntry);
request.setAttribute("edit_partner_entry.jsp-portletURL", portletURL);
%>

<div class="partner-filter" id="<portlet:namespace />partnerFilter">
	<div class="partner-fade" id="<portlet:namespace />partnerFade"></div>
</div>

<liferay-util:include page="/support/2/edit_partner_entry/header.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/support/2/edit_partner_entry/details_tabs.jsp" servletContext="<%= application %>" />