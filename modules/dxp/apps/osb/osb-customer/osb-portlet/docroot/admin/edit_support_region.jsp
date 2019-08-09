<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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