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

<%@ include file="/support/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean accountCustomer = (Boolean)request.getAttribute("edit_ticket_entry.jsp-accountCustomer");
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_ticket_entry.jsp-accountEntry");
boolean closed = (Boolean)request.getAttribute("edit_ticket_entry.jsp-closed");
boolean edit = (Boolean)request.getAttribute("edit_ticket_entry.jsp-edit");
boolean hasUpdateAdmin = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdmin");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
boolean hasUpdateBasic = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateBasic");
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("edit_ticket_entry.jsp-offeringEntry");
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry.jsp-productEntry");
boolean partnerWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-partnerWorker");
SupportResponse supportResponse = (SupportResponse)request.getAttribute("edit_ticket_entry.jsp-supportResponse");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

long supportRegionId = BeanParamUtil.getLong(ticketEntry, request, "supportRegionId");
String languageId = BeanParamUtil.getString(ticketEntry, request, "languageId");
String subject = BeanParamUtil.getString(ticketEntry, request, "subject");
String description = BeanParamUtil.getString(ticketEntry, request, "description");
String reproductionSteps = BeanParamUtil.getString(ticketEntry, request, "reproductionSteps");
int severity = BeanParamUtil.getInteger(ticketEntry, request, "severity");
int status = BeanParamUtil.getInteger(ticketEntry, request, "status");
int weight = BeanParamUtil.getInteger(ticketEntry, request, "weight");
int escalationLevel = BeanParamUtil.getInteger(ticketEntry, request, "escalationLevel");
int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
int subcomponent = BeanParamUtil.getInteger(ticketEntry, request, "subcomponent");
String subcomponentCustom = BeanParamUtil.getString(ticketEntry, request, "subcomponentCustom");
int resolution = BeanParamUtil.getInteger(ticketEntry, request, "resolution");

Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

if (ticketEntry.getDueDate() != null) {
	dueDate.setTime(ticketEntry.getDueDate());
}

AccountAttachment accountAttachment = null;

if (liferayIncOrg) {
	List<AccountAttachment> accountAttachments = AccountAttachmentServiceUtil.getAccountAttachments(accountEntry.getAccountEntryId(), AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID, AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);

	if (!accountAttachments.isEmpty()) {
		accountAttachment = accountAttachments.get(0);
	}
}

TicketFeedback liferayTicketFeedback = null;

if (closed && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE)) {
	liferayTicketFeedback = TicketFeedbackServiceUtil.fetchFirstOpenTicketFeedback(user.getUserId(), ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_LIFERAY);
}

TicketFeedback partnerTicketFeedback = null;

if (!screenShareMode && liferayIncOrg) {
	partnerTicketFeedback = TicketFeedbackServiceUtil.fetchFirstTicketFeedback(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_PARTNER);
}

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");
%>

<liferay-ui:error exception="<%= NoSuchOfferingEntryException.class %>" message="please-select-a-valid-support" />

<liferay-ui:error exception="<%= RequiredFieldException.class %>">

	<%
	RequiredFieldException rfe = (RequiredFieldException)errorException;
	%>

	<liferay-ui:message arguments="<%= LanguageUtil.get(request, rfe.getLabelId()) %>" key="please-upload-a-x-file" />
</liferay-ui:error>

<liferay-ui:error exception="<%= TicketCommentPendingTypeException.class %>" message="please-select-a-valid-need-response-from" />

<liferay-ui:error exception="<%= TicketEntryAttachmentSizeException.class %>">

	<%
	TicketEntryAttachmentSizeException tease = (TicketEntryAttachmentSizeException)errorException;
	%>

	<c:choose>
		<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EMPTY_FILE %>">
			<liferay-ui:message key="the-file-contains-no-data-and-cannot-be-uploaded" />
		</c:when>
		<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EXCEEDS_LIMIT %>">
			<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
		</c:when>
	</c:choose>
</liferay-ui:error>

<liferay-ui:error exception="<%= TicketEntryComponentException.class %>" message="please-select-a-valid-component" />
<liferay-ui:error exception="<%= TicketEntryResolutionException.class %>" message="please-select-a-valid-resolution" />
<liferay-ui:error exception="<%= TicketEntrySeverityException.class %>" message="please-select-a-valid-severity" />
<liferay-ui:error exception="<%= TicketEntryStatusException.class %>" message="please-select-a-valid-status" />
<liferay-ui:error exception="<%= TicketEntrySubcomponentException.class %>" message="please-provide-a-valid-subcomponent" />
<liferay-ui:error exception="<%= TicketEntrySubjectException.class %>" message="please-enter-a-valid-subject" />
<liferay-ui:error exception="<%= TicketEntrySystemStatusException.class %>" message="please-select-a-valid-system-status" />
<liferay-ui:error exception="<%= TicketEntryWeightException.class %>" message="please-enter-a-valid-weight" />

<liferay-ui:error exception="<%= TicketInformationException.class %>">

	<%
	TicketInformationException tie = (TicketInformationException)errorException;
	%>

	<liferay-ui:message arguments="<%= tie.getMessage() %>" key="invalid-value-provided-for-x" />
</liferay-ui:error>

<liferay-ui:error exception="<%= TicketSolutionStatusMessageException.class %>" message="please-enter-a-valid-rejection-comment" />

<c:choose>
	<c:when test="<%= edit && hasUpdateAdvanced %>">
		<h2 class="section-heading">
			<liferay-ui:message key="subject" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<aui:input bean="<%= ticketEntry %>" label="" model="<%= TicketEntry.class %>" name="subject" />
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="page-heading">
			<h1>
				<%= HtmlUtil.escape(ticketEntry.getSubject()) %>

				<input name="<portlet:namespace />subject" type="hidden" value="<%= HtmlUtil.escape(ticketEntry.getSubject()) %>" />
			</h1>
		</div>
	</c:otherwise>
</c:choose>

<%
TicketSolution ticketSolution = TicketSolutionLocalServiceUtil.getActiveTicketSolution(ticketEntry.getTicketEntryId());
%>

