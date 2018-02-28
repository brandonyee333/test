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

boolean accountCustomer = (Boolean)request.getAttribute("edit_ticket_entry.jsp-accountCustomer");
boolean canForward = (Boolean)request.getAttribute("edit_ticket_entry.jsp-canForward");
boolean closed = (Boolean)request.getAttribute("edit_ticket_entry.jsp-closed");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
boolean hasUpdateBasic = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateBasic");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

int resolution = BeanParamUtil.getInteger(ticketEntry, request, "resolution");
int status = BeanParamUtil.getInteger(ticketEntry, request, "status");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");

String label = StringPool.BLANK;
String onClick = StringPool.BLANK;

if (hasUpdateBasic && ticketWorker && (liferayIncOrg || supportPartnerWorker)) {
	if (hasUpdateAdvanced) {
		if (status == TicketEntryConstants.STATUS_BUILDING_PATCH) {
			label = "propose-solution";

			PortletURL buttonURL = renderResponse.createRenderURL();

			buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry/solution_details.jsp");
			buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
			buttonURL.setWindowState(LiferayWindowState.POP_UP);

			onClick = "window.open('" + buttonURL.toString() + "', 'solutionDetailsWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();";
		}
		else if (liferayIncOrg && (status == TicketEntryConstants.STATUS_INVESTIGATING)) {
			label = "reproduce";

			PortletURL buttonURL = renderResponse.createRenderURL();

			buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry/reproduction_steps.jsp");
			buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
			buttonURL.setWindowState(LiferayWindowState.POP_UP);

			onClick = "window.open('" + buttonURL.toString() + "', 'reproductionStepsWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();";
		}
		else {
			PortletURL buttonURL = renderResponse.createActionURL();

			buttonURL.setParameter(ActionRequest.ACTION_NAME, "updateTicketEntryStatus");
			buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
			buttonURL.setParameter(Constants.CMD, Constants.UPDATE);
			buttonURL.setParameter("redirect", portletURL.toString());
			buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));

			if (status == TicketEntryConstants.STATUS_ENGINEER_ANALYZING) {
				label = "build-patch";

				buttonURL.setParameter("status", String.valueOf(TicketEntryConstants.STATUS_BUILDING_PATCH));
			}
			else if ((status == TicketEntryConstants.STATUS_INACTIVE) || (status == TicketEntryConstants.STATUS_ON_HOLD) || (status == TicketEntryConstants.STATUS_PENDING_WORKER)) {
				label = "resume";

				buttonURL.setParameter("status", String.valueOf(TicketEntryConstants.STATUS_INVESTIGATING));
			}
			else if (supportPartnerWorker && (status == TicketEntryConstants.STATUS_INVESTIGATING)) {
				label = "reproduce";

				buttonURL.setParameter("status", String.valueOf(TicketEntryConstants.STATUS_REPRODUCED));
			}
			else if ((status == TicketEntryConstants.STATUS_INCIDENT_REPORTED) || (status == TicketEntryConstants.STATUS_OPEN) || (status == TicketEntryConstants.STATUS_REOPENED)) {
				label = "investigate";

				buttonURL.setParameter("status", String.valueOf(TicketEntryConstants.STATUS_INVESTIGATING));
			}
			else if (status == TicketEntryConstants.STATUS_REPRODUCED) {
				label = "analyze";

				buttonURL.setParameter("status", String.valueOf(TicketEntryConstants.STATUS_ENGINEER_ANALYZING));
			}

			onClick = "location.href='" + buttonURL.toString() + "';";
		}
	}

	if ((label.isEmpty() && !hasUpdateAdvanced && (status != TicketEntryConstants.STATUS_CLOSED)) || (status == TicketEntryConstants.STATUS_RESOLVED) || (status == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) || (status == TicketEntryConstants.STATUS_SOLUTION_PROPOSED)) {
		label = "close-ticket";

		PortletURL buttonURL = renderResponse.createRenderURL();

		buttonURL.setParameter("mvcPath", "/support/2/select_resolution.jsp");
		buttonURL.setWindowState(LiferayWindowState.POP_UP);

		onClick = "window.open('" + buttonURL.toString() + "', 'resolutionDetailsWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();";
	}
}
else if (hasUpdateBasic && accountCustomer) {
	if ((status == TicketEntryConstants.STATUS_CLOSED) && (resolution == TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER)) {
		label = "complete-ticket";

		PortletURL buttonURL = renderResponse.createActionURL();

		buttonURL.setParameter(ActionRequest.ACTION_NAME, "closeTicketEntry");
		buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
		buttonURL.setParameter("redirect", portletURL.toString());
		buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
		buttonURL.setParameter("resolution", String.valueOf(TicketEntryConstants.RESOLUTION_COMPLETED));

		onClick = "location.href='" + buttonURL.toString() + "';";
	}
	else {
		label = "close-ticket";

		PortletURL buttonURL = renderResponse.createRenderURL();

		buttonURL.setParameter("mvcPath", "/support/2/select_resolution.jsp");
		buttonURL.setWindowState(LiferayWindowState.POP_UP);

		onClick = "window.open('" + buttonURL.toString() + "', 'resolutionDetailsWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();";
	}
}
else if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.WATCH)) {
	boolean subscribed = SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), TicketEntry.class.getName(), ticketEntry.getTicketEntryId());

	if (!subscribed) {
		label = "watch-ticket";
	}
	else {
		label = "unwatch-ticket";
	}

	PortletURL buttonURL = renderResponse.createActionURL();

	buttonURL.setParameter(ActionRequest.ACTION_NAME, "watchTicket");
	buttonURL.setParameter("redirect", portletURL.toString());
	buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
	buttonURL.setParameter("watchTicket", String.valueOf(!subscribed));

	onClick = "location.href='" + buttonURL.toString() + "';";
}

