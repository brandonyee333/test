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

<%@ include file="/support/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "users");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/select_assigned_to.jsp");
portletURL.setParameter("tabs1", tabs1);

String tabsNames = "users";

if (liferayIncOrg) {
	tabsNames += ",support-teams,partners";
}
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<div class="unit">
		<div class="unit-content table-report">
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
						searchContainer="<%= new PartnerEntrySearch(renderRequest, portletURL) %>"
					>

						<%
						PartnerEntryDisplayTerms displayTerms = (PartnerEntryDisplayTerms)searchContainer.getDisplayTerms();
						PartnerEntrySearchTerms searchTerms = (PartnerEntrySearchTerms)searchContainer.getSearchTerms();
						%>

						<%@ include file="/support/partner_entry_search.jspf" %>

						<liferay-ui:search-container-results>
							<%@ include file="/support/partner_entry_search_results.jspf" %>
						</liferay-ui:search-container-results>

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
							sb.append("'); window.close();");

							String rowHREF = sb.toString();
							%>

							<liferay-ui:search-container-column-text
								href="<%= rowHREF %>"
								property="code"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</c:when>
				<c:when test='<%= tabs1.equals("support-teams") && liferayIncOrg %>'>
					<liferay-ui:search-container
						headerNames="name,accounts,time-zones,locales"
						iteratorURL="<%= portletURL %>"
					>
						<liferay-ui:search-container-results
							results="<%= SupportTeamLocalServiceUtil.getSupportTeams(searchContainer.getStart(), searchContainer.getEnd()) %>"
							total="<%= SupportTeamLocalServiceUtil.getSupportTeamsCount() %>"
						/>

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
							sb.append("'); window.close();");

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

									<span class="aui-helper-hidden collapse" id="<portlet:namespace />collapse_<%= supportTeam.getSupportTeamId() %>">
										<liferay-ui:icon
											image="../arrows/01_minus"
											label="<%= true %>"
											message="collapse"
											url='<%= "javascript:" + renderResponse.getNamespace() + "toggleAccountEntries(" + supportTeam.getSupportTeamId() + ", false);" %>'
										/>
									</span>

									<div class="aui-helper-hidden" id="<portlet:namespace />accountEntries_<%= supportTeam.getSupportTeamId() %>">

										<%
										for (int i = 0; i < accountEntries.size(); i++) {
											AccountEntry accountEntry = accountEntries.get(i);
										%>

											<a href="<%= rowHREF %>"><%= HtmlUtil.escape(accountEntry.getName()) %></a>

											<%= ((i + 1) < accountEntries.size()) ? "<br />" : StringPool.BLANK %>

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

									<%= LanguageUtil.get(pageContext, AccountEntryConstants.getLanguageLabel(languageId)) %><%= ((i + 1) < languageIds.size()) ? "<br />" : "" %>

								<%
								}
								%>

							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</c:when>
				<c:otherwise>

					<%
					LinkedHashMap userParams = new LinkedHashMap();

					if (liferayIncOrg) {
						userParams.put("usersTicketWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByTicketWorker"), null));
					}
					else {
						userParams.put("usersPartnerEntryWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByPartnerEntryWorkers"), user.getUserId()));
					}
					%>

					<liferay-ui:user-search
						portletURL="<%= portletURL %>"
						userParams="<%= userParams %>"
					>

						<%
						SearchContainer userSearchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);
						%>

						<liferay-ui:search-container
							headerNames="name,screen-name,email-address"
							searchContainer="<%= userSearchContainer %>"
						>
							<liferay-ui:search-container-results
								results="<%= userSearchContainer.getResults() %>"
								total="<%= userSearchContainer.getTotal() %>"
							/>

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
								sb.append("'); window.close();");

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

							<liferay-ui:search-iterator />
						</liferay-ui:search-container>
					</liferay-ui:user-search>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />toggleAccountEntries(supportTeamId, expand) {
		var A = AUI();

		if (expand) {
			A.one("#<portlet:namespace />accountEntries_" + supportTeamId).show();
			A.one("#<portlet:namespace />collapse_" + supportTeamId).show();
			A.one("#<portlet:namespace />expand_" + supportTeamId).hide();
		}
		else {
			A.one("#<portlet:namespace />accountEntries_" + supportTeamId).hide();
			A.one("#<portlet:namespace />collapse_" + supportTeamId).hide();
			A.one("#<portlet:namespace />expand_" + supportTeamId).show();
		}
	}
</aui:script>