<c:if test="<%= (ticketSolution != null) && (ticketSolution.getStatus() != TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION) && hasUpdateBasic && (accountCustomer || (user.getUserId() == ticketEntry.getUserId()) || (partnerWorker && PartnerWorkerLocalServiceUtil.hasPartnerWorker(ticketEntry.getUserId(), accountEntry.getPartnerEntryId()))) %>">
	<div class="solution-confirm-tab">&nbsp;</div>

	<div class="aui-helper-hidden">
		<div class="solution-confirm-container">
			<div class="callout-content">
				<strong><liferay-ui:message key="solution" />:</strong>

				<div class="solution-confirm-comment">
					<pre><%= SupportUtil.getHTML(ticketSolution.getSolution(), TicketCommentConstants.FORMAT_BBCODE) %></pre>
				</div>

				<%
				List<TicketAttachment> ticketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(ticketEntry.getTicketEntryId(), ticketSolution.getTicketSolutionId());

				for (TicketAttachment ticketAttachment : ticketAttachments) {
					LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					ticketAttachmentURL.setCopyCurrentRenderParameters(false);
					ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
					ticketAttachmentURL.setResourceID("ticketAttachment");
				%>

					<div class="callout-content">
						<strong><liferay-ui:message key="attachments" />:</strong>

						<a href="<%= ticketAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(ticketAttachment.getFileName()) %></a> (<%= TextFormatter.formatKB((double)ticketAttachment.getFileSize(), locale) %>k)
					</div>

				<%
				}

				List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketEntry.getTicketEntryId(), ticketSolution.getTicketSolutionId());

				for (TicketLink ticketLink : ticketLinks) {
				%>

					<div class="callout-content">
						<strong><liferay-ui:message key="links" />:</strong>

						<a href="<%= ticketLink.getUrl() %>" target="_blank"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>
					</div>

				<%
				}
				%>

			</div>

			<div class="solution-confirm-message">
				<div class="solution-confirm-question">
					<c:choose>
						<c:when test="<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_RESOLVED) || (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_UNABLE_TO_TEST) %>">
							<liferay-ui:message key="did-the-solution-work-in-your-production-environment" />
						</c:when>
						<c:when test="<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_RESOLVED_MANUALLY) || (ticketSolution.getType() == TicketSolutionConstants.TYPE_SERVICE) %>">
							<liferay-ui:message key="did-the-proposed-solution-solve-the-reported-issue" />
						</c:when>
						<c:when test="<%= ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING %>">
							<liferay-ui:message key="did-the-solution-work-in-your-preproduction-environment" />
						</c:when>
					</c:choose>
				</div>

				<div class="solution-confirm-answer">
					<a class="btn btn-yes solution-confirm-btn" href="javascript:;" onClick="<portlet:namespace />updateTicketSolution(<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING) ? TicketSolutionConstants.STATUS_RESOLVED : TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION %>, "", "");"><liferay-ui:message key="yes" /></a>

					<a class="btn btn-no solution-confirm-btn"><liferay-ui:message key="no" /></a>

					<c:if test="<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_RESOLVED) || (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING) %>">
						<a class="btn btn-not-applicable solution-confirm-btn style-a" href="javascript:;" onClick="<portlet:namespace />updateTicketSolution(<%= (ticketSolution.getStatus() == TicketSolutionConstants.STATUS_TESTING) ? TicketSolutionConstants.STATUS_UNABLE_TO_TEST : TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION %>, "", "");"><liferay-ui:message key="not-applicable" /></a>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<input name="<portlet:namespace />ticketSolutionStatus" type="hidden" value="" />
	<input name="<portlet:namespace />statusReason" type="hidden" value="" />
	<input name="<portlet:namespace />statusMessage" type="hidden" value="" />
</c:if>

<div class="callout-a <%= (!screenShareMode && liferayIncOrg && (accountEntry.getTier() != AccountEntryConstants.TIER_REGULAR)) ? "tier-" + AccountEntryConstants.getTierLabel(accountEntry.getTier()) : StringPool.BLANK %>">
	<div class="callout-content cleared">
		<div class="fr txt-b txt-up">
			<a href="javascript:<portlet:namespace />toggleForm('<portlet:namespace />ticketDisplayId', '<portlet:namespace />ticketPermalink'); document.getElementById('<portlet:namespace />ticketPermalink').focus(); document.getElementById('<portlet:namespace />ticketPermalink').select();"><liferay-ui:message key="click-for-permalink" /></a>

			<c:if test="<%= !screenShareMode && liferayIncOrg %>">
				<br />

				<div class="tier-text-h2" id="<portlet:namespace />tierDisplay">
					<%= LanguageUtil.get(pageContext, AccountEntryConstants.getTierLabel(accountEntry.getTier())) %> <liferay-ui:message key="project" />
				</div>
			</c:if>
		</div>

		<div class="fl txt-b txt-up">
			<liferay-ui:message key="ticket-number" />

			<div class="txt-b txt-h1-12 txt-h1-9 txt-h2-6 txt-h3-4">

				<%
				String ticketEntryURL = SupportUtil.getFriendlyTicketEntryURL(request, ticketEntry.getTicketEntryId());
				%>

				<div id="<portlet:namespace />ticketDisplayId">
					<a href="<%= ticketEntryURL %>"><%= ticketEntry.getDisplayId() %></a>
				</div>

				<input id="<portlet:namespace />ticketPermalink" onBlur="<portlet:namespace />toggleForm('<portlet:namespace />ticketPermalink', '<portlet:namespace />ticketDisplayId');" readonly="readonly" style="display: none; vertical-align: middle; width: 750px;" type="text" value="<%= ticketEntryURL %>" />
			</div>
		</div>
	</div>
</div>

