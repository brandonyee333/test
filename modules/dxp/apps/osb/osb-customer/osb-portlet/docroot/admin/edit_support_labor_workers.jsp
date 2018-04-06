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
String tabs3 = ParamUtil.getString(request, "tabs3", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

long supportLaborId = ParamUtil.getLong(request, "supportLaborId");

SupportLabor supportLabor = SupportLaborLocalServiceUtil.getSupportLabor(supportLaborId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_support_labor_workers.jsp");
portletURL.setParameter("tabs3", tabs3);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("supportLaborId", String.valueOf(supportLaborId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />supportLaborId" type="hidden" value="<%= supportLaborId %>" />

	<liferay-ui:message key="assign-support-workers-to" />: <%= HtmlUtil.escape(supportLabor.getName()) %>

	<br /><br />

	<input name="<portlet:namespace />addSupportWorkerIds" type="hidden" value="" />
	<input name="<portlet:namespace />removeSupportWorkerIds" type="hidden" value="" />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="current,available"
		param="tabs3"
		url="<%= portletURL.toString() %>"
	/>

	<liferay-ui:search-container
		headerNames="name,email-address,support-team"
		iteratorURL="<%= portletURL %>"
		rowChecker="<%= new UserSupportLaborChecker(renderResponse, supportLabor) %>"
		searchContainer="<%= new SupportWorkerSearch(renderRequest, portletURL) %>"
	>

		<%
		SupportWorkerDisplayTerms displayTerms = (SupportWorkerDisplayTerms)searchContainer.getDisplayTerms();
		SupportWorkerSearchTerms searchTerms = (SupportWorkerSearchTerms)searchContainer.getSearchTerms();
		%>

		<liferay-ui:search-toggle
			buttonLabel="search"
			displayTerms="<%= displayTerms %>"
			id="toggle_id_support_workers_search"
		>
			<aui:fieldset>
				<aui:input name="<%= displayTerms.FIRST_NAME %>" size="20" value="<%= displayTerms.getFirstName() %>" />

				<aui:input name="<%= displayTerms.MIDDLE_NAME %>" size="20" value="<%= displayTerms.getMiddleName() %>" />

				<aui:input name="<%= displayTerms.LAST_NAME %>" size="20" value="<%= displayTerms.getLastName() %>" />

				<aui:input name="<%= displayTerms.SCREEN_NAME %>" size="20" value="<%= displayTerms.getScreenName() %>" />

				<aui:input name="<%= displayTerms.EMAIL_ADDRESS %>" size="20" value="<%= displayTerms.getEmailAddress() %>" />

				<aui:input name="<%= displayTerms.SUPPORT_TEAM_NAME %>" size="20" value="<%= displayTerms.getSupportTeamName() %>" />
			</aui:fieldset>
		</liferay-ui:search-toggle>

		<liferay-ui:search-container-results>

			<%
			long searchSupportLaborId = 0;

			if (tabs3.equals("current")) {
				searchSupportLaborId = supportLabor.getSupportLaborId();
			}

			if (searchTerms.isAdvancedSearch()) {
				results = SupportWorkerLocalServiceUtil.search(searchSupportLaborId, searchTerms.getFirstName(), searchTerms.getMiddleName(), searchTerms.getLastName(), searchTerms.getScreenName(), searchTerms.getEmailAddress(), searchTerms.getSupportTeamName(), searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				total = SupportWorkerLocalServiceUtil.searchCount(searchSupportLaborId, searchTerms.getFirstName(), searchTerms.getMiddleName(), searchTerms.getLastName(), searchTerms.getScreenName(), searchTerms.getEmailAddress(), searchTerms.getSupportTeamName(), searchTerms.isAndOperator());
			}
			else {
				results = SupportWorkerLocalServiceUtil.search(searchSupportLaborId, searchTerms.getKeywords(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				total = SupportWorkerLocalServiceUtil.searchCount(searchSupportLaborId, searchTerms.getKeywords());
			}

			searchContainer.setResults(results);
			searchContainer.setTotal(total);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.SupportWorker"
			escapedModel="<%= true %>"
			keyProperty="supportWorkerId"
			modelVar="supportWorker"
		>

			<%
			User curUser = null;

			try {
				curUser = UserLocalServiceUtil.getUser(supportWorker.getUserId());
			}
			catch (NoSuchUserException e) {
			}

			if (Validator.isNull(curUser) || !curUser.isActive()) {
				row.setClassName("inactive");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name"
				value='<%= (curUser != null) ? HtmlUtil.escape(curUser.getFullName()) : "" %>'
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
				value='<%= (curUser != null) ? curUser.getEmailAddress() : "" %>'
			/>

			<%
			SupportTeam supportTeam = SupportTeamLocalServiceUtil.getSupportTeam(supportWorker.getSupportTeamId());
			%>

			<liferay-ui:search-container-column-text
				name="support-team"
				value="<%= HtmlUtil.escape(supportTeam.getName()) %>"
			/>

			<c:if test='<%= tabs3.equals("current") && (supportWorker != null) %>'>
				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu>
						<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/admin/edit_support_worker.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="supportWorkerId" value="<%= String.valueOf(supportWorker.getSupportWorkerId()) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							message="edit"
							url="<%= editURL %>"
						/>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<aui:button onClick='<%= renderResponse.getNamespace() + "updateSupportLaborWorkers('" + portletURL.toString() + "&" + renderResponse.getNamespace() + "cur=" + cur + "');" %>' value="update-associations" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateSupportLaborWorkers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addSupportWorkerIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeSupportWorkerIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateSupportLaborWorkers"><portlet:param name="mvcPath" value="/admin/edit_support_worker_labor_workers.jsp" /><portlet:param name="tabs3" value="<%= tabs3 %>" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>