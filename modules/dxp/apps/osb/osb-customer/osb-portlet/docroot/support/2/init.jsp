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
boolean liferayIncOrg = OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID);

boolean clockedIn = true;

if (liferayIncOrg && SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_WATCHER)) {
	clockedIn = SupportWorkerLocalServiceUtil.isClockedIn(user.getUserId());
}

boolean supportPartnerWorker = false;

LinkedHashMap<String, Object> partnerWorkerParams = new LinkedHashMap<String, Object>();

partnerWorkerParams.put("partnerWorker", new Long(user.getUserId()));
partnerWorkerParams.put("status", AccountEntryConstants.STATUSES_ACTIVE);

if (AccountEntryLocalServiceUtil.searchCount(null, partnerWorkerParams) > 0) {
	supportPartnerWorker = true;
}

boolean reverseCommentOrder = SupportUtil.getUserPreferenceValue(user.getUserId(), "reverseCommentOrder");

boolean screenShareMode = false;

if (liferayIncOrg) {
	screenShareMode = SupportUtil.getUserPreferenceValue(user.getUserId(), "screenShareMode");
}
%>

<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("init.jsp-cssIncluded")) && !themeDisplay.isStateExclusive() %>'>

	<%
	request.setAttribute("init.jsp-cssIncluded", Boolean.TRUE.toString());

	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);
	%>

	<liferay-util:html-top>
		<link href="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/support/2/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>
</c:if>

<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("init.jsp-navIncluded")) && !themeDisplay.isStateExclusive() && !themeDisplay.isStatePopUp() %>'>

	<%
	request.setAttribute("init.jsp-navIncluded", Boolean.TRUE.toString());
	%>

	<%@ include file="/support/2/nav.jspf" %>

	<%@ include file="/support/2/banner.jspf" %>
</c:if>