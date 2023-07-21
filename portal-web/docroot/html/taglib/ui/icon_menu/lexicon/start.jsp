<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.taglib.aui.AUIUtil" %>

<%@ page import="java.util.Map" %>

<%
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:icon-menu:cssClass"));
Map<String, Object> data = (Map<String, Object>)request.getAttribute("liferay-ui:icon-menu:data");
String direction = (String)request.getAttribute("liferay-ui:icon-menu:direction");
String icon = GetterUtil.getString((String)request.getAttribute("liferay-ui:icon-menu:icon"));
String id = GetterUtil.getString((String)request.getAttribute("liferay-ui:icon-menu:id"));
String message = (String)request.getAttribute("liferay-ui:icon-menu:message");
boolean scroll = GetterUtil.getBoolean(request.getAttribute("liferay-ui:icon-menu:scroll"));
String triggerCssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:icon-menu:triggerCssClass"));

if (Validator.isNull(icon)) {
	icon = "ellipsis-v";
}
%>

<div class="dropdown lfr-icon-menu <%= cssClass %>" <%= AUIUtil.buildData(data) %>>
	<a class="direction-<%= direction %> dropdown-toggle icon-monospaced <%= triggerCssClass %>" href="javascript:;" id="<%= id %>" title="<%= message %>">
		<aui:icon image="<%= icon %>" markupView="lexicon" />
	</a>

	<aui:script position="inline" use="liferay-menu">
		Liferay.Menu.register('<%= id %>');
	</aui:script>

	<c:choose>
		<c:when test="<%= scroll %>">
			<div class="dropdown-menu dropdown-menu-<%= direction %>">
				<ul class="inline-scroller">
		</c:when>
		<c:otherwise>
			<ul class="dropdown-menu dropdown-menu-<%= direction %>">
		</c:otherwise>
	</c:choose>