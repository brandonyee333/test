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
String tabs1 = ParamUtil.getString(request, "tabs1", "app-performance");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(themeDisplay.getScopeGroupId());

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_metrics/view.jsp");
portletURL.setParameter("tabs1", tabs1);
%>

<div class="marketplace-metrics view">
	<liferay-ui:header
		title="metrics"
	/>

	<%
	boolean showGoogleAnalyticsTab = OSBDeveloperEntryPermission.contains(themeDisplay.getPermissionChecker(), developerEntry.getDeveloperEntryId(), OSBActionKeys.SUBSCRIBER_UPDATE);

	String names = "app-performance,top-performers";

	if (showGoogleAnalyticsTab) {
		names = names.concat(",google-analytics");
	}
	%>

	<liferay-ui:tabs
		names="<%= names %>"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("app-performance") %>'>
			<%@ include file="/marketplace_metrics/app_performance.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("top-performers") %>'>
			<%@ include file="/marketplace_metrics/top_performers.jspf" %>
		</c:when>
		<c:when test='<%= showGoogleAnalyticsTab && tabs1.equals("google-analytics") %>'>
			<%@ include file="/marketplace_metrics/google_analytics.jspf" %>
		</c:when>
	</c:choose>
</div>