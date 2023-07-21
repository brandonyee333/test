<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TicketAttachment ticketAttachment = (TicketAttachment)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= TicketAttachmentPermissionChecker.contains(permissionChecker, ticketAttachment, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="removeRegionRestrictionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="/remove_region_restriction" />
			<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit-access"
			url="<%= removeRegionRestrictionURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= TicketAttachmentPermissionChecker.contains(permissionChecker, ticketAttachment, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteTicketAttachment" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>