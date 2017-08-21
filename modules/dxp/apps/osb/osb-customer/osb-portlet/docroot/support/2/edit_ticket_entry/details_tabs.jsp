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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_ticket_entry.jsp-accountEntry");

boolean accountCustomer = (Boolean)request.getAttribute("edit_ticket_entry.jsp-accountCustomer");
boolean closed = (Boolean)request.getAttribute("edit_ticket_entry.jsp-closed");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
boolean hasUpdateBasic = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateBasic");
boolean partnerWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-partnerWorker");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
String description = BeanParamUtil.getString(ticketEntry, request, "description");
String reproductionSteps = BeanParamUtil.getString(ticketEntry, request, "reproductionSteps");

TicketSolution ticketSolution = TicketSolutionLocalServiceUtil.getActiveTicketSolution(ticketEntry.getTicketEntryId());

TicketFeedback liferayTicketFeedback = null;

if (closed && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE)) {
	liferayTicketFeedback = TicketFeedbackServiceUtil.fetchFirstOpenTicketFeedback(user.getUserId(), ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_LIFERAY);
}

TicketFeedback partnerTicketFeedback = null;

if (!screenShareMode && liferayIncOrg) {
	partnerTicketFeedback = TicketFeedbackServiceUtil.fetchFirstTicketFeedback(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_PARTNER);
}

boolean hasViewLiferayTicketFeedback = (liferayIncOrg && closed && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.VIEW)) || ((liferayTicketFeedback == null) && closed && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE));

boolean hasViewTicketSolution = (ticketSolution != null) && (ticketSolution.getStatus() != TicketSolutionConstants.STATUS_RESOLVED_IN_PRODUCTION) && hasUpdateBasic && (accountCustomer || ticketWorker || (user.getUserId() == ticketEntry.getUserId()));

boolean hasViewSupportInstructions = liferayIncOrg && ((Validator.isNotNull(accountEntry.getInstructions()) && !TicketFlagLocalServiceUtil.hasTicketFlag(user.getUserId(), accountEntry.getAccountEntryId(), ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_READ_INSTRUCTIONS, TicketFlagConstants.FLAG_TRUE)));

String defaultGeneralTab = "description";

if (hasViewLiferayTicketFeedback) {
	defaultGeneralTab = "feedback";
}
else if (hasViewTicketSolution) {
	defaultGeneralTab = "solution";
}
else if (hasViewSupportInstructions) {
	defaultGeneralTab = "supportInstructions";
}

String generalTab = ParamUtil.getString(request, "generalTab", defaultGeneralTab);
%>

