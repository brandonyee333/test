<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("groupId", String.valueOf(themeDisplay.getScopeGroupId()));
%>

<aui:nav-bar cssClass="collapse-basic-search" id="toolbar" markupView="lexicon">
	<aui:nav-bar-search>
		<aui:form action="<%= portletURL %>" method="post" name="fm1">
			<liferay-util:include page="/workflow_definition_link_search.jsp" servletContext="<%= application %>" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>