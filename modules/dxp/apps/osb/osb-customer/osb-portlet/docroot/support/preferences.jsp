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

<%@ include file="/support/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

List<AccountCustomer> accountCustomers = AccountCustomerLocalServiceUtil.getUserAccountCustomers(user.getUserId(), new int[] {AccountCustomerConstants.ROLE_SALES, AccountCustomerConstants.ROLE_WATCHER});
%>

<c:if test="<%= !accountCustomers.isEmpty() %>">
	<strong><liferay-ui:message key="notifications" /></strong>

	<liferay-ui:icon-help message="if-you-turn-your-notifications-off-you-will-still-receive-notifications-for-tickets-you-watch" />

	<br /><br />

	<%
	for (AccountCustomer accountCustomer : accountCustomers) {
		AccountEntry accountEntry = accountCustomer.getAccountEntry();
	%>

		<strong><%= HtmlUtil.escape(accountEntry.getName()) %>:</strong>

		<br />

		<div>
			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="notify-me-when-tickets-are-updated" />:
				</td>
				<td class="switch">
					<portlet:actionURL name="toggleNotifications" var="toggleNotificationsURL">
						<portlet:param name="tabs1" value="preferences" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="accountCustomerId" value="<%= StringUtil.valueOf(accountCustomer.getAccountCustomerId()) %>" />
					</portlet:actionURL>

					<div class="toggle-on-off-switch">
						<a class="toggle-on-off-switch-ctrl <%= accountCustomer.hasNotificationsOn() ? "on" : "off" %>" href="<%= toggleNotificationsURL %>">
							<span class="toggle-on-off-switch-inner">
								<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="on" /></span>

								<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="off" /></span>
							</span>
						</a>
					</div>
				</td>
			</tr>
			</table>
		</div>

		<br />

	<%
	}
	%>

</c:if>

<liferay-portlet:actionURL name="updatePreferences" var="updatePreferencesURL">
	<portlet:param name="mvcPath" value="/support/view.jsp" />
</liferay-portlet:actionURL>