if (hasUpdateBasic && (((status == TicketEntryConstants.STATUS_CLOSED) && (!accountCustomer || (resolution != TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER))) || (accountCustomer && (status == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION)))) {
	label = "reopen";

	PortletURL buttonURL = renderResponse.createActionURL();

	buttonURL.setParameter(ActionRequest.ACTION_NAME, "reopenTicketEntry");
	buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
	buttonURL.setParameter("redirect", portletURL.toString());
	buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));

	onClick = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-reopen-this-ticket") + "')) { location.href='" + buttonURL + "'; } else { self.focus(); }";
}
%>

<c:if test="<%= Validator.isNotNull(label) %>">
	<input class="aui-button-input" <%= Validator.isNotNull(onClick) ? "onClick=\"" + onClick + "\"" : "" %> type="button" value="<liferay-ui:message key="<%= label %>" />" />
</c:if>

<%
Map<String, String> dropDownList = new TreeMap<String, String>();

if (!screenShareMode && hasUpdateAdvanced && (!closed || liferayIncOrg)) {
	PortletURL buttonURL = renderResponse.createRenderURL();

	buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry/edit_ticket_entry_dialog.jsp");
	buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
	buttonURL.setWindowState(LiferayWindowState.POP_UP);

	dropDownList.put("edit", renderResponse.getNamespace() + "openEditTicketPopup('" + buttonURL.toString() + "')");
}

if (!closed) {
	if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ASSIGN_WORKERS)) {
		PortletURL buttonURL = renderResponse.createRenderURL();

		buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry/edit_ticket_workers.jsp");
		buttonURL.setParameter("redirect", portletURL.toString());
		buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));

		dropDownList.put("assign-workers", "location.href='" + buttonURL.toString() + "';");
	}

	if (!screenShareMode) {
		if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ESCALATE)) {
			PortletURL buttonURL = renderResponse.createRenderURL();

			buttonURL.setParameter("mvcPath", "/support/2/escalate_ticket_entry.jsp");
			buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
			buttonURL.setWindowState(LiferayWindowState.POP_UP);

			dropDownList.put("escalate", "window.open('" + buttonURL.toString() + "', 'escalateTicketWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();");
		}

		if (hasUpdateAdvanced && ticketWorker && (status != TicketEntryConstants.STATUS_BUILDING_PATCH) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED) && (ArrayUtil.contains(TicketEntryConstants.STATUSES_WORKER_OPEN, status) || (status == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION))) {
			PortletURL buttonURL = renderResponse.createRenderURL();

			buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry/solution_details.jsp");
			buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
			buttonURL.setWindowState(LiferayWindowState.POP_UP);

			dropDownList.put("propose-solution", "window.open('" + buttonURL.toString() + "', 'solutionDetailsWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();");
		}
	}

	if ((status != TicketEntryConstants.STATUS_RESOLVED) && (status != TicketEntryConstants.STATUS_SOLUTION_DELIVERED) && (status != TicketEntryConstants.STATUS_SOLUTION_PROPOSED) && (((status == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) && hasUpdateAdvanced && ticketWorker) || (!hasUpdateAdvanced && (ticketEntry.getUserId() == user.getUserId())) || (hasUpdateBasic && accountCustomer))) {
		PortletURL buttonURL = renderResponse.createRenderURL();

		buttonURL.setParameter("mvcPath", "/support/2/select_resolution.jsp");
		buttonURL.setWindowState(LiferayWindowState.POP_UP);

		dropDownList.put("close-ticket", "window.open('" + buttonURL.toString() + "', 'solutionDetailsWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();");
	}
}
else if (hasUpdateBasic && accountCustomer && (status == TicketEntryConstants.STATUS_CLOSED) && (resolution == TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER)) {
	PortletURL buttonURL = renderResponse.createActionURL();

	buttonURL.setParameter(ActionRequest.ACTION_NAME, "reopenTicketEntry");
	buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
	buttonURL.setParameter("redirect", portletURL.toString());
	buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));

	dropDownList.put("reopen", "location.href='" + buttonURL.toString() + "';");
}

