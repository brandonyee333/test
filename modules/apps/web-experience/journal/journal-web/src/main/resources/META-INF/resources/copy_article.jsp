<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long groupId = ParamUtil.getLong(request, "groupId");
String oldArticleId = ParamUtil.getString(request, "oldArticleId");
String newArticleId = ParamUtil.getString(request, "newArticleId");
double version = ParamUtil.getDouble(request, "version");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "web-content"));
%>

<portlet:actionURL name="copyArticle" var="copyArticleURL">
	<portlet:param name="mvcPath" value="/copy_article.jsp" />
</portlet:actionURL>

<aui:form action="<%= copyArticleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="oldArticleId" type="hidden" value="<%= oldArticleId %>" />
	<aui:input name="version" type="hidden" value="<%= version %>" />

	<liferay-ui:error exception="<%= ArticleIdException.class %>" message="please-enter-a-valid-id" />
	<liferay-ui:error exception="<%= DuplicateArticleIdException.class %>" message="please-enter-a-unique-id" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="id" type="resource" value="<%= oldArticleId %>" />

			<c:choose>
				<c:when test="<%= journalWebConfiguration.journalFeedForceAutogenerateId() %>">
					<aui:input name="newId" type="resource" value='<%= LanguageUtil.get(request, "autogenerate-id") %>' />

					<aui:input name="newArticleId" type="hidden" />
					<aui:input name="autoArticleId" type="hidden" value="<%= true %>" />
				</c:when>
				<c:otherwise>
					<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" bean="<%= null %>" cssClass="lfr-input-text-container" field="articleId" fieldParam="newArticleId" label="" model="<%= JournalArticle.class %>" name="newArticleId" value="<%= newArticleId %>" />
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" value="copy" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" value="cancel" />
	</aui:button-row>
</aui:form>