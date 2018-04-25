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

long supportRegionId = ParamUtil.getLong(request, "supportRegionId");

SupportRegion supportRegion = null;

try {
	supportRegion = SupportRegionLocalServiceUtil.getSupportRegion(supportRegionId);
}
catch (NoSuchSupportRegionException nssre) {
}

String timeZoneId = BeanParamUtil.getString(supportRegion, request, "timeZoneId");

User managerUser = null;
%>

<portlet:actionURL name="updateSupportRegion" var="updateSupportRegionURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_region.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportRegionURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="supportRegionId" type="hidden" value="<%= supportRegionId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="support-region"
	/>

	<liferay-ui:error exception="<%= DuplicateSupportRegionException.class %>" message="please-enter-a-unique-name" />

	<aui:model-context bean="<%= supportRegion %>" model="<%= SupportRegion.class %>" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<aui:input label="" name="name" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="description" />
			</td>
			<td>
				<aui:input label="" name="description" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="time-zone" />
			</td>
			<td>
				<aui:select label="" name="timeZoneId">
					<aui:option label="" value="0" />

					<%
					String[] timeZoneIds = PropsUtil.getArray("time.zones");

					for (String curTimeZoneId : timeZoneIds) {
						TimeZone curTimeZone = TimeZone.getTimeZone(curTimeZoneId);
					%>

						<aui:option label="<%= curTimeZone.getDisplayName() %>" selected="<%= curTimeZoneId.equals(timeZoneId) %>" value="<%= curTimeZoneId %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="manager" />
			</td>
			<td>
				<c:if test="<%= managerUser != null %>">
					<%= HtmlUtil.escape(managerUser.getFullName()) %>
				</c:if>
			</td>
		</tr>
	</table>

	<br />

	<div>
		<aui:button type="submit" value="save" />

		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
	</div>
</aui:form>