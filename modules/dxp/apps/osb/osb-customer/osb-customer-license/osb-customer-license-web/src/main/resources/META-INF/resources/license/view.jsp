<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "portal");

boolean liferayIncOrg = OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID);

if (!liferayIncOrg) {
	tabs1 = "portal";
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/view.jsp");
portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);
%>

<div class="container-fluid-1280">
	<aui:row>
		<h1>
			<liferay-ui:message key="license-management" />
		</h1>
	</aui:row>

	<aui:row>
		<c:if test="<%= liferayIncOrg %>">
			<%@ include file="/license/view_portal_worker.jspf" %>
		</c:if>
	</aui:row>
</div>