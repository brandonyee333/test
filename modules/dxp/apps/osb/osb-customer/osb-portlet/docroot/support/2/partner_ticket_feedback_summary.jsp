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

<%@ include file="/support/2/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean closed = (Boolean)request.getAttribute("edit_ticket_entry.jsp-closed");

TicketFeedback ticketFeedback = null;

if (ticketEntry != null) {
	ticketFeedback = TicketFeedbackServiceUtil.fetchFirstTicketFeedback(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_PARTNER);
}
%>

<c:if test="<%= ticketFeedback != null %>">
	<h1 class="section-heading">
		<liferay-ui:message key="partner-ticket-feedback" />
	</h1>

	<c:choose>
		<c:when test="<%= ticketFeedback.getStatus() == TicketFeedbackConstants.STATUS_UNANSWERED %>">
			<div>
				<span class="txt-b">
					<liferay-ui:message key="no-feedback-was-provided" />
				</span>
			</div>
		</c:when>
		<c:otherwise>
			<div class="aui-helper-clearfix">
				<div class="aui-w50 content-column">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up">
							<liferay-ui:message key="reproducible" />:
						</span>

						<span>
							 <%= LanguageUtil.get(pageContext, ticketFeedback.getAnswer1Label()) %>
						</span>
					</div>
				</div>
				<div class="aui-w50 content-column">
					<div class="content-column-content right-column">
						<span class="txt-b txt-up">
							<liferay-ui:message key="timely-escalated" />:
						</span>

						<span>
							<%= LanguageUtil.get(pageContext, ticketFeedback.getAnswer2Label()) %>
						</span>
					</div>
				</div>
			</div>

			<div class="aui-helper-clearfix">
				<div class="aui-w50 content-column">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up">
							<liferay-ui:message key="escalated-properly" />:
						</span>

						<span>
							<%= LanguageUtil.get(pageContext, ticketFeedback.getAnswer3Label()) %>
						</span>

						<br />

						<span class="txt-b txt-up">
							<liferay-ui:message key="response-time" />:
						</span>

						<span>
							<%= ticketFeedback.getRating1() %> - <%= LanguageUtil.get(pageContext, ticketFeedback.getRating1Label()) %>
						</span>
					</div>
				</div>

				<div class="aui-w50 content-column">
					<div class="content-column-content right-column">
						<span class="txt-b txt-up">
							<liferay-ui:message key="technical-knowledge" />:
						</span>

						<span>
							<%= ticketFeedback.getRating2() %> - <%= LanguageUtil.get(pageContext, ticketFeedback.getRating2Label()) %>
						</span>

						<br />

						<span class="txt-b txt-up">
							<liferay-ui:message key="professionalism" />:
						</span>

						<span>
							<%= ticketFeedback.getRating3() %> - <%= LanguageUtil.get(pageContext, ticketFeedback.getRating3Label()) %>
						</span>
					</div>
				</div>
			</div>

			<div>
				<pre><%= HtmlUtil.escape(ticketFeedback.getComments()) %></pre>
			</div>
		</c:otherwise>
	</c:choose>

	<c:if test="<%= liferayIncOrg && OSBTicketFeedbackPermission.contains(permissionChecker, ticketFeedback, OSBActionKeys.UPDATE) %>">
		<br />

		<div>
			<c:if test="<%= !closed %>">
				<c:choose>
					<c:when test="<%= ticketFeedback.getStatus() == TicketFeedbackConstants.STATUS_UNANSWERED %>">
						<input class="aui-button-input" onClick="location.href='<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/edit_partner_ticket_feedback.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="ticketFeedbackId" value="<%= String.valueOf(ticketFeedback.getTicketFeedbackId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="submit-feedback" />" />
					</c:when>
					<c:otherwise>
						<input class="aui-button-input" onClick="location.href='<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/edit_partner_ticket_feedback.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="ticketFeedbackId" value="<%= String.valueOf(ticketFeedback.getTicketFeedbackId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="edit-feedback" />" />
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</c:if>
</c:if>