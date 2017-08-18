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
TicketFeedbackDisplayTerms displayTerms = new TicketFeedbackDisplayTerms(renderRequest);
%>

<div class="advanced-search feedback">
	<div>
		<span class="assignee search-param" onClick="<portlet:namespace />toggleSelected(this);" searchParam="assignee">
			<liferay-ui:message key="assignee" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="date search-param" onClick="<portlet:namespace />toggleSelected(this);" searchParam="date">
			<liferay-ui:message key="date" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
	</div>

	<div class="search-param-config">
		<div class="assignee search-param-dropdown" id="<portlet:namespace />assignee">
			<input id="<portlet:namespace />assignedUserIds" name="<portlet:namespace /><%= displayTerms.ASSIGNED_USER_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAssignedUserIds()) %>" />
			<input id="<portlet:namespace />supportTeamIds" name="<portlet:namespace /><%= displayTerms.ASSIGNED_SUPPORT_TEAM_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAssignedSupportTeamIds()) %>" />
			<input id="<portlet:namespace />partnerEntryIds" name="<portlet:namespace /><%= displayTerms.ASSIGNED_PARTNER_ENTRY_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAssignedPartnerEntryIds()) %>" />

			<div>
				<input class="aui-button-input" onClick="window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/select_assigned_to.jsp" /></portlet:renderURL>', 'assigned_to', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');" type="button" value="<liferay-ui:message key="choose-assignee" />" />
			</div>

			<span class="list" id="<portlet:namespace />assignedUserIdsList">

				<%
				for (long assignedUserId : displayTerms.getAssignedUserIds()) {
					User curUser = UserLocalServiceUtil.getUser(assignedUserId);
				%>

					<span id="<%= assignedUserId %>" onClick="<portlet:namespace />removeAssignedTo(this, 'assignedUserId');"><%= HtmlUtil.escape(curUser.getFullName()) %></span>

				<%
				}
				%>

			</span>
			<span class="list" id="<portlet:namespace />supportTeamIdsList">

				<%
				for (long assignedSupportTeamId : displayTerms.getAssignedSupportTeamIds()) {
					SupportTeam curSupportTeam = SupportTeamLocalServiceUtil.getSupportTeam(assignedSupportTeamId);
				%>

					<span id="<%= assignedSupportTeamId %>" onClick="<portlet:namespace />removeAssignedTo(this, 'supportTeamId');"><%= HtmlUtil.escape(curSupportTeam.getName()) %></span>

				<%
				}
				%>

			</span>
			<span class="list" id="<portlet:namespace />partnerEntryIdsList">

				<%
				for (long assignedPartnerEntryId : displayTerms.getAssignedPartnerEntryIds()) {
					PartnerEntry curPartnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(assignedPartnerEntryId);
				%>

					<span id="<%= assignedPartnerEntryId %>" onClick="<portlet:namespace />removeAssignedTo(this, 'partnerEntryId');"><%= HtmlUtil.escape(curPartnerEntry.getCode()) %></span>

				<%
				}
				%>

			</span>
		</div>

		<%
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, 2006);

		Date firstEnabledDate = calendar.getTime();

		calendar = Calendar.getInstance();

		calendar.add(Calendar.YEAR, 1);

		Date lastEnabledDate = calendar.getTime();
		%>

		<div class="date search-param-dropdown" id="<portlet:namespace />date">
			<h2 class="support-input-heading">
				<liferay-ui:message key="created-between" />
			</h2>

			<div class="aui-w100">
				<liferay-ui:message key="begin-date" />

				<liferay-ui:input-date
					cssClass="aui-w100"
					dayParam="<%= displayTerms.CREATE_DATE_GT_DAY %>"
					dayValue="<%= displayTerms.getCreateDateGTDay() %>"
					firstEnabledDate="<%= firstEnabledDate %>"
					lastEnabledDate="<%= lastEnabledDate %>"
					monthParam="<%= displayTerms.CREATE_DATE_GT_MONTH %>"
					monthValue="<%= displayTerms.getCreateDateGTMonth() %>"
					nullable="<%= true %>"
					yearParam="<%= displayTerms.CREATE_DATE_GT_YEAR %>"
					yearValue="<%= displayTerms.getCreateDateGTYear() %>"
				/>
			</div>

			<div class="aui-w100">
				<liferay-ui:message key="end-date" />

				<liferay-ui:input-date
					cssClass="aui-w100"
					dayParam="<%= displayTerms.CREATE_DATE_LT_DAY %>"
					dayValue="<%= displayTerms.getCreateDateLTDay() %>"
					firstEnabledDate="<%= firstEnabledDate %>"
					lastEnabledDate="<%= lastEnabledDate %>"
					monthParam="<%= displayTerms.CREATE_DATE_LT_MONTH %>"
					monthValue="<%= displayTerms.getCreateDateLTMonth() %>"
					nullable="<%= true %>"
					yearParam="<%= displayTerms.CREATE_DATE_LT_YEAR %>"
					yearValue="<%= displayTerms.getCreateDateLTYear() %>"
				/>
			</div>
		</div>
	</div>
</div>

<div class="search-results">
	<div id="<portlet:namespace />advancedSearchResultsContent">
	</div>
</div>