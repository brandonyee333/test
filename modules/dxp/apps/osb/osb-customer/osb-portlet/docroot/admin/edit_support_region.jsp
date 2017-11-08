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

String supportTeamIds = StringPool.BLANK;

User managerUser = null;

List<SupportTeam> supportTeams = null;

if (supportRegion != null) {
	managerUser = supportRegion.getManagerUser();

	supportTeams = supportRegion.getSupportTeams();

	for (SupportTeam supportTeam : supportTeams) {
		supportTeamIds += supportTeam.getSupportTeamId() + StringPool.COMMA;
	}
}
%>

<portlet:actionURL name="updateSupportRegion" var="updateSupportRegionURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_region.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportRegionURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="supportRegionId" type="hidden" value="<%= supportRegionId %>" />
	<aui:input name="supportTeamIds" type="hidden" value="<%= supportTeamIds %>" />

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
				<aui:input name="name" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="description" />
			</td>
			<td>
				<aui:input name="description" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="time-zone" />
			</td>
			<td>
				<aui:select name="timeZoneId">
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
		<input type="submit" value="<liferay-ui:message key="save" />" />

		<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
	</div>

	<c:if test="<%= supportRegion != null %>">
		<br />

		<liferay-ui:tabs
			names="teams"
		/>

		<div>
			<input onClick="var categoryWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/admin/select_support_team.jsp" /><portlet:param name="callback" value="selectSupportTeam" /></portlet:renderURL>', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();" type="button" value="<liferay-ui:message key="add-team" />" />
		</div>

		<br />

		<liferay-ui:search-container
			headerNames="name"
			id="supportTeam"
			total="<%= supportTeams.size() %>"
		>
			<liferay-ui:search-container-results
				results="<%= supportTeams %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.SupportTeam"
				escapedModel="<%= true %>"
				keyProperty="supportTeamId"
				modelVar="supportTeam"
			>
				<liferay-ui:search-container-column-text
					property="name"
				/>

				<liferay-ui:search-container-column-text>
					<input onClick="<portlet:namespace />removeRow('supportTeamIds', '<%= supportTeam.getSupportTeamId() %>', '<portlet:namespace />supportTeamSearchContainer', this);" type="button" value="<liferay-ui:message key="remove" />" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</c:if>
</aui:form>

<aui:script>
	function <portlet:namespace />addColumn(row, html) {
		var cell = row.insertCell(-1);

		cell.innerHTML = html;
	}

	function <portlet:namespace />removeRow(inputName, value, tableId, row) {
		eval('var values = document.<portlet:namespace />fm.<portlet:namespace />' + inputName + '.value');

		values = values.replace(value + ',', '');

		eval('document.<portlet:namespace />fm.<portlet:namespace />' + inputName + '.value = values;');

		var table = document.getElementById(tableId).getElementsByTagName('tbody')[0];

		table.removeChild(row.parentNode.parentNode);
	}

	function <portlet:namespace />selectRow(inputName, value, tableId, columnValues) {
		eval('var values = document.<portlet:namespace />fm.<portlet:namespace />' + inputName + '.value');

		if (values.indexOf(value + ',') == -1) {
			values += value + ',';

			eval('document.<portlet:namespace />fm.<portlet:namespace />' + inputName + '.value = values;');

			var table = document.getElementById(tableId);

			table.parentNode.parentNode.className = 'lfr-search-container';

			var tBody = table.getElementsByTagName('tbody')[0];

			var row = tBody.insertRow(-1);

			row.className = 'results-row';

			for (i = 0; i < columnValues.length; i++) {
				<portlet:namespace />addColumn(row, columnValues[i]);
			}

			<portlet:namespace />addColumn(row, '<input type="button" onClick="<portlet:namespace />removeRow(\'' + inputName + '\', \'' + value + '\', \'' + tableId + '\', this);" value="<liferay-ui:message key="remove" />" />');
		}
	}

	function <portlet:namespace />selectSupportTeam(supportTeamId, supportTeamName) {
		<portlet:namespace />selectRow('supportTeamIds', supportTeamId, '<portlet:namespace />supportTeamSearchContainer', [supportTeamName]);
	}
</aui:script>