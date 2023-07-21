<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs3 = (String)request.getAttribute("edit_role_assignments.jsp-tabs3");

int cur = (Integer)request.getAttribute("edit_role_assignments.jsp-cur");

Role role = (Role)request.getAttribute("edit_role_assignments.jsp-role");

String displayStyle = (String)request.getAttribute("edit_role_assignments.jsp-displayStyle");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_role_assignments.jsp-portletURL");

EmptyOnClickRowChecker rowChecker = new EmptyOnClickRowChecker(renderResponse);

if (tabs3.equals("available")) {
	rowChecker = new GroupRoleChecker(renderResponse, role);
}
%>

<liferay-ui:search-container
	id="assigneesSearch"
	rowChecker="<%= rowChecker %>"
	searchContainer="<%= new GroupSearch(renderRequest, portletURL) %>"
>

	<%
	GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

	if (!searchTerms.isSearch()) {
		searchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header-has-plus-btn");
	}

	LinkedHashMap<String, Object> groupParams = new LinkedHashMap<String, Object>();

	groupParams.put("site", Boolean.TRUE);

	if (tabs3.equals("current")) {
		groupParams.put("groupsRoles", Long.valueOf(role.getRoleId()));
	}

	total = GroupLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), groupParams);

	searchContainer.setTotal(total);
	%>

	<liferay-ui:search-container-results
		results="<%= GroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
	/>

	<%
	portletURL.setParameter("cur", String.valueOf(cur));
	%>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Group"
		escapedModel="<%= true %>"
		keyProperty="groupId"
		modelVar="group"
		rowIdProperty="friendlyURL"
	>
		<%@ include file="/site_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="<%= displayStyle %>"
		markupView="lexicon"
	/>
</liferay-ui:search-container>