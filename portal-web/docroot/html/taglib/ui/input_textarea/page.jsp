<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-textarea:cssClass"));
String param = (String)request.getAttribute("liferay-ui:input-textarea:param");
String paramId = (String)request.getAttribute("liferay-ui:input-textarea:paramId");
String defaultValue = (String)request.getAttribute("liferay-ui:input-textarea:defaultValue");
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-textarea:disabled"));

String value = ParamUtil.getString(request, param, defaultValue);
%>

<textarea class="form-control lfr-textarea <%= cssClass %>" <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= namespace %><%= paramId %>" name="<%= namespace %><%= param %>" wrap="soft" onKeyDown="Liferay.Util.disableEsc();"><%= HtmlUtil.escape(value) %></textarea>

<aui:script use="aui-char-counter">
	new A.CharCounter(
		{
			input: '#<%= namespace %><%= paramId %>',
			maxLength: <%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>
		}
	);
</aui:script>