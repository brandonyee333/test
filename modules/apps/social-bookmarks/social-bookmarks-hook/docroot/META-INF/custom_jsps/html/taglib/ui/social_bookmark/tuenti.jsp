<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String tuentiDisplayStyle = StringPool.BLANK;

if (displayStyle.equals("simple")) {
	tuentiDisplayStyle = "icon-style=\"small\"";
}
%>

<liferay-util:html-bottom
	outputKey="taglib_ui_social_bookmark_tuenti"
>
	<script src="<%= HttpUtil.getProtocol(request) %>://widgets.tuenti.com/widgets.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<a class="tuenti-share-button" <%= tuentiDisplayStyle %> href="<%= HttpUtil.getProtocol(request) %>://www.tuenti.com/share" share-url="<%= url %>"></a>