<div class="aui-helper-clearfix aui-w100">
	<div class="aui-w50 content-column">
		<div class="content-column-content left-column">

			<%
			String statusOnClick = StringPool.BLANK;

			if (!edit && !closed && hasUpdateAdvanced) {
				statusOnClick = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "statusDisplay', '" + renderResponse.getNamespace() + "status');";
			}
			%>

			<div class="callout-a" onClick="<%= statusOnClick %>">
				<div class="aui-helper-clearfix callout-content ticket-status">
					<div class="aui-w15 content-column">
						<div class="content-column-content left-column txt-b txt-up">
							<div>
								<liferay-ui:message key="status" />
							</div>

							<c:if test="<%= (ticketSolution != null) && (ticketSolution.getType() == TicketSolutionConstants.TYPE_TEMP) %>">
								<div class="temp-flag">
									<liferay-ui:message key="temp" />
								</div>
							</c:if>
						</div>
					</div>

					<div class="aui-w85 content-column ticket-value">
						<div class="content-column-content right-column txt-b txt-h1-12 txt-h1-9 txt-h3-4 txt-h3-6 txt-up">
							<c:if test="<%= !edit || !hasUpdateAdvanced %>">
								<div id="<portlet:namespace />statusDisplay">
									<%= LanguageUtil.get(pageContext, ticketEntry.getStatusLabel()) %>
								</div>
							</c:if>

							<c:choose>
								<c:when test="<%= hasUpdateAdvanced %>">
									<select id="<portlet:namespace />status" name="<portlet:namespace />status" onChange="<portlet:namespace />selectStatus(this.value)" style="<%= edit ? "" : "display: none;" %>">

										<%
										for (int statusId : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
											if ((statusId == TicketEntryConstants.STATUS_INACTIVE) || (statusId == TicketEntryConstants.STATUS_PENDING_WORKER) || ((statusId == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && !OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_SOLUTION)) || ((statusId == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) || (statusId == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
												if (statusId != status) {
													continue;
												}
											}
										%>

											<option <%= (statusId == status) ? "selected" : "" %> value="<%= statusId %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getStatusLabel(statusId)) %></option>

										<%
										}
										%>

									</select>
								</c:when>
								<c:otherwise>
									<input name="<portlet:namespace />status" type="hidden" value="<%= ticketEntry.getStatus() %>" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="aui-w50 content-column">
		<div class="content-column-content right-column">
			<div class="callout-a">
				<div class="aui-helper-clearfix callout-content">
					<div class="aui-w30 content-column">
						<div class="content-column-content left-column txt-b txt-up">
							<liferay-ui:message key="severity" />
						</div>
					</div>

					<div class="aui-w70 content-column ticket-value">
						<div class="content-column-content right-column txt-b txt-h1-12 txt-h1-9 txt-h3-4 txt-h3-6 txt-up">
							<c:choose>
								<c:when test="<%= edit && hasUpdateAdvanced %>">

									<%
									String severityOnChange = StringPool.BLANK;

									if (liferayIncOrg) {
										severityOnChange = renderResponse.getNamespace() + "selectSeverity(" + severity + ", this.value);";
									}
									%>

									<select id="<portlet:namespace />severity" name="<portlet:namespace />severity" onChange="<%= severityOnChange %>">
										<option value=""></option>

										<%
										for (int i = 1; i <= 3; i++) {
										%>

											<option <%= (i == severity) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getSeverityLabel(i)) %></option>

										<%
										}
										%>

									</select>
								</c:when>
								<c:otherwise>
									<span class="btn-a v<%= ticketEntry.getSeverity() %>"><%= LanguageUtil.get(pageContext, ticketEntry.getSeverityLabel()) %></span>

									<input name="<portlet:namespace />severity" type="hidden" value="<%= ticketEntry.getSeverity() %>" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="cleared"></div>

	<div class="aui-w50 content-column">
		<div class="content-column-content left-column">
			<div class="callout-a" id="<portlet:namespace />resolutionDisplay" style="<%= (screenShareMode || edit || closed || !ticketWorker) ? StringPool.BLANK : "display: none;" %>">
				<div class="aui-helper-clearfix callout-content">
					<div class="aui-w20 content-column">
						<div class="content-column-content left-column txt-b txt-up">
							<liferay-ui:message key="resolution" />
						</div>
					</div>

					<div class="aui-w80 content-column ticket-value">
						<div class="content-column-content right-column txt-b txt-h1-12 txt-h1-9 txt-h3-4 txt-h3-6 txt-up">
							<c:if test="<%= !edit || !hasUpdateAdvanced %>">
								<div id="<portlet:namespace />resolutionLabel">
									<%= LanguageUtil.get(pageContext, ticketEntry.getResolutionLabel()) %>
								</div>
							</c:if>

							<c:choose>
								<c:when test="<%= hasUpdateAdvanced %>">
									<select id="<portlet:namespace />resolution" name="<portlet:namespace />resolution" onChange="<portlet:namespace />quickSave();" style="<%= edit ? "" : "display: none;" %>">
										<option value=""></option>

										<%
										for (ListType resolutionType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION)) {
										%>

											<option <%= (resolutionType.getListTypeId() == resolution) ? "selected" : "" %> value="<%= resolutionType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, resolutionType.getName()) %></option>

										<%
										}
										%>

									</select>
								</c:when>
								<c:otherwise>
									<input name="<portlet:namespace />resolution" type="hidden" value="<%= ticketEntry.getResolution() %>" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>

			<%
			String pendingOnClick = StringPool.BLANK;

			if (!edit && !closed && hasUpdateAdvanced && ticketWorker) {
				pendingOnClick = renderResponse.getNamespace() + "togglePending(true);";
			}

			int[] pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING, TicketFlagConstants.FLAG_TRUE);
			%>

			<div class="callout-a" id="<portlet:namespace />pendingDisplay" onClick="<%= pendingOnClick %>" style="<%= (screenShareMode || edit || closed || !ticketWorker) ? "display: none;" : StringPool.BLANK %>">
				<div class="aui-helper-clearfix callout-content">
					<div class="aui-w30 content-column">
						<div class="content-column-content left-column txt-b txt-up">
							<liferay-ui:message key="pending" />
						</div>
					</div>

					<div class="aui-w70 content-column pending ticket-value" id="<portlet:namespace />pendingLabel">
						<div class="content-column-content pending-content right-column txt-b txt-h3-12 txt-h3-4 txt-h3-6 txt-h3-9 txt-up">

							<%
							for (int i = 0; i < pendingTypes.length; i++) {
								int pendingType = pendingTypes[i];
							%>

								<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" /><%= ((i + 1) < pendingTypes.length) ? StringPool.COMMA : StringPool.BLANK %>

							<%
							}
							%>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="aui-w50 content-column">
		<div class="content-column-content right-column">
			<div class="callout-a">
				<div class="aui-helper-clearfix callout-content">
					<div class="aui-w40 content-column">
						<div class="content-column-content left-column txt-b txt-up">
							<liferay-ui:message key="escalation-level" />
						</div>
					</div>

					<div class="aui-w60 content-column ticket-value">
						<div class="content-column-content right-column txt-b txt-h1-12 txt-h1-9 txt-h3-4 txt-h3-6 txt-up">
							<c:choose>
								<c:when test="<%= edit && hasUpdateAdvanced %>">
									<select name="<portlet:namespace />escalationLevel">

										<%
										for (ListType escalationLevelType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL)) {
										%>

											<option <%= (escalationLevelType.getListTypeId() == escalationLevel) ? "selected" : "" %> value="<%= escalationLevelType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, escalationLevelType.getName()) %></option>

										<%
										}
										%>

									</select>
								</c:when>
								<c:otherwise>
									<span class="btn-a v<%= ticketEntry.getEscalationLevelLabel() %>"><%= ticketEntry.getEscalationLevelLabel() %></span>

									<input name="<portlet:namespace />escalationLevel" type="hidden" value="<%= ticketEntry.getEscalationLevel() %>" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<c:if test="<%= !screenShareMode && !closed && ticketWorker %>">
	<div class="callout-a" id="<portlet:namespace />pendingTypes" style="<%= edit ? StringPool.BLANK : "display: none;" %>">
		<div class="callout-content cleared">
			<span class="txt-b txt-up">
				<liferay-ui:message key="need-response-from" />:
			</span>
			<span>

				<%
				for (int pendingType : TicketFlagConstants.TYPES_PENDING) {
				%>

					<input <%= ArrayUtil.contains(pendingTypes, pendingType) ? "checked=\"checked\"" : StringPool.BLANK %> name="<portlet:namespace />pendingTypes" type="checkbox" value="<%= pendingType %>" />

					<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" />

				<%
				}
				%>

				<c:if test="<%= !edit %>">
					<input class="aui-button-input" onClick="<portlet:namespace />updatePendingTypes();" type="button" value="<liferay-ui:message key="save" />" />

					<input class="aui-button-input" onClick="<portlet:namespace />togglePending(false);" type="button" value="<liferay-ui:message key="cancel" />" />
				</c:if>
			</span>
		</div>
	</div>
