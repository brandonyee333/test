<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
boolean showListed = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:journal-content-search:showListed"));
String targetPortletId = (String)request.getAttribute("liferay-ui:journal-content-search:targetPortletId");

PortletPreferences defaultPortletPreferences = PortletPreferencesFactoryUtil.fromDefaultXML(PortletConstants.DEFAULT_PREFERENCES);

defaultPortletPreferences.setValue("showListed", String.valueOf(showListed));
defaultPortletPreferences.setValue("targetPortletId", targetPortletId);
%>

<liferay-portlet:runtime
	defaultPreferences="<%= defaultPortletPreferences.toString() %>"
	portletName="com_liferay_journal_content_search_web_portlet_JournalContentSearchPortlet"
/>