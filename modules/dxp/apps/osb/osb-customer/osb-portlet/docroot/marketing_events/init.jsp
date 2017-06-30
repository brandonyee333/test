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
String defaultType = portletPreferences.getValue("defaultType", null);
boolean showSearch = GetterUtil.getBoolean(portletPreferences.getValue("showSearch", null), true);
String headerTitle = portletPreferences.getValue("headerTitle_" + LanguageUtil.getLanguageId(request), "Marketing Events");
String headerContent = portletPreferences.getValue("headerContent_" + LanguageUtil.getLanguageId(request), ContentUtil.get(PortletPropsValues.MARKETING_EVENTS_PORTLET_HEADER_CONTENT));
%>