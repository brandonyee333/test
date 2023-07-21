<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String searchContainerName = ParamUtil.getString(request, "searchContainerName");

List<CalendarBooking> calendarBookings = (List<CalendarBooking>)request.getAttribute("view.jsp-calendarBookings");
%>

<liferay-ui:search-container
	delta="<%= eventsPerPage %>"
	total="<%= calendarBookings.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= calendarBookings.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.calendar.model.CalendarBooking"
		keyProperty="calendarBookingId"
		modelVar="calendarBooking"
	>
		<liferay-ui:search-container-column-text
			name="<%= searchContainerName %>"
		>

			<%
			Group group = GroupLocalServiceUtil.getGroup(calendarBooking.getGroupId());

			LiferayPortletURL groupURL = PortletURLFactoryUtil.create(request, "com_liferay_site_my_sites_web_portlet_MySitesPortlet", layout.getPlid(), PortletRequest.ACTION_PHASE);

			groupURL.setWindowState(LiferayWindowState.NORMAL);

			groupURL.setParameter("struts_action", "/my_sites/view");
			groupURL.setParameter("groupId", String.valueOf(group.getGroupId()));

			if (group.hasPublicLayouts()) {
				groupURL.setParameter("privateLayout", "0");
			}
			else {
				groupURL.setParameter("privateLayout", "1");
			}

			String eventHREF = groupURL.toString();

			long selPlid = PortalUtil.getPlidFromPortletId(calendarBooking.getGroupId(), CalendarPortletKeys.CALENDAR);

			if (selPlid != LayoutConstants.DEFAULT_PLID) {
				LiferayPortletURL eventURL = PortletURLFactoryUtil.create(request, CalendarPortletKeys.CALENDAR, selPlid, PortletRequest.RENDER_PHASE);

				eventURL.setWindowState(LiferayWindowState.NORMAL);
				eventURL.setPortletMode(PortletMode.VIEW);

				eventURL.setParameter("mvcPath", "/view_calendar_booking.jsp");
				eventURL.setParameter("redirect", PortalUtil.getCurrentURL(request));
				eventURL.setParameter("calendarBookingId", String.valueOf(calendarBooking.getCalendarBookingId()));
				eventURL.setParameter("instanceIndex", String.valueOf(calendarBooking.getInstanceIndex()));

				eventHREF = eventURL.toString();
			}
			%>

			<div class="event">
				<span class="event-name">
					<a href="<%= eventHREF %>"><%= StringUtil.shorten(HtmlUtil.escape(calendarBooking.getTitle(locale)), 40) %></a>
				</span>

				<c:if test="<%= !calendarBooking.isAllDay() %>">
					<span class="event-time">
						<%= dateFormatTime.format(calendarBooking.getStartTime()) %>
					</span>
				</c:if>

				<c:if test="<%= group.isUser() %>">
					<span class="event-site">
						<a href="<%= groupURL.toString() %>"><%= group.getDescriptiveName(locale) %></a>
					</span>
				</c:if>
			</div>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		type="article"
	/>
</liferay-ui:search-container>