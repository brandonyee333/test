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

<%@ include file="/html/portal/saml/saml_login.jsp" %>

<aui:select label="identity-provider" name="idpEntityId">

	<%
	for (int i = 0; i < relevantIdpConnectionsJSONArray.length(); i++) {
		JSONObject relevantIdpConnectionJSONObject = relevantIdpConnectionsJSONArray.getJSONObject(i);

		String entityId = relevantIdpConnectionJSONObject.getString("entityId");
		String name = relevantIdpConnectionJSONObject.getString("name");
	%>

		<aui:option label="<%= HtmlUtil.escape(name) %>" value="<%= HtmlUtil.escapeAttribute(entityId) %>" />

	<%
	}
	%>

</aui:select>

<aui:fieldset>
	<aui:button-row>
		<aui:button type="submit" value="sign-in" />
	</aui:button-row>
</aui:fieldset>
