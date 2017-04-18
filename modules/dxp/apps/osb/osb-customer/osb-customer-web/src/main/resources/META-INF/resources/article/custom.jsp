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

<%@ include file="/init.jsp" %>

<%
DDMStructure ddmStructure = (DDMStructure)request.getAttribute("edit_article.jsp-structure");

String articleId = ParamUtil.getString(request, "articleId");
long groupId = ParamUtil.getLong(request, "groupId", themeDisplay.getScopeGroupId());

JournalArticle oldestArticle = JournalArticleLocalServiceUtil.getArticle(groupId, articleId, 1.0);

String structureId = StringPool.BLANK;

if (ddmStructure != null) {
	structureId = ddmStructure.getStructureKey();
}
%>

<c:if test='<%= structureId.equals("ARTICLE-DISPLAY") %>'>

	<%
	long originalAuthorUserId = user.getUserId();
	String originalAuthorUserName = user.getFullName();

	if (oldestArticle != null) {
		originalAuthorUserId = oldestArticle.getUserId();
		originalAuthorUserName = oldestArticle.getUserName();
	}
	%>

	<div class="article-original-author">
		<aui:input name="originalAuthorUserId" type="hidden" value="<%= originalAuthorUserId %>" />

		<label>
			<liferay-ui:message key="original-author" />
		</label>

		<span id="<portlet:namespace />originalAuthorUserName">
			<%= HtmlUtil.escape(originalAuthorUserName) %>
		</span>

		<div class="button-holder">
			<aui:button id="selectUser" value="select" />
		</div>
	</div>

	<aui:script use="aui-base">
		A.one('#<portlet:namespace />selectUser').on(
			'click',
			function(event) {
				Liferay.Util.selectEntity(
					{
						dialog: {
							constrain: true,
							destroyOnHide: true,
							modal: true
						},
						id: '<portlet:namespace />selectUser',
						title: '<liferay-ui:message arguments="user" key="select-x" />',
						uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcRenderCommandName" value="/journal/select_user" /></portlet:renderURL>'
					},
					function(event) {
						A.one('#<portlet:namespace />originalAuthorUserId').set('value', event.userid);
						A.one('#<portlet:namespace />originalAuthorUserName').setHTML(event.username);
					}
				);
			}
		);
	</aui:script>
</c:if>