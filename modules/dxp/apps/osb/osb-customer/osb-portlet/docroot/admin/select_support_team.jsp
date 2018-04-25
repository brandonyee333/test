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
String callback = ParamUtil.getString(request, "callback");
long supportTeamId = ParamUtil.getLong(request, "supportTeamId");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_support_team.jsp");
portletURL.setParameter("callback", callback);
portletURL.setParameter("supportTeamId", String.valueOf(supportTeamId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<liferay-ui:tabs
		names="support-teams"
	/>

	<liferay-ui:search-container
		headerNames="name,projects,time-zones,languages"
		iteratorURL="<%= portletURL %>"
		searchContainer="<%= new SupportTeamSearch(renderRequest, portletURL) %>"
	>

		<%
		SupportTeamDisplayTerms displayTerms = (SupportTeamDisplayTerms)searchContainer.getDisplayTerms();
		SupportTeamSearchTerms searchTerms = (SupportTeamSearchTerms)searchContainer.getSearchTerms();
		%>

		<liferay-ui:search-toggle
			buttonLabel="search"
			displayTerms="<%= displayTerms %>"
			id="toggle_id_support_teams_search"
		>
			<aui:fieldset>
				<aui:input name="<%= displayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />

				<aui:select name="<%= displayTerms.TYPE %>">
					<aui:option label="all" selected="<%= Validator.isNull(displayTerms.getType()) %>" value="" />
					<aui:option label="normal" selected="<%= (displayTerms.getType() != null) && (displayTerms.getType() == SupportTeamConstants.TYPE_NORMAL) %>" value="<%= SupportTeamConstants.TYPE_NORMAL %>" />
					<aui:option label="platinum-critical" selected="<%= (displayTerms.getType() != null) && (displayTerms.getType() == SupportTeamConstants.TYPE_PLATINUM_CRITICAL) %>" value="<%= SupportTeamConstants.TYPE_PLATINUM_CRITICAL %>" />
				</aui:select>
			</aui:fieldset>
		</liferay-ui:search-toggle>

		<liferay-ui:search-container-results>

			<%
			if (searchTerms.isAdvancedSearch()) {
				results = SupportTeamLocalServiceUtil.search(searchTerms.getName(), searchTerms.getType(), searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				total = SupportTeamLocalServiceUtil.searchCount(searchTerms.getName(), searchTerms.getType(), searchTerms.isAndOperator());
			}
			else {
				results = SupportTeamLocalServiceUtil.search(searchTerms.getKeywords(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				total = SupportTeamLocalServiceUtil.searchCount(searchTerms.getKeywords());
			}

			searchContainer.setResults(results);
			searchContainer.setTotal(total);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.SupportTeam"
			escapedModel="<%= true %>"
			keyProperty="supportTeamId"
			modelVar="supportTeam"
		>

			<%
			String rowHREF = null;

			if (supportTeam.getSupportTeamId() != supportTeamId) {
				StringBuilder sb = new StringBuilder(8);

				sb.append("javascript:opener.");
				sb.append(renderResponse.getNamespace());
				sb.append(callback);
				sb.append("('");
				sb.append(supportTeam.getSupportTeamId());
				sb.append("', '");
				sb.append(supportTeam.getName());
				sb.append("'); window.close();");

				rowHREF = sb.toString();
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
			>
				<%= supportTeam.getName() %>

				<c:if test="<%= supportTeam.getSupportTeamId() == supportTeamId %>">
					(<liferay-ui:message key="current" />)
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="type"
			>
				<c:choose>
					<c:when test="<%= supportTeam.getType() == SupportTeamConstants.TYPE_PLATINUM_CRITICAL %>">
						<liferay-ui:message key="platinum-critical" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="normal" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-regions"
			>

				<%
				List<SupportRegion> supportRegions = supportTeam.getSupportRegions();
				%>

				<c:if test="<%= !supportRegions.isEmpty() %>">
					<span class="expand" id="<portlet:namespace />expand_<%= supportTeam.getSupportTeamId() %>_supportRegions">
						<liferay-ui:icon
							image="../arrows/01_plus"
							label="<%= true %>"
							message="expand"
							url='<%= "javascript:" + renderResponse.getNamespace() + "toggleSupportRegions(" + supportTeam.getSupportTeamId() + ", true);" %>'
						/>
					</span>
					<span class="collapse hide" id="<portlet:namespace />collapse_<%= supportTeam.getSupportTeamId() %>_supportRegions">
						<liferay-ui:icon
							image="../arrows/01_minus"
							label="<%= true %>"
							message="collapse"
							url='<%= "javascript:" + renderResponse.getNamespace() + "toggleSupportRegions(" + supportTeam.getSupportTeamId() + ", false);" %>'
						/>
					</span>

					<div class="hide" id="<portlet:namespace />supportRegions_<%= supportTeam.getSupportTeamId() %>">

						<%
						for (int i = 0; i < supportRegions.size(); i++) {
							SupportRegion supportRegion = supportRegions.get(i);
						%>

							<aui:a href="<%= rowHREF %>" label="<%= supportRegion.getName() %>" />

							<%= ((i + 1) < supportRegions.size()) ? "<br />" : "" %>

						<%
						}
						%>

					</div>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="projects"
			>

				<%
				List<AccountEntry> accountEntries = supportTeam.getAccountEntries();
				%>

				<c:if test="<%= !accountEntries.isEmpty() %>">
					<span class="expand" id="<portlet:namespace />expand_<%= supportTeam.getSupportTeamId() %>_accountEntries">
						<liferay-ui:icon
							image="../arrows/01_plus"
							label="<%= true %>"
							message="expand"
							url='<%= "javascript:" + renderResponse.getNamespace() + "toggleAccountEntries(" + supportTeam.getSupportTeamId() + ", true);" %>'
						/>
					</span>
					<span class="collapse hide" id="<portlet:namespace />collapse_<%= supportTeam.getSupportTeamId() %>_accountEntries">
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

							<aui:a href="<%= rowHREF %>" label="<%= accountEntry.getName() %>" />

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
</aui:form>

<aui:script>
	function <portlet:namespace />toggleAccountEntries(supportTeamId, expand) {
		var A = AUI();

		if (expand) {
			A.one('#<portlet:namespace />accountEntries_' + supportTeamId).show();
			A.one('#<portlet:namespace />collapse_' + supportTeamId + '_accountEntries').show();
			A.one('#<portlet:namespace />expand_' + supportTeamId + '_accountEntries').hide();
		}
		else {
			A.one('#<portlet:namespace />accountEntries_' + supportTeamId).hide();
			A.one('#<portlet:namespace />collapse_' + supportTeamId + '_accountEntries').hide();
			A.one('#<portlet:namespace />expand_' + supportTeamId + '_accountEntries').show();
		}
	}

	function <portlet:namespace />toggleSupportRegions(supportTeamId, expand) {
		var A = AUI();

		if (expand) {
			A.one('#<portlet:namespace />supportRegions_' + supportTeamId).show();
			A.one('#<portlet:namespace />collapse_' + supportTeamId + '_supportRegions').show();
			A.one('#<portlet:namespace />expand_' + supportTeamId + '_supportRegions').hide();
		}
		else {
			A.one('#<portlet:namespace />supportRegions_' + supportTeamId).hide();
			A.one('#<portlet:namespace />collapse_' + supportTeamId + '_supportRegions').hide();
			A.one('#<portlet:namespace />expand_' + supportTeamId + '_supportRegions').show();
		}
	}
</aui:script>