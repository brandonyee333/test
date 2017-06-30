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

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
%>

<portlet:actionURL name="updateTicketFeedback" var="updateTicketFeedbackURL">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
</portlet:actionURL>

<aui:form action="<%= updateTicketFeedbackURL %>" class="uni-form" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<input name="<portlet:namespace />ticketFeedbackId" type="hidden" />
	<input name="<portlet:namespace />satisfied" type="hidden" />

	<div>
		<div id="<portlet:namespace />liferayTicketFeedbackButtons">
			<div class="aui-field-inline">
				<liferay-ui:message key="are-you-satisfied-with-the-service-provided-by-the-support-engineers-on-this-ticket" />
			</div>

			<div class="aui-field-inline indent">
				<input class="aui-button-input" onClick="<portlet:namespace />updateSatisfied(<%= TicketFeedbackConstants.SATISFIED_YES %>);" type="button" value="<liferay-ui:message key="yes" />" />

				<input class="aui-button-input" onClick="<portlet:namespace />updateSatisfied(<%= TicketFeedbackConstants.SATISFIED_NO %>);" type="button" value="<liferay-ui:message key="no" />" />
			</div>
		</div>

		<div class="aui-helper-hidden" id="<portlet:namespace />commentSection">
			<div class="indent">
				<liferay-ui:message key="thank-you" />

				<br /><br />

				<div id="<portlet:namespace />answerSatisfied">
					<liferay-ui:message key="were-glad-to-hear-that-what-went-well" />
				</div>

				<div id="<portlet:namespace />answerNotSatisfied">
					<liferay-ui:message key="were-sorry-to-hear-that-how-can-we-improve" />
				</div>

				<br />

				<textarea id="<portlet:namespace />comments" name="<portlet:namespace />comments" wrap="soft"></textarea>

				<br /><br />

				<input class="aui-button-input" type="submit" value="<liferay-ui:message key="submit" />" />
			</div>
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />toggleLiferayFeedback(satisfied) {
		var A = AUI();

		if (satisfied == <%= TicketFeedbackConstants.SATISFIED_YES %>) {
			A.one('#<portlet:namespace />answerNotSatisfied').hide();
		}
		else {
			A.one('#<portlet:namespace />answerSatisfied').hide();
		}

		A.one('#<portlet:namespace />commentSection').show();

		A.one('#<portlet:namespace />liferayTicketFeedbackButtons').hide();
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateSatisfied',
		function(satisfied) {
			var A = AUI();

			<portlet:namespace />toggleLiferayFeedback(satisfied);

			A.io.request(
				'<portlet:actionURL name="updateTicketFeedbackSatisfied" />',
				{
					data: {
						<portlet:namespace />satisfied: satisfied,
						<portlet:namespace />ticketEntryId: <%= ticketEntry.getTicketEntryId() %>
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							document.<portlet:namespace />fm.<portlet:namespace />satisfied.value = response.satisfied;
							document.<portlet:namespace />fm.<portlet:namespace />ticketFeedbackId.value = response.ticketFeedbackId;
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>