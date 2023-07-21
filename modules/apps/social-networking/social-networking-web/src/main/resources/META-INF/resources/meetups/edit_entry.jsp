<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long meetupsEntryId = ParamUtil.getLong(request, "meetupsEntryId");

MeetupsEntry meetupsEntry = null;

try {
	meetupsEntry = MeetupsEntryLocalServiceUtil.getMeetupsEntry(meetupsEntryId);
}
catch (NoSuchMeetupsEntryException nsmee) {
}

Calendar startDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

startDate.add(Calendar.MONTH, 1);

if ((meetupsEntry != null) && (meetupsEntry.getStartDate() != null)) {
	startDate.setTime(meetupsEntry.getStartDate());
}

Calendar endDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

endDate.add(Calendar.MONTH, 1);
endDate.add(Calendar.HOUR, 3);

if ((meetupsEntry != null) && (meetupsEntry.getStartDate() != null)) {
	endDate.setTime(meetupsEntry.getEndDate());
}
%>

<portlet:actionURL name="updateMeetupsEntry" var="updateMeetupsEntryURL" />

<aui:form action="<%= updateMeetupsEntryURL %>" enctype="multipart/form-data" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "updateMeetupsEntry(); return false;" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="meetupsEntryId" type="hidden" value="<%= meetupsEntryId %>" />

	<aui:model-context bean="<%= meetupsEntry %>" model="<%= MeetupsEntry.class %>" />

	<aui:input name="title" />

	<aui:input name="description" />

	<aui:input name="startDate" />

	<aui:input name="endDate" />

	<aui:input name="maxAttendees" />

	<aui:input name="price" />

	<aui:input label="thumbnail" name="fileName" type="file" />

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>" value="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />updateMeetupsEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>