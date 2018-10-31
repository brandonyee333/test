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

<c:if test="<%= layout != null %>">

	<%
	Layout rootLayout = null;

	List<Layout> curLayouts = new ArrayList<Layout>();

	curLayouts.add(layout);
	curLayouts.addAll(layout.getAncestors());

	int ancestorIndex = curLayouts.size() - 1;

	if ((ancestorIndex >= 0) && (ancestorIndex < curLayouts.size())) {
		rootLayout = curLayouts.get(ancestorIndex);
	}
	%>

	<div id="<portlet:namespace />navigationContainer">
		<c:if test="<%= rootLayout != null %>">
			<h1 class="regular-heading">
				<a href="<%= PortalUtil.getLayoutURL(rootLayout, themeDisplay) %>"><%= rootLayout.getName(themeDisplay.getLocale()) %></a>
			</h1>
		</c:if>

		<%
		StringBuilder sb = new StringBuilder();

		_buildNavigation(rootLayout, layout, curLayouts, themeDisplay, 1, sb);
		%>

		<div class="hide side-nav" id="<portlet:namespace />navigation">
			<%= sb.toString() %>
		</div>
	</div>
</c:if>

<aui:script use="aui-tree">
	var A = AUI();

	var navigationContainer = A.one('#<portlet:namespace />navigationContainer');

	if (navigationContainer) {
		var treeView = new A.TreeView(
			{
				contentBox: '.side-nav-tree',
				selectOnToggle: true,
				type: 'normal'
			}
		).render('#<portlet:namespace />navigation');

		var sideNavTree = navigationContainer.one('.side-nav-tree');

		if (sideNavTree) {
			var selected = sideNavTree.all('a.selected');

			selected.each(
				function(item, index, collection) {
					var selectedNode = treeView.getNodeByChild(item);

					selectedNode.expand();
				}
			);
		}

		A.all('.side-nav').show();
	}
</aui:script>

<%!
private void _buildNavigation(Layout rootLayout, Layout selLayout, List<Layout> layouts, ThemeDisplay themeDisplay, int layoutLevel, StringBuilder sb) throws Exception {
	List<Layout> childrenLayouts = null;

	if (rootLayout != null) {
		childrenLayouts = rootLayout.getChildren(themeDisplay.getPermissionChecker());
	}
	else {
		childrenLayouts = LayoutServiceUtil.getLayouts(selLayout.getGroupId(), selLayout.isPrivateLayout(), LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
	}

	if (childrenLayouts.isEmpty()) {
		return;
	}

	sb.append("<ul class=\"");

	if (layoutLevel == 1) {
		sb.append("side-nav-tree");
	}
	else {
		sb.append("tree-container");
	}

	sb.append("\">");

	for (int i = 0; i < childrenLayouts.size(); i++) {
		Layout childLayout = childrenLayouts.get(i);

		if (childLayout.isHidden()) {
			continue;
		}

		sb.append("<li class=\"tree-node");

		if (layouts.contains(childLayout)) {
			sb.append(" selected");
		}

		sb.append("\"><a ");

		if (layouts.contains(childLayout)) {
			sb.append("class=\"selected\" ");
		}

		sb.append("href=\"");
		sb.append(PortalUtil.getLayoutURL(childLayout, themeDisplay));
		sb.append("\" ");
		sb.append(PortalUtil.getLayoutTarget(childLayout));
		sb.append(">");
		sb.append(childLayout.getName(themeDisplay.getLocale()));
		sb.append("</a>");

		if (layoutLevel < 3) {
			_buildNavigation(childLayout, selLayout, layouts, themeDisplay, layoutLevel + 1, sb);
		}

		sb.append("</li>");
	}

	sb.append("</ul>");
}
%>