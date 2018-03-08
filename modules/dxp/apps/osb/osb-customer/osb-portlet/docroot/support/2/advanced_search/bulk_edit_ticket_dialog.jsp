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

<portlet:actionURL name="updateTicketEntries" var="updateTicketEntriesURL">
	<portlet:param name="mvcPath" value="/support/2/advanced_search.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTicketEntriesURL %>" enctype="multipart/form-data" method="post" name="updateTicketFm">
	<input id="<portlet:namespace />ticketEntryIds" name="<portlet:namespace />ticketEntryIds" type="hidden" value="" />

	<div class="bulk-edit-dialog">
		<liferay-ui:error exception="<%= TicketEntryAssigneeException.class %>" message="please-select-an-assignee-or-a-support-office" />
		<liferay-ui:error exception="<%= TicketEntryDueDateException.class %>" message="please-select-a-valid-due-date" />

		<div class="portlet-msg-info" id="<portlet:namespace />disclaimer">
			<liferay-ui:message key="you-have-selected-0-tickets-to-edit" />
		</div>

		<div class="hide portlet-msg-error" id="<portlet:namespace />assigneeException">
			<liferay-ui:message key="please-select-an-assignee-or-a-support-office" />
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />assignee"><liferay-ui:message key="assignee" /></label>

			<select name="<portlet:namespace />assignee" onchange="<portlet:namespace />validateAssigneeSupportRegion();">
				<option value="0"></option>

				<%
				LinkedHashMap params = new LinkedHashMap();

				params.put("usersSupportWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinBySupportWorker"), StringPool.BLANK));

				List<User> users = UserLocalServiceUtil.search(company.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserFirstNameComparator(true));

				for (User curUser : users) {
					if (!OrganizationLocalServiceUtil.hasUserOrganization(curUser.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {
						continue;
					}
				%>

					<option value="<%= curUser.getUserId() %>"><%= HtmlUtil.escape(curUser.getFullName()) %> (<%= curUser.getEmailAddress() %>)</option>

				<%
				}
				%>

			</select>
		</div>

		<div class="field-group">
			<label><liferay-ui:message key="or" /></label>
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />supportRegionIdLabel"><liferay-ui:message key="support-region" /></label>

			<select name="<portlet:namespace />supportRegionId" onchange="<portlet:namespace />validateAssigneeSupportRegion();">
				<option value="0"></option>

				<%
				List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				for (SupportRegion supportRegion : supportRegions) {
					String name = supportRegion.getName();

					if (name.equals("N/A")) {
						continue;
					}
				%>

					<option value="<%= supportRegion.getSupportRegionId() %>"><%= HtmlUtil.escape(supportRegion.getName()) %></option>

				<%
				}
				%>

			</select>
		</div>

		<br />

		<div class="hide portlet-msg-error" id="<portlet:namespace />dueDateException">
			<liferay-ui:message key="please-select-a-valid-due-date" />
		</div>

		<div class="field-group">
			<label id="<portlet:namespace />dueDateLabel"><liferay-ui:message key="due-date" /></label>

			<%
			Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

			Date firstEnabledDate = calendar.getTime();

			calendar.add(Calendar.YEAR, 4);

			Date lastEnabledDate = calendar.getTime();
			%>

			<liferay-ui:input-date
				dayParam="dueDateDay"
				firstEnabledDate="<%= firstEnabledDate %>"
				lastEnabledDate="<%= lastEnabledDate %>"
				monthParam="dueDateMonth"
				nullable="<%= true %>"
				yearParam="dueDateYear"
			/>

			<liferay-util:include page="/common/input_time.jsp" servletContext="<%= application %>">
				<liferay-util:param name="hourNullable" value="<%= Boolean.TRUE.toString() %>" />
				<liferay-util:param name="hourParam" value='<%= renderResponse.getNamespace() + "dueDateHour" %>' />
				<liferay-util:param name="minuteNullable" value="<%= Boolean.TRUE.toString() %>" />
				<liferay-util:param name="minuteParam" value='<%= renderResponse.getNamespace() + "dueDateMinute" %>' />
				<liferay-util:param name="amPmNullable" value="<%= Boolean.TRUE.toString() %>" />
				<liferay-util:param name="amPmParam" value='<%= renderResponse.getNamespace() + "dueDateAmPm" %>' />
			</liferay-util:include>
		</div>

		<div align="right">
			<input class="aui-button-input" onClick="<portlet:namespace />submit();" type="button" value="<liferay-ui:message key="save" />" />

			<input class="aui-button-input pull-left" onClick="<portlet:namespace />closeDialog(1);" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />submit() {
		if (<portlet:namespace />validate()) {
			submitForm(document.<portlet:namespace />updateTicketFm);
		}
	}

	function <portlet:namespace />validate() {
		if (<portlet:namespace />validateAssigneeSupportRegion() && <portlet:namespace />validateDueDate()) {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-make-these-changes" unicode="<%= true %>" />')) {
				return true;
			}
		}

		return false;
	}

	function <portlet:namespace />validateAssigneeSupportRegion() {
		var assignee = document.getElementsByName('<portlet:namespace />assignee')[0];
		var supportRegionId = document.getElementsByName('<portlet:namespace />supportRegionId')[0];

		var assigneeException = document.getElementById('<portlet:namespace />assigneeException');

		if (((assignee.value != 0) && (supportRegionId.value != 0)) || ((assignee.value == 0) && (supportRegionId.value == 0))) {
			assigneeException.classList.remove('hide');

			return false;
		}
		else if (!assigneeException.classList.contains('hide')) {
			assigneeException.classList.add('hide');
		}

		return true;
	}

	function <portlet:namespace />validateDueDate() {
		var dueDateMonth = document.getElementById('<portlet:namespace />dueDateMonth').value;
		var dueDateDay = document.getElementById('<portlet:namespace />dueDateDay').value;
		var dueDateYear = document.getElementById('<portlet:namespace />dueDateYear').value;
		var dueDateHour = document.getElementsByName('<portlet:namespace />dueDateHour')[0].value;
		var dueDateMinute = document.getElementsByName('<portlet:namespace />dueDateMinute')[0].value;
		var dueDateAmPm = document.getElementsByName('<portlet:namespace />dueDateAmPm')[0].value;

		if (dueDateAmPm == 1) {
			dueDateHour = +dueDateHour + +12;
		}

		var dueDateException = document.getElementById('<portlet:namespace />dueDateException');

		if ((dueDateMonth != -1) && (dueDateDay != -1) && (dueDateYear != -1) && dueDateHour && dueDateMinute && dueDateAmPm) {
			var dueDate = new Date(dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute, 0, 0);
			var now = new Date();

			if (dueDate <= now) {
				dueDateException.classList.remove('hide');

				return false;
			}
			else if (!dueDateException.classList.contains('hide')) {
				dueDateException.classList.add('hide');
			}
		}
		else if ((!dueDateMonth || (dueDateMonth == -1)) && (!dueDateDay || (dueDateDay == -1)) && (!dueDateYear || (dueDateYear == -1)) && !dueDateHour && !dueDateMinute && !dueDateAmPm) {
			if (!dueDateException.classList.contains('hide')) {
				dueDateException.classList.add('hide');
			}
		}
		else {
			dueDateException.classList.remove('hide');

			return false;
		}

		return true;
	}
</aui:script>