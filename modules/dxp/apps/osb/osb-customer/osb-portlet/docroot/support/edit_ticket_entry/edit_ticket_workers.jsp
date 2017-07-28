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
String tabs2 = ParamUtil.getString(request, "tabs2", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_ticket_entry/edit_ticket_workers.jsp");
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

		<div class="cleared section">
			<div class="fl">
				Edit Workers for Ticket: <%= ticketEntry.getDisplayId() %>
			</div>

			<div class="fr">
				<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
			</div>
		</div>

		<h1 class="section-heading">
			<liferay-ui:message key="users" />
		</h1>

		<div class="callout-a">
			<div class="callout-content">
				<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
				<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />

				<liferay-ui:tabs
					names="current,available"
					param="tabs2"
					url="<%= portletURL.toString() %>"
				/>

				<%
				UserTicketWorkerChecker rowChecker = new UserTicketWorkerChecker(renderResponse, ticketEntry);

				LinkedHashMap userParams = new LinkedHashMap();

				if (tabs2.equals("current")) {
					userParams.put("status", WorkflowConstants.STATUS_ANY);
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

				<liferay-ui:user-search
					portletURL="<%= portletURL %>"
					rowChecker="<%= rowChecker %>"
					userParams="<%= userParams %>"
				>

					<%
					SearchContainer userSearchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);
					%>

					<liferay-ui:search-container
						headerNames="name,screen-name,email-address,role"
						rowChecker="<%= rowChecker %>"
						searchContainer="<%= userSearchContainer %>"
					>
						<liferay-ui:search-container-results
							results="<%= userSearchContainer.getResults() %>"
							total="<%= userSearchContainer.getTotal() %>"
						/>

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

								<select <%= curUser.isActive() ? StringPool.BLANK : "disabled" %> name="<portlet:namespace />role_<%= curUser.getUserId() %>">
									<option></option>

									<%
									for (int i = 1; i <= 3; i++) {
									%>

										<option <%= (role == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, TicketWorkerConstants.getRoleLabel(i)) %></option>

									<%
									}
									%>

								</select>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="primary"
							>
								<input <%= ((ticketWorker != null) && ticketWorker.isPrimary()) ? "checked" : StringPool.BLANK %> name="<portlet:namespace />primaryUserId" type="radio" value="<%= curUser.getUserId() %>" />
							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

						<div class="separator"><!-- --></div>

						<input class="aui-button-input" onClick="<portlet:namespace />updateTicketWorkers('<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>');" type="button" value="<liferay-ui:message key="update-associations" />" />

						<input class="aui-button-input fr" onClick="<portlet:namespace />resetPrimaryUserRadios();" type="button" value="<liferay-ui:message key="use-last-primary-assignee" />" />

						<br /><br />

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</liferay-ui:user-search>
			</div>
		</div>
	</aui:form>

	<aui:script>
		function <portlet:namespace />resetPrimaryUserRadios() {
			var radios = document.getElementsByName("<portlet:namespace />primaryUserId");

			for (var i = 0; i < radios.length; i++) {
				radios[i].checked = false;
			}
		}

		Liferay.provide(
			window,
			'<portlet:namespace />updateTicketWorkers',
			function(assignmentsRedirect) {
				document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
				document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateTicketWorkers"><portlet:param name="mvcPath" value="/support/edit_ticket_entry/edit_ticket_workers.jsp" /><portlet:param name="tabs2" value="<%= tabs2 %>" /></portlet:actionURL>");
			},
			['liferay-util-list-fields']
		);
	</aui:script>
</c:if>