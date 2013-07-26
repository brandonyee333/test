<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/taglib/ui/social_bookmarks/init.jsp" %>

<c:if test="<%= typesArray.length > 0 %>">

	<%
	String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_social_bookmarks_page") + StringPool.UNDERLINE;
	%>

	<div class="taglib-social-bookmarks" id="<%= randomNamespace %>socialBookmarks">
		<ul class="unstyled">

			<%
			for (int i = 0; i < typesArray.length; i++) {
				String styleClass = "taglib-social-bookmark-" + typesArray[i];
			%>

				<li class="<%= styleClass %>">
					<liferay-ui:social-bookmark contentId="<%= contentId %>" target="<%= target %>" title="<%= title %>" type="<%= typesArray[i] %>" url="<%= url %>" />
				</li>

			<%
			}
			%>

		</ul>
	</div>

	<div id="share">
		<button class="btn" type="button">
			Share
			<i class="icon-share"></i>
		</button>
		SANITY
		<liferay-ui:icon-menu cssClass="icon-share" id="iconMenuShare" message="share">
			<liferay-ui:icon
				message="tweet"
				src=""
				url="twitter.com"
			/>

			<liferay-ui:icon
				message="like"
				src=""
				url='facebook.com'
			/>

			<liferay-ui:icon
				message="publically-recommend-on-google"
				src=""
				url='google.com/+'
			/>

		</liferay-ui:icon-menu>
	</div>

	<aui:script use="node">
		var share = A.one('#share');


	</aui:script>

</c:if>