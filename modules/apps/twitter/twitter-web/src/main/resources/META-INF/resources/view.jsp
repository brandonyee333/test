<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String twitterSn = contact.getTwitterSn();
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(twitterSn) %>">

		<%
		StringBuilder sb = new StringBuilder(5);

		sb.append("<a href=\"http://twitter.com/");
		sb.append(HtmlUtil.escapeAttribute(twitterSn));
		sb.append("\" target=\"_blank\">");
		sb.append(HtmlUtil.escape(twitterSn));
		sb.append("</a>");
		%>

		<liferay-ui:message arguments="<%= sb.toString() %>" key="your-twitter-screen-name-is-x" translateArguments="<%= false %>" />
	</c:when>
	<c:otherwise>

		<%
		String taglibConfigureHREF = "javascript:Liferay.Util.openWindow({id: '" + renderResponse.getNamespace() + "configureTwitter', title: '" + HtmlUtil.escapeJS(LanguageUtil.get(request, "my-account")) + "', uri: '" + HtmlUtil.escapeJS(themeDisplay.getURLMyAccount() + "#_" + PortletKeys.MY_ACCOUNT + "_tab=_" + PortletKeys.MY_ACCOUNT + "_socialNetwork") + "'});";
		%>

		<div class="alert alert-info">
			<a href="<%= taglibConfigureHREF %>"><liferay-ui:message key="please-configure-your-twitter-screen-name" /></a>
		</div>
	</c:otherwise>
</c:choose>