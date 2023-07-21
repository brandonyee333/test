<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);

WikiURLHelper wikiURLHelper = new WikiURLHelper(wikiRequestHelper, renderResponse, wikiGroupServiceConfiguration);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="viewPagesURL">
			<portlet:param name="mvcRenderCommandName" value="/wiki/view_pages" />
		</portlet:renderURL>

		<aui:nav-item href="<%= viewPagesURL %>" label="pages" selected="<%= true %>" />
	</aui:nav>

	<%
	PortletURL searchURL = wikiURLHelper.getSearchURL();
	%>

	<aui:nav-bar-search>
		<aui:form action="<%= searchURL %>" method="get" name="searchFm">
			<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="nodeId" type="hidden" value="<%= node.getNodeId() %>" />

			<liferay-ui:input-search
				id="keywords1"
				markupView="lexicon"
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>