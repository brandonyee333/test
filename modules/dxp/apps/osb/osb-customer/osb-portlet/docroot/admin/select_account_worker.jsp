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

<c:if test="<%= OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID) %>">

	<%
	String tabs1 = ParamUtil.getString(request, "tabs1");

	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/admin/select_account_worker.jsp");
	portletURL.setParameter("tabs1", tabs1);
	%>

	<aui:form action="<%= portletURL.toString() %>" method="post" onSubmit="submitForm(this); return false;">
		<div class="unit">
			<div class="table-report unit-content">
				<liferay-ui:tabs
					names="advocacy-specialist,managed-services,sales"
					param="tabs1"
					tabsValues='<%= AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST + "," + AccountWorkerConstants.ROLE_MANAGED_SERVICES + "," + AccountWorkerConstants.ROLE_SALES %>'
					url="<%= portletURL.toString() %>"
				/>

				<%@ include file="/common/user_search_inputs.jspf" %>

				<%
				long role = GetterUtil.getLong(tabs1, AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST);

				LinkedHashMap userParams = new LinkedHashMap();

				userParams.put("usersAccountWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountWorkerRole"), role));
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
						sb.append("selectAccountWorker('");
						sb.append(curUser.getUserId());
						sb.append("', '");
						sb.append(UnicodeFormatter.toString(curUser.getFullName()));
						sb.append("', '");
						sb.append(role);
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

					<liferay-ui:search-iterator
						markupView="lexicon"
					/>
				</liferay-ui:search-container>
			</div>
		</div>
	</aui:form>
</c:if>