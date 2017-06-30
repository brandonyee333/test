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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

String upgradeStep = ParamUtil.getString(request, "upgradeStep", "overview");

long developerEntryId = ParamUtil.getLong(request, "developerEntryId");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

String title = "convert-to-a-paid-app-developer-account";

if (developerEntry.getSubscriptionExpirationDate() != null) {
	title = "renew-paid-app-developer-account";

	upgradeStep = "pay";
}
%>

<div class="marketplace-registration upgrade-developer-entry">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="<%= title %>"
	/>

	<c:choose>
		<c:when test='<%= upgradeStep.equals("details") %>'>
			<liferay-util:include page="/marketplace_registration/upgrade_developer_entry/details.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= upgradeStep.equals("email") %>'>
			<liferay-util:include page="/marketplace_registration/upgrade_developer_entry/email.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= upgradeStep.equals("pay") %>'>
			<liferay-util:include page="/marketplace_registration/upgrade_developer_entry/pay.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/marketplace_registration/upgrade_developer_entry/overview.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>