<div class="details-tabs">
	<div class="details tab-view" id="<portlet:namespace />ticketDetails">
		<div class="tabs" id="<portlet:namespace />ticketTabsDetails">
			<div>
				<c:if test="<%= !screenShareMode && hasViewSupportInstructions %>">
					<span class="first" id="<portlet:namespace />supportInstructions" onClick="<portlet:namespace />reveal('supportInstructions');"><liferay-ui:message key="support-instructions" /></span>
				</c:if>

				<span class="<%= !hasViewSupportInstructions ? "first" : StringPool.BLANK %>" id="<portlet:namespace />description" onClick="<portlet:namespace />reveal('description');"><liferay-ui:message key="description" /></span>

				<c:if test="<%= (component == TicketEntryConstants.COMPONENT_CLUSTERING) || (component == TicketEntryConstants.COMPONENT_LICENSE) || (component == TicketEntryConstants.COMPONENT_UPGRADE) %>">
					<span id="<portlet:namespace />component" onClick="<portlet:namespace />reveal('component');"><liferay-ui:message key="<%= TicketEntryConstants.getComponentLabel(component) %>" /> <liferay-ui:message key="details" /></span>
				</c:if>

				<c:if test="<%= ticketWorker && !screenShareMode && Validator.isNotNull(reproductionSteps) %>">
					<span id="<portlet:namespace />stepsToReproduce" onClick="<portlet:namespace />reveal('stepsToReproduce');"><liferay-ui:message key="steps-to-reproduce" /></span>
				</c:if>

				<c:if test="<%= hasViewTicketSolution %>">
					<span class="highlight-red" id="<portlet:namespace />solution" onClick="<portlet:namespace />reveal('solution');">
						<label class="highlighted-flag">*</label> <liferay-ui:message key="solution" />
					</span>
				</c:if>

				<c:if test="<%= hasViewLiferayTicketFeedback || ((partnerTicketFeedback != null) && !screenShareMode && liferayIncOrg) %>">
					<span id="<portlet:namespace />feedback" onClick="<portlet:namespace />reveal('feedback');"><liferay-ui:message key="feedback" /></span>
				</c:if>

				<%
				int[] types = {TicketAttachmentConstants.TYPE_HOTFIX, TicketAttachmentConstants.TYPE_LARGE_FILE, TicketAttachmentConstants.TYPE_LARGE_HOTFIX, TicketAttachmentConstants.TYPE_NONE};

				if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_BASIC)) {
					types = ArrayUtil.append(TicketAttachmentConstants.TYPES, TicketAttachmentConstants.TYPES_LARGE);
				}

				int[] userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());

				int attachmentsCount = TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(ticketEntry.getTicketEntryId(), types, userVisibilities);
				%>

				<span id="<portlet:namespace />attachments" onClick="<portlet:namespace />reveal('attachments');"><liferay-ui:message arguments="<%= attachmentsCount %>" key="attachments-x" /></span>

				<%
				int linksCount = TicketLinkLocalServiceUtil.getTicketLinksCount(ticketEntry.getTicketEntryId(), userVisibilities);
				%>

				<span id="<portlet:namespace />links" onClick="<portlet:namespace />reveal('links');"><liferay-ui:message arguments="<%= linksCount %>" key="links-x" /></span>
			</div>
		</div>

		<div class="tab-content" id="<portlet:namespace />ticketTabContent">
			<div id="<portlet:namespace />ticketTabFullContent">
				<c:if test="<%= !screenShareMode && hasViewSupportInstructions %>">
					<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />supportInstructions">
						<pre><%= SupportUtil.getHTML(accountEntry.getInstructions()) %></pre>
					</div>
				</c:if>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />description">

					<%
					Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

					int envLFRNotification = 0;

					if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
						envLFRNotification = GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR));
					}
					else {
						envLFRNotification = ticketEntry.getEnvLFR();
					}
					%>

					<c:if test="<%= (envLFRNotification < ProductEntryConstants.PORTAL_VERSION_6_2_10) && !ArrayUtil.contains(ProductEntryConstants.LIST_TYPES_DEPRECATED, envLFRNotification) %>">
						<liferay-util:include page="/support/2/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
							<portlet:param name="envLFR" value="<%= String.valueOf(envLFRNotification) %>" />
						</liferay-util:include>
					</c:if>

					<%
					PortletPreferences supportPortletPreferences = SupportUtil.getPortletPreferences();

					ProductEntry productEntry = ticketEntry.getProductEntry();
					%>

					<c:if test='<%= GetterUtil.getBoolean(supportPortletPreferences.getValue("showInTicketView_" + productEntry.getLESADisplayName() + StringPool.UNDERLINE + component, StringPool.BLANK), false) %>'>

						<%
						String componentMessage = SupportUtil.getPreferenceValue(supportPortletPreferences, locale, "componentMessage_" + productEntry.getLESADisplayName() + StringPool.UNDERLINE + component);

						String componentMessageLink = supportPortletPreferences.getValue("componentMessageLink_" + productEntry.getLESADisplayName() + StringPool.UNDERLINE + component, StringPool.BLANK);
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

					<pre><%= SupportUtil.getHTML(ticketEntry.getDescription()) %></pre>
				</div>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />component">
					<div class="component-details">
						<c:choose>
							<c:when test="<%= component == TicketEntryConstants.COMPONENT_CLUSTERING %>">
								<liferay-util:include page="/support/2/edit_ticket_entry/details_clustering.jsp" servletContext="<%= application %>" />
							</c:when>
							<c:when test="<%= component == TicketEntryConstants.COMPONENT_LICENSE %>">
								<liferay-util:include page="/support/2/edit_ticket_entry/details_license.jsp" servletContext="<%= application %>" />
							</c:when>
							<c:when test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
								<liferay-util:include page="/support/2/edit_ticket_entry/details_upgrade.jsp" servletContext="<%= application %>" />
							</c:when>
						</c:choose>
					</div>
				</div>

				<c:if test="<%= ticketWorker && !screenShareMode && Validator.isNotNull(reproductionSteps) %>">
					<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />stepsToReproduce">
						<pre><%= SupportUtil.getHTML(ticketEntry.getReproductionSteps()) %></pre>
					</div>
				</c:if>

				<c:if test="<%= hasViewTicketSolution %>">

					<%
					request.setAttribute("details_tabs.jsp-ticketSolution", ticketSolution);
					%>

					<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />solution">
						<liferay-util:include page="/support/2/edit_ticket_entry/solution_confirm.jsp" servletContext="<%= application %>" />
					</div>
				</c:if>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />feedback">
					<c:if test="<%= (liferayIncOrg && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.VIEW)) || ((liferayTicketFeedback == null) && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE)) %>">
						<liferay-util:include page="/support/2/liferay_ticket_feedback_summary.jsp" servletContext="<%= application %>" />
					</c:if>

					<c:if test="<%= (partnerTicketFeedback != null) && !screenShareMode && liferayIncOrg %>">
						<liferay-util:include page="/support/2/partner_ticket_feedback_summary.jsp" servletContext="<%= application %>" />
					</c:if>
				</div>

				<c:if test="<%= (liferayTicketFeedback == null) && closed && OSBTicketFeedbackPermission.containsSubjectLiferay(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.UPDATE) %>">
					<liferay-util:include page="/common/dialog.jsp" servletContext="<%= application %>">
						<liferay-util:param name="centered" value="<%= Boolean.TRUE.toString() %>" />
						<liferay-util:param name="close" value="<%= Boolean.FALSE.toString() %>" />
						<liferay-util:param name="cssClass" value="edit-liferay-ticket-feedback-dialog" />
						<liferay-util:param name="draggable" value="<%= Boolean.FALSE.toString() %>" />
						<liferay-util:param name="id" value="1" />
						<liferay-util:param name="modal" value="<%= Boolean.TRUE.toString() %>" />
						<liferay-util:param name="mvcPath" value="/support/2/edit_liferay_ticket_feedback_dialog.jsp" />
						<liferay-util:param name="visible" value="<%= Boolean.TRUE.toString() %>" />
					</liferay-util:include>
				</c:if>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />attachments">
					<liferay-util:include page="/support/2/edit_ticket_entry/edit_ticket_attachments.jsp" servletContext="<%= application %>" />
				</div>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />links">
					<liferay-util:include page="/support/2/edit_ticket_entry/edit_ticket_links.jsp" servletContext="<%= application %>" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="aui-helper-hidden show-more-button" id="<portlet:namespace />showMoreButtonContainer">
	<input id="<portlet:namespace />showMoreButton" onclick="<portlet:namespace />updateDescription();" type="button" value="<liferay-ui:message key="show-more" />" />
