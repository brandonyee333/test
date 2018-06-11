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