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
			<liferay-ui:tabs names="users" />

			<%
			LinkedHashMap userParams = new LinkedHashMap();

			if (ticketEntryId > 0) {
				TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);

				AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(ticketEntry.getAccountEntryId());

				OSBCustomSQLParam osbCustomSQLParam = new OSBCustomSQLParam("usersAccountCustomerPartnerWorker", CustomSQLUtil.get("com.liferay.portal.service.persistence.UserFinder.joinByAccountCustomerPartnerWorker"), new Object[] {ticketEntry.getAccountEntryId(), new int[] {AccountCustomerConstants.ROLE_DEVELOPER, AccountCustomerConstants.ROLE_MANAGER}, accountEntry.getPartnerEntryId(), new int[] {PartnerWorkerConstants.ROLE_MANAGER, PartnerWorkerConstants.ROLE_MEMBER}});

				userParams.put("usersAccountCustomerPartnerWorker", osbCustomSQLParam);
			}
			else if (callback.equals("selectCreateUser")) {
				userParams.put("accountCreateUsers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.service.persistence.UserFinder.joinByAccountEntryCreateUser"), StringPool.BLANK));
			}
			else {
				userParams.put("accountModifiedUsers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.service.persistence.UserFinder.joinByAccountEntryModifiedUser"), StringPool.BLANK));
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
						className="com.liferay.portal.model.User"
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

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</liferay-ui:user-search>
		</div>
	</aui:form>
</c:if>