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

<aui:form action="<%= addTicketCallURL %>" method="post" name="fm">
	<liferay-ui:error exception="<%= TicketCallCustomerNameException.class %>" message="please-enter-a-valid-customer-name" />
	<liferay-ui:error exception="<%= TicketCallDateException.class %>" message="please-enter-a-valid-call-date" />
	<liferay-ui:error exception="<%= TicketCallLengthException.class %>" message="please-enter-a-valid-call-length" />
	<liferay-ui:error exception="<%= TicketCallTypeException.class %>" message="please-choose-a-valid-type" />

	<div class="aui-w95 unit">
		<h1 class="section-heading">
			<liferay-ui:message key="log-call" />
		</h1>

		<div class="cleared">
			<div class="aui-w33 content-column">
				<div class="left-column content-column-content">
					<span class="txt-b"><liferay-ui:message key="customer-name" />: </span>

					<br />

					<input name="<portlet:namespace />customerName" type="text" value="<%= HtmlUtil.escapeAttribute(customerName) %>" />
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="right-column content-column-content">
					<span class="txt-b"><liferay-ui:message key="contact-info" />: </span>

					<br />

					<input name="<portlet:namespace />customerContact" type="text" value="<%= HtmlUtil.escapeAttribute(customerContact) %>" />
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="content-column-content">
					<span class="txt-b"><liferay-ui:message key="call-type" />: </span>

					<br />

					<select name="<portlet:namespace />type">
						<option value="0"></option>
						<option <%= (type == TicketCallConstants.TYPE_INCOMING) ? "selected" : "" %> value="<%= TicketCallConstants.TYPE_INCOMING %>"><liferay-ui:message key="incoming" /></option>
						<option <%= (type == TicketCallConstants.TYPE_OUTGOING) ? "selected" : "" %> value="<%= TicketCallConstants.TYPE_OUTGOING %>"><liferay-ui:message key="outgoing" /></option>
					</select>
				</div>
			</div>
		</div>

		<br />

		<div class="cleared">
			<div class="aui-w33 content-column">
				<span class="txt-b"><liferay-ui:message key="call-date" />: </span>

				<br />

				<liferay-ui:input-field defaultValue="<%= calendar %>" field="callDate" model="<%= TicketCall.class %>" />
			</div>

			<div class="aui-w33 content-column">
				<div class="right-column content-column-content">
					<span class="txt-b"><liferay-ui:message key="call-length" />: </span>

					<br />

					<div class="lfr-input-time">
						<select name="<portlet:namespace />callLengthHours">

							<%
							for (int i = 0; i < 24; i++) {
							%>

								<option <%= (callLengthHours == i) ? "selected" : "" %> value="<%= i %>"><%= i %></option>

							<%
							}
							%>

						</select>

						<%= StringPool.COLON %>

						<select name="<portlet:namespace />callLengthMinutes">

							<%
							for (int i = 0; i < 60; i++) {
							%>

								<option <%= (callLengthMinutes == i) ? "selected" : "" %> value="<%= i %>"><%= i %></option>

							<%
							}
							%>

						</select>

						<%= StringPool.COLON %>

						<select name="<portlet:namespace />callLengthSeconds">

							<%
							for (int i = 0; i < 60; i++) {
							%>

								<option <%= (callLengthSeconds == i) ? "selected" : "" %> value="<%= i %>"><%= i %></option>

							<%
							}
							%>

						</select>
					</div>
				</div>
			</div>
		</div>

		<br />

		<div class="cleared">
			<div class="aui-w100 content-column">
				<div class="content-column-content">
					<span class="txt-b"><liferay-ui:message key="customer-confirmation" />: </span>

					<textarea name="<portlet:namespace />confirmation" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 100px; width: 100%;" wrap="soft"><%= HtmlUtil.escape(confirmation) %></textarea>
				</div>

				<br />
			</div>

			<div class="aui-w100 content-column">
				<div class="content-column-content">
					<span class="txt-b"><liferay-ui:message key="internal-instructions" />: </span>

					<textarea name="<portlet:namespace />instructions" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" style="height: 100px; width: 100%;" wrap="soft"><%= HtmlUtil.escape(instructions) %></textarea>
				</div>
			</div>
		</div>

		<div>
			<input class="aui-button-input" type="submit" value="<liferay-ui:message key="log-call" />" />

			<input class="aui-button-input" onClick="window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
		</div>
	</div>
</aui:form>

<aui:script>
	<c:if test="<%= ticketCallId > 0 %>">
		window.close();
	</c:if>
</aui:script>