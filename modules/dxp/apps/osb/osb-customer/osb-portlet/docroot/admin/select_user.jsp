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
String userParam = ParamUtil.getString(request, "userParam");

String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_user.jsp");
portletURL.setParameter("userParam", userParam);
portletURL.setParameter("callback", callback);
%>

<aui:form action="<%= portletURL.toString() %>" method="post" onSubmit="submitForm(this); return false;">
	<div class="unit">
		<div class="unit-content">
			<liferay-ui:tabs
				names="users"
			/>

			<%@ include file="/common/user_search_inputs.jspf" %>

			<%
			LinkedHashMap userParams = new LinkedHashMap();

			if (userParam.equals("accountEntryCreateUsers")) {
				userParams.put(userParam, new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountEntryCreateUser"), StringPool.BLANK));
			}
			else if (userParam.equals("accountEntryModifiedUsers")) {
				userParams.put(userParam, new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountEntryModifiedUser"), StringPool.BLANK));
			}
			else if (userParam.equals("orderEntryCreateUsers")) {
				userParams.put(userParam, new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByOrderEntryCreateUser"), StringPool.BLANK));
			}
			else if (userParam.equals("orderEntryModifiedUsers")) {
				userParams.put(userParam, new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByOrderEntryModifiedUser"), StringPool.BLANK));
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
	</div>
</aui:form>