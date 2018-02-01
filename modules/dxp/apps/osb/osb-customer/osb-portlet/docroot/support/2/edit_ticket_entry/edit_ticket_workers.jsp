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
String tabs2 = ParamUtil.getString(request, "tabs2", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/edit_ticket_entry/edit_ticket_workers.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntryId));
%>

<liferay-ui:error exception="<%= RequiredTicketWorkerException.class %>" message="tickets-must-have-at-least-one-worker-assigned" />

<c:if test="<%= OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ASSIGN_WORKERS) %>">
	<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
		<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(redirect) %>" />
		<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
		<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntryId %>" />

		<div class="clearfix section">
			<div class="pull-left">
				Edit Workers for Ticket: <%= ticketEntry.getDisplayId() %>
			</div>

			<div class="pull-right">
				<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
			</div>
		</div>

		<div>
			<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
			<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />

			<liferay-ui:tabs
				names="current,available"
				param="tabs2"
				url="<%= portletURL.toString() %>"
			/>

			<%@ include file="/common/user_search_inputs.jspf" %>

			<%
			LinkedHashMap userParams = new LinkedHashMap();

			if (tabs2.equals("current")) {
				userParams.put("usersTicketWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByTicketWorkerEntries"), Long.valueOf(ticketEntry.getTicketEntryId())));
			}
			else if (liferayIncOrg) {
				userParams.put("availableTicketWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAvailableTicketWorkers"), Long.valueOf(OSBConstants.ORGANIZATION_LIFERAY_INC_ID)));
			}
			else {
				AccountEntry accountEntry = ticketEntry.getAccountEntry();

				if (accountEntry.isPartnerManagedSupport()) {
					userParams.put("usersPartnerWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByPartnerWorker"), Long.valueOf(accountEntry.getPartnerEntryId())));
				}
			}
			%>

			<liferay-ui:search-container
				emptyResultsMessage="no-users-were-found"
				id="usersSearchContainer"
				iteratorURL="<%= portletURL %>"
				rowChecker="<%= new UserTicketWorkerChecker(renderResponse, ticketEntry) %>"
				searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
			>

				<%
				UserDisplayTerms searchTerms = (UserDisplayTerms)searchContainer.getSearchTerms();

				if (!searchTerms.isAdvancedSearch()) {
					searchContainer.setTotal(UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), searchTerms.getKeywords(), WorkflowConstants.STATUS_ANY, userParams));
					searchContainer.setResults(UserLocalServiceUtil.search(themeDisplay.getCompanyId(), searchTerms.getKeywords(), WorkflowConstants.STATUS_ANY, userParams, searchContainer.getStart(), searchContainer.getEnd(), new UserFirstNameComparator(true)));
				}
				else {
					searchContainer.setTotal(UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), firstName, middleName, lastName, screenName, emailAddress, WorkflowConstants.STATUS_ANY, userParams, true));
					searchContainer.setResults(UserLocalServiceUtil.search(themeDisplay.getCompanyId(), firstName, middleName, lastName, screenName, emailAddress, WorkflowConstants.STATUS_ANY, userParams, true, searchContainer.getStart(), searchContainer.getEnd(), new UserFirstNameComparator(true)));
				}
				%>

				<liferay-ui:search-container-row
					className="com.liferay.portal.kernel.model.User"
					escapedModel="<%= true %>"
					keyProperty="userId"
					modelVar="curUser"
				>

					<%
					TicketWorker ticketWorker = TicketWorkerLocalServiceUtil.fetchTicketWorker(curUser.getUserId(), ticketEntryId);

					if (!curUser.isActive()) {
						row.setClassName("inactive");
					}
					%>

					<liferay-ui:search-container-column-text
						name="name"
						property="fullName"
					/>

					<liferay-ui:search-container-column-text
						name="screen-name"
						property="screenName"
					/>

					<liferay-ui:search-container-column-text
						name="email-address"
						property="emailAddress"
					/>

					<liferay-ui:search-container-column-text
						name="role"
					>

						<%
						int role = 0;

						if (ticketWorker != null) {
							role = ticketWorker.getRole();
						}
						%>

						<select <%= curUser.isActive() ? "" : "disabled" %> name="<portlet:namespace />role_<%= curUser.getUserId() %>">
							<option></option>

							<%
							for (int i = 1; i <= 3; i++) {
							%>

								<option <%= (role == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, TicketWorkerConstants.getRoleLabel(i)) %></option>

							<%
							}
							%>

						</select>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="primary"
					>
						<input <%= ((ticketWorker != null) && ticketWorker.isPrimary()) ? "checked" : "" %> name="<portlet:namespace />primaryUserId" type="radio" value="<%= curUser.getUserId() %>" />
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<div class="separator"><!-- --></div>

				<input class="aui-button-input" onClick="<portlet:namespace />updateTicketWorkers('<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>');" type="button" value="<liferay-ui:message key="update-associations" />" />

				<input class="aui-button-input pull-right" onClick="<portlet:namespace />resetPrimaryUserRadios();" type="button" value="<liferay-ui:message key="use-last-primary-assignee" />" />

				<br /><br />

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</aui:form>

	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />resetPrimaryUserRadios',
			function() {
				var A = AUI();

				var radios = A.all('input[name^=<portlet:namespace />primaryUserId]');

				radios.each(
					function(item) {
						if (item.get('checked')) {
							item.set('checked', false);
						}
					}
				)
			},
			['aui-base']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />updateTicketWorkers',
			function(assignmentsRedirect) {
				var A = AUI();

				var form = A.one('#<portlet:namespace />fm');

				if (form) {
					var assignmentsRedirect = form.one('#<portlet:namespace />assignmentsRedirect');

					if (assignmentsRedirect) {
						assignmentsRedirect.val(assignmentsRedirect);
					}

					var addUserIds = form.one('#<portlet:namespace />addUserIds');

					if (addUserIds) {
						addUserIds.val(Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds'));
					}

					var removeUserIds = form.one('#<portlet:namespace />removeUserIds');

					if (removeUserIds) {
						removeUserIds.val(Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds'));
					}

					submitForm(form, '<portlet:actionURL name="updateTicketWorkers"><portlet:param name="mvcPath" value="/support/2/edit_ticket_entry/edit_ticket_workers.jsp" /><portlet:param name="tabs2" value="<%= tabs2 %>" /></portlet:actionURL>'');
				}
			},
			['aui-base', 'liferay-util-list-fields']
		);
	</aui:script>
</c:if>