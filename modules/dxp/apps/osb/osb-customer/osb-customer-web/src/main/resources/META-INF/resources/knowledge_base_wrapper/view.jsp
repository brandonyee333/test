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
String cmd = (String)request.getAttribute(Constants.CMD);
String redirect = (String)request.getAttribute(OSBWebKeys.REDIRECT);
KBArticle kbArticle = (KBArticle)request.getAttribute(OSBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<c:if test="<%= !cmd.equals(Constants.EDIT) && (kbArticle != null) %>">
	<div class="kb-entity-header">
		<c:if test="<%= Validator.isNotNull(redirect) %>">
			<div>
				<a class="back-btn" href="<%= HtmlUtil.escapeAttribute(redirect) %>">
					<liferay-ui:message key="back-to-search-results" />
				</a>
			</div>
		</c:if>

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

<liferay-portlet:runtime persistSettings="<%= false %>" portletName="<%= KBPortletKeys.KNOWLEDGE_BASE_DISPLAY %>" queryString="<%= originalRequest.getQueryString() %>" />