</c:if>

<c:if test="<%= edit && hasUpdateAdvanced && liferayIncOrg %>">
	<div id="<portlet:namespace />severityDetails" style="display: none;">
		<h2 class="section-heading">
			<liferay-ui:message key="severity-details" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<span class="txt-b"><liferay-ui:message key="what-is-the-reason-for-changing-the-ticket-severity" /></span>

				<input id="<portlet:namespace />severityReason" name="<portlet:namespace />severityReason" type="hidden" />

				<div id="<portlet:namespace />severityReasonLabel"></div>

				<br />

				<span class="txt-b"><liferay-ui:message key="please-explain-optional" /></span>

				<div>
					<textarea id="<portlet:namespace />severityReasonComments" name="<portlet:namespace />severityReasonComments" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 250px; width: 750px;" wrap="soft"></textarea>
				</div>
			</div>
		</div>
	</div>
</c:if>

<c:if test="<%= (liferayIncOrg && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.VIEW)) || ((liferayTicketFeedback == null) && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE)) %>">
	<liferay-util:include page="/support/liferay_ticket_feedback_summary.jsp" servletContext="<%= application %>" />
</c:if>

<c:if test="<%= (partnerTicketFeedback != null) && closed && !screenShareMode && liferayIncOrg %>">
	<liferay-util:include page="/support/partner_ticket_feedback_summary.jsp" servletContext="<%= application %>" />
</c:if>

<h2 class="section-heading">
	<liferay-ui:message key="ticket-info" />
