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

long supportWorkerId = ParamUtil.getLong(request, "supportWorkerId");

SupportWorker supportWorker = SupportWorkerLocalServiceUtil.getSupportWorker(supportWorkerId);

SupportTeam supportTeam = supportWorker.getSupportTeam();

String severities = StringPool.BLANK;

List<Integer> supportWorkerSeverities = supportWorker.getSeverities();

for (Integer supportWorkerSeverity : supportWorkerSeverities) {
	severities += supportWorkerSeverity + StringPool.COMMA;
}

String components = StringPool.BLANK;

List<Integer> supportWorkerComponents = supportWorker.getComponents();

for (Integer supportWorkerComponent : supportWorkerComponents) {
	components += supportWorkerComponent + StringPool.COMMA;
}

String accountTiers = StringPool.BLANK;

List<Integer> supportWorkerAccountTiers = supportWorker.getAccountTiers();

for (Integer supportWorkerAccountTier : supportWorkerAccountTiers) {
	accountTiers += supportWorkerAccountTier + StringPool.COMMA;
}

boolean hasClockInOutPermission = OSBSupportTeamPermission.contains(permissionChecker, supportTeam);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_support_worker.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("supportWorkerId", String.valueOf(supportWorkerId));
%>

<portlet:actionURL name="updateSupportWorker" var="updateSupportWorkerURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_worker.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportWorkerURL %>" method="post">
	<aui:input name="redirect" type="hidden" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="supportWorkerId" type="hidden" value="<%= supportWorkerId %>" />
	<aui:input name="severities" type="hidden" value="<%= severities %>" />
	<aui:input name="components" type="hidden" value="<%= components %>" />
	<aui:input name="accountTiers" type="hidden" value="<%= accountTiers %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="support-worker"
	/>

	<liferay-ui:error exception="<%= SupportWorkerMaxWorkException.class %>" message="max-work-cannot-equal-0" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>

				<%
				User supportWorkerUser = UserLocalServiceUtil.getUser(supportWorker.getUserId());
				%>

				<%= HtmlUtil.escape(supportWorkerUser.getFullName()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="support-team" />
			</td>
			<td>
				<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_support_team.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="supportTeamId" value="<%= String.valueOf(supportTeam.getSupportTeamId()) %>" /></portlet:renderURL>" id="<portlet:namespace />supportTeamName">
					<%= HtmlUtil.escape(supportTeam.getName()) %>
				</a>

				<portlet:renderURL var="selectSupportTeamURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/admin/select_support_team.jsp" />
					<portlet:param name="callback" value="selectSupportTeam" />
				</portlet:renderURL>

				<%
				String taglibSelectSupportTeam = "var supportTeamWindow = window.open('" + selectSupportTeamURL + "', 'supportTeam', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); supportTeamWindow.focus();";
				%>

				<aui:button onClick="<%= taglibSelectSupportTeam %>" value="change" />

				<input name="<portlet:namespace />supportTeamId" type="hidden" value="<%= supportTeam.getSupportTeamId() %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="status" />
			</td>
			<td>
				<c:if test="<%= supportWorker.getRole() != SupportWorkerConstants.ROLE_WATCHER %>">
					<c:choose>
						<c:when test="<%= hasClockInOutPermission %>">
							<portlet:actionURL name="clockInOut" var="clockInOutURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="supportWorkerId" value="<%= StringUtil.valueOf(supportWorker.getSupportWorkerId()) %>" />
							</portlet:actionURL>

							<div class="toggle-on-off-switch">
								<a class="toggle-on-off-switch-ctrl unlock <%= supportWorker.isClockedIn() ? "on" : "off" %>" href="<%= clockInOutURL %>">
									<span class="toggle-on-off-switch-inner">
										<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="in" /></span>

										<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="on-pto" /></span>
									</span>
								</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="toggle-on-off-switch">
								<span class="toggle-on-off-switch-ctrl <%= supportWorker.isClockedIn() ? "on" : "off" %>">
									<span class="toggle-on-off-switch-inner">
										<span class="toggle-on-off-switch-on txt-b"><liferay-ui:message key="in" /></span>

										<span class="toggle-on-off-switch-off txt-b"><liferay-ui:message key="on-pto" /></span>
									</span>
								</span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="allow-lesa-to-auto-assign-tickets-to-this-user" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= supportWorker %>"
					field="autoAssign"
					model="<%= SupportWorker.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="assigned-work" />
			</td>
			<td>
				<%= numberFormat.format(supportWorker.getAssignedWork()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="max-work" />
			</td>
			<td>
				<aui:input label="" name="maxWork" size="5" type="text" value="<%= numberFormat.format(supportWorker.getMaxWork()) %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="escalation-level" />
			</td>
			<td>
				<aui:select label="" name="escalationLevel">

					<%
					List<ListType> escalationLevelTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL);

					for (ListType escalationLevelType : escalationLevelTypes) {
					%>

						<option <%= (escalationLevelType.getListTypeId() == supportWorker.getEscalationLevel()) ? "selected" : "" %> value="<%= escalationLevelType.getListTypeId() %>"><%= LanguageUtil.get(request, escalationLevelType.getName()) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="default-escalation-level-2-assignment" />
			</td>
			<td>
				<aui:select label="" name="escalationLevel2Role">
					<option <%= (supportWorker.getEscalationLevel2Role() == SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_OTHER) ? "selected" : "" %> value="<%= SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_OTHER %>"><liferay-ui:message key="other" /></option>
					<option <%= (supportWorker.getEscalationLevel2Role() != SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_OTHER) ? "selected" : "" %> value="<%= SupportWorkerConstants.ESCALATION_LEVEL_2_ROLE_PRIMARY %>"><liferay-ui:message key="primary" /></option>
				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="notifications" />
			</td>
			<td>
				<aui:select label="" name="notifications">

					<%
					for (int i = 1; i <= 3; i++) {
					%>

						<option <%= (supportWorker.getNotifications() == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(request, SupportWorkerConstants.getNotificationsLabel(i)) %></option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>

		<c:if test="<%= supportTeam.getSupportLaborId() > 0 %>">
			<tr>
				<td>
					<portlet:renderURL var="supportTeamSupportLaborURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
						<portlet:param name="mvcPath" value="/admin/edit_support_labor.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="supportLaborId" value="<%= String.valueOf(supportTeam.getSupportLaborId()) %>" />
					</portlet:renderURL>

					<a href="<%= supportTeamSupportLaborURL %>">
						<liferay-ui:message key="support-team-labor-hours" />
					</a>
				</td>
				<td>
					<table class="lfr-table">
						<tr>

							<%
							for (int i = 0; i < 7; i++) {
							%>

								<td>
									<a href="<%= supportTeamSupportLaborURL %>">
										<liferay-ui:message key="<%= OSBDateUtil.DAY_NAMES[i] %>" />
									</a>
								</td>

							<%
							}
							%>

						</tr>
						<tr>

							<%
							SupportLabor supportTeamSupportLabor = SupportLaborLocalServiceUtil.getSupportLabor(supportTeam.getSupportLaborId());

							for (int i = 1; i <= 7; i++) {
							%>

								<td>
									<a href="<%= supportTeamSupportLaborURL %>">
										<%= supportTeamSupportLabor.formatDayHours(locale, i) %>
									</a>
								</td>

							<%
							}
							%>

						</tr>
					</table>
				</td>
			</tr>
		</c:if>

		<c:if test="<%= supportWorker.getSupportLaborId() > 0 %>">
			<portlet:renderURL var="supportWorkerSupportLaborURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_support_labor.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="supportLaborId" value="<%= String.valueOf(supportWorker.getSupportLaborId()) %>" />
			</portlet:renderURL>

			<tr>
				<td>
					<a href="<%= supportWorkerSupportLaborURL %>">
						<liferay-ui:message key="support-worker-labor-hours" />
					</a>
				</td>
				<td>
					<table class="lfr-table">
						<tr>

							<%
							for (int i = 0; i < 7; i++) {
							%>

								<td>
									<a href="<%= supportWorkerSupportLaborURL %>">
										<liferay-ui:message key="<%= OSBDateUtil.DAY_NAMES[i] %>" />
									</a>
								</td>

							<%
							}
							%>

						</tr>
						<tr>

							<%
							SupportLabor supportLabor = SupportLaborLocalServiceUtil.getSupportLabor(supportWorker.getSupportLaborId());

							for (int i = 1; i <= 7; i++) {
							%>

								<td>
									<a href="<%= supportWorkerSupportLaborURL %>">
										<%= supportLabor.formatDayHours(locale, i) %>
									</a>
								</td>

							<%
							}
							%>

						</tr>
					</table>
				</td>
			</tr>
		</c:if>
	</table>

	<br />

	<div>
		<aui:button type="submit" value="save" />

		<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
	</div>

	<br />

	<liferay-ui:tabs
		names="other-support-workers-representing-this-user"
	/>

	<br />

	<liferay-ui:search-container
		headerNames="support-team,utilization,severities,components,role"
		id="supportWorkers"
	>

		<%
		List<SupportWorker> userSupportWorkers = SupportWorkerLocalServiceUtil.getUserSupportWorkers(supportWorker.getUserId());

		userSupportWorkers = ListUtil.copy(userSupportWorkers);

		userSupportWorkers.remove(supportWorker);
		%>

		<liferay-ui:search-container-results
			results="<%= userSupportWorkers %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.SupportWorker"
			modelVar="curSupportWorker"
		>
			<portlet:renderURL var="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_support_worker.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="supportWorkerId" value="<%= String.valueOf(curSupportWorker.getSupportWorkerId()) %>" />
			</portlet:renderURL>

			<%
			SupportTeam supportWorkerSupportTeam = SupportTeamLocalServiceUtil.getSupportTeam(curSupportWorker.getSupportTeamId());
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-team"
				value="<%= HtmlUtil.escape(supportWorkerSupportTeam.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="status"
			>
				<c:if test="<%= supportWorker.getRole() != SupportWorkerConstants.ROLE_WATCHER %>">
					<portlet:actionURL name="clockInOut" var="clockInOutURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="supportWorkerId" value="<%= StringUtil.valueOf(supportWorker.getSupportWorkerId()) %>" />
					</portlet:actionURL>

					<div class="clock-in-out-switch">
						<a class="clock-in-out-switch-ctrl <%= supportWorker.isClockedIn() ? "in" : "out" %>" href="<%= clockInOutURL %>">
							<span class="clock-in-out-switch-inner">
								<span class="clock-in-out-switch-in txt-b"><liferay-ui:message key="in" /></span>

								<span class="clock-in-out-switch-out txt-b"><liferay-ui:message key="on-pto" /></span>
							</span>
						</a>
					</div>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="utilization"
			>
				<%= numberFormat.format(MathUtil.format((curSupportWorker.getAssignedWork() / curSupportWorker.getMaxWork()) * 100, 1, 1)) %>%

				(<%= numberFormat.format(curSupportWorker.getAssignedWork()) %>/<%= numberFormat.format(curSupportWorker.getMaxWork()) %>)
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="severities"
			>

				<%
				List<Integer> curSeverities = curSupportWorker.getSeverities();

				for (int i = 0; i < curSeverities.size(); i++) {
					Integer curSeverity = curSeverities.get(i);
				%>

					<%= LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(curSeverity)) %>

					<%= ((i + 1) < curSeverities.size()) ? "<br />" : "" %>

				<%
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="components"
			>

				<%
				List<Integer> curComponents = curSupportWorker.getComponents();

				for (int i = 0; i < curComponents.size(); i++) {
					Integer curComponent = curComponents.get(i);
				%>

					<%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(curComponent)) %>

					<%= ((i + 1) < curComponents.size()) ? "<br />" : "" %>

				<%
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="project-tiers"
			>

				<%
				List<Integer> curAccountTiers = curSupportWorker.getAccountTiers();

				for (int i = 0; i < curAccountTiers.size(); i++) {
					Integer curAccountTier = curAccountTiers.get(i);
				%>

					<%= LanguageUtil.get(request, AccountEntryConstants.getTierLabel(curAccountTier)) %>

					<%= ((i + 1) < curAccountTiers.size()) ? "<br />" : "" %>

				<%
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="role"
				value="<%= LanguageUtil.get(request, curSupportWorker.getRoleLabel()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>

	<br />

	<liferay-ui:tabs
		names="severities"
	/>

	<portlet:renderURL var="selectSeverityURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/admin/select_severity.jsp" />
	</portlet:renderURL>

	<%
	String taglibSelectSeverity = "var categoryWindow = window.open('" + selectSeverityURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
	%>

	<div>
		<aui:button onClick="<%= taglibSelectSeverity %>" value="add-severity" />
	</div>

	<br />

	<liferay-ui:search-container
		headerNames="severity,,"
		id="severity"
	>
		<liferay-ui:search-container-results
			results="<%= supportWorkerSeverities %>"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Integer"
			modelVar="severity"
		>
			<liferay-ui:search-container-column-text
				name="severity"
			>
				<%= LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(severity)) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('severities', '" + severity + "', '" + renderResponse.getNamespace() + "severitySearchContainer', this);" %>' value="remove" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>

	<br />

	<liferay-ui:tabs
		names="components"
	/>

	<portlet:renderURL var="selectComponentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/admin/select_component.jsp" />
	</portlet:renderURL>

	<%
	String taglibSelectComponent = "var categoryWindow = window.open('" + selectComponentURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
	%>

	<div>
		<aui:button onClick="<%= taglibSelectComponent %>" value="add-component" />
	</div>

	<br />

	<liferay-ui:search-container
		headerNames="component,,"
		id="component"
	>
		<liferay-ui:search-container-results
			results="<%= supportWorkerComponents %>"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Integer"
			modelVar="component"
		>
			<liferay-ui:search-container-column-text
				name="component"
			>
				<%= LanguageUtil.get(request, TicketEntryConstants.getComponentLabel(component)) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('components', '" + component + "', '" + renderResponse.getNamespace() + "componentSearchContainer', this);" %>' value="remove" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>

	<br />

	<liferay-ui:tabs
		names="project-tiers"
	/>

	<portlet:renderURL var="selectAccountTierURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/admin/select_account_tier.jsp" />
	</portlet:renderURL>

	<%
	String taglibSelectAccountTier = "var categoryWindow = window.open('" + selectAccountTierURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
	%>

	<div>
		<aui:button onClick="<%= taglibSelectAccountTier %>" value="add-project-tier" />
	</div>

	<br />

	<liferay-ui:search-container
		headerNames="account-tier,,"
		id="accountTier"
	>
		<liferay-ui:search-container-results
			results="<%= supportWorkerAccountTiers %>"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Integer"
			modelVar="accountTier"
		>
			<liferay-ui:search-container-column-text
				name="project-tier"
			>
				<%= LanguageUtil.get(request, AccountEntryConstants.getTierLabel(accountTier)) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('accountTiers', '" + accountTier + "', '" + renderResponse.getNamespace() + "accountTierSearchContainer', this);" %>' value="remove" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />addColumn(row, html) {
		var cell = row.insertCell(-1);

		cell.innerHTML = html;
	}

	function <portlet:namespace />removeRow(inputName, value, tableId, row) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		values = values.replace(value + ',', '');

		document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

		var table = document.getElementById(tableId).getElementsByTagName('tbody')[0];

		table.removeChild(row.parentNode.parentNode);
	}

	function <portlet:namespace />selectRow(inputName, value, tableId, columnValues) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		if (values.indexOf(value + ',') == -1) {
			values += value + ',';

			document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

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
		document.<portlet:namespace />fm.<portlet:namespace />supportTeamId.value = supportTeamId;

		var nameEl = document.getElementById('<portlet:namespace />supportTeamName');

		nameEl.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_support_team.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:renderURL>&<portlet:namespace />supportTeamId=' + supportTeamId;
		nameEl.innerHTML = supportTeamName + '&nbsp;';
	}
</aui:script>