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
%>

<div class="edit-liferay-ticket-feedback-container" id="<portlet:namespace />liferayTicketFeedbackDialogContainer">
	<div>
		<h2 class="center txt-b">
			<liferay-ui:message key="liferay-ticket-feedback" />
		</h2>

		<div class="margin-top-30 txt-b">
			<liferay-ui:message key="are-you-satisfied-with-the-service-provided-by-the-support-engineers-on-this-ticket" />
		</div>

		<div>
			<aui:input checked="true" name="dialogRadioButton" onClick='<%= renderResponse.getNamespace() + "toggleDialogSatisfied(" + TicketFeedbackConstants.SATISFIED_YES + ");" %>' type="radio" value="<%= TicketFeedbackConstants.SATISFIED_YES %>" />

			<liferay-ui:message key="yes" />
		</div>

		<div>
			<aui:input name="dialogRadioButton" onClick='<%= renderResponse.getNamespace() + "toggleDialogSatisfied(" + TicketFeedbackConstants.SATISFIED_NO + ");" %>' type="radio" value="<%= TicketFeedbackConstants.SATISFIED_NO %>" />

			<liferay-ui:message key="no" />
		</div>

		<div class="margin-top-10 txt-b">
			<div id="<portlet:namespace />dialogAnswerSatisfied">
				<liferay-ui:message key="were-glad-to-hear-that-what-went-well" />
			</div>

			<div class="hide" id="<portlet:namespace />dialogAnswerNotSatisfied">
				<liferay-ui:message key="were-sorry-to-hear-that-how-can-we-improve" />
			</div>

			<aui:input cssClass="textarea-comments" name="dialogComments" type="textarea" />
		</div>

		<div class="clearfix">
			<aui:button cssClass="aui-button-input fl" name="dialogCancelButton" onClick='<%= renderResponse.getNamespace() + "closeDialog(1);" %>' value="cancel" />

			<aui:button cssClass="aui-button-input fr" name="dialogSubmitButton" onClick='<%= renderResponse.getNamespace() + "submitLiferayTicketFeedback();" %>' value="submit" />
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />toggleDialogSatisfied(satisfied) {
		var A = AUI();

		if (satisfied == <%= TicketFeedbackConstants.SATISFIED_YES %>) {
			A.one('#<portlet:namespace />dialogAnswerNotSatisfied').hide();
			A.one('#<portlet:namespace />dialogAnswerSatisfied').show();
		}
		else {
			A.one('#<portlet:namespace />dialogAnswerNotSatisfied').show();
			A.one('#<portlet:namespace />dialogAnswerSatisfied').hide();
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />submitLiferayTicketFeedback',
		function() {
			var A = AUI();

			<portlet:namespace />closeDialog(1);

			var satisfied = '';

			if (document.getElementById('<portlet:namespace />dialogYesRadioButton').checked) {
				satisfied = document.getElementById('<portlet:namespace />dialogYesRadioButton').value;
			}
			else {
				satisfied = document.getElementById('<portlet:namespace />dialogNoRadioButton').value;
			}

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
							document.<portlet:namespace />fm.<portlet:namespace />comments.value = document.getElementById('<portlet:namespace />dialogComments').value;

							submitForm(document.<portlet:namespace />fm);
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>