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

int[] userVisibilities = null;

if (screenShareMode) {
	userVisibilities = new int[] {VisibilityConstants.PUBLIC};
}
else {
	userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
}
%>

<div class="aui-w100 history">

	<%
	if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADMIN)) {
		userVisibilities = ArrayUtil.append(userVisibilities, VisibilityConstants.ADMIN);
	}

	List<List<AuditEntry>> auditEntrySets = AuditEntryLocalServiceUtil.getAuditEntrySets(PortalUtil.getClassNameId(TicketEntry.class.getName()), ticketEntry.getTicketEntryId(), userVisibilities);

	for (int i = 0; i < auditEntrySets.size(); i++) {
		List<AuditEntry> auditEntries = auditEntrySets.get(i);

		AuditEntry auditEntry = auditEntries.get(0);
	%>

		<div class="audit-entry-set" id="<portlet:namespace />auditSet<%= auditEntry.getAuditSetId() %>">
			<div class="header" id="<portlet:namespace />audit<%= auditEntry.getAuditEntryId() %>">
				<div class="user-display" onClick="<portlet:namespace />toggleComment(event, <%= auditEntry.getAuditEntryId() %>);">

					<%
					String portraitURL = StringPool.BLANK;

					String auditEntryUserName = StringPool.BLANK;

					if (auditEntry.getUserId() != OSBConstants.USER_DEFAULT_USER_ID) {
						User auditEntryUser = UserLocalServiceUtil.getUser(auditEntry.getUserId());

						portraitURL = auditEntryUser.getPortraitURL(themeDisplay);

						auditEntryUserName = auditEntryUser.getFullName();
					}
					else {
						auditEntryUserName = "Auto";
					}
					%>

					<div class="audit user-avatar" style="background-image: url('<%= portraitURL %>&height=30&width=30')"></div>

					<span>
						<%= HtmlUtil.escape(auditEntryUserName) %>

						<c:if test="<%= auditEntry.getUserId() != OSBConstants.USER_DEFAULT_USER_ID %>">
							<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
								<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
								<portlet:param name="userId" value="<%= String.valueOf(auditEntry.getUserId()) %>" />
							</liferay-util:include>
						</c:if>
					</span>
				</div>

				&gt;

				<span class="summary">
					<liferay-ui:message key="<%= auditEntry.getActionLabel() %>" />

					<c:if test="<%= (auditEntry.getAction() != AuditEntryConstants.ACTION_SOLUTION_PROPOSED) && (auditEntry.getAction() != AuditEntryConstants.ACTION_SOLUTION_REJECTED) %>">
						<liferay-ui:message key="<%= auditEntry.getFieldClassNameIdLabel() %>" />
					</c:if>

					<c:if test="<%= (auditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketComment.class)) && (auditEntry.getPreviousAuditEntryId() > 0) && (auditEntry.getAction() != AuditEntryConstants.ACTION_ADD) %>">
						(<a href="#<portlet:namespace />audit<%= auditEntry.getPreviousAuditEntryId() %>"><liferay-ui:message key="go-to-previous" /></a>)
					</c:if>

					<c:if test="<%= auditEntry.getFieldClassNameId() != PortalUtil.getClassNameId(TicketEntry.class) %>">
						<c:if test="<%= auditEntry.getVisibility() != VisibilityConstants.PUBLIC %>">
							&gt;

							<span class="visibility-<%= auditEntry.getVisibilityLabel() %>">
								<%= LanguageUtil.format(request, "visible-to-x", auditEntry.getVisibilityLabel()) %>
							</span>
						</c:if>
					</c:if>
				</span>

				<div class="create-date">
					<span title="<%= fullDateFormatDateTime.format(auditEntry.getCreateDate()) %>"><%= shortDateFormatDate.format(auditEntry.getCreateDate()) %> <%= shortDateFormatTime.format(auditEntry.getCreateDate()) %></span>
				</div>
			</div>

			<div class="content">
				<c:choose>
					<c:when test="<%= (auditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketCall.class)) || (auditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketComment.class)) %>">
						<div class="aui-w100 comment-collapsed content-column ticket-comment" id="<portlet:namespace />commentContainer<%= auditEntry.getAuditEntryId() %>">
							<div class="comment">
								<div class="comment-body <%= (auditEntry.getAction() == AuditEntryConstants.ACTION_SOLUTION_REJECTED) ? "rejected-comment-body" : "" %>">

									<%
									for (int j = 0; j < auditEntries.size(); j++) {
										AuditEntry curAuditEntry = auditEntries.get(j);
									%>

										<c:choose>
											<c:when test="<%= (curAuditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketCall.class)) || (curAuditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketComment.class)) %>">
												<div>
													<c:choose>
														<c:when test="<%= Validator.isNotNull(auditEntry.getNewValue()) %>">
															<pre><%= SupportUtil.getHTML(auditEntry.getNewValue(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
														</c:when>
														<c:otherwise>
															<pre><%= SupportUtil.getHTML(auditEntry.getOldValue(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
														</c:otherwise>
													</c:choose>
												</div>
											</c:when>
											<c:when test="<%= curAuditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketAttachment.class) %>">
												<div>
													<strong><liferay-ui:message key="attachments" />:</strong> <%= curAuditEntry.getNewValue() %>

													<br />
												</div>
											</c:when>
											<c:when test="<%= curAuditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(TicketLink.class) %>">
												<div>
													<strong><liferay-ui:message key="links" />:</strong> <%= curAuditEntry.getNewValue() %>

													<br />
												</div>
											</c:when>
										</c:choose>

									<%
									}
									%>

								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<div class="aui-w20 content-column txt-sb">
								<div class="content-column-content left-column">
									<liferay-ui:message key="field" />
								</div>
							</div>

							<div class="aui-w40 content-column txt-sb">
								<div class="content-column-content middle-column">
									<liferay-ui:message key="original-value" />
								</div>
							</div>

							<div class="aui-w40 content-column txt-sb">
								<div class="content-column-content right-column">
									<liferay-ui:message key="new-value" />
								</div>
							</div>

							<%
							for (int j = 0; j < auditEntries.size(); j++) {
								AuditEntry curAuditEntry = auditEntries.get(j);

								String oldLabel = curAuditEntry.getOldLabel();

								if (Validator.isNull(oldLabel)) {
									oldLabel = curAuditEntry.getOldValue();
								}

								String newLabel = curAuditEntry.getNewLabel();

								if (Validator.isNull(newLabel)) {
									newLabel = curAuditEntry.getNewValue();
								}
							%>

								<div class="cleared"></div>

								<div class="aui-w20 content-column txt-sb">
									<div class="content-column-content left-column">
										<liferay-ui:message key="<%= curAuditEntry.getFieldLabel() %>" />
									</div>
								</div>

								<div class="aui-w40 content-column">
									<div class="content-column-content middle-column">
										<c:choose>
											<c:when test="<%= Validator.isNull(oldLabel) %>">
												<%= AuditEntryConstants.NOT_AVAILABLE %>
											</c:when>
											<c:when test="<%= curAuditEntry.getI18n() %>">
												<liferay-ui:message key="<%= HtmlUtil.escape(oldLabel) %>" />
											</c:when>
											<c:when test="<%= (curAuditEntry.getField() == AuditEntryConstants.FIELD_DESCRIPTION) || (curAuditEntry.getField() == AuditEntryConstants.FIELD_REPRODUCTION_STEPS) %>">
												<pre><%= SupportUtil.getHTML(oldLabel) %></pre>
											</c:when>
											<c:when test="<%= (curAuditEntry.getField() == AuditEntryConstants.FIELD_ENV_BROWSER) || (curAuditEntry.getField() == AuditEntryConstants.FIELD_ENV_OS) %>">

												<%
												int oldValue = GetterUtil.getInteger(curAuditEntry.getOldValue());
												%>

												<liferay-ui:message key="<%= TicketEntryConstants.getEnvLabel(oldValue) %>" />

												<c:if test="<%= Validator.isNotNull(curAuditEntry.getOldLabel()) %>">
													- <%= HtmlUtil.escape(curAuditEntry.getOldLabel()) %>
												</c:if>
											</c:when>
											<c:otherwise>
												<%= HtmlUtil.escape(oldLabel) %>
											</c:otherwise>
										</c:choose>
									</div>
								</div>

								<div class="aui-w40 content-column">
									<div class="content-column-content right-column">
										<c:choose>
											<c:when test="<%= Validator.isNull(newLabel) %>">
												<%= AuditEntryConstants.NOT_AVAILABLE %>
											</c:when>
											<c:when test="<%= curAuditEntry.getI18n() %>">
												<liferay-ui:message key="<%= HtmlUtil.escape(newLabel) %>" />
											</c:when>
											<c:when test="<%= (curAuditEntry.getField() == AuditEntryConstants.FIELD_DESCRIPTION) || (curAuditEntry.getField() == AuditEntryConstants.FIELD_REPRODUCTION_STEPS) %>">
												<pre><%= SupportUtil.getHTML(newLabel) %></pre>
											</c:when>
											<c:when test="<%= (curAuditEntry.getField() == AuditEntryConstants.FIELD_ENV_BROWSER) || (curAuditEntry.getField() == AuditEntryConstants.FIELD_ENV_OS) %>">

												<%
												int newValue = GetterUtil.getInteger(curAuditEntry.getNewValue());
												%>

												<liferay-ui:message key="<%= TicketEntryConstants.getEnvLabel(newValue) %>" />

												<c:if test="<%= Validator.isNotNull(curAuditEntry.getNewLabel()) %>">
													- <%= HtmlUtil.escape(curAuditEntry.getNewLabel()) %>
												</c:if>
											</c:when>
											<c:otherwise>
												<%= HtmlUtil.escape(newLabel) %>
											</c:otherwise>
										</c:choose>
									</div>
								</div>

							<%
							}
							%>

						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

	<%
	}
	%>

</div>