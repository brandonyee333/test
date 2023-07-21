<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
boolean collapsed = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:panel:collapsed")));
boolean collapsible = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:panel:collapsible")));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:panel:id"));
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("aui:panel:label"));
boolean localizeLabel = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:panel:localizeLabel")), true);
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:panel:dynamicAttributes");
%>

<%@ include file="/html/taglib/aui/panel/init-ext.jspf" %>