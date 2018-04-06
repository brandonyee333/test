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
long ticketCallId = ParamUtil.getLong(request, "ticketCallId");

long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");
int type = ParamUtil.getInteger(request, "type");
int callLengthHours = ParamUtil.getInteger(request, "callLengthHours");
int callLengthMinutes = ParamUtil.getInteger(request, "callLengthMinutes");
int callLengthSeconds = ParamUtil.getInteger(request, "callLengthSeconds");
String customerName = ParamUtil.getString(request, "customerName");
String customerContact = ParamUtil.getString(request, "customerContact");
String confirmation = ParamUtil.getString(request, "confirmation");
String instructions = ParamUtil.getString(request, "instructions");

Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);
%>

<portlet:actionURL name="addTicketCall" var="addTicketCallURL">
	<portlet:param name="mvcPath" value="/support/2/edit_ticket_call.jsp" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntryId) %>" />
</portlet:actionURL>

<aui:form action="<%= addTicketCallURL %>" cssClass="log-call-form" method="post" name="addTicketCallFm">
	<liferay-ui:error exception="<%= TicketCallCustomerNameException.class %>" message="please-enter-a-valid-customer-name" />
	<liferay-ui:error exception="<%= TicketCallDateException.class %>" message="please-enter-a-valid-call-date" />
	<liferay-ui:error exception="<%= TicketCallLengthException.class %>" message="please-enter-a-valid-call-length" />
	<liferay-ui:error exception="<%= TicketCallTypeException.class %>" message="please-choose-a-valid-type" />

	<h2>
		<liferay-ui:message key="log-call" />
	</h2>

	<div class="call-info clearfix">
		<div class="content-column w33">
			<div class="content-column-content left-column">
				<span class="txt-b"><liferay-ui:message key="customer-name" />: </span>

				<aui:input label="" name="customerName" type="text" value="<%= customerName %>" />
			</div>
		</div>

		<div class="content-column w33">
			<div class="content-column-content right-column">
				<span class="txt-b"><liferay-ui:message key="contact-info" />: </span>

				<aui:input label="" name="customerContact" type="text" value="<%= customerContact %>" />
			</div>
		</div>

		<div class="content-column w33">
			<div class="content-column-content">
				<span class="txt-b"><liferay-ui:message key="call-type" />: </span>

				<aui:select label="" name="type">
					<aui:option value="0" />
					<aui:option label="incoming" selected="<%= type == TicketCallConstants.TYPE_INCOMING %>" value="<%= TicketCallConstants.TYPE_INCOMING %>" />
					<aui:option label="outgoing" selected="<%= type == TicketCallConstants.TYPE_OUTGOING %>" value="<%= TicketCallConstants.TYPE_OUTGOING %>" />
				</aui:select>
			</div>
		</div>
	</div>

	<div class="call-times clearfix">
		<div class="content-column w33">
			<span class="txt-b"><liferay-ui:message key="call-date" />: </span>

			<liferay-ui:input-field
				defaultValue="<%= calendar %>"
				field="callDate"
				model="<%= TicketCall.class %>"
			/>
		</div>

		<div class="content-column w33">
			<div class="content-column-content right-column">
				<span class="txt-b"><liferay-ui:message key="call-length" />: </span>

				<div class="lfr-input-time">
					<aui:select label="" name="callLengthHours">

						<%
						for (int i = 0; i < 24; i++) {
						%>

							<aui:option label="<%= i %>" selected="<%= callLengthHours == i %>" value="<%= i %>" />

						<%
						}
						%>

					</aui:select>

					<%= StringPool.COLON %>

					<aui:select label="" name="callLengthMinutes">

						<%
						for (int i = 0; i < 60; i++) {
						%>

							<aui:option label="<%= i %>" selected="<%= callLengthMinutes == i %>" value="<%= i %>" />

						<%
						}
						%>

					</aui:select>

					<%= StringPool.COLON %>

					<aui:select label="" name="callLengthSeconds">

						<%
						for (int i = 0; i < 60; i++) {
						%>

							<aui:option label="<%= i %>" selected="<%= callLengthSeconds == i %>" value="<%= i %>" />

						<%
						}
						%>

					</aui:select>
				</div>
			</div>
		</div>
	</div>

	<div class="clearfix">
		<div class="content-column w100">
			<div class="content-column-content">
				<span class="txt-b"><liferay-ui:message key="customer-confirmation" />: </span>

				<aui:input label="<%= confirmation %>" name="confirmation" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 100px;" type="textarea" wrap="soft" />
			</div>
		</div>

		<div class="content-column w100">
			<div class="content-column-content">
				<span class="txt-b"><liferay-ui:message key="internal-instructions" />: </span>

				<aui:input label="<%= instructions %>" name="instructions" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 100px;" type="textarea" wrap="soft" />
			</div>
		</div>
	</div>

	<aui:button type="submit" value="log-call" />

	<aui:button onClick="window.close();" value="cancel" />
</aui:form>

<c:if test="<%= ticketCallId > 0 %>">
	<aui:script>
		window.close();
	</aui:script>
</c:if>