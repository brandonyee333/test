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
List<KBArticle> kbArticles = (List<KBArticle>)request.getAttribute("kb_article_navigation.jsp-kbArticles");
Layout selLayout = (Layout)request.getAttribute("kb_article_navigation.jsp-selLayout");
%>

<c:if test="<%= (kbArticles != null) && !kbArticles.isEmpty() %>">
	<ul class="tree-container">

		<%
		for (KBArticle curKBArticle : kbArticles) {
			boolean ancestor = kbArticleAncestorResourcePrimKeys.contains(curKBArticle.getResourcePrimKey());

			List<KBArticle> childKBArticles = KBArticleLocalServiceUtil.getKBArticles(themeDisplay.getScopeGroupId(), curKBArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new KBArticlePriorityComparator(true));
		%>

			<li class="tree-node <%= (ancestor && !childKBArticles.isEmpty()) ? "parent" : "child" %>">
				<a class="<%= ancestor ? "selected" : StringPool.BLANK %>" href="<%= KBArticleUtil.getKBArticleURL(request, selLayout.getPlid(), curKBArticle, redirect) %>"><span><%= curKBArticle.getTitle() %></span></a>

				<c:if test="<%= ancestor && !childKBArticles.isEmpty() %>">

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