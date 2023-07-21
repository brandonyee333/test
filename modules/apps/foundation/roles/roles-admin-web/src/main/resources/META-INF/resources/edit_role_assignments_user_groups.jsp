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

EmptyOnClickRowChecker rowChecker = new EmptyOnClickRowChecker(renderResponse);

if (tabs3.equals("available")) {
	rowChecker = new UserGroupRoleChecker(renderResponse, role);
}
%>

<liferay-ui:search-container
	id="assigneesSearch"
	rowChecker="<%= rowChecker %>"
	searchContainer="<%= new UserGroupSearch(renderRequest, portletURL) %>"
>

	<%
	UserGroupDisplayTerms searchTerms = (UserGroupDisplayTerms)searchContainer.getSearchTerms();

	if (!searchTerms.isSearch()) {
		searchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header-has-plus-btn");
	}

	LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<String, Object>();

	if (tabs3.equals("current")) {
		userGroupParams.put(UserGroupFinderConstants.PARAM_KEY_USER_GROUPS_ROLES, Long.valueOf(role.getRoleId()));
	}
	%>

	<liferay-ui:user-group-search-container-results
		searchTerms="<%= searchTerms %>"
		userGroupParams="<%= userGroupParams %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.UserGroup"
		escapedModel="<%= true %>"
		keyProperty="group.groupId"
		modelVar="userGroup"
	>
		<%@ include file="/user_group_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="<%= displayStyle %>"
		markupView="lexicon"
	/>
</liferay-ui:search-container>