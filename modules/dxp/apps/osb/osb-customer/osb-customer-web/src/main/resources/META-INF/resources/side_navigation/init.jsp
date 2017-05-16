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

<%@ page import="com.liferay.portal.kernel.util.SessionClicks" %>

<%
List<Layout> ancestorLayouts = new ArrayList<Layout>();

ancestorLayouts.add(layout);
ancestorLayouts.addAll(layout.getAncestors());

List<Long> kbArticleAncestorResourcePrimKeys = new ArrayList<Long>();

HttpServletRequest originalRequest = liferayPortletRequest.getOriginalHttpServletRequest();

KBArticle kbArticle = KBArticleUtil.getKBArticle(originalRequest);

if (kbArticle != null) {
	kbArticleAncestorResourcePrimKeys = kbArticle.getAncestorResourcePrimaryKeys();
}

String redirect = ParamUtil.getString(originalRequest, "_2_WAR_knowledgebaseportlet_redirect");

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(originalRequest, "_3_WAR_osbknowledgebaseportlet_redirect");
}

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(originalRequest, "_2_WAR_osbknowledgebaseportlet_redirect");
}
%>