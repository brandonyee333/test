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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/2/view.jsp");
	portletURL.setWindowState(LiferayWindowState.NORMAL);

	backURL = portletURL.toString();
}

long ticketFeedbackId = ParamUtil.getLong(request, "ticketFeedbackId");

TicketFeedback ticketFeedback = TicketFeedbackServiceUtil.getTicketFeedback(ticketFeedbackId);

int answer1 = ticketFeedback.getAnswer1();
int answer2 = ticketFeedback.getAnswer2();
int answer3 = ticketFeedback.getAnswer3();
int rating1 = ticketFeedback.getRating1();
int rating2 = ticketFeedback.getRating2();
int rating3 = ticketFeedback.getRating3();

String comments = BeanParamUtil.getString(ticketFeedback, request, "comments");

boolean hasUpdate = false;

if (liferayIncOrg && OSBTicketFeedbackPermission.contains(permissionChecker, ticketFeedback, OSBActionKeys.UPDATE)) {
	hasUpdate = true;
}
%>

<c:if test="<%= ticketFeedback.getSubject() == TicketFeedbackConstants.SUBJECT_PARTNER %>">
	<portlet:actionURL name="updateTicketFeedback" var="updateTicketFeedbackURL">
		<portlet:param name="mvcPath" value="/support/2/edit_partner_ticket_feedback.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateTicketFeedbackURL %>" class="uni-form" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="ticketFeedbackId" type="hidden" value="<%= ticketFeedbackId %>" />
		<aui:input name="satisfied" type="hidden" value="<%= TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE %>" />

		<div class="clearfix section">
			<div class="pull-right">
				<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
			</div>
		</div>

		<liferay-ui:error exception="<%= TicketFeedbackAnswerException.class %>" message="please-answer-all-the-questions" />
		<liferay-ui:error exception="<%= TicketFeedbackRatingException.class %>" message="please-rate-all-the-questions" />

		<div class="clearfix">
			<div class="pull-left">
				<div class="txt-b txt-up">
					<liferay-ui:message key="partner-feedback-for-ticket" />
				</div>
			</div>

			<div class="clearfix txt-b txt-h1-12 txt-h1-9 txt-h2-6 txt-h3-4">

				<%
				TicketEntry ticketEntry = ticketFeedback.getTicketEntry();
				%>

				<a href="<%= SupportUtil.getFriendlyTicketEntryURL(request, ticketEntry.getTicketEntryId()) %>"><%= ticketEntry.getDisplayId() %></a>
			</div>
		</div>

		<div class="clearfix">
			<div class="txt-i">
				<liferay-ui:message key="star-indicates-required-field" />
			</div>

			<br />

			<div class="txt-b">
				<liferay-ui:message key="was-the-issue-reproduced-when-escalated" /> *
			</div>

			<br />

			<aui:input checked="<%= answer1 == TicketFeedbackConstants.ANSWER_YES %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_YES) %>" name="answer1" type="radio" value="<%= TicketFeedbackConstants.ANSWER_YES %>" />
			<aui:input checked="<%= answer1 == TicketFeedbackConstants.ANSWER_NO %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_NO) %>" name="answer1" type="radio" value="<%= TicketFeedbackConstants.ANSWER_NO %>" />
			<aui:input checked="<%= answer1 == TicketFeedbackConstants.ANSWER_NOT_APPLICABLE %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_NOT_APPLICABLE) %>" name="answer1" type="radio" value="<%= TicketFeedbackConstants.ANSWER_NOT_APPLICABLE %>" />

			<br />

			<div class="txt-b">
				<liferay-ui:message key="was-the-issue-escalated-in-a-timely-manner" /> *
			</div>

			<br />

			<aui:input checked="<%= answer2 == TicketFeedbackConstants.ANSWER_YES %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_YES) %>" name="answer2" type="radio" value="<%= TicketFeedbackConstants.ANSWER_YES %>" />
			<aui:input checked="<%= answer2 == TicketFeedbackConstants.ANSWER_NO %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_NO) %>" name="answer2" type="radio" value="<%= TicketFeedbackConstants.ANSWER_NO %>" />
			<aui:input checked="<%= answer2 == TicketFeedbackConstants.ANSWER_NOT_APPLICABLE %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_NOT_APPLICABLE) %>" name="answer2" type="radio" value="<%= TicketFeedbackConstants.ANSWER_NOT_APPLICABLE %>" />

			<br />

			<div class="txt-b">
				<liferay-ui:message key="was-the-issue-escalated-properly" /> *
			</div>

			<br />

			<aui:input checked="<%= answer3 == TicketFeedbackConstants.ANSWER_YES %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_YES) %>" name="answer3" type="radio" value="<%= TicketFeedbackConstants.ANSWER_YES %>" />
			<aui:input checked="<%= answer3 == TicketFeedbackConstants.ANSWER_NO %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_NO) %>" name="answer3" type="radio" value="<%= TicketFeedbackConstants.ANSWER_NO %>" />
			<aui:input checked="<%= answer3 == TicketFeedbackConstants.ANSWER_NOT_APPLICABLE %>" disabled="<%= !hasUpdate %>" label="<%= TicketFeedbackConstants.getAnswerLabel(TicketFeedbackConstants.ANSWER_NOT_APPLICABLE) %>" name="answer3" type="radio" value="<%= TicketFeedbackConstants.ANSWER_NOT_APPLICABLE %>" />

			<br />

			<div class="txt-b">
				<liferay-ui:message key="response-time" /> *
			</div>

			<br />

			<aui:input checked="<%= rating1 == 5 %>" disabled="<%= !hasUpdate %>" label='<%= "5 - " + TicketFeedbackConstants.getRatingLabel(5) %>' name="rating1" type="radio" value="5" />
			<aui:input checked="<%= rating1 == 4 %>" disabled="<%= !hasUpdate %>" label='<%= "4 - " + TicketFeedbackConstants.getRatingLabel(4) %>' name="rating1" type="radio" value="4" />
			<aui:input checked="<%= rating1 == 3 %>" disabled="<%= !hasUpdate %>" label='<%= "3 - " + TicketFeedbackConstants.getRatingLabel(3) %>' name="rating1" type="radio" value="3" />
			<aui:input checked="<%= rating1 == 2 %>" disabled="<%= !hasUpdate %>" label='<%= "2 - " + TicketFeedbackConstants.getRatingLabel(2) %>' name="rating1" type="radio" value="2" />
			<aui:input checked="<%= rating1 == 1 %>" disabled="<%= !hasUpdate %>" label='<%= "1 - " + TicketFeedbackConstants.getRatingLabel(1) %>' name="rating1" type="radio" value="1" />

			<br />

			<div class="txt-b">
				<liferay-ui:message key="technical-knowledge" /> *
			</div>

			<br />

			<aui:input checked="<%= rating2 == 5 %>" disabled="<%= !hasUpdate %>" label='<%= "5 - " + TicketFeedbackConstants.getRatingLabel(5) %>' name="rating2" type="radio" value="5" />
			<aui:input checked="<%= rating2 == 4 %>" disabled="<%= !hasUpdate %>" label='<%= "4 - " + TicketFeedbackConstants.getRatingLabel(4) %>' name="rating2" type="radio" value="4" />
			<aui:input checked="<%= rating2 == 3 %>" disabled="<%= !hasUpdate %>" label='<%= "3 - " + TicketFeedbackConstants.getRatingLabel(3) %>' name="rating2" type="radio" value="3" />
			<aui:input checked="<%= rating2 == 2 %>" disabled="<%= !hasUpdate %>" label='<%= "2 - " + TicketFeedbackConstants.getRatingLabel(2) %>' name="rating2" type="radio" value="2" />
			<aui:input checked="<%= rating2 == 1 %>" disabled="<%= !hasUpdate %>" label='<%= "1 - " + TicketFeedbackConstants.getRatingLabel(1) %>' name="rating2" type="radio" value="1" />

			<br />

			<div class="txt-b">
				<liferay-ui:message key="professionalism" /> *
			</div>

			<br />

			<aui:input checked="<%= rating3 == 5 %>" disabled="<%= !hasUpdate %>" label='<%= "5 - " + TicketFeedbackConstants.getRatingLabel(5) %>' name="rating3" type="radio" value="5" />
			<aui:input checked="<%= rating3 == 4 %>" disabled="<%= !hasUpdate %>" label='<%= "4 - " + TicketFeedbackConstants.getRatingLabel(4) %>' name="rating3" type="radio" value="4" />
			<aui:input checked="<%= rating3 == 3 %>" disabled="<%= !hasUpdate %>" label='<%= "3 - " + TicketFeedbackConstants.getRatingLabel(3) %>' name="rating3" type="radio" value="3" />
			<aui:input checked="<%= rating3 == 2 %>" disabled="<%= !hasUpdate %>" label='<%= "2 - " + TicketFeedbackConstants.getRatingLabel(2) %>' name="rating3" type="radio" value="2" />
			<aui:input checked="<%= rating3 == 1 %>" disabled="<%= !hasUpdate %>" label='<%= "1 - " + TicketFeedbackConstants.getRatingLabel(1) %>' name="rating3" type="radio" value="1" />

			<br />

			<div class="txt-b">
				<liferay-ui:message key="additional-comments" />
			</div>

			<div>
				<c:choose>
					<c:when test="<%= hasUpdate %>">
						<aui:input label="<%= comments %>" name="comments" style="height: 250px; width: 90%;" type="textarea" wrap="soft" />
					</c:when>
					<c:otherwise>
						<%= HtmlUtil.escape(ticketFeedback.getComments()) %>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="<%= hasUpdate %>">
				<br />

				<aui:button cssClass="aui-button-input" type="submit" value="save" />

				<aui:button cssClass="aui-button-input" href="<%= backURL %>" value="cancel" />
			</c:if>
		</div>
	</aui:form>
</c:if>