<aui:form action="<%= updatePreferencesURL %>" method="post" name="fm">
	<aui:input name="<%= CMDConstants.CMD %>" type="hidden" value="<%= supportPartnerWorker ? CMDConstants.UPDATE_PROFILE_PREFERENCES : CMDConstants.UPDATE_PREFERENCES %>" />
	<aui:input name="tabs1" type="hidden" value="preferences" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<c:if test="<%= supportPartnerWorker %>">

		<%
		PortletPreferences preferences = SupportUtil.getUserPreferences(user.getUserId());

		boolean awayMessageEnabled = GetterUtil.getBoolean(preferences.getValue("awayMessageEnabled", null));
		int awayMessageStartMonth = GetterUtil.getInteger(preferences.getValue("awayMessageStartMonth", null));
		int awayMessageStartDay = GetterUtil.getInteger(preferences.getValue("awayMessageStartDay", null));
		int awayMessageStartYear = GetterUtil.getInteger(preferences.getValue("awayMessageStartYear", null));
		int awayMessageEndMonth = GetterUtil.getInteger(preferences.getValue("awayMessageEndMonth", null));
		int awayMessageEndDay = GetterUtil.getInteger(preferences.getValue("awayMessageEndDay", null));
		int awayMessageEndYear = GetterUtil.getInteger(preferences.getValue("awayMessageEndYear", null));
		String awayMessage = preferences.getValue("awayMessage", StringPool.BLANK);

		Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);
		%>

		<liferay-ui:tabs
			names="profile"
			param="tabs2"
		/>

		<liferay-ui:error exception="<%= AwayMessageDateException.class %>" message="please-enter-valid-dates" />
		<liferay-ui:error exception="<%= AwayMessageMessageException.class %>" message="please-enter-a-valid-message" />

		<div class="away-message">
			<div class="away-message-checkbox">
				<aui:fieldset>
					<h2 class="away-message-header">
						<liferay-ui:message key="away-message" />:
					</h2>

					<aui:input inlineField="true" label="auto-away-message-reply-enabled" name="awayMessageEnabled" style="margin-left: 25px;" type="checkbox" value="<%= awayMessageEnabled %>" />
				</aui:fieldset>
			</div>

			<div class="aui-helper-clearfix aui-w75">
				<div class="away-message-start-date aui-w50">
					<div>
						<liferay-ui:message key="start-date" />
					</div>

					<liferay-ui:input-date
						dayNullable="<%= false %>"
						dayParam="awayMessageStartDay"
						dayValue="<%= awayMessageStartDay %>"
						monthNullable="<%= false %>"
						monthParam="awayMessageStartMonth"
						monthValue="<%= awayMessageStartMonth %>"
						yearNullable="<%= false %>"
						yearParam="awayMessageStartYear"
						yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 4 %>"
						yearRangeStart="<%= calendar.get(Calendar.YEAR) %>"
						yearValue="<%= awayMessageStartYear %>"
					/>
				</div>

				<div class="away-message-end-date aui-w50">
					<div>
						<liferay-ui:message key="end-date-optional" />
					</div>

					<liferay-ui:input-date
						dayNullable="<%= true %>"
						dayParam="awayMessageEndDay"
						dayValue="<%= awayMessageEndDay %>"
						monthNullable="<%= true %>"
						monthParam="awayMessageEndMonth"
						monthValue="<%= awayMessageEndMonth %>"
						yearNullable="<%= true %>"
						yearParam="awayMessageEndYear"
						yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 4 %>"
						yearRangeStart="<%= calendar.get(Calendar.YEAR) %>"
						yearValue="<%= awayMessageEndYear %>"
					/>
				</div>
			</div>

			<div class="away-message-text-area">
				<aui:input label="message" name="awayMessage" type="textarea" value="<%= awayMessage %>" />
			</div>
		</div>
	</c:if>

	<c:if test="<%= liferayIncOrg %>">
		<liferay-ui:tabs
			names="general"
			param="tabs2"
		/>

		<aui:fieldset>
			<aui:input helpMessage="choose-this-option-if-you-wish-to-share-your-screen-with-other-people" ignoreRequestValue="true" label="screen-share-mode" name="screenShareMode" type="checkbox" value="<%= screenShareMode %>" />
		</aui:fieldset>

		<div class="callout-a">
			<div class="callout-content">
				<div class="cleared">
					<liferay-ui:message key="screen-share-mode-hides-internal-information" />
				</div>

				<br />

				<table class="screen-share-mode-description">
				<tr>
					<td class="aui-w50">
						<ul>
							<li>
								<liferay-ui:message key="due-date" />
							</li>
							<li>
								<liferay-ui:message key="special-instructions" />
							</li>
							<li>
								<liferay-ui:message key="reproduction-steps" />
							</li>
							<li>
								<liferay-ui:message key="edit-button" />
							</li>
							<li>
								<liferay-ui:message key="manage-links-button" />
							</li>
							<li>
								<liferay-ui:message key="escalate-button" />
							</li>
						</ul>
					</td>
					<td class="aui-w50">
						<ul>
							<li>
								<liferay-ui:message key="workers-tab" />
							</li>
							<li>
								<liferay-ui:message key="liferay-tab" />
							</li>
							<li>
								<liferay-ui:message key="all-comments-tab" />
							</li>
							<li>
								<liferay-ui:message key="history-tab" />
							</li>
							<li>
								<liferay-ui:message key="attachments-that-are-visible-to-workers-or-liferay" />
							</li>
							<li>
								<liferay-ui:message key="links-that-are-visible-to-workers-or-liferay" />
							</li>
						</ul>
					</td>
				</tr>
				</table>
			</div>
		</div>

		<aui:fieldset>
			<aui:input helpMessage="choose-this-option-if-you-wish-to-show-newest-comments-first" ignoreRequestValue="<%= true %>" label="reverse-comment-order" name="reverseCommentOrder" type="checkbox" value="<%= reverseCommentOrder %>" />
		</aui:fieldset>
	</c:if>

	<c:if test="<%= SupportUtil.isVersion2Enabled(user.getUserId()) %>">
		<aui:fieldset>
			<c:choose>
				<c:when test="<%= SupportUtil.isMobile(request) %>">
					<liferay-ui:message key="the-new-lesa-redesign-is-currently-unavailable-on-mobile-devices" />
				</c:when>
				<c:otherwise>

					<%
					boolean version2Enabled = SupportUtil.getUserPreferenceValue(user.getUserId(), "version2Enabled");
					%>

					<aui:input helpMessage="the-new-lesa-redesign-is-currently-unavailable-on-mobile-devices" ignoreRequestValue="<%= true %>" label="view-the-new-changes-to-lesa" name="version2Enabled" type="checkbox" value="<%= version2Enabled %>" />
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</c:if>

	<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />
</aui:form>