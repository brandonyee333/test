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
String userSQL = (String)request.getAttribute("userSQL");

String userParam = ParamUtil.getString(request, "userParam");
String firstName = ParamUtil.getString(request, "firstName");
String middleName = ParamUtil.getString(request, "middleName");
String lastName = ParamUtil.getString(request, "lastName");
String screenName = ParamUtil.getString(request, "screenName");
String emailAddress = ParamUtil.getString(request, "emailAddress");

String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/license/select_user");
portletURL.setParameter("userParam", userParam);
portletURL.setParameter("callback", callback);
%>

<c:if test="<%= OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID) %>">
	<aui:form action="<%= portletURL %>" method="post" name="selectUserFm">
		<div class="unit">
			<div class="unit-content">
				<liferay-ui:tabs
					names="users"
				/>

				<%@ include file="/common/user_search_inputs.jspf" %>

				<%
				LinkedHashMap<String, Object> userParams = new LinkedHashMap();

				userParams.put(userParam, new CustomSQLParam(userSQL, StringPool.BLANK));
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
</c:if>