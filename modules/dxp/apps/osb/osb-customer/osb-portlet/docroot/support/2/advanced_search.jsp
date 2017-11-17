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
String searchTab = ParamUtil.getString(request, "searchTab", "tickets");

int cur = ParamUtil.getInteger(request, "cur");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
long searchFilterId = ParamUtil.getLong(request, "searchFilterId");
String ticketDisplayId = ParamUtil.getString(request, "ticketDisplayId");

String defaultKeywords = StringPool.BLANK;
boolean hasSearchFilter = false;

if (searchFilterId > 0) {
	SearchFilter searchFilter = SearchFilterLocalServiceUtil.getSearchFilter(searchFilterId);

	JSONObject jsonObject = JSONFactoryUtil.createJSONObject(searchFilter.getFilter());

	defaultKeywords = jsonObject.getString("keywords");
	orderByCol = jsonObject.getString("orderByCol");
	orderByType = jsonObject.getString("orderByType");

	hasSearchFilter = true;
}
else if (Validator.isNotNull(ticketDisplayId)) {
	defaultKeywords = ticketDisplayId;
}

String keywords = ParamUtil.getString(request, "keywords", defaultKeywords);

boolean ticketWorker = liferayIncOrg || supportPartnerWorker;

AccountEntry accountEntry = null;

boolean singleAccount = false;

if (!ticketWorker) {
	List<AccountEntry> accountEntries = ListUtil.copy(AccountEntryLocalServiceUtil.getUserAccountEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS));

	Iterator<AccountEntry> itr = accountEntries.iterator();

	while (itr.hasNext()) {
		AccountEntry curAccountEntry = itr.next();

		if (curAccountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			itr.remove();
		}
	}

	if (accountEntries.size() == 1) {
		accountEntry = accountEntries.get(0);

		singleAccount = true;
	}
}

List<SupportTeam> supportTeams = SupportTeamLocalServiceUtil.getUserRoleSupportTeams(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER);

boolean supportManager = false;

