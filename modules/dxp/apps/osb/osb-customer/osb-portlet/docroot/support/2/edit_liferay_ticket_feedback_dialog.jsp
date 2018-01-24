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
			<aui:input checked="<%= true %>" id="dialogYesRadioButton" label="" name="dialogRadioButton" onClick='<%= renderResponse.getNamespace() + "toggleDialogSatisfied(" + TicketFeedbackConstants.SATISFIED_YES + ");" %>' type="radio" value="<%= TicketFeedbackConstants.SATISFIED_YES %>" />

			<liferay-ui:message key="yes" />
		</div>

		<div>
			<aui:input id="dialogNoRadioButton" label="" name="dialogRadioButton" onClick='<%= renderResponse.getNamespace() + "toggleDialogSatisfied(" + TicketFeedbackConstants.SATISFIED_NO + ");" %>' type="radio" value="<%= TicketFeedbackConstants.SATISFIED_NO %>" />

			<liferay-ui:message key="no" />
		</div>

		<div class="margin-top-10 txt-b">
			<div id="<portlet:namespace />dialogAnswerSatisfied">
				<liferay-ui:message key="were-glad-to-hear-that-what-went-well" />
			</div>

			<div class="hide" id="<portlet:namespace />dialogAnswerNotSatisfied">
				<liferay-ui:message key="were-sorry-to-hear-that-how-can-we-improve" />
			</div>

			<aui:input cssClass="textarea-comments" label="" name="dialogComments" type="textarea" />
		</div>

		<div class="clearfix">
			<aui:button cssClass="aui-button-input pull-left" name="dialogCancelButton" onClick='<%= renderResponse.getNamespace() + "closeDialog(1);" %>' value="cancel" />

			<aui:button cssClass="aui-button-input pull-right" name="dialogSubmitButton" onClick='<%= renderResponse.getNamespace() + "submitLiferayTicketFeedback();" %>' value="submit" />
		</div>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />submitLiferayTicketFeedback',
		function() {
			var A = AUI();

			<portlet:namespace />closeDialog(1);

			var satisfied;

			var dialogNoRadioButton = A.one('#<portlet:namespace />dialogNoRadioButton');

			if (dialogNoRadioButton) {
				satisfied = dialogNoRadioButton.val();
			}

			var dialogYesRadioButton = A.one('#<portlet:namespace />dialogYesRadioButton');

			if (dialogYesRadioButton) {
				var checked = dialogYesRadioButton.get('checked');

				if (checked) {
					satisfied = dialogYesRadioButton.val();
				}
			}

			A.io.request(
				'<portlet:actionURL name="updateTicketFeedbackSatisfied" />',
				{
					data: {
						<portlet:namespace />satisfied: satisfied,
						<portlet:namespace />ticketEntryId: <%= ticketEntry.getTicketEntryId() %>
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
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

	Liferay.provide(
		window,
		'<portlet:namespace />toggleDialogSatisfied',
		function(satisfaction) {
			var A = AUI();

			var satisfied = (satisfaction == <%= TicketFeedbackConstants.SATISFIED_YES %>);

			var dialogAnswerSatisfied = A.one('#<portlet:namespace />dialogAnswerSatisfied');

			if (dialogAnswerSatisfied) {
				dialogAnswerSatisfied.toggle(satisfied);
			}

			var dialogAnswerNotSatisfied = A.one('#<portlet:namespace />dialogAnswerNotSatisfied');

			if (dialogAnswerNotSatisfied) {
				dialogAnswerNotSatisfied.toggle(!satisfied);
			}
		},
		['aui-base']
	);
</aui:script>