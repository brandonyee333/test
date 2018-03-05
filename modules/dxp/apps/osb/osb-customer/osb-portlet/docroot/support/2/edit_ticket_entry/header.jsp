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

String backURL = (String)request.getAttribute("edit_ticket_entry.jsp-backURL");

int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
int escalationLevel = BeanParamUtil.getInteger(ticketEntry, request, "escalationLevel");
int resolution = BeanParamUtil.getInteger(ticketEntry, request, "resolution");
int severity = BeanParamUtil.getInteger(ticketEntry, request, "severity");
int status = BeanParamUtil.getInteger(ticketEntry, request, "status");
int subcomponent = BeanParamUtil.getInteger(ticketEntry, request, "subcomponent");
String subcomponentCustom = BeanParamUtil.getString(ticketEntry, request, "subcomponentCustom");
String subject = BeanParamUtil.getString(ticketEntry, request, "subject");

boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");
%>

<portlet:actionURL name="updateTicketEntryStatus" var="updateStatusURL">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
	<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
</portlet:actionURL>

<aui:form action="<%= updateStatusURL %>" method="post" name="ticketStatusFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />
	<aui:input name="body" type="hidden" value="" />
	<aui:input name="reproductionSteps" type="hidden" value="" />

	<div class="detail-view-header" id="<portlet:namespace />ticketHeader">
		<div class="back-link txt-sb">
			<a href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back" /></a>
		</div>

		<%
		String ticketEntryURL = SupportUtil.getFriendlyTicketEntryURL(request, ticketEntry.getTicketEntryId());
		%>

		<div class="header">
			<span class="pull-left ticket-severity <%= ticketEntry.getSeverityLabel() %>"></span>

			<c:if test="<%= clockedIn %>">
				<div class="buttons">
					<liferay-util:include page="/support/2/edit_ticket_entry/buttons.jsp" servletContext="<%= application %>" />
				</div>
			</c:if>

			<div class="page-heading" id="<portlet:namespace/>pageHeading">
				<span class="display-id" id="<portlet:namespace />ticketDisplayId"><a href="<%= ticketEntryURL %>"><%= ticketEntry.getDisplayId() %></a></span>

				<%
				String subjectTooltipTitle = StringPool.BLANK;
				String subjectTooltipTrigger = StringPool.BLANK;

				if (Validator.isNotNull(subject) && (subject.length() > 55)) {
					subjectTooltipTitle = "title='" + HtmlUtil.escape(subject) + "'";

					subjectTooltipTrigger = "tooltip-trigger";
				}
				%>

				<span class="<%= subjectTooltipTrigger %>" id="<portlet:namespace />subject" <%= subjectTooltipTitle %>><%= HtmlUtil.escape(subject) %></span>
			</div>

			<div class="sub-header">
				<span class="first segment">

					<%
					String componentLabelTooltipTitle = StringPool.BLANK;
					String componentLabelTooltipTrigger = StringPool.BLANK;

					if (Validator.isNotNull(ticketEntry.getComponentLabel())) {
						componentLabelTooltipTitle = "title='" + LanguageUtil.get(request, ticketEntry.getComponentLabel()) + "'";

						componentLabelTooltipTrigger = "tooltip-trigger";
					}
					%>

					<img class="<%= componentLabelTooltipTrigger %> ticket-img" id="<portlet:namespace />componentDisplay" src="<%= PortalUtil.getPathContext(request) %>/images/<%= ticketEntry.getComponentIcon() %>" <%= componentLabelTooltipTitle %> />

					<c:if test="<%= liferayIncOrg %>">
						<c:if test="<%= subcomponent > 0 %>">
							(<%= LanguageUtil.get(request, ticketEntry.getSubcomponentLabel()) %>)
						</c:if>

						<c:if test="<%= Validator.isNotNull(subcomponentCustom) %>">
							- <%= HtmlUtil.escape(subcomponentCustom) %>
						</c:if>
					</c:if>
				</span>
				<span class="spacer"></span>

				<span class="segment">

					<%
					String severityOnClick = StringPool.BLANK;

					if ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && hasUpdateAdvanced) {
						severityOnClick = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "severityDisplay', '" + renderResponse.getNamespace() + "severityDropDown');";
					}
					%>

					<div id="<portlet:namespace />severityDisplay" onClick="<%= severityOnClick %>">
						<liferay-ui:message key="severity" />:

						<span class="txt-sb"><%= LanguageUtil.get(request, ticketEntry.getSeverityLabel()) %></span>
					</div>

					<div id="<portlet:namespace />severityDropDown" style="display: none;">
						<aui:select autocomplete="off" label="" name="severity" onChange='<%= renderResponse.getNamespace() + "updateSeverity();" %>'>

							<%
							for (int i = 1; i <= 3; i++) {
							%>

								<aui:option label="<%= TicketEntryConstants.getSeverityLabel(i) %>" selected="<%= i == severity %>" value="<%= i %>" />

							<%
							}
							%>

						</aui:select>

						<aui:button name="severityCancel" onClick='<%= renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "severityDropDown', '" + renderResponse.getNamespace() + "severityDisplay');" %>' value="cancel" />
					</div>
				</span>
				<span class="spacer"></span>

				<c:if test="<%= !screenShareMode && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) %>">
					<span class="segment">

						<%
						String pendingOnClick = StringPool.BLANK;

						if ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && hasUpdateAdvanced) {
							pendingOnClick = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "pendingDisplay', '" + renderResponse.getNamespace() + "pendingCheckboxes');";
						}
						%>

						<div id="<portlet:namespace />pendingDisplay" onClick="<%= pendingOnClick %>">
							<liferay-ui:message key="pending" />:

							<%
							int[] pendingTypes = TicketFlagLocalServiceUtil.getTicketFlagTypes(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPES_PENDING, TicketFlagConstants.FLAG_TRUE);
							%>

							<span class="txt-sb">
								<c:choose>
									<c:when test="<%= ticketWorker %>">

										<%
										for (int i = 0; i < pendingTypes.length; i++) {
											int pendingType = pendingTypes[i];
										%>

											<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" /><%= ((i + 1) < pendingTypes.length) ? StringPool.COMMA : "" %>

										<%
										}
										%>

									</c:when>
									<c:otherwise>

										<%
										String pendingTypeLabel = StringPool.BLANK;

										if (ArrayUtil.contains(pendingTypes, TicketFlagConstants.TYPE_PENDING_CUSTOMER)) {
											pendingTypeLabel = TicketFlagConstants.getTypeLabel(TicketFlagConstants.TYPE_PENDING_CUSTOMER);
										}
										else if (pendingTypes.length > 0) {
											pendingTypeLabel = "support";
										}
										%>

										<liferay-ui:message key="<%= pendingTypeLabel %>" />
									</c:otherwise>
								</c:choose>
							</span>
						</div>

						<c:if test="<%= hasUpdateAdvanced %>">
							<div id="<portlet:namespace/>pendingCheckboxes" style="display: none;">

								<%
								for (int pendingType : TicketFlagConstants.TYPES_PENDING) {
								%>

									<aui:input checked="<%= ArrayUtil.contains(pendingTypes, pendingType) %>" label="" name="pendingTypes" type="checkbox" value="<%= pendingType %>" />

									<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" />

								<%
								}
								%>

								<aui:button name="pendingSubmit" onClick='<%= renderResponse.getNamespace() + "updatePendingTypes();" %>' value="save" />
								<aui:button name="pendingCancel" onClick='<%= renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "pendingCheckboxes', '" + renderResponse.getNamespace() + "pendingDisplay');" %>' value="cancel" />
							</div>
						</c:if>
					</span>
					<span class="spacer"></span>
				</c:if>

				<span class="segment">
					<liferay-ui:message key="escalation" />:

					<span class="txt-sb"><%= ticketEntry.getEscalationLevelLabel() %></span>
				</span>
				<span class="spacer"></span>

				<span class="segment">

					<%
					String statusOnClick = StringPool.BLANK;

					if ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && hasUpdateAdvanced) {
						statusOnClick = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "statusDisplay', '" + renderResponse.getNamespace() + "statusDropDown');";
					}
					%>

					<div id="<portlet:namespace />statusDisplay" onClick="<%= statusOnClick %>">
						<liferay-ui:message key="status" />:

						<span class="txt-sb"><%= LanguageUtil.get(request, ticketEntry.getStatusLabel()) %></span>

						<span class="<%= (resolution == 0) ? "hide" : "txt-sb" %>" id="<portlet:namespace />headerResolutionLabel">(<%= LanguageUtil.get(request, ticketEntry.getResolutionLabel()) %>)</span>

						<%
						String statusMessage = HtmlUtil.escape(SupportUtil.getPreferenceValue(locale, "statusMessage_" + status));
						%>

						<c:if test="<%= Validator.isNotNull(statusMessage) %>">
							<liferay-ui:icon-help message="<%= statusMessage %>" />
						</c:if>
					</div>

					<div id="<portlet:namespace/>statusDropDown" style="display: none;">
						<aui:select autocomplete="off" id="headerStatus" label="" name="status" onChange='<%= renderResponse.getNamespace() + "updateStatus(this.value);" %>'>

							<%
							for (int statusId : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
								if ((statusId == TicketEntryConstants.STATUS_INACTIVE) || (statusId == TicketEntryConstants.STATUS_PENDING_WORKER)|| (statusId == TicketEntryConstants.STATUS_REOPENED) || ((statusId == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && !OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_SOLUTION)) || ((statusId == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) || (statusId == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
									if (statusId != status) {
										continue;
									}
								}
							%>

								<aui:option label="<%= TicketEntryConstants.getStatusLabel(statusId) %>" selected="<%= ticketEntry.getStatus() == statusId %>" value="<%= statusId %>" />

							<%
							}
							%>

						</aui:select>

						<aui:select cssClass="hide" id="headerResolution" label="" name="resolution" onChange='<%= renderResponse.getNamespace() + "updateResolution(this.value);" %>'>
							<aui:option value="" />

							<%
							for (ListType resolutionType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION)) {
							%>

								<aui:option label="<%= resolutionType.getName() %>" selected="<%= resolutionType.getListTypeId() == resolution %>" value="<%= resolutionType.getListTypeId() %>" />

							<%
							}
							%>

						</aui:select>

						<aui:button name="statusCancel" onClick='<%= renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "statusDropDown', '" + renderResponse.getNamespace() + "statusDisplay');" %>' value="cancel" />
					</div>
				</span>
			</div>
		</div>
	</div>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />resetStatus',
		function() {
			var A = AUI();

			<portlet:namespace />toggleForm('<portlet:namespace />statusDropDown', '<portlet:namespace />statusDisplay');

			if (<%= resolution == 0 %>) {
				var resolutionDropDown = A.one('#<portlet:namespace />resolutionDropDown');

				if (resolutionDropDown) {
					resolutionDropDown.hide();
				}
			}
			else {
				<portlet:namespace />toggleForm('<portlet:namespace />resolutionDropDown', '<portlet:namespace />headerResolutionLabel');
			}

			var statusCancel = A.one('#<portlet:namespace />statusCancel');

			if (statusCancel) {
				statusCancel.hide();
			}

			var ticketStatusFm = A.one('#<portlet:namespace />ticketStatusFm');

			if (ticketStatusFm) {
				var resolution = ticketStatusFm.one('#<portlet:namespace />resolution');

				if (resolution) {
					resolution.val('<%= resolution %>');
				}

				var status = ticketStatusFm.one('#<portlet:namespace />status');

				if (status) {
					status.val('<%= status %>');
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />toggleForm',
		function(hideId, showId) {
			var A = AUI();

			var nodeShow = A.one('#' + showId);

			if (nodeShow) {
				nodeShow.show();
			}

			var nodeHide = A.one('#' + hideId);

			if (nodeHide) {
				nodeHide.hide();
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateResolution',
		function(resolution) {
			var A = AUI();

			var headerResolution = A.one('#<portlet:namespace />headerResolution option:selected');
			var headerStatus = A.one('#<portlet:namespace />headerStatus option:selected');

			if (headerResolution && headerStatus) {
				var oldStatusLabel = '<%= UnicodeLanguageUtil.get(request, ticketEntry.getStatusLabel()) %>';

				var newLabel = headerStatus.html() + ' - ' + headerResolution.html();

				if (confirm(Liferay.Language.get('are-you-sure-you-want-to-modify-the-status-from-x-to-x', [oldStatusLabel, newLabel]))) {
					var ticketStatusFm = A.one('#<portlet:namespace />ticketStatusFm');

					if (ticketStatusFm) {
						var cmd = ticketStatusFm.one('#<portlet:namespace /><%= CMDConstants.CMD %>');

						if (cmd) {
							cmd.val('<%= CMDConstants.CLOSE %>');
						}

						submitForm(ticketStatusFm);
					}
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateStatus',
		function(status) {
			var A = AUI();

			var newStatusLabel = A.one('#<portlet:namespace />headerStatus option:selected');

			if (newStatusLabel) {
				newStatusLabel = newStatusLabel.html();
			}

			var oldStatusLabel = '<%= UnicodeLanguageUtil.get(request, ticketEntry.getStatusLabel()) %>';

			if (newStatusLabel) {
				if (oldStatusLabel != newStatusLabel) {
					if ((status != '<%= TicketEntryConstants.STATUS_CLOSED %>') && !confirm(Liferay.Language.get('are-you-sure-you-want-to-modify-the-status-from-x-to-x', [oldStatusLabel, newStatusLabel]))) {
						<portlet:namespace />toggleForm('<portlet:namespace />statusDropDown', '<portlet:namespace />statusDisplay');

						var status = A.one('#<portlet:namespace />status');

						if (status) {
							status.val('<%= ticketEntry.getStatus() %>');
						}

						return false;
					}
				}
			}

			var headerResolution = A.one('#<portlet:namespace />headerResolution');

			if (headerResolution) {
				headerResolution.toggle(status == '<%= TicketEntryConstants.STATUS_CLOSED %>');
			}

			if (status != '<%= TicketEntryConstants.STATUS_CLOSED %>') {
				<c:if test="<%= liferayIncOrg && (status != TicketEntryConstants.STATUS_REPRODUCED) %>">
					if (status == '<%= TicketEntryConstants.STATUS_REPRODUCED %>') {
						window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="jspPage" value="/support/2/edit_ticket_entry/reproduction_steps.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'reproductionSteps', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800').focus();

						return;
					}
				</c:if>

				submitForm(document.<portlet:namespace />ticketStatusFm);
			}
		},
		['aui-base']
	);

	<c:if test="<%= hasUpdateAdvanced %>">
		Liferay.provide(
			window,
			'<portlet:namespace />updatePendingTypes',
			function() {
				var A = AUI();

				var ticketStatusFm = A.one('#<portlet:namespace />ticketStatusFm');

				if (ticketStatusFm) {
					var redirect = ticketStatusFm.one('#<portlet:namespace />redirect');

					if (redirect) {
						redirect.val('<%= portletURL.toString() %>');

						submitForm(ticketStatusFm, '<portlet:actionURL name="updatePendingTypes"><portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" /></portlet:actionURL>');
					}
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateReproductionStepValues',
			function(reproductionSteps) {
				var A = AUI();

				var ticketStatusFm = A.one('#<portlet:namespace />ticketStatusFm');

				if (ticketStatusFm) {
					var cmd = ticketStatusFm.one('#<portlet:namespace /><%= CMDConstants.CMD %>');

					if (cmd) {
						cmd.val('<%= CMDConstants.REPRODUCE %>');
					}

					var reproductionSteps = ticketStatusFm.one('#<portlet:namespace />reproductionSteps');

					if (reproductionSteps) {
						reproductionSteps.val(reproductionSteps);
					}

					submitForm(ticketStatusFm);
				}
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateSeverity',
			function() {
				var A = AUI();

				var ticketStatusFm = A.one('#<portlet:namespace />ticketStatusFm');

				if (ticketStatusFm) {
					var redirect = ticketStatusFm.one('#<portlet:namespace />redirect');

					if (redirect) {
						redirect.val('<%= portletURL.toString() %>');
					}

					submitForm(ticketStatusFm, '<portlet:actionURL name="updateTicketEntrySeverity"><portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" /></portlet:actionURL>');
				}
			},
			['aui-base']
		);
	</c:if>
</aui:script>

<aui:script use="aui-tooltip-delegate">
	new A.TooltipDelegate(
		{
			trigger: '.tooltip-trigger'
		}
	)
</aui:script>