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
boolean bulkEdit = ParamUtil.getBoolean(request, "bulkEdit");
long searchFilterId = ParamUtil.getLong(request, "searchFilterId");
boolean singleAccount = ParamUtil.getBoolean(request, "singleAccount");
boolean supportManager = ParamUtil.getBoolean(request, "supportManager");

SearchFilter searchFilter = null;

TicketEntryDisplayTerms displayTerms = null;

if (searchFilterId > 0) {
	searchFilter = SearchFilterLocalServiceUtil.getSearchFilter(searchFilterId);

	displayTerms = new TicketEntryDisplayTerms(renderRequest, searchFilter.getFilter());
}
else {
	displayTerms = new TicketEntryDisplayTerms(renderRequest);
}

boolean ticketWorker = liferayIncOrg || supportPartnerWorker;
%>

<div class="advanced-search ticket">
	<div>
		<span class="account search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="account">
			<liferay-ui:message key="project" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>

		<c:if test="<%= ticketWorker %>">
			<span class="assignee search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="assignee">
				<liferay-ui:message key="assignee" />

				<svg height="8" viewBox="0 0 8 8" width="8">
					<path d="M 0, 0 4, 4 8, 0" />
				</svg>
			</span>
		</c:if>

		<span class="component search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="component">
			<liferay-ui:message key="component" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="date search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="date">
			<liferay-ui:message key="date" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="environment search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="environment">
			<liferay-ui:message key="environment" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="pending search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="pending">
			<liferay-ui:message key="pending" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="search-param severity" onclick="<portlet:namespace />toggleSelected(this);" searchParam="severity">
			<liferay-ui:message key="severity" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="search-param status" onclick="<portlet:namespace />toggleSelected(this);" searchParam="status">
			<liferay-ui:message key="status" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>

		<c:if test="<%= liferayIncOrg %>">
			<span class="search-param support-region" onclick="<portlet:namespace />toggleSelected(this);" searchParam="supportRegion">
				<liferay-ui:message key="support-region" />

				<svg height="8" viewBox="0 0 8 8" width="8">
					<path d="M 0, 0 4, 4 8, 0" />
				</svg>
			</span>
		</c:if>

		<span class="search-param">
			<div class="header-checkbox">
				<label>
					<input <%= displayTerms.isSearchAttachments() ? "checked" : "" %> class="aui-field-input-choice" name="<portlet:namespace /><%= displayTerms.SEARCH_ATTACHMENTS %>" onchange="<portlet:namespace />toggleCheckbox(this);" type="checkbox" value="true" />

					<span class="checkbox-text"><liferay-ui:message key="search-attachments" /></span>
				</label>
			</div>
		</span>

		<c:if test="<%= supportManager %>">
			<span class="search-param">
				<div class="bulk-edit-toggle header-checkbox" id="<portlet:namespace />bulkEditToggle">
					<label>
						<input class="aui-field-input-choice" id="<portlet:namespace />bulkEditCheckbox" onClick="<portlet:namespace />toggleBulkEdit(this);" type="checkbox" />

						<span class="checkbox-text"><liferay-ui:message key="manager-mode" /></span>
					</label>
				</div>
			</span>
		</c:if>

		<c:if test="<%= ticketWorker %>">
			<c:choose>
				<c:when test="<%= searchFilterId > 0 %>">

					<%
					Map<String, String> dropDownList = new LinkedHashMap<String, String>();

					dropDownList.put("update-filter", renderResponse.getNamespace() + "updateSearchFilter(" + searchFilterId + ", '" + HtmlUtil.escapeJS(searchFilter.getName()) + "');");
					dropDownList.put("delete-filter", renderResponse.getNamespace() + "deleteSearchFilter(" + searchFilterId + ", '" + HtmlUtil.escapeJS(searchFilter.getName()) + "');");
					dropDownList.put("create-filter", renderResponse.getNamespace() + "updateSearchFilter(0, '');");
					%>

					<span class="fr three-dot" id="<portlet:namespace />threeDotMenu">
						<span class="three-dot-icon" id="<portlet:namespace />threeDotIcon">
							<span style="top: 6px;"></span>
							<span style="top: 14px;"></span>
							<span style="top: 22px;"></span>
						</span>

						<div class="drop-down-menu ticket-search">
							<div class="drop-down-arrow">
								<div></div>
							</div>

							<ul>

								<%
								for (Map.Entry<String, String> entry : dropDownList.entrySet()) {
									String curLabel = entry.getKey();
									String curOnClick = entry.getValue();

									StringBundler sb = new StringBundler(5);

									sb.append("<a href=\"javascript:;\" onClick=\"");
									sb.append(curOnClick);
									sb.append("\">");
									sb.append(LanguageUtil.get(request, curLabel));
									sb.append("</a>");
								%>

									<li>
										<%= sb.toString() %>
									</li>

								<%
								}
								%>

							</ul>
						</div>
					</span>
				</c:when>
				<c:otherwise>
					<input class="fr" onclick="<portlet:namespace />updateSearchFilter(0, '');" type="button" value="<liferay-ui:message key="create-filter" />" />
				</c:otherwise>
			</c:choose>
		</c:if>

		<c:if test="<%= liferayIncOrg %>">
			<span class="export-menu fr" id="<portlet:namespace />exportMenu">
				<a class="aui-button-input-export btn disabled" id="<portlet:namespace />exportButton" type="button"><liferay-ui:message key="export" /></a>

				<ul class="aui-helper-hidden drop-down-menu" id="<portlet:namespace />exportDropdown">
					<li>
						<a href="javascript:;" onclick="<portlet:namespace />exportTicketSearchResults('ticket-details');"><liferay-ui:message key="ticket-details" /></a>
					</li>
					<li>
						<a href="javascript:;" onclick="<portlet:namespace />exportTicketSearchResults('environment-details');"><liferay-ui:message key="environment-details" /></a>
					</li>
					<li>
						<a href="javascript:;" onclick="<portlet:namespace />exportTicketSearchResults('all');"><liferay-ui:message key="all" /></a>
					</li>
				</ul>
			</span>
		</c:if>
	</div>

	<div class="search-param-config">
		<div class="account search-param-dropdown" id="<portlet:namespace />account">
			<input id="<portlet:namespace />accountEntryIds" name="<portlet:namespace /><%= displayTerms.ACCOUNT_ENTRY_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAccountEntryIds()) %>" />

			<div class="aui-w50 fl">
				<c:if test="<%= !singleAccount %>">
					<div>
						<input class="aui-button-input" onClick="window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/select_account_entry.jsp" /></portlet:renderURL>', 'assigned_to', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800');" type="button" value="<liferay-ui:message key="choose-project" />" />
					</div>
				</c:if>

				<span class="list" id="<portlet:namespace />accountEntryIdsList">

					<%
					for (long accountEntryId : displayTerms.getAccountEntryIds()) {
						AccountEntry curAccountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);
					%>

						<c:choose>
							<c:when test="<%= singleAccount %>">
								<span class="single" id="<%= accountEntryId %>"><%= HtmlUtil.escape(curAccountEntry.getName()) %></span>
							</c:when>
							<c:otherwise>
								<span id="<%= accountEntryId %>" onclick="<portlet:namespace />removeAccountEntry(this);"><%= HtmlUtil.escape(curAccountEntry.getName()) %></span>
							</c:otherwise>
						</c:choose>

					<%
					}
					%>

				</span>
			</div>

			<c:if test="<%= ticketWorker %>">
				<div class="aui-w50 fr">
					<h2 class="support-input-heading">
						<liferay-ui:message key="project-tiers" />
					</h2>

					<%
					List<String> accountEntryTierLabels = new ArrayList<String>();

					for (int accountEntryTier : AccountEntryConstants.TIERS) {
						accountEntryTierLabels.add(AccountEntryConstants.getTierLabel(accountEntryTier));
					}
					%>

					<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
						<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getAccountEntryTiers()) %>" />
						<liferay-util:param name="labels" value="<%= StringUtil.merge(accountEntryTierLabels) %>" />
						<liferay-util:param name="name" value="<%= displayTerms.ACCOUNT_ENTRY_TIERS %>" />
						<liferay-util:param name="values" value="<%= StringUtil.merge(AccountEntryConstants.TIERS) %>" />
					</liferay-util:include>
				</div>
			</c:if>
		</div>

		<div class="assignee search-param-dropdown" id="<portlet:namespace />assignee">
			<input id="<portlet:namespace />assignedUserIds" name="<portlet:namespace /><%= displayTerms.ASSIGNED_USER_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAssignedUserIds()) %>" />
			<input id="<portlet:namespace />supportTeamIds" name="<portlet:namespace /><%= displayTerms.ASSIGNED_SUPPORT_TEAM_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAssignedSupportTeamIds()) %>" />
			<input id="<portlet:namespace />partnerEntryIds" name="<portlet:namespace /><%= displayTerms.ASSIGNED_PARTNER_ENTRY_IDS %>" type="hidden" value="<%= StringUtil.merge(displayTerms.getAssignedPartnerEntryIds()) %>" />

			<%
			long[] accountWorkerUserIds = displayTerms.getAccountWorkerUserIds();
			int[] accountWorkerRoles = displayTerms.getAccountWorkerRoles();

			StringBundler sb = new StringBundler((accountWorkerUserIds.length * 4) - 1);

			for (int i = 0; i < accountWorkerUserIds.length; i++) {
				sb.append(accountWorkerUserIds[i]);
				sb.append(StringPool.COLON);
				sb.append(accountWorkerRoles[i]);

				if (i < (accountWorkerUserIds.length - 1)) {
					sb.append(StringPool.COMMA);
				}
			}
			%>

			<input id="<portlet:namespace />accountWorkers" type="hidden" value="<%= sb.toString() %>" />
			<input id="<portlet:namespace />accountWorkerUserIds" name="<portlet:namespace /><%= displayTerms.ACCOUNT_WORKER_USER_IDS %>" type="hidden" value="<%= StringUtil.merge(accountWorkerUserIds) %>" />
			<input id="<portlet:namespace />accountWorkerRoles" name="<portlet:namespace /><%= displayTerms.ACCOUNT_WORKER_ROLES %>" type="hidden" value="<%= StringUtil.merge(accountWorkerRoles) %>" />

			<div>
				<input class="aui-button-input" onClick="window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/select_assigned_to.jsp" /></portlet:renderURL>', 'assigned_to', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');" type="button" value="<liferay-ui:message key="choose-assignee" />" />

				<c:if test="<%= liferayIncOrg %>">
					<input class="aui-button-input" onClick="window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/select_account_worker.jsp" /></portlet:renderURL>', 'assigned_to', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');" type="button" value="<liferay-ui:message key="choose-project-worker" />" />
				</c:if>
			</div>

			<span class="list" id="<portlet:namespace />assignedUserIdsList">

				<%
				for (long assignedUserId : displayTerms.getAssignedUserIds()) {
					User curUser = UserLocalServiceUtil.getUser(assignedUserId);
				%>

					<span id="<%= assignedUserId %>" onclick="<portlet:namespace />removeAssignedTo(this, 'assignedUserId');"><%= HtmlUtil.escape(curUser.getFullName()) %></span>

				<%
				}
				%>

			</span>
			<span class="list" id="<portlet:namespace />supportTeamIdsList">

				<%
				for (long assignedSupportTeamId : displayTerms.getAssignedSupportTeamIds()) {
					SupportTeam curSupportTeam = SupportTeamLocalServiceUtil.getSupportTeam(assignedSupportTeamId);
				%>

					<span id="<%= assignedSupportTeamId %>" onclick="<portlet:namespace />removeAssignedTo(this, 'supportTeamId');"><%= HtmlUtil.escape(curSupportTeam.getName()) %></span>

				<%
				}
				%>

			</span>
			<span class="list" id="<portlet:namespace />partnerEntryIdsList">

				<%
				for (long assignedPartnerEntryId : displayTerms.getAssignedPartnerEntryIds()) {
					PartnerEntry curPartnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(assignedPartnerEntryId);
				%>

					<span id="<%= assignedPartnerEntryId %>" onclick="<portlet:namespace />removeAssignedTo(this, 'partnerEntryId');"><%= HtmlUtil.escape(curPartnerEntry.getCode()) %></span>

				<%
				}
				%>

			</span>
			<span class="list" id="<portlet:namespace />accountWorkersList">

				<%
				for (int i = 0; i < accountWorkerUserIds.length; i++) {
					User curUser = UserLocalServiceUtil.getUser(accountWorkerUserIds[i]);

					String roleLabel = AccountWorkerConstants.getRoleLabel(accountWorkerRoles[i]);
				%>

					<span id="<%= accountWorkerUserIds[i] + ":" + accountWorkerRoles[i] %>" onclick="<portlet:namespace />removeAccountWorker(this);"><%= LanguageUtil.get(request, roleLabel) %>: <%= HtmlUtil.escape(curUser.getFullName()) %></span>

				<%
				}
				%>

			</span>
		</div>

		<div class="components search-param-dropdown" id="<portlet:namespace />component">
			<div class="check-all">
				<input onClick="<portlet:namespace />checkAll('component', true);" type="button" value="<liferay-ui:message key="check-all" />" />

				<input onClick="<portlet:namespace />checkAll('component', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
			</div>

			<%
			List<ListType> componentTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_COMPONENT);
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getComponents()) %>" />
				<liferay-util:param name="labels" value='<%= ListUtil.toString(componentTypes, "name") %>' />
				<liferay-util:param name="name" value="<%= displayTerms.COMPONENTS %>" />
				<liferay-util:param name="values" value='<%= ListUtil.toString(componentTypes, "listTypeId") %>' />
			</liferay-util:include>
		</div>

		<%
		Calendar calendar = Calendar.getInstance();
		%>

		<div class="date search-param-dropdown" id="<portlet:namespace />date">
			<div class="aui-helper-clearfix">
				<div class="aui-w25 content-column">
					<div class="content-column-content left-column">
						<h2 class="support-input-heading">
							<liferay-ui:message key="created-between" />
						</h2>

						<div class="aui-w100">
							<liferay-ui:message key="begin-date" />

							<liferay-ui:input-date
								cssClass="aui-w100"
								dayNullable="<%= true %>"
								dayParam="<%= displayTerms.CREATE_DATE_GT_DAY %>"
								dayValue="<%= displayTerms.getCreateDateGTDay() %>"
								monthNullable="<%= true %>"
								monthParam="<%= displayTerms.CREATE_DATE_GT_MONTH %>"
								monthValue="<%= displayTerms.getCreateDateGTMonth() %>"
								yearNullable="<%= true %>"
								yearParam="<%= displayTerms.CREATE_DATE_GT_YEAR %>"
								yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
								yearRangeStart="<%= 2006 %>"
								yearValue="<%= displayTerms.getCreateDateGTYear() %>"
							/>
						</div>

						<div class="aui-w100">
							<liferay-ui:message key="end-date" />

							<liferay-ui:input-date
								cssClass="aui-w100"
								dayNullable="<%= true %>"
								dayParam="<%= displayTerms.CREATE_DATE_LT_DAY %>"
								dayValue="<%= displayTerms.getCreateDateLTDay() %>"
								monthNullable="<%= true %>"
								monthParam="<%= displayTerms.CREATE_DATE_LT_MONTH %>"
								monthValue="<%= displayTerms.getCreateDateLTMonth() %>"
								yearNullable="<%= true %>"
								yearParam="<%= displayTerms.CREATE_DATE_LT_YEAR %>"
								yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
								yearRangeStart="<%= 2006 %>"
								yearValue="<%= displayTerms.getCreateDateLTYear() %>"
							/>
						</div>
					</div>
				</div>

				<div class="aui-w25 content-column">
					<div class="content-column-content left-column">
						<h2 class="support-input-heading">
							<liferay-ui:message key="closed-between" />
						</h2>

						<div class="aui-w100">
							<liferay-ui:message key="begin-date" />

							<liferay-ui:input-date
								cssClass="aui-w100"
								dayNullable="<%= true %>"
								dayParam="<%= displayTerms.CLOSED_DATE_GT_DAY %>"
								dayValue="<%= displayTerms.getClosedDateGTDay() %>"
								monthNullable="<%= true %>"
								monthParam="<%= displayTerms.CLOSED_DATE_GT_MONTH %>"
								monthValue="<%= displayTerms.getClosedDateGTMonth() %>"
								yearNullable="<%= true %>"
								yearParam="<%= displayTerms.CLOSED_DATE_GT_YEAR %>"
								yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
								yearRangeStart="<%= 2006 %>"
								yearValue="<%= displayTerms.getClosedDateGTYear() %>"
							/>
						</div>

						<div class="aui-w100">
							<liferay-ui:message key="end-date" />

							<liferay-ui:input-date
								cssClass="aui-w100"
								dayNullable="<%= true %>"
								dayParam="<%= displayTerms.CLOSED_DATE_LT_DAY %>"
								dayValue="<%= displayTerms.getClosedDateLTDay() %>"
								monthNullable="<%= true %>"
								monthParam="<%= displayTerms.CLOSED_DATE_LT_MONTH %>"
								monthValue="<%= displayTerms.getClosedDateLTMonth() %>"
								yearNullable="<%= true %>"
								yearParam="<%= displayTerms.CLOSED_DATE_LT_YEAR %>"
								yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
								yearRangeStart="<%= 2006 %>"
								yearValue="<%= displayTerms.getClosedDateLTYear() %>"
							/>
						</div>
					</div>
				</div>

				<c:if test="<%= !screenShareMode && ticketWorker %>">
					<div class="aui-w25 content-column">
						<div class="content-column-content left-column">
							<h2 class="support-input-heading">
								<liferay-ui:message key="due-between" />
							</h2>

							<div class="aui-w100">
								<liferay-ui:message key="begin-date" />

								<liferay-ui:input-date
									cssClass="aui-w100"
									dayNullable="<%= true %>"
									dayParam="<%= displayTerms.DUE_DATE_GT_DAY %>"
									dayValue="<%= displayTerms.getDueDateGTDay() %>"
									monthNullable="<%= true %>"
									monthParam="<%= displayTerms.DUE_DATE_GT_MONTH %>"
									monthValue="<%= displayTerms.getDueDateGTMonth() %>"
									yearNullable="<%= true %>"
									yearParam="<%= displayTerms.DUE_DATE_GT_YEAR %>"
									yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
									yearRangeStart="<%= 2006 %>"
									yearValue="<%= displayTerms.getDueDateGTYear() %>"
								/>
							</div>

							<div class="aui-w100">
								<liferay-ui:message key="end-date" />

								<liferay-ui:input-date
									cssClass="aui-w100"
									dayNullable="<%= true %>"
									dayParam="<%= displayTerms.DUE_DATE_LT_DAY %>"
									dayValue="<%= displayTerms.getDueDateLTDay() %>"
									monthNullable="<%= true %>"
									monthParam="<%= displayTerms.DUE_DATE_LT_MONTH %>"
									monthValue="<%= displayTerms.getDueDateLTMonth() %>"
									yearNullable="<%= true %>"
									yearParam="<%= displayTerms.DUE_DATE_LT_YEAR %>"
									yearRangeEnd="<%= calendar.get(Calendar.YEAR) + 1 %>"
									yearRangeStart="<%= 2006 %>"
									yearValue="<%= displayTerms.getDueDateLTYear() %>"
								/>
							</div>
						</div>
					</div>

					<div class="aui-w25 content-column">
						<div class="content-column-content left-column">
							<h2 class="support-input-heading">
								<liferay-ui:message key="satisfied-due-date" />
							</h2>

							<div class="aui-w100 radio">
								<label class="<%= ((displayTerms.getSatisfiedDueDate() != null) && displayTerms.isSatisfiedDueDate()) ? "active" : StringPool.BLANK %>">
									<input <%= ((displayTerms.getSatisfiedDueDate() != null) && displayTerms.isSatisfiedDueDate()) ? "checked" : StringPool.BLANK %> id="<portlet:namespace /><%= displayTerms.SATISFIED_DUE_DATE %>" name="<portlet:namespace /><%= displayTerms.SATISFIED_DUE_DATE %>" onchange="<portlet:namespace />toggleCheckbox(this);" type="radio" value="yes" />

									<liferay-ui:message key="yes" />
								</label>

								<label class="<%= ((displayTerms.getSatisfiedDueDate() != null) && !displayTerms.isSatisfiedDueDate()) ? "active" : StringPool.BLANK %>">
									<input <%= ((displayTerms.getSatisfiedDueDate() != null) && !displayTerms.isSatisfiedDueDate()) ? "checked" : StringPool.BLANK %> id="<portlet:namespace /><%= displayTerms.SATISFIED_DUE_DATE %>" name="<portlet:namespace /><%= displayTerms.SATISFIED_DUE_DATE %>" onchange="<portlet:namespace />toggleCheckbox(this);" type="radio" value="no" />

									<liferay-ui:message key="no" />
								</label>

								<label class="<%= (displayTerms.getSatisfiedDueDate() == null) ? "active" : StringPool.BLANK %>">
									<input <%= (displayTerms.getSatisfiedDueDate() == null) ? "checked" : StringPool.BLANK %> id="<portlet:namespace /><%= displayTerms.SATISFIED_DUE_DATE %>" name="<portlet:namespace /><%= displayTerms.SATISFIED_DUE_DATE %>" onchange="<portlet:namespace />toggleCheckbox(this);" type="radio" value="" />

									<liferay-ui:message key="n-a" />
								</label>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>

		<div class="environment search-param-dropdown" id="<portlet:namespace />environment">
			<div class="first section" id="<portlet:namespace />product">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="product" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('product', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('product', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getProductEntryIds()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(productEntries, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.PRODUCT_ENTRY_IDS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(productEntries, "productEntryId") %>' />
				</liferay-util:include>
			</div>

			<div class="section" id="<portlet:namespace />liferayVersion">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="liferay-version" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('liferayVersion', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('liferayVersion', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> envLFRTypes = ListUtil.copy(ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS));

				envLFRTypes.addAll(ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS));
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getEnvLFRIds()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(envLFRTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.ENV_LFR_IDS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(envLFRTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>

			<div class="section" id="<portlet:namespace />operatingSystem">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="operating-system" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('operatingSystem', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('operatingSystem', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> envOSTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ENV_OS);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getEnvOSIds()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(envOSTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.ENV_OS_IDS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(envOSTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>

			<div class="section" id="<portlet:namespace />applicationServer">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="application-server" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('applicationServer', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('applicationServer', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> envASTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ENV_AS);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getEnvASIds()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(envASTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.ENV_AS_IDS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(envASTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>

			<div class="section" id="<portlet:namespace />JVM">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="java-virtual-machine" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('JVM', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('JVM', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> envJVMTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ENV_JVM);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getEnvJVMIds()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(envJVMTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.ENV_JVM_IDS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(envJVMTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>

			<div class="section" id="<portlet:namespace />database">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="database" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('database', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('database', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> envDBTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ENV_DB);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getEnvDBIds()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(envDBTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.ENV_DB_IDS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(envDBTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>
		</div>

		<div class="pending search-param-dropdown" id="<portlet:namespace />pending">

			<%
			List<String> pendingTypeLabels = new ArrayList<String>();

			for (int pendingType : TicketFlagConstants.TYPES_PENDING) {
				pendingTypeLabels.add(TicketFlagConstants.getTypeLabel(pendingType));
			}
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getPendingTypes()) %>" />
				<liferay-util:param name="labels" value="<%= StringUtil.merge(pendingTypeLabels) %>" />
				<liferay-util:param name="name" value="<%= displayTerms.PENDING_TYPES %>" />
				<liferay-util:param name="values" value="<%= StringUtil.merge(TicketFlagConstants.TYPES_PENDING) %>" />
			</liferay-util:include>
		</div>

		<div class="search-param-dropdown severity" id="<portlet:namespace />severity">
			<div class="first section">
				<h2 class="support-input-heading">
					<liferay-ui:message key="severity" />
				</h2>

				<%
				String[] severityLabels = new String[3];
				int[] severityValues = new int[3];

				for (int i = 0; i < 3; i++) {
					severityLabels[i] = TicketEntryConstants.getSeverityLabel(i + 1);
					severityValues[i] = i + 1;
				}
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getSeverities()) %>" />
					<liferay-util:param name="labels" value="<%= StringUtil.merge(severityLabels) %>" />
					<liferay-util:param name="name" value="<%= displayTerms.SEVERITIES %>" />
					<liferay-util:param name="values" value="<%= StringUtil.merge(severityValues) %>" />
				</liferay-util:include>
			</div>

			<div class="section">
				<h2 class="support-input-heading">
					<liferay-ui:message key="escalation-level" />
				</h2>

				<%
				List<ListType> escalationLevelTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_ESCALATION_LEVEL);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getEscalationLevels()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(escalationLevelTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.ESCALATION_LEVELS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(escalationLevelTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>
		</div>

		<div class="search-param-dropdown status" id="<portlet:namespace />status">
			<div class="first section" id="<portlet:namespace />statusCheckboxes">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="status" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('statusCheckboxes', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('statusCheckboxes', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> statusTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_STATUS);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getStatuses()) %>" />
					<liferay-util:param name="labels" value='<%= "open," + ListUtil.toString(statusTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.STATUSES %>" />
					<liferay-util:param name="values" value='<%= TicketEntryConstants.STATUS_OPEN + StringPool.COMMA + ListUtil.toString(statusTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>

			<div class="section" id="<portlet:namespace />resolution">
				<h2 class="inline-block support-input-heading">
					<liferay-ui:message key="resolution" />
				</h2>

				<div class="check-all heading">
					<input onClick="<portlet:namespace />checkAll('resolution', true);" type="button" value="<liferay-ui:message key="check-all" />" />

					<input onClick="<portlet:namespace />checkAll('resolution', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
				</div>

				<%
				List<ListType> resolutionTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION);
				%>

				<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
					<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getResolutions()) %>" />
					<liferay-util:param name="labels" value='<%= ListUtil.toString(resolutionTypes, "name") %>' />
					<liferay-util:param name="name" value="<%= displayTerms.RESOLUTIONS %>" />
					<liferay-util:param name="values" value='<%= ListUtil.toString(resolutionTypes, "listTypeId") %>' />
				</liferay-util:include>
			</div>
		</div>

		<div class="search-param-dropdown support-region" id="<portlet:namespace />supportRegion">
			<div class="check-all">
				<input onClick="<portlet:namespace />checkAll('supportRegion', true);" type="button" value="<liferay-ui:message key="check-all" />" />

				<input onClick="<portlet:namespace />checkAll('supportRegion', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
			</div>

			<%
			List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getSupportRegionIds()) %>" />
				<liferay-util:param name="labels" value='<%= ListUtil.toString(supportRegions, "name") %>' />
				<liferay-util:param name="name" value="<%= displayTerms.SUPPORT_REGION_IDS %>" />
				<liferay-util:param name="values" value='<%= ListUtil.toString(supportRegions, "supportRegionId") %>' />
			</liferay-util:include>
		</div>

		<input id="<portlet:namespace />feedbackAvailable" name="<portlet:namespace />feedbackAvailable" type="hidden" value="<%= String.valueOf(displayTerms.getFeedbackAvailable()) %>" />
	</div>
</div>

<div class="search-results">
	<c:if test="<%= supportManager %>">
		<div class="<%= bulkEdit ? "" : "aui-helper-hidden" %>" id="<portlet:namespace />bulkEdit">
			<div class="multiple-ticket-select" id="<portlet:namespace />multipleTicketSelect">
				<div>
					<div class="multiple-ticket-checkbox">
						<span class="aui-helper-hidden multiple-ticket-checkmark">
							<div class="checkmark-stem"></div>

							<div class="checkmark-kick"></div>
						</span>
					</div>
				</div>

				<div>
					<div class="multiple-ticket-triangle"></div>
				</div>

				<ul class="multiple-ticket-select-menu">
					<li onClick="<portlet:namespace />multipleTicketSelect('all');">
						<liferay-ui:message key="select-all" />
					</li>
					<li onClick="<portlet:namespace />multipleTicketSelect('none');">
						<liferay-ui:message key="select-none" />
					</li>
				</ul>
			</div>

			<input class="aui-button-input" onClick="<portlet:namespace />openBulkEditDialog(1);" type="button" value="<%= LanguageUtil.get(pageContext, "edit") %>" />
		</div>
	</c:if>

	<div id="<portlet:namespace />advancedSearchResultsContent">
	</div>
</div>