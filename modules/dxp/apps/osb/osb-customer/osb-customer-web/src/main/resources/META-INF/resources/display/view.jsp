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

<%@ include file="/display/init.jsp" %>

<%
String queryString = (String)request.getAttribute("queryString");

String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-ui:error exception="<%= NoSuchArticleException.class %>" message="the-article-could-not-be-found" />
<liferay-ui:error exception="<%= PrincipalException.MustHavePermission.class %>" message="the-article-could-not-be-found" />

<c:if test="<%= Validator.isNotNull(redirect) %>">
	<div>
		<a class="back-btn" href="<%= HtmlUtil.escapeAttribute(redirect) %>">
			<liferay-ui:message key="back-to-search-results" />
		</a>
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(queryString) %>">
	<liferay-portlet:runtime
		persistSettings="<%= false %>"
		portletName="<%= JournalContentPortletKeys.JOURNAL_CONTENT %>"
		queryString="<%= queryString %>"
	/>
</c:if>