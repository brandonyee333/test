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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long holidayCalendarId = ParamUtil.getLong(request, "holidayCalendarId");

HolidayCalendar holidayCalendar = null;

try {
	holidayCalendar = HolidayCalendarLocalServiceUtil.getHolidayCalendar(holidayCalendarId);
}
catch (NoSuchHolidayCalendarException nshce) {
}

List<HolidayEntry> holidayEntries = new ArrayList<HolidayEntry>();

if (holidayCalendarId > 0) {
	holidayEntries = HolidayEntryLocalServiceUtil.getHolidayEntries(holidayCalendarId);
}

int holidayEntriesIndexCount = holidayEntries.size();

if (holidayEntriesIndexCount == 0) {
	holidayEntriesIndexCount = 1;
}

String[] holidayEntriesIndexes = new String[holidayEntriesIndexCount];
%>

<portlet:actionURL name="updateHolidayCalendar" var="updateHolidayCalendarURL">
	<portlet:param name="mvcPath" value="/admin/edit_holiday_calendar.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateHolidayCalendarURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="holidayCalendarId" type="hidden" value="<%= holidayCalendarId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="holiday-calendar"
	/>

	<liferay-ui:error exception="<%= HolidayCalendarNameException.class %>" message="please-enter-a-name" />

	<aui:model-context bean="<%= holidayCalendar %>" model="<%= HolidayCalendar.class %>" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<aui:input label="" name="name" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="description" />
			</td>
			<td>
				<aui:input label="" name="description" />
			</td>
		</tr>
	</table>

	<br />

	<div>
		<aui:button type="submit" value="save" />

		<c:if test="<%= holidayCalendar != null %>">
			<portlet:renderURL var="assignUsersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_holiday_calendar_users.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="holidayCalendarId" value="<%= String.valueOf(holidayCalendar.getHolidayCalendarId()) %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= assignUsersURL %>" label="assign-users" />
		</c:if>

		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
	</div>

	<br />

	<liferay-ui:tabs
		names="holidays"
	/>

	<div id="<portlet:namespace />holidayEntries">
		<liferay-ui:error exception="<%= DuplicateHolidayEntryException.class %>" message="you-have-entered-two-holidays-with-the-same-start-and-end-dates" />
		<liferay-ui:error exception="<%= HolidayEntryDateException.class %>" message="please-supply-a-valid-date" />
		<liferay-ui:error exception="<%= HolidayEntryStartDateLaterThanEndDateException.class %>" message="holiday-end-date-must-be-after-start-date" />

		<aui:fieldset>

			<%
			for (int i = 0; i < holidayEntriesIndexCount; i++) {
				String index = String.valueOf(i);

				holidayEntriesIndexes[i] = index;
			%>

				<div class="lfr-form-row lfr-form-row-inline">
					<div class="row-fields">
						<liferay-util:include page="/admin/holiday_entry.jsp" servletContext="<%= application %>">
							<liferay-util:param name="holidayCalendarId" value="<%= String.valueOf(holidayCalendarId) %>" />
							<liferay-util:param name="index" value="<%= index %>" />
						</liferay-util:include>
					</div>
				</div>

			<%
			}
			%>

		</aui:fieldset>
	</div>

	<aui:input name="holidayEntriesIndexes" type="hidden" value="<%= StringUtil.merge(holidayEntriesIndexes) %>" />
</aui:form>

<aui:script use="liferay-auto-fields">
	new Liferay.AutoFields(
		{
			contentBox: '#<portlet:namespace />holidayEntries > fieldset',
			fieldIndexes: '<portlet:namespace />holidayEntriesIndexes',
			namespace: '<portlet:namespace />',
			url: '<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_ADMIN %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><liferay-portlet:param name="mvcPath" value="/admin/holiday_entry.jsp" /></liferay-portlet:renderURL>',
			urlNamespace: '<%= "_" + OSBPortletKeys.OSB_ADMIN + "_" %>'
		}
	).render();
</aui:script>