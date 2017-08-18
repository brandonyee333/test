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

int[] holidayEntriesIndexes = new int[0];

if (holidayCalendarId > 0) {
	holidayEntries = HolidayEntryLocalServiceUtil.getHolidayEntries(holidayCalendarId);

	holidayEntriesIndexes = new int[holidayEntries.size()];

	for (int i = 0; i < holidayEntries.size(); i++) {
		holidayEntriesIndexes[i] = i;
	}
}

if (holidayEntries.isEmpty()) {
	holidayEntries.add(new HolidayEntryImpl());

	holidayEntriesIndexes = new int[] {0};
}

Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

Date firstEnabledDate = calendar.getTime();

calendar.add(Calendar.YEAR, 15);

Date lastEnabledDate = calendar.getTime();
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
				<aui:input name="name" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="description" />
			</td>
			<td>
				<aui:input name="description" />
			</td>
		</tr>
	</table>

	<br />

	<div>
		<input type="submit" value="<liferay-ui:message key="save" />" />

		<c:if test="<%= holidayCalendar != null %>">
			<portlet:renderURL var="assignUsersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_holiday_calendar_users.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="holidayCalendarId" value="<%= String.valueOf(holidayCalendar.getHolidayCalendarId()) %>" />
			</portlet:renderURL>

			<input onClick="location.href = '<%= HtmlUtil.escape(assignUsersURL) %>';" type="button" value="<liferay-ui:message key="assign-users" />" />
		</c:if>

		<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
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
			for (int i = 0; i < holidayEntriesIndexes.length; i++) {
				int holidayEntriesIndex = holidayEntriesIndexes[i];

				HolidayEntry holidayEntry = holidayEntries.get(i);

				int holidayEntryStartMonth = 0;
				int holidayEntryStartDay = 0;
				int holidayEntryStartYear = 0;

				Date holidayEntryStartDate = null;
				Date holidayEntryEndDate = null;

				if (holidayEntry.getStartDate() != null) {
					Calendar holidayEntryStartCal = Calendar.getInstance();

					holidayEntryStartCal.setTime(holidayEntry.getStartDate());

					holidayEntryStartDate = holidayEntryStartCal.getTime();

					holidayEntryStartMonth = holidayEntryStartCal.get(Calendar.MONTH);
					holidayEntryStartDay = holidayEntryStartCal.get(Calendar.DATE);
					holidayEntryStartYear = holidayEntryStartCal.get(Calendar.YEAR);
				}

				int holidayEntryEndMonth = 0;
				int holidayEntryEndDay = 0;
				int holidayEntryEndYear = 0;

				if (holidayEntry.getEndDate() != null) {
					Calendar holidayEntryEndCal = Calendar.getInstance();

					holidayEntryEndCal.setTime(holidayEntry.getEndDate());

					holidayEntryEndDate = holidayEntryEndCal.getTime();

					holidayEntryEndMonth = holidayEntryEndCal.get(Calendar.MONTH);
					holidayEntryEndDay = holidayEntryEndCal.get(Calendar.DATE);
					holidayEntryEndYear = holidayEntryEndCal.get(Calendar.YEAR);
				}
			%>

				<aui:model-context bean="<%= holidayEntry %>" model="<%= HolidayEntry.class %>" />

				<div class="lfr-form-row lfr-form-row-inline">
					<div class="row-fields">
						<aui:input name='<%= "holidayEntryId" + holidayEntriesIndex %>' type="hidden" value="<%= holidayEntry.getHolidayEntryId() %>" />

						<aui:input fieldParam='<%= "holidayEntryName" + holidayEntriesIndex %>' id='<%= "holidayEntryName" + holidayEntriesIndex %>' name="name" />

						<aui:input fieldParam='<%= "holidayEntryDescription" + holidayEntriesIndex %>' id='<%= "holidayEntryDescription" + holidayEntriesIndex %>' name="description" />

						<aui:input label="repeat-yearly" name='<%= "holidayEntryRepeatYearly" + holidayEntriesIndex %>' type="checkbox" value="<%= holidayEntry.getRepeatYearly() %>" />

						<br />

						<table class="lfr-table">
							<tr>
								<td>
									<strong><liferay-ui:message key="start-date" /></strong>
								</td>
								<td>
									<strong><liferay-ui:message key="end-date" /></strong>
								</td>
							</tr>
							<tr>
								<td>
									<liferay-ui:input-date
										cssClass="aui-w100"
										dayParam='<%= "holidayEntryStartDay" + holidayEntriesIndex %>'
										dayValue="<%= holidayEntryStartDay %>"
										firstEnabledDate="<%= holidayEntryStartDate.after(firstEnabledDate) ? holidayEntryStartDate : firstEnabledDate %>"
										lastEnabledDate="<%= holidayEntryStartDate.after(lastEnabledDate) ? holidayEntryStartDate : lastEnabledDate %>"
										monthParam='<%= "holidayEntryStartMonth" + holidayEntriesIndex %>'
										monthValue="<%= holidayEntryStartMonth %>"
										nullable="<%= false %>"
										yearParam='<%= "holidayEntryStartYear" + holidayEntriesIndex %>'
										yearValue="<%= holidayEntryStartYear %>"
									/>
								</td>
								<td>
									<liferay-ui:input-date
										cssClass="aui-w100"
										dayParam='<%= "holidayEntryEndDay" + holidayEntriesIndex %>'
										dayValue="<%= holidayEntryEndDay %>"
										firstEnabledDate="<%= holidayEntryEndDate.after(firstEnabledDate) ? holidayEntryEndDate : firstEnabledDate %>"
										lastEnabledDate="<%= holidayEntryEndDate.after(lastEnabledDate) ? holidayEntryEndDate : lastEnabledDate %>"
										monthParam='<%= "holidayEntryEndMonth" + holidayEntriesIndex %>'
										monthValue="<%= holidayEntryEndMonth %>"
										nullable="<%= false %>"
										yearParam='<%= "holidayEntryEndYear" + holidayEntriesIndex %>'
										yearValue="<%= holidayEntryEndYear %>"
									/>
								</td>
							</tr>
						</table>
					</div>
				</div>

			<%
			}
			%>

			<aui:input name="holidayEntriesIndexes" type="hidden" value="<%= StringUtil.merge(holidayEntriesIndexes) %>" />
		</aui:fieldset>
	</div>
</aui:form>

<aui:script use="liferay-auto-fields">
	new Liferay.AutoFields(
		{
			contentBox: '#<portlet:namespace />holidayEntries > fieldset',
			fieldIndexes: '<portlet:namespace />holidayEntriesIndexes'
		}
	).render();
</aui:script>