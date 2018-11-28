<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "portal");

boolean liferayIncOrg = OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID);

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
		<c:choose>
			<c:when test="<%= liferayIncOrg %>">
				<%@ include file="/license/view_portal_worker.jspf" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/license/view_portal_customer.jspf" %>
			</c:otherwise>
		</c:choose>
	</aui:row>
</div>