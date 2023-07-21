<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)row.getObject();
%>

<portlet:actionURL name="deleteWSRPConsumerPortlet" var="deleteURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="wsrpConsumerPortletId" value="<%= String.valueOf(wsrpConsumerPortlet.getWsrpConsumerPortletId()) %>" />
</portlet:actionURL>

<liferay-ui:icon-delete
	icon="trash"
	linkCssClass="icon-monospaced text-default"
	message="delete"
	showIcon="<%= true %>"
	url="<%= deleteURL %>"
/>