<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/tree_menu/init.jsp" %>

<%
List<WikiNode> wikiNodes = WikiNodeLocalServiceUtil.getNodes(scopeGroupId);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="the-node-could-not-be-found" />

			<aui:fieldset>
				<aui:select label="node" name="preferences--selNodeId--" showEmptyOption="<%= true %>">

					<%
					for (WikiNode wikiNode : wikiNodes) {
					%>

						<aui:option label="<%= wikiNode.getName() %>" selected="<%= selNodeId == wikiNode.getNodeId() %>" value="<%= wikiNode.getNodeId() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select name="preferences--depth--">
					<aui:option label="all" selected="<%= depth == WikiNavigationConstants.DEPTH_ALL %>" value="<%= WikiNavigationConstants.DEPTH_ALL %>" />

					<%
					for (int i = 1; i < 6; i++) {
					%>

						<aui:option label="<%= i %>" selected="<%= depth == i %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>