if (!screenShareMode) {
	if (liferayIncOrg) {
		PortletURL buttonURL = renderResponse.createRenderURL();

		buttonURL.setParameter("mvcPath", "/support/2/edit_ticket_call.jsp");
		buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
		buttonURL.setWindowState(LiferayWindowState.POP_UP);

		dropDownList.put("log-call", "window.open('" + buttonURL.toString() + "', 'ticketCallWindow', '" + _POP_UP_WINDOW_PARAMETERS + "').focus();");

		dropDownList.put("create-lpp-ticket", renderResponse.getNamespace() + "setupJIRAModal(this)");

		if (canForward && clockedIn) {
			dropDownList.put("forward", renderResponse.getNamespace() + "forwardTicketEntry();");
		}
	}

	if (hasUpdateBasic && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry.getTicketEntryId(), OSBActionKeys.WATCH)) {
		boolean subscribed = SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), TicketEntry.class.getName(), ticketEntry.getTicketEntryId());

		PortletURL buttonURL = renderResponse.createActionURL();

		buttonURL.setParameter(ActionRequest.ACTION_NAME, "watchTicket");
		buttonURL.setParameter("redirect", portletURL.toString());
		buttonURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
		buttonURL.setParameter("watchTicket", String.valueOf(!subscribed));

		dropDownList.put((!subscribed ? "watch-ticket" : "unwatch-ticket"), "location.href='" + buttonURL.toString() + "';");
	}
}
%>

<c:if test="<%= !dropDownList.isEmpty() %>">
	<span class="three-dot" id="<portlet:namespace />threeDotMenu">
		<span class="three-dot-icon" id="<portlet:namespace />threeDotIcon">
			<span style="top: 6px;"></span>
			<span style="top: 14px;"></span>
			<span style="top: 22px;"></span>
		</span>

		<div class="drop-down-menu">
			<div class="drop-down-arrow">
				<div></div>
			</div>

			<ul>

				<%
				for (Map.Entry<String, String> entry : dropDownList.entrySet()) {
					String curLabel = entry.getKey();
					String curOnClick = entry.getValue();

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"javascript:;\" onClick=\"");
					sb.append(curOnClick);
					sb.append("\">");
					sb.append(LanguageUtil.get(request, curLabel));
					sb.append("</a>");
				%>

					<li>
						<%= sb.toString() %>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</span>
</c:if>

<c:if test="<%= liferayIncOrg %>">
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />setupJIRAModal',
			function() {
				var A = AUI();

				var modal = new A.Modal(
					{
						bodyContent:
							'<button class="block btn" issueType="3" /><liferay-ui:message key="task" /></button>' +
							'<button class="block btn" issueType="11" /><liferay-ui:message key="patch" /></button>' +
							'<button class="block btn" issueType="46" /><liferay-ui:message key="l1-escalation" /></button>',
						centered: true,
						cssClass: 'jira-issue-modal',
						destroyOnHide: true,
						draggable: true,
						headerContent: '<h4><liferay-ui:message key="select-issue-type" unicode="<%= true %>" /></h4>',
						modal: true,
						resizable: false,
						visible: false,
						zIndex: 1036
					}
				).render();

				var handleButtonClick = function(event) {
					var issueType = event.currentTarget.getAttribute('issueType');

					window.open('<%= SupportUtil.createJIRAIssueURL(request, ticketEntry) %>&issuetype=' + issueType);

					modal.hide();
				};

				var jiraIssueModal = A.one('.jira-issue-modal');

				if (jiraIssueModal) {
					jiraIssueModal.delegate('click', handleButtonClick, 'button');
				}

				modal.show();
			},
			['aui-modal']
		);

		function <portlet:namespace />updateReproductionStepValues(reproductionSteps) {
			document.<portlet:namespace />fm1.<portlet:namespace />reproductionSteps.value = reproductionSteps;

			document.<portlet:namespace />fm1.<portlet:namespace />status.value = <%= TicketEntryConstants.STATUS_REPRODUCED %>;

			submitForm(document.<portlet:namespace />fm1);
		}
	</aui:script>
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />openEditTicketPopup',
		function(uri) {
			Liferay.Util.openWindow(
				{
					dialog: {
						centered: true,
						cssClass: 'edit-ticket-modal',
						destroyOnHide: true,
						modal: true,
						width: 760
					},
					id: '<portlet:namespace />editTicketPopup',
					title: '<%= LanguageUtil.get(request, "edit-ticket") + StringPool.COLON + StringPool.SPACE + ticketEntry.getDisplayId() %>',
					uri: uri
				}
			);
		}
	);
</aui:script>

<%!
private static final String _POP_UP_WINDOW_PARAMETERS = "directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800";
%>