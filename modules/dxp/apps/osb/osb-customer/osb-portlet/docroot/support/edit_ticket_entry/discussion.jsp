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

<%@ include file="/support/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "public");

String redirect = ParamUtil.getString(request, "redirect");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_ticket_entry.jsp-accountEntry");
boolean hasUpdateAdmin = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdmin");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

String commentBody = ParamUtil.getString(request, "commentBody0");

int[] userVisibilities = null;

if (screenShareMode) {
	userVisibilities = new int[] {VisibilityConstants.PUBLIC};
}
else {
	userVisibilities = TicketCommentLocalServiceUtil.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
}
%>

<c:if test="<%= userVisibilities.length > 0 %>">

	<%
	String[] tabs1Names = new String[userVisibilities.length];
	String[] tabs1Values = new String[userVisibilities.length];

	for (int i = 0; i < userVisibilities.length; i++) {
		int visibility = userVisibilities[i];

		String visibilityLabel = VisibilityConstants.toLabel(visibility);

		int ticketCommentCount = TicketCommentLocalServiceUtil.getTicketCommentsCount(ticketEntry.getTicketEntryId(), new int[] {visibility}, new int[] {WorkflowConstants.STATUS_APPROVED});

		tabs1Names[i] = LanguageUtil.format(pageContext, visibilityLabel + "-x", ticketCommentCount, false);
		tabs1Values[i] = visibilityLabel;
	}

	if (!screenShareMode) {
		if (tabs1Values.length > 1) {
			tabs1Names = ArrayUtil.append(tabs1Names, "all-comments");
			tabs1Values = ArrayUtil.append(tabs1Values, "all-comments");
		}

		if (ticketWorker) {
			tabs1Names = ArrayUtil.append(tabs1Names, "history");
			tabs1Values = ArrayUtil.append(tabs1Values, "history");

			tabs1Names = ArrayUtil.append(tabs1Names, "solutions");
			tabs1Values = ArrayUtil.append(tabs1Values, "solutions");
		}
	}

	if (!ArrayUtil.contains(tabs1Values, tabs1)) {
		tabs1 = tabs1Values[0];
	}

	PortletURL tabs1URL = renderResponse.createRenderURL();

	tabs1URL.setParameter("scroll", renderResponse.getNamespace() + "tabs_top");
	tabs1URL.setParameter("mvcPath", "/support/edit_ticket_entry.jsp");
	tabs1URL.setParameter("tabs1", tabs1);
	tabs1URL.setParameter("redirect", redirect);
	tabs1URL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
	%>

	<div class="hr"></div>

	<a id="<portlet:namespace />tabs_top" name="<portlet:namespace />tabs_top"></a>

	<c:if test="<%= tabs1Names.length > 1 %>">
		<liferay-ui:tabs
			names="<%= StringUtil.merge(tabs1Names) %>"
			portletURL="<%= tabs1URL %>"
			tabsValues="<%= StringUtil.merge(tabs1Values) %>"
		/>
	</c:if>

	<div id="<portlet:namespace />commentsContainer">
		<c:choose>
			<c:when test='<%= tabs1.equals("history") %>'>
				<%@ include file="/support/edit_ticket_entry/discussion_history.jspf" %>
			</c:when>
			<c:when test='<%= tabs1.equals("solutions") %>'>
				<%@ include file="/support/edit_ticket_entry/discussion_solutions.jspf" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/support/edit_ticket_entry/discussion_comments.jspf" %>
			</c:otherwise>
		</c:choose>
	</div>

	<%
	boolean showLiferayCommentFlag = false;

	if (TicketFlagLocalServiceUtil.hasTicketFlag(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD, TicketFlagConstants.FLAG_TRUE)) {
		TicketWorker latestTicketWorker = TicketWorkerLocalServiceUtil.fetchLatestTicketWorker(ticketEntry.getTicketEntryId());

		if (((latestTicketWorker != null) && (latestTicketWorker.getUserId() == user.getUserId())) || SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER, ticketEntry.getSupportRegionId(), null) || SupportWorkerLocalServiceUtil.isManagerOfWorker(user.getUserId(), latestTicketWorker.getUserId())) {
			if (tabs1.equals("liferay") && (latestTicketWorker != null) && (latestTicketWorker.getUserId() == user.getUserId())) {
				TicketFlagLocalServiceUtil.deleteTicketFlags(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD, TicketFlagConstants.FLAG_TRUE);
			}
			else {
				showLiferayCommentFlag = true;
			}
		}
	}
	%>

	<c:if test="<%= showLiferayCommentFlag %>">
		<span class="aui-helper-hidden exclamation-notification" id="<portlet:namespace />liferayCommentNotification" style="position: absolute; z-index: 11;">!</span>
	</c:if>

	<aui:script>
		<c:if test="<%= showLiferayCommentFlag %>">
			AUI().ready(
				function() {
					var A = AUI();

					var liferayTab = A.one("#<portlet:namespace />tabs1<%= StringUtil.toCharCode("liferay") %>TabsId");

					var notification = A.one("#<portlet:namespace />liferayCommentNotification");

					notification.setStyle('left', (liferayTab.get('offsetLeft') + (liferayTab.get('offsetWidth') - 25)) + "px");
					notification.setStyle('top', (liferayTab.get('offsetTop') - 15) + "px");

					notification.show();
				}
			);
		</c:if>

		function <portlet:namespace />toggleAll(collapse) {
			var A = AUI();

			var commentsContainer = A.one('#<portlet:namespace />commentsContainer');

			commentsContainer.all('.comment').each(
				function(item, index, collection) {
					if (collapse) {
						item.set('className', item.get('className').replace('comment-expanded', 'comment-collapsed'));
					}
					else {
						item.set('className', item.get('className').replace('comment-collapsed', 'comment-expanded'));
					}
				}
			);
		}

		function <portlet:namespace />toggleComment(event, i) {
			var comment = document.getElementById("<portlet:namespace />comment" + i);

			var box = comment.getBoundingClientRect();

			var offsetY = event.clientY - box.top;

			if (offsetY > 50) {
				return false;
			}

			var text = document.all ? document.selection.createRange().text : document.getSelection();

			if (text == '') {
				if (comment.className.indexOf('comment-collapsed') == -1) {
					comment.className = comment.className.replace('comment-expanded', 'comment-collapsed');
				}
				else {
					comment.className = comment.className.replace('comment-collapsed', 'comment-expanded');
				}
			}
		}

		function <portlet:namespace />toggleCommentStyle(event, comment) {
			var box = comment.getBoundingClientRect();

			var offsetY = event.clientY - box.top;

			if (offsetY > 50) {
				comment.style.cursor = '';
			}
			else {
				comment.style.cursor = 'pointer';
			}
		}
	</aui:script>
</c:if>