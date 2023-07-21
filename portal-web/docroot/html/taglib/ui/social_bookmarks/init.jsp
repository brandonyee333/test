<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String contentId = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:contentId"));
String[] types = (String[])request.getAttribute("liferay-ui:social-bookmark:types");
String url = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:url"));
String title = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:title"));
String target = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:target"));

String displayStyle = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmarks:displayStyle"));
%>