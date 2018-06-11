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

<%
DDMStructure ddmStructure = (DDMStructure)request.getAttribute("edit_article.jsp-structure");

String ddmStructureId = StringPool.BLANK;

if (ddmStructure != null) {
	ddmStructureId = ddmStructure.getStructureKey();
}
%>

<c:if test="<%= ddmStructureId.equals(OSBCustomerConstants.DDM_STRUCTURE_ARTICLE_DISPLAY_KEY) %>">

	<%
	String articleId = ParamUtil.getString(request, "articleId");
	long groupId = ParamUtil.getLong(request, "groupId", themeDisplay.getScopeGroupId());

	JournalArticle oldestJournalArticle = JournalArticleLocalServiceUtil.fetchArticle(groupId, articleId, 1.0);

	long originalAuthorUserId = user.getUserId();
	String originalAuthorUserName = user.getFullName();

	if (oldestJournalArticle != null) {
		originalAuthorUserId = oldestJournalArticle.getUserId();
		originalAuthorUserName = oldestJournalArticle.getUserName();
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