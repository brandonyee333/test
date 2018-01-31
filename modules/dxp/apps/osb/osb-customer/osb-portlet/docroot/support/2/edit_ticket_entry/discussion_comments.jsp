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

long discussionId = ParamUtil.getLong(request, "discussionId");
long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);

AccountEntry accountEntry = ticketEntry.getAccountEntry();

int[] userVisibilities = null;

if (screenShareMode) {
	userVisibilities = new int[] {VisibilityConstants.PUBLIC};
}
else {
	userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
}

boolean orderByAsc = true;

if (reverseCommentOrder) {
	orderByAsc = false;
}

List<TicketEntryDiscussion> ticketEntryDiscussions = new ArrayList<TicketEntryDiscussion>();

if (discussionTab.equals("all-comments")) {
	ticketEntryDiscussions = SupportUtil.getTicketEntryDiscussions(themeDisplay.getUserId(), ticketEntry.getTicketEntryId(), userVisibilities, orderByAsc);
}
else {
	if (discussionTab.equals("comments")) {
		discussionTab = "public";
	}

	ticketEntryDiscussions = SupportUtil.getTicketEntryDiscussions(themeDisplay.getUserId(), ticketEntry.getTicketEntryId(), new int[] {VisibilityConstants.toVisibility(discussionTab)}, orderByAsc);
}

boolean hasMaximumDraftTicketComment = false;

if (!ticketEntryDiscussions.isEmpty()) {
	TicketEntryDiscussion ticketEntryDiscussion = null;

	if (orderByAsc) {
		ticketEntryDiscussion = ticketEntryDiscussions.get(ticketEntryDiscussions.size() - 1);
	}
	else {
		ticketEntryDiscussion = ticketEntryDiscussions.get(0);
	}

	if (ticketEntryDiscussion.getStatus() == WorkflowConstants.STATUS_DRAFT) {
		hasMaximumDraftTicketComment = true;
	}
}

boolean partnerWorker = false;

if (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId())) {
	partnerWorker = true;
}

boolean ticketWorker = false;

if (liferayIncOrg || partnerWorker) {
	ticketWorker = true;
}

boolean hasAddTicketCommentPermission = (discussionTab.equals("public") && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_COMMENT_PUBLIC)) || (discussionTab.equals("workers") && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_COMMENT_WORKERS)) || (discussionTab.equals("liferay") && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_COMMENT_LIFERAY_INC));
boolean hasMarkAsSolutionPermission = OSBTicketCommentPermission.contains(permissionChecker, null, OSBActionKeys.MARK_AS_SOLUTION);
%>

<portlet:renderURL copyCurrentRenderParameters="<%= false %>" var="redirectURL" windowState="maximized">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
</portlet:renderURL>

