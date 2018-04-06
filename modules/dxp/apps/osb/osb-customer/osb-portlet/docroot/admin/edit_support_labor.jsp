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

long supportLaborId = ParamUtil.getLong(request, "supportLaborId");

SupportLabor supportLabor = null;

try {
	supportLabor = SupportLaborLocalServiceUtil.getSupportLabor(supportLaborId);
}
catch (NoSuchSupportLaborException e) {
}

String supportTeamIds = StringPool.BLANK;

List<SupportTeam> supportTeams = null;

if (supportLabor != null) {
	supportTeams = supportLabor.getSupportTeams();

	for (SupportTeam curSupportTeam : supportTeams) {
		supportTeamIds += curSupportTeam.getSupportTeamId() + StringPool.COMMA;
	}
}

String timeZoneId = BeanParamUtil.getString(supportLabor, request, "timeZoneId");
%>

<portlet:actionURL name="updateSupportLabor" var="updateSupportLaborURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_labor.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportLaborURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="supportLaborId" type="hidden" value="<%= supportLaborId %>" />
	<aui:input name="supportTeamIds" type="hidden" value="<%= supportTeamIds %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="labor-hours"
	/>

	<liferay-ui:error exception="<%= SupportLaborHourException.class %>" message="the-hours-entered-are-invalid" />
	<liferay-ui:error exception="<%= SupportLaborNameException.class %>" message="please-enter-a-name" />

	<aui:model-context bean="<%= supportLabor %>" model="<%= SupportLabor.class %>" />

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
	</table>

	<br />

	<div>
		<aui:button type="submit" value="save" />

		<c:if test="<%= supportLabor != null %>">
			<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_support_labor_workers.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="supportLaborId" value="<%= String.valueOf(supportLabor.getSupportLaborId()) %>" />
			</portlet:renderURL>

			<a class="btn btn-default" href="<%= HtmlUtil.escape(assignWorkersURL) %>"><liferay-ui:message key="assign-workers" /></a>
		</c:if>

		<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
	</div>

	<c:if test="<%= supportLabor != null %>">
		<br />

		<liferay-ui:tabs
			names="teams"
		/>

		<portlet:renderURL var="selectSupportTeamURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_support_team.jsp" />
			<portlet:param name="callback" value="selectSupportTeam" />
		</portlet:renderURL>

		<%
		String taglibSelectSupportTeam = "var categoryWindow = window.open('" + selectSupportTeamURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<div>
			<aui:button onClick="<%= taglibSelectSupportTeam %>" value="add-team" />
		</div>

		<br />

		<liferay-ui:search-container
			headerNames="name"
			id="supportTeam"
		>
			<liferay-ui:search-container-results
				results="<%= supportTeams %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.SupportTeam"
				escapedModel="<%= true %>"
				keyProperty="supportTeamId"
				modelVar="curSupportTeam"
			>
				<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/admin/edit_support_team.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="supportTeamId" value="<%= String.valueOf(curSupportTeam.getSupportTeamId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					property="name"
				/>

				<liferay-ui:search-container-column-text>
					<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('supportTeamIds', '" + curSupportTeam.getSupportTeamId() + "', '" + renderResponse.getNamespace() + "supportTeamSearchContainer', this);" %>' value="remove" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>
	</c:if>

	<br />

	<liferay-ui:tabs
		names="business-hours"
	/>

	<div class="portlet-msg-info">
		<liferay-ui:message key="if-not-open-on-a-day-leave-opening-and-closing-time-at-1200-am-all-times-local-to-timezone" />
	</div>

	<table class="lfr-table">
	<tr>

		<%
		for (String dayName : OSBDateUtil.DAY_NAMES) {
		%>

			<td>
				<strong><liferay-ui:message key="<%= dayName %>" /></strong>
			</td>

		<%
		}
		%>

	</tr>
	<tr>
		<td colspan="7">
			<liferay-ui:message key="opening-time" />
		</td>
	</tr>
	<tr>

		<%
		for (String shortDayName : OSBDateUtil.SHORT_DAY_NAMES) {
			int value = BeanParamUtil.getInteger(supportLabor, request, shortDayName + "Open");
		%>

			<td>
				<liferay-util:include page="/admin/input_select_half_hour_time.jsp" servletContext="<%= application %>">
					<liferay-util:param name="name" value='<%= shortDayName + "Open" %>' />
					<liferay-util:param name="value" value="<%= String.valueOf(value) %>" />
				</liferay-util:include>
			</td>

		<%
		}
		%>

	</tr>
	<tr>
		<td colspan="7">
			<liferay-ui:message key="closing-time" />
		</td>
	<tr>

		<%
		for (String shortDayName : OSBDateUtil.SHORT_DAY_NAMES) {
			int value = BeanParamUtil.getInteger(supportLabor, request, shortDayName + "Close");
		%>

			<td>
				<liferay-util:include page="/admin/input_select_half_hour_time.jsp" servletContext="<%= application %>">
					<liferay-util:param name="name" value='<%= shortDayName + "Close" %>' />
					<liferay-util:param name="value" value="<%= String.valueOf(value) %>" />
				</liferay-util:include>
			</td>

		<%
		}
		%>

	</tr>
	</table>
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

			<portlet:namespace />addColumn(row, '<input class="btn btn-default" onClick="<portlet:namespace />removeRow(\'' + inputName + '\', \'' + value + '\', \'' + tableId + '\', this);" type="button" value="<liferay-ui:message key="remove" />" />');
		}
	}

	function <portlet:namespace />selectSupportTeam(supportTeamId, supportTeamName) {
		<portlet:namespace />selectRow('supportTeamIds', supportTeamId, '<portlet:namespace />supportTeamSearchContainer', [supportTeamName]);
	}
</aui:script>