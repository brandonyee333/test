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

<aui:form action="<%= updateStatusURL %>" method="post" name="fm1">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />
	<aui:input name="body" type="hidden" value="" />
	<aui:input name="reproductionSteps" type="hidden" value="" />

	<div class="ticket-header" id="<portlet:namespace />ticketHeader">
		<div class="back-link cleared txt-sb">
			<a href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back" /></a>
		</div>

		<%
		String ticketEntryURL = SupportUtil.getFriendlyTicketEntryURL(request, ticketEntry.getTicketEntryId());
		%>

		<div class="head">
			<div class="header">
				<div class="fl ticket-severity v<%= ticketEntry.getSeverity() %>"></div>

				<c:if test="<%= clockedIn %>">
					<div class="buttons">
						<liferay-util:include page="/support/2/edit_ticket_entry/buttons.jsp" servletContext="<%= application %>" />
					</div>
				</c:if>

				<div class="page-heading" id="<portlet:namespace/>pageHeading">
					<span class="display-id" id="<portlet:namespace />ticketDisplayId"><a href="<%= ticketEntryURL %>"><%= ticketEntry.getDisplayId() %></a></span>

					<span id="<portlet:namespace />subject"><%= HtmlUtil.escape(subject) %></span>
				</div>

				<div class="sub-header">
					<span class="first segment">
						<img class="ticket-img" id="<portlet:namespace />componentDisplay" src="<%= PortalUtil.getPathContext(request) %>/images/<%= ticketEntry.getComponentIcon() %>" />

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
							<select autocomplete="off" id="<portlet:namespace />severity" name="<portlet:namespace />severity" onChange="<portlet:namespace />updateSeverity();">

								<%
								for (int i = 1; i <= 3; i++) {
								%>

									<option <%= (i == severity) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(i)) %></option>

								<%
								}
								%>

							</select>

							<input class="aui-button-input" id="<portlet:namespace />severityCancel" onclick="<portlet:namespace />toggleForm('<portlet:namespace/>severityDropDown', '<portlet:namespace />severityDisplay');" type="button" value="<liferay-ui:message key="cancel" />" />
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

												<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" /><%= ((i + 1) < pendingTypes.length) ? StringPool.COMMA : StringPool.BLANK %>

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

										<input <%= ArrayUtil.contains(pendingTypes, pendingType) ? "checked=\"checked\"" : StringPool.BLANK %> name="<portlet:namespace />pendingTypes" type="checkbox" value="<%= pendingType %>" />

										<liferay-ui:message key="<%= TicketFlagConstants.getTypeLabel(pendingType) %>" />

									<%
									}
									%>

									<input class="aui-button-input" id="<portlet:namespace />pendingSubmit" onclick="<portlet:namespace />updatePendingTypes();" type="button" value="<liferay-ui:message key="save" />" />
									<input class="aui-button-input" id="<portlet:namespace />pendingCancel" onclick="<portlet:namespace />toggleForm('<portlet:namespace/>pendingCheckboxes', '<portlet:namespace />pendingDisplay');" type="button" value="<liferay-ui:message key="cancel" />" />
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

							<span class="txt-sb" id="<portlet:namespace />statusLabel"><%= LanguageUtil.get(request, ticketEntry.getStatusLabel()) %></span>

							<span class="<%= (resolution == 0) ? "hide" : "txt-sb" %>" id="<portlet:namespace />resolutionLabel">(<%= LanguageUtil.get(request, ticketEntry.getResolutionLabel()) %>)</span>

							<%
							String statusMessage = HtmlUtil.escape(SupportUtil.getPreferenceValue(locale, "statusMessage_" + status));
							%>

							<c:if test="<%= Validator.isNotNull(statusMessage) %>">
								<liferay-ui:icon-help message="<%= statusMessage %>" />
							</c:if>
						</div>

						<div id="<portlet:namespace/>statusDropDown" style="display: none;">
							<select autocomplete="off" id="<portlet:namespace />status" name="<portlet:namespace />status" onChange="<portlet:namespace />updateStatus(this.value);">

								<%
								for (int statusId : TicketEntryConstants.STATUSES_WORKFLOW_ORDER) {
									if ((statusId == TicketEntryConstants.STATUS_INACTIVE) || (statusId == TicketEntryConstants.STATUS_PENDING_WORKER)|| (statusId == TicketEntryConstants.STATUS_REOPENED) || ((statusId == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && !OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_SOLUTION)) || ((statusId == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) || (statusId == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
										if (statusId != status) {
											continue;
										}
									}
								%>

									<option <%= (ticketEntry.getStatus() == statusId) ? "selected" : "" %> value="<%= statusId %>"><%= LanguageUtil.get(request, TicketEntryConstants.getStatusLabel(statusId)) %></option>

								<%
								}
								%>

							</select>

							<select id="<portlet:namespace />resolution" name="<portlet:namespace />resolution" onChange="<portlet:namespace />updateResolution(this.value);" style="display: none;">
								<option value=""></option>

								<%
								for (ListType resolutionType : ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION)) {
								%>

									<option <%= (resolutionType.getListTypeId() == resolution) ? "selected" : "" %> value="<%= resolutionType.getListTypeId() %>"><%= LanguageUtil.get(request, resolutionType.getName()) %></option>

								<%
								}
								%>

							</select>

							<input class="aui-button-input" id="<portlet:namespace />statusCancel" onclick="<portlet:namespace />toggleForm('<portlet:namespace/>statusDropDown', '<portlet:namespace />statusDisplay');" type="button" value="<liferay-ui:message key="cancel" />" />
						</div>
					</span>
				</div>
			</div>
		</div>
	</div>
</aui:form>

<aui:script use="aui-tooltip">
	<c:if test="<%= Validator.isNotNull(ticketEntry.getComponentLabel()) %>">
		new A.Tooltip(
			{
				align: {
					node: null,
					points: [ 'tl', 'bl' ]
				},
				arrow: 'tl',
				bodyContent: '<%= UnicodeLanguageUtil.get(request, ticketEntry.getComponentLabel()) %>',
				hideDelay: 0,
				trigger: '#<portlet:namespace />componentDisplay'
			}
		).render();
	</c:if>

	<c:if test="<%= Validator.isNotNull(subject) && (subject.length() > 55) %>">
		new A.Tooltip(
			{
				align: {
					node: null,
					points: [ 'tl', 'bl' ]
				},
				arrow: 'tl',
				bodyContent: '<%= HtmlUtil.escape(subject) %>',
				hideDelay: 0,
				trigger: '#<portlet:namespace/>subject'
			}
		).render();
	</c:if>
</aui:script>

<aui:script>
	function <portlet:namespace />resetStatus() {
		<portlet:namespace />toggleForm('<portlet:namespace />statusDropDown', '<portlet:namespace />statusDisplay');

		if (<%= resolution == 0 %>) {
			document.getElementById('<portlet:namespace />resolutionDropDown').style.display = "none";
		}
		else {
			<portlet:namespace />toggleForm('<portlet:namespace />resolutionDropDown', '<portlet:namespace />resolutionLabel');
		}

		document.getElementById('<portlet:namespace />resolutionCancel').style.display = 'none';
		document.getElementById('<portlet:namespace />statusCancel').style.display = 'none';

		document.<portlet:namespace />fm1.<portlet:namespace />resolution.value = '<%= resolution %>';
		document.<portlet:namespace />fm1.<portlet:namespace />status.value = '<%= status %>';
	}

	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = '';
		document.getElementById(hideId).style.display = 'none';
	}

	<c:if test="<%= hasUpdateAdvanced %>">
		function <portlet:namespace />updatePendingTypes() {
			var updatePendingURL = '<portlet:actionURL name="updatePendingTypes"><portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" /></portlet:actionURL>';

			document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';

			submitForm(document.<portlet:namespace />fm1, updatePendingURL);
		}

		function <portlet:namespace />updateReproductionSteps(reproductionSteps) {
			document.<portlet:namespace />fm1.<portlet:namespace /><%= CMDConstants.CMD %>.value = '<%= CMDConstants.REPRODUCE %>';
			document.<portlet:namespace />fm1.<portlet:namespace />reproductionSteps.value = reproductionSteps;

			submitForm(document.<portlet:namespace />fm1);
		}

		function <portlet:namespace />updateResolution(resolution) {
			var A = AUI();

			var oldStatusLabel = '<%= UnicodeLanguageUtil.get(request, ticketEntry.getStatusLabel()) %>';
			var newLabel = A.one('#<portlet:namespace />status option:selected').html() + ' - ' + A.one('#<portlet:namespace />resolution option:selected').html();

			if (!confirm(Liferay.Language.get('are-you-sure-you-want-to-modify-the-status-from-x-to-x', [oldStatusLabel, newLabel]))) {
				return false;
			}

			document.<portlet:namespace />fm1.<portlet:namespace /><%= CMDConstants.CMD %>.value = '<%= CMDConstants.CLOSE %>';

			submitForm(document.<portlet:namespace />fm1);
		}

		function <portlet:namespace />updateSeverity() {
			var updateSeverityURL = '<portlet:actionURL name="updateTicketEntrySeverity"><portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" /></portlet:actionURL>';

			document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';

			submitForm(document.<portlet:namespace />fm1, updateSeverityURL);
		}

		function <portlet:namespace />updateStatus(status) {
			var A = AUI();

			var oldStatusLabel = '<%= UnicodeLanguageUtil.get(request, ticketEntry.getStatusLabel()) %>';
			var newStatusLabel = A.one('#<portlet:namespace />status option:selected').html();

			if (oldStatusLabel == newStatusLabel) {
				return false;
			}

			if ((status != '<%= TicketEntryConstants.STATUS_CLOSED %>') && !confirm(Liferay.Language.get('are-you-sure-you-want-to-modify-the-status-from-x-to-x', [oldStatusLabel, newStatusLabel]))) {
				<portlet:namespace />toggleForm('<portlet:namespace />statusDropDown', '<portlet:namespace />statusDisplay');

				A.one('#<portlet:namespace />status').set('value', '<%= ticketEntry.getStatus() %>');

				return false;
			}

			if (status == '<%= TicketEntryConstants.STATUS_CLOSED %>') {
				document.getElementById('<portlet:namespace />resolution').style.display = '';

				return false;
			}
			else {
				document.getElementById('<portlet:namespace />resolution').style.display = 'none';
			}

			<c:if test="<%= liferayIncOrg && (status != TicketEntryConstants.STATUS_REPRODUCED) %>">
				if (status == '<%= TicketEntryConstants.STATUS_REPRODUCED %>') {
					window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="jspPage" value="/support/2/edit_ticket_entry/reproduction_steps.jsp" /><portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" /></portlet:renderURL>', 'reproductionSteps', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800').focus();

					return;
				}
			</c:if>

			submitForm(document.<portlet:namespace />fm1);
		}
	</c:if>
</aui:script>