<portlet:actionURL name="updateTicketComment" var="updateTicketCommentURL" windowState="maximized">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTicketCommentURL %>" enctype="multipart/form-data" id="<portlet:namespace />fm2" method="post" name="fm2">
	<input name="<portlet:namespace />discussionTab" type="hidden" value="<%= HtmlUtil.escapeAttribute(discussionTab) %>" />
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= redirectURL %>" />
	<input name="<portlet:namespace />ticketCommentId" type="hidden" value="" />
	<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />
	<input name="<portlet:namespace />suffix" type="hidden" />
	<input name="<portlet:namespace />visibility" type="hidden" value="<%= VisibilityConstants.toVisibility(discussionTab) %>" />
	<input name="<portlet:namespace />ticketCommentStatus" type="hidden" value="" />
	<input id="<portlet:namespace />type" name="<portlet:namespace />type" type="hidden" value="" />
	<input name="<portlet:namespace />ticketCannedResponseId" type="hidden" value="" />
	<input id="<portlet:namespace />intervalId" type="hidden" />
	<input id="<portlet:namespace />draftBody" type="hidden" />

	<c:if test="<%= hasAddTicketCommentPermission %>">
		<c:if test='<%= discussionTab.equals("liferay") || discussionTab.equals("public") || discussionTab.equals("workers") %>'>
			<%@ include file="/support/2/edit_ticket_entry/add_ticket_comment_form.jspf" %>
		</c:if>
	</c:if>

	<br /><br />

	<div>

		<%
		for (int i = 1; i <= ticketEntryDiscussions.size(); i++) {
			TicketEntryDiscussion ticketEntryDiscussion = ticketEntryDiscussions.get(i - 1);

			TicketComment ticketComment = ticketEntryDiscussion.getTicketComment();

			String cssClass = "comment-expanded";

			if ((ticketEntryDiscussions.size() > 20) && ((orderByAsc && (i <= (ticketEntryDiscussions.size() - 5))) || (!orderByAsc && (i > 5))) && (discussionId != ticketEntryDiscussion.getDiscussionId())) {
				cssClass = "comment-collapsed";
			}
			else if (discussionId == ticketEntryDiscussion.getDiscussionId()) {
				cssClass += " comment-scroll";
			}
		%>

			<div class="clearfix ticket-comment <%= cssClass %>" id="<portlet:namespace />ticketCommentContainer<%= i %>">
				<div class="content-column" onClick="<portlet:namespace />toggleComment(event, <%= i %>, 'ticket');">
					<div>
						<div class="user-avatar" style="background-image: url('<%= ticketEntryDiscussion.getUserPortraitURL(themeDisplay) %>&height=70&width=70')"></div>

						<div class="small-user-avatar" style="background-image: url('<%= ticketEntryDiscussion.getUserPortraitURL(themeDisplay) %>&height=50&width=50')"></div>
					</div>
				</div>

				<div class="comment-text-container" id="<portlet:namespace />scroll_<%= ticketEntryDiscussion.getDiscussionId() %>">
					<div class="user-name">
						<span onClick="<portlet:namespace />toggleComment(event, <%= i %>, 'ticket');">
							<%= HtmlUtil.escape(ticketEntryDiscussion.getUserName()) %>

							<c:if test="<%= ticketEntryDiscussion.getUser() != null %>">
								<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
									<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
									<portlet:param name="userId" value="<%= String.valueOf(ticketEntryDiscussion.getUserId()) %>" />
								</liferay-util:include>
							</c:if>
						</span>

						<%
						Map<String, String> menuOptions = new TreeMap<String, String>();

						if (ticketComment != null) {
							if (OSBTicketCommentPermission.contains(permissionChecker, ticketComment, OSBActionKeys.UPDATE)) {
								StringBundler sb = new StringBundler(9);

								sb.append("javascript:");
								sb.append(renderResponse.getNamespace());
								sb.append("showForm(");
								sb.append(i);
								sb.append(", ");
								sb.append(ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT);
								sb.append(", ");
								sb.append(hasMaximumDraftTicketComment);
								sb.append(");");

								menuOptions.put("edit", sb.toString());
							}

							if (discussionTab.equals("public") && hasMarkAsSolutionPermission && (ticketComment.getType() == TicketCommentConstants.TYPE_NORMAL) && (ticketComment.getStatus() == WorkflowConstants.STATUS_APPROVED)) {
								menuOptions.put("mark-as-solution", "javascript:" + renderResponse.getNamespace() + "updateTicketCommentType(" + ticketComment.getTicketCommentId() + ", " + TicketCommentConstants.TYPE_SOLUTION + ");");
							}

							if (OSBTicketCommentPermission.contains(permissionChecker, ticketComment, OSBActionKeys.DELETE)) {
								menuOptions.put("delete", "javascript:" + renderResponse.getNamespace() + "deleteComment(" + i + ");");
							}
						}
						%>

						<c:if test="<%= !menuOptions.isEmpty() %>">
							<span class="comment-menu three-dot">
								<span class="three-dot-icon">
									<span style="top: 2px"></span>
									<span style="top: 8px;"></span>
									<span style="top: 14px;"></span>
								</span>

								<div class="drop-down-menu">
									<div class="drop-down-arrow">
										<div></div>
									</div>

									<ul>

										<%
										for (Map.Entry<String, String> entry : menuOptions.entrySet()) {
											String label = entry.getKey();
											String url = entry.getValue();
										%>

											<li>
												<a href="<%= url %>" <%= url.startsWith("javascript") ? "" : "target=\"_blank\"" %>><liferay-ui:message key="<%= label %>" /></a>
											</li>

										<%
										}
										%>

									</ul>
								</div>
							</span>
						</c:if>

						<c:if test="<%= ticketEntryDiscussion.getStatus() != WorkflowConstants.STATUS_DRAFT %>">

							<%
							long customerPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

							PortletURL permalinkURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_SUPPORT, customerPlid, PortletRequest.RENDER_PHASE);

							permalinkURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
							permalinkURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
							permalinkURL.setParameter("discussionId", String.valueOf(ticketEntryDiscussion.getDiscussionId()));
							permalinkURL.setParameter("discussionType", ticketEntryDiscussion.getDiscussionType());
							permalinkURL.setParameter("friendly", String.valueOf(Boolean.TRUE));
							%>

							<div class="permalink">
								<a href="<%= permalinkURL.toString() %>" target="_blank"><img src="<%= PortalUtil.getPathContext(request) %>/support/2/images/link.png" /></a>
							</div>
						</c:if>
					</div>

					<div class="post-date">
						<%= fullDateFormatDateTime.format(ticketEntryDiscussion.getCreateDate()) %>
					</div>

					<span class="post-date">
						<%= shortDateFormatDate.format(ticketEntryDiscussion.getCreateDate()) %> <%= shortDateFormatTime.format(ticketEntryDiscussion.getCreateDate()) %>
					</span>

					<div class="comment">
						<c:if test="<%= ticketComment != null %>">
							<input name="<portlet:namespace />ticketCommentId<%= i %>" type="hidden" value="<%= ticketComment.getTicketCommentId() %>" />

							<%
							String commentBodyClass = "comment-body";

							if (ticketComment.getType() == TicketCommentConstants.TYPE_GAME_PLAN) {
								commentBodyClass += " type-game-plan";
							}

							if (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) {
								commentBodyClass += " comment-draft-border";
							}
							%>

							<div class="<%= commentBodyClass %>">
								<div class="comment-body-wrapper">
									<c:if test="<%= ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT %>">
										<div class="comment-draft-hint">
											<liferay-ui:message key="draft-comment" />:
										</div>
									</c:if>

									<div>

										<%
										int statusReason = GetterUtil.getInteger(ticketComment.getSettingsProperty("statusReason"));
										%>

										<c:if test="<%= statusReason > 0 %>">
											<strong>
												<liferay-ui:message key="solution-rejected" />: <%= LanguageUtil.get(request, TicketSolutionConstants.getStatusReasonLabel(statusReason)) %>
											</strong>
										</c:if>

										<pre><%= SupportUtil.getHTML(ticketComment) %></pre>
									</div>
								</div>
							</div>
						</c:if>

						<%
						List<TicketAttachment> ticketAttachments = ticketEntryDiscussion.getTicketAttachments();
						%>

						<c:if test="<%= !ticketAttachments.isEmpty() %>">
							<div>
								<strong><liferay-ui:message key="attachments" />:</strong>

								<%
								for (TicketAttachment ticketAttachment : ticketAttachments) {
									boolean hasViewPermission = OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.VIEW);

									LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

									ticketAttachmentURL.setCopyCurrentRenderParameters(false);
									ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
									ticketAttachmentURL.setResourceID("ticketAttachment");
								%>

									<div>
										<c:if test="<%= hasViewPermission %>">
											<a href="<%= ticketAttachmentURL.toString() %>" target="_blank">
										</c:if>

										<%= HtmlUtil.escape(ticketAttachment.getFileName()) %>

										<c:if test="<%= hasViewPermission %>">
											</a>
										</c:if>

										(<%= TextFormatter.formatStorageSize((double)ticketAttachment.getFileSize(), locale) %>k)
									</div>

								<%
								}
								%>

							</div>
						</c:if>

						<%
						List<TicketLink> ticketLinks = ticketEntryDiscussion.getTicketLinks();
						%>

						<c:if test="<%= !ticketLinks.isEmpty() %>">
							<div>
								<strong><liferay-ui:message key="links" />:</strong>

								<%
								for (TicketLink ticketLink : ticketLinks) {
								%>

									<div>
										<a href="<%= ticketLink.getUrl() %>" target="_blank"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>
									</div>

								<%
								}
								%>

							</div>
						</c:if>

						<c:if test="<%= ticketComment != null %>">

							<%
							cssClass = "comment-footer";

							if (discussionTab.equals("all-comments")) {
								cssClass += " visibility-" + ticketEntryDiscussion.getVisibilityLabel();
							}

							if (discussionTab.equals("public") && (ticketComment != null) && (ticketComment.getType() == TicketCommentConstants.TYPE_SOLUTION)) {
								cssClass += " type-solution";
							}
							%>

							<div class="clearfix <%= cssClass %>" id="<portlet:namespace />footer_<%= ticketComment.getTicketCommentId() %>">
								<c:choose>
									<c:when test='<%= discussionTab.equals("public") %>'>
										<div class="footnote solution-flag">
											<c:if test="<%= hasMarkAsSolutionPermission %>">
												(<a href="javascript:<portlet:namespace />updateTicketCommentType(<%= ticketComment.getTicketCommentId() %>, <%= TicketCommentConstants.TYPE_NORMAL %>);"><liferay-ui:message key="unmark" /></a>)
											</c:if>

											<liferay-ui:icon
												image="checked"
												label="<%= true %>"
												message="solution"
											/>
										</div>
									</c:when>
									<c:when test='<%= discussionTab.equals("all-comments") && (ticketComment.getVisibility() != VisibilityConstants.PUBLIC) %>'>
										<div class="footnote">
											<%= LanguageUtil.format(request, "visible-to-x", ticketComment.getVisibilityLabel()) %>
										</div>
									</c:when>
								</c:choose>
							</div>

							<div id="<portlet:namespace />commentForm<%= i %>" style="display: none;">
								<div id="<portlet:namespace />commentMessageDisplay<%= i %>"></div>

								<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
									<liferay-util:param name="content" value="<%= ticketComment.getBody() %>" />
									<liferay-util:param name="editorId" value='<%= "editor" + i %>' />
									<liferay-util:param name="name" value='<%= "commentBody" + i %>' />
								</liferay-util:include>

								<c:if test="<%= ticketWorker %>">

									<%
									int[] pendingTypes = null;

									if (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) {
										pendingTypes = StringUtil.split(ticketComment.getSettingsProperty("pendingTypes"), 0);
									}
									else {
										pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING, TicketFlagConstants.FLAG_TRUE);
									}
									%>

									<div class="comment-pending pull-right <%= (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) ? "" : "hide" %>" id="<portlet:namespace />pendingTypesDisplay<%= i %>">
										<liferay-ui:message key="need-response-from" />:

										<c:if test='<%= discussionTab.equals("public") %>'>
											<input <%= ArrayUtil.contains(pendingTypes, TicketFlagConstants.TYPE_PENDING_CUSTOMER) ? "checked=\"checked\"" : "" %> name="<portlet:namespace />pendingTypes<%= i %>" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_CUSTOMER %>" />

											<liferay-ui:message key="customer" />
										</c:if>

										<c:if test='<%= !discussionTab.equals("liferay") %>'>
											<input <%= ArrayUtil.contains(pendingTypes, TicketFlagConstants.TYPE_PENDING_PARTNER) ? "checked=\"checked\"" : "" %> name="<portlet:namespace />pendingTypes<%= i %>" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_PARTNER %>" />

											<liferay-ui:message key="partner" />
										</c:if>

										<c:if test="<%= (ticketEntry.getEscalationLevel() != TicketEntryConstants.ESCALATION_LEVEL_P1) %>">
											<input <%= ArrayUtil.contains(pendingTypes, TicketFlagConstants.TYPE_PENDING_LIFERAY) ? "checked=\"checked\"" : "" %> name="<portlet:namespace />pendingTypes<%= i %>" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_LIFERAY %>" />

											<liferay-ui:message key="liferay" />
										</c:if>
									</div>
								</c:if>

								<c:if test="<%= (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry.getTicketEntryId(), ActionKeys.ADD_ATTACHMENT) %>">
									<div>
										<table class="lfr-table">
										<tr>
											<th>
												<strong><liferay-ui:message key="upload-files" /></strong>
											</th>

											<c:if test="<%= ticketWorker %>">
												<th>
													<strong><liferay-ui:message key="hotfix" /></strong>
												</th>
											</c:if>
										</tr>

										<%
										List<TicketAttachment> draftTicketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(user.getUserId(), ticketEntry.getTicketEntryId(), VisibilityConstants.toVisibility(discussionTab), WorkflowConstants.STATUS_DRAFT);

										for (int j = 1; j <= 3; j++) {
											String draftSuffix = i + StringPool.UNDERLINE + j;

											TicketAttachment ticketAttachment = null;

											if ((j - 1) < draftTicketAttachments.size()) {
												ticketAttachment = draftTicketAttachments.get(j - 1);
											}
										%>

											<tr>
												<td>
													<label for="<portlet:namespace />file<%= draftSuffix %>"><liferay-ui:message key="file" /> <%= j %></label>

													<c:if test="<%= ticketAttachment != null %>">
														<span id="<portlet:namespace />fileName<%= draftSuffix %>"><%= ticketAttachment.getFileName().substring(ticketAttachment.getFileName().indexOf(StringPool.DASH) + 1) %></span>
													</c:if>

													<input class="<%= (ticketAttachment != null) ? "hide" : "" %>" id="<portlet:namespace /><%= "file" + draftSuffix %>" name="<portlet:namespace /><%= "file" + draftSuffix %>" type="file" />
												</td>

												<c:if test="<%= ticketWorker %>">
													<td>
														<aui:input checked="<%= (ticketAttachment != null) && (ticketAttachment.getType() == TicketAttachmentConstants.TYPE_HOTFIX) %>" disabled="<%= (ticketAttachment != null) ? true : false %>" label="" name='<%= "hotfix" + draftSuffix %>' type="checkbox" />
													</td>
												</c:if>

												<c:if test="<%= ticketAttachment != null %>">
													<td>
														<input class="aui-button-input" id="<portlet:namespace /><%= "deleteTicketAttachment" + draftSuffix %>" onClick="<portlet:namespace />deleteTicketAttachment(<%= i %>, <%= ticketAttachment.getTicketAttachmentId() %>, <%= j %>);" type="button" value="<liferay-ui:message key="delete" />" />
													</td>
												</c:if>
											</tr>

										<%
										}
										%>

										</table>
									</div>
								</c:if>

								<br />

								<div class="comment-buttons pull-right">
									<input class="aui-button-input" onClick="<portlet:namespace />updateComment(<%= i %>, <%= ticketComment.getStatus() %>);" type="button" value="<liferay-ui:message key="update" />" />

									<c:if test="<%= (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) && ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) || liferayIncOrg || partnerWorker) %>">
										<input class="aui-button-input" onClick="<portlet:namespace />updateComment(<%= i %>, <%= WorkflowConstants.STATUS_APPROVED %>);" type="button" value="<liferay-ui:message key="publish" />" />
									</c:if>

									<c:if test='<%= !discussionTab.equals("liferay") && (ticketComment.getStatus() != WorkflowConstants.STATUS_DRAFT) && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) %>'>
										<input class="aui-button-input" id="<portlet:namespace />needResponseButton" onClick="AUI().one('#<portlet:namespace />pendingTypesDisplay<%= i %>').show(); void('');" type="button" value="<liferay-ui:message key="add-need-response-from" />" />
									</c:if>

									<input class="aui-button-input" onClick="<portlet:namespace />cancelComment(<%= i %>);" type="button" value="<liferay-ui:message key="cancel" />" />
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>

		<%
		}
		%>

	</div>
</aui:form>