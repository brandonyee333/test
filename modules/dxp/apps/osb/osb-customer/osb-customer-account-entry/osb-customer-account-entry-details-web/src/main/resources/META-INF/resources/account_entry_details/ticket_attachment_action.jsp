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