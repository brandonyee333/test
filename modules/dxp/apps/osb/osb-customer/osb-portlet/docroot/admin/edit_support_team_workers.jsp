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

<%@ include file="/init.jsp" %>

<%
String tabs3 = ParamUtil.getString(request, "tabs3", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

long supportTeamId = ParamUtil.getLong(request, "supportTeamId");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/admin/edit_support_team.jsp");
	portletURL.setParameter("supportTeamId", String.valueOf(supportTeamId));

	redirect = portletURL.toString();
}

SupportTeam supportTeam = SupportTeamLocalServiceUtil.getSupportTeam(supportTeamId);

boolean hasClockInOutPermission = OSBSupportTeamPermission.contains(permissionChecker, supportTeam);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_support_team_workers.jsp");
portletURL.setParameter("tabs3", tabs3);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("supportTeamId", String.valueOf(supportTeamId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />supportTeamId" type="hidden" value="<%= supportTeamId %>" />

	<liferay-ui:message arguments="<%= HtmlUtil.escape(supportTeam.getName()) %>" key="edit-workers-for-support-team-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="users"
	/>

	<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />
	<liferay-ui:error exception="<%= SupportWorkerMaxWorkException.class %>" message="max-work-cannot-equal-0" />

	<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
	<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />

	<liferay-ui:tabs
		names="current,available"
		param="tabs3"
		url="<%= portletURL.toString() %>"
	/>

	<%@ include file="/common/user_search_inputs.jspf" %>

	<%
	LinkedHashMap userParams = new LinkedHashMap();

	if (tabs3.equals("current")) {
		userParams.put("usersSupportWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinBySupportWorkerTeam"), Long.valueOf(supportTeam.getSupportTeamId())));
	}
	%>

	<liferay-ui:search-container
		emptyResultsMessage="no-users-were-found"
		id="usersSearchContainer"
		iteratorURL="<%= portletURL %>"
		rowChecker="<%= new UserSupportWorkerChecker(renderResponse, supportTeam) %>"
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
			SupportWorker supportWorker = null;

			boolean autoAssign = false;
			double assignedWork = 0;
			double maxWork = 1;
			int escalationLevel = 0;
			int role = 0;
			int notifications = 0;

			try {
				supportWorker = SupportWorkerLocalServiceUtil.getSupportWorker(curUser.getUserId(), supportTeamId);

				autoAssign = supportWorker.getAutoAssign();
				assignedWork = supportWorker.getAssignedWork();
				maxWork = supportWorker.getMaxWork();
				escalationLevel = supportWorker.getEscalationLevel();
				role = supportWorker.getRole();
				notifications = supportWorker.getNotifications();
			}
			catch (Exception e) {
				assignedWork = SupportWorkerLocalServiceUtil.getAssignedWork(curUser.getUserId());
			}

			if (!curUser.isActive()) {
				row.setClassName("inactive");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
				property="emailAddress"
			/>

			<c:choose>
				<c:when test='<%= tabs3.equals("current") %>'>
					<liferay-ui:search-container-column-text
						name="status"
					>
						<c:if test="<%= supportWorker.getRole() != SupportWorkerConstants.ROLE_WATCHER %>">
							<c:choose>
								<c:when test="<%= hasClockInOutPermission %>">
									<portlet:actionURL name="clockInOut" var="clockInOutURL">
										<portlet:param name="mvcPath" value="/admin/edit_support_team_workers.jsp" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="supportTeamId" value="<%= StringUtil.valueOf(supportTeamId) %>" />
										<portlet:param name="supportWorkerId" value="<%= StringUtil.valueOf(supportWorker.getSupportWorkerId()) %>" />
									</portlet:actionURL>

									<div class="toggle-on-off-switch">
										<a class="toggle-on-off-switch-ctrl unlock <%= supportWorker.isClockedIn() ? "on" : "off" %>" href="<%= clockInOutURL %>">
											<span class="toggle-on-off-switch-inner">
												<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="in" /></span>

												<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="on-pto" /></span>
											</span>
										</a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="toggle-on-off-switch">
										<span class="toggle-on-off-switch-ctrl <%= supportWorker.isClockedIn() ? "on" : "off" %>">
											<span class="toggle-on-off-switch-inner">
												<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="in" /></span>

												<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="on-pto" /></span>
											</span>
										</span>
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="auto-assign"
					>
						<c:choose>
							<c:when test="<%= autoAssign %>">
								<liferay-ui:message key="yes" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="no" />
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="utilization"
					>
						<%= numberFormat.format(MathUtil.format((assignedWork / maxWork) * 100, 1, 1)) %>%

						(<%= numberFormat.format(assignedWork) %>/<%= numberFormat.format(maxWork) %>)
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="severities"
					>

						<%
						List<Integer> severities = supportWorker.getSeverities();

						for (int i = 0; i < severities.size(); i++) {
							Integer severity = severities.get(i);
						%>

							<%= LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(severity)) %><%= ((i + 1) < severities.size()) ? "<br />" : "" %>

						<%
						}
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="components"
					>

						<%
						List<Integer> components = supportWorker.getComponents();

						for (int i = 0; i < components.size(); i++) {
							Integer component = components.get(i);
						%>

							<%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(component)) %><%= ((i + 1) < components.size()) ? "<br />" : "" %>

						<%
						}
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="project-tiers"
					>

						<%
						List<Integer> accountTiers = supportWorker.getAccountTiers();

						for (int i = 0; i < accountTiers.size(); i++) {
							Integer accountTier = accountTiers.get(i);
						%>

							<%= LanguageUtil.get(request, AccountEntryConstants.getTierLabel(accountTier)) %><%= ((i + 1) < accountTiers.size()) ? "<br />" : "" %>

						<%
						}
						%>

					</liferay-ui:search-container-column-text>

					<input name="<portlet:namespace />maxWork_<%= curUser.getUserId() %>" type="hidden" value="<%= maxWork %>" />
					<input name="<portlet:namespace />notifications_<%= curUser.getUserId() %>" type="hidden" value="<%= notifications %>" />
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-column-text
						name="assigned-work"
					>
						<%= assignedWork %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="max-work"
					>
						<aui:input disabled="<%= supportWorker != null %>" label="" name='<%= "maxWork_" + curUser.getUserId() %>' size="5" type="text" value="<%= maxWork %>" />
					</liferay-ui:search-container-column-text>
				</c:otherwise>
			</c:choose>

			<liferay-ui:search-container-column-text
				name="notifications"
			>
				<aui:select label="" name='<%= "notifications_" + curUser.getUserId() %>'>

					<%
					for (int i = 1; i <= 3; i++) {
					%>

						<option <%= (notifications == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, SupportWorkerConstants.getNotificationsLabel(i)) %></option>

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="escalation-level"
			>
				<aui:select label="" name='<%= "escalationLevel_" + curUser.getUserId() %>'>

					<%
					List<ListType> escalationLevelTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL);

					for (ListType escalationLevelType : escalationLevelTypes) {
					%>

						<option <%= (escalationLevelType.getListTypeId() == escalationLevel) ? "selected" : "" %> value="<%= escalationLevelType.getListTypeId() %>"><%= LanguageUtil.get(request, escalationLevelType.getName()) %></option>

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<aui:select disabled="<%= !curUser.isActive() %>" label="" name='<%= "role_" + curUser.getUserId() %>'>
					<option></option>

					<%
					for (int i = 1; i <= 4; i++) {
					%>

						<option <%= (role == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, SupportWorkerConstants.getRoleLabel(i)) %></option>

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<c:if test='<%= tabs3.equals("current") && (supportWorker != null) %>'>
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu>
						<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/admin/edit_support_worker.jsp" />
							<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<portlet:param name="supportWorkerId" value="<%= String.valueOf(supportWorker.getSupportWorkerId()) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							message="edit"
							url="<%= editURL %>"
						/>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<aui:button onClick='<%= renderResponse.getNamespace() + "updateSupportWorkers('" + portletURL.toString() + "&" + renderResponse.getNamespace() + "cur=" + cur + "');" %>' value="update-associations" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateSupportWorkers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateSupportWorkers"><portlet:param name="mvcPath" value="/admin/edit_support_team_workers.jsp" /><portlet:param name="tabs3" value="<%= tabs3 %>" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>