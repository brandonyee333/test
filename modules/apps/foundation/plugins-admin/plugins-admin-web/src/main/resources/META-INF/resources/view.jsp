<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "portlets");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/plugins_admin/view");
portletURL.setParameter("tabs2", tabs2);

PortletURL marketplaceURL = null;

boolean showEditPluginHREF = true;
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL portletsURL = renderResponse.createRenderURL();

		portletsURL.setParameter("tabs2", "portlets");
		%>

		<aui:nav-item href="<%= portletsURL.toString() %>" label="portlets" selected='<%= tabs2.equals("portlets") %>' />

		<%
		PortletURL themesURL = renderResponse.createRenderURL();

		themesURL.setParameter("tabs2", "themes");
		%>

		<aui:nav-item href="<%= themesURL.toString() %>" label="themes" selected='<%= tabs2.equals("themes") %>' />

		<%
		PortletURL layoutTemplatesURL = renderResponse.createRenderURL();

		layoutTemplatesURL.setParameter("tabs2", "layout-templates");
		%>

		<aui:nav-item href="<%= layoutTemplatesURL.toString() %>" label="layout-templates" selected='<%= tabs2.equals("layout-templates") %>' />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<c:choose>
		<c:when test='<%= tabs2.equals("themes") %>'>
			<%@ include file="/themes.jspf" %>
		</c:when>
		<c:when test='<%= tabs2.equals("layout-templates") %>'>
			<%@ include file="/layout_templates.jspf" %>
		</c:when>
		<c:when test='<%= tabs2.equals("hook-plugins") %>'>
		</c:when>
		<c:when test='<%= tabs2.equals("web-plugins") %>'>
		</c:when>
		<c:otherwise>
			<%@ include file="/portlets.jspf" %>
		</c:otherwise>
	</c:choose>
</div>