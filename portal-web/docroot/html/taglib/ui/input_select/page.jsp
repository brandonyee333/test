<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-select:cssClass"));
String param = (String)request.getAttribute("liferay-ui:input-select:param");
Boolean defaultValue = (Boolean)request.getAttribute("liferay-ui:input-select:defaultValue");
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-select:disabled"));

boolean value = ParamUtil.getBoolean(request, param, defaultValue.booleanValue());
%>

<select class="form-control <%= cssClass %>" <%= disabled ? "disabled=\"disabled\"" : "" %> name="<%= namespace %><%= param %>">
	<option <%= value ? "selected" : "" %> value="1"><liferay-ui:message key="yes" /></option>
	<option <%= !value ? "selected" : "" %> value="0"><liferay-ui:message key="no" /></option>
</select>