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
KBArticle kbArticle = (KBArticle)request.getAttribute(OSBCustomerWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<c:if test="<%= kbArticle != null %>">
	<div class="kb-entity-header">
		<h1 class="float-container kb-title">
			<%= HtmlUtil.escape(kbArticle.getTitle()) %>
		</h1>

		<%
		List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getCategories(KBArticle.class.getName(), kbArticle.getClassPK());

		AssetCategoryDisplay assetCategoryDisplay = new AssetCategoryDisplay(locale, assetCategories);
		%>

		<div>
			<%= assetCategoryDisplay.renderAbstract() %>
		</div>
	</div>
</c:if>

<%
HttpServletRequest originalRequest = liferayPortletRequest.getOriginalHttpServletRequest();
%>

<liferay-util:buffer
	var="html"
>
	<liferay-portlet:runtime
		persistSettings="<%= false %>"
		portletName="<%= KBPortletKeys.KNOWLEDGE_BASE_DISPLAY %>"
		queryString="<%= originalRequest.getQueryString() %>"
	/>
</liferay-util:buffer>

<%
int x = html.indexOf("<div class=\"kb-article-tools\"");

if (x != -1) {
	int y = html.indexOf("</div>", x);

	if (y != -1) {
		html = html.substring(0, x) + html.substring(y + 6);
	}
}
%>

<%= html %>