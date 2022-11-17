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

<%@ include file="/html/portal/init.jsp" %>

<%
	String redirect = ParamUtil.getString(request, "redirect");

	JSONObject samlSsoLoginContext = (JSONObject)request.getAttribute("SAML_SSO_LOGIN_CONTEXT");

	JSONArray relevantIdpConnectionsJSONArray = samlSsoLoginContext.getJSONArray("relevantIdpConnections");
%>

<aui:form action='<%= PortalUtil.getPortalURL(request) + PortalUtil.getPathMain() + "/portal/login" %>' method="get" name="fm" style="padding: 1rem;">
	<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<c:choose>
		<c:when test="<%= relevantIdpConnectionsJSONArray.length() == 1 %>">

			<%
				JSONObject relevantIdpConnectionJSONObject = relevantIdpConnectionsJSONArray.getJSONObject(0);
			%>

			<aui:input name="idpEntityId" type="hidden" value='<%= HtmlUtil.escapeAttribute(relevantIdpConnectionJSONObject.getString("entityId")) %>' />

			<aui:script sandbox="<%= true %>">
				window.addEventListener("load", (event) => {
				window.fm.submit();
				});
			</aui:script>
		</c:when>
		<c:when test="<%= relevantIdpConnectionsJSONArray.length() > 1 %>">
			<p><liferay-ui:message key="please-select-your-identity-provider" /></p>

			<liferay-util:include page="/html/portal/select_idp.jsp" />
		</c:when>
		<c:otherwise>
			<liferay-ui:message key="no-identity-provider-is-available-to-sign-you-in" />
		</c:otherwise>
	</c:choose>
</aui:form>

<c:if test="<%= Validator.isNotNull(redirect) %>">
	<aui:script sandbox="<%= true %>">
		var form = document.getElementById('<portlet:namespace />fm');

		var redirect = form.querySelector('#<portlet:namespace />redirect');

		if (redirect) {
		var redirectVal = redirect.getAttribute('value');

		redirect.setAttribute('value', redirectVal + window.location.hash);
		}
	</aui:script>
</c:if>
