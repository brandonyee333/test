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

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/admin/view.jsp");
	portletURL.setParameter("tabs1", "support");
	portletURL.setParameter("tabs2", "teams");

	backURL = portletURL.toString();
}

long supportTeamId = ParamUtil.getLong(request, "supportTeamId");

SupportTeam supportTeam = null;

try {
	supportTeam = SupportTeamLocalServiceUtil.getSupportTeam(supportTeamId);
}
catch (NoSuchSupportTeamException nsste) {
}

long parentSupportTeamId = BeanParamUtil.getLong(supportTeam, request, "parentSupportTeamId");

SupportTeam parentSupportTeam = null;

try {
	parentSupportTeam = SupportTeamLocalServiceUtil.getSupportTeam(parentSupportTeamId);
}
catch (NoSuchSupportTeamException nsste) {
}

long supportLaborId = BeanParamUtil.getLong(supportTeam, request, "supportLaborId");

long locationSupportRegionId = BeanParamUtil.getLong(supportTeam, request, "locationSupportRegionId");

String childSupportTeamIds = StringPool.BLANK;

List<SupportTeam> childSupportTeams = null;

if (supportTeam != null) {
	childSupportTeams = supportTeam.getChildSupportTeams();

	for (SupportTeam curSupportTeam : childSupportTeams) {
		childSupportTeamIds += curSupportTeam.getSupportTeamId() + StringPool.COMMA;
	}
}

String accountEntryIds = StringPool.BLANK;

List<AccountEntry> accountEntries = null;

if (supportTeam != null) {
	accountEntries = supportTeam.getAccountEntries();

	for (AccountEntry accountEntry : accountEntries) {
		accountEntryIds += accountEntry.getAccountEntryId() + StringPool.COMMA;
	}
}

String languageIds = StringPool.BLANK;

List<String> supportTeamLanguageIds = null;

if (supportTeam != null) {
	supportTeamLanguageIds = supportTeam.getLanguageIds();

	for (String supportTeamLanguageId : supportTeamLanguageIds) {
		languageIds += supportTeamLanguageId + StringPool.COMMA;
	}
}

String supportRegionIds = StringPool.BLANK;

List<SupportRegion> supportTeamSupportRegions = null;

