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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean accountCustomer = (Boolean)request.getAttribute("edit_ticket_entry.jsp-accountCustomer");
boolean canForward = (Boolean)request.getAttribute("edit_ticket_entry.jsp-canForward");
boolean clockedIn = (Boolean)request.getAttribute("edit_ticket_entry.jsp-clockedIn");
boolean closed = (Boolean)request.getAttribute("edit_ticket_entry.jsp-closed");
boolean edit = (Boolean)request.getAttribute("edit_ticket_entry.jsp-edit");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
boolean hasUpdateBasic = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateBasic");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

int resolution = BeanParamUtil.getInteger(ticketEntry, request, "resolution");
int status = BeanParamUtil.getInteger(ticketEntry, request, "status");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");
%>

<div class="button-row">
	<c:choose>
		<c:when test="<%= edit %>">
			<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />
		</c:when>
		<c:when test="<%= hasUpdateBasic %>">
			<c:if test="<%= !screenShareMode && hasUpdateAdvanced && (!closed || liferayIncOrg) %>">
				<input class="aui-button-input" onClick="location.href = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /><portlet:param name="edit" value="true" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="edit" />" />
			</c:if>

			<c:if test="<%= accountCustomer && closed && (resolution == TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER) %>">
				<input class="aui-button-input" onClick="location.href = '<portlet:actionURL name="closeTicketEntry"><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /><portlet:param name="resolution" value="<%= String.valueOf(TicketEntryConstants.RESOLUTION_COMPLETED) %>" /></portlet:actionURL>';" type="button" value="<liferay-ui:message key="complete-ticket" />" />
			</c:if>

			<c:if test="<%= closed || (accountCustomer && (ticketEntry.getStatus() == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION)) %>">
				<portlet:actionURL name="reopenTicketEntry" var="reopenURL">
					<portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
				</portlet:actionURL>

				<input class="aui-button-input" onClick="javascript:if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-reopen-this-ticket") %>')) { location.href='<%= reopenURL %>'; } else { self.focus(); }" type="button" value="<liferay-ui:message key="reopen" />" />
			</c:if>
		</c:when>
		<c:when test="<%= !clockedIn %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="you-are-not-clocked-in-and-will-not-be-assigned-tickets-to-change-this-setting-please-contact-your-support-manager" />
			</div>
		</c:when>
	</c:choose>

	<c:if test="<%= !closed || (hasUpdateAdvanced && liferayIncOrg) %>">
		<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT) %>">
			<input class="aui-button-input" onClick="location.href = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/edit_ticket_entry/edit_ticket_attachments.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="manage-attachments" />" />
		</c:if>

		<c:if test="<%= !screenShareMode && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_LINK) %>">
			<input class="aui-button-input" onClick="location.href = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/edit_ticket_entry/edit_ticket_links.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="manage-links" />" />
		</c:if>
	</c:if>

	<c:if test="<%= !closed %>">
		<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ASSIGN_WORKERS) %>">
			<portlet:renderURL var="assignWorkersURL">
				<portlet:param name="mvcPath" value="/support/edit_ticket_entry/edit_ticket_workers.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
			</portlet:renderURL>

			<input class="aui-button-input" onClick="location.href = '<%= HtmlUtil.escape(assignWorkersURL) %>';" type="button" value="<liferay-ui:message key="assign-workers" />" />
		</c:if>

		<c:if test="<%= !screenShareMode && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ESCALATE) %>">
			<input class="aui-button-input" onClick="var escalateTicketWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/escalate_ticket_entry.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'escalate', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); escalateTicketWindow.focus();" type="button" value="<liferay-ui:message key="escalate" />" />
		</c:if>

		<c:if test="<%= !screenShareMode && hasUpdateAdvanced && ticketWorker && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_SOLUTION_PROPOSED) && ArrayUtil.contains(TicketEntryConstants.STATUSES_WORKER_OPEN, ticketEntry.getStatus()) %>">
			<input class="aui-button-input" onClick="var solutionDetailsWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/edit_ticket_entry/solution_details.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'solutionDetailsWindow', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); solutionDetailsWindow.focus();" type="button" value="<liferay-ui:message key="propose-solution" />" />
		</c:if>

		<c:if test="<%= !screenShareMode && hasUpdateAdvanced && ticketWorker && (ticketEntry.getStatus() == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) %>">
			<input class="aui-button-input" onClick="var solutionDetailsWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/edit_ticket_entry/solution_details.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'solutionDetailsWindow', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); solutionDetailsWindow.focus();" type="button" value="<liferay-ui:message key="propose-new-solution" />" />
		</c:if>

		<%
		boolean hasCloseTicketPermission = false;

		if (((status != TicketEntryConstants.STATUS_RESOLVED) && (status != TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) &&
			(((status == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && hasUpdateAdvanced && ticketWorker) || (!hasUpdateAdvanced && (ticketEntry.getUserId() == user.getUserId())) || (hasUpdateBasic && accountCustomer))) {

			hasCloseTicketPermission = true;
		}
		%>

		<c:if test="<%= hasCloseTicketPermission %>">
			<input class="aui-button-input" onClick="var closeTicketWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/select_resolution.jsp" /></portlet:renderURL>', 'resolution', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); closeTicketWindow.focus();" type="button" value="<liferay-ui:message key="close-ticket" />" />
		</c:if>
	</c:if>

	<c:if test="<%= !screenShareMode && liferayIncOrg %>">
		<input class="aui-button-input" onClick="var ticketCallWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/edit_ticket_call.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'ticketCallWindow', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); ticketCallWindow.focus();" type="button" value="<liferay-ui:message key="log-call" />" />
	</c:if>

	<c:if test="<%= edit %>">
		<input class="aui-button-input" onClick="location.href = '<%= portletURL.toString() %>';" type="button" value="<liferay-ui:message key="cancel" />" />
	</c:if>

	<c:if test="<%= !screenShareMode && !edit && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.WATCH) %>">

		<%
		boolean subscribed = SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), TicketEntry.class.getName(), ticketEntry.getTicketEntryId());
		%>

		<portlet:actionURL name="watchTicket" var="watchTicketURL">
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
			<portlet:param name="watchTicket" value="<%= String.valueOf(!subscribed) %>" />
		</portlet:actionURL>

		<input class="aui-button-input fr" onClick="location.href = '<%= watchTicketURL %>';" type="button" value="<%= !subscribed ? LanguageUtil.get(pageContext, "watch-ticket") : LanguageUtil.get(pageContext, "unwatch-ticket") %>" />

		<c:if test="<%= liferayIncOrg %>">
			<div class="bottom-row">
				<select id="<portlet:namespace />issueType" name="<portlet:namespace />issueType" onChange="<portlet:namespace />checkIssueType();">
					<option value=""><liferay-ui:message key="select-issue-type" /></option>
					<option value="3"><liferay-ui:message key="task" /></option>
					<option value="11"><liferay-ui:message key="patch" /></option>
					<option value="46"><liferay-ui:message key="l1-escalation" /></option>
				</select>

				<input disabled class="aui-button-input" id="<portlet:namespace />jiraButton" onClick="<portlet:namespace />openJIRALink();" type="button" value="<liferay-ui:message key="create-lpp-ticket" />" />

				<c:if test="<%= canForward && !edit && clockedIn %>">
					<input class="aui-button-input fr" id="<portlet:namespace />platCritForwardButton" onclick="<portlet:namespace />forwardTicketEntry();" type="button" value="<liferay-ui:message key="forward" />" />
				</c:if>
			</div>
		</c:if>
	</c:if>
</div>

<aui:script>
	<c:if test="<%= liferayIncOrg %>">
		function <portlet:namespace />checkIssueType() {
			var issueType = document.getElementById("<portlet:namespace />issueType");
			var jiraButton = document.getElementById("<portlet:namespace />jiraButton");

			if (issueType.value != '') {
				jiraButton.disabled = false;
			}
			else {
				jiraButton.disabled = true;
			}
		}

		function <portlet:namespace />openJIRALink() {
			var issueType = document.getElementById("<portlet:namespace />issueType").value;

			window.open('<%= SupportUtil.createJIRAIssueURL(request, ticketEntry) %>&issuetype=' + issueType);
		}
	</c:if>
</aui:script>