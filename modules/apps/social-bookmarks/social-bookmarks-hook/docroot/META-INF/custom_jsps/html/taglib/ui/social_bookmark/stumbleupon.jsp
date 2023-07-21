<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String stumbleUponDisplayStyle = "2";

if (displayStyle.equals("simple")) {
	stumbleUponDisplayStyle = "4";
}
else if (displayStyle.equals("vertical")) {
	stumbleUponDisplayStyle = "5";
}
%>

<liferay-util:html-bottom
	outputKey="taglib_ui_social_bookmark_stumble_upon"
>
	<script data-senna-track="temporary" type="text/javascript">
		if (window.STMBLPN) {
			delete window.STMBLPN;
		}
	</script>

	<script data-senna-track="temporary" src="<%= HttpUtil.getProtocol(request) %>://platform.stumbleupon.com/1/widgets.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<su:badge layout="<%= stumbleUponDisplayStyle %>" location="<%= HttpUtil.encodeURL(url) %>"></su:badge>