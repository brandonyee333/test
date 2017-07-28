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
long ticketSolutionId = ParamUtil.getLong(request, "ticketSolutionId");

TicketSolution ticketSolution = TicketSolutionLocalServiceUtil.getTicketSolution(ticketSolutionId);
%>

<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketSolution.getTicketEntryId(), OSBActionKeys.VIEW) %>">
	<div>
		<div class="callout-content">
			<strong><liferay-ui:message key="issue-summary" />:</strong>

			<div class="solution-issue-summary">
				<pre><%= SupportUtil.getHTML(ticketSolution.getSummary(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
			</div>

			<br />

			<strong><liferay-ui:message key="solution" />:</strong>

			<div class="solution-confirm-comment">
				<pre><%= SupportUtil.getHTML(ticketSolution.getSolution(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
			</div>

			<%
			List<TicketAttachment> ticketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(ticketSolution.getTicketEntryId(), ticketSolution.getTicketSolutionId());

			for (TicketAttachment ticketAttachment : ticketAttachments) {
				LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

				ticketAttachmentURL.setCopyCurrentRenderParameters(false);
				ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
				ticketAttachmentURL.setResourceID("ticketAttachment");
			%>

				<div class="callout-content">
					<strong><liferay-ui:message key="attachments" />:</strong>

					<a href="<%= ticketAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(ticketAttachment.getFileName()) %></a> (<%= TextFormatter.formatKB((double)ticketAttachment.getFileSize(), locale) %>k)
				</div>

			<%
			}

			List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketSolution.getTicketEntryId(), ticketSolution.getTicketSolutionId());

			for (TicketLink ticketLink : ticketLinks) {
			%>

				<div class="callout-content">
					<strong><liferay-ui:message key="links" />:</strong>

					<a href="<%= ticketLink.getUrl() %>"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>
				</div>

			<%
			}
			%>

		</div>

		<br />

		<div class="solution-confirm-question">
			<span class="txt-b"><liferay-ui:message key="what-is-the-reason-for-rejecting-the-ticket-solution" /></span>

			<div>
				<select id="<portlet:namespace />statusReason" name="<portlet:namespace />statusReason">

					<%
					for (int statusReason : TicketSolutionConstants.STATUS_REASONS) {
					%>

						<option value="<%= statusReason %>"><%= LanguageUtil.get(pageContext, TicketSolutionConstants.getStatusReasonLabel(statusReason)) %></option>

					<%
					}
					%>

				</select>
			</div>

			<span class="highlighted-flag">*</span>

			<span class="txt-b"><liferay-ui:message key="please-explain" /></span>

			<div id="<portlet:namespace />commentsContainer">
				<liferay-util:include page="/support/bbcode_editor.jsp" servletContext="<%= application %>">
					<liferay-util:param name="content" value="" />
					<liferay-util:param name="editorId" value="editor0" />
					<liferay-util:param name="name" value="statusMessage" />
				</liferay-util:include>
			</div>
		</div>

		<div class="solution-confirm-answer">
			<input class="btn btn-yes solution-confirm-btn" onClick="<portlet:namespace />rejectTicketSolution();" type="button" value="<liferay-ui:message key="send" />" />

			<input class="btn solution-confirm-btn style-a" onClick="window.location.reload();" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</div>

	<aui:script>
		function <portlet:namespace />rejectTicketSolution() {
			var statusReason = document.getElementById("<portlet:namespace />statusReason");
			var statusMessage = document.getElementById("<portlet:namespace />statusMessage");

			<portlet:namespace />updateTicketSolution('<%= TicketSolutionConstants.STATUS_UNRESOLVED %>', statusReason.value, statusMessage.value);
		}
	</aui:script>
</c:if>