</h2>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-w33 content-column">
			<div class="content-column-content left-column">
				<span class="txt-b txt-up"><liferay-ui:message key="reported-by" />:</span>

				<%
				User reportedByUser = null;
				String reportedByUserFullName = StringPool.BLANK;

				try {
					reportedByUser = UserLocalServiceUtil.getUser(ticketEntry.getUserId());
					reportedByUserFullName = reportedByUser.getFullName();
				}
				catch (NoSuchUserException nsue) {
					reportedByUserFullName = ticketEntry.getUserName();
				}
				%>

				<span id="<portlet:namespace />reportedByUserName">
					<%= HtmlUtil.escape(reportedByUserFullName) %>

					<c:if test="<%= (reportedByUser != null) %>">
						<c:choose>
							<c:when test="<%= AccountWorkerLocalServiceUtil.hasAccountWorkerRole(reportedByUser.getUserId(), AccountWorkerConstants.ROLE_MANAGED_SERVICES) %>">
								<img src="<%= PortalUtil.getPathContext(request) %>/images/liferay_managed_services_badge.png" />
							</c:when>
							<c:when test="<%= OrganizationLocalServiceUtil.hasUserOrganization(reportedByUser.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID) %>">
								<img src="<%= PortalUtil.getPathContext(request) %>/images/liferay_employee_badge.png" />
							</c:when>
							<c:when test="<%= PartnerWorkerLocalServiceUtil.hasPartnerWorker(reportedByUser.getUserId(), accountEntry.getPartnerEntryId()) %>">
								<img src="<%= PortalUtil.getPathContext(request) %>/images/partner_worker_badge.png" />
							</c:when>
						</c:choose>
					</c:if>
				</span>

				<input name="<portlet:namespace />reportedByUserId" type="hidden" value="<%= ticketEntry.getUserId() %>" />

				<c:if test="<%= edit && hasUpdateAdvanced %>">
					&nbsp;<input class="aui-button-input" type="button" value="<liferay-ui:message key="choose" />" onClick="var reportedByWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/select_user.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /><portlet:param name="callback" value="selectReportedBy" /></portlet:renderURL>', 'reported_by', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); reportedByWindow.focus();" />
				</c:if>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content middle-column">
				<span class="txt-b txt-up"><liferay-ui:message key="primary-assignee" />:</span>

				<%= SupportUtil.getPrimaryAssigneeUserName(ticketEntry.getTicketEntryId()) %>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content right-column">
				<span class="txt-b txt-up"><liferay-ui:message key="other-assignees" />:</span>

				<%= SupportUtil.getOtherAssigneesUserNames(ticketEntry.getTicketEntryId()) %>
			</div>
		</div>
	</div>

	<div class="aui-helper-clearfix callout-content">

		<%
		int columnCount = 0;

		List<User> advocacySpecialists = SupportUtil.getUsers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST);

		StringBuilder sb = new StringBuilder();

		for (User advocacySpecialist : advocacySpecialists) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}

			sb.append("<a href=\"mailto:");
			sb.append(HtmlUtil.escape(advocacySpecialist.getEmailAddress()));
			sb.append("\">");
			sb.append(HtmlUtil.escape(advocacySpecialist.getFullName()));
			sb.append("</a>");
		}

		if (sb.length() > 0) {
			columnCount++;
		}
		%>

		<c:if test="<%= sb.length() > 0 %>">
			<div class="aui-w33 content-column">
				<div class="content-column-content left-column">
					<span class="txt-b txt-up"><liferay-ui:message key="advocacy-specialists" />:</span>

					<%= sb.toString() %>
				</div>
			</div>
		</c:if>

		<%
		String salesReps = SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_SALES);

		if (Validator.isNotNull(salesReps)) {
			columnCount++;
		}
		%>

		<c:if test="<%= Validator.isNotNull(salesReps) %>">
			<div class="aui-w33 content-column">
				<div class="content-column-content <%= (columnCount == 1) ? "left-column" : "middle-column" %>">
					<span class="txt-b txt-up"><liferay-ui:message key="sales-reps" />:</span>

					<%= salesReps %>
				</div>
			</div>
		</c:if>

		<%
		String managedServices = SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_MANAGED_SERVICES);

		if (Validator.isNotNull(managedServices)) {
			columnCount++;
		}
		%>

		<c:if test="<%= Validator.isNotNull(managedServices) %>">
			<div class="aui-w33 content-column">
				<div class="content-column-content <%= (columnCount == 1) ? "left-column" : "" %><%= (columnCount == 2) ? "middle-column" : "" %><%= (columnCount == 3) ? "right-column" : "" %>">
					<span class="txt-b txt-up"><liferay-ui:message key="managed-services" />:</span>

					<%= managedServices %>
				</div>
			</div>
		</c:if>

		<c:choose>
			<c:when test="<%= hasUpdateAdmin %>">
				<div class="aui-w33 content-column">
					<div class="content-column-content <%= ((columnCount == 1) || (columnCount == 4)) ? "middle-column" : "right-column" %>">
						<span class="txt-b txt-up"><liferay-ui:message key="weight" />:</span>

						<c:choose>
							<c:when test="<%= edit %>">
								<select name="<portlet:namespace />weight">

									<%
									for (int i = 1; i <= 3; i++) {
									%>

										<option <%= (i == weight) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getWeightLabel(i)) %></option>

									<%
									}
									%>

								</select>
							</c:when>
							<c:otherwise>
								<%= LanguageUtil.get(pageContext, ticketEntry.getWeightLabel()) %>

								<input name="<portlet:namespace />weight" type="hidden" value="<%= ticketEntry.getWeight() %>" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<input name="<portlet:namespace />weight" type="hidden" value="<%= ticketEntry.getWeight() %>" />
			</c:otherwise>
		</c:choose>
	</div>

	<div class="hr pad"></div>

	<div class="aui-helper-clearfix callout-content">
		<div class="aui-w33 content-column">
			<div class="content-column-content left-column">
				<span class="txt-b txt-up"><liferay-ui:message key="created" />:</span>

				<%= longDateFormatDateTime.format(ticketEntry.getCreateDate()) %>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content middle-column">
				<span class="txt-b txt-up"><liferay-ui:message key="updated" />:</span>

				<%= longDateFormatDateTime.format(ticketEntry.getModifiedDate()) %>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content right-column">
				<c:if test="<%= !screenShareMode && ticketWorker %>">
					<span class="txt-b txt-up"><liferay-ui:message key="due" />:</span>

					<c:choose>
						<c:when test="<%= edit && hasUpdateAdmin %>">
							<div class="cleared">
								<liferay-ui:input-field bean="<%= ticketEntry %>" defaultValue="<%= dueDate %>" field="dueDate" formName="fm1" model="<%= TicketEntry.class %>" />
							</div>
						</c:when>
						<c:otherwise>
							<%= (ticketEntry.getComponent() == TicketEntryConstants.COMPONENT_LIFERAY_DEVELOPER_STUDIO) ? TicketEntryConstants.NOT_AVAILABLE : longDateFormatDateTime.format(ticketEntry.getDueDate()) %>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= !edit && hasUpdateAdmin && ticketEntry.isIgnoreDueDate() %>">
						<span class="ignore-due-date">(<liferay-ui:message key="ignored" />)</span>
					</c:if>
				</c:if>
			</div>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdmin %>">
					<div class="content-column-content right-column">
						<span class="txt-b txt-up"><liferay-ui:message key="ignore-due-date" />:</span>

						<input <%= ticketEntry.getIgnoreDueDate() ? "checked" : StringPool.BLANK %> name="<portlet:namespace />ignoreDueDate" type="checkbox" />
					</div>
				</c:when>
				<c:otherwise>
					<input name="<portlet:namespace />ignoreDueDate" type="hidden" value="<%= ticketEntry.getIgnoreDueDate() %>" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="aui-helper-clearfix callout-content">
		<div class="aui-w33 content-column">
			<div class="content-column-content left-column">
				<span class="txt-b txt-up"><liferay-ui:message key="project" />:</span>

				<span id="<portlet:namespace />accountEntryName">
					<c:if test="<%= !screenShareMode && ticketWorker %>">
						<a href="<liferay-portlet:renderURL><portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" /></liferay-portlet:renderURL>" target="_blank">
					</c:if>

					<%= HtmlUtil.escape(accountEntry.getName()) %>

					<c:if test="<%= !screenShareMode && ticketWorker %>">
						</a>
					</c:if>
				</span>

				<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content middle-column">
				<span class="txt-b txt-up"><liferay-ui:message key="product" />:</span>

				<%= productEntry.getName() %>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content right-column">
				<span class="txt-b txt-up"><liferay-ui:message key="support" />:</span>

				<span id="<portlet:namespace />supportResponseName">
					<%= supportResponse.getName() %>

					<c:if test="<%= (accountEntry.getStatus() == WorkflowConstants.STATUS_CLOSED) || (accountEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) %>">
						<span class="expired-text">(<liferay-ui:message key="expired" />)</span>
					</c:if>
				</span>

				<input name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= offeringEntry.getOfferingEntryId() %>" />

				<c:if test="<%= edit && liferayIncOrg %>">
					<input class="aui-button-input" onClick="var offeringEntryWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/select_offering_entry.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'offering_entry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=900'); void(''); offeringEntryWindow.focus();" type="button" value="<liferay-ui:message key="choose" />" />
				</c:if>
			</div>
		</div>
	</div>

	<c:if test="<%= ticketWorker %>">
		<div class="aui-helper-clearfix callout-content">
			<div class="aui-w33 content-column">
				<div class="content-column-content left-column">
					<span class="txt-b txt-up"><liferay-ui:message key="support-region" />:</span>

					<c:choose>
						<c:when test="<%= edit && hasUpdateAdvanced %>">
							<select name="<portlet:namespace />supportRegionId">

								<%
								List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

								for (SupportRegion supportRegion : supportRegions) {
								%>

									<option <%= (supportRegion.getSupportRegionId() == supportRegionId) ? "selected" : StringPool.BLANK %> value="<%= supportRegion.getSupportRegionId() %>"><%= HtmlUtil.escape(supportRegion.getName()) %></option>

								<%
								}
								%>

							</select>
						</c:when>
						<c:otherwise>

							<%
							String supportRegionName = StringPool.BLANK;

							try {
								SupportRegion supportRegion = SupportRegionLocalServiceUtil.getSupportRegion(supportRegionId);

								supportRegionName = supportRegion.getName();
							}
							catch (NoSuchSupportRegionException nssre) {
							}
							%>

							<%= HtmlUtil.escape(supportRegionName) %>

							<input name="<portlet:namespace />supportRegionId" type="hidden" value="<%= supportRegionId %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content middle-column">
					<span class="txt-b txt-up"><liferay-ui:message key="support-language" />:</span>

					<c:choose>
						<c:when test="<%= edit && hasUpdateAdvanced && liferayIncOrg %>">
							<select name="<portlet:namespace />languageId">

								<%
								for (String curLanguageId : AccountEntryConstants.LANGUAGES) {
								%>

									<option <%= languageId.equals(curLanguageId) ? "selected" : StringPool.BLANK %> value="<%= curLanguageId %>"><%= LanguageUtil.get(pageContext, AccountEntryConstants.getLanguageLabel(curLanguageId)) %></option>

								<%
								}
								%>

							</select>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(pageContext, AccountEntryConstants.getLanguageLabel(languageId)) %>

							<input name="<portlet:namespace />languageId" type="hidden" value="<%= HtmlUtil.escapeAttribute(languageId) %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</c:if>
