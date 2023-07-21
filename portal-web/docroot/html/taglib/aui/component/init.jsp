<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
java.lang.String excludeAttributes = GetterUtil.getString((java.lang.String)request.getAttribute("aui:component:excludeAttributes"));
java.lang.String javaScriptAttributes = GetterUtil.getString((java.lang.String)request.getAttribute("aui:component:javaScriptAttributes"));
java.lang.String module = GetterUtil.getString((java.lang.String)request.getAttribute("aui:component:module"));
java.lang.String name = GetterUtil.getString((java.lang.String)request.getAttribute("aui:component:name"));
java.util.Map<java.lang.String, java.lang.Object> options = (java.util.Map<java.lang.String, java.lang.Object>)request.getAttribute("aui:component:options");
java.lang.String scriptPosition = GetterUtil.getString((java.lang.String)request.getAttribute("aui:component:scriptPosition"));
javax.servlet.jsp.JspContext tagPageContext = (javax.servlet.jsp.JspContext)request.getAttribute("aui:component:tagPageContext");
boolean useJavaScript = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:component:useJavaScript")), true);
java.lang.String var = GetterUtil.getString((java.lang.String)request.getAttribute("aui:component:var"));
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:component:dynamicAttributes");
%>

<%@ include file="/html/taglib/aui/component/init-ext.jspf" %>