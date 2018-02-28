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
String discussionTab = ParamUtil.getString(request, "discussionTab", "public");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

String discussionId = ParamUtil.getString(request, "discussionId");

int[] userVisibilities = null;

if (screenShareMode) {
	userVisibilities = new int[] {VisibilityConstants.PUBLIC};
}
else {
	userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
}
%>

<c:if test="<%= userVisibilities.length > 0 %>">

	<%
	String[] discussionTabNames = new String[userVisibilities.length];
	String[] discussionTabValues = new String[userVisibilities.length];

	for (int i = 0; i < userVisibilities.length; i++) {
		int visibility = userVisibilities[i];

		String visibilityLabel = VisibilityConstants.toLabel(visibility);

		if (userVisibilities.length == 1) {
			visibilityLabel = "comments";

			discussionTab = "comments";
		}

		int ticketCommentCount = TicketCommentLocalServiceUtil.getTicketCommentsCount(ticketEntry.getTicketEntryId(), new int[] {visibility}, new int[] {WorkflowConstants.STATUS_APPROVED});

		discussionTabNames[i] = LanguageUtil.format(request, visibilityLabel + "-x", ticketCommentCount, false);
		discussionTabValues[i] = visibilityLabel;
	}

	if (!screenShareMode) {
		if (discussionTabValues.length > 1) {
			discussionTabNames = ArrayUtil.append(discussionTabNames, LanguageUtil.get(request, "all-comments"));
			discussionTabValues = ArrayUtil.append(discussionTabValues, "all-comments");
		}

		if (ticketWorker) {
			discussionTabNames = ArrayUtil.append(discussionTabNames, LanguageUtil.get(request, "history"));
			discussionTabValues = ArrayUtil.append(discussionTabValues, "history");

			discussionTabNames = ArrayUtil.append(discussionTabNames, LanguageUtil.get(request, "solutions"));
			discussionTabValues = ArrayUtil.append(discussionTabValues, "solutions");
		}
	}

	if (!ArrayUtil.contains(discussionTabValues, discussionTab)) {
		discussionTab = discussionTabValues[0];
	}
	%>

	<a id="<portlet:namespace />tabs_top" name="<portlet:namespace />tabs_top"></a>

	<div class="discussion tab-view" id="<portlet:namespace />discussion">
		<c:if test="<%= discussionTabNames.length > 0 %>">
			<div class="tab-container">
				<div class="tabs" id="<portlet:namespace />discussionTabs">
					<div>

						<%
						for (int i = 0; i < discussionTabNames.length; i++) {
							String discussionTabName = discussionTabNames[i];
							String discussionTabValue = discussionTabValues[i];
						%>

							<span class="<%= (i == 0) ? "first" : "" %>" id="<portlet:namespace /><%= discussionTabValue %>" onClick="<portlet:namespace />loadTab('<%= discussionTabValue %>', false);">

								<%
								boolean showLiferayCommentFlag = false;

								if (discussionTabValue.equals("liferay") && TicketFlagLocalServiceUtil.hasTicketFlag(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD, TicketFlagConstants.FLAG_TRUE)) {
									TicketWorker latestTicketWorker = TicketWorkerLocalServiceUtil.fetchLatestTicketWorker(ticketEntry.getTicketEntryId());

									if (((latestTicketWorker != null) && (latestTicketWorker.getUserId() == user.getUserId())) || SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER, ticketEntry.getSupportRegionId(), null) || SupportWorkerLocalServiceUtil.isManagerOfWorker(user.getUserId(), latestTicketWorker.getUserId())) {
										if (discussionTab.equals("liferay") && (latestTicketWorker != null) && (latestTicketWorker.getUserId() == user.getUserId())) {
											TicketFlagLocalServiceUtil.deleteTicketFlags(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD, TicketFlagConstants.FLAG_TRUE);
										}
										else {
											showLiferayCommentFlag = true;
										}
									}
								}
								%>

								<c:if test="<%= showLiferayCommentFlag %>">
									<span class="notification" id="<portlet:namespace />liferayCommentNotification"></span>
								</c:if>

								<%= HtmlUtil.escape(discussionTabName) %>
							</span>

						<%
						}
						%>

					</div>
				</div>
			</div>
		</c:if>

		<div class="tab-content" id="<portlet:namespace />commentsContainer">
			<div class="expand-collapse" id="<portlet:namespace />expandCollapse">
				<a href="javascript:<portlet:namespace />toggleAll(false);">+ <liferay-ui:message key="expand-all" /></a>

				<a href="javascript:<portlet:namespace />toggleAll(true);">- <liferay-ui:message key="collapse-all" /></a>
			</div>

			<div class="tab-content-tab" id="<portlet:namespace />tabContent">
				<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("bbcode_editor.jsp-javaScriptIncluded")) %>'>

					<%
					request.setAttribute("bbcode_editor.jsp-javaScriptIncluded", Boolean.TRUE.toString());
					%>

					<%@ include file="/support/2/javascript/bbcode_editor_js.jspf" %>
				</c:if>
			</div>
		</div>
	</div>

	<aui:script>
		function <portlet:namespace />addComment(status) {
			<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketEntry.getTicketEntryId(), ActionKeys.ADD_ATTACHMENT) %>">
				var commentBody = document.<portlet:namespace />ticketCommentFm.<portlet:namespace />commentBody0.value;

				<portlet:namespace />confirmAttachments(commentBody);
			</c:if>

			<c:if test="<%= ticketWorker && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) %>">
				var checkbox = document.<portlet:namespace />ticketCommentFm.<portlet:namespace />pendingTypes0;
				var discussionTab = <portlet:namespace />ticketCommentFm.<portlet:namespace />discussionTab;

				if (checkbox && (discussionTab.value === 'public')) {
					var validPendingType = false;

					for (var i = 0; i < checkbox.length; i++) {
						if (checkbox[i].checked) {
							validPendingType = true;

							break;
						}
					}

					if (!validPendingType) {
						alert('<liferay-ui:message key="please-select-a-valid-need-response-from" />');

						return false;
					}
				}
			</c:if>

			<portlet:namespace />updateComment(0, status);
		}

		function <portlet:namespace />checkAttachment(element) {
			var files = element.files;

			var filesize = ((files[0].size / 1024) / 1024).toFixed(4);

			if (filesize > 100) {
				alert('<%= UnicodeLanguageUtil.get(request, "attachments-that-are-uploaded-with-comments-cannot-exceed-100-mb") %>');

				element.value = '';
			}
		}

		function <portlet:namespace />deleteComment(suffix) {
			if (!confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
				return;
			}

			eval('var ticketCommentId = document.<portlet:namespace />ticketCommentFm.<portlet:namespace />ticketCommentId' + suffix + '.value;');

			document.<portlet:namespace />ticketCommentFm.<portlet:namespace />ticketCommentId.value = ticketCommentId;

			submitForm(document.<portlet:namespace />ticketCommentFm, '<portlet:actionURL name="deleteTicketComment"><portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" /></portlet:actionURL>');
		}

		function <portlet:namespace />initDraftCommentMessage(hasMaximumDraftTicketComment) {
			if (document.getElementById('<portlet:namespace />intervalId').value) {
				return false;
			}

			<portlet:namespace />showForm(0, false, hasMaximumDraftTicketComment);
		}

		function <portlet:namespace />toggleComment(event, i, type) {
			var comment = document.getElementById('<portlet:namespace />' + type + 'CommentContainer' + i);

			var text = document.all ? document.selection.createRange().text : document.getSelection();

			if (text == '') {
				if (comment.className.indexOf('comment-collapsed') == -1) {
					comment.className = comment.className.replace('comment-expanded', 'comment-collapsed');
				}
				else {
					comment.className = comment.className.replace('comment-collapsed', 'comment-expanded');
				}
			}
		}

		Liferay.provide(
			window,
			'<portlet:namespace />autoUpdateComment',
			function(suffix) {
				var form = document.<portlet:namespace />ticketCommentFm;

				eval('var commentBody = form.<portlet:namespace />commentBody' + suffix + '.value;');

				var draftBody = form.<portlet:namespace />draftBody.value;

				if (!/\S/.test(commentBody) || (commentBody == draftBody)) {
					return;
				}

				eval('var ticketCommentId = form.<portlet:namespace />ticketCommentId' + suffix + '.value;');

				var ticketEntryId = form.<portlet:namespace />ticketEntryId.value;
				var type = form.<portlet:namespace />type.value;
				var visibility = form.<portlet:namespace />visibility.value;

				eval('var pendingTypesObject = form.<portlet:namespace />pendingTypes' + suffix + ';');

				var pendingTypes = new Array();

				if (pendingTypesObject) {
					if (!pendingTypesObject.length) {
						pendingTypesObject = [pendingTypesObject];
					}

					for (var i = 0; i < pendingTypesObject.length; i++) {
						if (pendingTypesObject[i].checked) {
							pendingTypes.push(pendingTypesObject[i].value);
						}
					}
				}

				var A = AUI();

				A.io.request(
					'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="autoUpdateComment" />',
					{
						data: {
							<portlet:namespace />commentBody: commentBody,
							<portlet:namespace />pendingTypes: pendingTypes,
							<portlet:namespace />ticketCommentId: ticketCommentId,
							<portlet:namespace />ticketEntryId: ticketEntryId,
							<portlet:namespace />type: type,
							<portlet:namespace />visibility: visibility
						},
						dataType: 'JSON',
						method: 'POST',
						on: {
							failure: function() {
								var message = '<%= UnicodeLanguageUtil.get(request, "comment-cannot-be-saved-as-draft-please-save-your-work-before-refreshing") %>';

								<portlet:namespace />updateMessageDisplay(suffix, 'portlet-msg-error', message);
							},
							success: function() {
								var response = this.get('responseData');

								eval('form.<portlet:namespace />ticketCommentId' + suffix + '.value = response.ticketCommentId;');

								form.<portlet:namespace />draftBody.value = commentBody;

								var className = 'portlet-msg-success';
								var message = '<%= UnicodeLanguageUtil.get(request, "comment-is-saved-as-draft") %>';

								message += A.DataType.Date.format(
									new Date(),
									{
										format: '%X'
									}
								);

								if (response.commentPublished) {
									className = 'portlet-msg-error';
									message = '<%= UnicodeLanguageUtil.get(request, "your-draft-has-already-been-published.-please-refresh-the-page-to-see-the-changes") %>';

									<portlet:namespace />disableAutoUpdateComment();
								}
								else if (response.ticketEntryStatus && (response.ticketEntryStatus == <%= TicketEntryConstants.STATUS_CLOSED %>)) {
									className = 'portlet-msg-error';
									message = '<%= UnicodeLanguageUtil.get(request, "this-ticket-has-been-closed.-you-may-refresh-the-page-to-update-the-ticket-but-please-note-that-you-already-have-a-saved-draft") %>';
								}
								else if (response.maximumDraftTicketComments) {
									className = 'portlet-msg-error';
									message = '<%= UnicodeLanguageUtil.get(request, "the-number-of-draft-comments-has-exceeded-the-maximum-number-of-allowed-draft-comments") %>';
								}

								<portlet:namespace />updateMessageDisplay(suffix, className, message);
							}
						}
					}
				);
			},
			['aui-base', 'aui-io', 'datatype-date']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />cancelComment',
			function(suffix) {
				var A = AUI();

				<portlet:namespace />clearInterval();
				<portlet:namespace />updateMessageDisplay(suffix, '', '');

				var commentForm = A.one('#<portlet:namespace />commentForm' + suffix);

				if (commentForm) {
					commentForm.hide();
				}

				if (suffix === 0) {
					var form = A.one(document.<portlet:namespace />ticketCommentFm);

					form.reset();

					<c:if test='<%= discussionTab.equals("liferay") %>'>
						var addCommentButton = A.one('#<portlet:namespace />addCommentButton0');

						if (addCommentButton) {
							addCommentButton.show();
						}

						var addGamePlanButton = A.one('#<portlet:namespace />addGamePlanButton');

						if (addGamePlanButton) {
							addGamePlanButton.show();
						}

						var postGamePlanButton = A.one('#<portlet:namespace />postGamePlanButton');

						if (postGamePlanButton) {
							postGamePlanButton.hide();
						}
					</c:if>

					var addAttachments = A.one('#<portlet:namespace />addAttachments');

					if (addAttachments) {
						addAttachments.hide();
					}

					var addAttachmentsButton = A.one('#<portlet:namespace />addAttachmentsButton');

					if (addAttachmentsButton) {
						addAttachmentsButton.show();
					}
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />clearInterval',
			function() {
				var A = AUI();

				var intervalId = A.one('#<portlet:namespace />intervalId');

				if (intervalId.get('value')) {
					clearInterval(intervalId.get('value'));
				}

				intervalId.val('');
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />confirmAttachments',
			function(commentBody) {
				var A = AUI();

				for (var i = 1; i < 4; i++) {
					if (A.one('#<portlet:namespace />file0_' + i).val()) {
						return true;
					}
				}

				commentBody = commentBody.toLowerCase();

				var attachmentKeywords = ['<%= StringUtil.merge(SupportUtil.getAttachmentKeywords(), "', '") %>'];

				for (var j = 0; j < attachmentKeywords.length; j++) {
					var attachmentKeyword = attachmentKeywords[j].toLowerCase();

					if (commentBody.indexOf(attachmentKeyword) != -1) {
						var confirmationMsg = '<%= UnicodeLanguageUtil.get(request, "no-attachments-confirmation") %>';

						confirmationMsg = A.Lang.sub(confirmationMsg, [attachmentKeyword]);

						if (confirm(confirmationMsg)) {
							return true;
						}

						return false;
					}
				}

				return true;
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />deleteTicketAttachment',
			function(suffix, ticketAttachmentId, fileIndex) {
				var A = AUI();

				if (!confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
					return;
				}

				A.io.request(
					'<liferay-portlet:actionURL name="deleteTicketAttachment" />',
					{
						data: {
							<portlet:namespace />ticketAttachmentId: ticketAttachmentId
						},
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function() {
								var draftSuffix = suffix + '_' + fileIndex;

								A.one('#<portlet:namespace />file' + draftSuffix).show();
								A.one('#<portlet:namespace />fileName' + draftSuffix).remove();

								A.one('#<portlet:namespace />hotfix' + draftSuffix).val('false');
								A.one('#<portlet:namespace />hotfix' + draftSuffix + 'Checkbox').set('checked', false);
								A.one('#<portlet:namespace />hotfix' + draftSuffix + 'Checkbox').set('disabled', false);

								A.one('#<portlet:namespace />deleteTicketAttachment' + draftSuffix).remove();
							}
						}
					}
				);
			},
			['aui-base', 'aui-io']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />disableAutoUpdateComment',
			function() {
				var A = AUI();

				var intervalId = A.one('#<portlet:namespace />intervalId');

				if (intervalId) {
					intervalId.val('disabled');
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />loadTab',
			function(discussionTab, firstLoad) {
				var A = AUI();

				var tabURL = '';

				<portlet:renderURL copyCurrentRenderParameters="<%= false %>" var="tabURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="discussionId" value="<%= discussionId %>" />
					<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
				</portlet:renderURL>

				if ((discussionTab == 'all-comments') || (discussionTab == 'comments') || (discussionTab == 'liferay') || (discussionTab == 'public') || (discussionTab == 'workers')) {
					tabURL += '<%= tabURL %>&<portlet:namespace />mvcPath=/support/2/edit_ticket_entry/discussion_comments.jsp&<portlet:namespace />discussionTab=' + discussionTab;
				}
				else if ((discussionTab == 'history') || (discussionTab == 'solutions')) {
					tabURL += '<%= tabURL %>&<portlet:namespace />mvcPath=/support/2/edit_ticket_entry/discussion_' + discussionTab + '.jsp';
				}
				else {
					return;
				}

				var tabContentDiv = A.one('#<portlet:namespace />tabContent');

				A.io.request(
					tabURL,
					{
						data: {
							<portlet:namespace />commentBody0: '<%= HtmlUtil.escapeJS(ParamUtil.getString(request, "commentBody0")) %>'
						},
						on: {
							start: function() {
								tabContentDiv.html('<img src="<%= themeDisplay.getPathThemeImages() + "/aui/loading_indicator.gif" %>" style="display: block; margin: auto;" />');
							},
							success: function() {
								var response = this.get('responseData');

								tabContentDiv.html(response);

								var bbcodeEditorJavascript = document.getElementById('<portlet:namespace />bbcodeEditorJavascript');

								if (bbcodeEditorJavascript) {
									eval(bbcodeEditorJavascript.innerHTML);
								}

								A.all('.discussion .tabs span').removeClass('selected');

								var tab = A.one('.discussion .tabs #<portlet:namespace />' + discussionTab);

								tab.addClass('selected');

								<portlet:namespace />setUpThreeDotMenus();

								<c:if test="<%= Validator.isNotNull(discussionId) %>">
									if (firstLoad) {
										var discussionTop = document.getElementById('<portlet:namespace />scroll_<%= discussionId %>');

										if (discussionTop) {
											var discussionRect = discussionTop.getBoundingClientRect();

											window.scrollTo(0, discussionRect.top - 335);
										}
									}
								</c:if>
							}
						}
					}
				);
			},
			['aui-base', 'aui-io']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />loginDialog',
			function() {
				Liferay.Util.openWindow(
					{
						cache: false,
						dialog: {
							centered: true,
							close: false,
							cssClass: 'login-dialog',
							modal: true,
							resizable: false
						},
						id: '<portlet:namespace />loginDialog',
						uri: themeDisplay.getPathMain() + '/login/login?redirect=/group/customer/support/-/support/ticket/<%= ticketEntry.getDisplayId() %>'
					}
				);
			}
		);

		Liferay.provide(
			window,
			'<portlet:namespace />showAddAttachments',
			function() {
				var A = AUI();

				var addAttachments = A.one('#<portlet:namespace />addAttachments');

				if (addAttachments) {
					addAttachments.show()

					var hotfixCheckboxes = addAttachments.all('.hotfix-checkboxes');

					hotfixCheckboxes.each(
						function(checkbox) {
							checkbox.val('false');

							checkbox.set('checked', false);
						}
					);
				}

				var addAttachmentsButton = A.one('#<portlet:namespace />addAttachmentsButton');

				if (addAttachmentsButton) {
					addAttachmentsButton.hide();
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />showForm',
			function(suffix, isDraft, hasMaximumDraftTicketComment) {
				var A = AUI();

				var commentForm = A.one('#<portlet:namespace />commentForm' + suffix);

				if (commentForm) {
					commentForm.show();
				}

				var commentBody = A.one('#<portlet:namespace />commentBody' + suffix);

				if (commentBody) {
					commentBody.focus();

					var draftBody = A.one('#<portlet:namespace />draftBody');

					if (draftBody) {
						draftBody.val(commentBody.get('value'));
					}
				}

				var message = '<%= UnicodeLanguageUtil.get(request, "your-draft-comments-have-reached-maximum-comment-cannot-be-automatically-saved-as-draft") %>';

				if (!hasMaximumDraftTicketComment || isDraft) {
					message = '<%= UnicodeLanguageUtil.get(request, "comment-will-be-auto-saved-as-draft-every-30-seconds,-excluding-attachments") %>';
				}

				<portlet:namespace />updateMessageDisplay(suffix, 'portlet-msg-info', message);

				var formSuffix = A.one('#<portlet:namespace />suffix');

				if (formSuffix) {
					formSuffix.val(suffix);
				}

				var intervalId = A.one('#<portlet:namespace />intervalId');

				if (intervalId) {
					intervalId.val(
						setInterval(
							function() {
								<portlet:namespace />autoUpdateComment(suffix);
							},
							30000
						)
					);
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />toggleAll',
			function(collapse) {
				var A = AUI();

				var commentsContainer = A.one('#<portlet:namespace />commentsContainer');

				if (commentsContainer) {
					commentsContainer.all('.ticket-comment').each(
						function(item) {
							if (collapse) {
								item.set('className', item.get('className').replace('comment-expanded', 'comment-collapsed'));
							}
							else {
								item.set('className', item.get('className').replace('comment-collapsed', 'comment-expanded'));
							}
						}
					);
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateComment',
			function(suffix, status) {
				var A = AUI();

				var validateURL = '<%= PortalUtil.getPortalURL(request) %>' + '/delegate/user_session_validation';

				A.io.request(
					validateURL,
					{
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function() {
								var response = this.get('responseData');

								if (response.sessionValid) {
									if (response.pAuthToken) {
										var actionURL = document.getElementById('<portlet:namespace />ticketCommentFm').action;

										document.getElementById('<portlet:namespace />ticketCommentFm').action = actionURL.replace(/[?]p_auth=[A-Za-z0-9]+/g, '?p_auth=' + response.pAuthToken);
									}

									eval('var ticketCommentId = document.<portlet:namespace />ticketCommentFm.<portlet:namespace />ticketCommentId' + suffix + '.value;');

									<portlet:namespace />clearInterval();

									document.<portlet:namespace />ticketCommentFm.<portlet:namespace />ticketCommentId.value = ticketCommentId;
									document.<portlet:namespace />ticketCommentFm.<portlet:namespace />suffix.value = suffix;
									document.<portlet:namespace />ticketCommentFm.<portlet:namespace />ticketCommentStatus.value = status;

									submitForm(document.<portlet:namespace />ticketCommentFm);
								}
								else {
									<portlet:namespace />loginDialog();
								}
							}
						}
					}
				);
			},
			['aui-base', 'aui-io']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateMessageDisplay',
			function(suffix, className, message) {
				var A = AUI();

				var messageDisplay = A.one('#<portlet:namespace />commentMessageDisplay' + suffix);

				if (messageDisplay) {
					messageDisplay.set('className', className);

					messageDisplay.html(message);
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateTicketCommentType',
			function(ticketCommentId, type) {
				var A = AUI();

				A.io.request(
					'<liferay-portlet:actionURL name="updateTicketCommentType" />',
					{
						data: {
							<portlet:namespace />ticketCommentId: ticketCommentId,
							<portlet:namespace />type: type
						},
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function() {
								if (type == <%= TicketCommentConstants.TYPE_NORMAL %>) {
									A.one('#<portlet:namespace />footer_' + ticketCommentId).removeClass('type-solution');
								}
								else {
									A.all('.type-solution').removeClass('type-solution');

									A.one('#<portlet:namespace />footer_' + ticketCommentId).addClass('type-solution');
								}
							}
						}
					}
				);
			},
			['aui-base', 'aui-io']
		);
	</aui:script>

	<aui:script use="aui-base">
		<portlet:namespace />loadTab('<%= HtmlUtil.escape(discussionTab) %>', true);

		window.addEventListener(
			'keydown',
			function(A) {
				if (A.keyCode === 27) {
					if (Liferay.Util.getWindow()) {
						Liferay.Util.getWindow().close();
					}
				}
			}
		);
	</aui:script>
</c:if>