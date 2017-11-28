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

List<TicketFeedback> ticketFeedbacks = null;
TicketFeedback ticketFeedback = null;

if (ticketEntry != null) {
	ticketFeedbacks = TicketFeedbackServiceUtil.getTicketFeedbacks(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_LIFERAY);

	if (liferayIncOrg) {
		ticketFeedback = TicketFeedbackServiceUtil.fetchFirstTicketFeedback(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_LIFERAY);
	}
	else {
		ticketFeedback = TicketFeedbackServiceUtil.fetchFirstOpenTicketFeedback(user.getUserId(), ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_LIFERAY);
	}
}
%>

<c:if test="<%= liferayIncOrg || ((ticketFeedback == null) && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE)) %>">
	<h1 class="section-heading">
		<liferay-ui:message key="liferay-ticket-feedback" />
	</h1>

	<div class="ticket-feedback">
		<c:if test="<%= liferayIncOrg %>">
			<c:choose>
				<c:when test="<%= ticketFeedbacks.isEmpty() %>">
					<div class="txt-b>
						<liferay-ui:message key="no-feedback-was-provided" />
					</div>
				</c:when>
				<c:otherwise>

					<%
					for (TicketFeedback curTicketFeedback : ticketFeedbacks) {
						User ticketFeedbackUser = UserLocalServiceUtil.fetchUserById(ticketFeedback.getUserId());
					%>

						<c:if test="<%= curTicketFeedback.getSatisfied() != TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE %>">
							<div class="clearfix">
								<div class="content-column w100">
									<div class="content-column-content">
										<span class="display-name txt-b">
											<c:if test="<%= ticketFeedbackUser != null %>">
												<a href="<%= ticketFeedbackUser.getDisplayURL(themeDisplay) %>" target="_blank">
											</c:if>

											<%= HtmlUtil.escape(curTicketFeedback.getUserName()) %>

											<c:if test="<%= ticketFeedbackUser != null %>">
												</a>
											</c:if>
										</span>
										<span>
											- <%= curTicketFeedback.getModifiedDate() %>
										</span>
									</div>
								</div>
							</div>

							<div class="clearfix">
								<div class="content-column w50">
									<div class="content-column-content left-column">
										<span class="indent txt-b">
											<liferay-ui:message key="are-you-satisfied-with-the-service-provided-by-the-support-engineers-on-this-ticket" />
										</span>

										<div class="double-indent">
											<%= LanguageUtil.get(request, curTicketFeedback.getSatisfiedLabel()) %>
										</div>
									</div>
								</div>
							</div>

							<div class="clearfix">
								<div class="content-column">
									<div class="content-column-content left-column">
										<span class="indent txt-b">
											<c:choose>
												<c:when test="<%= curTicketFeedback.getSatisfied() == TicketFeedbackConstants.SATISFIED_YES %>">
													<liferay-ui:message key="what-did-we-do-well" />
												</c:when>
												<c:when test="<%= curTicketFeedback.getSatisfied() == TicketFeedbackConstants.SATISFIED_NO %>">
													<liferay-ui:message key="how-can-we-improve" />
												</c:when>
											</c:choose>
										</span>

										<br />

										<div class="double-indent">
											<pre><%= HtmlUtil.escape(curTicketFeedback.getComments()) %></pre>
										</div>
									</div>
								</div>
							</div>
						</c:if>

					<%
					}
					%>

					<c:if test="<%= (ticketFeedback != null) && (ticketFeedback.getSatisfied() == TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE) %>">
						<div class="clearfix">
							<div class="content-column w100">
								<div class="content-column-content">
									<span class="txt-b txt-up">
										<liferay-ui:message key="average-rating" />:
									</span>
									<span>
										<%= ticketFeedback.getAverageRating() %>
									</span>
								</div>
							</div>
						</div>

						<div class="clearfix">
							<div class="content-column w50">
								<div class="content-column-content left-column">
									<span class="txt-b txt-up">
										<liferay-ui:message key="final-resolution" />:
									</span>
									<span>
										<%= ticketFeedback.getRating1() %> - <%= LanguageUtil.get(request, ticketFeedback.getRating1Label()) %>
									</span>

									<br />

									<span class="txt-b txt-up">
										<liferay-ui:message key="response-time" />:
									</span>
									<span>
										<%= ticketFeedback.getRating2() %> - <%= LanguageUtil.get(request, ticketFeedback.getRating2Label()) %>
									</span>
								</div>
							</div>

							<div class="content-column w50">
								<div class="content-column-content right-column">
									<span class="txt-b txt-up">
										<liferay-ui:message key="technical-knowledge" />:
									</span>
									<span>
										<%= ticketFeedback.getRating3() %> - <%= LanguageUtil.get(request, ticketFeedback.getRating3Label()) %>
									</span>

									<br />

									<span class="txt-b txt-up">
										<liferay-ui:message key="professionalism" />:
									</span>
									<span>
										<%= ticketFeedback.getRating4() %> - <%= LanguageUtil.get(request, ticketFeedback.getRating4Label()) %>
									</span>
								</div>
							</div>
						</div>

						<div>
							<pre><%= HtmlUtil.escape(ticketFeedback.getComments()) %></pre>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:if test="<%= (ticketFeedback == null) && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE) %>">
			<liferay-util:include page="/support/2/edit_liferay_ticket_feedback.jsp" servletContext="<%= application %>" />
		</c:if>
	</div>
</c:if>