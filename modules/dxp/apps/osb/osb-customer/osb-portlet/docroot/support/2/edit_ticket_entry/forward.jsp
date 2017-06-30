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
String backURL = ParamUtil.getString(request, "backURL");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean impacted = ParamUtil.getBoolean(request, "impacted");
String mainGoal = ParamUtil.getString(request, "mainGoal");
String workDone = ParamUtil.getString(request, "workDone");
String customerNextSteps = ParamUtil.getString(request, "customerNextSteps");
String nextRegionNextSteps = ParamUtil.getString(request, "nextRegionNextSteps");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");

if (Validator.isNull(backURL)) {
	backURL = portletURL.toString();
}
%>

<div class="<%= (SessionErrors.contains(renderRequest, TicketEntryForwardingException.class) || SessionErrors.contains(renderRequest, TicketEntryForwardingFieldException.class)) ? StringPool.BLANK : "aui-helper-hidden" %> forward-popup" id="<portlet:namespace />forwardPopup">
	<div class="forward-popup-wrapper" id="<portlet:namespace />forwardPopupWrapper">
		<portlet:actionURL name="forwardTicketEntry" var="forwardTicketEntryURL">
			<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		</portlet:actionURL>

		<aui:form action="<%= forwardTicketEntryURL %>" class="uni-form forward-form" method="post" name="forwardFm">
			<input name="<portlet:namespace />redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
			<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />

			<liferay-ui:error exception="<%= TicketEntryForwardingException.class %>" message="no-other-support-worker-is-available-please-contact-your-manager" />

			<liferay-ui:error exception="<%= TicketEntryForwardingFieldException.class %>">

				<%
				TicketEntryForwardingFieldException teffe = (TicketEntryForwardingFieldException)errorException;
				%>

				<liferay-ui:message arguments="<%= teffe.getMessage() %>" key="x-was-not-filled-out" />
			</liferay-ui:error>

			<h2>
				<liferay-ui:message key="is-production-still-down-impacted" />*
			</h2>

			<liferay-ui:message key="yes" /> <input name="<portlet:namespace />impacted" <%= impacted ? "checked" : StringPool.BLANK %> type="radio" value="1" />

			<liferay-ui:message key="no" /> <input name="<portlet:namespace />impacted" <%= impacted ? StringPool.BLANK : "checked" %> type="radio" value="0" />

			<br /><br />

			<h2>
				<liferay-ui:message key="what-is-the-main-goal" />*
			</h2>

			<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= mainGoal %>" />
				<liferay-util:param name="editorId" value="mainGoalContainer" />
				<liferay-util:param name="name" value="mainGoal" />
			</liferay-util:include>

			<br /><br />

			<h2>
				<liferay-ui:message key="what-work-have-you-done-so-far" />*
			</h2>

			<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= workDone %>" />
				<liferay-util:param name="editorId" value="workDoneContainer" />
				<liferay-util:param name="name" value="workDone" />
			</liferay-util:include>

			<br /><br />

			<h2>
				<liferay-ui:message key="what-does-the-customer-still-need-to-do" />*
			</h2>

			<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= customerNextSteps %>" />
				<liferay-util:param name="editorId" value="customerNextStepsContainer" />
				<liferay-util:param name="name" value="customerNextSteps" />
			</liferay-util:include>

			<br /><br />

			<h2>
				<liferay-ui:message key="what-does-the-next-region-need-to-do" />*
			</h2>

			<liferay-util:include page="/support/2/bbcode_editor.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= nextRegionNextSteps %>" />
				<liferay-util:param name="editorId" value="nextRegionNextStepsContainer" />
				<liferay-util:param name="name" value="nextRegionNextSteps" />
			</liferay-util:include>

			<br /><br />

			<input class="aui-button-input" id="<portlet:namespace />forwardSubmit" type="submit" value="<liferay-ui:message key="forward" />" />

			<input class="aui-button-input" id="<portlet:namespace />forwardCancel" onclick="<portlet:namespace />cancelForwardTicketEntry();" type="button" value="<liferay-ui:message key="cancel" />" />
		</aui:form>
	</div>
</div>

<aui:script>
	var forwardPopupWrapper = document.getElementById("<portlet:namespace />forwardPopupWrapper");

	forwardPopupWrapper.style.height = (window.innerHeight - 200) + "px";

	function <portlet:namespace />cancelForwardTicketEntry() {
		var A = AUI();

		var forwardingPopup = A.one("#<portlet:namespace />forwardPopup");

		forwardingPopup.hide();
	}

	function <portlet:namespace />forwardTicketEntry() {
		var A = AUI();

		var forwardingPopup = A.one("#<portlet:namespace />forwardPopup");

		forwardingPopup.show();
	}
</aui:script>