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
String tabs1 = ParamUtil.getString(request, "tabs1", "users");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/select_assigned_to.jsp");
portletURL.setParameter("tabs1", tabs1);

String tabsNames = "users";

if (liferayIncOrg) {
	tabsNames += ",support-teams,partners";
}
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<div class="unit">
		<div class="table-report">
			<liferay-ui:tabs
				names="<%= tabsNames %>"
				param="tabs1"
				url="<%= portletURL.toString() %>"
			/>

			<c:choose>
				<c:when test='<%= tabs1.equals("partners") && liferayIncOrg %>'>

					<%
					LinkedHashMap params = new LinkedHashMap();

					params.put("managingSupport", Boolean.TRUE);
					%>

					<liferay-ui:search-container
						emptyResultsMessage="no-partners-were-found"
						id="partnerEntrySearchContainer"
						iteratorURL="<%= portletURL %>"
						searchContainer="<%= new PartnerEntrySearch(renderRequest, portletURL) %>"
					>

						<%
						PartnerEntryDisplayTerms displayTerms = (PartnerEntryDisplayTerms)searchContainer.getDisplayTerms();
						PartnerEntrySearchTerms searchTerms = (PartnerEntrySearchTerms)searchContainer.getSearchTerms();
						%>

						<%@ include file="/support/2/partner_entry_search.jspf" %>

						<%@ include file="/support/2/partner_entry_search_results.jspf" %>

						<liferay-ui:search-container-row
							className="com.liferay.osb.model.PartnerEntry"
							escapedModel="<%= true %>"
							keyProperty="partnerEntryId"
							modelVar="partnerEntry"
						>

							<%
							StringBuilder sb = new StringBuilder();

							sb.append("javascript:opener.");
							sb.append(renderResponse.getNamespace());
							sb.append("selectAssignedTo('partnerEntryId-");
							sb.append(partnerEntry.getPartnerEntryId());
							sb.append("', '");
							sb.append(UnicodeFormatter.toString(partnerEntry.getCode()));
							sb.append("');");

							String rowHREF = sb.toString();
							%>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								property="code"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</c:when>
				<c:when test='<%= tabs1.equals("support-teams") && liferayIncOrg %>'>
					<liferay-ui:search-container
						emptyResultsMessage="no-support-teams-were-found"
						headerNames="name,accounts,time-zones,locales"
						id="supportTeamsSearchContainer"
						iteratorURL="<%= portletURL %>"
						searchContainer="<%= new SupportTeamSearch(renderRequest, portletURL) %>"
					>

						<%
						searchContainer.setResults(SupportTeamLocalServiceUtil.getSupportTeams(searchContainer.getStart(), searchContainer.getEnd()));
						searchContainer.setTotal(SupportTeamLocalServiceUtil.getSupportTeamsCount());
						%>

						<liferay-ui:search-container-row
							className="com.liferay.osb.model.SupportTeam"
							escapedModel="<%= true %>"
							keyProperty="supportTeamId"
							modelVar="supportTeam"
						>

							<%
							StringBuilder sb = new StringBuilder();

							sb.append("javascript:opener.");
							sb.append(renderResponse.getNamespace());
							sb.append("selectAssignedTo('supportTeamId-");
							sb.append(supportTeam.getSupportTeamId());
							sb.append("', '");
							sb.append(UnicodeFormatter.toString(supportTeam.getName()));
							sb.append("');");

							String rowHREF = sb.toString();
							%>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								property="name"
							/>

							<liferay-ui:search-container-column-text
								name="projects"
							>

								<%
								List<AccountEntry> accountEntries = supportTeam.getAccountEntries();
								%>

								<c:if test="<%= !accountEntries.isEmpty() %>">
									<span class="expand" id="<portlet:namespace />expand_<%= supportTeam.getSupportTeamId() %>">
										<liferay-ui:icon
											image="../arrows/01_plus"
											label="<%= true %>"
											message="expand"
											url='<%= "javascript:" + renderResponse.getNamespace() + "toggleAccountEntries(" + supportTeam.getSupportTeamId() + ", true);" %>'
										/>
									</span>
									<span class="collapse hide" id="<portlet:namespace />collapse_<%= supportTeam.getSupportTeamId() %>">
										<liferay-ui:icon
											image="../arrows/01_minus"
											label="<%= true %>"
											message="collapse"
											url='<%= "javascript:" + renderResponse.getNamespace() + "toggleAccountEntries(" + supportTeam.getSupportTeamId() + ", false);" %>'
										/>
									</span>

									<div class="hide" id="<portlet:namespace />accountEntries_<%= supportTeam.getSupportTeamId() %>">

										<%
										for (int i = 0; i < accountEntries.size(); i++) {
											AccountEntry accountEntry = accountEntries.get(i);
										%>

											<a href="<%= rowHREF %>"><%= HtmlUtil.escape(accountEntry.getName()) %></a>

											<%= ((i + 1) < accountEntries.size()) ? "<br />" : "" %>

										<%
										}
										%>

									</div>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								name="languages"
							>

								<%
								List<String> languageIds = supportTeam.getLanguageIds();

								for (int i = 0; i < languageIds.size(); i++) {
									String languageId = languageIds.get(i);
								%>

									<%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(languageId)) %><%= ((i + 1) < languageIds.size()) ? "<br />" : "" %>

								<%
								}
								%>

							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</c:when>
				<c:otherwise>
					<%@ include file="/common/user_search_inputs.jspf" %>

					<%
					LinkedHashMap userParams = new LinkedHashMap();

					if (liferayIncOrg) {
						userParams.put("usersTicketWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByTicketWorker"), null));
					}
					else {
						userParams.put("usersPartnerEntryWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByPartnerEntryWorkers"), user.getUserId()));
					}
					%>

					<liferay-ui:search-container
						emptyResultsMessage="no-users-were-found"
						id="usersSearchContainer"
						iteratorURL="<%= portletURL %>"
						searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
					>

						<%
						UserDisplayTerms searchTerms = (UserDisplayTerms)searchContainer.getSearchTerms();

						if (!searchTerms.isAdvancedSearch()) {
							searchContainer.setTotal(UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), searchTerms.getKeywords(), WorkflowConstants.STATUS_ANY, userParams));
							searchContainer.setResults(UserLocalServiceUtil.search(themeDisplay.getCompanyId(), searchTerms.getKeywords(), WorkflowConstants.STATUS_ANY, userParams, searchContainer.getStart(), searchContainer.getEnd(), new UserFirstNameComparator(true)));
						}
						else {
							searchContainer.setTotal(UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), firstName, middleName, lastName, screenName, emailAddress, WorkflowConstants.STATUS_ANY, userParams, true));
							searchContainer.setResults(UserLocalServiceUtil.search(themeDisplay.getCompanyId(), firstName, middleName, lastName, screenName, emailAddress, WorkflowConstants.STATUS_ANY, userParams, true, searchContainer.getStart(), searchContainer.getEnd(), new UserFirstNameComparator(true)));
						}
						%>

						<liferay-ui:search-container-row
							className="com.liferay.portal.kernel.model.User"
							escapedModel="<%= true %>"
							keyProperty="userId"
							modelVar="curUser"
						>

							<%
							StringBuilder sb = new StringBuilder();

							sb.append("javascript:opener.");
							sb.append(renderResponse.getNamespace());
							sb.append("selectAssignedTo('");
							sb.append(curUser.getUserId());
							sb.append("', '");
							sb.append(UnicodeFormatter.toString(curUser.getFullName()));
							sb.append("');");

							String rowHREF = sb.toString();
							%>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								name="name"
								property="fullName"
							/>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								name="screen-name"
								property="screenName"
							/>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								name="email-address"
								property="emailAddress"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</aui:form>

<aui:script use="aui-base">
	function <portlet:namespace />toggleAccountEntries(id, expand) {
		var accountEntriesNode = A.one('#<portlet:namespace />accountEntries_' + id);

		if (accountEntriesNode) {
			accountEntriesNode.toggle(expand);
		}

		var collapseNode = A.one('#<portlet:namespace />collapse_' + id);

		if (collapseNode) {
			collapseNode.toggle(expand);
		}

		var expandNode = A.one('#<portlet:namespace />expand_' + id);

		if (expandNode) {
			expandNode.toggle(!expand);
		}
	}
</aui:script>