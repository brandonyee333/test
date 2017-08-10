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
String tabs1 = ParamUtil.getString(request, "tabs1", "tickets");

List<AccountCustomer> accountCustomers = AccountCustomerLocalServiceUtil.getUserAccountCustomers(user.getUserId(), new int[] {AccountCustomerConstants.ROLE_SALES, AccountCustomerConstants.ROLE_WATCHER});

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/view.jsp");
portletURL.setParameter("tabs1", tabs1);
%>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<a class="fr portlet-icon-back" href="<%= PortalUtil.getLayoutURL(themeDisplay) %>"><liferay-ui:message key="return-to-full-page" /></a>
</c:if>

<h1 class="nm section-heading">
	<liferay-ui:message key="support" />
</h1>

<liferay-util:include page="/support/banner.jsp" servletContext="<%= application %>" />

<c:choose>
	<c:when test="<%= liferayIncOrg %>">
		<c:choose>
			<c:when test="<%= SupportWorkerLocalServiceUtil.hasSupportWorkerRole(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER) %>">
				<liferay-ui:tabs
					names="tickets,feedback,projects,partners,manager-dashboard,preferences"
					param="tabs1"
					url="<%= portletURL.toString() %>"
				/>
			</c:when>
			<c:otherwise>
				<liferay-ui:tabs
					names="tickets,feedback,projects,partners,preferences"
					param="tabs1"
					url="<%= portletURL.toString() %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="<%= supportPartnerWorker %>">
		<liferay-ui:tabs
			names="tickets,projects,partners,preferences"
			param="tabs1"
			url="<%= portletURL.toString() %>"
		/>
	</c:when>
	<c:when test="<%= !accountCustomers.isEmpty() %>">
		<liferay-ui:tabs
			names="tickets,environment-details,preferences"
			param="tabs1"
			url="<%= portletURL.toString() %>"
		/>
	</c:when>
	<c:otherwise>
		<liferay-ui:tabs
			names="tickets,environment-details,preferences"
			param="tabs1"
			url="<%= portletURL.toString() %>"
		/>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test='<%= tabs1.equals("environment-details") %>'>
		<liferay-util:include page="/support/environment_details.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("feedback") %>'>
		<liferay-util:include page="/support/feedback.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("manager-dashboard") %>'>
		<liferay-util:include page="/support/manager_dashboard.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("partners") %>'>
		<liferay-util:include page="/support/partners.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("preferences") %>'>
		<liferay-util:include page="/support/preferences.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("projects") %>'>
		<liferay-util:include page="/support/accounts.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/support/tickets.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>