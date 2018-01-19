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
long holidayCalendarId = ParamUtil.getLong(request, "holidayCalendarId");
int index = ParamUtil.getInteger(request, "index");

List<HolidayEntry> holidayEntries = new ArrayList<HolidayEntry>();

if (holidayCalendarId > 0) {
	holidayEntries = HolidayEntryLocalServiceUtil.getHolidayEntries(holidayCalendarId);
}

HolidayEntry holidayEntry = new HolidayEntryImpl();

if (holidayEntries.size() > 0) {
	holidayEntry = holidayEntries.get(index);
}

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

Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

Date firstEnabledDate = calendar.getTime();

calendar.add(Calendar.YEAR, 15);

Date lastEnabledDate = calendar.getTime();
%>

<div class="field-row">
	<aui:model-context bean="<%= holidayEntry %>" model="<%= HolidayEntry.class %>" />

	<aui:input name='<%= "holidayEntryId" + index %>' type="hidden" value="<%= holidayEntry.getHolidayEntryId() %>" />

	<aui:input fieldParam='<%= "holidayEntryName" + index %>' id='<%= "holidayEntryName" + index %>' name="name" />

	<aui:input fieldParam='<%= "holidayEntryDescription" + index %>' id='<%= "holidayEntryDescription" + index %>' name="description" />

	<aui:input label="repeat-yearly" name='<%= "holidayEntryRepeatYearly" + index %>' type="checkbox" value="<%= holidayEntry.getRepeatYearly() %>" />

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
					cssClass="w100"
					dayParam='<%= "holidayEntryStartDay" + index %>'
					dayValue="<%= holidayEntryStartDay %>"
					firstEnabledDate="<%= ((holidayEntryStartDate != null) && holidayEntryStartDate.after(firstEnabledDate)) ? holidayEntryStartDate : firstEnabledDate %>"
					lastEnabledDate="<%= ((holidayEntryStartDate != null) && holidayEntryStartDate.after(lastEnabledDate)) ? holidayEntryStartDate : lastEnabledDate %>"
					monthParam='<%= "holidayEntryStartMonth" + index %>'
					monthValue="<%= holidayEntryStartMonth %>"
					nullable="<%= false %>"
					yearParam='<%= "holidayEntryStartYear" + index %>'
					yearValue="<%= holidayEntryStartYear %>"
				/>
			</td>
			<td>
				<liferay-ui:input-date
					cssClass="w100"
					dayParam='<%= "holidayEntryEndDay" + index %>'
					dayValue="<%= holidayEntryEndDay %>"
					firstEnabledDate="<%= ((holidayEntryEndDate != null) && holidayEntryEndDate.after(firstEnabledDate)) ? holidayEntryEndDate : firstEnabledDate %>"
					lastEnabledDate="<%= ((holidayEntryEndDate != null) && holidayEntryEndDate.after(lastEnabledDate)) ? holidayEntryEndDate : lastEnabledDate %>"
					monthParam='<%= "holidayEntryEndMonth" + index %>'
					monthValue="<%= holidayEntryEndMonth %>"
					nullable="<%= false %>"
					yearParam='<%= "holidayEntryEndYear" + index %>'
					yearValue="<%= holidayEntryEndYear %>"
				/>
			</td>
		</tr>
	</table>
</div>