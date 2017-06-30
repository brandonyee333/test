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
long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");
long userId = ParamUtil.getLong(request, "userId");
%>

<c:choose>
	<c:when test="<%= AccountWorkerLocalServiceUtil.hasAccountWorkerRole(userId, AccountWorkerConstants.ROLE_MANAGED_SERVICES) %>">
		<img src="<%= PortalUtil.getPathContext(request) %>/images/liferay_managed_services_badge.png" />
	</c:when>
	<c:when test="<%= OrganizationLocalServiceUtil.hasUserOrganization(userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID) %>">
		<img src="<%= PortalUtil.getPathContext(request) %>/images/liferay_employee_badge.png" />
	</c:when>
	<c:when test="<%= PartnerWorkerLocalServiceUtil.hasPartnerWorker(userId, partnerEntryId) %>">
		<img src="<%= PortalUtil.getPathContext(request) %>/images/partner_worker_badge.png" />
	</c:when>
</c:choose>