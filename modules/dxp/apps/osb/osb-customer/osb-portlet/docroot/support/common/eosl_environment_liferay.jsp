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
int envLFR = ParamUtil.getInteger(request, "envLFR");
%>

<div class="portlet-msg-info <%= ProductEntryConstants.isPortalVersion5_2(envLFR) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />supportMessageDisplay_5_2">
	<span><liferay-ui:message key="important" /></span>

	<br />

	<span class="txt-n"><liferay-ui:message arguments='<%= "/documents/3133562/8435737/EOV_announcement.pdf" %>' key="the-end-of-service-life-for-liferay-portal-5.2-ee-was-on-may-31-2017" /></span>
</div>

<div class="portlet-msg-info <%= ProductEntryConstants.isPortalVersion6_0(envLFR) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />supportMessageDisplay_6_0">
	<span><liferay-ui:message key="important" /></span>

	<br />

	<span class="txt-n"><liferay-ui:message arguments='<%= "/group/customer/products/portal/support/service-life" %>' key="the-end-of-service-life-for-liferay-portal-6.0-ee-will-be-on-september-10-2017" /></span>
</div>

<div class="portlet-msg-info <%= ProductEntryConstants.isPortalVersion6_1(envLFR) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />supportMessageDisplay_6_1">
	<span><liferay-ui:message key="important" /></span>

	<br />

	<span class="txt-n"><liferay-ui:message arguments='<%= "/group/customer/products/portal/support/service-life" %>' key="liferay-portal-6.1-ee-ga1-6.1-ee-ga2-and-6.1-ee-ga3-are-currently-in-the-limited-support-phase-of-their-lifecycle" /></span>
</div>