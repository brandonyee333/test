<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs3 = (String)request.getAttribute("edit_role_assignments.jsp-tabs3");

Role role = (Role)request.getAttribute("edit_role_assignments.jsp-role");

String displayStyle = (String)request.getAttribute("edit_role_assignments.jsp-displayStyle");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_role_assignments.jsp-portletURL");

EmptyOnClickRowChecker rowChecker = new UnsetUserRoleChecker(renderResponse, role);

if (tabs3.equals("available")) {
	rowChecker = new SetUserRoleChecker(renderResponse, role);
}
%>

<liferay-ui:membership-policy-error />

<liferay-ui:search-container
	id="assigneesSearch"
	rowChecker="<%= rowChecker %>"
	searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
	var="userSearchContainer"
>

	<%
	UserSearchTerms searchTerms = (UserSearchTerms)userSearchContainer.getSearchTerms();

	if (!searchTerms.isSearch()) {
		userSearchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header-has-plus-btn");
	}

	LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

	if (tabs3.equals("current")) {
		userParams.put("usersRoles", Long.valueOf(role.getRoleId()));
	}
	%>

	<liferay-ui:user-search-container-results
		userParams="<%= userParams %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="user2"
		rowIdProperty="screenName"
	>
		<%@ include file="/user_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="<%= displayStyle %>"
		markupView="lexicon"
	/>
</liferay-ui:search-container>