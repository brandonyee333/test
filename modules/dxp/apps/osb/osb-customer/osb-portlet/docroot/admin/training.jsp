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
String tabs2 = ParamUtil.getString(request, "tabs2");

String tabsNames = StringPool.BLANK;

if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_TRAINING_ADMIN_ID)) {
	tabsNames = "classes,courses,locations,students,certificate-templates,certification-exams,surveys,contracts";
}
else if (RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_TRAINING_TRAINER_ID)) {
	tabsNames = "classes,surveys";
}
else {
	tabs2 = StringPool.BLANK;
}

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

portletURL.setParameter("tabs2", tabs2);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "training"), portletURL.toString());
%>

<liferay-ui:tabs
	names="<%= tabsNames %>"
	param="tabs2"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("certification-exams") %>'>
		<%@ include file="/admin/training_exam.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("certificate-templates") %>'>
		<%@ include file="/admin/training_certificate_templates.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("courses") %>'>
		<%@ include file="/admin/training_courses.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("locations") %>'>
		<%@ include file="/admin/training_locations.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("students") %>'>
		<%@ include file="/admin/training_customers.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("surveys") %>'>
		<%@ include file="/admin/training_surveys.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("contracts") %>'>
		<%@ include file="/admin/training_contract_entries.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/admin/training_events.jspf" %>
	</c:otherwise>
</c:choose>