</div>

<c:if test="<%= !screenShareMode && liferayIncOrg && Validator.isNotNull(accountEntry.getInstructions()) && !TicketFlagLocalServiceUtil.hasTicketFlag(user.getUserId(), accountEntry.getAccountEntryId(), ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_READ_INSTRUCTIONS, TicketFlagConstants.FLAG_TRUE) %>">
	<div id="<portlet:namespace />specialInstructions">
		<h2 class="fl section-heading">
			<liferay-ui:message key="special-instructions" />
		</h2>

		<a class="mark-as-read" href="javascript:;" onClick="<portlet:namespace />markAsRead(<%= TicketFlagConstants.TYPE_READ_INSTRUCTIONS %>, "<portlet:namespace />specialInstructions");"><liferay-ui:message key="mark-as-read" /></a>

		<div class="callout-a cleared instructions">
			<div class="callout-content">
				<pre><%= SupportUtil.getHTML(accountEntry.getInstructions()) %></pre>
			</div>
		</div>
	</div>
</c:if>

<h2 class="section-heading">
	<liferay-ui:message key="description" />
</h2>

<div class="callout-a">
	<div class="callout-content">
		<div>
			<span class="txt-b txt-up"><%= edit ? "*" : StringPool.BLANK %><liferay-ui:message key="component" />:</span>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<select name="<portlet:namespace />component" onChange="<portlet:namespace />updateComponent(this.value)">
						<option value=""></option>

						<%
						for (ListType componentType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_COMPONENT)) {
							if ((componentType.getListTypeId() != component) && ArrayUtil.contains(TicketEntryConstants.COMPONENTS_DEPRECATED, componentType.getListTypeId())) {
								continue;
							}
						%>

							<option <%= (componentType.getListTypeId() == component) ? "selected" : "" %> value="<%= componentType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, componentType.getName()) %></option>

						<%
						}
						%>

					</select>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.get(pageContext, ticketEntry.getComponentLabel()) %>

					<input name="<portlet:namespace />component" type="hidden" value="<%= ticketEntry.getComponent() %>" />
				</c:otherwise>
			</c:choose>

			<%
			PortletPreferences supportPortletPreferences = SupportUtil.getPortletPreferences();
			%>

			<c:if test='<%= edit || GetterUtil.getBoolean(supportPortletPreferences.getValue("showInTicketView_" + component, StringPool.BLANK), false) %>'>

				<%
				String componentMessage = SupportUtil.getPreferenceValue(supportPortletPreferences, locale, "componentMessage_" + component);

				String componentMessageLink = supportPortletPreferences.getValue("componentMessageLink_" + component, StringPool.BLANK);
				%>

				<div class="portlet-msg-info component-message <%= Validator.isNotNull(componentMessage) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />componentMessageDisplay">
					<c:choose>
						<c:when test="<%= Validator.isNotNull(componentMessageLink) %>">
							<a href="<%= HtmlUtil.escapeAttribute(componentMessageLink) %>" target="_blank"><%= HtmlUtil.escape(componentMessage) %></a>
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(componentMessage) %>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
		</div>

		<c:choose>
			<c:when test="<%= hasUpdateAdvanced && liferayIncOrg %>">
				<div>
					<span class="txt-b txt-up"><liferay-ui:message key="subcomponent" />:</span>

					<c:choose>
						<c:when test="<%= edit %>">

							<%
							int[] subcomponents = TicketEntryConstants.getSubcomponents(component);
							%>

							<select name="<portlet:namespace />subcomponent" onChange="<portlet:namespace />selectSubcomponent(this.value);">
								<c:if test="<%= ticketEntry.getSubcomponent() <= 0 %>">
									<option value="0"><liferay-ui:message key="none" /></option>
								</c:if>

								<c:if test="<%= !ArrayUtil.contains(subcomponents, TicketEntryConstants.SUBCOMPONENT_OTHER) %>">
									<option <%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "selected" : StringPool.BLANK %> value="<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>"><liferay-ui:message key="other" /></option>
								</c:if>

								<%
								for (int curSubcomponent : subcomponents) {
								%>

									<option <%= (curSubcomponent == subcomponent) ? "selected" : StringPool.BLANK %> value="<%= curSubcomponent %>"><%= LanguageUtil.get(request, TicketEntryConstants.getSubcomponentLabel(curSubcomponent)) %></option>

								<%
								}
								%>

							</select>

							<input class="<%= (subcomponent == TicketEntryConstants.SUBCOMPONENT_OTHER) ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />subcomponentCustom" name="<portlet:namespace />subcomponentCustom" type="text" value="<%= HtmlUtil.escapeAttribute(subcomponentCustom) %>" />
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.get(pageContext, ticketEntry.getSubcomponentLabel()) %>

							<c:if test="<%= Validator.isNotNull(subcomponentCustom) %>">
								- <%= HtmlUtil.escape(subcomponentCustom) %>
							</c:if>

							<input name="<portlet:namespace />subcomponent" type="hidden" value="<%= ticketEntry.getSubcomponent() %>" />

							<input name="<portlet:namespace />subcomponentCustom" type="hidden" value="<%= HtmlUtil.escape(ticketEntry.getSubcomponentCustom()) %>" />
						</c:otherwise>
					</c:choose>
				</div>
			</c:when>
			<c:otherwise>
				<input name="<portlet:namespace />subcomponent" type="hidden" value="<%= ticketEntry.getSubcomponent() %>" />

				<input name="<portlet:namespace />subcomponentCustom" type="hidden" value="<%= HtmlUtil.escape(ticketEntry.getSubcomponentCustom()) %>" />
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="<%= edit && hasUpdateAdvanced %>">
				<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
					<liferay-util:param name="content" value="<%= description %>" />
					<liferay-util:param name="editorId" value="description" />
					<liferay-util:param name="height" value='<%= (component != TicketEntryConstants.COMPONENT_LICENSE) ? "300" : "" %>' />
					<liferay-util:param name="name" value="description" />
					<liferay-util:param name="showCounter" value="<%= String.valueOf(edit) %>" />
				</liferay-util:include>
			</c:when>
			<c:otherwise>
				<pre><%= SupportUtil.getHTML(ticketEntry.getDescription()) %></pre>

				<div style="display: none;">
					<liferay-ui:input-field bean="<%= ticketEntry %>" field="description" model="<%= TicketEntry.class %>" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<c:if test="<%= !screenShareMode && liferayIncOrg %>">
	<div id="<portlet:namespace />reproductionStepsLabel" style="<%= Validator.isNotNull(reproductionSteps) ? "" : "display: none;" %>">
		<h2 class="section-heading">
			<liferay-ui:message key="reproduction-steps" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<c:choose>
					<c:when test="<%= edit && hasUpdateAdvanced %>">
						<textarea id="<portlet:namespace />reproductionSteps" maxlength="<%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />reproductionSteps" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 300px; width: 100%;" wrap="soft"><%= HtmlUtil.escape(reproductionSteps) %></textarea>
					</c:when>
					<c:otherwise>
						<pre><%= SupportUtil.getHTML(ticketEntry.getReproductionSteps()) %></pre>

						<div style="display: none;">
							<liferay-ui:input-field bean="<%= ticketEntry %>" field="reproductionSteps" model="<%= TicketEntry.class %>" />
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</c:if>

