<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmarks/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_social_bookmarks_page") + StringPool.UNDERLINE;
%>

<div class="taglib-social-bookmarks" id="<%= randomNamespace %>socialBookmarks">
	<c:choose>
		<c:when test='<%= displayStyle.equals("menu") %>'>
			<liferay-ui:icon-menu
				direction="right"
				icon="share-alt"
				markupView="lexicon"
				message="share"
				showWhenSingleIcon="<%= true %>"
			>

				<%
				for (int i = 0; i < types.length; i++) {
				%>

					<liferay-ui:social-bookmark
						contentId="<%= contentId %>"
						displayStyle="<%= displayStyle %>"
						target="<%= target %>"
						title="<%= title %>"
						type="<%= types[i] %>"
						url="<%= url %>"
					/>

				<%
				}
				%>

			</liferay-ui:icon-menu>

			<aui:script use="liferay-social-bookmarks">
				var contentBoxNode = A.one('#<%= randomNamespace %>socialBookmarks');

				if (contentBoxNode) {
					new Liferay.SocialBookmarks(
						{
							contentBox: '#<%= randomNamespace %>socialBookmarks'
						}
					);
				}
			</aui:script>
		</c:when>
		<c:otherwise>
			<ul class="list-unstyled <%= displayStyle %>">

				<%
				for (int i = 0; i < types.length; i++) {
				%>

					<li class="taglib-social-bookmark taglib-social-bookmark-<%= types[i] %>">
						<liferay-ui:social-bookmark
							contentId="<%= contentId %>"
							displayStyle="<%= displayStyle %>"
							target="<%= target %>"
							title="<%= title %>"
							type="<%= types[i] %>"
							url="<%= url %>"
						/>
					</li>

				<%
				}
				%>

			</ul>
		</c:otherwise>
	</c:choose>
</div>