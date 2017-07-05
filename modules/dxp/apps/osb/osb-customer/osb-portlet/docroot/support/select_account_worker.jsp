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

<c:if test="<%= liferayIncOrg %>">

	<%
	String tabs1 = ParamUtil.getString(request, "tabs1");

	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/select_account_worker.jsp");
	portletURL.setParameter("tabs1", tabs1);
	%>

	<aui:form action="<%= portletURL.toString() %>" method="post" name="<portlet:namespace />fm" onSubmit="submitForm(this); return false;">
		<div class="unit">
			<div class="unit-content table-report">
				<liferay-ui:tabs
					names="advocacy-specialist,managed-services,sales"
					param="tabs1"
					tabsValues='<%= AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST + "," + AccountWorkerConstants.ROLE_MANAGED_SERVICES + "," + AccountWorkerConstants.ROLE_SALES %>'
					url="<%= portletURL.toString() %>"
				/>

				<%
				long role = GetterUtil.getLong(tabs1, AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST);

				LinkedHashMap userParams = new LinkedHashMap();

				userParams.put("usersAccountWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountWorkerRole"), role));
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

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</liferay-ui:user-search>
			</div>
		</div>
	</aui:form>
</c:if>