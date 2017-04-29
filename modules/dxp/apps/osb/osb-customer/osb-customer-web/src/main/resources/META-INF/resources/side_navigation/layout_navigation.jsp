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

<%@ include file="/side_navigation/init.jsp" %>

<%
Layout selLayout = (Layout)request.getAttribute("layout_navigation.jsp-selLayout");

if (selLayout == null) {
	long selLayoutPlid = ParamUtil.getLong(request, "selLayoutPlid");

	selLayout = LayoutLocalServiceUtil.getLayout(selLayoutPlid);
}

for (Layout childLayout : selLayout.getChildren()) {
	if (childLayout.isHidden()) {
		continue;
	}

	PortletPreferences kbPortletPreferences = PortletPreferencesFactoryUtil.getStrictPortletSetup(childLayout, KBPortletKeys.KNOWLEDGE_BASE_DISPLAY);

	long resourcePrimKey = GetterUtil.getLong(kbPortletPreferences.getValue("resourcePrimKey", null));

	List<KBArticle> kbArticles = new ArrayList<KBArticle>();

	if (resourcePrimKey > 0) {
		kbArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), resourcePrimKey, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));
	}
%>

	<li class="tree-node <%= ancestorLayouts.contains(childLayout) ? "selected" : StringPool.BLANK %>">
		<a class="<%= (childLayout.hasChildren() || !kbArticles.isEmpty()) ? "has-children" : StringPool.BLANK %> <%= ancestorLayouts.contains(childLayout) ? "selected" : StringPool.BLANK %>" href="<%= PortalUtil.getLayoutURL(childLayout, themeDisplay) %>" <%= PortalUtil.getLayoutTarget(childLayout) %>><span><%= childLayout.getName(locale) %></span></a>

		<c:if test="<%= childLayout.hasChildren() %>">

			<%
			request.setAttribute("layout_navigation.jsp-selLayout", childLayout);
			%>

			<ul class="tree-container">
				<liferay-util:include page="/side_navigation/layout_navigation.jsp" servletContext="<%= application %>" />
			</ul>
		</c:if>

		<%
		request.setAttribute("kb_article_navigation.jsp-kbArticles", kbArticles);
		request.setAttribute("kb_article_navigation.jsp-selLayout", childLayout);
		%>

		<liferay-util:include page="/side_navigation/kb_article_navigation.jsp" servletContext="<%= application %>" />
	</li>

<%
}
%>