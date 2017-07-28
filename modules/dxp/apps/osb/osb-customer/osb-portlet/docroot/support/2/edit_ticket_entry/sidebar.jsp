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
boolean hasUpdateAdmin = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdmin");
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_ticket_entry.jsp-accountEntry");
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("edit_ticket_entry.jsp-offeringEntry");
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry.jsp-productEntry");
SupportResponse supportResponse = (SupportResponse)request.getAttribute("edit_ticket_entry.jsp-supportResponse");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");

long supportRegionId = BeanParamUtil.getLong(ticketEntry, request, "supportRegionId");
int component = BeanParamUtil.getInteger(ticketEntry, request, "component");
%>

<div class="sidebar">
	<div class="section sidebar-account-detail">
		<c:choose>
			<c:when test="<%= ticketWorker %>">

				<%
				PortletURL accountEntryURL = renderResponse.createRenderURL();

				accountEntryURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
				accountEntryURL.setParameter("mvcPath", "/support/2/edit_account_entry.jsp");
				accountEntryURL.setParameter("redirect", portletURL.toString());
				accountEntryURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
				%>

				<h2 class="txt-sb">
					<a href="<%= accountEntryURL.toString() %>" id="<portlet:namespace />accountEntryName" target="_blank"><%= HtmlUtil.escape(accountEntry.getName()) %></a>
				</h2>
			</c:when>
			<c:otherwise>
				<h2 class="txt-sb">
					<%= HtmlUtil.escape(accountEntry.getName()) %>
				</h2>
			</c:otherwise>
		</c:choose>

		<div id="<portlet:namespace />supportResponseName">
			<c:if test="<%= supportResponse.getSupportLevel() != SupportResponseConstants.SUPPORT_LEVEL_FLOATING %>">
				<%= HtmlUtil.escape(supportResponse.getName()) %>
			</c:if>
		</div>

		<div id="<portlet:namespace />productEntryName">
			<%= HtmlUtil.escape(productEntry.getName()) %>
		</div>

		<c:if test="<%= ticketWorker %>">
			<div>

				<%
				String supportRegionName = StringPool.BLANK;

				try {
					SupportRegion supportRegion = SupportRegionLocalServiceUtil.getSupportRegion(supportRegionId);

					supportRegionName = supportRegion.getName();
				}
				catch (NoSuchSupportRegionException nssre) {
				}
				%>

				<liferay-ui:message arguments="<%= HtmlUtil.escape(supportRegionName) %>" key="x-support-region" />
			</div>
		</c:if>

		<div>
			<%= LanguageUtil.get(pageContext, ticketEntry.getLanguageLabel()) %>
		</div>

		<c:if test="<%= !screenShareMode && liferayIncOrg %>">
			<div>
				<liferay-ui:message arguments="<%= AccountEntryConstants.getTierLabel(accountEntry.getTier()) %>" key="x-project" translateArguments="<%= true %>" />
			</div>
		</c:if>

		<c:if test="<%= (accountEntry.getStatus() == WorkflowConstants.STATUS_CLOSED) || (accountEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) %>">
			<span class="expired-text"><liferay-ui:message key="expired" /></span>
		</c:if>

		<%
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		params.put("accountEntryIds", new long[] {accountEntry.getAccountEntryId()});

		int accountEntryOpenTickets = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);

		PortletURL accountEntryTicketSearchURL = renderResponse.createRenderURL();

		accountEntryTicketSearchURL.setParameter(ActionRequest.ACTION_NAME, "search");
		accountEntryTicketSearchURL.setParameter("mvcPath", "/support/2/advanced_search.jsp");
		accountEntryTicketSearchURL.setParameter("accountEntryIds", String.valueOf(accountEntry.getAccountEntryId()));
		accountEntryTicketSearchURL.setParameter("statuses", ArrayUtil.toStringArray(TicketEntryConstants.STATUSES_OPEN));
		%>

		<div class="sidebar-account-detail sub-section txt-up">
			<a href="<%= accountEntryTicketSearchURL.toString() %>" target="_blank"><liferay-ui:message arguments="<%= accountEntryOpenTickets %>" key='<%= (accountEntryOpenTickets == 1) ? "x-ticket-open" : "x-tickets-open" %>' /></a>
		</div>
	</div>

	<div class="last section">
		<div class="heading">
			<liferay-ui:message key="ticket-details" />
		</div>

		<div class="sub-section">
			<div>
				<liferay-ui:message key="created" />:

				<span class="txt-sb" title="<%= longDateFormatDateTime.format(ticketEntry.getCreateDate()) %>"><%= mediumDateFormatDate.format(ticketEntry.getCreateDate()) %></span>
			</div>

			<div>
				<liferay-ui:message key="updated" />:

				<span class="txt-sb" title="<%= longDateFormatDateTime.format(ticketEntry.getModifiedDate()) %>"><%= mediumDateFormatDate.format(ticketEntry.getModifiedDate()) %></span>
			</div>

			<c:if test="<%= !screenShareMode && ticketWorker %>">
				<div>
					<liferay-ui:message key="due" />:

					<%
					Calendar dueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

					if (ticketEntry.getDueDate() != null) {
						dueDate.setTime(ticketEntry.getDueDate());
					}
					%>

					<span class="txt-sb" title="<%= longDateFormatDateTime.format(dueDate.getTime()) %>"><%= mediumDateFormatDate.format(dueDate.getTime()) %></span>

					<c:if test="<%= hasUpdateAdmin && ticketEntry.isIgnoreDueDate() %>">
						<span class="ignore-due-date">(<liferay-ui:message key="ignored" />)</span>
					</c:if>
				</div>
			</c:if>
		</div>

		<div class="sub-section">
			<div>
				<div>
					<liferay-ui:message key="reporter" />:

					<%
					User reporterUser = UserLocalServiceUtil.fetchUserById(ticketEntry.getUserId());
					%>

					<span class="txt-sb" id="<portlet:namespace />reportedByUserName">
						<c:if test="<%= reporterUser != null %>">
							<%= HtmlUtil.escape(reporterUser.getFullName()) %>

							<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
								<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
								<portlet:param name="userId" value="<%= String.valueOf(reporterUser.getUserId()) %>" />
							</liferay-util:include>
						</c:if>
					</span>
				</div>

				<div>
					<liferay-ui:message key="assignee" />:

					<%
					TicketWorker primaryTicketWorker = TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(ticketEntry.getTicketEntryId());

					User assigneeUser = null;

					if (primaryTicketWorker != null) {
						assigneeUser = UserLocalServiceUtil.fetchUserById(primaryTicketWorker.getUserId());
					}
					%>

					<span class="txt-sb">
						<c:if test="<%= assigneeUser != null %>">
							<%= HtmlUtil.escape(assigneeUser.getFullName()) %>

							<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
								<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
								<portlet:param name="userId" value="<%= String.valueOf(assigneeUser.getUserId()) %>" />
							</liferay-util:include>
						</c:if>
					</span>
				</div>

				<%
				String otherAssignees = SupportUtil.getOtherAssigneesUserNames(ticketEntry.getTicketEntryId());
				%>

				<c:if test="<%= Validator.isNotNull(otherAssignees) %>">
					<div>
						<liferay-ui:message key="helpers" />:

						<span class="txt-sb"><%= otherAssignees %></span>
					</div>
				</c:if>

				<%
				String advocacySpecialists = SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST);
				%>

				<c:if test="<%= Validator.isNotNull(advocacySpecialists) %>">
					<div>
						<liferay-ui:message key="cas" />:

						<span class="txt-sb"><%= advocacySpecialists %></span>
					</div>
				</c:if>

				<%
				String salesReps = SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_SALES);
				%>

				<c:if test="<%= Validator.isNotNull(salesReps) %>">
					<div>
						<liferay-ui:message key="sales" />:

						<span class="txt-sb"><%= salesReps %></span>
					</div>
				</c:if>
			</div>
		</div>

		<%
		Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

		String envName = ParamUtil.getString(request, "envName", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_NAME)));
		int envOS = ParamUtil.getInteger(request, "envOS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS)));
		String envOSCustom = ParamUtil.getString(request, "envOSCustom", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS_CUSTOM)));
		int envDB = ParamUtil.getInteger(request, "envDB", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_DB)));
		int envJVM = ParamUtil.getInteger(request, "envJVM", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_JVM)));
		int envAS = ParamUtil.getInteger(request, "envAS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_AS)));
		int envLFR = ParamUtil.getInteger(request, "envLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_LFR)));
		int envBrowser = ParamUtil.getInteger(request, "envBrowser", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER)));
		String envBrowserCustom = ParamUtil.getString(request, "envBrowserCustom", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM)));
		int toEnvLFR = ParamUtil.getInteger(request, "toEnvLFR", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_UPGRADE_TO_ENV_LFR)));
		int envCS = ParamUtil.getInteger(request, "envCS", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_CS)));
		String envSearch = ParamUtil.getString(request, "envSearch", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_ENV_SEARCH)));

		String supportPhaseLabel = StringPool.BLANK;

		if (toEnvLFR > 0) {
			supportPhaseLabel = ProductEntryConstants.getSupportPhaseLabel(toEnvLFR);
		}
		else if (envLFR > 0) {
			supportPhaseLabel = ProductEntryConstants.getSupportPhaseLabel(envLFR);
		}
		%>

		<div class="last sub-section">
			<c:if test="<%= envLFR != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="lr" />:</span>

					<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envLFR)) %></span>

					<c:if test="<%= component == TicketEntryConstants.COMPONENT_UPGRADE %>">
						<liferay-ui:message key="to" />

						<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(toEnvLFR)) %></span>
					</c:if>

					<c:if test="<%= Validator.isNotNull(supportPhaseLabel) %>">
						<span class="support-phase-label">(<%= LanguageUtil.get(pageContext, supportPhaseLabel) %>)</span>
					</c:if>
				</div>
			</c:if>

			<c:if test="<%= envOS != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="os" />:</span>

					<c:choose>
						<c:when test="<%= envOS == TicketEntryConstants.ENV_OS_OTHER %>">
							<span class="txt-sb"><%= HtmlUtil.escape(envOSCustom) %></span>
						</c:when>
						<c:otherwise>
							<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envOS)) %></span>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

			<c:if test="<%= envAS != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="as" />:</span>

					<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envAS)) %></span>
				</div>
			</c:if>

			<c:if test="<%= envJVM != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="jvm" />:</span>

					<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envJVM)) %></span>
				</div>
			</c:if>

			<c:if test="<%= envDB != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="db" />:</span>

					<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envDB)) %></span>
				</div>
			</c:if>

			<c:if test="<%= envBrowser != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="br" />:</span>

					<c:choose>
						<c:when test="<%= envBrowser == TicketEntryConstants.ENV_BROWSER_OTHER %>">
							<span class="txt-sb"><%= HtmlUtil.escape(envBrowserCustom) %></span>
						</c:when>
						<c:otherwise>
							<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envBrowser)) %></span>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

			<c:if test="<%= envCS != 0 %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="cs" />:</span>

					<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(envCS)) %></span>
				</div>
			</c:if>

			<c:if test="<%= Validator.isNotNull(envSearch) %>">
				<div>
					<span class="txt-up"><liferay-ui:message key="sh" />:</span>

					<%
					int[] envSearchArray = StringUtil.split(envSearch, StringPool.NEW_LINE, 0);

					for (int curEnvSearch : envSearchArray) {
					%>

						<span class="txt-sb"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getEnvLabel(curEnvSearch)) %></span>

					<%
					}
					%>

				</div>
			</c:if>
		</div>
	</div>
</div>

<%
String tierMessage = HtmlUtil.escapeJS(SupportUtil.getPreferenceValue(locale, "tierMessage_" + accountEntry.getTier()));
%>

<c:if test="<%= Validator.isNotNull(tierMessage) %>">
	<aui:script use="aui-tooltip">
		new A.Tooltip(
			{
				bodyContent: '<%= tierMessage %>',
				trigger: '#<portlet:namespace />tierDisplay'
			}
		).render();
	</aui:script>
</c:if>