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

<%@ include file="/com.liferay.message.boards.web/init.jsp" %>

<%
MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE_DISPLAY);

MBCategory category = messageDisplay.getCategory();
%>

<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">
	<div class="spam" style="margin: 10px;">
		<c:choose>
			<c:when test="<%= spam %>">
				<portlet:actionURL name="/message_boards/edit_message" var="notSpamURL">
					<portlet:param name="<%= Constants.CMD %>" value="updateStatus" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
					<portlet:param name="spam" value="<%= String.valueOf(Boolean.FALSE) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../mail/compose"
					label="<%= true %>"
					message="not-spam"
					url="<%= notSpamURL %>"
				/>
			</c:when>
			<c:otherwise>
				<portlet:renderURL var="parentCategoryURL">
					<c:choose>
						<c:when test="<%= (category == null) || (category.getCategoryId() == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) %>">
							<portlet:param name="mvcRenderCommandName" value="/message_boards/view" />
						</c:when>
						<c:otherwise>
							<portlet:param name="mvcRenderCommandName" value="/message_boards/view_category" />
							<portlet:param name="mbCategoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
						</c:otherwise>
					</c:choose>
				</portlet:renderURL>

				<portlet:actionURL name="/message_boards/edit_message" var="markAsSpamURL">
					<portlet:param name="<%= Constants.CMD %>" value="updateStatus" />
					<portlet:param name="redirect" value="<%= parentCategoryURL %>" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
					<portlet:param name="spam" value="<%= String.valueOf(Boolean.TRUE) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../mail/delete"
					label="<%= true %>"
					message="mark-as-spam"
					url="<%= markAsSpamURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>

<c:if test="<%= !message.isApproved() && (message.getUserId() == themeDisplay.getUserId()) %>">
	<span class="h5 text-default">
		<div class="alert alert-danger" role="alert">
			<strong class="lead"><liferay-ui:message key="status" />: <aui:workflow-status markupView="lexicon" showIcon="<%= true %>" showLabel="<%= false %>" status="<%= message.getStatus() %>" /></strong>

			<p>
				<liferay-ui:message key="your-message-has-been-flagged-as-spam" />
			</p>
		</div>
	</span>
</c:if>