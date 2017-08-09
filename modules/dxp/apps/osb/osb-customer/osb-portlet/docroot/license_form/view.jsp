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
OrderEntry orderEntry = null;

String orderUuid = portletPreferences.getValue("orderUuid", StringPool.BLANK);

if (Validator.isNotNull(orderUuid)) {
	try {
		orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(orderUuid);
	}
	catch (Exception e) {
	}
}

int productVersion = GetterUtil.getInteger(portletPreferences.getValue("productVersion", StringPool.BLANK));
%>

<c:choose>
	<c:when test='<%= SessionMessages.contains(request, "licenseKeySent") %>'>
		<p>
			<liferay-ui:message key="thank-you" /> <%= LanguageUtil.format(request, "your-license-key-was-sent-to-x", SessionMessages.get(request, "licenseKeySent")) %>
		</p>

		<a href="/"><liferay-ui:message arguments='<%= "Liferay.com" %>' key="back-to-x" /></a>
	</c:when>
	<c:when test="<%= (orderEntry != null) && (productVersion > 0) %>">
		<liferay-util:include page="/license_form/form.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="portlet-msg-info">
			<liferay-ui:message key="please-configure-the-order-uuid-and-product-version" />
		</div>
	</c:otherwise>
</c:choose>