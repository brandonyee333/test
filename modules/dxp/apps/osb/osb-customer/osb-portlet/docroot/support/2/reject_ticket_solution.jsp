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
long ticketSolutionId = ParamUtil.getLong(request, "ticketSolutionId");

TicketSolution ticketSolution = TicketSolutionLocalServiceUtil.getTicketSolution(ticketSolutionId);
%>

<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketSolution.getTicketEntryId(), OSBActionKeys.VIEW) %>">
	<div class="solution-confirm-container">
		<div class="field-group">
			<label><liferay-ui:message key="issue-summary" />:</label>

			<div class="solution-issue-summary">
				<pre><%= SupportUtil.getHTML(ticketSolution.getSummary(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
			</div>
		</div>

		<div class="field-group">
			<label><liferay-ui:message key="solution" />:</label>

			<div class="solution-confirm-comment">
				<pre><%= SupportUtil.getHTML(ticketSolution.getSolution(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
			</div>
		</div>

		<%
		List<TicketAttachment> ticketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(ticketSolution.getTicketEntryId(), ticketSolution.getTicketSolutionId());

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			ticketAttachmentURL.setCopyCurrentRenderParameters(false);
			ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
			ticketAttachmentURL.setResourceID("ticketAttachment");
		%>

			<div class="field-group">
				<label><liferay-ui:message key="attachments" />:</label>

				<a href="<%= ticketAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(ticketAttachment.getFileName()) %></a> (<%= TextFormatter.formatStorageSize((double)ticketAttachment.getFileSize(), locale) %>k)
			</div>

		<%
		}

		List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketSolution.getTicketEntryId(), ticketSolution.getTicketSolutionId());

		for (TicketLink ticketLink : ticketLinks) {
		%>

			<div class="field-group">
				<label><liferay-ui:message key="links" />:</label>

				<a href="<%= ticketLink.getUrl() %>"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>
			</div>

		<%
		}
		%>

		<div class="field-group">
			<aui:select label="what-is-the-reason-for-rejecting-the-ticket-solution" name="statusReason">

				<%
				for (int statusReason : TicketSolutionConstants.STATUS_REASONS) {
				%>

					<aui:option label="<%= TicketSolutionConstants.getStatusReasonLabel(statusReason) %>" value="<%= statusReason %>" />

				<%
				}
				%>

			</aui:select>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />statusMessageLabel"><liferay-ui:message key="please-explain" /></label>

			<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="" />
				<liferay-util:param name="editorId" value="editor0" />
				<liferay-util:param name="name" value="statusMessage" />
			</liferay-util:include>
		</div>

		<div align="right">
			<aui:button cssClass="aui-button-input buttons" onClick='<%= renderResponse.getNamespace() + "rejectTicketSolution();" %>' value="send" />

			<aui:button cssClass="aui-button-input buttons fl" onClick='<%= renderResponse.getNamespace() + "closePopup();" %>' value="cancel" />
		</div>
	</div>

	<aui:script>
		function <portlet:namespace />closePopup() {
			document.getElementById('closethick').click();
		}

		function <portlet:namespace />rejectTicketSolution() {
			var statusMessage = document.getElementById('<portlet:namespace />statusMessage');
			var statusReason = document.getElementById('<portlet:namespace />statusReason');

			<portlet:namespace />updateTicketSolution('<%= TicketSolutionConstants.STATUS_UNRESOLVED %>', statusReason.value, statusMessage.value);
		}
	</aui:script>
</c:if>