<c:if test="<%= accountAttachment != null %>">
	<h2 class="section-heading">
		<liferay-ui:message key="additional-attachments" />
	</h2>

	<div class="callout-a">
		<div class="callout-content">

			<%
			LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			accountAttachmentURL.setCopyCurrentRenderParameters(false);
			accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
			accountAttachmentURL.setResourceID("accountAttachment");
			%>

			<table class="additional-attachments">
			<tr>
				<td>
					<a href="<%= accountAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(accountAttachment.getFileName()) %></a>
				</td>
				<td>
					<liferay-ui:message key="<%= accountAttachment.getTypeLabel() %>" />
				</td>
			</tr>
			</table>
		</div>
	</div>
</c:if>

<div class="component-details">
	<c:choose>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_CLUSTERING %>">
			<liferay-util:include page="/support/edit_ticket_entry/details_clustering.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_LICENSE %>">
			<liferay-util:include page="/support/edit_ticket_entry/details_license.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_UI %>">
			<liferay-util:include page="/support/edit_ticket_entry/details_ui.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
			<liferay-util:include page="/support/edit_ticket_entry/details_upgrade.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/support/edit_ticket_entry/details_generic.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>

<%
String statusMessage = HtmlUtil.escapeJS(SupportUtil.getPreferenceValue(locale, "statusMessage_" + status));
String tierMessage = HtmlUtil.escapeJS(SupportUtil.getPreferenceValue(locale, "tierMessage_" + accountEntry.getTier()));
%>

