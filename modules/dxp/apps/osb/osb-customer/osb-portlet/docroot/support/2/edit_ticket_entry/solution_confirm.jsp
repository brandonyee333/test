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

boolean accountCustomer = (Boolean)request.getAttribute("edit_ticket_entry.jsp-accountCustomer");
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_ticket_entry.jsp-accountEntry");
boolean partnerWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-partnerWorker");
TicketSolution ticketSolution = (TicketSolution)request.getAttribute("details_tabs.jsp-ticketSolution");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

boolean hasUpdateTicketSolution = accountCustomer || (partnerWorker && PartnerWorkerLocalServiceUtil.hasPartnerWorker(ticketEntry.getUserId(), accountEntry.getPartnerEntryId()));
%>

<liferay-ui:error exception="<%= TicketSolutionStatusMessageException.class %>" message="please-enter-a-valid-rejection-comment" />

<div class="solution-confirm-container">
	<c:if test="<%= ticketWorker %>">
		<div>
			<strong><liferay-ui:message key="issue-summary" />:</strong>

			<liferay-ui:message key='<%= ticketSolution.isUseCustomerSummary() ? "customers-description-accurately-captures-the-issue" : "customers-description-does-not-accurately-capture-the-issue" %>' />

			<br />

			<div class="solution-issue-summary">
				<pre><%= SupportUtil.getHTML(ticketSolution.getSummary(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
			</div>
		</div>
	</c:if>

	<div>
		<strong><liferay-ui:message key="solution" />:</strong>

		<div class="solution-confirm-comment">
			<pre><%= SupportUtil.getHTML(ticketSolution.getSolution(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
		</div>

		<%
		List<TicketAttachment> ticketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(ticketEntry.getTicketEntryId(), ticketSolution.getTicketSolutionId());

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			ticketAttachmentURL.setCopyCurrentRenderParameters(false);
			ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
			ticketAttachmentURL.setResourceID("ticketAttachment");
		%>

			<div>
				<strong><liferay-ui:message key="attachments" />:</strong>

				<a href="<%= ticketAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(ticketAttachment.getFileName()) %></a> (<%= TextFormatter.formatStorageSize((double)ticketAttachment.getFileSize(), locale) %>k)
			</div>

		<%
		}

		List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketEntry.getTicketEntryId(), ticketSolution.getTicketSolutionId());

		for (TicketLink ticketLink : ticketLinks) {
		%>

			<div>
				<strong><liferay-ui:message key="links" />:</strong>

				<a href="<%= ticketLink.getUrl() %>" target="_blank"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>
			</div>

		<%
		}
		%>

	</div>

	<c:if test="<%= ticketWorker %>">
		<div>
			<div>
				<input <%= ticketSolution.isReviewForKB() ? "checked" : "" %> disabled="disabled" type="checkbox" />

				<liferay-ui:message key="this-issue-should-be-reviewed-and-considered-to-be-added-as-an-article-in-the-knowledge-base" />
			</div>

			<div>
				<input <%= ticketSolution.isCustomerSpecific() ? "checked" : "" %> disabled="disabled" type="checkbox" />

				<liferay-ui:message key="this-issue-only-applies-to-this-customer" />
			</div>

			<div>
				<input <%= ticketSolution.isVersionSpecific() ? "checked" : "" %> disabled="disabled" type="checkbox" />

				<liferay-ui:message key="this-issue-only-applies-to-this-version-of-liferay" />
			</div>

			<div>
				<input <%= ticketSolution.isEnvironmentSpecific() ? "checked" : "" %> disabled="disabled" type="checkbox" />

				<liferay-ui:message key="this-issue-only-applies-to-this-environment" />
			</div>
		</div>
	</c:if>

	<c:if test="<%= hasUpdateTicketSolution %>">
		<div class="solution-confirm-message">
			<div class="solution-confirm-question">
				<c:choose>
					<c:when test="<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_RESOLVED) || (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_UNABLE_TO_TEST) %>">
						<liferay-ui:message key="did-the-solution-work-in-your-production-environment" />
					</c:when>
					<c:when test="<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_RESOLVED_MANUALLY) || (ticketSolution.getType() == TicketSolutionConstants.TYPE_SERVICE) %>">
						<liferay-ui:message key="did-the-proposed-solution-solve-the-reported-issue" />
					</c:when>
					<c:when test="<%= ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING %>">
						<liferay-ui:message key="did-the-solution-work-in-your-preproduction-environment" />
					</c:when>
				</c:choose>
			</div>

			<div class="solution-confirm-answer">
				<a class="btn btn-yes solution-confirm-btn" href="javascript:;" onClick="<portlet:namespace />updateTicketSolution(<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING) ? TicketSolutionConstants.STATUS_RESOLVED : TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION %>, "", "");"><liferay-ui:message key="yes" /></a>

				<a class="btn btn-no solution-confirm-btn"><liferay-ui:message key="no" /></a>

				<c:if test="<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_RESOLVED) || (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING) %>">
					<a class="btn btn-not-applicable solution-confirm-btn style-a" href="javascript:;" onClick="<portlet:namespace />updateTicketSolution(<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING) ? TicketSolutionConstants.STATUS_UNABLE_TO_TEST : TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION %>, "", "");"><liferay-ui:message key="not-applicable" /></a>
				</c:if>
			</div>
		</div>
	</c:if>
</div>

<c:if test="<%= hasUpdateTicketSolution %>">
	<portlet:actionURL name="updateTicketSolution" var="updateTicketSolutionURL">
		<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
		<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
		<portlet:param name="ticketSolutionId" value="<%= String.valueOf(ticketSolution.getTicketSolutionId()) %>" />
	</portlet:actionURL>

	<aui:form action="<%= updateTicketSolutionURL %>" enctype="multipart/form-data" method="post" name="fm7">
		<input name="<portlet:namespace />ticketSolutionStatus" type="hidden" value="" />
		<input name="<portlet:namespace />statusReason" type="hidden" value="" />
		<input name="<portlet:namespace />statusMessage" type="hidden" value="" />
	</aui:form>

	<aui:script use="aui-dialog,aui-io">
		var dialogNode = A.one('.solution-confirm-answer');

		dialogNode.delegate(
			'click',
			function() {
				var dialog = new A.Dialog(
					{
						align: {
							points: [A.WidgetPositionAlign.TC, A.WidgetPositionAlign.TC]
						},
						cssClass: 'solutions-confirm-dialog',
						destroyOnClose: true,
						height: '90%',
						modal: true,
						resizable: false,
						title: '<liferay-ui:message key="solution-rejected" />',
						width: '50%'
					}
				).plug(
					A.Plugin.IO,
					{
						uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/reject_ticket_solution.jsp" /><portlet:param name="ticketSolutionId" value="<%= String.valueOf(ticketSolution.getTicketSolutionId()) %>" /></portlet:renderURL>'
					}
				).render();
			},
			'.btn-no'
		);
	</aui:script>

	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />updateTicketSolution',
			function(ticketSolutionStatus, statusReason, statusMessage) {
				var form = A.one('#<portlet:namespace />fm7');

				if (form) {
					var ticketSolutionStatus = .one('#<portlet:namespace />ticketSolutionStatus');

					if (ticketSolutionStatus) {
						ticketSolutionStatus.val(ticketSolutionStatus);
					}

					var statusReason = form.one('#<portlet:namespace />statusReason');

					if (statusReason) {
						statusReason.val(statusReason);
					}

					var statusMessage = form.one('#<portlet:namespace />statusMessage');

					if (statusMessage) {
						statusMessage.val(statusMessage);
					}

					submitForm(form);
				}
			},
			['aui-base']
		);
	</aui:script>
</c:if>