if (!supportTeams.isEmpty() || RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {
	supportManager = true;
}
%>

<div class="margin-container">
	<div class="page-title">
		<liferay-ui:message key="search" />
	</div>

	<aui:input cssClass="advanced-search keywords" label="" name="keywords" onchange='<%= renderResponse.getNamespace() + "updateSearchResults();" %>' placeholder="search-lesa" value="keywords" />
	<aui:input name="cur" type="hidden" value="<%= cur %>" />
	<aui:input name="orderByCol" type="hidden" value="<%= orderByCol %>" />
	<aui:input name="orderByType" type="hidden" value="<%= orderByType %>" />

	<div class="advanced-search tab-view" id="<portlet:namespace />advancedSearchTabs">
		<div class="tabs" id="<portlet:namespace />advancedSearchTabHeaders">
			<div>
				<span class="first" id="<portlet:namespace />tickets" onClick="<portlet:namespace />loadTab('tickets');" searchTab="ticket">
					<liferay-ui:message key="tickets" />

					<span class="count" id="<portlet:namespace />ticketTabCount">(-)</span>
				</span>

				<c:if test="<%= liferayIncOrg || supportPartnerWorker %>">
					<span id="<portlet:namespace />accounts" onClick="<portlet:namespace />loadTab('accounts');" searchTab="account">
						<liferay-ui:message key="projects" />

						<span class="count" id="<portlet:namespace />accountTabCount">(-)</span>
					</span>
					<span id="<portlet:namespace />feedback" onClick="<portlet:namespace />loadTab('feedback');" searchTab="feedback">
						<liferay-ui:message key="feedback" />

						<span class="count" id="<portlet:namespace />feedbackTabCount">(-)</span>
					</span>
				</c:if>

				<c:if test="<%= liferayIncOrg %>">
					<span id="<portlet:namespace />partners" onClick="<portlet:namespace />loadTab('partners');" searchTab="partner">
						<liferay-ui:message key="partners" />

						<span class="count" id="<portlet:namespace />partnerTabCount">(-)</span>
					</span>
				</c:if>
			</div>
		</div>

		<div class="tab-content" id="<portlet:namespace />advancedSearchTabContent"></div>
	</div>
</div>

<c:if test="<%= supportManager %>">
	<liferay-util:include page="/common/dialog.jsp" servletContext="<%= application %>">
		<liferay-util:param name="centered" value="<%= Boolean.TRUE.toString() %>" />
		<liferay-util:param name="close" value="<%= Boolean.FALSE.toString() %>" />
		<liferay-util:param name="cssClass" value="edit-ticket-dialog" />
		<liferay-util:param name="draggable" value="<%= Boolean.FALSE.toString() %>" />
		<liferay-util:param name="id" value="1" />
		<liferay-util:param name="modal" value="<%= Boolean.TRUE.toString() %>" />
		<liferay-util:param name="mvcPath" value="/support/2/advanced_search/bulk_edit_ticket_dialog.jsp" />
		<liferay-util:param name="title" value='<%= LanguageUtil.get(request, "bulk-edit") %>' />
		<liferay-util:param name="width" value="760" />
	</liferay-util:include>
</c:if>

<aui:script>
	function <portlet:namespace />checkAll(id, check) {
		var A = AUI();

		A.all('#<portlet:namespace />' + id + ' input[type="checkbox"]').set('checked', check);

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />checkOnClick(element, event) {
		var node = <portlet:namespace />getSelectionNode();

		if (node && (element != node) && element.contains(node)) {
			event.preventDefault();
			event.stopPropagation();
		}
	}

	function <portlet:namespace />cleanCommas(inputString) {
		inputString = inputString.trim(',');

		inputString = inputString.replace(/,{2,}/g, ',');

		return inputString;
	}

	function <portlet:namespace />columnSort(orderByCol, curOrderByCol, orderByType) {
		var orderByColVal = '';
		var orderByTypeVal = '';

		if (orderByCol != curOrderByCol) {
			orderByColVal = orderByCol;

			orderByTypeVal = 'asc';
		}
		else if ((orderByCol == curOrderByCol) && (orderByType == 'asc')) {
			orderByColVal = orderByCol;

			orderByTypeVal = 'desc';
		}

		document.getElementById('<portlet:namespace />orderByCol').value = orderByColVal;
		document.getElementById('<portlet:namespace />orderByType').value = orderByTypeVal;

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />compileSearchParams(searchTab) {
		var returnURLPiece = '';

		var keywords = document.getElementById('<portlet:namespace />keywords').value;

		if (keywords.length > 0) {
			returnURLPiece = '&<portlet:namespace />keywords=' + keywords;
		}

		var cur = document.getElementById('<portlet:namespace />cur').value;

		returnURLPiece += '&<portlet:namespace />cur=' + cur;

		var orderByCol = document.getElementById('<portlet:namespace />orderByCol').value;

		returnURLPiece += '&<portlet:namespace />orderByCol=' + orderByCol;

		var orderByType = document.getElementById('<portlet:namespace />orderByType').value;

		returnURLPiece += '&<portlet:namespace />orderByType=' + orderByType;

		var inputs = document.querySelectorAll('.' + searchTab + '.advanced-search .search-param .checkbox input, .' + searchTab + '.advanced-search .search-param-config input, .' + searchTab + '.advanced-search .search-param-config select, .' + searchTab + '.advanced-search .search-param-config textarea');

		for (var i = 0; i < inputs.length; i++) {
			var element = inputs[i];

			if (!element.value || !element.name) {
				continue;
			}

			if ((element.tagName == 'INPUT') && ((element.type == 'checkbox') || (element.type == 'radio')) && !element.checked) {
				continue;
			}

			returnURLPiece += '&' + element.name + '=' + element.value;
		}

		return returnURLPiece;
	}

	function <portlet:namespace />deleteSearchFilter(searchFilterId, searchFilterName) {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-your-saved-custom-filter-x-permanently', searchFilterName))) {
			<portlet:renderURL var="redirectURL">
				<portlet:param name="mvcPath" value="/support/2/advanced_search.jsp" />
			</portlet:renderURL>

			var deleteURL = '<portlet:actionURL name="deleteSearchFilter"><portlet:param name="mvcPath" value="/support/2/advanced_search.jsp" /><portlet:param name="redirect" value="<%= redirectURL %>" /></portlet:actionURL>';

			deleteURL += '&<portlet:namespace />searchFilterId=' + encodeURIComponent(searchFilterId);

			window.location.href = deleteURL;
		}
	}

	function <portlet:namespace />disableDatePickers() {
		var datePickerButtons = document.querySelectorAll('.aui-datepicker-button-wrapper');

		for (var i = 0; i < datePickerButtons.length; i++) {
			datePickerButtons[i].parentElement.removeChild(datePickerButtons[i]);
		}
	}

	function <portlet:namespace />doUpdateSearchFilter(searchFilterId, searchFilterNameParam) {
		var updateURL = '<portlet:actionURL name="updateSearchFilter"><portlet:param name="mvcPath" value="/support/2/advanced_search.jsp" /><portlet:param name="searchTab" value="tickets" /><portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(TicketEntry.class.getName())) %>" /></portlet:actionURL>';

		updateURL += '&<portlet:namespace />searchFilterId=' + encodeURIComponent(searchFilterId);
		updateURL += searchFilterNameParam;

		updateURL += <portlet:namespace />compileSearchParams('ticket');

		window.location.href = updateURL;
	}

	function <portlet:namespace />exportTicketSearchResults(selection) {
		var ticketTabCount = document.getElementById('<portlet:namespace />ticketTabCount').innerHTML;

		if (ticketTabCount.match(/\d+/g)[0] > 5000) {
			return;
		}

		<portlet:resourceURL id="exportTicketSearchResults" var="exportTicketSearchResultsURL">
			<portlet:param name="advancedSearch" value="<%= Boolean.TRUE.toString() %>" />
		</portlet:resourceURL>

		var exportTicketSearchResultsURL = '<%= exportTicketSearchResultsURL %>&<portlet:namespace />export-selection=' + selection;

		exportTicketSearchResultsURL += <portlet:namespace />compileSearchParams('ticket');

		window.open(exportTicketSearchResultsURL);
	}

	function <portlet:namespace />getSelectionNode() {
		var selection = window.getSelection();

		if (selection.toString() != '') {
			return selection.anchorNode;
		}
	}

	function <portlet:namespace />loadBulkEditToggle(searchTab) {
		var A = AUI();

		var bulkEditToggle = A.one('#<portlet:namespace />bulkEditToggle');

		if (searchTab == 'tickets') {
			var multipleTicketSelect = A.one('#<portlet:namespace />multipleTicketSelect');

			multipleTicketSelect.on(
				'click',
				function() {
					multipleTicketSelect.toggleClass('open-multiple-ticket-select-menu');
				}
			);

			multipleTicketSelect.on(
				'clickoutside',
				function() {
					multipleTicketSelect.removeClass('open-multiple-ticket-select-menu');
				}
			);

			bulkEditToggle.show();
		}
		else {
			bulkEditToggle.hide();
		}
	}

	function <portlet:namespace />loadEventListeners() {
		var dateSelectors = document.querySelectorAll('.search-param-config select');

		for (var i = 0; i < dateSelectors.length; i++) {
			dateSelectors[i].onchange = function() {
				<portlet:namespace />updateSearchResults();
			};
		}
	}

	function <portlet:namespace />loadExportToggle() {
		var A = AUI();

		var exportMenu = A.one('#<portlet:namespace />exportMenu');

		exportMenu.on(
			'click',
			function() {
				exportMenu.toggleClass('open-drop-down');
			}
		);

		exportMenu.on(
			'clickoutside',
			function() {
				exportMenu.removeClass('open-drop-down');
			}
		);
	}

	function <portlet:namespace />multipleTicketSelect(selection) {
		var A = AUI();

		var checkmark = A.one('.multiple-ticket-checkmark');
		var ticketRows = A.all('#<portlet:namespace />advancedSearchResultsContent .ticket-row');
		var tickets = A.all('#<portlet:namespace />advancedSearchResultsContent input[type="checkbox"]');

		if (selection == 'all') {
			checkmark.show();
			ticketRows.addClass('selected');
			tickets.set('checked', true);
		}
		else if (selection == 'none') {
			checkmark.hide();
			ticketRows.removeClass('selected');
			tickets.set('checked', false);
		}
	}

	function <portlet:namespace />openBulkEditDialog() {
		var checked = document.querySelectorAll('input[type="checkbox"]:checked.bulk-edit-checkbox');
		var ticketEntryIds = document.getElementById('<portlet:namespace />ticketEntryIds');

		for (var i = 0; i < checked.length; i++) {
			var element = checked[i];

			var ticketEntryId = element.id.replace('<portlet:namespace />', '');

			if (ticketEntryIds.value == '') {
				ticketEntryIds.value = ticketEntryId;
			}
			else if (ticketEntryIds.value.indexOf(ticketEntryId) < 0) {
				ticketEntryIds.value += ',' + ticketEntryId;
			}
		}

		var disclaimer = document.getElementById('<portlet:namespace />disclaimer');

		disclaimer.innerHTML = disclaimer.innerHTML.replace(/[\d]+/g, checked.length);

		<portlet:namespace />openDialog(1);
	}

	function <portlet:namespace/>paginateSearchResults(cur) {
		document.getElementById('<portlet:namespace />cur').value = cur;

		<portlet:namespace/>updateSearchResults();
	}

	function <portlet:namespace />removeAccountEntry(element) {
		var databaseId = element.id;

		var hiddenInput = document.getElementById('<portlet:namespace />accountEntryIds');

		if (hiddenInput.value.indexOf(databaseId) > -1) {
			var values = hiddenInput.value.split(',');

			values.splice(values.indexOf(databaseId), 1);

			var newValue = <portlet:namespace />cleanCommas(values.join());

			hiddenInput.value = newValue;

			var selectedList = document.getElementById('<portlet:namespace />accountEntryIdsList');

			selectedList.removeChild(element);
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />removeAccountWorker(element) {
		var databaseKey = element.id;
		var role = null;
		var userId = null;

		if (databaseKey.indexOf(':') > 0) {
			role = databaseKey.substring(databaseKey.indexOf(':') + 1, databaseKey.length);
			userId = databaseKey.substring(0, databaseKey.indexOf(':'));
		}

		var hiddenIndexTrackerInput = document.getElementById('<portlet:namespace />accountWorkers');

		if (hiddenIndexTrackerInput.value.indexOf(databaseKey) > -1) {
			var indexTrackerValues = hiddenIndexTrackerInput.value.split(',');

			var valueIndex = indexTrackerValues.indexOf(databaseKey);

			indexTrackerValues.splice(valueIndex, 1);

			hiddenIndexTrackerInput.value = <portlet:namespace />cleanCommas(indexTrackerValues.join());

			var hiddenUserIdInput = document.getElementById('<portlet:namespace />accountWorkerUserIds');

			var userIdValues = hiddenUserIdInput.value.split(',');

			userIdValues.splice(userIdValues.indexOf(userId), 1);

			hiddenUserIdInput.value = <portlet:namespace />cleanCommas(userIdValues.join());

			var hiddenRolesInput = document.getElementById('<portlet:namespace />accountWorkerRoles');

			var roleValues = hiddenRolesInput.value.split(',');

			roleValues.splice(roleValues.indexOf(role), 1);

			hiddenRolesInput.value = <portlet:namespace />cleanCommas(roleValues.join());

			var selectedList = document.getElementById('<portlet:namespace />accountWorkersList');

			selectedList.removeChild(element);
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />removeAssignedTo(element, assignedType) {
		var databaseId = element.id;

		if (element.id.indexOf('-') > 0) {
			assignedType = databaseId.substring(0, databaseId.indexOf('-'));

			databaseId = databaseId.substring(databaseId.indexOf('-') + 1, databaseId.length);
		}

		var hiddenInput = document.getElementById('<portlet:namespace />' + assignedType + 's');

		if (hiddenInput.value.indexOf(databaseId) > -1) {
			var values = hiddenInput.value.split(',');

			values.splice(values.indexOf(databaseId), 1);

			var newValue = <portlet:namespace />cleanCommas(values.join());

			hiddenInput.value = newValue;

			var selectedList = document.getElementById('<portlet:namespace />' + assignedType + 'sList');

			selectedList.removeChild(element);
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />selectAccountEntry(accountEntryId, accountEntryName) {
		var accountEntryIds = document.getElementById('<portlet:namespace />accountEntryIds');

		if (accountEntryIds.value.indexOf(accountEntryId) < 0) {
			if (accountEntryIds.value == '') {
				accountEntryIds.value = accountEntryId;
			}
			else {
				accountEntryIds.value += ',' + accountEntryId;
			}

			var newSpan = document.createElement('span');

			newSpan.id = accountEntryId;
			newSpan.innerHTML = accountEntryName;

			newSpan.onclick = function() {
				<portlet:namespace />removeAccountEntry(this);
			};

			var selectedList = document.getElementById('<portlet:namespace />accountEntryIdsList');

			selectedList.appendChild(newSpan);
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />selectAccountWorker(assignedId, assignedName, assignedRole, assignedRoleName) {
		var hiddenIndexTrackerInput = document.getElementById('<portlet:namespace />accountWorkers');

		var databaseKey = assignedId + ':' + assignedRole;

		if (hiddenIndexTrackerInput.value.indexOf(databaseKey) < 0) {
			var hiddenUserIdInput = document.getElementById('<portlet:namespace />accountWorkerUserIds');

			var hiddenRoleInput = document.getElementById('<portlet:namespace />accountWorkerRoles');

			if (hiddenIndexTrackerInput.value == '') {
				hiddenIndexTrackerInput.value = databaseKey;
				hiddenUserIdInput.value = assignedId;
				hiddenRoleInput.value = assignedRole;
			}
			else {
				hiddenIndexTrackerInput.value += ',' + databaseKey;
				hiddenUserIdInput.value += ',' + assignedId;
				hiddenRoleInput.value += ',' + assignedRole;
			}

			var newSpan = document.createElement('span');

			newSpan.id = databaseKey;
			newSpan.innerHTML = assignedRoleName + ': ' + assignedName;

			newSpan.onclick = function() {
				<portlet:namespace />removeAccountWorker(this);
			};

			var selectedList = document.getElementById('<portlet:namespace />accountWorkersList');

			selectedList.appendChild(newSpan);
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />selectAssignedTo(assignedId, assignedName) {
		var assignedType = 'assignedUserId';

		var databaseId = assignedId;

		if (assignedId.indexOf('-') > 0) {
			assignedType = assignedId.substring(0, assignedId.indexOf('-'));

			databaseId = assignedId.substring(assignedId.indexOf('-') + 1, assignedId.length);
		}

		var hiddenInput = document.getElementById('<portlet:namespace />' + assignedType + 's');

		if (hiddenInput.value.indexOf(databaseId) < 0) {
			if (hiddenInput.value == '') {
				hiddenInput.value = databaseId;
			}
			else {
				hiddenInput.value += ',' + databaseId;
			}

			var newSpan = document.createElement('span');

			newSpan.id = assignedId;
			newSpan.innerHTML = assignedName;

			newSpan.onclick = function() {
				<portlet:namespace />removeAssignedTo(this, assignedType);
			};

			var selectedList = document.getElementById('<portlet:namespace />' + assignedType + 'sList');

			selectedList.appendChild(newSpan);
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />selectText(element, event) {
		event.stopImmediatePropagation();
		event.preventDefault();

		element.draggable = false;

		window.getSelection().selectAllChildren(element);
	}

	function <portlet:namespace />selectTicket(element) {
		var parentDiv = element.parentNode;
		var ticketRow = parentDiv.parentNode;

		if (element.checked) {
			ticketRow.classList.add('selected');
		}
		else {
			ticketRow.classList.remove('selected');
		}
	}

	function <portlet:namespace />setUpThreeDotMenus() {
		var A = AUI();

		A.all('.three-dot-icon').each(
			function(icon) {
				var event = A.Event.getListeners(icon, 'click');

				var parent = icon.get('parentNode');

				if (!event) {
					icon.on(
						'click',
						function() {
							parent.toggleClass('open-drop-down');
						}
					);

					icon.on(
						'clickoutside',
						function() {
							parent.removeClass('open-drop-down');
						}
					);
				}
			}
		);
	}

	function <portlet:namespace />singleAccountEntryLoad(accountEntryId, accountEntryName) {
		var accountEntryIds = document.getElementById('<portlet:namespace />accountEntryIds');

		if (accountEntryIds.value.indexOf(accountEntryId) < 0) {
			if (accountEntryIds.value == '') {
				accountEntryIds.value = accountEntryId;
			}
			else {
				accountEntryIds.value += ',' + accountEntryId;
			}

			var newSpan = document.createElement('span');

			newSpan.className = 'single';
			newSpan.id = accountEntryId;
			newSpan.innerHTML = accountEntryName;

			var selectedList = document.getElementById('<portlet:namespace />accountEntryIdsList');

			selectedList.appendChild(newSpan);

			<portlet:namespace />columnSort('create-date', 'create-date', 'asc');
		}
	}

	function <portlet:namespace />toggleBulkEdit(element) {
		var A = AUI();

		var bulkEdit = A.one('#<portlet:namespace />bulkEdit');
		var toggleBulkEdit = A.all('.search-results .toggle-bulk-edit');
		var toggleBulkEditCss = A.all('.search-results .toggle-bulk');

		if (element.checked) {
			bulkEdit.show();
			toggleBulkEdit.show();
			toggleBulkEditCss.removeClass('bulk-edit');

			document.getElementsByName('<portlet:namespace />statuses')[0].checked = true;
			document.getElementsByName('<portlet:namespace />escalationLevels')[0].checked = true;
			document.getElementsByName('<portlet:namespace />escalationLevels')[1].checked = true;
		}
		else {
			bulkEdit.hide();
			toggleBulkEdit.hide();
			toggleBulkEditCss.addClass('bulk-edit');

			document.getElementsByName('<portlet:namespace />statuses')[0].checked = false;
			document.getElementsByName('<portlet:namespace />escalationLevels')[0].checked = false;
			document.getElementsByName('<portlet:namespace />escalationLevels')[1].checked = false;
		}

		<portlet:namespace />toggleCheckbox(element);
	}

	function <portlet:namespace />toggleCheckbox(element) {
		if ((element.tagName == 'INPUT') && ((element.type == 'checkbox') || (element.type == 'radio'))) {
			var elementParent = element.parentElement;

			if (element.type == 'radio') {
				var radios = document.getElementsByName(element.name);

				for (var i = 0; i < radios.length; i++) {
					radios[i].parentElement.classList.remove('active');
				}
			}

			if (element.checked) {
				elementParent.classList.add('active');
			}
			else {
				elementParent.classList.remove('active');
			}
		}

		<portlet:namespace />updateSearchResults();
	}

	function <portlet:namespace />toggleExportButton(resultsCount) {
		var A = AUI();

		var exportButton = A.one('#<portlet:namespace/>exportButton');
		var exportDropdown = A.one('#<portlet:namespace />exportDropdown');

		if ((resultsCount > 0) && (resultsCount < 5000)) {
			exportButton.removeClass('disabled');
			exportDropdown.removeClass('hide');
		}
		else {
			exportButton.addClass('disabled');
			exportDropdown.addClass('hide');
		}
	}

	function <portlet:namespace />toggleSelected(element) {
		var filterToggles = element.parentElement.querySelectorAll('.search-param');

		for (var i = 0; i < filterToggles.length; i++) {
			if (filterToggles[i] != element) {
				filterToggles[i].classList.remove('selected');

				var curSearchParam = filterToggles[i].getAttribute('searchParam');

				if (curSearchParam) {
					document.getElementById('<portlet:namespace />' + curSearchParam).classList.remove('shown');
				}
			}
		}

		var searchParam = element.getAttribute('searchParam');

		if (searchParam) {
			if (element.classList.contains('selected')) {
				document.getElementById('<portlet:namespace />' + searchParam).classList.remove('shown');

				element.classList.remove('selected');
			}
			else {
				document.getElementById('<portlet:namespace />' + searchParam).classList.add('shown');

				element.classList.add('selected');
			}
		}
		else {
			<portlet:namespace />toggleCheckbox(element);
		}
	}

	function <portlet:namespace />updateTabCount(tabId, count) {
		var tabCounts = document.querySelectorAll('.tab-view .tabs span span');

		for (var i in tabCounts) {
			tabCounts[i].innerHTML = '(-)';
		}

		var tab = document.getElementById(tabId);

		if (count > 0) {
			tab.innerHTML = '(' + count + ')';
		}
		else {
			tab.innerHTML = '(-)';
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />loadTab',
		function(searchTab) {
			var A = AUI();

			var tabURL = '';

			<portlet:renderURL copyCurrentRenderParameters="<%= true %>" var="tabURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="" />
				<portlet:param name="singleAccount" value="<%= String.valueOf(singleAccount) %>" />
				<portlet:param name="supportManager" value="<%= String.valueOf(supportManager) %>" />
				<portlet:param name="ticketDisplayId" value="" />
				<portlet:param name="ticketEntryId" value="" />
			</portlet:renderURL>

			if ((searchTab == 'accounts') || (searchTab == 'feedback') || (searchTab == 'partners')) {
				tabURL += '<%= tabURL %>&<portlet:namespace />mvcPath=/support/2/advanced_search/' + searchTab + '.jsp';
			}
			else {
				tabURL += '<%= tabURL %>&<portlet:namespace />mvcPath=/support/2/advanced_search/tickets.jsp';
			}

			var tabContentDiv = A.one('#<portlet:namespace />advancedSearchTabContent');

			A.io.request(
				tabURL,
				{
					on: {
						start: function() {
							tabContentDiv.html('<img src="<%= themeDisplay.getPathThemeImages() + "/aui/loading_indicator.gif" %>" style="display: block; margin: auto;" />');
						},
						success: function() {
							var response = this.get('responseData');

							tabContentDiv.html(response);

							A.all('.advanced-search .tabs span').removeClass('selected');

							var tab = A.one('.advanced-search .tabs #<portlet:namespace />' + searchTab);

							tab.addClass('selected');

							<portlet:namespace />setUpThreeDotMenus();

							<c:if test="<%= singleAccount %>">
								<portlet:namespace />singleAccountEntryLoad(<%= accountEntry.getAccountEntryId() %>, '<%= HtmlUtil.escapeJS(accountEntry.getName()) %>');
							</c:if>

							<portlet:namespace />updateSearchResults();

							<portlet:namespace />disableDatePickers();

							<portlet:namespace />loadEventListeners();

							<c:if test="<%= supportManager %>">
								<portlet:namespace />loadBulkEditToggle(searchTab);
							</c:if>

							<c:if test="<%= liferayIncOrg %>">
								<portlet:namespace />loadExportToggle();
							</c:if>
						}
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateSearchFilter',
		function(searchFilterId, searchFilterName) {
			var A = AUI();

			if (searchFilterId > 0) {
				if (confirm(Liferay.Language.get('are-you-sure-you-want-to-update-your-saved-custom-filter-x', searchFilterName))) {
					<portlet:namespace />doUpdateSearchFilter(searchFilterId, '&<portlet:namespace />editFilterName=' + encodeURIComponent(searchFilterName));
				}

				return;
			}
			else {
				if (searchFilterId == -1) {
					searchFilterName = prompt(Liferay.Language.get('x-already-exists.-please-choose-a-different-name-for-this-custom-filter', searchFilterName));
				}
				else if (searchFilterId == -2) {
					searchFilterName = prompt(Liferay.Language.get('custom-filter-name-is-too-long.-please-choose-a-name-less-than-75-characters', searchFilterName));
				}
				else {
					searchFilterName = prompt(Liferay.Language.get('please-enter-a-valid-name-for-this-filter'));
				}

				A.io.request(
					'<liferay-portlet:actionURL name="validateSearchFilter" />',
					{
						data: {
							<portlet:namespace />searchFilterId: searchFilterId,
							<portlet:namespace />searchFilterName: searchFilterName
						},
						dataType: 'json',
						method: 'post',
						on: {
							success: function() {
								var response = this.get('responseData');

								if (response.duplicateSearchFilterName) {
									<portlet:namespace />updateSearchFilter(-1, searchFilterName);
								}
								else {
									if ((searchFilterName == null) || (searchFilterName == '')) {
										return;
									}
									else if (searchFilterName.length > <%= ModelHintsConstants.TEXT_MAX_LENGTH %>) {
										<portlet:namespace />updateSearchFilter(-2, searchFilterName);

										return;
									}

									<portlet:namespace />doUpdateSearchFilter(searchFilterId, '&<portlet:namespace />addFilterName=' + encodeURIComponent(searchFilterName));
								}
							}
						}
					}
				);
			}
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateSearchResults',
		function() {
			var A = AUI();

			var resultsContentDiv = A.one('#<portlet:namespace />advancedSearchResultsContent');
			var searchTab = A.one('.advanced-search .tabs .selected').getAttribute('searchTab');

			var compiledSearchParams = <portlet:namespace />compileSearchParams(searchTab);

			var resultsURL = '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" />&<portlet:namespace />mvcPath=/support/2/advanced_search/' + searchTab + '_search.jsp';

			var bulkEdit = A.one('#<portlet:namespace />bulkEditCheckbox');

			if (bulkEdit) {
				resultsURL += '&<portlet:namespace />bulkEdit=' + bulkEdit.get('checked');

				if (bulkEdit.get('checked')) {
					resultsURL += '&<portlet:namespace />statuses=' + <%= TicketEntryConstants.STATUS_OPEN %>;
					resultsURL += '&<portlet:namespace />escalationLevels=' + <%= TicketEntryConstants.ESCALATION_LEVEL_1 %>;
					resultsURL += '&<portlet:namespace />escalationLevels=' + <%= TicketEntryConstants.ESCALATION_LEVEL_2 %>;
				}
			}

			resultsURL += '&<portlet:namespace />supportManager=' + <%= supportManager %>;

			resultsURL += compiledSearchParams;

			var searchTabParam = searchTab;

			if ((searchTab == 'ticket') || (searchTab == 'partner') || (searchTab == 'account')) {
				searchTabParam += 's';
			}
			else if (searchTab != 'feedback') {
				searchTabParam = 'tickets';
			}

			var pageURL = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/advanced_search.jsp" /></portlet:renderURL>&<portlet:namespace />searchTab=' + searchTabParam;

			if (<%= hasSearchFilter %>) {
				pageURL += '&<portlet:namespace />searchFilterId=' + <%= searchFilterId %>;
			}

			pageURL += compiledSearchParams;

			window.history.replaceState(null, null, pageURL);

			document.getElementById('<portlet:namespace />cur').value = '';

			A.io.request(
				resultsURL,
				{
					data: {
						pageURL: pageURL,
						ticketWorker: '<%= ticketWorker %>'
					},
					on: {
						error: function() {
							resultsContentDiv.html('<div class="portlet-msg-error"><liferay-ui:message key="an-error-occurred-while-searching-please-try-again" unicode="<%= true %>" /></div>');
						},
						start: function() {
							resultsContentDiv.html('<img src="<%= themeDisplay.getPathThemeImages() + "/aui/loading_indicator.gif" %>" style="display: block; margin: auto;" />');
						},
						success: function() {
							var response = this.get('responseData');

							if (!response) {
								resultsContentDiv.html('<div class="portlet-msg-error"><liferay-ui:message key="an-error-occurred-while-searching-please-try-again" unicode="<%= true %>" /></div>');
							}
							else if (response.indexOf('redirect:') > -1) {
								var url = response.replace('redirect:', '');

								window.location.href = url;
							}
							else {
								resultsContentDiv.html(response);

								var filters = document.querySelectorAll('.search-param');

								for (var i = 0; i < filters.length; i++) {
									filters[i].classList.remove('active');
								}

								var activeFilters = document.getElementById('<portlet:namespace />activeFilters');

								if (activeFilters) {
									var split = activeFilters.value.split(',');

									for (var i = 0; i < split.length - 1; i++) {
										var activeFilter = document.querySelectorAll('.search-param.' + split[i]);

										if (activeFilter.length > 0) {
											activeFilter[0].classList.add('active');
										}
									}
								}

								var resultsCountInput = document.getElementById('<portlet:namespace />' + searchTab + 'ResultsListCount');

								var resultsCount = 0;

								if (resultsCountInput) {
									resultsCount = resultsCountInput.value;
								}

								<portlet:namespace />updateTabCount('<portlet:namespace />' + searchTab + 'TabCount', resultsCount);

								<portlet:namespace />toggleExportButton(resultsCount);
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>

<aui:script use="aui-base">
	<portlet:namespace />loadTab('<%= HtmlUtil.escape(searchTab) %>');

	<portlet:namespace />navSelect('advSearch');
</aui:script>