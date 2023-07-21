<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "settings");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL settingsURL = PortletURLUtil.clone(portletURL, renderResponse);

		settingsURL.setParameter("tabs1", "settings");
		%>

		<aui:nav-item href="<%= settingsURL.toString() %>" label="settings" selected='<%= tabs1.equals("settings") %>' />

		<%
		PortletURL sitesURL = PortletURLUtil.clone(portletURL, renderResponse);

		sitesURL.setParameter("tabs1", "sites");
		%>

		<aui:nav-item href="<%= sitesURL.toString() %>" label="sites" selected='<%= tabs1.equals("sites") %>' />

		<%
		PortletURL devicesURL = PortletURLUtil.clone(portletURL, renderResponse);

		devicesURL.setParameter("tabs1", "devices");
		%>

		<aui:nav-item href="<%= devicesURL.toString() %>" label="devices" selected='<%= tabs1.equals("devices") %>' />
	</aui:nav>

	<c:choose>
		<c:when test='<%= !tabs1.equals("settings") %>'>
			<aui:form action="<%= portletURL %>" name="searchFm">
				<aui:nav-bar-search>
					<liferay-ui:input-search
						markupView="lexicon"
					/>
				</aui:nav-bar-search>
			</aui:form>
		</c:when>
	</c:choose>
</aui:nav-bar>

<c:choose>
	<c:when test='<%= tabs1.equals("settings") %>'>
		<liferay-util:include page="/settings.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("sites") %>'>
		<liferay-util:include page="/sites.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/devices.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>