if (supportTeam != null) {
	supportTeamSupportRegions = supportTeam.getSupportRegions();

	for (SupportRegion supportRegion : supportTeamSupportRegions) {
		supportRegionIds += supportRegion.getSupportRegionId() + StringPool.COMMA;
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_support_team.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("supportTeamId", String.valueOf(supportTeamId));
%>

<portlet:actionURL name="updateSupportTeam" var="updateSupportTeamURL">
	<portlet:param name="mvcPath" value="/admin/edit_support_team.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSupportTeamURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="supportTeamId" type="hidden" value="<%= supportTeamId %>" />
	<aui:input name="accountEntryIds" type="hidden" value="<%= accountEntryIds %>" />
	<aui:input name="childSupportTeamIds" type="hidden" value="<%= childSupportTeamIds %>" />
	<aui:input name="languageIds" type="hidden" value="<%= languageIds %>" />
	<aui:input name="supportRegionIds" type="hidden" value="<%= supportRegionIds %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="support-team"
	/>

	<liferay-ui:error exception="<%= DuplicateSupportTeamException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= SupportTeamLocationException.class %>" message="please-select-a-valid-physical-location-support-region" />
	<liferay-ui:error exception="<%= SupportTeamNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= SupportTeamSupportLaborException.class %>" message="please-select-a-valid-set-of-labor-hours" />

	<aui:model-context bean="<%= supportTeam %>" model="<%= SupportTeam.class %>" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="parent-team" />
			</td>
			<td>
				<aui:select label="" name="parentSupportTeamId">
					<aui:option value="0"></aui:option>

					<%
					List<SupportTeam> supportTeams = SupportTeamLocalServiceUtil.getSupportTeams(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportTeam curSupportTeam : supportTeams) {
						if (curSupportTeam.getSupportTeamId() == supportTeamId) {
							continue;
						}
					%>

						<aui:option selected="<%= parentSupportTeamId == curSupportTeam.getSupportTeamId() %>" value="<%= curSupportTeam.getSupportTeamId() %>"><%= HtmlUtil.escape(curSupportTeam.getName()) %></aui:option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
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
				<liferay-ui:message key="type" />
			</td>
			<td>
				<aui:select label="" name="type">
					<aui:option value="0"><liferay-ui:message key="normal" /></aui:option>

					<%
					int type = 0;

					if (supportTeam != null) {
						type = supportTeam.getType();
					}
					%>

					<aui:option selected="<%= type == SupportTeamConstants.TYPE_PLATINUM_CRITICAL %>" value="1"><liferay-ui:message key="platinum-critical" /></aui:option>
				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="labor-hours" />
			</td>
			<td>
				<aui:select label="" name="supportLaborId">
					<aui:option value="0"></aui:option>

					<%
					List<SupportLabor> supportLabors = SupportLaborLocalServiceUtil.getSupportLabors(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportLabor supportLabor : supportLabors) {
					%>

						<aui:option selected="<%= supportLaborId == supportLabor.getSupportLaborId() %>" value="<%= supportLabor.getSupportLaborId() %>"><%= HtmlUtil.escape(supportLabor.getName()) %></aui:option>

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="physical-location-support-region" />
			</td>
			<td>
				<aui:select label="" name="locationSupportRegionId">
					<aui:option value="0"></aui:option>

					<%
					List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (SupportRegion curSupportRegion : supportRegions) {
					%>

						<aui:option selected="<%= locationSupportRegionId == curSupportRegion.getSupportRegionId() %>" value="<%= curSupportRegion.getSupportRegionId() %>"><%= HtmlUtil.escape(curSupportRegion.getName()) %></aui:option>

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

		<c:if test="<%= supportTeam != null %>">
			<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_support_team_workers.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="supportTeamId" value="<%= String.valueOf(supportTeam.getSupportTeamId()) %>" />
			</portlet:renderURL>

			<a class="btn btn-default" href="<%= HtmlUtil.escape(assignWorkersURL) %>"><liferay-ui:message key="assign-workers" /></a>

			<portlet:renderURL var="createChildTeamURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_support_team.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="parentSupportTeamId" value="<%= String.valueOf(supportTeam.getSupportTeamId()) %>" />
			</portlet:renderURL>

			<a class="btn btn-default" href="<%= HtmlUtil.escape(createChildTeamURL) %>"><liferay-ui:message key="create-child-team" /></a>
		</c:if>

		<a class="btn btn-default" href="<%= HtmlUtil.escape(backURL) %>"><liferay-ui:message key="cancel" /></a>
	</div>

	<c:if test="<%= supportTeam != null %>">
		<br />

		<liferay-ui:tabs
			names="child-teams"
		/>

		<portlet:renderURL var="selectChildSupportTeamURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_support_team.jsp" />
			<portlet:param name="callback" value="selectChildSupportTeam" />
			<portlet:param name="supportTeamId" value="<%= String.valueOf(supportTeamId) %>" />
		</portlet:renderURL>

		<%
		String taglibSelectChildSupportTeam = "var categoryWindow = window.open('" + selectChildSupportTeamURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<div>
			<aui:button onClick="<%= taglibSelectChildSupportTeam %>" value="add-child-team" />
		</div>

		<br />

		<liferay-ui:search-container
			headerNames="name,utilization"
			id="childSupportTeam"
		>
			<liferay-ui:search-container-results
				results="<%= childSupportTeams %>"
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

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="utilization"
				>
					<%= numberFormat.format(MathUtil.format((curSupportTeam.getAssignedWork() / curSupportTeam.getMaxWork()) * 100, 1, 1)) %>%

					(<%= numberFormat.format(curSupportTeam.getAssignedWork()) %>/<%= numberFormat.format(curSupportTeam.getMaxWork()) %>)
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('childSupportTeamIds', '" + curSupportTeam.getSupportTeamId() + "', '" + renderResponse.getNamespace() + "childSupportTeamSearchContainer', this);" %>' value="remove" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>

		<br />

		<liferay-ui:tabs
			names="projects"
		/>

		<portlet:renderURL var="selectAccountEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_account_entry.jsp" />
			<portlet:param name="callback" value="selectAccountEntry" />
		</portlet:renderURL>

		<%
		String taglibSelectAccountEntry = "var categoryWindow = window.open('" + selectAccountEntryURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<div>
			<aui:button onClick="<%= taglibSelectAccountEntry %>" value="add-project" />
		</div>

		<br />

		<liferay-ui:search-container
			headerNames="name,code,,"
			id="accountEntry"
		>
			<liferay-ui:search-container-results
				results="<%= accountEntries %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AccountEntry"
				escapedModel="<%= true %>"
				keyProperty="accountEntryId"
				modelVar="accountEntry"
			>
				<liferay-ui:search-container-column-text
					property="name"
				/>

				<liferay-ui:search-container-column-text
					property="code"
				/>

				<liferay-ui:search-container-column-text>
					<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('accountEntryIds', '" + accountEntry.getAccountEntryId() + "', '" + renderResponse.getNamespace() + "accountEntrySearchContainer', this);" %>' value="remove" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>

		<br />

		<liferay-ui:tabs
			names="languages"
		/>

		<portlet:renderURL var="selectLanguageURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_language.jsp" />
		</portlet:renderURL>

		<%
		String taglibSelectLanguage = "var categoryWindow = window.open('" + selectLanguageURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<div>
			<aui:button onClick="<%= taglibSelectLanguage %>" value="add-language" />
		</div>

		<br />

		<liferay-ui:search-container
			headerNames="language,,"
			id="language"
		>
			<liferay-ui:search-container-results
				results="<%= supportTeamLanguageIds %>"
			/>

			<liferay-ui:search-container-row
				className="java.lang.String"
				modelVar="languageId"
			>
				<liferay-ui:search-container-column-text
					name="language"
				>
					<%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(languageId)) %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('languageIds', '" + languageId + "', '" + renderResponse.getNamespace() + "languageSearchContainer', this);" %>' value="remove" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
		</liferay-ui:search-container>

		<br />

		<liferay-ui:tabs
			names="assigned-support-regions"
		/>

		<portlet:renderURL var="selectSupportRegionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_support_region.jsp" />
			<portlet:param name="callback" value="selectSupportRegion" />
		</portlet:renderURL>

		<%
		String taglibSelectSupportRegion = "var categoryWindow = window.open('" + selectSupportRegionURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
		%>

		<div>
			<aui:button onClick="<%= taglibSelectSupportRegion %>" value="add-support-region" />
		</div>

		<br />

		<liferay-ui:search-container
			headerNames="name,,"
			id="supportRegion"
		>
			<liferay-ui:search-container-results
				results="<%= supportTeamSupportRegions %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.SupportRegion"
				escapedModel="<%= true %>"
				keyProperty="supportRegionId"
				modelVar="supportRegion"
			>
				<liferay-ui:search-container-column-text
					property="name"
				/>

				<liferay-ui:search-container-column-text>
					<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('supportRegionIds', '" + supportRegion.getSupportRegionId() + "', '" + renderResponse.getNamespace() + "supportRegionSearchContainer', this);" %>' value="remove" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				paginate="<%= false %>"
			/>
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

	function <portlet:namespace />selectAccountEntry(accountEntryId, accountEntryName, accountEntryCode) {
		<portlet:namespace />selectRow('accountEntryIds', accountEntryId, '<portlet:namespace />accountEntrySearchContainer', [accountEntryName, accountEntryCode]);
	}

	function <portlet:namespace />selectChildSupportTeam(supportTeamId, supportTeamName) {
		<portlet:namespace />selectRow('childSupportTeamIds', supportTeamId, '<portlet:namespace />childSupportTeamSearchContainer', [supportTeamName]);
	}

	function <portlet:namespace />selectSupportRegion(supportRegionId, supportRegionName) {
		<portlet:namespace />selectRow('supportRegionIds', supportRegionId, '<portlet:namespace />supportRegionSearchContainer', [supportRegionName]);
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
</aui:script>