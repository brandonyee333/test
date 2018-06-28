<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");

PartnerEntry partnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(partnerEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_partner_entry_workers.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<aui:input name="assignmentsRedirect" type="hidden" value="" />
	<aui:input name="partnerEntryId" type="hidden" value="<%= partnerEntryId %>" />

	<liferay-ui:message arguments="<%= partnerEntry.getCode() %>" key="edit-workers-for-partner-x" />

	<br /><br />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="users"
	/>

	<liferay-ui:error exception="<%= RemoteServiceException.class %>">

		<%
		RemoteServiceException rse = (RemoteServiceException)errorException;
		%>

		<liferay-ui:message key="unable-to-sync-to-web.liferay.com" />

		<br />

		<%= rse.getMessage() %>
	</liferay-ui:error>

	<aui:input name="addUserIds" type="hidden" value="" />
	<aui:input name="removeUserIds" type="hidden" value="" />

	<liferay-ui:tabs
		names="current,available"
		param="tabs2"
		url="<%= portletURL.toString() %>"
	/>

	<%@ include file="/common/user_search_inputs.jspf" %>

	<%
	LinkedHashMap userParams = new LinkedHashMap();

	if (tabs2.equals("current")) {
		userParams.put("usersPartnerWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByPartnerWorker"), Long.valueOf(partnerEntry.getPartnerEntryId())));
	}
	%>

	<liferay-ui:search-container
		emptyResultsMessage="no-users-were-found"
		id="usersSearchContainer"
		iteratorURL="<%= portletURL %>"
		rowChecker="<%= new UserPartnerWorkerChecker(renderResponse, partnerEntry) %>"
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
			PartnerWorker partnerWorker = null;

			int role = 0;
			int notifications = 0;

			try {
				partnerWorker = PartnerWorkerLocalServiceUtil.getPartnerWorker(curUser.getUserId(), partnerEntryId);

				role = partnerWorker.getRole();
				notifications = partnerWorker.getNotifications();
			}
			catch (Exception e) {
			}

			if (!curUser.isActive()) {
				row.setClassName("inactive");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				name="screen-name"
				property="screenName"
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
				property="emailAddress"
			/>

			<liferay-ui:search-container-column-text
				name="notifications"
			>
				<aui:select disabled="<%= !curUser.isActive() %>" label="" name='<%= "notifications_" + curUser.getUserId() %>'>

					<%
					for (int i = 1; i <= 2; i++) {
					%>

						<aui:option label="<%= PartnerWorkerConstants.getNotificationsLabel(i) %>" selected="<%= notifications == i %>" value="<%= i %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<aui:select disabled="<%= !curUser.isActive() %>" label="" name='<%= "role_" + curUser.getUserId() %>'>
					<aui:option />

					<%
					for (int i = 1; i <= 3; i++) {
					%>

						<aui:option label="<%= PartnerWorkerConstants.getRoleLabel(i) %>" selected="<%= role == i %>" value="<%= i %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user-status"
			>
				<%= RoleLocalServiceUtil.hasUserRole(curUser.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) ? "" : LanguageUtil.get(request, "unverified") %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<aui:button onClick='<%= renderResponse.getNamespace() + "updatePartnerWorkers('" + portletURL.toString() + "&" + renderResponse.getNamespace() + "cur=" + cur + "');" %>' value="update-associations" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updatePartnerWorkers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');
			submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="updatePartnerWorkers"><portlet:param name="mvcPath" value="/admin/edit_partner_entry_workers.jsp" /></portlet:actionURL>');
		},
		['liferay-util-list-fields']
	);
</aui:script>