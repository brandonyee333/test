<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error exception="<%= ModelListenerException.class %>">

	<%
	ModelListenerException mle = (ModelListenerException)errorException;
	%>

	<%= HtmlUtil.escape(mle.getLocalizedMessage()) %>
</liferay-ui:error>

<liferay-ui:error key="com.liferay.osb.customer.zendesk.listeners.exception.ZendeskIntegrationException">

	<%
	ModelListenerException mle = (ModelListenerException)errorException;

	String message = mle.getLocalizedMessage();

	if (message.contains("Server returned status 429")) {
		message = LanguageUtil.get(request, "zendesk-rate-limit-exceeded");
	}
	%>

	<%= HtmlUtil.escape(message) %>
</liferay-ui:error>