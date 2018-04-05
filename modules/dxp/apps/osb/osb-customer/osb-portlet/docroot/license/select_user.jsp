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
String userParam = ParamUtil.getString(request, "userParam");

String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/select_user.jsp");
portletURL.setParameter("userParam", userParam);
portletURL.setParameter("callback", callback);
%>

<c:if test="<%= OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID) %>">
	<aui:form action="<%= portletURL.toString() %>" method="post" name="selectUserfm">
		<div class="unit">
			<div class="unit-content">
				<liferay-ui:tabs
					names="users"
				/>

				<%@ include file="/common/user_search_inputs.jspf" %>

				<%
				LinkedHashMap userParams = new LinkedHashMap();

				if (userParam.equals("licenseKeyCreateUsers")) {
					userParams.put(userParam, new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByLicenseKeyCreateUser"), StringPool.BLANK));
				}
				else if (userParam.equals("licenseKeyModifiedUsers")) {
					userParams.put(userParam, new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByLicenseKeyModifiedUser"), StringPool.BLANK));
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

					<liferay-ui:search-iterator markupView="lexicon" />
				</liferay-ui:search-container>
			</div>
		</div>
	</aui:form>
</c:if>