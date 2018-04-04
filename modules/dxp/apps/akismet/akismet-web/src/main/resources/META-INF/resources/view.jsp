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
portletURL.setWindowState(WindowState.MAXIMIZED);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-ui:error exception="<%= NoSuchMessageException.class %>" message="the-message-could-not-be-found" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />
<liferay-ui:error exception="<%= RequiredMessageException.class %>" message="you-cannot-delete-a-root-message-that-has-more-than-one-immediate-reply" />

<%@ include file="/message_boards.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteMBMessages',
		function(dicussion) {
			var deleteMBMessageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (deleteMBMessageIds && confirm('<%= UnicodeLanguageUtil.get(portletConfig.getResourceBundle(locale), "are-you-sure-you-want-to-delete-the-selected-messages") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />deleteMBMessageIds.value = deleteMBMessageIds;

				if (dicussion) {
					submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="deleteDiscussionMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
				}
				else {
					submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="deleteMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
				}
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />notSpamMBMessages',
		function() {
			var notSpamMBMessageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

			if (notSpamMBMessageIds && confirm('<%= UnicodeLanguageUtil.get(portletConfig.getResourceBundle(locale), "are-you-sure-you-want-to-mark-the-selected-messages-as-not-spam") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />notSpamMBMessageIds.value = notSpamMBMessageIds;
				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="markNotSpamMBMessages"><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>