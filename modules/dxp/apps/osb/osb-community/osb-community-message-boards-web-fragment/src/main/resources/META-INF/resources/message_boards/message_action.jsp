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

<%@ include file="/message_boards/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

MBMessage message = (MBMessage)objArray[0];

Set<Long> threadSubscriptionClassPKs = (Set<Long>)request.getAttribute("view.jsp-threadSubscriptionClassPKs");

MBCategory category = message.getCategory();
MBThread thread = message.getThread();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= MBMessagePermission.contains(permissionChecker, message, ActionKeys.UPDATE) && !thread.isLocked() %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_message" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, message.getGroupId(), message.getCategoryId(), ActionKeys.MOVE_THREAD) && !thread.isLocked() %>">
		<portlet:renderURL var="moveThreadURL">
			<portlet:param name="mvcRenderCommandName" value="/message_boards/move_thread" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="mbCategoryId" value="<%= String.valueOf(category.getCategoryId()) %>" />
			<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="move"
			url="<%= moveThreadURL %>"
		/>
	</c:if>

	<c:if test="<%= MBCategoryPermission.contains(permissionChecker, message.getGroupId(), message.getCategoryId(), ActionKeys.LOCK_THREAD) %>">
		<c:choose>
			<c:when test="<%= thread.isLocked() %>">
				<portlet:actionURL name="/message_boards/edit_message" var="unlockThreadURL">
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNLOCK %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="unlock-thread"
					url="<%= unlockThreadURL %>"
				/>
			</c:when>
			<c:otherwise>
				<portlet:actionURL name="/message_boards/edit_message" var="lockThreadURL">
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.LOCK %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
					<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="lock-thread"
					url="<%= lockThreadURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= portletName.equals(MBPortletKeys.MESSAGE_BOARDS) && enableRSS && MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW) %>">
		<liferay-ui:rss
			delta="<%= rssDelta %>"
			displayStyle="<%= rssDisplayStyle %>"
			feedType="<%= rssFeedType %>"
			url="<%= MBUtil.getRSSURL(plid, 0, message.getThreadId(), 0, themeDisplay) %>"
		/>
	</c:if>

	<c:if test="<%= MBMessagePermission.contains(permissionChecker, message, ActionKeys.SUBSCRIBE) && (mbGroupServiceSettings.isEmailMessageAddedEnabled() || mbGroupServiceSettings.isEmailMessageUpdatedEnabled()) %>">
		<c:choose>
			<c:when test="<%= (threadSubscriptionClassPKs != null) && threadSubscriptionClassPKs.contains(message.getThreadId()) %>">
				<portlet:actionURL name="/message_boards/edit_message" var="unsubscribeURL">
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="unsubscribe"
					url="<%= unsubscribeURL %>"
				/>
			</c:when>
			<c:otherwise>
				<portlet:actionURL name="/message_boards/edit_message" var="subscribeURL">
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="subscribe"
					url="<%= subscribeURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= MBMessagePermission.contains(permissionChecker, message, ActionKeys.PERMISSIONS) && !thread.isLocked() %>">
		<liferay-security:permissionsURL
			modelResource="<%= MBMessage.class.getName() %>"
			modelResourceDescription="<%= message.getSubject() %>"
			resourcePrimKey="<%= String.valueOf(message.getMessageId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>
</liferay-ui:icon-menu>