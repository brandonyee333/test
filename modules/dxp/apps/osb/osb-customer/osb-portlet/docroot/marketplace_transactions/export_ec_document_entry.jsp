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
Calendar defaultEndCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

int endDateDay = ParamUtil.getInteger(request, "endDateDay", defaultEndCalendar.get(Calendar.DATE));
int endDateMonth = ParamUtil.getInteger(request, "endDateMonth", defaultEndCalendar.get(Calendar.MONTH));
int endDateYear = ParamUtil.getInteger(request, "endDateYear", defaultEndCalendar.get(Calendar.YEAR));
int endDateYearRangeEnd = defaultEndCalendar.get(Calendar.YEAR);
int endDateYearRangeStart = defaultEndCalendar.get(Calendar.YEAR) - 50;

Calendar endCalendar = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), locale);

endCalendar.set(Calendar.MONTH, endDateMonth);
endCalendar.set(Calendar.DATE, endDateDay);
endCalendar.set(Calendar.YEAR, endDateYear);

Calendar defaultStartCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

defaultStartCalendar.add(Calendar.DATE, -13);

int startDateDay = ParamUtil.getInteger(request, "startDateDay", defaultStartCalendar.get(Calendar.DATE));
int startDateMonth = ParamUtil.getInteger(request, "startDateMonth", defaultStartCalendar.get(Calendar.MONTH));
int startDateYear = ParamUtil.getInteger(request, "startDateYear", defaultStartCalendar.get(Calendar.YEAR));
int startDateYearRangeEnd = defaultStartCalendar.get(Calendar.YEAR);
int startDateYearRangeStart = defaultStartCalendar.get(Calendar.YEAR) - 50;

Calendar startCalendar = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), locale);

startCalendar.set(Calendar.MONTH, startDateMonth);
startCalendar.set(Calendar.DATE, startDateDay);
startCalendar.set(Calendar.YEAR, startDateYear);
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveECDocumentEntryCSV" var="serveECDocumentEntryCsvURL" />

<div class="marketplace-transaction export-ec-document-entry">
	<aui:form action="<%= serveECDocumentEntryCsvURL %>" method="post" name="fm">
		<span class="aui-field aui-field-date">
			<span class="aui-field-content">
				<label class="aui-field-label"><liferay-ui:message key="start-date" /></label>

				<span class="aui-field-element">
					<liferay-ui:input-date
						dayParam='<%= "startDateDay" %>'
						dayValue="<%= startDateDay %>"
						monthParam='<%= "startDateMonth" %>'
						monthValue="<%= startDateMonth %>"
						yearParam='<%= "startDateYear" %>'
						yearRangeEnd="<%= startDateYearRangeEnd %>"
						yearRangeStart="<%= startDateYearRangeStart %>"
						yearValue="<%= startDateYear %>"
					/>
				</span>
			</span>
		</span>

		<span class="aui-field aui-field-date">
			<span class="aui-field-content">
				<label class="aui-field-label"><liferay-ui:message key="end-date" /></label>

				<span class="aui-field-element">
					<liferay-ui:input-date
						dayParam='<%= "endDateDay" %>'
						dayValue="<%= endDateDay %>"
						monthParam='<%= "endDateMonth" %>'
						monthValue="<%= endDateMonth %>"
						yearParam='<%= "endDateYear" %>'
						yearRangeEnd="<%= endDateYearRangeEnd %>"
						yearRangeStart="<%= endDateYearRangeStart %>"
						yearValue="<%= endDateYear %>"
					/>
				</span>
			</span>
		</span>

		<div class="button-row">
			<aui:button-row>
				<input class="aui-button-input" onClick="<portlet:namespace />exportECDocumentEntry();" type="button" value="<liferay-ui:message key="export" />" />

				<a class="btn cancel-button" onClick="<portlet:namespace />closeExportDialog();"><liferay-ui:message key="cancel" /></a>
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />exportECDocumentEntry() {
		submitForm(document.<portlet:namespace />fm, null, false);

		<portlet:namespace />closeExportDialog();
	}

	function <portlet:namespace />closeExportDialog() {
		AUI().DialogManager.closeByChild(AUI().one('.marketplace-transaction.export-ec-document-entry'));
	}
</aui:script>