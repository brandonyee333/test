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
List<KBArticle> kbArticles = (List<KBArticle>)request.getAttribute("kb_article_navigation.jsp-kbArticles");
Layout selLayout = (Layout)request.getAttribute("kb_article_navigation.jsp-selLayout");
%>

<c:if test="<%= (kbArticles != null) && !kbArticles.isEmpty() %>">
	<ul class="tree-container">

		<%
		for (KBArticle curKBArticle : kbArticles) {
			boolean isAncestor = kbArticleAncestorResourcePrimKeys.contains(curKBArticle.getResourcePrimKey());

			List<KBArticle> childKBArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), curKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));
		%>

			<li class="tree-node">
				<a class="<%= (isAncestor && !childKBArticles.isEmpty()) ? "has-children" : StringPool.BLANK %> <%= isAncestor ? "selected" : StringPool.BLANK %>" href="<%= KBArticleUtil.getKBArticleURL(request, selLayout.getPlid(), curKBArticle, redirect) %>"><span><%= curKBArticle.getTitle() %></span></a>

				<c:if test="<%= isAncestor && !childKBArticles.isEmpty() %>">

					<%
					request.setAttribute("kb_article_navigation.jsp-kbArticles", childKBArticles);
					%>

					<liferay-util:include page="/side_navigation/kb_article_navigation.jsp" servletContext="<%= application %>" />
				</c:if>
			</li>

		<%
		}
		%>

	</ul>
</c:if>