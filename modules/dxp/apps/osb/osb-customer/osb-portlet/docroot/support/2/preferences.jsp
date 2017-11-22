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
String tabs1 = ParamUtil.getString(request, "tabs1");

String redirect = ParamUtil.getString(request, "redirect");

List<String> tabsNamesList = new ArrayList<String>();

tabsNamesList.add("general");

if (!PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId())) {
	tabsNamesList.add("environment-configurations");
}

if (SupportWorkerLocalServiceUtil.hasSupportWorkerRole(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER)) {
	tabsNamesList.add("manager-dashboard");
}

if (!tabsNamesList.contains(tabs1)) {
	tabs1 = tabsNamesList.get(0);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/preferences.jsp");
%>

<div class="margin-container">
	<div class="page-title">
		<liferay-ui:message key="preferences" />
	</div>

	<liferay-ui:tabs
		names="<%= StringUtil.merge(tabsNamesList) %>"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("environment-configurations") %>'>
			<portlet:renderURL var="redirectURL">
				<portlet:param name="mvcPath" value="/support/2/preferences/environment_configurations.jsp" />
			</portlet:renderURL>

			<liferay-util:include page="/support/2/preferences/environment_configurations.jsp" servletContext="<%= application %>">
				<portlet:param name="redirect" value="<%= redirectURL %>" />
				<portlet:param name="tabs1" value="<%= tabs1 %>" />
			</liferay-util:include>
		</c:when>
		<c:when test='<%= tabs1.equals("manager-dashboard") %>'>
			<liferay-util:include page="/support/2/preferences/manager_dashboard.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>

			<%
			List<AccountCustomer> accountCustomers = AccountCustomerLocalServiceUtil.getUserAccountCustomers(user.getUserId(), new int[] {AccountCustomerConstants.ROLE_SALES, AccountCustomerConstants.ROLE_WATCHER});
			%>

				<h3>
					<liferay-ui:message key="notifications" />

					<liferay-ui:icon-help message="if-you-turn-your-notifications-off-you-will-still-receive-notifications-for-tickets-you-watch" />
				</h3>


			<c:if test="<%= !accountCustomers.isEmpty() %>">
				<div class="notification-preference">

					<%
					for (AccountCustomer accountCustomer : accountCustomers) {
						AccountEntry accountEntry = accountCustomer.getAccountEntry();
					%>

						<div><strong><%= HtmlUtil.escape(accountEntry.getName()) %>:</strong></div>

						<p>
							<liferay-ui:message key="notify-me-when-tickets-are-updated" />

							<portlet:actionURL name="toggleNotifications" var="toggleNotificationsURL">
								<portlet:param name="tabs1" value="<%= tabs1 %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="accountCustomerId" value="<%= StringUtil.valueOf(accountCustomer.getAccountCustomerId()) %>" />
							</portlet:actionURL>

							<a class="toggle-on-off-switch <%= accountCustomer.hasNotificationsOn() ? "on" : "off" %>" href="<%= toggleNotificationsURL %>">
								<span class="toggle-on-off-switch-inner">
									<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="on" /></span>

									<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="off" /></span>
								</span>
							</a>
						</p>

					<%
					}
					%>

				</div>
			</c:if>

			<liferay-portlet:actionURL name="updatePreferences" var="updatePreferencesURL">
				<portlet:param name="mvcPath" value="/support/2/preferences.jsp" />
			</liferay-portlet:actionURL>

			<aui:form action="<%= updatePreferencesURL %>" method="post" name="fm">
				<aui:input name="<%= CMDConstants.CMD %>" type="hidden" value="<%= supportPartnerWorker ? CMDConstants.UPDATE_PROFILE_PREFERENCES : CMDConstants.UPDATE_PREFERENCES %>" />
				<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
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
					%>

					<liferay-ui:error exception="<%= AwayMessageDateException.class %>" message="please-enter-valid-dates" />
					<liferay-ui:error exception="<%= AwayMessageMessageException.class %>" message="please-enter-a-valid-message" />

					<div class="away-message">
						<div class="away-message-checkbox">
							<aui:fieldset>
								<h2 class="away-message-header">
									<liferay-ui:message key="away-message" />:
								</h2>

								<aui:input inlineField="<%= true %>" label="auto-away-message-reply-enabled" name="awayMessageEnabled" style="margin-left: 25px;" type="checkbox" value="<%= awayMessageEnabled %>" />
							</aui:fieldset>
						</div>

						<div class="aui-w75 clearfix">

							<%
							Calendar calendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC), locale);

							Date firstEnabledDate = calendar.getTime();

							calendar.add(Calendar.YEAR, 4);

							Date lastEnabledDate = calendar.getTime();
							%>

							<div class="aui-w50 away-message-start-date">
								<p><liferay-ui:message key="start-date" /></p>

								<liferay-ui:input-date
									dayParam="awayMessageStartDay"
									dayValue="<%= awayMessageStartDay %>"
									firstEnabledDate="<%= firstEnabledDate %>"
									lastEnabledDate="<%= lastEnabledDate %>"
									monthParam="awayMessageStartMonth"
									monthValue="<%= awayMessageStartMonth %>"
									nullable="<%= false %>"
									yearParam="awayMessageStartYear"
									yearValue="<%= awayMessageStartYear %>"
								/>
							</div>

							<div class="aui-w50 away-message-end-date">
								<p><liferay-ui:message key="end-date-optional" /></p>

								<liferay-ui:input-date
									dayParam="awayMessageEndDay"
									dayValue="<%= awayMessageEndDay %>"
									firstEnabledDate="<%= firstEnabledDate %>"
									lastEnabledDate="<%= lastEnabledDate %>"
									monthParam="awayMessageEndMonth"
									monthValue="<%= awayMessageEndMonth %>"
									nullable="<%= true %>"
									yearParam="awayMessageEndYear"
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
					<aui:fieldset>
						<aui:input helpMessage="choose-this-option-if-you-wish-to-share-your-screen-with-other-people" ignoreRequestValue="<%= true %>" label="screen-share-mode" name="screenShareMode" type="checkbox" value="<%= screenShareMode %>" />
					</aui:fieldset>

					<div class="screen-share-mode">
						<liferay-ui:message key="screen-share-mode-hides-internal-information" />

						<div class="screen-share-mode-description">
							<ul class="w45">
								<li>
									<liferay-ui:message key="due-date" />
								</li>
								<li>
									<liferay-ui:message key="support-instructions" />
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

							<ul class="w45">
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
						</div>
					</div>
				</c:if>

				<aui:fieldset>
					<aui:input helpMessage="choose-this-option-if-you-wish-to-show-newest-comments-first" ignoreRequestValue="<%= true %>" label="reverse-comment-order" name="reverseCommentOrder" type="checkbox" value="<%= reverseCommentOrder %>" />
				</aui:fieldset>

				<aui:button type="submit" value="save" />
			</aui:form>
		</c:otherwise>
	</c:choose>
</div>

<aui:script>
	<portlet:namespace />navSelect('settings');
</aui:script>