<aui:script>
	<c:if test="<%= !edit && Validator.isNotNull(statusMessage) %>">
		AUI().ready(
			'aui-tooltip',
			function(A) {
				new A.Tooltip(
					{
						bodyContent: '<%= statusMessage %>',
						trigger: '#<portlet:namespace />statusDisplay'
					}
				).render();
			}
		);
	</c:if>

	<c:if test="<%= !edit && Validator.isNotNull(tierMessage) %>">
		AUI().ready(
			'aui-tooltip',
			function(A) {
				new A.Tooltip(
					{
						bodyContent: '<%= tierMessage %>',
						trigger: '#<portlet:namespace />tierDisplay'
					}
				).render();
			}
		);
	</c:if>

	<c:if test="<%= ticketSolution != null %>">
		AUI().use(
			'aui-dialog',
			'aui-io',
			function(A) {
				var solutionConfirmTab = A.one('.solution-confirm-tab');

				if (solutionConfirmTab) {
					var solutionConfirmDialog = A.one('.aui-helper-hidden .solution-confirm-container');

					var dialog = new A.Dialog(
						{
							bodyContent: solutionConfirmDialog,
							centered: true,
							cssClass: 'solutions-confirm-dialog',
							draggable: true,
							modal: true,
							title: '<liferay-ui:message key="solution-proposed" />',
							visible: <%= !TicketFlagLocalServiceUtil.hasTicketFlag(user.getUserId(), accountEntry.getAccountEntryId(), ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_READ_SOLUTION, TicketFlagConstants.FLAG_TRUE) %>,
							width: '50%'
						}
					).render();

					<c:if test="<%= !TicketFlagLocalServiceUtil.hasTicketFlag(user.getUserId(), accountEntry.getAccountEntryId(), ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_READ_SOLUTION, TicketFlagConstants.FLAG_TRUE) %>">
						dialog.on(
							'close',
							function() {
								<portlet:namespace />markAsRead(<%= TicketFlagConstants.TYPE_READ_SOLUTION %>, '');
							}
						);
					</c:if>

					solutionConfirmTab.on(
						'click',
						function() {
							dialog.show();
						}
					);

					var updateDialog = function(title, url) {
						var container = A.one('.solutions-confirm-dialog .solution-confirm-container');

						dialog.set('align', {points: [A.WidgetPositionAlign.TC, A.WidgetPositionAlign.TC]});
						dialog.set('height', '90%');
						dialog.set('title', title);
						dialog.set('bodyContent', container.load(url));

						dialog.syncUI();
					};

					var dialogNode = A.one('.solution-confirm-answer');

					dialogNode.delegate(
						'click',
						function() {
							var rejectTicketSolutionURL = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/reject_ticket_solution.jsp" /><portlet:param name="ticketSolutionId" value="<%= String.valueOf(ticketSolution.getTicketSolutionId()) %>" /></portlet:renderURL>';

							updateDialog('<liferay-ui:message key="solution-rejected" />', rejectTicketSolutionURL);
						},
						'.btn-no'
					);
				}
			}
		);

		function <portlet:namespace />updateTicketSolution(ticketSolutionStatus, statusReason, statusMessage) {
			var updateTicketSolutionURL = '<portlet:actionURL name="updateTicketSolution"><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /><portlet:param name="ticketSolutionId" value="<%= String.valueOf(ticketSolution.getTicketSolutionId()) %>" /></portlet:actionURL>';

			document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';

			document.<portlet:namespace />fm1.<portlet:namespace />ticketSolutionStatus.value = ticketSolutionStatus;
			document.<portlet:namespace />fm1.<portlet:namespace />statusReason.value = statusReason;
			document.<portlet:namespace />fm1.<portlet:namespace />statusMessage.value = statusMessage;

			submitForm(document.<portlet:namespace />fm1, updateTicketSolutionURL);
		}
	</c:if>

	function <portlet:namespace />quickSave() {
		if (document.<portlet:namespace />fm1.<portlet:namespace />edit.value == 'false') {
			document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
			submitForm(document.<portlet:namespace />fm1);
		}
	}

	function <portlet:namespace />resetSeverityDetails() {
		document.<portlet:namespace />fm1.<portlet:namespace />severity.value = '<%= severity %>';

		document.getElementById("<portlet:namespace />severityDetails").style.display = "none";
	}

	function <portlet:namespace />resetStatus() {
		<portlet:namespace />toggleForm('<portlet:namespace />status', '<portlet:namespace />statusDisplay');

		document.<portlet:namespace />fm1.<portlet:namespace />status.value = '<%= status %>';
	}

	function <portlet:namespace />selectOfferingEntry(accountEntryId, offeringEntryId, accountEntryName, supportResponseName) {
		document.<portlet:namespace />fm1.<portlet:namespace />accountEntryId.value = accountEntryId;
		document.<portlet:namespace />fm1.<portlet:namespace />offeringEntryId.value = offeringEntryId;

		var accountEntryNameEl = document.getElementById("<portlet:namespace />accountEntryName");

		accountEntryNameEl.innerHTML = accountEntryName;

		var supportResponseNameEl = document.getElementById("<portlet:namespace />supportResponseName");

		supportResponseNameEl.innerHTML = supportResponseName;
	}

	function <portlet:namespace />selectReportedBy(userId, userName) {
		document.<portlet:namespace />fm1.<portlet:namespace />reportedByUserId.value = userId;

		var userNameEl = document.getElementById("<portlet:namespace />reportedByUserName");

		userNameEl.innerHTML = userName;
	}

	function <portlet:namespace />selectSeverity(severity, newSeverity) {
		if (severity == newSeverity) {
			<portlet:namespace />resetSeverityDetails();

			return;
		}

		var renderURL = '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="jspPage" value="/support/edit_ticket_entry/severity_details.jsp" /></portlet:renderURL>';

		renderURL += '&severity=' + severity + '&newSeverity=' + newSeverity;

		var severityDetailsWindow = window.open(renderURL, 'severity_details', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800');

		severityDetailsWindow.focus();
	}

	function <portlet:namespace />selectStatus(status) {
		if (status == "<%= TicketEntryConstants.STATUS_CLOSED %>") {
			<portlet:namespace />toggleForm('<portlet:namespace />pendingDisplay', '<portlet:namespace />resolutionDisplay');
			<portlet:namespace />toggleForm('<portlet:namespace />resolutionLabel', '<portlet:namespace />resolution');
		}
		else if ((status == "<%= TicketEntryConstants.STATUS_REPRODUCED %>") && <%= liferayIncOrg && (status != TicketEntryConstants.STATUS_REPRODUCED) %>) {
			var reproductionStepsTicketWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="jspPage" value="/support/edit_ticket_entry/reproduction_steps.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'reproductionSteps', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800');

			reproductionStepsTicketWindow.focus();
		}
		else {
			<portlet:namespace />quickSave();
		}
	}

	function <portlet:namespace />selectSubcomponent(subcomponent) {
		var subcomponentCustom = AUI().one('#<portlet:namespace />subcomponentCustom');

		if (subcomponent == '<%= TicketEntryConstants.SUBCOMPONENT_OTHER %>') {
			subcomponentCustom.show();
		}
		else {
			subcomponentCustom.hide();

			subcomponentCustom.val('');
		}
	}

	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = "";
		document.getElementById(hideId).style.display = "none";
	}

	function <portlet:namespace />togglePending(edit) {
		if (edit) {
			document.getElementById('<portlet:namespace />pendingLabel').style.visibility = "hidden";
			document.getElementById('<portlet:namespace />pendingTypes').style.display = "";
		}
		else {
			var pendingTypes = document.<portlet:namespace />fm1.<portlet:namespace />pendingTypes;

			for (var i = 0; i < pendingTypes.length; i++) {
				if (pendingTypes[i].getAttribute("checked")) {
					pendingTypes[i].checked = true;
				}
				else {
					pendingTypes[i].checked = false;
				}
			}

			document.getElementById('<portlet:namespace />pendingLabel').style.visibility = "visible";
			document.getElementById('<portlet:namespace />pendingTypes').style.display = "none";
		}
	}

	function <portlet:namespace />updateComponent(component) {
		var updateURL = '<portlet:renderURL copyCurrentRenderParameters="<%= true %>"><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /><portlet:param name="component" value="" /></portlet:renderURL>';

		updateURL += "&<portlet:namespace />component=" + component;

		submitForm(document.<portlet:namespace />fm1, updateURL);
	}

	function <portlet:namespace />updatePendingTypes() {
		var updatePendingURL = '<portlet:actionURL name="updatePendingTypes"><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /></portlet:actionURL>';

		document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';

		submitForm(document.<portlet:namespace />fm1, updatePendingURL);
	}

	function <portlet:namespace />updateReproductionSteps(reproductionSteps) {
		document.<portlet:namespace />fm1.<portlet:namespace />reproductionSteps.value = reproductionSteps;
		document.getElementById("<portlet:namespace />reproductionStepsLabel").style.display = "";

		<portlet:namespace />quickSave();
	}

	function <portlet:namespace />updateSeverityDetails(severityReasonValue, severityReasonLabel, severityReasonComments) {
		document.getElementById("<portlet:namespace />severityReason").value = severityReasonValue;
		document.getElementById("<portlet:namespace />severityReasonLabel").innerHTML = severityReasonLabel;
		document.getElementById("<portlet:namespace />severityReasonComments").value = severityReasonComments;

		document.getElementById("<portlet:namespace />severityDetails").style.display = "";
	}

	function <portlet:namespace />updateSupportMessage(envLFR) {
		var supportMessageDisplay_5_2 = AUI().one('#<portlet:namespace />supportMessageDisplay_5_2');
		var supportMessageDisplay_6_0 = AUI().one('#<portlet:namespace />supportMessageDisplay_6_0');
		var supportMessageDisplay_6_1 = AUI().one('#<portlet:namespace />supportMessageDisplay_6_1');

		if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_4 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_5 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_6 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_7 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_8 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_5_2_9 %>)) {
			supportMessageDisplay_5_2.show();
		}
		else {
			supportMessageDisplay_5_2.hide();
		}

		if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_11 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_0_12 %>)) {
			supportMessageDisplay_6_0.show();
		}
		else {
			supportMessageDisplay_6_0.hide();
		}

		if ((envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_10 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_20 %>) || (envLFR == <%= ProductEntryConstants.PORTAL_VERSION_6_1_30 %>)) {
			supportMessageDisplay_6_1.show();
		}
		else {
			supportMessageDisplay_6_1.hide();
		}
	}

	function <portlet:namespace />updateTicketEntry(offeringEntryId) {
		var updateURL = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" /></portlet:renderURL>';

		if (offeringEntryId > 0) {
			updateURL += '&offeringEntryId=' + offeringEntryId;
		}

		submitForm(document.<portlet:namespace />fm1, updateURL);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />markAsRead',
		function(type, hideId) {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:actionURL name="updateTicketFlag" />',
				{
					data: {
						<portlet:namespace />accountEntryId: <%= accountEntry.getAccountEntryId() %>,
						<portlet:namespace />ticketEntryId: <%= ticketEntry.getTicketEntryId() %>,
						<portlet:namespace />type: type,
						<portlet:namespace />flag: <%= TicketFlagConstants.FLAG_TRUE %>
					},
					dataType: 'json',
					method: 'post',
					on: {
						start: function(event, id, obj) {
							if (hideId != '') {
								A.one('#' + hideId).hide();
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>