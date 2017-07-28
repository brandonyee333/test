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
long accountCallId = ParamUtil.getLong(request, "accountCallId");

AccountCall accountCall = null;

if (accountCallId > 0) {
	accountCall = AccountCallLocalServiceUtil.getAccountCall(accountCallId);
}

long accountEntryId = BeanParamUtil.getLong(accountCall, request, "accountEntryId");
int type = BeanParamUtil.getInteger(accountCall, request, "type");
int callLengthHours = ParamUtil.getInteger(request, "callLengthHours");
int callLengthMinutes = ParamUtil.getInteger(request, "callLengthMinutes");
int callLengthSeconds = ParamUtil.getInteger(request, "callLengthSeconds");

Calendar callDateCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

if (accountCall != null) {
	callLengthHours = accountCall.getCallLengthHours();
	callLengthMinutes = accountCall.getCallLengthMinutes();
	callLengthSeconds = accountCall.getCallLengthSeconds();

	callDateCalendar.setTime(accountCall.getCallDate());
}

int callDateMonth = callDateCalendar.get(Calendar.MONTH);
int callDateDay = callDateCalendar.get(Calendar.DAY_OF_MONTH);
int callDateYear = callDateCalendar.get(Calendar.YEAR);
int callDateHour = callDateCalendar.get(Calendar.HOUR_OF_DAY);
int callDateMinute = callDateCalendar.get(Calendar.MINUTE);
int callDateAmPm = callDateCalendar.get(Calendar.AM_PM);

Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);
%>

<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) %>">
	<portlet:actionURL name="updateAccountCall" var="updateAccountCallURL">
		<portlet:param name="mvcPath" value="/support/edit_account_call.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateAccountCallURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
		<aui:input name="accountCallId" type="hidden" value="<%= accountCallId %>" />
		<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

		<liferay-ui:error exception="<%= AccountCallDateException.class %>" message="please-enter-a-valid-call-date" />
		<liferay-ui:error exception="<%= AccountCallLengthException.class %>" message="please-enter-a-valid-call-length" />
		<liferay-ui:error exception="<%= AccountCallSummaryException.class %>" message="please-enter-a-valid-customer-name" />
		<liferay-ui:error exception="<%= AccountCallTypeException.class %>" message="please-choose-a-valid-type" />

		<div class="callout-a">
			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w33 content-column">
					<aui:select label="call-type" name="type" showEmptyOption="true">
						<aui:option label="incoming" selected="<%= type == AccountCallConstants.TYPE_INCOMING %>" value="<%= AccountCallConstants.TYPE_INCOMING %>" />
						<aui:option label="outgoing" selected="<%= type == AccountCallConstants.TYPE_OUTGOING %>" value="<%= AccountCallConstants.TYPE_OUTGOING %>" />
						<aui:option label="standing" selected="<%= type == AccountCallConstants.TYPE_STANDING %>" value="<%= AccountCallConstants.TYPE_STANDING %>" />
					</aui:select>
				</div>

				<div class="aui-w33 content-column">
					<strong><liferay-ui:message key="call-date" /></strong>

					<liferay-ui:input-date
						dayNullable="<%= true %>"
						dayParam="callDateDay"
						dayValue="<%= callDateDay %>"
						monthNullable="<%= true %>"
						monthParam="callDateMonth"
						monthValue="<%= callDateMonth %>"
						yearNullable="<%= true %>"
						yearParam="callDateYear"
						yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
						yearRangeStart="<%= 2010 %>"
						yearValue="<%= callDateYear %>"
					/>

					<liferay-util:include page="/common/input_time.jsp" servletContext="<%= application %>">
						<liferay-util:param name="hourParam" value="callDateHour" />
						<liferay-util:param name="hourValue" value="<%= String.valueOf(callDateHour) %>" />
						<liferay-util:param name="minuteParam" value="callDateMinute" />
						<liferay-util:param name="minuteValue" value="<%= String.valueOf(callDateMinute) %>" />
						<liferay-util:param name="amPmParam" value="callDateAmPm" />
						<liferay-util:param name="amPmValue" value="<%= String.valueOf(callDateAmPm) %>" />
					</liferay-util:include>
				</div>

				<div class="aui-w33 content-column">
					<div class="content-column-content right-column">
						<span class="txt-b"><liferay-ui:message key="call-length" /></span>

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

			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w15 content-column customer-info-label">
					<liferay-ui:message key="summary" />
				</div>

				<div class="aui-w85 content-column customer-info">
					<aui:input bean="<%= accountCall %>" field="summary" label="" model="<%= AccountCall.class %>" name="summary" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" />
				</div>
			</div>

			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w15 content-column customer-info-label">
					<liferay-ui:message key="clients-present" />
				</div>

				<div class="aui-w85 content-column customer-info">
					<aui:input bean="<%= accountCall %>" field="clientsPresent" label="" model="<%= AccountCall.class %>" name="clientsPresent" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" />
				</div>
			</div>

			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w15 content-column customer-info-label">
					<liferay-ui:message key="notes" />
				</div>

				<div class="aui-w85 content-column customer-info">
					<aui:input bean="<%= accountCall %>" field="notes" label="" model="<%= AccountCall.class %>" name="notes" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" />
				</div>
			</div>

			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w15 content-column customer-info-label">
					<liferay-ui:message key="action-items" />
				</div>

				<div class="aui-w85 content-column customer-info">
					<aui:input bean="<%= accountCall %>" field="actionItems" label="" model="<%= AccountCall.class %>" name="actionItems" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" />
				</div>
			</div>
		</div>

		<div class="aui-helper-clearfix">
			<div class="callout-content fr">
				<c:if test="<%= accountCall != null %>">
					<liferay-ui:message arguments="<%= new Object[] {accountCall.getModifiedUserName(), fullDateFormatDateTime.format(accountCall.getModifiedDate())} %>" key="x-on-x" />
				</c:if>

				<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />

				<input class="aui-button-input" onClick="javascript:Liferay.Util.getWindow().close();" type="button" value="<liferay-ui:message key="cancel" />" />
			</div>
		</div>
	</aui:form>
</c:if>

<aui:script>
	<c:if test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		Liferay.Util.getWindow().close();
	</c:if>
</aui:script>