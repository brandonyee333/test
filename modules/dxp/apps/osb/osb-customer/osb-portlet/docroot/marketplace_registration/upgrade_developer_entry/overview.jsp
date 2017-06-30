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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long developerEntryId = ParamUtil.getLong(request, "developerEntryId");
%>

<div class="upgrade-developer-entry-overview">
	<p>
		<liferay-ui:message key="converting-to-a-paid-app-developer-account-will-allow-you-to-price-and-sell-apps-in-the-liferay-marketplace" />
	</p>

	<div>
		<div class="upgrade-step-header details">
			<span class="circle">1</span>

			<span><liferay-ui:message key="upload-the-required-tax-form-and-confirm-contact-information-to-receive-payments" /></span>
		</div>

		<div class="upgrade-step-header email">
			<span class="circle">2</span>

			<span><liferay-ui:message key="confirm-or-create-a-paypal-account-to-receive-payments" /></span>
		</div>

		<div class="upgrade-step-header pay">
			<span class="circle">3</span>

			<span>

				<%
				Calendar calendar = CalendarFactoryUtil.getCalendar();

				calendar.setTime(new Date());
				%>

				<c:choose>
					<c:when test="<%= OSBConstants.MARKETPLACE_SUBSCRIPTION_PROMOTION_YEAR < calendar.get(Calendar.YEAR) %>">
						<liferay-ui:message key="pay-the-99-annual-fee" />
					</c:when>
					<c:otherwise>
						<del><liferay-ui:message key="pay-the-99-annual-fee" /></del>

						<em><liferay-ui:message key="launch-promotion,-fee-waved-for-a-limited-time" /></em>
					</c:otherwise>
				</c:choose>
			</span>
		</div>
	</div>

	<aui:layout cssClass="rt-align">
		<aui:button-row>
			<aui:button onClick="<%= backURL %>" value="cancel" />

			<portlet:renderURL var="upgradeDeveloperEntryDetailsURL">
				<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
				<portlet:param name="backURL" value="<%= backURL %>" />
				<portlet:param name="upgradeStep" value="details" />
				<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntryId) %>" />
			</portlet:renderURL>

			<aui:button onClick="<%= upgradeDeveloperEntryDetailsURL %>" value="enroll-now" />
		</aui:button-row>
	</aui:layout>
</div>