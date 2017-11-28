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
long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);

AccountEntry accountEntry = ticketEntry.getAccountEntry();

boolean partnerWorker = false;

if (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId())) {
	partnerWorker = true;
}

List<TicketSolution> ticketSolutions = TicketSolutionLocalServiceUtil.getTicketSolutions(ticketEntry.getTicketEntryId());
%>

<c:if test="<%= !ticketSolutions.isEmpty() %>">

	<%
	for (int i = 0; i < ticketSolutions.size(); i++) {
		TicketSolution ticketSolution = ticketSolutions.get(i);

		int j = i * 2;

		String cssClass = "comment-expanded";

		if ((ticketSolutions.size() > 5) && (i < (ticketSolutions.size() - 5))) {
			cssClass = "comment-collapsed";
		}
	%>

		<div class="ticket-comment <%= cssClass %>" id="<portlet:namespace />commentContainer<%= j %>">
			<div class="content-column w10">
				<div class="content-column-content left-column" onClick="<portlet:namespace />toggleComment(event, <%= j %>);">

					<%
					User solutionUser = UserLocalServiceUtil.getUser(ticketSolution.getUserId());
					%>

					<div class="user-avatar" style="background-image: url('<%= solutionUser.getPortraitURL(themeDisplay) %>&height=70&width=70')"></div>

					<div class="small-user-avatar" style="background-image: url('<%= solutionUser.getPortraitURL(themeDisplay) %>&height=50&width=50')"></div>
				</div>
			</div>

			<div class="content-column w90">
				<div class="user-name">
					<span onClick="<portlet:namespace />toggleComment(event, <%= j %>);">
						<%= HtmlUtil.escape(solutionUser.getFullName()) %>

						<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
							<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
							<portlet:param name="userId" value="<%= String.valueOf(solutionUser.getUserId()) %>" />
						</liferay-util:include>
					</span>
				</div>

				<div class="post-date">
					<%= fullDateFormatDateTime.format(ticketSolution.getCreateDate()) %>
				</div>

				<span class="post-date">
					<%= shortDateFormatDate.format(ticketSolution.getCreateDate()) %> <%= shortDateFormatTime.format(ticketSolution.getCreateDate()) %>
				</span>

				<div class="comment" id="<portlet:namespace />comment<%= j %>">
					<div class="comment-body">
						<div class="comment-body-wrapper">
							<c:if test="<%= liferayIncOrg || partnerWorker %>">
								<div>
									<strong><liferay-ui:message key="issue-summary" />:</strong>

									<liferay-ui:message key='<%= ticketSolution.isUseCustomerSummary() ? "customers-description-accurately-captures-the-issue" : "customers-description-does-not-accurately-capture-the-issue" %>' />

									<br />

									<pre><%= SupportUtil.getHTML(ticketSolution.getSummary(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
								</div>
							</c:if>

							<div>
								<strong><liferay-ui:message key="solution" />:</strong>

								<br />

								<pre><%= SupportUtil.getHTML(ticketSolution.getSolution(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
							</div>

							<br />

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
									<strong><liferay-ui:message key="link" />:</strong>

									<a href="<%= ticketLink.getUrl() %>"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>

									<c:if test="<%= liferayIncOrg %>">
										<span class="link-type">
											<liferay-ui:message key="<%= TicketLinkConstants.getTypeLabel(ticketLink.getType()) %>" />
										</span>
									</c:if>
								</div>

							<%
							}
							%>

							<c:if test="<%= liferayIncOrg || partnerWorker %>">
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
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:if test="<%= Validator.isNotNull(ticketSolution.getStatusMessage()) %>">
			<div class="ticket-comment <%= cssClass %>" id="<portlet:namespace />commentContainer<%= j + 1 %>">
				<div class="content-column w10">
					<div class="content-column-content left-column" onClick="<portlet:namespace />toggleComment(event, <%= j + 1 %>);">

						<%
						User statusByUser = UserLocalServiceUtil.getUser(ticketSolution.getStatusByUserId());
						%>

						<div class="user-avatar" style="background-image: url('<%= statusByUser.getPortraitURL(themeDisplay) %>&height=70&width=70')"></div>

						<div class="small-user-avatar" style="background-image: url('<%= statusByUser.getPortraitURL(themeDisplay) %>&height=50&width=50')"></div>
					</div>
				</div>

				<div class="content-column w90">
					<div class="user-name">
						<span onClick="<portlet:namespace />toggleComment(event, <%= j + 1 %>);">
							<%= HtmlUtil.escape(statusByUser.getFullName()) %>

							<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
								<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
								<portlet:param name="userId" value="<%= String.valueOf(statusByUser.getUserId()) %>" />
							</liferay-util:include>
						</span>
					</div>

					<div class="post-date">
						<%= fullDateFormatDateTime.format(ticketSolution.getCreateDate()) %>
					</div>

					<span class="post-date">
						<%= shortDateFormatDate.format(ticketSolution.getCreateDate()) %> <%= shortDateFormatTime.format(ticketSolution.getCreateDate()) %>
					</span>

					<div class="comment" id="<portlet:namespace />comment<%= j + 1 %>">
						<div class="comment-body">
							<div>
								<strong>
									<liferay-ui:message key="solution-rejected" />: <%= LanguageUtil.get(request, TicketSolutionConstants.getStatusReasonLabel(ticketSolution.getStatusReason())) %>
								</strong>

								<pre><%= SupportUtil.getHTML(ticketSolution.getStatusMessage(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>

	<%
	}
	%>

</c:if>