<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);

List<WikiNode> nodes = wikiPortletInstanceSettingsHelper.getAllPermittedNodes();

boolean print = Objects.equals(ParamUtil.getString(request, "viewMode"), Constants.PRINT);

WikiURLHelper wikiURLHelper = new WikiURLHelper(wikiRequestHelper, renderResponse, wikiGroupServiceConfiguration);
WikiVisualizationHelper wikiVisualizationHelper = new WikiVisualizationHelper(wikiRequestHelper, wikiPortletInstanceSettingsHelper, wikiGroupServiceConfiguration);
%>

<c:if test="<%= wikiVisualizationHelper.isUndoTrashControlVisible() %>">

	<%
	PortletURL undoTrashURL = wikiURLHelper.getUndoTrashURL();
	%>

	<liferay-trash:undo
		portletURL="<%= undoTrashURL.toString() %>"
	/>
</c:if>

<%
boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

PortletURL backToNodeURL = wikiURLHelper.getBackToNodeURL(node);

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backToNodeURL.toString());

	renderResponse.setTitle(node.getName());
}
%>

<c:if test="<%= !print %>">
	<c:if test="<%= wikiVisualizationHelper.isNodeNavigationVisible() %>">
		<aui:nav cssClass="nav-tabs">

			<%
			for (WikiNode curNode : nodes) {
				String cssClass = StringPool.BLANK;

				if (curNode.getNodeId() == node.getNodeId()) {
					cssClass = "active";
				}

				PortletURL viewPageURL = wikiURLHelper.getViewFrontPagePageURL(curNode);
			%>

				<aui:nav-item cssClass="<%= cssClass %>" href="<%= viewPageURL.toString() %>" label="<%= HtmlUtil.escape(curNode.getName()) %>" />

			<%
			}
			%>

		</aui:nav>
	</c:if>

	<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
		<aui:nav cssClass="navbar-nav">

			<%
			PortletURL frontPageURL = wikiURLHelper.getFrontPageURL(node);

			String label = wikiGroupServiceConfiguration.frontPageName();
			boolean selected = wikiVisualizationHelper.isFrontPageNavItemSelected();
			%>

			<aui:nav-item href="<%= frontPageURL.toString() %>" label="<%= label %>" selected="<%= selected %>" />

			<%
			PortletURL viewRecentChangesURL = wikiURLHelper.getViewRecentChangesURL(node);

			label = "recent-changes";
			selected = wikiVisualizationHelper.isViewRecentChangesNavItemSelected();
			%>

			<aui:nav-item href="<%= viewRecentChangesURL.toString() %>" label="<%= label %>" selected="<%= selected %>" />

			<%
			PortletURL viewAllPagesURL = wikiURLHelper.getViewPagesURL(node);

			label = "all-pages";
			selected = wikiVisualizationHelper.isViewAllPagesNavItemSelected();
			%>

			<aui:nav-item href="<%= viewAllPagesURL.toString() %>" label="<%= label %>" selected="<%= selected %>" />

			<%
			PortletURL viewOrphanPagesURL = wikiURLHelper.getViewOrphanPagesURL(node);

			label = "orphan-pages";
			selected = wikiVisualizationHelper.isViewOrphanPagesNavItemSelected();
			%>

			<aui:nav-item href="<%= viewOrphanPagesURL.toString() %>" label="<%= label %>" selected="<%= selected %>" />

			<%
			PortletURL viewDraftPagesURL = wikiURLHelper.getViewDraftPagesURL(node);

			label = "draft-pages";
			selected = wikiVisualizationHelper.isViewDraftPagesNavItemSelected();
			%>

			<aui:nav-item href="<%= viewDraftPagesURL.toString() %>" label="<%= label %>" selected="<%= selected %>" />
		</aui:nav>

		<%
		PortletURL searchURL = wikiURLHelper.getSearchURL();
		%>

		<aui:nav-bar-search>
			<div class="form-search">
				<aui:form action="<%= searchURL %>" method="get" name="searchFm">
					<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="nodeId" type="hidden" value="<%= node.getNodeId() %>" />

					<liferay-ui:input-search
						id="keywords1"
						markupView="lexicon"
					/>
				</aui:form>
			</div>
		</aui:nav-bar-search>
	</aui:nav-bar>

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		<aui:script>
			Liferay.Util.focusFormField(document.getElementById('<portlet:namespace />keywords1'));
		</aui:script>
	</c:if>
</c:if>