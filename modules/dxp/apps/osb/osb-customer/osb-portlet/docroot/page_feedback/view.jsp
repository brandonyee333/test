<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />radioButtons">
	<aui:field-wrapper inlineField="<%= true %>" label="was-this-helpful">
		<aui:input autocomplete="off" inlineField="<%= true %>" label="<%= FeedbackEntryConstants.getAnswerLabel(FeedbackEntryConstants.ANSWER_YES) %>" name="feedback" onclick='<%= renderResponse.getNamespace() + "checkFeedback();" %>' type="radio" value="<%= FeedbackEntryConstants.ANSWER_YES %>" />

		<aui:input autocomplete="off" inlineField="<%= true %>" label="<%= FeedbackEntryConstants.getAnswerLabel(FeedbackEntryConstants.ANSWER_NO) %>" name="feedback" onclick='<%= renderResponse.getNamespace() + "checkFeedback();" %>' type="radio" value="<%= FeedbackEntryConstants.ANSWER_NO %>" />
	</aui:field-wrapper>
</div>

<aui:script>
	function <portlet:namespace />checkFeedback() {
		var A = AUI();

		var satisfied = A.one('input[name=<portlet:namespace/>feedback]:checked').get('value');

		var radioButtons = document.getElementsByName('<portlet:namespace />feedback');

		for (var i in radioButtons) {
			radioButtons[i].disabled = true;
		}

		<portlet:namespace />updateFeedbackEntry(satisfied);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />openDialog',
		function(url, popupId) {
			var dialogY = (window.innerHeight / 2) + window.pageYOffset;

			Liferay.Util.openWindow(
				{
					cache: false,
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER,
						centered: true,
						close: false,
						cssClass: 'page-feedback-dialog',
						draggable: false,
						resizable: false,
						width: '30%'
					},
					id: popupId,
					uri: url
				}
			);
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateFeedbackEntry',
		function(satisfied) {
			var A = AUI();

			A.io.request(
				'<portlet:actionURL name="updateFeedbackEntry" />',
				{
					data: {
						<portlet:namespace />satisfied: satisfied
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							var radioButtonsDiv = document.getElementById('<portlet:namespace />radioButtons');

							radioButtonsDiv.style.display = 'none';

							var feedbackURL = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" />&<portlet:namespace />mvcPath=/page_feedback/feedback_dialog.jsp&<portlet:namespace />feedbackEntryId=' + response.feedbackEntryId;

							<portlet:namespace />openDialog(feedbackURL, '<portlet:namespace />updateFeedback');

							document.getElementById('<portlet:namespace />feedbackEntryId').value = response.feedbackEntryId;
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>