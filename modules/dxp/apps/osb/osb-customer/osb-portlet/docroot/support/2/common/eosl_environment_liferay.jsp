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
int envLFR = ParamUtil.getInteger(request, "envLFR");
String idPrefix = ParamUtil.getString(request, "idPrefix", "supportMessageDisplay");

idPrefix = renderResponse.getNamespace() + HtmlUtil.escapeAttribute(idPrefix);
%>

<div class="portlet-msg-info <%= ProductEntryConstants.isPortalVersion5_2(envLFR) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<%= idPrefix + "_5_2" %>">
	<span class="txt-b"><liferay-ui:message key="important" /></span>

	<br />

	<%
	Object[] arguments = {"<a href=\"/documents/3133562/8435737/EOV_announcement.pdf\">", "</a>"};
	%>

	<span><liferay-ui:message arguments="<%= arguments %>" key="the-end-of-service-life-for-liferay-portal-5.2-ee-was-on-may-31-2017" /></span>
</div>

<div class="portlet-msg-info <%= ProductEntryConstants.isPortalVersion6_0(envLFR) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<%= idPrefix + "_6_0" %>">
	<span class="txt-b"><liferay-ui:message key="important" /></span>

	<br />

	<%
	arguments = new Object[] {"<a href=\"/group/customer/products/portal/support/service-life\">", "</a>"};
	%>

	<span><liferay-ui:message arguments="<%= arguments %>" key="the-end-of-service-life-for-liferay-portal-6.0-ee-will-be-on-september-10-2017" /></span>
</div>

<div class="portlet-msg-info <%= ProductEntryConstants.isPortalVersion6_1(envLFR) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<%= idPrefix + "_6_1" %>">
	<span class="txt-b"><liferay-ui:message key="important" /></span>

	<br />

	<%
	arguments = new Object[] {"<a href=\"/group/customer/products/portal/support/service-life\">", "</a>"};
	%>

	<span><liferay-ui:message arguments="<%= arguments %>" key="liferay-portal-6.1-ee-ga1-6.1-ee-ga2-and-6.1-ee-ga3-are-currently-in-the-limited-support-phase-of-their-lifecycle" /></span>
</div>