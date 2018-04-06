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
long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/select_user.jsp");
portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntryId));
portletURL.setParameter("callback", callback);
%>

<c:if test="<%= (ticketEntryId > 0) || liferayIncOrg %>">
	<aui:form action="<%= portletURL.toString() %>" method="post" name="fm" onSubmit="submitForm(this); return false;">
		<div class="unit">
			<liferay-ui:tabs
				names="users"
			/>

			<%@ include file="/common/user_search_inputs.jspf" %>

			<%
			LinkedHashMap userParams = new LinkedHashMap();

			if (ticketEntryId > 0) {
				TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);

				AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(ticketEntry.getAccountEntryId());

				OSBCustomSQLParam osbCustomSQLParam = new OSBCustomSQLParam("usersAccountCustomerPartnerWorker", CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountCustomerPartnerWorker"), new Object[] {ticketEntry.getAccountEntryId(), new int[] {AccountCustomerConstants.ROLE_DEVELOPER, AccountCustomerConstants.ROLE_MANAGER}, accountEntry.getPartnerEntryId(), new int[] {PartnerWorkerConstants.ROLE_MANAGER, PartnerWorkerConstants.ROLE_MEMBER}});

				userParams.put("usersAccountCustomerPartnerWorker", osbCustomSQLParam);
			}
			else if (callback.equals("selectCreateUser")) {
				userParams.put("accountCreateUsers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountEntryCreateUser"), StringPool.BLANK));
			}
			else {
				userParams.put("accountModifiedUsers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountEntryModifiedUser"), StringPool.BLANK));
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
					keyProperty="userId"
					modelVar="curUser"
				>

					<%
					StringBundler sb = new StringBundler(8);

					sb.append("javascript:opener.");
					sb.append(renderResponse.getNamespace());
					sb.append(callback);
					sb.append("('");
					sb.append(curUser.getUserId());
					sb.append("', '");
					sb.append(UnicodeFormatter.toString(curUser.getFullName()));
					sb.append("'); window.close();");

					String rowHREF = sb.toString();
					%>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="name"
						value="<%= HtmlUtil.escape(curUser.getFullName()) %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="screen-name"
						value="<%= HtmlUtil.escape(curUser.getScreenName()) %>"
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
	</aui:form>
</c:if>