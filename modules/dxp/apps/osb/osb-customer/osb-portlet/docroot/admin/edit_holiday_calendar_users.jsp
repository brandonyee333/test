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

long holidayCalendarId = ParamUtil.getLong(request, "holidayCalendarId");

HolidayCalendar holidayCalendar = HolidayCalendarLocalServiceUtil.getHolidayCalendar(holidayCalendarId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_holiday_calendar_users.jsp");
portletURL.setParameter("tabs3", tabs3);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("holidayCalendarId", String.valueOf(holidayCalendarId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />holidayCalendarId" type="hidden" value="<%= holidayCalendarId %>" />

	<liferay-ui:message key="assign-users-to" />: <%= HtmlUtil.escape(holidayCalendar.getName()) %>

	<br /><br />

	<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
	<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="current,available"
		param="tabs3"
		url="<%= portletURL.toString() %>"
	/>

	<%@ include file="/common/user_search_inputs.jspf" %>

	<%
	LinkedHashMap userParams = new LinkedHashMap();

	if (tabs3.equals("current")) {
		userParams.put("usersSupportWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByHolidayCalendar"), Long.valueOf(holidayCalendarId)));
	}
	%>

	<liferay-ui:search-container
		emptyResultsMessage="no-users-were-found"
		id="usersSearchContainer"
		iteratorURL="<%= portletURL %>"
		rowChecker="<%= new UserHolidayCalendarChecker(renderResponse, holidayCalendar) %>"
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

			<c:if test='<%= tabs3.equals("current") && (curUser != null) %>'>
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu>
						<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/admin/edit_support_worker.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="userId" value="<%= String.valueOf(curUser.getUserId()) %>" />
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

		<aui:button onClick='<%= renderResponse.getNamespace() + "updateHolidayCalendarUsers('" + portletURL.toString() + "&" + renderResponse.getNamespace() + "cur=" + cur + "');" %>' value="update-associations" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateHolidayCalendarUsers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateHolidayCalendarUsers"><portlet:param name="mvcPath" value="/admin/edit_holiday_calendar_users.jsp" /><portlet:param name="tabs3" value="<%= tabs3 %>" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>