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
		<a class="<%= ancestorLayouts.contains(childLayout) ? "selected" : StringPool.BLANK %>" href="<%= PortalUtil.getLayoutURL(childLayout, themeDisplay) %>" <%= PortalUtil.getLayoutTarget(childLayout) %>><span><%= childLayout.getName(locale) %></span></a>

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