</div>

<aui:script use="aui-base">
	<portlet:namespace />reveal('<%= HtmlUtil.escape(generalTab) %>');
</aui:script>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />reveal',
		function(id) {
			var A = AUI();

			var tab = A.one(".details-tabs .details .tabs #<portlet:namespace />" + id);

			if (tab) {
				A.all(".details-tabs .details .tab-content-tab").hide();

				var tabContent = A.one(".details-tabs .details .tab-content #<portlet:namespace />" + id);

				tabContent.show();

				A.all(".details-tabs .details .tabs span").removeClass("selected");

				tab.addClass("selected");

				<portlet:namespace />updateShowMoreButton();
			}
			else {
				<portlet:namespace />reveal('description');
			}

			window.scroll(0, 0);
		}
	);

	function <portlet:namespace />updateDescription() {
		var pinElementIds = ['<portlet:namespace />discussionTabs', '<portlet:namespace />tabContent', '<portlet:namespace />ticketFade', '<portlet:namespace />ticketFilter'];
		var offsetElementIds = ['<portlet:namespace />showMoreButtonContainer', '<portlet:namespace />ticketTabContent'];

		var showMoreButton = document.getElementById('<portlet:namespace />showMoreButton');
		var ticketTabContent = document.getElementById('<portlet:namespace />ticketTabContent');

		if (ticketTabContent.classList.contains('truncated')) {
			showMoreButton.value = '<liferay-ui:message key="show-less" unicode="<%= true %>" />';

			ticketTabContent.classList.remove('truncated');
		}
		else {
			showMoreButton.value = '<liferay-ui:message key="show-more" unicode="<%= true %>" />';

			ticketTabContent.classList.add('truncated');
		}

		<portlet:namespace />pinElements(pinElementIds, offsetElementIds, 60);
	}

	function <portlet:namespace />updateShowMoreButton() {
		var A = AUI();

		var description = document.getElementById('<portlet:namespace />description');
		var showMoreButton = document.getElementById('<portlet:namespace />showMoreButton');
		var showMoreButtonContainer = A.one("#<portlet:namespace />showMoreButtonContainer");
		var ticketTabContent = document.getElementById('<portlet:namespace />ticketTabContent');
		var ticketTabFullContent = document.getElementById('<portlet:namespace />ticketTabFullContent');

		if (description.classList.contains('selected') && (ticketTabFullContent.offsetHeight > 500)) {
			showMoreButton.value = '<liferay-ui:message key="show-more" unicode="<%= true %>" />';
			showMoreButtonContainer.show();
			ticketTabContent.classList.add('truncated');
		}
		else {
			showMoreButtonContainer.hide();
			ticketTabContent.classList.remove('truncated');
		}
	}
</aui:script>